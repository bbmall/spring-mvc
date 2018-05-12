package com.bbmall.springmvc.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bmalinowski on 12.05.18.
 */
public class MainConditions implements Serializable {
    private BigDecimal temp;
    @JsonProperty("temp_min")
    private BigDecimal tempMin;
    @JsonProperty("temp_max")
    private BigDecimal tempMax;
    private BigDecimal pressure;
    @JsonProperty("sea_level")
    private BigDecimal seaLevel;
    @JsonProperty("grnd_level")
    private BigDecimal grndLevel;
    private BigDecimal humidity;
    @JsonProperty("temp_kf")
    private BigDecimal tempKf;

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public BigDecimal getTempMin() {
        return tempMin;
    }

    public void setTempMin(BigDecimal tempMin) {
        this.tempMin = tempMin;
    }

    public BigDecimal getTempMax() {
        return tempMax;
    }

    public void setTempMax(BigDecimal tempMax) {
        this.tempMax = tempMax;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(BigDecimal seaLevel) {
        this.seaLevel = seaLevel;
    }

    public BigDecimal getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(BigDecimal grndLevel) {
        this.grndLevel = grndLevel;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getTempKf() {
        return tempKf;
    }

    public void setTempKf(BigDecimal tempKf) {
        this.tempKf = tempKf;
    }
}
