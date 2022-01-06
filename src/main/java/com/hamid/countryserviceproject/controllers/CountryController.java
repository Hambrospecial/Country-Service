package com.hamid.countryserviceproject.controllers;

import com.hamid.countryserviceproject.beans.Country;
import com.hamid.countryserviceproject.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CountryController {

    CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getCountries(){
        try {
            List<Country> countries = countryService.getAllCountries();
            return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id){
        try {
            Country country = countryService.getCountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getcountries/countryName")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName){
        try {
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        try {
            country = countryService.addCountry(country);
            return new ResponseEntity<Country>(country, HttpStatus.CREATED);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country){
       try {
           Country existCountry = countryService.getCountryById(id);
           existCountry.setCountryName(country.getCountryName());
           existCountry.setCountryCapital(country.getCountryCapital());
           Country update_country = countryService.updateCountry(existCountry);
           return new ResponseEntity<Country>(country, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<Country>(country, HttpStatus.CONFLICT);

       }

    }

    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity<Country> deleteCountry (@PathVariable(value = "id") int id){
        Country country = null;
        try {
            country = countryService.getCountryById(id);
            countryService.deleteCountry(id);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Country>(country, HttpStatus.OK);

    }

}
