package com.shpp.p2p.cs.yparfeniuk.assignment2;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRectangle;

import java.awt.Color;
import java.awt.Graphics;

import com.shpp.cs.a.graphics.WindowProgram;

/*
 * This program draws two animal paws
 */
public class Assignment2Part3 extends WindowProgram {
	/*
	 * Constants controlling the relative positions of the three toes to the
	 * upper-left corner of the pawprint
	 */
	private static final double FIRST_TOE_OFFSET_X = 0;
	private static final double FIRST_TOE_OFFSET_Y = 20;
	private static final double SECOND_TOE_OFFSET_X = 30;
	private static final double SECOND_TOE_OFFSET_Y = 0;
	private static final double THIRD_TOE_OFFSET_X = 60;
	private static final double THIRD_TOE_OFFSET_Y = 20;

	// The position of the heel relative to the upper-left corner of the pawprint
	private static final double HEEL_OFFSET_X = 20;
	private static final double HEEL_OFFSET_Y = 40;

	// Each toe is an oval with this width and height
	private static final double TOE_WIDTH = 20;
	private static final double TOE_HEIGHT = 30;

	// The heel is an oval with this width and height
	private static final double HEEL_WIDTH = 40;
	private static final double HEEL_HEIGHT = 60;

	// This constant builds window with this parameters
	public static final int APPLICATION_WIDTH = 270;
	public static final int APPLICATION_HEIGHT = 220;

	public void run() {
		/*add(new GObject() { // it for setting new window size
			public void paint(Graphics arg0) {
				setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); // add new size of window
			}

			public GRectangle getBounds() { // Auto-generated method stub
				return null;
			}
		});*/
		drawPawprint(20, 20); // the paw with coordinate
		drawPawprint(180, 70);
	}

	/**
	 * Draws a pawprint. The parameters should specify the upper-left corner of the
	 * bounding box containing that pawprint.
	 * 
	 * @param x and y are coordinate of the upper-left corner of the bounding box
	 *          for the pawprint.
	 */
	private void drawPawprint(double x, double y) {
		drawHeel(x, y);
		drawToe(x, y);
	}

	// Create three toes for paw
	private void drawToe(double x, double y) {
		GOval ov = new GOval(FIRST_TOE_OFFSET_X + x, FIRST_TOE_OFFSET_Y + y, TOE_WIDTH, TOE_HEIGHT);
		ov.setColor(Color.BLACK); // this parameter as default for other toes
		ov.setFilled(true); // oval will be all painted
		add(ov); // add this element to window
		ov = new GOval(SECOND_TOE_OFFSET_X + x, SECOND_TOE_OFFSET_Y + y, TOE_WIDTH, TOE_HEIGHT);
		ov.setFilled(true);
		add(ov);
		ov = new GOval(THIRD_TOE_OFFSET_X + x, THIRD_TOE_OFFSET_Y + y, TOE_WIDTH, TOE_HEIGHT);
		ov.setFilled(true);
		add(ov);
	}

	// Draw one heel with calculate coordinates
	private void drawHeel(double x, double y) {
		GOval ov = new GOval(HEEL_OFFSET_X + x, HEEL_OFFSET_Y + y, HEEL_WIDTH, HEEL_HEIGHT);
		ov.setColor(Color.BLACK);
		ov.setFilled(true);
		add(ov);
	}
}
