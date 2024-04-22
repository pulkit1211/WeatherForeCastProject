package org.project.weatherforecastproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Current
{
    private int maxTemp;
    private int minTemp;
    private String text;
}
