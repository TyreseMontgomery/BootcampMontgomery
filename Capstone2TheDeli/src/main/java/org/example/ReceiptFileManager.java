package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {
    private static final String RECEIPT_FOLDER = "src/main/resources/ReceiptFolder";
    private static final String MASTER_FILE = RECEIPT_FOLDER + "/AllReceipts.csv";

    public static void saveTransaction(Order order) {
        ensureDirectoryExists();

        String timestamp = getCurrentTimestamp();
        String receiptFileName = RECEIPT_FOLDER + "/Receipt_" + timestamp + ".txt";

        // Write receipt TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFileName))) {
            writer.write("_-_-_-ORDER RECEIPT-_-_-_\n");
            writer.write("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("_-_-_-_-_-_-_-_-_-_-_-_-_\n");
            writer.write(order.toString());
            writer.write("-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
            writer.write(String.format("TOTAL: $%.2f\n", order.getTotalPrice()));
            writer.write("Thank you for your order!\n");
            System.out.println("Receipt saved to: " + receiptFileName);
        } catch (IOException e) {
            System.err.println("Error writing receipt: " + e.getMessage());
        }

        // Append to master CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MASTER_FILE, true))) {
            String sandwiches = String.join(" | ", order.getSandwiches().toString().split("\n")).replace(",", " ");
            String drinks = String.join(" | ", order.getDrinks().toString().split("\n")).replace(",", " ");
            String chips = String.join(" | ", order.getChipsList().toString().split("\n")).replace(",", " ");

            String csvLine = String.format("%s | %s | %s | %s | %.2f",
                    timestamp, sandwiches, drinks, chips, order.getTotalPrice());

            writer.write(csvLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error appending to master CSV: " + e.getMessage());
        }
    }

    private static void ensureDirectoryExists() {
        File folder = new File(RECEIPT_FOLDER);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Created receipt directory: " + RECEIPT_FOLDER);
            } else {
                System.err.println("Failed to create receipt directory.");
            }
        }
    }

    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }
}

