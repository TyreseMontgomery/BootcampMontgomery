import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load existing transactions from the CSV file
        List<Transaction> transactions = TransactionFileManager.loadTransactions();
        transactions.forEach(com.ledger.Ledger::addTransaction);  // Add loaded transactions to the ledger

        boolean running = true;
        while (running) {
            showHomeScreen();
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    viewLedger();
                    break;
                case "R":
                    viewReports();
                    break;
                case "X":
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

        // Create and save the deposit transaction
        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        Ledger.addTransaction(deposit);
        TransactionFileManager.saveTransaction(deposit);

        System.out.println("Deposit added successfully.");
    }

    private static void makePayment() {
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
        Ledger.addTransaction(payment);
        TransactionFileManager.saveTransaction(payment);

        System.out.println("Payment added successfully.");
    }

    private static void viewLedger() {
        boolean viewing = true;
        while (viewing) {
            System.out.println("--- Ledger ---");
            System.out.println("A) View All Transactions");
            System.out.println("D) View Deposits");
            System.out.println("P) View Payments");
            System.out.println("H) Go Back to Home");
            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayTransactions(Ledger.getAllTransactions());
                    break;
                case "D":
                    displayTransactions(Ledger.getDeposits());
                    break;
                case "P":
                    displayTransactions(Ledger.getPayments());
                    break;
                case "H":
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
            transactions.forEach(transaction -> System.out.println(transaction));
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
            System.out.println("6) Custom Search");
            System.out.println("0) Back to Home");
            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    displayTransactions(Ledger.getMonthToDateReport());
                    break;
                case "2":
                    displayTransactions(Ledger.getPreviousMonthReport());
                    break;
                case "3":
                    displayTransactions(Ledger.getYearToDateReport());
                    break;
                case "4":
                    displayTransactions(Ledger.getPreviousYearReport());
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "6":
                    customSearch();
                    break;
                case "0":
                    viewingReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void searchByVendor() {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        List<Transaction> transactionsByVendor = Ledger.getTransactionsByVendor(vendor);
        displayTransactions(transactionsByVendor);
    }

    private static void customSearch() {
        System.out.print("Enter start date (yyyy-mm-dd) or leave blank for no filter: ");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);

        System.out.print("Enter end date (yyyy-mm-dd) or leave blank for no filter: ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);

        System.out.print("Enter description or leave blank for no filter: ");
        String description = scanner.nextLine();
        if (description.isEmpty()) description = null;

        System.out.print("Enter vendor or leave blank for no filter: ");
        String vendor = scanner.nextLine();
        if (vendor.isEmpty()) vendor = null;

        System.out.print("Enter amount or leave blank for no filter: ");
        String amountInput = scanner.nextLine();
        Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

        List<Transaction> customResults = Ledger.customSearch(startDate, endDate, description, vendor, amount);
        displayTransactions(customResults);
    }
}
