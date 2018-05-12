package com.bbmall.springmvc.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bmalinowski on 10.05.18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OWData implements Serializable {
    private String cod;
    private Double message;
    private Long cnt;
    private City city;
    private List<Conditions> list;

    public List<Conditions> getList() {
        return list;
    }

    public void setList(List<Conditions> list) {
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "OWData{" +
                "cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", city=" + city +
                '}';
    }
}
