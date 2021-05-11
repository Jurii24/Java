package com.shpp.p2p.cs.yparfeniuk.assignment6.tm;

import acm.graphics.GCanvas;
import acm.graphics.GRect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ToneMatrixDisplay extends GCanvas implements MouseListener, MouseMotionListener, ComponentListener {
    private static final int GSCALE = 20;
    private static final Color LIT_COLOR = new Color(232, 232, 232);
    private static final Color UNLIT_COLOR = (new Color(134, 110, 255)).darker();
    private static final Color HIGHLIGHT_RECT_COLOR = new Color(255, 255, 0, 128);
    private static final Color BACKGROUND_COLOR;
    private boolean[][] matrix = new boolean[ToneMatrixConstants.size()][ToneMatrixConstants.size()];
    private GRect[][] matrixRects = new GRect[ToneMatrixConstants.size()][ToneMatrixConstants.size()];
    private double xScale;
    private double yScale;
    private double xTrans;
    private double yTrans;
    private boolean isMarking;
    private GRect highlightRect;
    private volatile int highlightedColumn = -1;

    static {
        BACKGROUND_COLOR = Color.GRAY;
    }

    public ToneMatrixDisplay() {
        this.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < ToneMatrixConstants.size(); ++i) {
            for (int j = 0; j < ToneMatrixConstants.size(); ++j) {
                this.matrixRects[i][j] = new GRect(0.0D, 0.0D, 0.0D, 0.0D);
                this.matrixRects[i][j].setFilled(true);
                this.matrixRects[i][j].setColor(Color.BLACK);
                this.matrixRects[i][j].setFillColor(UNLIT_COLOR);
                this.add(this.matrixRects[i][j]);
            }
        }

        this.highlightRect = new GRect(0.0D, 0.0D, 0.0D, 0.0D);
        this.highlightRect.setFilled(true);
        this.highlightRect.setColor(HIGHLIGHT_RECT_COLOR);
        this.add(this.highlightRect);
        this.setPreferredSize(new Dimension(ToneMatrixConstants.size() * 20, ToneMatrixConstants.size() * 20));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
    }

    private void redrawAll() {
        for (int row = 0; row < ToneMatrixConstants.size(); ++row) {
            for (int col = 0; col < ToneMatrixConstants.size(); ++col) {
                this.matrixRects[row][col].setBounds(this.xScale * (double) col + this.xTrans, this.yScale * (double) row + this.yTrans, this.xScale, this.yScale);
                this.matrixRects[row][col].setFillColor(this.matrix[row][col] ? LIT_COLOR : UNLIT_COLOR);
            }
        }

        if (this.highlightedColumn == -1) {
            this.highlightRect.setBounds(0.0D, 0.0D, 0.0D, 0.0D);
        } else {
            this.highlightRect.setBounds(this.xScale * (double) this.highlightedColumn + this.xTrans, this.yTrans, this.xScale, this.yScale * (double) ToneMatrixConstants.size());
        }

    }

    private void recalculateGeometry() {
        this.xScale = (double) this.getWidth() / (double) ToneMatrixConstants.size();
        this.yScale = (double) this.getHeight() / (double) ToneMatrixConstants.size();
        this.xScale = this.yScale = Math.min(this.xScale, this.yScale);
        this.xTrans = ((double) this.getWidth() - this.xScale * (double) ToneMatrixConstants.size()) / 2.0D;
        this.yTrans = ((double) this.getHeight() - this.yScale * (double) ToneMatrixConstants.size()) / 2.0D;
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && col >= 0 && row < ToneMatrixConstants.size() && col < ToneMatrixConstants.size();
    }

    private void markAt(int row, int col, boolean set) {
        if (this.inBounds(row, col)) {
            this.matrix[row][col] = set;
            this.matrixRects[row][col].setFillColor(set ? LIT_COLOR : UNLIT_COLOR);
        }

    }

    public void clear() {
        for (int i = 0; i < ToneMatrixConstants.size(); ++i) {
            for (int j = 0; j < ToneMatrixConstants.size(); ++j) {
                this.markAt(i, j, false);
            }
        }

    }

    public void mouseDragged(MouseEvent e) {
        int x = (int) (((double) e.getX() - this.xTrans) / this.xScale);
        int y = (int) (((double) e.getY() - this.yTrans) / this.yScale);
        this.markAt(y, x, this.isMarking);
    }

    public void mousePressed(MouseEvent e) {
        int x = (int) (((double) e.getX() - this.xTrans) / this.xScale);
        int y = (int) (((double) e.getY() - this.yTrans) / this.yScale);
        if (this.inBounds(y, x)) {
            this.isMarking = !this.matrix[y][x];
        }

        this.markAt(y, x, this.isMarking);
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

    public void componentHidden(ComponentEvent arg0) {
    }

    public void componentMoved(ComponentEvent arg0) {
    }

    public void componentShown(ComponentEvent arg0) {
    }

    public void componentResized(ComponentEvent arg0) {
        this.recalculateGeometry();
        this.redrawAll();
    }

    public boolean[][] getMatrix() {
        boolean[][] result = new boolean[ToneMatrixConstants.size()][ToneMatrixConstants.size()];

        for (int i = 0; i < ToneMatrixConstants.size(); ++i) {
            for (int j = 0; j < ToneMatrixConstants.size(); ++j) {
                result[ToneMatrixConstants.size() - 1 - i][j] = this.matrix[i][j];
            }
        }

        return result;
    }

    public void setMatrix(boolean[][] newMatrix) {
        if (newMatrix == null) {
            throw new NullPointerException("setMatrix cannot be called on a null matrix.");
        } else if (newMatrix.length != ToneMatrixConstants.size()) {
            throw new IllegalArgumentException("Incorrect size for setMatrix");
        } else {
            int row;
            for (row = 0; row < newMatrix.length; ++row) {
                if (newMatrix[row] == null) {
                    throw new IllegalArgumentException("Inner array is null.");
                }

                if (newMatrix[row].length != ToneMatrixConstants.size()) {
                    throw new IllegalArgumentException("Incorrect size for setMatrix");
                }
            }

            for (row = 0; row < ToneMatrixConstants.size(); ++row) {
                for (int col = 0; col < ToneMatrixConstants.size(); ++col) {
                    this.matrix[ToneMatrixConstants.size() - 1 - row][col] = newMatrix[row][col];
                }
            }

            this.redrawAll();
        }
    }

    public void highlightColumn(int column) {
        if (column < 0 || column >= ToneMatrixConstants.size()) {
            column = -1;
        }

        this.highlightedColumn = column;
        this.redrawAll();
    }
}
