package com.bbmall.springmvc.client.model;

import java.io.Serializable;

/**
 * Created by bmalinowski on 12.05.18.
 */
public class Coordinate implements Serializable {
    private Double lat;
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
