package com.bbmall.springmvc

import com.bbmall.springmvc.client.OpenWeatherClient
import com.bbmall.springmvc.service.WeatherService
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

//import io.restassured.http.ContentType.JSON
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

/**
 * Created by bmalinowski on 10.05.18.
 */
@ContextConfiguration(classes = SpringMvcApplication.class, name = "integrationContext")
@ActiveProfiles("test")
@SpringBootTest(classes = SpringMvcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WeatherControllerSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8585")

    @Autowired
    WebApplicationContext context

    @Autowired
    WeatherService weatherService

    OpenWeatherClient openWeatherClientMock

    def setup() {
        openWeatherClientMock = Mock()
        openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
        ReflectionTestUtils.setField(weatherService, "openWeatherClient", openWeatherClientMock)
    }

    def "endpoint should return non null value"() {
        when:
        def response = restClient.get(
                path: "/weather/statistics/" + CommonMockUtils.COUNTRY_MOCK + "/" + CommonMockUtils.CITY_MOCK,
                headers: ['Content-Type': 'application/json'],
                requestContentType: ContentType.JSON
        )
        then:
        response.status == 200
    }

    def "test cache. second request should not call client"() {
        def city = "X"
        def country = "Y"
        when:
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then:
        1 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()

        when:
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then:
        0 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
    }

    def "test cache. after 5 sec second request should call client"() {
        def city = "W"
        def country = "E"
        when:
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then:
        1 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()

        when:
        Thread.sleep(5000)
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then:
        1 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
    }
}
