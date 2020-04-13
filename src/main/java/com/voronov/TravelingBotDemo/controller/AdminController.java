package com.voronov.TravelingBotDemo.controller;

import com.voronov.TravelingBotDemo.domain.City;
import com.voronov.TravelingBotDemo.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> welcome() {
        Iterable<City> cities = cityRepository.findAll();

        return cities;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public City addCity(@RequestBody City city) {
        City cityForDb = new City(city.getName().toUpperCase(), city.getDescription().toLowerCase());
        return cityRepository.save(cityForDb);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> filterByName(@RequestBody City city) {
        Iterable<City> cities;
        if (city.getName() != null && !city.getName().isEmpty()) {
            cities = cityRepository.findByName(city.getName().toUpperCase());
        } else {
            cities = cityRepository.findAll();
        }
        return cities;
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> sortByName() {
        List<City> cities = cityRepository.findAll();

        return cities
                .stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
    }


    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public City editCity(@RequestBody City city) {
        return cityRepository.save(city);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> deleteCity(@RequestBody City city) {
        cityRepository.delete(city);
        Iterable<City> cities = cityRepository.findAll();

        return cities;
    }
}