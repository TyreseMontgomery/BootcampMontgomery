package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Ledger {

    public static List<Transaction> getAllFromFile() {
        return TransactionFileManager.readFile();
    }


    public static void sortByDateAndTimeDescending(List<Transaction> list) {
        list.sort(Comparator.comparing(Transaction::getDate)// compares the list by the date
                .thenComparing(Transaction::getTime)// then compares by time
                .reversed());
        // reverses the order creating a descending list first by date then by time if in the same date.
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = getAllFromFile();
        sortByDateAndTimeDescending(transactions);
        return transactions;
    }

    public static List<Transaction> getDeposits() {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction t : getAllFromFile()) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        sortByDateAndTimeDescending(deposits);
        return deposits;
    }

    public static List<Transaction> getPayments() {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction t : getAllFromFile()) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }
        sortByDateAndTimeDescending(payments);
        return payments;
    }

    public static List<Transaction> getMonthToDateReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        List<Transaction> result = new ArrayList<>();

        for (Transaction t : getAllFromFile()) {
            if (!t.getDate().isBefore(firstOfMonth) && !t.getDate().isAfter(today)) {
                result.add(t);
            }
        }
        sortByDateAndTimeDescending(result);
        return result;
    }

    public static List<Transaction> getPreviousMonthReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfPrevMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfPrevMonth = firstOfPrevMonth.withDayOfMonth(firstOfPrevMonth.lengthOfMonth());
        List<Transaction> result = new ArrayList<>();

        for (Transaction t : getAllFromFile()) {
            if (!t.getDate().isBefore(firstOfPrevMonth) && !t.getDate().isAfter(lastOfPrevMonth)) {
                result.add(t);
            }
        }
        sortByDateAndTimeDescending(result);
        return result;
    }

    public static List<Transaction> getYearToDateReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfYear = today.withDayOfYear(1);
        List<Transaction> result = new ArrayList<>();

        for (Transaction t : getAllFromFile()) {
            if (!t.getDate().isBefore(firstOfYear) && !t.getDate().isAfter(today)) {
                result.add(t);
            }
        }
        sortByDateAndTimeDescending(result);
        return result;
    }

    public static List<Transaction> getPreviousYearReport() {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        LocalDate firstOfLastYear = lastYear.withDayOfYear(1);
        LocalDate endOfLastYear = lastYear.withDayOfYear(lastYear.lengthOfYear());
        List<Transaction> result = new ArrayList<>();

        for (Transaction t : getAllFromFile()) {
            if (!t.getDate().isBefore(firstOfLastYear) && !t.getDate().isAfter(endOfLastYear)) {
                result.add(t);
            }
        }
        sortByDateAndTimeDescending(result);
        return result;
    }

    // useed trim to display without spaces
    // todo add more defencive coding.
    public static List<Transaction> getTransactionsByVendor(String vendorName ) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : getAllFromFile()) {
            if (t.getVendor().contains(vendorName.trim())) {
                result.add(t);
            }
        }
        sortByDateAndTimeDescending(result);
        return result;
    }


   public static void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            // I learned the Lambda expression 💥
            transactions.forEach(System.out::println);
        }
    }
}


