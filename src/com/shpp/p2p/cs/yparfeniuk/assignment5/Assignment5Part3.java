/*
 This program takes a string from the user (some car number),
 search for three letters
 and shows the words that can be composed of them uses dictionary file
 */
package com.shpp.p2p.cs.yparfeniuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {

    //the number of letters that must be
    private static final int NUMBER_OF_LETTERS = 3;

    @Override
    public void run() {
        ArrayList<String> dictionary = readFile(); //write to ArrayList some dictionary

        if (dictionary.size() == 0) { //not find dictionary file or file is empty
            println("Program is stopped");
        } else {
            while (true) {
                //takes a string from the user
                String carNumber = findLetters(readLine("Please, enter some car number: "));

                if (carNumber.length() == NUMBER_OF_LETTERS) { //check for number letters
                    searchForWords(carNumber, dictionary);
                }
            }
        }
    }

    /**
     * Method go through all the words in ArrayList and give them to isWordContainsLetters(),
     * if word is correct, print him
     *
     * @param carNumber  number of some car
     * @param dictionary ArrayList with words
     */
    private void searchForWords(String carNumber, ArrayList dictionary) {
        int numberWord = 0; //start index
        int sizeOfDictionary = dictionary.size();

        while (numberWord != sizeOfDictionary) {
            if (isWordContainsLetters(dictionary.get(numberWord).toString(), carNumber)) {
                println(dictionary.get(numberWord).toString());
            }
            numberWord++;
        }
    }

    /**
     * This method check if word contains this letters(in order)
     *
     * @param word    some word in dictionary
     * @param letters this letters (in order) must contains in word
     * @return true if word contains this letters, or false else
     */
    private boolean isWordContainsLetters(String word, String letters) {
        int wordLength = word.length();
        int numberOfLettersFound = 0; //count how many letters is found

        for (int i = 0; i < wordLength; i++) {
            if (word.charAt(i) == letters.charAt(numberOfLettersFound)) { //check word
                numberOfLettersFound++;
            }
            if (numberOfLettersFound == NUMBER_OF_LETTERS) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method writes dictionary file to ArrayList
     *
     * @return ArrayList with contains file
     */
    private ArrayList readFile() {
        ArrayList<String> dictionaryArray = new ArrayList<>(); //this ArrayList will be contains dictionary file

        try {
            //open some file
            BufferedReader dictionaryFile = new BufferedReader(new FileReader("assets/en-dictionary.txt"));
            String lineInDictionary; //variable for writing from file

            //reading file and writing to ArrayList
            while (true) {
                lineInDictionary = dictionaryFile.readLine();
                if (lineInDictionary == null) { //the file is over?
                    break;
                }
                dictionaryArray.add(lineInDictionary.toUpperCase()); //writing to ArrayList
            }
            dictionaryFile.close(); //close dictionary file
        } catch (Exception exception) {
            println("File not found");
            println(exception.toString());
        }
        return dictionaryArray;
    }

    /**
     * This method find letter in string
     *
     * @param number is some string
     * @return letters in UpperCase
     */
    private String findLetters(String number) {
        String letters = ""; //variable for only letters

        //find letters in "number" and write letter to "letters"
        for (int i = 0, numberLength = number.length(); i < numberLength; i++) {
            if (Character.isAlphabetic(number.charAt(i))) {
                letters += number.charAt(i);
            }
        }
        if (letters.length() != NUMBER_OF_LETTERS) { //(check) if not correct number of letters print
            println("Please, enter correct car number");
        }
        return letters.toUpperCase();
    }
}
