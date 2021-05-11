/*
 This program takes the word,
 counts the number of syllables in the word
 and returns the number of syllables
 */
package com.shpp.p2p.cs.yparfeniuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {

    @Override
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word.toLowerCase()));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        int numberOfLetters = word.length();
        int syllables = 0;

        for (int i = 0; i < numberOfLetters; i++) {

            //check if the letter is a vowel and if it is the first or before it is a vowel
            if (isVowel(word.charAt(i)) && (i == 0 || !isVowel(word.charAt(i - 1)))) {

                //if 'e' is the last letter in the word, and syllables != 0, then return result
                if ((i == numberOfLetters - 1) && (syllables != 0) && (word.charAt(i) == 'e')) {
                    continue;
                }
                syllables++;
            }
        }
        return syllables;
    }

    /**
     * Method checks if the letter is vowel
     *
     * @param letterInTheWord one letter in the word
     * @return true, if letter is vowel, else false
     */
    private boolean isVowel(char letterInTheWord) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'}; //all vowels
        int numberOfVowels = vowels.length;

        for (int i = 0; i < numberOfVowels; i++) {
            if (letterInTheWord == vowels[i]) {
                return true;
            }
        }
        return false;
    }
}
