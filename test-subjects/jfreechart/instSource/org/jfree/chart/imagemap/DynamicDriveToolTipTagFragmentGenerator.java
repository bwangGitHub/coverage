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
 * --------------------------------------------
 * DynamicDriveToolTipTagFragmentGenerator.java
 * --------------------------------------------
 * (C) Copyright 2003-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 *
 * Changes
 * -------
 * 12-Aug-2003 : Version 1 (RA);
 * 
 */
 
package org.jfree.chart.imagemap;

/**
 * Generates tooltips using the Dynamic Drive DHTML Tip Message 
 * library (http://www.dynamicdrive.com).
 */
public class DynamicDriveToolTipTagFragmentGenerator 
    implements ToolTipTagFragmentGenerator {
  static {
    CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h.ping();
  }


    /** The title, empty string not to display */
    protected String title = "";
  {
    CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h.statements[1]++;
  }

    /** The style number */
    protected int style = 1;
  {
    CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h.statements[2]++;
  }

    /**
     * Blank constructor.
     */
    public DynamicDriveToolTipTagFragmentGenerator() {
        super();
CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h.statements[3]++;
    }

    /**
     * Creates a new generator with specific title and style settings.
     *
     * @param title  title for use in all tooltips, use empty String not to 
     *               display a title.
     * @param style  style number, see http://www.dynamicdrive.com for more 
     *               information.
     */
    public DynamicDriveToolTipTagFragmentGenerator(String title, int style) {
        this.title = title;
CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h.statements[4]++;
        this.style = style;
CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h.statements[5]++;
    }

    /**
     * Generates a tooltip string to go in an HTML image map.
     *
     * @param toolTipText  the tooltip.
     * 
     * @return The formatted HTML area tag attribute(s).
     */
    public String generateToolTipFragment(String toolTipText) {
        return " onMouseOver=\"return stm(['" + this.title + "','" 
            + toolTipText + "'],Style[" + this.style + "]);\"" 
            + " onMouseOut=\"return htm();\"";
    }

}

class CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$d3xmj78vqpicftw8g9rrgnyi418olb1gkptvce3eysfv5aqzys5uybukn9ogixk2xh0h () {
    super("org.jfree.chart.imagemap.DynamicDriveToolTipTagFragmentGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.jfree.chart.imagemap.DynamicDriveToolTipTagFragmentGenerator.java");
      for (int i = 1; i <= 5; i++) {
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

