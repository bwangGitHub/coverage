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
 * DefaultOHLCDataset.java
 * -----------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Dec-2003 : Version 1 (DG);
 * 05-May-2004 : Now extends AbstractXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 29-Apr-2005 : Added equals() method (DG);
 *
 */

package org.jfree.data.xy;

import java.util.Arrays;
import java.util.Date;

/**
 * A simple implementation of the {@link OHLCDataset} interface.  This 
 * implementation supports only one series.
 */
public class DefaultOHLCDataset extends AbstractXYDataset 
                                implements OHLCDataset {
  static {
    CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.ping();
  }


    /** The series key. */
    private Comparable key;
    
    /** Storage for the data items. */
    private OHLCDataItem[] data;
    
    /**
     * Creates a new dataset.
     * 
     * @param key  the series key.
     * @param data  the data items.
     */
    public DefaultOHLCDataset(Comparable key, OHLCDataItem[] data) {
        this.key = key;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[1]++;
        this.data = data;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[2]++;
    }
    
    /**
     * Returns the series key. 
     * 
     * @param series  the series index (ignored).
     * 
     * @return The series key.
     */
    public Comparable getSeriesKey(int series) {
        return this.key;
    }

    /**
     * Returns the x-value for a data item.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The x-value.
     */
    public Number getX(int series, int item) {
        return new Long(this.data[item].getDate().getTime());
    }

    /**
     * Returns the x-value for a data item as a date.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The x-value as a date.
     */
    public Date getXDate(int series, int item) {
        return this.data[item].getDate();
    }

    /**
     * Returns the y-value.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The y value.
     */
    public Number getY(int series, int item) {
        return getClose(series, item);
    }

    /**
     * Returns the high value.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The high value.
     */
    public Number getHigh(int series, int item) {
        return this.data[item].getHigh();
    }
    
    /**
     * Returns the high-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The high-value.
     */
    public double getHighValue(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[3]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[4]++;
        Number high = getHigh(series, item);
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((high != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[1]++;
            result = high.doubleValue();
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[6]++;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[2]++;}
        return result;   
    }

    /**
     * Returns the low value.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The low value.
     */
    public Number getLow(int series, int item) {
        return this.data[item].getLow();
    }

    /**
     * Returns the low-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The low-value.
     */
    public double getLowValue(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[7]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[8]++;
        Number low = getLow(series, item);
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((low != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[3]++;
            result = low.doubleValue();
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[10]++;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[4]++;}
        return result;   
    }

    /**
     * Returns the open value.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The open value.
     */
    public Number getOpen(int series, int item) {
        return this.data[item].getOpen();
    }

    /**
     * Returns the open-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The open-value.
     */
    public double getOpenValue(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[11]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[12]++;
        Number open = getOpen(series, item);
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((open != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[5]++;
            result = open.doubleValue();
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[14]++;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[6]++;}
        return result;   
    }

    /**
     * Returns the close value.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The close value.
     */
    public Number getClose(int series, int item) {
        return this.data[item].getClose();
    }

    /**
     * Returns the close-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The close-value.
     */
    public double getCloseValue(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[15]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[16]++;
        Number close = getClose(series, item);
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((close != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[7]++;
            result = close.doubleValue();
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[18]++;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[8]++;}
        return result;   
    }

    /**
     * Returns the trading volume.
     * 
     * @param series  the series index (ignored).
     * @param item  the item index (zero-based).
     * 
     * @return The trading volume.
     */
    public Number getVolume(int series, int item) {
        return this.data[item].getVolume();
    }

    /**
     * Returns the volume-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The volume-value.
     */
    public double getVolumeValue(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[19]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[20]++;
        Number volume = getVolume(series, item);
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((volume != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[9]++;
            result = volume.doubleValue();
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[22]++;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[10]++;}
        return result;   
    }

    /**
     * Returns the series count.
     * 
     * @return 1.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the item count for the specified series.
     * 
     * @param series  the series index (ignored).
     * 
     * @return The item count.
     */
    public int getItemCount(int series) {
        return this.data.length;
    }
   
    /**
     * Sorts the data into ascending order by date.
     */
    public void sortDataByDate() {
        Arrays.sort(this.data);
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[23]++;    
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[11]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[12]++;}
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultOHLCDataset) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[13]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[14]++;}
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[26]++;
        DefaultOHLCDataset that = (DefaultOHLCDataset) obj;
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.key.equals(that.key)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[15]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[16]++;}
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[17]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t.branches[18]++;}
        return true;
    }    

}

class CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultOHLCDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$27sy6oy8nww1eqj8pfr5rp76flb3djya1c9t () {
    super("org.jfree.data.xy.DefaultOHLCDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.DefaultOHLCDataset.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

