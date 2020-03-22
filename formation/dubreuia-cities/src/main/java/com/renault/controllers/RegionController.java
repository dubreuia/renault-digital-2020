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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class RegionController extends HttpServlet {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @GetMapping("/{countryId}/region")
    public List<RegionDto> getRegions(@PathVariable("countryId") int countryId) {
        Country country = countryService.getCountry(countryId).orElseThrow();
        return country.getRegions().stream().map(r -> new RegionDto(r.getId(), r.getName())).collect(toList());
    }

    @GetMapping("/region/{id}")
    public RegionDto getRegion(@PathVariable("id") int id) {
        return regionService.getRegion(id).map(r -> new RegionDto(r.getId(), r.getName())).orElseThrow();
    }

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
