//package com.fleet.vehicle_routing.domain.DBDomain;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "vehicles")
//public class VehicleEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private int capacity;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "home_location_id")
//    private LocationEntity homeLocation;
//
//    @Column(name = "departure_time")
//    private LocalDateTime departureTime;
//
//    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<CustomerEntity> visits;
//
//    // Default constructor
//    public VehicleEntity() {
//        this.visits = new ArrayList<>();
//    }
//
//    // Parameterized constructor
//    public VehicleEntity(int id, int capacity, LocationEntity homeLocation, LocalDateTime departureTime) {
//        this.id = id;
//        this.capacity = capacity;
//        this.homeLocation = homeLocation;
//        this.departureTime = departureTime;
//        this.visits = new ArrayList<>();
//    }
//
//    // Calculate the total demand of all assigned customers
//    public int getTotalDemand() {
//        return visits.stream().mapToInt(CustomerEntity::getDemand).sum();
//    }
//
//    // Generate the complete route, including starting and returning to the home location
//    public List<LocationEntity> getRoute() {
//        List<LocationEntity> route = new ArrayList<>();
//        route.add(homeLocation);
//        for (CustomerEntity visit : visits) {
//            route.add(visit.getLocation());
//        }
//        route.add(homeLocation);  // Add home location at the end
//        return route;
//    }
//
//    // Calculate the total driving time in seconds (you may need to adjust this depending on how you calculate driving time)
//    public long getTotalDrivingTimeSeconds() {
//        long totalDrivingTime = 0;
//        LocationEntity previousLocation = homeLocation;
//
//        for (CustomerEntity visit : visits) {
//            totalDrivingTime += previousLocation.getDrivingTimeTo(visit.getLocation());
//            previousLocation = visit.getLocation();
//        }
//        totalDrivingTime += previousLocation.getDrivingTimeTo(homeLocation);
//
//        return totalDrivingTime;
//    }
//
//    // Getters and Setters
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public void setCapacity(int capacity) {
//        this.capacity = capacity;
//    }
//
//    public LocationEntity getHomeLocation() {
//        return homeLocation;
//    }
//
//    public void setHomeLocation(LocationEntity homeLocation) {
//        this.homeLocation = homeLocation;
//    }
//
//    public LocalDateTime getDepartureTime() {
//        return departureTime;
//    }
//
//    public void setDepartureTime(LocalDateTime departureTime) {
//        this.departureTime = departureTime;
//    }
//
//    public List<CustomerEntity> getVisits() {
//        return visits;
//    }
//
//    public void setVisits(List<CustomerEntity> visits) {
//        this.visits = visits;
//    }
//}
