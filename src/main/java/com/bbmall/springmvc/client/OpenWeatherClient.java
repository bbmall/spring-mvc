package com.bbmall.springmvc.client;

import com.bbmall.springmvc.client.model.OWData;

/**
 * Created by bmalinowski on 10.05.18.
 */
public interface OpenWeatherClient {
    OWData getData(String countryName, String cityName);
}
