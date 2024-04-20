package com.techelevator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
public class UtilitiesTest {

    private Utilities utilities;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        utilities = new Utilities();
        System.setOut(new PrintStream(outContent)); // Capture the System.out output
    }

    @Test
    public void testGetInputAsString() {
        // Simulate user input
        String input = "Test Input\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String result = utilities.getInputAsString("Enter something: ");
        assertEquals("Test Input", result);
    }

    @Test
    public void testGetInputAsInt_ValidInput() {
        // Simulate user input
        String input = "42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = utilities.getInputAsInt("Enter an integer: ");
        assertEquals(42, result);
    }

    @Test
    public void testGetInputAsInt_InvalidInput() {
        // Simulate user input
        String input = "Invalid\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = utilities.getInputAsInt("Enter an integer: ");
        assertEquals(0, result); // Invalid input should return 0
    }

    @Test
    public void testPadRight_String() {
        String padded = utilities.padRight("Hello", 10);
        assertEquals("Hello     ", padded);
    }

    @Test
    public void testPadRight_Int() {
        String padded = utilities.padRight(42, 5);
        assertEquals("42   ", padded);
    }

    @Test
    public void testFormatMoney() {
        String formatted = utilities.formatMoney(12.34);
        assertEquals("$12.34", formatted);
    }

    @Test
    public void testClearScreen() {
        // Cannot directly test clearScreen due to console interaction
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
