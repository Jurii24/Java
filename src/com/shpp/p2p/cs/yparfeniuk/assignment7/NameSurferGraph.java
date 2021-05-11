package com.shpp.p2p.cs.yparfeniuk.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
    /**
     * ArrayList of selected names (NameSurferEntry, name with rank)
     */
    private ArrayList<NameSurferEntry> selectedNames = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        selectedNames = new ArrayList<>();
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (entry != null && !selectedNames.contains(entry)) {
            selectedNames.add(entry);
            update();
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();

        int timeLine = getWidth() / NDECADES;

        createGraph(timeLine);

        if (selectedNames.size() != 0) {
            fillTheGraph(timeLine);
        }
    }

    /**
     * This method draw calculates the color for the name
     * and calls the method for drawing the data of each name.
     *
     * @param timeLine decade
     */
    private void fillTheGraph(int timeLine) {
        for (NameSurferEntry selectedName : selectedNames) {
            Color colorForName = GRAPHICS_COLOR[selectedNames.indexOf(selectedName) % GRAPHICS_COLOR.length];
            drawData(timeLine, selectedName, colorForName);
        }
    }

    /**
     * This method draws data about the selected names to the graph
     *
     * @param timeLine     decade
     * @param selectedName some row with data for name
     * @param color        color line and text for one name
     */
    private void drawData(int timeLine, NameSurferEntry selectedName, Color color) {
        int vectorY, nextVectorY = 0;

        for (int i = 0; i < NDECADES - 1; i++) {
            vectorY = calculateNewPosition(selectedName.getRank(i));
            nextVectorY = calculateNewPosition(selectedName.getRank(i + 1));

            drawNameLabel(timeLine, selectedName, i, vectorY, color); //label with name

            GLine line = new GLine(i * timeLine, vectorY, (i + 1) * timeLine, nextVectorY);
            line.setColor(color);
            add(line);
        }
        drawNameLabel(timeLine, selectedName, NDECADES - 1, nextVectorY, color); // for last decade (2010)
    }

    /**
     * This method adds a label with a name to the graph (if rank name == 0, draw * (else draw rank))
     *
     * @param timeLine     decade
     * @param selectedName some name
     * @param i            a specific string in the array of names
     * @param vectorY      vector y for label
     * @param color        color text
     */
    private void drawNameLabel(int timeLine, NameSurferEntry selectedName, int i, int vectorY, Color color) {
        GLabel name = new GLabel("" + selectedName.getName() + ((selectedName.getRank(i) == 0) ? " *"
                : selectedName.getRank(i)), i * timeLine, vectorY);
        name.setColor(color);
        add(name);
    }

    /**
     * This method calculates the position for the line on the drawn scale
     *
     * @param rank name rating
     * @return position on the chart
     */
    private int calculateNewPosition(int rank) {
        if (rank == 0) {
            return getHeight() - GRAPH_MARGIN_SIZE;
        }
        return GRAPH_MARGIN_SIZE + ((rank * (getHeight() - 2 * GRAPH_MARGIN_SIZE)) / MAX_RANK);
    }

    /**
     * This method creates a new blank template for the graph.
     * Draws two horizontal lines and 12 vertical lines with the name of the years
     *
     * @param timeLine a line that separates decades
     */
    private void createGraph(int timeLine) {
        for (int i = 0; i < NDECADES; i++) {
            int vectorX = i * timeLine;

            add(new GLine(vectorX, 0, vectorX, getHeight()));
            add(new GLabel("" + (START_DECADE + i * DECADE), vectorX + 2, getHeight() - 2)); //2 is retreats

            if (i < 2) {
                int vectorY = (i == 0) ? GRAPH_MARGIN_SIZE : getHeight() - GRAPH_MARGIN_SIZE;
                add(new GLine(0, vectorY, getWidth(), vectorY));
            }
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}