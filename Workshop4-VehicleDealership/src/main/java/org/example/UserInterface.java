package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private List<Vehicle> vehicles = new ArrayList<>();
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        init();

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    processAllVehiclesRequest();
                    break;
                case "2":
                    processVehiclesByPriceRequest();
                    break;
                case "3":
                    processVehiclesByMakeModelRequest();
                    break;
                case "4":
                    processVehiclesByYearRequest();
                    break;
                case "5":
                    processVehiclesByColorRequest();
                    break;
                case "6":
                    processVehiclesByMileageRequest();
                    break;
                case "7":
                    processVehiclesByTypeRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void init() {
        List<Vehicle> loadedVehicles = DealershipFileManager.getDealership();
        this.dealership = new Dealership("Ty's Cars", "111 N Adams Rd", "509-206-7044", (ArrayList<Vehicle>) loadedVehicles);
    }

    private void displayMenu() {
        System.out.println("\n========== Vehicle Dealership Menu ==========");
        System.out.println("1. View All Vehicles");
        System.out.println("2. Find Vehicles by Price");
        System.out.println("3. Find Vehicles by Make and Model");
        System.out.println("4. Find Vehicles by Year Range");
        System.out.println("5. Find Vehicles by Color");
        System.out.println("6. Find Vehicles by Mileage");
        System.out.println("7. Find Vehicles by Type");
        System.out.println("8. Add a Vehicle");
        System.out.println("9. Remove a Vehicle");
        System.out.println("0. Exit");
        System.out.println("=============================================");
    }

    private void displayVehicles(List<Vehicle> vehiclesToDisplay) {
        if (vehiclesToDisplay.isEmpty()) {
            System.out.println("No vehicles found matching your criteria.");
            return;
        }

        for (Vehicle vehicle : vehiclesToDisplay) {
            System.out.printf("VIN: %d | Year: %d | Make: %s | Model: %s | Type: %s | Color: %s | Odometer: %d | Price: $%.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }

    private void processAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    private void processVehiclesByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum price: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        displayVehicles(results);
    }

    private void processVehiclesByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByMakeAndModel(make, model);
        displayVehicles(results);
    }

    private void processVehiclesByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum year: ");
        int max = Integer.parseInt(scanner.nextLine());

        List<Vehicle> results = dealership.getVehiclesByYear(min, max);
        displayVehicles(results);
    }

    private void processVehiclesByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByColor(color);
        displayVehicles(results);
    }

    private void processVehiclesByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum mileage: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        displayVehicles(results);
    }

    private void processVehiclesByTypeRequest() {
        System.out.print("Enter vehicle type (e.g., SUV, Truck): ");
        String type = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByType(type);
        displayVehicles(results);
    }

    private void processAddVehicleRequest() {
       // wrapped in a try catch to prevent it from writing in the wrong format. same for the Parcing.
        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Make: ");
            String make = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            System.out.print("Enter Vehicle Type: ");
            String type = scanner.nextLine();
            System.out.print("Enter Color: ");
            String color = scanner.nextLine();
            System.out.print("Enter Odometer: ");
            int odometer = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(newVehicle);
            DealershipFileManager.saveDealership(newVehicle);
            System.out.println("Vehicle added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Vehicle not added.");
        }
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle to remove: ");
        try {
            int vin = Integer.parseInt(scanner.nextLine());
            Vehicle toRemove = null;

            for (Vehicle v : dealership.getAllVehicles()) {
                if (v.getVin() == vin) {
                    toRemove = v;
                    break;
                }
            }

            if (toRemove != null) {
                dealership.removeVehicle(toRemove);
                System.out.println("Vehicle removed from inventory. (Note: File not updated.)");
            } else {
                System.out.println("No vehicle with that VIN found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN entered.");
        }
    }
}
