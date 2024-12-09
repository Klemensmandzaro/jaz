package com.westeros.moviesclient;

import com.westeros.moviesclient.contract.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Component
public class MoviesDistionariesClient implements IMoviesDictionariesClient {
    IMoviesClientSettings settings;
    RestTemplate restClient;

    public MoviesDistionariesClient(IMoviesClientSettings settings) {
        restClient = new RestTemplate();
        this.settings = settings;
    }

    @Override
    public List<Dictionaries.LanguageDto> getLanguages() {
        String url = settings.getUrlBuilder()
                .pathSegment("configuration", "languages")
                .build()
                .toString();
        ResponseEntity<Dictionaries.LanguageDto[]> response =
                restClient.getForEntity(url, Dictionaries.LanguageDto[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<Dictionaries.CountryDto> getCountries() {
        String url = settings.getUrlBuilder()
                .pathSegment("configuration", "countries")
                .build()
                .toUriString();

        ResponseEntity<Dictionaries.CountryDto[]> response = restClient.getForEntity(url, Dictionaries.CountryDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<Dictionaries.GenreDto> getGenres() {
        String url = settings.getUrlBuilder()
                .pathSegment("genre")
                .pathSegment("movie")
                .pathSegment("list")
                .build()
                .toUriString();
        ResponseEntity<Dictionaries.GenreDto[]> response =
                restClient.getForEntity(url, Dictionaries.GenreDto[].class);

        return Arrays.asList(response.getBody());
    }
}
