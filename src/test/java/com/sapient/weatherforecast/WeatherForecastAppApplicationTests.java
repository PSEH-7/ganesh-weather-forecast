package com.sapient.weatherforecast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.weatherforecast.controllers.WeatherForcastRestController;
import com.sapient.weatherforecast.exception.ForecastServiceException;
import com.sapient.weatherforecast.exception.OWMRestClientException;
import com.sapient.weatherforecast.helpers.WeatherForecastServiceHelper;
import com.sapient.weatherforecast.pojo.OpenWeatherAPIResp;
import com.sapient.weatherforecast.response.SapientWeatherForecastResp;
import com.sapient.weatherforecast.service.WeatherForecastService;
import com.sapient.weatherforecast.service.impl.OWMWeatherForecastServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class WeatherForecastAppApplicationTests {
	 private static final String owmEndpoint = "https://samples.openweathermap.org/data/2.5/forecast";

	private static final String appId = "b6907d289e10d714a6e88b30761fae22";
	

	
	 private WeatherForcastRestController weatherForcastRestController;
	    
		
	    WeatherForecastService forecastService;  
	    
	    @Mock
	    RestTemplateBuilder restBuilder;
	    
	    @Mock
	     WeatherForecastProperties weatherForecastProperties;
	    
	    @Mock
	    RestTemplate restTemplate;
	    
	    @Spy
	    WeatherForecastServiceHelper weatherForecastServiceHelper;
	    
	  
	    @BeforeEach
	    public void before() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	    	Mockito.when(restBuilder.build()).thenReturn(restTemplate);
	    	Mockito.when(weatherForecastProperties.getAppID()).thenReturn(appId);
	    	Mockito.when(weatherForecastProperties.getEndpoint()).thenReturn(owmEndpoint);
	    	
	    	forecastService = new OWMWeatherForecastServiceImpl(restBuilder, weatherForecastProperties);
	    	weatherForcastRestController = new WeatherForcastRestController();
	    	Class<? extends WeatherForcastRestController> clazz = weatherForcastRestController.getClass();
	    	Field field = clazz.getDeclaredField("forecastService");
	    	field.setAccessible(true);
	    	field.set(weatherForcastRestController, forecastService);
	    	field = clazz.getDeclaredField("weatherForecastServiceHelper");
	    	field.setAccessible(true);
	    	field.set(weatherForcastRestController, weatherForecastServiceHelper);
	    	field = clazz.getDeclaredField("maxTempThreshold");
	    	field.setAccessible(true);
	    	field.set(weatherForcastRestController, 40D);
	    	
	    	
	    	
	    }
	    
	     
	    @Test
	    public void givenValidURL_forecastService_expect3DaysForecast() throws ForecastServiceException, IOException, URISyntaxException 
	    {
	    	
	    	
	    	String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("sampleForecastResp.json").toURI())));
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	OpenWeatherAPIResp body = objectMapper.readValue(content, OpenWeatherAPIResp.class);
			ResponseEntity<OpenWeatherAPIResp> respEntity = new ResponseEntity<OpenWeatherAPIResp>(body, HttpStatus.OK);
			
			String country = "US";
			String city = "London";
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(owmEndpoint)
			        .queryParam("appid",appId)
			        .queryParam("q", city+","+country);
			URI uri = builder.build().toUri();
	    	Mockito.when(restTemplate.getForEntity(uri,OpenWeatherAPIResp.class)).thenReturn(respEntity);
	    	
	    	SapientWeatherForecastResp resp = weatherForcastRestController.forecast(city, country);   
	    	
	    	Assertions.assertNotNull(resp.getThreeDayForecast().size() == 3 && "2017-02-20".equalsIgnoreCase(resp.getThreeDayForecast().get(0).getDate()));
	        
	    }
	    
	    @Test
	    public void givenURLError_forecastService_expectForeCastException() throws ForecastServiceException, IOException, URISyntaxException 
	    {
	    	
	    	
	    	String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("sampleForecastResp.json").toURI())));
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	OpenWeatherAPIResp body = objectMapper.readValue(content, OpenWeatherAPIResp.class);
			ResponseEntity<OpenWeatherAPIResp> respEntity = new ResponseEntity<OpenWeatherAPIResp>(body, HttpStatus.OK);
			
			String country = "";
			String city = "";
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(owmEndpoint)
			        .queryParam("appid",appId)
			        .queryParam("q", city+","+country);
			URI uri = builder.build().toUri();
	    	Mockito.when(restTemplate.getForEntity(uri,OpenWeatherAPIResp.class)).thenThrow(new RestClientException("Error"));
	    	 
			Assertions.assertThrows(ForecastServiceException.class, () -> weatherForcastRestController.forecast(city, country) );
	    }
	     


}
