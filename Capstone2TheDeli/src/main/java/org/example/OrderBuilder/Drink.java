package org.example.OrderBuilder;

import org.example.Interface.Price;

public class Drink implements Price {
    private final String size; // "Small", "Medium", "Large"
    private  String flavor;
    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor=flavor;
    }

    @Override
    public double getPrice() {
        return switch (size) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }
    @Override
    public String toString() {
        return size + " " + flavor + " ($" + String.format("%.2f", getPrice()) + ")";
    }

}
