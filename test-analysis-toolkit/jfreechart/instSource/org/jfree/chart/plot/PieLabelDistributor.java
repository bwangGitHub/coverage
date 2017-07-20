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
 * ------------------------
 * PieLabelDistributor.java
 * ------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 08-Mar-2004 : Version 1 (DG);
 * 18-Apr-2005 : Use StringBuffer (DG);
 * 14-Jun-2007 : Now extends AbstractPieLabelDistributor (DG);
 *
 */

package org.jfree.chart.plot;

import java.util.Collections;

/**
 * This class distributes the section labels for one side of a pie chart so 
 * that they do not overlap.
 */
public class PieLabelDistributor extends AbstractPieLabelDistributor {
  static {
    CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.ping();
  }

        
    /** The minimum gap. */
    private double minGap = 4.0;
  {
    CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[1]++;
  }
    
    /**
     * Creates a new distributor.
     * 
     * @param labelCount  the number of labels (ignored).
     */
    public PieLabelDistributor(int labelCount) {
        super();
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[2]++;
    }
    
    /**
     * Distributes the labels.
     * 
     * @param minY  the minimum y-coordinate in Java2D-space.
     * @param height  the available height (in Java2D units).
     */
    public void distributeLabels(double minY, double height) {
        sort();
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[3]++;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;  // sorts the label records into ascending order by baseY
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isOverlap()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[1]++;
            adjustInwards();
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[5]++;

        } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[2]++;}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        
        // if still overlapping, do something else...
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isOverlap()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[3]++;
            adjustDownwards(minY, height);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[7]++;

        } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[4]++;}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isOverlap()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[5]++; 
            adjustUpwards(minY, height);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[9]++;

        } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[6]++;}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isOverlap()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[7]++;  
            spreadEvenly(minY, height);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[11]++;

        } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[8]++;}

    }
    
    /**
     * Returns <code>true</code> if there are overlapping labels in the list, 
     * and <code>false</code> otherwise.
     * 
     * @return A boolean.
     */
    private boolean isOverlap() {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[12]++;
        double y = 0.0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < this.labels.size()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[1]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[2]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[3]++;
}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[14]++;
            PieLabelRecord plr = getPieLabelRecord(i);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((y > plr.getLowerY()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[9]++;
                return true;

            } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[10]++;}
            y = plr.getUpperY();
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[16]++;    
        }
        return false;
    }
    
    /**
     * Adjusts the y-coordinate for the labels in towards the center in an 
     * attempt to fix overlapping.
     */
    protected void adjustInwards() {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[17]++;   
        int lower = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[18]++;
        int upper = this.labels.size() - 1;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
        while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((upper > lower) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[4]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[5]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[6]++;
}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lower < upper - 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[11]++;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[21]++;
                PieLabelRecord r0 = getPieLabelRecord(lower);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[22]++;
                PieLabelRecord r1 = getPieLabelRecord(lower + 1);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[23]++;
int CodeCoverConditionCoverageHelper_C9; 
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((r1.getLowerY() < r0.getUpperY()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[13]++;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[24]++;
                    double adjust = r0.getUpperY() - r1.getLowerY() 
                                    + this.minGap;  
                    r1.setAllocatedY(r1.getAllocatedY() + adjust);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[25]++;
   
                } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[14]++;}

            } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[12]++;}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[26]++;
            PieLabelRecord r2 = getPieLabelRecord(upper - 1);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[27]++;
            PieLabelRecord r3 = getPieLabelRecord(upper);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[28]++;
int CodeCoverConditionCoverageHelper_C10;  
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((r2.getUpperY() > r3.getLowerY()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[15]++;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[29]++;
                double adjust = (r2.getUpperY() - r3.getLowerY()) + this.minGap;
                r3.setAllocatedY(r3.getAllocatedY() + adjust);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[30]++;
   
            } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[16]++;}                
            lower++;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[31]++; 
            upper--;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[32]++;
        }
    }
    
    /**
     * Any labels that are overlapping are moved down in an attempt to 
     * eliminate the overlaps.
     * 
     * @param minY  the minimum y value (in Java2D coordinate space).
     * @param height  the height available for all labels.
     */
    protected void adjustDownwards(double minY, double height) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[33]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < this.labels.size() - 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[7]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[8]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[9]++;
}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[34]++;
            PieLabelRecord record0 = getPieLabelRecord(i);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[35]++;
            PieLabelRecord record1 = getPieLabelRecord(i + 1);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((record1.getLowerY() < record0.getUpperY()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[17]++;
                record1.setAllocatedY(Math.min(minY + height, 
                        record0.getUpperY() + this.minGap 
                        + record1.getLabelHeight() / 2.0));
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[37]++;
   
            } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[18]++;}
        }        
    }

    /**
     * Any labels that are overlapping are moved up in an attempt to eliminate 
     * the overlaps.
     * 
     * @param minY  the minimum y value (in Java2D coordinate space).
     * @param height  the height available for all labels.
     */
    protected void adjustUpwards(double minY, double height) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[38]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = this.labels.size() - 1;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[10]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[11]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[12]++;
}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[39]++;
            PieLabelRecord record0 = getPieLabelRecord(i);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[40]++;
            PieLabelRecord record1 = getPieLabelRecord(i - 1);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((record1.getUpperY() > record0.getLowerY()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[19]++;
                record1.setAllocatedY(Math.max(minY, record0.getLowerY() 
                        - this.minGap - record1.getLabelHeight() / 2.0));
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[42]++;

            } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[20]++;}
        }        
    }

    /**
     * Labels are spaced evenly in the available space in an attempt to 
     * eliminate the overlaps.
     * 
     * @param minY  the minimum y value (in Java2D coordinate space).
     * @param height  the height available for all labels.
     */
    protected void spreadEvenly(double minY, double height) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[43]++;
        double y = minY;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[44]++;
        double sumOfLabelHeights = 0.0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[45]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < this.labels.size()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[13]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[14]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[15]++;
}
            sumOfLabelHeights += getPieLabelRecord(i).getLabelHeight();
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[46]++;
        }
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[47]++;
        double gap = Math.max(0, height - sumOfLabelHeights);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[48]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.labels.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[21]++;
            gap = gap / (this.labels.size() - 1);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[49]++;
   
        } else {
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.branches[22]++;}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[50]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[16]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < this.labels.size()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[16]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[17]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[18]++;
}
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[51]++;
            PieLabelRecord record = getPieLabelRecord(i);
            y = y + record.getLabelHeight() / 2.0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[52]++;
            record.setAllocatedY(y);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[53]++;
            y = y + record.getLabelHeight() / 2.0 + gap;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[54]++;
        }        
    }
        
    /**
     * Sorts the label records into ascending order by y-value.
     */
    public void sort() {
        Collections.sort(this.labels);
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[55]++;  
    }
    
    /**
     * Returns a string containing a description of the object for 
     * debugging purposes.
     * 
     * @return A string.
     */
    public String toString() {
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[56]++;
        StringBuffer result = new StringBuffer();
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[57]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[19]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < this.labels.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[19]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[20]--;
  CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.loops[21]++;
}
            result.append(getPieLabelRecord(i).toString()).append("\n");
CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx.statements[58]++;   
        }
        return result.toString();
    }
    
}

class CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx ());
  }
    public static long[] statements = new long[59];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PieLabelDistributor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$ij6x12groe7ao7rtvjkuvu86wja2zza8ljbkx () {
    super("org.jfree.chart.plot.PieLabelDistributor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 58; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.PieLabelDistributor.java");
      for (int i = 1; i <= 58; i++) {
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
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

