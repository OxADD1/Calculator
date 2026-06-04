package com.example;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Run with: mvn exec:java -Dexec.mainClass="com.example.JsonCalculator"
// Reads operations from: src/main/resources/calculation.json

/** Parses calculation operations from JSON and executes them. */
public class JsonCalculator {

  private static final Logger LOG = LoggerFactory.getLogger(JsonCalculator.class);

  /** Reads operations from JSON resource and prints results. */
  public static void main(String[] args) {
    Gson gson = new Gson();
    Reader reader = new InputStreamReader(
        JsonCalculator.class.getResourceAsStream("/calculation.json"),
        StandardCharsets.UTF_8
    );

    Operation[] operations = gson.fromJson(reader, Operation[].class);
    Calculator calc = new Calculator();

    for (Operation op : operations) {
      int result;
      switch (op.operation) {
        case "add":
          result = calc.add(op.operandA, op.operandB);
          break;
        case "subtract":
          result = calc.subtract(op.operandA, op.operandB);
          break;
        case "multiply":
          result = calc.multiply(op.operandA, op.operandB);
          break;
        case "divide":
          result = calc.divide(op.operandA, op.operandB);
          break;
        default:
          LOG.warn("Unknown operation: {}", op.operation);
          continue;
      }
      LOG.info("{} {} {} = {}", op.operandA, op.operation, op.operandB, result);
    }
  }
}
