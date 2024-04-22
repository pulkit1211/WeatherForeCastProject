package org.project.weatherforecastproject.service;

import org.project.weatherforecastproject.dto.Current;
import org.project.weatherforecastproject.dto.Forecast;
import org.project.weatherforecastproject.dto.WeatherResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public WeatherResponse getWeatherForecast(String city,int day) {
        String url = UriComponentsBuilder.fromHttpUrl("http://api.weatherapi.com/v1/forecast.json")
                .queryParam("key", "3ee06e71e8464e0f84363730242204")
                .queryParam("q", city)
                .queryParam("days", day)  // Customize based on need
                .toUriString();
        RestTemplate restTemplate=restTemplateBuilder.build();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        return mapJsonToWeatherResponse(jsonNode);
    }

    private WeatherResponse mapJsonToWeatherResponse(JsonNode jsonNode) {
        WeatherResponse response = new WeatherResponse();
        response.setRegion(jsonNode.path("location").path("region").asText());
        response.setCountry(jsonNode.path("location").path("country").asText());
        response.setDate(new Date(jsonNode.path("location").path("localtime_epoch").asLong() * 1000));

        Current current = new Current();
        current.setMaxTemp(jsonNode.path("current").path("temp_c").asInt());
        current.setMinTemp(jsonNode.path("current").path("temp_c").asInt());  // Assume same as max for simplification
        current.setText(jsonNode.path("current").path("condition").path("text").asText());
        response.setCurrent(current);

        List<Forecast> forecasts = new ArrayList<>();
        jsonNode.path("forecast").path("forecastday").forEach(dayNode -> {
            Forecast forecast = new Forecast();
            forecast.setDate(new Date(dayNode.path("date_epoch").asLong() * 1000));
            forecast.setMaxTemp(dayNode.path("day").path("maxtemp_c").asInt());
            forecast.setMinTemp(dayNode.path("day").path("mintemp_c").asInt());
            forecast.setAvgTemp(dayNode.path("day").path("avgtemp_c").asInt());
            forecast.setDescription(dayNode.path("day").path("condition").path("text").asText());
            forecasts.add(forecast);
        });
        response.setForecast(forecasts);

        return response;
    }
}
