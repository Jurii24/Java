/*
 * This program builds a pyramid, it`s base lies on the bottom line of the window and is located in the center
 */
package com.shpp.p2p.cs.yparfeniuk.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {
    // Size for programs window
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;

    //parameters for pyramid
    public static final int BRICK_HEIGHT = 20;
    public static final int BRICK_WIDTH = 50;
    public static final int BRICKS_IN_BASE = 15;
    public static final Color BRICK_COLOR = Color.ORANGE;
    public static final Color BRICK_BORDER_COLOR = Color.RED;

    public static final double BRICK_HALF = BRICK_WIDTH / 2.0;

    //global variable for start row
    private static double valueX = 0.0;
    private static double valueY = 0.0;

    @Override
    public void run() {
        //set basic parameters for x and y axis.
        valueX = (getWidth() - BRICK_WIDTH * BRICKS_IN_BASE) / 2.0;   // find start position x when "/2.0". Use this (APPLICATION_WIDTH + 10) instead getWidth() if you use linux os
        valueY = getHeight() - BRICK_HEIGHT - 1;    // "-1" for brick border color. Use this (APPLICATION_HEIGHT - 28.0) instead getHeight() if you use linux os
        /*I think the top menu sometimes doesn't have time to draw.
         Therefore, use the above recommendations if you are using Linux.
         Sometimes, pyramid can draws on another lines.
         Sorry, I use Linux, I don`t know how it`s work in Windows and can check it*/

        drawPyramid();
    }

    /**
     * This method draws every row in pyramid
     */
    private void drawPyramid() {
        for (int i = 1; i <= BRICKS_IN_BASE; i++) {
            buildBricksLine(i); //method for build every brick in row
        }
    }

    /**
     * This method draws every brick in every row
     */
    private void buildBricksLine(int numRows) {
        for (int i = 0, n = BRICKS_IN_BASE - numRows; i <= n; i++) {    //draw every brick in a row
            drawRect().setLocation(valueX + BRICK_WIDTH * i, valueY); //set location for new rect
        }

        //change x and y axis for next row
        valueX += BRICK_HALF;
        valueY -= BRICK_HEIGHT;
    }

    //draw and add to window new rect
    private GRect drawRect() {
        GRect rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        rect.setFilled(true);
        rect.setColor(BRICK_BORDER_COLOR);
        rect.setFillColor(BRICK_COLOR);
        add(rect);

        return rect; //return rect
    }
}
