package com.example;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;

// Run with: mvn exec:java -Dexec.mainClass="com.example.JsonCalculator"
// Reads operations from: src/main/resources/calculation.json
public class JsonCalculator {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(
            JsonCalculator.class.getResourceAsStream("/calculation.json")
        );

        Operation[] operations = gson.fromJson(reader, Operation[].class);
        Calculator calc = new Calculator();

        for (Operation op : operations) {
            int result;
            switch (op.operation) {
                case "add":      result = calc.add(op.a, op.b); break;
                case "subtract": result = calc.subtract(op.a, op.b); break;
                case "multiply": result = calc.multiply(op.a, op.b); break;
                case "divide":   result = calc.divide(op.a, op.b); break;
                default:
                    System.out.println("Unknown operation: " + op.operation);
                    continue;
            }
            System.out.println(op.a + " " + op.operation + " " + op.b + " = " + result);
        }
    }
}

class Operation {
    String operation;
    int a;
    int b;
}
