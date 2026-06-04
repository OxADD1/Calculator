package com.example;

// Vibe Coding Experiment (slide 78):
// This class was AI-generated without constraints (no CLAUDE.md, no rules).
// Check command: mvn checkstyle:check pmd:check spotbugs:check
// Result: 1 PMD violation found — "UselessParentheses" in percentage() method.
// Fix: removed unnecessary parentheses around (value / total) → value / total * 100.0

/**
 * Extends basic Calculator with scientific operations.
 */
public class ScientificCalculator extends Calculator {

  /** Raises base to the given exponent. */
  public double power(double base, double exponent) {
    return Math.pow(base, exponent);
  }

  /** Returns the square root of a number. */
  public double sqrt(double a) {
    if (a < 0) {
      throw new ArithmeticException("Cannot compute square root of a negative number");
    }
    return Math.sqrt(a);
  }

  /** Returns the remainder of a divided by b. */
  public int modulo(int a, int b) {
    if (b == 0) {
      throw new ArithmeticException("Cannot compute modulo with divisor zero");
    }
    return a % b;
  }

  /** Returns the absolute value. */
  public double abs(double a) {
    return Math.abs(a);
  }

  /** Computes the factorial of a non-negative integer. */
  public long factorial(int n) {
    if (n < 0) {
      throw new ArithmeticException("Factorial is not defined for negative numbers");
    }
    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /** Returns the percentage: (value / total) * 100. */
  public double percentage(double value, double total) {
    if (total == 0) {
      throw new ArithmeticException("Cannot compute percentage with total zero");
    }
    return value / total * 100.0;
  }
}
