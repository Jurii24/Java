package com.shpp.p2p.cs.yparfeniuk.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

import java.awt.Color;
import java.awt.Graphics;

import com.shpp.cs.a.graphics.WindowProgram;

/*
 * This program draw the flag and text in the bottom of window
 */
public class Assignment2Part4 extends WindowProgram {
	// Flag parameters
	public static final int FLAG_WIGTH = 390;
	public static final int FLAG_HEIGHT = 390;
	public static final boolean FLAG_HORIZONTAL_ORIENTATION = false;
	public static final int NUMBER_OF_COLORS = 3; // No more than 3
	public static final Color COLOR_FIRST = Color.BLACK;
	public static final Color COLOR_SECOND = Color.YELLOW;
	public static final Color COLOR_THIRTH = Color.RED;

	// Calculate size one piece of flag
	public static final double SIZE_ONE_HORIZONTAL_COLOR = FLAG_HEIGHT / NUMBER_OF_COLORS;
	public static final double SIZE_ONE_VERTICAL_COLOR = FLAG_WIGTH / NUMBER_OF_COLORS;

	// In another size of flag the program will build another window
	public static final int APPLICATION_WIDTH = (int) SIZE_ONE_VERTICAL_COLOR + FLAG_WIGTH;
	public static final int APPLICATION_HEIGHT = (int) SIZE_ONE_HORIZONTAL_COLOR + FLAG_HEIGHT;

	public void run() {
		/*add(new GObject() { // it for setting new window size
			public void paint(Graphics arg0) {
				setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); // add new size of window
			}

			public GRectangle getBounds() { // Auto-generated method stub
				return null;
			}
		});*/
		drawFlag();
		printText("Belgium"); // here can be other country
	}

	private void drawFlag() {
		for (int i = 0; i < NUMBER_OF_COLORS; i++) {
			drawColor(i); // here will be drawn one color in one time in the loop
		}
	}

	// In this method will drawn one color of flag
	private void drawColor(int numColor) {
		GRect r;
		if (FLAG_HORIZONTAL_ORIENTATION) { // here only if flag has horizontal color orientation
			r = new GRect(0, numColor * SIZE_ONE_HORIZONTAL_COLOR, FLAG_WIGTH, SIZE_ONE_HORIZONTAL_COLOR);
		} else {
			r = new GRect(numColor * SIZE_ONE_VERTICAL_COLOR, 0, SIZE_ONE_VERTICAL_COLOR, FLAG_HEIGHT);
		}
		r.setFilled(true);
		switch (numColor) { // drawn every color of flag
		case 0:
			r.setColor(COLOR_FIRST);
			break;
		case 1:
			r.setColor(COLOR_SECOND);
			break;
		case 2:
			r.setColor(COLOR_THIRTH);
			break;
		default:
			break;
		}
		add(r); // add this rect to window
		r.move(APPLICATION_WIDTH / 2 - FLAG_WIGTH / 2, APPLICATION_HEIGHT / 2 - FLAG_HEIGHT / 2); // move to center
	}

	// add this text to window
	private void printText(String country) {
		GLabel l = new GLabel("Flag of " + country);
		add(l, APPLICATION_WIDTH - l.getWidth(), APPLICATION_HEIGHT - l.getHeight() + 10);
	}
}
