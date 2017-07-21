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
 * LegendTitle.java
 * ----------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Pierre-Marie Le Biot;
 *
 * Changes
 * -------
 * 25-Nov-2004 : First working version (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint class (DG);
 * 11-Feb-2005 : Implemented PublicCloneable (DG);
 * 23-Feb-2005 : Replaced chart reference with LegendItemSource (DG);
 * 16-Mar-2005 : Added itemFont attribute (DG);
 * 17-Mar-2005 : Fixed missing fillShape setting (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 03-May-2005 : Modified equals() method to ignore sources (DG);
 * 13-May-2005 : Added settings for legend item label and graphic padding (DG);
 * 09-Jun-2005 : Fixed serialization bug (DG);
 * 01-Sep-2005 : Added itemPaint attribute (PMLB);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Use new LegendItemBlockContainer to restore support for
 *               LegendItemEntities (DG);
 * 06-Oct-2006 : Add tooltip and URL text to legend item (DG);
 * 13-Dec-2006 : Added support for GradientPaint in legend items (DG);
 * 16-Mar-2007 : Updated border drawing for changes in AbstractBlock (DG);
 * 18-May-2007 : Pass seriesKey and dataset to legend item block (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.block.Arrangement;
import org.jfree.chart.block.Block;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BlockFrame;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.CenterArrangement;
import org.jfree.chart.block.ColumnArrangement;
import org.jfree.chart.block.FlowArrangement;
import org.jfree.chart.block.LabelBlock;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A chart title that displays a legend for the data in the chart.
 * <P>
 * The title can be populated with legend items manually, or you can assign a
 * reference to the plot, in which case the legend items will be automatically
 * created to match the dataset(s).
 */
public class LegendTitle extends Title 
                         implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2644010518533854633L;
  static {
    CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[1]++;
  }
    
    /** The default item font. */
    public static final Font DEFAULT_ITEM_FONT 
        = new Font("SansSerif", Font.PLAIN, 12);
  static {
    CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[2]++;
  }

    /** The default item paint. */
    public static final Paint DEFAULT_ITEM_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[3]++;
  }

    /** The sources for legend items. */
    private LegendItemSource[] sources;
    
    /** The background paint (possibly <code>null</code>). */
    private transient Paint backgroundPaint;
    
    /** The edge for the legend item graphic relative to the text. */
    private RectangleEdge legendItemGraphicEdge;
    
    /** The anchor point for the legend item graphic. */
    private RectangleAnchor legendItemGraphicAnchor;
    
    /** The legend item graphic location. */
    private RectangleAnchor legendItemGraphicLocation;
    
    /** The padding for the legend item graphic. */
    private RectangleInsets legendItemGraphicPadding;

    /** The item font. */
    private Font itemFont;
    
    /** The item paint. */
    private transient Paint itemPaint;

    /** The padding for the item labels. */
    private RectangleInsets itemLabelPadding;

    /**
     * A container that holds and displays the legend items.
     */
    private BlockContainer items;
    
    /** 
     * The layout for the legend when it is positioned at the top or bottom
     * of the chart.
     */
    private Arrangement hLayout;
    
    /** 
     * The layout for the legend when it is positioned at the left or right
     * of the chart.
     */
    private Arrangement vLayout;
    
    /** 
     * An optional container for wrapping the legend items (allows for adding
     * a title or other text to the legend). 
     */
    private BlockContainer wrapper;

    /**
     * Constructs a new (empty) legend for the specified source.
     * 
     * @param source  the source.
     */
    public LegendTitle(LegendItemSource source) {
        this(source, new FlowArrangement(), new ColumnArrangement());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[4]++;
    }
    
    /**
     * Creates a new legend title with the specified arrangement.
     * 
     * @param source  the source.
     * @param hLayout  the horizontal item arrangement (<code>null</code> not
     *                 permitted).
     * @param vLayout  the vertical item arrangement (<code>null</code> not
     *                 permitted).
     */
    public LegendTitle(LegendItemSource source, 
                       Arrangement hLayout, Arrangement vLayout) {
        this.sources = new LegendItemSource[] {source};
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[5]++;
        this.items = new BlockContainer(hLayout);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[6]++;
        this.hLayout = hLayout;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[7]++;
        this.vLayout = vLayout;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[8]++;
        this.backgroundPaint = null;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[9]++;  
        this.legendItemGraphicEdge = RectangleEdge.LEFT;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[10]++;
        this.legendItemGraphicAnchor = RectangleAnchor.CENTER;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[11]++;
        this.legendItemGraphicLocation = RectangleAnchor.CENTER;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[12]++;
        this.legendItemGraphicPadding = new RectangleInsets(2.0, 2.0, 2.0, 2.0);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[13]++;
        this.itemFont = DEFAULT_ITEM_FONT;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[14]++;
        this.itemPaint = DEFAULT_ITEM_PAINT;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[15]++;
        this.itemLabelPadding = new RectangleInsets(2.0, 2.0, 2.0, 2.0);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[16]++;
    }
    
    /**
     * Returns the legend item sources.
     * 
     * @return The sources.
     */
    public LegendItemSource[] getSources() {
        return this.sources;   
    }
    
    /**
     * Sets the legend item sources and sends a {@link TitleChangeEvent} to
     * all registered listeners.
     * 
     * @param sources  the sources (<code>null</code> not permitted).
     */
    public void setSources(LegendItemSource[] sources) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sources == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[1]++;
            throw new IllegalArgumentException("Null 'sources' argument.");
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[2]++;}
        this.sources = sources;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[18]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[19]++;
    }

    /**
     * Returns the background paint.
     * 
     * @return The background paint (possibly <code>null</code>).
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;   
    }
    
    /**
     * Sets the background paint for the legend and sends a 
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     */
    public void setBackgroundPaint(Paint paint) {
        this.backgroundPaint = paint;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[20]++;   
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[21]++;
    }
    
    /**
     * Returns the location of the shape within each legend item. 
     * 
     * @return The location (never <code>null</code>).
     */
    public RectangleEdge getLegendItemGraphicEdge() {
        return this.legendItemGraphicEdge;
    }
    
    /**
     * Sets the location of the shape within each legend item.
     * 
     * @param edge  the edge (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicEdge(RectangleEdge edge) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((edge == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[3]++;
            throw new IllegalArgumentException("Null 'edge' argument.");

        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[4]++;}
        this.legendItemGraphicEdge = edge;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[23]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[24]++;
    }
    
    /**
     * Returns the legend item graphic anchor.
     * 
     * @return The graphic anchor (never <code>null</code>).
     */
    public RectangleAnchor getLegendItemGraphicAnchor() {
        return this.legendItemGraphicAnchor;
    }
    
    /**
     * Sets the anchor point used for the graphic in each legend item.
     * 
     * @param anchor  the anchor point (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicAnchor(RectangleAnchor anchor) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[5]++;
            throw new IllegalArgumentException("Null 'anchor' point.");

        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[6]++;}
        this.legendItemGraphicAnchor = anchor;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[26]++;
    }
    
    /**
     * Returns the legend item graphic location.
     * 
     * @return The location (never <code>null</code>).
     */
    public RectangleAnchor getLegendItemGraphicLocation() {
        return this.legendItemGraphicLocation;
    }
    
    /**
     * Sets the legend item graphic location.
     * 
     * @param anchor  the anchor (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicLocation(RectangleAnchor anchor) {
        this.legendItemGraphicLocation = anchor;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[27]++;
    }
    
    /**
     * Returns the padding that will be applied to each item graphic.
     * 
     * @return The padding (never <code>null</code>).
     */
    public RectangleInsets getLegendItemGraphicPadding() {
        return this.legendItemGraphicPadding;    
    }
    
    /**
     * Sets the padding that will be applied to each item graphic in the 
     * legend and sends a {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param padding  the padding (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicPadding(RectangleInsets padding) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[7]++;
            throw new IllegalArgumentException("Null 'padding' argument.");
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[8]++;}
        this.legendItemGraphicPadding = padding;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[29]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[30]++;
    }
    
    /**
     * Returns the item font.
     * 
     * @return The font (never <code>null</code>).
     */
    public Font getItemFont() {
        return this.itemFont;   
    }
    
    /**
     * Sets the item font and sends a {@link TitleChangeEvent} to
     * all registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     */
    public void setItemFont(Font font) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[9]++;
            throw new IllegalArgumentException("Null 'font' argument.");
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[10]++;}
        this.itemFont = font;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[32]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[33]++;
    }
    
    /**
     * Returns the item paint.
     *
     * @return The paint (never <code>null</code>).
     */
    public Paint getItemPaint() {
        return this.itemPaint;   
    }
   
    /**
     * Sets the item paint.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setItemPaint(Paint paint) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[11]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[12]++;}
        this.itemPaint = paint;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[35]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[36]++;
    }
   
    /**
     * Returns the padding used for the items labels.
     * 
     * @return The padding (never <code>null</code>).
     */
    public RectangleInsets getItemLabelPadding() {
        return this.itemLabelPadding;   
    }
    
    /**
     * Sets the padding used for the item labels in the legend.
     * 
     * @param padding  the padding (<code>null</code> not permitted).
     */
    public void setItemLabelPadding(RectangleInsets padding) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[13]++;
            throw new IllegalArgumentException("Null 'padding' argument.");
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[14]++;}
        this.itemLabelPadding = padding;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[38]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[39]++;
    }
    
    /**
     * Fetches the latest legend items.
     */
    protected void fetchLegendItems() {
        this.items.clear();
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[40]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[41]++;
        RectangleEdge p = getPosition();
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(p)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[15]++;
            this.items.setArrangement(this.hLayout);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[43]++;
   
        }
        else {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[16]++;
            this.items.setArrangement(this.vLayout);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[44]++;   
        }
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[45]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s < this.sources.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[1]--;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[2]--;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[3]++;
}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[46]++;
            LegendItemCollection legendItems = this.sources[s].getLegendItems();
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((legendItems != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[17]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[48]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < legendItems.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[4]--;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[5]--;
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.loops[6]++;
}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[49]++;
                    LegendItem item = legendItems.get(i);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[50]++;
                    Block block = createLegendItemBlock(item);
                    this.items.add(block);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[51]++;
                }

            } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[18]++;}
        }
    }
    
    /**
     * Creates a legend item block.
     * 
     * @param item  the legend item.
     * 
     * @return The block.
     */
    protected Block createLegendItemBlock(LegendItem item) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[52]++;
        BlockContainer result = null;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[53]++;
        LegendGraphic lg = new LegendGraphic(item.getShape(), 
                item.getFillPaint());
        lg.setFillPaintTransformer(item.getFillPaintTransformer());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[54]++;
        lg.setShapeFilled(item.isShapeFilled());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[55]++;
        lg.setLine(item.getLine());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[56]++;
        lg.setLineStroke(item.getLineStroke());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[57]++;
        lg.setLinePaint(item.getLinePaint());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[58]++;
        lg.setLineVisible(item.isLineVisible());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[59]++;
        lg.setShapeVisible(item.isShapeVisible());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[60]++;
        lg.setShapeOutlineVisible(item.isShapeOutlineVisible());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[61]++;
        lg.setOutlinePaint(item.getOutlinePaint());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[62]++;
        lg.setOutlineStroke(item.getOutlineStroke());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[63]++;
        lg.setPadding(this.legendItemGraphicPadding);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[64]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[65]++;

        LegendItemBlockContainer legendItem = new LegendItemBlockContainer(
                new BorderArrangement(), item.getDataset(), 
                item.getSeriesKey());
        lg.setShapeAnchor(getLegendItemGraphicAnchor());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[66]++;
        lg.setShapeLocation(getLegendItemGraphicLocation());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[67]++;
        legendItem.add(lg, this.legendItemGraphicEdge);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[68]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[69]++;
        LabelBlock labelBlock = new LabelBlock(item.getLabel(), this.itemFont, 
                this.itemPaint);
        labelBlock.setPadding(this.itemLabelPadding);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[70]++;
        legendItem.add(labelBlock);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[71]++;
        legendItem.setToolTipText(item.getToolTipText());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[72]++;
        legendItem.setURLText(item.getURLText());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[73]++;
        
        result = new BlockContainer(new CenterArrangement());
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[74]++;
        result.add(legendItem);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[75]++;
        
        return result;
    }
    
    /**
     * Returns the container that holds the legend items.
     * 
     * @return The container for the legend items.
     */
    public BlockContainer getItemContainer() {
        return this.items;
    }

    /**
     * Arranges the contents of the block, within the given constraints, and 
     * returns the block size.
     * 
     * @param g2  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     * 
     * @return The block size (in Java2D units, never <code>null</code>).
     */
    public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[76]++;
        Size2D result = new Size2D();
        fetchLegendItems();
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[77]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[78]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.items.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[19]++;
            return result;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[20]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[79]++;
        BlockContainer container = this.wrapper;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[80]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((container == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[21]++;
            container = this.items;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[81]++;

        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[22]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[82]++;
        RectangleConstraint c = toContentConstraint(constraint);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[83]++;
        Size2D size = container.arrange(g2, c);
        result.height = calculateTotalHeight(size.height);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[84]++;
        result.width = calculateTotalWidth(size.width);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[85]++;
        return result;
    }

    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the available area for the title.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[86]++;
    }

    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     * 
     * @return An {@link org.jfree.chart.block.EntityBlockResult} or 
     *         <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[87]++;
        Rectangle2D target = (Rectangle2D) area.clone();
        target = trimMargin(target);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[88]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[89]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[23]++;
            g2.setPaint(this.backgroundPaint);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[90]++;
            g2.fill(target);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[91]++;

        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[24]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[92]++;
        BlockFrame border = getFrame();
        border.draw(g2, target);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[93]++;
        border.getInsets().trim(target);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[94]++;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[95]++;
        BlockContainer container = this.wrapper;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[96]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((container == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[25]++;
            container = this.items;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[97]++;
 
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[26]++;}
        target = trimPadding(target);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[98]++;
        return container.draw(g2, target, params);   
    }

    /**
     * Sets the wrapper container for the legend.
     * 
     * @param wrapper  the wrapper container.
     */
    public void setWrapper(BlockContainer wrapper) {
        this.wrapper = wrapper;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[99]++;
    }
    
    /**
     * Tests this title for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[100]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[27]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[28]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[101]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj instanceof LegendTitle) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[30]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[102]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[31]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[32]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[103]++;
        LegendTitle that = (LegendTitle) obj;
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[104]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[34]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[105]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.legendItemGraphicEdge != that.legendItemGraphicEdge) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[36]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[106]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.legendItemGraphicAnchor != that.legendItemGraphicAnchor) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[37]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[38]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[107]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.legendItemGraphicLocation != that.legendItemGraphicLocation) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[39]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[40]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[108]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.itemFont.equals(that.itemFont)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[41]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[42]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[109]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.itemPaint.equals(that.itemPaint)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[43]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[44]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[110]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.hLayout.equals(that.hLayout)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[45]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[46]++;}
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[111]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.vLayout.equals(that.vLayout)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[47]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.branches[48]++;}
        return true;
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
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[112]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[113]++;
        SerialUtilities.writePaint(this.itemPaint, stream);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[114]++;
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
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[115]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[116]++;
        this.itemPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9.statements[117]++;
    }

}

class CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9 ());
  }
    public static long[] statements = new long[118];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.LegendTitle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$4itld9nmqkaczifa8l6uqpnq9 () {
    super("org.jfree.chart.title.LegendTitle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 117; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.LegendTitle.java");
      for (int i = 1; i <= 117; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

