package com.westeros.updater.controllers;

import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesDictionariesClient;
import com.westeros.updater.updater.MoviesUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdaterConfiguration {

    @Bean
    public MoviesUpdater moviesUpdater(ICatalogData dbCatalog,
                                       IMoviesClient moviesClient,
                                       IMoviesDictionariesClient moviesDictionariesClient) {
        return new MoviesUpdater(dbCatalog, moviesClient, moviesDictionariesClient);
    }
}
