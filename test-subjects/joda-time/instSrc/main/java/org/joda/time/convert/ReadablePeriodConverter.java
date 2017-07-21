/*
 *  Copyright 2001-2009 Stephen Colebourne
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
package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

/**
 * ReadablePeriodConverter extracts milliseconds and chronology from a ReadablePeriod.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
class ReadablePeriodConverter extends AbstractConverter
        implements PeriodConverter {
  static {
    CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9.ping();
  }


    /**
     * Singleton instance.
     */
    static final ReadablePeriodConverter INSTANCE = new ReadablePeriodConverter();
  static {
    CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected ReadablePeriodConverter() {
        super();
CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts duration values from an object of this converter's type, and
     * sets them into the given ReadWritablePeriod.
     *
     * @param duration duration to get modified
     * @param object  the object to convert, must not be null
     * @param chrono  the chronology to use
     * @throws NullPointerException if the duration or object is null
     * @throws ClassCastException if the object is an invalid type
     * @throws IllegalArgumentException if the object is invalid
     */
    public void setInto(ReadWritablePeriod duration, Object object, Chronology chrono) {
        duration.setPeriod((ReadablePeriod) object);
CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9.statements[3]++;
    }

    /**
     * Selects a suitable period type for the given object.
     *
     * @param object  the object to examine, must not be null
     * @return the period type from the readable duration
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public PeriodType getPeriodType(Object object) {
CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9.statements[4]++;
        ReadablePeriod period = (ReadablePeriod) object;
        return period.getPeriodType();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns ReadablePeriod class.
     * 
     * @return ReadablePeriod.class
     */
    public Class<?> getSupportedType() {
        return ReadablePeriod.class;
    }

}

class CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9 ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$11gwqgscgcvwxkvk4jul67xzre2dlorx4f0qpila63q9 () {
    super("org.joda.time.convert.ReadablePeriodConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("org.joda.time.convert.ReadablePeriodConverter.java");
      for (int i = 1; i <= 4; i++) {
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

