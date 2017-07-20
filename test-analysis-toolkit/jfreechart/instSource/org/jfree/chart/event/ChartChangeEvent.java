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
 * ---------------------
 * ChartChangeEvent.java
 * ---------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 24-Aug-2001)
 * --------------------------
 * 24-Aug-2001 : Added standard source header. Fixed DOS encoding problem (DG);
 * 07-Nov-2001 : Updated header (DG);
 *               Change event type names (DG);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Feb-2005 : Changed the type from int to ChartChangeEventType (DG);
 *
 */

package org.jfree.chart.event;

import java.util.EventObject;

import org.jfree.chart.JFreeChart;

/**
 * A change event that encapsulates information about a change to a chart.
 */
public class ChartChangeEvent extends EventObject {
  static {
    CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.ping();
  }


    /** The type of event. */
    private ChartChangeEventType type;

    /** The chart that generated the event. */
    private JFreeChart chart;

    /**
     * Creates a new chart change event.
     *
     * @param source  the source of the event (could be the chart, a title, 
     *                an axis etc.)
     */
    public ChartChangeEvent(Object source) {
        this(source, null, ChartChangeEventType.GENERAL);
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[1]++;
    }

    /**
     * Creates a new chart change event.
     *
     * @param source  the source of the event (could be the chart, a title, an 
     *                axis etc.)
     * @param chart  the chart that generated the event.
     */
    public ChartChangeEvent(Object source, JFreeChart chart) {
        this(source, chart, ChartChangeEventType.GENERAL);
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[2]++;
    }

    /**
     * Creates a new chart change event.
     *
     * @param source  the source of the event (could be the chart, a title, an
                      axis etc.)
     * @param chart  the chart that generated the event.
     * @param type  the type of event.
     */
    public ChartChangeEvent(Object source, JFreeChart chart, 
                            ChartChangeEventType type) {
        super(source);
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[3]++;
        this.chart = chart;
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[4]++;
        this.type = type;
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[5]++;
    }

    /**
     * Returns the chart that generated the change event.
     *
     * @return The chart that generated the change event.
     */
    public JFreeChart getChart() {
        return this.chart;
    }

    /**
     * Sets the chart that generated the change event.
     *
     * @param chart  the chart that generated the event.
     */
    public void setChart(JFreeChart chart) {
        this.chart = chart;
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[6]++;
    }

    /**
     * Returns the event type.
     *
     * @return The event type.
     */
    public ChartChangeEventType getType() {
        return this.type;
    }

    /**
     * Sets the event type.
     *
     * @param type  the event type.
     */
    public void setType(ChartChangeEventType type) {
        this.type = type;
CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x.statements[7]++;
    }

}

class CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1jzqk4nh0ys6d3l837gnc5s53kea0mq8x () {
    super("org.jfree.chart.event.ChartChangeEvent.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("org.jfree.chart.event.ChartChangeEvent.java");
      for (int i = 1; i <= 7; i++) {
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

