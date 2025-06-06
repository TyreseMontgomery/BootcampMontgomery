package com.ledger;

import org.example.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Ledger {
    private static List<Transaction> transactions = new ArrayList<>();

    // Add a transaction to the ledger
    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Get all transactions, sorted by date and time (newest first)
    public static List<Transaction> getAllTransactions() {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Filter transactions to show only deposits (positive amounts)
    public static List<Transaction> getDeposits() {
        return transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Filter transactions to show only payments (negative amounts)
    public static List<Transaction> getPayments() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Generate Month-to-Date report
    public static List<Transaction> getMonthToDateReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstOfMonth) && !t.getDate().isAfter(today))
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Generate Previous Month report
    public static List<Transaction> getPreviousMonthReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfPrevMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfPrevMonth = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstOfPrevMonth) && !t.getDate().isAfter(lastOfPrevMonth))
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Generate Year-to-Date report
    public static List<Transaction> getYearToDateReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfYear = today.withDayOfYear(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstOfYear) && !t.getDate().isAfter(today))
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Generate Previous Year report
    public static List<Transaction> getPreviousYearReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfPrevYear = today.minusYears(1).withDayOfYear(1);
        LocalDate lastOfPrevYear = today.minusYears(1).withDayOfYear(today.minusYears(1).lengthOfYear());
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstOfPrevYear) && !t.getDate().isAfter(lastOfPrevYear))
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    // Generate report by vendor
    public static List<Transaction> getTransactionsByVendor(String vendor) {
        return transactions.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getTime).reversed())
                .collect(Collectors.toList());
    }

    }

