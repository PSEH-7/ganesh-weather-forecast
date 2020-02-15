
package com.sapient.weatherforecast.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cod",
    "message",
    "cnt",
    "list",
    "city"
})
public class OpenWeatherAPIResp implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6350013359221685010L;
	
	
	@JsonProperty("cod")
    private String cod;
    @JsonProperty("message")
    private Double message;
    @JsonProperty("cnt")
    private Long cnt;
    @JsonProperty("list")
    private java.util.List<ADayWeather> dayWeatherList = new ArrayList<>();
    @JsonProperty("city")
    private City city;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    public OpenWeatherAPIResp withCod(String cod) {
        this.cod = cod;
        return this;
    }

    @JsonProperty("message")
    public Double getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Double message) {
        this.message = message;
    }

    public OpenWeatherAPIResp withMessage(Double message) {
        this.message = message;
        return this;
    }

    @JsonProperty("cnt")
    public Long getCnt() {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }

    public OpenWeatherAPIResp withCnt(Long cnt) {
        this.cnt = cnt;
        return this;
    }

    @JsonProperty("list")
    public List<ADayWeather> getDayWeatherList() {
        return dayWeatherList;
    }

    @JsonProperty("list")
    public void setDayWeatherList(List<ADayWeather> dayWeatherList) {
        this.dayWeatherList = dayWeatherList;
    }

    public OpenWeatherAPIResp withDayWeatherList(List<ADayWeather> dayWeatherList) {
        this.dayWeatherList = dayWeatherList;
        return this;
    }

    @JsonProperty("city")
    public City getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(City city) {
        this.city = city;
    }

    public OpenWeatherAPIResp withCity(City city) {
        this.city = city;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OpenWeatherAPIResp withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
