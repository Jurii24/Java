package com.shpp.p2p.cs.yparfeniuk.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        for (int i = 0, numRow = toneMatrix.length; i < numRow; i++) {
            if (toneMatrix[i][column]) {
                result = addNewSound(result, samples[i]);
            }
        }
        return normalizeResult(result);
    }

    /**
     * The method normalizes the sound intensity in the array
     *
     * @param result An array of sums of sounds intensities
     * @return Normalized array of sound intensity (from -1.0 to +1.0)
     */
    private static double[] normalizeResult(double[] result) {
        double maxValue = findMaxValue(result); //max value in array (result)

        if (maxValue > 1 || maxValue < -1) { //if max value is not normal (-1 to +1), the array will be normalize
            for (int i = 0, countTones = result.length; i < countTones; i++) {
                result[i] = result[i] / maxValue; //array normalization
            }
        }
        return result;
    }

    /**
     * The method searches as far as possible from the allowable sound intensity (in array)
     *
     * @param result Array of the sum of the intensity of sounds
     * @return The value is as far away from the allowable sound intensity as possible (in array)
     */
    private static double findMaxValue(double[] result) {
        double minValue = 0.0, maxValue = 0.0;

        for (double tone : result) {
            if (tone > maxValue) {
                maxValue = tone;
            } else if (tone < minValue) {
                minValue = tone;
            }
        }
        return (maxValue > -minValue) ? maxValue : minValue;
    }

    /**
     * This method adds samples of some sound to result,
     * and creates new combination of sounds
     *
     * @param result Combination of sounds
     * @param sample Samples of some sound
     * @return result = result + sample (new combination of sounds)
     */
    private static double[] addNewSound(double[] result, double[] sample) {
        for (int i = 0, resLength = result.length; i < resLength; i++) {
            result[i] += sample[i];
        }
        return result;
    }
}
