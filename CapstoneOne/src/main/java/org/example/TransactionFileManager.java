package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TransactionFileManager {
    private static final String FILE_NAME = "transactions.csv";

    // Load all transactions from the CSV file
    public static List<com.ledger.Transaction> loadTransactions() {
        List<com.ledger.Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Convert each CSV line into a Transaction object
                transactions.add(com.ledger.Transaction.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
        return transactions;
    }

    // Save a new transaction to the CSV file
    public static void saveTransaction(com.ledger.Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Write the transaction in CSV format
            bw.write(transaction.toCSV());
            bw.newLine(); // Add a newline after each transaction entry
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Overwrite the entire file with the new list of transactions
    public static void saveAllTransactions(List<com.ledger.Transaction> transactions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (com.ledger.Transaction transaction : transactions) {
                // Write each transaction to the file in CSV format
                bw.write(transaction.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failure to write file.");
        }
    }
}
