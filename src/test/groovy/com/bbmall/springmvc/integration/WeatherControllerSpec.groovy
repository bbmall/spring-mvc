package com.bbmall.springmvc.integration

import com.bbmall.springmvc.CommonMockUtils
import com.bbmall.springmvc.SpringMvcApplication
import com.bbmall.springmvc.client.OpenWeatherClient
import com.bbmall.springmvc.exceptions.NoDataFoundException
import com.bbmall.springmvc.service.WeatherService
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

//import io.restassured.http.ContentType.JSON
/**
 * Created by bmalinowski on 10.05.18.
 */
@ContextConfiguration(classes = SpringMvcApplication.class, name = "integrationContext")
@ActiveProfiles("test")
@SpringBootTest(classes = SpringMvcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WeatherControllerSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8585/")

    @Autowired
    WebApplicationContext context

    @Autowired
    WeatherService weatherService

    OpenWeatherClient openWeatherClientMock = Mock()

    def setup() {
        openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
        ReflectionTestUtils.setField(weatherService, "openWeatherClient", openWeatherClientMock)
    }

    def "null arguments should return 400"() {
        expect:
        try {
            response = restClient.get(
                    path: "/weather/avg",
                    headers: ['Content-Type': 'application/json'],
                    requestContentType: ContentType.JSON,
                    query: [
                            countryCode: CommonMockUtils.COUNTRY_MOCK,
                    ]
            )
        } catch (Exception e) {
            e.statusCode == 400
        }
        try {
            response = restClient.get(
                    path: "/weather/avg",
                    headers: ['Content-Type': 'application/json'],
                    requestContentType: ContentType.JSON,
                    query: [
                            cityName: CommonMockUtils.CITY_MOCK,
                    ]
            )
        } catch (Exception e) {
            e.statusCode == 400
        }
        try {
            response = restClient.get(
                    path: "/weather/avg",
                    headers: ['Content-Type': 'application/json'],
                    requestContentType: ContentType.JSON
            )
        } catch (Exception e) {
            e.statusCode == 400
        }
    }

    def "endpoint should return non null value"() {
        when:
        def response = restClient.get(
                path: "/weather/avg",
                headers: ['Content-Type': 'application/json'],
                requestContentType: ContentType.JSON,
                query: [
                        countryCode: CommonMockUtils.COUNTRY_MOCK,
                        cityName   : CommonMockUtils.CITY_MOCK
                ]
        )
        then:
        response.status == 200
    }

    def "no data for given input should return 404"() {

        openWeatherClientMock = Mock(OpenWeatherClient)
        openWeatherClientMock.getData(CommonMockUtils.COUNTRY_MOCK, CommonMockUtils.CITY_MOCK) >> {
            throw new NoDataFoundException("No data found")
        }
        ReflectionTestUtils.setField(weatherService, "openWeatherClient", openWeatherClientMock)

        expect:
        try {
            def response = restClient.get(
                    path: "/weather/avg",
                    headers: ['Content-Type': 'application/json'],
                    requestContentType: ContentType.JSON,
                    query: [
                            countryCode: CommonMockUtils.COUNTRY_MOCK,
                            cityName   : CommonMockUtils.CITY_MOCK
                    ]
            )
        } catch (Exception e) {
            e.statusCode == 404
        }
    }

    def "test cache. second request should not call client"() {
        def city = "X"
        def country = "Y"
        when: "first call"
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then: "get client data"
        1 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()

        when: "second call"
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then: "get from cache"
        0 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
    }

    def "test cache. after 5 sec second request should call client"() {
        def city = "W"
        def country = "E"
        when: "first call"
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then: "get client data"
        1 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()

        when: "second call after cache evicted"
        //TODO: replace with sth that simulate time passed
        Thread.sleep(5000)
        weatherService.calculateStatisticForCountryAndCity(country, city)
        then: "get new client data"
        1 * openWeatherClientMock.getData(_, _) >> CommonMockUtils.getOWDataMock()
    }
}
