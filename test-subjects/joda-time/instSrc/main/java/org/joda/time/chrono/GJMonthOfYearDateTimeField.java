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
package org.joda.time.chrono;

import java.util.Locale;

/**
 * Provides time calculations for the month of the year component of time.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
final class GJMonthOfYearDateTimeField extends BasicMonthOfYearDateTimeField {
  static {
    CodeCoverCoverageCounter$8ztsq86thjv8shdjygmkjtnn4i0nd7t7yny8b7jm3tjc32ap.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = -4748157875845286249L;
  static {
    CodeCoverCoverageCounter$8ztsq86thjv8shdjygmkjtnn4i0nd7t7yny8b7jm3tjc32ap.statements[1]++;
  }

    /**
     * Restricted constructor
     */
    GJMonthOfYearDateTimeField(BasicChronology chronology) {
        super(chronology, 2);
CodeCoverCoverageCounter$8ztsq86thjv8shdjygmkjtnn4i0nd7t7yny8b7jm3tjc32ap.statements[2]++;
    }

    //-----------------------------------------------------------------------
    public String getAsText(int fieldValue, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).monthOfYearValueToText(fieldValue);
    }

    //-----------------------------------------------------------------------
    public String getAsShortText(int fieldValue, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).monthOfYearValueToShortText(fieldValue);
    }

    //-----------------------------------------------------------------------
    protected int convertText(String text, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).monthOfYearTextToValue(text);
    }

    //-----------------------------------------------------------------------
    public int getMaximumTextLength(Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getMonthMaxTextLength();
    }

    //-----------------------------------------------------------------------
    public int getMaximumShortTextLength(Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getMonthMaxShortTextLength();
    }

}

class CodeCoverCoverageCounter$8ztsq86thjv8shdjygmkjtnn4i0nd7t7yny8b7jm3tjc32ap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8ztsq86thjv8shdjygmkjtnn4i0nd7t7yny8b7jm3tjc32ap ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$8ztsq86thjv8shdjygmkjtnn4i0nd7t7yny8b7jm3tjc32ap () {
    super("org.joda.time.chrono.GJMonthOfYearDateTimeField.java");
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
    log.startNamedSection("org.joda.time.chrono.GJMonthOfYearDateTimeField.java");
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

