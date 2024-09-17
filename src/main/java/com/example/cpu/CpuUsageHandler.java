package com.example.cpu;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CpuUsageHandler {

    @Inject
    private CpuUsageCalculator calculator;

    public CpuUsageResult handleCpuUsage() {
        int cpuUsage = calculator.calculateCpuUsage();

        if (cpuUsage == 100 || cpuUsage == -1) {
            return resultWithCalculationError(cpuUsage);
        }

        if (cpuUsage > 75) {
            return resultWithHighUsageWarning(cpuUsage);
        }

        return new CpuUsageResult(cpuUsage, "");
    }

    private CpuUsageResult resultWithCalculationError(int cpuUsage) {
        return new CpuUsageResult(cpuUsage, "Error caught when calculating cpu usage");
    }

    private CpuUsageResult resultWithHighUsageWarning(int cpuUsage) {
        return new CpuUsageResult(cpuUsage, "Cpu usage is very high");
    }
}
