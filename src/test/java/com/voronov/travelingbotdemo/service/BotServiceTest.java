package com.voronov.travelingbotdemo.service;

import com.voronov.travelingbotdemo.repo.CityRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class BotServiceTest {

    @Autowired
    private BotService botService;

    @MockBean
    CityRepository cityRepository;

    static {
        ApiContextInitializer.init();
    }
    @Test
    void findCityByName() {
        String nameCity = "Берлин";
        String nameCityToUpperCase = nameCity.toUpperCase();

        String infoOfCityFromDb = botService.findCityByName(nameCity);
//        assertEquals(nameCityToUpperCase, infoOfCityFromDb.);
    }
}