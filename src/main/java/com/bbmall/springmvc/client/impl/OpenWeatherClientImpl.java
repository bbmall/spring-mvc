package com.bbmall.springmvc.client.impl;

import com.bbmall.springmvc.client.OpenWeatherClient;
import com.bbmall.springmvc.client.model.OWData;
import com.bbmall.springmvc.exceptions.ExternalServiceException;
import com.bbmall.springmvc.exceptions.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
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
        logger.info("Using external service \"" + openWeatherURL + "\"");
        try {
            final OWData openweatherData = restTemplate.getForObject(getOpenWeatherUrl(countryName, cityName), OWData.class);
            return openweatherData;
        } catch (HttpClientErrorException e) {
            throw new ExternalServiceException(e.getStatusCode(), e.getMessage());
        } catch (RestClientException e) {
            throw new NoDataFoundException(e.getMessage());
        }
    }

    private String getOpenWeatherUrl(String countryName, String cityName) {
        return openWeatherURL + String.format("?q=%s,%s&appid=%s", cityName, countryName, openWeatherAppId);

    }
}
