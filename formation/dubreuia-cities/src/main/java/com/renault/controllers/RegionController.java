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

    // TODO - "/country/<country_id>/region" (GET) : retourne les régions pour le pays correspondant à l'id donné
    // TODO - "/country/region/<id>" (GET) : retourne la région correspondant à l'id donné

    @PostMapping("/{countryId}")
    public void insertRegion(@PathVariable("countryId") int countryId, @RequestBody RegionDto regionDto) {
        Country country = countryService.getCountry(countryId).orElseThrow();
        regionService.save(new Region(regionDto.getName(), country));
    }

    @PostMapping("/region")
    public void insertCountryRegion(@RequestBody CountryRegionDto dto) {
        Language language = Language.fromName(dto.getCountryLanguage()).orElseThrow();
        Country country = new Country(dto.getCountryName(), language);
        Region region = new Region(dto.getRegionName(), country);
        regionService.save(country, region);
    }

}
