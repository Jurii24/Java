/*
 *This program elevations to the degree some number without import Math.pow and another Math
 */
package com.shpp.p2p.cs.yparfeniuk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    @Override
    public void run() {
        try {
            double base = readDouble("Please, enter the base: ");   //take a base number
            int exponent = readInt("Please, enter the exponent: "); //take an exponent

            if (exponent == 0) {    //number with exponent 0 always equals one
                println("1");
            } else {
                println(raiseToPower(base, exponent));
            }

        } catch (Exception exception) { //catch some exception
            println("Sorry, base must be double variable use ',' instead '.'");
            println("Sorry, exponent must be integer variable (only some numbers)");
        }
    }

    /**
     * Elevation to the power of a number
     */
    private double raiseToPower(double base, int exponent) {
        double s = base;    //s is a future number (elevation to the power, base to exponent)

        //loop for elevation to the power. This loop for both negative and positive exponent
        while (!(exponent == 1 || exponent == -1)) {
            s *= base;
            if (exponent < 0) {
                exponent++;
            } else {
                exponent--;
            }
        }

        //last check, and return s if exponent more than 0, and return 1/s if not
        return (exponent > 0) ? s : (1 / s);
    }
}