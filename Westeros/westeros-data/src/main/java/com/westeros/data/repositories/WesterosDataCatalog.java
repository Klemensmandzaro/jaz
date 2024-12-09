package com.westeros.data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class WesterosDataCatalog implements ICatalogData {

    private final CompanyRepository companies;
    private final MoviesRepository movies;
    private final ActorsRepository actors;
    private final CharactersRepository characters;
    private final CountryRepository country;
    private final GenreRepository genres;
    private final LanguageRepository languages;


    public WesterosDataCatalog(CompanyRepository companies, MoviesRepository movies, ActorsRepository actors, CharactersRepository characters, CountryRepository country, GenreRepository genres, LanguageRepository languages) {
        this.companies = companies;
        this.movies = movies;
        this.actors = actors;
        this.characters = characters;
        this.country = country;
        this.genres = genres;
        this.languages = languages;
    }

    @Override
    public CompanyRepository getCompanies() {
        return companies;
    }

    @Override
    public MoviesRepository getMovies() {
        return movies;
    }

    @Override
    public ActorsRepository getActors() {
        return actors;
    }

    @Override
    public CharactersRepository getCharacters() {
        return characters;
    }

    @Override
    public CountryRepository getCountries() {
        return country;
    }

    @Override
    public GenreRepository getGenres() {
        return genres;
    }

    @Override
    public LanguageRepository getLanguages() {
        return languages;
    }


}
