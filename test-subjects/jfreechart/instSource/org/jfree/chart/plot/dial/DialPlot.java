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
 * DialPlot.java
 * -------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 08-Mar-2007 : Fix in hashCode() (DG);
 * 17-Oct-2007 : Fixed listener registration/deregistration bugs (DG);
 * 24-Oct-2007 : Maintain pointers in their own list, so they can be
 *               drawn after other layers (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.ValueDataset;
import org.jfree.util.ObjectList;
import org.jfree.util.ObjectUtilities;

/**
 * A dial plot.
 * 
 * @since 1.0.7
 */
public class DialPlot extends Plot implements DialLayerChangeListener {
  static {
    CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.ping();
  }


    /**
     * The background layer (optional).
     */
    private DialLayer background;
    
    /**
     * The needle cap (optional).
     */
    private DialLayer cap;
    
    /**
     * The dial frame.
     */
    private DialFrame dialFrame;
    
    /**
     * The dataset(s) for the dial plot.
     */
    private ObjectList datasets;
    
    /**
     * The scale(s) for the dial plot. 
     */
    private ObjectList scales;
    
    /** Storage for keys that map datasets to scales. */
    private ObjectList datasetToScaleMap;

    /**
     * The drawing layers for the dial plot.
     */
    private List layers;
    
    /** 
     * The pointer(s) for the dial.
     */
    private List pointers;
    
    /**
     * The x-coordinate for the view window.
     */
    private double viewX;
    
    /**
     * The y-coordinate for the view window.
     */
    private double viewY;
    
    /**
     * The width of the view window, expressed as a percentage.
     */
    private double viewW;
    
    /**
     * The height of the view window, expressed as a percentage.
     */
    private double viewH;
    
    /** 
     * Creates a new instance of <code>DialPlot</code>.
     */
    public DialPlot() {
        this(null);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[1]++;    
    }
    
    /** 
     * Creates a new instance of <code>DialPlot</code>.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public DialPlot(ValueDataset dataset) {
        this.background = null;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[2]++;
        this.cap = null;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[3]++;
        this.dialFrame = new ArcDialFrame();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[4]++;
        this.datasets = new ObjectList();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[5]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[1]++;
            this.setDataset(dataset);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[7]++;
  
        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[2]++;}
        this.scales = new ObjectList();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[8]++;
        this.datasetToScaleMap = new ObjectList();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[9]++;
        this.layers = new java.util.ArrayList();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[10]++;
        this.pointers = new java.util.ArrayList();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[11]++;
        this.viewX = 0.0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[12]++;
        this.viewY = 0.0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[13]++;
        this.viewW = 1.0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[14]++;
        this.viewH = 1.0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[15]++;
    }

    /**
     * Returns the background.
     *
     * @return The background (possibly <code>null</code>).
     *
     * @see #setBackground(DialLayer)
     */
    public DialLayer getBackground() {
        return this.background;
    }
    
    /**
     * Sets the background layer and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param background  the background layer (<code>null</code> permitted).
     *
     * @see #getBackground()
     */
    public void setBackground(DialLayer background) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.background != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[3]++;
            this.background.removeChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[17]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[4]++;}
        this.background = background;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[18]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((background != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[5]++;
            background.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[20]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[6]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[21]++;
    }
    
    /**
     * Returns the cap.
     *
     * @return The cap (possibly <code>null</code>).
     *
     * @see #setCap(DialLayer)
     */
    public DialLayer getCap() {
        return this.cap;
    }
    
    /**
     * Sets the cap and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     *
     * @param cap  the cap (<code>null</code> permitted).
     *
     * @see #getCap()
     */
    public void setCap(DialLayer cap) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.cap != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[7]++;
            this.cap.removeChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[23]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[8]++;}
        this.cap = cap;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[24]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cap != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[9]++;
            cap.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[26]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[10]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[27]++;
    }

    /**
     * Returns the dial's frame.
     *
     * @return The dial's frame (never <code>null</code>).
     *
     * @see #setDialFrame(DialFrame)
     */
    public DialFrame getDialFrame() {
        return this.dialFrame;
    }
    
    /**
     * Sets the dial's frame and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param frame  the frame (<code>null</code> not permitted).
     *
     * @see #getDialFrame()
     */
    public void setDialFrame(DialFrame frame) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((frame == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[11]++;
            throw new IllegalArgumentException("Null 'frame' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[12]++;}
        this.dialFrame.removeChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[29]++;
        this.dialFrame = frame;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[30]++;
        frame.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[31]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[32]++;
    }

    /**
     * Returns the x-coordinate of the viewing rectangle.  This is specified
     * in the range 0.0 to 1.0, relative to the dial's framing rectangle.
     * 
     * @return The x-coordinate of the viewing rectangle.
     * 
     * @see #setView(double, double, double, double)
     */
    public double getViewX() {
        return this.viewX;
    }
    
    /**
     * Returns the y-coordinate of the viewing rectangle.  This is specified
     * in the range 0.0 to 1.0, relative to the dial's framing rectangle.
     * 
     * @return The y-coordinate of the viewing rectangle.
     * 
     * @see #setView(double, double, double, double)
     */
    public double getViewY() {
        return this.viewY;
    }
    
    /**
     * Returns the width of the viewing rectangle.  This is specified
     * in the range 0.0 to 1.0, relative to the dial's framing rectangle.
     * 
     * @return The width of the viewing rectangle.
     * 
     * @see #setView(double, double, double, double)
     */
    public double getViewWidth() {
        return this.viewW;
    }
    
    /**
     * Returns the height of the viewing rectangle.  This is specified
     * in the range 0.0 to 1.0, relative to the dial's framing rectangle.
     * 
     * @return The height of the viewing rectangle.
     * 
     * @see #setView(double, double, double, double)
     */
    public double getViewHeight() {
        return this.viewH;
    }
    
    /**
     * Sets the viewing rectangle, relative to the dial's framing rectangle,
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param x  the x-coordinate (in the range 0.0 to 1.0).
     * @param y  the y-coordinate (in the range 0.0 to 1.0).
     * @param w  the width (in the range 0.0 to 1.0).
     * @param h  the height (in the range 0.0 to 1.0).
     * 
     * @see #getViewX()
     * @see #getViewY()
     * @see #getViewWidth()
     * @see #getViewHeight()
     */
    public void setView(double x, double y, double w, double h) {
        this.viewX = x;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[33]++;
        this.viewY = y;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[34]++;
        this.viewW = w;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[35]++;
        this.viewH = h;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[36]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[37]++;
    }

    /**
     * Adds a layer to the plot and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param layer  the layer (<code>null</code> not permitted).
     */
    public void addLayer(DialLayer layer) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((layer == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[13]++;
            throw new IllegalArgumentException("Null 'layer' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[14]++;}
        this.layers.add(layer);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[39]++;
        layer.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[40]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[41]++;
    }
    
    /**
     * Returns the index for the specified layer.
     * 
     * @param layer  the layer (<code>null</code> not permitted).
     * 
     * @return The layer index.
     */
    public int getLayerIndex(DialLayer layer) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((layer == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[15]++;
            throw new IllegalArgumentException("Null 'layer' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[16]++;}
        return this.layers.indexOf(layer);
    }
    
    /**
     * Removes the layer at the specified index and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param index  the index.
     */
    public void removeLayer(int index) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[43]++;
        DialLayer layer = (DialLayer) this.layers.get(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((layer != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[17]++;
            layer.removeChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[45]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[18]++;}
        this.layers.remove(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[46]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[47]++;
    }
    
    /**
     * Removes the specified layer and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param layer  the layer (<code>null</code> not permitted).
     */
    public void removeLayer(DialLayer layer) {
        // defer argument checking
        removeLayer(getLayerIndex(layer));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[48]++;
    }
    
    /**
     * Adds a pointer to the plot and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param pointer  the pointer (<code>null</code> not permitted).
     */
    public void addPointer(DialPointer pointer) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((pointer == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[19]++;
            throw new IllegalArgumentException("Null 'pointer' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[20]++;}
        this.pointers.add(pointer);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[50]++;
        pointer.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[51]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[52]++;
    }
    
    /**
     * Returns the index for the specified pointer.
     * 
     * @param pointer  the pointer (<code>null</code> not permitted).
     * 
     * @return The pointer index.
     */
    public int getPointerIndex(DialPointer pointer) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((pointer == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[21]++;
            throw new IllegalArgumentException("Null 'pointer' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[22]++;}
        return this.pointers.indexOf(pointer);
    }
    
    /**
     * Removes the pointer at the specified index and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param index  the index.
     */
    public void removePointer(int index) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[54]++;
        DialPointer pointer = (DialPointer) this.pointers.get(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((pointer != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[23]++;
            pointer.removeChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[56]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[24]++;}
        this.pointers.remove(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[57]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[58]++;
    }
    
    /**
     * Removes the specified pointer and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param pointer  the pointer (<code>null</code> not permitted).
     */
    public void removePointer(DialPointer pointer) {
        // defer argument checking
        removeLayer(getPointerIndex(pointer));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[59]++;
    }

    /**
     * Returns the dial pointer that is associated with the specified
     * dataset, or <code>null</code>.
     * 
     * @param datasetIndex  the dataset index.
     * 
     * @return The pointer.
     */
    public DialPointer getPointerForDataset(int datasetIndex) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[60]++;
        DialPointer result = null;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[61]++;
        Iterator iterator = this.pointers.iterator();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[62]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[1]--;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[2]--;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[3]++;
}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[63]++;
            DialPointer p = (DialPointer) iterator.next();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((p.getDatasetIndex() == datasetIndex) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[25]++;
                return p;

            } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[26]++;}
        }
        return result;
    }
    
    /**
     * Returns the primary dataset for the plot.
     *
     * @return The primary dataset (possibly <code>null</code>).
     */
    public ValueDataset getDataset() {
        return getDataset(0);
    }

    /**
     * Returns the dataset at the given index.
     *
     * @param index  the dataset index.
     *
     * @return The dataset (possibly <code>null</code>).
     */
    public ValueDataset getDataset(int index) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[65]++;
        ValueDataset result = null;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.datasets.size() > index) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[27]++;
            result = (ValueDataset) this.datasets.get(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[67]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[28]++;}
        return result;
    }

    /**
     * Sets the dataset for the plot, replacing the existing dataset, if there 
     * is one, and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public void setDataset(ValueDataset dataset) {
        setDataset(0, dataset);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[68]++;
    }

    /**
     * Sets a dataset for the plot.
     *
     * @param index  the dataset index.
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public void setDataset(int index, ValueDataset dataset) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[69]++;
        
        ValueDataset existing = (ValueDataset) this.datasets.get(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[70]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[29]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[71]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[30]++;}
        this.datasets.set(index, dataset);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[72]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[73]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[31]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[74]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[32]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[75]++;
        
        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[76]++;
        
    }

    /**
     * Returns the number of datasets.
     *
     * @return The number of datasets.
     */
    public int getDatasetCount() {
        return this.datasets.size();
    }    
    
    /**
     * Draws the plot.  This method is usually called by the {@link JFreeChart}
     * instance that manages the plot.
     * 
     * @param g2  the graphics target.
     * @param area  the area in which the plot should be drawn.
     * @param anchor  the anchor point (typically the last point that the 
     *     mouse clicked on, <code>null</code> is permitted).
     * @param parentState  the state for the parent plot (if any).
     * @param info  used to collect plot rendering info (<code>null</code> 
     *     permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, 
            PlotState parentState, PlotRenderingInfo info) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[77]++;
        
        // first, expand the viewing area into a drawing frame
        Rectangle2D frame = viewToFrame(area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[78]++;
int CodeCoverConditionCoverageHelper_C18;
        
        // draw the background if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((this.background != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.background.isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[33]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.background.isClippedToWindow()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[35]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[80]++;
                Shape savedClip = g2.getClip();
                g2.setClip(this.dialFrame.getWindow(frame));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[81]++;
                this.background.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[82]++;
                g2.setClip(savedClip);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[83]++;

            }
            else {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[36]++;
                this.background.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[84]++;
            }

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[34]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[85]++;
        
        Iterator iterator = this.layers.iterator();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[86]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
        while ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[4]--;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[5]--;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[6]++;
}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[87]++;
            DialLayer current = (DialLayer) iterator.next();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[88]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((current.isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[37]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[89]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((current.isClippedToWindow()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[39]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[90]++;
                    Shape savedClip = g2.getClip();
                    g2.setClip(this.dialFrame.getWindow(frame));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[91]++;
                    current.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[92]++;
                    g2.setClip(savedClip);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[93]++;

                }
                else {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[40]++;
                    current.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[94]++;
                }

            } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[38]++;}
        }
        
        // draw the pointers
        iterator = this.pointers.iterator();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[95]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[96]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[7]++;


int CodeCoverConditionCoverageHelper_C23;
        while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[7]--;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[8]--;
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.loops[9]++;
}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[97]++;
            DialPointer current = (DialPointer) iterator.next();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[98]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((current.isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[41]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[99]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((current.isClippedToWindow()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[43]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[100]++;
                    Shape savedClip = g2.getClip();
                    g2.setClip(this.dialFrame.getWindow(frame));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[101]++;
                    current.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[102]++;
                    g2.setClip(savedClip);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[103]++;

                }
                else {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[44]++;
                    current.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[104]++;
                }

            } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[42]++;}
        }
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[105]++;
int CodeCoverConditionCoverageHelper_C26;

        // draw the cap if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((this.cap != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.cap.isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[45]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[106]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.cap.isClippedToWindow()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[47]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[107]++;
                Shape savedClip = g2.getClip();
                g2.setClip(this.dialFrame.getWindow(frame));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[108]++;
                this.cap.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[109]++;
                g2.setClip(savedClip);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[110]++;

            }
            else {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[48]++;
                this.cap.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[111]++;
            }

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[46]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[112]++;
int CodeCoverConditionCoverageHelper_C28;
        
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.dialFrame.isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[49]++;
            this.dialFrame.draw(g2, this, frame, area);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[113]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[50]++;}
        
    }
    
    /**
     * Returns the frame surrounding the specified view rectangle.
     * 
     * @param view  the view rectangle (<code>null</code> not permitted).
     * 
     * @return The frame rectangle.
     */
    private Rectangle2D viewToFrame(Rectangle2D view) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[114]++;
        double width = view.getWidth() / this.viewW;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[115]++;
        double height = view.getHeight() / this.viewH;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[116]++;
        double x = view.getX() - (width * this.viewX);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[117]++;
        double y = view.getY() - (height * this.viewY);
        return new Rectangle2D.Double(x, y, width, height);
    }
    
    /**
     * Returns the value from the specified dataset.
     * 
     * @param datasetIndex  the dataset index.
     * 
     * @return The data value.
     */
    public double getValue(int datasetIndex) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[118]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[119]++;
        ValueDataset dataset = getDataset(datasetIndex);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[120]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[51]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[121]++;
            Number n = dataset.getValue();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[122]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[53]++;
                result = n.doubleValue();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[123]++;

            } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[52]++;}
        return result;
    }
    
    /**
     * Adds a dial scale to the plot and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     * 
     * @param index  the scale index.
     * @param scale  the scale (<code>null</code> not permitted).
     */
    public void addScale(int index, DialScale scale) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[124]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((scale == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[55]++;
            throw new IllegalArgumentException("Null 'scale' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[56]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[125]++;
        DialScale existing = (DialScale) this.scales.get(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[126]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[57]++;
            removeLayer(existing);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[127]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[58]++;}
        this.layers.add(scale);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[128]++;
        this.scales.set(index, scale);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[129]++;
        scale.addChangeListener(this);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[130]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[131]++;         
    }
    
    /**
     * Returns the scale at the given index.
     *
     * @param index  the scale index.
     *
     * @return The scale (possibly <code>null</code>).
     */
    public DialScale getScale(int index) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[132]++;
        DialScale result = null;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[133]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.scales.size() > index) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[59]++;
            result = (DialScale) this.scales.get(index);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[134]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[60]++;}
        return result;
    }

    /**
     * Maps a dataset to a particular scale.
     * 
     * @param index  the dataset index (zero-based).
     * @param scaleIndex  the scale index (zero-based).
     */
    public void mapDatasetToScale(int index, int scaleIndex) {
        this.datasetToScaleMap.set(index, new Integer(scaleIndex));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[135]++;  
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[136]++; 
    }
    
    /**
     * Returns the dial scale for a specific dataset.
     * 
     * @param datasetIndex  the dataset index.
     * 
     * @return The dial scale.
     */
    public DialScale getScaleForDataset(int datasetIndex) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[137]++;
        DialScale result = (DialScale) this.scales.get(0);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[138]++;    
        Integer scaleIndex = (Integer) this.datasetToScaleMap.get(datasetIndex);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[139]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((scaleIndex != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[61]++;
            result = getScale(scaleIndex.intValue());
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[140]++;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[62]++;}
        return result;    
    }
    
    /**
     * A utility method that computes a rectangle using relative radius values.
     * 
     * @param rect  the reference rectangle (<code>null</code> not permitted).
     * @param radiusW  the width radius (must be > 0.0)
     * @param radiusH  the height radius.
     * 
     * @return A new rectangle.
     */
    public static Rectangle2D rectangleByRadius(Rectangle2D rect, 
            double radiusW, double radiusH) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[141]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((rect == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[63]++;
            throw new IllegalArgumentException("Null 'rect' argument.");

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[64]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[142]++;
        double x = rect.getCenterX();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[143]++;
        double y = rect.getCenterY();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[144]++;
        double w = rect.getWidth() * radiusW;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[145]++;
        double h = rect.getHeight() * radiusH;
        return new Rectangle2D.Double(x - w / 2.0, y - h / 2.0, w, h);
    }
    
    /**
     * Receives notification when a layer has changed, and responds by 
     * forwarding a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param event  the event.
     */
    public void dialLayerChanged(DialLayerChangeEvent event) {
        this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[146]++;
    }

    /**
     * Tests this <code>DialPlot</code> instance for equality with an 
     * arbitrary object.  The plot's dataset(s) is (are) not included in 
     * the test.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[147]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[65]++;
            return true;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[66]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[148]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((obj instanceof DialPlot) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[68]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[149]++;
        DialPlot that = (DialPlot) obj;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[150]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.background, that.background)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[70]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[151]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.cap, that.cap)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[72]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[152]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.dialFrame.equals(that.dialFrame)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[74]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[153]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.viewX != that.viewX) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[76]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[154]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.viewY != that.viewY) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[78]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[155]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.viewW != that.viewW) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[80]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[156]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.viewH != that.viewH) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[82]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[157]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.layers.equals(that.layers)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[84]++;}
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[158]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.pointers.equals(that.pointers)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.branches[86]++;}
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[159]++;
        int result = 193;
        result = 37 * result + ObjectUtilities.hashCode(this.background);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[160]++;
        result = 37 * result + ObjectUtilities.hashCode(this.cap);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[161]++;
        result = 37 * result + this.dialFrame.hashCode();
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[162]++;
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[163]++;
        long temp = Double.doubleToLongBits(this.viewX);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[164]++;
        temp = Double.doubleToLongBits(this.viewY);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[165]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[166]++;
        temp = Double.doubleToLongBits(this.viewW);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[167]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[168]++;
        temp = Double.doubleToLongBits(this.viewH);
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[169]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[170]++;
        return result;
    }
    
    /**
     * Returns the plot type.
     * 
     * @return <code>"DialPlot"</code>
     */
    public String getPlotType() {
        return "DialPlot";
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
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[171]++;
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
CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069.statements[172]++;
    }

    
}

class CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069 ());
  }
    public static long[] statements = new long[173];
    public static long[] branches = new long[87];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[47];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.DialPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 46; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$elhn8zad5q2n9ud3a069 () {
    super("org.jfree.chart.plot.dial.DialPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 172; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 86; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 46; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.DialPlot.java");
      for (int i = 1; i <= 172; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 86; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 46; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

