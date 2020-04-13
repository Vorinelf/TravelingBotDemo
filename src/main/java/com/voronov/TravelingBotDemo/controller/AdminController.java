package com.voronov.TravelingBotDemo.controller;

import com.voronov.TravelingBotDemo.domain.City;
import com.voronov.TravelingBotDemo.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public City restMethod(String param) {
        City city = new City();
        city.setId((long) 1);
        city.setName("Minsk");
        city.setDescription(param);

        return city;
    }

    @GetMapping
    public String welcome(Map<String, Object> model) {
        Iterable<City> cities = cityRepository.findAll();
        model.put("cities", cities);
        return "main";
    }

    @PostMapping("main")
    public String addCity(@RequestParam String name, @RequestParam String description, Map<String, Object> model) {
        City city = new City(name.toUpperCase(), description.toLowerCase());
        cityRepository.save(city);
        Iterable<City> cities = cityRepository.findAll();
        model.put("cities", cities);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        Iterable<City> cities;
        if (name != null && !name.isEmpty()) {
            cities = cityRepository.findByName(name.toUpperCase());
        } else {
            cities = cityRepository.findAll();
        }
        model.put("cities", cities);

        return "main";
    }

    @GetMapping("edit")
    public String editCity(@RequestParam("id") City city, Map<String, Object> model) {
        model.put("city", city);

        return "edit";
    }

    @PostMapping("save")
    public String saveCity(@RequestParam("id") City city,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           Map<String, Object> model
    ) {
        city.setName(name.toUpperCase());
        city.setDescription(description.toLowerCase());
        cityRepository.save(city);
        Iterable<City> cities = cityRepository.findAll();
        model.put("cities", cities);

        return "main";
    }

    @PostMapping("delete")
    public String deleteCity(@RequestParam("id") City city, Map<String,Object> model){
        cityRepository.delete(city);
        Iterable<City> cities = cityRepository.findAll();
        model.put("cities", cities);

        return "main";
    }
}