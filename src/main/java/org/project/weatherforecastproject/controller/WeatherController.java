package org.project.weatherforecastproject.controller;

import org.project.weatherforecastproject.dto.WeatherResponse;
import org.project.weatherforecastproject.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController
{
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}/{day}/forecast")
    public WeatherResponse getWeatherForecast(@PathVariable String city,@PathVariable int day) {
        return weatherService.getWeatherForecast(city,day);
    }
}
