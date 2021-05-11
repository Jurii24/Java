package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest;

import acm.gui.TablePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest.gui.TeestPanel;

public class TeestRunnerPanel extends TablePanel {
    private final List tests;
    private final Map panels;
    private static final int HORIZONTAL_GAP = 5;

    public TeestRunnerPanel(TeestSuite... tests) {
        this(Arrays.asList(tests));
    }

    public TeestRunnerPanel(List tests) {
        super(1, tests.size(), 5, 0);
        this.panels = new HashMap();
        this.tests = new ArrayList(tests);
        Iterator var3 = tests.iterator();

        while (var3.hasNext()) {
            TeestSuite suite = (TeestSuite) var3.next();
            TeestPanel panel = new TeestPanel(suite);
            this.panels.put(suite, panel);
            this.add(panel, "anchor=NORTH");
        }

    }

    public void runTests() {
        Iterator var2 = this.tests.iterator();

        while (var2.hasNext()) {
            final TeestSuite suite = (TeestSuite) var2.next();
            Iterator var4 = suite.iterator();

            while (var4.hasNext()) {
                final TeestCase teest = (TeestCase) var4.next();
                (new Thread() {
                    public void run() {
                        ((TeestPanel) TeestRunnerPanel.this.panels.get(suite)).testCompleted(teest, teest.resultOf());
                    }
                }).start();
            }
        }

    }
}
