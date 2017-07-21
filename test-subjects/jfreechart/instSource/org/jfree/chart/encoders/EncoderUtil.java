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
 * ----------------
 * EncoderUtil.java
 * ----------------
 * (C) Copyright 2004-2007, by Richard Atkinson and Contributors.
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

/**
 * A collection of utility methods for encoding images and returning them as a 
 * byte[] or writing them directly to an OutputStream.
 */
public class EncoderUtil {
  static {
    CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.ping();
  }


    /**
     * Encode the image in a specific format.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * 
     * @return The byte[] that is the encoded image.
     * @throws IOException
     */
    public static byte[] encode(BufferedImage image, String format) 
        throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[1]++;
        ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format);
        return imageEncoder.encode(image);
    }

    /**
     * Encode the image in a specific format.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param encodeAlpha  Whether to encode alpha transparency (not supported 
     *                     by all ImageEncoders).
     * @return The byte[] that is the encoded image.
     * @throws IOException
     */
    public static byte[] encode(BufferedImage image, String format,
                                boolean encodeAlpha) throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[2]++;
        ImageEncoder imageEncoder 
            = ImageEncoderFactory.newInstance(format, encodeAlpha);
        return imageEncoder.encode(image);
    }

    /**
     * Encode the image in a specific format.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param quality  The quality to use for the image encoding (not supported
     *                 by all ImageEncoders).
     * @return The byte[] that is the encoded image.
     * @throws IOException
     */
    public static byte[] encode(BufferedImage image, String format,
                                float quality) throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[3]++;
        ImageEncoder imageEncoder 
            = ImageEncoderFactory.newInstance(format, quality);
        return imageEncoder.encode(image);
    }

    /**
     * Encode the image in a specific format.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param quality  The quality to use for the image encoding (not supported 
     *                 by all ImageEncoders).
     * @param encodeAlpha  Whether to encode alpha transparency (not supported 
     *                     by all ImageEncoders).
     * @return The byte[] that is the encoded image.
     * @throws IOException
     */
    public static byte[] encode(BufferedImage image, String format,
                                float quality, boolean encodeAlpha) 
        throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[4]++;
        ImageEncoder imageEncoder 
            = ImageEncoderFactory.newInstance(format, quality, encodeAlpha);
        return imageEncoder.encode(image);
    }

    /**
     * Encode the image in a specific format and write it to an OutputStream.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @throws IOException
     */
    public static void writeBufferedImage(BufferedImage image, String format, 
        OutputStream outputStream) throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[5]++;
        ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format);
        imageEncoder.encode(image, outputStream);
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[6]++;
    }

    /**
     * Encode the image in a specific format and write it to an OutputStream.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @param quality  The quality to use for the image encoding (not 
     *                 supported by all ImageEncoders).
     * @throws IOException
     */
    public static void writeBufferedImage(BufferedImage image, String format, 
        OutputStream outputStream, float quality) throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[7]++;
        ImageEncoder imageEncoder 
            = ImageEncoderFactory.newInstance(format, quality);
        imageEncoder.encode(image, outputStream);
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[8]++;
    }

    /**
     * Encode the image in a specific format and write it to an OutputStream.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @param encodeAlpha  Whether to encode alpha transparency (not 
     *                     supported by all ImageEncoders).
     * @throws IOException
     */
    public static void writeBufferedImage(BufferedImage image, String format, 
        OutputStream outputStream, boolean encodeAlpha) throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[9]++;
        ImageEncoder imageEncoder 
            = ImageEncoderFactory.newInstance(format, encodeAlpha);
        imageEncoder.encode(image, outputStream);
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[10]++;
    }

    /**
     * Encode the image in a specific format and write it to an OutputStream.
     *
     * @param image  The image to be encoded.
     * @param format  The {@link ImageFormat} to use.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @param quality  The quality to use for the image encoding (not 
     *                 supported by all ImageEncoders).
     * @param encodeAlpha  Whether to encode alpha transparency (not supported 
     *                     by all ImageEncoders).
     * @throws IOException
     */
    public static void writeBufferedImage(BufferedImage image, String format, 
        OutputStream outputStream, float quality, boolean encodeAlpha) 
        throws IOException {
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[11]++;
        ImageEncoder imageEncoder 
            = ImageEncoderFactory.newInstance(format, quality, encodeAlpha);
        imageEncoder.encode(image, outputStream);
CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1.statements[12]++;
    }

}

class CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$43z79rxshgtubczc37f2mlzs1 () {
    super("org.jfree.chart.encoders.EncoderUtil.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.encoders.EncoderUtil.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

