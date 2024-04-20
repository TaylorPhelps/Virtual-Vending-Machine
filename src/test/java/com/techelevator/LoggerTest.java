package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LoggerTest {

    private Logger logger;

    @Before
    public void setup() {
        logger = new Logger();
    }

    @Test
    public void testLog() {
        // Call Logger method
        logger.log("Test log message");

        // Check if the log was created with correct log
        File logFile = new File("log.txt");
        assertTrue("Log file should exist", logFile.exists());

        // Read content
        try (Scanner scanner = new Scanner(logFile)){
            String logContent = scanner.nextLine();
            assertEquals("Test log", logContent);
        } catch (Exception e) {
            fail("Error reading log file: " + e.getMessage());
        }
    }
}
