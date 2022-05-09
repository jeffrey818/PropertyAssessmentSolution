package com.PA;

public class Location {
    private double latitude;
    private double longitude;
    private String pointLoc;

    public Location(double latitude, double longitude, String pointLoc) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.pointLoc = pointLoc;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPointLoc() {
        return pointLoc;
    }

    @Override
    public String toString() {
        return
                "(" + latitude +
                ", " + longitude +
                ')';
    }
}
