package com.example.cpu;

import jakarta.inject.Singleton;

@Singleton
public class CpuUsageNative implements CpuUsageProvider {

    // set it with your location
    static {
        System.load("C:\\Users\\huber\\IdeaProjects\\SysMon\\target\\CpuMonitor.dll");
    }

    @Override
    public native int getCpuUsage();
}
