package com.voronov.travelingbotdemo;

import com.voronov.travelingbotdemo.entity.City;
import com.voronov.travelingbotdemo.repo.CityRepository;
import com.voronov.travelingbotdemo.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    CityRepository cityRepository;

    static {
        ApiContextInitializer.init();
    }




    @Test
    public void addCity(){

        City city= new City("Париж","эйфелева башня");
        City cityForDb = new City(city.getName().toUpperCase(), city.getDescription().toLowerCase());
        adminService.addCity(city);

        Mockito.verify(cityRepository, Mockito.times(1)).save(cityForDb);


    }


}
