package com.westeros.updater.updater;


import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.IMoviesClient;

import java.time.LocalDate;

public class MoviesUpdater implements IUpdateMovies{

    private final ICatalogData dbCatalog;
    private final IMoviesClient moviesClient;

    public MoviesUpdater(ICatalogData dbCatalog, IMoviesClient moviesClient) {
        this.dbCatalog = dbCatalog;
        this.moviesClient = moviesClient;
    }

    @Override
    public void updateByDateRange(LocalDate from, LocalDate to) {

    }
}
