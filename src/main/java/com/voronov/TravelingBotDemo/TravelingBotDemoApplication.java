package com.voronov.TravelingBotDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TravelingBotDemoApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TravelingBotDemoApplication.class, args);
    }

}
