package com.voronov.travelingbotdemo.service;

import com.voronov.travelingbotdemo.entity.City;
import com.voronov.travelingbotdemo.repo.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    CityRepository cityRepository;

    static {
        ApiContextInitializer.init();
    }

    @Test
    public void addCity() {
        City city = new City("Париж", "Эйфелева башня");
        City cityForDb = new City(city.getName().toUpperCase(), city.getDescription());
        adminService.addCity(city);

        Mockito.verify(cityRepository, Mockito.times(1)).save(cityForDb);
    }

    @Test
    void filterCityByName() {
        City city = new City("Лондон", "Биг Бен");
        String cityNameForDb = city.getName().toUpperCase();
        adminService.filterCityByName(city);

        Mockito.verify(cityRepository, Mockito.times(1)).findByName(cityNameForDb);
    }

    @Test
    void filterCityByNameNull() {
        City city = new City();
        adminService.filterCityByName(city);

        Mockito.verify(cityRepository, Mockito.times(1)).findAll();
    }


}