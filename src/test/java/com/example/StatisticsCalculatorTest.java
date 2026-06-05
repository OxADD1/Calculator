package com.example;

// Constrained Re-Do (slide 83):
// This test was AI-generated WITH CLAUDE.md and .claude/rules/ active.
// Only fix needed: JUnit version was outdated (5.11.4 → 6.0.3).

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("StatisticsCalculator")
class StatisticsCalculatorTest {

  private StatisticsCalculator stats;

  @BeforeEach
  void setUp() {
    stats = new StatisticsCalculator();
  }

  @Nested
  @DisplayName("mean()")
  class MeanTest {

    @Test
    @DisplayName("calculates mean of integers")
    void calculatesIntegerMean() {
      double result = stats.mean(List.of(2.0, 4.0, 6.0));
      assertEquals(4.0, result, 1e-9);
    }

    @Test
    @DisplayName("calculates mean of a single value")
    void singleValue() {
      assertEquals(7.0, stats.mean(List.of(7.0)), 1e-9);
    }

    @Test
    @DisplayName("throws on null input")
    void throwsOnNull() {
      assertThrows(IllegalArgumentException.class, () -> stats.mean(null));
    }

    @Test
    @DisplayName("throws on empty list")
    void throwsOnEmpty() {
      assertThrows(IllegalArgumentException.class, () -> stats.mean(List.of()));
    }
  }

  @Nested
  @DisplayName("median()")
  class MedianTest {

    @Test
    @DisplayName("returns middle element for odd-sized list")
    void oddSizedList() {
      double result = stats.median(List.of(3.0, 1.0, 2.0));
      assertEquals(2.0, result, 1e-9);
    }

    @Test
    @DisplayName("returns average of two middle elements for even-sized list")
    void evenSizedList() {
      double result = stats.median(List.of(1.0, 2.0, 3.0, 4.0));
      assertEquals(2.5, result, 1e-9);
    }

    @Test
    @DisplayName("handles unsorted input")
    void unsortedInput() {
      double result = stats.median(List.of(5.0, 1.0, 3.0, 2.0, 4.0));
      assertEquals(3.0, result, 1e-9);
    }

    @Test
    @DisplayName("returns the value for single-element list")
    void singleElement() {
      assertEquals(42.0, stats.median(List.of(42.0)), 1e-9);
    }

    @Test
    @DisplayName("throws on empty list")
    void throwsOnEmpty() {
      assertThrows(IllegalArgumentException.class, () -> stats.median(List.of()));
    }
  }

  @Nested
  @DisplayName("mode()")
  class ModeTest {

    @Test
    @DisplayName("returns single mode")
    void singleMode() {
      List<Double> result = stats.mode(List.of(1.0, 2.0, 2.0, 3.0));
      assertEquals(List.of(2.0), result);
    }

    @Test
    @DisplayName("returns multiple modes when tied")
    void multipleModes() {
      List<Double> result = stats.mode(List.of(1.0, 1.0, 2.0, 2.0, 3.0));
      assertEquals(List.of(1.0, 2.0), result);
    }

    @Test
    @DisplayName("returns all values when all have same frequency")
    void allSameFrequency() {
      List<Double> result = stats.mode(List.of(1.0, 2.0, 3.0));
      assertEquals(List.of(1.0, 2.0, 3.0), result);
    }

    @Test
    @DisplayName("handles single-element list")
    void singleElement() {
      assertEquals(List.of(5.0), stats.mode(List.of(5.0)));
    }

    @Test
    @DisplayName("throws on null input")
    void throwsOnNull() {
      assertThrows(IllegalArgumentException.class, () -> stats.mode(null));
    }
  }

  @Nested
  @DisplayName("standardDeviation()")
  class StandardDeviationTest {

    @Test
    @DisplayName("calculates standard deviation of a dataset")
    void calculatesStdDev() {
      // Population std dev of [2, 4, 4, 4, 5, 5, 7, 9] = 2.0
      List<Double> data = List.of(2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0);
      assertEquals(2.0, stats.standardDeviation(data), 1e-9);
    }

    @Test
    @DisplayName("returns zero for identical values")
    void identicalValues() {
      assertEquals(0.0, stats.standardDeviation(List.of(5.0, 5.0, 5.0)), 1e-9);
    }

    @Test
    @DisplayName("returns zero for single value")
    void singleValue() {
      assertEquals(0.0, stats.standardDeviation(List.of(3.0)), 1e-9);
    }

    @Test
    @DisplayName("throws on empty list")
    void throwsOnEmpty() {
      assertThrows(IllegalArgumentException.class,
          () -> stats.standardDeviation(List.of()));
    }
  }
}
