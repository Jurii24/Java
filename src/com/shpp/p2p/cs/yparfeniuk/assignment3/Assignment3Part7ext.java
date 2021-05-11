package com.shpp.p2p.cs.yparfeniuk.assignment3;

import java.util.Scanner;

public class Assignment3Part7ext {
    public static double money = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("What year do you plan to retire? ");
        int rateYear = sc.nextInt();

        System.out.print("What year do you plan to start saving? ");
        int startDate = sc.nextInt();

        System.out.print("How much $ per year do you plan to save? ");
        int sum = sc.nextInt();

        System.out.println();
        money += sum;
        System.out.println("In " + rateYear + ", you'd have around $" + countMoney(rateYear - startDate, sum));
    }

    public static double countMoney(int years, int sum) {
        if (years != 0) {
            money += (money * 0.075 + sum);
            countMoney(--years, sum);
        }
        return money;
    }
}
