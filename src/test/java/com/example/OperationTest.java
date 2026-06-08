package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Operation")
class OperationTest {

  /** Tests that Gson correctly populates all fields from JSON. */
  @Test
  @DisplayName("fields are populated by Gson deserialization")
  void gsonDeserialization() {
    // Arrange
    String json =
        "{\"operation\":\"add\",\"operandA\":5,\"operandB\":3}";
    Gson gson = new Gson();

    // Act
    Operation op = gson.fromJson(json, Operation.class);

    // Assert
    assertEquals("add", op.operation);
    assertEquals(5, op.operandA);
    assertEquals(3, op.operandB);
  }

  /** Tests that fields can be set and read directly. */
  @Test
  @DisplayName("fields can be set directly")
  void directFieldAccess() {
    // Arrange
    Operation op = new Operation();

    // Act
    op.operation = "multiply";
    op.operandA = 6;
    op.operandB = 7;

    // Assert
    assertEquals("multiply", op.operation);
    assertEquals(6, op.operandA);
    assertEquals(7, op.operandB);
  }
}
