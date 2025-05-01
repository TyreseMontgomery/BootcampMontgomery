package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileManager {
    // TODO Started using T as the Variable in the for loops to make it easier to keep up
    // Reads all transactions from the CSV file
    public static List<Transaction> readFile() {
        List<Transaction> transactionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            // Skip header if present
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(row[0]);
                LocalTime transactionTime = LocalTime.parse(row[1]);
                String description = row[2];
                String vendor = row[3];
                double amount = Double.parseDouble(row[4]);

                Transaction transaction = new Transaction(transactionDate, transactionTime, description, vendor, amount);
                transactionList.add(transaction);
            }
        } catch (IOException ex) {
            System.out.println(" Failed to read the transactions CSV file.");
            ex.printStackTrace();
        }

        return transactionList;
    }

    public static void saveTransaction(Transaction transaction) {
        String filePath = "src/main/resources/transactions.csv";
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
                bufferedWriter.write("date|time|description|vendor|amount\n");
            }
            bufferedWriter.write(transaction.toString() + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Something went wrong while saving the transaction.");
            e.printStackTrace();
        }
    }

    // (Optional) Utility to display all transactions from the file
    public static void displayTransactionsFromFile() {
        List<Transaction> transactions = readFile();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.printf("%s | %s | %s | %s |$%.2f",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }
}
