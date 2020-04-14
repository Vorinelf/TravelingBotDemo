package com.voronov.travelingbotdemo.service;


import com.voronov.travelingbotdemo.entity.City;
import com.voronov.travelingbotdemo.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private final CityRepository cityRepository;

    public AdminService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    public City addCity(City city) {
        City cityForDb = new City(city.getName().toUpperCase(), city.getDescription().toLowerCase());
        return cityRepository.save(cityForDb);
    }

    public List<City> filterCityByName(City city) {
        List<City> cities;
        if (city.getName() != null && !city.getName().isEmpty()) {
            cities = cityRepository.findByName(city.getName().toUpperCase());
        } else {
            cities = cityRepository.findAll();
        }
        return cities;
    }

    public List<City> sortCityByName() {
        List<City> cities = cityRepository.findAll();
        List<City> sortedList = cities
                .stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        return sortedList;
    }

    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> deleteCity(City city) {
        cityRepository.delete(city);
        return findAllCities();
    }
}
