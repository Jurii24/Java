/*
 * This program for win-win casino game. Win some money, no less than 20 dollars
 */
package com.shpp.p2p.cs.yparfeniuk.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {
    //global variable for money
    private static int money = 0;

    @Override
    public void run() {
        //start game and print game result
        println("It took " + playGame() + " games to earn $" + money);
    }

    /**
     * This method starts new games and return how many game was played
     */
    private int playGame() {
        RandomGenerator eagle = new RandomGenerator();  //new random generator for game
        int numberOfGames = 0;
        int rate;

        while (money <= 19) {   //in loop playing some game if we have not more money than 19
            rate = 1; //set first rate

            while (eagle.nextBoolean()) {   //if eagle true rate*2, else we win this rate
                rate *= 2;
            }

            println("This game, you earned $" + rate);  //result one played game
            money += rate;  //win this rate
            println("Your total is $" + money); //output how many win money
            numberOfGames++;    //one game is win
        }

        return numberOfGames;   //return how many game was playing
    }
}