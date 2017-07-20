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
 * ---------------
 * LegendItem.java
 * ---------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   David Li;
 *                   Wolfgang Irler;
 *                   Luke Quinane;
 *
 * Changes (from 2-Oct-2002)
 * -------------------------
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Jan-2003 : Dropped outlineStroke attribute (DG);
 * 08-Oct-2003 : Applied patch for displaying series line style, contributed by
 *               Luke Quinane (DG);
 * 21-Jan-2004 : Added the shapeFilled flag (DG);
 * 04-Jun-2004 : Added equals() method, implemented Serializable (DG);
 * 25-Nov-2004 : Changes required by new LegendTitle implementation (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 20-Apr-2005 : Added tooltip and URL text (DG);
 * 28-Nov-2005 : Separated constructors for AttributedString labels (DG);
 * 10-Dec-2005 : Fixed serialization bug (1377239) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Added dataset and series index fields (DG);
 * 13-Dec-2006 : Added fillPaintTransformer attribute (DG);
 * 18-May-2007 : Added dataset and seriesKey fields (DG);
 * 03-Aug-2007 : Fixed null pointer exception (DG);
 *
 */

package org.jfree.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.AttributedString;
import java.text.CharacterIterator;

import org.jfree.data.general.Dataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.AttributedStringUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.ShapeUtilities;

/**
 * A temporary storage object for recording the properties of a legend item, 
 * without any consideration for layout issues. 
 */
public class LegendItem implements Serializable {
  static {
    CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -797214582948827144L;
  static {
    CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[1]++;
  }
    
    /**
     * The dataset.
     * 
     * @since 1.0.6
     */
    private Dataset dataset;
    
    /**
     * The series key.
     * 
     * @since 1.0.6
     */
    private Comparable seriesKey;
    
    /** The dataset index. */
    private int datasetIndex;
    
    /** The series index. */
    private int series;
    
    /** The label. */
    private String label;
    
    /** The attributed label (if null, fall back to the regular label). */
    private transient AttributedString attributedLabel;

    /** 
     * The description (not currently used - could be displayed as a tool tip). 
     */
    private String description;
    
    /** The tool tip text. */
    private String toolTipText;
    
    /** The url text. */
    private String urlText;

    /** A flag that controls whether or not the shape is visible. */
    private boolean shapeVisible;
    
    /** The shape. */
    private transient Shape shape;
    
    /** A flag that controls whether or not the shape is filled. */
    private boolean shapeFilled;

    /** The paint. */
    private transient Paint fillPaint;
    
    /** 
     * A gradient paint transformer. 
     * 
     * @since 1.0.4
     */
    private GradientPaintTransformer fillPaintTransformer;
    
    /** A flag that controls whether or not the shape outline is visible. */
    private boolean shapeOutlineVisible;
    
    /** The outline paint. */
    private transient Paint outlinePaint;
    
    /** The outline stroke. */
    private transient Stroke outlineStroke;

    /** A flag that controls whether or not the line is visible. */
    private boolean lineVisible;
    
    /** The line. */
    private transient Shape line;
    
    /** The stroke. */
    private transient Stroke lineStroke;
    
    /** The line paint. */
    private transient Paint linePaint;

    /**
     * The shape must be non-null for a LegendItem - if no shape is required,
     * use this.
     */
    private static final Shape UNUSED_SHAPE = new Line2D.Float();
  static {
    CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[2]++;
  }
    
    /**
     * The stroke must be non-null for a LegendItem - if no stroke is required,
     * use this.
     */
    private static final Stroke UNUSED_STROKE = new BasicStroke(0.0f);
  static {
    CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[3]++;
  }
    
    /**
     * Creates a legend item with a filled shape.  The shape is not outlined,
     * and no line is visible.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (<code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param shape  the shape (<code>null</code> not permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code>
     *                   not permitted).
     */
    public LegendItem(String label, String description, 
                      String toolTipText, String urlText, 
                      Shape shape, Paint fillPaint) {
        
        this(label, description, toolTipText, urlText, 
                /* shape visible = */ true, shape, 
                /* shape filled = */ true, fillPaint, 
                /* shape outlined */ false, Color.black, UNUSED_STROKE,
                /* line visible */ false, UNUSED_SHAPE, UNUSED_STROKE,
                Color.black);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[4]++;

    }
    
    /**
     * Creates a legend item with a filled and outlined shape.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (<code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param shape  the shape (<code>null</code> not permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code>
     *                   not permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> not 
     *                       permitted).
     * @param outlinePaint  the outline paint (<code>null</code> not 
     *                      permitted).
     */
    public LegendItem(String label, String description, 
                      String toolTipText, String urlText, 
                      Shape shape, Paint fillPaint, 
                      Stroke outlineStroke, Paint outlinePaint) {
        
        this(label, description, toolTipText, urlText,
                /* shape visible = */ true, shape, 
                /* shape filled = */ true, fillPaint, 
                /* shape outlined = */ true, outlinePaint, outlineStroke,
                /* line visible */ false, UNUSED_SHAPE, UNUSED_STROKE,
                Color.black);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[5]++;

    }
    
    /**
     * Creates a legend item using a line.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (<code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param line  the line (<code>null</code> not permitted).
     * @param lineStroke  the line stroke (<code>null</code> not permitted).
     * @param linePaint  the line paint (<code>null</code> not permitted).
     */
    public LegendItem(String label, String description, 
                      String toolTipText, String urlText, 
                      Shape line, Stroke lineStroke, Paint linePaint) {
        
        this(label, description, toolTipText, urlText,
                /* shape visible = */ false, UNUSED_SHAPE,
                /* shape filled = */ false, Color.black,
                /* shape outlined = */ false, Color.black, UNUSED_STROKE,
                /* line visible = */ true, line, lineStroke, linePaint);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[6]++;
    }
    
    /**
     * Creates a new legend item.
     *
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (not currently used, 
     *        <code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param shapeVisible  a flag that controls whether or not the shape is 
     *                      displayed.
     * @param shape  the shape (<code>null</code> permitted).
     * @param shapeFilled  a flag that controls whether or not the shape is 
     *                     filled.
     * @param fillPaint  the fill paint (<code>null</code> not permitted).
     * @param shapeOutlineVisible  a flag that controls whether or not the 
     *                             shape is outlined.
     * @param outlinePaint  the outline paint (<code>null</code> not permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> not 
     *                       permitted).
     * @param lineVisible  a flag that controls whether or not the line is 
     *                     visible.
     * @param line  the line.
     * @param lineStroke  the stroke (<code>null</code> not permitted).
     * @param linePaint  the line paint (<code>null</code> not permitted).
     */
    public LegendItem(String label, String description,
                      String toolTipText, String urlText,
                      boolean shapeVisible, Shape shape,
                      boolean shapeFilled, Paint fillPaint, 
                      boolean shapeOutlineVisible, Paint outlinePaint,
                      Stroke outlineStroke,
                      boolean lineVisible, Shape line,
                      Stroke lineStroke, Paint linePaint) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[1]++;
            throw new IllegalArgumentException("Null 'label' argument.");
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[2]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fillPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[3]++;
            throw new IllegalArgumentException("Null 'fillPaint' argument.");
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[4]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lineStroke == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[5]++;
            throw new IllegalArgumentException("Null 'lineStroke' argument.");

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[6]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((outlinePaint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[7]++;
            throw new IllegalArgumentException("Null 'outlinePaint' argument.");

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[8]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((outlineStroke == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[9]++;
            throw new IllegalArgumentException(
                    "Null 'outlineStroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[10]++;}
        this.label = label;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[12]++;
        this.attributedLabel = null;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[13]++;
        this.description = description;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[14]++;
        this.shapeVisible = shapeVisible;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[15]++;
        this.shape = shape;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[16]++;
        this.shapeFilled = shapeFilled;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[17]++;
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[18]++;
        this.fillPaintTransformer = new StandardGradientPaintTransformer();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[19]++;
        this.shapeOutlineVisible = shapeOutlineVisible;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[20]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[21]++;
        this.outlineStroke = outlineStroke;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[22]++;
        this.lineVisible = lineVisible;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[23]++;
        this.line = line;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[24]++;
        this.lineStroke = lineStroke;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[25]++;
        this.linePaint = linePaint;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[26]++;
        this.toolTipText = toolTipText;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[27]++;
        this.urlText = urlText;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[28]++;
    }
    
    /**
     * Creates a legend item with a filled shape.  The shape is not outlined,
     * and no line is visible.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (<code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param shape  the shape (<code>null</code> not permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code>
     *                   not permitted).
     */
    public LegendItem(AttributedString label, String description, 
                      String toolTipText, String urlText, 
                      Shape shape, Paint fillPaint) {
        
        this(label, description, toolTipText, urlText, 
                /* shape visible = */ true, shape,
                /* shape filled = */ true, fillPaint,
                /* shape outlined = */ false, Color.black, UNUSED_STROKE,
                /* line visible = */ false, UNUSED_SHAPE, UNUSED_STROKE,
                Color.black);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[29]++;
        
    }
    
    /**
     * Creates a legend item with a filled and outlined shape.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (<code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param shape  the shape (<code>null</code> not permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code>
     *                   not permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> not 
     *                       permitted).
     * @param outlinePaint  the outline paint (<code>null</code> not 
     *                      permitted).
     */
    public LegendItem(AttributedString label, String description, 
                      String toolTipText, String urlText, 
                      Shape shape, Paint fillPaint, 
                      Stroke outlineStroke, Paint outlinePaint) {
        
        this(label, description, toolTipText, urlText,
                /* shape visible = */ true, shape,
                /* shape filled = */ true, fillPaint,
                /* shape outlined = */ true, outlinePaint, outlineStroke,
                /* line visible = */ false, UNUSED_SHAPE, UNUSED_STROKE,
                Color.black);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[30]++;
    }
    
    /**
     * Creates a legend item using a line.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (<code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param line  the line (<code>null</code> not permitted).
     * @param lineStroke  the line stroke (<code>null</code> not permitted).
     * @param linePaint  the line paint (<code>null</code> not permitted).
     */
    public LegendItem(AttributedString label, String description, 
                      String toolTipText, String urlText, 
                      Shape line, Stroke lineStroke, Paint linePaint) {
        
        this(label, description, toolTipText, urlText,
                /* shape visible = */ false, UNUSED_SHAPE,
                /* shape filled = */ false, Color.black,
                /* shape outlined = */ false, Color.black, UNUSED_STROKE,
                /* line visible = */ true, line, lineStroke, linePaint
        );
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[31]++;
    }
    
    /**
     * Creates a new legend item.
     *
     * @param label  the label (<code>null</code> not permitted).
     * @param description  the description (not currently used, 
     *        <code>null</code> permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text (<code>null</code> permitted).
     * @param shapeVisible  a flag that controls whether or not the shape is 
     *                      displayed.
     * @param shape  the shape (<code>null</code> permitted).
     * @param shapeFilled  a flag that controls whether or not the shape is 
     *                     filled.
     * @param fillPaint  the fill paint (<code>null</code> not permitted).
     * @param shapeOutlineVisible  a flag that controls whether or not the 
     *                             shape is outlined.
     * @param outlinePaint  the outline paint (<code>null</code> not permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> not 
     *                       permitted).
     * @param lineVisible  a flag that controls whether or not the line is 
     *                     visible.
     * @param line  the line.
     * @param lineStroke  the stroke (<code>null</code> not permitted).
     * @param linePaint  the line paint (<code>null</code> not permitted).
     */
    public LegendItem(AttributedString label, String description,
                      String toolTipText, String urlText,
                      boolean shapeVisible, Shape shape,
                      boolean shapeFilled, Paint fillPaint, 
                      boolean shapeOutlineVisible, Paint outlinePaint,
                      Stroke outlineStroke,
                      boolean lineVisible, Shape line, Stroke lineStroke,
                      Paint linePaint) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[11]++;
            throw new IllegalArgumentException("Null 'label' argument.");
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[12]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((fillPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[13]++;
            throw new IllegalArgumentException("Null 'fillPaint' argument.");
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[14]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lineStroke == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[15]++;
            throw new IllegalArgumentException("Null 'lineStroke' argument.");

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[16]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((outlinePaint == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[17]++;
            throw new IllegalArgumentException("Null 'outlinePaint' argument.");

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[18]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((outlineStroke == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[19]++;
            throw new IllegalArgumentException(
                "Null 'outlineStroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[20]++;}
        this.label = characterIteratorToString(label.getIterator());
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[37]++;
        this.attributedLabel = label;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[38]++;
        this.description = description;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[39]++;
        this.shapeVisible = shapeVisible;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[40]++;
        this.shape = shape;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[41]++;
        this.shapeFilled = shapeFilled;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[42]++;
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[43]++;
        this.fillPaintTransformer = new StandardGradientPaintTransformer();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[44]++;
        this.shapeOutlineVisible = shapeOutlineVisible;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[45]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[46]++;
        this.outlineStroke = outlineStroke;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[47]++;
        this.lineVisible = lineVisible;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[48]++;
        this.line = line;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[49]++;
        this.lineStroke = lineStroke;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[50]++;
        this.linePaint = linePaint;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[51]++;
        this.toolTipText = toolTipText;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[52]++;
        this.urlText = urlText;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[53]++;
    }

    /**
     * Returns a string containing the characters from the given iterator.
     * 
     * @param iterator  the iterator (<code>null</code> not permitted).
     * 
     * @return A string.
     */
    private String characterIteratorToString(CharacterIterator iterator) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[54]++;
        int endIndex = iterator.getEndIndex();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[55]++;
        int beginIndex = iterator.getBeginIndex();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[56]++;
        int count = endIndex - beginIndex;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((count <= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[21]++;
            return "";

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[22]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[58]++;
        char[] chars = new char[count];
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[59]++;
        int i = 0;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[60]++;
        char c = iterator.first();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[61]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((c != CharacterIterator.DONE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.loops[1]--;
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.loops[2]--;
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.loops[3]++;
}
            chars[i] = c;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[62]++;
            i++;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[63]++;
            c = iterator.next();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[64]++;
        }
        return new String(chars);
    }
    
    /**
     * Returns the dataset.
     * 
     * @return The dataset.
     * 
     * @since 1.0.6
     * 
     * @see #setDatasetIndex(int)
     */
    public Dataset getDataset() {
        return this.dataset;
    }
    
    /**
     * Sets the dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @since 1.0.6
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[65]++;
    }
    
    /**
     * Returns the dataset index for this legend item.
     * 
     * @return The dataset index.
     * 
     * @since 1.0.2
     * 
     * @see #setDatasetIndex(int)
     * @see #getDataset()
     */
    public int getDatasetIndex() {
        return this.datasetIndex;
    }
    
    /**
     * Sets the dataset index for this legend item.
     * 
     * @param index  the index.
     * 
     * @since 1.0.2
     * 
     * @see #getDatasetIndex()
     */
    public void setDatasetIndex(int index) {
        this.datasetIndex = index;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[66]++;
    }
    
    /**
     * Returns the series key.
     * 
     * @return The series key.
     * 
     * @since 1.0.6
     * 
     * @see #setSeriesKey(Comparable)
     */
    public Comparable getSeriesKey() {
        return this.seriesKey;
    }
    
    /**
     * Sets the series key.
     * 
     * @param key  the series key.
     * 
     * @since 1.0.6
     */
    public void setSeriesKey(Comparable key) {
        this.seriesKey = key;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[67]++;
    }
    
    /**
     * Returns the series index for this legend item.
     * 
     * @return The series index.
     * 
     * @since 1.0.2
     */
    public int getSeriesIndex() {
        return this.series;
    }
    
    /**
     * Sets the series index for this legend item.
     * 
     * @param index  the index.
     * 
     * @since 1.0.2
     */
    public void setSeriesIndex(int index) {
        this.series = index;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[68]++;
    }
    
    /**
     * Returns the label.
     *
     * @return The label (never <code>null</code>).
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the attributed label.
     *
     * @return The attributed label (possibly <code>null</code>).
     */
    public AttributedString getAttributedLabel() {
        return this.attributedLabel;
    }

    /**
     * Returns the description for the legend item.
     * 
     * @return The description.
     */
    public String getDescription() {
        return this.description;   
    }
    
    /**
     * Returns the tool tip text.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String getToolTipText() {
        return this.toolTipText;   
    }
    
    /**
     * Returns the URL text.
     * 
     * @return The URL text (possibly <code>null</code>).
     */
    public String getURLText() {
        return this.urlText; 
    }
    
    /**
     * Returns a flag that indicates whether or not the shape is visible.
     * 
     * @return A boolean.
     */
    public boolean isShapeVisible() {
        return this.shapeVisible;
    }
    
    /**
     * Returns the shape used to label the series represented by this legend 
     * item.
     *
     * @return The shape (never <code>null</code>).
     */
    public Shape getShape() {
        return this.shape;
    }
    
    /**
     * Returns a flag that controls whether or not the shape is filled.
     * 
     * @return A boolean.
     */
    public boolean isShapeFilled() {
        return this.shapeFilled;
    }

    /**
     * Returns the fill paint.
     *
     * @return The fill paint (never <code>null</code>).
     */
    public Paint getFillPaint() {
        return this.fillPaint;
    }

    /**
     * Returns the flag that controls whether or not the shape outline
     * is visible.
     * 
     * @return A boolean.
     */
    public boolean isShapeOutlineVisible() {
        return this.shapeOutlineVisible;
    }
    
    /**
     * Returns the line stroke for the series.
     *
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getLineStroke() {
        return this.lineStroke;
    }
    
    /**
     * Returns the paint used for lines.
     * 
     * @return The paint.
     */
    public Paint getLinePaint() {
        return this.linePaint;
    }
    
    /**
     * Returns the outline paint.
     *
     * @return The outline paint (never <code>null</code>).
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }

    /**
     * Returns the outline stroke.
     *
     * @return The outline stroke (never <code>null</code>).
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    /**
     * Returns a flag that indicates whether or not the line is visible.
     * 
     * @return A boolean.
     */
    public boolean isLineVisible() {
        return this.lineVisible;
    }
    
    /**
     * Returns the line.
     * 
     * @return The line.
     */
    public Shape getLine() {
        return this.line;
    }
    
    /**
     * Returns the transformer used when the fill paint is an instance of 
     * <code>GradientPaint</code>.
     * 
     * @return The transformer (never <code>null</code>).
     * 
     * @since 1.0.4
     * 
     * @see #setFillPaintTransformer(GradientPaintTransformer)
     */
    public GradientPaintTransformer getFillPaintTransformer() {
        return this.fillPaintTransformer;
    }
    
    /**
     * Sets the transformer used when the fill paint is an instance of 
     * <code>GradientPaint</code>.
     * 
     * @param transformer  the transformer (<code>null</code> not permitted).
     * 
     * @since 1.0.4
     * 
     * @see #getFillPaintTransformer()
     */
    public void setFillPaintTransformer(GradientPaintTransformer transformer) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[69]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((transformer == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[23]++; 
            throw new IllegalArgumentException("Null 'transformer' attribute.");

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[24]++;}
        this.fillPaintTransformer = transformer;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[70]++;
    }
    
    /**
     * Tests this item for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[71]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[25]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[26]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[72]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof LegendItem) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[27]++;
                return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[28]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[73]++;
        LegendItem that = (LegendItem) obj;
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.datasetIndex != that.datasetIndex) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[30]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[75]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.series != that.series) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[32]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[76]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.label.equals(that.label)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[34]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[77]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((AttributedStringUtilities.equal(this.attributedLabel, 
                that.attributedLabel)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[36]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[78]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.description, that.description)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[38]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.shapeVisible != that.shapeVisible) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[40]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[80]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.shape, that.shape)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[42]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[81]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.shapeFilled != that.shapeFilled) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[44]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[82]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.fillPaint.equals(that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[45]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[46]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[83]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.fillPaintTransformer, 
                that.fillPaintTransformer)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[48]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[84]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.shapeOutlineVisible != that.shapeOutlineVisible) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[50]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[85]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.outlineStroke.equals(that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[51]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[52]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[86]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.outlinePaint.equals(that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[53]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[54]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[87]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((!this.lineVisible == that.lineVisible) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[56]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[88]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.line, that.line)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[58]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[89]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.lineStroke.equals(that.lineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[59]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[60]++;}
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[90]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.linePaint.equals(that.linePaint)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.branches[62]++;}
        return true;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream (<code>null</code> not permitted).
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[91]++;
        SerialUtilities.writeAttributedString(this.attributedLabel, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[92]++;
        SerialUtilities.writeShape(this.shape, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[93]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[94]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[95]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[96]++;
        SerialUtilities.writeShape(this.line, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[97]++;
        SerialUtilities.writeStroke(this.lineStroke, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[98]++;
        SerialUtilities.writePaint(this.linePaint, stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[99]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream (<code>null</code> not permitted).
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[100]++;
        this.attributedLabel = SerialUtilities.readAttributedString(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[101]++;
        this.shape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[102]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[103]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[104]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[105]++;
        this.line = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[106]++;
        this.lineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[107]++;
        this.linePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt.statements[108]++;
    }
    
}

class CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt ());
  }
    public static long[] statements = new long[109];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[33];
  static {
    final String SECTION_NAME = "org.jfree.chart.LegendItem.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 32; i++) {
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

  public CodeCoverCoverageCounter$mwabff4vuy0sl4qdnp3i2xt () {
    super("org.jfree.chart.LegendItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 108; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.LegendItem.java");
      for (int i = 1; i <= 108; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 32; i++) {
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

