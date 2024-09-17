package com.example.memory;

import jakarta.inject.Singleton;

@Singleton
public class MemoryUsageNative implements MemoryUsageProvider {

    // set it with your location
    static {
        System.load("C:\\Users\\huber\\IdeaProjects\\SysMon\\target\\MemoryMonitor.dll");
    }

    @Override
    public native int getMemoryUsage();
}
