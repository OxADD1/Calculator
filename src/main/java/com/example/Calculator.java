package com.example;

/** Provides basic arithmetic operations. */
public class Calculator {
  /** Adds two numbers. */
  public int add(int a, int b) {
    return a + b;
  }

  /** Subtracts second number from first. */
  public int subtract(int a, int b) {
    return a - b;
  }

  /** Multiplies two numbers. */
  public int multiply(int a, int b) {
    return a * b;
  }

  /** Divides first number by second. */
  public int divide(int a, int b) {
    return a / b;
  }

  // === Intentionally bad code for testing quality tools (Exercise 3.1) ===
  // Uncomment each block to test: which tool catches what? 
  // Cmd + / 

  // CheckStyle: bad naming + formatting
  // public void x(){ }

  // PMD: empty catch block
  // public int safeParse(String input) {
  //   try {
  //     return Integer.parseInt(input);
  //   } catch (NumberFormatException e) {
  //   }
  //   return 0;
  // }

  // SpotBugs: String comparison with == instead of .equals()
  // public boolean isAdmin(String username) {
  //   return username == "admin";
  // }
}
