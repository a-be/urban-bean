package com.hirath.urbanbean.mapping;

import java.util.List;

public class Trafic {
    private List<Radar> radars;

    public Trafic(List<Radar> radars) {
        this.radars = radars;
    }

    public void introduce(String description, Object value){
        radars.forEach(radar -> radar.routed(description, value));
    }
}
