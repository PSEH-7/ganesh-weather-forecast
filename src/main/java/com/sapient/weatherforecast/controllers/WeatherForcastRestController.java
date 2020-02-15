package com.sapient.weatherforecast.controllers;

import java.time.LocalDate;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.weatherforecast.exception.ForecastServiceException;
import com.sapient.weatherforecast.helpers.WeatherForecastServiceHelper;
import com.sapient.weatherforecast.pojo.OpenWeatherAPIResp;
import com.sapient.weatherforecast.response.DayMinMaxTemp;
import com.sapient.weatherforecast.response.SapientWeatherForecastResp;
import com.sapient.weatherforecast.service.WeatherForecastService;

@RestController
public class WeatherForcastRestController {
	private static Logger logger = LoggerFactory.getLogger(WeatherForcastRestController.class);

	@Autowired
	private WeatherForecastService forecastService;
	@Autowired
	private WeatherForecastServiceHelper weatherForecastServiceHelper;
	
	@Value("${maxTempThreshold}")
	private Double maxTempThreshold;

	@GetMapping("/forecast")
	public SapientWeatherForecastResp forecast(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "country", required = true) String country)
			throws ForecastServiceException {
		logger.debug("Request submitted forecast for City:{} and Country: {}",city,country);
		SapientWeatherForecastResp resp = new SapientWeatherForecastResp();
		try {
			OpenWeatherAPIResp openWeatherAPIResp = forecastService.getFroecast(city, country);

			TreeMap<LocalDate, DayMinMaxTemp> dateMinMaxTempMap = weatherForecastServiceHelper
					.constructDateMimMaxTemparature(openWeatherAPIResp);
			weatherForecastServiceHelper.buildForecastResp(resp, dateMinMaxTempMap, weatherForecastServiceHelper.convertCelciusToKelvin(maxTempThreshold));

		} catch (ForecastServiceException forecastEx) {
			logger.error("Error due to Forecast Service", forecastEx);
			throw forecastEx;
		}
		return resp;
	}

}
