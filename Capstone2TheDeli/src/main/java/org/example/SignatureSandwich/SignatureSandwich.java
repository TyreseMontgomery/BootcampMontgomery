package org.example.SignatureSandwich;

import org.example.Builder.BreadTopping;
import org.example.Builder.Topping;
import org.example.enums.SandwichLength;

import java.util.List;

public abstract class SignatureSandwich {
    private SandwichLength length;
    private BreadTopping bread;
    private List<Topping> toppings;
    private boolean isToasted;

    public SignatureSandwich(SandwichLength length, BreadTopping bread, List<Topping> toppings) {
        this.length = length;
        this.bread = bread;
        this.toppings = toppings;
        this.isToasted = isToasted;
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
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public double getTotalPrice() {
        double total = length.getBasePrice(); // base price includes bread
        int inches = length.getLengthInInches();

        for (Topping topping : toppings) {
            total += topping.getPrice(inches);
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(length).append(" sandwich on ").append(bread.getName()).append(" with:\n");
        for (Topping topping : toppings) {
            sb.append(" - ").append(topping.getName()).append("\n");
        }
        sb.append("Total: $").append(String.format("%.2f", getTotalPrice()));
        return sb.toString();
    }
}



