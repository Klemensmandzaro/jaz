package com.westeros.data.repositories;

import com.westeros.data.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long> {
}
