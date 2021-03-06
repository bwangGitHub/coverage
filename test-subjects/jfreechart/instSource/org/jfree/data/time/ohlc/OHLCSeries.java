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
 * OHLCSeries.java
 * ---------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 04-Dec-2006 : Version 1 (DG);
 *
 */

package org.jfree.data.time.ohlc;

import org.jfree.data.ComparableObjectItem;
import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.time.RegularTimePeriod;

/**
 * A list of (RegularTimePeriod, open, high, low, close) data items.
 *
 * @since 1.0.4
 *
 * @see OHLCSeriesCollection
 */
public class OHLCSeries extends ComparableObjectSeries {
  static {
    CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.ping();
  }

    
    /**
     * Creates a new empty series.  By default, items added to the series will 
     * be sorted into ascending order by period, and duplicate periods will 
     * not be allowed.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    public OHLCSeries(Comparable key) {
        super(key, true, false);
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.statements[1]++;
    }
    
    /**
     * Returns the time period for the specified item.
     * 
     * @param index  the item index.
     * 
     * @return The time period.
     */
    public RegularTimePeriod getPeriod(int index) {
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.statements[2]++;
        final OHLCItem item = (OHLCItem) getDataItem(index);
        return item.getPeriod();
    }
    
    /**
     * Returns the data item at the specified index.
     * 
     * @param index  the item index.
     * 
     * @return The data item.
     */
    public ComparableObjectItem getDataItem(int index) {
        return super.getDataItem(index);
    }

    /**
     * Adds a data item to the series.
     *
     * @param period  the period.
     * @param open  the open-value.
     * @param high  the high-value.
     * @param low  the low-value.
     * @param close  the close-value.
     */
    public void add(RegularTimePeriod period, double open, double high, 
            double low, double close) {
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getItemCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.branches[1]++;
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.statements[4]++;
            OHLCItem item0 = (OHLCItem) this.getDataItem(0);
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((period.getClass().equals(item0.getPeriod().getClass())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.branches[3]++;
                throw new IllegalArgumentException(
                        "Can't mix RegularTimePeriod class types.");

            } else {
  CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.branches[2]++;}
        super.add(new OHLCItem(period, open, high, low, close), true);
CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69.statements[6]++;
    }

}

class CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.data.time.ohlc.OHLCSeries.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$nrffpj9f1fd4fg8xmn1zs69 () {
    super("org.jfree.data.time.ohlc.OHLCSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.ohlc.OHLCSeries.java");
      for (int i = 1; i <= 6; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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

