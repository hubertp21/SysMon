package com.example.cpu;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CpuUsageCalculator {

    @Inject
    private CpuUsageProvider cpuUsageProvider;

    public int calculateCpuUsage() {
        try {
            return cpuUsageProvider.getCpuUsage();
        } catch (Exception exception) {
            return 100;
        }
    }
}
