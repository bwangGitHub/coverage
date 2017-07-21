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
 * -------------------
 * MarkerAxisBand.java
 * -------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Sep-2002 : Updated Javadoc comments (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Renamed HorizontalMarkerAxisBand --> MarkerAxisBand (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 07-Apr-2004 : Changed text bounds calculation (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.plot.IntervalMarker;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;

/**
 * A band that can be added to a number axis to display regions.
 */
public class MarkerAxisBand implements Serializable {
  static {
    CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1729482413886398919L;
  static {
    CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[1]++;
  }
    
    /** The axis that the band belongs to. */
    private NumberAxis axis;

    /** The top outer gap. */
    private double topOuterGap;

    /** The top inner gap. */
    private double topInnerGap;

    /** The bottom outer gap. */
    private double bottomOuterGap;

    /** The bottom inner gap. */
    private double bottomInnerGap;

    /** The font. */
    private Font font;

    /** Storage for the markers. */
    private List markers;

    /**
     * Constructs a new axis band.
     *
     * @param axis  the owner.
     * @param topOuterGap  the top outer gap.
     * @param topInnerGap  the top inner gap.
     * @param bottomOuterGap  the bottom outer gap.
     * @param bottomInnerGap  the bottom inner gap.
     * @param font  the font.
     */
    public MarkerAxisBand(NumberAxis axis,
                          double topOuterGap, double topInnerGap,
                          double bottomOuterGap, double bottomInnerGap,
                          Font font) {
        this.axis = axis;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[2]++;
        this.topOuterGap = topOuterGap;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[3]++;
        this.topInnerGap = topInnerGap;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[4]++;
        this.bottomOuterGap = bottomOuterGap;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[5]++;
        this.bottomInnerGap = bottomInnerGap;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[6]++;
        this.font = font;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[7]++;
        this.markers = new java.util.ArrayList();
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[8]++;
    }

    /**
     * Adds a marker to the band.
     *
     * @param marker  the marker.
     */
    public void addMarker(IntervalMarker marker) {
        this.markers.add(marker);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[9]++;
    }

    /**
     * Returns the height of the band.
     *
     * @param g2  the graphics device.
     *
     * @return The height of the band.
     */
    public double getHeight(Graphics2D g2) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[10]++;

        double result = 0.0;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.markers.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[1]++;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[12]++;
            LineMetrics metrics = this.font.getLineMetrics(
                "123g", g2.getFontRenderContext()
            );
            result = this.topOuterGap + this.topInnerGap + metrics.getHeight()
                     + this.bottomInnerGap + this.bottomOuterGap;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[13]++;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[2]++;}
        return result;

    }

    /**
     * A utility method that draws a string inside a rectangle.
     *
     * @param g2  the graphics device.
     * @param bounds  the rectangle.
     * @param font  the font.
     * @param text  the text.
     */
    private void drawStringInRect(Graphics2D g2, Rectangle2D bounds, Font font,
                                  String text) {

        g2.setFont(font);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[14]++;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[15]++;
        FontMetrics fm = g2.getFontMetrics(font);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[16]++;
        Rectangle2D r = TextUtilities.getTextBounds(text, g2, fm);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[17]++;
        double x = bounds.getX();
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((r.getWidth() < bounds.getWidth()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[3]++;
            x = x + (bounds.getWidth() - r.getWidth()) / 2;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[19]++;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[4]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[20]++;
        LineMetrics metrics = font.getLineMetrics(
            text, g2.getFontRenderContext()
        );
        g2.drawString(
            text, (float) x, (float) (bounds.getMaxY() 
                - this.bottomInnerGap - metrics.getDescent())
        );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[21]++;
    }

    /**
     * Draws the band.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param dataArea  the data area.
     * @param x  the x-coordinate.
     * @param y  the y-coordinate.
     */
    public void draw(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea,
                     double x, double y) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[22]++;

        double h = getHeight(g2);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[23]++;
        Iterator iterator = this.markers.iterator();
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.loops[1]--;
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.loops[2]--;
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.loops[3]++;
}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[25]++;
            IntervalMarker marker = (IntervalMarker) iterator.next();
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[26]++;
            double start =  Math.max(
                marker.getStartValue(), this.axis.getRange().getLowerBound()
            );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[27]++;
            double end = Math.min(
                marker.getEndValue(), this.axis.getRange().getUpperBound()
            );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[28]++;
            double s = this.axis.valueToJava2D(
                start, dataArea, RectangleEdge.BOTTOM
            );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[29]++;
            double e = this.axis.valueToJava2D(
                end, dataArea, RectangleEdge.BOTTOM
            );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[30]++;
            Rectangle2D r = new Rectangle2D.Double(
                s, y + this.topOuterGap, e - s, 
                h - this.topOuterGap - this.bottomOuterGap
            );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[31]++;

            Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, marker.getAlpha())
            );
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[32]++;
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[33]++;
            g2.fill(r);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[34]++;
            g2.setPaint(marker.getOutlinePaint());
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[35]++;
            g2.draw(r);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[36]++;
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[37]++;

            g2.setPaint(Color.black);
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[38]++;
            drawStringInRect(g2, r, this.font, marker.getLabel());
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[39]++;
        }

    }

    /**
     * Tests this axis for equality with another object.  Note that the axis 
     * that the band belongs to is ignored in the test.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[40]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[6]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof MarkerAxisBand) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[8]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[42]++;
        MarkerAxisBand that = (MarkerAxisBand) obj;
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.topOuterGap != that.topOuterGap) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[10]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.topInnerGap != that.topInnerGap) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[12]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.bottomInnerGap != that.bottomInnerGap) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[14]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.bottomOuterGap != that.bottomOuterGap) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[16]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.font, that.font)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[18]++;}
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.markers, that.markers)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.branches[20]++;}
        return true;
    }
    
    /**
     * Returns a hash code for the object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[49]++;
        int result = 37;
        result = 19 * result + this.font.hashCode();
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[50]++;
        result = 19 * result + this.markers.hashCode();
CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1.statements[51]++;
        return result;
    }

}

class CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1 ());
  }
    public static long[] statements = new long[52];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.MarkerAxisBand.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$19rcdjek6bxkxbvh8hoih3tf750wo1 () {
    super("org.jfree.chart.axis.MarkerAxisBand.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 51; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.MarkerAxisBand.java");
      for (int i = 1; i <= 51; i++) {
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
    for (int i = 1; i <= 11; i++) {
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

