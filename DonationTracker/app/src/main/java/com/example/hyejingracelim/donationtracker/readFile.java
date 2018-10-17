package com.example.hyejingracelim.donationtracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class readFile {
    private static List<String[]> data;
    public static void readData() {
        try {
            FileReader fileReader = new FileReader(new File("Users/Jinwoo Hwang/Documents/group59a/DonationTracker/LocationData.csv"));
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(2)
                    .build();
            data = csvReader.readAll();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public static List<String[]> getData() {
        return data;
    }
}



