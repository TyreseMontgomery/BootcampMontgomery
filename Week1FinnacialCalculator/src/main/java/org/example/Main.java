package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ask the user questions first
        // collect all the data
        // assume information makes sense
        // calculate equation
        // display number:
        // |principle = p|
        // |annual Interest rate = r|
        // |loan term = y|
        // |monthly interest rate =i|
        // |Number of monthly payments = n|
        // |Monthly payments = m |
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input principle value of home.");
        double p = scanner.nextDouble();

        System.out.println(" Next, input your interest rate");

        double inputr = scanner.nextDouble();
        double r = (inputr / 12) /100;

        System.out.println(" Lastly, Input your loan term (in years)");
        double y = scanner.nextDouble();

        int n = (int) (y*12);
         // M = P[r(1+r)^n] / [(1+r)^n-1]
        double v = 1 + r;
        double b = Math.pow(v,n);
        double l = b * r;
        double left = p * l;
        double right = b - 1;
        double m = left / right;
        double totalInterest = m * n - p;

        // A $53,000 loan at 7.625% interest for 15 years would
        //have a $495.09/mo payment with a total interest of $36,115.99
        System.out.printf("you would have $%.2f payment with a total interest of $%.2f", m, totalInterest);


    }
}