package org.example;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
    public class Transaction {
        private LocalDate transactionDate;
        private LocalTime transactionTime;
        private String description;
        private String vendor;
        private double amount;

        // Constructor that takes date, time, description, vendor, and amount
        public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
            this.transactionDate = date;
            this.transactionTime = time;
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }

        // Constructor to create a transaction from a CSV line
        public Transaction(String dateStr, String timeStr, String description, String vendor, double amount) {
            this.transactionDate = LocalDate.parse(dateStr); // Convert the date string to LocalDate
            this.transactionTime = LocalTime.parse(timeStr); // Convert the time string to LocalTime
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }

        // Getters and Setters
        public LocalDate getDate() {
            return transactionDate;
        }

        public void setDate(LocalDate transactionDate) {
            this.transactionDate = this.transactionDate;
        }

        public LocalTime getTime() {
            return transactionTime;
        }

        public void setTime(LocalTime time) {
            this.transactionTime = time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        // To display the transaction in a readable format

        // Static method to parse a CSV line and return a Transaction object
        public static org.example.Transaction fromCSV(String line) {
            String[] parts = line.split("\\|");
            LocalDate date = LocalDate.parse(parts[0]);
            LocalTime time = LocalTime.parse(parts[1]);
            String description = parts[2];
            String vendor = parts[3];
            double amount = Double.parseDouble(parts[4]);
            return new org.example.Transaction(date, time, description, vendor, amount);
        }

        // Convert the Transaction object to CSV format (for saving to file)
        public String toCSV() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return transactionDate.format(dateFormatter) + "|" + transactionTime.format(timeFormatter) + "|" + description + "|" + vendor + "|" + amount;
        }
        @Override
        public String toString() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return ("%s|%s|%s|%s|%s|").formatted(transactionDate.format(dateFormatter), transactionTime.format(timeFormatter), description, vendor, amount);
        }
    }


