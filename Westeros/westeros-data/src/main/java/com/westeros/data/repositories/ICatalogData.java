package com.westeros.data.repositories;


public interface ICatalogData {
    CompanyRepository getCompanies();
    MoviesRepository getMovies();
    ActorsRepository getActors();
    CharactersRepository getCharacters();
    CountryRepository getCountries();
    GenreRepository getGenres();
    LanguageRepository getLanguages();

}
