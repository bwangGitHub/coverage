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
 * ColorBlock.java
 * ---------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 16-Mar-2007 : Implemented equals() and fixed serialization (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A block that is filled with a single color.
 */
public class ColorBlock extends AbstractBlock implements Block {
  static {
    CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = 3383866145634010865L;
  static {
    CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[1]++;
  }

    /** The paint. */
    private transient Paint paint;
    
    /**
     * Creates a new block.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * @param width  the width.
     * @param height  the height.
     */
    public ColorBlock(Paint paint, double width, double height) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[2]++;}
        this.paint = paint;
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[3]++;
        setWidth(width);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[4]++;
        setHeight(height);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[5]++;
    }

    /**
     * Returns the paint.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @since 1.0.5
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Draws the block.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[6]++;
        Rectangle2D bounds = getBounds();
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[7]++;
        g2.fill(bounds);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[8]++;
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     * 
     * @return Always <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        draw(g2, area);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[9]++;
        return null;
    }
    
    /**
     * Tests this block for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[4]++;}
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof ColorBlock) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[6]++;}
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[12]++;
        ColorBlock that = (ColorBlock) obj;
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.branches[8]++;}
        return super.equals(obj);
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[14]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[15]++;
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
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[16]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t.statements[17]++;
    }

}

class CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.ColorBlock.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$k7locspvoffiyqwd0gk7k9t () {
    super("org.jfree.chart.block.ColorBlock.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.ColorBlock.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

