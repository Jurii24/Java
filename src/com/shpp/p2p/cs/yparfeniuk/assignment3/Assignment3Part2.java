/*
 * The program builds gradins numbers from the number specified by the user
 */
package com.shpp.p2p.cs.yparfeniuk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {
    //global values for the gradins numbers (this number take user in method takeANumber)
    private static int someNumberN = 1;

    @Override
    public void run() {
        takeANumber();
        goToOne();  //build gradins numbers from someNumberN
    }

    /**
     * This method takes some number from user and write him to global variable
     */
    private void takeANumber() {
        try {
            someNumberN = readInt("Enter a number: ");  //take some number

            if (someNumberN <= 0) { //check this number (only positives if not, user must enter else)
                println("Please, enter a number more than \"1\"");
                takeANumber();
            }

        } catch (Exception exception) { //catch some exception
            println("Sorry, the number must be integer variable (only some numbers)");
        }
    }

    /**
     * This method builds gradins numbers from someNumberN
     */
    private void goToOne() {
        while (someNumberN != 1) {  //number 1 is end
            if (someNumberN % 2 == 0) {
                println(someNumberN + " is even so I take half: " + (someNumberN /= 2));
            } else {
                println(someNumberN + " is odd so I make 3n + 1: " + (someNumberN = someNumberN * 3 + 1));
            }
        }

        println("end.");    //in the end gradins numbers
    }
}