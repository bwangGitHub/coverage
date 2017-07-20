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
 * -----------------
 * OHLCDataItem.java
 * -----------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Dec-2003 : Version 1 (DG);
 * 29-Apr-2005 : Added equals() method (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a single (open-high-low-close) data item in 
 * an {@link DefaultOHLCDataset}.  This data item is commonly used 
 * to summarise the trading activity of a financial commodity for 
 * a fixed period (most often one day).
 */
public class OHLCDataItem implements Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 7753817154401169901L;
  static {
    CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[1]++;
  }
    
    /** The date. */
    private Date date;
    
    /** The open value. */
    private Number open;

    /** The high value. */
    private Number high;
    
    /** The low value. */
    private Number low;
    
    /** The close value. */
    private Number close;
    
    /** The trading volume (number of shares, contracts or whatever). */
    private Number volume;

    /**
     * Creates a new item.
     * 
     * @param date  the date (<code>null</code> not permitted).
     * @param open  the open value.
     * @param high  the high value.
     * @param low  the low value.
     * @param close  the close value.
     * @param volume  the volume.
     */
    public OHLCDataItem(Date date, 
                        double open, 
                        double high, 
                        double low, 
                        double close,
                        double volume) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[1]++;
            throw new IllegalArgumentException("Null 'date' argument.");

        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[2]++;}
        this.date = date;
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[3]++;
        this.open = new Double(open);
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[4]++;
        this.high = new Double(high);
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[5]++;
        this.low = new Double(low);
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[6]++;
        this.close = new Double(close);
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[7]++;
        this.volume = new Double(volume);
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[8]++;
    }
    
    /**
     * Returns the date that the data item relates to.
     * 
     * @return The date (never <code>null</code>).
     */
    public Date getDate() {
        return this.date;
    }
 
    /**
     * Returns the open value.
     * 
     * @return The open value.
     */
    public Number getOpen() {
        return this.open;
    }
    
    /**
     * Returns the high value.
     * 
     * @return The high value.
     */
    public Number getHigh() {
        return this.high;
    }
    
    /**
     * Returns the low value.
     * 
     * @return The low value.
     */
    public Number getLow() {
        return this.low;
    }
    
    /**
     * Returns the close value.
     * 
     * @return The close value.
     */
    public Number getClose() {
        return this.close;
    }
    
    /**
     * Returns the volume.
     * 
     * @return The volume.
     */
    public Number getVolume() {
        return this.volume;
    }
    
    /**
     * Checks this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[3]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[4]++;}
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof OHLCDataItem) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[6]++;}
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[11]++;
        OHLCDataItem that = (OHLCDataItem) obj;
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.date.equals(that.date)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[8]++;}
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.high.equals(that.high)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[10]++;}
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.low.equals(that.low)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[11]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[12]++;}
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.open.equals(that.open)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[13]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[14]++;}
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.close.equals(that.close)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[15]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[16]++;}
        return true;
    }
    
    /**
     * Compares this object with the specified object for order. Returns a 
     * negative integer, zero, or a positive integer as this object is less 
     * than, equal to, or greater than the specified object.
     * 
     * @param object  the object to compare to.
     * 
     * @return A negative integer, zero, or a positive integer as this object 
     *         is less than, equal to, or greater than the specified object.
     */
    public int compareTo(Object object) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((object instanceof OHLCDataItem) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[17]++;
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.statements[18]++;
            OHLCDataItem item = (OHLCDataItem) object;
            return this.date.compareTo(item.date);

        }
        else {
CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5.branches[18]++;
            throw new ClassCastException("OHLCDataItem.compareTo().");
        }
    }
    
}

class CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.OHLCDataItem.java";
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

  public CodeCoverCoverageCounter$xdl8l1txcb5g5xpkhyegbl57j5 () {
    super("org.jfree.data.xy.OHLCDataItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.jfree.data.xy.OHLCDataItem.java");
      for (int i = 1; i <= 18; i++) {
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

