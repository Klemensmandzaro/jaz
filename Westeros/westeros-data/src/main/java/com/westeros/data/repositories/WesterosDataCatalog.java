package com.westeros.data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class WesterosDataCatalog implements ICatalogData {

    private final CompanyRepository companies;
    private final MoviesRepository movies;


    public WesterosDataCatalog(CompanyRepository companies, MoviesRepository movies) {
        this.companies = companies;
        this.movies = movies;
    }

    @Override
    public CompanyRepository getCompanies() {
        return companies;
    }

    @Override
    public MoviesRepository getMovies() {
        return movies;
    }
}
