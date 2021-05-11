package com.shpp.p2p.cs.yparfeniuk.assignment6.tm;

/**
 * This class contains methods that produce constants you might find useful when
 * implementing the Tone Matrix.
 * <p/>
 * Due to the way that JAR files work, we couldn't make this an interface while still
 * letting you change the constant if you wanted to experiment with larger or smaller
 * tone matrices.  Please do not use this code as a reference for defining constants;
 * you should almost always define constants using static final variables.
 */
public class ToneMatrixConstants {
    /**
     * Returns the size of one side of the tone matrix.
     *
     * @return The size of one side of the tone matrix.
     * @usage ToneMatrixConstants.size()
     */
    public static int size() {
        return 16;
    }

    /**
     * Returns the length, in samples, of the array that should be used for each note.  Decreasing this
     * value will make the notes play faster, while increasing it will make the notes play more slowly.
     * <p/>
     * By default, this function returns a sample that's one-eighth of a second long.
     */
    public static int sampleSize() {
        return StdAudio.SAMPLE_RATE / 8;
    }
}
