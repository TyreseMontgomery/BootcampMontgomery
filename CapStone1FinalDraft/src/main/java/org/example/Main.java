package org.example;
//todo run program, articulate methods, show highlights,
// mention comparator utility

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import static org.example.Ledger.getAllTransactions;

class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // 'Final' means it will not change - so I can use it in my methods.
        boolean running = true;
        while (running) {
            showHomeScreen();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addDeposit();
                    break;
                case 2:
                    makePayment();
                    break;
                case 3:
                    viewLedger();
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showHomeScreen() {
        System.out.println("--- Accounting Ledger Application ---");
        System.out.println("1) Add Deposit");
        System.out.println("2) Make Payment");
        System.out.println("3) View Ledger");
        System.out.println("4) View Reports");
        System.out.println("5) Exit");
        System.out.print("Please select an option: ");
    }

    private static void addDeposit() {
        //todo debug why it is not letting us enter a description
        if (scanner.hasNextLine()) scanner.nextLine();
        System.out.print("Enter deposit description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount (positive number): ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Amount must be positive for a deposit.");
            return;
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();


        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        TransactionFileManager.saveTransaction(deposit);
        System.out.println("Deposit added successfully.");
    }

    private static void makePayment() {
        if (scanner.hasNextLine()) scanner.nextLine();
        System.out.print("Enter payment description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount (positive number): ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Amount must be positive for a payment.");
            return;
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // Create and save the payment transaction (negative amount)
        Transaction payment = new Transaction(date, time, description, vendor, -amount);
        TransactionFileManager.saveTransaction(payment);

        System.out.println("Payment added successfully.");
    }

    private static void viewLedger() {
        boolean viewing = true;
        while (viewing) {
            System.out.println("--- Ledger ---");
            System.out.println("1) View All Transactions");
            System.out.println("2) View Deposits");
            System.out.println("3) View Payments");
            System.out.println("4) Go Back to Home");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayTransactions(getAllTransactions());
                    break;
                case 2:
                    displayTransactions(Ledger.getDeposits());
                    break;
                case 3:
                    displayTransactions(Ledger.getPayments());
                    break;
                case 4:
                    viewing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            transactions.forEach(System.out::println);
        }
    }

    private static void viewReports() {
        boolean viewingReports = true;
        while (viewingReports) {
            System.out.println("--- Reports ---");
            System.out.println("1) Month-To-Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year-To-Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Home");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    displayTransactions(Ledger.getMonthToDateReport());
                    break;
                case 2:
                    displayTransactions(Ledger.getPreviousMonthReport());
                    break;
                case 3:
                    displayTransactions(Ledger.getYearToDateReport());
                    break;
                case 4:
                    displayTransactions(Ledger.getPreviousYearReport());
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 0:
                    viewingReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    public static void searchByVendor () {
        Scanner scanner = new Scanner(System.in);

        getAllTransactions();
        System.out.print("Enter vendor name to search: ");
        String vendor = scanner.nextLine();

        List<Transaction> results = Ledger.getTransactionsByVendor(vendor);

        if (results.isEmpty()) {
            System.out.println("No transactions found for vendor: " + vendor);
        } else {
            displayTransactions(results);
        }
    }
}
