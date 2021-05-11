package com.shpp.p2p.cs.yparfeniuk.assignment2;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRectangle;

import java.awt.Color;
import java.awt.Graphics;

import com.shpp.cs.a.graphics.WindowProgram;

/*
 * This program draw caterpillar
 */
public class Assignment2Part6 extends WindowProgram {
	// Circle parameters
	public static final int CIRCLE_NUM = 6;
	public static final double CIRCLE_RADIUS = 90;
	public static final Color CIRCLE_COLOR = Color.GREEN;
	public static final Color BORDER_OF_CIRCLE = Color.RED;

	// In another size of circle radius or circle num the program will build another
	// window
	public static final int APPLICATION_WIDTH = (int) (CIRCLE_RADIUS * CIRCLE_NUM + CIRCLE_RADIUS) + 1;
	public static final int APPLICATION_HEIGHT = (int) ((CIRCLE_RADIUS * 2) / 3 + CIRCLE_RADIUS * 2) + 100;

	public void run() {
		add(new GObject() { // it for setting new window size
			public void paint(Graphics arg0) {
				setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); // add new size of window
			}

			public GRectangle getBounds() { // Auto-generated method stub
				return null;
			}
		});
		drawСaterpillar();
	}

	private void drawСaterpillar() {
		GOval ov;
		for (int i = 0; i < CIRCLE_NUM; i++) { // build the caterpillar
			if (i % 2 == 0) { // calculating for oval position
				ov = new GOval(i * CIRCLE_RADIUS, (CIRCLE_RADIUS * 2) / 3, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
			} else {
				ov = new GOval(i * CIRCLE_RADIUS, 0, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
			}
			ov.setColor(BORDER_OF_CIRCLE);
			ov.setFilled(true);
			ov.setFillColor(CIRCLE_COLOR);
			add(ov); // add this oval to window
		}
	}
}
