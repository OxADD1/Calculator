package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ScientificCalculator")
class ScientificCalculatorTest {

  private ScientificCalculator calc;

  @BeforeEach
  void setUp() {
    calc = new ScientificCalculator();
  }

  @Nested
  @DisplayName("power()")
  class PowerTest {

    @Test
    @DisplayName("raises base to positive exponent")
    void positiveExponent() {
      // Arrange
      double base = 2.0;
      double exponent = 3.0;

      // Act
      double result = calc.power(base, exponent);

      // Assert
      assertEquals(8.0, result, 1e-9);
    }

    @Test
    @DisplayName("any number to the power of zero is one")
    void exponentZero() {
      assertEquals(1.0, calc.power(5.0, 0.0), 1e-9);
    }

    @Test
    @DisplayName("any number to the power of one is itself")
    void exponentOne() {
      assertEquals(7.0, calc.power(7.0, 1.0), 1e-9);
    }

    @Test
    @DisplayName("negative exponent returns reciprocal")
    void negativeExponent() {
      // 2^-2 = 0.25
      assertEquals(0.25, calc.power(2.0, -2.0), 1e-9);
    }

    @Test
    @DisplayName("zero to positive exponent is zero")
    void zeroBase() {
      assertEquals(0.0, calc.power(0.0, 5.0), 1e-9);
    }

    @Test
    @DisplayName("negative base with even exponent is positive")
    void negativeBaseEvenExponent() {
      assertEquals(16.0, calc.power(-2.0, 4.0), 1e-9);
    }

    @Test
    @DisplayName("negative base with odd exponent is negative")
    void negativeBaseOddExponent() {
      assertEquals(-8.0, calc.power(-2.0, 3.0), 1e-9);
    }

    @Test
    @DisplayName("fractional exponent computes root")
    void fractionalExponent() {
      // 9^0.5 = 3.0
      assertEquals(3.0, calc.power(9.0, 0.5), 1e-9);
    }
  }

  @Nested
  @DisplayName("sqrt()")
  class SqrtTest {

    @Test
    @DisplayName("computes square root of perfect square")
    void perfectSquare() {
      assertEquals(4.0, calc.sqrt(16.0), 1e-9);
    }

    @Test
    @DisplayName("computes square root of non-perfect square")
    void nonPerfectSquare() {
      assertEquals(1.4142135623730951, calc.sqrt(2.0), 1e-9);
    }

    @Test
    @DisplayName("square root of zero is zero")
    void sqrtOfZero() {
      assertEquals(0.0, calc.sqrt(0.0), 1e-9);
    }

    @Test
    @DisplayName("square root of one is one")
    void sqrtOfOne() {
      assertEquals(1.0, calc.sqrt(1.0), 1e-9);
    }

    @Test
    @DisplayName("throws on negative input")
    void throwsOnNegative() {
      ArithmeticException ex = assertThrows(
          ArithmeticException.class, () -> calc.sqrt(-1.0));
      assertEquals("Cannot compute square root of a negative number",
          ex.getMessage());
    }
  }

  @Nested
  @DisplayName("modulo()")
  class ModuloTest {

    @Test
    @DisplayName("computes remainder of positive numbers")
    void positiveModulo() {
      assertEquals(1, calc.modulo(7, 3));
    }

    @Test
    @DisplayName("returns zero when evenly divisible")
    void evenlyDivisible() {
      assertEquals(0, calc.modulo(10, 5));
    }

    @Test
    @DisplayName("handles negative dividend")
    void negativeDividend() {
      // Java: -7 % 3 = -1
      assertEquals(-1, calc.modulo(-7, 3));
    }

    @Test
    @DisplayName("handles negative divisor")
    void negativeDivisor() {
      // Java: 7 % -3 = 1
      assertEquals(1, calc.modulo(7, -3));
    }

    @Test
    @DisplayName("modulo of zero dividend is zero")
    void zeroDividend() {
      assertEquals(0, calc.modulo(0, 5));
    }

    @Test
    @DisplayName("throws on divisor zero")
    void throwsOnDivisorZero() {
      ArithmeticException ex = assertThrows(
          ArithmeticException.class, () -> calc.modulo(10, 0));
      assertEquals("Cannot compute modulo with divisor zero",
          ex.getMessage());
    }
  }

  @Nested
  @DisplayName("abs()")
  class AbsTest {

    @Test
    @DisplayName("positive number stays positive")
    void positiveNumber() {
      assertEquals(5.0, calc.abs(5.0), 1e-9);
    }

    @Test
    @DisplayName("negative number becomes positive")
    void negativeNumber() {
      assertEquals(5.0, calc.abs(-5.0), 1e-9);
    }

    @Test
    @DisplayName("zero stays zero")
    void zero() {
      assertEquals(0.0, calc.abs(0.0), 1e-9);
    }

    @Test
    @DisplayName("handles large negative value")
    void largeNegative() {
      assertEquals(1e15, calc.abs(-1e15), 1e-9);
    }

    @Test
    @DisplayName("handles fractional values")
    void fractionalValue() {
      assertEquals(0.123, calc.abs(-0.123), 1e-9);
    }
  }

  @Nested
  @DisplayName("factorial()")
  class FactorialTest {

    @Test
    @DisplayName("factorial of zero is one")
    void factorialOfZero() {
      assertEquals(1L, calc.factorial(0));
    }

    @Test
    @DisplayName("factorial of one is one")
    void factorialOfOne() {
      assertEquals(1L, calc.factorial(1));
    }

    @Test
    @DisplayName("factorial of five is 120")
    void factorialOfFive() {
      assertEquals(120L, calc.factorial(5));
    }

    @Test
    @DisplayName("factorial of ten is 3628800")
    void factorialOfTen() {
      assertEquals(3_628_800L, calc.factorial(10));
    }

    @Test
    @DisplayName("factorial of twenty")
    void factorialOfTwenty() {
      assertEquals(2_432_902_008_176_640_000L, calc.factorial(20));
    }

    @Test
    @DisplayName("throws on negative input")
    void throwsOnNegative() {
      ArithmeticException ex = assertThrows(
          ArithmeticException.class, () -> calc.factorial(-1));
      assertEquals("Factorial is not defined for negative numbers",
          ex.getMessage());
    }
  }

  @Nested
  @DisplayName("percentage()")
  class PercentageTest {

    @Test
    @DisplayName("calculates simple percentage")
    void simplePercentage() {
      // 25 / 200 * 100 = 12.5%
      assertEquals(12.5, calc.percentage(25.0, 200.0), 1e-9);
    }

    @Test
    @DisplayName("full value is 100 percent")
    void fullValue() {
      assertEquals(100.0, calc.percentage(50.0, 50.0), 1e-9);
    }

    @Test
    @DisplayName("zero value is zero percent")
    void zeroValue() {
      assertEquals(0.0, calc.percentage(0.0, 100.0), 1e-9);
    }

    @Test
    @DisplayName("value greater than total exceeds 100 percent")
    void exceedsHundred() {
      assertEquals(200.0, calc.percentage(200.0, 100.0), 1e-9);
    }

    @Test
    @DisplayName("handles negative value")
    void negativeValue() {
      assertEquals(-50.0, calc.percentage(-25.0, 50.0), 1e-9);
    }

    @Test
    @DisplayName("throws on total zero")
    void throwsOnTotalZero() {
      ArithmeticException ex = assertThrows(
          ArithmeticException.class, () -> calc.percentage(10.0, 0.0));
      assertEquals("Cannot compute percentage with total zero",
          ex.getMessage());
    }
  }

  // === Exercise 4.2: Weak tests identified during critical review ===
  // The following "inherited methods" tests are WEAK — they test Java's inheritance
  // mechanism, not our code. add() and divide() are already tested in CalculatorTest.
  // These tests would pass even if ScientificCalculator had no logic at all.

  @Nested
  @DisplayName("inherited Calculator methods")
  class InheritedMethodsTest {

    @Test
    @DisplayName("add still works through inheritance")
    void addWorksViaInheritance() {
      assertEquals(7, calc.add(3, 4));
    }

    @Test
    @DisplayName("divide still works through inheritance")
    void divideWorksViaInheritance() {
      assertEquals(5, calc.divide(10, 2));
    }
  }

  // === Exercise 4.2: Missing edge case — written by hand ===
  // Claude Code missed: factorial(21) overflows long, but no test catches it.
  // factorial(20) = 2_432_902_008_176_640_000 fits in long,
  // factorial(21) silently overflows — the method returns a wrong value.

  @Nested
  @DisplayName("factorial() — missing edge cases (hand-written)")
  class FactorialEdgeCaseTest {

    @Test
    @DisplayName("factorial(21) overflows long — returns wrong result")
    void factorialOverflowsLong() {
      // GIVEN — factorial(21) is too large for long
      long result = calc.factorial(21);

      // THEN — result is wrong due to overflow (not the real factorial)
      // Real factorial(21) = 51_090_942_171_709_440_000 > Long.MAX_VALUE
      // This test documents that the method silently overflows
      assertTrue(result < 0, "factorial(21) should overflow and become negative");
    }
  }
}
