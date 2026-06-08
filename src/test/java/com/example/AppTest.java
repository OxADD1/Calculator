package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App")
class AppTest {

  /** Tests that main runs the calculator demo without errors. */
  @Test
  @DisplayName("main runs calculator demo without errors")
  void mainRunsDemo() {
    // Arrange
    String[] args = new String[]{};

    // Act & Assert
    assertDoesNotThrow(() -> App.main(args));
  }
}
