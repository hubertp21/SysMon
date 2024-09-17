package com.example;

import io.micronaut.configuration.picocli.PicocliRunner;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;

import java.util.Scanner;

@Command(name = "sysmon", description = "System monitor CLI",
        mixinStandardHelpOptions = true)
public class SysmonCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SysmonCommand.class);

    @Inject
    CpuUsageFacade cpuUsageFacade;

    @Inject
    MemoryUsageFacade memoryUsageFacade;

    public static void main(String[] args) {
        PicocliRunner.run(SysmonCommand.class, args);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            LOG.info("enter command (cpu, mem, q): ");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "cpu" -> LOG.info("{}", cpuUsageFacade.getCpuUsage());
                case "mem" -> LOG.info("{}", memoryUsageFacade.getMemoryUsage());
                case "q" -> {
                    running = false;
                    LOG.info("exiting...");
                }
                default -> LOG.warn("unknown command!\ntry 'cpu', 'mem', or 'q' to exit");
            }
        }
        scanner.close();
    }
}
