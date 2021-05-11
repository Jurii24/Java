package com.shpp.p2p.cs.yparfeniuk.assignment6.sg;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLine;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SteganographyDrawingCanvas extends GCanvas implements MouseListener, MouseMotionListener, SteganographyConstants {
    private double lastX;
    private double lastY;

    public SteganographyDrawingCanvas() {
        this.setPreferredSize(new Dimension(400, 200));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setCursor(Cursor.getPredefinedCursor(1));
    }

    public void clear() {
        this.removeAll();
    }

    public void mouseDragged(MouseEvent e) {
        this.add(new GLine(this.lastX, this.lastY, (double) e.getX(), (double) e.getY()));
        this.lastX = (double) e.getX();
        this.lastY = (double) e.getY();
    }

    public void mousePressed(MouseEvent e) {
        this.lastX = (double) e.getX();
        this.lastY = (double) e.getY();
        this.add(new GLine(this.lastX, this.lastY, this.lastX, this.lastY));
    }

    public void setImage(GImage image) {
        this.clear();
        this.add(new GImage(image.getPixelArray()));
    }

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    public void mouseMoved(MouseEvent arg0) {
    }
}
