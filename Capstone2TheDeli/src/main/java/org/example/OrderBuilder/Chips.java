package org.example.OrderBuilder;

import org.example.Interface.Price;

public class Chips implements Price {
    private String flavor;
    public Chips(String flavor) {
        this.flavor=flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }
    @Override
    public String toString() {
        return flavor + " chips ($" + String.format("%.2f", getPrice()) + ")";
    }

}

