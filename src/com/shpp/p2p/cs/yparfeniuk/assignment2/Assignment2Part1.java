package com.shpp.p2p.cs.yparfeniuk.assignment2;

import com.shpp.cs.a.console.TextProgram;

/*
 * The program finds discriminant of quadratic equation
 */
public class Assignment2Part1 extends TextProgram {

	// Take three nums for quadratic equation and call the method for find root
	public void run() {
		double a = readDouble("Please enter a: ");
		double b = readDouble("Please enter b: ");
		double c = readDouble("Please enter c: ");
		findRoot(a, b, c);
	}

	// This method find root of quadratic equation
	private void findRoot(double a, double b, double c) {
		double d = Math.pow(b, 2) - 4 * a * c; // this formula finds a discriminant

		// analyze the discriminant and find root
		if (d == 0) {
			println("There is one root: " + (-b / (2 * a)));
		} else if (d > 0) {
			println("There are two roots: " + ((-b + Math.sqrt(d)) / (2 * a)) + " and "
					+ ((-b - Math.sqrt(d)) / (2 * a)));
		} else {
			println("There are no real roots");
		}
	}
}
