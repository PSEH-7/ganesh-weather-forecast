package com.sapient.weatherforecast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherForecastProperties.class)
public class WeatherForecastAppApplication {
	private static Logger logger = LoggerFactory.getLogger(WeatherForecastAppApplication.class);
	public static void main(String[] args) {
		logger.info("WeatherForecastAppApplication Strating");
		SpringApplication.run(WeatherForecastAppApplication.class, args);
	}

}
