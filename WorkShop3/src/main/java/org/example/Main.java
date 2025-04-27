package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> allProducts = FileLoader.readFile();
        ShoppingCart cart = new ShoppingCart();
        // TODO Thank Jonathan for this template because it really helped 💚
        while (true) {
            System.out.println("Welcome to the store! Select a Number for the options below:");
            System.out.println("1. View all products");
            System.out.println("2. Search by SKU");
            System.out.println("3. Search by price range");
            System.out.println("4. Search by name");
            System.out.println("5. Add to cart");
            System.out.println("6. Remove from cart");
            System.out.println("7. View cart");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayProducts(allProducts);
                    break;

                case 2:
                    System.out.print("Enter SKU to search: ");
                    String skuSearch = scanner.nextLine();

                    Product skuFound = findBySku(allProducts, skuSearch);

                    if (skuFound != null) {
                        System.out.println("Found: SKU: " + skuFound.getSku()+"| Name: "+ skuFound.getProductName() +"| Price: "
                                + skuFound.getPrice()+" | Department:"+skuFound.getDepartment());

                    } else {
                        System.out.println("No product found with that SKU.");
                    }
                    break;

                case 3:
                    System.out.print("Enter minimum price: ");
                    double minPrice = Double.parseDouble(scanner.nextLine());

                    System.out.print("Enter maximum price: ");
                    double maxPrice = Double.parseDouble(scanner.nextLine());

                    List<Product> priceSearched = filterByPriceRange(allProducts, minPrice, maxPrice);
                    displayProducts(priceSearched);
                    break;

                case 4:
                    System.out.print("Enter name to search: ");
                    String nameSearch = scanner.nextLine();

                    List<Product> nameContains = searchByName(allProducts, nameSearch);
                    displayProducts(nameContains);
                    break;

                case 5:
                    System.out.print("Enter SKU to add to cart: ");
                    String skuToAdd = scanner.nextLine();

                    Product productToAdd = findBySku(allProducts, skuToAdd);
                    if (productToAdd != null) {
                        cart.addProductToCart(productToAdd);
                    } else {
                        System.out.println("Unable to find product.");
                    }
                    break;

                case 6:
                    System.out.print("Enter SKU to remove from cart: ");
                    String skuToRemove = scanner.nextLine();

                    cart.removeProduct(skuToRemove);
                    break;

                case 7:
                    cart.displayItems();
                    break;

                case 8:
                    checkout(cart);
                    break;

                case 9:

                    System.out.println("Have A Nice Day!");

                    System.exit(0);
                    break;


                default:

                    System.out.println("ERROR 404!!");
            }
        }
    }



    public static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {

                System.out.printf("SKU: %s | Name: %s | Price: $%.2f | Department: %s%n",
                        product.getSku(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getDepartment());
               // I could not get this to print in the println?
            }
        }
    }


    public static Product findBySku(List<Product> products, String sku) {
        for (Product product : products) {
            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }


    public static List<Product> searchByName(List<Product> products, String name) {
        List<Product> matches = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(product);
            }
        }
        return matches;
    }


    public static List<Product> filterByPriceRange(List<Product> products, double min, double max) {
        List<Product> matches = new ArrayList<>();
        for (Product product : products) {
            double price = product.getPrice();
            if (price >= min && price <= max) {
                matches.add(product);
            }
        }
        return matches;
    }
    public static void checkout(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);
        double totalAmount = cart.getCartTotal();

        System.out.println("Total amount for this order: $" + totalAmount);

        System.out.print("Enter payment amount: $");
        double payment = Double.parseDouble(scanner.nextLine());

        if (payment < totalAmount) {
            System.out.println("Insufficient payment. TO THE SHADOW REALM🔥.");
            return;
        }
        double change = payment - totalAmount;
        System.out.println("Change due: $" + change);

        printReceipt(cart, totalAmount, payment, change);
        cart.clearCart();

        System.out.println("Thank you for your buisness!");
    }
    private static void printReceipt(ShoppingCart cart, double totalAmount, double payment, double change) {
        System.out.println("******* SALES RECEIPT *******");
        System.out.println("Items Purchased:");


        for (Product product : cart.getProducts()) {
            System.out.println("SKU: " + product.getSku() + " | Name: " + product.getProductName() + " | Price: $" + product.getPrice());
        }

        System.out.println("Total: $" + totalAmount);

        System.out.println("Amount Paid: $" + payment);

        System.out.println("Change Given: $" + change);

        System.out.println("********************");
    }

}
