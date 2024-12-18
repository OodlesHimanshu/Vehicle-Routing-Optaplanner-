//package com.fleet.vehicle_routing.domain.DBDomain;
//
//import jakarta.persistence.*;
//import java.util.HashMap;
//import java.util.Map;
//
//@Entity
//@Table(name = "locations")
//public class LocationEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private double latitude;
//
//    private double longitude;
//
//    @ElementCollection
//    @CollectionTable(name = "driving_time", joinColumns = @JoinColumn(name = "location_id"))
//    @MapKeyJoinColumn(name = "target_location_id")
//    @Column(name = "driving_time_seconds")
//    private Map<LocationEntity, Long> drivingTimeSeconds = new HashMap<>();
//
//    public LocationEntity() {
//    }
//
//    public LocationEntity(int id, double latitude, double longitude) {
//        this.id = id;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Map<LocationEntity, Long> getDrivingTimeSeconds() {
//        return drivingTimeSeconds;
//    }
//
//    public void setDrivingTimeSeconds(Map<LocationEntity, Long> drivingTimeSeconds) {
//        this.drivingTimeSeconds = drivingTimeSeconds;
//    }
//
//    public long getDrivingTimeTo(LocationEntity location) {
//        return drivingTimeSeconds.get(location);
//    }
//}
