package com.shpp.p2p.cs.yparfeniuk.assignment2;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

import java.awt.Color;
import java.awt.Graphics;

import com.shpp.cs.a.graphics.WindowProgram;

/*
 * This program draw face robot
 */
public class Assignment2Part7ext extends WindowProgram {
	// Head parameters
	public static final int HEAD_WIDTH = 200;
	public static final int HEAD_HEIGHT = 200;
	public static final Color HEAD_COLOR = Color.GREEN;

	// eyes parameters
	public static final double EYE_RADIUS = 10;
	public static final Color EYE_COLOR = Color.RED;

	// mouth parameters
	public static final double MOUTH_WIDTH = 100;
	public static final double MOUTH_HEIGHT = 20;
	public static final Color MOUTH_COLOR = Color.BLUE;

	// In another size of head the program will build another window
	public static final int APPLICATION_WIDTH = HEAD_WIDTH + HEAD_WIDTH / 4;
	public static final int APPLICATION_HEIGHT = HEAD_HEIGHT + HEAD_HEIGHT / 4;

	public void run() {
		add(new GObject() { // it for setting new window size
			public void paint(Graphics arg0) {
				setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); // add new size of window
			}

			public GRectangle getBounds() { // Auto-generated method stub
				return null;
			}
		});
		drawFaceRobot();
	}

	private void drawFaceRobot() {
		GRect r = new GRect(HEAD_WIDTH / 8, HEAD_HEIGHT / 8, HEAD_WIDTH, HEAD_HEIGHT);
		r.setColor(HEAD_COLOR);
		r.setFilled(true);
		add(r);
		drawEye();
		drawMouth();
	}

	private void drawEye() {
		GOval o;
		for (int i = 0; i <= HEAD_WIDTH / 2; i += HEAD_WIDTH / 2) {
			o = new GOval(APPLICATION_WIDTH - HEAD_WIDTH + HEAD_WIDTH / 8 + i - EYE_RADIUS,
					APPLICATION_HEIGHT - HEAD_HEIGHT + HEAD_HEIGHT / 8 - EYE_RADIUS, EYE_RADIUS * 2, EYE_RADIUS * 2);
			o.setColor(EYE_COLOR);
			o.setFilled(true);
			add(o);
		}
	}

	private void drawMouth() {
		GRect r = new GRect(APPLICATION_WIDTH / 2 - MOUTH_WIDTH / 2,
				APPLICATION_HEIGHT - HEAD_HEIGHT / 4 - HEAD_HEIGHT / 8 - MOUTH_HEIGHT / 2, MOUTH_WIDTH, MOUTH_HEIGHT);
		r.setColor(MOUTH_COLOR);
		r.setFilled(true);
		add(r);
	}
}
