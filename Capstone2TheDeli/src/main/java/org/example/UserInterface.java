package org.example;

import org.example.Builder.*;
import org.example.OrderBuilder.Chips;
import org.example.OrderBuilder.Drink;
import org.example.SignatureSandwich.BLTSandwich;
import org.example.SignatureSandwich.PhillyCheeseSteakSandwich;
import org.example.SignatureSandwich.SignatureSandwich;
import org.example.enums.SandwichLength;
import org.example.enums.ToppingType;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order = new Order();

    public UserInterface() {
    }

    public void start() {
        System.out.println("Welcome to the Sandwich Deli!");
        displaySignatureSandwiches();
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> buildCustomSandwich();
                case "2" -> selectSignatureSandwich();
                case "3" -> addDrink();
                case "4" -> addChips();
                case "5" -> checkout();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Thank you for visiting!");
    }

    private void printMainMenu() {
        System.out.println("\n-_-_-_ MENU _-_-_");
        System.out.println("1. Build Custom Sandwich");
        System.out.println("2. Choose Signature Sandwich");
        System.out.println("3. Add Drink");
        System.out.println("4. Add Chips");
        System.out.println("5. Checkout");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private void printToppingReference() {
        System.out.println("\n-_-_-_-_Topping Reference -_-_-_-_");
        System.out.println("BREAD: white, wheat, multigrain, italian");
        System.out.println("MEAT: turkey, ham, roast beef, chicken");
        System.out.println("CHEESE: cheddar, swiss, provolone, american");
        System.out.println("SAUCE: mayo, mustard, ketchup, ranch, chipotle");
        System.out.println("FREE_TOPPING: lettuce, tomato, onion, pickle, cucumber, green pepper");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n");
    }

    private void buildCustomSandwich() {
        SandwichBuilder builder = new SandwichBuilder();

        System.out.print("Choose sandwich length (4, 8, 12): ");
        int length = Integer.parseInt(scanner.nextLine());
        SandwichLength sandwichLength = SandwichLength.fromInches(length);
        if (sandwichLength == null) {
            System.out.println("Invalid length. Sandwich not created.");
            return;
        }
        builder.setLength(sandwichLength);

        // 1. Bread
        System.out.println("Select your bread type:");
        System.out.println("Options: white, wheat, multigrain, italian");
        String breadType = scanner.nextLine();
        builder.setBread(new BreadTopping(breadType));

        // 2. Meat
        System.out.println("Select your meat topping:");
        System.out.println("Examples: turkey, ham, roast beef, chicken");
        String meat = scanner.nextLine();
        builder.addTopping(new PremiumTopping(meat, ToppingType.MEAT));

        System.out.print("Would you like extra meat? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter extra meat type:");
            String extraMeat = scanner.nextLine();
            builder.addTopping(new PremiumTopping(extraMeat, ToppingType.MEAT));
        }

        // 3. Cheese
        System.out.println("Select your cheese topping:");
        System.out.println("Examples: cheddar, swiss, provolone, american");
        String cheese = scanner.nextLine();
        builder.addTopping(new PremiumTopping(cheese, ToppingType.CHEESE));

        System.out.print("Would you like extra cheese? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter extra cheese type:");
            String extraCheese = scanner.nextLine();
            builder.addTopping(new PremiumTopping(extraCheese, ToppingType.CHEESE));
        }

        // 4. Regular toppings
        System.out.println("Add regular toppings (lettuce, tomato, onion, pickle, etc). Type 'done' to finish:");
        while (true) {
            System.out.print("Enter topping: ");
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) break;
            builder.addTopping(new RegularTopping(topping));
        }

        // 5. Sauces
        System.out.println("Add sauces (mayo, mustard, ranch, chipotle, etc). Type 'done' to finish:");
        while (true) {
            System.out.print("Enter sauce: ");
            String sauce = scanner.nextLine();
            if (sauce.equalsIgnoreCase("done")) break;
            builder.addTopping(new RegularTopping(sauce));
        }

        // 6. Toasted
        System.out.print("Would you like your sandwich toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");
        builder.setToasted(toasted);

        // Finalize sandwich
        Sandwich sandwich = builder.build();
        order.addSandwich(sandwich);
        System.out.println("Custom sandwich added to order.");

    }


    private void selectSignatureSandwich() {
        displaySignatureSandwiches();
        System.out.println("Select a signature sandwich:");
        System.out.println("1. Philly Cheesesteak");
        System.out.println("2. BLT");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        SignatureSandwich selectedSandwich = null;

        switch (choice) {
            case "1" -> selectedSandwich = new PhillyCheeseSteakSandwich();
            case "2" -> selectedSandwich = new BLTSandwich();
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        System.out.print("Would you like it toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Would you like to customize it? (yes/no): ");
        boolean customize = scanner.nextLine().equalsIgnoreCase("yes");

        if (customize) {
            SandwichBuilder builder = new SandwichBuilder();
            builder.setLength(selectedSandwich.getLength());
            builder.setBread(selectedSandwich.getBread());
            builder.setToasted(toasted);

            // Let user modify toppings
            for (Topping topping : selectedSandwich.getToppings()) {
                builder.addTopping(topping); // preload with defaults
            }

            System.out.println("You can now add or change toppings.");
            while (true) {
                System.out.print("Would you like to add a new topping? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("no")) break;

                try {
                    printToppingReference();
                    System.out.print("Topping type (MEAT, CHEESE, SAUCE, FREE_TOPPING): ");
                    ToppingType type = ToppingType.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Enter topping name (e.g., ham, cheddar, lettuce): ");
                    String toppingName = scanner.nextLine();

                    Topping newTopping = (type == ToppingType.MEAT || type == ToppingType.CHEESE)
                            ? new PremiumTopping(toppingName, type)
                            : new RegularTopping(toppingName);

                    builder.addTopping(newTopping);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid topping type. Try again.");
                }
            }

            Sandwich customSandwich = builder.build();
            order.addSandwich(customSandwich);
            System.out.println("Customized sandwich added to order.");
        } else {
            selectedSandwich.setToasted(toasted);
            order.addSignatureSandwich(selectedSandwich);
            System.out.println("Signature sandwich added to order.");
        }
    }

    private void addDrink() {
        System.out.print("Enter drink size (small, medium, large): ");
        String size = scanner.nextLine().toLowerCase();
        System.out.print("Enter drink flavor: \n");
        String flavor = scanner.nextLine();
        Drink drink = new Drink(size, flavor);
        order.addDrink(drink);
        System.out.println("Added " + size + " " + flavor + " drink.");
    }

    private void addChips() {
        System.out.print("Enter chip flavor: ");
        String flavor = scanner.nextLine();
        Chips chips = new Chips(flavor);
        order.addChips(chips);
        System.out.println("Added chips: " + flavor);
    }
    private void checkout() {
        order.printReceipt();
        ReceiptFileManager.saveTransaction(order);
        System.out.println("Receipt written to receipt.txt");
    }
    private void displaySignatureSandwiches() {
        System.out.println("\n_-_-_-_-_-_- Signature Sandwiches -_-_-_-_-_");
        System.out.println("1. Philly Cheesesteak:");
        System.out.println(" - Length: 8 inches");
        System.out.println(" - Bread: Italian");
        System.out.println(" - Toppings: Steak, Provolone Cheese, Onions, Green Peppers, Mayo");

        System.out.println("\n2. BLT:");
        System.out.println(" - Length: 8 inches");
        System.out.println(" - Bread: Italian");
        System.out.println(" - Toppings: Bacon, Lettuce, Tomato, Mayo");

        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n");
    }

}
