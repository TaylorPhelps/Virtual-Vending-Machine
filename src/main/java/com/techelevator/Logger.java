package com.techelevator;

import java.io.*;
import java.util.Scanner;


public class Logger {

    //Print a message in the Log.txt file
    public static void log(String message){
        //Open or create a file to append the message each iteration
        try(PrintWriter dataOutput = new PrintWriter(new FileOutputStream("Log.txt", true))){
            dataOutput.println(message);
        }
        //Print an error if the file is not found
        catch (FileNotFoundException e){
            System.err.println("Cannot open the file for writing.");
        }
    }
}
