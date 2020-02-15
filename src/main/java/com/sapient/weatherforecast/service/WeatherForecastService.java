package com.sapient.weatherforecast.service;

import com.sapient.weatherforecast.exception.ForecastServiceException;
import com.sapient.weatherforecast.pojo.OpenWeatherAPIResp;

public interface WeatherForecastService {


	OpenWeatherAPIResp getFroecast(String city, String country) throws ForecastServiceException;
	

}
