package com.renault.controllers;

import com.renault.dtos.CountryRegionCityDto;
import com.renault.entities.City;
import com.renault.entities.Country;
import com.renault.entities.Language;
import com.renault.entities.Region;
import com.renault.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class CityController extends HttpServlet {

    @Autowired
    private CityService cityService;

    @PostMapping("/region/city")
    public void insertCountryRegionCity(@RequestBody CountryRegionCityDto dto) {
        Language language = Language.fromName(dto.getCountryLanguage()).orElseThrow();
        Country country = new Country(dto.getCountryName(), language);
        Region region = new Region(dto.getRegionName(), country);
        City city = new City(dto.getCityName(), region);
        cityService.save(country, region, city);
    }

}