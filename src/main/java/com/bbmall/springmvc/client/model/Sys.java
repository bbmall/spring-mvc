package com.bbmall.springmvc.client.model;

import java.io.Serializable;

/**
 * Created by bmalinowski on 12.05.18.
 */
public class Sys implements Serializable {
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
