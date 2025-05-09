package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
public static List<Vehicle> getDealership(){
    List<Vehicle> vehicles = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"))){
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


            Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
            vehicles.add(vehicle);
        }
    } catch (IOException ex) {
        System.out.println(" Failed to read the transactions CSV file.");
        ex.printStackTrace();

    }
    return vehicles;
}
    public static void saveDealership(Vehicle vehicle) {
        String filePath = ("src/main/resources/inventory.csv");
        File file = new File(filePath);

        try {


            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;
            FileWriter fileWriter = new FileWriter(filePath,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (isEmpty) {
                bufferedWriter.write("Ty's Car Lot |111 N Adams Rd|509-206-7044\n");
            }
            bufferedWriter.write(vehicle.toString() + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Something went wrong while saving the transaction.");
            e.printStackTrace();
        }
    }
}

