package com.example.coviddataapp.controllers;

import com.example.coviddataapp.dto.CountryDataDTO;
import com.example.coviddataapp.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    CovidDataService covidDataService;

    /**
     * This API returns data of the given country
     * @param countryName
     * @return
     */
    @GetMapping("/oneCountryData")
    public ResponseEntity<List<CountryDataDTO>> getCountryByName(@RequestParam("countryName") String countryName) {
        List<CountryDataDTO> countries = covidDataService.getByName(countryName);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * This API returns data of the given country on the given date
     * @param countryName
     * @param date
     * @return
     */
    @GetMapping("/oneCountryDataWithDate")
    public ResponseEntity<List<CountryDataDTO>> getCountryByNameAndDate(@RequestParam("countryName") String countryName,
                                                                       @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)  {
        List<CountryDataDTO> countries = covidDataService.getByNameAndDate(countryName, date);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * This API returns data the given country on the current date
     * @param countryName
     * @return
     */
    @GetMapping("/todayCountryData")
    public ResponseEntity<List<CountryDataDTO>> getTodayCountryData(@RequestParam("countryName") String countryName) {
        List<CountryDataDTO> countries = covidDataService.getByNameAndDate(countryName, LocalDate.now());
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

}
