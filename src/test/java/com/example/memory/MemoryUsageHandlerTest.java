package com.example.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MemoryUsageHandlerTest {

    @Mock
    private MemoryUsageCalculator memoryUsageCalculator;

    @InjectMocks
    private MemoryUsageHandler underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSuccessfulResult() {
        // given
        when(memoryUsageCalculator.calculateMemoryUsage()).thenReturn(10);

        // when then
        assertEquals(successfulResult(), underTest.handleMemoryUsage());
    }

    @Test
    void shouldReturnCalculationErrorWhenUsageIsFull() {
        // given
        when(memoryUsageCalculator.calculateMemoryUsage()).thenReturn(100);

        // when then
        assertEquals(resultWithFullUsageCalculationError(), underTest.handleMemoryUsage());
    }

    @Test
    void shouldReturnCalculationErrorWhenUsageIsNegative() {
        // given
        when(memoryUsageCalculator.calculateMemoryUsage()).thenReturn(-1);

        // when then
        assertEquals(resultWithNegativeValueCalculationError(), underTest.handleMemoryUsage());
    }

    @Test
    void shouldReturnHighUsageWarningWhenUsageIsVeryHigh() {
        // given
        when(memoryUsageCalculator.calculateMemoryUsage()).thenReturn(90);

        // when then
        assertEquals(resultWithHighUsageWarning(), underTest.handleMemoryUsage());
    }


    private MemoryUsageResult successfulResult() {
        return new MemoryUsageResult(10, "");
    }

    private MemoryUsageResult resultWithFullUsageCalculationError() {
        return new MemoryUsageResult(100, "Error caught when calculating memory usage");
    }

    private MemoryUsageResult resultWithNegativeValueCalculationError() {
        return new MemoryUsageResult(-1, "Error caught when calculating memory usage");
    }

    private MemoryUsageResult resultWithHighUsageWarning() {
        return new MemoryUsageResult(90, "Memory usage is very high");
    }
}
