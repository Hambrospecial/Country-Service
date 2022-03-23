package com.hamid.countryserviceproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamid.countryserviceproject.models.Country;
import com.hamid.countryserviceproject.controllers.CountryController;
import com.hamid.countryserviceproject.services.CountryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.hamid.countryserviceproject")
@AutoConfigureMockMvc
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = {ControllerMockMVCTest.class})
public class ControllerMockMVCTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    List<Country> mycountries;
    Country country;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    @Order(1)
    public void test_getAllcountries() throws Exception{
        mycountries = new ArrayList<>();
        mycountries.add(new Country(1, "India","Delhi"));
        mycountries.add(new Country(2, "USA","Washington"));

        Mockito.when(countryService.getAllCountries()).thenReturn(mycountries);

        this.mockMvc.perform(get("/getcountries"))
                        .andExpect(status().isFound())
                        .andDo(print());
    }

    @Test
    @Order(2)
    public void test_getCountryById() throws Exception{
        country = new Country(1, "Nigeria", "Abuja");
        int id = 1;
        Mockito.when(countryService.getCountryById(id)).thenReturn(country);

        this.mockMvc.perform(get("/getcountries/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("countryName").value("Nigeria"))
                .andExpect(jsonPath("countryCapital").value("Abuja"))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void test_getCountryByName() throws Exception{
        country = new Country(1, "Nigeria", "Abuja");
        String countryName = "Nigeria";
        Mockito.when(countryService.getCountryByName(countryName)).thenReturn(country);

        this.mockMvc.perform(get("/getcountries/countryName").param("name", "Nigeria"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("countryName").value("Nigeria"))
                .andExpect(jsonPath("countryCapital").value("Abuja"))
                .andDo(print());
    }

    @Test
    @Order(4)
    public void test_addCountry() throws Exception{

        country = new Country(1, "Nigeria", "Abuja");
        Mockito.when(countryService.addCountry(country)).thenReturn(country);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(country);

        this.mockMvc.perform(post("/addcountry")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                     .andExpect(status().isCreated())
                     .andDo(print());

    }

    @Test
    @Order(5)
    public void test_updateCountry() throws Exception{
        country = new Country(1, "Nigeria", "Abuja");

        int countryId = 1;

        Mockito.when(countryService.getCountryById(countryId)).thenReturn(country);
        Mockito.when(countryService.updateCountry(country)).thenReturn(country);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(country);

        this.mockMvc.perform(put("/updatecountry/{id}",countryId)
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("countryName").value("Nigeria"))
                .andExpect(jsonPath("countryCapital").value("Abuja"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void test_deleteCountry() throws Exception{
        country = new Country(1, "Nigeria", "Abuja");
        int countryId = 1;
        Mockito.when(countryService.getCountryById(countryId)).thenReturn(country);

        this.mockMvc.perform(delete("/deletecountry/{id}", countryId))
                .andExpect(status().isOk());
    }
}
