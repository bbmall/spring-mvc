package com.bbmall.springmvc.service.impl;

import com.bbmall.springmvc.client.OpenWeatherClient;
import com.bbmall.springmvc.client.model.Conditions;
import com.bbmall.springmvc.client.model.MainConditions;
import com.bbmall.springmvc.client.model.OWData;
import com.bbmall.springmvc.data.WeatherStatistic;
import com.bbmall.springmvc.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


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
        final OWData data = openWeatherClient.getData(countryName, cityName);
        return calculateStatistics(data);
    }

    private WeatherStatistic calculateStatistics(OWData owData) {
        final List<MainConditions> mainConditions = owData.getList().stream().map(Conditions::getMain)
                .collect(Collectors.toList());
        BigDecimal sumTemperature = BigDecimal.ZERO;
        BigDecimal sumPressure = BigDecimal.ZERO;
        BigDecimal sumHumidity = BigDecimal.ZERO;

        for (MainConditions main : mainConditions) {
            sumHumidity = sumHumidity.add(main.getHumidity());
            sumPressure = sumPressure.add(main.getPressure());
            sumTemperature = sumTemperature.add(main.getTemp());
        }
        return new WeatherStatistic(owData.getCity().getName(), owData.getCity().getCountry(),
                getAverageValue(sumTemperature, mainConditions.size()),
                getAverageValue(sumPressure, mainConditions.size()),
                getAverageValue(sumHumidity, mainConditions.size()));
    }

    private BigDecimal getAverageValue(BigDecimal sumTemperature, int count) {
        return sumTemperature.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
    }


}
