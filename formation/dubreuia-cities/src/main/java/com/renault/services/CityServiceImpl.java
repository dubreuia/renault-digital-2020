package com.renault.services;

import com.renault.entities.City;
import com.renault.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public void save(City city) {
        cityRepository.save(city);
    }

}
