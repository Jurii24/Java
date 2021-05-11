package com.shpp.p2p.cs.yparfeniuk.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {
    /**
     * Convertible database (names and ranks)
     */
    private final HashMap<String, NameSurferEntry> statisticsOfNames = new HashMap<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        BufferedReader statisticsFile = null;
        try {
            statisticsFile = new BufferedReader(new FileReader("assets/" + filename));
            String line;
            while ((line = statisticsFile.readLine()) != null) { //read file
                NameSurferEntry entry = new NameSurferEntry(line);
                statisticsOfNames.put(entry.getName(), entry);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!!!\nFile must be in \"assets/\" folder\n" + exception.toString());
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeFile(statisticsFile);
        }
    }

    /**
     * This method close file
     *
     * @param statisticsFile is BufferedReader with open file
     */
    private void closeFile(BufferedReader statisticsFile) {
        if (statisticsFile != null) {
            try {
                statisticsFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return statisticsOfNames.getOrDefault(StringUtils.capitalize(name.toLowerCase()), null);
    }
}