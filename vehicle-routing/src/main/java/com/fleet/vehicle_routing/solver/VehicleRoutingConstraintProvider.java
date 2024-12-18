package com.fleet.vehicle_routing.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import com.fleet.vehicle_routing.domain.Vehicle;
import com.fleet.vehicle_routing.domain.Customer;
import org.jspecify.annotations.NonNull;

public class VehicleRoutingConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint @NonNull [] defineConstraints(@NonNull ConstraintFactory factory) {
        return new Constraint[]{
                vehicleCapacity(factory),
                serviceFinishedAfterMaxEndTime(factory),
                maxVehicleDrivingTime(factory),
               // customerServiceTimeWindow(factory),
                visitShouldHaveVehicle(factory)
        };
    }


    public Constraint maxVehicleDrivingTime(ConstraintFactory factory) {
        final long MAX_DRIVING_TIME = 28800;

        return factory
                .forEach(Vehicle.class)
                .filter(vehicle -> vehicle.getTotalDrivingTimeSeconds() > MAX_DRIVING_TIME)
                .penalize(HardSoftScore.ONE_HARD,
                        vehicle -> Math.toIntExact(vehicle.getTotalDrivingTimeSeconds() - MAX_DRIVING_TIME)) // Safe conversion
                .asConstraint("maxVehicleDrivingTime");
    }


//    public Constraint customerServiceTimeWindow(ConstraintFactory factory) {
//        return factory
//                .forEach(Customer.class)
//                .filter(customer -> customer.getStartServiceTime() == null || customer.getStartServiceTime().isBefore(customer.getMinStartTime()) || customer.getStartServiceTime().isAfter(customer.getMaxEndTime()))
//                .penalize(HardSoftScore.ONE_SOFT, customer -> 1000)
//                .asConstraint("customerServiceTimeWindow");
//    }


    public Constraint vehicleCapacity(ConstraintFactory factory){
        return factory
                .forEach(Vehicle.class)
                .filter(vehicle -> vehicle.getCapacity() < vehicle.getTotalDemand())
                .penalize(HardSoftScore.ONE_HARD,
                        vehicle -> vehicle.getTotalDemand() - vehicle.getCapacity())
                .asConstraint("vehicleCapacity");
    }
    public Constraint visitShouldHaveVehicle(ConstraintFactory factory){
        return factory
                .forEachIncludingUnassigned(Customer.class)
                .filter(visit -> visit.getVehicle()==null)
                .penalize(HardSoftScore.ofSoft(10))
                .asConstraint("visitShouldHaveVehicle");
    }

   public Constraint serviceFinishedAfterMaxEndTime(ConstraintFactory factory){
        return factory
                .forEach(Customer.class)
                .filter(Customer::isServiceFinishedAfterMaxEndTime)
                .penalize(HardSoftScore.ofSoft(10))
                .asConstraint("serviceFinishedAfterMaxEndTime");
   }

}
