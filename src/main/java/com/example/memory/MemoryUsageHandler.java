package com.example.memory;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MemoryUsageHandler {

    @Inject
    private MemoryUsageCalculator calculator;

    public MemoryUsageResult handleMemoryUsage() {
        int memoryUsage = calculator.calculateMemoryUsage();

        if (memoryUsage == 100 || memoryUsage == -1) {
            return resultWithCalculationError(memoryUsage);
        }

        if (memoryUsage > 85) {
            return resultWithHighUsageWarning(memoryUsage);
        }

        return new MemoryUsageResult(memoryUsage, "");
    }

    private MemoryUsageResult resultWithCalculationError(int memoryUsage) {
        return new MemoryUsageResult(memoryUsage, "Error caught when calculating memory usage");
    }

    private MemoryUsageResult resultWithHighUsageWarning(int memoryUsage) {
        return new MemoryUsageResult(memoryUsage, "Memory usage is very high");
    }
}
