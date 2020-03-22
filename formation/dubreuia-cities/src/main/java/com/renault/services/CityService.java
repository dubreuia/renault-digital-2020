package com.renault.services;

import com.renault.entities.City;

import java.util.Optional;

public interface CityService {

    Optional<City> findCity(int id);

    void save(City city);

}
