package com.example.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class MemoryUsageCalculatorTest {

    @Mock
    private MemoryUsageProvider memoryUsageProvider;

    @InjectMocks
    private MemoryUsageCalculator underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateCpuUsage() {
        // given
        when(memoryUsageProvider.getMemoryUsage()).thenReturn(1);

        // when then
        assertEquals(1, underTest.calculateMemoryUsage());
    }

    @Test
    void shouldReturn100IfExceptionIsCaught() {
        // given
        doThrow(new RuntimeException()).when(memoryUsageProvider).getMemoryUsage();

        // when then
        assertEquals(100, underTest.calculateMemoryUsage());
    }
}
