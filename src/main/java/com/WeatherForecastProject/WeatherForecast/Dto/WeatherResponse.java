package com.WeatherForecastProject.WeatherForecast.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

private String country;
private String region;
private Date date;
private Current current;
private List<Forecast> forecastList;

}
