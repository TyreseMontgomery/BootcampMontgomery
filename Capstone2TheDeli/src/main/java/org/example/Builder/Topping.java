package org.example.Builder;

import org.example.enums.ToppingType;

public abstract class Topping {
    private String name;
    private ToppingType type;

    public Topping(String name, ToppingType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }

    public abstract double getPrice(int sandwichSizeInInches);
}
