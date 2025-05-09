package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    public static List<Vehicle> getDealership() {
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split("\\|");

                int vin = Integer.parseInt(row[0]);
                int year = Integer.parseInt(row[1]);
                String make = row[2];
                String model = row[3];
                String vehicleType = row[4];
                String color = row[5];
                int odometer = Integer.parseInt(row[6]);
                double price = Double.parseDouble(row[7]);


                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                vehicles.add(vehicle);
            }
        } catch (IOException ex) {
            System.out.println(" Failed to read the transactions CSV file.");
            ex.printStackTrace();

        }
        return vehicles;
    }

    public static void saveDealership(Vehicle vehicle) {
        String filePath = "src/main/resources/inventory.csv";
        File file = new File(filePath);

        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (isEmpty) {

                    writer.write("VIN|Year|Make|Model|Type|Color|Odometer|Price");
                    writer.newLine();
                }

                writer.write(String.format(
                        "\n%d|%d|%s|%s|%s|%s|%d|%.2f",
                        vehicle.getVin(), vehicle.getYear(), vehicle.getMake(),
                        vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(),
                        vehicle.getOdometer(), vehicle.getPrice()
                ));
                writer.newLine();
            }
            System.out.println("Vehicle saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save vehicle to inventory file.");
            e.printStackTrace();
        }
    }
}