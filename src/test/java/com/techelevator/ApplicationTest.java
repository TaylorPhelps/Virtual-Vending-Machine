package com.techelevator;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ApplicationTest {

	private final ByteArrayOutputStream outContent = new  ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final ByteArrayInputStream inContent = new ByteArrayInputStream("1\n2\n3\n4\n5\n".getBytes());
	private final InputStream originalIn = System.in;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent)); // Redirect System.out to outContent stream
		System.setIn(inContent); // Redirect System.in to inContent
	}

	@After
	public void restore() {
		System.setOut(originalOut); // Restore original System.out
		System.setIn(originalIn); // Restore original System.in
	}

	@Test
	public void test_DisplayedVendingMachineItems() {
		String input = "1\n3\n"; // selects display inventory then exits
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Application.main(new String[]{}); // Run main method

		String output = outContent.toString(); // Captures the output from the console

		assertTrue(outContent.toString().contains("Display Vending Machine Items"));

		assertTrue(outContent.toString().contains("Chip"));

		assertTrue(outContent.toString().contains("Candy"));

		assertTrue(outContent.toString().contains("Drink"));

		assertTrue(outContent.toString().contains("Gum"));
	}

	@Test
	public void test_Purchase() {
		Application.main(new String[]{});
		assertTrue(outContent.toString().contains("Purchase"));
	}

	@Test
	public void test_Exit() {
		Application.main(new String[]{});
		assertFalse(outContent.toString().contains("Exception"));
	}
}