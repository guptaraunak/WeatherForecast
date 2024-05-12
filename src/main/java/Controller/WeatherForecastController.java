package Controller;

import Dto.WeatherResponse;
import Service.WeatherForecastService;
import com.WeatherForecastProject.WeatherForecast.WeatherForecastApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather/Forecast")
public class WeatherForecastController {

    @Autowired
    WeatherForecastService weatherForecastService;

@GetMapping("/{city}/{days}")
public WeatherResponse getWeatherForecast(@PathVariable("city") String city, @PathVariable("days") int days)
{
    return weatherForecastService.getWeatherForcastData(city, days);
}

}
