package com.voronov.travelingbotdemo.controller;

import com.voronov.travelingbotdemo.entity.City;
import com.voronov.travelingbotdemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public List<City> findAll() {
        return adminService.findAllCities();
    }

    @PostMapping("/add")
    public City add(@RequestBody City city) {
        return adminService.addCity(city);
    }

    @GetMapping("/filter")
    public Iterable<City> filterByName(@RequestBody City city) {
        return adminService.filterCityByName(city);
    }

    @GetMapping("/sort")
    public List<City> sortByName() {
        return adminService.sortCityByName();
    }

    @PutMapping
    public City update(@RequestBody City city) {
        return adminService.updateCity(city);
    }

    @DeleteMapping
    public List<City> delete(@RequestBody City city) {
        return adminService.deleteCity(city);
    }
}