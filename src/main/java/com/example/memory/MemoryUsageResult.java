package com.example.memory;

public record MemoryUsageResult(int usage, String error) {

    @Override
    public String toString() {
        return "memory usage= " + usage +
                ", error= " + error;
    }
}
