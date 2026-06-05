# CLAUDE.md — project root

## Coding rules
- Ask questions before implementing. If multiple interpretations exist, present them — do not pick silently.
- State success criteria before writing code. Make them verifiable (tests, checks, examples).
- Prefer the simplest solution that meets the requirement. No speculative abstractions.
- Touch only what the request needs. No drive-by refactors. Match existing style.

## Project
Java 21 Maven project: Calculator
Arithmetic calculator with basic, scientific, JSON-based, and interactive REPL modes.
Package: com.example | Entry point: App.java

## Architecture
- Calculator — basic arithmetic (add, subtract, multiply, divide)
- ScientificCalculator extends Calculator — power, sqrt, modulo, abs, factorial, percentage
- JsonCalculator — reads operations from resources/calculation.json via Gson
- InteractiveCalculator — REPL with history support
- CalculationHistory — stores session calculation history
- Operation — JSON model (Gson reflection)

## Coding Standards
- Google Java Style Guide (2-space indent, google_checks.xml)
- SLF4J + Logback for all logging: `private static final Logger LOG = LoggerFactory.getLogger(ClassName.class);`
- All public methods must have Javadoc
- Comments in English
- Catch specific exceptions, log with context: LOG.error("message", e)
- JUnit 5 with @DisplayName on every test
- Tests follow AAA pattern (Arrange, Act, Assert)

## Forbidden Patterns
- NEVER use System.out.println() — use LOG.info/warn/error
- NEVER use e.printStackTrace() — use LOG.error("message", e)
- NEVER use wildcard imports (e.g. import java.util.*)
- NEVER suppress warnings without justification
- NEVER use String comparison with == — use .equals()
- NEVER leave empty catch blocks
- NEVER place test classes or properties in src/main/java
- NEVER commit generated files, IDE config (.idea/, .vscode/), or log files

## Dependencies
- Gson 2.13.2 — JSON parsing
- SLF4J 2.0.17 + Logback 1.5.32 — logging

## Quality Gates
Run before every commit:
```
mvn clean compile checkstyle:check pmd:check spotbugs:check
```
- CheckStyle: google_checks.xml, fails on warnings
- PMD: fails on violations
- SpotBugs: bytecode analysis with spotbugs-exclude.xml
- Zero violations policy — all three tools must pass
- mvn clean install must succeed without errors

## Git Workflow
- Feature branch per task, never commit directly to main
- Iterative commits: generate → review → fix → commit
- Meaningful commit messages describing the change
