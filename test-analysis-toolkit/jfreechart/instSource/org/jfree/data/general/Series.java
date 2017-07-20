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
 * -----------
 * Series.java
 * -----------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 15-Nov-2001 : Version 1 (DG);
 * 29-Nov-2001 : Added cloning and property change support (DG);
 * 30-Jan-2002 : Added a description attribute and changed the constructors to 
 *               protected (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Added equals() method (DG);
 * 26-Jun-2003 : Changed listener list to use EventListenerList - see bug 
 *               757027 (DG);
 * 15-Oct-2003 : Added a flag to control whether or not change events are sent 
 *               to registered listeners (DG);
 * 19-May-2005 : Made abstract (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 04-May-2006 : Updated API docs (DG);
 * 26-Sep-2007 : Added isEmpty() and getItemCount() methods (DG);
 * 
 */

package org.jfree.data.general;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.swing.event.EventListenerList;

import org.jfree.util.ObjectUtilities;

/**
 * Base class representing a data series.  Subclasses are left to implement the
 * actual data structures.
 * <P>
 * The series has two properties ("Key" and "Description") for which you can
 * register a <code>PropertyChangeListener</code>.
 * <P>
 * You can also register a {@link SeriesChangeListener} to receive notification 
 * of changes to the series data.
 */
public abstract class Series implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$co1ntq4h75hk3iw69.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6906561437538683581L;
  static {
    CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[1]++;
  }
    
    /** The key for the series. */
    private Comparable key;

    /** A description of the series. */
    private String description;

    /** Storage for registered change listeners. */
    private EventListenerList listeners;

    /** Object to support property change notification. */
    private PropertyChangeSupport propertyChangeSupport;

    /** A flag that controls whether or not changes are notified. */
    private boolean notify;

    /**
     * Creates a new series with the specified key.  
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    protected Series(Comparable key) {
        this(key, null);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[2]++;
    }

    /**
     * Creates a new series with the specified key and description.
     *
     * @param key  the series key (<code>null</code> NOT permitted).
     * @param description  the series description (<code>null</code> permitted).
     */
    protected Series(Comparable key, String description) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[1]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[2]++;}
        this.key = key;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[4]++;
        this.description = description;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[5]++;
        this.listeners = new EventListenerList();
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[6]++;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[7]++;
        this.notify = true;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[8]++;   
    }

    /**
     * Returns the key for the series.
     *
     * @return The series key (never <code>null</code>).
     * 
     * @see #setKey(Comparable)
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Sets the key for the series and sends a <code>PropertyChangeEvent</code> 
     * (with the property name "Key") to all registered listeners.
     *
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @see #getKey()
     */
    public void setKey(Comparable key) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[3]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[4]++;}
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[10]++;
        Comparable old = this.key;
        this.key = key;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[11]++;
        this.propertyChangeSupport.firePropertyChange("Key", old, key);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[12]++;
    }

    /**
     * Returns a description of the series.
     *
     * @return The series description (possibly <code>null</code>).
     * 
     * @see #setDescription(String)
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the series and sends a 
     * <code>PropertyChangeEvent</code> to all registered listeners.
     *
     * @param description  the description (<code>null</code> permitted).
     * 
     * @see #getDescription()
     */
    public void setDescription(String description) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[13]++;
        String old = this.description;
        this.description = description;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[14]++;
        this.propertyChangeSupport.firePropertyChange("Description", old, 
                description);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[15]++;
    }

    /**
     * Returns the flag that controls whether or not change events are sent to 
     * registered listeners.
     * 
     * @return A boolean.
     * 
     * @see #setNotify(boolean)
     */
    public boolean getNotify() {
        return this.notify;
    }
    
    /**
     * Sets the flag that controls whether or not change events are sent to 
     * registered listeners.
     * 
     * @param notify  the new value of the flag.
     * 
     * @see #getNotify()
     */
    public void setNotify(boolean notify) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.notify != notify) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[5]++;
            this.notify = notify;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[17]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[18]++;

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[6]++;}
    }
    
    /**
     * Returns <code>true</code> if the series contains no data items, and
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @since 1.0.7
     */
    public boolean isEmpty() {
        return (getItemCount() == 0);
    }
    
    /**
     * Returns the number of data items in the series.
     * 
     * @return The number of data items in the series.
     */
    public abstract int getItemCount();
    
    /**
     * Returns a clone of the series.
     * <P>
     * Notes:
     * <ul>
     * <li>No need to clone the name or description, since String object is 
     * immutable.</li>
     * <li>We set the listener list to empty, since the listeners did not 
     * register with the clone.</li>
     * <li>Same applies to the PropertyChangeSupport instance.</li>
     * </ul>
     *
     * @return A clone of the series.
     * 
     * @throws CloneNotSupportedException  not thrown by this class, but 
     *         subclasses may differ.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[19]++;

        Series clone = (Series) super.clone();
        clone.listeners = new EventListenerList();
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[20]++;
        clone.propertyChangeSupport = new PropertyChangeSupport(clone);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[21]++;
        return clone;

    }

    /**
     * Tests the series for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[8]++;}
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof Series) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[10]++;}
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[24]++;
        Series that = (Series) obj;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getKey().equals(that.getKey())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[12]++;}
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(getDescription(), that.getDescription())) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[14]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = this.key.hashCode();
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[27]++;
        result = 29 * result + (this.description != null 
                ? this.description.hashCode() : 0);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[28]++;
        return result;
    }

    /**
     * Registers an object with this series, to receive notification whenever 
     * the series changes.
     * <P>
     * Objects being registered must implement the {@link SeriesChangeListener} 
     * interface.
     *
     * @param listener  the listener to register.
     */
    public void addChangeListener(SeriesChangeListener listener) {
        this.listeners.add(SeriesChangeListener.class, listener);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[29]++;
    }

    /**
     * Deregisters an object, so that it not longer receives notification 
     * whenever the series changes.
     *
     * @param listener  the listener to deregister.
     */
    public void removeChangeListener(SeriesChangeListener listener) {
        this.listeners.remove(SeriesChangeListener.class, listener);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[30]++;
    }

    /**
     * General method for signalling to registered listeners that the series
     * has been changed.
     */
    public void fireSeriesChanged() {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.notify) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[15]++;
            notifyListeners(new SeriesChangeEvent(this));
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[32]++;

        } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[16]++;}
    }

    /**
     * Sends a change event to all registered listeners.
     *
     * @param event  contains information about the event that triggered the 
     *               notification.
     */
    protected void notifyListeners(SeriesChangeEvent event) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[33]++;

        Object[] listenerList = this.listeners.getListenerList();
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[34]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i = listenerList.length - 2;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.loops[1]--;
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.loops[2]--;
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.loops[3]++;
}
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((listenerList[i] == SeriesChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$co1ntq4h75hk3iw69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[17]++;
                ((SeriesChangeListener) listenerList[i + 1]).seriesChanged(
                        event);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[36]++;

            } else {
  CodeCoverCoverageCounter$co1ntq4h75hk3iw69.branches[18]++;}
        }

    }

    /**
     * Adds a property change listener to the series.
     *
     * @param listener  the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[37]++;
    }

    /**
     * Removes a property change listener from the series.
     *
     * @param listener The listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[38]++;
    }

    /**
     * Fires a property change event.
     *
     * @param property  the property key.
     * @param oldValue  the old value.
     * @param newValue  the new value.
     */
    protected void firePropertyChange(String property, Object oldValue, 
            Object newValue) {
        this.propertyChangeSupport.firePropertyChange(property, oldValue, 
                newValue);
CodeCoverCoverageCounter$co1ntq4h75hk3iw69.statements[39]++;
    }

}

class CodeCoverCoverageCounter$co1ntq4h75hk3iw69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$co1ntq4h75hk3iw69 ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.general.Series.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$co1ntq4h75hk3iw69 () {
    super("org.jfree.data.general.Series.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.Series.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
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

