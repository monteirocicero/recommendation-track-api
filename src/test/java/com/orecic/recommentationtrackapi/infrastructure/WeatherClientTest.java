package com.orecic.recommentationtrackapi.infrastructure;

import com.orecic.recommentationtrackapi.infrastructure.client.WeatherClient;
import com.orecic.recommentationtrackapi.infrastructure.data.weather.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherClientTest {

    @Autowired
    WeatherClient weatherClient;

    @Test
    public void shouldGetWeatherByCity() {
        WeatherResponse weatherResponse = weatherClient.getWeatherByCity("fortaleza", "metric","b77e07f479efe92156376a8b07640ced");
        Assertions.assertTrue(weatherResponse.isValid());
    }

    @Test
    public void shouldGetWeatherByLatAndLon() {
        WeatherResponse weatherResponse = weatherClient.getWeatherByCoordinates("-88.9167", "13.8333", "metric", "b77e07f479efe92156376a8b07640ced");
        Assertions.assertTrue(weatherResponse.isValid());

    }

}
