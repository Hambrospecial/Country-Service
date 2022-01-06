//package com.hamid.countryserviceproject.services;

import com.hamid.countryserviceproject.beans.Country;
import com.hamid.countryserviceproject.controllers.AddResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//@Component
//public class CountryService {



    //Collection for the list of the countries.
//    static HashMap<Integer, Country> countryMap;

    //Country service constructor
//    public CountryService() {
//        countryMap = new HashMap<Integer, Country>();
//        Country indiaCountry = new Country(1, "India", "Delta");
//        Country usaCountry = new Country(2, "USA", "Washington");
//        Country ukCountry = new Country(3, "UK", "London");
//
//        countryMap.put(1, indiaCountry);
//        countryMap.put(2, usaCountry);
//        countryMap.put(3, ukCountry);
//
//    }

    //Getting all the countries in the collection.
//    public List getAllCountries(){
//        List countries = new ArrayList(countryMap.values());
//        return countries;
//    }

    //Getting a country by Id from the collection
//    public Country getCountryById(int mapId){
//        Country country = countryMap.get(mapId);
//        return country;
//    }

    //Getting a country by Name from the collection
//    public Country getCountryByName(String countryName){
//        Country country = null;
//        for(int key : countryMap.keySet()) {
//            if (countryMap.get(key).getCountryName().equals(countryName)) {
//                country = countryMap.get(key);
//            }
//        }
//            return country;
//    }

    //Adding a new country to the collection
//    public Country addCountry(Country country){
//        country.setId(getMaxId());
//        countryMap.put(country.getId(), country);
//        return country;
//    }

    // Utility method to get max id.
//    public static int getMaxId(){
//        int max = 0;
//        for (int id : countryMap.keySet()){
//            if (max <= id){
//                max = id;
//            }
//        }
//        return max+1;
//    }

    //Updating a country in the collection
//    public Country updateCountry(Country country){
//        if(country.getId() > 0){
//            countryMap.put(country.getId(), country);
//        }
//        return country;
//    }

    //Deleting a country from the collection
//    public AddResponse deleteCountry(int mapId){
//        countryMap.remove(mapId);
//        AddResponse res = new AddResponse();
//        res.setMsg("Country deleted...");
//        res.setId(mapId);
//        return res;
//    }
//}
