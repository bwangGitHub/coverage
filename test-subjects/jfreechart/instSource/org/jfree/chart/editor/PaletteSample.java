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
 * ------------------
 * PaletteSample.java
 * ------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 21-Jan-2003 : Added standard header (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.chart.editor;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.jfree.chart.plot.ColorPalette;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;


/**
 * A panel that displays a palette sample.
 *
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class PaletteSample extends JComponent implements ListCellRenderer {
  static {
    CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.ping();
  }


    /** The palette being displayed. */
    private ColorPalette palette;

    /** The preferred size of the component; */
    private Dimension preferredSize;

    /**
     * Creates a new sample.
     *
     * @param palette  the palette.
     */
    public PaletteSample(ColorPalette palette) {
        this.palette = palette;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[1]++;
        this.preferredSize = new Dimension(80, 18);
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[2]++;
    }

    /**
     * Returns a list cell renderer for the stroke, so the sample can be 
     * displayed in a list or combo.
     *
     * @param list  the list component.
     * @param value  the value.
     * @param index  the index.
     * @param isSelected  a flag that indicates whether or not the item is 
     *                    selected.
     * @param cellHasFocus  a flag that indicates whether or not the cell has 
     *                      the focus.
     *
     * @return The renderer.
     */
    public Component getListCellRendererComponent(JList list, Object value, 
                                                  int index,
                                                  boolean isSelected, 
                                                  boolean cellHasFocus) {
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value instanceof PaletteSample) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.branches[1]++;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[4]++;
            PaletteSample in = (PaletteSample) value;
            setPalette(in.getPalette());
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[5]++;

        } else {
  CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.branches[2]++;}
        return this;
    }

    /**
     * Returns the current palette object being displayed.
     *
     * @return The palette.
     */
    public ColorPalette getPalette() {
        return this.palette;
    }

    /**
     * Returns the preferred size of the component.
     *
     * @return The preferred size.
     */
    public Dimension getPreferredSize() {
        return this.preferredSize;
    }

    /**
     * Draws the sample.
     *
     * @param g  the graphics device.
     */
    public void paintComponent(Graphics g) {
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[6]++;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF
        );
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[7]++;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[8]++;
        Dimension size = getSize();
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[9]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[10]++;
        double ww = size.getWidth() - insets.left - insets.right;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[11]++;
        double hh = size.getHeight() - insets.top - insets.bottom;

        g2.setStroke(new BasicStroke(1.0f));
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[12]++;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[13]++;

        double y1 = insets.top;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[14]++;
        double y2 = y1 + hh;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[15]++;
        double xx = insets.left;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[16]++;
        Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[17]++;
        int count = 0;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xx <= insets.left + ww) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.loops[1]--;
  CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.loops[2]--;
  CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.loops[3]++;
}
            count++;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[19]++;
            line.setLine(xx, y1, xx, y2);
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[20]++;
            g2.setPaint(this.palette.getColor(count));
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[21]++;
            g2.draw(line);
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[22]++;
            xx += 1;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[23]++;
        }
    }

    /**
     * Sets the palette object being displayed.
     *
     * @param palette  the palette.
     */
    public void setPalette(ColorPalette palette) {
        this.palette = palette;
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[24]++;
        this.repaint();
CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5.statements[25]++;
    }

}

class CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.PaletteSample.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$6omzpsxnic1w6rs6q3pbg6h7gkf5 () {
    super("org.jfree.chart.editor.PaletteSample.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.PaletteSample.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

