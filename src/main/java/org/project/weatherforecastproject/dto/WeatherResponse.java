package org.project.weatherforecastproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class WeatherResponse
{
    private String region;
    private String country;
    private Date date;
    private Current current;
    private List<Forecast> forecast;
}
