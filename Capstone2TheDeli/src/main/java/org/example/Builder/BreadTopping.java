package org.example.Builder;

import org.example.enums.ToppingType;

public class BreadTopping extends Topping {
    public BreadTopping(String name) {
        super(name, ToppingType.BREAD);
    }

    @Override
    public double getPrice(int sandwichSizeInInches) {
        return 0.0; // price is included in base sandwich price
    }
}
