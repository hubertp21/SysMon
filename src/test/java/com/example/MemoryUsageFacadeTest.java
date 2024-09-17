package com.example;

import com.example.memory.MemoryUsageHandler;
import com.example.memory.MemoryUsageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class MemoryUsageFacadeTest {

    @Mock
    private MemoryUsageHandler memoryUsageHandler;

    @InjectMocks
    private MemoryUsageFacade underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetCpuUsage() {
        // given
        when(memoryUsageHandler.handleMemoryUsage()).thenReturn(successfulResult());

        // when then
        assertEquals(successfulResult(), underTest.getMemoryUsage());
    }

    @Test
    void shouldReturnCalculationErrorWhenUsageIsFull() {
        // given
        doThrow(new RuntimeException()).when(memoryUsageHandler).handleMemoryUsage();

        // when then
        assertEquals(resultWithApplicationFlowFailure(), underTest.getMemoryUsage());
    }

    private MemoryUsageResult successfulResult() {
        return new MemoryUsageResult(10, "");
    }

    private MemoryUsageResult resultWithApplicationFlowFailure() {
        return new MemoryUsageResult(-1, "Error occurred in application flow");
    }
}
