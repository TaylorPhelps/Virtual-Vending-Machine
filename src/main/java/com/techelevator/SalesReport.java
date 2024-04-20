package com.techelevator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SalesReport {

    public static void printSalesReport(){
        //Get current date and time
        LocalDateTime now = LocalDateTime.now();
        //Format date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);
        
        //Construct file name based on formatted date and time
        String fileName = "SalesReport_" + formattedDateTime + ".txt";

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))) {
            //Write header and separator
            writer.println("Sales Report: ");
            writer.println("---------------------------------------");
            //Initialize total sales amount
            int totalSalesAmount = 0;

            //Loop through each product in the inventory
            for (var product : Inventory.getProducts().values()) {
            //Write product name and quantity to file
            writer.println(product.getName() + "|" + product.getQuantity());

            //Calculate sales amount for each product and add to total sales amount
            totalSalesAmount += (int) ((product.MAX_QUANTITY - product.getQuantity()) * product.getPrice());
            }

            //Write total sales amount to file
            writer.println("\nTOTAL SALES $" + String.format("%.2f", totalSalesAmount / 100.0));
        } catch (IOException e) {
            //Handle file writing error
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
