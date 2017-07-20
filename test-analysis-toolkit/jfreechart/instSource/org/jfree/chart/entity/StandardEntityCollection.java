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
 * -----------------------------
 * StandardEntityCollection.java
 * -----------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-May-2002 : Version 1 (DG);
 * 26-Jun-2002 : Added iterator() method (DG);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 19-May-2004 : Implemented Serializable (DG);
 * 29-Sep-2004 : Renamed addEntity() --> add() and addEntities() 
 *               --> addAll() (DG);
 * 19-Jan-2005 : Changed storage from Collection --> List (DG);
 * 20-May-2005 : Fixed bug 1113521 - inefficiency in getEntity() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Implemented PublicCloneable and fixed clone() method (DG);
 *
 */

package org.jfree.chart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A standard implementation of the {@link EntityCollection} interface.
 */
public class StandardEntityCollection implements EntityCollection, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5384773031184897047L;
  static {
    CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[1]++;
  }
    
    /** Storage for the entities. */
    private List entities;

    /**
     * Constructs a new entity collection (initially empty).
     */
    public StandardEntityCollection() {
        this.entities = new java.util.ArrayList();
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[2]++;
    }

    /**
     * Returns the number of entities in the collection.
     * 
     * @return The entity count.
     */
    public int getEntityCount() {
        return this.entities.size();
    }
    
    /**
     * Returns a chart entity from the collection.
     * 
     * @param index  the entity index.
     * 
     * @return The entity.
     * 
     * @see #add(ChartEntity)
     */
    public ChartEntity getEntity(int index) {
        return (ChartEntity) this.entities.get(index);
    }
    
    /**
     * Clears all the entities from the collection.
     */
    public void clear() {
        this.entities.clear();
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[3]++;
    }

    /**
     * Adds an entity to the collection.
     *
     * @param entity  the entity (<code>null</code> not permitted).
     */
    public void add(ChartEntity entity) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((entity == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[1]++;
            throw new IllegalArgumentException("Null 'entity' argument.");

        } else {
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[2]++;}
        this.entities.add(entity);
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[5]++;
    }
    
    /**
     * Adds all the entities from the specified collection.
     * 
     * @param collection  the collection of entities (<code>null</code> not
     *     permitted).
     */
    public void addAll(EntityCollection collection) {
        this.entities.addAll(collection.getEntities());
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[6]++;
    }

    /**
     * Returns the last entity in the list with an area that encloses the 
     * specified coordinates, or <code>null</code> if there is no such entity.
     *
     * @param x  the x coordinate.
     * @param y  the y coordinate.
     *
     * @return The entity (possibly <code>null</code>).
     */
    public ChartEntity getEntity(double x, double y) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[7]++;
        int entityCount = this.entities.size();
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = entityCount - 1;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[1]--;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[2]--;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[3]++;
}
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[9]++;
            ChartEntity entity = (ChartEntity) this.entities.get(i);
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((entity.getArea().contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[3]++;
                return entity;

            } else {
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[4]++;}
        }
        return null;
    }

    /**
     * Returns the entities in an unmodifiable collection.
     * 
     * @return The entities.
     */
    public Collection getEntities() {
        return Collections.unmodifiableCollection(this.entities);
    }
    
    /**
     * Returns an iterator for the entities in the collection.
     *
     * @return An iterator.
     */
    public Iterator iterator() {
        return this.entities.iterator();
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[5]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[6]++;}
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof StandardEntityCollection) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[7]++;
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[13]++;
            StandardEntityCollection that = (StandardEntityCollection) obj;
            return ObjectUtilities.equal(this.entities, that.entities);

        } else {
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.branches[8]++;}
        return false;
    }

    /**
     * Returns a clone of this entity collection.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[14]++;
        StandardEntityCollection clone 
                = (StandardEntityCollection) super.clone();
        clone.entities = new java.util.ArrayList(this.entities.size());
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[15]++;
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[16]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < this.entities.size()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[4]--;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[5]--;
  CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.loops[6]++;
}
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[17]++;
            ChartEntity entity = (ChartEntity) this.entities.get(i);
            clone.entities.add(entity.clone());
CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5.statements[18]++;
        }
        return clone;   
    }

}

class CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.StandardEntityCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$7hvhc99xki8cwvaj396wkxhm0gbbe71fqn1qb454yxzz5 () {
    super("org.jfree.chart.entity.StandardEntityCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.entity.StandardEntityCollection.java");
      for (int i = 1; i <= 18; i++) {
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
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

