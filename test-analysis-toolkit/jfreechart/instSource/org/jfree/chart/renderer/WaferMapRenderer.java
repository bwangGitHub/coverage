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
 * WaferMapRenderer.java
 * ---------------------
 * (C) Copyright 2003-2007, by Robert Redburn and Contributors.
 *
 * Original Author:  Robert Redburn;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 25-Nov-2003 : Version 1, contributed by Robert Redburn.  Changes have been 
 *               made to fit the JFreeChart coding style (DG);
 * 20-Apr-2005 : Small update for changes to LegendItem class (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.renderer;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.data.general.WaferMapDataset;

/**
 * A renderer for wafer map plots.  Provides color managment facilities.
 */
public class WaferMapRenderer extends AbstractRenderer {
  static {
    CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.ping();
  }


    /** paint index */
    private Map paintIndex;
    
    /** plot */
    private WaferMapPlot plot;
    
    /** paint limit */
    private int paintLimit;
    
    /** default paint limit */
    private static final int DEFAULT_PAINT_LIMIT = 35;
  static {
    CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[1]++;
  }  
    
    /** default multivalue paint calculation */
    public static final int POSITION_INDEX = 0;
  static {
    CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[2]++;
  }
    
    /** The default value index. */
    public static final int VALUE_INDEX = 1;
  static {
    CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[3]++;
  }
    
    /** paint index method */
    private int paintIndexMethod;
    
    /**
     * Creates a new renderer.
     */
    public WaferMapRenderer() {
        this(null, null);
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[4]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param paintLimit  the paint limit.
     * @param paintIndexMethod  the paint index method.
     */
    public WaferMapRenderer(int paintLimit, int paintIndexMethod) {
        this(new Integer(paintLimit), new Integer(paintIndexMethod));
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[5]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param paintLimit  the paint limit.
     * @param paintIndexMethod  the paint index method.
     */
    public WaferMapRenderer(Integer paintLimit, Integer paintIndexMethod) {
        
        super();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[6]++;
        this.paintIndex = new HashMap();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[7]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paintLimit == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[1]++;
            this.paintLimit = DEFAULT_PAINT_LIMIT;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[9]++;

        }
        else {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[2]++;
            this.paintLimit = paintLimit.intValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[10]++;
        }
        
        this.paintIndexMethod = VALUE_INDEX;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[11]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paintIndexMethod != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[3]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isMethodValid(paintIndexMethod.intValue())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[5]++; 
                this.paintIndexMethod = paintIndexMethod.intValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[14]++;

            } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[4]++;}
    }

    /**
     * Verifies that the passed paint index method is valid.
     * 
     * @param method  the method.
     * 
     * @return <code>true</code> or </code>false</code>.
     */
    private boolean isMethodValid(int method) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[15]++;
        switch (method) {
            case POSITION_INDEX:
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[7]++; return true;
            case VALUE_INDEX:
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[8]++;    return true;
            default:
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[9]++; return false;
        }
    }

    /**
     * Returns the drawing supplier from the plot.
     * 
     * @return The drawing supplier.
     */
    public DrawingSupplier getDrawingSupplier() {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[16]++;
        DrawingSupplier result = null;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[17]++;
        WaferMapPlot p = getPlot();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[10]++;
            result = p.getDrawingSupplier();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[19]++;

        } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[11]++;}
        return result;
    }

    /**
     * Returns the plot.
     * 
     * @return The plot.
     */
    public WaferMapPlot getPlot() {
        return this.plot;
    }

    /**
     * Sets the plot and build the paint index.
     * 
     * @param plot  the plot.
     */
    public void setPlot(WaferMapPlot plot) {
        this.plot = plot;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[20]++;
        makePaintIndex();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[21]++;
    }
    
    /**
     * Returns the paint for a given chip value.
     * 
     * @param value  the value.
     * 
     * @return The paint.
     */
    public Paint getChipColor(Number value) {
        return getSeriesPaint(getPaintIndex(value));
    }
    
    /**
     * Returns the paint index for a given chip value.
     * 
     * @param value  the value.
     * 
     * @return The paint index.
     */
    private int getPaintIndex(Number value) {
        return ((Integer) this.paintIndex.get(value)).intValue();
    }
    
    /**
     * Builds a map of chip values to paint colors.
     * paintlimit is the maximum allowed number of colors.
     */
    private void makePaintIndex() {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.plot == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[12]++;
            return;

        } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[13]++;}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[23]++;
        WaferMapDataset data = this.plot.getDataset();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[24]++;
        Number dataMin = data.getMinValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[25]++;
        Number dataMax = data.getMaxValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[26]++;
        Set uniqueValues = data.getUniqueValues();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((uniqueValues.size() <= this.paintLimit) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[14]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[28]++;
            int count = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[1]++;


int CodeCoverConditionCoverageHelper_C7; // assign a color for each unique value
            for (Iterator i = uniqueValues.iterator();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[1]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[2]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[3]++;
}
                this.paintIndex.put(i.next(), new Integer(count++));
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[30]++;
            }

        }
        else {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[15]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[31]++;  
            // more values than paints so map
            // multiple values to the same color
            switch (this.paintIndexMethod) {
                case POSITION_INDEX:
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[16]++; 
                    makePositionIndex(uniqueValues);
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[32]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[33]++; 
                    break;
                case VALUE_INDEX:
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[17]++;    
                    makeValueIndex(dataMax, dataMin, uniqueValues);
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[34]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[35]++; 
                    break;
                default:
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[18]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[36]++;
                    break;
            }
        }
    }
        
    /**
     * Builds the paintindex by assigning colors based on the number 
     * of unique values: totalvalues/totalcolors.
     * 
     * @param uniqueValues  the set of unique values.
     */
    private void makePositionIndex(Set uniqueValues) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[37]++;
        int valuesPerColor = (int) Math.ceil(
            (double) uniqueValues.size() / this.paintLimit
        );
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[38]++;
        int count = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[39]++; // assign a color for each unique value
        int paint = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[40]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        for (Iterator i = uniqueValues.iterator();(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[4]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[5]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[6]++;
}
            this.paintIndex.put(i.next(), new Integer(paint));
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[41]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((++count % valuesPerColor == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[19]++;
                paint++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[43]++;

            } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[20]++;}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((paint > this.paintLimit) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[21]++;
                paint = this.paintLimit;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[45]++;

            } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[22]++;}
        }
    }

    /**
     * Builds the paintindex by assigning colors evenly across the range
     * of values:  maxValue-minValue/totalcolors
     * 
     * @param max  the maximum value.
     * @param min  the minumum value.
     * @param uniqueValues  the unique values.
     */
    private void makeValueIndex(Number max, Number min, Set uniqueValues) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[46]++;
        double valueRange = max.doubleValue() - min.doubleValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[47]++;
        double valueStep = valueRange / this.paintLimit;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[48]++;
        int paint = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[49]++;
        double cutPoint = min.doubleValue() + valueStep;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;
        for (Iterator i = uniqueValues.iterator();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[7]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[8]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[9]++;
}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[51]++;
            Number value = (Number) i.next();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[52]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[10]++;


int CodeCoverConditionCoverageHelper_C12;
            while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value.doubleValue() > cutPoint) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[10]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[11]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[12]++;
}
                cutPoint += valueStep;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[53]++;
                paint++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[54]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((paint > this.paintLimit) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[23]++;
                    paint = this.paintLimit;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[56]++;

                } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[24]++;}
            } 
            this.paintIndex.put(value, new Integer(paint));
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[57]++;
        }
    }

    /**
     * Builds the list of legend entries.  called by getLegendItems in
     * WaferMapPlot to populate the plot legend.
     * 
     * @return The legend items.
     */
    public LegendItemCollection getLegendCollection() {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[58]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[59]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((this.paintIndex != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.paintIndex.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[25]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[60]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.paintIndex.size() <= this.paintLimit) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[27]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[61]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[13]++;


int CodeCoverConditionCoverageHelper_C16;
                for (Iterator i = this.paintIndex.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false);) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[13]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[14]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[15]++;
}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[62]++;
                    // in this case, every color has a unique value
                    Map.Entry entry =  (Map.Entry) i.next();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[63]++;
                    String label = entry.getKey().toString();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[64]++;
                    String description = label;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[65]++;
                    Shape shape = new Rectangle2D.Double(1d, 1d, 1d, 1d);
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[66]++;
                    Paint paint = getSeriesPaint(
                        ((Integer) entry.getValue()).intValue()
                    );
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[67]++;
                    Paint outlinePaint = Color.black;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[68]++;
                    Stroke outlineStroke = DEFAULT_STROKE;

                    result.add(new LegendItem(label, description, null, 
                            null, shape, paint, outlineStroke, outlinePaint));
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[69]++;
                    
                }
               
            }
            else {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[28]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[70]++;
                // in this case, every color has a range of values
                Set unique = new HashSet();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[71]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[16]++;


int CodeCoverConditionCoverageHelper_C17;
                for (Iterator i = this.paintIndex.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false);) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[16]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[17]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[18]++;
}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[72]++;
                    Map.Entry entry = (Map.Entry) i.next();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
                    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((unique.add(entry.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[29]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[74]++;
                        String label = getMinPaintValue(
                            (Integer) entry.getValue()).toString()
                            + " - " + getMaxPaintValue(
                                (Integer) entry.getValue()).toString();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[75]++;
                        String description = label;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[76]++;
                        Shape shape = new Rectangle2D.Double(1d, 1d, 1d, 1d);
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[77]++;
                        Paint paint = getSeriesPaint(
                            ((Integer) entry.getValue()).intValue()
                        );
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[78]++;
                        Paint outlinePaint = Color.black;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[79]++;
                        Stroke outlineStroke = DEFAULT_STROKE;

                        result.add(new LegendItem(label, description, 
                                null, null, shape, paint, outlineStroke, 
                                outlinePaint));
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[80]++;

                    } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[30]++;}
                } // end foreach map entry
            }
 // end else
        } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[26]++;}
        return result;
    }

    /**
     * Returns the minimum chip value assigned to a color
     * in the paintIndex
     * 
     * @param index  the index.
     * 
     * @return The value.
     */
    private Number getMinPaintValue(Integer index) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[81]++;
        double minValue = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[82]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[19]++;


int CodeCoverConditionCoverageHelper_C19;
        for (Iterator i = this.paintIndex.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false);) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[19]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[20]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[21]++;
}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[83]++;
            Map.Entry entry = (Map.Entry) i.next();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[84]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((((Integer) entry.getValue()).equals(index)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[31]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[85]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((((Number) entry.getKey()).doubleValue() < minValue) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[33]++;
                    minValue = ((Number) entry.getKey()).doubleValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[86]++;

                } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[32]++;}
        }               
        return new Double(minValue);
    }
    
    /**
     * Returns the maximum chip value assigned to a color
     * in the paintIndex
     * 
     * @param index  the index.
     * 
     * @return The value
     */
    private Number getMaxPaintValue(Integer index) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[87]++;
        double maxValue = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[88]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[22]++;


int CodeCoverConditionCoverageHelper_C22;
        for (Iterator i = this.paintIndex.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false);) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[22]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[23]--;
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.loops[24]++;
}
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[89]++;
            Map.Entry entry = (Map.Entry) i.next();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[90]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((((Integer) entry.getValue()).equals(index)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[35]++;
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[91]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((((Number) entry.getKey()).doubleValue() > maxValue) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[37]++;
                    maxValue = ((Number) entry.getKey()).doubleValue();
CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.statements[92]++;

                } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p.branches[36]++;}
        }               
        return new Double(maxValue);
    }


}

class CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p ());
  }
    public static long[] statements = new long[93];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.WaferMapRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 24; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$20kzu3ljca2anahe4cyr75g54afr2936p () {
    super("org.jfree.chart.renderer.WaferMapRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 92; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.WaferMapRenderer.java");
      for (int i = 1; i <= 92; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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
 // end class wafermaprenderer
