package com.hamid.countryserviceproject;

import com.hamid.countryserviceproject.models.Country;
import com.hamid.countryserviceproject.repositories.CountryRepository;
import com.hamid.countryserviceproject.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {

    @Mock
    CountryRepository countryRep;

    @InjectMocks
    CountryService countryService;

    public List<Country> mycountries;

    @Test
    @Order(1)
    public void test_getAllCountries(){

        List<Country> mycountries = new ArrayList<>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "USA", "Washington"));

        Mockito.when(countryRep.findAll()).thenReturn(mycountries); //Mocking Statement.
        Assert.assertEquals(2, countryService.getAllCountries().size());
    }

    @Test
    @Order(2)
    public void test_getCountryById(){
        List<Country> mycountries = new ArrayList<>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "USA", "Washington"));

        int countryId = 1;

        Mockito.when(countryRep.findAll()).thenReturn(mycountries);     //Mocking Statement
        Assert.assertEquals(countryId, countryService.getCountryById(countryId).getId());
    }

    @Test
    @Order(3)
    public void test_getCountryByName(){
        List<Country> mycountries = new ArrayList<>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "USA", "Washington"));

        String countryName = "India";

        Mockito.when(countryRep.findAll()).thenReturn(mycountries);     //Mocking Statement
        Assert.assertEquals(countryName, countryService.getCountryByName(countryName).getCountryName());
    }

    @Test
    @Order(4)
    public void test_addCountry(){

        Country country = new Country(3, "Germany", "Berlin");
        Mockito.when(countryRep.save(country)).thenReturn(country);

        Assert.assertEquals(country, countryService.addCountry(country));
    }

    @Test
    @Order(5)
    public void test_updateCountry(){

        Country country = new Country(3, "Germany", "Berlin");
        Mockito.when(countryRep.save(country)).thenReturn(country);

        Assert.assertEquals(country, countryService.updateCountry(country));
    }

    @Test
    @Order(6)
    public void test_deleteCountry(){
        Country country = new Country(3, "Germany", "Berlin");
        countryService.deleteCountry(country.getId());
        Mockito.verify(countryRep, Mockito.times(1)).deleteById(country.getId());

    }

}
