package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Module 4: Mockito — a mocking framework for unit tests.
// Mockito creates fake objects (mocks) so you can test a class in isolation
// without real dependencies (database, API, service).
// Key concepts: mock() = create fake, verify() = check method was called,
// when().thenReturn() = control what the fake returns (stubbing).
// Run with: mvn test

@DisplayName("CalculationHistory (Mockito)")
class CalculationHistoryMockTest {

  @Test
  @DisplayName("verify: record() is called with correct arguments")
  void verifyRecordIsCalled() {
    // GIVEN — create a fake history
    CalculationHistory history = mock(CalculationHistory.class);

    // WHEN — use the mock as if it were real
    history.record("2 + 3", "5");

    // THEN — verify the right method was called
    verify(history).record("2 + 3", "5");
  }

  @Test
  @DisplayName("stubbing: when().thenReturn() controls mock behavior")
  void stubbingWithWhenThenReturn() {
    // GIVEN — fake the history service
    CalculationHistory history = mock(CalculationHistory.class);
    when(history.size()).thenReturn(42);
    when(history.lastEntry()).thenReturn("10 * 5 = 50");

    // WHEN — call stubbed methods
    int size = history.size();
    String last = history.lastEntry();

    // THEN — mock returns the values we configured
    assertEquals(42, size);
    assertEquals("10 * 5 = 50", last);
  }

  @Test
  @DisplayName("stubbing: getEntries() returns fake list")
  void stubbingGetEntries() {
    // GIVEN — fake history with predefined entries
    CalculationHistory history = mock(CalculationHistory.class);
    when(history.getEntries()).thenReturn(List.of("1 + 1 = 2", "3 * 4 = 12"));

    // WHEN
    List<String> entries = history.getEntries();

    // THEN
    assertEquals(2, entries.size());
    assertEquals("1 + 1 = 2", entries.get(0));
  }
}
