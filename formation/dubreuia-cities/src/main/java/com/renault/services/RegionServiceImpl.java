package com.renault.services;

import com.renault.entities.Country;
import com.renault.entities.Region;
import com.renault.repositories.CountryRepository;
import com.renault.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Optional<Region> getRegion(int id) {
        return regionRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Region region) {
        regionRepository.save(region);
    }

    @Override
    @Transactional
    public void save(Country country, Region region) {
        countryRepository.save(country);
        regionRepository.save(region);
    }

}
