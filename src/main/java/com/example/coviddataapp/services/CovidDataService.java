package com.example.coviddataapp.services;

import com.example.coviddataapp.dto.CountryDataDTO;
import com.example.coviddataapp.entities.CountryDataBO;
import com.example.coviddataapp.repositories.DataRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CovidDataService {

    @Value("${resource.url}")
    String RESOURCE_URL;

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    DataRepository dataRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * This method returns the given country data
     * @param countryName
     * @return
     */
    public List<CountryDataDTO> getByName(String countryName) {
        Optional<List<CountryDataBO>> countriesOptional = dataRepository.findAllByCountryName(countryName);
        List<CountryDataDTO> countries = new ArrayList<>();
        if(countriesOptional.isPresent()){
            for(CountryDataBO countryData : countriesOptional.get()){
                CountryDataDTO countryDataDTO = modelMapper.map(countryData, CountryDataDTO.class);
                countries.add(countryDataDTO);
            }
        }

        return countries;
    }

    /**
     * This method returns the given country data on the given date
     * @param countryName
     * @param date
     * @return
     */
    public List<CountryDataDTO> getByNameAndDate(String countryName, LocalDate date) {
        Optional<List<CountryDataBO>> countriesOptional = dataRepository.findAllByCountryNameAndDate(countryName, date);
        List<CountryDataDTO> countries = new ArrayList<>();
        if(countriesOptional.isPresent()){
            for(CountryDataBO countryData : countriesOptional.get()){
                CountryDataDTO countryDataDTO = modelMapper.map(countryData, CountryDataDTO.class);
                countries.add(countryDataDTO);
            }
        }

        return countries;
    }

    /**
     * This Method is scheduled to be executed every 3 hours
     * It imports the csv file data into database
     * @throws IOException
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000, initialDelay = 0)
    public void uploadCsvData() throws IOException {

        // retrieve covid data from coronavirus.politologue.com website
        ResponseEntity<Resource> response = restTemplate.getForEntity(RESOURCE_URL, Resource.class);

        // write covid data to csv file
        File targetFile = new File("covid-data.csv");
        InputStream is = Objects.requireNonNull(response.getBody()).getInputStream();
        FileUtils.copyInputStreamToFile(is, targetFile);

        // save data to database
        InputStream inputStream = new FileInputStream(targetFile);
        saveData(inputStream);
    }

    private void saveData(InputStream inputStream) {
        List<CountryDataBO> dataList = new ArrayList<>();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setDelimiterDetectionEnabled(true, ';');
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parsALLRecords = parser.parseAllRecords(inputStream);

        // remove the unused data in the file
        parsALLRecords.remove(0);
        parsALLRecords.remove(0);

        parsALLRecords.forEach(record -> {
            CountryDataBO data = new CountryDataBO();
            String stringDate = record.getString(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(stringDate, formatter);
            data.setDate(localDate);
            data.setCountryName((record.getString(1)));
            data.setInfections(record.getString(2));
            data.setDeaths(record.getString(3));
            data.setRecovered(record.getString(4));
            data.setDeathRate(record.getString(5));
            data.setRecoveredRate(record.getString(6));
            data.setInfectionRate(record.getString(7));
            dataList.add(data);

        });

        dataRepository.saveAll(dataList);
    }
}
