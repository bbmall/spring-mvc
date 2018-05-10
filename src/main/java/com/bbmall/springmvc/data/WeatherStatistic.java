package com.bbmall.springmvc.data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bmalinowski on 10.05.18.
 */
public class WeatherStatistic implements Serializable {

    private String cityName;
    private String countryCode;
    private BigDecimal averageTemperature;
    private BigDecimal averagePressure;
    private BigDecimal averageHumidity;

    public WeatherStatistic(String cityName, String countryCode, BigDecimal averageTemperature,
                            BigDecimal averagePressure, BigDecimal averageHumidity) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.averageTemperature = averageTemperature;
        this.averagePressure = averagePressure;
        this.averageHumidity = averageHumidity;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public BigDecimal getAverageTemperature() {
        return averageTemperature;
    }

    public BigDecimal getAveragePressure() {
        return averagePressure;
    }

    public BigDecimal getAverageHumidity() {
        return averageHumidity;
    }
//    public WeatherStatistic(String cityName, String countryCode, Double averageTemperature, Double averagePressure,
//                            Double averageHumidity) {
//        this.cityName = cityName;
//        this.countryCode = countryCode;
//        this.averageTemperature = averageTemperature;
//        this.averagePressure = averagePressure;
//        this.averageHumidity = averageHumidity;
//    }
//
//    public String getCityName() {
//        return cityName;
//    }
//
//    public String getCountryCode() {
//        return countryCode;
//    }
//
//    public Double getAverageTemperature() {
//        return averageTemperature;
//    }
//
//    public Double getAveragePressure() {
//        return averagePressure;
//    }
//
//    public Double getAverageHumidity() {
//        return averageHumidity;
//    }
}
