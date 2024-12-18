package com.fleet.vehicle_routing.domain;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;


@PlanningSolution
public class VehicleSolution {

    @PlanningId
    int id;

    @PlanningEntityCollectionProperty
    private List<Vehicle> vehicles;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider
    private List<Customer> visits;

    @PlanningScore
    private HardSoftScore hardSoftScore;


    public VehicleSolution() {
    }

    public VehicleSolution(int id, List<Customer> visits, List<Vehicle> vehicles) {
        this.id = id;
        this.visits = visits;
        this.vehicles = vehicles;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Customer> getVisits() {
        return visits;
    }

    public void setVisits(List<Customer> visits) {
        this.visits = visits;
    }

    public HardSoftScore getHardSoftScore() {
        return hardSoftScore;
    }

    public void setHardSoftScore(HardSoftScore hardSoftScore) {
        this.hardSoftScore = hardSoftScore;
    }
}
