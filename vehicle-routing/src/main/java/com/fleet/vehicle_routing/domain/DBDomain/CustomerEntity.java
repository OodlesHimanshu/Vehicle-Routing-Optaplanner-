//package com.fleet.vehicle_routing.domain.DBDomain;
//
//import jakarta.persistence.*;
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "customers")
//public class CustomerEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_id")
//    private LocationEntity location;
//
//    private int demand;
//
//    @Column(name = "min_start_time")
//    private LocalDateTime minStartTime;
//
//    @Column(name = "max_end_time")
//    private LocalDateTime maxEndTime;
//
//   // @Convert(converter = DurationConverter.class)
//    @Column(name = "service_duration")
//    private Duration serviceDuration;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vehicle_id")
//    private VehicleEntity vehicle;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "previous_visit_id")
//    private CustomerEntity previousVisit;
//
//    @Column(name = "arrival_time")
//    private LocalDateTime arrivalTime;
//
//    public CustomerEntity() {
//    }
//
//    public CustomerEntity(String name, LocationEntity location, int demand, LocalDateTime minStartTime,
//                          LocalDateTime maxEndTime, Duration serviceDuration) {
//        this.name = name;
//        this.location = location;
//        this.demand = demand;
//        this.minStartTime = minStartTime;
//        this.maxEndTime = maxEndTime;
//        this.serviceDuration = serviceDuration;
//    }
//
//    // Getters and setters...
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocationEntity getLocation() {
//        return location;
//    }
//
//    public void setLocation(LocationEntity location) {
//        this.location = location;
//    }
//
//    public int getDemand() {
//        return demand;
//    }
//
//    public void setDemand(int demand) {
//        this.demand = demand;
//    }
//
//    public LocalDateTime getMinStartTime() {
//        return minStartTime;
//    }
//
//    public void setMinStartTime(LocalDateTime minStartTime) {
//        this.minStartTime = minStartTime;
//    }
//
//    public LocalDateTime getMaxEndTime() {
//        return maxEndTime;
//    }
//
//    public void setMaxEndTime(LocalDateTime maxEndTime) {
//        this.maxEndTime = maxEndTime;
//    }
//
//    public Duration getServiceDuration() {
//        return serviceDuration;
//    }
//
//    public void setServiceDuration(Duration serviceDuration) {
//        this.serviceDuration = serviceDuration;
//    }
//
//    public VehicleEntity getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(VehicleEntity vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    public CustomerEntity getPreviousVisit() {
//        return previousVisit;
//    }
//
//    public void setPreviousVisit(CustomerEntity previousVisit) {
//        this.previousVisit = previousVisit;
//    }
//
//    public LocalDateTime getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public void setArrivalTime(LocalDateTime arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//}
