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

import java.util.Date;

import org.joda.time.Chronology;

/**
 * DateConverter converts a java util Date to an instant or partial.
 * The Date is converted to milliseconds in the ISO chronology.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
final class DateConverter extends AbstractConverter
        implements InstantConverter, PartialConverter {
  static {
    CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap.ping();
  }


    /**
     * Singleton instance.
     */
    static final DateConverter INSTANCE = new DateConverter();
  static {
    CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected DateConverter() {
        super();
CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the millis, which is the Date millis value.
     * 
     * @param object  the Date to convert, must not be null
     * @param chrono  the non-null result of getChronology
     * @return the millisecond value
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public long getInstantMillis(Object object, Chronology chrono) {
CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap.statements[3]++;
        Date date = (Date) object;
        return date.getTime();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns Date.class.
     * 
     * @return Date.class
     */
    public Class<?> getSupportedType() {
        return Date.class;
    }

}

class CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5oppigc16g9p0p5htdbih0mvhqap () {
    super("org.joda.time.convert.DateConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("org.joda.time.convert.DateConverter.java");
      for (int i = 1; i <= 3; i++) {
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

