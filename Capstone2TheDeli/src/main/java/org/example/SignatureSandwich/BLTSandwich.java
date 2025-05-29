package org.example.SignatureSandwich;

import org.example.Builder.BreadTopping;
import org.example.Builder.PremiumTopping;
import org.example.Builder.RegularTopping;
import org.example.enums.SandwichLength;
import org.example.enums.ToppingType;

import java.util.List;

public class BLTSandwich extends SignatureSandwich {
        public BLTSandwich() {
            super(
                    SandwichLength.EIGHT_INCH,
                    new BreadTopping("White"),
                    List.of(
                            new PremiumTopping("Bacon", ToppingType.MEAT),
                            new PremiumTopping("Cheddar", ToppingType.CHEESE),
                            new RegularTopping("Lettuce"),
                            new RegularTopping("Tomato"),
                            new RegularTopping("Ranch")
                    )
            );
        }
    }
