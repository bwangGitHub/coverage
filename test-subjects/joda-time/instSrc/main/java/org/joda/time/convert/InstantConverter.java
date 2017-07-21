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
package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

/**
 * InstantConverter defines how an object is converted to milliseconds/chronology.
 * <p>
 * The two methods in this interface must be called in order, as the
 * <code>getInstantMillis</code> method relies on the result of the
 * <code>getChronology</code> method being passed in.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public interface InstantConverter extends Converter {

    /**
     * Extracts the chronology from an object of this converter's type
     * where the time zone is specified.
     * 
     * @param object  the object to convert
     * @param zone  the specified zone to use, null means default zone
     * @return the chronology, never null
     * @throws ClassCastException if the object is invalid
     */
    Chronology getChronology(Object object, DateTimeZone zone);

    /**
     * Extracts the chronology from an object of this converter's type
     * where the chronology may be specified.
     * <p>
     * If the chronology is non-null it should be used. If it is null, then the
     * object should be queried, and if it has no chronology then ISO default is used.
     * 
     * @param object  the object to convert
     * @param chrono  the chronology to use, null means use object
     * @return the chronology, never null
     * @throws ClassCastException if the object is invalid
     */
    Chronology getChronology(Object object, Chronology chrono);

    //-----------------------------------------------------------------------
    /**
     * Extracts the millis from an object of this converter's type.
     * <p>
     * The chronology passed in is the result of the call to <code>getChronology</code>.
     * 
     * @param object  the object to convert
     * @param chrono  the chronology to use, which is the non-null result of getChronology()
     * @return the millisecond instant
     * @throws ClassCastException if the object is invalid
     * @throws IllegalArgumentException if object conversion fails
     */
    long getInstantMillis(Object object, Chronology chrono);

}

class CodeCoverCoverageCounter$1ozv9xerxbp2cpdzwhvyubckqftywl275 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ozv9xerxbp2cpdzwhvyubckqftywl275 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1ozv9xerxbp2cpdzwhvyubckqftywl275 () {
    super("org.joda.time.convert.InstantConverter.java");
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
    log.startNamedSection("org.joda.time.convert.InstantConverter.java");
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

