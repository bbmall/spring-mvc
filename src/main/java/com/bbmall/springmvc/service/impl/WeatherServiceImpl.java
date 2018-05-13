package com.bbmall.springmvc.service.impl;

import com.bbmall.springmvc.client.OpenWeatherClient;
import com.bbmall.springmvc.client.model.Conditions;
import com.bbmall.springmvc.client.model.MainConditions;
import com.bbmall.springmvc.client.model.OWData;
import com.bbmall.springmvc.data.WeatherStatistic;
import com.bbmall.springmvc.exceptions.BadParameterException;
import com.bbmall.springmvc.exceptions.NoDataFoundException;
import com.bbmall.springmvc.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        validateInput(countryName, cityName);
        final OWData data = openWeatherClient.getData(countryName, cityName);

        return calculateStatistics(data);
    }

    private void validateInput(String countryName, String cityName) {
        if (StringUtils.isEmpty(countryName))
            throw new BadParameterException("The 'countryName' parameter is missing");
        if (StringUtils.isEmpty(cityName))
            throw new BadParameterException("The 'cityName' parameter is missing");
    }

    private WeatherStatistic calculateStatistics(OWData owData) {
        logger.info("Calculating data for " + owData.getCity());
        validateData(owData);

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

    private void validateData(OWData owData) {
        if (owData == null || owData.getList() == null || owData.getList().isEmpty())
            throw new NoDataFoundException("Wrong country or city name");
    }

    private BigDecimal getAverageValue(BigDecimal sumTemperature, int count) {
        return sumTemperature.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
    }


}
