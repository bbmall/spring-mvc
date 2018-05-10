package com.bbmall.springmvc.service;

import com.bbmall.springmvc.data.WeatherStatistic;

/**
 * Created by bmalinowski on 10.05.18.
 */
public interface WeatherService {

    WeatherStatistic calculateStatisticForCountryAndCity(String countryName, String cityName);
}
