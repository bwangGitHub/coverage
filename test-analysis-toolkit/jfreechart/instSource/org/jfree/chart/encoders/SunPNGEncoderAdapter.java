/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------------
 * SunPNGEncoderAdapter.java
 * -------------------------
 * (C) Copyright 2004, 2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 01-Aug-2004 : Initial version (RA);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.encoders;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * Adapter class for the Sun PNG Encoder.  The ImageEncoderFactory will only 
 * return a reference to this class by default if the library has been compiled 
 * under a JDK 1.4+ and is being run using a JDK 1.4+.
 */
public class SunPNGEncoderAdapter implements ImageEncoder {
  static {
    CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.ping();
  }


    /**
     * Get the quality of the image encoding (always 0.0).
     *
     * @return A float representing the quality.
     */
    public float getQuality() {
        return 0.0f;
    }

    /**
     * Set the quality of the image encoding (not supported in this 
     * ImageEncoder).
     *
     * @param quality  A float representing the quality.
     */
    public void setQuality(float quality) {
        //  No op
    }

    /**
     * Get whether the encoder should encode alpha transparency (always false).
     *
     * @return Whether the encoder is encoding alpha transparency.
     */
    public boolean isEncodingAlpha() {
        return false;
    }

    /**
     * Set whether the encoder should encode alpha transparency (not 
     * supported in this ImageEncoder).
     *
     * @param encodingAlpha  Whether the encoder should encode alpha 
     *                       transparency.
     */
    public void setEncodingAlpha(boolean encodingAlpha) {
        //  No op
    }

    /**
     * Encodes an image in PNG format.
     *
     * @param bufferedImage  The image to be encoded.
     * 
     * @return The byte[] that is the encoded image.
     * 
     * @throws IOException
     */
    public byte[] encode(BufferedImage bufferedImage) throws IOException {
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.statements[1]++;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        encode(bufferedImage, outputStream);
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.statements[2]++;
        return outputStream.toByteArray();
    }

    /**
     * Encodes an image in PNG format and writes it to an OutputStream.
     *
     * @param bufferedImage  The image to be encoded.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @throws IOException
     */
    public void encode(BufferedImage bufferedImage, OutputStream outputStream) 
        throws IOException {
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((bufferedImage == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.branches[1]++;
            throw new IllegalArgumentException("Null 'image' argument.");

        } else {
  CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.branches[2]++;}
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((outputStream == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.branches[3]++;
            throw new IllegalArgumentException("Null 'outputStream' argument.");

        } else {
  CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.branches[4]++;}
        ImageIO.write(bufferedImage, ImageFormat.PNG, outputStream);
CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l.statements[5]++;
    }

}

class CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.encoders.SunPNGEncoderAdapter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3ss9au2ll3tdnu587jpynd2mz956pkph5fg1q3l () {
    super("org.jfree.chart.encoders.SunPNGEncoderAdapter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.encoders.SunPNGEncoderAdapter.java");
      for (int i = 1; i <= 5; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
        if (loops[i * 3 - 2] != 0L) {
          log.passCounter("L" + i + "-0", loops[i * 3 - 2]);
          loops[i * 3 - 2] = 0L;
        }
        if ( loops[i * 3 - 1] != 0L) {
          log.passCounter("L" + i + "-1", loops[i * 3 - 1]);
          loops[i * 3 - 1] = 0L;
        }
        if ( loops[i * 3] != 0L) {
          log.passCounter("L" + i + "-2", loops[i * 3]);
          loops[i * 3] = 0L;
        }
      }
  }
}

