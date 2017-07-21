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
 * AbstractRenderer.java
 * ---------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes:
 * --------
 * 22-Aug-2002 : Version 1, draws code out of AbstractXYItemRenderer to share 
 *               with AbstractCategoryItemRenderer (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 06-Nov-2002 : Moved to the com.jrefinery.chart.renderer package (DG);
 * 21-Nov-2002 : Added a paint table for the renderer to use (DG);
 * 17-Jan-2003 : Moved plot classes into a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 29-Apr-2003 : Added valueLabelFont and valueLabelPaint attributes, based on 
 *               code from Arnaud Lelievre (DG);
 * 29-Jul-2003 : Amended code that doesn't compile with JDK 1.2.2 (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 15-Sep-2003 : Fixed serialization (NB);
 * 17-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Moved PlotRenderingInfo into RendererState to allow for 
 *               multiple threads using a single renderer (DG);
 * 20-Oct-2003 : Added missing setOutlinePaint() method (DG);
 * 23-Oct-2003 : Split item label attributes into 'positive' and 'negative' 
 *               values (DG);
 * 26-Nov-2003 : Added methods to get the positive and negative item label 
 *               positions (DG);
 * 01-Mar-2004 : Modified readObject() method to prevent null pointer exceptions
 *               after deserialization (DG);
 * 19-Jul-2004 : Fixed bug in getItemLabelFont(int, int) method (DG);
 * 04-Oct-2004 : Updated equals() method, eliminated use of NumberUtils,
 *               renamed BooleanUtils --> BooleanUtilities, ShapeUtils -->
 *               ShapeUtilities (DG);
 * 15-Mar-2005 : Fixed serialization of baseFillPaint (DG);
 * 16-May-2005 : Base outline stroke should never be null (DG);
 * 01-Jun-2005 : Added hasListener() method for unit testing (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Minor API doc update (DG);
 * 19-Feb-2007 : Fixes for clone() method (DG);
 * 28-Feb-2007 : Use cached event to signal changes (DG);
 * 19-Apr-2007 : Deprecated seriesVisible and seriesVisibleInLegend flags (DG);
 * 20-Apr-2007 : Deprecated paint, fillPaint, outlinePaint, stroke, 
 *               outlineStroke, shape, itemLabelsVisible, itemLabelFont, 
 *               itemLabelPaint, positiveItemLabelPosition, 
 *               negativeItemLabelPosition and createEntities override 
 *               fields (DG);
 * 13-Jun-2007 : Added new autoPopulate flags for core series attributes (DG);
 * 23-Oct-2007 : Updated lookup methods to better handle overridden 
 *               methods (DG);
 * 
 */

package org.jfree.chart.renderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import javax.swing.event.EventListenerList;

import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.BooleanList;
import org.jfree.util.BooleanUtilities;
import org.jfree.util.ObjectList;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintList;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ShapeList;
import org.jfree.util.ShapeUtilities;
import org.jfree.util.StrokeList;

/**
 * Base class providing common services for renderers.  Most methods that update
 * attributes of the renderer will fire a {@link RendererChangeEvent}, which 
 * normally means the plot that owns the renderer will receive notification that
 * the renderer has been changed (the plot will, in turn, notify the chart).
 */
public abstract class AbstractRenderer implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -828267569428206075L;
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[1]++;
  }
    
    /** Zero represented as a <code>Double</code>. */
    public static final Double ZERO = new Double(0.0);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[2]++;
  }
    
    /** The default paint. */
    public static final Paint DEFAULT_PAINT = Color.blue;
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[3]++;
  }

    /** The default outline paint. */
    public static final Paint DEFAULT_OUTLINE_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[4]++;
  }

    /** The default stroke. */
    public static final Stroke DEFAULT_STROKE = new BasicStroke(1.0f);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[5]++;
  }

    /** The default outline stroke. */
    public static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(1.0f);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[6]++;
  }

    /** The default shape. */
    public static final Shape DEFAULT_SHAPE 
            = new Rectangle2D.Double(-3.0, -3.0, 6.0, 6.0);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[7]++;
  }

    /** The default value label font. */
    public static final Font DEFAULT_VALUE_LABEL_FONT 
            = new Font("SansSerif", Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[8]++;
  }

    /** The default value label paint. */
    public static final Paint DEFAULT_VALUE_LABEL_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[9]++;
  }

    /** 
     * A flag that controls the visibility of ALL series.
     * 
     * @deprecated This field is redundant, you can rely on seriesVisibleList
     *     and baseSeriesVisible.  Deprecated from version 1.0.6 onwards.
     */
    private Boolean seriesVisible;
    
    /** A list of flags that controls whether or not each series is visible. */
    private BooleanList seriesVisibleList;

    /** The default visibility for each series. */
    private boolean baseSeriesVisible;
    
    /** 
     * A flag that controls the visibility of ALL series in the legend. 
     * 
     * @deprecated This field is redundant, you can rely on 
     *     seriesVisibleInLegendList and baseSeriesVisibleInLegend.  
     *     Deprecated from version 1.0.6 onwards.
     */
    private Boolean seriesVisibleInLegend;
    
    /** 
     * A list of flags that controls whether or not each series is visible in 
     * the legend. 
     */
    private BooleanList seriesVisibleInLegendList;

    /** The default visibility for each series in the legend. */
    private boolean baseSeriesVisibleInLegend;
        
    /** 
     * The paint for ALL series (optional). 
     *
     * @deprecated This field is redundant, you can rely on paintList and 
     *     basePaint.  Deprecated from version 1.0.6 onwards.
     */
    private transient Paint paint;

    /** The paint list. */
    private PaintList paintList;

    /**
     * A flag that controls whether or not the paintList is auto-populated
     * in the {@link #lookupSeriesPaint(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesPaint;
    
    /** The base paint. */
    private transient Paint basePaint;

    /** 
     * The fill paint for ALL series (optional). 
     *
     * @deprecated This field is redundant, you can rely on fillPaintList and 
     *     baseFillPaint.  Deprecated from version 1.0.6 onwards.
     */
    private transient Paint fillPaint;

    /** The fill paint list. */
    private PaintList fillPaintList;
    
    /**
     * A flag that controls whether or not the fillPaintList is auto-populated
     * in the {@link #lookupSeriesFillPaint(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesFillPaint;

    /** The base fill paint. */
    private transient Paint baseFillPaint;

    /** 
     * The outline paint for ALL series (optional). 
     *
     * @deprecated This field is redundant, you can rely on outlinePaintList 
     *         and baseOutlinePaint.  Deprecated from version 1.0.6 onwards.
     */
    private transient Paint outlinePaint;

    /** The outline paint list. */
    private PaintList outlinePaintList;

    /**
     * A flag that controls whether or not the outlinePaintList is 
     * auto-populated in the {@link #lookupSeriesOutlinePaint(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesOutlinePaint;
    
    /** The base outline paint. */
    private transient Paint baseOutlinePaint;

    /** 
     * The stroke for ALL series (optional). 
     *
     * @deprecated This field is redundant, you can rely on strokeList and 
     *     baseStroke.  Deprecated from version 1.0.6 onwards.
     */
    private transient Stroke stroke;

    /** The stroke list. */
    private StrokeList strokeList;

    /**
     * A flag that controls whether or not the strokeList is auto-populated
     * in the {@link #lookupSeriesStroke(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesStroke;

    /** The base stroke. */
    private transient Stroke baseStroke;

    /** 
     * The outline stroke for ALL series (optional). 
     *
     * @deprecated This field is redundant, you can rely on strokeList and 
     *     baseStroke.  Deprecated from version 1.0.6 onwards.
     */
    private transient Stroke outlineStroke;

    /** The outline stroke list. */
    private StrokeList outlineStrokeList;

    /** The base outline stroke. */
    private transient Stroke baseOutlineStroke;

    /**
     * A flag that controls whether or not the outlineStrokeList is 
     * auto-populated in the {@link #lookupSeriesOutlineStroke(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesOutlineStroke;

    /** 
     * The shape for ALL series (optional). 
     *
     * @deprecated This field is redundant, you can rely on shapeList and 
     *     baseShape.  Deprecated from version 1.0.6 onwards.
     */
    private transient Shape shape;

    /** A shape list. */
    private ShapeList shapeList;
    
    /**
     * A flag that controls whether or not the shapeList is auto-populated
     * in the {@link #lookupSeriesShape(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesShape;

    /** The base shape. */
    private transient Shape baseShape;

    /** 
     * Visibility of the item labels for ALL series (optional). 
     * 
     * @deprecated This field is redundant, you can rely on 
     *     itemLabelsVisibleList and baseItemLabelsVisible.  Deprecated from 
     *     version 1.0.6 onwards.
     */
    private Boolean itemLabelsVisible;

    /** Visibility of the item labels PER series. */
    private BooleanList itemLabelsVisibleList;

    /** The base item labels visible. */
    private Boolean baseItemLabelsVisible;

    /** 
     * The item label font for ALL series (optional). 
     * 
     * @deprecated This field is redundant, you can rely on itemLabelFontList 
     *     and baseItemLabelFont.  Deprecated from version 1.0.6 onwards.
     */
    private Font itemLabelFont;

    /** The item label font list (one font per series). */
    private ObjectList itemLabelFontList;

    /** The base item label font. */
    private Font baseItemLabelFont;

    /** 
     * The item label paint for ALL series. 
     * 
     * @deprecated This field is redundant, you can rely on itemLabelPaintList 
     *     and baseItemLabelPaint.  Deprecated from version 1.0.6 onwards.
     */
    private transient Paint itemLabelPaint;

    /** The item label paint list (one paint per series). */
    private PaintList itemLabelPaintList;

    /** The base item label paint. */
    private transient Paint baseItemLabelPaint;

    /** 
     * The positive item label position for ALL series (optional). 
     * 
     * @deprecated This field is redundant, you can rely on the 
     *     positiveItemLabelPositionList and basePositiveItemLabelPosition
     *     fields.  Deprecated from version 1.0.6 onwards.
     */
    private ItemLabelPosition positiveItemLabelPosition;
    
    /** The positive item label position (per series). */
    private ObjectList positiveItemLabelPositionList;
    
    /** The fallback positive item label position. */
    private ItemLabelPosition basePositiveItemLabelPosition;
    
    /** 
     * The negative item label position for ALL series (optional). 
     * 
     * @deprecated This field is redundant, you can rely on the 
     *     negativeItemLabelPositionList and baseNegativeItemLabelPosition
     *     fields.  Deprecated from version 1.0.6 onwards.
     */
    private ItemLabelPosition negativeItemLabelPosition;
    
    /** The negative item label position (per series). */
    private ObjectList negativeItemLabelPositionList;
    
    /** The fallback negative item label position. */
    private ItemLabelPosition baseNegativeItemLabelPosition;

    /** The item label anchor offset. */
    private double itemLabelAnchorOffset = 2.0;
  {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[10]++;
  }

    /** 
     * A flag that controls whether or not entities are generated for 
     * ALL series (optional). 
     * 
     * @deprecated This field is redundant, you can rely on the 
     *     createEntitiesList and baseCreateEntities fields.  Deprecated from 
     *     version 1.0.6 onwards.
     */
    private Boolean createEntities;

    /** 
     * Flags that control whether or not entities are generated for each 
     * series.  This will be overridden by 'createEntities'. 
     */
    private BooleanList createEntitiesList;

    /**
     * The default flag that controls whether or not entities are generated.
     * This flag is used when both the above flags return null. 
     */
    private boolean baseCreateEntities;
    
    /** Storage for registered change listeners. */
    private transient EventListenerList listenerList;

    /** An event for re-use. */
    private transient RendererChangeEvent event;
    
    /**
     * Default constructor.
     */
    public AbstractRenderer() {

        this.seriesVisible = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[11]++;
        this.seriesVisibleList = new BooleanList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[12]++;
        this.baseSeriesVisible = true;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[13]++;
        
        this.seriesVisibleInLegend = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[14]++;
        this.seriesVisibleInLegendList = new BooleanList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[15]++;
        this.baseSeriesVisibleInLegend = true;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[16]++;

        this.paint = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[17]++;
        this.paintList = new PaintList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[18]++;
        this.basePaint = DEFAULT_PAINT;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[19]++;
        this.autoPopulateSeriesPaint = true;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[20]++;

        this.fillPaint = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[21]++;
        this.fillPaintList = new PaintList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[22]++;
        this.baseFillPaint = Color.white;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[23]++;
        this.autoPopulateSeriesFillPaint = false;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[24]++;

        this.outlinePaint = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[25]++;
        this.outlinePaintList = new PaintList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[26]++;
        this.baseOutlinePaint = DEFAULT_OUTLINE_PAINT;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[27]++;
        this.autoPopulateSeriesOutlinePaint = false;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[28]++;

        this.stroke = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[29]++;
        this.strokeList = new StrokeList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[30]++;
        this.baseStroke = DEFAULT_STROKE;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[31]++;
        this.autoPopulateSeriesStroke = false;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[32]++;

        this.outlineStroke = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[33]++;
        this.outlineStrokeList = new StrokeList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[34]++;
        this.baseOutlineStroke = DEFAULT_OUTLINE_STROKE;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[35]++;
        this.autoPopulateSeriesOutlineStroke = false;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[36]++;

        this.shape = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[37]++;
        this.shapeList = new ShapeList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[38]++;
        this.baseShape = DEFAULT_SHAPE;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[39]++;
        this.autoPopulateSeriesShape = true;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[40]++;

        this.itemLabelsVisible = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[41]++;
        this.itemLabelsVisibleList = new BooleanList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[42]++;
        this.baseItemLabelsVisible = Boolean.FALSE;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[43]++;

        this.itemLabelFont = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[44]++;
        this.itemLabelFontList = new ObjectList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[45]++;
        this.baseItemLabelFont = new Font("SansSerif", Font.PLAIN, 10);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[46]++;

        this.itemLabelPaint = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[47]++;
        this.itemLabelPaintList = new PaintList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[48]++;
        this.baseItemLabelPaint = Color.black;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[49]++;

        this.positiveItemLabelPosition = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[50]++;
        this.positiveItemLabelPositionList = new ObjectList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[51]++;
        this.basePositiveItemLabelPosition = new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[52]++;
        
        this.negativeItemLabelPosition = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[53]++;
        this.negativeItemLabelPositionList = new ObjectList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[54]++;
        this.baseNegativeItemLabelPosition = new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[55]++;

        this.createEntities = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[56]++;
        this.createEntitiesList = new BooleanList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[57]++;
        this.baseCreateEntities = true;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[58]++;
        
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[59]++;

    }

    /**
     * Returns the drawing supplier from the plot.
     * 
     * @return The drawing supplier.
     */
    public abstract DrawingSupplier getDrawingSupplier();
    
    // SERIES VISIBLE (not yet respected by all renderers)

    /**
     * Returns a boolean that indicates whether or not the specified item 
     * should be drawn (this is typically used to hide an entire series).
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return A boolean.
     */
    public boolean getItemVisible(int series, int item) {
        return isSeriesVisible(series);
    }
    
    /**
     * Returns a boolean that indicates whether or not the specified series 
     * should be drawn.
     * 
     * @param series  the series index.
     * 
     * @return A boolean.
     */
    public boolean isSeriesVisible(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[60]++;
        boolean result = this.baseSeriesVisible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[61]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.seriesVisible != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[1]++;
            result = this.seriesVisible.booleanValue();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[62]++;
   
        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[2]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[63]++;
            Boolean b = this.seriesVisibleList.getBoolean(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[64]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((b != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[3]++;
                result = b.booleanValue();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[65]++;
   
            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[4]++;}
        }
        return result;
    }
    
    /**
     * Returns the flag that controls the visibility of ALL series.  This flag 
     * overrides the per series and default settings - you must set it to 
     * <code>null</code> if you want the other settings to apply.
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesVisible(Boolean)
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #getSeriesVisible(int)} and
     *     {@link #getBaseSeriesVisible()}.
     */
    public Boolean getSeriesVisible() {
        return this.seriesVisible;   
    }
    
    /**
     * Sets the flag that controls the visibility of ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  This flag 
     * overrides the per series and default settings - you must set it to 
     * <code>null</code> if you want the other settings to apply.
     * 
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesVisible()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesVisible(int, Boolean)} 
     *     and {@link #setBaseSeriesVisible(boolean)}.
     */
    public void setSeriesVisible(Boolean visible) {
         setSeriesVisible(visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[66]++;
    }
    
    /**
     * Sets the flag that controls the visibility of ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  This flag 
     * overrides the per series and default settings - you must set it to 
     * <code>null</code> if you want the other settings to apply.
     * 
     * @param visible  the flag (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesVisible()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesVisible(int, Boolean)} 
     *     and {@link #setBaseSeriesVisible(boolean)}.
     */
    public void setSeriesVisible(Boolean visible, boolean notify) {
        this.seriesVisible = visible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[67]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[68]++;
int CodeCoverConditionCoverageHelper_C3;   
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[5]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[69]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[6]++;}
    }
    
    /**
     * Returns the flag that controls whether a series is visible.
     *
     * @param series  the series index (zero-based).
     *
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesVisible(int, Boolean)
     */
    public Boolean getSeriesVisible(int series) {
        return this.seriesVisibleList.getBoolean(series);
    }
    
    /**
     * Sets the flag that controls whether a series is visible and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesVisible(int)
     */
    public void setSeriesVisible(int series, Boolean visible) {
        setSeriesVisible(series, visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[70]++;
    }
    
    /**
     * Sets the flag that controls whether a series is visible and, if 
     * requested, sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param series  the series index.
     * @param visible  the flag (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesVisible(int)
     */
    public void setSeriesVisible(int series, Boolean visible, boolean notify) {
        this.seriesVisibleList.setBoolean(series, visible);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[71]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[72]++;
int CodeCoverConditionCoverageHelper_C4;       
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[7]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[73]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[8]++;}
    }

    /**
     * Returns the base visibility for all series.
     *
     * @return The base visibility.
     * 
     * @see #setBaseSeriesVisible(boolean)
     */
    public boolean getBaseSeriesVisible() {
        return this.baseSeriesVisible;
    }

    /**
     * Sets the base visibility and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     *
     * @param visible  the flag.
     * 
     * @see #getBaseSeriesVisible()
     */
    public void setBaseSeriesVisible(boolean visible) {
        // defer argument checking...
        setBaseSeriesVisible(visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[74]++;
    }
    
    /**
     * Sets the base visibility and, if requested, sends 
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the visibility.
     * @param notify  notify listeners?
     * 
     * @see #getBaseSeriesVisible()
     */
    public void setBaseSeriesVisible(boolean visible, boolean notify) {
        this.baseSeriesVisible = visible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[75]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[76]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[9]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[77]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[10]++;}
    }

    // SERIES VISIBLE IN LEGEND (not yet respected by all renderers)
    
    /**
     * Returns <code>true</code> if the series should be shown in the legend,
     * and <code>false</code> otherwise.
     * 
     * @param series  the series index.
     * 
     * @return A boolean.
     */
    public boolean isSeriesVisibleInLegend(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[78]++;
        boolean result = this.baseSeriesVisibleInLegend;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[79]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.seriesVisibleInLegend != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[11]++;
            result = this.seriesVisibleInLegend.booleanValue();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[80]++;
   
        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[12]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[81]++;
            Boolean b = this.seriesVisibleInLegendList.getBoolean(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[82]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((b != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[13]++;
                result = b.booleanValue();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[83]++;
   
            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[14]++;}
        }
        return result;
    }
    
    /**
     * Returns the flag that controls the visibility of ALL series in the 
     * legend.  This flag overrides the per series and default settings - you 
     * must set it to <code>null</code> if you want the other settings to 
     * apply.
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesVisibleInLegend(Boolean)
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #getSeriesVisibleInLegend(int)} 
     *     and {@link #getBaseSeriesVisibleInLegend()}.
     */
    public Boolean getSeriesVisibleInLegend() {
        return this.seriesVisibleInLegend;   
    }
    
    /**
     * Sets the flag that controls the visibility of ALL series in the legend 
     * and sends a {@link RendererChangeEvent} to all registered listeners.  
     * This flag overrides the per series and default settings - you must set 
     * it to <code>null</code> if you want the other settings to apply.
     * 
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesVisibleInLegend()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesVisibleInLegend(int, 
     *     Boolean)} and {@link #setBaseSeriesVisibleInLegend(boolean)}.
     */
    public void setSeriesVisibleInLegend(Boolean visible) {
         setSeriesVisibleInLegend(visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[84]++;
    }
    
    /**
     * Sets the flag that controls the visibility of ALL series in the legend 
     * and sends a {@link RendererChangeEvent} to all registered listeners.  
     * This flag overrides the per series and default settings - you must set 
     * it to <code>null</code> if you want the other settings to apply.
     * 
     * @param visible  the flag (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesVisibleInLegend()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesVisibleInLegend(int, 
     *     Boolean, boolean)} and {@link #setBaseSeriesVisibleInLegend(boolean,
     *     boolean)}.
     */
    public void setSeriesVisibleInLegend(Boolean visible, boolean notify) {
        this.seriesVisibleInLegend = visible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[85]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[86]++;
int CodeCoverConditionCoverageHelper_C8;   
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[15]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[87]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[16]++;}
    }
    
    /**
     * Returns the flag that controls whether a series is visible in the 
     * legend.  This method returns only the "per series" settings - to 
     * incorporate the override and base settings as well, you need to use the 
     * {@link #isSeriesVisibleInLegend(int)} method.
     *
     * @param series  the series index (zero-based).
     *
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesVisibleInLegend(int, Boolean)
     */
    public Boolean getSeriesVisibleInLegend(int series) {
        return this.seriesVisibleInLegendList.getBoolean(series);
    }
    
    /**
     * Sets the flag that controls whether a series is visible in the legend 
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesVisibleInLegend(int)
     */
    public void setSeriesVisibleInLegend(int series, Boolean visible) {
        setSeriesVisibleInLegend(series, visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[88]++;
    }
    
    /**
     * Sets the flag that controls whether a series is visible in the legend
     * and, if requested, sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param series  the series index.
     * @param visible  the flag (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesVisibleInLegend(int)
     */
    public void setSeriesVisibleInLegend(int series, Boolean visible, 
                                         boolean notify) {
        this.seriesVisibleInLegendList.setBoolean(series, visible);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[89]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[90]++;
int CodeCoverConditionCoverageHelper_C9;       
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[17]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[91]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[18]++;}
    }

    /**
     * Returns the base visibility in the legend for all series.
     *
     * @return The base visibility.
     * 
     * @see #setBaseSeriesVisibleInLegend(boolean)
     */
    public boolean getBaseSeriesVisibleInLegend() {
        return this.baseSeriesVisibleInLegend;
    }

    /**
     * Sets the base visibility in the legend and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible  the flag.
     * 
     * @see #getBaseSeriesVisibleInLegend()
     */
    public void setBaseSeriesVisibleInLegend(boolean visible) {
        // defer argument checking...
        setBaseSeriesVisibleInLegend(visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[92]++;
    }
    
    /**
     * Sets the base visibility in the legend and, if requested, sends 
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the visibility.
     * @param notify  notify listeners?
     * 
     * @see #getBaseSeriesVisibleInLegend()
     */
    public void setBaseSeriesVisibleInLegend(boolean visible, boolean notify) {
        this.baseSeriesVisibleInLegend = visible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[93]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[94]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[19]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[95]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[20]++;}
    }

    // PAINT
    
    /**
     * Returns the paint used to fill data items as they are drawn.
     * <p>
     * The default implementation passes control to the 
     * <code>getSeriesPaint</code> method. You can override this method if you 
     * require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The paint (never <code>null</code>).
     */
    public Paint getItemPaint(int row, int column) {
        return lookupSeriesPaint(row);
    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series  the series index (zero-based).
     *
     * @return The paint (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Paint lookupSeriesPaint(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[96]++;
int CodeCoverConditionCoverageHelper_C11;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.paint != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[21]++;
            return this.paint;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[22]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[97]++;

        // otherwise look up the paint list
        Paint seriesPaint = getSeriesPaint(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[98]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((seriesPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.autoPopulateSeriesPaint) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[23]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[99]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[100]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[25]++;
                seriesPaint = supplier.getNextPaint();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[101]++;
                setSeriesPaint(series, seriesPaint, false);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[102]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[24]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[103]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((seriesPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[27]++;
            seriesPaint = this.basePaint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[104]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[28]++;}
        return seriesPaint;

    }

    /**
     * Sets the paint to be used for ALL series, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  If this is 
     * <code>null</code>, the renderer will use the paint for the series.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesPaint(int, Paint)} and 
     *     {@link #setBasePaint(Paint)}.
     */
    public void setPaint(Paint paint) {
        setPaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[105]++;
    }
    
    /**
     * Sets the paint to be used for all series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesPaint(int, Paint, 
     *     boolean)} and {@link #setBasePaint(Paint, boolean)}.
     */
    public void setPaint(Paint paint, boolean notify) {
        this.paint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[106]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[107]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[29]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[108]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[30]++;}
    }
    
    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series  the series index (zero-based).
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setSeriesPaint(int, Paint)
     */
    public Paint getSeriesPaint(int series) {
        return this.paintList.getPaint(series);
    }
    
    /**
     * Sets the paint used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesPaint(int)
     */
    public void setSeriesPaint(int series, Paint paint) {
        setSeriesPaint(series, paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[109]++;
    }
    
    /**
     * Sets the paint used for a series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index.
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesPaint(int)
     */
    public void setSeriesPaint(int series, Paint paint, boolean notify) {
        this.paintList.setPaint(series, paint);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[110]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[111]++;
int CodeCoverConditionCoverageHelper_C16;       
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[31]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[112]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[32]++;}
    }

    /**
     * Returns the base paint.
     *
     * @return The base paint (never <code>null</code>).
     * 
     * @see #setBasePaint(Paint)
     */
    public Paint getBasePaint() {
        return this.basePaint;
    }

    /**
     * Sets the base paint and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBasePaint()
     */
    public void setBasePaint(Paint paint) {
        // defer argument checking...
        setBasePaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[113]++;
    }
    
    /**
     * Sets the base paint and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #getBasePaint()
     */
    public void setBasePaint(Paint paint, boolean notify) {
        this.basePaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[114]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[115]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[33]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[116]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[34]++;}
    }
    
    /**
     * Returns the flag that controls whether or not the series paint list is
     * automatically populated when {@link #lookupSeriesPaint(int)} is called.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setAutoPopulateSeriesPaint(boolean)
     */
    public boolean getAutoPopulateSeriesPaint() {
        return this.autoPopulateSeriesPaint;
    }
    
    /**
     * Sets the flag that controls whether or not the series paint list is
     * automatically populated when {@link #lookupSeriesPaint(int)} is called.
     * 
     * @param auto  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #getAutoPopulateSeriesPaint()
     */
    public void setAutoPopulateSeriesPaint(boolean auto) {
        this.autoPopulateSeriesPaint = auto;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[117]++;
    }

    //// FILL PAINT //////////////////////////////////////////////////////////
    
    /**
     * Returns the paint used to fill data items as they are drawn.  The 
     * default implementation passes control to the 
     * {@link #lookupSeriesFillPaint(int)} method - you can override this 
     * method if you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The paint (never <code>null</code>).
     */
    public Paint getItemFillPaint(int row, int column) {
        return lookupSeriesFillPaint(row);
    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series  the series (zero-based index).
     *
     * @return The paint (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Paint lookupSeriesFillPaint(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[118]++;
int CodeCoverConditionCoverageHelper_C18;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[35]++;
            return this.fillPaint;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[36]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[119]++;

        // otherwise look up the paint table
        Paint seriesFillPaint = getSeriesFillPaint(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[120]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((seriesFillPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.autoPopulateSeriesFillPaint) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[37]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[121]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[122]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[39]++;
                seriesFillPaint = supplier.getNextFillPaint();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[123]++;
                setSeriesFillPaint(series, seriesFillPaint, false);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[124]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[40]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[38]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[125]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((seriesFillPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[41]++;
            seriesFillPaint = this.baseFillPaint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[126]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[42]++;}
        return seriesFillPaint;

    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series  the series (zero-based index).
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setSeriesFillPaint(int, Paint)
     */
    public Paint getSeriesFillPaint(int series) {
        return this.fillPaintList.getPaint(series);    
    }
    
    /**
     * Sets the paint used for a series fill and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesFillPaint(int)
     */
    public void setSeriesFillPaint(int series, Paint paint) {
        setSeriesFillPaint(series, paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[127]++;
    }

    /**
     * Sets the paint used to fill a series and, if requested, 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesFillPaint(int)
     */    
    public void setSeriesFillPaint(int series, Paint paint, boolean notify) {
        this.fillPaintList.setPaint(series, paint);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[128]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[129]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[43]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[130]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[44]++;}
    }

    /**
     * Sets the fill paint for ALL series (optional).
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesFillPaint(int, Paint)} 
     *     and {@link #setBaseFillPaint(Paint)}.
     */
    public void setFillPaint(Paint paint) {
        setFillPaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[131]++;
    }

    /**
     * Sets the fill paint for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesFillPaint(int, Paint,
     *     boolean)} and {@link #setBaseFillPaint(Paint, boolean)}.
     */
    public void setFillPaint(Paint paint, boolean notify) {
        this.fillPaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[132]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[133]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[45]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[134]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[46]++;}
    }
    
    /**
     * Returns the base fill paint.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setBaseFillPaint(Paint)
     */
    public Paint getBaseFillPaint() {
        return this.baseFillPaint;
    }

    /**
     * Sets the base fill paint and sends a {@link RendererChangeEvent} to 
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseFillPaint()
     */
    public void setBaseFillPaint(Paint paint) {
        // defer argument checking...
        setBaseFillPaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[135]++;
    }
    
    /**
     * Sets the base fill paint and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #getBaseFillPaint()
     */
    public void setBaseFillPaint(Paint paint, boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[136]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[47]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[48]++;}
        this.baseFillPaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[137]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[138]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[49]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[139]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[50]++;}
    }

    /**
     * Returns the flag that controls whether or not the series fill paint list
     * is automatically populated when {@link #lookupSeriesFillPaint(int)} is 
     * called.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setAutoPopulateSeriesFillPaint(boolean)
     */
    public boolean getAutoPopulateSeriesFillPaint() {
        return this.autoPopulateSeriesFillPaint;
    }
    
    /**
     * Sets the flag that controls whether or not the series fill paint list is
     * automatically populated when {@link #lookupSeriesFillPaint(int)} is 
     * called.
     * 
     * @param auto  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #getAutoPopulateSeriesFillPaint()
     */
    public void setAutoPopulateSeriesFillPaint(boolean auto) {
        this.autoPopulateSeriesFillPaint = auto;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[140]++;
    }

    // OUTLINE PAINT //////////////////////////////////////////////////////////
    
    /**
     * Returns the paint used to outline data items as they are drawn.
     * <p>
     * The default implementation passes control to the 
     * {@link #lookupSeriesOutlinePaint} method.  You can override this method 
     * if you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The paint (never <code>null</code>).
     */
    public Paint getItemOutlinePaint(int row, int column) {
        return lookupSeriesOutlinePaint(row);
    }

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     *
     * @param series  the series (zero-based index).
     *
     * @return The paint (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Paint lookupSeriesOutlinePaint(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[141]++;
int CodeCoverConditionCoverageHelper_C26;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[51]++;
            return this.outlinePaint;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[52]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[142]++;

        // otherwise look up the paint table
        Paint seriesOutlinePaint = getSeriesOutlinePaint(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[143]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((seriesOutlinePaint == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.autoPopulateSeriesOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[53]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[144]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[145]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[55]++;
                seriesOutlinePaint = supplier.getNextOutlinePaint();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[146]++;
                setSeriesOutlinePaint(series, seriesOutlinePaint, false);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[147]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[56]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[54]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[148]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((seriesOutlinePaint == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[57]++;
            seriesOutlinePaint = this.baseOutlinePaint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[149]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[58]++;}
        return seriesOutlinePaint;

    }

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     *
     * @param series  the series (zero-based index).
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setSeriesOutlinePaint(int, Paint)
     */
    public Paint getSeriesOutlinePaint(int series) {
        return this.outlinePaintList.getPaint(series);    
    }
    
    /**
     * Sets the paint used for a series outline and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesOutlinePaint(int)
     */
    public void setSeriesOutlinePaint(int series, Paint paint) {
        setSeriesOutlinePaint(series, paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[150]++;
    }

    /**
     * Sets the paint used to draw the outline for a series and, if requested, 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesOutlinePaint(int)
     */    
    public void setSeriesOutlinePaint(int series, Paint paint, boolean notify) {
        this.outlinePaintList.setPaint(series, paint);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[151]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[152]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[59]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[153]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[60]++;}
    }

    /**
     * Sets the outline paint for ALL series (optional) and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesOutlinePaint(int, 
     *     Paint)} and {@link #setBaseOutlinePaint(Paint)}.
     */
    public void setOutlinePaint(Paint paint) {
        setOutlinePaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[154]++;
    }

    /**
     * Sets the outline paint for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesOutlinePaint(int, 
     *     Paint, boolean)} and {@link #setBaseOutlinePaint(Paint, boolean)}.
     */
    public void setOutlinePaint(Paint paint, boolean notify) {
        this.outlinePaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[155]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[156]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[61]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[157]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[62]++;}
    }
    
    /**
     * Returns the base outline paint.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setBaseOutlinePaint(Paint)
     */
    public Paint getBaseOutlinePaint() {
        return this.baseOutlinePaint;
    }

    /**
     * Sets the base outline paint and sends a {@link RendererChangeEvent} to 
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseOutlinePaint()
     */
    public void setBaseOutlinePaint(Paint paint) {
        // defer argument checking...
        setBaseOutlinePaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[158]++;
    }
    
    /**
     * Sets the base outline paint and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #getBaseOutlinePaint()
     */
    public void setBaseOutlinePaint(Paint paint, boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[159]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[63]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[64]++;}
        this.baseOutlinePaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[160]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[161]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[65]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[162]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[66]++;}
    }

    /**
     * Returns the flag that controls whether or not the series outline paint 
     * list is automatically populated when 
     * {@link #lookupSeriesOutlinePaint(int)} is called.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setAutoPopulateSeriesOutlinePaint(boolean)
     */
    public boolean getAutoPopulateSeriesOutlinePaint() {
        return this.autoPopulateSeriesOutlinePaint;
    }
    
    /**
     * Sets the flag that controls whether or not the series outline paint list
     * is automatically populated when {@link #lookupSeriesOutlinePaint(int)} 
     * is called.
     * 
     * @param auto  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #getAutoPopulateSeriesOutlinePaint()
     */
    public void setAutoPopulateSeriesOutlinePaint(boolean auto) {
        this.autoPopulateSeriesOutlinePaint = auto;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[163]++;
    }

    // STROKE
    
    /**
     * Returns the stroke used to draw data items.
     * <p>
     * The default implementation passes control to the getSeriesStroke method.
     * You can override this method if you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getItemStroke(int row, int column) {
        return lookupSeriesStroke(row);
    }

    /**
     * Returns the stroke used to draw the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Stroke lookupSeriesStroke(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[164]++;
int CodeCoverConditionCoverageHelper_C34;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[67]++;
            return this.stroke;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[68]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[165]++;

        // otherwise look up the paint table
        Stroke result = getSeriesStroke(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[166]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.autoPopulateSeriesStroke) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[69]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[167]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[168]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[71]++;
                result = supplier.getNextStroke();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[169]++;
                setSeriesStroke(series, result, false);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[170]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[72]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[70]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[171]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[73]++;
            result = this.baseStroke;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[172]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[74]++;}
        return result;

    }
    
    /**
     * Sets the stroke for ALL series and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesStroke(int, Stroke)} 
     *     and {@link #setBaseStroke(Stroke)}.
     */
    public void setStroke(Stroke stroke) {
        setStroke(stroke, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[173]++;
    }
    
    /**
     * Sets the stroke for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesStroke(int, Stroke, 
     *     boolean)} and {@link #setBaseStroke(Stroke, boolean)}.
     */
    public void setStroke(Stroke stroke, boolean notify) {
        this.stroke = stroke;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[174]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[175]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[75]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[176]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[76]++;}
    }    

    /**
     * Returns the stroke used to draw the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setSeriesStroke(int, Stroke)
     */
    public Stroke getSeriesStroke(int series) {
        return this.strokeList.getStroke(series);
    }
    
    /**
     * Sets the stroke used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getSeriesStroke(int)
     */
    public void setSeriesStroke(int series, Stroke stroke) {
        setSeriesStroke(series, stroke, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[177]++;
    }
    
    /**
     * Sets the stroke for a series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param stroke  the stroke (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesStroke(int)
     */
    public void setSeriesStroke(int series, Stroke stroke, boolean notify) {
        this.strokeList.setStroke(series, stroke);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[178]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[179]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[77]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[180]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[78]++;}
    }    

    /**
     * Returns the base stroke.
     *
     * @return The base stroke (never <code>null</code>).
     * 
     * @see #setBaseStroke(Stroke)
     */
    public Stroke getBaseStroke() {
        return this.baseStroke;
    }

    /**
     * Sets the base stroke and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getBaseStroke()
     */
    public void setBaseStroke(Stroke stroke) {
        // defer argument checking...
        setBaseStroke(stroke, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[181]++;
    }

    /**
     * Sets the base stroke and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #getBaseStroke()
     */
    public void setBaseStroke(Stroke stroke, boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[182]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[79]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[80]++;}
        this.baseStroke = stroke;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[183]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[184]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[81]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[185]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[82]++;}
    }    

    /**
     * Returns the flag that controls whether or not the series stroke list is
     * automatically populated when {@link #lookupSeriesStroke(int)} is called.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setAutoPopulateSeriesStroke(boolean)
     */
    public boolean getAutoPopulateSeriesStroke() {
        return this.autoPopulateSeriesStroke;
    }
    
    /**
     * Sets the flag that controls whether or not the series stroke list is
     * automatically populated when {@link #lookupSeriesStroke(int)} is called.
     * 
     * @param auto  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #getAutoPopulateSeriesStroke()
     */
    public void setAutoPopulateSeriesStroke(boolean auto) {
        this.autoPopulateSeriesStroke = auto;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[186]++;
    }

    // OUTLINE STROKE 
    
    /**
     * Returns the stroke used to outline data items.  The default 
     * implementation passes control to the 
     * {@link #lookupSeriesOutlineStroke(int)} method. You can override this 
     * method if you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getItemOutlineStroke(int row, int column) {
        return lookupSeriesOutlineStroke(row);
    }

    /**
     * Returns the stroke used to outline the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Stroke lookupSeriesOutlineStroke(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[187]++;
int CodeCoverConditionCoverageHelper_C42;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[83]++;
            return this.outlineStroke;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[84]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[188]++;

        // otherwise look up the stroke table
        Stroke result = getSeriesOutlineStroke(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[189]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.autoPopulateSeriesOutlineStroke) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[85]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[190]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[191]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[87]++;
                result = supplier.getNextOutlineStroke();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[192]++;
                setSeriesOutlineStroke(series, result, false);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[193]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[88]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[86]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[194]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[89]++;
            result = this.baseOutlineStroke;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[195]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[90]++;}
        return result;

    }

    /**
     * Sets the outline stroke for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesOutlineStroke(int, 
     *     Stroke)} and {@link #setBaseOutlineStroke(Stroke)}.
     */
    public void setOutlineStroke(Stroke stroke) {
        setOutlineStroke(stroke, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[196]++;
    }

    /**
     * Sets the outline stroke for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesOutlineStroke(int, 
     *     Stroke, boolean)} and {@link #setBaseOutlineStroke(Stroke, boolean)}.
     */
    public void setOutlineStroke(Stroke stroke, boolean notify) {
        this.outlineStroke = stroke;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[197]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[198]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[91]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[199]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[92]++;}
    }
    
    /**
     * Returns the stroke used to outline the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setSeriesOutlineStroke(int, Stroke)
     */
    public Stroke getSeriesOutlineStroke(int series) {
        return this.outlineStrokeList.getStroke(series);
    }
    
    /**
     * Sets the outline stroke used for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getSeriesOutlineStroke(int)
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke) {
        setSeriesOutlineStroke(series, stroke, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[200]++;
    }

    /**
     * Sets the outline stroke for a series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index.
     * @param stroke  the stroke (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesOutlineStroke(int)
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke, 
                                       boolean notify) {
        this.outlineStrokeList.setStroke(series, stroke);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[201]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[202]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[93]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[203]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[94]++;}
    }
    
    /**
     * Returns the base outline stroke.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setBaseOutlineStroke(Stroke)
     */
    public Stroke getBaseOutlineStroke() {
        return this.baseOutlineStroke;
    }

    /**
     * Sets the base outline stroke and sends a {@link RendererChangeEvent} to 
     * all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getBaseOutlineStroke()
     */
    public void setBaseOutlineStroke(Stroke stroke) {
        setBaseOutlineStroke(stroke, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[204]++;
    }

    /**
     * Sets the base outline stroke and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getBaseOutlineStroke()
     */
    public void setBaseOutlineStroke(Stroke stroke, boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[205]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[95]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[96]++;}
        this.baseOutlineStroke = stroke;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[206]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[207]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[97]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[208]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[98]++;}
    }
    
    /**
     * Returns the flag that controls whether or not the series outline stroke 
     * list is automatically populated when 
     * {@link #lookupSeriesOutlineStroke(int)} is called.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setAutoPopulateSeriesOutlineStroke(boolean)
     */
    public boolean getAutoPopulateSeriesOutlineStroke() {
        return this.autoPopulateSeriesOutlineStroke;
    }
    
    /**
     * Sets the flag that controls whether or not the series outline stroke list
     * is automatically populated when {@link #lookupSeriesOutlineStroke(int)} 
     * is called.
     * 
     * @param auto  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #getAutoPopulateSeriesOutlineStroke()
     */
    public void setAutoPopulateSeriesOutlineStroke(boolean auto) {
        this.autoPopulateSeriesOutlineStroke = auto;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[209]++;
    }

    // SHAPE
    
    /**
     * Returns a shape used to represent a data item.
     * <p>
     * The default implementation passes control to the getSeriesShape method.
     * You can override this method if you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The shape (never <code>null</code>).
     */
    public Shape getItemShape(int row, int column) {
        return lookupSeriesShape(row);
    }

    /**
     * Returns a shape used to represent the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The shape (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Shape lookupSeriesShape(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[210]++;
int CodeCoverConditionCoverageHelper_C50;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.shape != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[99]++;
            return this.shape;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[100]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[211]++;

        // otherwise look up the shape list
        Shape result = getSeriesShape(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[212]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.autoPopulateSeriesShape) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[101]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[213]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[214]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[103]++;
                result = supplier.getNextShape();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[215]++;
                setSeriesShape(series, result, false);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[216]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[104]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[102]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[217]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[105]++;
            result = this.baseShape;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[218]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[106]++;}
        return result;

    }

    /**
     * Sets the shape for ALL series (optional) and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param shape  the shape (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesShape(int, Shape)} 
     *     and {@link #setBaseShape(Shape)}.
     */
    public void setShape(Shape shape) {
        setShape(shape, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[219]++;
    }
    
    /**
     * Sets the shape for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param shape  the shape (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesShape(int, Shape, 
     *     boolean)} and {@link #setBaseShape(Shape, boolean)}.
     */
    public void setShape(Shape shape, boolean notify) {
        this.shape = shape;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[220]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[221]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[107]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[222]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[108]++;}
    }
    
    /**
     * Returns a shape used to represent the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The shape (possibly <code>null</code>).
     * 
     * @see #setSeriesShape(int, Shape)
     */
    public Shape getSeriesShape(int series) {
        return this.shapeList.getShape(series);
    }

    /**
     * Sets the shape used for a series and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param shape  the shape (<code>null</code> permitted).
     * 
     * @see #getSeriesShape(int)
     */
    public void setSeriesShape(int series, Shape shape) {
        setSeriesShape(series, shape, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[223]++;
    }

    /**
     * Sets the shape for a series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero based).
     * @param shape  the shape (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesShape(int)
     */
    public void setSeriesShape(int series, Shape shape, boolean notify) {
        this.shapeList.setShape(series, shape);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[224]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[225]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[109]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[226]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[110]++;}
    }
    
    /**
     * Returns the base shape.
     *
     * @return The shape (never <code>null</code>).
     * 
     * @see #setBaseShape(Shape)
     */
    public Shape getBaseShape() {
        return this.baseShape;
    }

    /**
     * Sets the base shape and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     *
     * @param shape  the shape (<code>null</code> not permitted).
     * 
     * @see #getBaseShape()
     */
    public void setBaseShape(Shape shape) {
        // defer argument checking...
        setBaseShape(shape, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[227]++;
    }

    /**
     * Sets the base shape and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param shape  the shape (<code>null</code> not permitted). 
     * @param notify  notify listeners?
     * 
     * @see #getBaseShape()
     */
    public void setBaseShape(Shape shape, boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[228]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[111]++;
            throw new IllegalArgumentException("Null 'shape' argument.");
 
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[112]++;}
        this.baseShape = shape;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[229]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[230]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[113]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[231]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[114]++;}
    }
    
    /**
     * Returns the flag that controls whether or not the series shape list is
     * automatically populated when {@link #lookupSeriesShape(int)} is called.
     * 
     * @return A boolean.
     * 
     * @since 1.0.6
     * 
     * @see #setAutoPopulateSeriesShape(boolean)
     */
    public boolean getAutoPopulateSeriesShape() {
        return this.autoPopulateSeriesShape;
    }
    
    /**
     * Sets the flag that controls whether or not the series shape list is
     * automatically populated when {@link #lookupSeriesShape(int)} is called.
     * 
     * @param auto  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #getAutoPopulateSeriesShape()
     */
    public void setAutoPopulateSeriesShape(boolean auto) {
        this.autoPopulateSeriesShape = auto;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[232]++;
    }

    // ITEM LABEL VISIBILITY...

    /**
     * Returns <code>true</code> if an item label is visible, and 
     * <code>false</code> otherwise.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return A boolean.
     */
    public boolean isItemLabelVisible(int row, int column) {
        return isSeriesItemLabelsVisible(row);
    }

    /**
     * Returns <code>true</code> if the item labels for a series are visible, 
     * and <code>false</code> otherwise.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return A boolean.
     */    
    public boolean isSeriesItemLabelsVisible(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[233]++;
int CodeCoverConditionCoverageHelper_C58;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.itemLabelsVisible != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[115]++;
            return this.itemLabelsVisible.booleanValue();

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[116]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[234]++;

        // otherwise look up the boolean table
        Boolean b = this.itemLabelsVisibleList.getBoolean(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[235]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[117]++;
            b = this.baseItemLabelsVisible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[236]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[118]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[237]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[119]++;
            b = Boolean.FALSE;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[238]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[120]++;}
        return b.booleanValue();

    }
    
    /**
     * Sets the visibility of the item labels for ALL series.
     * 
     * @param visible  the flag.
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelsVisible(int, 
     *     Boolean)} and {@link #setBaseItemLabelsVisible(boolean)}.
     */
    public void setItemLabelsVisible(boolean visible) {        
        setItemLabelsVisible(BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[239]++;
        // The following alternative is only supported in JDK 1.4 - we support 
        // JDK 1.3.1 onwards
        // setItemLabelsVisible(Boolean.valueOf(visible));
    }
    
    /**
     * Sets the visibility of the item labels for ALL series (optional).
     * 
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelsVisible(int, 
     *     Boolean)} and {@link #setBaseItemLabelsVisible(boolean)}.
     */
    public void setItemLabelsVisible(Boolean visible) {
        setItemLabelsVisible(visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[240]++;
    }
    
    /**
     * Sets the visibility of item labels for ALL series and, if requested, 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  a flag that controls whether or not the item labels are 
     *                 visible (<code>null</code> permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelsVisible(int, 
     *     Boolean, boolean)} and {@link #setBaseItemLabelsVisible(Boolean, 
     *     boolean)}.
     */
    public void setItemLabelsVisible(Boolean visible, boolean notify) {
        this.itemLabelsVisible = visible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[241]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[242]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[121]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[243]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[122]++;}
    }

    /**
     * Sets a flag that controls the visibility of the item labels for a series,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the flag.
     */
    public void setSeriesItemLabelsVisible(int series, boolean visible) {
        setSeriesItemLabelsVisible(series, BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[244]++;
    }
    
    /**
     * Sets the visibility of the item labels for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the flag (<code>null</code> permitted).
     */
    public void setSeriesItemLabelsVisible(int series, Boolean visible) {
        setSeriesItemLabelsVisible(series, visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[245]++;
    }

    /**
     * Sets the visibility of item labels for a series and, if requested, sends 
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the visible flag.
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     */
    public void setSeriesItemLabelsVisible(int series, Boolean visible, 
                                           boolean notify) {
        this.itemLabelsVisibleList.setBoolean(series, visible);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[246]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[247]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[123]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[248]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[124]++;}
    }

    /**
     * Returns the base setting for item label visibility.  A <code>null</code>
     * result should be interpreted as equivalent to <code>Boolean.FALSE</code>.
     * 
     * @return A flag (possibly <code>null</code>).
     * 
     * @see #setBaseItemLabelsVisible(boolean)
     */
    public Boolean getBaseItemLabelsVisible() {
        // this should have been defined as a boolean primitive, because 
        // allowing null values is a nuisance...but it is part of the final
        // API now, so we'll have to support it.
        return this.baseItemLabelsVisible;
    }

    /**
     * Sets the base flag that controls whether or not item labels are visible,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #getBaseItemLabelsVisible()
     */
    public void setBaseItemLabelsVisible(boolean visible) {
        setBaseItemLabelsVisible(BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[249]++;
    }
    
    /**
     * Sets the base setting for item label visibility and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag (<code>null</code> is permitted, and viewed
     *     as equivalent to <code>Boolean.FALSE</code>).
     */
    public void setBaseItemLabelsVisible(Boolean visible) {
        setBaseItemLabelsVisible(visible, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[250]++;
    }

    /**
     * Sets the base visibility for item labels and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag (<code>null</code> is permitted, and viewed
     *     as equivalent to <code>Boolean.FALSE</code>).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getBaseItemLabelsVisible()
     */
    public void setBaseItemLabelsVisible(Boolean visible, boolean notify) {
        this.baseItemLabelsVisible = visible;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[251]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[252]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[125]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[253]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[126]++;}
    }

    //// ITEM LABEL FONT //////////////////////////////////////////////////////

    /**
     * Returns the font for an item label.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The font (never <code>null</code>).
     */
    public Font getItemLabelFont(int row, int column) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[254]++;
        Font result = this.itemLabelFont;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[255]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[127]++;
            result = getSeriesItemLabelFont(row);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[256]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[257]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[129]++;
                result = this.baseItemLabelFont;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[258]++;
   
            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[130]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[128]++;}
        return result;
    }

    /**
     * Returns the font used for all item labels.  This may be 
     * <code>null</code>, in which case the per series font settings will apply.
     * 
     * @return The font (possibly <code>null</code>).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #getSeriesItemLabelFont(int)} and
     *     {@link #getBaseItemLabelFont()}.
     */
    public Font getItemLabelFont() {
        return this.itemLabelFont;   
    }
    
    /**
     * Sets the item label font for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  You can set 
     * this to <code>null</code> if you prefer to set the font on a per series 
     * basis.
     * 
     * @param font  the font (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelFont(int, 
     *     Font)} and {@link #setBaseItemLabelFont(Font)}.
     */
    public void setItemLabelFont(Font font) {
        setItemLabelFont(font, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[259]++;
    }
    
    /**
     * Sets the item label font for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param font  the font (<code>null</code> permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelFont(int, 
     *     Font, boolean)} and {@link #setBaseItemLabelFont(Font, boolean)}.
     */
    public void setItemLabelFont(Font font, boolean notify) {
        this.itemLabelFont = font;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[260]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[261]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[131]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[262]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[132]++;}
    }

    /**
     * Returns the font for all the item labels in a series.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return The font (possibly <code>null</code>).
     * 
     * @see #setSeriesItemLabelFont(int, Font)
     */
    public Font getSeriesItemLabelFont(int series) {
        return (Font) this.itemLabelFontList.get(series);
    }

    /**
     * Sets the item label font for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  
     * 
     * @param series  the series index (zero-based).
     * @param font  the font (<code>null</code> permitted).
     * 
     * @see #getSeriesItemLabelFont(int)
     */
    public void setSeriesItemLabelFont(int series, Font font) {
        setSeriesItemLabelFont(series, font, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[263]++;
    }

    /**
     * Sets the item label font for a series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero based).
     * @param font  the font (<code>null</code> permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getSeriesItemLabelFont(int)
     */
    public void setSeriesItemLabelFont(int series, Font font, boolean notify) {
        this.itemLabelFontList.set(series, font);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[264]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[265]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[133]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[266]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[134]++;}
    }
    
    /**
     * Returns the base item label font (this is used when no other font 
     * setting is available).
     * 
     * @return The font (<code>never</code> null).
     * 
     * @see #setBaseItemLabelFont(Font)
     */
    public Font getBaseItemLabelFont() {
        return this.baseItemLabelFont;
    }

    /**
     * Sets the base item label font and sends a {@link RendererChangeEvent} to 
     * all registered listeners.  
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getBaseItemLabelFont()
     */
    public void setBaseItemLabelFont(Font font) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[267]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[135]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[136]++;}
        setBaseItemLabelFont(font, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[268]++;
    }

    /**
     * Sets the base item label font and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getBaseItemLabelFont()
     */
    public void setBaseItemLabelFont(Font font, boolean notify) {
        this.baseItemLabelFont = font;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[269]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[270]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[137]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[271]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[138]++;}
    }

    //// ITEM LABEL PAINT  ////////////////////////////////////////////////////

    /**
     * Returns the paint used to draw an item label.
     * 
     * @param row  the row index (zero based).
     * @param column  the column index (zero based).
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getItemLabelPaint(int row, int column) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[272]++;
        Paint result = this.itemLabelPaint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[273]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[139]++;
            result = getSeriesItemLabelPaint(row);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[274]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[275]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[141]++;
                result = this.baseItemLabelPaint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[276]++;
   
            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[142]++;}

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[140]++;}
        return result;
    }
    
    /**
     * Returns the paint used for all item labels.  This may be 
     * <code>null</code>, in which case the per series paint settings will 
     * apply.
     * 
     * @return The paint (possibly <code>null</code>).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #getSeriesItemLabelPaint(int)} 
     *     and {@link #getBaseItemLabelPaint()}.
     */
    public Paint getItemLabelPaint() {
        return this.itemLabelPaint;   
    }

    /**
     * Sets the item label paint for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelPaint(int, 
     *     Paint)} and {@link #setBaseItemLabelPaint(Paint)}.
     */
    public void setItemLabelPaint(Paint paint) {
        setItemLabelPaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[277]++;
    }

    /**
     * Sets the item label paint for ALL series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint.
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelPaint(int, 
     *     Paint, boolean)} and {@link #setBaseItemLabelPaint(Paint, boolean)}.
     */
    public void setItemLabelPaint(Paint paint, boolean notify) {
        this.itemLabelPaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[278]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[279]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[143]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[280]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[144]++;}
    }
    
    /**
     * Returns the paint used to draw the item labels for a series.
     * 
     * @param series  the series index (zero based).
     * 
     * @return The paint (possibly <code>null<code>).
     * 
     * @see #setSeriesItemLabelPaint(int, Paint)
     */
    public Paint getSeriesItemLabelPaint(int series) {
        return this.itemLabelPaintList.getPaint(series);
    }

    /**
     * Sets the item label paint for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series (zero based index).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesItemLabelPaint(int)
     */
    public void setSeriesItemLabelPaint(int series, Paint paint) {
        setSeriesItemLabelPaint(series, paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[281]++;
    }
    
    /**
     * Sets the item label paint for a series and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero based).
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getSeriesItemLabelPaint(int)
     */
    public void setSeriesItemLabelPaint(int series, Paint paint, 
                                        boolean notify) {
        this.itemLabelPaintList.setPaint(series, paint);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[282]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[283]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[145]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[284]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[146]++;}
    }
    
    /**
     * Returns the base item label paint.
     * 
     * @return The paint (never <code>null<code>).
     * 
     * @see #setBaseItemLabelPaint(Paint)
     */
    public Paint getBaseItemLabelPaint() {
        return this.baseItemLabelPaint;
    }

    /**
     * Sets the base item label paint and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseItemLabelPaint()
     */
    public void setBaseItemLabelPaint(Paint paint) {
        // defer argument checking...
        setBaseItemLabelPaint(paint, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[285]++;
    }

    /**
     * Sets the base item label paint and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners..
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getBaseItemLabelPaint()
     */
    public void setBaseItemLabelPaint(Paint paint, boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[286]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[147]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[148]++;}
        this.baseItemLabelPaint = paint;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[287]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[288]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[149]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[289]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[150]++;}
    }
    
    // POSITIVE ITEM LABEL POSITION...

    /**
     * Returns the item label position for positive values.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The item label position (never <code>null</code>).
     * 
     * @see #getNegativeItemLabelPosition(int, int)
     */
    public ItemLabelPosition getPositiveItemLabelPosition(int row, int column) {
        return getSeriesPositiveItemLabelPosition(row);
    }

    /**
     * Returns the item label position for positive values in ALL series.
     * 
     * @return The item label position (possibly <code>null</code>).
     * 
     * @see #setPositiveItemLabelPosition(ItemLabelPosition)
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on 
     *     {@link #getSeriesPositiveItemLabelPosition(int)} 
     *     and {@link #getBasePositiveItemLabelPosition()}.
     */
    public ItemLabelPosition getPositiveItemLabelPosition() {
        return this.positiveItemLabelPosition;
    }

    /**
     * Sets the item label position for positive values in ALL series, and 
     * sends a {@link RendererChangeEvent} to all registered listeners.  You 
     * need to set this to <code>null</code> to expose the settings for 
     * individual series.
     * 
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getPositiveItemLabelPosition()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on 
     *     {@link #setSeriesPositiveItemLabelPosition(int, ItemLabelPosition)} 
     *     and {@link #setBasePositiveItemLabelPosition(ItemLabelPosition)}.
     */
    public void setPositiveItemLabelPosition(ItemLabelPosition position) {
        setPositiveItemLabelPosition(position, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[290]++;
    }
    
    /**
     * Sets the positive item label position for ALL series and (if requested) 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position  the position (<code>null</code> permitted).
     * @param notify  notify registered listeners?
     * 
     * @see #getPositiveItemLabelPosition()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on 
     *     {@link #setSeriesPositiveItemLabelPosition(int, ItemLabelPosition, 
     *     boolean)} and {@link #setBasePositiveItemLabelPosition(
     *     ItemLabelPosition, boolean)}.
     */
    public void setPositiveItemLabelPosition(ItemLabelPosition position, 
                                             boolean notify) {
        this.positiveItemLabelPosition = position;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[291]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[292]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[151]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[293]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[152]++;}
    }

    /**
     * Returns the item label position for all positive values in a series.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return The item label position (never <code>null</code>).
     * 
     * @see #setSeriesPositiveItemLabelPosition(int, ItemLabelPosition)
     */
    public ItemLabelPosition getSeriesPositiveItemLabelPosition(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[294]++;
int CodeCoverConditionCoverageHelper_C77;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((this.positiveItemLabelPosition != null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[153]++;
            return this.positiveItemLabelPosition;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[154]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[295]++;

        // otherwise look up the position table
        ItemLabelPosition position = (ItemLabelPosition) 
            this.positiveItemLabelPositionList.get(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[296]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[155]++;
            position = this.basePositiveItemLabelPosition;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[297]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[156]++;}
        return position;

    }
    
    /**
     * Sets the item label position for all positive values in a series and 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getSeriesPositiveItemLabelPosition(int)
     */
    public void setSeriesPositiveItemLabelPosition(int series, 
                                                   ItemLabelPosition position) {
        setSeriesPositiveItemLabelPosition(series, position, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[298]++;
    }

    /**
     * Sets the item label position for all positive values in a series and (if
     * requested) sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param series  the series index (zero-based).
     * @param position  the position (<code>null</code> permitted).
     * @param notify  notify registered listeners?
     * 
     * @see #getSeriesPositiveItemLabelPosition(int)
     */
    public void setSeriesPositiveItemLabelPosition(int series, 
                                                   ItemLabelPosition position, 
                                                   boolean notify) {
        this.positiveItemLabelPositionList.set(series, position);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[299]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[300]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[157]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[301]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[158]++;}
    }

    /**
     * Returns the base positive item label position.
     * 
     * @return The position (never <code>null</code>).
     * 
     * @see #setBasePositiveItemLabelPosition(ItemLabelPosition)
     */
    public ItemLabelPosition getBasePositiveItemLabelPosition() {
        return this.basePositiveItemLabelPosition;
    }

    /**
     * Sets the base positive item label position.
     * 
     * @param position  the position (<code>null</code> not permitted).
     * 
     * @see #getBasePositiveItemLabelPosition()
     */
    public void setBasePositiveItemLabelPosition(ItemLabelPosition position) {
        // defer argument checking...
        setBasePositiveItemLabelPosition(position, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[302]++;
    }
    
    /**
     * Sets the base positive item label position and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position  the position (<code>null</code> not permitted).
     * @param notify  notify registered listeners?
     * 
     * @see #getBasePositiveItemLabelPosition()
     */
    public void setBasePositiveItemLabelPosition(ItemLabelPosition position, 
                                                 boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[303]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[159]++;
            throw new IllegalArgumentException("Null 'position' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[160]++;}
        this.basePositiveItemLabelPosition = position;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[304]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[305]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[161]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[306]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[162]++;}
    }

    // NEGATIVE ITEM LABEL POSITION...

    /**
     * Returns the item label position for negative values.  This method can be 
     * overridden to provide customisation of the item label position for 
     * individual data items.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column (zero-based).
     * 
     * @return The item label position (never <code>null</code>).
     * 
     * @see #getPositiveItemLabelPosition(int, int)
     */
    public ItemLabelPosition getNegativeItemLabelPosition(int row, int column) {
        return getSeriesNegativeItemLabelPosition(row);
    }

    /**
     * Returns the item label position for negative values in ALL series.
     * 
     * @return The item label position (possibly <code>null</code>).
     * 
     * @see #setNegativeItemLabelPosition(ItemLabelPosition)
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on 
     *     {@link #getSeriesNegativeItemLabelPosition(int)} 
     *     and {@link #getBaseNegativeItemLabelPosition()}.
     */
    public ItemLabelPosition getNegativeItemLabelPosition() {
        return this.negativeItemLabelPosition;
    }

    /**
     * Sets the item label position for negative values in ALL series, and 
     * sends a {@link RendererChangeEvent} to all registered listeners.  You 
     * need to set this to <code>null</code> to expose the settings for 
     * individual series.
     * 
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getNegativeItemLabelPosition()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on 
     *     {@link #setSeriesNegativeItemLabelPosition(int, ItemLabelPosition)} 
     *     and {@link #setBaseNegativeItemLabelPosition(ItemLabelPosition)}.
     */
    public void setNegativeItemLabelPosition(ItemLabelPosition position) {
        setNegativeItemLabelPosition(position, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[307]++;
    }
    
    /**
     * Sets the item label position for negative values in ALL series and (if 
     * requested) sends a {@link RendererChangeEvent} to all registered 
     * listeners.  
     * 
     * @param position  the position (<code>null</code> permitted).
     * @param notify  notify registered listeners?
     * 
     * @see #getNegativeItemLabelPosition()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on 
     *     {@link #setSeriesNegativeItemLabelPosition(int, ItemLabelPosition, 
     *     boolean)} and {@link #setBaseNegativeItemLabelPosition(
     *     ItemLabelPosition, boolean)}.
     */
    public void setNegativeItemLabelPosition(ItemLabelPosition position, 
                                             boolean notify) {
        this.negativeItemLabelPosition = position;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[308]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[309]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[163]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[310]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[164]++;}
    }

    /**
     * Returns the item label position for all negative values in a series.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return The item label position (never <code>null</code>).
     * 
     * @see #setSeriesNegativeItemLabelPosition(int, ItemLabelPosition)
     */
    public ItemLabelPosition getSeriesNegativeItemLabelPosition(int series) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[311]++;
int CodeCoverConditionCoverageHelper_C83;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((this.negativeItemLabelPosition != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[165]++;
            return this.negativeItemLabelPosition;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[166]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[312]++;

        // otherwise look up the position list
        ItemLabelPosition position = (ItemLabelPosition) 
            this.negativeItemLabelPositionList.get(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[313]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[167]++;
            position = this.baseNegativeItemLabelPosition;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[314]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[168]++;}
        return position;

    }

    /**
     * Sets the item label position for negative values in a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getSeriesNegativeItemLabelPosition(int)
     */
    public void setSeriesNegativeItemLabelPosition(int series, 
                                                   ItemLabelPosition position) {
        setSeriesNegativeItemLabelPosition(series, position, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[315]++;
    }

    /**
     * Sets the item label position for negative values in a series and (if 
     * requested) sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param series  the series index (zero-based).
     * @param position  the position (<code>null</code> permitted).
     * @param notify  notify registered listeners?
     * 
     * @see #getSeriesNegativeItemLabelPosition(int)
     */
    public void setSeriesNegativeItemLabelPosition(int series, 
                                                   ItemLabelPosition position, 
                                                   boolean notify) {
        this.negativeItemLabelPositionList.set(series, position);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[316]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[317]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[169]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[318]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[170]++;}
    }

    /**
     * Returns the base item label position for negative values.
     * 
     * @return The position (never <code>null</code>).
     * 
     * @see #setBaseNegativeItemLabelPosition(ItemLabelPosition)
     */
    public ItemLabelPosition getBaseNegativeItemLabelPosition() {
        return this.baseNegativeItemLabelPosition;
    }

    /**
     * Sets the base item label position for negative values and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position  the position (<code>null</code> not permitted).
     * 
     * @see #getBaseNegativeItemLabelPosition()
     */
    public void setBaseNegativeItemLabelPosition(ItemLabelPosition position) {
        setBaseNegativeItemLabelPosition(position, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[319]++;
    }
    
    /**
     * Sets the base negative item label position and, if requested, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position  the position (<code>null</code> not permitted).
     * @param notify  notify registered listeners?
     * 
     * @see #getBaseNegativeItemLabelPosition()
     */
    public void setBaseNegativeItemLabelPosition(ItemLabelPosition position, 
                                                 boolean notify) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[320]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[171]++;
            throw new IllegalArgumentException("Null 'position' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[172]++;}
        this.baseNegativeItemLabelPosition = position;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[321]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[322]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[173]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[323]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[174]++;}
    }

    /**
     * Returns the item label anchor offset.
     *
     * @return The offset.
     * 
     * @see #setItemLabelAnchorOffset(double)
     */
    public double getItemLabelAnchorOffset() {
        return this.itemLabelAnchorOffset;
    }

    /**
     * Sets the item label anchor offset.
     *
     * @param offset  the offset.
     * 
     * @see #getItemLabelAnchorOffset()
     */
    public void setItemLabelAnchorOffset(double offset) {
        this.itemLabelAnchorOffset = offset;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[324]++;
        fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[325]++;
    }

    /**
     * Returns a boolean that indicates whether or not the specified item 
     * should have a chart entity created for it.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return A boolean.
     */
    public boolean getItemCreateEntity(int series, int item) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[326]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((this.createEntities != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[175]++;
            return this.createEntities.booleanValue();

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[176]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[327]++;
            Boolean b = getSeriesCreateEntities(series);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[328]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((b != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[177]++;
                return b.booleanValue();

            }
            else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[178]++;
                return this.baseCreateEntities;
            }
        }
    }
    
    /**
     * Returns the flag that controls whether or not chart entities are created 
     * for the items in ALL series.  This flag overrides the per series and 
     * default settings - you must set it to <code>null</code> if you want the
     * other settings to apply.
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #getSeriesCreateEntities(int)} 
     *     and {@link #getBaseCreateEntities()}.
     */
    public Boolean getCreateEntities() {
        return this.createEntities;  
    }
    
    /**
     * Sets the flag that controls whether or not chart entities are created 
     * for the items in ALL series, and sends a {@link RendererChangeEvent} to 
     * all registered listeners.  This flag overrides the per series and 
     * default settings - you must set it to <code>null</code> if you want the
     * other settings to apply.
     * 
     * @param create  the flag (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesCreateEntities(int, 
     *     Boolean)} and {@link #setBaseCreateEntities(boolean)}.
     */
    public void setCreateEntities(Boolean create) {
         setCreateEntities(create, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[329]++;
    }
    
    /**
     * Sets the flag that controls whether or not chart entities are created 
     * for the items in ALL series, and sends a {@link RendererChangeEvent} to 
     * all registered listeners.  This flag overrides the per series and 
     * default settings - you must set it to <code>null</code> if you want the
     * other settings to apply.
     * 
     * @param create  the flag (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelFont(int, 
     *     Font, boolean)} and {@link #setBaseItemLabelFont(Font, boolean)}.
     */
    public void setCreateEntities(Boolean create, boolean notify) {
        this.createEntities = create;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[330]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[331]++;
int CodeCoverConditionCoverageHelper_C90;   
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[179]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[332]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[180]++;}
    }
    
    /**
     * Returns the flag that controls whether entities are created for a
     * series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesCreateEntities(int, Boolean)
     */
    public Boolean getSeriesCreateEntities(int series) {
        return this.createEntitiesList.getBoolean(series);
    }
    
    /**
     * Sets the flag that controls whether entities are created for a series,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param create  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesCreateEntities(int)
     */
    public void setSeriesCreateEntities(int series, Boolean create) {
        setSeriesCreateEntities(series, create, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[333]++;
    }
    
    /**
     * Sets the flag that controls whether entities are created for a series
     * and, if requested, sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param series  the series index.
     * @param create  the flag (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesCreateEntities(int)
     */
    public void setSeriesCreateEntities(int series, Boolean create, 
                                        boolean notify) {
        this.createEntitiesList.setBoolean(series, create);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[334]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[335]++;
int CodeCoverConditionCoverageHelper_C91;       
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[181]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[336]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[182]++;}
    }

    /**
     * Returns the base visibility for all series.
     *
     * @return The base visibility.
     * 
     * @see #setBaseCreateEntities(boolean)
     */
    public boolean getBaseCreateEntities() {
        return this.baseCreateEntities;
    }

    /**
     * Sets the base flag that controls whether entities are created
     * for a series, and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     *
     * @param create  the flag.
     * 
     * @see #getBaseCreateEntities()
     */
    public void setBaseCreateEntities(boolean create) {
        // defer argument checking...
        setBaseCreateEntities(create, true);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[337]++;
    }
    
    /**
     * Sets the base flag that controls whether entities are created and, 
     * if requested, sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param create  the visibility.
     * @param notify  notify listeners?
     * 
     * @see #getBaseCreateEntities()
     */
    public void setBaseCreateEntities(boolean create, boolean notify) {
        this.baseCreateEntities = create;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[338]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[339]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[183]++;
            fireChangeEvent();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[340]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[184]++;}
    }

    /** The adjacent offset. */
    private static final double ADJ = Math.cos(Math.PI / 6.0);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[341]++;
  }
    
    /** The opposite offset. */
    private static final double OPP = Math.sin(Math.PI / 6.0);
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[342]++;
  }
    
    /**
     * Calculates the item label anchor point.
     *
     * @param anchor  the anchor.
     * @param x  the x coordinate.
     * @param y  the y coordinate.
     * @param orientation  the plot orientation.
     *
     * @return The anchor point (never <code>null</code>).
     */
    protected Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor,
            double x, double y, PlotOrientation orientation) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[343]++;
        Point2D result = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[344]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[185]++;
            result = new Point2D.Double(x, y);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[345]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[186]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[346]++;
int CodeCoverConditionCoverageHelper_C94; if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE1) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[187]++;
            result = new Point2D.Double(x + OPP * this.itemLabelAnchorOffset, 
                    y - ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[347]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[188]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[348]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE2) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[189]++;
            result = new Point2D.Double(x + ADJ * this.itemLabelAnchorOffset, 
                    y - OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[349]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[190]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[350]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE3) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[191]++;
            result = new Point2D.Double(x + this.itemLabelAnchorOffset, y);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[351]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[192]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[352]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE4) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[193]++;
            result = new Point2D.Double(x + ADJ * this.itemLabelAnchorOffset, 
                    y + OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[353]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[194]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[354]++;
int CodeCoverConditionCoverageHelper_C98; if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE5) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[195]++;
            result = new Point2D.Double(x + OPP * this.itemLabelAnchorOffset, 
                    y + ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[355]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[196]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[356]++;
int CodeCoverConditionCoverageHelper_C99; if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE6) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[197]++;
            result = new Point2D.Double(x, y + this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[357]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[198]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[358]++;
int CodeCoverConditionCoverageHelper_C100; if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE7) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[199]++;
            result = new Point2D.Double(x - OPP * this.itemLabelAnchorOffset, 
                    y + ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[359]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[200]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[360]++;
int CodeCoverConditionCoverageHelper_C101; if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE8) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[201]++;
            result = new Point2D.Double(x - ADJ * this.itemLabelAnchorOffset, 
                    y + OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[361]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[202]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[362]++;
int CodeCoverConditionCoverageHelper_C102; if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE9) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[203]++;
            result = new Point2D.Double(x - this.itemLabelAnchorOffset, y);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[363]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[204]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[364]++;
int CodeCoverConditionCoverageHelper_C103; if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE10) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[205]++;
            result = new Point2D.Double(x - ADJ * this.itemLabelAnchorOffset, 
                    y - OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[365]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[206]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[366]++;
int CodeCoverConditionCoverageHelper_C104; if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE11) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[207]++;
            result = new Point2D.Double(x - OPP * this.itemLabelAnchorOffset, 
                    y - ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[367]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[208]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[368]++;
int CodeCoverConditionCoverageHelper_C105; if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE12) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[209]++;
            result = new Point2D.Double(x, y - this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[369]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[210]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[370]++;
int CodeCoverConditionCoverageHelper_C106; if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE1) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[211]++;
            result = new Point2D.Double(
                    x + 2.0 * OPP * this.itemLabelAnchorOffset, 
                    y - 2.0 * ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[371]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[212]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[372]++;
int CodeCoverConditionCoverageHelper_C107; if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE2) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[213]++;
            result = new Point2D.Double(
                    x + 2.0 * ADJ * this.itemLabelAnchorOffset, 
                    y - 2.0 * OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[373]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[214]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[374]++;
int CodeCoverConditionCoverageHelper_C108; if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE3) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[215]++;
            result = new Point2D.Double(x + 2.0 * this.itemLabelAnchorOffset, 
                    y);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[375]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[216]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[376]++;
int CodeCoverConditionCoverageHelper_C109; if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE4) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[217]++;
            result = new Point2D.Double(
                    x + 2.0 * ADJ * this.itemLabelAnchorOffset, 
                    y + 2.0 * OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[377]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[218]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[378]++;
int CodeCoverConditionCoverageHelper_C110; if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE5) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[219]++;
            result = new Point2D.Double(
                    x + 2.0 * OPP * this.itemLabelAnchorOffset, 
                    y + 2.0 * ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[379]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[220]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[380]++;
int CodeCoverConditionCoverageHelper_C111; if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE6) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[221]++;
            result = new Point2D.Double(x, 
                    y + 2.0 * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[381]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[222]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[382]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE7) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[223]++;
            result = new Point2D.Double(
                    x - 2.0 * OPP * this.itemLabelAnchorOffset, 
                    y + 2.0 * ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[383]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[224]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[384]++;
int CodeCoverConditionCoverageHelper_C113; if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE8) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[225]++;
            result = new Point2D.Double(
                    x - 2.0 * ADJ * this.itemLabelAnchorOffset, 
                    y + 2.0 * OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[385]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[226]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[386]++;
int CodeCoverConditionCoverageHelper_C114; if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE9) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[227]++;
            result = new Point2D.Double(x - 2.0 * this.itemLabelAnchorOffset, 
                    y);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[387]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[228]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[388]++;
int CodeCoverConditionCoverageHelper_C115; if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE10) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[229]++;
            result = new Point2D.Double(
                    x - 2.0 * ADJ * this.itemLabelAnchorOffset, 
                    y - 2.0 * OPP * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[389]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[230]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[390]++;
int CodeCoverConditionCoverageHelper_C116; if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE11) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[231]++;
            result = new Point2D.Double(
                x - 2.0 * OPP * this.itemLabelAnchorOffset, 
                y - 2.0 * ADJ * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[391]++;

        }
        else {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[232]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[392]++;
int CodeCoverConditionCoverageHelper_C117; if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE12) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[233]++;
            result = new Point2D.Double(x, 
                    y - 2.0 * this.itemLabelAnchorOffset);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[393]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[234]++;}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
        return result;
    }
    
    /**
     * Registers an object to receive notification of changes to the renderer.
     *
     * @param listener  the listener (<code>null</code> not permitted).
     * 
     * @see #removeChangeListener(RendererChangeListener)
     */
    public void addChangeListener(RendererChangeListener listener) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[394]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[235]++;
            throw new IllegalArgumentException("Null 'listener' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[236]++;}
        this.listenerList.add(RendererChangeListener.class, listener);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[395]++;
    }

    /**
     * Deregisters an object so that it no longer receives 
     * notification of changes to the renderer.
     *
     * @param listener  the object (<code>null</code> not permitted).
     * 
     * @see #addChangeListener(RendererChangeListener)
     */
    public void removeChangeListener(RendererChangeListener listener) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[396]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[237]++;
            throw new IllegalArgumentException("Null 'listener' argument.");
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[238]++;}
        this.listenerList.remove(RendererChangeListener.class, listener);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[397]++;
    }

    /**
     * Returns <code>true</code> if the specified object is registered with
     * the dataset as a listener.  Most applications won't need to call this 
     * method, it exists mainly for use by unit testing code.
     * 
     * @param listener  the listener.
     * 
     * @return A boolean.
     */
    public boolean hasListener(EventListener listener) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[398]++;
        List list = Arrays.asList(this.listenerList.getListenerList());
        return list.contains(listener);
    }
    
    /**
     * Sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @since 1.0.5
     */
    protected void fireChangeEvent() {
        
        // the commented out code would be better, but only if 
        // RendererChangeEvent is immutable, which it isn't.  See if there is
        // a way to fix this...
        
        //if (this.event == null) {
        //    this.event = new RendererChangeEvent(this);
        //}
        //notifyListeners(this.event);
        
        notifyListeners(new RendererChangeEvent(this));
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[399]++;
    }
    
    /**
     * Notifies all registered listeners that the renderer has been modified.
     *
     * @param event  information about the change event.
     */
    public void notifyListeners(RendererChangeEvent event) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[400]++;
        Object[] ls = this.listenerList.getListenerList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[401]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.loops[1]++;


int CodeCoverConditionCoverageHelper_C120;
        for (int i = ls.length - 2;(((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.loops[1]--;
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.loops[2]--;
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.loops[3]++;
}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[402]++;
int CodeCoverConditionCoverageHelper_C121;
            if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((ls[i] == RendererChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[239]++;
                ((RendererChangeListener) ls[i + 1]).rendererChanged(event);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[403]++;

            } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[240]++;}
        }
    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[404]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[241]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[242]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[405]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractRenderer) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[243]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[244]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[406]++;
        AbstractRenderer that = (AbstractRenderer) obj;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[407]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesVisible, that.seriesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[245]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[246]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[408]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((this.seriesVisibleList.equals(that.seriesVisibleList)) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[247]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[248]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[409]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((this.baseSeriesVisible != that.baseSeriesVisible) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[249]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[250]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[410]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesVisibleInLegend, 
                that.seriesVisibleInLegend)) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[251]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[252]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[411]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((this.seriesVisibleInLegendList.equals(
                that.seriesVisibleInLegendList)) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[253]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[254]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[412]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((this.baseSeriesVisibleInLegend != that.baseSeriesVisibleInLegend) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[255]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[256]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[413]++;
int CodeCoverConditionCoverageHelper_C130;
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[257]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[258]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[414]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.paintList, that.paintList)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[259]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[260]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[415]++;
int CodeCoverConditionCoverageHelper_C132;
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.basePaint, that.basePaint)) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[261]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[262]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[416]++;
int CodeCoverConditionCoverageHelper_C133;
        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[263]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[264]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[417]++;
int CodeCoverConditionCoverageHelper_C134;
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.fillPaintList, that.fillPaintList)) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[265]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[266]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[418]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.baseFillPaint, that.baseFillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[267]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[268]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[419]++;
int CodeCoverConditionCoverageHelper_C136;
        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[269]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[270]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[420]++;
int CodeCoverConditionCoverageHelper_C137;
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlinePaintList,
                that.outlinePaintList)) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[271]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[272]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[421]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.baseOutlinePaint, 
                that.baseOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[273]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[274]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[422]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[275]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[276]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[423]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.strokeList, that.strokeList)) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[277]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[278]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[424]++;
int CodeCoverConditionCoverageHelper_C141;
        if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseStroke, that.baseStroke)) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[279]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[280]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[425]++;
int CodeCoverConditionCoverageHelper_C142;
        if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[281]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[282]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[426]++;
int CodeCoverConditionCoverageHelper_C143;
        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStrokeList, 
                that.outlineStrokeList)) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[283]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[284]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[427]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.baseOutlineStroke, that.baseOutlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)
        ) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[285]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[286]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[428]++;
int CodeCoverConditionCoverageHelper_C145;
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shape, that.shape)) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[287]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[288]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[429]++;
int CodeCoverConditionCoverageHelper_C146;
        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapeList, that.shapeList)) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[289]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[290]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[430]++;
int CodeCoverConditionCoverageHelper_C147;
        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseShape, that.baseShape)) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[291]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[292]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[431]++;
int CodeCoverConditionCoverageHelper_C148;
        if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelsVisible, 
                that.itemLabelsVisible)) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[293]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[294]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[432]++;
int CodeCoverConditionCoverageHelper_C149;
        if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelsVisibleList, 
                that.itemLabelsVisibleList)) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[295]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[296]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[433]++;
int CodeCoverConditionCoverageHelper_C150;
        if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseItemLabelsVisible, 
                that.baseItemLabelsVisible)) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[297]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[298]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[434]++;
int CodeCoverConditionCoverageHelper_C151;
        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelFont, that.itemLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[299]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[300]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[435]++;
int CodeCoverConditionCoverageHelper_C152;
        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelFontList, 
                that.itemLabelFontList)) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[301]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[302]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[436]++;
int CodeCoverConditionCoverageHelper_C153;
        if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseItemLabelFont, 
                that.baseItemLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[303]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[304]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[437]++;
int CodeCoverConditionCoverageHelper_C154;
 
        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.itemLabelPaint, that.itemLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[305]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[306]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[438]++;
int CodeCoverConditionCoverageHelper_C155;
        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelPaintList, 
                that.itemLabelPaintList)) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[307]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[308]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[439]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.baseItemLabelPaint, 
                that.baseItemLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[309]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[310]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[440]++;
int CodeCoverConditionCoverageHelper_C157;

        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.positiveItemLabelPosition, 
                that.positiveItemLabelPosition)) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[311]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[312]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[441]++;
int CodeCoverConditionCoverageHelper_C158;
        if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.positiveItemLabelPositionList, 
                that.positiveItemLabelPositionList)) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[313]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[314]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[442]++;
int CodeCoverConditionCoverageHelper_C159;
        if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.basePositiveItemLabelPosition, 
                that.basePositiveItemLabelPosition)) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[315]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[316]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[443]++;
int CodeCoverConditionCoverageHelper_C160;

        if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.negativeItemLabelPosition, 
                that.negativeItemLabelPosition)) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[317]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[318]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[444]++;
int CodeCoverConditionCoverageHelper_C161;
        if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.negativeItemLabelPositionList, 
                that.negativeItemLabelPositionList)) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[319]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[320]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[445]++;
int CodeCoverConditionCoverageHelper_C162;
        if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseNegativeItemLabelPosition, 
                that.baseNegativeItemLabelPosition)) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[321]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[322]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[446]++;
int CodeCoverConditionCoverageHelper_C163;
        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((this.itemLabelAnchorOffset != that.itemLabelAnchorOffset) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[323]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[324]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[447]++;
int CodeCoverConditionCoverageHelper_C164;
        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.createEntities, that.createEntities)) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[325]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[326]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[448]++;
int CodeCoverConditionCoverageHelper_C165;
        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.createEntitiesList, 
                that.createEntitiesList)) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[327]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[328]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[449]++;
int CodeCoverConditionCoverageHelper_C166;
        if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((this.baseCreateEntities != that.baseCreateEntities) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[329]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[330]++;}
        return true;
    }
    
    /**
     * Returns a hashcode for the renderer.
     * 
     * @return The hashcode.
     */
    public int hashCode() {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[450]++;
        int result = 193;   
        result = 37 * result + ObjectUtilities.hashCode(this.stroke);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[451]++;     
        result = 37 * result + ObjectUtilities.hashCode(this.baseStroke);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[452]++;    
        result = 37 * result + ObjectUtilities.hashCode(this.outlineStroke);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[453]++;
        result = 37 * result + ObjectUtilities.hashCode(this.baseOutlineStroke);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[454]++;
        return result;
    }
    
    /**
     * Returns an independent copy of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the renderer 
     *         does not support cloning.
     */
    protected Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[455]++;
        AbstractRenderer clone = (AbstractRenderer) super.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[456]++;
int CodeCoverConditionCoverageHelper_C167;
        
        if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((this.seriesVisibleList != null) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[331]++;
            clone.seriesVisibleList 
                    = (BooleanList) this.seriesVisibleList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[457]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[332]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[458]++;
int CodeCoverConditionCoverageHelper_C168;
        
        if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((this.seriesVisibleInLegendList != null) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[333]++;
            clone.seriesVisibleInLegendList 
                    = (BooleanList) this.seriesVisibleInLegendList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[459]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[334]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[460]++;
int CodeCoverConditionCoverageHelper_C169;

        // 'paint' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((this.paintList != null) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[335]++;
            clone.paintList = (PaintList) this.paintList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[461]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[336]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[462]++;
int CodeCoverConditionCoverageHelper_C170;
        // 'basePaint' : immutable, no need to clone reference
        
        if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((this.fillPaintList != null) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[337]++;
            clone.fillPaintList = (PaintList) this.fillPaintList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[463]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[338]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[464]++;
int CodeCoverConditionCoverageHelper_C171;
        // 'outlinePaint' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((this.outlinePaintList != null) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[339]++;
            clone.outlinePaintList = (PaintList) this.outlinePaintList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[465]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[340]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[466]++;
int CodeCoverConditionCoverageHelper_C172;
        // 'baseOutlinePaint' : immutable, no need to clone reference
        
        // 'stroke' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((this.strokeList != null) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[341]++;
            clone.strokeList = (StrokeList) this.strokeList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[467]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[342]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[468]++;
int CodeCoverConditionCoverageHelper_C173;
        // 'baseStroke' : immutable, no need to clone reference
        
        // 'outlineStroke' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((this.outlineStrokeList != null) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[343]++;
            clone.outlineStrokeList 
                = (StrokeList) this.outlineStrokeList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[469]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[344]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[470]++;
int CodeCoverConditionCoverageHelper_C174;
        // 'baseOutlineStroke' : immutable, no need to clone reference
        
        if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((this.shape != null) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[345]++;
            clone.shape = ShapeUtilities.clone(this.shape);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[471]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[346]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[472]++;
int CodeCoverConditionCoverageHelper_C175;
        if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((this.shapeList != null) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[347]++;
            clone.shapeList = (ShapeList) this.shapeList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[473]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[348]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[474]++;
int CodeCoverConditionCoverageHelper_C176;
        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((this.baseShape != null) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[349]++;
            clone.baseShape = ShapeUtilities.clone(this.baseShape);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[475]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[350]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[476]++;
int CodeCoverConditionCoverageHelper_C177;
        
        // 'itemLabelsVisible' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((this.itemLabelsVisibleList != null) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[351]++;
            clone.itemLabelsVisibleList 
                = (BooleanList) this.itemLabelsVisibleList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[477]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[352]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[478]++;
int CodeCoverConditionCoverageHelper_C178;
        // 'basePaint' : immutable, no need to clone reference
        
        // 'itemLabelFont' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((this.itemLabelFontList != null) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[353]++;
            clone.itemLabelFontList 
                = (ObjectList) this.itemLabelFontList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[479]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[354]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[480]++;
int CodeCoverConditionCoverageHelper_C179;
        // 'baseItemLabelFont' : immutable, no need to clone reference

        // 'itemLabelPaint' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((this.itemLabelPaintList != null) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[355]++;
            clone.itemLabelPaintList 
                = (PaintList) this.itemLabelPaintList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[481]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[356]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[482]++;
int CodeCoverConditionCoverageHelper_C180;
        // 'baseItemLabelPaint' : immutable, no need to clone reference
        
        // 'postiveItemLabelAnchor' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((this.positiveItemLabelPositionList != null) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[357]++;
            clone.positiveItemLabelPositionList 
                = (ObjectList) this.positiveItemLabelPositionList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[483]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[358]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[484]++;
int CodeCoverConditionCoverageHelper_C181;
        // 'baseItemLabelAnchor' : immutable, no need to clone reference

        // 'negativeItemLabelAnchor' : immutable, no need to clone reference
        if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((this.negativeItemLabelPositionList != null) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[359]++;
            clone.negativeItemLabelPositionList 
                = (ObjectList) this.negativeItemLabelPositionList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[485]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[360]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[486]++;
int CodeCoverConditionCoverageHelper_C182;
        // 'baseNegativeItemLabelAnchor' : immutable, no need to clone reference
        
        if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((this.createEntitiesList != null) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[361]++;
            clone.createEntitiesList 
                    = (BooleanList) this.createEntitiesList.clone();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[487]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.branches[362]++;}
        clone.listenerList = new EventListenerList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[488]++;
        clone.event = null;
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[489]++;
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
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[490]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[491]++;
        SerialUtilities.writePaint(this.basePaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[492]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[493]++;
        SerialUtilities.writePaint(this.baseFillPaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[494]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[495]++;
        SerialUtilities.writePaint(this.baseOutlinePaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[496]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[497]++;
        SerialUtilities.writeStroke(this.baseStroke, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[498]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[499]++;
        SerialUtilities.writeStroke(this.baseOutlineStroke, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[500]++;
        SerialUtilities.writeShape(this.shape, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[501]++;
        SerialUtilities.writeShape(this.baseShape, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[502]++;
        SerialUtilities.writePaint(this.itemLabelPaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[503]++;
        SerialUtilities.writePaint(this.baseItemLabelPaint, stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[504]++;

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
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[505]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[506]++;
        this.basePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[507]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[508]++;
        this.baseFillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[509]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[510]++;
        this.baseOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[511]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[512]++;
        this.baseStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[513]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[514]++;
        this.baseOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[515]++;
        this.shape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[516]++;
        this.baseShape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[517]++;
        this.itemLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[518]++;
        this.baseItemLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[519]++;
        
        // listeners are not restored automatically, but storage must be 
        // provided...
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p.statements[520]++;

    }

}

class CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p ());
  }
    public static long[] statements = new long[521];
    public static long[] branches = new long[363];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[183];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.AbstractRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 182; i++) {
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

  public CodeCoverCoverageCounter$1ib8k6g9t1mz20larh4urcqpqehexcw2p () {
    super("org.jfree.chart.renderer.AbstractRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 520; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 362; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 182; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.AbstractRenderer.java");
      for (int i = 1; i <= 520; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 362; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 182; i++) {
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

