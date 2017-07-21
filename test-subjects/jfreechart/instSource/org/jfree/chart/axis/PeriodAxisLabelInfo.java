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
 * ------------------------
 * PeriodAxisLabelInfo.java
 * ------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 01-Jun-2004 : Version 1 (DG);
 * 23-Feb-2005 : Replaced Spacer with RectangleInsets (DG);
 * 01-Mar-2005 : Modified constructors to accept DateFormat (DG);
 * 20-May-2005 : Added default constants and null argument checks in the 
 *               constructor (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;

/**
 * A record that contains information for one "band" of date labels in 
 * a {@link PeriodAxis}.
 */
public class PeriodAxisLabelInfo implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.ping();
  }

    
    // TODO: this class is mostly immutable, so implementing Cloneable isn't
    // really necessary.  But there is still a hole in that you can get the
    // dateFormat and modify it.  We could return a copy, but that would slow
    // things down. Needs resolving.
    
    /** For serialization. */
    private static final long serialVersionUID = 5710451740920277357L;
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[1]++;
  }
    
    /** The default insets. */
    public static final RectangleInsets DEFAULT_INSETS 
        = new RectangleInsets(2, 2, 2, 2);
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[2]++;
  }
    
    /** The default font. */
    public static final Font DEFAULT_FONT 
        = new Font("SansSerif", Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[3]++;
  }
    
    /** The default label paint. */
    public static final Paint DEFAULT_LABEL_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[4]++;
  }
    
    /** The default divider stroke. */
    public static final Stroke DEFAULT_DIVIDER_STROKE = new BasicStroke(0.5f);
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[5]++;
  }
    
    /** The default divider paint. */
    public static final Paint DEFAULT_DIVIDER_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[6]++;
  }

    /** The subclass of {@link RegularTimePeriod} to use for this band. */
    private Class periodClass;
    
    /** Controls the gaps around the band. */
    private RectangleInsets padding;
    
    /** The date formatter. */
    private DateFormat dateFormat;
    
    /** The label font. */
    private Font labelFont;
    
    /** The label paint. */
    private transient Paint labelPaint;
    
    /** A flag that controls whether or not dividers are visible. */
    private boolean drawDividers;
    
    /** The stroke used to draw the dividers. */
    private transient Stroke dividerStroke;
    
    /** The paint used to draw the dividers. */
    private transient Paint dividerPaint;
        
    /**
     * Creates a new instance.
     * 
     * @param periodClass  the subclass of {@link RegularTimePeriod} to use 
     *                     (<code>null</code> not permitted).
     * @param dateFormat  the date format (<code>null</code> not permitted).
     */
    public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat) {
        this(
            periodClass, dateFormat, DEFAULT_INSETS, DEFAULT_FONT, 
            DEFAULT_LABEL_PAINT, true, DEFAULT_DIVIDER_STROKE, 
            DEFAULT_DIVIDER_PAINT
        );
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[7]++;
    }
    
    /**
     * Creates a new instance.
     * 
     * @param periodClass  the subclass of {@link RegularTimePeriod} to use
     *                     (<code>null</code> not permitted).
     * @param dateFormat  the date format (<code>null</code> not permitted).
     * @param padding  controls the space around the band (<code>null</code> 
     *                 not permitted).
     * @param labelFont  the label font (<code>null</code> not permitted).
     * @param labelPaint  the label paint (<code>null</code> not permitted).
     * @param drawDividers  a flag that controls whether dividers are drawn.
     * @param dividerStroke  the stroke used to draw the dividers 
     *                       (<code>null</code> not permitted).
     * @param dividerPaint  the paint used to draw the dividers 
     *                      (<code>null</code> not permitted).
     */
    public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat, 
                               RectangleInsets padding,
                               Font labelFont, Paint labelPaint, 
                               boolean drawDividers, Stroke dividerStroke, 
                               Paint dividerPaint) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((periodClass == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[1]++;
            throw new IllegalArgumentException("Null 'periodClass' argument.");

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[2]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dateFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[3]++;
            throw new IllegalArgumentException("Null 'dateFormat' argument.");

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[4]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[5]++;
            throw new IllegalArgumentException("Null 'padding' argument.");

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[6]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((labelFont == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[7]++;
            throw new IllegalArgumentException("Null 'labelFont' argument.");

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[8]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((labelPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[9]++;
            throw new IllegalArgumentException("Null 'labelPaint' argument.");

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[10]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dividerStroke == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[11]++;
            throw new IllegalArgumentException(
                    "Null 'dividerStroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[12]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dividerPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[13]++;
            throw new IllegalArgumentException("Null 'dividerPaint' argument.");

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[14]++;}
        this.periodClass = periodClass;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[15]++;
        this.dateFormat = dateFormat;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[16]++;
        this.padding = padding;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[17]++;
        this.labelFont = labelFont;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[18]++;
        this.labelPaint = labelPaint;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[19]++;
        this.drawDividers = drawDividers;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[20]++;
        this.dividerStroke = dividerStroke;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[21]++;
        this.dividerPaint = dividerPaint;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[22]++;
    }
    
    /**
     * Returns the subclass of {@link RegularTimePeriod} that should be used 
     * to generate the date labels.
     * 
     * @return The class.
     */
    public Class getPeriodClass() {
        return this.periodClass;   
    }
    
    /**
     * Returns the date formatter.
     * 
     * @return The date formatter (never <code>null</code>).
     */
    public DateFormat getDateFormat() {
        return this.dateFormat;   
    }
    
    /**
     * Returns the padding for the band.
     * 
     * @return The padding.
     */
    public RectangleInsets getPadding() {
        return this.padding;   
    }
    
    /**
     * Returns the label font.
     * 
     * @return The label font (never <code>null</code>).
     */
    public Font getLabelFont() {
        return this.labelFont;   
    }
    
    /**
     * Returns the label paint.
     * 
     * @return The label paint.
     */
    public Paint getLabelPaint() {
        return this.labelPaint;   
    }
    
    /**
     * Returns a flag that controls whether or not dividers are drawn.
     * 
     * @return A flag.
     */
    public boolean getDrawDividers() {
        return this.drawDividers;   
    }
    
    /**
     * Returns the stroke used to draw the dividers.
     * 
     * @return The stroke.
     */
    public Stroke getDividerStroke() {
        return this.dividerStroke;   
    }
    
    /**
     * Returns the paint used to draw the dividers.
     * 
     * @return The paint.
     */
    public Paint getDividerPaint() {
        return this.dividerPaint;   
    }
    
    /**
     * Creates a time period that includes the specified millisecond, assuming
     * the given time zone.
     * 
     * @param millisecond  the time.
     * @param zone  the time zone.
     * 
     * @return The time period.
     */
    public RegularTimePeriod createInstance(Date millisecond, TimeZone zone) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[23]++;
        RegularTimePeriod result = null;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[24]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[25]++;
            Constructor c = this.periodClass.getDeclaredConstructor(
                new Class[] {Date.class, TimeZone.class}
            );
            result = (RegularTimePeriod) c.newInstance(
                new Object[] {millisecond, zone}
            );
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[26]++;   
        }
        catch (Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[16]++;
            // do nothing            
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[15]++;
}
  }
        return result;  
    }

    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[17]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[18]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof PeriodAxisLabelInfo) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[19]++;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[29]++;
            PeriodAxisLabelInfo info = (PeriodAxisLabelInfo) obj;
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((info.periodClass.equals(this.periodClass)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[21]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[22]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((info.dateFormat.equals(this.dateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[23]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[24]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((info.padding.equals(this.padding)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[25]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[26]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((info.labelFont.equals(this.labelFont)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[27]++;
                return false;

            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[28]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[34]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((info.labelPaint.equals(this.labelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[29]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[30]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[35]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((info.drawDividers != this.drawDividers) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[31]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[32]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[36]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info.dividerStroke.equals(this.dividerStroke)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[33]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[34]++;}
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[37]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((info.dividerPaint.equals(this.dividerPaint)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[35]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[36]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.branches[20]++;}
        return false;
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[38]++;
        int result = 41;
        result = 37 * this.periodClass.hashCode();
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[39]++;
        result = 37 * this.dateFormat.hashCode();
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[40]++;
        return result;
    }
    
    /**
     * Returns a clone of the object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[41]++;
        PeriodAxisLabelInfo clone = (PeriodAxisLabelInfo) super.clone();
        return clone;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[42]++;
        SerialUtilities.writePaint(this.labelPaint, stream);
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[43]++;
        SerialUtilities.writeStroke(this.dividerStroke, stream);
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[44]++;
        SerialUtilities.writePaint(this.dividerPaint, stream);
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[45]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[46]++;
        this.labelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[47]++;
        this.dividerStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[48]++;
        this.dividerPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p.statements[49]++;
    }
   
}

class CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p ());
  }
    public static long[] statements = new long[50];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.PeriodAxisLabelInfo.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$ij2b5v92epfldzv0dw5y7wq0s4hlrzhetrb6p () {
    super("org.jfree.chart.axis.PeriodAxisLabelInfo.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 49; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.PeriodAxisLabelInfo.java");
      for (int i = 1; i <= 49; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

