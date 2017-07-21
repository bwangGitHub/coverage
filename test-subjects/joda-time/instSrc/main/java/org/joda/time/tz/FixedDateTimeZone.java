/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.tz;

import org.joda.time.DateTimeZone;

/**
 * Basic DateTimeZone implementation that has a fixed name key and offsets.
 * <p>
 * FixedDateTimeZone is thread-safe and immutable.
 * 
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class FixedDateTimeZone extends DateTimeZone {
  static {
    CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.ping();
  }


    private static final long serialVersionUID = -3513011772763289092L;
  static {
    CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[1]++;
  }

    private final String iNameKey;
    private final int iWallOffset;
    private final int iStandardOffset;

    public FixedDateTimeZone(String id, String nameKey,
                             int wallOffset, int standardOffset) {
        super(id);
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[2]++;
        iNameKey = nameKey;
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[3]++;
        iWallOffset = wallOffset;
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[4]++;
        iStandardOffset = standardOffset;
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[5]++;
    }

    public String getNameKey(long instant) {
        return iNameKey;
    }

    public int getOffset(long instant) {
        return iWallOffset;
    }

    public int getStandardOffset(long instant) {
        return iStandardOffset;
    }

    public int getOffsetFromLocal(long instantLocal) {
        return iWallOffset;
    }

    public boolean isFixed() {
        return true;
    }

    public long nextTransition(long instant) {
        return instant;
    }

    public long previousTransition(long instant) {
        return instant;
    }

    /**
     * Override to return the correct timzone instance.
     * @since 1.5
     */
    public java.util.TimeZone toTimeZone() {
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[6]++;
        String id = getID();
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((id.length() == 6) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((id.startsWith("+")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((id.startsWith("-")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.branches[1]++;
            // standard format offset [+-]hh:mm
            // our ID is without any prefix, so we need to add the GMT back
            return java.util.TimeZone.getTimeZone("GMT" + getID());

        } else {
  CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.branches[2]++;}
        // unusual offset, so setup a SimpleTimeZone as best we can
        return new java.util.SimpleTimeZone(iWallOffset, getID());
    }

    public boolean equals(Object obj) {
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.branches[4]++;}
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof FixedDateTimeZone) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.branches[5]++;
CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.statements[10]++;
            FixedDateTimeZone other = (FixedDateTimeZone)obj;
            return
                getID().equals(other.getID()) &&
                iStandardOffset == other.iStandardOffset &&
                iWallOffset == other.iWallOffset;

        } else {
  CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5.branches[6]++;}
        return false;
    }

    public int hashCode() {
        return getID().hashCode() + 37 * iStandardOffset + 31 * iWallOffset;
    }

}

class CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.tz.FixedDateTimeZone.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$bjwzgg6nxc7a83lc0k99s5uwhyxe1vijj5 () {
    super("org.joda.time.tz.FixedDateTimeZone.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.tz.FixedDateTimeZone.java");
      for (int i = 1; i <= 10; i++) {
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
    for (int i = 1; i <= 3; i++) {
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

