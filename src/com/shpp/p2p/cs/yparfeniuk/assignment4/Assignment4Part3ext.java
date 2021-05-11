package com.shpp.p2p.cs.yparfeniuk.assignment4;

import com.shpp.cs.a.console.TextProgram;

public class Assignment4Part3ext extends TextProgram {
    //constant for application parameters
    public void run() {
        while (true) {
            String digits = readLine("Enter a numeric string: ");
            if (digits.length() == 0)
                break;
            println(addCommasToNumericString(digits));
        }
    }

    private String addCommasToNumericString(String digits) {
        if (digits.length() <= 3) {
            return digits;
        }
        String bDigits = "";
        for (int i = digits.length() - 1; i >= 0; i--) {
            if (((digits.length() - 1 - i) % 3 == 0) && (i != digits.length() - 1)) {
                bDigits = "," + bDigits;
            }
            bDigits = digits.charAt(i) + bDigits;
        }
        return bDigits;
    }
}