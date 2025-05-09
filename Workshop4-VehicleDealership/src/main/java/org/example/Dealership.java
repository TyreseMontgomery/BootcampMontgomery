package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    String name;
    String address;
    String phone;
    ArrayList<Vehicle> inventory;
//TODO Fill out Custom Getters for the lists
    List <Vehicle> vehicles = new ArrayList<>();
    public Dealership(String name, String address, String phone, ArrayList<Vehicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = inventory;
        this.vehicles=inventory;

    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public  List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> pricelist = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
           double price = vehicle.getPrice();
           if (price >= min && price <= max) {
               pricelist.add(vehicle);
           }}
        return pricelist;
    }


    public List<Vehicle> getVehiclesByMakeAndModel(String make, String model) {
        List<Vehicle> makeAndModelList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                makeAndModelList.add(vehicle);
            }
        }
        return makeAndModelList;
    }



    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> vehicleByYearList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            int year = vehicle.getYear();
            if (year >= min && year <= max) {
                vehicleByYearList.add(vehicle);
            }
        }
        return vehicleByYearList;
    }


    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicleByColorList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehicleByColorList.add(vehicle);
            }
        }
        return vehicleByColorList;
    }
    public List<Vehicle> getVehiclesByMileage(double min, double max) {
        List<Vehicle> vehicleByMilageList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            double mileage = vehicle.getOdometer();
            if (mileage >= min && mileage <= max) {
                vehicleByMilageList.add(vehicle);
            }
        }
        return vehicleByMilageList;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehicleListByType = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType().equals(vehicleType)) {
                vehicleListByType.add(vehicle);
            }
        }
        return vehicleListByType;
    }
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }
    public void addVehicle(Vehicle vehicleToAdd) {
        if (vehicleToAdd != null) {
            vehicles.add(vehicleToAdd);
        } else {
            System.out.println("Please Enter A Proper Vehicle");
        }
    }
    public void removeVehicle(Vehicle vehicleToRemove) {
        vehicles.remove(vehicleToRemove);
    }
}

