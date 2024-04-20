package com.techelevator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SessionTest {

    private Session session;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        session = new Session();
        // Capture output
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testStartSession_FeedMoney() {
        // Simulate user input
        String input = "1\n4\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        session.startSession();
        assertTrue(outContent.toString().contains("Finish feeding money"));
    }

    @Test
    public void testStartSession_SelectProduct() {
        // Simulate user input for selecting a product
        String input = "2\nA1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        session.startSession();
        assertTrue(outContent.toString().contains("Crunch Crunch, Yum!")); // Assuming "A1" is a chip
    }

    @Test
    public void testReturnChange() {
        // Set balance to simulate returning change
        session.setBalanceInPennies(65);
        session.returnChange();
        assertEquals("Change should be returned correctly", 0, session.getBalanceInPennies());
        // Check if the correct change was returned
        assertTrue(outContent.toString().contains("Change Due:"));
    }

    @Test
    public void testPrintMenu() {
        session.printMenu();
        assertTrue(outContent.toString().contains("(1) Feed Money"));
        assertTrue(outContent.toString().contains("(2) Select Product"));
        assertTrue(outContent.toString().contains("(3) Finish Transaction"));
    }

    @Test
    public void testGetCurrentDateTimeString() {
        String dateTimeString = session.getCurrentDateTimeString();
        assertNotNull("Date time string should not be null", dateTimeString);
        // Check if the format is correct
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss a");
        LocalDateTime.parse(dateTimeString, formatter); // This will throw an exception if the format is incorrect
    }

    // Helper method to set the balance for testing
    private void setBalanceForTesting(int balanceInPennies) {
        session.setBalanceInPennies(balanceInPennies);
    }

    // Helper method to get the captured output from System.out
    private String getOutput() {
        return outContent.toString();
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setIn(null);
    }
}
