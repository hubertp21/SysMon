package com.example.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CpuUsageHandlerTest {

    @Mock
    private CpuUsageCalculator cpuUsageCalculator;

    @InjectMocks
    private CpuUsageHandler underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSuccessfulResult() {
        // given
        when(cpuUsageCalculator.calculateCpuUsage()).thenReturn(10);

        // when then
        assertEquals(successfulResult(), underTest.handleCpuUsage());
    }

    @Test
    void shouldReturnCalculationErrorWhenUsageIsFull() {
        // given
        when(cpuUsageCalculator.calculateCpuUsage()).thenReturn(100);

        // when then
        assertEquals(resultWithFullUsageCalculationError(), underTest.handleCpuUsage());
    }

    @Test
    void shouldReturnCalculationErrorWhenUsageIsNegative() {
        // given
        when(cpuUsageCalculator.calculateCpuUsage()).thenReturn(-1);

        // when then
        assertEquals(resultWithNegativeValueCalculationError(), underTest.handleCpuUsage());
    }

    @Test
    void shouldReturnHighUsageWarningWhenUsageIsVeryHigh() {
        // given
        when(cpuUsageCalculator.calculateCpuUsage()).thenReturn(85);

        // when then
        assertEquals(resultWithHighUsageWarning(), underTest.handleCpuUsage());
    }


    private CpuUsageResult successfulResult() {
        return new CpuUsageResult(10, "");
    }

    private CpuUsageResult resultWithFullUsageCalculationError() {
        return new CpuUsageResult(100, "Error caught when calculating cpu usage");
    }

    private CpuUsageResult resultWithNegativeValueCalculationError() {
        return new CpuUsageResult(-1, "Error caught when calculating cpu usage");
    }

    private CpuUsageResult resultWithHighUsageWarning() {
        return new CpuUsageResult(85, "Cpu usage is very high");
    }
}
