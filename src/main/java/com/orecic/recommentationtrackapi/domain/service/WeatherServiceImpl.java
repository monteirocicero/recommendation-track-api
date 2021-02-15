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
    private final String unit = "metric";

    @Autowired
    public WeatherServiceImpl(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public Double getWeatherByCity(String city) {
        return weatherClient.getWeatherByCity(city, unit, weatherAppId).getTemperature();
    }

    @Override
    public Double getWeatherByLatLon(String lat, String lon) {
        return weatherClient.getWeatherByCoordinates(lat, lon, unit, weatherAppId).getTemperature();
    }
}
