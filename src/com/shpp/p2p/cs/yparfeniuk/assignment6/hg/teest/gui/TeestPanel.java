package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest.gui;

import acm.gui.TablePanel;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest.*;

public class TeestPanel extends TablePanel {
    private final TeestSuite testSuite;
    private final Map teestRows = new HashMap();
    private static final int SPACING = 5;

    public TeestPanel(TeestSuite suite) {
        super(suite.numTests() + 1, 1, 5, 5);
        this.testSuite = suite;
        JLabel name = new JLabel(suite.getName(), 0);
        this.add(name);
        Iterator var4 = this.testSuite.iterator();

        while (var4.hasNext()) {
            TeestCase teest = (TeestCase) var4.next();
            TeestCaseRow row = new TeestCaseRow(teest);
            this.teestRows.put(teest, row);
            this.add(row, "anchor=WEST");
        }

        this.setBorder(new LineBorder(Color.BLACK, 1));
    }

    public void testCompleted(TeestCase test, TeestResult result) {
        ((TeestCaseRow) this.teestRows.get(test)).setResult(result);
    }
}
