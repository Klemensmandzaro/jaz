package com.westeros.moviesclient;

import com.westeros.moviesclient.contract.Dictionaries;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
public interface IMoviesDictionariesClient {
    List<Dictionaries.LanguageDto> getLanguages();
    List<Dictionaries.CountryDto> getCountries();
    List<Dictionaries.GenreDto> getGenres();
}
