package com.shpp.p2p.cs.yparfeniuk.assignment4;

import acm.graphics.GLine;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;


public class Assignment4Part2ext extends WindowProgram {
    //constant for application parameters
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 800;

    GLine sun = new GLine(0, 0, 0, 0);
    double firstPosition;
    double secondPosition;
    boolean press;

    @Override
    public void run() {
        addMouseListeners();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        super.mouseDragged(mouseEvent);
        sun.setEndPoint(mouseEvent.getX(), mouseEvent.getY());
        if (press) {
            add(sun);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        super.mousePressed(mouseEvent);
        sun = new GLine(firstPosition = mouseEvent.getX(), secondPosition = mouseEvent.getY(),
                firstPosition, secondPosition);
        press = true;
        add(sun);
    }
}
