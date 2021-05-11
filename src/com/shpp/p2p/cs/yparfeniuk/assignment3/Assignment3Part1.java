/*
 * Program to checks the performance of aerobics exercises for a week.
 */
package com.shpp.p2p.cs.yparfeniuk.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Check your cardiovascular health and blood pressure in a week
 */
public class Assignment3Part1 extends TextProgram {
    //constant values for Cardiovascular Health and Blood Pressure with normal
    private static final int CARDIOVASCULAR_HEALTH = 5;
    private static final int MINUTES_CARDIOVASCULAR_HEALTH = 30;
    private static final int BLOOD_PRESSURE = 3;
    private static final int MINUTES_BLOOD_PRESSURE = 40;

    //global values for checking days with cardiovascular health and blood pressure
    private static int daysForCardiovascularHealth = 0;
    private static int daysForBloodPressure = 0;

    @Override
    public void run() {
        takeMinutesOnEveryDayInAWeek();
        statisticsForAWeek();
    }

    /**
     * User input how many minutes did he do aerobics on day
     * and this method analise this value and
     * change global values daysForCardiovascularHealth and daysForBloodPressure.
     * Normal is no less than 30 minutes for cardiovascular health and no less than 40 minutes for blood pressure
     */
    private void takeMinutesOnEveryDayInAWeek() {
        int dayInAWeek = 1, minuteOnDay; //the first dayInAWeek is Monday

        while (dayInAWeek <= 7) {
            try {
                minuteOnDay = readInt("How many minutes did you do on day " + dayInAWeek + "? ");
                if (minuteOnDay <= 0) {  //value minuteOnDay may be not important for statistics
                    continue;
                }

                if (minuteOnDay >= MINUTES_BLOOD_PRESSURE) {
                    ++daysForCardiovascularHealth;
                    ++daysForBloodPressure;
                } else if (minuteOnDay >= MINUTES_CARDIOVASCULAR_HEALTH) {
                    ++daysForCardiovascularHealth;
                }

                dayInAWeek++; //next day

            } catch (Exception exception) { //catch some exception
                println("Sorry, minutes must be integer variable (only some numbers)");
                break;
            }
        }
    }

    /**
     * This method whether there was enough exercise to lower blood pressure and cholesterol,
     * and if not, how many days the user did not last 40 or 30 minutes a day.
     * Normal is no less than 5 days for cardiovascular health and no less than 3 days for blood pressure
     */
    private void statisticsForAWeek() {
        println("\nCardiovascular health:");    //show analised Cardiovascular health
        if (daysForCardiovascularHealth < CARDIOVASCULAR_HEALTH) {
            println("You needed to train hard for at least "
                    + (CARDIOVASCULAR_HEALTH - daysForCardiovascularHealth) + " more day(s) a week!");
        } else {
            println("Great job! You've done enough exercise for cardiovascular health.");
        }

        println("Blood pressure:"); //show analised Blood pressure
        if (daysForBloodPressure < BLOOD_PRESSURE) {
            println("You needed to train hard for at least "
                    + (BLOOD_PRESSURE - daysForBloodPressure) + " more day(s) a week!");
        } else {
            println("Great job! You've done enough exercise to keep a low blood pressure.");
        }
    }
}
