/*
 * This program builds some animation for 5 sec.
 */
package com.shpp.p2p.cs.yparfeniuk.assignment3;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment3Part6 extends WindowProgram {
    //constant for application parameters
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 800;

    //constant for rect parameters
    public static final int RECT_SIZE = 600;
    private static final double RECT_HALF = RECT_SIZE / 2.0;

    //global variable for basic x and y axis
    private static double valueXForBasicRect;
    private static double valueYForBasicRect;

    //global variable for another smaller rect
    private static int rectWidthOneSide = RECT_SIZE;
    private static int rectWidthSecondSide = 0;
    private static int rectHeight = 0;

    @Override
    public void run() {
        //calculate x and y axis
        valueXForBasicRect = getWidth() / 2.0 - RECT_HALF;
        valueYForBasicRect = getHeight() / 2.0 - RECT_HALF;

        //timer for look how many sec long animation
        long startTime = System.currentTimeMillis();
        startAnimation(); //start animation
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

    /**
     * This method for animation settings and start every animation
     */
    private void startAnimation() {
        //some settings before animation
        GRect rectOne = createRect(0, 0, RECT_SIZE, RECT_SIZE);
        GRect rectSecond = createRect(RECT_SIZE, 0, 1, RECT_SIZE); //second rect in start have 1 pixel width

        //create first animation with change x axis in 1 pixels
        makeAnimation(rectOne, rectSecond, 1, 0);
        pause(500);

        //some settings for second animation. Here every rect have 1 pixels height in start
        GRect rectThird = createRect(RECT_HALF, 0, RECT_HALF, 1);
        GRect rectFourth = createRect(0, RECT_SIZE, RECT_HALF, 1);

        //create second animation with change y axis
        makeAnimation(rectThird, rectFourth, 0, 1);
        pause(500);

        //create final animation
        changeColorsFourRects(rectOne, rectSecond, rectThird, rectFourth); //final animation with changes colors
    }

    /**
     * This method makes some animation with set parameters
     */
    private void makeAnimation(GRect rectOne, GRect rectSecond, int x, int y) {
        while (rectWidthOneSide != RECT_HALF || ((y != 0) && rectHeight != RECT_HALF)) { //loop start new animation
            if (y == 0) {   //check what animation is must to be
                rectWidthOneSide -= x;
                rectWidthSecondSide += x;
                rectOne.setSize(rectWidthOneSide, RECT_SIZE);
                rectSecond.setSize(rectWidthSecondSide, RECT_SIZE);
            } else {
                rectHeight += y;
                rectOne.setSize(RECT_HALF, rectHeight);
                rectSecond.setSize(RECT_HALF, rectHeight);
            }

            rectSecond.move(-x, -y);
            pause(5);
        }
    }

    /**
     * This method changes color in four rects
     */
    private void changeColorsFourRects(GRect rectOne, GRect rectSecond, GRect rectThird, GRect rectFourth) {
        for (int i = 0; i < 7; i++) {
            rectOne.setColor(createNewColor());
            rectSecond.setColor(createNewColor());
            rectThird.setColor(createNewColor());
            rectFourth.setColor(createNewColor());

            pause(i * 38);  //calculate pause time (because main animation, must have 5 sec)
        }
    }

    /**
     * This method creates and returns new rect with set parameters
     */
    private GRect createRect(double x, double y, double rectWidth, double rectHeight) {
        GRect rect = new GRect(valueXForBasicRect + x, valueYForBasicRect + y, rectWidth, rectHeight);
        rect.setFilled(true);
        rect.setColor(createNewColor());
        add(rect);

        return rect;
    }

    /**
     * This method creates and returns new random color
     */
    private static Color createNewColor() {
        RandomGenerator random = new RandomGenerator();
        return random.nextColor();
    }
}
