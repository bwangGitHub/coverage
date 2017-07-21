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
 * --------------------------
 * SunJPEGEncoderAdapter.java
 * --------------------------
 * (C) Copyright 2004-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 01-Aug-2004 : Initial version (RA);
 * 01-Nov-2005 : To remove the dependency on non-supported APIs, use ImageIO 
 *               instead of com.sun.image.codec.jpeg.JPEGImageEncoder - this 
 *               adapter will only be available on JDK 1.4 or later (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Pass quality setting to ImageIO. Also increased default 
 *               value to 0.95 (DG);
 * 
 */

package org.jfree.chart.encoders;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * Adapter class for the Sun JPEG Encoder.  The {@link ImageEncoderFactory} 
 * will only return a reference to this class by default if the library has 
 * been compiled under a JDK 1.4+ and is being run using a JRE 1.4+.
 */
public class SunJPEGEncoderAdapter implements ImageEncoder {
  static {
    CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.ping();
  }

    
    /** The quality setting (in the range 0.0f to 1.0f). */
    private float quality = 0.95f;
  {
    CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[1]++;
  }

    /**
     * Creates a new <code>SunJPEGEncoderAdapter</code> instance.
     */
    public SunJPEGEncoderAdapter() {
    }

    /**
     * Returns the quality of the image encoding, which is a number in the
     * range 0.0f to 1.0f (higher values give better quality output, but larger
     * file sizes).  The default value is 0.95f.
     *
     * @return A float representing the quality, in the range 0.0f to 1.0f.
     * 
     * @see #setQuality(float)
     */
    public float getQuality() {
        return this.quality;
    }

    /**
     * Set the quality of the image encoding.
     *
     * @param quality  A float representing the quality (in the range 0.0f to
     *     1.0f).
     *     
     * @see #getQuality()
     */
    public void setQuality(float quality) {
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((quality < 0.0f) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((quality > 1.0f) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.branches[1]++;
            throw new IllegalArgumentException(
                    "The 'quality' must be in the range 0.0f to 1.0f");

        } else {
  CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.branches[2]++;}
        this.quality = quality;
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[3]++;
    }

    /**
     * Returns <code>false</code> always, indicating that this encoder does not
     * encode alpha transparency.
     *
     * @return <code>false</code>.
     */
    public boolean isEncodingAlpha() {
        return false;
    }

    /**
     * Set whether the encoder should encode alpha transparency (this is not 
     * supported for JPEG, so this method does nothing).
     *
     * @param encodingAlpha  ignored.
     */
    public void setEncodingAlpha(boolean encodingAlpha) {
        //  No op
    }

    /**
     * Encodes an image in JPEG format.
     *
     * @param bufferedImage  the image to be encoded (<code>null</code> not 
     *     permitted).
     * 
     * @return The byte[] that is the encoded image.
     * 
     * @throws IOException if there is an I/O problem.
     * @throws NullPointerException if <code>bufferedImage</code> is 
     *     <code>null</code>.
     */
    public byte[] encode(BufferedImage bufferedImage) throws IOException {
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[4]++;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        encode(bufferedImage, outputStream);
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[5]++;
        return outputStream.toByteArray();
    }

    /**
     * Encodes an image in JPEG format and writes it to an output stream.
     *
     * @param bufferedImage  the image to be encoded (<code>null</code> not 
     *     permitted).
     * @param outputStream  the OutputStream to write the encoded image to 
     *     (<code>null</code> not permitted).
     * 
     * @throws IOException if there is an I/O problem.
     * @throws NullPointerException if <code>bufferedImage</code> is 
     *     <code>null</code>.
     */
    public void encode(BufferedImage bufferedImage, OutputStream outputStream) 
            throws IOException {
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bufferedImage == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.branches[3]++;
            throw new IllegalArgumentException("Null 'image' argument.");

        } else {
  CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.branches[4]++;}
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((outputStream == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.branches[5]++;
            throw new IllegalArgumentException("Null 'outputStream' argument.");

        } else {
  CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.branches[6]++;}
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[8]++;
        Iterator iterator = ImageIO.getImageWritersByFormatName("jpeg");
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[9]++;
        ImageWriter writer = (ImageWriter) iterator.next();
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[10]++;
        ImageWriteParam p = writer.getDefaultWriteParam();
        p.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[11]++;
        p.setCompressionQuality(this.quality);
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[12]++;
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[13]++;
        ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
        writer.setOutput(ios);
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[14]++;
        writer.write(null, new IIOImage(bufferedImage, null, null), p);
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[15]++;
        ios.flush();
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[16]++;
        writer.dispose();
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[17]++;
        ios.close();
CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335.statements[18]++;
    }

}

class CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.chart.encoders.SunJPEGEncoderAdapter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1};
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

  public CodeCoverCoverageCounter$r0oy4uusa2cmjwk456g4zlwq512p3wg4zakyb335 () {
    super("org.jfree.chart.encoders.SunJPEGEncoderAdapter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.jfree.chart.encoders.SunJPEGEncoderAdapter.java");
      for (int i = 1; i <= 18; i++) {
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

