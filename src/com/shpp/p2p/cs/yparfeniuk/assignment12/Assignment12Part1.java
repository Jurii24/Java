/*
This program counts silhouettes on a monochrome image (using Depth-First Search).
If no arguments are entered, the default file name will be used.
Also checks the file name.
This program has a filter (FILTER_FOR_OBJECT) to clear the image of unnecessary pixels.
 */
package com.shpp.p2p.cs.yparfeniuk.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* WARNING!!!
If you received "java.lang.StackOverflowError"
please increase your stack memory (-Xss24m is normal).
Please adjust the sensitivity of your program (change FILTER_FOR_OBJECT)
 */

public class Assignment12Part1 extends Thread {
    private static final String DEFAULT_FILE = "test.jpg"; //default file name (or file path)
    private static final int MIN_FILENAME = 5; //min length of file name

    private static final int MAX_VALUE_ARGB = 255; //max value every of ARGB parameter

    /**
     * Permissible deviation of the silhouette search
     */
    private static final int FILTER_FOR_OBJECT = 100; //please adjust the sensitivity of your program

    /**
     * Calculated permissible deviation of the silhouette search
     */
    private static int diapason;

    /**
     * Max and Min number in image[][]
     */
    private static int maxPixelValue = 0;
    private static int minPixelValue;

    /**
     * Array of sums of all pixel colors (including alpha)
     */
    private static int[][] image;

    /**
     * Coordinates of all vertices of silhouettes
     */
    private static ArrayList<String> silhouet = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 0 && checkFileName(DEFAULT_FILE)) {
            findSilhouettes(DEFAULT_FILE);
        } else if (args.length == 1 && checkFileName(args[0])) {
            findSilhouettes(args[0]);
        } else {
            System.err.println("Wrong number of arguments!!!\n" +
                    "Enter the file name as the first argument,\n" +
                    "otherwise (without arguments) will be the default value\n" +
                    "(file name in the root of the program, near folder \"src\")\n" +
                    "will be the file \"test.jpg\"");
        }
    }

    /**
     * This method checks filename or file path
     *
     * @param fileName file name
     * @return true, if the file name has been verified
     */
    private static boolean checkFileName(String fileName) {
        return fileName.length() >= MIN_FILENAME && fileName.contains(".");
    }

    /**
     * This method starts a method that parses the image
     * and then runs a method for counting silhouettes and prints the result
     *
     * @param fileName file name
     */
    private static void findSilhouettes(String fileName) {
        try {
            parseImage(ImageIO.read(new File(fileName)));
            System.out.println(DepthFirstSearch());
        } catch (IOException exception) {
            System.err.println(exception);
        }
    }

    /**
     * This method reads the image and writes it to the array image[][].
     * Also find maxPixelValue and minPixelValue
     *
     * @param bufferedImage buffered Image
     */
    private static void parseImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Color pixel;
        image = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixel = new Color(bufferedImage.getRGB(j, i), true);
                image[i][j] = pixel.getRed() + pixel.getGreen() + pixel.getBlue()
                        + (MAX_VALUE_ARGB - pixel.getAlpha()); //calculate the color given the alpha channel
                maxPixelValue = Math.max(image[i][j], maxPixelValue);
                minPixelValue = i == 0 ? image[i][j] : Math.min(image[i][j], minPixelValue);
            }
        }
        checkMaxAndMinPixels();
    }

    /**
     * This method compares values maxPixelValue and minPixelValue.
     * If they are the same then there are no silhouettes
     */
    private static void checkMaxAndMinPixels() {
        if (maxPixelValue == minPixelValue) {
            System.out.println("No silhouettes found!");
            System.exit(0);
        }
    }

    /**
     * This method searches for the first vertex of the silhouette
     * and starts searching for all vertices of this silhouette.
     * The method scans the entire image for all possible silhouettes
     *
     * @return number of silhouettes
     */
    private static int DepthFirstSearch() {
        int countSilhouet = 0;
        diapason = minPixelValue + FILTER_FOR_OBJECT; //allowable range

        for (int i = 0; i < image.length - 1; i++) {
            for (int j = 0; j < image[i].length - 1; j++) {
                if (image[i][j] <= diapason && !silhouet.contains(i + ";" + j)) { //new silhouet
                    lookVertices(i, j); //find all vertices
                    countSilhouet++; //new silhouet is found
                }
            }
        }
        return countSilhouet;
    }

    /**
     * This method receives the position of the first pixel of the silhouette
     * and looks at each pixel of the whole silhouette.
     * The method checks the coordinates of each pixel of the silhouette
     *
     * @param i column number
     * @param j row number
     */
    private static void lookVertices(int i, int j) {
        silhouet.add(i + ";" + j); //coordinate checked
        try {
            if (j + 1 <= image[0].length - 1 && image[i][j + 1] <= diapason && !silhouet.contains(i + ";" + (j + 1))) {
                lookVertices(i, j + 1); //right
            }
            if (i + 1 <= image.length - 1 && image[i + 1][j] <= diapason && !silhouet.contains((i + 1) + ";" + j)) {
                lookVertices(i + 1, j); //bottom
            }
            if (j >= 1 && image[i][j - 1] <= diapason && !silhouet.contains(i + ";" + (j - 1))) {
                lookVertices(i, j - 1); //left
            }
            if (i >= 1 && image[i - 1][j] <= diapason && !silhouet.contains((i - 1) + ";" + j)) {
                lookVertices(i - 1, j); //top
            }

            //auxiliary vertices
            if (i + 1 <= image.length - 1 && j + 1 <= image[0].length - 1 &&
                    image[i + 1][j + 1] <= diapason && !silhouet.contains((i + 1) + ";" + (j + 1))) {
                lookVertices(i + 1, j + 1);
            }
            if (i + 1 <= image.length - 1 && j >= 1 &&
                    image[i + 1][j - 1] <= diapason && !silhouet.contains((i + 1) + ";" + (j - 1))) {
                lookVertices(i + 1, j - 1);
            }
            if (i >= 1 && j >= 1 && image[i - 1][j - 1] <= diapason && !silhouet.contains((i - 1) + ";" + (j - 1))) {
                lookVertices(i - 1, j - 1);
            }
            if (i >= 1 && j + 1 <= image[0].length - 1 &&
                    image[i - 1][j + 1] <= diapason && !silhouet.contains((i - 1) + ";" + (j + 1))) {
                lookVertices(i - 1, j + 1);
            }
        } catch (StackOverflowError stackOverflowError) {
            System.out.println("Please, add image with less silhouet or resize your stack memory");
            System.err.println(stackOverflowError);
            System.exit(0);
        }
    }
}