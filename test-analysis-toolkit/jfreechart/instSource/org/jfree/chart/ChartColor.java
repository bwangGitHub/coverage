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
 * ---------------
 * ChartColor.java
 * ---------------
 * (C) Copyright 2003-2007, by Cameron Riley and Contributors.
 *
 * Original Author:  Cameron Riley;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1, contributed by Cameron Riley (DG);
 * 25-Nov-2004 : Changed first 7 colors to softer shades (DG);
 * 03-Nov-2005 : Removed orange color, too close to yellow - see bug
 *               report 1328408 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart;

import java.awt.Color;
import java.awt.Paint;

/**
 * Class to extend the number of Colors available to the charts. This
 * extends the java.awt.Color object and extends the number of final
 * Colors publically accessible.
 */
public class ChartColor extends Color {
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.ping();
  }


    /** A very dark red color. */
    public static final Color VERY_DARK_RED = new Color(0x80, 0x00, 0x00);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[1]++;
  }

    /** A dark red color. */
    public static final Color DARK_RED = new Color(0xc0, 0x00, 0x00);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[2]++;
  }

    /** A light red color. */
    public static final Color LIGHT_RED = new Color(0xFF, 0x40, 0x40);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[3]++;
  }

    /** A very light red color. */
    public static final Color VERY_LIGHT_RED = new Color(0xFF, 0x80, 0x80);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[4]++;
  }

    /** A very dark yellow color. */
    public static final Color VERY_DARK_YELLOW = new Color(0x80, 0x80, 0x00);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[5]++;
  }

    /** A dark yellow color. */
    public static final Color DARK_YELLOW = new Color(0xC0, 0xC0, 0x00);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[6]++;
  }

    /** A light yellow color. */
    public static final Color LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x40);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[7]++;
  }

    /** A very light yellow color. */
    public static final Color VERY_LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x80);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[8]++;
  }

    /** A very dark green color. */
    public static final Color VERY_DARK_GREEN = new Color(0x00, 0x80, 0x00);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[9]++;
  }

    /** A dark green color. */
    public static final Color DARK_GREEN = new Color(0x00, 0xC0, 0x00);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[10]++;
  }

    /** A light green color. */
    public static final Color LIGHT_GREEN = new Color(0x40, 0xFF, 0x40);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[11]++;
  }

    /** A very light green color. */
    public static final Color VERY_LIGHT_GREEN = new Color(0x80, 0xFF, 0x80);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[12]++;
  }

    /** A very dark cyan color. */
    public static final Color VERY_DARK_CYAN = new Color(0x00, 0x80, 0x80);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[13]++;
  }

    /** A dark cyan color. */
    public static final Color DARK_CYAN = new Color(0x00, 0xC0, 0xC0);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[14]++;
  }

    /** A light cyan color. */
    public static final Color LIGHT_CYAN = new Color(0x40, 0xFF, 0xFF);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[15]++;
  }

    /** Aa very light cyan color. */
    public static final Color VERY_LIGHT_CYAN = new Color(0x80, 0xFF, 0xFF);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[16]++;
  }

    /** A very dark blue color. */
    public static final Color VERY_DARK_BLUE = new Color(0x00, 0x00, 0x80);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[17]++;
  }

    /** A dark blue color. */
    public static final Color DARK_BLUE = new Color(0x00, 0x00, 0xC0);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[18]++;
  }

    /** A light blue color. */
    public static final Color LIGHT_BLUE = new Color(0x40, 0x40, 0xFF);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[19]++;
  }

    /** A very light blue color. */
    public static final Color VERY_LIGHT_BLUE = new Color(0x80, 0x80, 0xFF);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[20]++;
  }

    /** A very dark magenta/purple color. */
    public static final Color VERY_DARK_MAGENTA = new Color(0x80, 0x00, 0x80);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[21]++;
  }

    /** A dark magenta color. */
    public static final Color DARK_MAGENTA = new Color(0xC0, 0x00, 0xC0);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[22]++;
  }

    /** A light magenta color. */
    public static final Color LIGHT_MAGENTA = new Color(0xFF, 0x40, 0xFF);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[23]++;
  }

    /** A very light magenta color. */
    public static final Color VERY_LIGHT_MAGENTA = new Color(0xFF, 0x80, 0xFF);
  static {
    CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[24]++;
  }

    /**
     * Creates a Color with an opaque sRGB with red, green and blue values in 
     * range 0-255.
     *
     * @param r  the red component in range 0x00-0xFF.
     * @param g  the green component in range 0x00-0xFF.
     * @param b  the blue component in range 0x00-0xFF.
     */
    public ChartColor(int r, int g, int b) {
        super(r, g, b);
CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29.statements[25]++;
    }

    /**
     * Convenience method to return an array of <code>Paint</code> objects that
     * represent the pre-defined colors in the <code>Color<code> and 
     * <code>ChartColor</code> objects.
     *
     * @return An array of objects with the <code>Paint</code> interface.
     */
    public static Paint[] createDefaultPaintArray() {

        return new Paint[] {
            new Color(0xFF, 0x55, 0x55),
            new Color(0x55, 0x55, 0xFF),
            new Color(0x55, 0xFF, 0x55),
            new Color(0xFF, 0xFF, 0x55),
            new Color(0xFF, 0x55, 0xFF),
            new Color(0x55, 0xFF, 0xFF),
            Color.pink,
            Color.gray,
            ChartColor.DARK_RED,
            ChartColor.DARK_BLUE,
            ChartColor.DARK_GREEN,
            ChartColor.DARK_YELLOW,
            ChartColor.DARK_MAGENTA,
            ChartColor.DARK_CYAN,
            Color.darkGray,
            ChartColor.LIGHT_RED,
            ChartColor.LIGHT_BLUE,
            ChartColor.LIGHT_GREEN,
            ChartColor.LIGHT_YELLOW,
            ChartColor.LIGHT_MAGENTA,
            ChartColor.LIGHT_CYAN,
            Color.lightGray,
            ChartColor.VERY_DARK_RED,
            ChartColor.VERY_DARK_BLUE,
            ChartColor.VERY_DARK_GREEN,
            ChartColor.VERY_DARK_YELLOW,
            ChartColor.VERY_DARK_MAGENTA,
            ChartColor.VERY_DARK_CYAN,
            ChartColor.VERY_LIGHT_RED,
            ChartColor.VERY_LIGHT_BLUE,
            ChartColor.VERY_LIGHT_GREEN,
            ChartColor.VERY_LIGHT_YELLOW,
            ChartColor.VERY_LIGHT_MAGENTA,
            ChartColor.VERY_LIGHT_CYAN
        };
    }

}

class CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$k7aznibt4wrml7to5irrl29 () {
    super("org.jfree.chart.ChartColor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
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
    log.startNamedSection("org.jfree.chart.ChartColor.java");
      for (int i = 1; i <= 25; i++) {
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

