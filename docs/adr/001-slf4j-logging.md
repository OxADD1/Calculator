# ADR-001: Use SLF4J + Logback instead of System.out.println

## Status
Accepted

## Context
The project originally used `System.out.println()` for all output. This makes it impossible to control log levels, filter output, or configure logging behavior without changing code.

## Decision
Replace `System.out.println()` with SLF4J as the logging facade and Logback as the implementation.

## Consequences
- Log levels (DEBUG, INFO, WARN, ERROR) can be configured without code changes
- Output format and destination are configurable via `logback.xml`
- PMD and SpotBugs no longer flag logging violations
- Added two dependencies: `slf4j-api` and `logback-classic`
