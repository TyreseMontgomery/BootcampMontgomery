package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }
    public void addProductToCart(Product product) {
        products.add(product);
        System.out.println("Added to cart: " + product.getProductName()+"$"+product.getPrice());
    }
    public void removeProduct(String sku) {
        boolean found = false;

        // Loop through the products list
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getSku().equalsIgnoreCase(sku)) {
                products.remove(i);
                System.out.println("Removed from cart: " + product.getProductName());
                found = true;
                break;
            }
            // I index method made the most sense for me here.
        }

        if (!found) {
            System.out.println("No product found in cart with SKU: " + sku);
        }
    }

    public double getCartTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    public void displayItems() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (Product product : products) {
                System.out.println("SKU: " + product.getSku() +
                        " | Name: " + product.getProductName() +
                        " | Price: $" + String.format("%.2f", product.getPrice()) +
                        " | Department: " + product.getDepartment());
            }
        }
    }
    public List<Product> getProducts() {
        return this.products;
        // this is so I can use these items in the receipt
    }
    public void clearCart() {
        products.clear();
        // this is to empty the cart after the transaction. ( I am not trying to write a file quite yet)
    }
}
