package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Run with: mvn compile exec:java -Dexec.mainClass="com.example.App"

/** Main application entry point. */
public class App {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);

  /** Runs the calculator demo. */
  public static void main(String[] args) {
    Calculator calc = new Calculator();
    LOG.info("2 + 3 = {}", calc.add(2, 3));
    LOG.info("10 - 4 = {}", calc.subtract(10, 4));
    LOG.info("6 * 7 = {}", calc.multiply(6, 7));
  }
}
