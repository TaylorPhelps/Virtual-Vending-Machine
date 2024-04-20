package com.techelevator;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Session {

    // coins enum 
    private enum Coin {
        QUARTER(25),
        DIME(10),
        NICKEL(5);

        private final int amount;

        private Coin(int amount) {
            this.amount = amount;
        }

    }

    // main menu items
    static String[] mainMenu = {
            "(1) Feed Money",
            "(2) Select Product",
            "(3) Finish Transaction"
    };

    // the type of bills the machine accepts
    private static final List<Bill> Bills = Arrays.asList(
            new Bill(2000, "$20.00"),
            new Bill(1000, "$10.00"),
            new Bill(500, "$5.00"),
            new Bill(100, "$1.00"));

    
    // balance in pennies
    private int balanceInPennies = 0;

    public int getBalanceInPennies() {
        return balanceInPennies;
    }


    public void setBalanceInPennies(int balanceInPennies) {
        this.balanceInPennies = balanceInPennies;
    }


    // starts a customer session
    public void startSession() {
        
        // variable used for reading from the input scanner
        String readValue = "";

        while (!readValue.equals("3")) {
            
            // present main menu to the user
            printMenu();
            
            // get user selection
            readValue = Utilities.getInputAsString("\nPlease make a selection: ").toLowerCase();
            switch (readValue) {
                // user wants to feed money
                case "1": {
                    int selection = 0;
                    do {
                        // clear screen
                        Utilities.clearScreen();
                        
                        // print balance
                        printBalance();

                        // display the type of bills that machine accepts to the user

                        for (int x = 0; x < Bills.size(); x++) {
                            Utilities.printLine("(" + (x + 1) + ") " + Bills.get(x).getName());
                        }

                        // show the last menu item
                        Utilities.printLine("(" + (Bills.size() + 1) + ") Finish feeding money");

                        // get user selection
                        selection = Utilities.getInputAsInt("\nPlease select a bill: ");

                        // ensure that user made a valid selection
                        if (selection > 0 && selection <= Bills.size()) {
                            
                            // increase the balance
                            this.balanceInPennies += Bills.get(selection - 1).getAmount();
                            
                            //Adding record to Log.txt
                            Logger.log(
                                getCurrentDateTimeString() + " " +
                                "FEED MONEY " +
                                Bills.get(selection-1).getName() + " " +
                                getBalance()
                                );
                        }
                    } while (selection != Bills.size() + 1);
                    Utilities.clearScreen();
                }
                    break;
                
                    // user wants to make a purchase
                    case "2": {
                    // clear screen
                    Utilities.clearScreen();
                    
                    // display inventory
                    Inventory.PrintInventory();
                    
                    // get user input
                    String selection = Utilities.getInputAsString("\nPlease select a product: ");
                    
                    // ensure user made a valid selection
                    if (Inventory.getProducts().containsKey(selection)) {
                        
                        // get the product
                        Product product = Inventory.getProducts().get(selection);
                        
                        // make sure that product is not sold out
                        if (product.getQuantity() > 0) {
                            
                            // make sure that the customer has enough money to make the purchase
                            if (product.getPrice() <= this.balanceInPennies) {
                                
                                // deduct the cost of the product from the balance
                                this.balanceInPennies -= product.getPrice();
                                
                                // decrease the product quantity
                                product.decreaseQuantity();
                                
                                Utilities.printLine(product.toString());
                                
                                // print special message based on the product category
                                product.printMessage();
                                
                                // log the sale
                                Logger.log(
                                    getCurrentDateTimeString() + " " +
                                    product.getName() + " " +
                                    product.getSlot()+ " " +
                                    Utilities.formatMoney(product.getPrice()/100) + " " +
                                    getBalance()
                                    );
                                
                            } else {
                                // user does not have enough money for the selection
                                Utilities.printLine("You don't have enough money.");
                                 
                            }

                        } else {
                            // item is currently sold out
                            Utilities.printLine("Item is currently sold out.");
                            
                        }
                    } else {
                        // user selected an invalid product
                        Utilities.printLine("Invalid product selected.");
                    }
                }
                    break;
                
                    // user wants ot end the sesssion
                    case "3": {
                     
                     String balanceBefore = getBalance();

                     // return change in coins
                     returnChange();
                     
                     // log the sale
                     Logger.log(
                        getCurrentDateTimeString() + " " +
                        "GIVE CHANGE " +
                        balanceBefore + " " +
                        getBalance()
                        );
                    return;
                }
                default:
                    break;
            }

        }
    }

    
    // this method determines the type and amount of coins (starting form the largest coin type, quarters)
    // to return to customer once the sessions complete
    public void returnChange() {
            
        List<String> responseList = new ArrayList<String>();
        
        // calculate number of quarters returned and update the balance
        returnChange(Coin.QUARTER, responseList);
        
        // calculate number of dimes returned and update the balance
        returnChange(Coin.DIME, responseList);

        // calculate number of nickels returned and update the balance
        returnChange(Coin.NICKEL, responseList);
        
        // calculate number of quarters returned and update the balance
        if (this.balanceInPennies > 0) responseList.add(this.balanceInPennies + " pennies");

        // now that we returned all the coins set the balance to 0
        this.balanceInPennies = 0;

        // print balance due
        Utilities.printLine("Change Due: " + String.join(", " , responseList));

    }

    // this function determines the amount of coins returned given a coin type
    // and deducts that amount from the balance
    private void returnChange(Coin coin, List<String> changeList) {
        if (this.balanceInPennies >= coin.amount) {
            int total = (int) Math.floor(balanceInPennies / coin.amount);
            this.balanceInPennies = balanceInPennies % coin.amount;
            if (total == 1)
                changeList.add((total + " " + coin.name().toLowerCase()));
            else
                changeList.add((total + " " + coin.name().toLowerCase() + "s"));
        }
    }

    // prints the session menu
    public void printMenu() {
        
        // print a blank line
        Utilities.printLine();
        
        // print balance
        printBalance();
        
        // print a blank line
        Utilities.printLine();
        
        // print menu items
        for (String menuItem : mainMenu) {
            Utilities.printLine(menuItem);
        }

    }

    // prints the current balance in U.S. currency format
    private void printBalance() {
        double balance = this.balanceInPennies / 100.0;
        Utilities.printLine("Current Money Provided: " + Utilities.formatMoney(balance) + "\n");
    }


    // returns the current date and time as a formatted string
    public String getCurrentDateTimeString(){
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        return timeFormat.format(now);
    }
    
    // return the current balance in U.S. currency format
    public String getBalance() {
        double balance = this.balanceInPennies / 100.0;
        return Utilities.formatMoney(balance);
    }
  
}
