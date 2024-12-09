package com.westeros.data.repositories;

import com.westeros.data.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsRepository extends JpaRepository<Actor, Long> {
}
