package com.example;

// Constrained Re-Do (slide 83):
// This class was AI-generated WITH CLAUDE.md and .claude/rules/ active.
// Check command: mvn checkstyle:check pmd:check spotbugs:check
// Result: 0 violations. Only fix needed: JUnit version was outdated (5.11.4 → 6.0.3).

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides statistical calculations on numeric datasets.
 *
 * <p>Supports mean, median, mode, and standard deviation computations
 * on lists of numeric values.
 */
public class StatisticsCalculator {

  /**
   * Calculates the arithmetic mean of a dataset.
   *
   * @param values the dataset
   * @return the mean value
   * @throws IllegalArgumentException if values is null or empty
   */
  public double mean(List<Double> values) {
    validateNotEmpty(values);
    double sum = 0.0;
    for (double value : values) {
      sum += value;
    }
    return sum / values.size();
  }

  /**
   * Calculates the median of a dataset.
   *
   * <p>For an odd-sized list, returns the middle element.
   * For an even-sized list, returns the average of the two middle elements.
   *
   * @param values the dataset
   * @return the median value
   * @throws IllegalArgumentException if values is null or empty
   */
  public double median(List<Double> values) {
    validateNotEmpty(values);
    List<Double> sorted = new ArrayList<>(values);
    Collections.sort(sorted);
    int size = sorted.size();
    if (size % 2 == 1) {
      return sorted.get(size / 2);
    }
    return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
  }

  /**
   * Finds the mode(s) of a dataset.
   *
   * <p>Returns all values that appear with the highest frequency.
   * If all values appear equally often, all values are modes.
   *
   * @param values the dataset
   * @return a list of mode values sorted in ascending order
   * @throws IllegalArgumentException if values is null or empty
   */
  public List<Double> mode(List<Double> values) {
    validateNotEmpty(values);
    Map<Double, Integer> frequency = new HashMap<>();
    for (double value : values) {
      frequency.merge(value, 1, Integer::sum);
    }
    int maxFreq = Collections.max(frequency.values());
    List<Double> modes = new ArrayList<>();
    for (Map.Entry<Double, Integer> entry : frequency.entrySet()) {
      if (entry.getValue() == maxFreq) {
        modes.add(entry.getKey());
      }
    }
    Collections.sort(modes);
    return modes;
  }

  /**
   * Calculates the population standard deviation of a dataset.
   *
   * @param values the dataset
   * @return the standard deviation
   * @throws IllegalArgumentException if values is null or empty
   */
  public double standardDeviation(List<Double> values) {
    validateNotEmpty(values);
    double avg = mean(values);
    double sumSquaredDiffs = 0.0;
    for (double value : values) {
      sumSquaredDiffs += (value - avg) * (value - avg);
    }
    return Math.sqrt(sumSquaredDiffs / values.size());
  }

  private void validateNotEmpty(List<Double> values) {
    if (values == null || values.isEmpty()) {
      throw new IllegalArgumentException("Dataset must not be null or empty");
    }
  }
}
