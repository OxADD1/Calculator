package com.example;

// === Exercise 3.3: AI vs. Static Analysis — Comparison Table ===
// Prompt: "Review my Calculator class for quality issues"
//
// | Finding                        | Static tools found it? | Claude Code found it? |
// |--------------------------------|------------------------|-----------------------|
// | Formatting / style violations  | CheckStyle: yes        | no                    |
// | Empty catch blocks             | PMD: yes               | yes                   |
// | String == comparison bug       | SpotBugs: yes          | yes                   |
// | Design (methods could be static)| no                    | yes                   |
// | Missing validation (div by 0)  | no                     | yes                   |
// | Logging (System.out vs SLF4J)  | no                     | no (already fixed)    |
// | readFile() returns wrong value | no                     | yes                   |
// | throws Exception too generic   | no                     | yes                   |
//
// Key insight: Static tools catch rules. AI catches intent.
// Neither catches everything alone.

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
    // Exercise 4.1: introduced bug (return a + b) — all 5 tests caught it
    // Run: mvn test
    return a * b;
  }

  /** Divides first number by second. */
  public int divide(int a, int b) {
    return a / b;
  }

  // Exercise 4.3 (slide 100): TDD with AI — implementation written after tests
  /**
   * Computes the factorial of a non-negative integer.
   *
   * @param n the non-negative integer
   * @return n! as a long
   * @throws ArithmeticException if n is negative
   */
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

  // === Intentionally bad code for testing quality tools (Exercise 3.1) ===
  // Uncomment each block to test: which tool catches what?
  // Cmd + /

  // CheckStyle: bad naming + formatting
  // public void x(){ }

  // PMD: empty catch block
  // public int safeParse(String input) {
  // try {
  // return Integer.parseInt(input);
  // } catch (NumberFormatException e) {
  // }
  // return 0;
  // }

  // SpotBugs: String comparison with == instead of .equals()
  // public boolean isAdmin(String username) {
  // return username == "admin";
  // }

  // === Intentionally bad code for bug hunting (Exercise 3.2) ===

  // Bug 1 fixed: null check before calling toString()
  /** Returns string description of an object. */
  public String describe(Object obj) {
    if (obj == null) {
      return "null";
    }
    return obj.toString();
  }

  // Bug 2 fixed: try-with-resources closes reader automatically
  /** Reads first line from a file. */
  public String readFile(String path) throws Exception {
    try (java.io.FileReader reader =
        new java.io.FileReader(path, java.nio.charset.StandardCharsets.UTF_8)) {
      return reader.toString();
    }
  }
}
