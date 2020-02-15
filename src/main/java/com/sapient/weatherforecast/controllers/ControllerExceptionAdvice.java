package com.sapient.weatherforecast.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.weatherforecast.exception.ForecastServiceException;
import com.sapient.weatherforecast.response.WeatherForecastErrorResp;

@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {
	
	 	@ExceptionHandler(ForecastServiceException.class)
	    public ResponseEntity<WeatherForecastErrorResp> handleForecastError(Exception ex, WebRequest request) {

		 WeatherForecastErrorResp errors = new WeatherForecastErrorResp();
	        errors.setTimestamp(LocalDateTime.now());
	        errors.setError("Error due to Forecast Service rest service not functioning as expected, actual error:"+ex.getMessage());
	        errors.setStatus(HttpStatus.NOT_FOUND.value());

	        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	    }
	 
	 	@ExceptionHandler(Exception.class)
	    public ResponseEntity<WeatherForecastErrorResp> handleOtherErrors(Exception ex, WebRequest request) {

		 WeatherForecastErrorResp errors = new WeatherForecastErrorResp();
	        errors.setTimestamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.NOT_FOUND.value());

	        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	    }

}
