package com.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Calculator — readFile()")
class CalculatorReadFileTest {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  /** Tests that readFile returns a non-null result for an existing file. */
  @Test
  @DisplayName("returns non-null for existing file")
  void returnsNonNullForExistingFile(@TempDir Path tempDir)
      throws Exception {
    // Arrange
    Path file = tempDir.resolve("test.txt");
    Files.writeString(file, "hello world");

    // Act
    String result = calc.readFile(file.toString());

    // Assert
    assertNotNull(result);
  }

  /** Tests that readFile throws FileNotFoundException for missing file. */
  @Test
  @DisplayName("throws FileNotFoundException for missing file")
  void throwsForMissingFile() {
    // Arrange
    String missingPath = "/nonexistent/path.txt";

    // Act & Assert
    assertThrows(FileNotFoundException.class,
        () -> calc.readFile(missingPath));
  }
}
