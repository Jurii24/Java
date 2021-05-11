package com.shpp.p2p.cs.yparfeniuk.assignment6.tm;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

public final class StdAudio {
    public static final int SAMPLE_RATE = 44100;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int BITS_PER_SAMPLE = 16;
    private static final double MAX_16_BIT = 32767.0D;
    private static final int SAMPLE_BUFFER_SIZE = 4096;
    private static SourceDataLine line;
    private static byte[] buffer;
    private static int bufferSize = 0;

    static {
        init();
    }

    private static void init() {
        try {
            AudioFormat e = new AudioFormat(44100.0F, 16, 1, true, false);
            Info info = new Info(SourceDataLine.class, e);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(e, 8192);
            buffer = new byte[2730];
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
            System.exit(1);
        }

        line.start();
    }

    public static void play(double in) {
        if (in < -1.0D) {
            in = -1.0D;
        }

        if (in > 1.0D) {
            in = 1.0D;
        }

        short s = (short) ((int) (32767.0D * in));
        buffer[bufferSize++] = (byte) s;
        buffer[bufferSize++] = (byte) (s >> 8);
        if (bufferSize >= buffer.length) {
            line.write(buffer, 0, buffer.length);
            bufferSize = 0;
        }

    }

    public static void flush() {
        bufferSize = 0;
    }

    public static void play(double[] input) {
        for (int i = 0; i < input.length; ++i) {
            play(input[i]);
        }

        flush();
    }
}
