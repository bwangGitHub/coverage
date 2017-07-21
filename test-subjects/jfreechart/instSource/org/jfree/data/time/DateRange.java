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
 * --------------
 * DateRange.java
 * --------------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Bill Kelemen;
 *
 * Changes
 * -------
 * 22-Apr-2002 : Version 1 based on code by Bill Kelemen (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Sep-2003 : Minor Javadoc update (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import org.jfree.data.Range;

/**
 * A range specified in terms of two <code>java.util.Date</code> objects.  
 * Instances of this class are immutable.
 */
public class DateRange extends Range implements Serializable {
  static {
    CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4705682568375418157L;
  static {
    CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[1]++;
  }
    
    /** The lower bound for the range. */
    private Date lowerDate;

    /** The upper bound for the range. */
    private Date upperDate;

    /**
     * Default constructor.
     */
    public DateRange() {
        this(new Date(0), new Date(1));
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[2]++;
    }

    /**
     * Constructs a new range.
     *
     * @param lower  the lower bound (<code>null</code> not permitted).
     * @param upper  the upper bound (<code>null</code> not permitted).
     */
    public DateRange(Date lower, Date upper) {

        super(lower.getTime(), upper.getTime());
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[3]++;
        this.lowerDate = lower;
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[4]++;
        this.upperDate = upper;
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[5]++;

    }

    /**
     * Constructs a new range using two values that will be interpreted as 
     * "milliseconds since midnight GMT, 1-Jan-1970".
     *
     * @param lower  the lower (oldest) date.
     * @param upper  the upper (most recent) date.
     */
    public DateRange(double lower, double upper) {
        super(lower, upper);
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[6]++;
        this.lowerDate = new Date((long) lower);
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[7]++;
        this.upperDate = new Date((long) upper);
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[8]++;
    }

    /**
     * Constructs a new range that is based on another {@link Range}.  The 
     * other range does not have to be a {@link DateRange}.  If it is not, the 
     * upper and lower bounds are evaluated as milliseconds since midnight 
     * GMT, 1-Jan-1970.
     *
     * @param other  the other range (<code>null</code> not permitted).
     */
    public DateRange(Range other) {
        this(other.getLowerBound(), other.getUpperBound());
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[9]++;
    }

    /**
     * Returns the lower (earlier) date for the range.
     *
     * @return The lower date for the range.
     */
    public Date getLowerDate() {
        return this.lowerDate;
    }

    /**
     * Returns the upper (later) date for the range.
     *
     * @return The upper date for the range.
     */
    public Date getUpperDate() {
        return this.upperDate;
    }
    
    /**
     * Returns a string representing the date range (useful for debugging).
     * 
     * @return A string representing the date range.
     */
    public String toString() {
CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d.statements[10]++;
        DateFormat df = DateFormat.getDateTimeInstance();
        return "[" + df.format(this.lowerDate) + " --> " 
            + df.format(this.upperDate) + "]";
    }

}

class CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2vr4llyabyce5qxwtbhv1d () {
    super("org.jfree.data.time.DateRange.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.jfree.data.time.DateRange.java");
      for (int i = 1; i <= 10; i++) {
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

