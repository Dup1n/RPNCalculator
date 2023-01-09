package org.dupin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RPN rpn = new RPN();
        Scanner scanner = new Scanner(System.in);
        double result = 0;

        while (true) {
            System.out.println("Do you want add a number?[yes/no]");
            String answer = scanner.next();
            if (answer.equals("yes")) {
                rpn.add(result);
                System.out.println("Enter a number");
                result = scanner.nextFloat();
            }
            System.out.println("Select a command: enter, ac, swap, add, sub, mul, div, inv, sqrt or pow");
            String command = scanner.next();
            switch (command) {
                case "enter": rpn.add(result); break;
                case "add": result = rpn.sum(result); break;
                case "sub": result = rpn.sub(result); break;
                case "mul": result = rpn.mul(result); break;
                case "div": result = rpn.div(result); break;
                case "pow": result = rpn.power(result); break;
                case "inv": result = rpn.inverse(result); break;
                case "sqrt": result = rpn.sqrt(result); break;
                case "swap": result = rpn.swap(result); break;
                case "ac": rpn.ac(); break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
            System.out.println(rpn);
            System.out.println(result);
        }
    }
}