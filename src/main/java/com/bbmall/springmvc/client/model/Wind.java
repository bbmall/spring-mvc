package com.bbmall.springmvc.client.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bmalinowski on 12.05.18.
 */
public class Wind implements Serializable {
    private BigDecimal speed;
    private BigDecimal deg;

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getDeg() {
        return deg;
    }

    public void setDeg(BigDecimal deg) {
        this.deg = deg;
    }
}
