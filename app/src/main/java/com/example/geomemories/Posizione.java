package com.example.geomemories;

import android.location.Location;

public class Posizione {
    private double latitude;
    private double longitude;

    public Posizione(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Posizione(Location location){
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String format(){
        return Double.toString(latitude) + ";" + Double.toString(longitude);
    }


}
