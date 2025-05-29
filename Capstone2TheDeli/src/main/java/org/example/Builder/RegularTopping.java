package org.example.Builder;

import org.example.enums.ToppingType;

public class RegularTopping extends Topping {
    public RegularTopping(String name) {
        super(name, ToppingType.REGULAR);
    }

    @Override
    public double getPrice(int sandwichSizeInInches) {
        return 0.0; // Included in sandwich price
    }
}
