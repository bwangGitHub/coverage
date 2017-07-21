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
 * -----------------------
 * DrawableLegendItem.java
 * -----------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Luke Quinane;
 *                   Barak Naveh;
 *
 * Changes
 * -------
 * 07-Feb-2002 : Version 1 (DG);
 * 23-Sep-2002 : Renamed LegendItem --> DrawableLegendItem (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Oct-2003 : Applied patch for displaying series line style, contributed by 
 *               Luke Quinane (DG);
 * 27-Mar-2004 : Added getMaxX() and getMaxY() methods (BN);
 * 27-Jan-2005 : Cleared out code that belongs in the LegendItem class (DG);
 * 
 */

package org.jfree.chart;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * This class contains a single legend item along with position details for 
 * drawing the item on a particular chart.
 * 
 * @deprecated This class is not used by JFreeChart.
 */
public class DrawableLegendItem {
  static {
    CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.ping();
  }


    /** 
     * The legend item (encapsulates information about the label, color and 
     * shape). 
     */
    private LegendItem item;

    /** The x-coordinate for the item's location. */
    private double x;

    /** The y-coordinate for the item's location. */
    private double y;

    /** The width of the item. */
    private double width;

    /** The height of the item. */
    private double height;

    /** A shape used to indicate color on the legend. */
    private Shape marker;
    
    /** A line used to indicate the series stroke on the legend */
    private Line2D line;

    /** The label position within the item. */
    private Point2D labelPosition;

    /**
     * Create a legend item.
     *
     * @param item  the legend item for display.
     */
    public DrawableLegendItem(LegendItem item) {
        this.item = item;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[1]++;
    }

    /**
     * Returns the legend item.
     *
     * @return The legend item.
     */
    public LegendItem getItem() {
        return this.item;
    }

    /**
     * Get the x-coordinate for the item's location.
     *
     * @return The x-coordinate for the item's location.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Set the x-coordinate for the item's location.
     *
     * @param x  the x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[2]++;
    }

    /**
     * Get the y-coordinate for the item's location.
     *
     * @return The y-coordinate for the item's location.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set the y-coordinate for the item's location.
     *
     * @param y  the y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[3]++;
    }

    /**
     * Get the width of this item.
     *
     * @return The width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of this item.
     *
     * @return The height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the largest X coordinate of the framing rectangle of this legend 
     * item.
     * 
     * @return The largest x coordinate of the framing rectangle of this legend 
     *         item.
     */
    public double getMaxX() {
        return getX() + getWidth();
    }

    /**
     * Returns the largest Y coordinate of the framing rectangle of this legend 
     * item.
     * 
     * @return The largest Y coordinate of the framing rectangle of this legend 
     *         item.
     */
    public double getMaxY() {
        return getY() + getHeight();
    }
    
    /**
     * Get the marker.
     *
     * @return The shape used to indicate color on the legend for this item.
     */
    public Shape getMarker() {
        return this.marker;
    }

    /**
     * Set the marker.
     *
     * @param marker  a shape used to indicate color on the legend for this 
     *                item.
     */
    public void setMarker(Shape marker) {
        this.marker = marker;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[4]++;
    }
    
    /**
     * Sets the line used to label this series.
     *
     * @param l the new line to use.
     */
    public void setLine(Line2D l) {
        this.line = l;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[5]++;
    }

    /**
     * Returns the list.
     * 
     * @return The line.
     */
    public Line2D getLine() {
        return this.line;
    }

    /**
     * Returns the label position.
     *
     * @return The label position.
     */
    public Point2D getLabelPosition() {
        return this.labelPosition;
    }

    /**
     * Sets the label position.
     *
     * @param position  the label position.
     */
    public void setLabelPosition(Point2D position) {
        this.labelPosition = position;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[6]++;
    }

    /**
     * Set the bounds of this item.
     *
     * @param x  x-coordinate for the item's location.
     * @param y  y-coordinate for the item's location.
     * @param width  the width of this item.
     * @param height  the height of this item.
     */
    public void setBounds(double x, double y, double width, double height) {
        this.x = x;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[7]++;
        this.y = y;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[8]++;
        this.width = width;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[9]++;
        this.height = height;
CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x.statements[10]++;
    }

}

class CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$27v2v1cy1ioq0judu4js55zyjtgpxsybcb4x () {
    super("org.jfree.chart.DrawableLegendItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.DrawableLegendItem.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

