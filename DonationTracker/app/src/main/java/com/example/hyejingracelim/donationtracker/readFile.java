package com.example.hyejingracelim.donationtracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class readFile {
    private static List<String[]> data;
    public static void readData(String file) {
        try {
            // Creates a filereader object
            // class with CSV file used as a parameter.
            FileReader fileReader = new FileReader(file);
            // creates a csvReader object that passes filereader as the parameter
            // skips the first two lines when reading the csv file
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(2)
                    .build();
            data = csvReader.readAll();                 //Each element in list
                                                        // has a separate String (separated by commas in the file)
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();                        //If there is a FileNotFoundException,
        }                                               //it prints the reason of the exception.
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public static List<String[]> getData() {
        return data;
    }
}



