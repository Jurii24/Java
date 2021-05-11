package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest;

public abstract class TeestResult {
    public abstract ResultTypeHolder.ResultType getType();

    public static TeestResult success() {
        return SuccessResult.INSTANCE;
    }

    public static TeestResult failure() {
        return FailureResult.INSTANCE;
    }

    public static TeestResult exception(Exception e) {
        return new ExceptionResult(e);
    }
}
