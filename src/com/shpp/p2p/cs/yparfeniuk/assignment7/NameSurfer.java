package com.shpp.p2p.cs.yparfeniuk.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

/* Warning!! The field "textField" must be active to use the key "Enter". Sorry for my english, I'm try */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    /**
     * Draw new empty graph
     */
    private final NameSurferGraph graph = new NameSurferGraph();

    private NameSurferDataBase dataBase; // variable for database

    JTextField textField; //variable for text field in window

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    @Override
    public void init() {
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        addInteractors();
        add(graph);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Graph")) {
            graph.addEntry(dataBase.findEntry(textField.getText()));
            add(graph);
        } else if (e.getActionCommand().equals("Clear")) {
            textField.setText("");
            graph.clear();
        }
    }

    /**
     * This method add new interactors to window
     * and add some listeners (action and "Enter" key)
     */
    private void addInteractors() {
        this.add(new JLabel("Name:"), "North");
        this.add(textField = new JTextField(TEXT_FIELD_LENGTH), "North");
        this.add(new JButton("Graph"), "North");
        this.add(new JButton("Clear"), "North");
        this.addActionListeners();
        clickEnterListener();
    }

    /**
     * This method checks if the user has pressed the "Enter" button
     */
    private void clickEnterListener() {
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) { //it must be
            }

            /**
             * This method checks which key is pressed and fill graph
             *
             * @param keyEvent the key is pressed
             */
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    graph.addEntry(dataBase.findEntry(textField.getText()));
                    add(graph);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) { //it also must be
            }
        });
    }
}