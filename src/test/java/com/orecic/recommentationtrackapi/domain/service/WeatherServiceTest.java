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
    public void shouldGetWeatherWithSuccess() {
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
}
