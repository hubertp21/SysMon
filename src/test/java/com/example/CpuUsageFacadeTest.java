package com.example;

import com.example.cpu.CpuUsageHandler;
import com.example.cpu.CpuUsageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class CpuUsageFacadeTest {

    @Mock
    private CpuUsageHandler cpuUsageHandler;

    @InjectMocks
    private CpuUsageFacade underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetCpuUsage() {
        // given
        when(cpuUsageHandler.handleCpuUsage()).thenReturn(successfulResult());

        // when then
        assertEquals(successfulResult(), underTest.getCpuUsage());
    }

    @Test
    void shouldReturnCalculationErrorWhenUsageIsFull() {
        // given
        doThrow(new RuntimeException()).when(cpuUsageHandler).handleCpuUsage();

        // when then
        assertEquals(resultWithApplicationFlowFailure(), underTest.getCpuUsage());
    }

    private CpuUsageResult successfulResult() {
        return new CpuUsageResult(10, "");
    }

    private CpuUsageResult resultWithApplicationFlowFailure() {
        return new CpuUsageResult(-1, "Error occurred in application flow");
    }
}
