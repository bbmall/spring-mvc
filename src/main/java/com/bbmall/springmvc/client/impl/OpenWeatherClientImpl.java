package com.bbmall.springmvc.client.impl;

import com.bbmall.springmvc.client.OpenWeatherClient;
import com.bbmall.springmvc.client.model.OWData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bmalinowski on 10.05.18.
 */
@Service
public class OpenWeatherClientImpl implements OpenWeatherClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${openweathermap.url}")
    private String openWeatherURL;

    @Value("${openweathermap.appid}")
    private String openWeatherAppId;

    @Autowired
    private RestTemplate restTemplate;

    public OWData getData(String countryName, String cityName) {
        logger.info("In Openweather client - getData");
        final OWData openweatherData = restTemplate.getForObject(getOpenWeatherUrl(countryName, cityName), OWData.class);

        return openweatherData;
    }

    private String getOpenWeatherUrl(String countryName, String cityName) {
        return openWeatherURL + String.format("?q=%s,%s&appid=%s", cityName, countryName, openWeatherAppId);

    }
}
