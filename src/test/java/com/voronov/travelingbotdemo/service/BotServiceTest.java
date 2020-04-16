package com.voronov.travelingbotdemo.service;

import com.voronov.travelingbotdemo.repo.CityRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
        botService.findCityByName(nameCity);

        Mockito.verify(cityRepository, Mockito.times(1)).findByName(nameCityToUpperCase);

    }

    @Test
    void findCityByNameError() {
        String nameCity = " ";
        String answer = botService.findCityByName(nameCity);

        assertEquals("К сожалению, такой город не найден." + "\n" +
                "Пожалуйста, введите корректное название города на русском языке!", answer);
    }
}