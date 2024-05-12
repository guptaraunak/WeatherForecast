package com.WeatherForecastProject.WeatherForecast.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {

    private int temp;
    private String desc;

}
