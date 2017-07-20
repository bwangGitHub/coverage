/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * CategoryItemRendererState.java
 * ------------------------------
 * (C) Copyright 2003-2006, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (since 20-Oct-2003):
 * ----------------------------
 * 20-Oct-2003 : Added series running total (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Updated API docs (DG);
 *
 */

package org.jfree.chart.renderer.category;

import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.RendererState;

/**
 * An object that retains temporary state information for a 
 * {@link CategoryItemRenderer}.
 */
public class CategoryItemRendererState extends RendererState {
  static {
    CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h.ping();
  }


    /** The bar width. */
    private double barWidth;
    
    /** The series running total. */
    private double seriesRunningTotal;
    
    /**
     * Creates a new object for recording temporary state information for a
     * renderer.
     * 
     * @param info  the plot rendering info (<code>null</code> permitted).
     */
    public CategoryItemRendererState(PlotRenderingInfo info) {
        super(info);
CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h.statements[1]++;
        this.barWidth = 0.0;
CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h.statements[2]++;
        this.seriesRunningTotal = 0.0;
CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h.statements[3]++;
    }
    
    /**
     * Returns the bar width.
     * 
     * @return The bar width.
     * 
     * @see #setBarWidth(double)
     */
    public double getBarWidth() {
        return this.barWidth;
    }
    
    /**
     * Sets the bar width.  The renderer calculates this value and stores it 
     * here - it is not intended that users can manually set the bar width.
     * 
     * @param width  the width.
     * 
     * @see #getBarWidth()
     */
    public void setBarWidth(double width) {
        this.barWidth = width;
CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h.statements[4]++;
    }
    
    /**
     * Returns the series running total.
     * 
     * @return The running total.
     * 
     * @see #setSeriesRunningTotal(double)
     */
    public double getSeriesRunningTotal() {
        return this.seriesRunningTotal;    
    }
    
    /**
     * Sets the series running total (this method is intended for the use of 
     * the renderer only).
     * 
     * @param total  the new total.
     * 
     * @see #getSeriesRunningTotal()
     */
    void setSeriesRunningTotal(double total) {
        this.seriesRunningTotal = total;
CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h.statements[5]++;
    }
    
}

class CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$171h2mugi38prgbz2iybzzti3333lfvxw3hfqa7pikpzw4h () {
    super("org.jfree.chart.renderer.category.CategoryItemRendererState.java");
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
    log.startNamedSection("org.jfree.chart.renderer.category.CategoryItemRendererState.java");
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

