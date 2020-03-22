package com.renault.controllers;

import com.renault.dtos.CountryRegionDto;
import com.renault.dtos.RegionDto;
import com.renault.entities.Country;
import com.renault.entities.Language;
import com.renault.entities.Region;
import com.renault.services.CountryService;
import com.renault.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class RegionController extends HttpServlet {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @PostMapping("/{countryId}")
    public void insertRegion(@PathVariable("countryId") int countryId, @RequestBody RegionDto regionDto) {
        Country country = countryService.getCountry(countryId).orElseThrow();
        regionService.save(new Region(regionDto.getName(), country));
    }

    @PostMapping("/region")
    public void insertCountryRegion(@RequestBody CountryRegionDto countryRegionDto) {
        Language language = Language.fromName(countryRegionDto.getCountryLanguage()).orElseThrow();
        Country country = new Country(countryRegionDto.getCountryName(), language);
        regionService.save(country, new Region(countryRegionDto.getRegionName(), country));
    }

}
