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
 * AbstractDataset.java
 * --------------------
 * (C)opyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu (for Astrium and EADS Corporate Research 
 *                   Center);
 *
 * Changes (from 21-Aug-2001)
 * --------------------------
 * 21-Aug-2001 : Added standard header. Fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated e-mail address in header (DG);
 * 15-Oct-2001 : Moved to new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 17-Nov-2001 : Changed constructor from public to protected, created new 
 *               AbstractSeriesDataset class and transferred series-related 
 *               methods, updated Javadoc comments (DG);
 * 04-Mar-2002 : Updated import statements (DG);
 * 11-Jun-2002 : Updated for change in the event constructor (DG);
 * 07-Aug-2002 : Changed listener list to use  
 *               javax.swing.event.EventListenerList (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 27-Mar-2003 : Implemented Serializable (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 08-Sep-2003 : Serialization fixes (NB);
 * 11-Sep-2003 : Cloning Fixes (NB);
 * 01-Jun-2005 : Added hasListener() method for unit testing (DG);
 * 
 */

package org.jfree.data.general;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import javax.swing.event.EventListenerList;

/**
 * An abstract implementation of the {@link Dataset} interface, containing a 
 * mechanism for registering change listeners.
 */
public abstract class AbstractDataset implements Dataset, 
                                                 Cloneable, 
                                                 Serializable,
                                                 ObjectInputValidation {
  static {
    CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1918768939869230744L;
  static {
    CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[1]++;
  }
    
    /** The group that the dataset belongs to. */
    private DatasetGroup group;

    /** Storage for registered change listeners. */
    private transient EventListenerList listenerList;

    /**
     * Constructs a dataset. By default, the dataset is assigned to its own 
     * group.
     */
    protected AbstractDataset() {
        this.group = new DatasetGroup();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[2]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[3]++;
    }

    /**
     * Returns the dataset group for the dataset.
     *
     * @return The group.
     */
    public DatasetGroup getGroup() {
        return this.group;
    }

    /**
     * Sets the dataset group for the dataset.
     *
     * @param group  the group (<code>null</code> not permitted).
     */
    public void setGroup(DatasetGroup group) {
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((group == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.branches[1]++;
            throw new IllegalArgumentException("Null 'group' argument.");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.branches[2]++;}
        this.group = group;
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[5]++;
    }

    /**
     * Registers an object to receive notification of changes to the dataset.
     *
     * @param listener  the object to register.
     */
    public void addChangeListener(DatasetChangeListener listener) {
        this.listenerList.add(DatasetChangeListener.class, listener);
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[6]++;
    }

    /**
     * Deregisters an object so that it no longer receives notification of 
     * changes to the dataset.
     *
     * @param listener  the object to deregister.
     */
    public void removeChangeListener(DatasetChangeListener listener) {
        this.listenerList.remove(DatasetChangeListener.class, listener);
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[7]++;
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
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[8]++;
        List list = Arrays.asList(this.listenerList.getListenerList());
        return list.contains(listener);
    }
    
    /**
     * Notifies all registered listeners that the dataset has changed.
     */
    protected void fireDatasetChanged() {
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[9]++;
    }

    /**
     * Notifies all registered listeners that the dataset has changed.
     *
     * @param event  contains information about the event that triggered the 
     *               notification.
     */
    protected void notifyListeners(DatasetChangeEvent event) {
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[10]++;

        Object[] listeners = this.listenerList.getListenerList();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.loops[1]--;
  CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.loops[2]--;
  CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.loops[3]++;
}
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((listeners[i] == DatasetChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.branches[3]++;
                ((DatasetChangeListener) listeners[i + 1]).datasetChanged(
                    event
                );
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[13]++;

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.branches[4]++;}
        }

    }

    /**
     * Returns a clone of the dataset. The cloned dataset will NOT include the 
     * {@link DatasetChangeListener} references that have been registered with 
     * this dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the dataset does not support 
     *                                     cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[14]++;
        AbstractDataset clone = (AbstractDataset) super.clone();
        clone.listenerList = new EventListenerList();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[15]++;
        return clone;    
    }
    
    /**
     * Handles serialization.
     *
     * @param stream  the output stream.
     *
     * @throws IOException if there is an I/O problem.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[16]++;
    }

    /**
     * Restores a serialized object.
     *
     * @param stream  the input stream.
     *
     * @throws IOException if there is an I/O problem.
     * @throws ClassNotFoundException if there is a problem loading a class.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[17]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[18]++;
        stream.registerValidation(this, 10);
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[19]++;  // see comments about priority of
                                              // 10 in validateObject() 
    }
 
    /**
     * Validates the object. We use this opportunity to call listeners who have 
     * registered during the deserialization process, as listeners are not 
     * serialized. This method is called by the serialization system after the 
     * entire graph is read.
     *  
     * This object has registered itself to the system with a priority of 10. 
     * Other callbacks may register with a higher priority number to be called 
     * before this object, or with a lower priority number to be called after 
     * the listeners were notified.
     * 
     * All listeners are supposed to have register by now, either in their 
     * readObject or validateObject methods. Notify them that this dataset has 
     * changed.  
     *
     * @exception InvalidObjectException If the object cannot validate itself.
     */
    public void validateObject() throws InvalidObjectException {
       fireDatasetChanged();
CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5.statements[20]++;
    }
   
}

class CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5 ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.data.general.AbstractDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$7mye35nvnajjyjct5vhecjju51wi3j5 () {
    super("org.jfree.data.general.AbstractDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.AbstractDataset.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

