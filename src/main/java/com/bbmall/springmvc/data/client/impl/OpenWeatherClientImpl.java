package com.bbmall.springmvc.data.client.impl;

import com.bbmall.springmvc.data.WeatherStatistic;
import com.bbmall.springmvc.data.client.OpenWeatherClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Created by bmalinowski on 10.05.18.
 */
@Service
public class OpenWeatherClientImpl implements OpenWeatherClient {
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    public WeatherStatistic getData(String countryName, String cityName){
        logger.info("In Openweather client - getData");
        return fakeData(countryName, cityName);
    }

    private WeatherStatistic fakeData(String countryName, String cityName){
        final int rm = BigDecimal.ROUND_HALF_UP;
        final BigDecimal averageTemperature = new BigDecimal(1 / 3.0).setScale(2, rm);
        final BigDecimal averagePressure = new BigDecimal(1 / 7.0).setScale(2, rm);
        final BigDecimal averageHumidity = new BigDecimal(1 / 11.0).setScale(2, rm);
        return new WeatherStatistic(cityName, countryName, averageTemperature, averagePressure,
                averageHumidity);
    }
}
