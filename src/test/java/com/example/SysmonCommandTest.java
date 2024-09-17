package com.example;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import nl.altindag.log.LogCaptor;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SysmonCommandTest {

    @ParameterizedTest
    @MethodSource("commandAndExpectedLogProvider")
    void testCommandWithExpectedLog(String userInput, String expectedLog) {
        LogCaptor logCaptor = LogCaptor.forClass(SysmonCommand.class);

        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[]{};
            PicocliRunner.run(SysmonCommand.class, ctx, args);
            assertTrue(logCaptor.getInfoLogs().stream().anyMatch(log -> log.contains(expectedLog)));
        }
    }

    static Stream<Object[]> commandAndExpectedLogProvider() {
        return Stream.of(
                new Object[]{"cpu\nq\n", "cpu usage"},
                new Object[]{"mem\nq\n", "memory usage"},
                new Object[]{"q\n", "exiting..."}
        );
    }
}
