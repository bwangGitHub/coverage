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

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Factory that creates instances of PeriodFormatter.
 * <p>
 * Period formatting is performed by the {@link PeriodFormatter} class.
 * Three classes provide factory methods to create formatters, and this is one.
 * The others are {@link ISOPeriodFormat} and {@link PeriodFormatterBuilder}.
 * <p>
 * PeriodFormat is thread-safe and immutable, and the formatters it returns
 * are as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see ISOPeriodFormat
 * @see PeriodFormatterBuilder
 */
public class PeriodFormat {
  static {
    CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.ping();
  }


    /**
     * The resource bundle name.
     */
    private static final String BUNDLE_NAME = "org.joda.time.format.messages";
  static {
    CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[1]++;
  }
    /**
     * The created formatters.
     */
    private static final ConcurrentMap<Locale, PeriodFormatter> FORMATTERS = new ConcurrentHashMap<Locale, PeriodFormatter>();
  static {
    CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[2]++;
  }

    /**
     * Constructor.
     *
     * @since 1.1 (previously private)
     */
    protected PeriodFormat() {
        super();
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[3]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the default formatter that outputs words in English.
     * <p>
     * This calls {@link #wordBased(Locale)} using a locale of {@code ENGLISH}.
     * 
     * @return the formatter, not null
     */
    public static PeriodFormatter getDefault() {
        return wordBased(Locale.ENGLISH);
    }

    /**
     * Returns a word based formatter for the JDK default locale.
     * <p>
     * This calls {@link #wordBased(Locale)} using the {@link Locale#getDefault() default locale}.
     * 
     * @return the formatter, not null
     * @since 2.0
     */
    public static PeriodFormatter wordBased() {
        return wordBased(Locale.getDefault());
    }

    /**
     * Returns a word based formatter for the specified locale.
     * <p>
     * The words are configured in a resource bundle text file -
     * {@code org.joda.time.format.messages}.
     * This can be added to via the normal classpath resource bundle mechanisms.
     * <p>
     * Available languages are English, German, Dutch, French, Spanish and Portuguese.
     * 
     * @return the formatter, not null
     * @since 2.0
     */
    public static PeriodFormatter wordBased(Locale locale) {
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[4]++;
        PeriodFormatter pf = FORMATTERS.get(locale);
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((pf == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.branches[1]++;
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[6]++;
            ResourceBundle b = ResourceBundle.getBundle(BUNDLE_NAME, locale);
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[7]++;
            String[] variants = {
                    b.getString("PeriodFormat.space"), b.getString("PeriodFormat.comma"),
                    b.getString("PeriodFormat.commandand"), b.getString("PeriodFormat.commaspaceand")};
            pf = new PeriodFormatterBuilder()
                .appendYears()
                .appendSuffix(b.getString("PeriodFormat.year"), b.getString("PeriodFormat.years"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendMonths()
                .appendSuffix(b.getString("PeriodFormat.month"), b.getString("PeriodFormat.months"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendWeeks()
                .appendSuffix(b.getString("PeriodFormat.week"), b.getString("PeriodFormat.weeks"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendDays()
                .appendSuffix(b.getString("PeriodFormat.day"), b.getString("PeriodFormat.days"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendHours()
                .appendSuffix(b.getString("PeriodFormat.hour"), b.getString("PeriodFormat.hours"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendMinutes()
                .appendSuffix(b.getString("PeriodFormat.minute"), b.getString("PeriodFormat.minutes"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendSeconds()
                .appendSuffix(b.getString("PeriodFormat.second"), b.getString("PeriodFormat.seconds"))
                .appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
                .appendMillis()
                .appendSuffix(b.getString("PeriodFormat.millisecond"), b.getString("PeriodFormat.milliseconds"))
                .toFormatter();
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[8]++;
            FORMATTERS.putIfAbsent(locale, pf);
CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.statements[9]++;

        } else {
  CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx.branches[2]++;}
        return pf;
    }

}

class CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.joda.time.format.PeriodFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$xugz0vnvd5wm3ptm79t7svocwx () {
    super("org.joda.time.format.PeriodFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.PeriodFormat.java");
      for (int i = 1; i <= 9; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

