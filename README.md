
# CPU and Memory Usage CLI Application

This is a Java-based command-line interface (CLI) application that calculates CPU and memory usage. The application is built using the Micronaut framework and integrates C code with Java through JNI (Java Native Interface) and native methods. The project is structured into modules for CPU and memory, which communicate with the main application through facades.

## Features

- **CPU Usage Calculation**: The `cpu` command calculates and returns the current CPU usage.
- **Memory Usage Calculation**: The `mem` command calculates and returns the current memory usage.
- **Native Integration**: The application uses C code for calculating system metrics, integrated with Java through JNI.

## Project Structure

- **Main Application**: The core application is built using Micronaut with support for command-line argument parsing through Picocli.
- **CPU Module**: A dedicated module for calculating CPU usage, written in C, with JNI bindings to Java.
- **Memory Module**: A dedicated module for calculating memory usage, also written in C, with JNI bindings to Java.
- **Facades**: The CPU and memory modules interact with the main application via facade classes, providing a clean and modular architecture.

## Prerequisites

- **Java 17** or later
- **Maven 3.6+**
- **GCC (for compiling C code)**
- **Micronaut 3.x** (or latest version)
- **Picocli 4.x** (or latest version)
- **Windows OS** (for running `.bat` scripts to compile C code)

## Build and Compilation

**Build the Java Application**: The project is built with Maven. To compile and package the Java application, run:
   ```bash
   mvn clean install
   ```

   This will compile the Java code and package it into a jar file.

## Running the Application

   ```bash
   java -jar .\target\sysmon-0.1.jar
   ```

The application supports three commands:

1. **Calculate CPU Usage**:
   ```bash
   cpu
   ```

2. **Calculate Memory Usage**:
   ```bash
   mem
   ```

3. **Quit**:
   ```bash
   q
   ```

## Native Libraries

The JNI native methods require correct paths to the compiled native libraries. Ensure that the paths to the `.dll` (or `.so` for Linux) libraries are correctly specified in the Java native classes. Update the paths in the following files:

- `CpuUsageNative.java`
- `MemoryUsageNative.java`

For example:
```java
System.loadLibrary("path/to/cpu_usage_lib");
System.loadLibrary("path/to/memory_usage_lib");
```

Make sure the libraries are in the expected directory or specify the full path if necessary.

## License

This project is licensed under the MIT License.
