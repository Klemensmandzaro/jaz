package com.westeros.data.repositories;

import com.westeros.data.model.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
