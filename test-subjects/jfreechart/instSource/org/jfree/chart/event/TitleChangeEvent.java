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
 * TitleChangeEvent.java
 * ---------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 22-Jun-2001)
 * --------------------------
 * 22-Jun-2001 : Changed Title to AbstractTitle while incorporating 
 *               David Berry's changes (DG);
 * 24-Aug-2001 : Fixed DOS encoding problem (DG);
 * 07-Nov-2001 : Updated header (DG);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Jan-2004 : Renamed AbstractTitle --> Title and moved to new package (DG);
 *
 */

package org.jfree.chart.event;

import org.jfree.chart.title.Title;

/**
 * A change event that encapsulates information about a change to a chart title.
 */
public class TitleChangeEvent extends ChartChangeEvent {
  static {
    CodeCoverCoverageCounter$1y4842vgjwsnpj99urzlfsu7rvcczftk1.ping();
  }


    /** The chart title that generated the event. */
    private Title title;

    /**
     * Default constructor.
     *
     * @param title  the chart title that generated the event.
     */
    public TitleChangeEvent(Title title) {
        super(title);
CodeCoverCoverageCounter$1y4842vgjwsnpj99urzlfsu7rvcczftk1.statements[1]++;
        this.title = title;
CodeCoverCoverageCounter$1y4842vgjwsnpj99urzlfsu7rvcczftk1.statements[2]++;
    }

    /**
     * Returns the title that generated the event.
     *
     * @return The title that generated the event.
     */
    public Title getTitle() {
        return this.title;
    }

}

class CodeCoverCoverageCounter$1y4842vgjwsnpj99urzlfsu7rvcczftk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1y4842vgjwsnpj99urzlfsu7rvcczftk1 ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1y4842vgjwsnpj99urzlfsu7rvcczftk1 () {
    super("org.jfree.chart.event.TitleChangeEvent.java");
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
    log.startNamedSection("org.jfree.chart.event.TitleChangeEvent.java");
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

