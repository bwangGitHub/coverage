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
 * -----------------------
 * DatasetChangeEvent.java
 * -----------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 24-Aug-2001)
 * --------------------------
 * 24-Aug-2001 : Added standard source header. Fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Move to new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 11-Jun-2002 : Separated the event source from the dataset to cover the case 
 *               where the dataset is changed to null in the Plot class.  
 *               Updated Javadocs (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 05-Oct-2004 : Minor Javadoc updates (DG);
 *
 */

package org.jfree.data.general;

/**
 * A change event that encapsulates information about a change to a dataset.
 */
public class DatasetChangeEvent extends java.util.EventObject {
  static {
    CodeCoverCoverageCounter$27savq9acjjxoq9b50ps6qyamietpxa0hwf5.ping();
  }


    /**
     * The dataset that generated the change event.
     */
    private Dataset dataset;

    /**
     * Constructs a new event.  The source is either the dataset or the 
     * {@link org.jfree.chart.plot.Plot} class.  The dataset can be 
     * <code>null</code> (in this case the source will be the 
     * {@link org.jfree.chart.plot.Plot} class).
     *
     * @param source  the source of the event.
     * @param dataset  the dataset that generated the event (<code>null</code>
     *                 permitted).
     */
    public DatasetChangeEvent(Object source, Dataset dataset) {
        super(source);
CodeCoverCoverageCounter$27savq9acjjxoq9b50ps6qyamietpxa0hwf5.statements[1]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$27savq9acjjxoq9b50ps6qyamietpxa0hwf5.statements[2]++;
    }

    /**
     * Returns the dataset that generated the event.  Note that the dataset
     * may be <code>null</code> since adding a <code>null</code> dataset to a 
     * plot will generated a change event.
     *
     * @return The dataset (possibly <code>null</code>).
     */
    public Dataset getDataset() {
        return this.dataset;
    }

}

class CodeCoverCoverageCounter$27savq9acjjxoq9b50ps6qyamietpxa0hwf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27savq9acjjxoq9b50ps6qyamietpxa0hwf5 ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$27savq9acjjxoq9b50ps6qyamietpxa0hwf5 () {
    super("org.jfree.data.general.DatasetChangeEvent.java");
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
    log.startNamedSection("org.jfree.data.general.DatasetChangeEvent.java");
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

