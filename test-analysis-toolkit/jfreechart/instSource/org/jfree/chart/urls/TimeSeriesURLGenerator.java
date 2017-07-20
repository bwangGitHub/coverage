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
 * ---------------------------
 * TimeSeriesURLGenerator.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributors:     David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 29-Aug-2002 : Initial version (RA);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 13-Jan-2005 : Modified for XHTML 1.0 compliance (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Jul-2006 : Swap call to dataset's getX() --> getXValue() (DG);
 * 17-Apr-2007 : Added null argument checks to constructor, new accessor 
 *               methods, added equals() override and used new URLUtilities
 *               class to encode series key and date (DG);
 * 
 */

package org.jfree.chart.urls;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import org.jfree.data.xy.XYDataset;

/**
 * A URL generator for time series charts.
 */
public class TimeSeriesURLGenerator implements XYURLGenerator, Serializable {
  static {
    CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -9122773175671182445L;
  static {
    CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[1]++;
  }    
    
    /** A formatter for the date. */
    private DateFormat dateFormat = DateFormat.getInstance();
  {
    CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[2]++;
  }
    
    /** Prefix to the URL */
    private String prefix = "index.html";
  {
    CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[3]++;
  }

    /** Name to use to identify the series */
    private String seriesParameterName = "series";
  {
    CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[4]++;
  }

    /** Name to use to identify the item */
    private String itemParameterName = "item";
  {
    CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[5]++;
  }

    /**
     * Default constructor.
     */
    public TimeSeriesURLGenerator() {
        super();
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[6]++;
    }

    /**
     * Construct TimeSeriesURLGenerator overriding defaults.
     *
     * @param dateFormat  a formatter for the date (<code>null</code> not 
     *         permitted).
     * @param prefix  the prefix of the URL (<code>null</code> not permitted).
     * @param seriesParameterName  the name of the series parameter in the URL
     *         (<code>null</code> not permitted).
     * @param itemParameterName  the name of the item parameter in the URL 
     *         (<code>null</code> not permitted).
     */
    public TimeSeriesURLGenerator(DateFormat dateFormat, String prefix,
            String seriesParameterName, String itemParameterName) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dateFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[1]++;
            throw new IllegalArgumentException("Null 'dateFormat' argument.");

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[2]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[3]++;
            throw new IllegalArgumentException("Null 'prefix' argument.");

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[4]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'seriesParameterName' argument.");

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[6]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((itemParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[7]++;
            throw new IllegalArgumentException(
                    "Null 'itemParameterName' argument.");

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[8]++;}
        
        this.dateFormat = (DateFormat) dateFormat.clone();
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[11]++;
        this.prefix = prefix;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[12]++;
        this.seriesParameterName = seriesParameterName;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[13]++;
        this.itemParameterName = itemParameterName;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[14]++;

    }

    /**
     * Returns a clone of the date format assigned to this URL generator.
     * 
     * @return The date format (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public DateFormat getDateFormat() {
        return (DateFormat) this.dateFormat.clone();
    }
    
    /**
     * Returns the prefix string.
     * 
     * @return The prefix string (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public String getPrefix() {
        return this.prefix;
    }
    
    /**
     * Returns the series parameter name.
     * 
     * @return The series parameter name (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public String getSeriesParameterName() {
        return this.seriesParameterName;
    }
    
    /**
     * Returns the item parameter name.
     * 
     * @return The item parameter name (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public String getItemParameterName() {
        return this.itemParameterName;
    }
    
    /**
     * Generates a URL for a particular item within a series.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series number (zero-based index).
     * @param item  the item number (zero-based index).
     *
     * @return The generated URL.
     */
    public String generateURL(XYDataset dataset, int series, int item) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[15]++;
        String result = this.prefix;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[16]++;
        boolean firstParameter = result.indexOf("?") == -1;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[17]++;
        Comparable seriesKey = dataset.getSeriesKey(series);
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((seriesKey != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[9]++;
            result += firstParameter ? "?" : "&amp;";
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[19]++;
            result += this.seriesParameterName + "=" + URLUtilities.encode(
                    seriesKey.toString(), "UTF-8");
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[20]++;
            firstParameter = false;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[21]++;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[10]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[22]++;

        long x = (long) dataset.getXValue(series, item);
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[23]++;
        String xValue = this.dateFormat.format(new Date(x));
        result += firstParameter ? "?" : "&amp;";
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[24]++;
        result += this.itemParameterName + "=" + URLUtilities.encode(xValue, 
                "UTF-8");
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[25]++;

        return result;
    }

    /**
     * Tests this generator for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[12]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof TimeSeriesURLGenerator) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[14]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[28]++;
        TimeSeriesURLGenerator that = (TimeSeriesURLGenerator) obj;
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.dateFormat.equals(that.dateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[16]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[30]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.itemParameterName.equals(that.itemParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[18]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.prefix.equals(that.prefix)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[20]++;}
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.seriesParameterName.equals(that.seriesParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5.branches[22]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5 ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.urls.TimeSeriesURLGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$5ebx4v43fvijqmolt88fnz8gis9zruz2xcfqanh5r5 () {
    super("org.jfree.chart.urls.TimeSeriesURLGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.urls.TimeSeriesURLGenerator.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

