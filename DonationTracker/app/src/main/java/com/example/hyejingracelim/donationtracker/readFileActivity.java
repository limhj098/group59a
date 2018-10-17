package com.example.hyejingracelim.donationtracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class readFileActivity {
    private static String key, locationName, locationType, address, city, state, latitude, longitude, zipCode,
            phone, website;

    public static void readData(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(2)
                    .build();
            List<String[]> data = csvReader.readAll();
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < 11; j++) {
                    switch (j) {
                        case 0:
                            key = data.get(i).toString();
                            break;
                        case 1:
                            locationName = data.get(i).toString();
                            break;
                        case 2:
                            latitude = data.get(i).toString();
                            break;
                        case 3:
                            longitude = data.get(i).toString();
                            break;
                        case 4:
                            address = data.get(i).toString();
                            break;
                        case 5:
                            city = data.get(i).toString();
                            break;
                        case 6:
                            state = data.get(i).toString();
                            break;
                        case 7:
                            zipCode = data.get(i).toString();
                            break;
                        case 8:
                            locationType = data.get(i).toString();
                            break;
                        case 9:
                            phone = data.get(i).toString();
                            break;
                        case 10:
                            website = data.get(i).toString();
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



