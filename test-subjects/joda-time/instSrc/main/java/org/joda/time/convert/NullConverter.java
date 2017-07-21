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
import org.joda.time.DateTimeUtils;
import org.joda.time.Period;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;

/**
 * NullConverter converts null to an instant, partial, duration, period
 * or interval. Null means now for instant/partial, zero for duration/period
 * and from now to now for interval.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
class NullConverter extends AbstractConverter
        implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter {
  static {
    CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.ping();
  }


    /**
     * Singleton instance.
     */
    static final NullConverter INSTANCE = new NullConverter();
  static {
    CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected NullConverter() {
        super();
CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the millisecond duration, which is zero.
     * 
     * @param object  the object to convert, which is null
     * @return the millisecond duration
     */
    public long getDurationMillis(Object object) {
        return 0L;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the given ReadWritableDuration to zero milliseconds.
     *
     * @param duration duration to get modified
     * @param object  the object to convert, which is null
     * @param chrono  the chronology to use
     * @throws NullPointerException if the duration is null
     */
    public void setInto(ReadWritablePeriod duration, Object object, Chronology chrono) {
        duration.setPeriod((Period) null);
CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.statements[3]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts interval endpoint values from an object of this converter's
     * type, and sets them into the given ReadWritableInterval.
     *
     * @param writableInterval interval to get modified, not null
     * @param object  the object to convert, which is null
     * @param chrono  the chronology to use, may be null
     * @throws NullPointerException if the interval is null
     */
    public void setInto(ReadWritableInterval writableInterval, Object object, Chronology chrono) {
        writableInterval.setChronology(chrono);
CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.statements[4]++;
CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.statements[5]++;
        long now = DateTimeUtils.currentTimeMillis();
        writableInterval.setInterval(now, now);
CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29.statements[6]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns null.
     * 
     * @return null
     */
    public Class<?> getSupportedType() {
        return null;
    }

}

class CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$6ivv1zfcspoqyaf190h41ekd8x29 () {
    super("org.joda.time.convert.NullConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("org.joda.time.convert.NullConverter.java");
      for (int i = 1; i <= 6; i++) {
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

