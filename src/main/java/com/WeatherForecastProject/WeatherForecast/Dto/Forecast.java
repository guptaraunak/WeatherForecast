package com.WeatherForecastProject.WeatherForecast.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Forecast {

    private Date date;
    private int maxTemp;
    private int minTemp;
    private int avgTemp;
    private String desc;
}
