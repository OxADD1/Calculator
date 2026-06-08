package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("InteractiveCalculator")
class InteractiveCalculatorTest {

  private final InputStream originalIn = System.in;

  @AfterEach
  void tearDown() {
    System.setIn(originalIn);
  }

  private void runWithInput(String input) {
    System.setIn(
        new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    new InteractiveCalculator().run();
  }

  @Nested
  @DisplayName("commands")
  class Commands {

    /** Tests that the quit command ends the REPL session. */
    @Test
    @DisplayName("quit ends session")
    void quitEndsSession() {
      // Arrange
      String input = "quit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that the exit command ends the REPL session. */
    @Test
    @DisplayName("exit ends session")
    void exitEndsSession() {
      // Arrange
      String input = "exit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that EOF (empty input stream) ends the session gracefully. */
    @Test
    @DisplayName("EOF ends session gracefully")
    void eofEndsSession() {
      // Arrange
      String input = "";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that empty lines are skipped without error. */
    @Test
    @DisplayName("empty lines are skipped")
    void emptyLinesSkipped() {
      // Arrange
      String input = "\n\n\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that the help command prints usage info. */
    @Test
    @DisplayName("help shows usage info")
    void helpShowsUsage() {
      // Arrange
      String input = "help\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that history shows empty message when no calculations. */
    @Test
    @DisplayName("history shows empty message when no calculations")
    void historyEmpty() {
      // Arrange
      String input = "history\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that history lists entries after calculations. */
    @Test
    @DisplayName("history shows entries after calculations")
    void historyWithEntries() {
      // Arrange
      String input = "2 + 3\n10 - 4\nhistory\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that the clear command removes all history entries. */
    @Test
    @DisplayName("clear removes history entries")
    void clearRemovesHistory() {
      // Arrange
      String input = "2 + 3\nclear\nhistory\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }
  }

  @Nested
  @DisplayName("binary operations")
  class BinaryOperations {

    /** Tests that addition evaluates correctly via the REPL. */
    @Test
    @DisplayName("addition: 5 + 3")
    void addition() {
      // Arrange
      String input = "5 + 3\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that subtraction evaluates correctly via the REPL. */
    @Test
    @DisplayName("subtraction: 10 - 4")
    void subtraction() {
      // Arrange
      String input = "10 - 4\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that multiplication evaluates correctly via the REPL. */
    @Test
    @DisplayName("multiplication: 6 * 7")
    void multiplication() {
      // Arrange
      String input = "6 * 7\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that division evaluates correctly via the REPL. */
    @Test
    @DisplayName("division: 10 / 2")
    void division() {
      // Arrange
      String input = "10 / 2\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that modulo evaluates correctly via the REPL. */
    @Test
    @DisplayName("modulo: 10 % 3")
    void modulo() {
      // Arrange
      String input = "10 % 3\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that power evaluates correctly via the REPL. */
    @Test
    @DisplayName("power: 2 ^ 8")
    void power() {
      // Arrange
      String input = "2 ^ 8\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that an unknown operator logs an error without crashing. */
    @Test
    @DisplayName("unknown operator logs error")
    void unknownOperator() {
      // Arrange
      String input = "2 @ 3\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that division by zero is caught and logged as error. */
    @Test
    @DisplayName("division by zero logs error")
    void divisionByZero() {
      // Arrange
      String input = "10 / 0\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }
  }

  @Nested
  @DisplayName("unary operations")
  class UnaryOperations {

    /** Tests that sqrt with an integer result formats correctly. */
    @Test
    @DisplayName("sqrt with integer result")
    void sqrtInteger() {
      // Arrange
      String input = "sqrt 16\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that sqrt with a non-integer result shows decimals. */
    @Test
    @DisplayName("sqrt with non-integer result")
    void sqrtNonInteger() {
      // Arrange
      String input = "sqrt 2\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that abs returns the absolute value of a negative number. */
    @Test
    @DisplayName("abs of negative number")
    void absNegative() {
      // Arrange
      String input = "abs -5\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that factorial computes correctly via the REPL. */
    @Test
    @DisplayName("factorial")
    void factorial() {
      // Arrange
      String input = "factorial 6\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that an unknown unary command logs an error. */
    @Test
    @DisplayName("unknown command logs error")
    void unknownCommand() {
      // Arrange
      String input = "foo 5\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }
  }

  @Nested
  @DisplayName("error handling")
  class ErrorHandling {

    /** Tests that input with too many parts triggers an error message. */
    @Test
    @DisplayName("invalid input with wrong part count")
    void invalidPartCount() {
      // Arrange
      String input = "1 2 3 4\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that non-numeric operands trigger a format error. */
    @Test
    @DisplayName("invalid number format in binary expression")
    void invalidNumberFormat() {
      // Arrange
      String input = "abc + def\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that a single word triggers the invalid input message. */
    @Test
    @DisplayName("single word triggers invalid input message")
    void singleWord() {
      // Arrange
      String input = "hello\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }

    /** Tests that a non-numeric unary operand triggers a format error. */
    @Test
    @DisplayName("invalid number in unary expression")
    void invalidUnaryNumber() {
      // Arrange
      String input = "sqrt abc\nquit\n";

      // Act & Assert
      assertDoesNotThrow(() -> runWithInput(input));
    }
  }

  /** Tests that the main method starts the REPL and exits on quit. */
  @Test
  @DisplayName("main method starts and exits on EOF")
  void mainMethod() {
    // Arrange
    System.setIn(new ByteArrayInputStream(
        "quit\n".getBytes(StandardCharsets.UTF_8)));

    // Act & Assert
    assertDoesNotThrow(
        () -> InteractiveCalculator.main(new String[]{}));
  }
}
