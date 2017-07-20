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
 * BlockBorder.java
 * ----------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 04-Feb-2005 : Added equals() and implemented Serializable (DG);
 * 23-Feb-2005 : Added attribute for border color (DG);
 * 06-May-2005 : Added new convenience constructors (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 16-Mar-2007 : Implemented BlockFrame (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.PaintUtilities;

/**
 * A border for a block.  This class is immutable.
 */
public class BlockBorder implements BlockFrame, Serializable {
  static {
    CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 4961579220410228283L;
  static {
    CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[1]++;
  }
    
    /** An empty border. */
    public static final BlockBorder NONE = new BlockBorder(
            RectangleInsets.ZERO_INSETS, Color.white);
  static {
    CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[2]++;
  }
    
    /** The space reserved for the border. */
    private RectangleInsets insets;
    
    /** The border color. */
    private transient Paint paint;
    
    /**
     * Creates a default border.
     */
    public BlockBorder() {
        this(Color.black);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[3]++;    
    }
    
    /**
     * Creates a new border with the specified color.
     * 
     * @param paint  the color (<code>null</code> not permitted).
     */
    public BlockBorder(Paint paint) {
        this(new RectangleInsets(1, 1, 1, 1), paint);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[4]++;   
    }
    
    /**
     * Creates a new border with the specified line widths (in black).
     * 
     * @param top  the width of the top border.
     * @param left  the width of the left border.
     * @param bottom  the width of the bottom border.
     * @param right  the width of the right border.
     */
    public BlockBorder(double top, double left, double bottom, double right) {
        this(new RectangleInsets(top, left, bottom, right), Color.black);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[5]++;    
    }
    
    /**
     * Creates a new border with the specified line widths (in black).
     * 
     * @param top  the width of the top border.
     * @param left  the width of the left border.
     * @param bottom  the width of the bottom border.
     * @param right  the width of the right border.
     * @param paint  the border paint (<code>null</code> not permitted).
     */
    public BlockBorder(double top, double left, double bottom, double right, 
                       Paint paint) {
        this(new RectangleInsets(top, left, bottom, right), paint);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[6]++;    
    }
    
    /**
     * Creates a new border.
     * 
     * @param insets  the border insets (<code>null</code> not permitted).
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public BlockBorder(RectangleInsets insets, Paint paint) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((insets == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[1]++;
            throw new IllegalArgumentException("Null 'insets' argument.");

        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[2]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[4]++;}
        this.insets = insets;
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[9]++;
        this.paint = paint;
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[10]++;
    }

    /**
     * Returns the space reserved for the border.
     * 
     * @return The space (never <code>null</code>).
     */
    public RectangleInsets getInsets() {
        return this.insets;
    }
    
    /**
     * Returns the paint used to draw the border.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getPaint() {
        return this.paint;   
    }
    
    /**
     * Draws the border by filling in the reserved space.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[11]++;
        // this default implementation will just fill the available
        // border space with a single color
        double t = this.insets.calculateTopInset(area.getHeight());
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[12]++;
        double b = this.insets.calculateBottomInset(area.getHeight());
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[13]++;
        double l = this.insets.calculateLeftInset(area.getWidth());
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[14]++;
        double r = this.insets.calculateRightInset(area.getWidth());
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[15]++;
        double x = area.getX();
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[16]++;
        double y = area.getY();
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[17]++;
        double w = area.getWidth();
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[18]++;
        double h = area.getHeight();
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[19]++;
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[20]++;
        Rectangle2D rect = new Rectangle2D.Double();
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[5]++;
            rect.setRect(x, y, w, t);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[22]++;
            g2.fill(rect);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[23]++;

        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[6]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((b > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[7]++;
            rect.setRect(x, y + h - b, w, b);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[25]++;
            g2.fill(rect);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[26]++;

        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[8]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((l > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[9]++;
            rect.setRect(x, y, l, h);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[28]++;
            g2.fill(rect);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[29]++;

        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[10]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((r > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[11]++;
            rect.setRect(x + w - r, y, r, h);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[31]++;
            g2.fill(rect);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[32]++;

        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[12]++;}
    }
    
    /**
     * Tests this border for equality with an arbitrary instance.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[13]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[14]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof BlockBorder) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[15]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[16]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[35]++;
        BlockBorder that = (BlockBorder) obj;
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.insets.equals(that.insets)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[17]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[18]++;}
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.branches[20]++;}
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
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[38]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[39]++;
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
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[40]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd.statements[41]++;
    }
 
}

class CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd ());
  }
    public static long[] statements = new long[42];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.BlockBorder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$3xkfr27zvpfblz3h74i8fcbxd () {
    super("org.jfree.chart.block.BlockBorder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 41; i++) {
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
    log.startNamedSection("org.jfree.chart.block.BlockBorder.java");
      for (int i = 1; i <= 41; i++) {
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

