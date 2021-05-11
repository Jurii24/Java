package com.shpp.p2p.cs.yparfeniuk.assignment2;

//import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
//import acm.graphics.GRectangle;

import java.awt.*;

import com.shpp.cs.a.graphics.WindowProgram;

/*
 * This program draws the first optical illusion
 */
public class Assignment2Part2 extends WindowProgram {
	// Oval parameters
	public static final int CIRCLE_RADIUS = 100;
	public static final Color CIRCLE_COLOR = Color.BLACK;

	// Rect parameters
	public static final int RECT_WIDTH = 350;
	public static final int RECT_HEIGHT = 300;
	public static final Color RECT_COLOR = Color.WHITE;

	// In another size of circle and rect the program will build another window
	public static final int APPLICATION_WIDTH = RECT_WIDTH + CIRCLE_RADIUS * 2;
	public static final int APPLICATION_HEIGHT = RECT_HEIGHT + CIRCLE_RADIUS * 2;

	public void run() {
		/*add(new GObject() { // it for setting new window size
			public void paint(Graphics arg0) {
				setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); // add new size of window
			}

			public GRectangle getBounds() { // Auto-generated method stub
				return null;
			}
		});*/
		//this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		drawOvals();
		drawRect();
	}

	// This method draws all ovals
	private void drawOvals() {
		for (int i = 0; i <= RECT_HEIGHT; i += RECT_HEIGHT) {
			for (int j = 0; j <= RECT_WIDTH; j += RECT_WIDTH) { // draws two oval in row
				GOval ov = new GOval(j, i, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2); // create new oval
				ov.setColor(CIRCLE_COLOR);
				ov.setFilled(true); // oval will be all painted
				add(ov); // add this oval to window
			}
		}
	}

	// This method draws a rect
	private void drawRect() {
		GRect r = new GRect(CIRCLE_RADIUS, CIRCLE_RADIUS, RECT_WIDTH, RECT_HEIGHT);
		r.setColor(RECT_COLOR);
		r.setFilled(true); // rect will be all painted
		add(r); // add this rect to window
	}
}
