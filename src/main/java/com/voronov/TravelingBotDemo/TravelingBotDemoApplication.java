package com.voronov.TravelingBotDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@EnableScheduling
@SpringBootApplication
public class TravelingBotDemoApplication {
		public static void main(String[] args) {
			ApiContextInitializer.init();
			SpringApplication.run(TravelingBotDemoApplication.class, args);
	}

}
