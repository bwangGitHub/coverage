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
 * -------------------------
 * DialLayerChangeEvent.java
 * -------------------------
 * (C) Copyright 2006-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Nov-2006 : Version 1 (DG);
 *
 */

package org.jfree.chart.plot.dial;

import org.jfree.chart.event.ChartChangeEvent;

/**
 * An event that can be forwarded to any {@link DialLayerChangeListener} to 
 * signal a change to a {@link DialLayer}.
 * 
 * @since 1.0.7
 */
public class DialLayerChangeEvent extends ChartChangeEvent {
  static {
    CodeCoverCoverageCounter$344g260nxq5qi2qz5ik24077mj5fntk8vsudnj5.ping();
  }


    /** The dial layer that generated the event. */
    private DialLayer layer;

    /**
     * Creates a new instance.
     *
     * @param layer  the dial layer that generated the event.
     */
    public DialLayerChangeEvent(DialLayer layer) {
        super(layer);
CodeCoverCoverageCounter$344g260nxq5qi2qz5ik24077mj5fntk8vsudnj5.statements[1]++;
        this.layer = layer;
CodeCoverCoverageCounter$344g260nxq5qi2qz5ik24077mj5fntk8vsudnj5.statements[2]++;
    }

    /**
     * Returns the layer that generated the event.
     *
     * @return The layer that generated the event.
     */
    public DialLayer getDialLayer() {
        return this.layer;
    }

}

class CodeCoverCoverageCounter$344g260nxq5qi2qz5ik24077mj5fntk8vsudnj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$344g260nxq5qi2qz5ik24077mj5fntk8vsudnj5 ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$344g260nxq5qi2qz5ik24077mj5fntk8vsudnj5 () {
    super("org.jfree.chart.plot.dial.DialLayerChangeEvent.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.dial.DialLayerChangeEvent.java");
      for (int i = 1; i <= 2; i++) {
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

