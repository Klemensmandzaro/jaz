package com.westeros.data.repositories;

import com.westeros.data.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository<Character, Long> {
}
