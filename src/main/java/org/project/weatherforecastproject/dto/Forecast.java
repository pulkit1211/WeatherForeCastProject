package org.project.weatherforecastproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Forecast
{
    private Date date;
    private int maxTemp;
    private int minTemp;
    private int avgTemp;
    private String description;
}
