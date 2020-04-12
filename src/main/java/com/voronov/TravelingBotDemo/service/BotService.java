package com.voronov.TravelingBotDemo.service;

import com.voronov.TravelingBotDemo.domain.City;
import com.voronov.TravelingBotDemo.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BotService {
    @Autowired
    private final CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<String> findCityByName(String cityName) {
        List<City> cities = cityRepository.findByName(cityName.toUpperCase());
        List<String> descriptions = new ArrayList<>();
        for (City city : cities
        ) {
            descriptions.add(city.getDescription());
        }
        return descriptions;
    }



    public BotService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

//    @Transactional(readOnly = true)
//    public City findByChatId(long id) {
//        return cityRepositoryForUser.findByChatId(id);
//    }
//
//    @Transactional(readOnly = true)
//    public List<City> findAllUsers() {
//        return cityRepositoryForUser.findAll();
//    }
//
//    public List<City> findNewUsers(){
//        List<City> users = cityRepositoryForUser.findNewUsers();
//
//        users.forEach((user) ->user.setNotified(true));
//        cityRepositoryForUser.saveAll(users);
//        return users;
//    }
//
//    @Transactional
//    public void addUser (City user) {
//        user.setAdmin(cityRepositoryForUser.count()==0);
//        cityRepositoryForUser.save(user);
//    }
//
//    @Transactional
//    public void updateUser (City user) {
//        cityRepositoryForUser.save(user);
//    }


}
