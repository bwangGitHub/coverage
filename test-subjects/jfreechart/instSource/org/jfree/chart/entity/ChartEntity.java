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
 * ----------------
 * ChartEntity.java
 * ----------------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Xavier Poinsard;
 *                   Robert Fuller;
 *
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added Javadoc comments (DG);
 * 26-Jun-2002 : Added methods for image maps (DG);
 * 05-Aug-2002 : Added constructor and accessors for URL support in image maps
 *               Added getImageMapAreaTag() - previously in subclasses (RA);
 * 05-Sep-2002 : Added getImageMapAreaTag(boolean) to support OverLIB for 
 *               tooltips http://www.bosrup.com/web/overlib (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Oct-2002 : Changed getImageMapAreaTag to use title instead of alt 
 *               attribute so HTML image maps now work in Mozilla and Opera as 
 *               well as Internet Explorer (RA);
 * 13-Mar-2003 : Change getImageMapAreaTag to only return a tag when there is a
 *               tooltip or URL, as suggested by Xavier Poinsard (see Feature 
 *               Request 688079) (DG);
 * 12-Aug-2003 : Added support for custom image maps using 
 *               ToolTipTagFragmentGenerator and URLTagFragmentGenerator (RA);
 * 02-Sep-2003 : Incorporated fix (791901) submitted by Robert Fuller (DG);
 * 19-May-2004 : Added equals() method and implemented Cloneable and 
 *               Serializable (DG);
 * 29-Sep-2004 : Implemented PublicCloneable (DG);
 * 13-Jan-2005 : Fixed for compliance with XHTML 1.0 (DG);
 * 18-Apr-2005 : Use StringBuffer (DG);
 * 20-Apr-2005 : Added toString() implementation (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Feb-2007 : API doc update (DG);
 * 13-Nov-2007 : Reorganised equals(), implemented hashCode (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.URLTagFragmentGenerator;
import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A class that captures information about some component of a chart (a bar, 
 * line etc).
 */
public class ChartEntity implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4445994133561919083L;
  static {
    CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[1]++;
  }
    
    /** The area occupied by the entity (in Java 2D space). */
    private transient Shape area;

    /** The tool tip text for the entity. */
    private String toolTipText;

    /** The URL text for the entity. */
    private String urlText;

    /**
     * Creates a new chart entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     */
    public ChartEntity(Shape area) {
        // defer argument checks...
        this(area, null);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[2]++;
    }

    /**
     * Creates a new chart entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     */
    public ChartEntity(Shape area, String toolTipText) {
        // defer argument checks...
        this(area, toolTipText, null);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[3]++;
    }

    /**
     * Creates a new entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text for HTML image maps (<code>null</code> 
     *                 permitted).
     */
    public ChartEntity(Shape area, String toolTipText, String urlText) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((area == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[1]++;
            throw new IllegalArgumentException("Null 'area' argument.");
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[2]++;}
        this.area = area;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[5]++;
        this.toolTipText = toolTipText;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[6]++;
        this.urlText = urlText;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[7]++;
    }

    /**
     * Returns the area occupied by the entity (in Java 2D space).
     *
     * @return The area (never <code>null</code>).
     */
    public Shape getArea() {
        return this.area;
    }

    /**
     * Sets the area for the entity.
     * <P>
     * This class conveys information about chart entities back to a client.
     * Setting this area doesn't change the entity (which has already been
     * drawn).
     *
     * @param area  the area (<code>null</code> not permitted).
     */
    public void setArea(Shape area) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((area == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[3]++;
            throw new IllegalArgumentException("Null 'area' argument.");
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[4]++;}
        this.area = area;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[9]++;
    }

    /**
     * Returns the tool tip text for the entity.
     *
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Sets the tool tip text.
     *
     * @param text  the text (<code>null</code> permitted).
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[10]++;
    }

    /**
     * Returns the URL text for the entity.
     *
     * @return The URL text (possibly <code>null</code>).
     */
    public String getURLText() {
        return this.urlText;
    }

    /**
     * Sets the URL text.
     *
     * @param text the text (<code>null</code> permitted).
     */
    public void setURLText(String text) {
        this.urlText = text;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[11]++;
    }

    /**
     * Returns a string describing the entity area.  This string is intended
     * for use in an AREA tag when generating an image map.
     *
     * @return The shape type (never <code>null</code>).
     */
    public String getShapeType() {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.area instanceof Rectangle2D) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[5]++;
            return "rect";

        }
        else {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[6]++;
            return "poly";
        }
    }

    /**
     * Returns the shape coordinates as a string.
     *
     * @return The shape coordinates (never <code>null</code>).
     */
    public String getShapeCoords() {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.area instanceof Rectangle2D) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[7]++;
            return getRectCoords((Rectangle2D) this.area);

        }
        else {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[8]++;
            return getPolyCoords(this.area);
        }
    }

    /**
     * Returns a string containing the coordinates (x1, y1, x2, y2) for a given
     * rectangle.  This string is intended for use in an image map.
     *
     * @param rectangle  the rectangle (<code>null</code> not permitted).
     *
     * @return Upper left and lower right corner of a rectangle.
     */
    private String getRectCoords(Rectangle2D rectangle) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((rectangle == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[9]++;
            throw new IllegalArgumentException("Null 'rectangle' argument.");
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[10]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[15]++;
        int x1 = (int) rectangle.getX();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[16]++;
        int y1 = (int) rectangle.getY();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[17]++;
        int x2 = x1 + (int) rectangle.getWidth();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[18]++;
        int y2 = y1 + (int) rectangle.getHeight();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        //      fix by rfuller
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((x2 == x1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[11]++;
            x2++;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[20]++;

        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[12]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((y2 == y1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[13]++;
            y2++;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[22]++;

        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[14]++;}
        //      end fix by rfuller
        return x1 + "," + y1 + "," + x2 + "," + y2;
    }

    /**
     * Returns a string containing the coordinates for a given shape.  This
     * string is intended for use in an image map.
     *
     * @param shape  the shape (<code>null</code> not permitted).
     *
     * @return The coordinates for a given shape as string.
     */
    private String getPolyCoords(Shape shape) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[15]++;
            throw new IllegalArgumentException("Null 'shape' argument.");
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[16]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[24]++;
        StringBuffer result = new StringBuffer();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[25]++;
        boolean first = true;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[26]++;
        float[] coords = new float[6];
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[27]++;
        PathIterator pi = shape.getPathIterator(null, 1.0);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((pi.isDone()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.loops[1]--;
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.loops[2]--;
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.loops[3]++;
}
            pi.currentSegment(coords);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[29]++;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((first) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[17]++;
                first = false;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[31]++;
                result.append((int) coords[0]);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[32]++;
                result.append(",").append((int) coords[1]);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[33]++;

            }
            else {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[18]++;
                result.append(",");
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[34]++;
                result.append((int) coords[0]);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[35]++;
                result.append(",");
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[36]++;
                result.append((int) coords[1]);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[37]++;
            }
            pi.next();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[38]++;
        }
        return result.toString();
    }

    /**
     * Returns an HTML image map tag for this entity.  The returned fragment
     * should be <code>XHTML 1.0</code> compliant.
     *
     * @param toolTipTagFragmentGenerator  a generator for the HTML fragment
     *     that will contain the tooltip text (<code>null</code> not permitted 
     *     if this entity contains tooltip information).
     * @param urlTagFragmentGenerator  a generator for the HTML fragment that
     *     will contain the URL reference (<code>null</code> not permitted if 
     *     this entity has a URL).
     * 
     * @return The HTML tag.
     */
    public String getImageMapAreaTag(
            ToolTipTagFragmentGenerator toolTipTagFragmentGenerator,
            URLTagFragmentGenerator urlTagFragmentGenerator) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[39]++;

        StringBuffer tag = new StringBuffer();
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[40]++;
        boolean hasURL = (this.urlText == null ? false 
                : !this.urlText.equals(""));
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[41]++;
        boolean hasToolTip = (this.toolTipText == null ? false 
                : !this.toolTipText.equals(""));
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((hasURL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((hasToolTip) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[19]++;
            tag.append("<area shape=\"" + getShapeType() + "\"" + " coords=\"" 
                    + getShapeCoords() + "\"");
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[43]++;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[44]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((hasToolTip) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[21]++;
                tag.append(toolTipTagFragmentGenerator.generateToolTipFragment(
                        this.toolTipText));
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[45]++;

            } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[22]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[46]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((hasURL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[23]++;
                tag.append(urlTagFragmentGenerator.generateURLFragment(
                        this.urlText));
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[47]++;

            } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[24]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[48]++;
int CodeCoverConditionCoverageHelper_C14;
            // if there is a tool tip, we expect it to generate the title and
            // alt values, so we only add an empty alt if there is no tooltip
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((hasToolTip) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[25]++;
                tag.append(" alt=\"\"");
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[49]++;

            } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[26]++;}
            tag.append("/>");
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[50]++;

        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[20]++;}
        return tag.toString();
    }
    
    /**
     * Returns a string representation of the chart entity, useful for 
     * debugging.
     * 
     * @return A string.
     */
    public String toString() {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[51]++;
        StringBuffer buf = new StringBuffer("ChartEntity: ");
        buf.append("tooltip = ");
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[52]++;
        buf.append(this.toolTipText);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[53]++;
        return buf.toString();
    }
    
    /**
     * Tests the entity for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[27]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[28]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[55]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof ChartEntity) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[30]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[56]++;
        ChartEntity that = (ChartEntity) obj;
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[57]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.area.equals(that.area)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[31]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[32]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[58]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipText, that.toolTipText)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[34]++;}
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[59]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.urlText, that.urlText)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.branches[36]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[60]++;
        int result = 37;
        result = HashUtilities.hashCode(result, this.toolTipText);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[61]++;
        result = HashUtilities.hashCode(result, this.urlText);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[62]++;
        return result;
    }
    
    /**
     * Returns a clone of the entity.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the 
     *         entity.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    
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
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[63]++;
        SerialUtilities.writeShape(this.area, stream);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[64]++;
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
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[65]++;
        this.area = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9.statements[66]++;
    }

}

class CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9 ());
  }
    public static long[] statements = new long[67];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.ChartEntity.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$3zny5j6bzhtzslphbtja9uue9 () {
    super("org.jfree.chart.entity.ChartEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 66; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.entity.ChartEntity.java");
      for (int i = 1; i <= 66; i++) {
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
    for (int i = 1; i <= 19; i++) {
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

