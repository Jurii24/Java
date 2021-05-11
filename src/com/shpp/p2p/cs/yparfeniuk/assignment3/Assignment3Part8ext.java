package com.shpp.p2p.cs.yparfeniuk.assignment3;

import acm.util.RandomGenerator;

import java.util.Scanner;

public class Assignment3Part8ext {
    public static int money1 = 0;
    public static int money2 = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("money = ");
        money1 = money2 = sc.nextInt();

        boolean first = true;
        RandomGenerator orel = new RandomGenerator();
        while (money1 != 0 && money2 != 0) {
            if (orel.nextBoolean(0.5)) {
                if(first){
                    --money1;
                    ++money2;
                } else {
                    --money2;
                    ++money1;
                }
            }
            first = !first;
            System.out.println("First player = " + money1 + "\nSecond player = " + money2);
        }
        if (money1 == 0) {
            System.out.println("Second player is win!!!\nHis money = " + money2);
        }
        if (money2 == 0) {
            System.out.println("First player is win!!!\nHis money = " + money1);
        }
    }
}
