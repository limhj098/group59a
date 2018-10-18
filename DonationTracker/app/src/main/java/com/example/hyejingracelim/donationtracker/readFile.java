package com.example.hyejingracelim.donationtracker;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
    public static HashMap<String, Integer> getMap() {
        HashMap<String, Integer> loaded = new HashMap<>();
        String key, locationName, locationType, address, city, state, latitude, longitude, zipCode, phone, website;
        for (int i = 0; i < data.size(); i++) {      //There are 12 elements for each Location in the file, so I want to get the correct element
            //for each variable, so I scan through the list making sure I put in the right variable the element of the right place in the List.
            for (int j = 0; j < 11; j++) {
                switch (j) {
                    case 0:
                        key = data.get(i + j).toString();
                        loaded.put(key, i + j);
                        break;
                    case 1:
                        locationName = data.get(i + j).toString();
                        loaded.put(locationName, i + j);
                        break;
                    case 2:
                        latitude = data.get(i + j).toString();
                        loaded.put(latitude, i + j);
                        break;
                    case 3:
                        longitude = data.get(i + j).toString();
                        loaded.put(longitude, i + j);
                        break;
                    case 4:
                        address = data.get(i + j).toString();
                        loaded.put(address, i + j);
                        break;
                    case 5:
                        city = data.get(i + j).toString();
                        loaded.put(city, i + j);
                        break;
                    case 6:
                        state = data.get(i + j).toString();
                        loaded.put(state, i + j);
                        break;
                    case 7:
                        zipCode = data.get(i + j).toString();
                        loaded.put(zipCode, i + j);
                        break;
                    case 8:
                        locationType = data.get(i + j).toString();
                        loaded.put(locationType, i + j);
                        break;
                    case 9:
                        phone = data.get(i + j).toString();
                        loaded.put(phone, i + j);
                        break;
                    case 10:
                        website = data.get(i + j).toString();
                        loaded.put(website, i + j);
                        break;
                    default:
                        break;
                }
            }
        }
        for (HashMap.Entry<String, Integer> entry : loaded.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        return loaded;
    }
}



