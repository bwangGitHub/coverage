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
 * --------------------
 * WaferMapDataset.java
 * --------------------
 * (C)opyright 2003-2007, by Robert Redburn and Contributors.
 *
 * Original Author:  Robert Redburn;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 25-Nov-2003 : Version 1 contributed by Robert Redburn (with some 
 *               modifications to match style conventions) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 
 */

package org.jfree.data.general;

import java.util.Set;
import java.util.TreeSet;

import org.jfree.data.DefaultKeyedValues2D;

/**
 * A dataset that can be used with the {@link org.jfree.chart.plot.WaferMapPlot}
 * class.
 */
public class WaferMapDataset extends AbstractDataset {
  static {
    CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.ping();
  }


    /** 
     * Storage structure for the data values (row key is chipx, column is 
     * chipy)
     */
    private DefaultKeyedValues2D data;
    
    /** wafer x dimension */
    private int maxChipX;
    
    /** wafer y dimension */
    private int maxChipY;
    
    /** space to draw between chips */
    private double chipSpace;
    
    /** maximum value in this dataset */
    private Double maxValue;
    
    /** minimum value in this dataset */
    private Double minValue;
    
    /** default chip spacing */
    private static final double DEFAULT_CHIP_SPACE = 1d;
  static {
    CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[1]++;
  }
    
    /**
     * Creates a new dataset using the default chipspace.
     * 
     * @param maxChipX  the wafer x-dimension.
     * @param maxChipY  the wafer y-dimension.
     */
    public WaferMapDataset(int maxChipX, int maxChipY) {
        this(maxChipX, maxChipY, null);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[2]++;
    }
    
    /**
     * Creates a new dataset.
     * 
     * @param maxChipX  the wafer x-dimension. 
     * @param maxChipY  the wafer y-dimension.
     * @param chipSpace  the space between chips.
     */
    public WaferMapDataset(int maxChipX, int maxChipY, Number chipSpace) {
        
        this.maxValue = new Double(Double.NEGATIVE_INFINITY);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[3]++;
        this.minValue = new Double(Double.POSITIVE_INFINITY);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[4]++;
        this.data = new DefaultKeyedValues2D();
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[5]++;
        
        this.maxChipX = maxChipX;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[6]++;
        this.maxChipY = maxChipY;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[7]++;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((chipSpace == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[1]++;
            this.chipSpace = DEFAULT_CHIP_SPACE;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[9]++;
 
        }
        else {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[2]++;
            this.chipSpace = chipSpace.doubleValue();
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[10]++;
        }

    }

    /**
     * Sets a value in the dataset.
     * 
     * @param value  the value.
     * @param chipx  the x-index for the chip.
     * @param chipy  the y-index for the chip.
     */
    public void addValue(Number value, Comparable chipx, Comparable chipy) {
        setValue(value, chipx, chipy);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[11]++;
    }
    
    /**
     * Adds a value to the dataset.
     * 
     * @param v  the value.
     * @param x  the x-index.
     * @param y  the y-index.
     */
    public void addValue(int v, int x, int y) {
        setValue(new Double(v), new Integer(x), new Integer(y));
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[12]++;
    }
    
    /**
     * Sets a value in the dataset and updates min and max value entries.
     * 
     * @param value  the value.
     * @param chipx  the x-index.
     * @param chipy  the y-index.
     */
    public void setValue(Number value, Comparable chipx, Comparable chipy) {
        this.data.setValue(value, chipx, chipy);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[13]++;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isMaxValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[3]++;
            this.maxValue = (Double) value;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[15]++;

        } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[4]++;}
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isMinValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[5]++;
            this.minValue = (Double) value;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[17]++;

        } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[6]++;}
    }

    /**
     * Returns the number of unique values.
     * 
     * @return The number of unique values.
     */
    public int getUniqueValueCount() {
        return getUniqueValues().size();
    }

    /**
     * Returns the set of unique values.
     * 
     * @return The set of unique values.
     */
    public Set getUniqueValues() {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[18]++;
        Set unique = new TreeSet();
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        //step through all the values and add them to the hash
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((r < this.data.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[1]--;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[2]--;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[3]++;
}
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[20]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c < this.data.getColumnCount()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[4]--;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[5]--;
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.loops[6]++;
}
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[21]++;
                Number value = this.data.getValue(r, c);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[7]++;
                    unique.add(value);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[23]++;

                } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[8]++;}
            }
        }
        return unique;
    }

    /**
     * Returns the data value for a chip.
     * 
     * @param chipx  the x-index.
     * @param chipy  the y-index.
     * 
     * @return The data value.
     */
    public Number getChipValue(int chipx, int chipy) {
        return getChipValue(new Integer(chipx), new Integer(chipy));
    }

    /**
     * Returns the value for a given chip x and y or null.
     * 
     * @param chipx  the x-index.
     * @param chipy  the y-index.
     * 
     * @return The data value.
     */
    public Number getChipValue(Comparable chipx, Comparable chipy) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[24]++;
        int rowIndex = this.data.getRowIndex(chipx);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((rowIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[9]++;
            return null;

        } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[10]++;}
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[26]++;
        int colIndex = this.data.getColumnIndex(chipy);
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((colIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[11]++;
            return null;

        } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[12]++;}
        return this.data.getValue(rowIndex, colIndex);
    }

    /**
     * Tests to see if the passed value is larger than the stored maxvalue.
     * 
     * @param check  the number to check.
     * 
     * @return A boolean.
     */
    public boolean isMaxValue(Number check) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((check.doubleValue() > this.maxValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[14]++;}
        return false;
    }

    /**
     * Tests to see if the passed value is smaller than the stored minvalue.
     * 
     * @param check  the number to check.
     * 
     * @return A boolean.
     */
    public boolean isMinValue(Number check) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((check.doubleValue() < this.minValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.branches[16]++;}
        return false;
    }
    
    /** 
     * Returns the maximum value stored in the dataset.
     * 
     * @return The maximum value.
     */
    public Number getMaxValue() {
        return this.maxValue;   
    }
    
    /** 
     * Returns the minimum value stored in the dataset.
     * 
     * @return The minimum value.
     */
    public Number getMinValue() {
        return this.minValue;   
    }

    /**
     * Returns the wafer x-dimension.
     * 
     * @return The number of chips in the x-dimension.
     */
    public int getMaxChipX() {
        return this.maxChipX;
    }

    /**
     * Sets wafer x dimension.
     * 
     * @param maxChipX  the number of chips in the x-dimension.
     */
    public void setMaxChipX(int maxChipX) {
        this.maxChipX = maxChipX;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[30]++;
    }

    /**
     * Returns the number of chips in the y-dimension.
     * 
     * @return The number of chips.
     */
    public int getMaxChipY() {
        return this.maxChipY;
    }

    /**
     * Sets the number of chips in the y-dimension.
     * 
     * @param maxChipY  the number of chips.
     */
    public void setMaxChipY(int maxChipY) {
        this.maxChipY = maxChipY;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[31]++;
    }

    /**
     * Returns the space to draw between chips.
     * 
     * @return The space.
     */
    public double getChipSpace() {
        return this.chipSpace;
    }

    /**
     * Sets the space to draw between chips.
     * 
     * @param space  the space.
     */
    public void setChipSpace(double space) {
        this.chipSpace = space;
CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5.statements[32]++;
    }
    
}

class CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5 ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.general.WaferMapDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$a7gaf4ps5wy4dzoj5umitimka3x31b5 () {
    super("org.jfree.data.general.WaferMapDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.WaferMapDataset.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

