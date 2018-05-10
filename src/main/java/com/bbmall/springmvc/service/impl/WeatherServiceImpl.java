package com.bbmall.springmvc.service.impl;

import com.bbmall.springmvc.data.WeatherStatistic;
import com.bbmall.springmvc.data.client.OpenWeatherClient;
import com.bbmall.springmvc.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * Created by bmalinowski on 10.05.18.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @Cacheable(value = "OPEN_WEATHER_MAP_CACHE")
    public WeatherStatistic calculateStatisticForCountryAndCity(String countryName, String cityName) {
        logger.info("country=" + countryName + " and city=" + cityName);
        return openWeatherClient.getData(countryName, cityName);
    }

}
