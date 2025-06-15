package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContractFileManager {
// Used String builder to append.
    private static final String FILE_PATH = "src/main/resources/contracts.csv";

    public static void saveContract(Contract contract) {
        File file = new File(FILE_PATH);

        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (isEmpty) {
                    writer.write("Type|Date|Name|Email|Vehicle|TotalPrice|MonthlyPayment|ExtraFields");
                    writer.newLine();
                }

                StringBuilder line = new StringBuilder();


                String vehicleData = serializeVehicle(contract.getVehicleSold());

                if (contract instanceof SalesContract) {
                    SalesContract sc = (SalesContract) contract;
                    line.append("SalesContract").append("|")
                            .append(sc.getDate()).append("|")
                            .append(sc.getName()).append("|")
                            .append(sc.getEmail()).append("|")
                            .append(vehicleData).append("|")
                            .append(format(sc.getTotalPrice())).append("|")
                            .append(format(sc.getMonthlyPayment())).append("|")
                            .append(format(sc.getSalesTax())).append(",")
                            .append(sc.getRecordingFee()).append(",")
                            .append(format(sc.getProcessingFee())).append(",")
                            .append(sc.getIsFinanced());

                } else if (contract instanceof LeaseContract) {
                    LeaseContract lc = (LeaseContract) contract;
                    line.append("LeaseContract").append("|")
                            .append(lc.getDate()).append("|")
                            .append(lc.getName()).append("|")
                            .append(lc.getEmail()).append("|")
                            .append(vehicleData).append("|")
                            .append(format(lc.getTotalPrice())).append("|")
                            .append(format(lc.getMonthlyPayment())).append("|")
                            .append("N/A");
                }

                writer.write(line.toString());
                writer.newLine();
                System.out.println("Contract saved to file.");
            }

        } catch (IOException e) {
            System.out.println("Failed to save contract to file.");
            e.printStackTrace();
        }
    }

    public static List<Contract> loadContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split("\\|");

                String type = row[0];
                String date = row[1];
                String name = row[2];
                String email = row[3];

                Vehicle vehicleSold = deserializeVehicle(row[4]);
                double totalPrice = Double.parseDouble(row[5]);
                double monthlyPayment = Double.parseDouble(row[6]);

                if ("SalesContract".equals(type)) {
                    String[] extras = row[7].split(",");
                    double salesTax = Double.parseDouble(extras[0]);
                    int recordingFee = Integer.parseInt(extras[1]);
                    double processingFee = Double.parseDouble(extras[2]);
                    boolean isFinanced = Boolean.parseBoolean(extras[3]);

                    Contract sc = new SalesContract(
                            date, name, email, vehicleSold, totalPrice, monthlyPayment,
                            salesTax, recordingFee, processingFee, isFinanced, monthlyPayment
                    );
                    contracts.add(sc);

                } else if ("LeaseContract".equals(type)) {
                    Contract lc = new LeaseContract(date, name, email, vehicleSold, totalPrice, monthlyPayment);
                    contracts.add(lc);
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to load contracts from file.");
            e.printStackTrace();
        }

        return contracts;
    }

    private static String serializeVehicle(Vehicle v) {
        if (v == null) {
            return "null,null,null,null,null,null,null,null";
        }
        return v.getVin() + "," + v.getYear() + "," + v.getMake() + "," +
                v.getModel() + "," + v.getVehicleType() + "," + v.getColor() + "," +
                v.getOdometer() + "," + format(v.getPrice());
    }

    private static Vehicle deserializeVehicle(String data) {
        String[] parts = data.split(",");
        int vin = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        String make = parts[2];
        String model = parts[3];
        String vehicleType = parts[4];
        boolean isSold = Boolean.parseBoolean(parts[5]);
        String color = parts[6];
        int odometer = Integer.parseInt(parts[7]);
        double price = Double.parseDouble(parts[8]);

        return new Vehicle(vin, year, make, model, vehicleType,isSold, color, odometer, price);
    }


    private static String format(double value) {
        return String.format("%.2f", value);
    }
}
