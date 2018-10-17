package com.example.hyejingracelim.donationtracker;
import java.io.BufferedReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class readFile {
    static String line = "";
    static String cvsSplit = ",";
    static BufferedReader readFile = null;
    static String[] stored;
    public static void readData() {
        try {
            readFile = new BufferedReader(new FileReader("LocationData.csv"));
            while ((line = readFile.readLine()) != null) {
                stored = line.split(cvsSplit);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (readFile != null) {
                try {
                    readFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String[] getData() {
        return stored;
    }
}



