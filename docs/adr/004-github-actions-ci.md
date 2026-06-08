# ADR-004: Use GitHub Actions for CI instead of Jenkins

## Status
Accepted

## Context
The project needs automated builds and quality checks on every push. Jenkins requires a separate server to run. GitHub Actions is built into GitHub and free for public repositories.

## Decision
Use GitHub Actions as the CI pipeline. A Jenkinsfile is kept in the repo as documentation of the pipeline stages.

## Consequences
- No external server needed — runs directly on GitHub
- Green checks visible on every PR and commit on main
- Pipeline runs: build, test, CheckStyle, PMD, SpotBugs, JaCoCo
- Jenkinsfile serves as additional evidence for the Architecture Checklist
