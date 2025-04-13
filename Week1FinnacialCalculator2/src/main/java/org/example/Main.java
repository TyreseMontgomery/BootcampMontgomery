package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ask questions to gather information
        // calculate answer
        // show results
        System.out.println("Input deposit value.");
        double p = scanner.nextDouble();
        System.out.println(" Next, input your interest rate");
        double di = scanner.nextDouble();
        double r= di/100;
        System.out.println(" Lastly, input your time until mature date");
        double t = scanner.nextDouble();
        //FV = P × (1 + (r / 365))^(365 × t)

        double q = r / 365;
        double w =Math.pow ((q+1),(365 * t));
        double fv= w*p;
        double totalInterestGained = fv-p;
        System.out.printf(" at maturity date you will have $%.2f, accruing $%.2f in interest.",fv,totalInterestGained);



    }
}