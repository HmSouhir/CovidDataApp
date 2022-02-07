package com.example.coviddataapp.repositories;

import com.example.coviddataapp.entities.CountryDataBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DataRepository extends JpaRepository<CountryDataBO, Long> {

    Optional<List<CountryDataBO>> findAllByCountryName(String name);

    Optional<List<CountryDataBO>> findAllByCountryNameAndDate(String name, LocalDate date);
}
