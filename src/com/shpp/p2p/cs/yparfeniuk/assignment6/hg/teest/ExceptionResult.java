package com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionResult extends TeestResult {
    private final Exception cause;

    public ExceptionResult(Exception xcause) {
        cause = xcause;
    }

    public ResultTypeHolder.ResultType getType() {
        return ResultTypeHolder.ResultType.EXCEPTION;
    }

    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        cause.printStackTrace(pw);
        return "An exception occurred during the test.  This means that your method did not finish running and instead produced this error:\n" + sw.toString();
    }
}
