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
 * CategoryDataset.java
 * --------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 21-Aug-2001)
 * --------------------------
 * 21-Aug-2001 : Added standard header. Fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated e-mail address in header (DG);
 * 15-Oct-2001 : Moved to new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 17-Nov-2001 : Updated Javadoc comments (DG);
 * 04-Mar-2002 : Updated import statement (DG);
 * 23-Oct-2002 : Reorganised code (DG);
 * 10-Jan-2003 : Updated Javadocs (DG);
 * 21-Jan-2003 : Merged with TableDataset (which only existed in CVS) (DG);
 * 13-Mar-2003 : Added KeyedValues2DDataset interface (DG);
 * 23-Apr-2003 : Switched CategoryDataset and KeyedValues2DDataset so that 
 *               CategoryDataset is the super interface (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.category (DG);
 *
 */

package org.jfree.data.category;

import org.jfree.data.KeyedValues2D;
import org.jfree.data.general.Dataset;

/**
 * The interface for a dataset with one or more series, and values associated 
 * with categories.
 * <P>
 * The categories are represented by <code>Comparable</code> instance, with the
 * category label being provided by the <code>toString</code> method.
 */
public interface CategoryDataset extends KeyedValues2D, Dataset {

    // no additional methods required

}

class CodeCoverCoverageCounter$7vckrcherv6e6nf5fk6ckfxblhlha3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7vckrcherv6e6nf5fk6ckfxblhlha3l ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$7vckrcherv6e6nf5fk6ckfxblhlha3l () {
    super("org.jfree.data.category.CategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("org.jfree.data.category.CategoryDataset.java");
      for (int i = 1; i <= -1; i++) {
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

