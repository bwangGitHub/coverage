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
 * KeyedValueComparator.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 05-Mar-2003 : Version 1 (DG);
 * 27-Aug-2003 : Moved SortOrder from org.jfree.data --> org.jfree.util (DG);
 * 12-Jan-2005 : Added accessor methods (DG);
 *
 */

package org.jfree.data;

import java.util.Comparator;

import org.jfree.util.SortOrder;

/**
 * A utility class that can compare and order two {@link KeyedValue} instances 
 * and sort them into ascending or descending order by key or by value.
 */
public class KeyedValueComparator implements Comparator {
  static {
    CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.ping();
  }


    /** The comparator type. */
    private KeyedValueComparatorType type;

    /** The sort order. */
    private SortOrder order;

    /**
     * Creates a new comparator.
     *
     * @param type  the type (<code>BY_KEY</code> or <code>BY_VALUE</code>, 
     *              <code>null</code> not permitted).
     * @param order  the order (<code>null</code> not permitted).
     */
    public KeyedValueComparator(KeyedValueComparatorType type, 
                                SortOrder order) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[1]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[2]++;}
        this.type = type;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[2]++;
        this.order = order;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[3]++;
    }

    /**
     * Returns the type.
     * 
     * @return The type (never <code>null</code>).
     */
    public KeyedValueComparatorType getType() {
        return this.type;
    }
    
    /**
     * Returns the sort order.
     * 
     * @return The sort order (never <code>null</code>).
     */
    public SortOrder getOrder() {
        return this.order;
    }
    
    /**
     * Compares two {@link KeyedValue} instances and returns an 
     * <code>int</code> that indicates the relative order of the two objects.
     *
     * @param o1  object 1.
     * @param o2  object 2.
     *
     * @return An int indicating the relative order of the objects.
     */
    public int compare(Object o1, Object o2) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((o2 == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[3]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[4]++;}
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((o1 == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[5]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[6]++;}

        int result;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[6]++;

        KeyedValue kv1 = (KeyedValue) o1;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[7]++;
        KeyedValue kv2 = (KeyedValue) o2;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.type == KeyedValueComparatorType.BY_KEY) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[7]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[9]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.order.equals(SortOrder.ASCENDING)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[9]++;
                result = kv1.getKey().compareTo(kv2.getKey());
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[10]++;

            }
            else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[10]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[11]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.order.equals(SortOrder.DESCENDING)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[11]++;
                result = kv2.getKey().compareTo(kv1.getKey());
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[12]++;

            }
            else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[12]++;
                throw new IllegalArgumentException("Unrecognised sort order.");
            }
}

        }
        else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[8]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[13]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.type == KeyedValueComparatorType.BY_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[13]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[14]++;
            Number n1 = kv1.getValue();
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[15]++;
            Number n2 = kv2.getValue();
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n2 == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[15]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[16]++;}
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n1 == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[17]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[18]++;}
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[18]++;
            double d1 = n1.doubleValue();
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[19]++;
            double d2 = n2.doubleValue();
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[20]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.order.equals(SortOrder.ASCENDING)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[19]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[21]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((d1 > d2) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[21]++;
                    result = 1;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[22]++;

                }
                else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[22]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[23]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((d1 < d2) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[23]++;
                    result = -1;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[24]++;

                }
                else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[24]++;
                    result = 0;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[25]++;
                }
}

            }
            else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[20]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[26]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.order.equals(SortOrder.DESCENDING)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[25]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[27]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((d1 > d2) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[27]++;
                    result = -1;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[28]++;

                }
                else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[28]++;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[29]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((d1 < d2) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[29]++;
                    result = 1;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[30]++;

                }
                else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[30]++;
                    result = 0;
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.statements[31]++;
                }
}

            }
            else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[26]++;
                throw new IllegalArgumentException("Unrecognised sort order.");
            }
}

        }
        else {
CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt.branches[14]++;
            throw new IllegalArgumentException("Unrecognised type.");
        }
}

        return result;
    }

}

class CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.data.KeyedValueComparator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$3fkk8g9j4mp0cez7cmjiqynxjoscz79d3g7brdt () {
    super("org.jfree.data.KeyedValueComparator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.KeyedValueComparator.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

