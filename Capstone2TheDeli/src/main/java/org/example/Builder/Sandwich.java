package org.example.Builder;

import org.example.enums.SandwichLength;

import java.util.List;

public class Sandwich {
    private SandwichLength length;
    private BreadTopping bread;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(SandwichLength length, BreadTopping bread, List<Topping> toppings, boolean toasted) {
        this.length = length;
        this.bread = bread;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    public SandwichLength getLength() {
        return length;
    }

    public void setLength(SandwichLength length) {
        this.length = length;
    }

    public BreadTopping getBread() {
        return bread;
    }

    public void setBread(BreadTopping bread) {
        this.bread = bread;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(length).append(" sandwich on ").append(bread.getName()).append(" with:\n");
        for (Topping topping : toppings) {
            sb.append(" - ").append(topping.getName()).append("\n");
        }
        if (toasted) {
            sb.append(" - Toasted\n");
        }
        sb.append("Total: $").append(String.format("%.2f", getTotalPrice()));
        return sb.toString();
    }

    public double getTotalPrice() {
        double total = length.getBasePrice();
        int inches = length.getLengthInInches();
        for (Topping topping : toppings) {
            total += topping.getPrice(inches);
        }
        return total;
    }
}
