package com.renault.services;

import com.renault.entities.City;
import com.renault.entities.Country;
import com.renault.entities.Language;
import com.renault.entities.Region;
import com.renault.entities.User;
import com.renault.repositories.CityRepository;
import com.renault.repositories.CountryRepository;
import com.renault.repositories.RegionRepository;
import com.renault.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void deleteAll() {
        cityRepository.deleteAll();
        regionRepository.deleteAll();
        countryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Override
    @Transactional
    public void insertData() {
        Country france = new Country("France", Language.FR);
        countryRepository.save(france);
        Region idf = new Region("IDF", france);
        regionRepository.save(idf);
        City paris = new City("Paris", idf);
        City montreuil = new City("Montreuil", idf);
        cityRepository.save(paris);
        cityRepository.save(montreuil);

        Country canada = new Country("Canada", Language.EN);
        countryRepository.save(canada);
        Region quebec = new Region("Québec", canada);
        regionRepository.save(quebec);
        City montreal = new City("Montréal", quebec);
        City laval = new City("Laval", quebec);
        cityRepository.save(montreal);
        cityRepository.save(laval);

        Country japan = new Country("Japan", Language.JA);
        countryRepository.save(japan);

        User user = new User("Alex");
        user.setFollowedCities(List.of(paris, montreal));
        userRepository.save(user);
    }

}
