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
 * DefaultKeyedValues2DDataset.java
 * --------------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 13-Mar-2003 : Version 1 (copied from DefaultCategoryDataset) (DG);
 * 23-Apr-2003 : Moved implementation into the DefaultCategoryDataset 
 *               class (DG);
 *
 */

package org.jfree.data.general;

import java.io.Serializable;

import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A default implementation of the {@link KeyedValues2DDataset} interface.
 *
 */
public class DefaultKeyedValues2DDataset extends DefaultCategoryDataset
                                         implements KeyedValues2DDataset, 
                                                    Serializable {
  static {
    CodeCoverCoverageCounter$1pdafkh56m5d6zpbdrfq7xa6c2wcik0ewue3n0qmcknbcd4fvl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4288210771905990424L;
  static {
    CodeCoverCoverageCounter$1pdafkh56m5d6zpbdrfq7xa6c2wcik0ewue3n0qmcknbcd4fvl.statements[1]++;
  }
    
    // no new methods
    
}

class CodeCoverCoverageCounter$1pdafkh56m5d6zpbdrfq7xa6c2wcik0ewue3n0qmcknbcd4fvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1pdafkh56m5d6zpbdrfq7xa6c2wcik0ewue3n0qmcknbcd4fvl ());
  }
    public static long[] statements = new long[2];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1pdafkh56m5d6zpbdrfq7xa6c2wcik0ewue3n0qmcknbcd4fvl () {
    super("org.jfree.data.general.DefaultKeyedValues2DDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.DefaultKeyedValues2DDataset.java");
      for (int i = 1; i <= 1; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

