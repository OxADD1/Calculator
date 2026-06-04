package com.example;

import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Run with: mvn compile exec:java -Dexec.mainClass="com.example.InteractiveCalculator"

/**
 * Interactive REPL calculator with scientific operations and history.
 *
 * <p>Supported expressions:
 * <ul>
 *   <li>Binary: {@code 5 + 3}, {@code 10 - 4}, {@code 6 * 7}, {@code 10 / 2},
 *       {@code 10 % 3}, {@code 2 ^ 8}</li>
 *   <li>Unary: {@code sqrt 16}, {@code abs -5}, {@code factorial 6}</li>
 *   <li>Commands: {@code history}, {@code clear}, {@code help}, {@code quit}</li>
 * </ul>
 */
public class InteractiveCalculator {

  private static final Logger LOG = LoggerFactory.getLogger(InteractiveCalculator.class);

  private final ScientificCalculator calc = new ScientificCalculator();
  private final CalculationHistory history = new CalculationHistory();

  /** Starts the interactive REPL. */
  public static void main(String[] args) {
    new InteractiveCalculator().run();
  }

  /** Main REPL loop. */
  public void run() {
    Scanner scanner = new Scanner(System.in, "UTF-8");
    printBanner();

    while (true) {
      System.out.print("> ");
      if (!scanner.hasNextLine()) {
        break;
      }
      String line = scanner.nextLine().trim();

      if (line.isEmpty()) {
        continue;
      }
      if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
        LOG.info("Session ended. {} calculations performed.", history.size());
        break;
      }
      if (line.equalsIgnoreCase("help")) {
        printHelp();
        continue;
      }
      if (line.equalsIgnoreCase("history")) {
        printHistory();
        continue;
      }
      if (line.equalsIgnoreCase("clear")) {
        history.clear();
        System.out.println("History cleared.");
        continue;
      }

      evaluate(line);
    }
    scanner.close();
  }

  private void evaluate(String input) {
    try {
      String[] parts = input.split("\\s+");

      if (parts.length == 2) {
        evaluateUnary(parts[0], parts[1], input);
      } else if (parts.length == 3) {
        evaluateBinary(parts[0], parts[1], parts[2], input);
      } else {
        System.out.println("Invalid input. Type 'help' for usage.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: invalid number format.");
    } catch (ArithmeticException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void evaluateUnary(String command, String operand, String input) {
    switch (command.toLowerCase()) {
      case "sqrt":
        double sqrtResult = calc.sqrt(Double.parseDouble(operand));
        printAndRecord(input, formatNumber(sqrtResult));
        break;
      case "abs":
        double absResult = calc.abs(Double.parseDouble(operand));
        printAndRecord(input, formatNumber(absResult));
        break;
      case "factorial":
        long factResult = calc.factorial(Integer.parseInt(operand));
        printAndRecord(input, String.valueOf(factResult));
        break;
      default:
        System.out.println("Unknown command: " + command + ". Type 'help' for usage.");
        break;
    }
  }

  private void evaluateBinary(String left, String operator, String right, String input) {
    double a = Double.parseDouble(left);
    double b = Double.parseDouble(right);
    double result;

    switch (operator) {
      case "+":
        result = calc.add((int) a, (int) b);
        break;
      case "-":
        result = calc.subtract((int) a, (int) b);
        break;
      case "*":
        result = calc.multiply((int) a, (int) b);
        break;
      case "/":
        if (b == 0) {
          throw new ArithmeticException("Division by zero");
        }
        result = calc.divide((int) a, (int) b);
        break;
      case "%":
        result = calc.modulo((int) a, (int) b);
        break;
      case "^":
        result = calc.power(a, b);
        break;
      default:
        System.out.println("Unknown operator: " + operator + ". Type 'help' for usage.");
        return;
    }
    printAndRecord(input, formatNumber(result));
  }

  private void printAndRecord(String expression, String result) {
    System.out.println("  " + result);
    history.record(expression, result);
  }

  private String formatNumber(double value) {
    if (value == (long) value) {
      return String.valueOf((long) value);
    }
    return String.valueOf(value);
  }

  private void printHistory() {
    List<String> entries = history.getEntries();
    if (entries.isEmpty()) {
      System.out.println("No calculations yet.");
      return;
    }
    System.out.println("--- History ---");
    for (int i = 0; i < entries.size(); i++) {
      System.out.println("  " + (i + 1) + ") " + entries.get(i));
    }
    System.out.println("---------------");
  }

  private void printBanner() {
    System.out.println("========================================");
    System.out.println("  Interactive Scientific Calculator");
    System.out.println("  Type 'help' for commands, 'quit' to exit");
    System.out.println("========================================");
  }

  private void printHelp() {
    System.out.println("Usage:");
    System.out.println("  Arithmetic:   5 + 3 | 10 - 4 | 6 * 7 | 10 / 2");
    System.out.println("  Power:        2 ^ 8");
    System.out.println("  Modulo:       10 % 3");
    System.out.println("  Square root:  sqrt 16");
    System.out.println("  Absolute:     abs -5");
    System.out.println("  Factorial:    factorial 6");
    System.out.println("  History:      history | clear");
    System.out.println("  Exit:         quit | exit");
  }
}
