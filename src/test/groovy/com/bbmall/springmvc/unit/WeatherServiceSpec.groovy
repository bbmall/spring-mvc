package com.bbmall.springmvc.unit

import com.bbmall.springmvc.CommonMockUtils
import com.bbmall.springmvc.client.OpenWeatherClient
import com.bbmall.springmvc.data.WeatherStatistic
import com.bbmall.springmvc.service.WeatherService
import com.bbmall.springmvc.service.impl.WeatherServiceImpl
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

/**
 * Created by bmalinowski on 10.05.18.
 */
class WeatherServiceSpec extends Specification {

    WeatherService weatherService = new WeatherServiceImpl()
    OpenWeatherClient openWeatherClientMock = Mock()

    def 'calculate mock data should be correct'() {
        given:
        openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
        ReflectionTestUtils.setField(weatherService, "openWeatherClient", openWeatherClientMock)

        WeatherStatistic statistic = weatherService.calculateStatisticForCountryAndCity(CommonMockUtils.COUNTRY_MOCK,
                CommonMockUtils.CITY_MOCK)

        expect:
        statistic == CommonMockUtils.WEATHER_STATISTIC_MOCK
    }

}
