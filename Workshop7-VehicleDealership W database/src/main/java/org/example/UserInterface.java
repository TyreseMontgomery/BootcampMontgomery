package org.example;

import org.example.DAO.LeaseContractDao;
import org.example.DAO.SalesContractDao;
import org.example.DAO.VehicleDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final SalesContractDao salesContractDao;
    private final LeaseContractDao leaseContractDao;
    private final VehicleDAO vehicleDAO;
    private List<Vehicle> vehicles = new ArrayList<>();
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);
    private final String connectionString;
    private final String dbUserName;
    private final String dbPassword;


    public UserInterface(String connectionString, String dbUserName, String dbPassword) {
        this.connectionString = connectionString;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        this.salesContractDao = new SalesContractDao(connectionString,dbUserName, dbPassword);
        this.leaseContractDao = new LeaseContractDao(connectionString,dbUserName, dbPassword);
        this.vehicleDAO = new VehicleDAO(connectionString,dbUserName, dbPassword);
    }


    public void display() {


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
                case "10":
                    processSellVehicleRequest();
                    break;
                case "11":
                    processSalesContractRequest();
                case "12":
                    processLeaseContractRequest();
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
        System.out.println("10. Sell a Vehicle");
        System.out.println("11. Create Sales Contract");
        System.out.println("12. Create Lease Contract");
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
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);

        List<Vehicle> allVehicles = vehicleDAO.getAllVehicles();
        displayVehicles(allVehicles);
    }

    private void processVehiclesByPriceRequest() {

        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
        System.out.print("Enter minimum price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum price: ");
        double max = Double.parseDouble(scanner.nextLine());


        for (Vehicle vehicle : vehicleDAO.getByPrice(min, max)) {
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

    private void processVehiclesByMakeModelRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
        System.out.println("(enter nothing for just make search)");
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.println("(enter nothing for just make search)");
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        for (Vehicle vehicle : vehicleDAO.getByMakeOrModel(make, model)) {
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

    private void processVehiclesByYearRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
        System.out.print("Enter minimum year: ");
        int minYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());
        for (Vehicle vehicle : vehicleDAO.getByYearRange(minYear, maxYear)) {
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

    private void processVehiclesByColorRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        for (Vehicle vehicle : vehicleDAO.getByColor(color)) {
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

    private void processVehiclesByMileageRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
        System.out.print("Enter minimum mileage: ");
        int minMiles = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum mileage: ");
        int maxMiles = Integer.parseInt(scanner.nextLine());
        for (Vehicle vehicle : vehicleDAO.getByMileageRange(minMiles, maxMiles)) {
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

    private void processVehiclesByTypeRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
        System.out.print("Enter vehicle type (e.g., SUV, Truck): ");
        String type = scanner.nextLine();
        for (Vehicle vehicle : vehicleDAO.getByType(type)) {
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

    private void processAddVehicleRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);
            try {
                System.out.print("Enter VIN: ");
                int vin = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Year: ");
                int year = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Make and Model (e.g., Toyota Camry): ");
                String nameInput = scanner.nextLine();
                String[] parts = nameInput.trim().split(" ", 2);
                String make = parts[0];
                String model = parts.length > 1 ? parts[1] : "";
                // ternary if else conditions   ^

                System.out.print("Enter Vehicle Type: ");
                String type = scanner.nextLine();
                System.out.print("Enter Color: ");
                String color = scanner.nextLine();
                System.out.print("Enter Odometer: ");
                int odometer = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Price: ");
                double price = Double.parseDouble(scanner.nextLine());

                boolean isSold = false;

                Vehicle newVehicle = new Vehicle(vin, year, make, model, type, isSold, color, odometer, price);

                boolean added = vehicleDAO.addVehicle(newVehicle);
                if (added) {
                    System.out.println("Vehicle added successfully to database!");
                } else {
                    System.out.println("Failed to add vehicle to database.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Vehicle not added.");
            }
        }

    private void processRemoveVehicleRequest() {
        VehicleDAO vehicleDAO = new VehicleDAO(connectionString, dbUserName, dbPassword);

        try {
            System.out.print("Enter VIN of vehicle to remove: ");
            int vin = Integer.parseInt(scanner.nextLine());

            boolean removed = vehicleDAO.removeVehicleByVin(vin);
            if (removed) {
                System.out.println("Vehicle removed successfully.");
            } else {
                System.out.println("No vehicle found with that VIN.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN format.");
        }
    }

    private void processSellVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle to sell: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle v = dealership.getVehicleByVin(vin);
        if (v == null) {
            System.out.println("Vehicle not found with VIN: " + vin);
            return;
        }

        System.out.print("Enter buyer's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter buyer's email: ");
        String email = scanner.nextLine();
        System.out.print("Enter contract type (sales/lease): ");
        String contractType = scanner.nextLine();

        Contract contract;

        if ("sales".equalsIgnoreCase(contractType)) {
            System.out.print("Is the vehicle financed? (true/false): ");
            boolean isFinanced = Boolean.parseBoolean(scanner.nextLine());

            double salesTax = v.getPrice() * 0.05;
            int recordingFee = 100;
            double processingFee = v.getPrice() < 10000 ? 295 : 495;
            double totalPrice = v.getPrice() + salesTax + recordingFee + processingFee;
            double monthlyPayment = isFinanced ? totalPrice / 12.0 : 0;

            contract = new SalesContract("2025-05-18", name, email, v, totalPrice, monthlyPayment, salesTax, recordingFee, processingFee, isFinanced, monthlyPayment);
        } else {
            double monthlyPayment = v.getPrice() / 36.0;
            contract = new LeaseContract("2025-05-18", name, email, v, v.getPrice(), monthlyPayment);
        }

        dealership.removeVehicle(v);
        ContractFileManager.saveContract(contract);
        System.out.println("Vehicle sold and contract saved.");
    }

    private void processSalesContractRequest() {

        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine());
            Vehicle vehicle = vehicleDAO.getVehicleByVin(vin);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Customer Email: ");
            String email = scanner.nextLine();

            System.out.print("Is the customer financing? (yes/no): ");
            boolean isFinanced = scanner.nextLine().trim().equalsIgnoreCase("yes");

            String date = LocalDate.now().toString();

            SalesContract contract = new SalesContract(
                    date, name, email, vehicle,
                    0, 0,
                    0, 0, 0, isFinanced, 0
            );

            if (salesContractDao.addSalesContract(contract)) {
                System.out.println("Sales contract saved successfully!");
            } else {
                System.out.println("Failed to save sales contract.");
            }

        } catch (Exception e) {
            System.out.println("Error creating sales contract.");
            e.printStackTrace();
        }


    }

    private void processLeaseContractRequest() {
        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine());
            Vehicle vehicle = vehicleDAO.getVehicleByVin(vin);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Customer Email: ");
            String email = scanner.nextLine();

            String date = LocalDate.now().toString();

            LeaseContract contract = new LeaseContract(
                    date, name, email, vehicle,
                    0, 0
            );

            if (leaseContractDao.addLeaseContract(contract)) {
                System.out.println("Lease contract saved successfully!");
            } else {
                System.out.println("Failed to save lease contract.");
            }

        } catch (Exception e) {
            System.out.println("Error creating lease contract.");
            e.printStackTrace();
        }
    }
}
//TODO VERFIY ALL PHASES WORK AND ARE DECENTLY DEFECTIVELY CODED.