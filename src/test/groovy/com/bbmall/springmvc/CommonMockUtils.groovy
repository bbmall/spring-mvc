package com.bbmall.springmvc

import com.bbmall.springmvc.client.model.OWData
import com.bbmall.springmvc.data.WeatherStatistic
import com.fasterxml.jackson.databind.ObjectMapper

import java.math.RoundingMode

/**
 * Created by bmalinowski on 12.05.18.
 */
class CommonMockUtils {
    def static CITY_MOCK = "Warsaw"
    def static COUNTRY_MOCK = "PL"

    def static WEATHER_STATISTIC_MOCK = new WeatherStatistic(CITY_MOCK, COUNTRY_MOCK, getBigDecimalValue(283.1266667),
            getBigDecimalValue(971.36), getBigDecimalValue(78.33333))

    def static getOWDataMock() {
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("test_owdata.json")
        ObjectMapper mapper = new ObjectMapper()
        mapper.readValue(is, OWData.class)
    }

    private static BigDecimal getBigDecimalValue(double value) {
        new BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
    }

    def static getEmptyWeatherStatistic() {
        new WeatherStatistic("", "", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)
    }
}
