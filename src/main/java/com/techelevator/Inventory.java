package com.techelevator;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory {
    
    private static final int SLOT_COLUMN_INDEX = 0;
    private static final int NAME_COLUMN_INDEX = 1;
    private static final int PRICE_COLUMN_INDEX = 2;
    private static final int TYPE_COLUMN_INDEX = 3;

    // static inventory of items
    // this list is reloaded on each machine restart
    private static Map<String, Product> products; 
    
    // loads the inventory items from a file
    public static void LoadFromFile(String fileName) {
        Inventory.products = new TreeMap<>();
        try (Scanner scanner = new Scanner(new File(fileName));)
        {
			
			while (scanner.hasNextLine()) {
				String[]cols = scanner.nextLine().split("\\|");
                
                if(cols.length == 4) {
                    Product product = new Product();
                    product.setSlot(cols[SLOT_COLUMN_INDEX]);
                    product.setName(cols[NAME_COLUMN_INDEX]);
                    product.setPrice((int)(Double.parseDouble(cols[PRICE_COLUMN_INDEX])*100));
                    product.setType(cols[TYPE_COLUMN_INDEX]);
                    Inventory.products.put(cols[SLOT_COLUMN_INDEX], product);
                } else {
                    throw new Exception("Invalid number of columns: expected 4 but found " + cols.length);
                }
                
			}

			scanner.close();
		} catch (Exception ex) {
			// log 
		}
    }

    public static Map<String, Product> getProducts() {
        return products;
    }

    public static void setProducts(Map<String, Product> products) {
        Inventory.products = products;
    }

    // this method prints inventory items on the screen
    public static void PrintInventory(){
        Utilities.printLine();
        Utilities.printLine("Slot Qty   Product             Price");
        Utilities.printLine("---------------------------------------");
        for (var product : Inventory.products.values()) {
            if(product.getQuantity()>0)
                Utilities.printLine(product.toString());
            else 
                Utilities.printLine(product.toString() + " " + Utilities.COLOR_RED + "SOLD OUT" + Utilities.COLOR_RESET);
        }
    }
}
