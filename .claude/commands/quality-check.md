Run the full quality pipeline:
1. mvn clean compile
2. mvn checkstyle:check
3. mvn pmd:check
4. mvn spotbugs:check
Report violations grouped by tool.
Suggest fixes for each violation.
