package com.sapient.weatherforecast.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SapientWeatherForecastResp {
	
	List<DayMinMaxTemp> threeDayForecast;
	
	String forecastSuggestion = "Normal Weather";

	public List<DayMinMaxTemp> getThreeDayForecast() {
		return threeDayForecast;
	}

	public void setThreeDayForecast(List<DayMinMaxTemp> threeDayForecast) {
		this.threeDayForecast = threeDayForecast;
	}

	public String getForecastSuggestion() {
		return forecastSuggestion;
	}

	public void setForecastSuggestion(String forecastSuggestion) {
		this.forecastSuggestion = forecastSuggestion;
	}
	
	

}
