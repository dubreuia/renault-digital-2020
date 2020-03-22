package com.renault.integration;

import com.renault.CitiesApplication;
import com.renault.dtos.CountryDto;
import com.renault.entities.Country;
import com.renault.entities.Region;
import com.renault.repositories.CountryRepository;
import com.renault.repositories.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CitiesApplication.class, webEnvironment = RANDOM_PORT)
public class RegionTest extends TestIntegration {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void should_POST_root_add_new_region() {
        Country country = countryRepository.findAll().stream()
                .filter(c -> "France".equals(c.getName()))
                .findFirst().orElseThrow();
        JsonObject alpes = Json.createObjectBuilder().add("name", "Alpes").build();
        post(format("country/%s", country.getId()), alpes);

        List<Region> regions = regionRepository.findAll();
        regions.forEach(region -> System.out.println(region.getName()));
    }

    @Test
    public void should_POST_region_add_new_region() {
        JsonObject ukMidwest = Json.createObjectBuilder()
                .add("countryName", "UK")
                .add("countryLanguage", "English")
                .add("regionName", "Midwest")
                .build();
        post("country/region", ukMidwest);

        List<Region> regions = regionRepository.findAll();
        regions.forEach(region -> System.out.println(region.getName()));
    }

    private static CountryDto getCountryDto(JsonObject jsonObject) {
        return new CountryDto(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("language"));
    }

}
