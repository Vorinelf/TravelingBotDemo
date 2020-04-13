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
        if (cities.size() == 0 && cities.isEmpty()) {
            descriptions.add("К сожалению, такой город не найден."+"\n"+"Пожалуйста, введите " +
                    "корректное название города на русском языке!");
        } else {
            for (City city : cities
            ) {
                descriptions.add(city.getDescription());
            }
        }
        return descriptions;
    }

    public BotService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
