package com.westeros.updater.updater;

import java.time.LocalDate;

public interface IUpdateMovies {

    void updateByDateRange(LocalDate from, LocalDate to);
}
