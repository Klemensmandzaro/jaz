package com.westeros.moviesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dictionaries {
    public record GenreDto(int id, String name){}
    public record CountryDto(
            @JsonProperty("english_name") String englishName,
            @JsonProperty("native_name") String nativeName,
            @JsonProperty("iso_3166_1") String isoCode){}
    public record LanguageDto(String name,
                              @JsonProperty("english_name") String englishName,
                              @JsonProperty("iso_639_1") String isoCode){}
}
