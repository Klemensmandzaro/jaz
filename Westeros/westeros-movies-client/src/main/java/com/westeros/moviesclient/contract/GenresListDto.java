package com.westeros.moviesclient.contract;

import com.westeros.moviesclient.contract.Dictionaries;

import java.util.List;

public class GenresListDto {

    public List<Dictionaries.GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<Dictionaries.GenreDto> genres) {
        this.genres = genres;
    }

    private List<Dictionaries.GenreDto> genres;
}
