package org.example.Builder;

import org.example.enums.ToppingType;

public class PremiumTopping extends Topping {
    public PremiumTopping(String name, ToppingType type) {
        super(name, type);
    }

    @Override
    public double getPrice(int sandwichSizeInInches) {
        return switch (getType()) {
            case MEAT -> switch (sandwichSizeInInches) {
                case 4 -> 1.00;
                case 8 -> 2.00;
                case 12 -> 3.00;
                default -> 0.0;
            };
            case EXTRA_MEAT -> switch (sandwichSizeInInches) {
                case 4 -> 0.50;
                case 8 -> 1.00;
                case 12 -> 1.50;
                default -> 0.0;
            };
            case CHEESE -> switch (sandwichSizeInInches) {
                case 4 -> 0.75;
                case 8 -> 1.50;
                case 12 -> 2.25;
                default -> 0.0;
            };
            case EXTRA_CHEESE -> switch (sandwichSizeInInches) {
                case 4 -> 0.30;
                case 8 -> 0.60;
                case 12 -> 0.90;
                default -> 0.0;
            };
            default -> 0.0;
        };
    }
}
