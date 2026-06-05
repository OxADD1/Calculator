package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CalculationHistory")
class CalculationHistoryTest {

  private CalculationHistory history;

  @BeforeEach
  void setUp() {
    history = new CalculationHistory();
  }

  @Nested
  @DisplayName("record()")
  class RecordTest {

    @Test
    @DisplayName("records a single entry with correct format")
    void recordsSingleEntry() {
      // Arrange & Act
      history.record("2 + 3", "5");

      // Assert
      assertEquals(1, history.size());
      assertEquals("2 + 3 = 5", history.lastEntry());
    }

    @Test
    @DisplayName("records multiple entries in order")
    void recordsMultipleEntries() {
      // Arrange & Act
      history.record("1 + 1", "2");
      history.record("3 * 4", "12");
      history.record("10 / 2", "5");

      // Assert
      List<String> entries = history.getEntries();
      assertEquals(3, entries.size());
      assertEquals("1 + 1 = 2", entries.get(0));
      assertEquals("3 * 4 = 12", entries.get(1));
      assertEquals("10 / 2 = 5", entries.get(2));
    }
  }

  @Nested
  @DisplayName("getEntries()")
  class GetEntriesTest {

    @Test
    @DisplayName("returns empty list when no entries recorded")
    void emptyByDefault() {
      assertTrue(history.getEntries().isEmpty());
    }

    @Test
    @DisplayName("returns unmodifiable list")
    void returnsUnmodifiableList() {
      history.record("1 + 1", "2");
      List<String> entries = history.getEntries();

      assertThrows(UnsupportedOperationException.class,
          () -> entries.add("tampered"));
    }

    @Test
    @DisplayName("reflects entries added after retrieval")
    void reflectsNewEntries() {
      // Arrange — get entries before adding
      List<String> entries = history.getEntries();
      assertEquals(0, entries.size());

      // Act — add entry after getting the view
      history.record("5 - 2", "3");

      // Assert — unmodifiable view reflects the change
      assertEquals(1, entries.size());
    }
  }

  @Nested
  @DisplayName("size()")
  class SizeTest {

    @Test
    @DisplayName("initially zero")
    void initiallyZero() {
      assertEquals(0, history.size());
    }

    @Test
    @DisplayName("increases with each recorded entry")
    void increasesWithRecords() {
      history.record("a", "b");
      assertEquals(1, history.size());

      history.record("c", "d");
      assertEquals(2, history.size());
    }

    @Test
    @DisplayName("resets to zero after clear")
    void resetsAfterClear() {
      history.record("a", "b");
      history.record("c", "d");
      history.clear();

      assertEquals(0, history.size());
    }
  }

  @Nested
  @DisplayName("clear()")
  class ClearTest {

    @Test
    @DisplayName("removes all entries")
    void removesAllEntries() {
      // Arrange
      history.record("1 + 1", "2");
      history.record("2 + 2", "4");

      // Act
      history.clear();

      // Assert
      assertTrue(history.getEntries().isEmpty());
      assertNull(history.lastEntry());
    }

    @Test
    @DisplayName("clearing empty history has no effect")
    void clearEmptyIsNoOp() {
      history.clear();
      assertEquals(0, history.size());
    }

    @Test
    @DisplayName("can record again after clearing")
    void recordAfterClear() {
      // Arrange
      history.record("old", "entry");
      history.clear();

      // Act
      history.record("new", "entry");

      // Assert
      assertEquals(1, history.size());
      assertEquals("new = entry", history.lastEntry());
    }
  }

  @Nested
  @DisplayName("lastEntry()")
  class LastEntryTest {

    @Test
    @DisplayName("returns null when history is empty")
    void returnsNullWhenEmpty() {
      assertNull(history.lastEntry());
    }

    @Test
    @DisplayName("returns the most recently recorded entry")
    void returnsMostRecent() {
      history.record("1 + 1", "2");
      history.record("9 * 9", "81");

      assertEquals("9 * 9 = 81", history.lastEntry());
    }

    @Test
    @DisplayName("returns only entry when one recorded")
    void singleEntry() {
      history.record("7 + 3", "10");
      assertEquals("7 + 3 = 10", history.lastEntry());
    }
  }
}
