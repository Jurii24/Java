package com.shpp.p2p.cs.yparfeniuk.assignment6.tm;

import acm.graphics.GMath;

public class ToneMatrixWaveGenerator {
   private static final double[] FREQUENCIES = new double[]{130.81D, 146.83D, 164.81D, 196.0D, 220.0D};

   private static int periodOf(double frequency) {
      return GMath.round(44100.0D / frequency);
   }

   private static double[] sineWave(double frequency) {
      double[] result = new double[periodOf(frequency)];

      for(int i = 0; i < result.length; ++i) {
         result[i] = Math.sin(6.283185307179586D * (double)i / (double)result.length);
      }

      return result;
   }

   public static double[][] getSamples() {
      double[][] result = new double[ToneMatrixConstants.size()][];

      for(int note = 0; note < result.length; ++note) {
         int noteIndex = note % FREQUENCIES.length;
         double[] triangle = sineWave(FREQUENCIES[noteIndex] * Math.pow(2.0D, (double)(1 + note / FREQUENCIES.length)));
         double[] generated = new double[ToneMatrixConstants.sampleSize()];

         for(int attenuationFactor = 0; attenuationFactor < generated.length; ++attenuationFactor) {
            generated[attenuationFactor] = triangle[attenuationFactor % triangle.length];
         }

         double var10 = -1.0D / (double)generated.length;

         for(int leadInFactor = 0; leadInFactor < generated.length; ++leadInFactor) {
            generated[leadInFactor] *= 1.0D + (double)leadInFactor * var10;
         }

         double var11 = 1.0D / (double)(generated.length / 64);

         for(int x = 0; x < generated.length / 64; ++x) {
            generated[x] *= var11 * (double)x * (double)x / (double)(generated.length / 64);
         }

         result[note] = generated;
      }

      return result;
   }
}
