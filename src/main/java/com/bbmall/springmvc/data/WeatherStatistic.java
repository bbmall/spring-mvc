package com.bbmall.springmvc.data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bmalinowski on 10.05.18.
 */
public class WeatherStatistic implements Serializable {

    private String cityName;
    private String countryCode;
    private BigDecimal averageTemperature;
    private BigDecimal averagePressure;
    private BigDecimal averageHumidity;

    public WeatherStatistic(String cityName, String countryCode, BigDecimal averageTemperature,
                            BigDecimal averagePressure, BigDecimal averageHumidity) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.averageTemperature = averageTemperature;
        this.averagePressure = averagePressure;
        this.averageHumidity = averageHumidity;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public BigDecimal getAverageTemperature() {
        return averageTemperature;
    }

    public BigDecimal getAveragePressure() {
        return averagePressure;
    }

    public BigDecimal getAverageHumidity() {
        return averageHumidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherStatistic that = (WeatherStatistic) o;

        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (averageTemperature != null ? !averageTemperature.equals(that.averageTemperature) : that.averageTemperature != null)
            return false;
        if (averagePressure != null ? !averagePressure.equals(that.averagePressure) : that.averagePressure != null)
            return false;
        return averageHumidity != null ? averageHumidity.equals(that.averageHumidity) : that.averageHumidity == null;
    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (averageTemperature != null ? averageTemperature.hashCode() : 0);
        result = 31 * result + (averagePressure != null ? averagePressure.hashCode() : 0);
        result = 31 * result + (averageHumidity != null ? averageHumidity.hashCode() : 0);
        return result;
    }
}
