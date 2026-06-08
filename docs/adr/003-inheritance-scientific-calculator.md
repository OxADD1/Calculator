# ADR-003: ScientificCalculator extends Calculator

## Status
Accepted

## Context
The project needs advanced math operations (power, sqrt, modulo) in addition to basic arithmetic (add, subtract, multiply, divide).

## Decision
Create `ScientificCalculator` as a subclass of `Calculator` using inheritance.

## Consequences
- ScientificCalculator reuses all basic operations from Calculator
- InteractiveCalculator only needs one dependency instead of two
- New scientific operations can be added without touching the base Calculator
