package com.kibe.QuizApplication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RiderLocation {
    private String riderId;
    private String name;
    private double latitude;
    private double longitude;

    public RiderLocation(String riderId, String name, double latitude, double longitude) {
        this.riderId = riderId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RiderLocation{" +
                "riderId='" + riderId + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
