package com.hamid.countryserviceproject.services;

import com.hamid.countryserviceproject.beans.Country;
import com.hamid.countryserviceproject.controllers.AddResponse;
import com.hamid.countryserviceproject.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;

@Component
@Service
public class CountryService {

    CountryRepository countryRep;

    @Autowired
    public CountryService(CountryRepository countryRep) {
        this.countryRep = countryRep;
    }

    public List<Country> getAllCountries(){
        List<Country> countries = countryRep.findAll();
        return countries;
    }

    public Country getCountryById(int id){
        List<Country> countries = countryRep.findAll();
        Country country = null;

        for (Country con:countries){
            if(con.getId() == id) country = con;
        }
        return country;
    }

    public Country getCountryByName(String countryName){
        List<Country> countries = countryRep.findAll();
        Country country = null;
        for (Country con : countries){
            if(con.getCountryName().equalsIgnoreCase(countryName)) country = con;
        }
        return country;
    }

    public Country addCountry(Country country){
        country.setId(getMaxId());
        countryRep.save(country);
        return country;

    }

    public Country updateCountry(Country country){
        countryRep.save(country);
        return country;
    }

    public void deleteCountry(int id){
        countryRep.deleteById(id);
    }

    //Utility method to get max id
    public int getMaxId(){
        return countryRep.findAll().size() + 1;
    }
}
