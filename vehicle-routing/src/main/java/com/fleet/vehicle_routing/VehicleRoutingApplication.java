package com.fleet.vehicle_routing;

import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import com.fleet.vehicle_routing.domain.Location;
import com.fleet.vehicle_routing.domain.Vehicle;
import com.fleet.vehicle_routing.domain.VehicleSolution;
import com.fleet.vehicle_routing.domain.Customer;
import com.fleet.vehicle_routing.solver.VehicleRoutingConstraintProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class VehicleRoutingApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleRoutingApplication.class, args);

		System.out.println(LocalDateTime.now());
		SolverFactory<VehicleSolution> solverFactory = SolverFactory.create(new SolverConfig()
				.withSolutionClass(VehicleSolution.class)
				.withEntityClasses(Vehicle.class, Customer.class)
				.withConstraintProviderClass(VehicleRoutingConstraintProvider.class)
				.withTerminationSpentLimit(Duration.ofSeconds(10))); // Time limit increased

		VehicleSolution problem = loadData();
		VehicleSolution solution = solverFactory.buildSolver().solve(problem);

		System.out.println("Optimized Solution:");
		printSolution(solution);
	}

	public static VehicleSolution loadData() {

		List<Location> locations = List.of(
				new Location(1, 28.613939, 77.209023),
				new Location(2, 19.076090, 72.877426),
				new Location(3, 13.082680, 80.270718),
				new Location(4, 22.572645, 88.363892),
				new Location(5, 25.594095, 85.137566),
				new Location(6, 23.810300, 90.412500),
				new Location(7, 12.971599, 77.594566),
				new Location(8, 19.182157, 84.791682),
				new Location(9, 31.549700, 74.343600),
				new Location(10, 22.719568, 75.857727),
				new Location(11, 26.846700, 80.946200),
				new Location(12, 28.704060, 77.102493),
				new Location(13, 21.170200, 72.831100),
				new Location(14, 15.317300, 75.713900),
				new Location(15, 10.850500, 76.271100),
				new Location(16, 18.520430, 73.856744),
				new Location(17, 34.083671, 74.797282),
				new Location(18, 9.931233, 76.267304),
				new Location(19, 27.176670, 78.008075),
				new Location(20, 25.317645, 82.973914)
		);

		locations.forEach(location -> location.calculateDistances(locations));

		List<Vehicle> vehicles = List.of(
				new Vehicle(1, 90, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0)),
				new Vehicle(2, 100, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0)),
				new Vehicle(3, 300, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0)),
				new Vehicle(4, 150, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0)),
				new Vehicle(5, 200, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0))
		);


		List<Customer> visits = List.of(
				new Customer(1, "Gautam", locations.get(1), 100, LocalDateTime.of(2023, 12, 11, 9, 0), LocalDateTime.of(2023, 12, 11, 12, 0), Duration.ofMinutes(30)),
				new Customer(2, "Aman", locations.get(2), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
				new Customer(3, "Priya", locations.get(3), 120, LocalDateTime.of(2023, 12, 11, 11, 0), LocalDateTime.of(2023, 12, 11, 14, 0), Duration.ofMinutes(40)),
				new Customer(4, "Rahul", locations.get(4), 150, LocalDateTime.of(2023, 12, 11, 12, 0), LocalDateTime.of(2023, 12, 11, 15, 0), Duration.ofMinutes(25)),
				new Customer(5, "Ananya", locations.get(5), 80, LocalDateTime.of(2023, 12, 11, 13, 0), LocalDateTime.of(2023, 12, 11, 16, 0), Duration.ofMinutes(35)),
				new Customer(6, "Vikas", locations.get(6), 60, LocalDateTime.of(2023, 12, 11, 9, 30), LocalDateTime.of(2023, 12, 11, 12, 30), Duration.ofMinutes(30)),
				new Customer(7, "Megha", locations.get(7), 100, LocalDateTime.of(2023, 12, 11, 10, 30), LocalDateTime.of(2023, 12, 11, 13, 30), Duration.ofMinutes(20)),
				new Customer(8, "Kiran", locations.get(8), 110, LocalDateTime.of(2023, 12, 11, 11, 30), LocalDateTime.of(2023, 12, 11, 14, 30), Duration.ofMinutes(30)),
				new Customer(9, "Ravi", locations.get(9), 70, LocalDateTime.of(2023, 12, 11, 12, 30), LocalDateTime.of(2023, 12, 11, 15, 30), Duration.ofMinutes(25)),
				new Customer(10, "Neha", locations.get(10), 95, LocalDateTime.of(2023, 12, 11, 13, 30), LocalDateTime.of(2023, 12, 11, 16, 30), Duration.ofMinutes(40)),
				new Customer(11, "Sanjay", locations.get(11), 130, LocalDateTime.of(2023, 12, 11, 9, 45), LocalDateTime.of(2023, 12, 11, 12, 45), Duration.ofMinutes(35)),
				new Customer(12, "Pooja", locations.get(12), 140, LocalDateTime.of(2023, 12, 11, 10, 45), LocalDateTime.of(2023, 12, 11, 13, 45), Duration.ofMinutes(30))
		);

		return new VehicleSolution(1, visits, vehicles);
	}

	private static void printSolution(VehicleSolution solution) {
		if (solution == null) {
			System.out.println("No solution found.");
			return;
		}

		for (Vehicle vehicle : solution.getVehicles()) {
			System.out.println("Vehicle " + vehicle.getId() + " Route: ");
			if (vehicle.getRoute() == null || vehicle.getRoute().isEmpty()) {
				System.out.println("No route assigned to this vehicle.");
			} else {
				vehicle.getRoute().forEach(location ->
						System.out.println("Location ID: " + location.getId() + ", Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude())
				);
			}

			System.out.println("Vehicle Capacity: " + vehicle.getCapacity());
			System.out.println("Total Demand: " + vehicle.getTotalDemand());
			System.out.println("Total Distance: " + vehicle.getTotalDistanceMeters() + " meters\n");
		}

		System.out.println("Score: " + solution.getHardSoftScore());
	}
}
