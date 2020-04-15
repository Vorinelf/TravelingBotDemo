package com.voronov.travelingbotdemo.controller;

import com.voronov.travelingbotdemo.entity.City;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.telegram.telegrambots.ApiContextInitializer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminController adminController;


    static {
        ApiContextInitializer.init();
    }

    @Test
    public void test() throws Exception {


        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void add() throws Exception {
//        City city = new City("Париж", "Эйфелева башня");
//        City cityForDb = new City(city.getName().toUpperCase(), city.getDescription().toLowerCase());
//
//        MvcResult mvcResult = this.mockMvc.perform(post("/add").requestAttr("city", city))
//                .andDo(print())
//                .andExpect(content().contentType(cityForDb.getName()))
//                .andReturn();

    }

    @Test
    void filterByName() {
    }
}