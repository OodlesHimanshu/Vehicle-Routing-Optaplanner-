package com.fleet.vehicle_routing.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningListVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@PlanningEntity
public class Vehicle {

    @PlanningId
    private int id;
    private int capacity;
    private Location homeLocation;
    private LocalDateTime departureTime;

    @PlanningListVariable(allowsUnassignedValues = true)
    private List<Customer>visits;

    public Vehicle() {
    }

    public Vehicle(int id, int capacity, Location homeLocation, LocalDateTime departureTime) {
        this.id = id;
        this.capacity = capacity;
        this.homeLocation = homeLocation;
        this.departureTime = departureTime;
        this.visits = new ArrayList<>();
    }

    public int getTotalDemand() {
        int totalDemand = 0;
        for (Customer visit : visits) {
            totalDemand += visit.getDemand();
        }
        return totalDemand;
    }

    public List<Location> getRoute() {
        if (visits.isEmpty()) {
            return Collections.emptyList();
        }

        List<Location> route = new ArrayList<>();
        route.add(homeLocation);
        for (Customer visit : visits) {
            route.add(visit.getLocation());
        }
        route.add(homeLocation);
        return route;
    }

    public boolean isVisitNull(){
        for(Customer visit: visits){
            if(visit==null){
                return true;
            }
        }
        return false;
    }


    public long getTotalDrivingTimeSeconds() {
        if (visits.isEmpty()) {
            return 0;
        }

        long totalDrivingTime = 0;
        Location previousLocation = homeLocation;

        for (Customer visit : visits) {
            totalDrivingTime += previousLocation.getDrivingTimeTo(visit.getLocation());
            previousLocation = visit.getLocation();
        }
        totalDrivingTime += previousLocation.getDrivingTimeTo(homeLocation);

        return totalDrivingTime;
    }

    public int getTotalDistanceMeters() {

        if (visits.isEmpty()) {
            return 0;
        }

        int totalDistance = 0;

        Location previousLocation = homeLocation;


        for (Customer visit : visits) {
            totalDistance += previousLocation.getDrivingTimeTo(visit.getLocation());
            previousLocation = visit.getLocation();
        }

        totalDistance += previousLocation.getDrivingTimeTo(homeLocation);

        return totalDistance;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public List<Customer> getVisits() {
        return visits;
    }

    public void setVisits(List<Customer> visits) {
        this.visits = visits;
    }


}
