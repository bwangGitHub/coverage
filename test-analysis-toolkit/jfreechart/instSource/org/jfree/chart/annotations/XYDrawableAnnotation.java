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
 * -------------------------
 * XYDrawableAnnotation.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 21-May-2003 : Version 1 (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 30-Sep-2004 : Added support for tool tips and URLs (DG);
 *
 */

package org.jfree.chart.annotations;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.Drawable;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A general annotation that can be placed on an {@link XYPlot}.
 */
public class XYDrawableAnnotation extends AbstractXYAnnotation
                                  implements Cloneable, PublicCloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6540812859722691020L;
  static {
    CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[1]++;
  }
    
    /** The x-coordinate. */
    private double x;

    /** The y-coordinate. */
    private double y;

    /** The width. */
    private double width;

    /** The height. */
    private double height;

    /** The drawable object. */
    private Drawable drawable;

    /**
     * Creates a new annotation to be displayed within the given area.
     *
     * @param x  the x-coordinate for the area.
     * @param y  the y-coordinate for the area.
     * @param width  the width of the area.
     * @param height  the height of the area.
     * @param drawable  the drawable object (<code>null</code> not permitted).
     */
    public XYDrawableAnnotation(double x, double y, double width, double height,
                                Drawable drawable) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((drawable == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[1]++;
            throw new IllegalArgumentException("Null 'drawable' argument.");

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[2]++;}
        this.x = x;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[3]++;
        this.y = y;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[4]++;
        this.width = width;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[5]++;
        this.height = height;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[6]++;
        this.drawable = drawable;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[7]++;

    }

    /**
     * Draws the annotation.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  if supplied, this info object will be populated with
     *              entity information.
     */
    public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis, 
                     int rendererIndex,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[8]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[9]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[10]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[11]++;
        float j2DX = (float) domainAxis.valueToJava2D(this.x, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[12]++;
        float j2DY = (float) rangeAxis.valueToJava2D(this.y, dataArea, 
                rangeEdge);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[13]++;
        Rectangle2D area = new Rectangle2D.Double(j2DX - this.width / 2.0, 
                j2DY - this.height / 2.0, this.width, this.height);
        this.drawable.draw(g2, area);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[14]++;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[15]++;
        String toolTip = getToolTipText();
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[16]++;
        String url = getURL();
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((toolTip != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[3]++;
            addEntity(info, area, rendererIndex, toolTip, url);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[18]++;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[4]++;}
        
    }

    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object to test against.
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[5]++; // simple case
            return true;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[6]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;      
        // now try to reject equality...
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[8]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof XYDrawableAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[10]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[22]++;
        XYDrawableAnnotation that = (XYDrawableAnnotation) obj;
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[12]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[14]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.width != that.width) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[16]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.height != that.height) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[18]++;}
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.drawable, that.drawable)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.branches[20]++;}
        // seem to be the same... 
        return true;
        
    }
    
    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.x);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[28]++;
        result = (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[29]++;
        temp = Double.doubleToLongBits(this.y);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[30]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[31]++;
        temp = Double.doubleToLongBits(this.width);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[32]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[33]++;
        temp = Double.doubleToLongBits(this.height);
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[34]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d.statements[35]++;
        return result;
    }
    
    /**
     * Returns a clone of the annotation.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the annotation can't be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

class CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYDrawableAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$40ss1hffsn07qdtl1w0q5clys61mi5rmaq99v1d () {
    super("org.jfree.chart.annotations.XYDrawableAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.XYDrawableAnnotation.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
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

