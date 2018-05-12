package com.bbmall.springmvc

import com.bbmall.springmvc.client.OpenWeatherClient
import com.bbmall.springmvc.client.model.OWData
import com.bbmall.springmvc.data.WeatherStatistic
import com.bbmall.springmvc.service.WeatherService
import com.bbmall.springmvc.service.impl.WeatherServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

import java.math.RoundingMode

/**
 * Created by bmalinowski on 10.05.18.
 */
class WeatherServiceSpec extends Specification {

    def city = "Warsaw"
    def country = "PL"
    WeatherService weatherService = new WeatherServiceImpl()
    OpenWeatherClient openWeatherClientMock = Mock()

    def 'calculate mock data should be correct'() {
        given:
        def weatherStatisticMock = new WeatherStatistic(city, country, getBigDecimalValue(283.1266667),
                getBigDecimalValue(971.36), getBigDecimalValue(78.33333))

        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("test_owdata.json")
        ObjectMapper mapper = new ObjectMapper()
        def oWData = mapper.readValue(is, OWData.class)

        openWeatherClientMock.getData(_, _) >> oWData
        ReflectionTestUtils.setField(weatherService, "openWeatherClient", openWeatherClientMock)

        WeatherStatistic statistic = weatherService.calculateStatisticForCountryAndCity(country, city)

        expect:
        statistic == weatherStatisticMock
    }

    private static BigDecimal getBigDecimalValue(double value) {
        new BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
    }
}
