package com.fleet.vehicle_routing.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Location {
    private int id;
    private double latitude;
    private double longitude;

    private Map<Location, Long> drivingTimeSeconds = new HashMap<>();

    public Location(){

    }
    public Location(int id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public Map<Location, Long> getDrivingTimeSeconds() {
        return drivingTimeSeconds;
    }

    public void setDrivingTimeSeconds(Map<Location, Long> drivingTimeSeconds) {
        this.drivingTimeSeconds = drivingTimeSeconds;
    }

    public long getDrivingTimeTo(Location location) {
        return drivingTimeSeconds.get(location);
    }

    public void calculateDistances(List<Location> locations) {
        for (Location location : locations) {
            long distance = (long) Math.sqrt(Math.pow(latitude - location.latitude, 2) + Math.pow(longitude - location.longitude, 2));
            drivingTimeSeconds.put(location, distance);
        }
    }
//    public void calculateDistances(List<Location> locations) {
//        final int EARTH_RADIUS_KM = 6371; // Radius of the Earth in kilometers
//
//        for (Location location : locations) {
//            // Convert latitude and longitude from degrees to radians
//            double latitude1 = Math.toRadians(this.latitude);
//            double longitude1 = Math.toRadians(this.longitude);
//            double latitude2 = Math.toRadians(location.latitude);
//            double longitude2 = Math.toRadians(location.longitude);
//
//            // Calculate the differences in latitude and longitude
//            double deltaLatitude = latitude2 - latitude1;
//            double deltaLongitude = longitude2 - longitude1;
//
//            // Haversine formula
//            double a = Math.pow(Math.sin(deltaLatitude / 2), 2)
//                    + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLongitude / 2), 2);
//            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//            // Calculate the distance in kilometers
//            long distance = Math.round(EARTH_RADIUS_KM * c);
//
//            // Store the distance in the distanceMap
//            drivingTimeSeconds.put(location, distance);
//        }
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
