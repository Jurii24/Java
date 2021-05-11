package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest;

public abstract class TeestCase {
    public abstract String getName();

    public abstract boolean runTest();

    public final TeestResult resultOf() {
        try {
            return this.runTest() ? TeestResult.success() : TeestResult.failure();
        } catch (Exception var2) {
            return TeestResult.exception(var2);
        }
    }
}
