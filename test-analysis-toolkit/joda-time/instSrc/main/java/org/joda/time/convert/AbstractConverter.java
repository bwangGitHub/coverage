/*
 *  Copyright 2001-2006 Stephen Colebourne
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
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;

/**
 * AbstractConverter simplifies the process of implementing a converter.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class AbstractConverter implements Converter {
  static {
    CodeCoverCoverageCounter$aq7wvhvpqjnd9pi9677diam57djlhsfb6p.ping();
  }


    /**
     * Restricted constructor.
     */
    protected AbstractConverter() {
        super();
CodeCoverCoverageCounter$aq7wvhvpqjnd9pi9677diam57djlhsfb6p.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts the millis from an object of this convertor's type.
     * <p>
     * This implementation returns the current time.
     * 
     * @param object  the object to convert
     * @param chrono  the chronology to use, which is always non-null
     * @return the millisecond value
     */
    public long getInstantMillis(Object object, Chronology chrono) {
        return DateTimeUtils.currentTimeMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts the chronology from an object of this convertor's type
     * where the time zone is specified.
     * <p>
     * This implementation returns the ISO chronology.
     * 
     * @param object  the object to convert
     * @param zone  the specified zone to use, null means default zone
     * @return the chronology, never null
     */
    public Chronology getChronology(Object object, DateTimeZone zone) {
        return ISOChronology.getInstance(zone);
    }

    /**
     * Extracts the chronology from an object of this convertor's type
     * where the chronology is specified.
     * <p>
     * This implementation returns the chronology specified, or the
     * ISO chronology in the default zone if null passed in.
     * 
     * @param object  the object to convert
     * @param chrono  the chronology to use, null means ISO default
     * @return the chronology, never null
     */
    public Chronology getChronology(Object object, Chronology chrono) {
        return DateTimeUtils.getChronology(chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts the values of the partial from an object of this converter's type.
     * The chrono parameter is a hint to the converter, should it require a
     * chronology to aid in conversion.
     * <p>
     * This implementation calls {@link #getInstantMillis(Object, Chronology)}.
     * 
     * @param fieldSource  a partial that provides access to the fields.
     *  This partial may be incomplete and only getFieldType(int) should be used
     * @param object  the object to convert
     * @param chrono  the chronology to use, which is the non-null result of getChronology()
     * @return the array of field values that match the fieldSource, must be non-null valid
     * @throws ClassCastException if the object is invalid
     */
    public int[] getPartialValues(ReadablePartial fieldSource, Object object, Chronology chrono) {
CodeCoverCoverageCounter$aq7wvhvpqjnd9pi9677diam57djlhsfb6p.statements[2]++;
        long instant = getInstantMillis(object, chrono);
        return chrono.get(fieldSource, instant);
    }

    /**
     * Extracts the values of the partial from an object of this converter's type.
     * The chrono parameter is a hint to the converter, should it require a
     * chronology to aid in conversion.
     * <p>
     * This implementation calls {@link #getPartialValues(ReadablePartial, Object, Chronology)}.
     * 
     * @param fieldSource  a partial that provides access to the fields.
     *  This partial may be incomplete and only getFieldType(int) should be used
     * @param object  the object to convert
     * @param chrono  the chronology to use, which is the non-null result of getChronology()
     * @param parser  if converting from a String, the given parser is preferred
     * @return the array of field values that match the fieldSource, must be non-null valid
     * @throws ClassCastException if the object is invalid
     * @since 1.3
     */
    public int[] getPartialValues(ReadablePartial fieldSource,
            Object object, Chronology chrono, DateTimeFormatter parser) {
        return getPartialValues(fieldSource, object, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Selects a suitable period type for the given object.
     *
     * @param object  the object to examine
     * @return the period type, never null
     */
    public PeriodType getPeriodType(Object object) {
        return PeriodType.standard();
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the input is a ReadableInterval.
     * <p>
     * If it is, then the calling code should cast and copy the fields directly.
     *
     * @param object  the object to convert
     * @param chrono  the chronology to use, may be null
     * @return true if the input is a ReadableInterval
     */
    public boolean isReadableInterval(Object object, Chronology chrono) {
        return false;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a debugging string version of this converter.
     * 
     * @return a debugging string
     */
    public String toString() {
        return "Converter[" + (getSupportedType() == null ? "null" : getSupportedType().getName()) + "]";
    }

}

class CodeCoverCoverageCounter$aq7wvhvpqjnd9pi9677diam57djlhsfb6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aq7wvhvpqjnd9pi9677diam57djlhsfb6p ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$aq7wvhvpqjnd9pi9677diam57djlhsfb6p () {
    super("org.joda.time.convert.AbstractConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("org.joda.time.convert.AbstractConverter.java");
      for (int i = 1; i <= 2; i++) {
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

