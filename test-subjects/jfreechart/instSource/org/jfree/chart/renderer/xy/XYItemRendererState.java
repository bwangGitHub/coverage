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
 * XYItemRendererState.java
 * ------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 07-Oct-2003 : Version 1 (DG);
 * 27-Jan-2004 : Added workingLine attribute (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 04-May-2007 : Added processVisibleItemsOnly flag (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.geom.Line2D;

import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.RendererState;

/**
 * The state for an {@link XYItemRenderer}.
 */
public class XYItemRendererState extends RendererState {
  static {
    CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx.ping();
  }


    /** 
     * A line object that the renderer can reuse to save instantiating a lot 
     * of objects. 
     */
    public Line2D workingLine;
    
    /** 
     * A flag that controls whether the plot should pass ALL data items to the
     * renderer, or just the items that will be visible.
     * 
     * @since 1.0.6
     */
    private boolean processVisibleItemsOnly;
    
    /**
     * Creates a new state.
     * 
     * @param info  the plot rendering info.
     */
    public XYItemRendererState(PlotRenderingInfo info) {
        super(info);
CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx.statements[1]++;
        this.workingLine = new Line2D.Double();
CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx.statements[2]++;
        this.processVisibleItemsOnly = true;
CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx.statements[3]++;
    }

    /**
     * Returns the flag that controls whether the plot passes all data
     * items in each series to the renderer, or just the visible items.  The
     * default value is <code>true</code>.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setProcessVisibleItemsOnly(boolean)
     */
    public boolean getProcessVisibleItemsOnly() {
        return this.processVisibleItemsOnly;
    }
    
    /**
     * Sets the flag that controls whether the plot passes all data
     * items in each series to the renderer, or just the visible items.
     * 
     * @param flag  the new flag value.
     * 
     * @since 1.0.6
     */
    public void setProcessVisibleItemsOnly(boolean flag) {
        this.processVisibleItemsOnly = flag;
CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx.statements[4]++;
    }
   
}

class CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$kd1ps3ecvx1yjjwctvqtumx54q6x2i86lbtcx () {
    super("org.jfree.chart.renderer.xy.XYItemRendererState.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("org.jfree.chart.renderer.xy.XYItemRendererState.java");
      for (int i = 1; i <= 4; i++) {
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

