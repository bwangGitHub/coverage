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

/**
 * Factory that creates instances of PeriodFormatter for the ISO8601 standard.
 * <p>
 * Period formatting is performed by the {@link PeriodFormatter} class.
 * Three classes provide factory methods to create formatters, and this is one.
 * The others are {@link PeriodFormat} and {@link PeriodFormatterBuilder}.
 * <p>
 * ISOPeriodFormat is thread-safe and immutable, and the formatters it
 * returns are as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see PeriodFormat
 * @see PeriodFormatterBuilder
 */
public class ISOPeriodFormat {
  static {
    CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.ping();
  }


    /** Cache of standard format. */
    private static PeriodFormatter cStandard;
    /** Cache of alternate months format. */
    private static PeriodFormatter cAlternate;
    /** Cache of alternate extended months format. */
    private static PeriodFormatter cAlternateExtended;
    /** Cache of alternate weeks format. */
    private static PeriodFormatter cAlternateWithWeeks;
    /** Cache of alternate extended weeks format. */
    private static PeriodFormatter cAlternateExtendedWihWeeks;

    /**
     * Constructor.
     *
     * @since 1.1 (previously private)
     */
    protected ISOPeriodFormat() {
        super();
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * The standard ISO format - PyYmMwWdDThHmMsS.
     * Milliseconds are not output.
     * Note that the ISO8601 standard actually indicates weeks should not
     * be shown if any other field is present and vice versa.
     * 
     * @return the formatter
     */
    public static PeriodFormatter standard() {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cStandard == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[1]++;
            cStandard = new PeriodFormatterBuilder()
                .appendLiteral("P")
                .appendYears()
                .appendSuffix("Y")
                .appendMonths()
                .appendSuffix("M")
                .appendWeeks()
                .appendSuffix("W")
                .appendDays()
                .appendSuffix("D")
                .appendSeparatorIfFieldsAfter("T")
                .appendHours()
                .appendSuffix("H")
                .appendMinutes()
                .appendSuffix("M")
                .appendSecondsWithOptionalMillis()
                .appendSuffix("S")
                .toFormatter();
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[3]++;

        } else {
  CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[2]++;}
        return cStandard;
    }

    /**
     * The alternate ISO format, PyyyymmddThhmmss, which excludes weeks.
     * <p>
     * Even if weeks are present in the period, they are not output.
     * Fractional seconds (milliseconds) will appear if required.
     * 
     * @return the formatter
     */
    public static PeriodFormatter alternate() {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cAlternate == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[3]++;
            cAlternate = new PeriodFormatterBuilder()
                .appendLiteral("P")
                .printZeroAlways()
                .minimumPrintedDigits(4)
                .appendYears()
                .minimumPrintedDigits(2)
                .appendMonths()
                .appendDays()
                .appendSeparatorIfFieldsAfter("T")
                .appendHours()
                .appendMinutes()
                .appendSecondsWithOptionalMillis()
                .toFormatter();
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[5]++;

        } else {
  CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[4]++;}
        return cAlternate;
    }

    /**
     * The alternate ISO format, Pyyyy-mm-ddThh:mm:ss, which excludes weeks.
     * <p>
     * Even if weeks are present in the period, they are not output.
     * Fractional seconds (milliseconds) will appear if required.
     * 
     * @return the formatter
     */
    public static PeriodFormatter alternateExtended() {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((cAlternateExtended == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[5]++;
            cAlternateExtended = new PeriodFormatterBuilder()
                .appendLiteral("P")
                .printZeroAlways()
                .minimumPrintedDigits(4)
                .appendYears()
                .appendSeparator("-")
                .minimumPrintedDigits(2)
                .appendMonths()
                .appendSeparator("-")
                .appendDays()
                .appendSeparatorIfFieldsAfter("T")
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .appendSeparator(":")
                .appendSecondsWithOptionalMillis()
                .toFormatter();
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[7]++;

        } else {
  CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[6]++;}
        return cAlternateExtended;
    }

    /**
     * The alternate ISO format, PyyyyWwwddThhmmss, which excludes months.
     * <p>
     * Even if months are present in the period, they are not output.
     * Fractional seconds (milliseconds) will appear if required.
     * 
     * @return the formatter
     */
    public static PeriodFormatter alternateWithWeeks() {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cAlternateWithWeeks == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[7]++;
            cAlternateWithWeeks = new PeriodFormatterBuilder()
                .appendLiteral("P")
                .printZeroAlways()
                .minimumPrintedDigits(4)
                .appendYears()
                .minimumPrintedDigits(2)
                .appendPrefix("W")
                .appendWeeks()
                .appendDays()
                .appendSeparatorIfFieldsAfter("T")
                .appendHours()
                .appendMinutes()
                .appendSecondsWithOptionalMillis()
                .toFormatter();
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[9]++;

        } else {
  CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[8]++;}
        return cAlternateWithWeeks;
    }

    /**
     * The alternate ISO format, Pyyyy-Www-ddThh:mm:ss, which excludes months.
     * <p>
     * Even if months are present in the period, they are not output.
     * Fractional seconds (milliseconds) will appear if required.
     * 
     * @return the formatter
     */
    public static PeriodFormatter alternateExtendedWithWeeks() {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cAlternateExtendedWihWeeks == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[9]++;
            cAlternateExtendedWihWeeks = new PeriodFormatterBuilder()
                .appendLiteral("P")
                .printZeroAlways()
                .minimumPrintedDigits(4)
                .appendYears()
                .appendSeparator("-")
                .minimumPrintedDigits(2)
                .appendPrefix("W")
                .appendWeeks()
                .appendSeparator("-")
                .appendDays()
                .appendSeparatorIfFieldsAfter("T")
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .appendSeparator(":")
                .appendSecondsWithOptionalMillis()
                .toFormatter();
CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.statements[11]++;

        } else {
  CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp.branches[10]++;}
        return cAlternateExtendedWihWeeks;
    }

}

class CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.format.ISOPeriodFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$8kcja2i9mqpbpq9ft7bojtv88wty0yp () {
    super("org.joda.time.format.ISOPeriodFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.ISOPeriodFormat.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

