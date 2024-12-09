package com.westeros;

import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesClientUriBuilderProvider;
import com.westeros.moviesclient.MoviesClient;
import com.westeros.moviesclient.MoviesClientUriBuilderProvider;
import com.westeros.moviesclient.contract.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    /**
     * Proszę uzupełnić te pole kluczem do api
     */
    static String API_KEY = "44eebdfb151cd2dd23c9dbb7e8933647";

    public static void main(String[] args){

        IMoviesClientUriBuilderProvider urlBuilderProvider = new MoviesClientUriBuilderProvider(API_KEY, "api.themoviedb.org", 3);
        IMoviesClient moviesClient = new MoviesClient(urlBuilderProvider);
        CheckItOut(moviesClient);

        urlBuilderProvider.builder()
                .pathSegment("discover")
                .pathSegment("movie")
                .queryParam("primary_release_date.gte", LocalDate.now().minusMonths(1))
                .queryParam("primary_release_date.lte", LocalDate.now())
                .build()
                .toUriString();
    }

    public static void CheckItOut(IMoviesClient moviesClient) {
        var from = LocalDate.now().minusMonths(1);
        var to = LocalDate.now();


        /**
         * pobieram pierwszą strone wyników filmów które zostały wyprodukowane w zeszłym miesiącu
         */
        PagedResultDto result = moviesClient.getByDateRange(from, to);
        var movies = result.movies();

        /**
         * pobieram wszystkie strony wyników
         */
        for (int page = 1; page <= result.totalPages(); page++){
            movies.addAll(moviesClient.getByDateRange(from, to, page).movies());
        }
        var detailedMovies = new ArrayList<MovieDto>();
        var allCredits = new ArrayList<CreditsDto>();

        for (var movie : movies)
        {
            /**
             * dla każdego filmu pobieram jego szczegóły
             */
            MovieDto detailedMovie = moviesClient.getMovie(movie.id());
            detailedMovies.add(detailedMovie);

            /**
             * dla każdego filmu pobieram informacje o zespole i aktorach, który tworzył dany film
             */
            CreditsDto credits = moviesClient.getCredits(movie.id());
            allCredits.add(credits);

            /**
             * dla każdego aktora pobieram jego szczegółowe informacje
             */
            for (var actorSummary :
                    credits.cast()) {
                ActorDto detailedActor = moviesClient.getActorDetails(actorSummary.id());
            }
        }
    }
}
