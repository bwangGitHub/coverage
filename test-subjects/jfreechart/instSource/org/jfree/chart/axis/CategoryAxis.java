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
 * CategoryAxis.java
 * -----------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Pady Srinivasan (patch 1217634);
 *
 * Changes
 * -------
 * 21-Aug-2001 : Added standard header. Fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 04-Dec-2001 : Changed constructors to protected, and tidied up default 
 *               values (DG);
 * 19-Apr-2002 : Updated import statements (DG);
 * 05-Sep-2002 : Updated constructor for changes in Axis class (DG);
 * 06-Nov-2002 : Moved margins from the CategoryPlot class (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 22-Jan-2002 : Removed monolithic constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-May-2003 : Merged HorizontalCategoryAxis and VerticalCategoryAxis into 
 *               this class (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2003 : Fixed serialization bug (DG);
 * 26-Nov-2003 : Added category label offset (DG);
 * 06-Jan-2004 : Moved axis line attributes to Axis class, rationalised 
 *               category label position attributes (DG);
 * 07-Jan-2004 : Added new implementation for linewrapping of category 
 *               labels (DG);
 * 17-Feb-2004 : Moved deprecated code to bottom of source file (DG);
 * 10-Mar-2004 : Changed Dimension --> Dimension2D in text classes (DG);
 * 16-Mar-2004 : Added support for tooltips on category labels (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D 
 *               because of JDK bug 4976448 which persists on JDK 1.3.1 (DG);
 * 03-Sep-2004 : Added 'maxCategoryLabelLines' attribute (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * 21-Jan-2005 : Modified return type for RectangleAnchor.coordinates() 
 *               method (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 08-Jun-2005 : Fixed bug in axis layout (DG);
 * 22-Nov-2005 : Added a method to access the tool tip text for a category
 *               label (DG);
 * 23-Nov-2005 : Added per-category font and paint options - see patch 
 *               1217634 (DG);
 * ------------- JFreeChart 1.0.x ---------------------------------------------
 * 11-Jan-2006 : Fixed null pointer exception in drawCategoryLabels - see bug
 *               1403043 (DG);
 * 18-Aug-2006 : Fix for bug drawing category labels, thanks to Adriaan
 *               Joubert (1277726) (DG);
 * 02-Oct-2006 : Updated category label entity (DG);
 * 30-Oct-2006 : Updated refreshTicks() method to account for possibility of
 *               multiple domain axes (DG);
 * 07-Mar-2007 : Fixed bug in axis label positioning (DG);
 * 27-Sep-2007 : Added getCategorySeriesMiddle() method (DG);
 * 21-Nov-2007 : Fixed performance bug noted by FindBugs in the 
 *               equalPaintMaps() method (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.entity.CategoryLabelEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.text.TextBlock;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ShapeUtilities;

/**
 * An axis that displays categories.
 */
public class CategoryAxis extends Axis implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5886554608114265863L;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[1]++;
  }
    
    /** 
     * The default margin for the axis (used for both lower and upper margins).
     */
    public static final double DEFAULT_AXIS_MARGIN = 0.05;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[2]++;
  }

    /** 
     * The default margin between categories (a percentage of the overall axis
     * length). 
     */
    public static final double DEFAULT_CATEGORY_MARGIN = 0.20;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[3]++;
  }

    /** The amount of space reserved at the start of the axis. */
    private double lowerMargin;

    /** The amount of space reserved at the end of the axis. */
    private double upperMargin;

    /** The amount of space reserved between categories. */
    private double categoryMargin;
    
    /** The maximum number of lines for category labels. */
    private int maximumCategoryLabelLines;

    /** 
     * A ratio that is multiplied by the width of one category to determine the 
     * maximum label width. 
     */
    private float maximumCategoryLabelWidthRatio;
    
    /** The category label offset. */
    private int categoryLabelPositionOffset; 
    
    /** 
     * A structure defining the category label positions for each axis 
     * location. 
     */
    private CategoryLabelPositions categoryLabelPositions;
    
    /** Storage for tick label font overrides (if any). */
    private Map tickLabelFontMap;
    
    /** Storage for tick label paint overrides (if any). */
    private transient Map tickLabelPaintMap;
    
    /** Storage for the category label tooltips (if any). */
    private Map categoryLabelToolTips;

    /**
     * Creates a new category axis with no label.
     */
    public CategoryAxis() {
        this(null);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[4]++;    
    }
    
    /**
     * Constructs a category axis, using default values where necessary.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public CategoryAxis(String label) {

        super(label);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[5]++;

        this.lowerMargin = DEFAULT_AXIS_MARGIN;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[6]++;
        this.upperMargin = DEFAULT_AXIS_MARGIN;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[7]++;
        this.categoryMargin = DEFAULT_CATEGORY_MARGIN;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[8]++;
        this.maximumCategoryLabelLines = 1;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[9]++;
        this.maximumCategoryLabelWidthRatio = 0.0f;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[10]++;
        
        setTickMarksVisible(false);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[11]++;  // not supported by this axis type yet
        
        this.categoryLabelPositionOffset = 4;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[12]++;
        this.categoryLabelPositions = CategoryLabelPositions.STANDARD;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[13]++;
        this.tickLabelFontMap = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[14]++;
        this.tickLabelPaintMap = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[15]++;
        this.categoryLabelToolTips = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[16]++;
        
    }

    /**
     * Returns the lower margin for the axis.
     *
     * @return The margin.
     * 
     * @see #getUpperMargin()
     * @see #setLowerMargin(double)
     */
    public double getLowerMargin() {
        return this.lowerMargin;
    }

    /**
     * Sets the lower margin for the axis and sends an {@link AxisChangeEvent} 
     * to all registered listeners.
     *
     * @param margin  the margin as a percentage of the axis length (for 
     *                example, 0.05 is five percent).
     *                
     * @see #getLowerMargin()
     */
    public void setLowerMargin(double margin) {
        this.lowerMargin = margin;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[17]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[18]++;
    }

    /**
     * Returns the upper margin for the axis.
     *
     * @return The margin.
     * 
     * @see #getLowerMargin()
     * @see #setUpperMargin(double)
     */
    public double getUpperMargin() {
        return this.upperMargin;
    }

    /**
     * Sets the upper margin for the axis and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     *
     * @param margin  the margin as a percentage of the axis length (for 
     *                example, 0.05 is five percent).
     *                
     * @see #getUpperMargin()
     */
    public void setUpperMargin(double margin) {
        this.upperMargin = margin;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[19]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[20]++;
    }

    /**
     * Returns the category margin.
     *
     * @return The margin.
     * 
     * @see #setCategoryMargin(double)
     */
    public double getCategoryMargin() {
        return this.categoryMargin;
    }

    /**
     * Sets the category margin and sends an {@link AxisChangeEvent} to all 
     * registered listeners.  The overall category margin is distributed over 
     * N-1 gaps, where N is the number of categories on the axis.
     *
     * @param margin  the margin as a percentage of the axis length (for 
     *                example, 0.05 is five percent).
     *                
     * @see #getCategoryMargin()
     */
    public void setCategoryMargin(double margin) {
        this.categoryMargin = margin;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[21]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[22]++;
    }

    /**
     * Returns the maximum number of lines to use for each category label.
     * 
     * @return The maximum number of lines.
     * 
     * @see #setMaximumCategoryLabelLines(int)
     */
    public int getMaximumCategoryLabelLines() {
        return this.maximumCategoryLabelLines;
    }
    
    /**
     * Sets the maximum number of lines to use for each category label and
     * sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param lines  the maximum number of lines.
     * 
     * @see #getMaximumCategoryLabelLines()
     */
    public void setMaximumCategoryLabelLines(int lines) {
        this.maximumCategoryLabelLines = lines;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[23]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[24]++;
    }
    
    /**
     * Returns the category label width ratio.
     * 
     * @return The ratio.
     * 
     * @see #setMaximumCategoryLabelWidthRatio(float)
     */
    public float getMaximumCategoryLabelWidthRatio() {
        return this.maximumCategoryLabelWidthRatio;
    }
    
    /**
     * Sets the maximum category label width ratio and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param ratio  the ratio.
     * 
     * @see #getMaximumCategoryLabelWidthRatio()
     */
    public void setMaximumCategoryLabelWidthRatio(float ratio) {
        this.maximumCategoryLabelWidthRatio = ratio;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[25]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[26]++;
    }
    
    /**
     * Returns the offset between the axis and the category labels (before 
     * label positioning is taken into account).
     * 
     * @return The offset (in Java2D units).
     * 
     * @see #setCategoryLabelPositionOffset(int)
     */
    public int getCategoryLabelPositionOffset() {
        return this.categoryLabelPositionOffset;
    }
    
    /**
     * Sets the offset between the axis and the category labels (before label 
     * positioning is taken into account).
     * 
     * @param offset  the offset (in Java2D units).
     * 
     * @see #getCategoryLabelPositionOffset()
     */
    public void setCategoryLabelPositionOffset(int offset) {
        this.categoryLabelPositionOffset = offset;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[27]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[28]++;
    }
    
    /**
     * Returns the category label position specification (this contains label 
     * positioning info for all four possible axis locations).
     * 
     * @return The positions (never <code>null</code>).
     * 
     * @see #setCategoryLabelPositions(CategoryLabelPositions)
     */
    public CategoryLabelPositions getCategoryLabelPositions() {
        return this.categoryLabelPositions;
    }
    
    /**
     * Sets the category label position specification for the axis and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param positions  the positions (<code>null</code> not permitted).
     * 
     * @see #getCategoryLabelPositions()
     */
    public void setCategoryLabelPositions(CategoryLabelPositions positions) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[29]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((positions == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[1]++;
            throw new IllegalArgumentException("Null 'positions' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[2]++;}
        this.categoryLabelPositions = positions;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[30]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[31]++;
    }
    
    /**
     * Returns the font for the tick label for the given category.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setTickLabelFont(Comparable, Font)
     */
    public Font getTickLabelFont(Comparable category) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[32]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[3]++;
            throw new IllegalArgumentException("Null 'category' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[4]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[33]++;
        Font result = (Font) this.tickLabelFontMap.get(category);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[34]++;
int CodeCoverConditionCoverageHelper_C3;
        // if there is no specific font, use the general one...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[5]++;
            result = getTickLabelFont();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[35]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[6]++;}
        return result;
    }
    
    /**
     * Sets the font for the tick label for the specified category and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * @param font  the font (<code>null</code> permitted).
     * 
     * @see #getTickLabelFont(Comparable)
     */
    public void setTickLabelFont(Comparable category, Font font) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[7]++;
            throw new IllegalArgumentException("Null 'category' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[8]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[9]++;
            this.tickLabelFontMap.remove(category);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[38]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[10]++;
            this.tickLabelFontMap.put(category, font);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[39]++;
        }
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[40]++;
    }
    
    /**
     * Returns the paint for the tick label for the given category.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setTickLabelPaint(Paint)
     */
    public Paint getTickLabelPaint(Comparable category) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[11]++;
            throw new IllegalArgumentException("Null 'category' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[12]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[42]++;
        Paint result = (Paint) this.tickLabelPaintMap.get(category);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;
        // if there is no specific paint, use the general one...
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[13]++;
            result = getTickLabelPaint();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[44]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[14]++;}
        return result;
    }
    
    /**
     * Sets the paint for the tick label for the specified category and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getTickLabelPaint(Comparable)
     */
    public void setTickLabelPaint(Comparable category, Paint paint) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[15]++;
            throw new IllegalArgumentException("Null 'category' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[16]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[17]++;
            this.tickLabelPaintMap.remove(category);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[47]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[18]++;
            this.tickLabelPaintMap.put(category, paint);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[48]++;
        }
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[49]++;
    }
    
    /**
     * Adds a tooltip to the specified category and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category  the category (<code>null<code> not permitted).
     * @param tooltip  the tooltip text (<code>null</code> permitted).
     * 
     * @see #removeCategoryLabelToolTip(Comparable)
     */
    public void addCategoryLabelToolTip(Comparable category, String tooltip) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[50]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[19]++;
            throw new IllegalArgumentException("Null 'category' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[20]++;}
        this.categoryLabelToolTips.put(category, tooltip);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[51]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[52]++;
    }
    
    /**
     * Returns the tool tip text for the label belonging to the specified 
     * category.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @see #addCategoryLabelToolTip(Comparable, String)
     * @see #removeCategoryLabelToolTip(Comparable)
     */
    public String getCategoryLabelToolTip(Comparable category) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[21]++;
            throw new IllegalArgumentException("Null 'category' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[22]++;}
        return (String) this.categoryLabelToolTips.get(category);
    }
    
    /**
     * Removes the tooltip for the specified category and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category  the category (<code>null<code> not permitted).
     * 
     * @see #addCategoryLabelToolTip(Comparable, String)
     * @see #clearCategoryLabelToolTips()
     */
    public void removeCategoryLabelToolTip(Comparable category) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[23]++;
            throw new IllegalArgumentException("Null 'category' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[24]++;}
        this.categoryLabelToolTips.remove(category);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[55]++;   
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[56]++;
    }
    
    /**
     * Clears the category label tooltips and sends an {@link AxisChangeEvent} 
     * to all registered listeners.
     * 
     * @see #addCategoryLabelToolTip(Comparable, String)
     * @see #removeCategoryLabelToolTip(Comparable)
     */
    public void clearCategoryLabelToolTips() {
        this.categoryLabelToolTips.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[57]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[58]++;
    }
    
    /**
     * Returns the Java 2D coordinate for a category.
     * 
     * @param anchor  the anchor point.
     * @param category  the category index.
     * @param categoryCount  the category count.
     * @param area  the data area.
     * @param edge  the location of the axis.
     * 
     * @return The coordinate.
     */
    public double getCategoryJava2DCoordinate(CategoryAnchor anchor, 
                                              int category, 
                                              int categoryCount, 
                                              Rectangle2D area,
                                              RectangleEdge edge) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[59]++;
    
        double result = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((anchor == CategoryAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[25]++;
            result = getCategoryStart(category, categoryCount, area, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[61]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[26]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[62]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((anchor == CategoryAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[27]++;
            result = getCategoryMiddle(category, categoryCount, area, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[63]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[28]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[64]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((anchor == CategoryAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[29]++;
            result = getCategoryEnd(category, categoryCount, area, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[65]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[30]++;}
}
}
        return result;
                                                      
    }
                                              
    /**
     * Returns the starting coordinate for the specified category.
     *
     * @param category  the category.
     * @param categoryCount  the number of categories.
     * @param area  the data area.
     * @param edge  the axis location.
     *
     * @return The coordinate.
     * 
     * @see #getCategoryMiddle(int, int, Rectangle2D, RectangleEdge)
     * @see #getCategoryEnd(int, int, Rectangle2D, RectangleEdge)
     */
    public double getCategoryStart(int category, int categoryCount, 
                                   Rectangle2D area,
                                   RectangleEdge edge) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[66]++;

        double result = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[31]++;
            result = area.getX() + area.getWidth() * getLowerMargin();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[68]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[32]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[69]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[33]++;
            result = area.getMinY() + area.getHeight() * getLowerMargin();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[70]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[34]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[71]++;

        double categorySize = calculateCategorySize(categoryCount, area, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[72]++;
        double categoryGapWidth = calculateCategoryGapSize(categoryCount, area,
                edge);

        result = result + category * (categorySize + categoryGapWidth);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[73]++;
        return result;
        
    }

    /**
     * Returns the middle coordinate for the specified category.
     *
     * @param category  the category.
     * @param categoryCount  the number of categories.
     * @param area  the data area.
     * @param edge  the axis location.
     *
     * @return The coordinate.
     * 
     * @see #getCategoryStart(int, int, Rectangle2D, RectangleEdge)
     * @see #getCategoryEnd(int, int, Rectangle2D, RectangleEdge)
     */
    public double getCategoryMiddle(int category, int categoryCount, 
                                    Rectangle2D area, RectangleEdge edge) {

        return getCategoryStart(category, categoryCount, area, edge)
               + calculateCategorySize(categoryCount, area, edge) / 2;

    }

    /**
     * Returns the end coordinate for the specified category.
     *
     * @param category  the category.
     * @param categoryCount  the number of categories.
     * @param area  the data area.
     * @param edge  the axis location.
     *
     * @return The coordinate.
     * 
     * @see #getCategoryStart(int, int, Rectangle2D, RectangleEdge)
     * @see #getCategoryMiddle(int, int, Rectangle2D, RectangleEdge)
     */
    public double getCategoryEnd(int category, int categoryCount, 
                                 Rectangle2D area, RectangleEdge edge) {

        return getCategoryStart(category, categoryCount, area, edge)
               + calculateCategorySize(categoryCount, area, edge);

    }
    
    /**
     * Returns the middle coordinate (in Java2D space) for a series within a 
     * category.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param itemMargin  the item margin (0.0 <= itemMargin < 1.0);
     * @param area  the area (<code>null</code> not permitted).
     * @param edge  the edge (<code>null</code> not permitted).
     * 
     * @return The coordinate in Java2D space.
     * 
     * @since 1.0.7
     */
    public double getCategorySeriesMiddle(Comparable category, 
            Comparable seriesKey, CategoryDataset dataset, double itemMargin,
            Rectangle2D area, RectangleEdge edge) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[74]++;
        
        int categoryIndex = dataset.getColumnIndex(category);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[75]++;
        int categoryCount = dataset.getColumnCount();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[76]++;
        int seriesIndex = dataset.getRowIndex(seriesKey);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[77]++;
        int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[78]++;
        double start = getCategoryStart(categoryIndex, categoryCount, area, 
                edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[79]++;
        double end = getCategoryEnd(categoryIndex, categoryCount, area, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[80]++;
        double width = end - start;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[81]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((seriesCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[35]++;
            return start + width / 2.0;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[36]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[82]++;
            double gap = (width * itemMargin) / (seriesCount - 1);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[83]++;
            double ww = (width * (1 - itemMargin)) / seriesCount;
            return start + (seriesIndex * (ww + gap)) + ww / 2.0;
        }
    }

    /**
     * Calculates the size (width or height, depending on the location of the 
     * axis) of a category.
     *
     * @param categoryCount  the number of categories.
     * @param area  the area within which the categories will be drawn.
     * @param edge  the axis location.
     *
     * @return The category size.
     */
    protected double calculateCategorySize(int categoryCount, Rectangle2D area,
                                           RectangleEdge edge) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[84]++;

        double result = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[85]++;
        double available = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[86]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[37]++;
            available = area.getWidth();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[87]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[38]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[88]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[39]++;
            available = area.getHeight();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[89]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[40]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[90]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((categoryCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[41]++;
            result = available * (1 - getLowerMargin() - getUpperMargin() 
                     - getCategoryMargin());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[91]++;
            result = result / categoryCount;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[92]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[42]++;
            result = available * (1 - getLowerMargin() - getUpperMargin());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[93]++;
        }
        return result;

    }

    /**
     * Calculates the size (width or height, depending on the location of the 
     * axis) of a category gap.
     *
     * @param categoryCount  the number of categories.
     * @param area  the area within which the categories will be drawn.
     * @param edge  the axis location.
     *
     * @return The category gap width.
     */
    protected double calculateCategoryGapSize(int categoryCount, 
                                              Rectangle2D area,
                                              RectangleEdge edge) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[94]++;

        double result = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[95]++;
        double available = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[96]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[43]++;
            available = area.getWidth();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[97]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[44]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[98]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[45]++;
            available = area.getHeight();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[99]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[46]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[100]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((categoryCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[47]++;
            result = available * getCategoryMargin() / (categoryCount - 1);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[101]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[48]++;}

        return result;

    }

    /**
     * Estimates the space required for the axis, given a specific drawing area.
     *
     * @param g2  the graphics device (used to obtain font information).
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the axis should be drawn.
     * @param edge  the axis location (top or bottom).
     * @param space  the space already reserved.
     *
     * @return The space required to draw the axis.
     */
    public AxisSpace reserveSpace(Graphics2D g2, Plot plot, 
                                  Rectangle2D plotArea, 
                                  RectangleEdge edge, AxisSpace space) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[102]++;
int CodeCoverConditionCoverageHelper_C25;

        // create a new space object if one wasn't supplied...
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[49]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[103]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[50]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[104]++;
int CodeCoverConditionCoverageHelper_C26;
        
        // if the axis is not visible, no additional space is required...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[51]++;
            return space;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[52]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[105]++;

        // calculate the max size of the tick labels (if visible)...
        double tickLabelHeight = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[106]++;
        double tickLabelWidth = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[107]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isTickLabelsVisible()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[53]++;
            g2.setFont(getTickLabelFont());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[108]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[109]++;
            AxisState state = new AxisState();
            // we call refresh ticks just to get the maximum width or height
            refreshTicks(g2, state, plotArea, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[110]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[111]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[55]++;
                tickLabelHeight = state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[112]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[56]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[113]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[57]++;
                tickLabelHeight = state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[114]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[58]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[115]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[59]++;
                tickLabelWidth = state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[116]++;
 
            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[60]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[117]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[61]++;
                tickLabelWidth = state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[118]++;
 
            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[62]++;}
}
}
}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[54]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[119]++;
        
        // get the axis label size and update the space object...
        Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[120]++;
        double labelHeight = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[121]++;
        double labelWidth = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[122]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[63]++;
            labelHeight = labelEnclosure.getHeight();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[123]++;
            space.add(labelHeight + tickLabelHeight 
                    + this.categoryLabelPositionOffset, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[124]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[64]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[125]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[65]++;
            labelWidth = labelEnclosure.getWidth();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[126]++;
            space.add(labelWidth + tickLabelWidth 
                    + this.categoryLabelPositionOffset, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[127]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[66]++;}
}
        return space;

    }

    /**
     * Configures the axis against the current plot.
     */
    public void configure() {
        // nothing required
    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the axis should be drawn 
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the plot is being drawn 
     *                  (<code>null</code> not permitted).
     * @param edge  the location of the axis (<code>null</code> not permitted).
     * @param plotState  collects information about the plot 
     *                   (<code>null</code> permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Graphics2D g2, 
                          double cursor, 
                          Rectangle2D plotArea, 
                          Rectangle2D dataArea,
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[128]++;
int CodeCoverConditionCoverageHelper_C34;
        
        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[67]++;
            return new AxisState(cursor);

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[68]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[129]++;
int CodeCoverConditionCoverageHelper_C35;
        
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isAxisLineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[69]++;
            drawAxisLine(g2, cursor, dataArea, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[130]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[70]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[131]++;

        // draw the category labels and axis label
        AxisState state = new AxisState(cursor);
        state = drawCategoryLabels(g2, plotArea, dataArea, edge, state, 
                plotState);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[132]++;
        state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[133]++;
    
        return state;

    }

    /**
     * Draws the category labels and returns the updated axis state.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param dataArea  the area inside the axes (<code>null</code> not 
     *                  permitted).
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param state  the axis state (<code>null</code> not permitted).
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     * 
     * @return The updated axis state (never <code>null</code>).
     * 
     * @deprecated Use {@link #drawCategoryLabels(Graphics2D, Rectangle2D, 
     *     Rectangle2D, RectangleEdge, AxisState, PlotRenderingInfo)}.
     */
    protected AxisState drawCategoryLabels(Graphics2D g2,
                                           Rectangle2D dataArea,
                                           RectangleEdge edge,
                                           AxisState state,
                                           PlotRenderingInfo plotState) {
        
        // this method is deprecated because we really need the plotArea
        // when drawing the labels - see bug 1277726
        return drawCategoryLabels(g2, dataArea, dataArea, edge, state, 
                plotState);
    }
    
    /**
     * Draws the category labels and returns the updated axis state.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param dataArea  the area inside the axes (<code>null</code> not 
     *                  permitted).
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param state  the axis state (<code>null</code> not permitted).
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     * 
     * @return The updated axis state (never <code>null</code>).
     */
    protected AxisState drawCategoryLabels(Graphics2D g2,
                                           Rectangle2D plotArea,
                                           Rectangle2D dataArea,
                                           RectangleEdge edge,
                                           AxisState state,
                                           PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[134]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((state == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[71]++;
            throw new IllegalArgumentException("Null 'state' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[72]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[135]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((isTickLabelsVisible()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[73]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[136]++;       
            List ticks = refreshTicks(g2, state, plotArea, edge);       
            state.setTicks(ticks);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[137]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[138]++;        
          
            int categoryIndex = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[139]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[140]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[1]++;


int CodeCoverConditionCoverageHelper_C38;
            while ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[1]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[2]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[3]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[141]++;
                
                CategoryTick tick = (CategoryTick) iterator.next();
                g2.setFont(getTickLabelFont(tick.getCategory()));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[142]++;
                g2.setPaint(getTickLabelPaint(tick.getCategory()));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[143]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[144]++;

                CategoryLabelPosition position 
                        = this.categoryLabelPositions.getLabelPosition(edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[145]++;
                double x0 = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[146]++;
                double x1 = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[147]++;
                double y0 = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[148]++;
                double y1 = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[149]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[75]++;
                    x0 = getCategoryStart(categoryIndex, ticks.size(), 
                            dataArea, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[150]++;
                    x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, 
                            edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[151]++;
                    y1 = state.getCursor() - this.categoryLabelPositionOffset;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[152]++;
                    y0 = y1 - state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[153]++;

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[76]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[154]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[77]++;
                    x0 = getCategoryStart(categoryIndex, ticks.size(), 
                            dataArea, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[155]++;
                    x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, 
                            edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[156]++; 
                    y0 = state.getCursor() + this.categoryLabelPositionOffset;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[157]++;
                    y1 = y0 + state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[158]++;

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[78]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[159]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[79]++;
                    y0 = getCategoryStart(categoryIndex, ticks.size(), 
                            dataArea, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[160]++;
                    y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, 
                            edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[161]++;
                    x1 = state.getCursor() - this.categoryLabelPositionOffset;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[162]++;
                    x0 = x1 - state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[163]++;

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[80]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[164]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[81]++;
                    y0 = getCategoryStart(categoryIndex, ticks.size(), 
                            dataArea, edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[165]++;
                    y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, 
                            edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[166]++;
                    x0 = state.getCursor() + this.categoryLabelPositionOffset;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[167]++;
                    x1 = x0 - state.getMax();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[168]++;

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[82]++;}
}
}
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[169]++;
                Rectangle2D area = new Rectangle2D.Double(x0, y0, (x1 - x0), 
                        (y1 - y0));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[170]++;
                Point2D anchorPoint = RectangleAnchor.coordinates(area, 
                        position.getCategoryAnchor());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[171]++;
                TextBlock block = tick.getLabel();
                block.draw(g2, (float) anchorPoint.getX(), 
                        (float) anchorPoint.getY(), position.getLabelAnchor(), 
                        (float) anchorPoint.getX(), (float) anchorPoint.getY(), 
                        position.getAngle());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[172]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[173]++;
                Shape bounds = block.calculateBounds(g2, 
                        (float) anchorPoint.getX(), (float) anchorPoint.getY(), 
                        position.getLabelAnchor(), (float) anchorPoint.getX(), 
                        (float) anchorPoint.getY(), position.getAngle());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[174]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((plotState != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((plotState.getOwner() != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[83]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[175]++;
                    EntityCollection entities 
                            = plotState.getOwner().getEntityCollection();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[176]++;
int CodeCoverConditionCoverageHelper_C44;
                    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[85]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[177]++;
                        String tooltip = getCategoryLabelToolTip(
                                tick.getCategory());
                        entities.add(new CategoryLabelEntity(tick.getCategory(),
                                bounds, tooltip, null));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[178]++;

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[86]++;}

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[84]++;}
                categoryIndex++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[179]++;
            }
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[180]++;
int CodeCoverConditionCoverageHelper_C45;

            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((edge.equals(RectangleEdge.TOP)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[87]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[181]++;
                double h = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorUp(h);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[182]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[88]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[183]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((edge.equals(RectangleEdge.BOTTOM)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[89]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[184]++;
                double h = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorDown(h);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[185]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[90]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[186]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[91]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[187]++;
                double w = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorLeft(w);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[188]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[92]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[189]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[93]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[190]++;
                double w = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorRight(w);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[191]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[94]++;}
}
}
}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[74]++;}
        return state;
    }

    /**
     * Creates a temporary list of ticks that can be used when drawing the axis.
     *
     * @param g2  the graphics device (used to get font measurements).
     * @param state  the axis state.
     * @param dataArea  the area inside the axes.
     * @param edge  the location of the axis.
     * 
     * @return A list of ticks.
     */
    public List refreshTicks(Graphics2D g2, 
                             AxisState state,
                             Rectangle2D dataArea,
                             RectangleEdge edge) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[192]++;

        List ticks = new java.util.ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[193]++;
int CodeCoverConditionCoverageHelper_C49;
        
        // sanity check for data area...
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((dataArea.getHeight() <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((dataArea.getWidth() < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[95]++;
            return ticks;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[96]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[194]++;

        CategoryPlot plot = (CategoryPlot) getPlot();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[195]++;
        List categories = plot.getCategoriesForAxis(this);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[196]++;
        double max = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[197]++;
int CodeCoverConditionCoverageHelper_C50;
                
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((categories != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[97]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[198]++;
            CategoryLabelPosition position 
                    = this.categoryLabelPositions.getLabelPosition(edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[199]++;
            float r = this.maximumCategoryLabelWidthRatio;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[200]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((r <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[99]++;
                r = position.getWidthRatio();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[201]++;
   
            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[100]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[202]++;
                  
            float l = 0.0f;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[203]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((position.getWidthType() == CategoryLabelWidthType.CATEGORY) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[101]++;
                l = (float) calculateCategorySize(categories.size(), dataArea, 
                        edge);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[204]++;
  
            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[102]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[205]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[103]++;
                    l = (float) dataArea.getWidth();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[206]++;
   
                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[104]++;
                    l = (float) dataArea.getHeight();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[207]++;   
                }
            }
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[208]++;
            int categoryIndex = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[209]++;
            Iterator iterator = categories.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[210]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[4]++;


int CodeCoverConditionCoverageHelper_C54;
            while ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[4]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[5]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[6]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[211]++;
                Comparable category = (Comparable) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[212]++;
                TextBlock label = createLabel(category, l * r, edge, g2);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[213]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[105]++;
                    max = Math.max(max, calculateTextBlockHeight(label, 
                            position, g2));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[214]++;

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[106]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[215]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[107]++;
                    max = Math.max(max, calculateTextBlockWidth(label, 
                            position, g2));
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[216]++;

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[108]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[217]++;
                Tick tick = new CategoryTick(category, label, 
                        position.getLabelAnchor(),
                        position.getRotationAnchor(), position.getAngle());
                ticks.add(tick);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[218]++;
                categoryIndex = categoryIndex + 1;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[219]++;
            }

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[98]++;}
        state.setMax(max);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[220]++;
        return ticks;
        
    }

    /**
     * Creates a label.
     *
     * @param category  the category.
     * @param width  the available width. 
     * @param edge  the edge on which the axis appears.
     * @param g2  the graphics device.
     *
     * @return A label.
     */
    protected TextBlock createLabel(Comparable category, float width, 
                                    RectangleEdge edge, Graphics2D g2) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[221]++;
        TextBlock label = TextUtilities.createTextBlock(category.toString(), 
                getTickLabelFont(category), getTickLabelPaint(category), width,
                this.maximumCategoryLabelLines, new G2TextMeasurer(g2));  
        return label; 
    }
    
    /**
     * A utility method for determining the width of a text block.
     *
     * @param block  the text block.
     * @param position  the position.
     * @param g2  the graphics device.
     *
     * @return The width.
     */
    protected double calculateTextBlockWidth(TextBlock block, 
                                             CategoryLabelPosition position, 
                                             Graphics2D g2) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[222]++;
                                                    
        RectangleInsets insets = getTickLabelInsets();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[223]++;
        Size2D size = block.calculateDimensions(g2);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[224]++;
        Rectangle2D box = new Rectangle2D.Double(0.0, 0.0, size.getWidth(), 
                size.getHeight());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[225]++;
        Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(),
                0.0f, 0.0f);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[226]++;
        double w = rotatedBox.getBounds2D().getWidth() + insets.getTop() 
                + insets.getBottom();
        return w;
        
    }

    /**
     * A utility method for determining the height of a text block.
     *
     * @param block  the text block.
     * @param position  the label position.
     * @param g2  the graphics device.
     *
     * @return The height.
     */
    protected double calculateTextBlockHeight(TextBlock block, 
                                              CategoryLabelPosition position, 
                                              Graphics2D g2) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[227]++;
                                                    
        RectangleInsets insets = getTickLabelInsets();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[228]++;
        Size2D size = block.calculateDimensions(g2);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[229]++;
        Rectangle2D box = new Rectangle2D.Double(0.0, 0.0, size.getWidth(), 
                size.getHeight());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[230]++;
        Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(),
                0.0f, 0.0f);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[231]++;
        double h = rotatedBox.getBounds2D().getHeight() 
                   + insets.getTop() + insets.getBottom();
        return h;
        
    }

    /**
     * Creates a clone of the axis.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the axis does 
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[232]++;
        CategoryAxis clone = (CategoryAxis) super.clone();
        clone.tickLabelFontMap = new HashMap(this.tickLabelFontMap);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[233]++;
        clone.tickLabelPaintMap = new HashMap(this.tickLabelPaintMap);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[234]++;
        clone.categoryLabelToolTips = new HashMap(this.categoryLabelToolTips);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[235]++;
        return clone;  
    }
    
    /**
     * Tests this axis for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[236]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[109]++;
            return true;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[110]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[237]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryAxis) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[112]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[238]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[114]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[239]++;
        CategoryAxis that = (CategoryAxis) obj;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[240]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((that.lowerMargin != this.lowerMargin) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[116]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[241]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((that.upperMargin != this.upperMargin) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[117]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[118]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[242]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((that.categoryMargin != this.categoryMargin) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[119]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[120]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[243]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((that.maximumCategoryLabelWidthRatio 
                != this.maximumCategoryLabelWidthRatio) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[121]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[122]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[244]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((that.categoryLabelPositionOffset 
                != this.categoryLabelPositionOffset) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[123]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[124]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[245]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(that.categoryLabelPositions, 
                this.categoryLabelPositions)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[125]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[126]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[246]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(that.categoryLabelToolTips, 
                this.categoryLabelToolTips)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[127]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[128]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[247]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickLabelFontMap, 
                that.tickLabelFontMap)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[129]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[130]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[248]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((equalPaintMaps(this.tickLabelPaintMap, that.tickLabelPaintMap)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[131]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[132]++;}
        return true;
    }

    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[249]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((getLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[133]++;
            return getLabel().hashCode();

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[134]++;
            return 0;
        }
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
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[250]++;
        writePaintMap(this.tickLabelPaintMap, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[251]++;
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
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[252]++;
        this.tickLabelPaintMap = readPaintMap(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[253]++;
    }
 
    /**
     * Reads a <code>Map</code> of (<code>Comparable</code>, <code>Paint</code>)
     * elements from a stream.
     * 
     * @param in  the input stream.
     * 
     * @return The map.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     * 
     * @see #writePaintMap(Map, ObjectOutputStream)
     */
    private Map readPaintMap(ObjectInputStream in) 
            throws IOException, ClassNotFoundException {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[254]++;
        boolean isNull = in.readBoolean();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[255]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((isNull) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[135]++;
            return null;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[136]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[256]++;
        Map result = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[257]++;
        int count = in.readInt();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[258]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[7]++;


int CodeCoverConditionCoverageHelper_C71;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[7]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[8]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[9]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[259]++;
            Comparable category = (Comparable) in.readObject();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[260]++;
            Paint paint = SerialUtilities.readPaint(in);
            result.put(category, paint);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[261]++;
        }
        return result;
    }
    
    /**
     * Writes a map of (<code>Comparable</code>, <code>Paint</code>)
     * elements to a stream.
     * 
     * @param map  the map (<code>null</code> permitted).
     * 
     * @param out
     * @throws IOException
     * 
     * @see #readPaintMap(ObjectInputStream)
     */
    private void writePaintMap(Map map, ObjectOutputStream out) 
            throws IOException {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[262]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((map == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[137]++;
            out.writeBoolean(true);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[263]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[138]++;
            out.writeBoolean(false);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[264]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[265]++;
            Set keys = map.keySet();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[266]++;
            int count = keys.size();
            out.writeInt(count);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[267]++;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[268]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[269]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[10]++;


int CodeCoverConditionCoverageHelper_C73;
            while ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[10]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[11]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[12]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[270]++;
                Comparable key = (Comparable) iterator.next();
                out.writeObject(key);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[271]++;
                SerialUtilities.writePaint((Paint) map.get(key), out);
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[272]++;
            }
        }
    }
    
    /**
     * Tests two maps containing (<code>Comparable</code>, <code>Paint</code>)
     * elements for equality.
     * 
     * @param map1  the first map (<code>null</code> not permitted).
     * @param map2  the second map (<code>null</code> not permitted).
     * 
     * @return A boolean.
     */
    private boolean equalPaintMaps(Map map1, Map map2) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[273]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((map1.size() != map2.size()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[139]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[140]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[274]++;
        Set entries = map1.entrySet();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[275]++;
        Iterator iterator = entries.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[276]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[13]++;


int CodeCoverConditionCoverageHelper_C75;
        while ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[13]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[14]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.loops[15]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[277]++;
            Map.Entry entry = (Map.Entry) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[278]++;
            Paint p1 = (Paint) entry.getValue();
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[279]++;
            Paint p2 = (Paint) map2.get(entry.getKey());
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.statements[280]++;
int CodeCoverConditionCoverageHelper_C76;
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(p1, p2)) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[141]++;
                return false;
  
            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd.branches[142]++;}
        }
        return true;
    }

}

class CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd ());
  }
    public static long[] statements = new long[281];
    public static long[] branches = new long[143];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[77];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,2,2,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 76; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$sd7jgrcliqd2e3s6n71815i5pd () {
    super("org.jfree.chart.axis.CategoryAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 280; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 142; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 76; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.CategoryAxis.java");
      for (int i = 1; i <= 280; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 142; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 76; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

