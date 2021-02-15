package com.orecic.recommentationtrackapi.domain.service;

import com.orecic.recommentationtrackapi.infrastructure.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.appId}")
    private String weatherAppId;

    private final WeatherClient weatherClient;

    @Autowired
    public WeatherServiceImpl(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public Double getWeatherByCity(String city) {
        String unit = "metric";
        return weatherClient.getWeatherByCity(city, unit, weatherAppId).getTemperature();
    }
}
