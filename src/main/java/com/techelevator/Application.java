package com.techelevator;

public class Application {

	// main menu items
	private static String[] mainMenu = {
	 	Utilities.COLOR_GREEN + "(1)" + Utilities.COLOR_RESET + " Display Vending Machine Items",
	 	Utilities.COLOR_GREEN + "(2)" + Utilities.COLOR_RESET + " Purchase",
		Utilities.COLOR_GREEN + "(3)" + Utilities.COLOR_RESET + " Exit"
	};

	public static void main(String[] args) {


		// load a "fresh" inventory from the file
		Inventory.LoadFromFile("vendingmachine.csv");
		
		// clear screen
		Utilities.clearScreen();
		
		// print welcome message
		Utilities.printColorLine(Utilities.COLOR_BLUE + "Welcome!\n");

		// variable used for reading user input
		String readValue = "";

		while (!readValue.equals("3")) {

			// print main menu items
			printMainMenu();

			// get user input
			readValue = Utilities.getInputAsString("\nPlease make a selection: ").toLowerCase();

			switch (readValue) {
				case "1":
					// user wants to see the inventory
					// print inventory	
					Inventory.PrintInventory();
					break;
				case "2":
					// user wants to make a purchase
					// clear screen and start a ne session	
					Utilities.clearScreen();
					Session session = new Session();
					session.startSession();
					break;
				case "4": 
					// Hidden option: Generate Sales Report
					Utilities.clearScreen();
					SalesReport.printSalesReport();
					break;
				default:
					break;
			}
		}
	}

	// this method prints main menu on the screen
	private static void printMainMenu(){
		Utilities.printLine("");
		for (String menuItem : mainMenu) {
			Utilities.printLine(menuItem);
		}

	}
}