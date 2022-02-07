package com.example.coviddataapp.dto;

import java.time.LocalDate;

public class CountryDataDTO {

    private LocalDate date;
    private String countryName;
    private String infections;
    private String deaths;
    private String recovered;
    private String deathRate;
    private String recoveredRate;
    private String infectionRate;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getInfections() {
        return infections;
    }

    public void setInfections(String infections) {
        this.infections = infections;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(String deathRate) {
        this.deathRate = deathRate;
    }

    public String getRecoveredRate() {
        return recoveredRate;
    }

    public void setRecoveredRate(String recoveredRate) {
        this.recoveredRate = recoveredRate;
    }

    public String getInfectionRate() {
        return infectionRate;
    }

    public void setInfectionRate(String infectionRate) {
        this.infectionRate = infectionRate;
    }
}
