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
 * ----------------------
 * AbstractDialLayer.java
 * ----------------------
 * (C) Copyright 2006-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Nov-2006 : Version 1 (DG);
 * 17-Nov-2006 : Added visible flag (DG);
 * 16-Oct-2007 : Implemented equals() and clone() (DG);
 *
 */

package org.jfree.chart.plot.dial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import javax.swing.event.EventListenerList;

import org.jfree.chart.HashUtilities;

/**
 * A base class that can be used to implement a {@link DialLayer}.  It includes
 * an event notification mechanism.
 * 
 * @since 1.0.7
 */
public abstract class AbstractDialLayer implements DialLayer {
  static {
    CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.ping();
  }


    /** A flag that controls whether or not the layer is visible. */
    private boolean visible;
    
    /** Storage for registered listeners. */
    private transient EventListenerList listenerList;

    /**
     * Creates a new instance.
     */
    protected AbstractDialLayer() {
        this.visible = true;
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[1]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[2]++;
    }
    
    /**
     * Returns <code>true</code> if this layer is visible (should be displayed),
     * and <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setVisible(boolean)
     */
    public boolean isVisible() {
        return this.visible;
    }
    
    /**
     * Sets the flag that determines whether or not this layer is drawn by
     * the plot, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #isVisible()
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[3]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[4]++;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.branches[2]++;}
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractDialLayer) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.branches[4]++;}
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[7]++;
        AbstractDialLayer that = (AbstractDialLayer) obj;
        return this.visible == that.visible;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[8]++;
        int result = 23;
        result = HashUtilities.hashCode(result, this.visible);
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[9]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning this
     *     instance.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[10]++;
        AbstractDialLayer clone = (AbstractDialLayer) super.clone();
        // we don't clone the listeners
        clone.listenerList = new EventListenerList();
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[11]++;
        return clone;
    }
    
    /**
     * Registers an object for notification of changes to the dial layer.
     *
     * @param listener  the object that is being registered.
     * 
     * @see #removeChangeListener(DialLayerChangeListener)
     */
    public void addChangeListener(DialLayerChangeListener listener) {
        this.listenerList.add(DialLayerChangeListener.class, listener);
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[12]++;
    }

    /**
     * Deregisters an object for notification of changes to the dial layer.
     *
     * @param listener  the object to deregister.
     * 
     * @see #addChangeListener(DialLayerChangeListener)
     */
    public void removeChangeListener(DialLayerChangeListener listener) {
        this.listenerList.remove(DialLayerChangeListener.class, listener);
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[13]++;
    }

    /**
     * Returns <code>true</code> if the specified object is registered with
     * the dataset as a listener.  Most applications won't need to call this 
     * method, it exists mainly for use by unit testing code.
     * 
     * @param listener  the listener.
     * 
     * @return A boolean.
     */
    public boolean hasListener(EventListener listener) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[14]++;
        List list = Arrays.asList(this.listenerList.getListenerList());
        return list.contains(listener);
    }
    
    /**
     * Notifies all registered listeners that the dial layer has changed.
     * The {@link DialLayerChangeEvent} provides information about the change.
     *
     * @param event  information about the change to the axis.
     */
    protected void notifyListeners(DialLayerChangeEvent event) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[15]++;
        Object[] listeners = this.listenerList.getListenerList();
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.loops[1]--;
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.loops[2]--;
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.loops[3]++;
}
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((listeners[i] == DialLayerChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.branches[5]++;
                ((DialLayerChangeListener) listeners[i + 1]).dialLayerChanged(
                        event);
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[18]++;

            } else {
  CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.branches[6]++;}
        }
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[19]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt.statements[20]++;
    }
    
}

class CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.AbstractDialLayer.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$aq7wvhvpqjnd9qzl3sfzx4nfiebnu7ospt () {
    super("org.jfree.chart.plot.dial.AbstractDialLayer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.AbstractDialLayer.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
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

