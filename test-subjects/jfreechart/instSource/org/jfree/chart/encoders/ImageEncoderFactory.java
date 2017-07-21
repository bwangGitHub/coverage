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
 * ------------------------
 * ImageEncoderFactory.java
 * ------------------------
 * (C) Copyright 2004-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 01-Aug-2004 : Initial version (RA);
 * 01-Nov-2005 : Now using ImageIO for JPEG encoding, so we no longer have a
 *               dependency on com.sun.* which isn't available on all 
 *               implementations (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.encoders;

import java.util.Hashtable;

/**
 * Factory class for returning {@link ImageEncoder}s for different 
 * {@link ImageFormat}s.
 */
public class ImageEncoderFactory {
  static {
    CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.ping();
  }

    
    /** Storage for the encoders. */
    private static Hashtable encoders = null;
  static {
    CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[1]++;
  }

    static {
        init();
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[2]++;
    }

    /**
     * Sets up default encoders (uses Sun PNG Encoder if JDK 1.4+ and the
     * SunPNGEncoderAdapter class is available).
     */
    private static void init() {
        encoders = new Hashtable();
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[3]++;
        encoders.put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[4]++;
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[5]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            //  Test for being run under JDK 1.4+
            Class.forName("javax.imageio.ImageIO");
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[6]++;
            //  Test for JFreeChart being compiled under JDK 1.4+
            Class.forName("org.jfree.chart.encoders.SunPNGEncoderAdapter");
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[7]++;
            encoders.put("png", 
                    "org.jfree.chart.encoders.SunPNGEncoderAdapter");
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[8]++;
            encoders.put("jpeg",
                    "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[9]++;
        } 
        catch (ClassNotFoundException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.branches[2]++;
            encoders.put("png", 
                    "org.jfree.chart.encoders.KeypointPNGEncoderAdapter");
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[10]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.branches[1]++;
}
  }
    }

    /**
     * Used to set additional encoders or replace default ones.
     *
     * @param format  The image format name.
     * @param imageEncoderClassName  The name of the ImageEncoder class.
     */
    public static void setImageEncoder(String format, 
                                       String imageEncoderClassName) {
        encoders.put(format, imageEncoderClassName);
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[11]++;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format  The image format required.
     * 
     * @return The ImageEncoder or <code>null</code> if none available.
     */
    public static ImageEncoder newInstance(String format) {
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[12]++;
        ImageEncoder imageEncoder = null;
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[13]++;
        String className = (String) encoders.get(format);
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((className == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.branches[3]++;
            throw new IllegalArgumentException("Unsupported image format - " 
                    + format);

        } else {
  CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.branches[4]++;}
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[15]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[16]++;
            Class imageEncoderClass = Class.forName(className);
            imageEncoder = (ImageEncoder) imageEncoderClass.newInstance();
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[17]++;
        } 
        catch (Exception e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.branches[6]++;
            throw new IllegalArgumentException(e.toString());
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.branches[5]++;
}
  }
        return imageEncoder;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format  The image format required.
     * @param quality  The quality to be set before returning.
     * 
     * @return The ImageEncoder or <code>null</code> if none available.
     */
    public static ImageEncoder newInstance(String format, float quality) {
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[18]++;
        ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setQuality(quality);
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[19]++;
        return imageEncoder;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format  The image format required.
     * @param encodingAlpha  Sets whether alpha transparency should be encoded.
     * 
     * @return The ImageEncoder or <code>null</code> if none available.
     */
    public static ImageEncoder newInstance(String format, 
                                           boolean encodingAlpha) {
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[20]++;
        ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setEncodingAlpha(encodingAlpha);
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[21]++;
        return imageEncoder;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format  The image format required.
     * @param quality  The quality to be set before returning.
     * @param encodingAlpha  Sets whether alpha transparency should be encoded.
     * 
     * @return The ImageEncoder or <code>null</code> if none available.
     */
    public static ImageEncoder newInstance(String format, float quality, 
                                           boolean encodingAlpha) {
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[22]++;
        ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setQuality(quality);
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[23]++;
        imageEncoder.setEncodingAlpha(encodingAlpha);
CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x.statements[24]++;
        return imageEncoder;
    }

}

class CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.encoders.ImageEncoderFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$gx8o6urfatvigwfuiugq9ut6xeefngolzn68x () {
    super("org.jfree.chart.encoders.ImageEncoderFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.encoders.ImageEncoderFactory.java");
      for (int i = 1; i <= 24; i++) {
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
    for (int i = 1; i <= 1; i++) {
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

