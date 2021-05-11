/*
 The program sums up two positive prime numbers of type String.
 Numbers are entered by the user.
 Numbers could be of different lengths
 */
package com.shpp.p2p.cs.yparfeniuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

    //percentage to divide the number (42 / PERCENTAGE_DIVIDE_NUMBER = 4 or 42 % PERCENTAGE_DIVIDE_NUMBER = 2)
    private static final int PERCENTAGE_DIVIDE_NUMBER = 10;

    @Override
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return sumN1AndN2 String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        int n1Length = n1.length() - 1;
        int n2Length = n2.length() - 1;
        int result = 0, remainder = 0;
        String sumN1AndN2 = "";

        while (n1Length >= 0 || n2Length >= 0) { //calculate sumN1AndN2 = n1 + n2

            //calculate result
            if (n1Length >= 0 && n2Length >= 0) {
                result = remainder + (n1.charAt(n1Length) - '0') + (n2.charAt(n2Length) - '0');
            } else if (n1Length <= -1 && n2Length >= 0) {   //if n1 ended, work with n2
                result = remainder + (n2.charAt(n2Length) - '0');
            } else if (n1Length >= 0 && n2Length <= -1) {   //if n2 ended, work with n1
                result = remainder + (n1.charAt(n1Length) - '0');
            }

            //result before ten and remainder after ten (if 42: remainder = 4 and result = 2)
            remainder = result / PERCENTAGE_DIVIDE_NUMBER;
            result %= PERCENTAGE_DIVIDE_NUMBER;

            //next position
            n1Length--;
            n2Length--;

            //add remainder (if only not equals 0) and result to sumN1AndN2 if length n1 and n2 < 0,
            // else add only result to sumN1AndN2
            sumN1AndN2 = (n1Length < 0 && n2Length < 0 && remainder != 0) ?
                    (char) (remainder + '0') + "" + (char) (result + '0') + sumN1AndN2 :
                    (char) (result + '0') + sumN1AndN2;
        }
        return sumN1AndN2;
    }
}
