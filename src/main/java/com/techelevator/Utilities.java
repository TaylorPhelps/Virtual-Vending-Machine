package com.techelevator;

import java.text.DecimalFormat;
import java.util.Scanner;

// helper methods for printing & formatting
// and shared input scanner
public class Utilities {
    
    // static shared input scanner
    private static Scanner input = new Scanner(System.in);
    
    // money formatter
    private static final DecimalFormat money = new DecimalFormat("$0.00");

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\033[0;33m"; 
    public static final String COLOR_CYAN = "\033[0;36m"; 

    public static void printLine(){
        System.out.println();
    }
    
    public static void printLine(String line){
        System.out.println (line);
    }
    
    public static void printColorLine(String line){
        System.out.println(line + Utilities.COLOR_RESET);
    }
    
    public static void printColor(String line){
        System.out.print(line + Utilities.COLOR_RESET);
    }
    
    public static void printLine(int line){
        System.out.println (line);
    }
    
    public static String getInputAsString(String message){
        printColor(Utilities.COLOR_YELLOW +  message);
        return getInputAsString();
    }
    public static String getInputAsString(){
        return input.nextLine().toUpperCase();
    }
    
    public static int getInputAsInt(String message){
        printColor(Utilities.COLOR_YELLOW + message);
        String ret =  input.nextLine();
        try{
            return Integer.parseInt(ret);
         } catch (NumberFormatException nfe) {
        }
        return 0;
    }
    
    public static void Dispose(){
        input.close();
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }
    
    public static String padRight(int s, int n) {
        return String.format("%-" + n + "s", s);  
    }
    public static String padRight(double s, int n) {
        return String.format("%-" + n + "s", s);  
    }

    
    public static String formatMoney(double amount){
        return money.format(amount);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
    }

}
