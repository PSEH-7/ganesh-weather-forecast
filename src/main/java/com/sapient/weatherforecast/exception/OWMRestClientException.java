package com.sapient.weatherforecast.exception;

public class OWMRestClientException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679919095538526888L;
	
	public OWMRestClientException(String message){
		super(message);
	}

	public OWMRestClientException(String message, Throwable t){
		super(message, t);
	}


}
