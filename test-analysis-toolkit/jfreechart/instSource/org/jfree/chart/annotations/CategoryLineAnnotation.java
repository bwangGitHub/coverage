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
 * CategoryLineAnnotation.java
 * ---------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-Jul-2005 : Version 1, based on CategoryTextAnnotation (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Mar-2007 : Reimplemented hashCode() (DG);
 *
 */

package org.jfree.chart.annotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A line annotation that can be placed on a {@link CategoryPlot}.
 */
public class CategoryLineAnnotation implements CategoryAnnotation, 
                                               Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = 3477740483341587984L;
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[1]++;
  }

    /** The category for the start of the line. */
    private Comparable category1;

    /** The value for the start of the line. */
    private double value1;

    /** The category for the end of the line. */
    private Comparable category2;
    
    /** The value for the end of the line. */
    private double value2;
    
    /** The line color. */
    private transient Paint paint = Color.black;
  {
    CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[2]++;
  }
    
    /** The line stroke. */
    private transient Stroke stroke = new BasicStroke(1.0f);
  {
    CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[3]++;
  }
     
    /**
     * Creates a new annotation that draws a line between (category1, value1)
     * and (category2, value2).
     *
     * @param category1  the category (<code>null</code> not permitted).
     * @param value1  the value.
     * @param category2  the category (<code>null</code> not permitted).
     * @param value2  the value.
     * @param paint  the line color (<code>null</code> not permitted).
     * @param stroke  the line stroke (<code>null</code> not permitted).
     */
    public CategoryLineAnnotation(Comparable category1, double value1, 
                                  Comparable category2, double value2,
                                  Paint paint, Stroke stroke) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((category1 == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[1]++;
            throw new IllegalArgumentException("Null 'category1' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[2]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((category2 == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[3]++;
            throw new IllegalArgumentException("Null 'category2' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[4]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[5]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[6]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[7]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[8]++;}
        this.category1 = category1;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[8]++;
        this.value1 = value1;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[9]++;
        this.category2 = category2;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[10]++;
        this.value2 = value2;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[11]++;
        this.paint = paint;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[12]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[13]++;
    }

    /**
     * Returns the category for the start of the line.
     * 
     * @return The category for the start of the line (never <code>null</code>).
     * 
     * @see #setCategory1(Comparable)
     */
    public Comparable getCategory1() {
        return this.category1;
    }
    
    /**
     * Sets the category for the start of the line.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * 
     * @see #getCategory1()
     */
    public void setCategory1(Comparable category) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[9]++;
            throw new IllegalArgumentException("Null 'category' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[10]++;}
        this.category1 = category;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[15]++;
    }
    
    /**
     * Returns the y-value for the start of the line.
     * 
     * @return The y-value for the start of the line.
     * 
     * @see #setValue1(double)
     */
    public double getValue1() {
        return this.value1;
    }
    
    /**
     * Sets the y-value for the start of the line.
     * 
     * @param value  the value.
     * 
     * @see #getValue1()
     */
    public void setValue1(double value) {
        this.value1 = value;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[16]++;    
    }
    
    /**
     * Returns the category for the end of the line.
     * 
     * @return The category for the end of the line (never <code>null</code>).
     * 
     * @see #setCategory2(Comparable)
     */
    public Comparable getCategory2() {
        return this.category2;
    }
    
    /**
     * Sets the category for the end of the line.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * 
     * @see #getCategory2()
     */
    public void setCategory2(Comparable category) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[11]++;
            throw new IllegalArgumentException("Null 'category' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[12]++;}
        this.category2 = category;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[18]++;
    }
    
    /**
     * Returns the y-value for the end of the line.
     * 
     * @return The y-value for the end of the line.
     * 
     * @see #setValue2(double)
     */
    public double getValue2() {
        return this.value2;
    }
    
    /**
     * Sets the y-value for the end of the line.
     * 
     * @param value  the value.
     * 
     * @see #getValue2()
     */
    public void setValue2(double value) {
        this.value2 = value;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[19]++;    
    }
    
    /**
     * Returns the paint used to draw the connecting line.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Sets the paint used to draw the connecting line.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[13]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[14]++;}
        this.paint = paint;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[21]++;
    }
    
    /**
     * Returns the stroke used to draw the connecting line.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setStroke(Stroke)
     */
    public Stroke getStroke() {
        return this.stroke;
    }
    
    /**
     * Sets the stroke used to draw the connecting line.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getStroke()
     */
    public void setStroke(Stroke stroke) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[15]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[16]++;}
        this.stroke = stroke;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[23]++;
    }
    
    /**
     * Draws the annotation.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     */
    public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea,
                     CategoryAxis domainAxis, ValueAxis rangeAxis) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[24]++;

        CategoryDataset dataset = plot.getDataset();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[25]++;
        int catIndex1 = dataset.getColumnIndex(this.category1);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[26]++;
        int catIndex2 = dataset.getColumnIndex(this.category2);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[27]++;
        int catCount = dataset.getColumnCount();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[28]++;

        double lineX1 = 0.0f;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[29]++;
        double lineY1 = 0.0f;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[30]++;
        double lineX2 = 0.0f;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[31]++;
        double lineY2 = 0.0f;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[32]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[33]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
            plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[34]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
            plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
        
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[17]++;
            lineY1 = domainAxis.getCategoryJava2DCoordinate(
                CategoryAnchor.MIDDLE, catIndex1, catCount, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[36]++;
            lineX1 = rangeAxis.valueToJava2D(this.value1, dataArea, rangeEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[37]++;
            lineY2 = domainAxis.getCategoryJava2DCoordinate(
                CategoryAnchor.MIDDLE, catIndex2, catCount, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[38]++;
            lineX2 = rangeAxis.valueToJava2D(this.value2, dataArea, rangeEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[39]++;

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[18]++;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[40]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[19]++;
            lineX1 = domainAxis.getCategoryJava2DCoordinate(
                CategoryAnchor.MIDDLE, catIndex1, catCount, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[41]++;
            lineY1 = rangeAxis.valueToJava2D(this.value1, dataArea, rangeEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[42]++;
            lineX2 = domainAxis.getCategoryJava2DCoordinate(
                CategoryAnchor.MIDDLE, catIndex2, catCount, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[43]++;
            lineY2 = rangeAxis.valueToJava2D(this.value2, dataArea, rangeEdge);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[44]++;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[20]++;}
}
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[45]++;
        g2.setStroke(this.stroke);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[46]++;
        g2.drawLine((int) lineX1, (int) lineY1, (int) lineX2, (int) lineY2);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[47]++;
    }

    /**
     * Tests this object for equality with another.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[21]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[22]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryLineAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[24]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[50]++;
        CategoryLineAnnotation that = (CategoryLineAnnotation) obj;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.category1.equals(that.getCategory1())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[26]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.value1 != that.getValue1()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[27]++;
            return false;
    
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[28]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.category2.equals(that.getCategory2())) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[30]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[54]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.value2 != that.getValue2()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[31]++;
            return false;
    
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[32]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[34]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[56]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.branches[36]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[57]++;
        int result = 193;
        result = 37 * result + this.category1.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[58]++;
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[59]++;
        long temp = Double.doubleToLongBits(this.value1);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[60]++;
        result = 37 * result + this.category2.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[61]++;
        temp = Double.doubleToLongBits(this.value2);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[62]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[63]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[64]++;
        result = 37 * result + this.stroke.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[65]++;
        return result; 
    }
    
    /**
     * Returns a clone of the annotation.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    
    }
  
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[66]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[67]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[68]++;
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
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[69]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[70]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd.statements[71]++;
    }

}

class CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd ());
  }
    public static long[] statements = new long[72];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.CategoryLineAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$4b4elyw2od3fme1nhfojfwrs284ch07nzq12snx9pd () {
    super("org.jfree.chart.annotations.CategoryLineAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 71; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.CategoryLineAnnotation.java");
      for (int i = 1; i <= 71; i++) {
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
    for (int i = 1; i <= 18; i++) {
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

