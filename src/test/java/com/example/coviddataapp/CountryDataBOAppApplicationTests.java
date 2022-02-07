package com.example.coviddataapp;

import com.example.coviddataapp.dto.CountryDataDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
class CountryDataBOAppApplicationTests {

    RestTemplate restTemplate = new RestTemplate();

    final  String url = "http://localhost:8080/api";


    @Test
    public void testGetOneCountryByName() throws URISyntaxException {
        String tempoUrl = "/oneCountryData?countryName=Maroc";
        URI uri = new URI(url + tempoUrl);

        ResponseEntity<CountryDataDTO[]> listResponseEntity = restTemplate.getForEntity(uri, CountryDataDTO[].class);

        Assert.assertEquals(200, listResponseEntity.getStatusCodeValue());
        Assert.assertNotNull(listResponseEntity.getBody());

    }

    @Test
    public void testGetOneCountryByNameAndDate() throws URISyntaxException {
        String tempoUrl = "/oneCountryDataWithDate?countryName=Maroc&date=2022-01-28";
        URI uri = new URI(url + tempoUrl);

        ResponseEntity<CountryDataDTO[]> listResponseEntity = restTemplate.getForEntity(uri, CountryDataDTO[].class);

        Assert.assertEquals(200, listResponseEntity.getStatusCodeValue());
        Assert.assertNotNull(listResponseEntity.getBody());

    }

    @Test
    public void testGetTodayCovidDataByCountryName() throws URISyntaxException {
        String tempoUrl = "/todayCountryData?countryName=Maroc";
        URI uri = new URI(url + tempoUrl);

        ResponseEntity<CountryDataDTO[]> listResponseEntity = restTemplate.getForEntity(uri, CountryDataDTO[].class);

        Assert.assertEquals(200, listResponseEntity.getStatusCodeValue());
        Assert.assertNotNull(listResponseEntity.getBody());


    }

}
