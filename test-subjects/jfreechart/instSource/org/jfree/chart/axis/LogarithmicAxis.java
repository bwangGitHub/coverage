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
 * LogarithmicAxis.java
 * --------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Michael Duffy / Eric Thomas;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   David M. O'Donnell;
 *                   Scott Sams;
 *                   Sergei Ivanov;
 *
 * Changes
 * -------
 * 14-Mar-2002 : Version 1 contributed by Michael Duffy (DG);
 * 19-Apr-2002 : drawVerticalString() is now drawRotatedString() in
 *               RefineryUtilities (DG);
 * 23-Apr-2002 : Added a range property (DG);
 * 15-May-2002 : Modified to be able to deal with negative and zero values (via
 *               new 'adjustedLog10()' method);  occurrences of "Math.log(10)"
 *               changed to "LOG10_VALUE"; changed 'intValue()' to
 *               'longValue()' in 'refreshTicks()' to fix label-text value
 *               out-of-range problem; removed 'draw()' method; added
 *               'autoRangeMinimumSize' check; added 'log10TickLabelsFlag'
 *               parameter flag and implementation (ET);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 16-Jul-2002 : Implemented support for plotting positive values arbitrarily
 *               close to zero (added 'allowNegativesFlag' flag) (ET).
 * 05-Sep-2002 : Updated constructor reflecting changes in the Axis class (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 22-Nov-2002 : Bug fixes from David M. O'Donnell (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double (DG);
 * 20-Jan-2003 : Removed unnecessary constructors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 08-May-2003 : Fixed plotting of datasets with lower==upper bounds when
 *               'minAutoRange' is very small; added 'strictValuesFlag'
 *               and default functionality of throwing a runtime exception
 *               if 'allowNegativesFlag' is false and any values are less
 *               than or equal to zero; added 'expTickLabelsFlag' and
 *               changed to use "1e#"-style tick labels by default
 *               ("10^n"-style tick labels still supported via 'set'
 *               method); improved generation of tick labels when range of
 *               values is small; changed to use 'NumberFormat.getInstance()'
 *               to create 'numberFormatterObj' (ET);
 * 14-May-2003 : Merged HorizontalLogarithmicAxis and
 *               VerticalLogarithmicAxis (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 07-Nov-2003 : Modified to use new NumberTick class (DG);
 * 08-Apr-2004 : Use numberFormatOverride if set - see patch 930139 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 21-Apr-2005 : Added support for upper and lower margins; added
 *               get/setAutoRangeNextLogFlag() methods and changed
 *               default to 'autoRangeNextLogFlag'==false (ET);
 * 22-Apr-2005 : Removed refreshTicks() and fixed names and parameters for
 *               refreshHorizontalTicks() & refreshVerticalTicks();
 *               changed javadoc on setExpTickLabelsFlag() to specify
 *               proper default (ET);
 * 22-Apr-2005 : Renamed refreshHorizontalTicks --> refreshTicksHorizontal
 *               (and likewise the vertical version) for consistency with
 *               other axis classes (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 02-Mar-2007 : Applied patch 1671069 to fix zooming (DG);
 * 22-Mar-2007 : Use new defaultAutoRange attribute (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * A numerical axis that uses a logarithmic scale.
 */
public class LogarithmicAxis extends NumberAxis {
  static {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2502918599004103054L;
  static {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[1]++;
  }
    
    /** Useful constant for log(10). */
    public static final double LOG10_VALUE = Math.log(10.0);
  static {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[2]++;
  }

    /** Smallest arbitrarily-close-to-zero value allowed. */
    public static final double SMALL_LOG_VALUE = 1e-100;
  static {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[3]++;
  }

    /** Flag set true to allow negative values in data. */
    protected boolean allowNegativesFlag = false;
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[4]++;
  }

    /** 
     * Flag set true make axis throw exception if any values are
     * <= 0 and 'allowNegativesFlag' is false. 
     */
    protected boolean strictValuesFlag = true;
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[5]++;
  }

    /** Number formatter for generating numeric strings. */
    protected final NumberFormat numberFormatterObj
        = NumberFormat.getInstance();
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[6]++;
  }

    /** Flag set true for "1e#"-style tick labels. */
    protected boolean expTickLabelsFlag = false;
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[7]++;
  }

    /** Flag set true for "10^n"-style tick labels. */
    protected boolean log10TickLabelsFlag = false;
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[8]++;
  }

    /** True to make 'autoAdjustRange()' select "10^n" values. */
    protected boolean autoRangeNextLogFlag = false;
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[9]++;
  }

    /** Helper flag for log axis processing. */
    protected boolean smallLogFlag = false;
  {
    CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[10]++;
  }

    /**
     * Creates a new axis.
     *
     * @param label  the axis label.
     */
    public LogarithmicAxis(String label) {
        super(label);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[11]++;
        setupNumberFmtObj();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[12]++;      //setup number formatter obj
    }

    /**
     * Sets the 'allowNegativesFlag' flag; true to allow negative values
     * in data, false to be able to plot positive values arbitrarily close to
     * zero.
     *
     * @param flgVal  the new value of the flag.
     */
    public void setAllowNegativesFlag(boolean flgVal) {
        this.allowNegativesFlag = flgVal;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[13]++;
    }

    /**
     * Returns the 'allowNegativesFlag' flag; true to allow negative values
     * in data, false to be able to plot positive values arbitrarily close
     * to zero.
     *
     * @return The flag.
     */
    public boolean getAllowNegativesFlag() {
        return this.allowNegativesFlag;
    }

    /**
     * Sets the 'strictValuesFlag' flag; if true and 'allowNegativesFlag'
     * is false then this axis will throw a runtime exception if any of its
     * values are less than or equal to zero; if false then the axis will
     * adjust for values less than or equal to zero as needed.
     *
     * @param flgVal true for strict enforcement.
     */
    public void setStrictValuesFlag(boolean flgVal) {
        this.strictValuesFlag = flgVal;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[14]++;
    }

    /**
     * Returns the 'strictValuesFlag' flag; if true and 'allowNegativesFlag'
     * is false then this axis will throw a runtime exception if any of its
     * values are less than or equal to zero; if false then the axis will
     * adjust for values less than or equal to zero as needed.
     *
     * @return <code>true</code> if strict enforcement is enabled.
     */
    public boolean getStrictValuesFlag() {
        return this.strictValuesFlag;
    }

    /**
     * Sets the 'expTickLabelsFlag' flag.  If the 'log10TickLabelsFlag'
     * is false then this will set whether or not "1e#"-style tick labels
     * are used.  The default is to use regular numeric tick labels.
     *
     * @param flgVal true for "1e#"-style tick labels, false for
     * log10 or regular numeric tick labels.
     */
    public void setExpTickLabelsFlag(boolean flgVal) {
        this.expTickLabelsFlag = flgVal;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[15]++;
        setupNumberFmtObj();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[16]++;             //setup number formatter obj
    }

    /**
     * Returns the 'expTickLabelsFlag' flag.
     *
     * @return <code>true</code> for "1e#"-style tick labels,
     *         <code>false</code> for log10 or regular numeric tick labels.
     */
    public boolean getExpTickLabelsFlag() {
      return this.expTickLabelsFlag;
    }

    /**
     * Sets the 'log10TickLabelsFlag' flag.  The default value is false.
     *
     * @param flag true for "10^n"-style tick labels, false for "1e#"-style
     * or regular numeric tick labels.
     */
    public void setLog10TickLabelsFlag(boolean flag) {
        this.log10TickLabelsFlag = flag;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[17]++;
    }

    /**
     * Returns the 'log10TickLabelsFlag' flag.
     *
     * @return <code>true</code> for "10^n"-style tick labels,
     *         <code>false</code> for "1e#"-style or regular numeric tick
     *         labels.
     */
    public boolean getLog10TickLabelsFlag() {
        return this.log10TickLabelsFlag;
    }

    /**
     * Sets the 'autoRangeNextLogFlag' flag.  This determines whether or
     * not the 'autoAdjustRange()' method will select the next "10^n"
     * values when determining the upper and lower bounds.  The default
     * value is false.
     *
     * @param flag <code>true</code> to make the 'autoAdjustRange()'
     * method select the next "10^n" values, <code>false</code> to not.
     */
    public void setAutoRangeNextLogFlag(boolean flag) {
        this.autoRangeNextLogFlag = flag;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[18]++;
    }

    /**
     * Returns the 'autoRangeNextLogFlag' flag.
     *
     * @return <code>true</code> if the 'autoAdjustRange()' method will
     * select the next "10^n" values, <code>false</code> if not.
     */
    public boolean getAutoRangeNextLogFlag() {
        return this.autoRangeNextLogFlag;
    }

    /**
     * Overridden version that calls original and then sets up flag for
     * log axis processing.
     *
     * @param range  the new range.
     */
    public void setRange(Range range) {
        super.setRange(range);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[19]++;      // call parent method
        setupSmallLogFlag();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[20]++;        // setup flag based on bounds values
    }

    /**
     * Sets up flag for log axis processing.  Set true if negative values
     * not allowed and the lower bound is between 0 and 10.
     */
    protected void setupSmallLogFlag() {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[21]++;
        // set flag true if negative values not allowed and the
        // lower bound is between 0 and 10:
        double lowerVal = getRange().getLowerBound();
        this.smallLogFlag = (!this.allowNegativesFlag && lowerVal < 10.0 
                && lowerVal > 0.0);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[22]++;
    }

    /**
     * Sets up the number formatter object according to the
     * 'expTickLabelsFlag' flag.
     */
    protected void setupNumberFmtObj() {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[23]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.numberFormatterObj instanceof DecimalFormat) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[1]++;
            //setup for "1e#"-style tick labels or regular
            // numeric tick labels, depending on flag:
            ((DecimalFormat) this.numberFormatterObj).applyPattern(
                    this.expTickLabelsFlag ? "0E0" : "0.###");
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[24]++;

        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[2]++;}
    }

    /**
     * Returns the log10 value, depending on if values between 0 and
     * 1 are being plotted.  If negative values are not allowed and
     * the lower bound is between 0 and 10 then a normal log is
     * returned; otherwise the returned value is adjusted if the
     * given value is less than 10.
     *
     * @param val the value.
     *
     * @return log<sub>10</sub>(val).
     * 
     * @see #switchedPow10(double) 
     */
    protected double switchedLog10(double val) {
        return this.smallLogFlag ? Math.log(val)
                / LOG10_VALUE : adjustedLog10(val);
    }

    /** 
     * Returns a power of 10, depending on if values between 0 and
     * 1 are being plotted.  If negative values are not allowed and
     * the lower bound is between 0 and 10 then a normal power is
     * returned; otherwise the returned value is adjusted if the
     * given value is less than 1.
     * 
     * @param val the value.
     * 
     * @return 10<sup>val</sup>.
     * 
     * @since 1.0.5
     * @see #switchedLog10(double) 
     */
    public double switchedPow10(double val) {
        return this.smallLogFlag ? Math.pow(10.0, val) : adjustedPow10(val);
    }

    /**
     * Returns an adjusted log10 value for graphing purposes.  The first
     * adjustment is that negative values are changed to positive during
     * the calculations, and then the answer is negated at the end.  The
     * second is that, for values less than 10, an increasingly large
     * (0 to 1) scaling factor is added such that at 0 the value is
     * adjusted to 1, resulting in a returned result of 0.
     *
     * @param val  value for which log10 should be calculated.
     *
     * @return An adjusted log<sub>10</sub>(val).
     * 
     * @see #adjustedPow10(double) 
     */
    public double adjustedLog10(double val) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[25]++;
        boolean negFlag = (val < 0.0);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((negFlag) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[3]++;
            val = -val;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[27]++;
          // if negative then set flag and make positive
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[4]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((val < 10.0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[5]++;                // if < 10 then
            val += (10.0 - val) / 10.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[29]++;
  //increase so 0 translates to 0
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[6]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[30]++;
        //return value; negate if original value was negative:
        double res = Math.log(val) / LOG10_VALUE;
        return negFlag ? (-res) : res;
    }

    /**
     * Returns an adjusted power of 10 value for graphing purposes.  The first
     * adjustment is that negative values are changed to positive during
     * the calculations, and then the answer is negated at the end.  The
     * second is that, for values less than 1, a progressive logarithmic
     * offset is subtracted such that at 0 the returned result is also 0.
     *
     * @param val  value for which power of 10 should be calculated.
     *
     * @return An adjusted 10<sup>val</sup>.
     * 
     * @since 1.0.5
     * @see #adjustedLog10(double)
     */
    public double adjustedPow10(double val) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[31]++;
        boolean negFlag = (val < 0.0);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((negFlag) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[7]++;
            val = -val;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[33]++;
 // if negative then set flag and make positive
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[8]++;}
        double res;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((val < 1.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[9]++;
            res = (Math.pow(10, val + 1.0) - 10.0) / 9.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[35]++;
 //invert adjustLog10
        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[10]++;
            res = Math.pow(10, val);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[36]++;            
        }
        return negFlag ? (-res) : res;
    }

    /**
     * Returns the largest (closest to positive infinity) double value that is
     * not greater than the argument, is equal to a mathematical integer and
     * satisfying the condition that log base 10 of the value is an integer
     * (i.e., the value returned will be a power of 10: 1, 10, 100, 1000, etc.).
     *
     * @param lower a double value below which a floor will be calcualted.
     *
     * @return 10<sup>N</sup> with N .. { 1 ... }
     */
    protected double computeLogFloor(double lower) {

        double logFloor;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.allowNegativesFlag) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[11]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
            //negative values are allowed
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lower > 10.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[13]++;   //parameter value is > 10
                // The Math.log() function is based on e not 10.
                logFloor = Math.log(lower) / LOG10_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[39]++;
                logFloor = Math.floor(logFloor);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[40]++;
                logFloor = Math.pow(10, logFloor);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[41]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[14]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[42]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lower < -10.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[15]++;   //parameter value is < -10
                //calculate log using positive value:
                logFloor = Math.log(-lower) / LOG10_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[43]++;
                //calculate floor using negative value:
                logFloor = Math.floor(-logFloor);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[44]++;
                //calculate power using positive value; then negate
                logFloor = -Math.pow(10, -logFloor);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[45]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[16]++;
                //parameter value is -10 > val < 10
                logFloor = Math.floor(lower);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[46]++;   //use as-is
            }
}

        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[12]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
            //negative values not allowed
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((lower > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[17]++;   //parameter value is > 0
                // The Math.log() function is based on e not 10.
                logFloor = Math.log(lower) / LOG10_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[48]++;
                logFloor = Math.floor(logFloor);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[49]++;
                logFloor = Math.pow(10, logFloor);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[50]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[18]++;
                //parameter value is <= 0
                logFloor = Math.floor(lower);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[51]++;   //use as-is
            }
        }
        return logFloor;
    }

    /**
     * Returns the smallest (closest to negative infinity) double value that is
     * not less than the argument, is equal to a mathematical integer and
     * satisfying the condition that log base 10 of the value is an integer
     * (i.e., the value returned will be a power of 10: 1, 10, 100, 1000, etc.).
     *
     * @param upper a double value above which a ceiling will be calcualted.
     *
     * @return 10<sup>N</sup> with N .. { 1 ... }
     */
    protected double computeLogCeil(double upper) {

        double logCeil;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.allowNegativesFlag) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[19]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
            //negative values are allowed
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((upper > 10.0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[21]++;
                //parameter value is > 10
                // The Math.log() function is based on e not 10.
                logCeil = Math.log(upper) / LOG10_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[54]++;
                logCeil = Math.ceil(logCeil);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[55]++;
                logCeil = Math.pow(10, logCeil);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[56]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[22]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[57]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((upper < -10.0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[23]++;
                //parameter value is < -10
                //calculate log using positive value:
                logCeil = Math.log(-upper) / LOG10_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[58]++;
                //calculate ceil using negative value:
                logCeil = Math.ceil(-logCeil);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[59]++;
                //calculate power using positive value; then negate
                logCeil = -Math.pow(10, -logCeil);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[60]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[24]++;
               //parameter value is -10 > val < 10
               logCeil = Math.ceil(upper);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[61]++;     //use as-is
            }
}

        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[20]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[62]++;
int CodeCoverConditionCoverageHelper_C13;
            //negative values not allowed
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((upper > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[25]++;
                //parameter value is > 0
                // The Math.log() function is based on e not 10.
                logCeil = Math.log(upper) / LOG10_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[63]++;
                logCeil = Math.ceil(logCeil);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[64]++;
                logCeil = Math.pow(10, logCeil);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[65]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[26]++;
                //parameter value is <= 0
                logCeil = Math.ceil(upper);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[66]++;     //use as-is
            }
        }
        return logCeil;
    }

    /**
     * Rescales the axis to ensure that all data is visible.
     */
    public void autoAdjustRange() {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[67]++;

        Plot plot = getPlot();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[68]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[27]++;
            return;
  // no plot, no data.
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[28]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[69]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((plot instanceof ValueAxisPlot) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[29]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[70]++;
            ValueAxisPlot vap = (ValueAxisPlot) plot;

            double lower;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[71]++;
            Range r = vap.getDataRange(this);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[72]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[31]++;
                   //no real data present
                r = getDefaultAutoRange();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[73]++;
                lower = r.getLowerBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[74]++;
    //get lower bound value
            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[32]++;
                //actual data is present
                lower = r.getLowerBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[75]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[76]++;
int CodeCoverConditionCoverageHelper_C17;    //get lower bound value
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((this.strictValuesFlag) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((this.allowNegativesFlag) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((lower <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[33]++;
                    //strict flag set, allow-negatives not set and values <= 0
                    throw new RuntimeException("Values less than or equal to "
                            + "zero not allowed with logarithmic axis");

                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[34]++;}
            }

            //apply lower margin by decreasing lower bound:
            final double lowerMargin;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[77]++;
            if (lower > 0.0 && (lowerMargin = getLowerMargin()) > 0.0) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[35]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[78]++;
                   //lower bound and margin OK; get log10 of lower bound
                final double logLower = (Math.log(lower) / LOG10_VALUE);
                double logAbs;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[79]++;      //get absolute value of log10 value
                if ((logAbs = Math.abs(logLower)) < 1.0) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[37]++;
                    logAbs = 1.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[80]++;
     //if less than 1.0 then make it 1.0
                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[38]++;}              //subtract out margin and get exponential value:
                lower = Math.pow(10, (logLower - (logAbs * lowerMargin)));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[81]++;

            } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[36]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[82]++;
int CodeCoverConditionCoverageHelper_C20;

            //if flag then change to log version of lowest value
            // to make range begin at a 10^n value:
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.autoRangeNextLogFlag) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[39]++;
                lower = computeLogFloor(lower);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[83]++;

            } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[40]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[84]++;
int CodeCoverConditionCoverageHelper_C21;

            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((this.allowNegativesFlag) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((lower >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((lower < SMALL_LOG_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[41]++;
                //negatives not allowed and lower range bound is zero
                lower = r.getLowerBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[85]++;
    //use data range bound instead
            } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[42]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[86]++;

            double upper = r.getUpperBound();

             //apply upper margin by increasing upper bound:
            final double upperMargin;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[87]++;
            if (upper > 0.0 && (upperMargin = getUpperMargin()) > 0.0) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[43]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[88]++;
                   //upper bound and margin OK; get log10 of upper bound
                final double logUpper = (Math.log(upper) / LOG10_VALUE);
                double logAbs;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[89]++;      //get absolute value of log10 value
                if ((logAbs = Math.abs(logUpper)) < 1.0) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[45]++;
                    logAbs = 1.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[90]++;
     //if less than 1.0 then make it 1.0
                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[46]++;}              //add in margin and get exponential value:
                upper = Math.pow(10, (logUpper + (logAbs * upperMargin)));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[91]++;

            } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[44]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[92]++;
int CodeCoverConditionCoverageHelper_C24;

            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (128)) == 0 || true) &&
 ((this.allowNegativesFlag) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((upper < 1.0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((upper > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((lower > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 4) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 4) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[47]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[93]++;
                //negatives not allowed and upper bound between 0 & 1
                //round up to nearest significant digit for bound:
                //get negative exponent:
                double expVal = Math.log(upper) / LOG10_VALUE;
                expVal = Math.ceil(-expVal + 0.001);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[94]++; //get positive exponent
                expVal = Math.pow(10, expVal);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[95]++;      //create multiplier value
                //multiply, round up, and divide for bound value:
                upper = (expVal > 0.0) ? Math.ceil(upper * expVal) / expVal
                    : Math.ceil(upper);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[96]++;

            }
            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[48]++;
                //negatives allowed or upper bound not between 0 & 1
                //if flag then change to log version of highest value to
                // make range begin at a 10^n value; else use nearest int
                upper = (this.autoRangeNextLogFlag) ? computeLogCeil(upper)
                    : Math.ceil(upper);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[97]++;
            }
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[98]++;
            // ensure the autorange is at least <minRange> in size...
            double minRange = getAutoRangeMinimumSize();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[99]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((upper - lower < minRange) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[49]++;
                upper = (upper + lower + minRange) / 2;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[100]++;
                lower = (upper + lower - minRange) / 2;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[101]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[102]++;
int CodeCoverConditionCoverageHelper_C26;
                //if autorange still below minimum then adjust by 1%
                // (can be needed when minRange is very small):
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((upper - lower < minRange) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[51]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[103]++;
                    double absUpper = Math.abs(upper);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[104]++;
                    //need to account for case where upper==0.0
                    double adjVal = (absUpper > SMALL_LOG_VALUE) ? absUpper
                        / 100.0 : 0.01;
                    upper = (upper + lower + adjVal) / 2;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[105]++;
                    lower = (upper + lower - adjVal) / 2;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[106]++;

                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[52]++;}

            } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[50]++;}

            setRange(new Range(lower, upper), false, false);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[107]++;
            setupSmallLogFlag();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[108]++;
       //setup flag based on bounds values
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[30]++;}
    }

    /**
     * Converts a data value to a coordinate in Java2D space, assuming that
     * the axis runs along one edge of the specified plotArea.
     * Note that it is possible for the coordinate to fall outside the
     * plotArea.
     *
     * @param value  the data value.
     * @param plotArea  the area for plotting the data.
     * @param edge  the axis location.
     *
     * @return The Java2D coordinate.
     */
    public double valueToJava2D(double value, Rectangle2D plotArea,
                                RectangleEdge edge) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[109]++;

        Range range = getRange();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[110]++;
        double axisMin = switchedLog10(range.getLowerBound());
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[111]++;
        double axisMax = switchedLog10(range.getUpperBound());
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[112]++;

        double min = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[113]++;
        double max = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[114]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[53]++;
            min = plotArea.getMinX();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[115]++;
            max = plotArea.getMaxX();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[116]++;

        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[54]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[117]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[55]++;
            min = plotArea.getMaxY();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[118]++;
            max = plotArea.getMinY();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[119]++;

        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[56]++;}
}

        value = switchedLog10(value);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[120]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[121]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[57]++;
            return max - (((value - axisMin) / (axisMax - axisMin)) 
                    * (max - min));

        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[58]++;
            return min + (((value - axisMin) / (axisMax - axisMin)) 
                    * (max - min));
        }

    }

    /**
     * Converts a coordinate in Java2D space to the corresponding data
     * value, assuming that the axis runs along one edge of the specified
     * plotArea.
     *
     * @param java2DValue  the coordinate in Java2D space.
     * @param plotArea  the area in which the data is plotted.
     * @param edge  the axis location.
     *
     * @return The data value.
     */
    public double java2DToValue(double java2DValue, Rectangle2D plotArea,
                                RectangleEdge edge) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[122]++;

        Range range = getRange();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[123]++;
        double axisMin = switchedLog10(range.getLowerBound());
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[124]++;
        double axisMax = switchedLog10(range.getUpperBound());
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[125]++;

        double plotMin = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[126]++;
        double plotMax = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[127]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[59]++;
            plotMin = plotArea.getX();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[128]++;
            plotMax = plotArea.getMaxX();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[129]++;

        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[60]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[130]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[61]++;
            plotMin = plotArea.getMaxY();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[131]++;
            plotMax = plotArea.getMinY();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[132]++;

        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[62]++;}
}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[133]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[63]++;
            return switchedPow10(axisMax - ((java2DValue - plotMin) 
                    / (plotMax - plotMin)) * (axisMax - axisMin));

        }
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[64]++;
            return switchedPow10(axisMin + ((java2DValue - plotMin) 
                    / (plotMax - plotMin)) * (axisMax - axisMin));
        }
    }

    /**
     * Zooms in on the current range.
     * 
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomRange(double lowerPercent, double upperPercent) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[134]++;
        double startLog = switchedLog10(getRange().getLowerBound());
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[135]++;
        double lengthLog = switchedLog10(getRange().getUpperBound()) - startLog;
        Range adjusted;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[136]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[65]++;
            adjusted = new Range(
                    switchedPow10(
                            startLog + (lengthLog * (1 - upperPercent))),
                    switchedPow10(
                            startLog + (lengthLog * (1 - lowerPercent))));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[137]++;

        } 
        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[66]++;
            adjusted = new Range(
                    switchedPow10(startLog + (lengthLog * lowerPercent)),
                    switchedPow10(startLog + (lengthLog * upperPercent)));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[138]++;
        }

        setRange(adjusted);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[139]++;
    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
    protected List refreshTicksHorizontal(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          RectangleEdge edge) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[140]++;

        List ticks = new java.util.ArrayList();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[141]++;
        Range range = getRange();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[142]++;

        //get lower bound value:
        double lowerBoundVal = range.getLowerBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[143]++;
int CodeCoverConditionCoverageHelper_C34;
              //if small log values and lower bound value too small
              // then set to a small value (don't allow <= 0):
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((this.smallLogFlag) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((lowerBoundVal < SMALL_LOG_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[67]++;
            lowerBoundVal = SMALL_LOG_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[144]++;

        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[68]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[145]++;

        //get upper bound value
        double upperBoundVal = range.getUpperBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[146]++;

        //get log10 version of lower bound and round to integer:
        int iBegCount = (int) Math.rint(switchedLog10(lowerBoundVal));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[147]++;
        //get log10 version of upper bound and round to integer:
        int iEndCount = (int) Math.rint(switchedLog10(upperBoundVal));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[148]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (32)) == 0 || true) &&
 ((iBegCount == iEndCount) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((iBegCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((Math.pow(10, iBegCount) > lowerBoundVal) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[69]++;
              //only 1 power of 10 value, it's > 0 and its resulting
              // tick value will be larger than lower bound of data
            --iBegCount;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[149]++;
       //decrement to generate more ticks
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[70]++;}

        double currentTickValue;
        String tickLabel;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[150]++;
        boolean zeroTickFlag = false;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[151]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[1]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = iBegCount;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i <= iEndCount) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[1]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[2]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[3]++;
}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[152]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[4]++;


int CodeCoverConditionCoverageHelper_C37;
            //for each power of 10 value; create ten ticks
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((j < 10) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[4]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[5]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[6]++;
}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[153]++;
int CodeCoverConditionCoverageHelper_C38;
                //for each tick to be displayed
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.smallLogFlag) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[71]++;
                    //small log values in use; create numeric value for tick
                    currentTickValue = Math.pow(10, i) + (Math.pow(10, i) * j);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[154]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[155]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (128)) == 0 || true) &&
 ((this.expTickLabelsFlag) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (64)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((i < 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((currentTickValue > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((currentTickValue < 1.0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 4) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 4) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[73]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[156]++;
int CodeCoverConditionCoverageHelper_C40;
                        //showing "1e#"-style ticks or negative exponent
                        // generating tick value between 0 & 1; show fewer
                        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (128)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (64)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((i > -4) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((j < 2) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((currentTickValue >= upperBoundVal) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 4) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 4) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[75]++;
                          //first tick of series, or not too small a value and
                          // one of first 3 ticks, or last tick to be displayed
                            // set exact number of fractional digits to be shown
                            // (no effect if showing "1e#"-style ticks):
                            this.numberFormatterObj
                                .setMaximumFractionDigits(-i);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[157]++;
                               //create tick label (force use of fmt obj):
                            tickLabel = makeTickLabel(currentTickValue, true);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[158]++;

                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[76]++;    //no tick label to be shown
                            tickLabel = "";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[159]++;
                        }

                    }
                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[74]++;     //tick value not between 0 & 1
                               //show tick label if it's the first or last in
                               // the set, or if it's 1-5; beyond that show
                               // fewer as the values get larger:
                        tickLabel = (j < 1 || (i < 1 && j < 5) || (j < 4 - i)
                                         || currentTickValue >= upperBoundVal)
                                         ? makeTickLabel(currentTickValue) : "";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[160]++;
                    }

                }
                else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[72]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[161]++;
int CodeCoverConditionCoverageHelper_C41; //not small log values in use; allow for values <= 0
                    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((zeroTickFlag) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[77]++;   //if did zero tick last iter then
                        --j;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[162]++;
              //decrement to do 1.0 tick now
                    } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[78]++;}     //calculate power-of-ten value for tick:
                    currentTickValue = (i >= 0)
                        ? Math.pow(10, i) + (Math.pow(10, i) * j)
                        : -(Math.pow(10, -i) - (Math.pow(10, -i - 1) * j));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[163]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[164]++;
int CodeCoverConditionCoverageHelper_C42;
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((zeroTickFlag) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[79]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[165]++;
int CodeCoverConditionCoverageHelper_C43;  // did not do zero tick last iteration
                        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((Math.abs(currentTickValue - 1.0) < 0.0001) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((lowerBoundVal <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((upperBoundVal >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[81]++;
                            //tick value is 1.0 and 0.0 is within data range
                            currentTickValue = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[166]++;     //set tick value to zero
                            zeroTickFlag = true;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[167]++;
        //indicate zero tick
                        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[82]++;}

                    }
                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[80]++;     //did zero tick last iteration
                        zeroTickFlag = false;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[168]++;         //clear flag
                    }               //create tick label string:
                               //show tick label if "1e#"-style and it's one
                               // of the first two, if it's the first or last
                               // in the set, or if it's 1-5; beyond that
                               // show fewer as the values get larger:
                    tickLabel = ((this.expTickLabelsFlag && j < 2)
                                || j < 1
                                || (i < 1 && j < 5) || (j < 4 - i)
                                || currentTickValue >= upperBoundVal)
                                   ? makeTickLabel(currentTickValue) : "";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[169]++;
                }
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[170]++;
int CodeCoverConditionCoverageHelper_C44;

                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((currentTickValue > upperBoundVal) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[83]++;
                    return ticks;
   // if past highest data value then exit
                                    // method
                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[84]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[171]++;
int CodeCoverConditionCoverageHelper_C45;

                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((currentTickValue >= lowerBoundVal - SMALL_LOG_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[85]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[172]++;
                    //tick value not below lowest data value
                    TextAnchor anchor = null;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[173]++;
                    TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[174]++;
                    double angle = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[175]++;
int CodeCoverConditionCoverageHelper_C46;
                    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[87]++;
                        anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[176]++;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[177]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[178]++;
int CodeCoverConditionCoverageHelper_C47;
                        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[89]++;
                            angle = Math.PI / 2.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[179]++;

                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[90]++;
                            angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[180]++;
                        }

                    }
                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[88]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[181]++;
int CodeCoverConditionCoverageHelper_C48;
                        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[91]++;
                            anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[182]++;
                            rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[183]++;

                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[92]++;
                            anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[184]++;
                            rotationAnchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[185]++;
                        }
                    }
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[186]++;

                    Tick tick = new NumberTick(new Double(currentTickValue), 
                            tickLabel, anchor, rotationAnchor, angle);
                    ticks.add(tick);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[187]++;

                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[86]++;}
            }
        }
        return ticks;

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
    protected List refreshTicksVertical(Graphics2D g2, 
                                        Rectangle2D dataArea,
                                        RectangleEdge edge) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[188]++;

        List ticks = new java.util.ArrayList();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[189]++;

        //get lower bound value:
        double lowerBoundVal = getRange().getLowerBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[190]++;
int CodeCoverConditionCoverageHelper_C49;
        //if small log values and lower bound value too small
        // then set to a small value (don't allow <= 0):
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((this.smallLogFlag) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((lowerBoundVal < SMALL_LOG_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[93]++;
            lowerBoundVal = SMALL_LOG_VALUE;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[191]++;

        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[94]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[192]++;
        //get upper bound value
        double upperBoundVal = getRange().getUpperBound();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[193]++;

        //get log10 version of lower bound and round to integer:
        int iBegCount = (int) Math.rint(switchedLog10(lowerBoundVal));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[194]++;
        //get log10 version of upper bound and round to integer:
        int iEndCount = (int) Math.rint(switchedLog10(upperBoundVal));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[195]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((iBegCount == iEndCount) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((iBegCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((Math.pow(10, iBegCount) > lowerBoundVal) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[95]++;
              //only 1 power of 10 value, it's > 0 and its resulting
              // tick value will be larger than lower bound of data
            --iBegCount;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[196]++;
       //decrement to generate more ticks
        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[96]++;}

        double tickVal;
        String tickLabel;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[197]++;
        boolean zeroTickFlag = false;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[198]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[7]++;


int CodeCoverConditionCoverageHelper_C51;
        for (int i = iBegCount;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((i <= iEndCount) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[7]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[8]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[9]++;
}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[199]++;
            //for each tick with a label to be displayed
            int jEndCount = 10;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[200]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((i == iEndCount) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[97]++;
                jEndCount = 1;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[201]++;

            } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[98]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[202]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[10]++;


int CodeCoverConditionCoverageHelper_C53;

            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((j < jEndCount) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[10]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[11]--;
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.loops[12]++;
}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[203]++;
int CodeCoverConditionCoverageHelper_C54;
                //for each tick to be displayed
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.smallLogFlag) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[99]++;
                    //small log values in use
                    tickVal = Math.pow(10, i) + (Math.pow(10, i) * j);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[204]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[205]++;
int CodeCoverConditionCoverageHelper_C55;
                    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[101]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[206]++;
int CodeCoverConditionCoverageHelper_C56;
                        //first tick of group; create label text
                        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.log10TickLabelsFlag) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[103]++;
                            //if flag then
                            tickLabel = "10^" + i;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[207]++;
   //create "log10"-type label
                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[104]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[208]++;
int CodeCoverConditionCoverageHelper_C57;    //not "log10"-type label
                            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.expTickLabelsFlag) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[105]++;
                                //if flag then
                                tickLabel = "1e" + i;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[209]++;
  //create "1e#"-type label
                            }
                            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[106]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[210]++;
int CodeCoverConditionCoverageHelper_C58;    //not "1e#"-type label
                                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[107]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[211]++;   // if positive exponent then
                                                // make integer
                                    NumberFormat format
                                        = getNumberFormatOverride();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[212]++;
int CodeCoverConditionCoverageHelper_C59;
                                    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((format != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[109]++;
                                        tickLabel = format.format(tickVal);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[213]++;

                                    }
                                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[110]++;
                                        tickLabel = Long.toString((long)
                                                Math.rint(tickVal));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[214]++;
                                    }

                                }
                                else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[108]++;
                                    //negative exponent; create fractional value
                                    //set exact number of fractional digits to
                                    // be shown:
                                    this.numberFormatterObj
                                        .setMaximumFractionDigits(-i);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[215]++;
                                    //create tick label:
                                    tickLabel = this.numberFormatterObj.format(
                                            tickVal);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[216]++;
                                }
                            }
                        }

                    }
                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[102]++;   //not first tick to be displayed
                        tickLabel = "";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[217]++;     //no tick label
                    }

                }
                else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[100]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[218]++;
int CodeCoverConditionCoverageHelper_C60; //not small log values in use; allow for values <= 0
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((zeroTickFlag) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[111]++;      //if did zero tick last iter then
                        --j;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[219]++;

                    } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[112]++;}               //decrement to do 1.0 tick now
                    tickVal = (i >= 0) ? Math.pow(10, i) + (Math.pow(10, i) * j)
                             : -(Math.pow(10, -i) - (Math.pow(10, -i - 1) * j));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[220]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[221]++;
int CodeCoverConditionCoverageHelper_C61;
                    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[113]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[222]++;
int CodeCoverConditionCoverageHelper_C62;  //first tick of group
                        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((zeroTickFlag) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[115]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[223]++;
int CodeCoverConditionCoverageHelper_C63;     // did not do zero tick last
                                                 // iteration
                            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((i > iBegCount) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((i < iEndCount) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((Math.abs(tickVal - 1.0) < 0.0001) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[117]++;
                                // not first or last tick on graph and value
                                // is 1.0
                                tickVal = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[224]++;        //change value to 0.0
                                zeroTickFlag = true;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[225]++;  //indicate zero tick
                                tickLabel = "0";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[226]++;
      //create label for tick
                            }
                            else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[118]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[227]++;
int CodeCoverConditionCoverageHelper_C64;
                                //first or last tick on graph or value is 1.0
                                //create label for tick:
                                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.log10TickLabelsFlag) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[119]++;
                                       //create "log10"-type label
                                    tickLabel = (((i < 0) ? "-" : "")
                                            + "10^" + Math.abs(i));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[228]++;

                                }
                                else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[120]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[229]++;
int CodeCoverConditionCoverageHelper_C65;
                                    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.expTickLabelsFlag) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[121]++;
                                           //create "1e#"-type label
                                        tickLabel = (((i < 0) ? "-" : "")
                                                + "1e" + Math.abs(i));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[230]++;

                                    }
                                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[122]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[231]++;
                                        NumberFormat format
                                            = getNumberFormatOverride();
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[232]++;
int CodeCoverConditionCoverageHelper_C66;
                                        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((format != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[123]++;
                                            tickLabel = format.format(tickVal);
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[233]++;

                                        }
                                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[124]++;
                                            tickLabel =  Long.toString(
                                                    (long) Math.rint(tickVal));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[234]++;
                                        }
                                    }
                                }
                            }

                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[116]++;     // did zero tick last iteration
                            tickLabel = "";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[235]++;         //no label
                            zeroTickFlag = false;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[236]++;   //clear flag
                        }

                    }
                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[114]++;       // not first tick of group
                        tickLabel = "";
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[237]++;           //no label
                        zeroTickFlag = false;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[238]++;     //make sure flag cleared
                    }
                }
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[239]++;
int CodeCoverConditionCoverageHelper_C67;

                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((tickVal > upperBoundVal) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[125]++;
                    return ticks;
  //if past highest data value then exit method
                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[126]++;}
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[240]++;
int CodeCoverConditionCoverageHelper_C68;

                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((tickVal >= lowerBoundVal - SMALL_LOG_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[127]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[241]++;
                    //tick value not below lowest data value
                    TextAnchor anchor = null;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[242]++;
                    TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[243]++;
                    double angle = 0.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[244]++;
int CodeCoverConditionCoverageHelper_C69;
                    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[129]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[245]++;
int CodeCoverConditionCoverageHelper_C70;
                        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[131]++;
                            anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[246]++;
                            rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[247]++;
                            angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[248]++;

                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[132]++;
                            anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[249]++;
                            rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[250]++;
                            angle = Math.PI / 2.0;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[251]++;
                        }

                    }
                    else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[130]++;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[252]++;
int CodeCoverConditionCoverageHelper_C71;
                        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[133]++;
                            anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[253]++;
                            rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[254]++;

                        }
                        else {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[134]++;
                            anchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[255]++;
                            rotationAnchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[256]++;
                        }
                    }
                    //create tick object and add to list:
                    ticks.add(new NumberTick(new Double(tickVal), tickLabel, 
                            anchor, rotationAnchor, angle));
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[257]++;

                } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[128]++;}
            }
        }
        return ticks;
    }

    /**
     * Converts the given value to a tick label string.
     *
     * @param val the value to convert.
     * @param forceFmtFlag true to force the number-formatter object
     * to be used.
     *
     * @return The tick label string.
     */
    protected String makeTickLabel(double val, boolean forceFmtFlag) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.statements[258]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((this.expTickLabelsFlag) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((forceFmtFlag) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[135]++;
            //using exponents or force-formatter flag is set
            // (convert 'E' to lower-case 'e'):
            return this.numberFormatterObj.format(val).toLowerCase();

        } else {
  CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81.branches[136]++;}
        return getTickUnit().valueToString(val);
    }

    /**
     * Converts the given value to a tick label string.
     * @param val the value to convert.
     *
     * @return The tick label string.
     */
    protected String makeTickLabel(double val) {
        return makeTickLabel(val, false);
    }

}

class CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81 ());
  }
    public static long[] statements = new long[259];
    public static long[] branches = new long[137];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.LogarithmicAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,0,0,1,3,0,0,3,1,1,1,1,1,1,1,1,1,2,3,1,1,1,3,3,1,1,3,1,1,1,1,1,2,3,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 72; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$8xfb6x0c70phm25m1257z3oc1iivo81 () {
    super("org.jfree.chart.axis.LogarithmicAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 258; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 136; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.LogarithmicAxis.java");
      for (int i = 1; i <= 258; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 136; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

