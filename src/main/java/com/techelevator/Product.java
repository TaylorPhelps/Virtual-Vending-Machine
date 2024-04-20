package com.techelevator;

public class Product {
    
    public final int MAX_QUANTITY = 5;
    
    private String slot;
    
    private String name;
    
    private int price;
    
    private String type;
    
    // initialize the quantity to default max
    private int quantity = MAX_QUANTITY;
    
    // used to determine the longest product name for formatting
    private static int longestName = 0;

    public String getSlot() {
        return slot;
    }
    public void setSlot(String slot) {
        this.slot = slot;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        if(this.name.length()>longestName) longestName = this.name.length()+1;
    }
    public double getPrice() {
        return price;
    }

    public double getPriceInDollars() {
        return price/100.0;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
               
        if(this.quantity >0){
            return 
                Utilities.COLOR_GREEN + Utilities.padRight(slot, 6) + Utilities.COLOR_RESET +  
                Utilities.padRight(this.quantity ,6) +
                Utilities.padRight(this.name, Product.longestName) + 
                    " " + Utilities.formatMoney(this.getPriceInDollars());
        } else {
            return 
                Utilities.COLOR_RED + Utilities.padRight(slot, 6) +Utilities.COLOR_RESET +  
                Utilities.padRight(this.quantity ,6) +
                Utilities.padRight(this.name, Product.longestName) + 
                    " " + Utilities.formatMoney(this.getPriceInDollars());
        }
        
    }

    public void decreaseQuantity(){
        this.quantity--;
    }
    // prints a special message based on the category
    public void printMessage(){
        switch (this.type) {
            case "Chip":
                Utilities.printColorLine(Utilities.COLOR_CYAN +  "Crunch Crunch, Yum!");
                break;
            case "Candy":
                Utilities.printColorLine(Utilities.COLOR_CYAN +  "Munch Munch, Yum!");
                break;
            case "Drink":
                Utilities.printColorLine(Utilities.COLOR_CYAN +  "Glug Glug, Yum!");
                break;
            case "Gum":
                Utilities.printColorLine(Utilities.COLOR_CYAN +  "Chew Chew, Yum!");
                break;
            default:
                Utilities.printColorLine(Utilities.COLOR_CYAN +  "Enjoy!");
                break;
        }
    }

    public Product(){}
    public Product(String slot, String cat, int price,String name){
        this.slot = slot;
        this.type = cat;
        this.price = price;
        this.name = name;
    }
}
