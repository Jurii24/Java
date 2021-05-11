package com.shpp.p2p.cs.yparfeniuk.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.Arrays;

public class NameSurferEntry implements NameSurferConstants {
    private String name; //is some name people
    private final int[] rank = new int[NDECADES]; //the rank some name

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] elementInLine = line.split(" ");

        for (int i = 0; i < elementInLine.length; i++) {
            if (i != 0) { //first element is "name", "rank" start from second element
                rank[i - 1] = Integer.parseInt(elementInLine[i]);
            } else {
                name = elementInLine[i];
            }
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        try {
            return rank[decade];
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("This decade is not found" + "\n" + error);
        }
        return 0;
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    @Override
    public String toString() {
        return name + " " + Arrays.toString(rank);
    }
}