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
 * -------------
 * ClipPath.java
 * -------------
 * (C) Copyright 2003, 2004, 2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu;
 *
 * Changes
 * -------
 * 22-Apr-2003 : Added standard header (DG);
 * 09-May-2003 : Added AxisLocation (DG);
 * 11-Sep-2003 : Implemented Cloneable (NB);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.chart;

import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.ui.RectangleEdge;

/**
 * This class would typically be used with a 
 * {@link org.jfree.chart.plot.ContourPlot}.  It allows the user to define a 
 * <code>GeneralPath</code> curve in plot coordinates.  This curve can then be 
 * used mask off or define regions within the contour plot.  The data must be 
 * sorted.
 * 
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class ClipPath implements Cloneable {
  static {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.ping();
  }


    /** The x values. */
    private double[] xValue = null;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[1]++;
  }
    
    /** The y values. */
    private double[] yValue = null;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[2]++;
  }

    /** Controls whether drawing will be clipped (
     * false would still allow the drawing or filling of path */
    private boolean clip = true;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[3]++;
  }

    /** Controls whether the path is drawn as an outline. */
    private boolean drawPath = false;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[4]++;
  }

    /** Controls whether the path is filled. */
    private boolean fillPath = false;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[5]++;
  }

    /** The fill paint. */
    private Paint fillPaint = null;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[6]++;
  }
    
    /** The draw paint. */
    private Paint drawPaint = null;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[7]++;
  }
    
    /** The draw stroke. */
    private Stroke drawStroke = null;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[8]++;
  }
    
    /** The composite. */
    private Composite composite = null;
  {
    CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[9]++;
  }

    /**
     * Constructor for ClipPath.
     */
    public ClipPath() {
        super();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[10]++;
    }

    /**
     * Constructor for ClipPath.
     * Default values are assumed for the fillPath and drawPath options as 
     * false and true respectively.  The fillPaint is set to Color.GRAY, the 
     * drawColor is Color.BLUE, the stroke is BasicStroke(1)
     * and the composite is AlphaComposite.Src.
     *
     * @param xValue  x coordinates of curved to be created
     * @param yValue  y coordinates of curved to be created
     */
    public ClipPath(double[] xValue, double[] yValue) {
        this(xValue, yValue, true, false, true);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[11]++;
    }


    /**
     * Constructor for ClipPath.
     * The fillPaint is set to Color.GRAY, the drawColor is Color.BLUE, the 
     * stroke is BasicStroke(1) and the composite is AlphaComposite.Src.
     *
     * @param xValue  x coordinates of curved to be created
     * @param yValue  y coordinates of curved to be created
     * @param clip  clip?
     * @param fillPath  whether the path is to filled
     * @param drawPath  whether the path is to drawn as an outline
     */
    public ClipPath(double[] xValue, double[] yValue,
                    boolean clip, boolean fillPath, boolean drawPath) {
        this.xValue = xValue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[12]++;
        this.yValue = yValue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[13]++;

        this.clip = clip;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[14]++;
        this.fillPath = fillPath;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[15]++;
        this.drawPath = drawPath;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[16]++;

        this.fillPaint = java.awt.Color.gray;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[17]++;
        this.drawPaint = java.awt.Color.blue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[18]++;
        this.drawStroke = new BasicStroke(1);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[19]++;
        this.composite = java.awt.AlphaComposite.Src;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[20]++;
    }

    /**
     * Constructor for ClipPath.
     *
     * @param xValue  x coordinates of curved to be created
     * @param yValue  y coordinates of curved to be created
     * @param fillPath  whether the path is to filled
     * @param drawPath  whether the path is to drawn as an outline
     * @param fillPaint  the fill paint
     * @param drawPaint  the outline stroke color
     * @param drawStroke  the stroke style
     * @param composite  the composite rule
     */
    public ClipPath(double[] xValue, double[] yValue, boolean fillPath, 
                    boolean drawPath, Paint fillPaint, Paint drawPaint, 
                    Stroke drawStroke, Composite composite) {

        this.xValue = xValue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[21]++;
        this.yValue = yValue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[22]++;

        this.fillPath = fillPath;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[23]++;
        this.drawPath = drawPath;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[24]++;

        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[25]++;
        this.drawPaint = drawPaint;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[26]++;
        this.drawStroke = drawStroke;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[27]++;
        this.composite = composite;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[28]++;

    }

    /**
     * Draws the clip path.
     *
     * @param g2  current graphics2D.
     * @param dataArea  the dataArea that the plot is being draw in.
     * @param horizontalAxis  the horizontal axis.
     * @param verticalAxis  the vertical axis.
     *
     * @return The GeneralPath defining the outline
     */
    public GeneralPath draw(Graphics2D g2,
                            Rectangle2D dataArea,
                            ValueAxis horizontalAxis, ValueAxis verticalAxis) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[29]++;

        GeneralPath generalPath = generateClipPath(
            dataArea, horizontalAxis, verticalAxis
        );
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((this.fillPath) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.drawPath) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[1]++;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[31]++;
            Composite saveComposite = g2.getComposite();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[32]++;
            Paint savePaint = g2.getPaint();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[33]++;
            Stroke saveStroke = g2.getStroke();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[34]++;
int CodeCoverConditionCoverageHelper_C2;

            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[3]++;
                g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[35]++;

            } else {
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[4]++;}
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.composite != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[5]++;
                g2.setComposite(this.composite);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[37]++;

            } else {
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[6]++;}
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.fillPath) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[7]++;
                g2.fill(generalPath);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[39]++;

            } else {
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[8]++;}
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[40]++;
int CodeCoverConditionCoverageHelper_C5;

            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.drawStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[9]++;
                g2.setStroke(this.drawStroke);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[41]++;

            } else {
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[10]++;}
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.drawPath) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[11]++;
                g2.draw(generalPath);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[43]++;

            } else {
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[12]++;}
            g2.setPaint(savePaint);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[44]++;
            g2.setComposite(saveComposite);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[45]++;
            g2.setStroke(saveStroke);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[46]++;

        } else {
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.branches[2]++;}
        return generalPath;

    }

    /**
     * Generates the clip path.
     *
     * @param dataArea  the dataArea that the plot is being draw in.
     * @param horizontalAxis  the horizontal axis.
     * @param verticalAxis  the vertical axis.
     *
     * @return The GeneralPath defining the outline
     */
    public GeneralPath generateClipPath(Rectangle2D dataArea,
                                        ValueAxis horizontalAxis, 
                                        ValueAxis verticalAxis) {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[47]++;

        GeneralPath generalPath = new GeneralPath();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[48]++;
        double transX = horizontalAxis.valueToJava2D(
            this.xValue[0], dataArea, RectangleEdge.BOTTOM
        );
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[49]++;
        double transY = verticalAxis.valueToJava2D(
            this.yValue[0], dataArea, RectangleEdge.LEFT
        );
        generalPath.moveTo((float) transX, (float) transY);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[50]++;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[51]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((k < this.yValue.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$edwe5yj86q483ig9km75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.loops[1]--;
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.loops[2]--;
  CodeCoverCoverageCounter$edwe5yj86q483ig9km75.loops[3]++;
}
            transX = horizontalAxis.valueToJava2D(
                this.xValue[k], dataArea, RectangleEdge.BOTTOM
            );
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[52]++;
            transY = verticalAxis.valueToJava2D(
                this.yValue[k], dataArea, RectangleEdge.LEFT
            );
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[53]++;
            generalPath.lineTo((float) transX, (float) transY);
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[54]++;
        }
        generalPath.closePath();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[55]++;

        return generalPath;

    }

    /**
     * Returns the composite.
     * 
     * @return Composite
     */
    public Composite getComposite() {
        return this.composite;
    }

    /**
     * Returns the drawPaint.
     * 
     * @return Paint
     */
    public Paint getDrawPaint() {
        return this.drawPaint;
    }

    /**
     * Returns the drawPath.
     * 
     * @return boolean
     */
    public boolean isDrawPath() {
        return this.drawPath;
    }

    /**
     * Returns the drawStroke.
     * 
     * @return Stroke
     */
    public Stroke getDrawStroke() {
        return this.drawStroke;
    }

    /**
     * Returns the fillPaint.
     * 
     * @return Paint
     */
    public Paint getFillPaint() {
        return this.fillPaint;
    }

    /**
     * Returns the fillPath.
     * 
     * @return boolean
     */
    public boolean isFillPath() {
        return this.fillPath;
    }

    /**
     * Returns the xValue.
     * 
     * @return double[]
     */
    public double[] getXValue() {
        return this.xValue;
    }

    /**
     * Returns the yValue.
     * 
     * @return double[]
     */
    public double[] getYValue() {
        return this.yValue;
    }

    /**
     * Sets the composite.
     * 
     * @param composite The composite to set
     */
    public void setComposite(Composite composite) {
        this.composite = composite;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[56]++;
    }

    /**
     * Sets the drawPaint.
     * 
     * @param drawPaint The drawPaint to set
     */
    public void setDrawPaint(Paint drawPaint) {
        this.drawPaint = drawPaint;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[57]++;
    }

    /**
     * Sets the drawPath.
     * 
     * @param drawPath The drawPath to set
     */
    public void setDrawPath(boolean drawPath) {
        this.drawPath = drawPath;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[58]++;
    }

    /**
     * Sets the drawStroke.
     * 
     * @param drawStroke The drawStroke to set
     */
    public void setDrawStroke(Stroke drawStroke) {
        this.drawStroke = drawStroke;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[59]++;
    }

    /**
     * Sets the fillPaint.
     * 
     * @param fillPaint The fillPaint to set
     */
    public void setFillPaint(Paint fillPaint) {
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[60]++;
    }

    /**
     * Sets the fillPath.
     * 
     * @param fillPath The fillPath to set
     */
    public void setFillPath(boolean fillPath) {
        this.fillPath = fillPath;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[61]++;
    }

    /**
     * Sets the xValue.
     * 
     * @param xValue The xValue to set
     */
    public void setXValue(double[] xValue) {
        this.xValue = xValue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[62]++;
    }

    /**
     * Sets the yValue.
     * 
     * @param yValue The yValue to set
     */
    public void setYValue(double[] yValue) {
        this.yValue = yValue;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[63]++;
    }

    /**
     * Returns the clip.
     * 
     * @return boolean
     */
    public boolean isClip() {
        return this.clip;
    }

    /**
     * Sets the clip.
     * 
     * @param clip The clip to set
     */
    public void setClip(boolean clip) {
        this.clip = clip;
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[64]++;
    }

    /** 
     * Returns a clone of the object (a deeper clone than default to avoid bugs 
     * when setting values in cloned object).
     * 
     * @return The clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[65]++;
        ClipPath clone = (ClipPath) super.clone();
        clone.xValue = (double[]) this.xValue.clone();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[66]++;
        clone.yValue = (double[]) this.yValue.clone();
CodeCoverCoverageCounter$edwe5yj86q483ig9km75.statements[67]++;
        return clone;
    }

}

class CodeCoverCoverageCounter$edwe5yj86q483ig9km75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$edwe5yj86q483ig9km75 ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.ClipPath.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$edwe5yj86q483ig9km75 () {
    super("org.jfree.chart.ClipPath.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.ClipPath.java");
      for (int i = 1; i <= 67; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

