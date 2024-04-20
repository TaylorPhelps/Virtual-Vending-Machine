package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setup() {
        // Create a mock inventory to test
        Map<String, Product> mockProducts = new TreeMap<>();
        mockProducts.put("A1", new Product("A1", "Chips", 150, "Snack"));
        mockProducts.put("B2", new Product("B2", "Soda", 200, "Beverage"));

        inventory = new Inventory();
        inventory.setProducts(mockProducts);
    }

    @Test
    public void testLoadFromFile() {
        // Simulate loading from file using mock data
        assertEquals("Chips", inventory.getProducts().get("A1").getName());
        assertEquals(200, inventory.getProducts().get("B2").getPrice());
    }

    @Test
    public void testPrintInventory() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call PrintInventory method
        inventory.PrintInventory();

        // Check for expected output
        String expectedOutput = "\nSlot Qty   Product             Price\n" + "---------------------------------------\n" + "A1 5   Chips             $1.50\n" + "B2 5   Soda             $2.00\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
