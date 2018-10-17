package com.example.hyejingracelim.donationtracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class readFileActivity {
    private String key, locationName, locationType, address, city, state, latitude, longitude, zipCode,
            phone, website;
    public static void readData() {
        try {
            File file = new File("LocationData.csv")
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                                      .withSkipLines(2)
                                      .build();
            List<String[]> data = csvReader.readAll();
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < 11; j++) {
                    switch (j) {
                        case 0: key = data.get(i);
                                break;
                        case 1: locationName = data.get(i);
                                break;
                        case 2: latitude = data.get(i);
                                break;
                        case 3: longitude = data.get(i);
                                break;
                        case 4: address = data.get(i);
                                break;
                        case 5: city = data.get(i);
                                break;
                        case 6: state = data.get(i);
                                break;
                        case 7: zipCode = data.get(i);
                                break;
                        case 8: locationType = data.get(i);
                                break;
                        case 9: phone = data.get(i);
                                break;
                        case 10: website = data.get(i);
                                break;
                        default: break;
                    }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
