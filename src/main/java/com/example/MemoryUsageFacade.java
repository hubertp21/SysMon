package com.example;

import com.example.memory.MemoryUsageHandler;
import com.example.memory.MemoryUsageResult;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MemoryUsageFacade {

    @Inject
    private MemoryUsageHandler memoryUsageHandler;

    public MemoryUsageResult getMemoryUsage() {
        try {
            return memoryUsageHandler.handleMemoryUsage();
        } catch (Exception exception) {
            return resultWithApplicationFlowFailure();
        }
    }

    private MemoryUsageResult resultWithApplicationFlowFailure() {
        return new MemoryUsageResult(-1, "Error occurred in application flow");
    }
}
