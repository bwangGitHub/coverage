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
 * StandardXYItemRenderer.java
 * ---------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Mark Watson (www.markwatson.com);
 *                   Jonathan Nash;
 *                   Andreas Schneider;
 *                   Norbert Kiesel (for TBD Networks);
 *                   Christian W. Zuckschwerdt;
 *                   Bill Kelemen;
 *                   Nicolas Brodu (for Astrium and EADS Corporate Research 
 *                   Center);
 *
 * Changes:
 * --------
 * 19-Oct-2001 : Version 1, based on code by Mark Watson (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 21-Dec-2001 : Added working line instance to improve performance (DG);
 * 22-Jan-2002 : Added code to lock crosshairs to data points.  Based on code 
 *               by Jonathan Nash (DG);
 * 23-Jan-2002 : Added DrawInfo parameter to drawItem() method (DG);
 * 28-Mar-2002 : Added a property change listener mechanism so that the 
 *               renderer no longer needs to be immutable (DG);
 * 02-Apr-2002 : Modified to handle null values (DG);
 * 09-Apr-2002 : Modified draw method to return void.  Removed the translated 
 *               zero from the drawItem method.  Override the initialise() 
 *               method to calculate it (DG);
 * 13-May-2002 : Added code from Andreas Schneider to allow changing 
 *               shapes/colors per item (DG);
 * 24-May-2002 : Incorporated tooltips into chart entities (DG);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 05-Aug-2002 : Incorporated URLs for HTML image maps into chart entities (RA);
 * 08-Aug-2002 : Added discontinuous lines option contributed by 
 *               Norbert Kiesel (DG);
 * 20-Aug-2002 : Added user definable default values to be returned by 
 *               protected methods unless overridden by a subclass (DG);
 * 23-Sep-2002 : Updated for changes in the XYItemRenderer interface (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 15-May-2003 : Modified to take into account the plot orientation (DG);
 * 29-Jul-2003 : Amended code that doesn't compile with JDK 1.2.2 (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 24-Aug-2003 : Added null/NaN checks in drawItem (BK);
 * 08-Sep-2003 : Fixed serialization (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 21-Jan-2004 : Override for getLegendItem() method (DG);
 * 27-Jan-2004 : Moved working line into state object (DG);
 * 10-Feb-2004 : Changed drawItem() method to make cut-and-paste overriding 
 *               easier (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 08-Jun-2004 : Modified to use getX() and getY() methods (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 25-Aug-2004 : Created addEntity() method in superclass (DG);
 * 08-Oct-2004 : Added 'gapThresholdType' as suggested by Mike Watts (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 23-Feb-2005 : Fixed getLegendItem() method to show lines.  Fixed bug
 *               1077108 (shape not visible for first item in series) (DG);
 * 10-Apr-2005 : Fixed item label positioning with horizontal orientation (DG);
 * 20-Apr-2005 : Use generators for legend tooltips and URLs (DG);
 * 27-Apr-2005 : Use generator for series label in legend (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 15-Jun-2006 : Fixed bug (1380480) for rendering series as path (DG); 
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 14-Mar-2007 : Fixed problems with the equals() and clone() methods (DG);
 * 23-Mar-2007 : Clean-up of shapesFilled attributes (DG);
 * 20-Apr-2007 : Updated getLegendItem() and drawItem() for renderer 
 *               change (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() 
 *               method (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 08-Jun-2007 : Fixed bug in entity creation (DG);
 * 21-Nov-2007 : Deprecated override flag methods (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.BooleanList;
import org.jfree.util.BooleanUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;
import org.jfree.util.UnitType;

/**
 * Standard item renderer for an {@link XYPlot}.  This class can draw (a) 
 * shapes at each point, or (b) lines between points, or (c) both shapes and 
 * lines.
 * <P>
 * This renderer has been retained for historical reasons and, in general, you
 * should use the {@link XYLineAndShapeRenderer} class instead.
 */
public class StandardXYItemRenderer extends AbstractXYItemRenderer 
        implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3271351259436865995L;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[1]++;
  }
    
    /** Constant for the type of rendering (shapes only). */
    public static final int SHAPES = 1;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[2]++;
  }

    /** Constant for the type of rendering (lines only). */
    public static final int LINES = 2;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[3]++;
  }

    /** Constant for the type of rendering (shapes and lines). */
    public static final int SHAPES_AND_LINES = SHAPES | LINES;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[4]++;
  }

    /** Constant for the type of rendering (images only). */
    public static final int IMAGES = 4;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[5]++;
  }

    /** Constant for the type of rendering (discontinuous lines). */
    public static final int DISCONTINUOUS = 8;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[6]++;
  }

    /** Constant for the type of rendering (discontinuous lines). */
    public static final int DISCONTINUOUS_LINES = LINES | DISCONTINUOUS;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[7]++;
  }

    /** A flag indicating whether or not shapes are drawn at each XY point. */
    private boolean baseShapesVisible;

    /** A flag indicating whether or not lines are drawn between XY points. */
    private boolean plotLines;

    /** A flag indicating whether or not images are drawn between XY points. */
    private boolean plotImages;

    /** A flag controlling whether or not discontinuous lines are used. */
    private boolean plotDiscontinuous;

    /** Specifies how the gap threshold value is interpreted. */
    private UnitType gapThresholdType = UnitType.RELATIVE;
  {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[8]++;
  }
    
    /** Threshold for deciding when to discontinue a line. */
    private double gapThreshold = 1.0;
  {
    CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[9]++;
  }

    /** 
     * A flag that controls whether or not shapes are filled for ALL series. 
     *
     * @deprecated As of 1.0.8, this override should not be used.
     */
    private Boolean shapesFilled;

    /** 
     * A table of flags that control (per series) whether or not shapes are 
     * filled. 
     */
    private BooleanList seriesShapesFilled;

    /** The default value returned by the getShapeFilled() method. */
    private boolean baseShapesFilled;

    /** 
     * A flag that controls whether or not each series is drawn as a single 
     * path. 
     */
    private boolean drawSeriesLineAsPath;

    /** 
     * The shape that is used to represent a line in the legend. 
     * This should never be set to <code>null</code>. 
     */
    private transient Shape legendLine;
    
    /**
     * Constructs a new renderer.
     */
    public StandardXYItemRenderer() {
        this(LINES, null);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[10]++;
    }

    /**
     * Constructs a new renderer.  To specify the type of renderer, use one of 
     * the constants: {@link #SHAPES}, {@link #LINES} or 
     * {@link #SHAPES_AND_LINES}.
     *
     * @param type  the type.
     */
    public StandardXYItemRenderer(int type) {
        this(type, null);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[11]++;
    }

    /**
     * Constructs a new renderer.  To specify the type of renderer, use one of 
     * the constants: {@link #SHAPES}, {@link #LINES} or 
     * {@link #SHAPES_AND_LINES}.
     *
     * @param type  the type of renderer.
     * @param toolTipGenerator  the item label generator (<code>null</code> 
     *                          permitted).
     */
    public StandardXYItemRenderer(int type, 
                                  XYToolTipGenerator toolTipGenerator) {
        this(type, toolTipGenerator, null);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[12]++;
    }

    /**
     * Constructs a new renderer.  To specify the type of renderer, use one of 
     * the constants: {@link #SHAPES}, {@link #LINES} or 
     * {@link #SHAPES_AND_LINES}.
     *
     * @param type  the type of renderer.
     * @param toolTipGenerator  the item label generator (<code>null</code> 
     *                          permitted).
     * @param urlGenerator  the URL generator.
     */
    public StandardXYItemRenderer(int type,
                                  XYToolTipGenerator toolTipGenerator,
                                  XYURLGenerator urlGenerator) {

        super();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[13]++;
        setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[14]++;
        setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[15]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (((type & SHAPES) != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[1]++;
            this.baseShapesVisible = true;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[17]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[2]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((type & LINES) != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[3]++;
            this.plotLines = true;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[19]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[4]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 (((type & IMAGES) != 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[5]++;
            this.plotImages = true;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[21]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[6]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (((type & DISCONTINUOUS) != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[7]++;
            this.plotDiscontinuous = true;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[23]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[8]++;}

        this.shapesFilled = null;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[24]++;
        this.seriesShapesFilled = new BooleanList();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[25]++;
        this.baseShapesFilled = true;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[26]++;
        this.legendLine = new Line2D.Double(-7.0, 0.0, 7.0, 0.0);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[27]++;
        this.drawSeriesLineAsPath = false;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[28]++;
    }

    /**
     * Returns true if shapes are being plotted by the renderer.
     *
     * @return <code>true</code> if shapes are being plotted by the renderer.
     * 
     * @see #setBaseShapesVisible
     */
    public boolean getBaseShapesVisible() {
        return this.baseShapesVisible;
    }

    /**
     * Sets the flag that controls whether or not a shape is plotted at each 
     * data point.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseShapesVisible
     */
    public void setBaseShapesVisible(boolean flag) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.baseShapesVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[9]++;
            this.baseShapesVisible = flag;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[30]++;
            fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[31]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[10]++;}
    }

    // SHAPES FILLED

    /**
     * Returns the flag used to control whether or not the shape for an item is
     * filled.
     * <p>
     * The default implementation passes control to the 
     * <code>getSeriesShapesFilled</code> method.  You can override this method 
     * if you require different behaviour.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     * 
     * @see #getSeriesShapesFilled(int)
     */
    public boolean getItemShapeFilled(int series, int item) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        // return the overall setting, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.shapesFilled != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[11]++;
            return this.shapesFilled.booleanValue();

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[12]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[33]++;

        // otherwise look up the paint table
        Boolean flag = this.seriesShapesFilled.getBoolean(series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[13]++;
            return flag.booleanValue();

        }
        else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[14]++;
            return this.baseShapesFilled;
        }
    }

    /**
     * Returns the override flag that controls whether or not shapes are filled
     * for ALL series.
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @since 1.0.5
     * 
     * @deprecated As of 1.0.8, you should avoid using this method and rely
     *             on just the per-series ({@link #getSeriesShapesFilled(int)}) 
     *             and base-level ({@link #getBaseShapesFilled()}) settings.
     */
    public Boolean getShapesFilled() {
        return this.shapesFilled;
    }
    
    /**
     * Sets the override flag that controls whether or not shapes are filled
     * for ALL series and sends a {@link RendererChangeEvent} to all registered
     * listeners. 
     *
     * @param filled  the flag.
     * 
     * @see #setShapesFilled(Boolean)
     * 
     * @deprecated As of 1.0.8, you should avoid using this method and rely
     *             on just the per-series ({@link #setSeriesShapesFilled(int, 
     *             Boolean)}) and base-level ({@link #setBaseShapesVisible(
     *             boolean)}) settings.
     */
    public void setShapesFilled(boolean filled) {
        // here we use BooleanUtilities to remain compatible with JDKs < 1.4 
        setShapesFilled(BooleanUtilities.valueOf(filled));
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[35]++;
    }

    /**
     * Sets the override flag that controls whether or not shapes are filled
     * for ALL series and sends a {@link RendererChangeEvent} to all registered
     * listeners. 
     *
     * @param filled  the flag (<code>null</code> permitted).
     * 
     * @see #setShapesFilled(boolean)
     * 
     * @deprecated As of 1.0.8, you should avoid using this method and rely
     *             on just the per-series ({@link #setSeriesShapesFilled(int, 
     *             Boolean)}) and base-level ({@link #setBaseShapesVisible(
     *             boolean)}) settings.
     */
    public void setShapesFilled(Boolean filled) {
        this.shapesFilled = filled;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[36]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[37]++;
    }

    /**
     * Returns the flag used to control whether or not the shapes for a series
     * are filled.
     *
     * @param series  the series index (zero-based).
     *
     * @return A boolean.
     */
    public Boolean getSeriesShapesFilled(int series) {
        return this.seriesShapesFilled.getBoolean(series);
    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag.
     * 
     * @see #getSeriesShapesFilled(int)
     */
    public void setSeriesShapesFilled(int series, Boolean flag) {
        this.seriesShapesFilled.setBoolean(series, flag);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[38]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[39]++;
    }

    /**
     * Returns the base 'shape filled' attribute.
     *
     * @return The base flag.
     * 
     * @see #setBaseShapesFilled(boolean)
     */
    public boolean getBaseShapesFilled() {
        return this.baseShapesFilled;
    }

    /**
     * Sets the base 'shapes filled' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseShapesFilled()
     */
    public void setBaseShapesFilled(boolean flag) {
        this.baseShapesFilled = flag;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[40]++;
    }

    /**
     * Returns true if lines are being plotted by the renderer.
     *
     * @return <code>true</code> if lines are being plotted by the renderer.
     * 
     * @see #setPlotLines(boolean)
     */
    public boolean getPlotLines() {
        return this.plotLines;
    }

    /**
     * Sets the flag that controls whether or not a line is plotted between 
     * each data point and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getPlotLines()
     */
    public void setPlotLines(boolean flag) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.plotLines != flag) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[15]++;
            this.plotLines = flag;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[42]++;
            fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[43]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[16]++;}
    }

    /**
     * Returns the gap threshold type (relative or absolute).
     * 
     * @return The type.
     * 
     * @see #setGapThresholdType(UnitType)
     */
    public UnitType getGapThresholdType() {
        return this.gapThresholdType;
    }
    
    /**
     * Sets the gap threshold type and sends a {@link RendererChangeEvent} to 
     * all registered listeners.
     * 
     * @param thresholdType  the type (<code>null</code> not permitted).
     * 
     * @see #getGapThresholdType()
     */
    public void setGapThresholdType(UnitType thresholdType) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((thresholdType == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[17]++;
            throw new IllegalArgumentException(
                    "Null 'thresholdType' argument.");

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[18]++;}
        this.gapThresholdType = thresholdType;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[45]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[46]++;
    }
    
    /**
     * Returns the gap threshold for discontinuous lines.
     *
     * @return The gap threshold.
     * 
     * @see #setGapThreshold(double)
     */
    public double getGapThreshold() {
        return this.gapThreshold;
    }

    /**
     * Sets the gap threshold for discontinuous lines and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param t  the threshold.
     * 
     * @see #getGapThreshold()
     */
    public void setGapThreshold(double t) {
        this.gapThreshold = t;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[47]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[48]++;
    }

    /**
     * Returns true if images are being plotted by the renderer.
     *
     * @return <code>true</code> if images are being plotted by the renderer.
     * 
     * @see #setPlotImages(boolean)
     */
    public boolean getPlotImages() {
        return this.plotImages;
    }

    /**
     * Sets the flag that controls whether or not an image is drawn at each 
     * data point and sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getPlotImages()
     */
    public void setPlotImages(boolean flag) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.plotImages != flag) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[19]++;
            this.plotImages = flag;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[50]++;
            fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[51]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[20]++;}
    }

    /**
     * Returns a flag that controls whether or not the renderer shows
     * discontinuous lines.
     *
     * @return <code>true</code> if lines should be discontinuous.
     */
    public boolean getPlotDiscontinuous() {
        return this.plotDiscontinuous;
    }
    
    /**
     * Sets the flag that controls whether or not the renderer shows
     * discontinuous lines, and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * 
     * @param flag  the new flag value.
     * 
     * @since 1.0.5
     */
    public void setPlotDiscontinuous(boolean flag) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.plotDiscontinuous != flag) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[21]++;
            this.plotDiscontinuous = flag;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[53]++;
            fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[54]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[22]++;}
    }

    /**
     * Returns a flag that controls whether or not each series is drawn as a 
     * single path.
     * 
     * @return A boolean.
     * 
     * @see #setDrawSeriesLineAsPath(boolean)
     */
    public boolean getDrawSeriesLineAsPath() {
        return this.drawSeriesLineAsPath;
    }
    
    /**
     * Sets the flag that controls whether or not each series is drawn as a 
     * single path.
     * 
     * @param flag  the flag.
     * 
     * @see #getDrawSeriesLineAsPath()
     */
    public void setDrawSeriesLineAsPath(boolean flag) {
        this.drawSeriesLineAsPath = flag;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[55]++;
    }
    
    /**
     * Returns the shape used to represent a line in the legend.
     * 
     * @return The legend line (never <code>null</code>).
     * 
     * @see #setLegendLine(Shape)
     */
    public Shape getLegendLine() {
        return this.legendLine;   
    }
    
    /**
     * Sets the shape used as a line in each legend item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param line  the line (<code>null</code> not permitted).
     * 
     * @see #getLegendLine()
     */
    public void setLegendLine(Shape line) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((line == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[23]++;
            throw new IllegalArgumentException("Null 'line' argument.");
   
        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[24]++;}
        this.legendLine = line;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[57]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[58]++;
    }

    /**
     * Returns a legend item for a series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[59]++;
        XYPlot plot = getPlot();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[25]++;
            return null;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[26]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[61]++;
        LegendItem result = null;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[62]++;
        XYDataset dataset = plot.getDataset(datasetIndex);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[27]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getItemVisible(series, 0)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[29]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[65]++;
                String label = getLegendItemLabelGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[66]++;
                String description = label;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[67]++;
                String toolTipText = null;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[31]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[69]++;

                } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[32]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[70]++;
                String urlText = null;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[71]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[33]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[72]++;

                } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[34]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[73]++;
                Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[74]++;
                boolean shapeFilled = getItemShapeFilled(series, 0);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[75]++;
                Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[76]++;
                Paint linePaint = paint;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[77]++;
                Stroke lineStroke = lookupSeriesStroke(series);
                result = new LegendItem(label, description, toolTipText, 
                        urlText, this.baseShapesVisible, shape, shapeFilled,
                        paint, !shapeFilled, paint, lineStroke, 
                        this.plotLines, this.legendLine, lineStroke, linePaint);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[78]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[79]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[80]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[81]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[82]++;

            } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[28]++;}
        return result;
    }

    /**
     * Records the state for the renderer.  This is used to preserve state 
     * information between calls to the drawItem() method for a single chart 
     * drawing.
     */
    public static class State extends XYItemRendererState {
        
        /** The path for the current series. */
        public GeneralPath seriesPath;
        
        /** The series index. */
        private int seriesIndex;
        
        /** 
         * A flag that indicates if the last (x, y) point was 'good' 
         * (non-null). 
         */
        private boolean lastPointGood;
        
        /**
         * Creates a new state instance.
         * 
         * @param info  the plot rendering info.
         */
        public State(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[83]++;
        }
        
        /**
         * Returns a flag that indicates if the last point drawn (in the 
         * current series) was 'good' (non-null).
         * 
         * @return A boolean.
         */
        public boolean isLastPointGood() {
            return this.lastPointGood;
        }
        
        /**
         * Sets a flag that indicates if the last point drawn (in the current 
         * series) was 'good' (non-null).
         * 
         * @param good  the flag.
         */
        public void setLastPointGood(boolean good) {
            this.lastPointGood = good;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[84]++;
        }
        
        /**
         * Returns the series index for the current path.
         * 
         * @return The series index for the current path.
         */
        public int getSeriesIndex() {
            return this.seriesIndex;
        }
        
        /**
         * Sets the series index for the current path.
         * 
         * @param index  the index.
         */
        public void setSeriesIndex(int index) {
            this.seriesIndex = index;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[85]++;
        }
    }
    
    /**
     * Initialises the renderer.
     * <P>
     * This method will be called before the first item is rendered, giving the
     * renderer an opportunity to initialise any state information it wants to 
     * maintain. The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return The renderer state.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[86]++;

        State state = new State(info);
        state.seriesPath = new GeneralPath();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[87]++;
        state.seriesIndex = -1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[88]++;
        return state;

    }
    
    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, 
                         XYItemRendererState state,
                         Rectangle2D dataArea, 
                         PlotRenderingInfo info, 
                         XYPlot plot,
                         ValueAxis domainAxis, 
                         ValueAxis rangeAxis, 
                         XYDataset dataset,
                         int series, 
                         int item, 
                         CrosshairState crosshairState, 
                         int pass) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[89]++;

        boolean itemVisible = getItemVisible(series, item);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[90]++;
        
        // setup for collecting optional entity info...
        Shape entityArea = null;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[91]++;
        EntityCollection entities = null;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[92]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[35]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[93]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[36]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[94]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[95]++;
        Paint paint = getItemPaint(series, item);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[96]++;
        Stroke seriesStroke = getItemStroke(series, item);
        g2.setPaint(paint);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[97]++;
        g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[98]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[99]++;

        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[100]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[101]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((Double.isNaN(x1)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[37]++;
            itemVisible = false;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[102]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[38]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[103]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[104]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[105]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[106]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[107]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getPlotLines()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[39]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[108]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.drawSeriesLineAsPath) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[41]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[109]++;
                State s = (State) state;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[110]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((s.getSeriesIndex() != series) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[43]++;
                    // we are starting a new series path
                    s.seriesPath.reset();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[111]++;
                    s.lastPointGood = false;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[112]++;
                    s.setSeriesIndex(series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[113]++;

                } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[44]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[114]++;
int CodeCoverConditionCoverageHelper_C23;
                
                // update path to reflect latest point
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((itemVisible) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((Double.isNaN(transX1)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((Double.isNaN(transY1)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[45]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[115]++;
                    float x = (float) transX1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[116]++;
                    float y = (float) transY1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[117]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[47]++;
                        x = (float) transY1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[118]++;
                        y = (float) transX1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[119]++;

                    } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[48]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[120]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((s.isLastPointGood()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[49]++;
                        // TODO: check threshold
                        s.seriesPath.lineTo(x, y);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[121]++;

                    }
                    else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[50]++;
                        s.seriesPath.moveTo(x, y);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[122]++;
                    }
                    s.setLastPointGood(true);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[123]++;

                }
                else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[46]++;
                    s.setLastPointGood(false);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[124]++;
                }
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[125]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((item == dataset.getItemCount(series) - 1) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[51]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[126]++;
int CodeCoverConditionCoverageHelper_C27;
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((s.seriesIndex == series) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[53]++;
                        // draw path
                        g2.setStroke(lookupSeriesStroke(series));
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[127]++;
                        g2.setPaint(lookupSeriesPaint(series));
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[128]++;
                        g2.draw(s.seriesPath);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[129]++;

                    } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[54]++;}

                } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[52]++;}

            }

            else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[42]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[130]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((item != 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((itemVisible) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[55]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[131]++;
                // get the previous data point...
                double x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[132]++;
                double y0 = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[133]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((Double.isNaN(x0)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[57]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[134]++;
                    boolean drawLine = true;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[135]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((getPlotDiscontinuous()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[59]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[136]++;
                        // only draw a line if the gap between the current and 
                        // previous data point is within the threshold
                        int numX = dataset.getItemCount(series);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[137]++;
                        double minX = dataset.getXValue(series, 0);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[138]++;
                        double maxX = dataset.getXValue(series, numX - 1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[139]++;
int CodeCoverConditionCoverageHelper_C31;
                        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.gapThresholdType == UnitType.ABSOLUTE) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[61]++;
                            drawLine = Math.abs(x1 - x0) <= this.gapThreshold;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[140]++;

                        }
                        else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[62]++;
                            drawLine = Math.abs(x1 - x0) <= ((maxX - minX) 
                                / numX * getGapThreshold());
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[141]++;
                        }

                    } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[60]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[142]++;
int CodeCoverConditionCoverageHelper_C32;
                    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((drawLine) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[63]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[143]++;
                        double transX0 = domainAxis.valueToJava2D(x0, dataArea,
                                xAxisLocation);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[144]++;
                        double transY0 = rangeAxis.valueToJava2D(y0, dataArea, 
                                yAxisLocation);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[145]++;
int CodeCoverConditionCoverageHelper_C33;

                        // only draw if we have good values
                        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (128)) == 0 || true) &&
 ((Double.isNaN(transX0)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (32)) == 0 || true) &&
 ((Double.isNaN(transY0)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((Double.isNaN(transX1)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((Double.isNaN(transY1)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 4) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 4) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[65]++;
                            return;

                        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[66]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[146]++;
int CodeCoverConditionCoverageHelper_C34;

                        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[67]++;
                            state.workingLine.setLine(transY0, transX0, 
                                    transY1, transX1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[147]++;

                        }
                        else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[68]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[148]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[69]++;
                            state.workingLine.setLine(transX0, transY0, 
                                    transX1, transY1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[149]++;

                        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[70]++;}
}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[150]++;
int CodeCoverConditionCoverageHelper_C36;

                        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((state.workingLine.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[71]++;
                            g2.draw(state.workingLine);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[151]++;

                        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[72]++;}

                    } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[64]++;}

                } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[58]++;}

            } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[56]++;}
}

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[40]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[152]++;
int CodeCoverConditionCoverageHelper_C37;
        
        // we needed to get this far even for invisible items, to ensure that
        // seriesPath updates happened, but now there is nothing more we need
        // to do for non-visible items...
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((itemVisible) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[73]++;
            return;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[74]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[153]++;
int CodeCoverConditionCoverageHelper_C38;

        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((getBaseShapesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[75]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[154]++;

            Shape shape = getItemShape(series, item);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[155]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[77]++;
                shape = ShapeUtilities.createTranslatedShape(shape, transY1, 
                        transX1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[156]++;

            }
            else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[78]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[157]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[79]++;
                shape = ShapeUtilities.createTranslatedShape(shape, transX1, 
                        transY1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[158]++;

            } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[80]++;}
}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[159]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((shape.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[81]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[160]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((getItemShapeFilled(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[83]++;
                    g2.fill(shape);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[161]++;

                }
                else {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[84]++;
                    g2.draw(shape);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[162]++;
                }

            } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[82]++;}
            entityArea = shape;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[163]++;


        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[76]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[164]++;
int CodeCoverConditionCoverageHelper_C43;

        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((getPlotImages()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[85]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[165]++;
            Image image = getImage(plot, series, item, transX1, transY1);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[166]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((image != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[87]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[167]++;
                Point hotspot = getImageHotspot(plot, series, item, transX1, 
                        transY1, image);
                g2.drawImage(image, (int) (transX1 - hotspot.getX()), 
                        (int) (transY1 - hotspot.getY()), null);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[168]++;
                entityArea = new Rectangle2D.Double(transX1 - hotspot.getX(), 
                        transY1 - hotspot.getY(), image.getWidth(null), 
                        image.getHeight(null));
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[169]++;

            } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[88]++;}


        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[86]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[170]++;

        double xx = transX1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[171]++;
        double yy = transY1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[172]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[89]++;
            xx = transY1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[173]++;
            yy = transX1;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[174]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[90]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[175]++;
int CodeCoverConditionCoverageHelper_C46;          

        // draw the item label if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[91]++;
            drawItemLabel(g2, orientation, dataset, series, item, xx, yy, 
                    (y1 < 0.0));
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[176]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[92]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[177]++;

        int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[178]++;
        int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
        updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                rangeAxisIndex, transX1, transY1, orientation);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[179]++;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[180]++;
int CodeCoverConditionCoverageHelper_C47;

        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((dataArea.contains(xx, yy)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[93]++;
            addEntity(entities, entityArea, dataset, series, item, xx, yy);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[181]++;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[94]++;}

    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[182]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[95]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[96]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[183]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((obj instanceof StandardXYItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[97]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[98]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[184]++;
        StandardXYItemRenderer that = (StandardXYItemRenderer) obj;
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[185]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.baseShapesVisible != that.baseShapesVisible) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[99]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[100]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[186]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.plotLines != that.plotLines) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[101]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[102]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[187]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.plotImages != that.plotImages) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[103]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[104]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[188]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.plotDiscontinuous != that.plotDiscontinuous) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[105]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[106]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[189]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.gapThresholdType != that.gapThresholdType) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[107]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[108]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[190]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.gapThreshold != that.gapThreshold) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[109]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[110]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[191]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapesFilled, that.shapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[112]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[192]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.seriesShapesFilled.equals(that.seriesShapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[114]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[193]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.baseShapesFilled != that.baseShapesFilled) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[116]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[194]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.drawSeriesLineAsPath != that.drawSeriesLineAsPath) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[117]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[118]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[195]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendLine, that.legendLine)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[119]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.branches[120]++;}
        return super.equals(obj);

    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[196]++;
        StandardXYItemRenderer clone = (StandardXYItemRenderer) super.clone();
        clone.seriesShapesFilled 
                = (BooleanList) this.seriesShapesFilled.clone();
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[197]++;
        clone.legendLine = ShapeUtilities.clone(this.legendLine);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[198]++;
        return clone;
    }

    ////////////////////////////////////////////////////////////////////////////
    // PROTECTED METHODS
    // These provide the opportunity to subclass the standard renderer and 
    // create custom effects.
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the image used to draw a single data item.
     *
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param series  the series index.
     * @param item  the item index.
     * @param x  the x value of the item.
     * @param y  the y value of the item.
     *
     * @return The image.
     * 
     * @see #getPlotImages()
     */
    protected Image getImage(Plot plot, int series, int item, 
                             double x, double y) {
        // this method must be overridden if you want to display images
        return null;
    }

    /**
     * Returns the hotspot of the image used to draw a single data item.
     * The hotspot is the point relative to the top left of the image
     * that should indicate the data item. The default is the center of the
     * image.
     *
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param image  the image (can be used to get size information about the 
     *               image)
     * @param series  the series index
     * @param item  the item index
     * @param x  the x value of the item
     * @param y  the y value of the item
     *
     * @return The hotspot used to draw the data item.
     */
    protected Point getImageHotspot(Plot plot, int series, int item,
                                    double x, double y, Image image) {
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[199]++;

        int height = image.getHeight(null);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[200]++;
        int width = image.getWidth(null);
        return new Point(width / 2, height / 2);

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
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[201]++;
        this.legendLine = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[202]++;
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
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[203]++;
        SerialUtilities.writeShape(this.legendLine, stream);
CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip.statements[204]++;
    }

}

class CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip ());
  }
    public static long[] statements = new long[205];
    public static long[] branches = new long[121];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[61];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.StandardXYItemRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,1,2,2,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 60; i++) {
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

  public CodeCoverCoverageCounter$5c4l6388boie9i9yxha7mb5griddguftcon6kz24ip () {
    super("org.jfree.chart.renderer.xy.StandardXYItemRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 204; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 120; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 60; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.StandardXYItemRenderer.java");
      for (int i = 1; i <= 204; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 120; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 60; i++) {
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

