package com.renault.integration;

import com.renault.CitiesApplication;
import com.renault.dtos.CityDto;
import com.renault.entities.City;
import com.renault.entities.User;
import com.renault.repositories.CityRepository;
import com.renault.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CitiesApplication.class, webEnvironment = RANDOM_PORT)
public class CityTest extends TestIntegration {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void should_POST_follow_city_adds_a_followed_city_for_user() {
        City city = cityRepository.findAll().stream()
                .filter(c -> "Montreuil".equals(c.getName()))
                .findFirst().orElseThrow();
        User user = userRepository.findAll().stream()
                .filter(u -> "Alex".equals(u.getName()))
                .findFirst().orElseThrow();

        post(format("user/%s/followCity/%s", user.getId(), city.getId()));

        List<CityDto> cities = getCitiesForUser(user.getId());
        System.out.println(cities);
    }

    @Test
    public void should_DELETE_follow_city_removes_a_followed_city_for_user() {
        City city = cityRepository.findAll().stream()
                .filter(c -> "Paris".equals(c.getName()))
                .findFirst().orElseThrow();
        User user = userRepository.findAll().stream()
                .filter(u -> "Alex".equals(u.getName()))
                .findFirst().orElseThrow();

        delete(format("user/%s/followCity/%s", user.getId(), city.getId()));

        List<CityDto> cities = getCitiesForUser(user.getId());
        System.out.println(cities);
    }

    private List<CityDto> getCitiesForUser(int userId) {
        String sqlString = "SELECT city.name AS city_name " +
                "FROM user, city, user_city " +
                "WHERE user.id = " + userId + " " +
                "AND user.id = user_city.user_id " +
                "AND city.id = user_city.city_id";
        @SuppressWarnings("unchecked")
        List<Object> resultList = entityManager.createNativeQuery(sqlString).getResultList();
        List<CityDto> cities = new ArrayList<>();
        for (Object object : resultList) {
            String cityName = (String) object;
            cities.add(new CityDto(0, cityName));
        }
        return cities;
    }

}
