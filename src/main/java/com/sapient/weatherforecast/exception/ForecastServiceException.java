package com.sapient.weatherforecast.exception;

public class ForecastServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6541595550868229831L;

	public ForecastServiceException(String message, Throwable t){
		super(message, t);
	}

}
