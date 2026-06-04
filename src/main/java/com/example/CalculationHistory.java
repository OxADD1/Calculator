package com.example;

// Vibe Coding Experiment (slide 78):
// This class was AI-generated without constraints (no CLAUDE.md, no rules).
// Check command: mvn checkstyle:check pmd:check spotbugs:check
// Result: 1 PMD violation found — "UselessParentheses" in ScientificCalculator.percentage().
// Fix: removed unnecessary parentheses around (value / total) → value / total * 100.0

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Stores a history of calculations performed during a session. */
public class CalculationHistory {

  private final List<String> entries = new ArrayList<>();

  /** Records a calculation entry. */
  public void record(String expression, String result) {
    entries.add(expression + " = " + result);
  }

  /** Returns an unmodifiable view of all history entries. */
  public List<String> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  /** Returns the number of entries. */
  public int size() {
    return entries.size();
  }

  /** Clears all history. */
  public void clear() {
    entries.clear();
  }

  /** Returns the last entry, or null if empty. */
  public String lastEntry() {
    if (entries.isEmpty()) {
      return null;
    }
    return entries.get(entries.size() - 1);
  }
}
