package com.renault.services;

import com.renault.entities.City;
import com.renault.entities.User;
import com.renault.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void followCityAndSave(User user, City city) {
        List<City> followedCities = user.getFollowedCities();
        followedCities.add(city);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void unfollowCityAndSave(User user, City city) {
        List<City> followedCities = user.getFollowedCities();
        followedCities = followedCities.stream().filter(c -> !c.equals(city)).collect(toList());
        user.setFollowedCities(followedCities);
        userRepository.save(user);
    }

}
