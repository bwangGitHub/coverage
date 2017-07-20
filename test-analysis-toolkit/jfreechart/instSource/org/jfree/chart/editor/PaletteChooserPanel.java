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
 * PaletteChooserPanel.java
 * ------------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 27-Jan-2003 : Added standard header (DG);
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.chart.editor;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jfree.chart.plot.ColorPalette;
import org.jfree.chart.plot.RainbowPalette;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;

/**
 * A component for choosing a palette from a list of available palettes.
 *
 * @deprecated This class is no longer supported.  If you are creating
 *     contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
class PaletteChooserPanel extends JPanel {
  static {
    CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.ping();
  }


    /** A combo for selecting the stroke. */
    private JComboBox selector;

    /**
     * Constructor.
     *
     * @param current  the current palette sample.
     * @param available  an array of 'available' palette samples.
     */
    public PaletteChooserPanel(PaletteSample current, 
                               PaletteSample[] available) {
        setLayout(new BorderLayout());
CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.statements[1]++;
        this.selector = new JComboBox(available);
CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.statements[2]++;
        this.selector.setSelectedItem(current);
CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.statements[3]++;
        this.selector.setRenderer(new PaletteSample(new RainbowPalette()));
CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.statements[4]++;
        add(this.selector);
CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.statements[5]++;
    }

    /**
     * Returns the selected palette.
     *
     * @return The selected palette.
     */
    public ColorPalette getSelectedPalette() {
CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt.statements[6]++;
        PaletteSample sample = (PaletteSample) this.selector.getSelectedItem();
        return sample.getPalette();
    }
}

class CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iixm5oa49gth1xh8ergumzat2zwxa5m1aurtt () {
    super("org.jfree.chart.editor.PaletteChooserPanel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("org.jfree.chart.editor.PaletteChooserPanel.java");
      for (int i = 1; i <= 6; i++) {
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

