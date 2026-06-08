# ADR-002: Use Gson for JSON parsing

## Status
Accepted

## Context
The `JsonCalculator` needs to read operations from a JSON file. Java has no built-in JSON parser, so a library is required.

## Decision
Use Google Gson for JSON deserialization.

## Consequences
- Simple API: one call to deserialize JSON into `Operation[]`
- Lightweight library with no transitive dependencies
- No annotation or configuration required — maps JSON fields to Java fields by name
