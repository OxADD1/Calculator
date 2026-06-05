package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Module 4: JUnit basics — Given/When/Then structure, lifecycle, edge cases
// Run with: mvn test

@DisplayName("Calculator")
class CalculatorTest {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    // fresh Calculator instance before each test — prevents test coupling
    calc = new Calculator();
  }

  // === add() ===

  @Test
  @DisplayName("add: two positive numbers")
  void addTwoPositiveNumbers() {
    // GIVEN — set up objects and data
    int a = 2;
    int b = 3;

    // WHEN — call the method being tested
    int result = calc.add(a, b);

    // THEN — verify the result
    assertEquals(5, result);
  }

  @Test
  @DisplayName("add: negative numbers")
  void addNegativeNumbers() {
    // GIVEN
    int a = -4;
    int b = -6;

    // WHEN
    int result = calc.add(a, b);

    // THEN
    assertEquals(-10, result);
  }

  @Test
  @DisplayName("add: zero does not change value")
  void addZero() {
    // GIVEN
    int a = 7;

    // WHEN
    int result = calc.add(a, 0);

    // THEN
    assertEquals(7, result);
  }

  @Test
  @DisplayName("add: integer overflow wraps around")
  void addOverflow() {
    // GIVEN — MAX_VALUE + 1 wraps to MIN_VALUE in Java
    int a = Integer.MAX_VALUE;
    int b = 1;

    // WHEN
    int result = calc.add(a, b);

    // THEN
    assertEquals(Integer.MIN_VALUE, result);
  }

  // === subtract() ===

  @Test
  @DisplayName("subtract: positive result")
  void subtractPositiveResult() {
    // GIVEN
    int a = 10;
    int b = 4;

    // WHEN
    int result = calc.subtract(a, b);

    // THEN
    assertEquals(6, result);
  }

  @Test
  @DisplayName("subtract: negative result")
  void subtractNegativeResult() {
    // GIVEN
    int a = 3;
    int b = 8;

    // WHEN
    int result = calc.subtract(a, b);

    // THEN
    assertEquals(-5, result);
  }

  // === multiply() — Exercise 4.1: Written by hand (no AI) ===
  // Covers: positive, negative, zero, large numbers (overflow)
  // Note: decimal precision not applicable — multiply() uses int, not double

  @Test
  @DisplayName("multiply: two positive numbers")
  void multiplyTwoPositiveNumbers() {
    // GIVEN
    int a = 3;
    int b = 4;

    // WHEN
    int result = calc.multiply(a, b);

    // THEN
    assertEquals(12, result);
  }

  @Test
  @DisplayName("multiply: negative times positive")
  void multiplyNegativeTimesPositive() {
    // GIVEN
    int a = -3;
    int b = 5;

    // WHEN
    int result = calc.multiply(a, b);

    // THEN
    assertEquals(-15, result);
  }

  @Test
  @DisplayName("multiply: by zero returns zero")
  void multiplyByZero() {
    // GIVEN
    int a = 100;

    // WHEN
    int result = calc.multiply(a, 0);

    // THEN
    assertEquals(0, result);
  }

  @Test
  @DisplayName("multiply: large numbers cause overflow")
  void multiplyOverflow() {
    // GIVEN — MAX_VALUE * 2 overflows in Java
    int a = Integer.MAX_VALUE;
    int b = 2;

    // WHEN
    int result = calc.multiply(a, b);

    // THEN — overflow wraps around: 2147483647 * 2 = -2
    assertEquals(-2, result);
  }

  @Test
  @DisplayName("multiply: negative times negative is positive")
  void multiplyNegativeTimesNegative() {
    // GIVEN
    int a = -6;
    int b = -7;

    // WHEN
    int result = calc.multiply(a, b);

    // THEN
    assertEquals(42, result);
  }

  // === divide() ===

  @Test
  @DisplayName("divide: even division")
  void divideEvenDivision() {
    // GIVEN
    int a = 10;
    int b = 2;

    // WHEN
    int result = calc.divide(a, b);

    // THEN
    assertEquals(5, result);
  }

  @Test
  @DisplayName("divide: integer division truncates")
  void divideIntegerTruncation() {
    // GIVEN — 7 / 2 = 3 (not 3.5) because int division
    int a = 7;
    int b = 2;

    // WHEN
    int result = calc.divide(a, b);

    // THEN
    assertEquals(3, result);
  }

  @Test
  @DisplayName("divide: by zero throws ArithmeticException")
  void divideByZeroThrowsException() {
    // GIVEN
    int a = 10;
    int b = 0;

    // WHEN / THEN — assertThrows catches the exception
    assertThrows(ArithmeticException.class, () -> calc.divide(a, b));
  }
}
