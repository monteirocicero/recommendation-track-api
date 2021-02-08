package com.orecic.recommentationtrackapi.infrastructure.client;

import com.orecic.recommentationtrackapi.infrastructure.data.weather.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${weather.api.name}", url = "${weather.api.url}")
public interface WeatherClient {

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    WeatherResponse getWeatherByCity(@RequestParam("q") String fortaleza,
                                     @RequestParam("units") String unit, @RequestParam("appid") String appId);

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    WeatherResponse getWeatherByCoordinates(@RequestParam("lon") String longitude, @RequestParam("lat") String latitude, @RequestParam("units") String metric, @RequestParam("appId") String appId);
}
