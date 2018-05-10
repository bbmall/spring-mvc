package com.bbmall.springmvc.data.client;

import com.bbmall.springmvc.data.WeatherStatistic;

/**
 * Created by bmalinowski on 10.05.18.
 */
public interface OpenWeatherClient {
    WeatherStatistic getData(String countryName, String cityName);
}
