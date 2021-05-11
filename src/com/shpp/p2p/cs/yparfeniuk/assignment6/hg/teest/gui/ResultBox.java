package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest.gui;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.io.IODialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest.*;

public class ResultBox extends GCanvas implements MouseListener, ComponentListener {
    private static final int SIZE = 50;
    private static final Map resultToDisplay = buildResultToDisplayMap();
    private static final Color DEFAULT_COLOR;
    private static final String DEFAULT_MESSAGE = "Running";
    private final GRect rect;
    private final GLabel label;
    private volatile TeestResult result;

    static {
        DEFAULT_COLOR = Color.YELLOW;
    }

    private static Map buildResultToDisplayMap() {
        HashMap result = new HashMap();
        result.put(ResultTypeHolder.ResultType.SUCCESS, new ResultBox.ResultInfo(Color.GREEN, "Pass"));
        result.put(ResultTypeHolder.ResultType.FAILURE, new ResultBox.ResultInfo(new Color(255, 127, 0), "Fail"));
        result.put(ResultTypeHolder.ResultType.EXCEPTION, new ResultBox.ResultInfo(Color.RED, "Error!"));
        return result;
    }

    public ResultBox() {
        this.setPreferredSize(new Dimension(50, 50));
        this.rect = new GRect(0.0D, 0.0D, 0.0D, 0.0D);
        this.rect.setFilled(true);
        this.rect.setColor(DEFAULT_COLOR);
        this.add(this.rect);
        this.label = new GLabel("");
        this.label.setFont("SansSerif-BOLD-10");
        this.add(this.label);
        this.setText("Running");
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addComponentListener(this);
        this.addMouseListener(this);
    }

    private void setText(String message) {
        this.label.setLabel(message);
        this.label.setLocation(((double) this.getWidth() - this.label.getWidth()) / 2.0D, ((double) this.getHeight() + this.label.getAscent()) / 2.0D);
    }

    public void setResult(TeestResult result) {
        this.result = result;
        ResultBox.ResultInfo info = (ResultBox.ResultInfo) resultToDisplay.get(result.getType());
        this.rect.setColor(info.color);
        this.setText(info.text);
    }

    public void mouseClicked(MouseEvent arg0) {
        if (this.result != null) {
            (new IODialog()).println(this.result);
        } else {
            (new IODialog()).println("This test is still running.\nIf the test does not complete soon, it might be stuck in an infinite loop.");
        }

    }

    public void componentResized(ComponentEvent e) {
        this.rect.setBounds(0.0D, 0.0D, (double) this.getWidth(), (double) this.getHeight());
        this.setText(this.label.getLabel());
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    private static final class ResultInfo {
        public final Color color;
        public final String text;

        public ResultInfo(Color c, String t) {
            this.color = c;
            this.text = t;
        }
    }
}
