package com.example;

/** Main application entry point. */
public class App {
  /** Runs the calculator demo. */
  public static void main(String[] args) {
    Calculator calc = new Calculator();
    System.out.println("2 + 3 = " + calc.add(2, 3));
    System.out.println("10 - 4 = " + calc.subtract(10, 4));
    System.out.println("6 * 7 = " + calc.multiply(6, 7));
  }
}
