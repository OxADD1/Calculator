package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Calculator — describe()")
class CalculatorDescribeTest {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  @DisplayName("describes a string object")
  void describesString() {
    assertEquals("hello", calc.describe("hello"));
  }

  @Test
  @DisplayName("describes an integer object")
  void describesInteger() {
    assertEquals("42", calc.describe(42));
  }

  @Test
  @DisplayName("returns 'null' for null input")
  void returnsNullStringForNull() {
    assertEquals("null", calc.describe(null));
  }

  @Test
  @DisplayName("describes a boolean object")
  void describesBoolean() {
    assertEquals("true", calc.describe(Boolean.TRUE));
  }

  @Test
  @DisplayName("describes an empty string")
  void describesEmptyString() {
    assertEquals("", calc.describe(""));
  }
}
