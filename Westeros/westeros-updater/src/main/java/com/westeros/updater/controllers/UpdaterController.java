package com.westeros.updater.controllers;

import com.westeros.updater.updater.IUpdateMovies;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/updater")
public class UpdaterController {

    private final IUpdateMovies moviesUpdater;

    public UpdaterController(IUpdateMovies moviesUpdater) {
        this.moviesUpdater = moviesUpdater;
    }

    @GetMapping("/start")
    public ResponseEntity<String> start(
            @RequestParam("from") LocalDate from,
            @RequestParam("to") LocalDate to) {

        new Thread(() -> moviesUpdater.updateByDateRange(from, to)).start();

        return ResponseEntity.ok("Update started for date range: " + from + " to " + to);
    }
}
