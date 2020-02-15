package com.sapient.weatherforecast.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.function.BiFunction;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.sapient.weatherforecast.pojo.ADayWeather;
import com.sapient.weatherforecast.pojo.OpenWeatherAPIResp;
import com.sapient.weatherforecast.response.DayMinMaxTemp;
import com.sapient.weatherforecast.response.SapientWeatherForecastResp;

@Component
public class WeatherForecastServiceHelper {
	
	/**
	 * @param resp
	 * @param dateMinMaxTempMap
	 */
	public void buildForecastResp(SapientWeatherForecastResp resp,
			TreeMap<LocalDate, DayMinMaxTemp> dateMinMaxTempMap, Double maxTempThreshold) {
		Integer hotsunnycount = 0,rainycount = 0;
		List<DayMinMaxTemp> dayMinMaxTempList = new ArrayList<>(3);
		for(DayMinMaxTemp dayMinMaxTemp : dateMinMaxTempMap.values()){
			dayMinMaxTemp.setMaxTemp(convertKelvinToCelcius(dayMinMaxTemp.getMaxTemp()));
			dayMinMaxTemp.setMinTemp(convertKelvinToCelcius(dayMinMaxTemp.getMinTemp()));
			
			if (dayMinMaxTemp.getMaxTemp() >= maxTempThreshold) {
				hotsunnycount++;
			} else if(dayMinMaxTemp.isRainy()) rainycount++;
			
			dayMinMaxTempList.add(dayMinMaxTemp);
		}
		
		
		if(hotsunnycount >=3){
			resp.setForecastSuggestion("Apply Sunscreen Lotion");
		} else if(rainycount >= 3){
			resp.setForecastSuggestion("Carry Umberla");
		} 
		
		resp.setThreeDayForecast(dayMinMaxTempList);
	}
	
	/**
	 * @param openWeatherAPIResp
	 * @return
	 */
	public TreeMap<LocalDate, DayMinMaxTemp> constructDateMimMaxTemparature(OpenWeatherAPIResp openWeatherAPIResp) {
		TreeMap<LocalDate, DayMinMaxTemp> dateMinMaxTempMap = new TreeMap<>();
		for (ADayWeather dayWeather : openWeatherAPIResp.getDayWeatherList()) {
			String date = dayWeather.getDtTxt().substring(0, 10);
			LocalDate key = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
			
			dateMinMaxTempMap.compute(key, (LocalDate k,DayMinMaxTemp v) -> {
				return this.mapCompute(k, v, dayWeather); } );

			if(dateMinMaxTempMap.size()> 3){
				dateMinMaxTempMap.remove(dateMinMaxTempMap.lastKey());
			}
			
			/*DayMinMaxTemp dayMinMaxTemp = dateMinMaxTempMap.get(key);
			if (dayMinMaxTemp != null) {
				if (dayWeather.getMain().getTempMax() > dayMinMaxTemp.getMaxTemp()) {
					dayMinMaxTemp.setMaxTemp(dayWeather.getMain().getTempMax());
				} else if (dayWeather.getMain().getTempMin() < dayMinMaxTemp.getMinTemp()) {
					dayMinMaxTemp.setMinTemp(dayWeather.getMain().getTempMin());
				}
				dateMinMaxTempMap.put(key, dayMinMaxTemp);
			} else {
				dayMinMaxTemp = new DayMinMaxTemp();
				dayMinMaxTemp.setDate(date);
				dayMinMaxTemp.setMaxTemp(dayWeather.getMain().getTempMax());
				dayMinMaxTemp.setMinTemp(dayWeather.getMain().getTempMin());
				if (!CollectionUtils.isEmpty(dayWeather.getWeather())
						&& "Rain".equalsIgnoreCase(dayWeather.getWeather().get(0).getMain())) {
					dayMinMaxTemp.setRainy(Boolean.TRUE);
				} else if (dayMinMaxTemp.getMaxTemp() >= maxTempThreshold) {
					dayMinMaxTemp.setHotSunny(Boolean.TRUE);
				}
				
				if(dateMinMaxTempMap.size()> 3){
					dateMinMaxTempMap.remove(dateMinMaxTempMap.lastKey());
				}
			}

			dateMinMaxTempMap.put(key, dayMinMaxTemp);*/
		}
		return dateMinMaxTempMap;
	}
	
	private DayMinMaxTemp mapCompute(LocalDate key, DayMinMaxTemp dayMinMaxTemp, ADayWeather dayWeather){
		String date = dayWeather.getDtTxt().substring(0, 10);
		if (dayMinMaxTemp != null) {
			if (dayWeather.getMain().getTempMax() > dayMinMaxTemp.getMaxTemp()) {
				dayMinMaxTemp.setMaxTemp(dayWeather.getMain().getTempMax());
			} else if (dayWeather.getMain().getTempMin() < dayMinMaxTemp.getMinTemp()) {
				dayMinMaxTemp.setMinTemp(dayWeather.getMain().getTempMin());
			}
			
		} else {
			dayMinMaxTemp = new DayMinMaxTemp();
			dayMinMaxTemp.setDate(date);
			dayMinMaxTemp.setMaxTemp(dayWeather.getMain().getTempMax());
			dayMinMaxTemp.setMinTemp(dayWeather.getMain().getTempMin());
			dayMinMaxTemp.setRainy(dayWeather.getRain() != null && dayWeather.getRain().get3h() != null);					
		}
		return dayMinMaxTemp;
	}

	private double convertKelvinToCelcius(Double kelvin) {
		return kelvin - 273.15F;
	}
	
	public double convertCelciusToKelvin(Double celcius) {
		return celcius + 273.15F;
	}


}
