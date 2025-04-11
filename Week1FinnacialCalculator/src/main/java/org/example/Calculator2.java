package org.example;

import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
        // ask the user questions first
        // collect all the data
        // assume information makes sense
        // calculate equation
        // display number;
        Scanner scanner = new Scanner(System.in);;
        // display number:
        // |principle = p|
        // |annual Interest rate = r|
        // |loan term = y|
        // |monthly interest rate =i|
        // |Number of monthly payments = n|
        // Monthly payments = m |
        System.out.println("Input principle value of home.");
        double p = scanner.nextDouble();

        System.out.println(" Next, input your interest rate");

        double inputr = scanner.nextDouble();
        double r = (inputr / 12) /100;

        System.out.println(" Lastly, Input your loan term (in years)");
        double y = scanner.nextDouble();

        int n = (int) (y*12);

        double v = 1 + r;
        double b = Math.pow(v,n);
        double l = b * r;
        double left = p * l;
        double right = b - 1;
        double m = left / right;
        double totalInterest = m * n - p;


        System.out.printf("You will pay %.2f$ in total interest over the term .",totalInterest);



    }
}