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
 * ------------------------------
 * KeypointPNGEncoderAdapter.java
 * ------------------------------
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
import java.io.IOException;
import java.io.OutputStream;

import com.keypoint.PngEncoder;

/**
 * Adapter class for the Keypoint PNG Encoder.  The ImageEncoderFactory will 
 * only return a reference to this class by default if the library has been 
 * compiled under a JDK < 1.4 or is being run using a JDK < 1.4.
 */
public class KeypointPNGEncoderAdapter implements ImageEncoder {
  static {
    CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.ping();
  }

    
    /** The quality setting. */
    private int quality = 9;
  {
    CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[1]++;
  }
    
    /** Encode alpha? */
    private boolean encodingAlpha = false;
  {
    CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[2]++;
  }

    /**
     * Get the quality of the image encoding.  The underlying encoder uses int 
     * values:  0 for no compression, and values 1 through 9 for various levels
     * of compression (1 is best speed, 9 is best compression).
     *
     * @return A float representing the quality.
     */
    public float getQuality() {
        return this.quality;
    }

    /**
     * Set the quality of the image encoding (supported).  The underlying
     * encoder uses int values:  0 for no compression, and values 1 through 9 
     * for various levels of compression (1 is best speed, 9 is best 
     * compression).
     *
     * @param quality  A float representing the quality.
     */
    public void setQuality(float quality) {
        this.quality = (int) quality;
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[3]++;
    }

    /**
     * Get whether the encoder should encode alpha transparency.
     *
     * @return Whether the encoder is encoding alpha transparency.
     */
    public boolean isEncodingAlpha() {
        return this.encodingAlpha;
    }

    /**
     * Set whether the encoder should encode alpha transparency (supported).
     *
     * @param encodingAlpha  Whether the encoder should encode alpha 
     *                       transparency.
     */
    public void setEncodingAlpha(boolean encodingAlpha) {
        this.encodingAlpha = encodingAlpha;
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[4]++;
    }

    /**
     * Encodes an image in PNG format.
     *
     * @param bufferedImage  The image to be encoded.
     * @return The byte[] that is the encoded image.
     * @throws IOException
     */
    public byte[] encode(BufferedImage bufferedImage) throws IOException {
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((bufferedImage == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.branches[1]++;
            throw new IllegalArgumentException("Null 'image' argument.");

        } else {
  CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.branches[2]++;}
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[6]++;
        PngEncoder encoder = new PngEncoder(bufferedImage, this.encodingAlpha, 
                0, this.quality);
        return encoder.pngEncode();
    }

    /**
     * Encodes an image in PNG format and writes it to an 
     * <code>OutputStream</code>.
     *
     * @param bufferedImage  The image to be encoded.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @throws IOException
     */
    public void encode(BufferedImage bufferedImage, OutputStream outputStream) 
        throws IOException {
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bufferedImage == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.branches[3]++;
            throw new IllegalArgumentException("Null 'image' argument.");

        } else {
  CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.branches[4]++;}
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((outputStream == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.branches[5]++;
            throw new IllegalArgumentException("Null 'outputStream' argument.");

        } else {
  CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.branches[6]++;}
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[9]++;
        PngEncoder encoder = new PngEncoder(bufferedImage, this.encodingAlpha, 
                0, this.quality);
        outputStream.write(encoder.pngEncode());
CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81.statements[10]++;
    }

}

class CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.chart.encoders.KeypointPNGEncoderAdapter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$1c5svad65qzv1u6mbrmc9qsngzzrr06dg98orvs5rv02c81 () {
    super("org.jfree.chart.encoders.KeypointPNGEncoderAdapter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.encoders.KeypointPNGEncoderAdapter.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

