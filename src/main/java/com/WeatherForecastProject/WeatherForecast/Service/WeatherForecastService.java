package com.WeatherForecastProject.WeatherForecast.Service;

import com.WeatherForecastProject.WeatherForecast.Dto.Current;
import com.WeatherForecastProject.WeatherForecast.Dto.Forecast;
import com.WeatherForecastProject.WeatherForecast.Dto.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherForecastService {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public WeatherResponse getWeatherForcastData(String city, int days) {

        String url= UriComponentsBuilder.fromHttpUrl("http://api.weatherapi.com/v1/forecast.json")
                .queryParam("key", "c4c6cd8bf9484bb1b10142150240905")
                .queryParam("q",city)
                .queryParam("days",days)
                .toUriString();

        RestTemplate restTemplate=restTemplateBuilder.build();

        JsonNode jsonNode=restTemplate.getForObject(url, JsonNode.class);

        return mapJsonNodeToWeatherResponse(jsonNode);
    }

    private WeatherResponse mapJsonNodeToWeatherResponse(JsonNode jsonNode)
    {
        WeatherResponse weatherResponse=new WeatherResponse();

        weatherResponse.setCountry(jsonNode.path("location").path("country").asText());
        weatherResponse.setRegion(jsonNode.path("location").path("region").asText());
        weatherResponse.setDate(new Date(jsonNode.path("location").path("localtime_epoch").asLong()*1000));

        Current current=new Current();

        current.setTemp(jsonNode.path("current").path("temp_c").asInt());
        current.setDesc(jsonNode.path("current").path("condition").path("text").asText());
        weatherResponse.setCurrent(current);

        List<Forecast> forecastList=new ArrayList<>();
        jsonNode.path("forecast").path("forecastday").forEach(dayNode -> {
            Forecast forecast=new Forecast();
            forecast.setDate(new Date(dayNode.path("date_epoch").asLong()*1000));
            forecast.setMinTemp(dayNode.path("day").path("mintemp_c").asInt());
            forecast.setMaxTemp(dayNode.path("day").path("maxtemp_c").asInt());
            forecast.setAvgTemp(dayNode.path("day").path("avgtemp_c").asInt());
            forecast.setDesc(dayNode.path("day").path("condition").path("text").asText());
            forecastList.add(forecast);
        });

        weatherResponse.setForecastList(forecastList);

        return weatherResponse;
    }

}
