package com.renault.services;

import com.renault.entities.Country;
import com.renault.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void insertCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountry(int id) {
        return countryRepository.findById(id);
    }

    @Override
    public void deleteCountry(int id) {
        countryRepository.deleteById(id);
    }

    @Override
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

}
