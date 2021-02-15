package com.orecic.recommentationtrackapi.domain.service;

public interface WeatherService {
    Double getWeatherByCity(String city);

    Double getWeatherByLatLon(String lat, String lon);
}

