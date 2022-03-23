package com.hamid.countryserviceproject;

import com.hamid.countryserviceproject.models.Country;
import com.hamid.countryserviceproject.controllers.CountryController;
import com.hamid.countryserviceproject.services.CountryService;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ControllerMockitoTest.class})
public class ControllerMockitoTest {

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    List<Country> mycountries;
    Country country;

    @Test
    @Order(1)
    public void test_getAllCountries(){
        mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "USA", "Washington"));
        Mockito.when(countryService.getAllCountries()).thenReturn(mycountries); //Mocking
        ResponseEntity<List<Country>> res = countryController.getCountries();

        Assert.assertEquals(HttpStatus.FOUND, res.getStatusCode());
        Assert.assertEquals(2, res.getBody().size());
    }

    @Test
    @Order(2)
    public void test_getCountryById() {

        country = new Country(1, "India", "Delhi");
        int id = 1;

        Mockito.when(countryService.getCountryById(id)).thenReturn(country); //Mocking
        ResponseEntity<Country> country = countryController.getCountryById(id);

        Assert.assertEquals(HttpStatus.OK, country.getStatusCode());
        Assert.assertEquals(id, country.getBody().getId());
    }

    @Test
    @Order(3)
    public void test_getCountryByName(){

        country = new Country(2, "Nigeria", "Capital");
        String name = "Nigeria";

        Mockito.when(countryService.getCountryByName(name)).thenReturn(country);
        ResponseEntity<Country> country = countryController.getCountryByName(name);

        Assert.assertEquals(HttpStatus.FOUND, country.getStatusCode());
        Assert.assertEquals(name, country.getBody().getCountryName());
    }

    @Test
    @Order(4)
    public void test_addCountry(){

        country = new Country(2, "South Africa", "Cape Town");

        Mockito.when(countryService.addCountry(country)).thenReturn(country);
        ResponseEntity<Country> res = countryController.addCountry(country);

        Assert.assertEquals(HttpStatus.CREATED, res.getStatusCode());
        Assert.assertEquals(country, res.getBody());

    }

    @Test
    @Order(5)
    public void test_updateCountry(){

        country = new Country(2, "South Africa", "Cape Town");
        int id = 2;

        Mockito.when(countryService.getCountryById(id)).thenReturn(country);
        Mockito.when(countryService.updateCountry(country)).thenReturn(country);
        ResponseEntity<Country> res = countryController.updateCountry(id, country);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
        Assert.assertEquals(2, res.getBody().getId());
        Assert.assertEquals("South Africa", res.getBody().getCountryName());
        Assert.assertEquals("Cape Town", res.getBody().getCountryCapital());

    }

    @Test
    @Order(6)
    public void test_deleteCountry(){

        country = new Country(2, "South Africa", "Cape Town");
        int id = 2;

        Mockito.when(countryService.getCountryById(id)).thenReturn(country);
        ResponseEntity<Country> res = countryController.deleteCountry(id);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

}
