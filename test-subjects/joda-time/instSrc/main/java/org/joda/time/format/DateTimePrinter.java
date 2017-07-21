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
package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

/**
 * Internal interface for creating textual representations of datetimes.
 * <p>
 * Application users will rarely use this class directly. Instead, you
 * will use one of the factory classes to create a {@link DateTimeFormatter}.
 * <p>
 * The factory classes are:<br />
 * - {@link DateTimeFormatterBuilder}<br />
 * - {@link DateTimeFormat}<br />
 * - {@link ISODateTimeFormat}<br />
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @see DateTimeFormatterBuilder
 * @see DateTimeFormat
 * @see ISODateTimeFormat
 * @since 1.0
 */
public interface DateTimePrinter {

    /**
     * Returns the expected maximum number of characters produced.
     * The actual amount should rarely exceed this estimate.
     * 
     * @return the estimated length
     */
    int estimatePrintedLength();

    //-----------------------------------------------------------------------
    /**
     * Prints an instant from milliseconds since 1970-01-01T00:00:00Z,
     * using the given Chronology.
     *
     * @param buf  formatted instant is appended to this buffer, not null
     * @param instant  millis since 1970-01-01T00:00:00Z
     * @param chrono  the chronology to use, not null
     * @param displayOffset  if a time zone offset is printed, force it to use
     * this millisecond value
     * @param displayZone  the time zone to use, null means local time
     * @param locale  the locale to use, null means default locale
     */
    void printTo(StringBuffer buf, long instant, Chronology chrono,
                 int displayOffset, DateTimeZone displayZone, Locale locale);

    /**
     * Prints an instant from milliseconds since 1970-01-01T00:00:00Z,
     * using the given Chronology.
     *
     * @param out  formatted instant is written out
     * @param instant  millis since 1970-01-01T00:00:00Z
     * @param chrono  the chronology to use, not null
     * @param displayOffset  if a time zone offset is printed, force it to use
     * this millisecond value
     * @param displayZone  the time zone to use, null means local time
     * @param locale  the locale to use, null means default locale
     */
    void printTo(Writer out, long instant, Chronology chrono,
                 int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException;

    //-----------------------------------------------------------------------
    /**
     * Prints a ReadablePartial.
     *
     * @param buf  formatted partial is appended to this buffer, not null
     * @param partial  partial to format, not null
     * @param locale  the locale to use, null means default locale
     */
    void printTo(StringBuffer buf, ReadablePartial partial, Locale locale);

    /**
     * Prints a ReadablePartial.
     *
     * @param out  formatted partial is written out, not null
     * @param partial  partial to format, not null
     * @param locale  the locale to use, null means default locale
     */
    void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException;

}

class CodeCoverCoverageCounter$7zjyp8azrifu6oh3at9n4a2ygibbyht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7zjyp8azrifu6oh3at9n4a2ygibbyht ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$7zjyp8azrifu6oh3at9n4a2ygibbyht () {
    super("org.joda.time.format.DateTimePrinter.java");
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
    log.startNamedSection("org.joda.time.format.DateTimePrinter.java");
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

