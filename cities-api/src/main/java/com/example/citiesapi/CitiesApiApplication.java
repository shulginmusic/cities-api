package com.example.citiesapi;

import com.example.citiesapi.model.APIToken;
import com.example.citiesapi.model.City;
import com.example.citiesapi.service.APITokenService;
import com.example.citiesapi.service.CityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class CitiesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiesApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CityService cityService, APITokenService apiTokenService) {
		return args -> {
			cityService.create(new City("St Augustine", "Florida", 1565));

			//Reset hits every minute
			//Ref: https://www.techiedelight.com/periodically-execute-task-java/

			Timer timer = new Timer();

			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					List<APIToken> tokens = apiTokenService.findAll();
					for (APIToken token : tokens) {
						token.setHits(0);
						apiTokenService.update(token);
						System.out.println(token.getToken() + " token reset to 0 hits");
					}
				}
			}, 0, 60000);
		};
	}

}
