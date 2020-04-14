package com.voronov.travelingbotdemo.service;

import com.voronov.travelingbotdemo.entity.City;
import com.voronov.travelingbotdemo.repo.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BotService {

    private final CityRepository cityRepository;

    public BotService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public String findCityByName(String cityName) {
        List<City> cities = cityRepository.findByName(cityName.toUpperCase());
        List<String> descriptions = new ArrayList<>();
        String answerForUser;
        if (cities.size() == 0) {
            answerForUser = cityName + ":" + "\n" + "К сожалению, такой город не найден." + "\n" +
                    "Пожалуйста, введите коретное название города на русском языке!";
        } else {
            for (City city : cities) {
                descriptions.add(city.getDescription());
            }
            String cityFromDb = cities
                    .stream()
                    .map(City::getName)
                    .findFirst()
                    .get();
            String totalDescriptions = descriptions
                    .stream()
                    .distinct()
                    .collect(Collectors.joining(";" + "\n"));
            answerForUser = cityFromDb + ":" + "\n" + totalDescriptions;
        }
        return answerForUser;
    }

}
