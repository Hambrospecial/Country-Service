package com.hamid.countryserviceproject;

import com.hamid.countryserviceproject.models.Country;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTest {

    @BeforeEach
    void setUp(){
    }

    @Test
    @Order(1)
    void getAllCountriesIntegrationTest() throws JSONException {

        String expected =  "[\r\n"
                + "   {\r\n"
                + "       \"id\": 1,\r\n"
                + "        \"countryName\": \"India\",\r\n"
                + "        \"countryCapital\": \"Delhi\"\r\n"
                + "    },\r\n"
                + "       {\r\n"
                + "       \"id\": 2,\r\n"
                + "        \"countryName\": \"USA\",\r\n"
                + "        \"countryCapital\":\"Washington\"\r\n"
                + "     }\r\n"
                + " ]";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);
        System.out.println("Test 1a: " + response.getStatusCode());
        System.out.println("Test 1b: " + response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(2)
    void getCountryByIdIntegrationTest() throws JSONException {

        String expected = "{\r\n" +
                "\"id\": 1,\r\n" +
                "\"countryName\": \"India\",\r\n" +
                "\"countryCapital\": \"Delhi\"\r\n" +
                "}";

        TestRestTemplate restTemplate= new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/1", String.class);
        System.out.println("Test 2a: " + response.getStatusCode());
        System.out.println("Test 2b: " + response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(3)
    void getCountryByNameIntegrationTest() throws JSONException {

        String expected = "{\r\n" +
                "\"id\": 1,\r\n" +
                "\"countryName\": \"India\",\r\n" +
                "\"countryCapital\": \"Delhi\"\r\n" +
                "}";

        TestRestTemplate restTemplate= new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/countryName?name=India", String.class);
        System.out.println("Test 3a: " + response.getStatusCode());
        System.out.println("Test 3b: " + response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(4)
    void addCountryIntegrationTest() throws JSONException {

        Country country = new Country(3, "Germany", "Berlin");
        String expected = "{\r\n" +
                "\"id\": 3,\r\n" +
                "\"countryName\": \"Germany\",\r\n" +
                "\"countryCapital\": \"Berlin\"\r\n" +
                "}";

        TestRestTemplate restTemplate= new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request = new HttpEntity<>(country, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);

        System.out.println("Test 4: " + response.getBody());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    @Order(5)
    void updateCountryIntegrationTest() throws JSONException {

        Country country = new Country(3, "Japan", "Tokyo");
        String expected = "{\r\n" +
                "\"id\": 3,\r\n" +
                "\"countryName\": \"Japan\",\r\n" +
                "\"countryCapital\": \"Tokyo\"\r\n" +
                "}";

        TestRestTemplate restTemplate= new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/updatecountry/3", HttpMethod.PUT, request, String.class);

        System.out.println("Test 5: " + response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    @Order(6)
    void deleteCountryIntegrationTest() throws JSONException {

        Country country = new Country(3, "Japan", "Tokyo");

        String expected = "{\r\n" +
                "\"id\": 3,\r\n" +
                "\"countryName\": \"Japan\",\r\n" +
                "\"countryCapital\": \"Tokyo\"\r\n" +
                "}";

        TestRestTemplate restTemplate= new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/deletecountry/3", HttpMethod.DELETE, request, String.class);

        System.out.println("Test 6: " + response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}
