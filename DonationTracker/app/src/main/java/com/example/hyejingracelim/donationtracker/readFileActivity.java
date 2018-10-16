package com.example.hyejingracelim.donationtracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.List;

private int key;
private String locationName, locationType, address, city, state, type, latitude, longitude, zipCode,
               phone, website;
public static class readFileActivity {
    public static void readData(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                                      .withSkipLines(2)
                                      .build();
            List<String[]> data = csvReader.readAll();
            for (String[] row : data) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
