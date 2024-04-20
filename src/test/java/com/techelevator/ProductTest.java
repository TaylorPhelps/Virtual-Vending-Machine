package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ProductTest {

    private Product product;

    @Before
    public void setup() {
        product = new Product();
        product.setSlot("A1");
        product.setName("Chips");
        product.setPrice(150);
        product.setType("Snack");
    }

    @Test
    public void testGetSlot() {
        assertEquals("A1", product.getSlot());
    }

    @Test
    public void testGetName() {
        assertEquals("Chips", product.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("150", product.getPrice());
    }

    @Test
    public void testGetPriceInDollars() {
        assertEquals(150, product.getPriceInDollars(), 0.01);
    }

    @Test
    public void testGetType() {
        assertEquals("Snack", product.getType());
    }

    @Test
    public void testGetQuantity() {
        product.decreaseQuantity();
        assertEquals(4, product.getQuantity());
    }

    @Test
    public void testPrintMessage() {
        // Redirect System.out to output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call printMessage
        product.printMessage();

        // CHeck output
        String expectedOutput = "Crunch, Crunch, Yum!";
        assertEquals(expectedOutput, outContent.toString().trim());
    }
}
