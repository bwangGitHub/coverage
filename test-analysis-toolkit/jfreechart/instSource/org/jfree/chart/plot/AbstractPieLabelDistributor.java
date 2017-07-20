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
 * --------------------------------
 * AbstractPieLabelDistributor.java
 * --------------------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 14-Jun-2007 : Version 1 (DG);
 *
 */

package org.jfree.chart.plot;

import java.io.Serializable;
import java.util.List;

/**
 * A base class for handling the distribution of pie section labels.  Create
 * your own subclass and set it using the 
 * {@link PiePlot#setLabelDistributor(AbstractPieLabelDistributor)} method
 * if you want to customise the label distribution.
 */
public abstract class AbstractPieLabelDistributor implements Serializable {
  static {
    CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.ping();
  }


    /** The label records. */
    protected List labels;

    /**
     * Creates a new instance.
     */
    public AbstractPieLabelDistributor() {
        this.labels = new java.util.ArrayList();
CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.statements[1]++;
    }

    /**
     * Returns a label record from the list.
     * 
     * @param index  the index.
     * 
     * @return The label record.
     */
    public PieLabelRecord getPieLabelRecord(int index) {
        return (PieLabelRecord) this.labels.get(index);   
    }
    
    /**
     * Adds a label record.
     * 
     * @param record  the label record (<code>null</code> not permitted).
     */
    public void addPieLabelRecord(PieLabelRecord record) {
CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((record == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.branches[1]++;
            throw new IllegalArgumentException("Null 'record' argument.");

        } else {
  CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.branches[2]++;}
        this.labels.add(record);
CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.statements[3]++;
    }
    
    /**
     * Returns the number of items in the list.
     * 
     * @return The item count.
     */
    public int getItemCount() {
        return this.labels.size();   
    }
    
    /**
     * Clears the list of labels.
     */
    public void clear() {
        this.labels.clear();
CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd.statements[4]++;
    }
    
    /**
     * Called by the {@link PiePlot} class.  Implementations should distribute
     * the labels in this.labels then return.
     * 
     * @param minY  the y-coordinate for the top of the label area.
     * @param height  the height of the label area.
     */
    public abstract void distributeLabels(double minY, double height);
    
}

class CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.AbstractPieLabelDistributor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$1mo0hhj98w20a4n1nyyfm6rbwgcv3c7wyku5l7nk3f14k25gtd () {
    super("org.jfree.chart.plot.AbstractPieLabelDistributor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.AbstractPieLabelDistributor.java");
      for (int i = 1; i <= 4; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

