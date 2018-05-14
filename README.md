# Weather-info
Basic REST server utilizing spring-boot 2, spring mvc, java 8 and Spock


Application runs on port 8585. To run application simple run:

`mvn spring-boot:run`

To run integration tests use

`mvn clean verify -PintegrationTests` 

Application exposes a REST endpoint:

   `/weather/avg?countryCode=PL&cityName=Warsaw`

providing example output:

```json
{
    "cityName": "Warsaw",
    "countryCode": "PL",
    "averageTemperature": 274.74,
    "averagePressure": 966.21,
    "averageHumidity": 88.78
}
