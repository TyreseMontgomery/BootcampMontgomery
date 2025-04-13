package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what is your monthly payout?");
        double pmt = scanner.nextDouble();
        System.out.println("what is your expected interest rate?");
        double inR= scanner.nextDouble();
        double r = (inR/12)/100;
        System.out.println("lastly how many years will it pay you out?");
        double y= scanner.nextDouble();
        double n= y*12;


        //PV = PMT × [(1 - (1 + r)^(-n)) / r]
        double v = 1+r;
        double b = Math.pow(v,(-n));
        double e = 1-b;
        double o = e / r;
        double pv= pmt * o;
        System.out.printf("The Present value is $%.2f",pv);






    }

}