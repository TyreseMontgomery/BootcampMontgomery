package org.example.DAO;

import org.example.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private final String connectionString;
    private final String dbUserName;
    private final String dbPassword;

    public VehicleDAO(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.dbUserName = userName;
        this.dbPassword = password;
    }
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int vin = rs.getInt("vin");
                int year = rs.getInt("year");
                String name = rs.getString("name");  // assuming name = make + model
                String vehicleType= rs.getString("type");
                String color = rs.getString("color");
                int odometer = rs.getInt("odometer");
                double price = rs.getDouble("price");
                boolean isSold = rs.getBoolean("sold");

                Vehicle vehicle = new Vehicle(vin,year,name,name,vehicleType,isSold,color,odometer,price);
                vehicle.setSold(isSold);  // if your Vehicle class has this method
                vehicles.add(vehicle);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> getByPrice(double min, double max) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setVin(rs.getInt("vin"));
                    newVehicle.setMake(rs.getString("name"));
                    newVehicle.setVehicleType(rs.getString("type"));
                    newVehicle.setPrice(rs.getDouble("price"));
                    newVehicle.setYear(rs.getInt("year"));
                    newVehicle.setColor(rs.getString("color"));
                    newVehicle.setOdometer(rs.getInt("odometer"));
                    list.add(newVehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Vehicle> getByMakeOrModel(String make, String model) {
        List<Vehicle> list = new ArrayList<>();
        //TODO LEARNED DYNAMIC QUERYS TO MAKE THE SEARCH CHANCE DEPENDING ON INPUT FOR ACCURACY! SUPER COOL STUFF 🔥😮

        String query = "SELECT * FROM vehicles WHERE 1=1";

        List<String> conditions = new ArrayList<>();
        List<String> values = new ArrayList<>();

        if (make != null && !make.isBlank()) {
            conditions.add("name LIKE ?");
            values.add("%" + make + "%");
        }
        if (model != null && !model.isBlank()) {
            conditions.add("name LIKE ?");
            values.add("%" + model + "%");
        }
        // adds conditions to the dynamic query. very cool :)
        if (!conditions.isEmpty()) {
            query += " AND (" + String.join(" OR ", conditions) + ")";
        }
        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < values.size(); i++) {
                stmt.setString(i + 1, values.get(i));
                // if there is values in the for make or model they fill in the parameter index. the coolest 😎
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setVin(rs.getInt("vin"));
                    newVehicle.setMake(rs.getString("name"));
                    newVehicle.setVehicleType(rs.getString("type"));
                    newVehicle.setPrice(rs.getDouble("price"));
                    newVehicle.setYear(rs.getInt("year"));
                    newVehicle.setColor(rs.getString("color"));
                    newVehicle.setOdometer(rs.getInt("odometer"));
                    list.add(newVehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Vehicle> getByColor(String color) {
        List<Vehicle> list = new ArrayList<>();
        if (color == null || color.isBlank()) {
            return list;
        }

        String query = "SELECT * FROM vehicles WHERE color LIKE ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + color + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setVin(rs.getInt("vin"));
                    newVehicle.setMake(rs.getString("name"));
                    newVehicle.setVehicleType(rs.getString("type"));
                    newVehicle.setPrice(rs.getDouble("price"));
                    newVehicle.setYear(rs.getInt("year"));
                    newVehicle.setColor(rs.getString("color"));
                    newVehicle.setOdometer(rs.getInt("odometer"));
                    list.add(newVehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Vehicle> getByMileageRange(int minMiles, int maxMiles) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, minMiles);
            stmt.setInt(2, maxMiles);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setVin(rs.getInt("vin"));
                    newVehicle.setMake(rs.getString("name"));
                    newVehicle.setVehicleType(rs.getString("type"));
                    newVehicle.setPrice(rs.getDouble("price"));
                    newVehicle.setYear(rs.getInt("year"));
                    newVehicle.setColor(rs.getString("color"));
                    newVehicle.setOdometer(rs.getInt("odometer"));
                    list.add(newVehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Vehicle> getByType(String type) {
        List<Vehicle> list = new ArrayList<>();

        if (type == null || type.isBlank()) {
            return list;
        }

        String query = "SELECT * FROM vehicles WHERE type LIKE ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + type + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setVin(rs.getInt("vin"));
                    newVehicle.setMake(rs.getString("name"));
                    newVehicle.setVehicleType(rs.getString("type"));
                    newVehicle.setPrice(rs.getDouble("price"));
                    newVehicle.setYear(rs.getInt("year"));
                    newVehicle.setColor(rs.getString("color"));
                    newVehicle.setOdometer(rs.getInt("odometer"));
                    list.add(newVehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Vehicle> getByYearRange(int minYear, int maxYear) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setVin(rs.getInt("vin"));
                    newVehicle.setMake(rs.getString("name"));
                    newVehicle.setVehicleType(rs.getString("type"));
                    newVehicle.setPrice(rs.getDouble("price"));
                    newVehicle.setYear(rs.getInt("year"));
                    newVehicle.setColor(rs.getString("color"));
                    newVehicle.setOdometer(rs.getInt("odometer"));
                    list.add(newVehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, name, type, sold, price, color, odometer, year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getVehicleType());
            stmt.setBoolean(4, vehicle.isSold());
            stmt.setDouble(5, vehicle.getPrice());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setInt(8, vehicle.getYear());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean removeVehicleByVin(int vin) {
        String query = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vin);
            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Vehicle getVehicleByVin(int vin) {
        String query = "SELECT * FROM vehicles WHERE vin = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vin);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int year = rs.getInt("year");
                    String name = rs.getString("name"); // assuming this is make + model
                    String vehicletype = rs.getString("type");
                    boolean isSold= rs.getBoolean("sold");
                    double price = rs.getDouble("price");
                    String color = rs.getString("color");
                    int odometer = rs.getInt("odometer");

                    // If you're storing make+model in 'name', you might split it here
                    String make = name;
                    String model = ""; // Optional: extract model if stored separately

                    Vehicle vehicle = new Vehicle (vin, year,make,model,vehicletype, isSold ,color,odometer,price);
                    vehicle.setSold(isSold);
                    return vehicle;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
