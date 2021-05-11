/*
 This program reads the CSV file
 and gives user the desired column
 */
package com.shpp.p2p.cs.yparfeniuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    @Override
    public void run() {
        println(extractColumn("food-origins.csv", 1)); //print column from file
    }

    /**
     * This method writes the file to ArrayList
     * and shows the selected column
     *
     * @param filename    name csv file
     * @param columnIndex index in csv file
     * @return data from csv file from columnIndex
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> dataInFile = new ArrayList<>(); // new ArrayList for file data

        try {
            BufferedReader readCsvFile = new BufferedReader(new FileReader("assets/" + filename));
            String rowInFile; //variable for writing from file

            //reading file and writing to ArrayList
            while (true) {
                rowInFile = readCsvFile.readLine();
                if (rowInFile == null) { //the file is over?
                    break;
                }
                dataInFile.add("\"" + fieldsIn(rowInFile).get(columnIndex) + "\""); //add necessary column to dataInFile
            }
            readCsvFile.close(); //close csv file
        } catch (Exception exception) {
            println("File not found");
            println(exception.toString());
        }

        //return ArrayList if file contain this column, or null if not
        return (dataInFile.size() == 0) ? null : dataInFile;
    }

    /**
     * This method divides the row into columns
     *
     * @param line some file line
     * @return ArrayList with columns
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> someRow = new ArrayList<>(); //future ArrayList with columns
        String data = ""; //variable for the value in the column
        int startPosition = 0;
        int lineLength = line.length() - 1;
        boolean isComa = true; //whether to respond to a coma

        while (startPosition <= lineLength) { //find column in "line" and write to ArrayList
            if (isComa && line.charAt(startPosition) == ',') {
                if (data.length() != 0) {
                    someRow.add(data); //add this value from column
                    data = ""; //zeroing
                }
                startPosition++; //next position
                continue; //next iteration
            }

            if (line.charAt(startPosition) == '"' &&
                    (startPosition == lineLength || line.charAt(startPosition + 1) != '"')) {
                isComa = !isComa; //change coma value

                if (isComa) {
                    someRow.add(data); //add this value from column
                    data = ""; //zeroing
                }
                startPosition++; //next position
                continue; //next iteration
            } else if ((line.charAt(startPosition) == '"' && line.charAt(startPosition + 1) == '"')) {
                startPosition++; //next position
            }

            data += line.charAt(startPosition); //formulate the value in the column
            startPosition++; //next position
        }
        if (data.length() >= 1) { //if don't write all
            someRow.add(data);  //add this value from column
        }
        return someRow;
    }
}