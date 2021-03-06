package com.orecic.recommentationtrackapi.infrastructure.data.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherResponse {

    @JsonProperty("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @JsonIgnore
    public boolean isValid() {
        return main.getTemp() != null;
    }

    @JsonIgnore
    public Double getTemperature() {
        if (isValid()) {
            return getMain().getTemp();
        }
        throw new RuntimeException("Temperature does not exist");
    }
}
