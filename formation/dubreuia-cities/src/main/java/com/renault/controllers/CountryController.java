package com.renault.controllers;

import com.renault.dtos.CountryDto;
import com.renault.entities.Country;
import com.renault.entities.Language;
import com.renault.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServlet;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class CountryController extends HttpServlet {

    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public List<CountryDto> getCountries() {
        return countryService.getCountries().stream()
                .map(c -> new CountryDto(c.getId(), c.getName(), c.getLanguage().getName()))
                .collect(toList());
    }

    @GetMapping("/{id}")
    public CountryDto getCountry(@PathVariable("id") int id) {
        return countryService.getCountry(id)
                .map(c -> new CountryDto(c.getId(), c.getName(), c.getLanguage().getName()))
                .orElseThrow(() -> new HttpClientErrorException(NOT_FOUND, format("Country not found: %d", id)));
    }

    @PostMapping("")
    public void insertCountry(@RequestBody CountryDto countryDto) {
        Language language = Language.fromName(countryDto.getLanguage()).orElseThrow();
        countryService.insertCountry(new Country(countryDto.getName(), language));
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") int id) {
        try {
            countryService.deleteCountry(id);
        } catch (DataIntegrityViolationException e) {
            throw new HttpClientErrorException(NOT_ACCEPTABLE,
                    format("Country '%s' still used", id));
        }
    }

    @PutMapping("")
    public void updateCountry(@RequestBody CountryDto countryDto) {
        Country country = countryService.getCountry(countryDto.getId()).orElseThrow();
        country.setName(countryDto.getName());
        country.setLanguage(Language.fromName(countryDto.getLanguage()).orElseThrow());
        countryService.updateCountry(country);
    }

}
