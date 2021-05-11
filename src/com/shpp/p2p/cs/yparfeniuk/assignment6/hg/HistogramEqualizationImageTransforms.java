package com.shpp.p2p.cs.yparfeniuk.assignment6.hg;

import acm.graphics.GImage;

public class HistogramEqualizationImageTransforms {
    public static GImage toGrayscale(GImage image) {
        int[][] pixels = image.getPixelArray();

        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                int intensity = (int) (0.3D * (double) GImage.getRed(pixels[row][col]) + 0.59D * (double) GImage.getGreen(pixels[row][col]) + 0.11D * (double) GImage.getBlue(pixels[row][col]) + 0.5D);
                pixels[row][col] = GImage.createRGBPixel(intensity, intensity, intensity);
            }
        }

        return new GImage(pixels);
    }

    public static int[][] imageToLuminances(int[][] pixels) {
        int[][] luminances = new int[pixels.length][pixels[0].length];

        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                luminances[row][col] = GImage.getRed(pixels[row][col]);
            }
        }

        return luminances;
    }
}
