package com.bbmall.springmvc

import com.bbmall.springmvc.service.WeatherService
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
//import io.restassured.http.ContentType.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

/**
 * Created by bmalinowski on 10.05.18.
 */
@ContextConfiguration(classes = SpringMvcApplication.class, name = "integrationContext")
//@ActiveProfiles("test")
@SpringBootTest(classes = SpringMvcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FirstIntegrationSpec extends Specification {

    @Autowired
    WebApplicationContext context

    RESTClient restClient = new RESTClient("http://localhost:8585")


    def "endpoint should return non null value"() {
        when:
        def countryCode = "PL"
        def cityName = "Warsaw"

        def response = restClient.get(
                path: "/weather/statistics/" + countryCode + "/" + cityName,
                headers: ['Content-Type': 'application/json'],
                requestContentType: ContentType.JSON
        )
        then:
        response.status == 200


    }
}
