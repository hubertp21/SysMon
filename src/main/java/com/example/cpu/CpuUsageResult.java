package com.example.cpu;

public record CpuUsageResult(int usage, String error) {

    @Override
    public String toString() {
        return "cpu usage= " + usage +
                ", error= " + error;
    }
}
