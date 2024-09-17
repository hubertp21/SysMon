package com.example;

import com.example.cpu.CpuUsageHandler;
import com.example.cpu.CpuUsageResult;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CpuUsageFacade {

    @Inject
    private CpuUsageHandler cpuUsageHandler;

    public CpuUsageResult getCpuUsage() {
        try {
            return cpuUsageHandler.handleCpuUsage();
        } catch (Exception exception) {
            return resultWithApplicationFlowFailure();
        }
    }

    private CpuUsageResult resultWithApplicationFlowFailure() {
        return new CpuUsageResult(-1, "Error occurred in application flow");
    }
}
