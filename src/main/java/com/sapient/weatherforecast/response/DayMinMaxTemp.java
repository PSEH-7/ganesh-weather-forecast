package com.sapient.weatherforecast.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DayMinMaxTemp {
	
	  public String date;
	  public Double minTemp;
	  public Double maxTemp;
	  @JsonIgnore
	  public boolean rainy;
	  @JsonIgnore
	  public boolean hotSunny;
	  
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}
	public Double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(Double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public boolean isRainy() {
		return rainy;
	}
	public void setRainy(boolean rainy) {
		this.rainy = rainy;
	}
	public boolean isHotSunny() {
		return hotSunny;
	}
	public void setHotSunny(boolean hotSunny) {
		this.hotSunny = hotSunny;
	}
	  
	  

}
