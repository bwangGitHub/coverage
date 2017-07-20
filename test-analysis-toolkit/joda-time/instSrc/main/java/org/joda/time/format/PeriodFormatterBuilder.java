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
package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import org.joda.time.DateTimeConstants;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

/**
 * Factory that creates complex instances of PeriodFormatter via method calls.
 * <p>
 * Period formatting is performed by the {@link PeriodFormatter} class.
 * Three classes provide factory methods to create formatters, and this is one.
 * The others are {@link PeriodFormat} and {@link ISOPeriodFormat}.
 * <p>
 * PeriodFormatterBuilder is used for constructing formatters which are then
 * used to print or parse. The formatters are built by appending specific fields
 * or other formatters to an instance of this builder.
 * <p>
 * For example, a formatter that prints years and months, like "15 years and 8 months",
 * can be constructed as follows:
 * <p>
 * <pre>
 * PeriodFormatter yearsAndMonths = new PeriodFormatterBuilder()
 *     .printZeroAlways()
 *     .appendYears()
 *     .appendSuffix(" year", " years")
 *     .appendSeparator(" and ")
 *     .printZeroRarelyLast()
 *     .appendMonths()
 *     .appendSuffix(" month", " months")
 *     .toFormatter();
 * </pre>
 * <p>
 * PeriodFormatterBuilder itself is mutable and not thread-safe, but the
 * formatters that it builds are thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see PeriodFormat
 */
public class PeriodFormatterBuilder {
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.ping();
  }

    private static final int PRINT_ZERO_RARELY_FIRST = 1;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[1]++;
  }
    private static final int PRINT_ZERO_RARELY_LAST = 2;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[2]++;
  }
    private static final int PRINT_ZERO_IF_SUPPORTED = 3;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[3]++;
  }
    private static final int PRINT_ZERO_ALWAYS = 4;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[4]++;
  }
    private static final int PRINT_ZERO_NEVER = 5;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[5]++;
  }
    
    private static final int YEARS = 0;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[6]++;
  }
    private static final int MONTHS = 1;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[7]++;
  }
    private static final int WEEKS = 2;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[8]++;
  }
    private static final int DAYS = 3;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[9]++;
  }
    private static final int HOURS = 4;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[10]++;
  }
    private static final int MINUTES = 5;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[11]++;
  }
    private static final int SECONDS = 6;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[12]++;
  }
    private static final int MILLIS = 7;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[13]++;
  }
    private static final int SECONDS_MILLIS = 8;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[14]++;
  }
    private static final int SECONDS_OPTIONAL_MILLIS = 9;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[15]++;
  }
    private static final int MAX_FIELD = SECONDS_OPTIONAL_MILLIS;
  static {
    CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[16]++;
  }

    private int iMinPrintedDigits;
    private int iPrintZeroSetting;
    private int iMaxParsedDigits;
    private boolean iRejectSignedValues;

    private PeriodFieldAffix iPrefix;

    // List of Printers and Parsers used to build a final formatter.
    private List<Object> iElementPairs;
    /** Set to true if the formatter is not a printer. */
    private boolean iNotPrinter;
    /** Set to true if the formatter is not a parser. */
    private boolean iNotParser;

    // Last PeriodFormatter appended of each field type.
    private FieldFormatter[] iFieldFormatters;

    public PeriodFormatterBuilder() {
        clear();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[17]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a PeriodFormatter using all the appended elements.
     * <p>
     * This is the main method used by applications at the end of the build
     * process to create a usable formatter.
     * <p>
     * Subsequent changes to this builder do not affect the returned formatter.
     * <p>
     * The returned formatter may not support both printing and parsing.
     * The methods {@link PeriodFormatter#isPrinter()} and
     * {@link PeriodFormatter#isParser()} will help you determine the state
     * of the formatter.
     * 
     * @return the newly created formatter
     * @throws IllegalStateException if the builder can produce neither a printer nor a parser
     */
    public PeriodFormatter toFormatter() {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[18]++;
        PeriodFormatter formatter = toFormatter(iElementPairs, iNotPrinter, iNotParser);
        iFieldFormatters = (FieldFormatter[]) iFieldFormatters.clone();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[19]++;
        return formatter;
    }

    /**
     * Internal method to create a PeriodPrinter instance using all the
     * appended elements.
     * <p>
     * Most applications will not use this method.
     * If you want a printer in an application, call {@link #toFormatter()}
     * and just use the printing API.
     * <p>
     * Subsequent changes to this builder do not affect the returned printer.
     * 
     * @return the newly created printer, null if builder cannot create a printer
     */
    public PeriodPrinter toPrinter() {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[20]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((iNotPrinter) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[2]++;}
        return toFormatter().getPrinter();
    }

    /**
     * Internal method to create a PeriodParser instance using all the
     * appended elements.
     * <p>
     * Most applications will not use this method.
     * If you want a printer in an application, call {@link #toFormatter()}
     * and just use the printing API.
     * <p>
     * Subsequent changes to this builder do not affect the returned parser.
     * 
     * @return the newly created parser, null if builder cannot create a parser
     */
    public PeriodParser toParser() {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iNotParser) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[3]++;
            return null;

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[4]++;}
        return toFormatter().getParser();
    }

    //-----------------------------------------------------------------------
    /**
     * Clears out all the appended elements, allowing this builder to be reused.
     */
    public void clear() {
        iMinPrintedDigits = 1;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[22]++;
        iPrintZeroSetting = PRINT_ZERO_RARELY_LAST;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[23]++;
        iMaxParsedDigits = 10;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[24]++;
        iRejectSignedValues = false;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[25]++;
        iPrefix = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[26]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iElementPairs == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[5]++;
            iElementPairs = new ArrayList<Object>();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[28]++;

        } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[6]++;
            iElementPairs.clear();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[29]++;
        }
        iNotPrinter = false;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[30]++;
        iNotParser = false;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[31]++;
        iFieldFormatters = new FieldFormatter[10];
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[32]++;
    }

    /**
     * Appends another formatter.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder append(PeriodFormatter formatter) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[7]++;
            throw new IllegalArgumentException("No formatter supplied");

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[8]++;}
        clearPrefix();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[34]++;
        append0(formatter.getPrinter(), formatter.getParser());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[35]++;
        return this;
    }

    /**
     * Appends a printer parser pair.
     * <p>
     * Either the printer or the parser may be null, in which case the builder will
     * be unable to produce a parser or printer repectively.
     *
     * @param printer  appends a printer to the builder, null if printing is not supported
     * @param parser  appends a parser to the builder, null if parsing is not supported
     * @return this PeriodFormatterBuilder
     * @throws IllegalArgumentException if both the printer and parser are null
     */
    public PeriodFormatterBuilder append(PeriodPrinter printer, PeriodParser parser) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((printer == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((parser == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[9]++;
            throw new IllegalArgumentException("No printer or parser supplied");

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[10]++;}
        clearPrefix();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[37]++;
        append0(printer, parser);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[38]++;
        return this;
    }

    /**
     * Instructs the printer to emit specific text, and the parser to expect it.
     * The parser is case-insensitive.
     *
     * @return this PeriodFormatterBuilder
     * @throws IllegalArgumentException if text is null
     */
    public PeriodFormatterBuilder appendLiteral(String text) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[11]++;
            throw new IllegalArgumentException("Literal must not be null");

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[12]++;}
        clearPrefix();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[40]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[41]++;
        Literal literal = new Literal(text);
        append0(literal, literal);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[42]++;
        return this;
    }

    /**
     * Set the minimum digits printed for the next and following appended
     * fields. By default, the minimum digits printed is one. If the field value
     * is zero, it is not printed unless a printZero rule is applied.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder minimumPrintedDigits(int minDigits) {
        iMinPrintedDigits = minDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[43]++;
        return this;
    }

    /**
     * Set the maximum digits parsed for the next and following appended
     * fields. By default, the maximum digits parsed is ten.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder maximumParsedDigits(int maxDigits) {
        iMaxParsedDigits = maxDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[44]++;
        return this;
    }

    /**
     * Reject signed values when parsing the next and following appended fields.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder rejectSignedValues(boolean v) {
        iRejectSignedValues = v;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[45]++;
        return this;
    }

    /**
     * Never print zero values for the next and following appended fields,
     * unless no fields would be printed. If no fields are printed, the printer
     * forces the last "printZeroRarely" field to print a zero.
     * <p>
     * This field setting is the default.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder printZeroRarelyLast() {
        iPrintZeroSetting = PRINT_ZERO_RARELY_LAST;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[46]++;
        return this;
    }

    /**
     * Never print zero values for the next and following appended fields,
     * unless no fields would be printed. If no fields are printed, the printer
     * forces the first "printZeroRarely" field to print a zero.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder printZeroRarelyFirst() {
        iPrintZeroSetting = PRINT_ZERO_RARELY_FIRST;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[47]++;
        return this;
    }

    /**
     * Print zero values for the next and following appened fields only if the
     * period supports it.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder printZeroIfSupported() {
        iPrintZeroSetting = PRINT_ZERO_IF_SUPPORTED;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[48]++;
        return this;
    }

    /**
     * Always print zero values for the next and following appended fields,
     * even if the period doesn't support it. The parser requires values for
     * fields that always print zero.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder printZeroAlways() {
        iPrintZeroSetting = PRINT_ZERO_ALWAYS;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[49]++;
        return this;
    }

    /**
     * Never print zero values for the next and following appended fields,
     * unless no fields would be printed. If no fields are printed, the printer
     * forces the last "printZeroRarely" field to print a zero.
     * <p>
     * This field setting is the default.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder printZeroNever() {
        iPrintZeroSetting = PRINT_ZERO_NEVER;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[50]++;
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Append a field prefix which applies only to the next appended field. If
     * the field is not printed, neither is the prefix.
     *
     * @param text text to print before field only if field is printed
     * @return this PeriodFormatterBuilder
     * @see #appendSuffix
     */
    public PeriodFormatterBuilder appendPrefix(String text) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[13]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[14]++;}
        return appendPrefix(new SimpleAffix(text));
    }

    /**
     * Append a field prefix which applies only to the next appended field. If
     * the field is not printed, neither is the prefix.
     * <p>
     * During parsing, the singular and plural versions are accepted whether
     * or not the actual value matches plurality.
     *
     * @param singularText text to print if field value is one
     * @param pluralText text to print if field value is not one
     * @return this PeriodFormatterBuilder
     * @see #appendSuffix
     */
    public PeriodFormatterBuilder appendPrefix(String singularText,
                                                 String pluralText) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[52]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((singularText == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((pluralText == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[15]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[16]++;}
        return appendPrefix(new PluralAffix(singularText, pluralText));
    }

    /**
     * Append a field prefix which applies only to the next appended field. If
     * the field is not printed, neither is the prefix.
     *
     * @param prefix custom prefix
     * @return this PeriodFormatterBuilder
     * @see #appendSuffix
     */
    private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix prefix) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[53]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[17]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[18]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((iPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[19]++;
            prefix = new CompositeAffix(iPrefix, prefix);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[55]++;

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[20]++;}
        iPrefix = prefix;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[56]++;
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Instruct the printer to emit an integer years field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendYears() {
        appendField(YEARS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[57]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer months field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendMonths() {
        appendField(MONTHS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[58]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer weeks field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendWeeks() {
        appendField(WEEKS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[59]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer days field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendDays() {
        appendField(DAYS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[60]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer hours field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendHours() {
        appendField(HOURS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[61]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer minutes field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendMinutes() {
        appendField(MINUTES);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[62]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer seconds field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendSeconds() {
        appendField(SECONDS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[63]++;
        return this;
    }

    /**
     * Instruct the printer to emit a combined seconds and millis field, if supported.
     * The millis will overflow into the seconds if necessary.
     * The millis are always output.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendSecondsWithMillis() {
        appendField(SECONDS_MILLIS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[64]++;
        return this;
    }

    /**
     * Instruct the printer to emit a combined seconds and millis field, if supported.
     * The millis will overflow into the seconds if necessary.
     * The millis are only output if non-zero.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        appendField(SECONDS_OPTIONAL_MILLIS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[65]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer millis field, if supported.
     * <p>
     * The number of printed and parsed digits can be controlled using
     * {@link #minimumPrintedDigits(int)} and {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendMillis() {
        appendField(MILLIS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[66]++;
        return this;
    }

    /**
     * Instruct the printer to emit an integer millis field, if supported.
     * <p>
     * The number of arsed digits can be controlled using {@link #maximumParsedDigits(int)}.
     *
     * @return this PeriodFormatterBuilder
     */
    public PeriodFormatterBuilder appendMillis3Digit() {
        appendField(7, 3);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[67]++;
        return this;
    }

    private void appendField(int type) {
        appendField(type, iMinPrintedDigits);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[68]++;
    }

    private void appendField(int type, int minPrinted) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[69]++;
        FieldFormatter field = new FieldFormatter(minPrinted, iPrintZeroSetting,
            iMaxParsedDigits, iRejectSignedValues, type, iFieldFormatters, iPrefix, null);
        append0(field, field);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[70]++;
        iFieldFormatters[type] = field;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[71]++;
        iPrefix = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[72]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Append a field suffix which applies only to the last appended field. If
     * the field is not printed, neither is the suffix.
     *
     * @param text text to print after field only if field is printed
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if no field exists to append to
     * @see #appendPrefix
     */
    public PeriodFormatterBuilder appendSuffix(String text) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[73]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[21]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[22]++;}
        return appendSuffix(new SimpleAffix(text));
    }

    /**
     * Append a field suffix which applies only to the last appended field. If
     * the field is not printed, neither is the suffix.
     * <p>
     * During parsing, the singular and plural versions are accepted whether or
     * not the actual value matches plurality.
     *
     * @param singularText text to print if field value is one
     * @param pluralText text to print if field value is not one
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if no field exists to append to
     * @see #appendPrefix
     */
    public PeriodFormatterBuilder appendSuffix(String singularText,
                                               String pluralText) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[74]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((singularText == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((pluralText == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[23]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[24]++;}
        return appendSuffix(new PluralAffix(singularText, pluralText));
    }

    /**
     * Append a field suffix which applies only to the last appended field. If
     * the field is not printed, neither is the suffix.
     *
     * @param suffix custom suffix
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if no field exists to append to
     * @see #appendPrefix
     */
    private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix suffix) {
        final Object originalPrinter;
        final Object originalParser;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[75]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iElementPairs.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[25]++;
            originalPrinter = iElementPairs.get(iElementPairs.size() - 2);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[76]++;
            originalParser = iElementPairs.get(iElementPairs.size() - 1);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[77]++;

        } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[26]++;
            originalPrinter = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[78]++;
            originalParser = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[79]++;
        }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[80]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (128)) == 0 || true) &&
 ((originalPrinter == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((originalParser == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((originalPrinter != originalParser) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((originalPrinter instanceof FieldFormatter) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[27]++;
            throw new IllegalStateException("No field to apply suffix to");

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[28]++;}

        clearPrefix();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[81]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[82]++;
        FieldFormatter newField = new FieldFormatter((FieldFormatter) originalPrinter, suffix);
        iElementPairs.set(iElementPairs.size() - 2, newField);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[83]++;
        iElementPairs.set(iElementPairs.size() - 1, newField);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[84]++;
        iFieldFormatters[newField.getFieldType()] = newField;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[85]++;
        
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Append a separator, which is output if fields are printed both before
     * and after the separator.
     * <p>
     * For example, <code>builder.appendDays().appendSeparator(",").appendHours()</code>
     * will only output the comma if both the days and hours fields are output.
     * <p>
     * The text will be parsed case-insensitively.
     * <p>
     * Note: appending a separator discontinues any further work on the latest
     * appended field.
     *
     * @param text  the text to use as a separator
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if this separator follows a previous one
     */
    public PeriodFormatterBuilder appendSeparator(String text) {
        return appendSeparator(text, text, null, true, true);
    }

    /**
     * Append a separator, which is output only if fields are printed after the separator.
     * <p>
     * For example,
     * <code>builder.appendDays().appendSeparatorIfFieldsAfter(",").appendHours()</code>
     * will only output the comma if the hours fields is output.
     * <p>
     * The text will be parsed case-insensitively.
     * <p>
     * Note: appending a separator discontinues any further work on the latest
     * appended field.
     *
     * @param text  the text to use as a separator
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if this separator follows a previous one
     */
    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String text) {
        return appendSeparator(text, text, null, false, true);
    }

    /**
     * Append a separator, which is output only if fields are printed before the separator.
     * <p>
     * For example,
     * <code>builder.appendDays().appendSeparatorIfFieldsBefore(",").appendHours()</code>
     * will only output the comma if the days fields is output.
     * <p>
     * The text will be parsed case-insensitively.
     * <p>
     * Note: appending a separator discontinues any further work on the latest
     * appended field.
     *
     * @param text  the text to use as a separator
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if this separator follows a previous one
     */
    public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String text) {
        return appendSeparator(text, text, null, true, false);
    }

    /**
     * Append a separator, which is output if fields are printed both before
     * and after the separator.
     * <p>
     * This method changes the separator depending on whether it is the last separator
     * to be output.
     * <p>
     * For example, <code>builder.appendDays().appendSeparator(",", "&").appendHours().appendSeparator(",", "&").appendMinutes()</code>
     * will output '1,2&3' if all three fields are output, '1&2' if two fields are output
     * and '1' if just one field is output.
     * <p>
     * The text will be parsed case-insensitively.
     * <p>
     * Note: appending a separator discontinues any further work on the latest
     * appended field.
     *
     * @param text  the text to use as a separator
     * @param finalText  the text used used if this is the final separator to be printed
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if this separator follows a previous one
     */
    public PeriodFormatterBuilder appendSeparator(String text, String finalText) {
        return appendSeparator(text, finalText, null, true, true);
    }

    /**
     * Append a separator, which is output if fields are printed both before
     * and after the separator.
     * <p>
     * This method changes the separator depending on whether it is the last separator
     * to be output.
     * <p>
     * For example, <code>builder.appendDays().appendSeparator(",", "&").appendHours().appendSeparator(",", "&").appendMinutes()</code>
     * will output '1,2&3' if all three fields are output, '1&2' if two fields are output
     * and '1' if just one field is output.
     * <p>
     * The text will be parsed case-insensitively.
     * <p>
     * Note: appending a separator discontinues any further work on the latest
     * appended field.
     *
     * @param text  the text to use as a separator
     * @param finalText  the text used used if this is the final separator to be printed
     * @param variants  set of text values which are also acceptable when parsed
     * @return this PeriodFormatterBuilder
     * @throws IllegalStateException if this separator follows a previous one
     */
    public PeriodFormatterBuilder appendSeparator(String text, String finalText,
                                                  String[] variants) {
        return appendSeparator(text, finalText, variants, true, true);
    }

    private PeriodFormatterBuilder appendSeparator(String text, String finalText,
                                                   String[] variants,
                                                   boolean useBefore, boolean useAfter) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[86]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((finalText == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[29]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[30]++;}

        clearPrefix();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[87]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[88]++;
        
        // optimise zero formatter case
        List<Object> pairs = iElementPairs;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[89]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((pairs.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[31]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[90]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((useAfter) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((useBefore == false) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[33]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[91]++;
                Separator separator = new Separator(
                        text, finalText, variants,
                        Literal.EMPTY, Literal.EMPTY, useBefore, useAfter);
                append0(separator, separator);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[92]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[34]++;}
            return this;

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[32]++;}
        
        // find the last separator added
        int i;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[93]++;
        Separator lastSeparator = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[94]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[1]++;


int CodeCoverConditionCoverageHelper_C18;
        for (i=pairs.size();(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); ) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[1]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[2]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[3]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[95]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pairs.get(i) instanceof Separator) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[35]++;
                lastSeparator = (Separator) pairs.get(i);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[96]++;
                pairs = pairs.subList(i + 1, pairs.size());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[97]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[98]++;
                break;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[36]++;}
            i--;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[99]++;  // element pairs
        }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[100]++;
int CodeCoverConditionCoverageHelper_C20;
        
        // merge formatters
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((lastSeparator != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((pairs.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[37]++;
            throw new IllegalStateException("Cannot have two adjacent separators");

        } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[38]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[101]++;
            Object[] comp = createComposite(pairs);
            pairs.clear();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[102]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[103]++;
            Separator separator = new Separator(
                    text, finalText, variants,
                    (PeriodPrinter) comp[0], (PeriodParser) comp[1],
                    useBefore, useAfter);
            pairs.add(separator);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[104]++;
            pairs.add(separator);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[105]++;
        }
        
        return this;
    }

    //-----------------------------------------------------------------------
    private void clearPrefix() throws IllegalStateException {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[106]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((iPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[39]++;
            throw new IllegalStateException("Prefix not followed by field");

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[40]++;}
        iPrefix = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[107]++;
    }

    private PeriodFormatterBuilder append0(PeriodPrinter printer, PeriodParser parser) {
        iElementPairs.add(printer);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[108]++;
        iElementPairs.add(parser);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[109]++;
        iNotPrinter |= (printer == null);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[110]++;
        iNotParser |= (parser == null);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[111]++;
        return this;
    }

    //-----------------------------------------------------------------------
    private static PeriodFormatter toFormatter(List<Object> elementPairs, boolean notPrinter, boolean notParser) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[112]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((notPrinter) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((notParser) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[41]++;
            throw new IllegalStateException("Builder has created neither a printer nor a parser");

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[42]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[113]++;
        int size = elementPairs.size();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[114]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((size >= 2) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((elementPairs.get(0) instanceof Separator) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[43]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[115]++;
            Separator sep = (Separator) elementPairs.get(0);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[116]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((sep.iAfterParser == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((sep.iAfterPrinter == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[45]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[117]++;
                PeriodFormatter f = toFormatter(elementPairs.subList(2, size), notPrinter, notParser);
                sep = sep.finish(f.getPrinter(), f.getParser());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[118]++;
                return new PeriodFormatter(sep, sep);

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[46]++;}

        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[44]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[119]++;
        Object[] comp = createComposite(elementPairs);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[120]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((notPrinter) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[47]++;
            return new PeriodFormatter(null, (PeriodParser) comp[1]);

        } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[48]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[121]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((notParser) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[49]++;
            return new PeriodFormatter((PeriodPrinter) comp[0], null);

        } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[50]++;
            return new PeriodFormatter((PeriodPrinter) comp[0], (PeriodParser) comp[1]);
        }
}
    }

    private static Object[] createComposite(List<Object> elementPairs) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[122]++;
        switch (elementPairs.size()) {
            case 0:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[51]++;
                return new Object[] {Literal.EMPTY, Literal.EMPTY};
            case 1:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[52]++;
                return new Object[] {elementPairs.get(0), elementPairs.get(1)};
            default:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[53]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[123]++;
                Composite comp = new Composite(elementPairs);
                return new Object[] {comp, comp};
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Defines a formatted field's prefix or suffix text.
     * This can be used for fields such as 'n hours' or 'nH' or 'Hour:n'.
     */
    static interface PeriodFieldAffix {
        int calculatePrintedLength(int value);
        
        void printTo(StringBuffer buf, int value);
        
        void printTo(Writer out, int value) throws IOException;
        
        /**
         * @return new position after parsing affix, or ~position of failure
         */
        int parse(String periodStr, int position);

        /**
         * @return position where affix starts, or original ~position if not found
         */
        int scan(String periodStr, int position);
    }

    //-----------------------------------------------------------------------
    /**
     * Implements an affix where the text does not vary by the amount.
     */
    static class SimpleAffix implements PeriodFieldAffix {
        private final String iText;

        SimpleAffix(String text) {
            iText = text;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[124]++;
        }

        public int calculatePrintedLength(int value) {
            return iText.length();
        }

        public void printTo(StringBuffer buf, int value) {
            buf.append(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[125]++;
        }

        public void printTo(Writer out, int value) throws IOException {
            out.write(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[126]++;
        }

        public int parse(String periodStr, int position) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[127]++;
            String text = iText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[128]++;
            int textLength = text.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[129]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches(true, position, text, 0, textLength)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[54]++;
                return position + textLength;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[55]++;}
            return ~position;
        }

        public int scan(String periodStr, final int position) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[130]++;
            String text = iText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[131]++;
            int textLength = text.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[132]++;
            int sourceLength = periodStr.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[133]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[4]++;


int CodeCoverConditionCoverageHelper_C28;
            search:
            for (int pos = position;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((pos < sourceLength) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); pos++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[4]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[5]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[6]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[134]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches(true, pos, text, 0, textLength)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[56]++;
                    return pos;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[57]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[135]++;
                // Only allow number characters to be skipped in search of suffix.
                switch (periodStr.charAt(pos)) {
                case '0':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[58]++; case '1':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[59]++; case '2':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[60]++; case '3':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[61]++; case '4':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[62]++;
                case '5':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[63]++; case '6':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[64]++; case '7':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[65]++; case '8':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[66]++; case '9':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[67]++;
                case '.':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[68]++; case ',':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[69]++; case '+':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[70]++; case '-':
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[71]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[136]++;
                    break;
                default:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[72]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[137]++;
                    break search;
                }
            }
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implements an affix where the text varies by the amount of the field.
     * Only singular (1) and plural (not 1) are supported.
     */
    static class PluralAffix implements PeriodFieldAffix {
        private final String iSingularText;
        private final String iPluralText;

        PluralAffix(String singularText, String pluralText) {
            iSingularText = singularText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[138]++;
            iPluralText = pluralText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[139]++;
        }

        public int calculatePrintedLength(int value) {
            return (value == 1 ? iSingularText : iPluralText).length();
        }

        public void printTo(StringBuffer buf, int value) {
            buf.append(value == 1 ? iSingularText : iPluralText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[140]++;
        }

        public void printTo(Writer out, int value) throws IOException {
            out.write(value == 1 ? iSingularText : iPluralText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[141]++;
        }

        public int parse(String periodStr, int position) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[142]++;
            String text1 = iPluralText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[143]++;
            String text2 = iSingularText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[144]++;
int CodeCoverConditionCoverageHelper_C30; 

            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((text1.length() < text2.length()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[73]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[145]++;
                // Swap in order to match longer one first.
                String temp = text1;
                text1 = text2;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[146]++;
                text2 = temp;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[147]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[74]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[148]++;
int CodeCoverConditionCoverageHelper_C31;

            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches
                (true, position, text1, 0, text1.length())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[75]++;
                return position + text1.length();

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[76]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[149]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches
                (true, position, text2, 0, text2.length())) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[77]++;
                return position + text2.length();

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[78]++;}

            return ~position;
        }

        public int scan(String periodStr, final int position) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[150]++;
            String text1 = iPluralText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[151]++;
            String text2 = iSingularText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[152]++;
int CodeCoverConditionCoverageHelper_C33; 

            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((text1.length() < text2.length()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[79]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[153]++;
                // Swap in order to match longer one first.
                String temp = text1;
                text1 = text2;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[154]++;
                text2 = temp;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[155]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[80]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[156]++;

            int textLength1 = text1.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[157]++;
            int textLength2 = text2.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[158]++;

            int sourceLength = periodStr.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[159]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[7]++;


int CodeCoverConditionCoverageHelper_C34;
            for (int pos = position;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((pos < sourceLength) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); pos++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[7]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[8]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[9]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[160]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches(true, pos, text1, 0, textLength1)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[81]++;
                    return pos;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[82]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[161]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches(true, pos, text2, 0, textLength2)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[83]++;
                    return pos;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[84]++;}
            }
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Builds a composite affix by merging two other affix implementations.
     */
    static class CompositeAffix implements PeriodFieldAffix {
        private final PeriodFieldAffix iLeft;
        private final PeriodFieldAffix iRight;

        CompositeAffix(PeriodFieldAffix left, PeriodFieldAffix right) {
            iLeft = left;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[162]++;
            iRight = right;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[163]++;
        }

        public int calculatePrintedLength(int value) {
            return iLeft.calculatePrintedLength(value)
                + iRight.calculatePrintedLength(value);
        }

        public void printTo(StringBuffer buf, int value) {
            iLeft.printTo(buf, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[164]++;
            iRight.printTo(buf, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[165]++;
        }

        public void printTo(Writer out, int value) throws IOException {
            iLeft.printTo(out, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[166]++;
            iRight.printTo(out, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[167]++;
        }

        public int parse(String periodStr, int position) {
            position = iLeft.parse(periodStr, position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[168]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[169]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((position >= 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[85]++;
                position = iRight.parse(periodStr, position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[170]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[86]++;}
            return position;
        }

        public int scan(String periodStr, final int position) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[171]++;
            int pos = iLeft.scan(periodStr, position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[172]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[87]++;
                return iRight.scan(periodStr, pos);

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[88]++;}
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Formats the numeric value of a field, potentially with prefix/suffix.
     */
    static class FieldFormatter
            implements PeriodPrinter, PeriodParser {
        private final int iMinPrintedDigits;
        private final int iPrintZeroSetting;
        private final int iMaxParsedDigits;
        private final boolean iRejectSignedValues;
        
        /** The index of the field type, 0=year, etc. */
        private final int iFieldType;
        /**
         * The array of the latest formatter added for each type.
         * This is shared between all the field formatters in a formatter.
         */
        private final FieldFormatter[] iFieldFormatters;
        
        private final PeriodFieldAffix iPrefix;
        private final PeriodFieldAffix iSuffix;

        FieldFormatter(int minPrintedDigits, int printZeroSetting,
                       int maxParsedDigits, boolean rejectSignedValues,
                       int fieldType, FieldFormatter[] fieldFormatters,
                       PeriodFieldAffix prefix, PeriodFieldAffix suffix) {
            iMinPrintedDigits = minPrintedDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[173]++;
            iPrintZeroSetting = printZeroSetting;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[174]++;
            iMaxParsedDigits = maxParsedDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[175]++;
            iRejectSignedValues = rejectSignedValues;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[176]++;
            iFieldType = fieldType;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[177]++;
            iFieldFormatters = fieldFormatters;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[178]++;
            iPrefix = prefix;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[179]++;
            iSuffix = suffix;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[180]++;
        }

        FieldFormatter(FieldFormatter field, PeriodFieldAffix suffix) {
            iMinPrintedDigits = field.iMinPrintedDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[181]++;
            iPrintZeroSetting = field.iPrintZeroSetting;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[182]++;
            iMaxParsedDigits = field.iMaxParsedDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[183]++;
            iRejectSignedValues = field.iRejectSignedValues;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[184]++;
            iFieldType = field.iFieldType;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[185]++;
            iFieldFormatters = field.iFieldFormatters;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[186]++;
            iPrefix = field.iPrefix;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[187]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[188]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((field.iSuffix != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[89]++;
                suffix = new CompositeAffix(field.iSuffix, suffix);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[189]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[90]++;}
            iSuffix = suffix;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[190]++;
        }

        public int countFieldsToPrint(ReadablePeriod period, int stopAt, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[191]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((stopAt <= 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[91]++;
                return 0;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[92]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[192]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((iPrintZeroSetting == PRINT_ZERO_ALWAYS) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((getFieldValue(period) != Long.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[93]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[94]++;}
            return 0;
        }

        public int calculatePrintedLength(ReadablePeriod period, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[193]++;
            long valueLong = getFieldValue(period);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[194]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((valueLong == Long.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[95]++;
                return 0;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[96]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[195]++;

            int sum = Math.max(FormatUtils.calculateDigitCount(valueLong), iMinPrintedDigits);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[196]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((iFieldType >= SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[97]++;
                // valueLong contains the seconds and millis fields
                // the minimum output is 0.000, which is 4 digits
                sum = Math.max(sum, 4);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[197]++;
                // plus one for the decimal point
                sum++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[198]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[199]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((iFieldType == SECONDS_OPTIONAL_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 (((Math.abs(valueLong) % DateTimeConstants.MILLIS_PER_SECOND) == 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[99]++;
                    sum -= 4;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[200]++;
 // remove three digits and decimal point
                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[100]++;}
                // reset valueLong to refer to the seconds part for the prefic/suffix calculation
                valueLong = valueLong / DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[201]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[98]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[202]++;
            int value = (int) valueLong;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[203]++;
int CodeCoverConditionCoverageHelper_C45;

            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((iPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[101]++;
                sum += iPrefix.calculatePrintedLength(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[204]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[102]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[205]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((iSuffix != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[103]++;
                sum += iSuffix.calculatePrintedLength(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[206]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[104]++;}

            return sum;
        }
        
        public void printTo(StringBuffer buf, ReadablePeriod period, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[207]++;
            long valueLong = getFieldValue(period);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[208]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((valueLong == Long.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[105]++;
                return;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[106]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[209]++;
            int value = (int) valueLong;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[210]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((iFieldType >= SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[107]++;
                value = (int) (valueLong / DateTimeConstants.MILLIS_PER_SECOND);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[211]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[108]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[212]++;
int CodeCoverConditionCoverageHelper_C49;

            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((iPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[109]++;
                iPrefix.printTo(buf, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[213]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[110]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[214]++;
            int minDigits = iMinPrintedDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[215]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((minDigits <= 1) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[111]++;
                FormatUtils.appendUnpaddedInteger(buf, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[216]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[112]++;
                FormatUtils.appendPaddedInteger(buf, value, minDigits);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[217]++;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[218]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((iFieldType >= SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[113]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[219]++;
                int dp = (int) (Math.abs(valueLong) % DateTimeConstants.MILLIS_PER_SECOND);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[220]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((iFieldType == SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((dp > 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[115]++;
                    buf.append('.');
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[221]++;
                    FormatUtils.appendPaddedInteger(buf, dp, 3);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[222]++;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[116]++;}

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[114]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[223]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((iSuffix != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[117]++;
                iSuffix.printTo(buf, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[224]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[118]++;}
        }

        public void printTo(Writer out, ReadablePeriod period, Locale locale) throws IOException {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[225]++;
            long valueLong = getFieldValue(period);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[226]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((valueLong == Long.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[119]++;
                return;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[120]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[227]++;
            int value = (int) valueLong;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[228]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((iFieldType >= SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[121]++;
                value = (int) (valueLong / DateTimeConstants.MILLIS_PER_SECOND);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[229]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[122]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[230]++;
int CodeCoverConditionCoverageHelper_C56;

            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((iPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[123]++;
                iPrefix.printTo(out, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[231]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[124]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[232]++;
            int minDigits = iMinPrintedDigits;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[233]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((minDigits <= 1) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[125]++;
                FormatUtils.writeUnpaddedInteger(out, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[234]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[126]++;
                FormatUtils.writePaddedInteger(out, value, minDigits);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[235]++;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[236]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((iFieldType >= SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[127]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[237]++;
                int dp = (int) (Math.abs(valueLong) % DateTimeConstants.MILLIS_PER_SECOND);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[238]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((iFieldType == SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((dp > 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[129]++;
                    out.write('.');
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[239]++;
                    FormatUtils.writePaddedInteger(out, dp, 3);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[240]++;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[130]++;}

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[128]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[241]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((iSuffix != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[131]++;
                iSuffix.printTo(out, value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[242]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[132]++;}
        }

        public int parseInto(
                ReadWritablePeriod period, String text, 
                int position, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[243]++;

            boolean mustParse = (iPrintZeroSetting == PRINT_ZERO_ALWAYS);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[244]++;
int CodeCoverConditionCoverageHelper_C61;

            // Shortcut test.
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((position >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[133]++;
                return mustParse ? ~position : position;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[134]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[245]++;
int CodeCoverConditionCoverageHelper_C62;

            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((iPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[135]++;
                position = iPrefix.parse(text, position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[246]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[247]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((position >= 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[137]++;
                    // If prefix is found, then the parse must finish.
                    mustParse = true;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[248]++;

                } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[138]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[249]++;
int CodeCoverConditionCoverageHelper_C64;
                    // Prefix not found, so bail.
                    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((mustParse) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[139]++;
                        // It's okay because parsing of this field is not
                        // required. Don't return an error. Fields down the
                        // chain can continue on, trying to parse.
                        return ~position;

                    } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[140]++;}
                    return position;
                }

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[136]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[250]++;

            int suffixPos = -1;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[251]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((iSuffix != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((mustParse) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[141]++;
                // Pre-scan the suffix, to help determine if this field must be
                // parsed.
                suffixPos = iSuffix.scan(text, position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[252]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[253]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((suffixPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[143]++;
                    // If suffix is found, then parse must finish.
                    mustParse = true;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[254]++;

                } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[144]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[255]++;
int CodeCoverConditionCoverageHelper_C67;
                    // Suffix not found, so bail.
                    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((mustParse) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[145]++;
                        // It's okay because parsing of this field is not
                        // required. Don't return an error. Fields down the
                        // chain can continue on, trying to parse.
                        return ~suffixPos;

                    } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[146]++;}
                    return suffixPos;
                }

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[142]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[256]++;
int CodeCoverConditionCoverageHelper_C68;

            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((mustParse) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isSupported(period.getPeriodType(), iFieldType)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[147]++;
                // If parsing is not required and the field is not supported,
                // exit gracefully so that another parser can continue on.
                return position;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[148]++;}

            int limit;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[257]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((suffixPos > 0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[149]++;
                limit = Math.min(iMaxParsedDigits, suffixPos - position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[258]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[150]++;
                limit = Math.min(iMaxParsedDigits, text.length() - position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[259]++;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[260]++;

            // validate input number
            int length = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[261]++;
            int fractPos = -1;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[262]++;
            boolean hasDigits = false;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[263]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[10]++;


int CodeCoverConditionCoverageHelper_C70;
            while ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((length < limit) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[10]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[11]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[12]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[264]++;
                char c = text.charAt(position + length);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[265]++;
int CodeCoverConditionCoverageHelper_C71;
                // leading sign
                if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (128)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C71 |= (32)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C71 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((iRejectSignedValues) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 4) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 4) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[151]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[266]++;
                    boolean negative = c == '-';
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[267]++;

                    // Next character must be a digit.
                    if (length + 1 >= limit || (c = text.charAt(position + length + 1)) < '0' || c > '9')
                    {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[153]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[268]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[154]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[269]++;
int CodeCoverConditionCoverageHelper_C73;

                    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[155]++;
                        length++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[270]++;

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[156]++;
                        // Skip the '+' for parseInt to succeed.
                        position++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[271]++;
                    }
                    // Expand the limit to disregard the sign character.
                    limit = Math.min(limit + 1, text.length() - position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[272]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[273]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[152]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[274]++;
int CodeCoverConditionCoverageHelper_C74;
                // main number
                if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[157]++;
                    hasDigits = true;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[275]++;

                } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[158]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[276]++;
int CodeCoverConditionCoverageHelper_C75;
                    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C75 |= (128)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (32)) == 0 || true) &&
 ((c == ',') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((iFieldType == SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((iFieldType == SECONDS_OPTIONAL_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 4) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 4) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[159]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[277]++;
int CodeCoverConditionCoverageHelper_C76;
                        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((fractPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[161]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[278]++;
                            // can't have two decimals
                            break;

                        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[162]++;}
                        fractPos = position + length + 1;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[279]++;
                        // Expand the limit to disregard the decimal point.
                        limit = Math.min(limit + 1, text.length() - position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[280]++;

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[160]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[281]++;
                        break;
                    }
                }
                length++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[282]++;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[283]++;
int CodeCoverConditionCoverageHelper_C77;

            if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((hasDigits) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[163]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[164]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[284]++;
int CodeCoverConditionCoverageHelper_C78;

            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((suffixPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((position + length != suffixPos) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[165]++;
                // If there are additional non-digit characters before the
                // suffix is reached, then assume that the suffix found belongs
                // to a field not yet reached. Return original position so that
                // another parser can continue on.
                return position;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[166]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[285]++;
int CodeCoverConditionCoverageHelper_C79;

            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((iFieldType != SECONDS_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((iFieldType != SECONDS_OPTIONAL_MILLIS) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[167]++;
                // Handle common case.
                setFieldValue(period, iFieldType, parseInt(text, position, length));
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[286]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[168]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[287]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((fractPos < 0) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[169]++;
                setFieldValue(period, SECONDS, parseInt(text, position, length));
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[288]++;
                setFieldValue(period, MILLIS, 0);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[289]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[170]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[290]++;
                int wholeValue = parseInt(text, position, fractPos - position - 1);
                setFieldValue(period, SECONDS, wholeValue);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[291]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[292]++;

                int fractLen = position + length - fractPos;
                int fractValue;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[293]++;
int CodeCoverConditionCoverageHelper_C81;
                if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((fractLen <= 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[171]++;
                    fractValue = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[294]++;

                } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[172]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[295]++;
int CodeCoverConditionCoverageHelper_C82;
                    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((fractLen >= 3) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[173]++;
                        fractValue = parseInt(text, fractPos, 3);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[296]++;

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[174]++;
                        fractValue = parseInt(text, fractPos, fractLen);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[297]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[298]++;
int CodeCoverConditionCoverageHelper_C83;
                        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((fractLen == 1) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[175]++;
                            fractValue *= 100;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[299]++;

                        } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[176]++;
                            fractValue *= 10;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[300]++;
                        }
                    }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[301]++;
int CodeCoverConditionCoverageHelper_C84;
                    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((wholeValue < 0) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[177]++;
                        fractValue = -fractValue;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[302]++;

                    } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[178]++;}
                }

                setFieldValue(period, MILLIS, fractValue);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[303]++;
            }
}
                
            position += length;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[304]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[305]++;
int CodeCoverConditionCoverageHelper_C85;

            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((position >= 0) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((iSuffix != null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[179]++;
                position = iSuffix.parse(text, position);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[306]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[180]++;}
                
            return position;
        }

        /**
         * @param text text to parse
         * @param position position in text
         * @param length exact count of characters to parse
         * @return parsed int value
         */
        private int parseInt(String text, int position, int length) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[307]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((length >= 10) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[181]++;
                // Since value may exceed max, use stock parser which checks for this.
                return Integer.parseInt(text.substring(position, position + length));

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[182]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[308]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((length <= 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[183]++;
                return 0;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[184]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[309]++;
            int value = text.charAt(position++);
            length--;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[310]++;
            boolean negative;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[311]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((value == '-') && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[185]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[312]++;
int CodeCoverConditionCoverageHelper_C89;
                if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((--length < 0) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[187]++;
                    return 0;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[188]++;}
                negative = true;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[313]++;
                value = text.charAt(position++);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[314]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[186]++;
                negative = false;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[315]++;
            }
            value -= '0';
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[316]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[317]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[13]++;


int CodeCoverConditionCoverageHelper_C90;
            while ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((length-- > 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[13]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[14]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[15]++;
}
                value = ((value << 3) + (value << 1)) + text.charAt(position++) - '0';
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[318]++;
            }
            return negative ? -value : value;
        }

        /**
         * @return Long.MAX_VALUE if nothing to print, otherwise value
         */
        long getFieldValue(ReadablePeriod period) {
            PeriodType type;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[319]++;
int CodeCoverConditionCoverageHelper_C91;
            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((iPrintZeroSetting == PRINT_ZERO_ALWAYS) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[189]++;
                type = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[320]++;
 // Don't need to check if supported.
            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[190]++;
                type = period.getPeriodType();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[321]++;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[322]++;
int CodeCoverConditionCoverageHelper_C92;
            if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((isSupported(type, iFieldType) == false) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[191]++;
                return Long.MAX_VALUE;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[192]++;}

            long value;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[323]++;

            switch (iFieldType) {
            default:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[193]++;
                return Long.MAX_VALUE;
            case YEARS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[194]++;
                value = period.get(DurationFieldType.years());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[324]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[325]++;
                break;
            case MONTHS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[195]++;
                value = period.get(DurationFieldType.months());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[326]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[327]++;
                break;
            case WEEKS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[196]++;
                value = period.get(DurationFieldType.weeks());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[328]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[329]++;
                break;
            case DAYS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[197]++;
                value = period.get(DurationFieldType.days());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[330]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[331]++;
                break;
            case HOURS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[198]++;
                value = period.get(DurationFieldType.hours());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[332]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[333]++;
                break;
            case MINUTES:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[199]++;
                value = period.get(DurationFieldType.minutes());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[334]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[335]++;
                break;
            case SECONDS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[200]++;
                value = period.get(DurationFieldType.seconds());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[336]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[337]++;
                break;
            case MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[201]++;
                value = period.get(DurationFieldType.millis());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[338]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[339]++;
                break;
            case SECONDS_MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[202]++; // drop through
            case SECONDS_OPTIONAL_MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[203]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[340]++;
                int seconds = period.get(DurationFieldType.seconds());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[341]++;
                int millis = period.get(DurationFieldType.millis());
                value = (seconds * (long) DateTimeConstants.MILLIS_PER_SECOND) + millis;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[342]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[343]++;
                break;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[344]++;
int CodeCoverConditionCoverageHelper_C93;

            // determine if period is zero and this is the last field
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((value == 0) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[204]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[345]++;
                switch (iPrintZeroSetting) {
                case PRINT_ZERO_NEVER:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[206]++;
                    return Long.MAX_VALUE;
                case PRINT_ZERO_RARELY_LAST:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[207]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[346]++;
int CodeCoverConditionCoverageHelper_C94;
                    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((isZero(period)) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((iFieldFormatters[iFieldType] == this) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[208]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[347]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[16]++;


int CodeCoverConditionCoverageHelper_C95;
                        for (int i = iFieldType + 1;(((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((i <= MAX_FIELD) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[16]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[17]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[18]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[348]++;
int CodeCoverConditionCoverageHelper_C96;
                            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (8)) == 0 || true) &&
 ((isSupported(type, i)) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((iFieldFormatters[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[210]++;
                                return Long.MAX_VALUE;

                            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[211]++;}
                        }

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[209]++;
                        return Long.MAX_VALUE;
                    }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[349]++;
                    break;
                case PRINT_ZERO_RARELY_FIRST:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[212]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[350]++;
int CodeCoverConditionCoverageHelper_C97;
                    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (8)) == 0 || true) &&
 ((isZero(period)) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((iFieldFormatters[iFieldType] == this) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[213]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[351]++;
                        int i = Math.min(iFieldType, 8);  // line split out for IBM JDK
                        i--;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[352]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[353]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[19]++;


int CodeCoverConditionCoverageHelper_C98;                              // see bug 1660490
                        for (;(((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (8)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((i <= MAX_FIELD) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 2) && false); i--) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[19]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[20]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[21]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[354]++;
int CodeCoverConditionCoverageHelper_C99;
                            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((isSupported(type, i)) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((iFieldFormatters[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[215]++;
                                return Long.MAX_VALUE;

                            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[216]++;}
                        }

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[214]++;
                        return Long.MAX_VALUE;
                    }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[355]++;
                    break; default : CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[217]++;
                }

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[205]++;}

            return value;
        }

        boolean isZero(ReadablePeriod period) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[356]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[22]++;


int CodeCoverConditionCoverageHelper_C100;
            for (int i = 0, isize = period.size();(((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[22]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[23]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[24]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[357]++;
int CodeCoverConditionCoverageHelper_C101;
                if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((period.getValue(i) != 0) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[218]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[219]++;}
            }
            return true;
        }

        boolean isSupported(PeriodType type, int field) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[358]++;
            switch (field) {
            default:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[220]++;
                return false;
            case YEARS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[221]++;
                return type.isSupported(DurationFieldType.years());
            case MONTHS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[222]++;
                return type.isSupported(DurationFieldType.months());
            case WEEKS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[223]++;
                return type.isSupported(DurationFieldType.weeks());
            case DAYS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[224]++;
                return type.isSupported(DurationFieldType.days());
            case HOURS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[225]++;
                return type.isSupported(DurationFieldType.hours());
            case MINUTES:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[226]++;
                return type.isSupported(DurationFieldType.minutes());
            case SECONDS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[227]++;
                return type.isSupported(DurationFieldType.seconds());
            case MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[228]++;
                return type.isSupported(DurationFieldType.millis());
            case SECONDS_MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[229]++; // drop through
            case SECONDS_OPTIONAL_MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[230]++;
                return type.isSupported(DurationFieldType.seconds()) ||
                       type.isSupported(DurationFieldType.millis());
            }
        }

        void setFieldValue(ReadWritablePeriod period, int field, int value) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[359]++;
            switch (field) {
            default:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[231]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[360]++;
                break;
            case YEARS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[232]++;
                period.setYears(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[361]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[362]++;
                break;
            case MONTHS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[233]++;
                period.setMonths(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[363]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[364]++;
                break;
            case WEEKS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[234]++;
                period.setWeeks(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[365]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[366]++;
                break;
            case DAYS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[235]++;
                period.setDays(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[367]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[368]++;
                break;
            case HOURS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[236]++;
                period.setHours(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[369]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[370]++;
                break;
            case MINUTES:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[237]++;
                period.setMinutes(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[371]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[372]++;
                break;
            case SECONDS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[238]++;
                period.setSeconds(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[373]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[374]++;
                break;
            case MILLIS:
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[239]++;
                period.setMillis(value);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[375]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[376]++;
                break;
            }
        }

        int getFieldType() {
            return iFieldType;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Handles a simple literal piece of text.
     */
    static class Literal
            implements PeriodPrinter, PeriodParser {
        static final Literal EMPTY = new Literal("");
        private final String iText;

        Literal(String text) {
            iText = text;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[377]++;
        }

        public int countFieldsToPrint(ReadablePeriod period, int stopAt, Locale locale) {
            return 0;
        }

        public int calculatePrintedLength(ReadablePeriod period, Locale locale) {
            return iText.length();
        }

        public void printTo(StringBuffer buf, ReadablePeriod period, Locale locale) {
            buf.append(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[378]++;
        }

        public void printTo(Writer out, ReadablePeriod period, Locale locale) throws IOException {
            out.write(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[379]++;
        }

        public int parseInto(
                ReadWritablePeriod period, String periodStr,
                int position, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[380]++;
int CodeCoverConditionCoverageHelper_C102;
            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches(true, position, iText, 0, iText.length())) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[240]++;
                return position + iText.length();

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[241]++;}
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Handles a separator, that splits the fields into multiple parts.
     * For example, the 'T' in the ISO8601 standard.
     */
    static class Separator
            implements PeriodPrinter, PeriodParser {
        private final String iText;
        private final String iFinalText;
        private final String[] iParsedForms;

        private final boolean iUseBefore;
        private final boolean iUseAfter;

        private final PeriodPrinter iBeforePrinter;
        private volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private volatile PeriodParser iAfterParser;

        Separator(String text, String finalText, String[] variants,
                PeriodPrinter beforePrinter, PeriodParser beforeParser,
                boolean useBefore, boolean useAfter) {
            iText = text;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[381]++;
            iFinalText = finalText;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[382]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[383]++;
int CodeCoverConditionCoverageHelper_C103;

            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C103 |= (128)) == 0 || true) &&
 ((finalText == null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C103 |= (32)) == 0 || true) &&
 ((text.equals(finalText)) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C103 |= (8)) == 0 || true) &&
 ((variants == null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((variants.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 4) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 4) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[242]++;

                iParsedForms = new String[] {text};
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[384]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[243]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[385]++;
                // Filter and reverse sort the parsed forms.
                TreeSet<String> parsedSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
                parsedSet.add(text);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[386]++;
                parsedSet.add(finalText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[387]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[388]++;
int CodeCoverConditionCoverageHelper_C104;
                if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((variants != null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[244]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[389]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[25]++;


int CodeCoverConditionCoverageHelper_C105;
                    for (int i=variants.length;(((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false); ) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[25]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[26]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[27]++;
}
                        parsedSet.add(variants[i]);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[390]++;
                    }

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[245]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[391]++;
                ArrayList<String> parsedList = new ArrayList<String>(parsedSet);
                Collections.reverse(parsedList);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[392]++;
                iParsedForms = parsedList.toArray(new String[parsedList.size()]);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[393]++;
            }

            iBeforePrinter = beforePrinter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[394]++;
            iBeforeParser = beforeParser;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[395]++;
            iUseBefore = useBefore;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[396]++;
            iUseAfter = useAfter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[397]++;
        }

        public int countFieldsToPrint(ReadablePeriod period, int stopAt, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[398]++;
            int sum = iBeforePrinter.countFieldsToPrint(period, stopAt, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[399]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((sum < stopAt) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[246]++;
                sum += iAfterPrinter.countFieldsToPrint(period, stopAt, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[400]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[247]++;}
            return sum;
        }

        public int calculatePrintedLength(ReadablePeriod period, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[401]++;
            PeriodPrinter before = iBeforePrinter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[402]++;
            PeriodPrinter after = iAfterPrinter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[403]++;
            
            int sum = before.calculatePrintedLength(period, locale)
                    + after.calculatePrintedLength(period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[404]++;
int CodeCoverConditionCoverageHelper_C107;
            
            if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((iUseBefore) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[248]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[405]++;
int CodeCoverConditionCoverageHelper_C108;
                if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((before.countFieldsToPrint(period, 1, locale) > 0) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[250]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[406]++;
int CodeCoverConditionCoverageHelper_C109;
                    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((iUseAfter) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[252]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[407]++;
                        int afterCount = after.countFieldsToPrint(period, 2, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[408]++;
int CodeCoverConditionCoverageHelper_C110;
                        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((afterCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[254]++;
                            sum += (afterCount > 1 ? iText : iFinalText).length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[409]++;

                        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[255]++;}

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[253]++;
                        sum += iText.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[410]++;
                    }

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[251]++;}

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[249]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[411]++;
int CodeCoverConditionCoverageHelper_C111; if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (8)) == 0 || true) &&
 ((iUseAfter) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((after.countFieldsToPrint(period, 1, locale) > 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[256]++;
                sum += iText.length();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[412]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[257]++;}
}
            
            return sum;
        }

        public void printTo(StringBuffer buf, ReadablePeriod period, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[413]++;
            PeriodPrinter before = iBeforePrinter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[414]++;
            PeriodPrinter after = iAfterPrinter;
            
            before.printTo(buf, period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[415]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[416]++;
int CodeCoverConditionCoverageHelper_C112;
            if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((iUseBefore) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[258]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[417]++;
int CodeCoverConditionCoverageHelper_C113;
                if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((before.countFieldsToPrint(period, 1, locale) > 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[260]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[418]++;
int CodeCoverConditionCoverageHelper_C114;
                    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((iUseAfter) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[262]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[419]++;
                        int afterCount = after.countFieldsToPrint(period, 2, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[420]++;
int CodeCoverConditionCoverageHelper_C115;
                        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((afterCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[264]++;
                            buf.append(afterCount > 1 ? iText : iFinalText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[421]++;

                        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[265]++;}

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[263]++;
                        buf.append(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[422]++;
                    }

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[261]++;}

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[259]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[423]++;
int CodeCoverConditionCoverageHelper_C116; if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (8)) == 0 || true) &&
 ((iUseAfter) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((after.countFieldsToPrint(period, 1, locale) > 0) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[266]++;
                buf.append(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[424]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[267]++;}
}
            after.printTo(buf, period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[425]++;
        }

        public void printTo(Writer out, ReadablePeriod period, Locale locale) throws IOException {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[426]++;
            PeriodPrinter before = iBeforePrinter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[427]++;
            PeriodPrinter after = iAfterPrinter;
            
            before.printTo(out, period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[428]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[429]++;
int CodeCoverConditionCoverageHelper_C117;
            if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((iUseBefore) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[268]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[430]++;
int CodeCoverConditionCoverageHelper_C118;
                if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((before.countFieldsToPrint(period, 1, locale) > 0) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[270]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[431]++;
int CodeCoverConditionCoverageHelper_C119;
                    if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((iUseAfter) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[272]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[432]++;
                        int afterCount = after.countFieldsToPrint(period, 2, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[433]++;
int CodeCoverConditionCoverageHelper_C120;
                        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((afterCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[274]++;
                            out.write(afterCount > 1 ? iText : iFinalText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[434]++;

                        } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[275]++;}

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[273]++;
                        out.write(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[435]++;
                    }

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[271]++;}

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[269]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[436]++;
int CodeCoverConditionCoverageHelper_C121; if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (8)) == 0 || true) &&
 ((iUseAfter) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((after.countFieldsToPrint(period, 1, locale) > 0) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[276]++;
                out.write(iText);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[437]++;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[277]++;}
}
            after.printTo(out, period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[438]++;
        }

        public int parseInto(
                ReadWritablePeriod period, String periodStr,
                int position, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[439]++;
            int oldPos = position;
            position = iBeforeParser.parseInto(period, periodStr, position, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[440]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[441]++;
int CodeCoverConditionCoverageHelper_C122;

            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((position < 0) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[278]++;
                return position;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[279]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[442]++;

            boolean found = false;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[443]++;
int CodeCoverConditionCoverageHelper_C123;
            if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((position > oldPos) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[280]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[444]++;
                // Consume this separator.
                String[] parsedForms = iParsedForms;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[445]++;
                int length = parsedForms.length;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[446]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[28]++;


int CodeCoverConditionCoverageHelper_C124;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[28]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[29]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[30]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[447]++;
                    String parsedForm = parsedForms[i];
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[448]++;
int CodeCoverConditionCoverageHelper_C125;
                    if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C125 |= (32)) == 0 || true) &&
 ((parsedForm == null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((parsedForm.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((periodStr.regionMatches
                        (true, position, parsedForm, 0, parsedForm.length())) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 3) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 3) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[282]++;
                        
                        position += (parsedForm == null ? 0 : parsedForm.length());
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[449]++;
                        found = true;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[450]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[451]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[283]++;}
                }

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[281]++;}

            oldPos = position;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[452]++;
            position = iAfterParser.parseInto(period, periodStr, position, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[453]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[454]++;
int CodeCoverConditionCoverageHelper_C126;

            if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((position < 0) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[284]++;
                return position;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[285]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[455]++;
int CodeCoverConditionCoverageHelper_C127;

            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (8)) == 0 || true) &&
 ((found) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((position == oldPos) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 2) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[286]++;
                // Separator should not have been supplied.
                return ~oldPos;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[287]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[456]++;
int CodeCoverConditionCoverageHelper_C128;

            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (32)) == 0 || true) &&
 ((position > oldPos) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((found) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((iUseBefore) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 3) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 3) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[288]++;
                // Separator was required.
                return ~oldPos;

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[289]++;}

            return position;
        }

        Separator finish(PeriodPrinter afterPrinter, PeriodParser afterParser) {
            iAfterPrinter = afterPrinter;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[457]++;
            iAfterParser = afterParser;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[458]++;
            return this;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Composite implementation that merges other fields to create a full pattern.
     */
    static class Composite
            implements PeriodPrinter, PeriodParser {
        
        private final PeriodPrinter[] iPrinters;
        private final PeriodParser[] iParsers;

        Composite(List<Object> elementPairs) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[459]++;
            List<Object> printerList = new ArrayList<Object>();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[460]++;
            List<Object> parserList = new ArrayList<Object>();

            decompose(elementPairs, printerList, parserList);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[461]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[462]++;
int CodeCoverConditionCoverageHelper_C129;

            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((printerList.size() <= 0) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[290]++;
                iPrinters = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[463]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[291]++;
                iPrinters = printerList.toArray(
                        new PeriodPrinter[printerList.size()]);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[464]++;
            }
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[465]++;
int CodeCoverConditionCoverageHelper_C130;

            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((parserList.size() <= 0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[292]++;
                iParsers = null;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[466]++;

            } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[293]++;
                iParsers = parserList.toArray(
                        new PeriodParser[parserList.size()]);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[467]++;
            }
        }

        public int countFieldsToPrint(ReadablePeriod period, int stopAt, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[468]++;
            int sum = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[469]++;
            PeriodPrinter[] printers = iPrinters;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[470]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[31]++;


int CodeCoverConditionCoverageHelper_C131;
            for (int i=printers.length;(((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (8)) == 0 || true) &&
 ((sum < stopAt) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) && false); ) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[31]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[32]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[33]++;
}
                sum += printers[i].countFieldsToPrint(period, Integer.MAX_VALUE, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[471]++;
            }
            return sum;
        }

        public int calculatePrintedLength(ReadablePeriod period, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[472]++;
            int sum = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[473]++;
            PeriodPrinter[] printers = iPrinters;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[474]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[34]++;


int CodeCoverConditionCoverageHelper_C132;
            for (int i=printers.length;(((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false); ) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[34]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[35]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[36]++;
}
                sum += printers[i].calculatePrintedLength(period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[475]++;
            }
            return sum;
        }

        public void printTo(StringBuffer buf, ReadablePeriod period, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[476]++;
            PeriodPrinter[] printers = iPrinters;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[477]++;
            int len = printers.length;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[478]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[37]++;


int CodeCoverConditionCoverageHelper_C133;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[37]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[38]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[39]++;
}
                printers[i].printTo(buf, period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[479]++;
            }
        }

        public void printTo(Writer out, ReadablePeriod period, Locale locale) throws IOException {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[480]++;
            PeriodPrinter[] printers = iPrinters;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[481]++;
            int len = printers.length;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[482]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[40]++;


int CodeCoverConditionCoverageHelper_C134;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[40]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[41]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[42]++;
}
                printers[i].printTo(out, period, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[483]++;
            }
        }

        public int parseInto(
                ReadWritablePeriod period, String periodStr,
                int position, Locale locale) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[484]++;
            PeriodParser[] parsers = iParsers;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[485]++;
int CodeCoverConditionCoverageHelper_C135;
            if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((parsers == null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[294]++;
                throw new UnsupportedOperationException();

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[295]++;}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[486]++;

            int len = parsers.length;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[487]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[43]++;


int CodeCoverConditionCoverageHelper_C136;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (8)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((position >= 0) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 2) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[43]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[44]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[45]++;
}
                position = parsers[i].parseInto(period, periodStr, position, locale);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[488]++;
            }
            return position;
        }

        private void decompose(List<Object> elementPairs, List<Object> printerList, List<Object> parserList) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[489]++;
            int size = elementPairs.size();
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[490]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[46]++;


int CodeCoverConditionCoverageHelper_C137;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false); i+=2) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[46]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[47]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[48]++;
}
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[491]++;
                Object element = elementPairs.get(i);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[492]++;
int CodeCoverConditionCoverageHelper_C138;
                if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((element instanceof PeriodPrinter) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[296]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[493]++;
int CodeCoverConditionCoverageHelper_C139;
                    if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((element instanceof Composite) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[298]++;
                        addArrayToList(printerList, ((Composite) element).iPrinters);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[494]++;

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[299]++;
                        printerList.add(element);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[495]++;
                    }

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[297]++;}

                element = elementPairs.get(i + 1);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[496]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[497]++;
int CodeCoverConditionCoverageHelper_C140;
                if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((element instanceof PeriodParser) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[300]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[498]++;
int CodeCoverConditionCoverageHelper_C141;
                    if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((element instanceof Composite) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[302]++;
                        addArrayToList(parserList, ((Composite) element).iParsers);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[499]++;

                    } else {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[303]++;
                        parserList.add(element);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[500]++;
                    }

                } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[301]++;}
            }
        }

        private void addArrayToList(List<Object> list, Object[] array) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[501]++;
int CodeCoverConditionCoverageHelper_C142;
            if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((array != null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[304]++;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[502]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[49]++;


int CodeCoverConditionCoverageHelper_C143;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((i<array.length) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[49]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[50]--;
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.loops[51]++;
}
                    list.add(array[i]);
CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.statements[503]++;
                }

            } else {
  CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1.branches[305]++;}
        }
    }

}

class CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1 ());
  }
    public static long[] statements = new long[504];
    public static long[] branches = new long[306];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[144];
  static {
    final String SECTION_NAME = "org.joda.time.format.PeriodFormatterBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,2,1,1,1,2,1,3,2,1,2,1,1,2,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,3,0,1,2,3,1,1,2,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,2,1,2,2,2,2,1,1,1,3,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,2,1,1,1,3,1,2,3,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 143; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[52];

  public CodeCoverCoverageCounter$55348yowg5r8zyz2b5kkx68y92f99h7un4tx88nvc1 () {
    super("org.joda.time.format.PeriodFormatterBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 503; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 305; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 143; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 51; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.PeriodFormatterBuilder.java");
      for (int i = 1; i <= 503; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 305; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 143; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 17; i++) {
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

