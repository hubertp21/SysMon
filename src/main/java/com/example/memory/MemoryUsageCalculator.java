package com.example.memory;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MemoryUsageCalculator {

    @Inject
    private MemoryUsageProvider memoryUsageProvider;

    public int calculateMemoryUsage() {
        try {
            return memoryUsageProvider.getMemoryUsage();
        } catch (Exception exception) {
            return 100;
        }
    }
}
