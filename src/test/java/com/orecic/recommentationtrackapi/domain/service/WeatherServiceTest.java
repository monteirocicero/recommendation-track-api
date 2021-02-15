package com.orecic.recommentationtrackapi.domain.service;

import com.orecic.recommentationtrackapi.infrastructure.client.WeatherClient;
import com.orecic.recommentationtrackapi.infrastructure.data.weather.Main;
import com.orecic.recommentationtrackapi.infrastructure.data.weather.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WeatherServiceTest {

    @MockBean
    private WeatherClient weatherClient;

    @InjectMocks
    WeatherServiceImpl weatherService;

    @BeforeEach
    public void init() {
        weatherService = new WeatherServiceImpl(weatherClient);
    }

    @Test
    public void shouldGetWeatherByCityNameWithSuccess() {
        // given
        var city = "campinas";
        var weatherMock = new WeatherResponse();
        var mainTemperature = new Main();
        mainTemperature.setTemp(27.62);
        weatherMock.setMain(mainTemperature);
        ReflectionTestUtils.setField(weatherService, "weatherAppId", "b77e07f479efe92156376a8b07640ced");

        // when
        Mockito.when(weatherClient.getWeatherByCity(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(weatherMock);

        // then
        var realTemperature = weatherService.getWeatherByCity(city);

        Assertions.assertEquals(mainTemperature.getTemp(), realTemperature);
    }

    @Test
    public void shouldGetWeatherByLatLonWithSuccess() {
        // given
        var lat = "59.9127";
        var lon = "10.7461";
        var weatherMock = new WeatherResponse();
        var mainTemperature = new Main();
        mainTemperature.setTemp(-3.86);
        weatherMock.setMain(mainTemperature);
        ReflectionTestUtils.setField(weatherService, "weatherAppId", "b77e07f479efe92156376a8b07640ced");

        // when
        Mockito.when(weatherClient.getWeatherByCoordinates(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(weatherMock);

        // then
        var realTemperature = weatherService.getWeatherByLatLon(lat, lon);

        Assertions.assertEquals(mainTemperature.getTemp(), realTemperature);
    }


}
