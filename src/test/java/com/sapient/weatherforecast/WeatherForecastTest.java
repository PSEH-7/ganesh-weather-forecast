package com.sapient.weatherforecast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.sapient.weatherforecast.response.SapientWeatherForecastResp;


@SpringBootTest(classes = WeatherForecastAppApplication.class, 
webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherForecastTest {

	/* @TestConfiguration
	    static class TestConfig {

	        @Bean
	        public RestTemplateBuilder restTemplateBuilder() {

	            return new RestTemplateBuilder()
	                    .setConnectTimeout(Duration.ofSeconds(5));
	        }
	        
	       
	        
	    }*/
	
//	@LocalServerPort
    private int port;

//	@Autowired
	private TestRestTemplate restTemplate;
	
	
	
	public void givenCity_whenGetWeatherForecast_thenStatus200()
	  throws Exception {
		
		/*SapientWeatherForecastResp resp = restTemplate.getForObject(
				"http://localhost:" + port +"/sapient-weather-forecast-service/forecast?city=Bangalore&country=India", SapientWeatherForecastResp.class);
	 
		
		Assertions.assertNotNull(resp);*/
	    
	}

}
