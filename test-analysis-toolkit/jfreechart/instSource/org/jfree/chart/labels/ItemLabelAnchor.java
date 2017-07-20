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
 * --------------------
 * ItemLabelAnchor.java
 * --------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 29-Apr-2003 : Version 1 (DG);
 * 19-Feb-2004 : Moved to org.jfree.chart.labels package, added readResolve() 
 *               method (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * An enumeration of the positions that a value label can take, relative to an 
 * item in a {@link org.jfree.chart.plot.CategoryPlot}.
 */
public final class ItemLabelAnchor implements Serializable {
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1233101616128695658L;
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[1]++;
  }
    
    /** CENTER. */
    public static final ItemLabelAnchor CENTER 
        = new ItemLabelAnchor("ItemLabelAnchor.CENTER");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[2]++;
  }

    /** INSIDE1. */
    public static final ItemLabelAnchor INSIDE1 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE1");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[3]++;
  }

    /** INSIDE2. */
    public static final ItemLabelAnchor INSIDE2 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE2");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[4]++;
  }

    /** INSIDE3. */
    public static final ItemLabelAnchor INSIDE3 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE3");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[5]++;
  }

    /** INSIDE4. */
    public static final ItemLabelAnchor INSIDE4 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE4");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[6]++;
  }

    /** INSIDE5. */
    public static final ItemLabelAnchor INSIDE5 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE5");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[7]++;
  }

    /** INSIDE6. */
    public static final ItemLabelAnchor INSIDE6 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE6");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[8]++;
  }

    /** INSIDE7. */
    public static final ItemLabelAnchor INSIDE7 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE7");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[9]++;
  }

    /** INSIDE8. */
    public static final ItemLabelAnchor INSIDE8 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE8");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[10]++;
  }

    /** INSIDE9. */
    public static final ItemLabelAnchor INSIDE9 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE9");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[11]++;
  }

    /** INSIDE10. */
    public static final ItemLabelAnchor INSIDE10 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE10");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[12]++;
  }

    /** INSIDE11. */
    public static final ItemLabelAnchor INSIDE11 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE11");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[13]++;
  }

    /** INSIDE12. */
    public static final ItemLabelAnchor INSIDE12 
        = new ItemLabelAnchor("ItemLabelAnchor.INSIDE12");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[14]++;
  }

    /** OUTSIDE1. */
    public static final ItemLabelAnchor OUTSIDE1 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE1");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[15]++;
  }

    /** OUTSIDE2. */
    public static final ItemLabelAnchor OUTSIDE2 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE2");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[16]++;
  }

    /** OUTSIDE3. */
    public static final ItemLabelAnchor OUTSIDE3 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE3");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[17]++;
  }

    /** OUTSIDE4. */
    public static final ItemLabelAnchor OUTSIDE4 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE4");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[18]++;
  }

    /** OUTSIDE5. */
    public static final ItemLabelAnchor OUTSIDE5 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE5");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[19]++;
  }

    /** OUTSIDE6. */
    public static final ItemLabelAnchor OUTSIDE6 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE6");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[20]++;
  }

    /** OUTSIDE7. */
    public static final ItemLabelAnchor OUTSIDE7 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE7");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[21]++;
  }

    /** OUTSIDE8. */
    public static final ItemLabelAnchor OUTSIDE8 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE8");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[22]++;
  }

    /** OUTSIDE9. */
    public static final ItemLabelAnchor OUTSIDE9 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE9");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[23]++;
  }

    /** OUTSIDE10. */
    public static final ItemLabelAnchor OUTSIDE10 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE10");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[24]++;
  }

    /** OUTSIDE11. */
    public static final ItemLabelAnchor OUTSIDE11 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE11");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[25]++;
  }

    /** OUTSIDE12. */
    public static final ItemLabelAnchor OUTSIDE12 
        = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE12");
  static {
    CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[26]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private ItemLabelAnchor(String name) {
        this.name = name;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[27]++;
    }

    /**
     * Returns a string representing the object.
     *
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified 
     * object, and <code>false</code> otherwise.
     *
     * @param o  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object o) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[28]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == o) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[2]++;}
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((o instanceof ItemLabelAnchor) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[4]++;}
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[30]++;

        ItemLabelAnchor order = (ItemLabelAnchor) o;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(order.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[6]++;}

        return true;

    }
    
    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[32]++;
        ItemLabelAnchor result = null;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.CENTER)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[7]++;
            result = ItemLabelAnchor.CENTER;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[34]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[8]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[35]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE1)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[9]++;
            result = ItemLabelAnchor.INSIDE1;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[36]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[10]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[37]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE2)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[11]++;
            result = ItemLabelAnchor.INSIDE2;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[38]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[12]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[39]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE3)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[13]++;
            result = ItemLabelAnchor.INSIDE3;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[40]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[14]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[41]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE4)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[15]++;
            result = ItemLabelAnchor.INSIDE4;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[42]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[16]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[43]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE5)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[17]++;
            result = ItemLabelAnchor.INSIDE5;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[44]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[18]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[45]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE6)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[19]++;
            result = ItemLabelAnchor.INSIDE6;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[46]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[20]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[47]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE7)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[21]++;
            result = ItemLabelAnchor.INSIDE7;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[48]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[22]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[49]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE8)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[23]++;
            result = ItemLabelAnchor.INSIDE8;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[50]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[24]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[51]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE9)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[25]++;
            result = ItemLabelAnchor.INSIDE9;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[52]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[26]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[53]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE10)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[27]++;
            result = ItemLabelAnchor.INSIDE10;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[54]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[28]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[55]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE11)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[29]++;
            result = ItemLabelAnchor.INSIDE11;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[56]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[30]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[57]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.INSIDE12)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[31]++;
            result = ItemLabelAnchor.INSIDE12;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[58]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[32]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[59]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE1)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[33]++;
            result = ItemLabelAnchor.OUTSIDE1;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[60]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[34]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[61]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE2)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[35]++;
            result = ItemLabelAnchor.OUTSIDE2;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[62]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[36]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[63]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE3)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[37]++;
            result = ItemLabelAnchor.OUTSIDE3;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[64]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[38]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[65]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE4)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[39]++;
            result = ItemLabelAnchor.OUTSIDE4;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[66]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[40]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[67]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE5)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[41]++;
            result = ItemLabelAnchor.OUTSIDE5;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[68]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[42]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[69]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE6)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[43]++;
            result = ItemLabelAnchor.OUTSIDE6;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[70]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[44]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[71]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE7)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[45]++;
            result = ItemLabelAnchor.OUTSIDE7;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[72]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[46]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[73]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE8)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[47]++;
            result = ItemLabelAnchor.OUTSIDE8;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[74]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[48]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[75]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE9)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[49]++;
            result = ItemLabelAnchor.OUTSIDE9;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[76]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[50]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[77]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE10)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[51]++;
            result = ItemLabelAnchor.OUTSIDE10;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[78]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[52]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[79]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE11)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[53]++;
            result = ItemLabelAnchor.OUTSIDE11;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[80]++;

        }
        else {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[54]++;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[81]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.equals(ItemLabelAnchor.OUTSIDE12)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[55]++;
            result = ItemLabelAnchor.OUTSIDE12;
CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.statements[82]++;

        } else {
  CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht.branches[56]++;}
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

}

class CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht ());
  }
    public static long[] statements = new long[83];
    public static long[] branches = new long[57];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.ItemLabelAnchor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 28; i++) {
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

  public CodeCoverCoverageCounter$8kw3nej0b8nt84242ru9gd968glpaht () {
    super("org.jfree.chart.labels.ItemLabelAnchor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 82; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 56; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.ItemLabelAnchor.java");
      for (int i = 1; i <= 82; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 56; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
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

