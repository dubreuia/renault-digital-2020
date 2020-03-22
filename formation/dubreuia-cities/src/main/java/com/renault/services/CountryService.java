package com.renault.services;

import com.renault.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    void insertCountry(Country country);

    List<Country> getCountries();

    Optional<Country> getCountry(int id);

    void deleteCountry(int id);

    void updateCountry(Country country);

}
