package com.shpp.p2p.cs.yparfeniuk.assignment10;

import com.shpp.p2p.cs.yparfeniuk.assignment10.Assignment10Part1;

import java.util.Arrays;

public class Assignment10Part1Test {
    public static void main(String[] args) {
        checkCase(new String[] {"10+20+30"},                                          "60.0");
        checkCase(new String[] {"x", "x=2.5"},                                      "2.5");
        checkCase(new String[] {"x+3", "x=2"},                                      "5.0");
        checkCase(new String[] {"x+5+x+3+x+1+x", "x=3"},                            "21.0");
        checkCase(new String[] {"x", "x=2"},                                      "2.0");
        checkCase(new String[] {"x+5", "x=2"},                                  "7.0");
        checkCase(new String[] {"-10", "x=2"},                                      "-10.0");
        checkCase(new String[] {"-  10", "x=2"},                                    "-10.0");
        checkCase(new String[] {"-x", "x=2"},                                       "-2.0");
        checkCase(new String[] {"-  x", "x=2"},                                     "-2.0");
        checkCase(new String[] {"-10+ -x", "x=2"},                                 "-12.0");
        checkCase(new String[] {"-20-x", "x=2"},                                  "-22.0");
        checkCase(new String[] {"x-10", "x=2"},                                     "-8.0");
        checkCase(new String[] {"-x-10", "x=2"},                                    "-12.0");
/**/        checkCase(new String[] {"10+10-20+20", "x=2"},                              "20.0");
        checkCase(new String[] {"10-10+20-20", "x=2"},                              "0.0");
        checkCase(new String[] {"x*x", "x=2"},                                      "4.0");
/**/        checkCase(new String[] {"x+3*3", "x=2"},                                    "11.0");
/**/        checkCase(new String[] {"1+x-3*4+5-6", "x=2"},                              "-10.0");
        checkCase(new String[] {"x*2*-x*2*x*2  *  2*2 ", "x=2"},                  "-256.0");
        checkCase(new String[] {"-x*-x", "x=2"},                                  "4.0");
        checkCase(new String[] {"4/x", "x=2"},                                      "2.0");
/**/        checkCase(new String[] {"256/2/x/2", "x=2"},                                "32.0");
/**/        checkCase(new String[] {"5*6/x*3", "x=2"},                                  "45.0");
        checkCase(new String[] {"6/3*5/4", "x=2"},                                  "2.5");
        checkCase(new String[] {"10/-x", "x=2"},                                  "-5.0");
/**/        checkCase(new String[] {"1+x-3*4/-2", "x=2"},                             "9.0");
        checkCase(new String[] {"2^x", "x=2"},                                      "4.0");
        checkCase(new String[] {"-x^2", "x=2"},                                     "4.0");
        checkCase(new String[] {"-x^3", "x=2"},                                     "-8.0");
        checkCase(new String[] {"2^-x", "x=2"},                                   "0.25");
        checkCase(new String[] {"x^2^2", "x=2"},                                    "16.0");
        checkCase(new String[] {"2^2^2^2", "x=2"},                              "65536.0");
        checkCase(new String[] {"x-y-z", "x=10", "y=-20", "z=-30"},                 "60.0");
        checkCase(new String[] {"10"},                                              "10.0");
        checkCase(new String[] {"x", "x=10", "y=20"},                               "10.0");
        checkCase(new String[] {"0/0"},                                             "exception. ");
        checkCase(new String[] {"x", "y=10"},                                       "exception. ");
        checkCase(new String[] {"", "x=2"},                                         "exception. ");
    }

    private static void checkCase(String[] args, String expected) {
        System.out.println("expression and variables: " + Arrays.toString(args));
        System.out.print("   actual: ");
        try {
            Assignment10Part1.main(args);
//            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" expected: " + expected + "\n");
    }
}
