package com.renault.integration;

import com.renault.CitiesApplication;
import com.renault.dtos.CountryDto;
import com.renault.entities.Country;
import com.renault.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CitiesApplication.class, webEnvironment = RANDOM_PORT)
public class CountryTest extends TestIntegration {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void should_GET_root_returns_all_the_countries() {
        JsonReader response = get("country/");
        JsonArray countries = response.readArray();
        assertEquals(countryRepository.count(), countries.size());

        List<Country> all = countryRepository.findAll();
        countries.stream()
                .map(JsonValue::asJsonObject)
                .map(CountryTest::getCountryDto)
                .forEach(country -> {
                    assertTrue(country.getId() > 0);
                    assertEquals(1, all.stream()
                            .map(Country::getName)
                            .filter(name -> country.getName().equals(name))
                            .count());
                    assertFalse(country.getLanguage().isBlank());
                });
    }

    @Test
    public void should_POST_root_add_new_country() {
        JsonObject uk = Json.createObjectBuilder()
                .add("name", "United Kingdom")
                .add("language", "English")
                .build();
        post("country/", uk);

        List<Country> countries = countryRepository.findAll();
        System.out.println(countries);
    }

    @Test
    public void should_DELETE_root_remove_existing_country() {
        List<Country> all = countryRepository.findAll();
        Country france = all.stream().filter(country -> "France".equals(country.getName())).findFirst().orElseThrow();
        delete(format("country/%s", france.getId()));

        List<Country> countries = countryRepository.findAll();
        System.out.println(countries);
    }

    @Test
    public void should_PUT_root_modify_existing_country() {
        List<Country> all = countryRepository.findAll();
        Country france = all.stream().filter(country -> "France".equals(country.getName())).findFirst().orElseThrow();
        JsonObject updatedFrance = Json.createObjectBuilder()
                .add("id", france.getId())
                .add("name", "United France")
                .add("language", "English")
                .build();
        put("country", updatedFrance);

        List<Country> countries = countryRepository.findAll();
        System.out.println(countries);
    }

    private static CountryDto getCountryDto(JsonObject jsonObject) {
        return new CountryDto(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("language"));
    }

}
