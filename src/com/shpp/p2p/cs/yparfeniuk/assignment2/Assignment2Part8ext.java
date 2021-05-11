package com.shpp.p2p.cs.yparfeniuk.assignment2;

import com.shpp.cs.a.console.TextProgram;

/*
 * The program playing fizz bazz buff
 */
public class Assignment2Part8ext extends TextProgram {

	// Take a num for start a game
	public void run() {
		int a = readInt("Please enter a number: ");
		count(a);
	}

	// start game
	private void count(int a) {
		for (int i = 0; i < a; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				if (i % 3 == 0 && i % 5 == 0) {
					println("Buzz");
				} else if (i % 3 == 0) {
					println("Fizz");
				} else
					println("Bazz");
			} else {
				println(i);
			}
		}

	}
}
