package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JsonCalculator")
class JsonCalculatorTest {

  /** Tests that main loads calculation.json and executes all operations. */
  @Test
  @DisplayName("main processes all operations from JSON resource")
  void mainProcessesOperations() {
    // Arrange
    String[] args = new String[]{};

    // Act & Assert
    assertDoesNotThrow(() -> JsonCalculator.main(args));
  }
}
