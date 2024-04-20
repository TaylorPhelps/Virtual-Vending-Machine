package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.Assert.*;
public class SalesReportTest {

    private SalesReport salesReport;

    @Before
    public void setup() {
        salesReport = new SalesReport();
    }

    @Test
    public void testPrintSalesReport() {
        // Call printSalesReport method
        salesReport.printSalesReport();

        // Construct file based on current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);
        String expectedFileName = "SalesReport_" + formattedDateTime + ".txt";

        // Check expected file creation
        File salesReportFile = new File(expectedFileName);
        assertTrue("Sales report file should exist", salesReportFile.exists());

        // Read content
        try (Scanner scanner = new Scanner(salesReportFile)){
            String firstLine = scanner.nextLine();
            assertEquals("First line should be 'Sales Report:'", "Sales Report:", firstLine);
        } catch (Exception e) {
            fail("Error reading sales report file: " + e.getMessage());
        }
    }
}
