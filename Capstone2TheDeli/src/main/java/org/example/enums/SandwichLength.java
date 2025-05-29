package org.example.enums;

public enum SandwichLength {
    FOUR_INCH(4, 5.00),
    EIGHT_INCH(8, 7.50),
    TWELVE_INCH(12, 9.00);

    private final int lengthInInches;
    private final double basePrice;

    SandwichLength(int lengthInInches, double basePrice) {
        this.lengthInInches = lengthInInches;
        this.basePrice = basePrice;
    }

    public int getLengthInInches() {
        return lengthInInches;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public static SandwichLength fromInches(int inches) {
        for (SandwichLength sl : values()) {
            if (sl.lengthInInches == inches) {
                return sl;
            }
        }
        return null; // Or throw new IllegalArgumentException("Invalid length")
    }

    @Override
    public String toString() {
        return lengthInInches + "\"";
    }
}

