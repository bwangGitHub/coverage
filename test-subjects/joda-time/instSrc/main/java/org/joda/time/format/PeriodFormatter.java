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

import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

/**
 * Controls the printing and parsing of a time period to and from a string.
 * <p>
 * This class is the main API for printing and parsing used by most applications.
 * Instances of this class are created via one of three factory classes:
 * <ul>
 * <li>{@link PeriodFormat} - formats by pattern and style</li>
 * <li>{@link ISOPeriodFormat} - ISO8601 formats</li>
 * <li>{@link PeriodFormatterBuilder} - complex formats created via method calls</li>
 * </ul>
 * <p>
 * An instance of this class holds a reference internally to one printer and
 * one parser. It is possible that one of these may be null, in which case the
 * formatter cannot print/parse. This can be checked via the {@link #isPrinter()}
 * and {@link #isParser()} methods.
 * <p>
 * The underlying printer/parser can be altered to behave exactly as required
 * by using a decorator modifier:
 * <ul>
 * <li>{@link #withLocale(Locale)} - returns a new formatter that uses the specified locale</li>
 * </ul>
 * This returns a new formatter (instances of this class are immutable).
 * <p>
 * The main methods of the class are the <code>printXxx</code> and
 * <code>parseXxx</code> methods. These are used as follows:
 * <pre>
 * // print using the default locale
 * String periodStr = formatter.print(period);
 * // print using the French locale
 * String periodStr = formatter.withLocale(Locale.FRENCH).print(period);
 * 
 * // parse using the French locale
 * Period date = formatter.withLocale(Locale.FRENCH).parsePeriod(str);
 * </pre>
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public class PeriodFormatter {
  static {
    CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.ping();
  }


    /** The internal printer used to output the datetime. */
    private final PeriodPrinter iPrinter;
    /** The internal parser used to output the datetime. */
    private final PeriodParser iParser;
    /** The locale to use for printing and parsing. */
    private final Locale iLocale;
    /** The period type used in parsing. */
    private final PeriodType iParseType;

    /**
     * Creates a new formatter, however you will normally use the factory
     * or the builder.
     * 
     * @param printer  the internal printer, null if cannot print
     * @param parser  the internal parser, null if cannot parse
     */
    public PeriodFormatter(
            PeriodPrinter printer, PeriodParser parser) {
        super();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[1]++;
        iPrinter = printer;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[2]++;
        iParser = parser;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[3]++;
        iLocale = null;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[4]++;
        iParseType = null;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[5]++;
    }

    /**
     * Constructor.
     * 
     * @param printer  the internal printer, null if cannot print
     * @param parser  the internal parser, null if cannot parse
     * @param locale  the locale to use
     * @param type  the parse period type
     */
    private PeriodFormatter(
            PeriodPrinter printer, PeriodParser parser,
            Locale locale, PeriodType type) {
        super();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[6]++;
        iPrinter = printer;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[7]++;
        iParser = parser;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[8]++;
        iLocale = locale;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[9]++;
        iParseType = type;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[10]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Is this formatter capable of printing.
     * 
     * @return true if this is a printer
     */
    public boolean isPrinter() {
        return (iPrinter != null);
    }

    /**
     * Gets the internal printer object that performs the real printing work.
     * 
     * @return the internal printer
     */
    public PeriodPrinter getPrinter() {
        return iPrinter;
    }

    /**
     * Is this formatter capable of parsing.
     * 
     * @return true if this is a parser
     */
    public boolean isParser() {
        return (iParser != null);
    }

    /**
     * Gets the internal parser object that performs the real parsing work.
     * 
     * @return the internal parser
     */
    public PeriodParser getParser() {
        return iParser;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter with a different locale that will be used
     * for printing and parsing.
     * <p>
     * A PeriodFormatter is immutable, so a new instance is returned,
     * and the original is unaltered and still usable.
     * 
     * @param locale  the locale to use
     * @return the new formatter
     */
    public PeriodFormatter withLocale(Locale locale) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((locale == getLocale()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((locale != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((locale.equals(getLocale())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[1]++;
            return this;

        } else {
  CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[2]++;}
        return new PeriodFormatter(iPrinter, iParser, locale, iParseType);
    }

    /**
     * Gets the locale that will be used for printing and parsing.
     * 
     * @return the locale to use
     */
    public Locale getLocale() {
        return iLocale;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter with a different PeriodType for parsing.
     * <p>
     * A PeriodFormatter is immutable, so a new instance is returned,
     * and the original is unaltered and still usable.
     * 
     * @param type  the type to use in parsing
     * @return the new formatter
     */
    public PeriodFormatter withParseType(PeriodType type) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type == iParseType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[3]++;
            return this;

        } else {
  CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[4]++;}
        return new PeriodFormatter(iPrinter, iParser, iLocale, type);
    }

    /**
     * Gets the PeriodType that will be used for parsing.
     * 
     * @return the parse type to use
     */
    public PeriodType getParseType() {
        return iParseType;
    }

    //-----------------------------------------------------------------------
    /**
     * Prints a ReadablePeriod to a StringBuffer.
     *
     * @param buf  the formatted period is appended to this buffer
     * @param period  the period to format, not null
     */
    public void printTo(StringBuffer buf, ReadablePeriod period) {
        checkPrinter();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[13]++;
        checkPeriod(period);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[14]++;
        
        getPrinter().printTo(buf, period, iLocale);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[15]++;
    }

    /**
     * Prints a ReadablePeriod to a Writer.
     *
     * @param out  the formatted period is written out
     * @param period  the period to format, not null
     */
    public void printTo(Writer out, ReadablePeriod period) throws IOException {
        checkPrinter();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[16]++;
        checkPeriod(period);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[17]++;
        
        getPrinter().printTo(out, period, iLocale);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[18]++;
    }

    /**
     * Prints a ReadablePeriod to a new String.
     *
     * @param period  the period to format, not null
     * @return the printed result
     */
    public String print(ReadablePeriod period) {
        checkPrinter();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[19]++;
        checkPeriod(period);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[20]++;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[21]++;
        
        PeriodPrinter printer = getPrinter();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[22]++;
        StringBuffer buf = new StringBuffer(printer.calculatePrintedLength(period, iLocale));
        printer.printTo(buf, period, iLocale);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[23]++;
        return buf.toString();
    }

    /**
     * Checks whether printing is supported.
     * 
     * @throws UnsupportedOperationException if printing is not supported
     */
    private void checkPrinter() {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iPrinter == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[5]++;
            throw new UnsupportedOperationException("Printing not supported");

        } else {
  CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[6]++;}
    }

    /**
     * Checks whether the period is non-null.
     * 
     * @throws IllegalArgumentException if the period is null
     */
    private void checkPeriod(ReadablePeriod period) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[7]++;
            throw new IllegalArgumentException("Period must not be null");

        } else {
  CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[8]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a period from the given text, at the given position, saving the
     * result into the fields of the given ReadWritablePeriod. If the parse
     * succeeds, the return value is the new text position. Note that the parse
     * may succeed without fully reading the text.
     * <p>
     * The parse type of the formatter is not used by this method.
     * <p>
     * If it fails, the return value is negative, but the period may still be
     * modified. To determine the position where the parse failed, apply the
     * one's complement operator (~) on the return value.
     *
     * @param period  a period that will be modified
     * @param text  text to parse
     * @param position position to start parsing from
     * @return new position, if negative, parse failed. Apply complement
     * operator (~) to get position of failure
     * @throws IllegalArgumentException if any field is out of range
     */
    public int parseInto(ReadWritablePeriod period, String text, int position) {
        checkParser();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[26]++;
        checkPeriod(period);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[27]++;
        
        return getParser().parseInto(period, text, position, iLocale);
    }

    /**
     * Parses a period from the given text, returning a new Period.
     *
     * @param text  text to parse
     * @return parsed value in a Period object
     * @throws IllegalArgumentException if any field is out of range
     */
    public Period parsePeriod(String text) {
        checkParser();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[28]++;
        
        return parseMutablePeriod(text).toPeriod();
    }

    /**
     * Parses a period from the given text, returning a new MutablePeriod.
     *
     * @param text  text to parse
     * @return parsed value in a MutablePeriod object
     * @throws IllegalArgumentException if any field is out of range
     */
    public MutablePeriod parseMutablePeriod(String text) {
        checkParser();
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[29]++;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[30]++;
        
        MutablePeriod period = new MutablePeriod(0, iParseType);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[31]++;
        int newPos = getParser().parseInto(period, text, 0, iLocale);
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[32]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((newPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[9]++;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((newPos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[11]++;
                return period;

            } else {
  CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[12]++;}

        } else {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[10]++;
            newPos = ~newPos;
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[34]++;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(text, newPos));
    }

    /**
     * Checks whether parsing is supported.
     * 
     * @throws UnsupportedOperationException if parsing is not supported
     */
    private void checkParser() {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((iParser == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[13]++;
            throw new UnsupportedOperationException("Parsing not supported");

        } else {
  CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1.branches[14]++;}
    }

}

class CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1 ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.joda.time.format.PeriodFormatter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$9e2yzi142ttgttc0xwa7a9lvxgvyzc1 () {
    super("org.joda.time.format.PeriodFormatter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.PeriodFormatter.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

