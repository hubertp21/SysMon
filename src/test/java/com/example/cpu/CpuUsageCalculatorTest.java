package com.example.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CpuUsageCalculatorTest {

    @Mock
    private CpuUsageProvider cpuUsageProvider;

    @InjectMocks
    private CpuUsageCalculator underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateCpuUsage() {
        // given
        when(cpuUsageProvider.getCpuUsage()).thenReturn(1);

        // when then
        assertEquals(1, underTest.calculateCpuUsage());
    }

    @Test
    void shouldReturn100IfExceptionIsCaught() {
        // given
        doThrow(new RuntimeException()).when(cpuUsageProvider).getCpuUsage();

        // when then
        assertEquals(100, underTest.calculateCpuUsage());
    }
}
