package com.westeros.updater.updater;


import com.westeros.data.model.*;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesDictionariesClient;
import com.westeros.moviesclient.contract.ActorDto;
import com.westeros.moviesclient.contract.Dictionaries;
import com.westeros.moviesclient.contract.MovieDto;
import com.westeros.moviesclient.contract.PagedResultDto;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;

public class MoviesUpdater implements IUpdateMovies{

    private final ICatalogData dbCatalog;
    private final IMoviesClient moviesClient;
    private final IMoviesDictionariesClient moviesDictionariesClient;

    public MoviesUpdater(ICatalogData dbCatalog, IMoviesClient moviesClient, IMoviesDictionariesClient moviesDictionariesClient) {
        this.dbCatalog = dbCatalog;
        this.moviesClient = moviesClient;

        this.moviesDictionariesClient = moviesDictionariesClient;
    }

    @Override
    public void updateByDateRange(LocalDate from, LocalDate to) {
        List<Dictionaries.CountryDto> countries = moviesDictionariesClient.getCountries();
        for (Dictionaries.CountryDto country : countries) {
            Country temporaryCountry = new Country();
            BeanUtils.copyProperties(country, temporaryCountry);
            dbCatalog.getCountries().save(temporaryCountry);
        }

        List<Dictionaries.GenreDto> genres = moviesDictionariesClient.getGenres();
        for (Dictionaries.GenreDto genre : genres) {
            Genre temporaryGenre = new Genre();
            BeanUtils.copyProperties(genre, temporaryGenre);
            dbCatalog.getGenres().save(temporaryGenre);
        }


        List<Dictionaries.LanguageDto> languages = moviesDictionariesClient.getLanguages();
        for (Dictionaries.LanguageDto language : languages) {
            Language temporaryLanguage = new Language();
            BeanUtils.copyProperties(language, temporaryLanguage);
            dbCatalog.getLanguages().save(temporaryLanguage);
        }

        PagedResultDto response = moviesClient.getByDateRange(from, to);
        for (PagedResultDto.MovieSummaryDto summaryDto : response.getMovies())
        {
            MovieDto movieDto = moviesClient.getMovie(summaryDto.getId());
            for(MovieDto.CompanySummaryDto companySummaryDto: movieDto.getProductionCompanies()){
                Company company = new Company();
                BeanUtils.copyProperties(companySummaryDto, company);
                dbCatalog.getCompanies().save(company);
            }

            Movie movie = new Movie();
            BeanUtils.copyProperties(summaryDto, movie);
            dbCatalog.getMovies().save(movie);
        }

    }
}
