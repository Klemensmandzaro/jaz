package com.westeros.updater;

import com.westeros.data.model.Company;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.IMoviesClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication(scanBasePackages = {"com.westeros"})
public class WesterosUpdaterApplication implements CommandLineRunner {

    private final IMoviesClient client;
    private final ICatalogData db;

    public WesterosUpdaterApplication(IMoviesClient client, ICatalogData db) {
        this.client = client;
        this.db = db;
    }

    public static void main(String[] args) {
        SpringApplication.run(WesterosUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        db.getCompanies();
    }
}
