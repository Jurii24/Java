package com.shpp.p2p.cs.yparfeniuk.assignment2;

import java.awt.Color;
import java.awt.Graphics;

import com.shpp.cs.a.graphics.WindowProgram;

import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

/*
 * This program draws the second optical illusion
 */
public class Assignment2Part5 extends WindowProgram {
	/* The number of rows and columns in the grid, respectively. */
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 6;

	/* The width and height of each box and color */
	private static final double BOX_SIZE = 40;
	private static final Color BOX_COLOR = Color.BLACK;

	/* The horizontal and vertical spacing between the boxes. */
	private static final double BOX_SPACING = 10;

	// All space of boxes
	public static final double BOXES_WIGTH = BOX_SIZE * NUM_COLS + (NUM_COLS - 1) * BOX_SPACING;
	public static final double BOXES_HEIGHT = BOX_SIZE * NUM_ROWS + (NUM_ROWS - 1) * BOX_SPACING;

	/*
	 * In another size of free space between boxes or size of box or num rows or
	 * columns the program will build another window
	 */
	public static final int APPLICATION_WIDTH = (int) (BOXES_WIGTH + BOX_SIZE);
	public static final int APPLICATION_HEIGHT = (int) (BOXES_HEIGHT + BOX_SIZE);

	public void run() {
		add(new GObject() { // it for setting new window size
			public void paint(Graphics arg0) {
				setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); // add new size of window
			}

			public GRectangle getBounds() { // Auto-generated method stub
				return null;
			}
		});
		drawBox();
	}

	private void drawBox() {
		for (int i = 0; i < NUM_ROWS; i++) { // draw every row
			for (int j = 0; j < NUM_COLS; j++) { // draw every column in the row
				drawRects(i, j);
			}
		}
	}

	private void drawRects(int numRow, int numColumn) {
		double valueOfX = numColumn * BOX_SIZE + BOX_SPACING * numColumn; // calculating the rect position on x
		double valueOfY = numRow * BOX_SIZE + BOX_SPACING * numRow; // calculating the rect position on y
		GRect r = new GRect(valueOfX, valueOfY, BOX_SIZE, BOX_SIZE); // create new rect
		r.setColor(BOX_COLOR);
		r.setFilled(true);
		add(r); // add this rect to window
		r.move(APPLICATION_WIDTH / 2 - BOXES_WIGTH / 2, APPLICATION_HEIGHT / 2 - BOXES_HEIGHT / 2); // move to center
	}
}
