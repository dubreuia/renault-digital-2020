package com.renault;

import com.renault.entities.City;
import com.renault.entities.Country;
import com.renault.entities.Language;
import com.renault.entities.Region;
import com.renault.services.ApplicationService;
import com.renault.services.CityService;
import com.renault.services.CountryService;
import com.renault.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CitiesApplication extends SpringBootServletInitializer {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CityService cityService;

    public static void main(String[] args) {
        SpringApplication.run(CitiesApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            applicationService.deleteAll();

            Country france = new Country("France", Language.FR);
            countryService.insertCountry(france);

            Region idf = new Region("IDF", france);
            regionService.save(idf);

            cityService.save(new City("Paris", idf));
            cityService.save(new City("Montreuil", idf));
        };
    }

}
