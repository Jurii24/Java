package com.shpp.p2p.cs.yparfeniuk.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment3Part9ext extends WindowProgram {
    //constant for application parameters
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 800;

    public static final int PAUSE = 100;

    public static final int SUN_DIAMETER = 100;

    public static final double Y_FOR_EARTH = APPLICATION_HEIGHT - APPLICATION_HEIGHT / 4.0;

    GOval sun;
    GRect earth;

    @Override
    public void run() {
        createBackground();
        createSun();

        moveSun();
    }

    private void moveSun() {
        sun.move(0, 2);
        add(earth);
        pause(PAUSE);
        if (sun.getY() >= Y_FOR_EARTH) {
            return;
        }
        moveSun();
    }

    private void createSun() {
        sun = new GOval(getWidth() / 2.0 - SUN_DIAMETER / 2.0, getHeight() / 2.0 - SUN_DIAMETER / 2.0,
                SUN_DIAMETER, SUN_DIAMETER);
        sun.setFilled(true);
        sun.setColor(Color.YELLOW);
        add(sun);
    }

    private void createBackground() {
        GRect sky = new GRect(0, 0, getWidth(), getHeight());
        sky.setColor(Color.BLUE);
        sky.setFilled(true);
        add(sky);

        earth = new GRect(0, Y_FOR_EARTH, getWidth(), Y_FOR_EARTH);
        earth.setColor(Color.GREEN);
        earth.setFilled(true);
        earth.setVisible(true);
        add(earth);
    }
}
