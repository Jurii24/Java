package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TeestSuite implements Iterable {

    private final List testCases;
    private final String name;

    public TeestSuite(String name, TeestCase... testCases) {
        this(name, Arrays.asList(testCases));
    }

    public TeestSuite(String name, List testCases) {
        this.testCases = new ArrayList(testCases);
        this.name = name;
    }

    public int numTests() {
        return this.testCases.size();
    }

    public String getName() {
        return this.name;
    }

    public TeestCase getTestCase(int index) {
        return (TeestCase) this.testCases.get(index);
    }

    public Iterator iterator() {
        return this.testCases.iterator();
    }
}
