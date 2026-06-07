package com.example;

// Vibe Coding Experiment (slide 78):
// This class was AI-generated without constraints (no CLAUDE.md, no rules).
// Check command: mvn checkstyle:check pmd:check spotbugs:check
// Result: 1 PMD violation found — "UselessParentheses" in ScientificCalculator.percentage().
// Fix: removed unnecessary parentheses around (value / total) → value / total * 100.0

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
 * <li>Binary: {@code 5 + 3}, {@code 10 - 4}, {@code 6 * 7}, {@code 10 / 2},
 * {@code 10 % 3}, {@code 2 ^ 8}</li>
 * <li>Unary: {@code sqrt 16}, {@code abs -5}, {@code factorial 6}</li>
 * <li>Commands: {@code history}, {@code clear}, {@code help}, {@code quit}</li>
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
      LOG.info("> ");
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
        LOG.info("History cleared.");
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
        LOG.info("Invalid input. Type 'help' for usage.");
      }
    } catch (NumberFormatException e) {
      LOG.info("Error: invalid number format.");
    } catch (ArithmeticException e) {
      LOG.info("Error: {}", e.getMessage());
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
        LOG.info("Unknown command: {}. Type 'help' for usage.", command);
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
        LOG.info("Unknown operator: {}. Type 'help' for usage.", operator);
        return;
    }
    printAndRecord(input, formatNumber(result));
  }

  private void printAndRecord(String expression, String result) {
    LOG.info("  {}", result);
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
      LOG.info("No calculations yet.");
      return;
    }
    LOG.info("--- History ---");
    for (int i = 0; i < entries.size(); i++) {
      LOG.info("  {}) {}", i + 1, entries.get(i));
    }
    LOG.info("---------------");
  }

  private void printBanner() {
    LOG.info("========================================");
    LOG.info("  Interactive Scientific Calculator");
    LOG.info("  Type 'help' for commands, 'quit' to exit");
    LOG.info("========================================");
  }

  private void printHelp() {
    LOG.info("Usage:");
    LOG.info("  Arithmetic:   5 + 3 | 10 - 4 | 6 * 7 | 10 / 2");
    LOG.info("  Power:        2 ^ 8");
    LOG.info("  Modulo:       10 % 3");
    LOG.info("  Square root:  sqrt 16");
    LOG.info("  Absolute:     abs -5");
    LOG.info("  Factorial:    factorial 6");
    LOG.info("  History:      history | clear");
    LOG.info("  Exit:         quit | exit");
  }
}
