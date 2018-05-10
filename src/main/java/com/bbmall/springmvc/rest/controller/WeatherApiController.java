package com.bbmall.springmvc.rest.controller;

import com.bbmall.springmvc.data.WeatherStatistic;
import com.bbmall.springmvc.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bmalinowski on 10.05.18.
 */
@RestController
@RequestMapping("/weather")
public class WeatherApiController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/statistics/{countryCode}/{cityName}")
    public ResponseEntity<WeatherStatistic> getStatistics(@PathVariable("countryCode") String countryCode,
                                                          @PathVariable("cityName") String cityName) {
        final WeatherStatistic weatherStatistic = weatherService.calculateStatisticForCountryAndCity(countryCode, cityName);
        return ResponseEntity.ok(weatherStatistic);
    }
}
