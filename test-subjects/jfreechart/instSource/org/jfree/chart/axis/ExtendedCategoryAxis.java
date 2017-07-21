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
 * ExtendedCategoryAxis.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 07-Jan-2004 : Updated the createLabel() method (DG);
 * 29-Jan-2004 : Added paint attribute (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 21-Mar-2007 : Implemented equals(), clone() and fixed serialization (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextBlock;
import org.jfree.text.TextFragment;
import org.jfree.text.TextLine;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;

/**
 * An extended version of the {@link CategoryAxis} class that supports 
 * sublabels on the axis.
 */
public class ExtendedCategoryAxis extends CategoryAxis {
  static {
    CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -3004429093959826567L;
  static {
    CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[1]++;
  }

    /** Storage for the sublabels. */
    private Map sublabels;
    
    /** The sublabel font. */
    private Font sublabelFont;
    
    /** The sublabel paint. */
    private transient Paint sublabelPaint;
    
    /**
     * Creates a new axis.
     * 
     * @param label  the axis label.
     */
    public ExtendedCategoryAxis(String label) {
        super(label);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[2]++;
        this.sublabels = new HashMap();
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[3]++;
        this.sublabelFont = new Font("SansSerif", Font.PLAIN, 10);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[4]++;
        this.sublabelPaint = Color.black;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[5]++;
    }
    
    /**
     * Returns the font for the sublabels.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setSubLabelFont(Font)
     */
    public Font getSubLabelFont() {
        return this.sublabelFont;
    }
    
    /**
     * Sets the font for the sublabels and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getSubLabelFont()
     */
    public void setSubLabelFont(Font font) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[1]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[2]++;}
        this.sublabelFont = font;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[7]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[8]++;
    }
    
    /**
     * Returns the paint for the sublabels.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setSubLabelPaint(Paint)
     */
    public Paint getSubLabelPaint() {
        return this.sublabelPaint;
    }
    
    /**
     * Sets the paint for the sublabels and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getSubLabelPaint()
     */
    public void setSubLabelPaint(Paint paint) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[4]++;}
        this.sublabelPaint = paint;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[10]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[11]++;
    }
    
    /**
     * Adds a sublabel for a category.
     * 
     * @param category  the category.
     * @param label  the label.
     */
    public void addSubLabel(Comparable category, String label) {
        this.sublabels.put(category, label);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[12]++;
    }
    
    /**
     * Overrides the default behaviour by adding the sublabel to the text 
     * block that is used for the category label.
     * 
     * @param category  the category.
     * @param width  the width (not used yet).
     * @param edge  the location of the axis.
     * @param g2  the graphics device.
     * 
     * @return A label.
     */
    protected TextBlock createLabel(Comparable category, float width, 
                                    RectangleEdge edge, Graphics2D g2) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[13]++;
        TextBlock label = super.createLabel(category, width, edge, g2);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[14]++;   
        String s = (String) this.sublabels.get(category);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[5]++;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[7]++;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[17]++;
                TextLine line = new TextLine(s, this.sublabelFont, 
                        this.sublabelPaint);
                label.addLine(line);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[18]++;

            }
            else {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[8]++;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[19]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[9]++;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[20]++;
                TextLine line = label.getLastLine();
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((line != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[11]++;
                    line.addFragment(new TextFragment("  " + s, 
                            this.sublabelFont, this.sublabelPaint));
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[22]++;

                } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[12]++;}

            } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[10]++;}
}

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[6]++;}
        return label; 
    }
    
    /**
     * Tests this axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[14]++;}
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof ExtendedCategoryAxis) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[16]++;}
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[25]++;
        ExtendedCategoryAxis that = (ExtendedCategoryAxis) obj;
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.sublabelFont.equals(that.sublabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[18]++;}
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.sublabelPaint, that.sublabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[20]++;}
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.sublabels.equals(that.sublabels)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.branches[22]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a clone of this axis.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[29]++;
        ExtendedCategoryAxis clone = (ExtendedCategoryAxis) super.clone();
        clone.sublabels = new HashMap(this.sublabels);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[30]++;
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
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[31]++;
        SerialUtilities.writePaint(this.sublabelPaint, stream);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[32]++;
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
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[33]++;
        this.sublabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt.statements[34]++;
    }

}

class CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.ExtendedCategoryAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,2,1,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$35ux7t5n3a38393trb02r87y82c3rxc4rn7djdt () {
    super("org.jfree.chart.axis.ExtendedCategoryAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.ExtendedCategoryAxis.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
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

