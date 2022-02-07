package com.example.coviddataapp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CountryData")
public class CountryDataBO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "pays")
    private String countryName;

    @Column(name = "infections")
    private String infections;

    @Column(name = "deces")
    private String deaths;

    @Column(name = "guerisons")
    private String recovered;

    @Column(name = "tauxDeces")
    private String deathRate;

    @Column(name = "tauxGuerison")
    private String recoveredRate;

    @Column(name = "tauxInfection")
    private String infectionRate;

    public CountryDataBO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
