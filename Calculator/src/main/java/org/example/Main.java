package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number...");
        int firstNumber = scanner.nextInt();

        System.out.println(" Enter the second number...");
        int secondNumber = scanner.nextInt();

        System.out.println("Enter (A = Add) (S = Subtract) (D + Divide) or (M = Multiply)");
        String month = scanner.next();
        String str;
        switch (month) {
            case "D":
                str = (firstNumber + "/" +secondNumber + "=" +Math.floorDiv(firstNumber,secondNumber));
                break;
            case "A":
                str = (firstNumber + "+" +secondNumber + "=" +Math.addExact(firstNumber,secondNumber));
                break;
            case "S":
                str = (firstNumber + "-" +secondNumber + "="+Math.subtractExact(firstNumber,secondNumber));
                break;
            case "M":
                str = ((firstNumber + "*" +secondNumber + "="+Math.multiplyExact(firstNumber,secondNumber)));
                break;
            default:
                str = "I can not Calculate this.";
                break;
        }

        System.out.println("Result " + str);
















    }
}