package org.example;

import org.example.Builder.Sandwich;
import org.example.OrderBuilder.Chips;
import org.example.OrderBuilder.Drink;
import org.example.SignatureSandwich.SignatureSandwich;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Sandwich> sandwiches = new ArrayList<>();
    private final List<SignatureSandwich> signatureSandwiches = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private final List<Chips> chipsList = new ArrayList<>();

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<SignatureSandwich> getSignatureSandwiches() {
        return signatureSandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChipsList() {
        return chipsList;
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addSignatureSandwich(SignatureSandwich sandwich) {
        signatureSandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chips) {
       chipsList.add(chips);
    }

    public void printReceipt() {
        System.out.println("\n-_-_-_-_Your Order-_-_-_-_");
        for (Sandwich s : sandwiches) {
            System.out.println(s);
            System.out.println();
        }
        for (SignatureSandwich sig : signatureSandwiches) {
            System.out.println(sig.toString());
            System.out.println();
        }
        for (Drink drink : drinks) {
            System.out.println(drink.toString());
        }
        for (Chips chip : chipsList) {
            System.out.println(chip.toString());

        }
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.printf("Total: $%.2f\n", getTotalPrice());
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");
    }

    public void writeReceiptToFile(String filename) {
        try (FileWriter writer = new FileWriter("")) {
            writer.write("==== Your Order ====\n");

            for (Sandwich s : sandwiches) {
                writer.write(s.toString());
                writer.write("\n\n");
            }
            for (SignatureSandwich sig : signatureSandwiches) {
                writer.write(sig.toString());
                writer.write("\n\n");
            }
            for (Drink drink : drinks) {
                writer.write(drink.toString() + "\n");
            }
            for (Chips chip : chipsList) {
                writer.write(chip.toString() + "\n");
            }
            writer.write("-_-_-_-_-_-_-_-_-_-_-_-_\n");
            writer.write(String.format("Total: $%.2f\n", getTotalPrice()));
            writer.write("-_-_-_-_-_-_-_-_-_-_-_-_\n");
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (Sandwich s : sandwiches) {
            total += s.getTotalPrice();
        }
        for (SignatureSandwich sig : signatureSandwiches) {
            total += sig.getTotalPrice();
        }
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }
        for (Chips chip : chipsList) {
            total += chip.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sandwiches=" + sandwiches.toString() +
                ", signatureSandwiches=" + signatureSandwiches.toString() +
                ", drinks=" + drinks.toString() +
                ", chipsList=" + chipsList.toString() +
                '}';
    }
}


