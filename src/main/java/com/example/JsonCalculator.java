package com.example;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonCalculator {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(
            JsonCalculator.class.getResourceAsStream("/calculation.json")
        );

        Operation op = gson.fromJson(reader, Operation.class);
        Calculator calc = new Calculator();

        int result;
        switch (op.operation) {
            case "add":      result = calc.add(op.a, op.b); break;
            case "subtract": result = calc.subtract(op.a, op.b); break;
            case "multiply": result = calc.multiply(op.a, op.b); break;
            default:
                System.out.println("Unknown operation: " + op.operation);
                return;
        }

        System.out.println(op.a + " " + op.operation + " " + op.b + " = " + result);
    }
}

class Operation {
    String operation;
    int a;
    int b;
}
