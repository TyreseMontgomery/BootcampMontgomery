package org.example.Builder;
import org.example.enums.SandwichLength;

import java.util.ArrayList;
import java.util.List;

public class SandwichBuilder {
    private SandwichLength length;
    private BreadTopping bread;
    private final List<Topping> toppings = new ArrayList<>();
    private boolean toasted = false;

    public SandwichBuilder setLength(SandwichLength length) {
        this.length = length;
        return this;
    }

    public SandwichBuilder setBread(BreadTopping bread) {
        this.bread = bread;
        return this;
    }
    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public SandwichBuilder addTopping(Topping topping) {
        toppings.add(topping);
        return this;
    }

    public Sandwich build() {
        return new Sandwich(length, bread, toppings,toasted);
    }

}
