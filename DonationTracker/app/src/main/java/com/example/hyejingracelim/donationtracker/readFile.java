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
            FileReader fileReader = new FileReader(new File(file));
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(2)
                    .build();
            data = csvReader.readAll();
        }
        catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        }
        catch (IOException e1) {
            System.out.println("IOException");
        }
    }
    public static List<String[]> getData() {
        return data;
    }
}



