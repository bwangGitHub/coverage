/*
 *  Copyright 2001-2011 Stephen Colebourne
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.MutableDateTime.Property;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

/**
 * Factory that creates complex instances of DateTimeFormatter via method calls.
 * <p>
 * Datetime formatting is performed by the {@link DateTimeFormatter} class.
 * Three classes provide factory methods to create formatters, and this is one.
 * The others are {@link DateTimeFormat} and {@link ISODateTimeFormat}.
 * <p>
 * DateTimeFormatterBuilder is used for constructing formatters which are then
 * used to print or parse. The formatters are built by appending specific fields
 * or other formatters to an instance of this builder.
 * <p>
 * For example, a formatter that prints month and year, like "January 1970",
 * can be constructed as follows:
 * <p>
 * <pre>
 * DateTimeFormatter monthAndYear = new DateTimeFormatterBuilder()
 *     .appendMonthOfYearText()
 *     .appendLiteral(' ')
 *     .appendYear(4, 4)
 *     .toFormatter();
 * </pre>
 * <p>
 * DateTimeFormatterBuilder itself is mutable and not thread-safe, but the
 * formatters that it builds are thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @author Fredrik Borgh
 * @since 1.0
 * @see DateTimeFormat
 * @see ISODateTimeFormat
 */
public class DateTimeFormatterBuilder {
  static {
    CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.ping();
  }


    /** Array of printers and parsers (alternating). */
    private ArrayList<Object> iElementPairs;
    /** Cache of the last returned formatter. */
    private Object iFormatter;

    //-----------------------------------------------------------------------
    /**
     * Creates a DateTimeFormatterBuilder.
     */
    public DateTimeFormatterBuilder() {
        super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[1]++;
        iElementPairs = new ArrayList<Object>();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a DateTimeFormatter using all the appended elements.
     * <p>
     * This is the main method used by applications at the end of the build
     * process to create a usable formatter.
     * <p>
     * Subsequent changes to this builder do not affect the returned formatter.
     * <p>
     * The returned formatter may not support both printing and parsing.
     * The methods {@link DateTimeFormatter#isPrinter()} and
     * {@link DateTimeFormatter#isParser()} will help you determine the state
     * of the formatter.
     *
     * @throws UnsupportedOperationException if neither printing nor parsing is supported
     */
    public DateTimeFormatter toFormatter() {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[3]++;
        Object f = getFormatter();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[4]++;
        DateTimePrinter printer = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isPrinter(f)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[1]++;
            printer = (DateTimePrinter) f;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[6]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[2]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[7]++;
        DateTimeParser parser = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isParser(f)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[3]++;
            parser = (DateTimeParser) f;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[9]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[4]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((printer != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parser != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[5]++;
            return new DateTimeFormatter(printer, parser);

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[6]++;}
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }

    /**
     * Internal method to create a DateTimePrinter instance using all the
     * appended elements.
     * <p>
     * Most applications will not use this method.
     * If you want a printer in an application, call {@link #toFormatter()}
     * and just use the printing API.
     * <p>
     * Subsequent changes to this builder do not affect the returned printer.
     *
     * @throws UnsupportedOperationException if printing is not supported
     */
    public DateTimePrinter toPrinter() {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[11]++;
        Object f = getFormatter();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isPrinter(f)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[7]++;
            return (DateTimePrinter) f;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[8]++;}
        throw new UnsupportedOperationException("Printing is not supported");
    }

    /**
     * Internal method to create a DateTimeParser instance using all the
     * appended elements.
     * <p>
     * Most applications will not use this method.
     * If you want a parser in an application, call {@link #toFormatter()}
     * and just use the parsing API.
     * <p>
     * Subsequent changes to this builder do not affect the returned parser.
     *
     * @throws UnsupportedOperationException if parsing is not supported
     */
    public DateTimeParser toParser() {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[13]++;
        Object f = getFormatter();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isParser(f)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[9]++;
            return (DateTimeParser) f;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[10]++;}
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    //-----------------------------------------------------------------------
    /**
     * Returns true if toFormatter can be called without throwing an
     * UnsupportedOperationException.
     * 
     * @return true if a formatter can be built
     */
    public boolean canBuildFormatter() {
        return isFormatter(getFormatter());
    }

    /**
     * Returns true if toPrinter can be called without throwing an
     * UnsupportedOperationException.
     * 
     * @return true if a printer can be built
     */
    public boolean canBuildPrinter() {
        return isPrinter(getFormatter());
    }

    /**
     * Returns true if toParser can be called without throwing an
     * UnsupportedOperationException.
     * 
     * @return true if a parser can be built
     */
    public boolean canBuildParser() {
        return isParser(getFormatter());
    }

    //-----------------------------------------------------------------------
    /**
     * Clears out all the appended elements, allowing this builder to be
     * reused.
     */
    public void clear() {
        iFormatter = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[15]++;
        iElementPairs.clear();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[16]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Appends another formatter.
     *
     * @param formatter  the formatter to add
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if formatter is null or of an invalid type
     */
    public DateTimeFormatterBuilder append(DateTimeFormatter formatter) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[11]++;
            throw new IllegalArgumentException("No formatter supplied");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[12]++;}
        return append0(formatter.getPrinter(), formatter.getParser());
    }

    /**
     * Appends just a printer. With no matching parser, a parser cannot be
     * built from this DateTimeFormatterBuilder.
     *
     * @param printer  the printer to add
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if printer is null or of an invalid type
     */
    public DateTimeFormatterBuilder append(DateTimePrinter printer) {
        checkPrinter(printer);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[18]++;
        return append0(printer, null);
    }

    /**
     * Appends just a parser. With no matching printer, a printer cannot be
     * built from this builder.
     *
     * @param parser  the parser to add
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if parser is null or of an invalid type
     */
    public DateTimeFormatterBuilder append(DateTimeParser parser) {
        checkParser(parser);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[19]++;
        return append0(null, parser);
    }

    /**
     * Appends a printer/parser pair.
     *
     * @param printer  the printer to add
     * @param parser  the parser to add
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if printer or parser is null or of an invalid type
     */
    public DateTimeFormatterBuilder append(DateTimePrinter printer, DateTimeParser parser) {
        checkPrinter(printer);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[20]++;
        checkParser(parser);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[21]++;
        return append0(printer, parser);
    }

    /**
     * Appends a printer and a set of matching parsers. When parsing, the first
     * parser in the list is selected for parsing. If it fails, the next is
     * chosen, and so on. If none of these parsers succeeds, then the failed
     * position of the parser that made the greatest progress is returned.
     * <p>
     * Only the printer is optional. In addition, it is illegal for any but the
     * last of the parser array elements to be null. If the last element is
     * null, this represents the empty parser. The presence of an empty parser
     * indicates that the entire array of parse formats is optional.
     *
     * @param printer  the printer to add
     * @param parsers  the parsers to add
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if any printer or parser is of an invalid type
     * @throws IllegalArgumentException if any parser element but the last is null
     */
    public DateTimeFormatterBuilder append(DateTimePrinter printer, DateTimeParser[] parsers) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((printer != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[13]++;
            checkPrinter(printer);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[23]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[14]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((parsers == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[15]++;
            throw new IllegalArgumentException("No parsers supplied");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[16]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[25]++;
        int length = parsers.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((length == 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[17]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parsers[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[19]++;
                throw new IllegalArgumentException("No parser supplied");

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[20]++;}
            return append0(printer, parsers[0]);

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[18]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[28]++;

        DateTimeParser[] copyOfParsers = new DateTimeParser[length];
        int i;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
        for (i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < length - 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[1]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[2]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[3]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[30]++;
            if ((copyOfParsers[i] = parsers[i]) == null) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[21]++;
                throw new IllegalArgumentException("Incomplete parser array");

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[22]++;}
        }
        copyOfParsers[i] = parsers[i];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[31]++;

        return append0(printer, new MatchingParser(copyOfParsers));
    }

    /**
     * Appends just a parser element which is optional. With no matching
     * printer, a printer cannot be built from this DateTimeFormatterBuilder.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if parser is null or of an invalid type
     */
    public DateTimeFormatterBuilder appendOptional(DateTimeParser parser) {
        checkParser(parser);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[32]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[33]++;
        DateTimeParser[] parsers = new DateTimeParser[] {parser, null};
        return append0(null, new MatchingParser(parsers));
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the parser is non null and a provider.
     * 
     * @param parser  the parser to check
     */
    private void checkParser(DateTimeParser parser) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[34]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((parser == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[23]++;
            throw new IllegalArgumentException("No parser supplied");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[24]++;}
    }

    /**
     * Checks if the printer is non null and a provider.
     * 
     * @param printer  the printer to check
     */
    private void checkPrinter(DateTimePrinter printer) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((printer == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[25]++;
            throw new IllegalArgumentException("No printer supplied");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[26]++;}
    }

    private DateTimeFormatterBuilder append0(Object element) {
        iFormatter = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[36]++;
        // Add the element as both a printer and parser.
        iElementPairs.add(element);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[37]++;
        iElementPairs.add(element);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[38]++;
        return this;
    }

    private DateTimeFormatterBuilder append0(
            DateTimePrinter printer, DateTimeParser parser) {
        iFormatter = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[39]++;
        iElementPairs.add(printer);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[40]++;
        iElementPairs.add(parser);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[41]++;
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Instructs the printer to emit a specific character, and the parser to
     * expect it. The parser is case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    /**
     * Instructs the printer to emit specific text, and the parser to expect
     * it. The parser is case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if text is null
     */
    public DateTimeFormatterBuilder appendLiteral(String text) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[27]++;
            throw new IllegalArgumentException("Literal must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[28]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[43]++;
        switch (text.length()) {
            case 0:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[29]++;
                return this;
            case 1:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[30]++;
                return append0(new CharacterLiteral(text.charAt(0)));
            default:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[31]++;
                return append0(new StringLiteral(text));
        }
    }

    /**
     * Instructs the printer to emit a field value as a decimal number, and the
     * parser to expect an unsigned decimal number.
     *
     * @param fieldType  type of field to append
     * @param minDigits  minimum number of digits to <i>print</i>
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null
     */
    public DateTimeFormatterBuilder appendDecimal(
            DateTimeFieldType fieldType, int minDigits, int maxDigits) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[44]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[32]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[33]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((maxDigits < minDigits) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[34]++;
            maxDigits = minDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[46]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[35]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[47]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((minDigits < 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((maxDigits <= 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[36]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[37]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[48]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((minDigits <= 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[38]++;
            return append0(new UnpaddedNumber(fieldType, maxDigits, false));

        } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[39]++;
            return append0(new PaddedNumber(fieldType, maxDigits, false, minDigits));
        }
    }

    /**
     * Instructs the printer to emit a field value as a fixed-width decimal
     * number (smaller numbers will be left-padded with zeros), and the parser
     * to expect an unsigned decimal number with the same fixed width.
     * 
     * @param fieldType  type of field to append
     * @param numDigits  the exact number of digits to parse or print, except if
     * printed value requires more digits
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null or if <code>numDigits <= 0</code>
     * @since 1.5
     */
    public DateTimeFormatterBuilder appendFixedDecimal(
            DateTimeFieldType fieldType, int numDigits) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[49]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[40]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[41]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[50]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((numDigits <= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[42]++;
            throw new IllegalArgumentException("Illegal number of digits: " + numDigits);

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[43]++;}
        return append0(new FixedNumber(fieldType, numDigits, false));
    }

    /**
     * Instructs the printer to emit a field value as a decimal number, and the
     * parser to expect a signed decimal number.
     *
     * @param fieldType  type of field to append
     * @param minDigits  minimum number of digits to <i>print</i>
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null
     */
    public DateTimeFormatterBuilder appendSignedDecimal(
            DateTimeFieldType fieldType, int minDigits, int maxDigits) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[51]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[44]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[45]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[52]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((maxDigits < minDigits) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[46]++;
            maxDigits = minDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[53]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[47]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[54]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((minDigits < 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((maxDigits <= 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[48]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[49]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[55]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((minDigits <= 1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[50]++;
            return append0(new UnpaddedNumber(fieldType, maxDigits, true));

        } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[51]++;
            return append0(new PaddedNumber(fieldType, maxDigits, true, minDigits));
        }
    }

    /**
     * Instructs the printer to emit a field value as a fixed-width decimal
     * number (smaller numbers will be left-padded with zeros), and the parser
     * to expect an signed decimal number with the same fixed width.
     * 
     * @param fieldType  type of field to append
     * @param numDigits  the exact number of digits to parse or print, except if
     * printed value requires more digits
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null or if <code>numDigits <= 0</code>
     * @since 1.5
     */
    public DateTimeFormatterBuilder appendFixedSignedDecimal(
            DateTimeFieldType fieldType, int numDigits) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[56]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[52]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[53]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[57]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((numDigits <= 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[54]++;
            throw new IllegalArgumentException("Illegal number of digits: " + numDigits);

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[55]++;}
        return append0(new FixedNumber(fieldType, numDigits, true));
    }

    /**
     * Instructs the printer to emit a field value as text, and the
     * parser to expect text.
     *
     * @param fieldType  type of field to append
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null
     */
    public DateTimeFormatterBuilder appendText(DateTimeFieldType fieldType) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[58]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[56]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[57]++;}
        return append0(new TextField(fieldType, false));
    }

    /**
     * Instructs the printer to emit a field value as short text, and the
     * parser to expect text.
     *
     * @param fieldType  type of field to append
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null
     */
    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType fieldType) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[59]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[58]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[59]++;}
        return append0(new TextField(fieldType, true));
    }

    /**
     * Instructs the printer to emit a remainder of time as a decimal fraction,
     * without decimal point. For example, if the field is specified as
     * minuteOfHour and the time is 12:30:45, the value printed is 75. A
     * decimal point is implied, so the fraction is 0.75, or three-quarters of
     * a minute.
     *
     * @param fieldType  type of field to append
     * @param minDigits  minimum number of digits to print.
     * @param maxDigits  maximum number of digits to print or parse.
     * @return this DateTimeFormatterBuilder, for chaining
     * @throws IllegalArgumentException if field type is null
     */
    public DateTimeFormatterBuilder appendFraction(
            DateTimeFieldType fieldType, int minDigits, int maxDigits) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[60]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[60]++;
            throw new IllegalArgumentException("Field type must not be null");

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[61]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[61]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((maxDigits < minDigits) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[62]++;
            maxDigits = minDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[62]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[63]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[63]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((minDigits < 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((maxDigits <= 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[64]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[65]++;}
        return append0(new Fraction(fieldType, minDigits, maxDigits));
    }

    /**
     * Appends the print/parse of a fractional second.
     * <p>
     * This reliably handles the case where fractional digits are being handled
     * beyond a visible decimal point. The digits parsed will always be treated
     * as the most significant (numerically largest) digits.
     * Thus '23' will be parsed as 230 milliseconds.
     * Contrast this behaviour to {@link #appendMillisOfSecond}.
     * This method does not print or parse the decimal point itself.
     * 
     * @param minDigits  minimum number of digits to print
     * @param maxDigits  maximum number of digits to print or parse
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendFractionOfSecond(int minDigits, int maxDigits) {
        return appendFraction(DateTimeFieldType.secondOfDay(), minDigits, maxDigits);
    }

    /**
     * Appends the print/parse of a fractional minute.
     * <p>
     * This reliably handles the case where fractional digits are being handled
     * beyond a visible decimal point. The digits parsed will always be treated
     * as the most significant (numerically largest) digits.
     * Thus '23' will be parsed as 0.23 minutes (converted to milliseconds).
     * This method does not print or parse the decimal point itself.
     * 
     * @param minDigits  minimum number of digits to print
     * @param maxDigits  maximum number of digits to print or parse
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendFractionOfMinute(int minDigits, int maxDigits) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), minDigits, maxDigits);
    }

    /**
     * Appends the print/parse of a fractional hour.
     * <p>
     * This reliably handles the case where fractional digits are being handled
     * beyond a visible decimal point. The digits parsed will always be treated
     * as the most significant (numerically largest) digits.
     * Thus '23' will be parsed as 0.23 hours (converted to milliseconds).
     * This method does not print or parse the decimal point itself.
     * 
     * @param minDigits  minimum number of digits to print
     * @param maxDigits  maximum number of digits to print or parse
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendFractionOfHour(int minDigits, int maxDigits) {
        return appendFraction(DateTimeFieldType.hourOfDay(), minDigits, maxDigits);
    }

    /**
     * Appends the print/parse of a fractional day.
     * <p>
     * This reliably handles the case where fractional digits are being handled
     * beyond a visible decimal point. The digits parsed will always be treated
     * as the most significant (numerically largest) digits.
     * Thus '23' will be parsed as 0.23 days (converted to milliseconds).
     * This method does not print or parse the decimal point itself.
     * 
     * @param minDigits  minimum number of digits to print
     * @param maxDigits  maximum number of digits to print or parse
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendFractionOfDay(int minDigits, int maxDigits) {
        return appendFraction(DateTimeFieldType.dayOfYear(), minDigits, maxDigits);
    }

    /**
     * Instructs the printer to emit a numeric millisOfSecond field.
     * <p>
     * This method will append a field that prints a three digit value.
     * During parsing the value that is parsed is assumed to be three digits.
     * If less than three digits are present then they will be counted as the
     * smallest parts of the millisecond. This is probably not what you want
     * if you are using the field as a fraction. Instead, a fractional
     * millisecond should be produced using {@link #appendFractionOfSecond}.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMillisOfSecond(int minDigits) {
        return appendDecimal(DateTimeFieldType.millisOfSecond(), minDigits, 3);
    }

    /**
     * Instructs the printer to emit a numeric millisOfDay field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMillisOfDay(int minDigits) {
        return appendDecimal(DateTimeFieldType.millisOfDay(), minDigits, 8);
    }

    /**
     * Instructs the printer to emit a numeric secondOfMinute field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendSecondOfMinute(int minDigits) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric secondOfDay field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendSecondOfDay(int minDigits) {
        return appendDecimal(DateTimeFieldType.secondOfDay(), minDigits, 5);
    }

    /**
     * Instructs the printer to emit a numeric minuteOfHour field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMinuteOfHour(int minDigits) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric minuteOfDay field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMinuteOfDay(int minDigits) {
        return appendDecimal(DateTimeFieldType.minuteOfDay(), minDigits, 4);
    }

    /**
     * Instructs the printer to emit a numeric hourOfDay field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendHourOfDay(int minDigits) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric clockhourOfDay field.
     *
     * @param minDigits minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendClockhourOfDay(int minDigits) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric hourOfHalfday field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendHourOfHalfday(int minDigits) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric clockhourOfHalfday field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendClockhourOfHalfday(int minDigits) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric dayOfWeek field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendDayOfWeek(int minDigits) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), minDigits, 1);
    }

    /**
     * Instructs the printer to emit a numeric dayOfMonth field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendDayOfMonth(int minDigits) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric dayOfYear field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendDayOfYear(int minDigits) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), minDigits, 3);
    }

    /**
     * Instructs the printer to emit a numeric weekOfWeekyear field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendWeekOfWeekyear(int minDigits) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric weekyear field.
     *
     * @param minDigits  minimum number of digits to <i>print</i>
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendWeekyear(int minDigits, int maxDigits) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), minDigits, maxDigits);
    }

    /**
     * Instructs the printer to emit a numeric monthOfYear field.
     *
     * @param minDigits  minimum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMonthOfYear(int minDigits) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), minDigits, 2);
    }

    /**
     * Instructs the printer to emit a numeric year field.
     *
     * @param minDigits  minimum number of digits to <i>print</i>
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendYear(int minDigits, int maxDigits) {
        return appendSignedDecimal(DateTimeFieldType.year(), minDigits, maxDigits);
    }

    /**
     * Instructs the printer to emit a numeric year field which always prints
     * and parses two digits. A pivot year is used during parsing to determine
     * the range of supported years as <code>(pivot - 50) .. (pivot + 49)</code>.
     *
     * <pre>
     * pivot   supported range   00 is   20 is   40 is   60 is   80 is
     * ---------------------------------------------------------------
     * 1950      1900..1999      1900    1920    1940    1960    1980
     * 1975      1925..2024      2000    2020    1940    1960    1980
     * 2000      1950..2049      2000    2020    2040    1960    1980
     * 2025      1975..2074      2000    2020    2040    2060    1980
     * 2050      2000..2099      2000    2020    2040    2060    2080
     * </pre>
     *
     * @param pivot  pivot year to use when parsing
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTwoDigitYear(int pivot) {
        return appendTwoDigitYear(pivot, false);
    }

    /**
     * Instructs the printer to emit a numeric year field which always prints
     * two digits. A pivot year is used during parsing to determine the range
     * of supported years as <code>(pivot - 50) .. (pivot + 49)</code>. If
     * parse is instructed to be lenient and the digit count is not two, it is
     * treated as an absolute year. With lenient parsing, specifying a positive
     * or negative sign before the year also makes it absolute.
     *
     * @param pivot  pivot year to use when parsing
     * @param lenientParse  when true, if digit count is not two, it is treated
     * as an absolute year
     * @return this DateTimeFormatterBuilder, for chaining
     * @since 1.1
     */
    public DateTimeFormatterBuilder appendTwoDigitYear(int pivot, boolean lenientParse) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), pivot, lenientParse));
    }

    /**
     * Instructs the printer to emit a numeric weekyear field which always prints
     * and parses two digits. A pivot year is used during parsing to determine
     * the range of supported years as <code>(pivot - 50) .. (pivot + 49)</code>.
     *
     * <pre>
     * pivot   supported range   00 is   20 is   40 is   60 is   80 is
     * ---------------------------------------------------------------
     * 1950      1900..1999      1900    1920    1940    1960    1980
     * 1975      1925..2024      2000    2020    1940    1960    1980
     * 2000      1950..2049      2000    2020    2040    1960    1980
     * 2025      1975..2074      2000    2020    2040    2060    1980
     * 2050      2000..2099      2000    2020    2040    2060    2080
     * </pre>
     *
     * @param pivot  pivot weekyear to use when parsing
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int pivot) {
        return appendTwoDigitWeekyear(pivot, false);
    }

    /**
     * Instructs the printer to emit a numeric weekyear field which always prints
     * two digits. A pivot year is used during parsing to determine the range
     * of supported years as <code>(pivot - 50) .. (pivot + 49)</code>. If
     * parse is instructed to be lenient and the digit count is not two, it is
     * treated as an absolute weekyear. With lenient parsing, specifying a positive
     * or negative sign before the weekyear also makes it absolute.
     *
     * @param pivot  pivot weekyear to use when parsing
     * @param lenientParse  when true, if digit count is not two, it is treated
     * as an absolute weekyear
     * @return this DateTimeFormatterBuilder, for chaining
     * @since 1.1
     */
    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int pivot, boolean lenientParse) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), pivot, lenientParse));
    }

    /**
     * Instructs the printer to emit a numeric yearOfEra field.
     *
     * @param minDigits  minimum number of digits to <i>print</i>
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendYearOfEra(int minDigits, int maxDigits) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), minDigits, maxDigits);
    }

    /**
     * Instructs the printer to emit a numeric year of century field.
     *
     * @param minDigits  minimum number of digits to print
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendYearOfCentury(int minDigits, int maxDigits) {
        return appendDecimal(DateTimeFieldType.yearOfCentury(), minDigits, maxDigits);
    }

    /**
     * Instructs the printer to emit a numeric century of era field.
     *
     * @param minDigits  minimum number of digits to print
     * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimated
     * maximum number of digits to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendCenturyOfEra(int minDigits, int maxDigits) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), minDigits, maxDigits);
    }

    /**
     * Instructs the printer to emit a locale-specific AM/PM text, and the
     * parser to expect it. The parser is case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    /**
     * Instructs the printer to emit a locale-specific dayOfWeek text. The
     * parser will accept a long or short dayOfWeek text, case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    /**
     * Instructs the printer to emit a short locale-specific dayOfWeek
     * text. The parser will accept a long or short dayOfWeek text,
     * case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    /**
     * Instructs the printer to emit a short locale-specific monthOfYear
     * text. The parser will accept a long or short monthOfYear text,
     * case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMonthOfYearText() { 
        return appendText(DateTimeFieldType.monthOfYear());
    }

    /**
     * Instructs the printer to emit a locale-specific monthOfYear text. The
     * parser will accept a long or short monthOfYear text, case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    /**
     * Instructs the printer to emit a locale-specific era text (BC/AD), and
     * the parser to expect it. The parser is case-insensitive.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    /**
     * Instructs the printer to emit a locale-specific time zone name.
     * Using this method prevents parsing, because time zone names are not unique.
     * See {@link #appendTimeZoneName(Map)}.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(TimeZoneName.LONG_NAME, null), null);
    }

    /**
     * Instructs the printer to emit a locale-specific time zone name, providing a lookup for parsing.
     * Time zone names are not unique, thus the API forces you to supply the lookup.
     * The names are searched in the order of the map, thus it is strongly recommended
     * to use a {@code LinkedHashMap} or similar.
     *
     * @param parseLookup  the table of names, not null
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTimeZoneName(Map<String, DateTimeZone> parseLookup) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[64]++;
        TimeZoneName pp = new TimeZoneName(TimeZoneName.LONG_NAME, parseLookup);
        return append0(pp, pp);
    }

    /**
     * Instructs the printer to emit a short locale-specific time zone name.
     * Using this method prevents parsing, because time zone names are not unique.
     * See {@link #appendTimeZoneShortName(Map)}.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTimeZoneShortName() {
        return append0(new TimeZoneName(TimeZoneName.SHORT_NAME, null), null);
    }

    /**
     * Instructs the printer to emit a short locale-specific time zone
     * name, providing a lookup for parsing.
     * Time zone names are not unique, thus the API forces you to supply the lookup.
     * The names are searched in the order of the map, thus it is strongly recommended
     * to use a {@code LinkedHashMap} or similar.
     *
     * @param parseLookup  the table of names, not null
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> parseLookup) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[65]++;
        TimeZoneName pp = new TimeZoneName(TimeZoneName.SHORT_NAME, parseLookup);
        return append0(pp, pp);
    }

    /**
     * Instructs the printer to emit the identifier of the time zone.
     * From version 2.0, this field can be parsed.
     *
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTimeZoneId() {
        return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
    }

    /**
     * Instructs the printer to emit text and numbers to display time zone
     * offset from UTC. A parser will use the parsed time zone offset to adjust
     * the datetime.
     * <p>
     * If zero offset text is supplied, then it will be printed when the zone is zero.
     * During parsing, either the zero offset text, or the offset will be parsed.
     *
     * @param zeroOffsetText  the text to use if time zone offset is zero. If
     * null, offset is always shown.
     * @param showSeparators  if true, prints ':' separator before minute and
     * second field and prints '.' separator before fraction field.
     * @param minFields  minimum number of fields to print, stopping when no
     * more precision is required. 1=hours, 2=minutes, 3=seconds, 4=fraction
     * @param maxFields  maximum number of fields to print
     * @return this DateTimeFormatterBuilder, for chaining
     */
    public DateTimeFormatterBuilder appendTimeZoneOffset(
            String zeroOffsetText, boolean showSeparators,
            int minFields, int maxFields) {
        return append0(new TimeZoneOffset
                       (zeroOffsetText, zeroOffsetText, showSeparators, minFields, maxFields));
    }

    /**
     * Instructs the printer to emit text and numbers to display time zone
     * offset from UTC. A parser will use the parsed time zone offset to adjust
     * the datetime.
     * <p>
     * If zero offset print text is supplied, then it will be printed when the zone is zero.
     * If zero offset parse text is supplied, then either it or the offset will be parsed.
     *
     * @param zeroOffsetPrintText  the text to print if time zone offset is zero. If
     * null, offset is always shown.
     * @param zeroOffsetParseText  the text to optionally parse to indicate that the time
     * zone offset is zero. If null, then always use the offset.
     * @param showSeparators  if true, prints ':' separator before minute and
     * second field and prints '.' separator before fraction field.
     * @param minFields  minimum number of fields to print, stopping when no
     * more precision is required. 1=hours, 2=minutes, 3=seconds, 4=fraction
     * @param maxFields  maximum number of fields to print
     * @return this DateTimeFormatterBuilder, for chaining
     * @since 2.0
     */
    public DateTimeFormatterBuilder appendTimeZoneOffset(
            String zeroOffsetPrintText, String zeroOffsetParseText, boolean showSeparators,
            int minFields, int maxFields) {
        return append0(new TimeZoneOffset
                       (zeroOffsetPrintText, zeroOffsetParseText, showSeparators, minFields, maxFields));
    }

    //-----------------------------------------------------------------------
    /**
     * Calls upon {@link DateTimeFormat} to parse the pattern and append the
     * results into this builder.
     *
     * @param pattern  pattern specification
     * @throws IllegalArgumentException if the pattern is invalid
     * @see DateTimeFormat
     */
    public DateTimeFormatterBuilder appendPattern(String pattern) {
        DateTimeFormat.appendPatternTo(this, pattern);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[66]++;
        return this;
    }

    //-----------------------------------------------------------------------
    private Object getFormatter() {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[67]++;
        Object f = iFormatter;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[68]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[66]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[69]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((iElementPairs.size() == 2) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[68]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[70]++;
                Object printer = iElementPairs.get(0);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[71]++;
                Object parser = iElementPairs.get(1);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[72]++;
int CodeCoverConditionCoverageHelper_C35;

                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((printer != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[70]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[73]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((printer == parser) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((parser == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[72]++;
                        f = printer;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[74]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[73]++;}

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[71]++;
                    f = parser;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[75]++;
                }

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[69]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[76]++;
int CodeCoverConditionCoverageHelper_C37;

            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[74]++;
                f = new Composite(iElementPairs);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[77]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[75]++;}

            iFormatter = f;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[78]++;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[67]++;}

        return f;
    }

    private boolean isPrinter(Object f) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[79]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((f instanceof DateTimePrinter) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[76]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[80]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((f instanceof Composite) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[78]++;
                return ((Composite)f).isPrinter();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[79]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[77]++;}
        return false;
    }

    private boolean isParser(Object f) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[81]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((f instanceof DateTimeParser) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[80]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[82]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((f instanceof Composite) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[82]++;
                return ((Composite)f).isParser();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[83]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[81]++;}
        return false;
    }

    private boolean isFormatter(Object f) {
        return (isPrinter(f) || isParser(f));
    }

    static void appendUnknownString(StringBuffer buf, int len) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[83]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[4]++;


int CodeCoverConditionCoverageHelper_C42;
        for (int i = len;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((--i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[4]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[5]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[6]++;
}
            buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[84]++;
        }
    }

    static void printUnknownString(Writer out, int len) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[85]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[7]++;


int CodeCoverConditionCoverageHelper_C43;
        for (int i = len;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((--i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[7]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[8]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[9]++;
}
            out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[86]++;
        }
    }

    //-----------------------------------------------------------------------
    static class CharacterLiteral
            implements DateTimePrinter, DateTimeParser {

        private final char iValue;

        CharacterLiteral(char value) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[87]++;
            iValue = value;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[88]++;
        }

        public int estimatePrintedLength() {
            return 1;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
            buf.append(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[89]++;
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
            out.write(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[90]++;
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
            buf.append(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[91]++;
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
            out.write(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[92]++;
        }

        public int estimateParsedLength() {
            return 1;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[93]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((position >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[84]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[85]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[94]++;

            char a = text.charAt(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[95]++;
            char b = iValue;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[96]++;
int CodeCoverConditionCoverageHelper_C45;

            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((a != b) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[86]++;
                a = Character.toUpperCase(a);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[97]++;
                b = Character.toUpperCase(b);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[98]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[99]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((a != b) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[88]++;
                    a = Character.toLowerCase(a);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[100]++;
                    b = Character.toLowerCase(b);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[101]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[102]++;
int CodeCoverConditionCoverageHelper_C47;
                    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((a != b) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[90]++;
                        return ~position;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[91]++;}

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[89]++;}

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[87]++;}

            return position + 1;
        }
    }

    //-----------------------------------------------------------------------
    static class StringLiteral
            implements DateTimePrinter, DateTimeParser {

        private final String iValue;

        StringLiteral(String value) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[103]++;
            iValue = value;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[104]++;
        }

        public int estimatePrintedLength() {
            return iValue.length();
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
            buf.append(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[105]++;
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
            out.write(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[106]++;
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
            buf.append(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[107]++;
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
            out.write(iValue);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[108]++;
        }

        public int estimateParsedLength() {
            return iValue.length();
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[109]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((text.regionMatches(true, position, iValue, 0, iValue.length())) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[92]++;
                return position + iValue.length();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[93]++;}
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    static abstract class NumberFormatter
            implements DateTimePrinter, DateTimeParser {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType fieldType,
                int maxParsedDigits, boolean signed) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[110]++;
            iFieldType = fieldType;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[111]++;
            iMaxParsedDigits = maxParsedDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[112]++;
            iSigned = signed;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[113]++;
        }

        public int estimateParsedLength() {
            return iMaxParsedDigits;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[114]++;
            int limit = Math.min(iMaxParsedDigits, text.length() - position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[115]++;

            boolean negative = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[116]++;
            int length = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[117]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[10]++;


int CodeCoverConditionCoverageHelper_C49;
            while ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((length < limit) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[10]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[11]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[12]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[118]++;
                char c = text.charAt(position + length);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[119]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (128)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((iSigned) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 4) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 4) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[94]++;
                    negative = c == '-';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[120]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[121]++;

                    // Next character must be a digit.
                    if (length + 1 >= limit || (c = text.charAt(position + length + 1)) < '0' || c > '9')
                    {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[96]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[122]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[97]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[123]++;
int CodeCoverConditionCoverageHelper_C52;

                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[98]++;
                        length++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[124]++;

                    } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[99]++;
                        // Skip the '+' for parseInt to succeed.
                        position++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[125]++;
                    }
                    // Expand the limit to disregard the sign character.
                    limit = Math.min(limit + 1, text.length() - position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[126]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[127]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[95]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[128]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[100]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[129]++;
                    break;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[101]++;}
                length++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[130]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[131]++;
int CodeCoverConditionCoverageHelper_C54;

            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[102]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[103]++;}

            int value;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[132]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((length >= 9) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[104]++;
                // Since value may exceed integer limits, use stock parser
                // which checks for this.
                value = Integer.parseInt(text.substring(position, position += length));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[133]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[105]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[134]++;
                int i = position;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[135]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[106]++;
                    i++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[136]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[107]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[137]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    value = text.charAt(i++) - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[138]++;
                } catch (StringIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[109]++;
                    return ~position;
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[108]++;
}
  }
                position += length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[139]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[140]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[13]++;


int CodeCoverConditionCoverageHelper_C57;
                while ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i < position) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[13]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[14]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[15]++;
}
                    value = ((value << 3) + (value << 1)) + text.charAt(i++) - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[141]++;
                }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[142]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[110]++;
                    value = -value;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[143]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[111]++;}
            }

            bucket.saveField(iFieldType, value);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[144]++;
            return position;
        }
    }

    //-----------------------------------------------------------------------
    static class UnpaddedNumber extends NumberFormatter {

        protected UnpaddedNumber(DateTimeFieldType fieldType,
                       int maxParsedDigits, boolean signed)
        {
            super(fieldType, maxParsedDigits, signed);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[145]++;
        }

        public int estimatePrintedLength() {
            return iMaxParsedDigits;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[146]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[147]++;
                DateTimeField field = iFieldType.getField(chrono);
                FormatUtils.appendUnpaddedInteger(buf, field.get(instant));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[148]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[113]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[149]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[112]++;
}
  }
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[150]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[151]++;
                DateTimeField field = iFieldType.getField(chrono);
                FormatUtils.writeUnpaddedInteger(out, field.get(instant));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[152]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[115]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[153]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[114]++;
}
  }
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[154]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((partial.isSupported(iFieldType)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[116]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[155]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                try {
CodeCoverTryBranchHelper_Try4 = true;
                    FormatUtils.appendUnpaddedInteger(buf, partial.get(iFieldType));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[156]++;
                } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[119]++;
                    buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[157]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[118]++;
}
  }

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[117]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[158]++;
            }
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[159]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((partial.isSupported(iFieldType)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[120]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[160]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                try {
CodeCoverTryBranchHelper_Try5 = true;
                    FormatUtils.writeUnpaddedInteger(out, partial.get(iFieldType));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[161]++;
                } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[123]++;
                    out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[162]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[122]++;
}
  }

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[121]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[163]++;
            }
        }
    }

    //-----------------------------------------------------------------------
    static class PaddedNumber extends NumberFormatter {

        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType fieldType, int maxParsedDigits,
                     boolean signed, int minPrintedDigits)
        {
            super(fieldType, maxParsedDigits, signed);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[164]++;
            iMinPrintedDigits = minPrintedDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[165]++;
        }

        public int estimatePrintedLength() {
            return iMaxParsedDigits;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[166]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
            try {
CodeCoverTryBranchHelper_Try6 = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[167]++;
                DateTimeField field = iFieldType.getField(chrono);
                FormatUtils.appendPaddedInteger(buf, field.get(instant), iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[168]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[125]++;
                appendUnknownString(buf, iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[169]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[124]++;
}
  }
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[170]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
            try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[171]++;
                DateTimeField field = iFieldType.getField(chrono);
                FormatUtils.writePaddedInteger(out, field.get(instant), iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[172]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[127]++;
                printUnknownString(out, iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[173]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[126]++;
}
  }
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[174]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((partial.isSupported(iFieldType)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[128]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[175]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
                try {
CodeCoverTryBranchHelper_Try8 = true;
                    FormatUtils.appendPaddedInteger(buf, partial.get(iFieldType), iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[176]++;
                } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[131]++;
                    appendUnknownString(buf, iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[177]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[130]++;
}
  }

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[129]++;
                appendUnknownString(buf, iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[178]++;
            }
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[179]++;
int CodeCoverConditionCoverageHelper_C62;
            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((partial.isSupported(iFieldType)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[132]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[180]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
                try {
CodeCoverTryBranchHelper_Try9 = true;
                    FormatUtils.writePaddedInteger(out, partial.get(iFieldType), iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[181]++;
                } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[135]++;
                    printUnknownString(out, iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[182]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[134]++;
}
  }

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[133]++;
                printUnknownString(out, iMinPrintedDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[183]++;
            }
        }
    }

    //-----------------------------------------------------------------------
    static class FixedNumber extends PaddedNumber {

        protected FixedNumber(DateTimeFieldType fieldType, int numDigits, boolean signed) {
            super(fieldType, numDigits, signed, numDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[184]++;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[185]++;
            int newPos = super.parseInto(bucket, text, position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[186]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((newPos < 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[136]++;
                return newPos;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[137]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[187]++;
            int expectedPos = position + iMaxParsedDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[188]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((newPos != expectedPos) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[138]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[189]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((iSigned) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[140]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[190]++;
                    char c = text.charAt(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[191]++;
int CodeCoverConditionCoverageHelper_C66;
                    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[142]++;
                        expectedPos++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[192]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[143]++;}

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[141]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[193]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((newPos > expectedPos) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[144]++;
                    // The failure is at the position of the first extra digit.
                    return ~(expectedPos + 1);

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[145]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[194]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((newPos < expectedPos) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[146]++;
                    // The failure is at the position where the next digit should be.
                    return ~newPos;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[147]++;}
}

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[139]++;}
            return newPos;
        }
    }

    //-----------------------------------------------------------------------
    static class TwoDigitYear
            implements DateTimePrinter, DateTimeParser {

        /** The field to print/parse. */
        private final DateTimeFieldType iType;
        /** The pivot year. */
        private final int iPivot;
        private final boolean iLenientParse;

        TwoDigitYear(DateTimeFieldType type, int pivot, boolean lenientParse) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[195]++;
            iType = type;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[196]++;
            iPivot = pivot;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[197]++;
            iLenientParse = lenientParse;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[198]++;
        }

        public int estimateParsedLength() {
            return iLenientParse ? 4 : 2;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[199]++;
            int limit = text.length() - position;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[200]++;
int CodeCoverConditionCoverageHelper_C69;

            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((iLenientParse) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[148]++;
                limit = Math.min(2, limit);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[201]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[202]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((limit < 2) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[150]++;
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[151]++;}

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[149]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[203]++;
                boolean hasSignChar = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[204]++;
                boolean negative = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[205]++;
                int length = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[206]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[16]++;


int CodeCoverConditionCoverageHelper_C71;
                while ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((length < limit) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[16]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[17]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[18]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[207]++;
                    char c = text.charAt(position + length);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[208]++;
int CodeCoverConditionCoverageHelper_C72;
                    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (32)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 3) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 3) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[152]++;
                        hasSignChar = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[209]++;
                        negative = c == '-';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[210]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[211]++;
int CodeCoverConditionCoverageHelper_C73;
                        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[154]++;
                            length++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[212]++;

                        } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[155]++;
                            // Skip the '+' for parseInt to succeed.
                            position++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[213]++;
                            limit--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[214]++;
                        }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[215]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[153]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[216]++;
int CodeCoverConditionCoverageHelper_C74;
                    if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[156]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[217]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[157]++;}
                    length++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[218]++;
                }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[219]++;
int CodeCoverConditionCoverageHelper_C75;
                
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[158]++;
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[159]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[220]++;
int CodeCoverConditionCoverageHelper_C76;

                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((hasSignChar) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((length != 2) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[160]++;
                    int value;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[221]++;
int CodeCoverConditionCoverageHelper_C77;
                    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((length >= 9) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[162]++;
                        // Since value may exceed integer limits, use stock
                        // parser which checks for this.
                        value = Integer.parseInt(text.substring(position, position += length));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[222]++;

                    } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[163]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[223]++;
                        int i = position;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[224]++;
int CodeCoverConditionCoverageHelper_C78;
                        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[164]++;
                            i++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[225]++;

                        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[165]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[226]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
                        try {
CodeCoverTryBranchHelper_Try10 = true;
                            value = text.charAt(i++) - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[227]++;
                        } catch (StringIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try10 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[167]++;
                            return ~position;
                        } finally {
    if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[166]++;
}
  }
                        position += length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[228]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[229]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[19]++;


int CodeCoverConditionCoverageHelper_C79;
                        while ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((i < position) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[19]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[20]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[21]++;
}
                            value = ((value << 3) + (value << 1)) + text.charAt(i++) - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[230]++;
                        }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[231]++;
int CodeCoverConditionCoverageHelper_C80;
                        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[168]++;
                            value = -value;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[232]++;

                        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[169]++;}
                    }
                    
                    bucket.saveField(iType, value);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[233]++;
                    return position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[161]++;}
            }

            int year;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[234]++;
            char c = text.charAt(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[235]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[170]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[171]++;}
            year = c - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[236]++;
            c = text.charAt(position + 1);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[237]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[238]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[172]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[173]++;}
            year = ((year << 3) + (year << 1)) + c - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[239]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[240]++;

            int pivot = iPivot;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[241]++;
int CodeCoverConditionCoverageHelper_C83;
            // If the bucket pivot year is non-null, use that when parsing
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((bucket.getPivotYear() != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[174]++;
                pivot = bucket.getPivotYear().intValue();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[242]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[175]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[243]++;

            int low = pivot - 50;

            int t;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[244]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((low >= 0) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[176]++;
                t = low % 100;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[245]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[177]++;
                t = 99 + ((low + 1) % 100);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[246]++;
            }

            year += low + ((year < t) ? 100 : 0) - t;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[247]++;

            bucket.saveField(iType, year);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[248]++;
            return position + 2;
        }
        
        public int estimatePrintedLength() {
            return 2;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[249]++;
            int year = getTwoDigitYear(instant, chrono);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[250]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[178]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[251]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[252]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[179]++;
                FormatUtils.appendPaddedInteger(buf, year, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[253]++;
            }
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[254]++;
            int year = getTwoDigitYear(instant, chrono);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[255]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[180]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[256]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[257]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[181]++;
                FormatUtils.writePaddedInteger(out, year, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[258]++;
            }
        }

        private int getTwoDigitYear(long instant, Chronology chrono) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[259]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
            try {
CodeCoverTryBranchHelper_Try11 = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[260]++;
                int year = iType.getField(chrono).get(instant);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[261]++;
int CodeCoverConditionCoverageHelper_C87;
                if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[183]++;
                    year = -year;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[262]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[184]++;}
                return year % 100;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try11 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[185]++;
                return -1;
            } finally {
    if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[182]++;
}
  }
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[263]++;
            int year = getTwoDigitYear(partial);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[264]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[186]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[265]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[266]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[187]++;
                FormatUtils.appendPaddedInteger(buf, year, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[267]++;
            }
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[268]++;
            int year = getTwoDigitYear(partial);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[269]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[188]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[270]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[271]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[189]++;
                FormatUtils.writePaddedInteger(out, year, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[272]++;
            }
        }

        private int getTwoDigitYear(ReadablePartial partial) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[273]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((partial.isSupported(iType)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[190]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[274]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
                try {
CodeCoverTryBranchHelper_Try12 = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[275]++;
                    int year = partial.get(iType);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[276]++;
int CodeCoverConditionCoverageHelper_C91;
                    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[193]++;
                        year = -year;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[277]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[194]++;}
                    return year % 100;
                } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try12 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[195]++;} finally {
    if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[192]++;
}
  }

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[191]++;} 
            return -1;
        }
    }

    //-----------------------------------------------------------------------
    static class TextField
            implements DateTimePrinter, DateTimeParser {

        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache =
                    new HashMap<Locale, Map<DateTimeFieldType, Object[]>>();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType fieldType, boolean isShort) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[278]++;
            iFieldType = fieldType;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[279]++;
            iShort = isShort;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[280]++;
        }

        public int estimatePrintedLength() {
            return iShort ? 6 : 20;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[281]++;
boolean CodeCoverTryBranchHelper_Try13 = false;
            try {
CodeCoverTryBranchHelper_Try13 = true;
                buf.append(print(instant, chrono, locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[282]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try13 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[197]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[283]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try13 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[196]++;
}
  }
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[284]++;
boolean CodeCoverTryBranchHelper_Try14 = false;
            try {
CodeCoverTryBranchHelper_Try14 = true;
                out.write(print(instant, chrono, locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[285]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try14 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[199]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[286]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try14 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[198]++;
}
  }
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[287]++;
boolean CodeCoverTryBranchHelper_Try15 = false;
            try {
CodeCoverTryBranchHelper_Try15 = true;
                buf.append(print(partial, locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[288]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try15 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[201]++;
                buf.append('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[289]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try15 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[200]++;
}
  }
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[290]++;
boolean CodeCoverTryBranchHelper_Try16 = false;
            try {
CodeCoverTryBranchHelper_Try16 = true;
                out.write(print(partial, locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[291]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try16 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[203]++;
                out.write('\ufffd');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[292]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try16 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[202]++;
}
  }
        }

        private String print(long instant, Chronology chrono, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[293]++;
            DateTimeField field = iFieldType.getField(chrono);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[294]++;
int CodeCoverConditionCoverageHelper_C92;
            if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((iShort) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[204]++;
                return field.getAsShortText(instant, locale);

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[205]++;
                return field.getAsText(instant, locale);
            }
        }

        private String print(ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[295]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((partial.isSupported(iFieldType)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[206]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[296]++;
                DateTimeField field = iFieldType.getField(partial.getChronology());
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[297]++;
int CodeCoverConditionCoverageHelper_C94;
                if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((iShort) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[208]++;
                    return field.getAsShortText(partial, locale);

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[209]++;
                    return field.getAsText(partial, locale);
                }

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[207]++;
                return "\ufffd";
            }
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @SuppressWarnings("unchecked")
        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[298]++;
            Locale locale = bucket.getLocale();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[299]++;
            // handle languages which might have non ASCII A-Z or punctuation
            // bug 1788282
            Set<String> validValues = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[300]++;
            int maxLength = 0;
            synchronized (cParseCache) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[301]++;
                Map<DateTimeFieldType, Object[]> innerMap = cParseCache.get(locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[302]++;
int CodeCoverConditionCoverageHelper_C95;
                if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((innerMap == null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[210]++;
                    innerMap = new HashMap<DateTimeFieldType, Object[]>();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[303]++;
                    cParseCache.put(locale, innerMap);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[304]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[211]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[305]++;
                Object[] array = innerMap.get(iFieldType);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[306]++;
int CodeCoverConditionCoverageHelper_C96;
                if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[212]++;
                    validValues = new HashSet<String>(32);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[307]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[308]++;
                    MutableDateTime dt = new MutableDateTime(0L, DateTimeZone.UTC);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[309]++;
                    Property property = dt.property(iFieldType);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[310]++;
                    int min = property.getMinimumValueOverall();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[311]++;
                    int max = property.getMaximumValueOverall();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[312]++;
int CodeCoverConditionCoverageHelper_C97;
                    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((max - min > 32) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[214]++;  // protect against invalid fields
                        return ~position;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[215]++;}
                    maxLength = property.getMaximumTextLength(locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[313]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[314]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[22]++;


int CodeCoverConditionCoverageHelper_C98;
                    for (int i = min;(((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((i <= max) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[22]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[23]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[24]++;
}
                        property.set(i);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[315]++;
                        validValues.add(property.getAsShortText(locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[316]++;
                        validValues.add(property.getAsShortText(locale).toLowerCase(locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[317]++;
                        validValues.add(property.getAsShortText(locale).toUpperCase(locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[318]++;
                        validValues.add(property.getAsText(locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[319]++;
                        validValues.add(property.getAsText(locale).toLowerCase(locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[320]++;
                        validValues.add(property.getAsText(locale).toUpperCase(locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[321]++;
                    }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[322]++;
int CodeCoverConditionCoverageHelper_C99;
                    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 (("en".equals(locale.getLanguage())) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((iFieldType == DateTimeFieldType.era()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[216]++;
                        // hack to support for parsing "BCE" and "CE" if the language is English
                        validValues.add("BCE");
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[323]++;
                        validValues.add("bce");
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[324]++;
                        validValues.add("CE");
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[325]++;
                        validValues.add("ce");
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[326]++;
                        maxLength = 3;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[327]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[217]++;}
                    array = new Object[] {validValues, Integer.valueOf(maxLength)};
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[328]++;
                    innerMap.put(iFieldType, array);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[329]++;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[213]++;
                    validValues = (Set<String>) array[0];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[330]++;
                    maxLength = ((Integer) array[1]).intValue();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[331]++;
                }
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[332]++;
            // match the longest string first using our knowledge of the max length
            int limit = Math.min(text.length(), position + maxLength);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[333]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[25]++;


int CodeCoverConditionCoverageHelper_C100;
            for (int i = limit;(((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((i > position) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[25]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[26]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[27]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[334]++;
                String match = text.substring(position, i);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[335]++;
int CodeCoverConditionCoverageHelper_C101;
                if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((validValues.contains(match)) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[218]++;
                    bucket.saveField(iFieldType, match, locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[336]++;
                    return i;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[219]++;}
            }
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    static class Fraction
            implements DateTimePrinter, DateTimeParser {

        private final DateTimeFieldType iFieldType;
        protected int iMinDigits;
        protected int iMaxDigits;

        protected Fraction(DateTimeFieldType fieldType, int minDigits, int maxDigits) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[337]++;
            iFieldType = fieldType;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[338]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[339]++;
int CodeCoverConditionCoverageHelper_C102;
            // Limit the precision requirements.
            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((maxDigits > 18) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[220]++;
                maxDigits = 18;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[340]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[221]++;}
            iMinDigits = minDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[341]++;
            iMaxDigits = maxDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[342]++;
        }

        public int estimatePrintedLength() {
            return iMaxDigits;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[343]++;
boolean CodeCoverTryBranchHelper_Try17 = false;
            try {
CodeCoverTryBranchHelper_Try17 = true;
                printTo(buf, null, instant, chrono);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[344]++;
            } catch (IOException e) {
CodeCoverTryBranchHelper_Try17 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[223]++;
                // Not gonna happen.
            } finally {
    if ( CodeCoverTryBranchHelper_Try17 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[222]++;
}
  }
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
            printTo(null, out, instant, chrono);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[345]++;
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[346]++;
            // removed check whether field is supported, as input field is typically
            // secondOfDay which is unsupported by TimeOfDay
            long millis = partial.getChronology().set(partial, 0L);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[347]++;
boolean CodeCoverTryBranchHelper_Try18 = false;
            try {
CodeCoverTryBranchHelper_Try18 = true;
                printTo(buf, null, millis, partial.getChronology());
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[348]++;
            } catch (IOException e) {
CodeCoverTryBranchHelper_Try18 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[225]++;
                // Not gonna happen.
            } finally {
    if ( CodeCoverTryBranchHelper_Try18 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[224]++;
}
  }
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[349]++;
            // removed check whether field is supported, as input field is typically
            // secondOfDay which is unsupported by TimeOfDay
            long millis = partial.getChronology().set(partial, 0L);
            printTo(null, out, millis, partial.getChronology());
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[350]++;
        }

        protected void printTo(StringBuffer buf, Writer out, long instant, Chronology chrono)
            throws IOException
        {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[351]++;
            DateTimeField field = iFieldType.getField(chrono);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[352]++;
            int minDigits = iMinDigits;

            long fraction;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[353]++;
boolean CodeCoverTryBranchHelper_Try19 = false;
            try {
CodeCoverTryBranchHelper_Try19 = true;
                fraction = field.remainder(instant);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[354]++;
            } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try19 = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[227]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[355]++;
int CodeCoverConditionCoverageHelper_C103;
                if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((buf != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[228]++;
                    appendUnknownString(buf, minDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[356]++;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[229]++;
                    printUnknownString(out, minDigits);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[357]++;
                }
                return;
            } finally {
    if ( CodeCoverTryBranchHelper_Try19 ) {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[226]++;
}
  }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[358]++;
int CodeCoverConditionCoverageHelper_C104;

            if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((fraction == 0) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[230]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[359]++;
int CodeCoverConditionCoverageHelper_C105;
                if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((buf != null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[232]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[360]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[28]++;


int CodeCoverConditionCoverageHelper_C106;
                    while ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((--minDigits >= 0) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[28]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[29]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[30]++;
}
                        buf.append('0');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[361]++;
                    }

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[233]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[362]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[31]++;


int CodeCoverConditionCoverageHelper_C107;
                    while ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((--minDigits >= 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[31]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[32]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[33]++;
}
                        out.write('0');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[363]++;
                    }
                }
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[231]++;}

            String str;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[364]++;
            long[] fractionData = getFractionData(fraction, field);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[365]++;
            long scaled = fractionData[0];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[366]++;
            int maxDigits = (int) fractionData[1];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[367]++;
int CodeCoverConditionCoverageHelper_C108;
            
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 (((scaled & 0x7fffffff) == scaled) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[234]++;
                str = Integer.toString((int) scaled);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[368]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[235]++;
                str = Long.toString(scaled);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[369]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[370]++;

            int length = str.length();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[371]++;
            int digits = maxDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[372]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[34]++;


int CodeCoverConditionCoverageHelper_C109;
            while ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((length < digits) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[34]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[35]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[36]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[373]++;
int CodeCoverConditionCoverageHelper_C110;
                if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((buf != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[236]++;
                    buf.append('0');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[374]++;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[237]++;
                    out.write('0');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[375]++;
                }
                minDigits--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[376]++;
                digits--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[377]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[378]++;
int CodeCoverConditionCoverageHelper_C111;

            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((minDigits < digits) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[238]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[379]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[37]++;


int CodeCoverConditionCoverageHelper_C112;
                // Chop off as many trailing zero digits as necessary.
                while ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((minDigits < digits) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[37]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[38]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[39]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[380]++;
int CodeCoverConditionCoverageHelper_C113;
                    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (8)) == 0 || true) &&
 ((length <= 1) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((str.charAt(length - 1) != '0') && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[240]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[381]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[241]++;}
                    digits--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[382]++;
                    length--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[383]++;
                }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[384]++;
int CodeCoverConditionCoverageHelper_C114;
                if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((length < str.length()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[242]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[385]++;
int CodeCoverConditionCoverageHelper_C115;
                    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((buf != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[244]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[386]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[40]++;


int CodeCoverConditionCoverageHelper_C116;
                        for (int i=0;(((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[40]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[41]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[42]++;
}
                            buf.append(str.charAt(i));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[387]++;
                        }

                    } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[245]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[388]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[43]++;


int CodeCoverConditionCoverageHelper_C117;
                        for (int i=0;(((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[43]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[44]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[45]++;
}
                            out.write(str.charAt(i));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[389]++;
                        }
                    }
                    return;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[243]++;}

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[239]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[390]++;
int CodeCoverConditionCoverageHelper_C118;

            if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((buf != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[246]++;
                buf.append(str);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[391]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[247]++;
                out.write(str);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[392]++;
            }
        }
        
        private long[] getFractionData(long fraction, DateTimeField field) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[393]++;
            long rangeMillis = field.getDurationField().getUnitMillis();
            long scalar;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[394]++;
            int maxDigits = iMaxDigits;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[395]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[46]++;


            while (true) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[46]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[47]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[48]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[396]++;
                switch (maxDigits) {
                default:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[248]++; scalar = 1L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[397]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[398]++; break;
                case 1:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[249]++;  scalar = 10L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[399]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[400]++; break;
                case 2:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[250]++;  scalar = 100L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[401]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[402]++; break;
                case 3:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[251]++;  scalar = 1000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[403]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[404]++; break;
                case 4:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[252]++;  scalar = 10000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[405]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[406]++; break;
                case 5:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[253]++;  scalar = 100000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[407]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[408]++; break;
                case 6:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[254]++;  scalar = 1000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[409]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[410]++; break;
                case 7:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[255]++;  scalar = 10000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[411]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[412]++; break;
                case 8:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[256]++;  scalar = 100000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[413]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[414]++; break;
                case 9:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[257]++;  scalar = 1000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[415]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[416]++; break;
                case 10:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[258]++; scalar = 10000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[417]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[418]++; break;
                case 11:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[259]++; scalar = 100000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[419]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[420]++; break;
                case 12:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[260]++; scalar = 1000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[421]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[422]++; break;
                case 13:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[261]++; scalar = 10000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[423]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[424]++; break;
                case 14:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[262]++; scalar = 100000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[425]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[426]++; break;
                case 15:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[263]++; scalar = 1000000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[427]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[428]++; break;
                case 16:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[264]++; scalar = 10000000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[429]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[430]++; break;
                case 17:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[265]++; scalar = 100000000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[431]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[432]++; break;
                case 18:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[266]++; scalar = 1000000000000000000L;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[433]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[434]++; break;
                }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[435]++;
int CodeCoverConditionCoverageHelper_C120;
                if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((((rangeMillis * scalar) / scalar) == rangeMillis) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[267]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[436]++;
                    break;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[268]++;}
                // Overflowed: scale down.
                maxDigits--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[437]++;
            }
            
            return new long[] {fraction * scalar / rangeMillis, maxDigits};
        }

        public int estimateParsedLength() {
            return iMaxDigits;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[438]++;
            DateTimeField field = iFieldType.getField(bucket.getChronology());
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[439]++;
            
            int limit = Math.min(iMaxDigits, text.length() - position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[440]++;

            long value = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[441]++;
            long n = field.getDurationField().getUnitMillis() * 10;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[442]++;
            int length = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[443]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[49]++;


int CodeCoverConditionCoverageHelper_C121;
            while ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((length < limit) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[49]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[50]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[51]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[444]++;
                char c = text.charAt(position + length);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[445]++;
int CodeCoverConditionCoverageHelper_C122;
                if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C122 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[269]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[446]++;
                    break;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[270]++;}
                length++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[447]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[448]++;
                long nn = n / 10;
                value += (c - '0') * nn;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[449]++;
                n = nn;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[450]++;
            }

            value /= 10;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[451]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[452]++;
int CodeCoverConditionCoverageHelper_C123;

            if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[271]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[272]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[453]++;
int CodeCoverConditionCoverageHelper_C124;

            if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((value > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[273]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[274]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[454]++;

            DateTimeField parseField = new PreciseDateTimeField(
                DateTimeFieldType.millisOfSecond(),
                MillisDurationField.INSTANCE,
                field.getDurationField());

            bucket.saveField(parseField, (int) value);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[455]++;

            return position + length;
        }
    }

    //-----------------------------------------------------------------------
    static class TimeZoneOffset
            implements DateTimePrinter, DateTimeParser {

        private final String iZeroOffsetPrintText;
        private final String iZeroOffsetParseText;
        private final boolean iShowSeparators;
        private final int iMinFields;
        private final int iMaxFields;

        TimeZoneOffset(String zeroOffsetPrintText, String zeroOffsetParseText,
                                boolean showSeparators,
                                int minFields, int maxFields)
        {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[456]++;
            iZeroOffsetPrintText = zeroOffsetPrintText;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[457]++;
            iZeroOffsetParseText = zeroOffsetParseText;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[458]++;
            iShowSeparators = showSeparators;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[459]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[460]++;
int CodeCoverConditionCoverageHelper_C125;
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((minFields <= 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((maxFields < minFields) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[275]++;
                throw new IllegalArgumentException();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[276]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[461]++;
int CodeCoverConditionCoverageHelper_C126;
            if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((minFields > 4) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[277]++;
                minFields = 4;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[462]++;
                maxFields = 4;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[463]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[278]++;}
            iMinFields = minFields;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[464]++;
            iMaxFields = maxFields;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[465]++;
        }
            
        public int estimatePrintedLength() {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[466]++;
            int est = 1 + iMinFields << 1;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[467]++;
int CodeCoverConditionCoverageHelper_C127;
            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[279]++;
                est += iMinFields - 1;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[468]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[280]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[469]++;
int CodeCoverConditionCoverageHelper_C128;
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((iZeroOffsetPrintText != null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((iZeroOffsetPrintText.length() > est) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[281]++;
                est = iZeroOffsetPrintText.length();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[470]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[282]++;}
            return est;
        }
        
        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[471]++;
int CodeCoverConditionCoverageHelper_C129;
            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((displayZone == null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[283]++;
                return;
  // no zone
            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[284]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[472]++;
int CodeCoverConditionCoverageHelper_C130;
            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((iZeroOffsetPrintText != null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[285]++;
                buf.append(iZeroOffsetPrintText);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[473]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[286]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[474]++;
int CodeCoverConditionCoverageHelper_C131;
            if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((displayOffset >= 0) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[287]++;
                buf.append('+');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[475]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[288]++;
                buf.append('-');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[476]++;
                displayOffset = -displayOffset;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[477]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[478]++;

            int hours = displayOffset / DateTimeConstants.MILLIS_PER_HOUR;
            FormatUtils.appendPaddedInteger(buf, hours, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[479]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[480]++;
int CodeCoverConditionCoverageHelper_C132;
            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((iMaxFields == 1) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[289]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[290]++;}
            displayOffset -= hours * (int)DateTimeConstants.MILLIS_PER_HOUR;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[481]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[482]++;
int CodeCoverConditionCoverageHelper_C133;
            if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((iMinFields <= 1) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[291]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[292]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[483]++;

            int minutes = displayOffset / DateTimeConstants.MILLIS_PER_MINUTE;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[484]++;
int CodeCoverConditionCoverageHelper_C134;
            if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[293]++;
                buf.append(':');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[485]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[294]++;}
            FormatUtils.appendPaddedInteger(buf, minutes, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[486]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[487]++;
int CodeCoverConditionCoverageHelper_C135;
            if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((iMaxFields == 2) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[295]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[296]++;}
            displayOffset -= minutes * DateTimeConstants.MILLIS_PER_MINUTE;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[488]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[489]++;
int CodeCoverConditionCoverageHelper_C136;
            if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((iMinFields <= 2) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[297]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[298]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[490]++;

            int seconds = displayOffset / DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[491]++;
int CodeCoverConditionCoverageHelper_C137;
            if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[299]++;
                buf.append(':');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[492]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[300]++;}
            FormatUtils.appendPaddedInteger(buf, seconds, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[493]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[494]++;
int CodeCoverConditionCoverageHelper_C138;
            if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((iMaxFields == 3) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[301]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[302]++;}
            displayOffset -= seconds * DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[495]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[496]++;
int CodeCoverConditionCoverageHelper_C139;
            if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((iMinFields <= 3) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[303]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[304]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[497]++;
int CodeCoverConditionCoverageHelper_C140;

            if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[305]++;
                buf.append('.');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[498]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[306]++;}
            FormatUtils.appendPaddedInteger(buf, displayOffset, 3);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[499]++;
        }
        
        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[500]++;
int CodeCoverConditionCoverageHelper_C141;
            if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((displayZone == null) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[307]++;
                return;
  // no zone
            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[308]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[501]++;
int CodeCoverConditionCoverageHelper_C142;
            if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((iZeroOffsetPrintText != null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[309]++;
                out.write(iZeroOffsetPrintText);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[502]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[310]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[503]++;
int CodeCoverConditionCoverageHelper_C143;
            if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((displayOffset >= 0) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[311]++;
                out.write('+');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[504]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[312]++;
                out.write('-');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[505]++;
                displayOffset = -displayOffset;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[506]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[507]++;

            int hours = displayOffset / DateTimeConstants.MILLIS_PER_HOUR;
            FormatUtils.writePaddedInteger(out, hours, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[508]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[509]++;
int CodeCoverConditionCoverageHelper_C144;
            if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((iMaxFields == 1) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[313]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[314]++;}
            displayOffset -= hours * (int)DateTimeConstants.MILLIS_PER_HOUR;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[510]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[511]++;
int CodeCoverConditionCoverageHelper_C145;
            if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((iMinFields == 1) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[315]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[316]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[512]++;

            int minutes = displayOffset / DateTimeConstants.MILLIS_PER_MINUTE;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[513]++;
int CodeCoverConditionCoverageHelper_C146;
            if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[317]++;
                out.write(':');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[514]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[318]++;}
            FormatUtils.writePaddedInteger(out, minutes, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[515]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[516]++;
int CodeCoverConditionCoverageHelper_C147;
            if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((iMaxFields == 2) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[319]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[320]++;}
            displayOffset -= minutes * DateTimeConstants.MILLIS_PER_MINUTE;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[517]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[518]++;
int CodeCoverConditionCoverageHelper_C148;
            if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((iMinFields == 2) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[321]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[322]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[519]++;

            int seconds = displayOffset / DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[520]++;
int CodeCoverConditionCoverageHelper_C149;
            if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[323]++;
                out.write(':');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[521]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[324]++;}
            FormatUtils.writePaddedInteger(out, seconds, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[522]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[523]++;
int CodeCoverConditionCoverageHelper_C150;
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((iMaxFields == 3) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[325]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[326]++;}
            displayOffset -= seconds * DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[524]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[525]++;
int CodeCoverConditionCoverageHelper_C151;
            if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (8)) == 0 || true) &&
 ((displayOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((iMinFields == 3) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[327]++;
                return;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[328]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[526]++;
int CodeCoverConditionCoverageHelper_C152;

            if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((iShowSeparators) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[329]++;
                out.write('.');
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[527]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[330]++;}
            FormatUtils.writePaddedInteger(out, displayOffset, 3);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[528]++;
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
            // no zone info
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
            // no zone info
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[529]++;
            int limit = text.length() - position;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[530]++;
int CodeCoverConditionCoverageHelper_C153;

            zeroOffset:
            if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((iZeroOffsetParseText != null) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[331]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[531]++;
int CodeCoverConditionCoverageHelper_C154;
                if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((iZeroOffsetParseText.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[333]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[532]++;
int CodeCoverConditionCoverageHelper_C155;
                    // Peek ahead, looking for sign character.
                    if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((limit > 0) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[335]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[533]++;
                        char c = text.charAt(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[534]++;
int CodeCoverConditionCoverageHelper_C156;
                        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (8)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C156 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[337]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[535]++;
                            break zeroOffset;

                        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[338]++;}

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[336]++;}
                    bucket.setOffset(Integer.valueOf(0));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[536]++;
                    return position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[334]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[537]++;
int CodeCoverConditionCoverageHelper_C157;
                if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((text.regionMatches(true, position, iZeroOffsetParseText, 0, iZeroOffsetParseText.length())) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[339]++;
                    bucket.setOffset(Integer.valueOf(0));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[538]++;
                    return position + iZeroOffsetParseText.length();

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[340]++;}

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[332]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[539]++;
int CodeCoverConditionCoverageHelper_C158;

            // Format to expect is sign character followed by at least one digit.

            if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((limit <= 1) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[341]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[342]++;}

            boolean negative;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[540]++;
            char c = text.charAt(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[541]++;
int CodeCoverConditionCoverageHelper_C159;
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[343]++;
                negative = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[542]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[344]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[543]++;
int CodeCoverConditionCoverageHelper_C160; if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[345]++;
                negative = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[544]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[346]++;
                return ~position;
            }
}

            limit--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[545]++;
            position++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[546]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[547]++;
int CodeCoverConditionCoverageHelper_C161;

            // Format following sign is one of:
            //
            // hh
            // hhmm
            // hhmmss
            // hhmmssSSS
            // hh:mm
            // hh:mm:ss
            // hh:mm:ss.SSS

            // First parse hours.

            if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((digitCount(text, position, 2) < 2) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[347]++;
                // Need two digits for hour.
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[348]++;}

            int offset;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[548]++;

            int hours = FormatUtils.parseTwoDigits(text, position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[549]++;
int CodeCoverConditionCoverageHelper_C162;
            if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((hours > 23) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[349]++;
                return ~position;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[350]++;}
            offset = hours * DateTimeConstants.MILLIS_PER_HOUR;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[550]++;
            limit -= 2;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[551]++;
            position += 2;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[552]++;

            parse: {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[553]++;
int CodeCoverConditionCoverageHelper_C163;
                // Need to decide now if separators are expected or parsing
                // stops at hour field.

                if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((limit <= 0) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[351]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[554]++;
                    break parse;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[352]++;}

                boolean expectSeparators;
                c = text.charAt(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[555]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[556]++;
int CodeCoverConditionCoverageHelper_C164;
                if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((c == ':') && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[353]++;
                    expectSeparators = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[557]++;
                    limit--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[558]++;
                    position++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[559]++;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[354]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[560]++;
int CodeCoverConditionCoverageHelper_C165; if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C165 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[355]++;
                    expectSeparators = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[561]++;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[356]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[562]++;
                    break parse;
                }
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[563]++;

                // Proceed to parse minutes.

                int count = digitCount(text, position, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[564]++;
int CodeCoverConditionCoverageHelper_C166;
                if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (8)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((expectSeparators) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[357]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[565]++;
                    break parse;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[358]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[566]++;
int CodeCoverConditionCoverageHelper_C167; if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((count < 2) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[359]++;
                    // Need two digits for minute.
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[360]++;}
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[567]++;

                int minutes = FormatUtils.parseTwoDigits(text, position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[568]++;
int CodeCoverConditionCoverageHelper_C168;
                if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((minutes > 59) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[361]++;
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[362]++;}
                offset += minutes * DateTimeConstants.MILLIS_PER_MINUTE;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[569]++;
                limit -= 2;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[570]++;
                position += 2;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[571]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[572]++;
int CodeCoverConditionCoverageHelper_C169;

                // Proceed to parse seconds.

                if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((limit <= 0) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[363]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[573]++;
                    break parse;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[364]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[574]++;
int CodeCoverConditionCoverageHelper_C170;

                if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((expectSeparators) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[365]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[575]++;
int CodeCoverConditionCoverageHelper_C171;
                    if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((text.charAt(position) != ':') && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[367]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[576]++;
                        break parse;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[368]++;}
                    limit--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[577]++;
                    position++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[578]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[366]++;}

                count = digitCount(text, position, 2);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[579]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[580]++;
int CodeCoverConditionCoverageHelper_C172;
                if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (8)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((expectSeparators) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[369]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[581]++;
                    break parse;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[370]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[582]++;
int CodeCoverConditionCoverageHelper_C173; if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((count < 2) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[371]++;
                    // Need two digits for second.
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[372]++;}
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[583]++;

                int seconds = FormatUtils.parseTwoDigits(text, position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[584]++;
int CodeCoverConditionCoverageHelper_C174;
                if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((seconds > 59) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[373]++;
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[374]++;}
                offset += seconds * DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[585]++;
                limit -= 2;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[586]++;
                position += 2;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[587]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[588]++;
int CodeCoverConditionCoverageHelper_C175;

                // Proceed to parse fraction of second.

                if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((limit <= 0) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[375]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[589]++;
                    break parse;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[376]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[590]++;
int CodeCoverConditionCoverageHelper_C176;

                if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((expectSeparators) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[377]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[591]++;
int CodeCoverConditionCoverageHelper_C177;
                    if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (8)) == 0 || true) &&
 ((text.charAt(position) != '.') && 
  ((CodeCoverConditionCoverageHelper_C177 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((text.charAt(position) != ',') && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[379]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[592]++;
                        break parse;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[380]++;}
                    limit--;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[593]++;
                    position++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[594]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[378]++;}
                
                count = digitCount(text, position, 3);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[595]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[596]++;
int CodeCoverConditionCoverageHelper_C178;
                if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (8)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((expectSeparators) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[381]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[597]++;
                    break parse;

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[382]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[598]++;
int CodeCoverConditionCoverageHelper_C179; if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((count < 1) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[383]++;
                    // Need at least one digit for fraction of second.
                    return ~position;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[384]++;}
}

                offset += (text.charAt(position++) - '0') * 100;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[599]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[600]++;
int CodeCoverConditionCoverageHelper_C180;
                if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[385]++;
                    offset += (text.charAt(position++) - '0') * 10;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[601]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[602]++;
int CodeCoverConditionCoverageHelper_C181;
                    if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((count > 2) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[387]++;
                        offset += text.charAt(position++) - '0';
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[603]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[388]++;}

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[386]++;}
            }

            bucket.setOffset(Integer.valueOf(negative ? -offset : offset));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[604]++;
            return position;
        }

        /**
         * Returns actual amount of digits to parse, but no more than original
         * 'amount' parameter.
         */
        private int digitCount(String text, int position, int amount) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[605]++;
            int limit = Math.min(text.length() - position, amount);
            amount = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[606]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[607]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[52]++;


int CodeCoverConditionCoverageHelper_C182;
            for (;(((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((limit > 0) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false); limit--) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[52]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[53]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[54]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[608]++;
                char c = text.charAt(position + amount);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[609]++;
int CodeCoverConditionCoverageHelper_C183;
                if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C183 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 2) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[389]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[610]++;
                    break;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[390]++;}
                amount++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[611]++;
            }
            return amount;
        }
    }

    //-----------------------------------------------------------------------
    static class TimeZoneName
            implements DateTimePrinter, DateTimeParser {

        static final int LONG_NAME = 0;
        static final int SHORT_NAME = 1;

        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        TimeZoneName(int type, Map<String, DateTimeZone> parseLookup) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[612]++;
            iType = type;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[613]++;
            iParseLookup = parseLookup;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[614]++;
        }

        public int estimatePrintedLength() {
            return (iType == SHORT_NAME ? 4 : 20);
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
            buf.append(print(instant - displayOffset, displayZone, locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[615]++;
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
            out.write(print(instant - displayOffset, displayZone, locale));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[616]++;
        }

        private String print(long instant, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[617]++;
int CodeCoverConditionCoverageHelper_C184;
            if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((displayZone == null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[391]++;
                return "";
  // no zone
            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[392]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[618]++;
            switch (iType) {
                case LONG_NAME:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[393]++;
                    return displayZone.getName(instant, locale);
                case SHORT_NAME:
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[394]++;
                    return displayZone.getShortName(instant, locale); default : CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[395]++;
            }
            return "";
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
            // no zone info
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
            // no zone info
        }

        public int estimateParsedLength() {
            return (iType == SHORT_NAME ? 4 : 20);
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[619]++;
            String str = text.substring(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[620]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[55]++;


            for (String name : iParseLookup.keySet()) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[55]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[56]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[57]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[621]++;
int CodeCoverConditionCoverageHelper_C185;
                if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((str.startsWith(name)) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[396]++;
                    bucket.setZone(iParseLookup.get(name));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[622]++;
                    return position + name.length();

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[397]++;}
            }
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    static enum TimeZoneId
            implements DateTimePrinter, DateTimeParser {

        INSTANCE;
        static final Set<String> ALL_IDS = DateTimeZone.getAvailableIDs();
        static final int MAX_LENGTH;
        static {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[623]++;
            int max = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[624]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[58]++;


            for (String id : ALL_IDS) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[58]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[59]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[60]++;
}
                max = Math.max(max, id.length());
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[625]++;
            }
            MAX_LENGTH = max;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[626]++;
        }

        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
            buf.append(displayZone != null ? displayZone.getID() : "");
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[627]++;
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
            out.write(displayZone != null ? displayZone.getID() : "");
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[628]++;
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
            // no zone info
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
            // no zone info
        }

        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[629]++;
            String str = text.substring(position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[630]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[61]++;


            for (String id : ALL_IDS) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[61]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[62]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[63]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[631]++;
int CodeCoverConditionCoverageHelper_C186;
                if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((str.startsWith(id)) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[398]++;
                    bucket.setZone(DateTimeZone.forID(id));
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[632]++;
                    return position + id.length();

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[399]++;}
            }
            return ~position;
        }
    }

    //-----------------------------------------------------------------------
    static class Composite
            implements DateTimePrinter, DateTimeParser {

        private final DateTimePrinter[] iPrinters;
        private final DateTimeParser[] iParsers;

        private final int iPrintedLengthEstimate;
        private final int iParsedLengthEstimate;

        Composite(List<Object> elementPairs) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[633]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[634]++;

            List<Object> printerList = new ArrayList<Object>();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[635]++;
            List<Object> parserList = new ArrayList<Object>();

            decompose(elementPairs, printerList, parserList);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[636]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[637]++;
int CodeCoverConditionCoverageHelper_C187;

            if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((printerList.size() <= 0) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[400]++;
                iPrinters = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[638]++;
                iPrintedLengthEstimate = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[639]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[401]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[640]++;
                int size = printerList.size();
                iPrinters = new DateTimePrinter[size];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[641]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[642]++;
                int printEst = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[643]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[64]++;


int CodeCoverConditionCoverageHelper_C188;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[64]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[65]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[66]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[644]++;
                    DateTimePrinter printer = (DateTimePrinter) printerList.get(i);
                    printEst += printer.estimatePrintedLength();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[645]++;
                    iPrinters[i] = printer;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[646]++;
                }
                iPrintedLengthEstimate = printEst;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[647]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[648]++;
int CodeCoverConditionCoverageHelper_C189;

            if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((parserList.size() <= 0) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[402]++;
                iParsers = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[649]++;
                iParsedLengthEstimate = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[650]++;

            } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[403]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[651]++;
                int size = parserList.size();
                iParsers = new DateTimeParser[size];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[652]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[653]++;
                int parseEst = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[654]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[67]++;


int CodeCoverConditionCoverageHelper_C190;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[67]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[68]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[69]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[655]++;
                    DateTimeParser parser = (DateTimeParser) parserList.get(i);
                    parseEst += parser.estimateParsedLength();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[656]++;
                    iParsers[i] = parser;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[657]++;
                }
                iParsedLengthEstimate = parseEst;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[658]++;
            }
        }

        public int estimatePrintedLength() {
            return iPrintedLengthEstimate;
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[659]++;
            DateTimePrinter[] elements = iPrinters;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[660]++;
int CodeCoverConditionCoverageHelper_C191;
            if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[404]++;
                throw new UnsupportedOperationException();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[405]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[661]++;
int CodeCoverConditionCoverageHelper_C192;

            if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[406]++;
                // Guard against default locale changing concurrently.
                locale = Locale.getDefault();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[662]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[407]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[663]++;

            int len = elements.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[664]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[70]++;


int CodeCoverConditionCoverageHelper_C193;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[70]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[71]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[72]++;
}
                elements[i].printTo(buf, instant, chrono, displayOffset, displayZone, locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[665]++;
            }
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[666]++;
            DateTimePrinter[] elements = iPrinters;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[667]++;
int CodeCoverConditionCoverageHelper_C194;
            if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[408]++;
                throw new UnsupportedOperationException();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[409]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[668]++;
int CodeCoverConditionCoverageHelper_C195;

            if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[410]++;
                // Guard against default locale changing concurrently.
                locale = Locale.getDefault();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[669]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[411]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[670]++;

            int len = elements.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[671]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[73]++;


int CodeCoverConditionCoverageHelper_C196;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[73]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[74]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[75]++;
}
                elements[i].printTo(out, instant, chrono, displayOffset, displayZone, locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[672]++;
            }
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[673]++;
            DateTimePrinter[] elements = iPrinters;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[674]++;
int CodeCoverConditionCoverageHelper_C197;
            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[412]++;
                throw new UnsupportedOperationException();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[413]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[675]++;
int CodeCoverConditionCoverageHelper_C198;

            if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[414]++;
                // Guard against default locale changing concurrently.
                locale = Locale.getDefault();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[676]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[415]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[677]++;

            int len = elements.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[678]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[76]++;


int CodeCoverConditionCoverageHelper_C199;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[76]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[77]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[78]++;
}
                elements[i].printTo(buf, partial, locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[679]++;
            }
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[680]++;
            DateTimePrinter[] elements = iPrinters;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[681]++;
int CodeCoverConditionCoverageHelper_C200;
            if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[416]++;
                throw new UnsupportedOperationException();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[417]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[682]++;
int CodeCoverConditionCoverageHelper_C201;

            if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[418]++;
                // Guard against default locale changing concurrently.
                locale = Locale.getDefault();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[683]++;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[419]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[684]++;

            int len = elements.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[685]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[79]++;


int CodeCoverConditionCoverageHelper_C202;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[79]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[80]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[81]++;
}
                elements[i].printTo(out, partial, locale);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[686]++;
            }
        }

        public int estimateParsedLength() {
            return iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[687]++;
            DateTimeParser[] elements = iParsers;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[688]++;
int CodeCoverConditionCoverageHelper_C203;
            if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[420]++;
                throw new UnsupportedOperationException();

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[421]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[689]++;

            int len = elements.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[690]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[82]++;


int CodeCoverConditionCoverageHelper_C204;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (8)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((position >= 0) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 2) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[82]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[83]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[84]++;
}
                position = elements[i].parseInto(bucket, text, position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[691]++;
            }
            return position;
        }

        boolean isPrinter() {
            return iPrinters != null;
        }

        boolean isParser() {
            return iParsers != null;
        }

        /**
         * Processes the element pairs, putting results into the given printer
         * and parser lists.
         */
        private void decompose(List<Object> elementPairs, List<Object> printerList, List<Object> parserList) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[692]++;
            int size = elementPairs.size();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[693]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[85]++;


int CodeCoverConditionCoverageHelper_C205;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false); i+=2) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[85]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[86]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[87]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[694]++;
                Object element = elementPairs.get(i);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[695]++;
int CodeCoverConditionCoverageHelper_C206;
                if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((element instanceof DateTimePrinter) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[422]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[696]++;
int CodeCoverConditionCoverageHelper_C207;
                    if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((element instanceof Composite) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[424]++;
                        addArrayToList(printerList, ((Composite)element).iPrinters);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[697]++;

                    } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[425]++;
                        printerList.add(element);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[698]++;
                    }

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[423]++;}

                element = elementPairs.get(i + 1);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[699]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[700]++;
int CodeCoverConditionCoverageHelper_C208;
                if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((element instanceof DateTimeParser) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[426]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[701]++;
int CodeCoverConditionCoverageHelper_C209;
                    if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((element instanceof Composite) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[428]++;
                        addArrayToList(parserList, ((Composite)element).iParsers);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[702]++;

                    } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[429]++;
                        parserList.add(element);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[703]++;
                    }

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[427]++;}
            }
        }

        private void addArrayToList(List<Object> list, Object[] array) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[704]++;
int CodeCoverConditionCoverageHelper_C210;
            if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((array != null) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[430]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[705]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[88]++;


int CodeCoverConditionCoverageHelper_C211;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((i<array.length) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[88]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[89]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[90]++;
}
                    list.add(array[i]);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[706]++;
                }

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[431]++;}
        }
    }

    //-----------------------------------------------------------------------
    static class MatchingParser
            implements DateTimeParser {

        private final DateTimeParser[] iParsers;
        private final int iParsedLengthEstimate;

        MatchingParser(DateTimeParser[] parsers) {
            super();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[707]++;
            iParsers = parsers;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[708]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[709]++;
            int est = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[710]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[91]++;


int CodeCoverConditionCoverageHelper_C212;
            for (int i=parsers.length;(((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false) ;) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[91]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[92]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[93]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[711]++;
                DateTimeParser parser = parsers[i];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[712]++;
int CodeCoverConditionCoverageHelper_C213;
                if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((parser != null) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[432]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[713]++;
                    int len = parser.estimateParsedLength();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[714]++;
int CodeCoverConditionCoverageHelper_C214;
                    if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((len > est) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[434]++;
                        est = len;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[715]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[435]++;}

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[433]++;}
            }
            iParsedLengthEstimate = est;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[716]++;
        }

        public int estimateParsedLength() {
            return iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[717]++;
            DateTimeParser[] parsers = iParsers;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[718]++;
            int length = parsers.length;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[719]++;

            final Object originalState = bucket.saveState();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[720]++;
            boolean isOptional = false;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[721]++;

            int bestValidPos = position;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[722]++;
            Object bestValidState = null;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[723]++;

            int bestInvalidPos = position;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[724]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[94]++;


int CodeCoverConditionCoverageHelper_C215;

            for (int i=0;(((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[94]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[95]--;
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.loops[96]++;
}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[725]++;
                DateTimeParser parser = parsers[i];
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[726]++;
int CodeCoverConditionCoverageHelper_C216;
                if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((parser == null) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[436]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[727]++;
int CodeCoverConditionCoverageHelper_C217;
                    // The empty parser wins only if nothing is better.
                    if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((bestValidPos <= position) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[438]++;
                        return position;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[439]++;}
                    isOptional = true;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[728]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[729]++;
                    break;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[437]++;}
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[730]++;
                int parsePos = parser.parseInto(bucket, text, position);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[731]++;
int CodeCoverConditionCoverageHelper_C218;
                if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((parsePos >= position) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[440]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[732]++;
int CodeCoverConditionCoverageHelper_C219;
                    if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((parsePos > bestValidPos) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[442]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[733]++;
int CodeCoverConditionCoverageHelper_C220;
                        if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (32)) == 0 || true) &&
 ((parsePos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C220 |= (8)) == 0 || true) &&
 (((i + 1) >= length) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((parsers[i + 1] == null) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 3) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 3) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[444]++;

                            // Completely parsed text or no more parsers to
                            // check. Skip the rest.
                            return parsePos;

                        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[445]++;}
                        bestValidPos = parsePos;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[734]++;
                        bestValidState = bucket.saveState();
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[735]++;

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[443]++;}

                } else {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[441]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[736]++;
int CodeCoverConditionCoverageHelper_C221;
                    if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((parsePos < 0) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[446]++;
                        parsePos = ~parsePos;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[737]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[738]++;
int CodeCoverConditionCoverageHelper_C222;
                        if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((parsePos > bestInvalidPos) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[448]++;
                            bestInvalidPos = parsePos;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[739]++;

                        } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[449]++;}

                    } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[447]++;}
                }
                bucket.restoreState(originalState);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[740]++;
            }
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[741]++;
int CodeCoverConditionCoverageHelper_C223;

            if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (32)) == 0 || true) &&
 ((bestValidPos > position) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C223 |= (8)) == 0 || true) &&
 ((bestValidPos == position) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((isOptional) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 3) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 3) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[450]++;
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[742]++;
int CodeCoverConditionCoverageHelper_C224;
                // Restore the state to the best valid parse.
                if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((bestValidState != null) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[452]++;
                    bucket.restoreState(bestValidState);
CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.statements[743]++;

                } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[453]++;}
                return bestValidPos;

            } else {
  CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469.branches[451]++;}

            return ~bestInvalidPos;
        }
    }

}

class CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469 ());
  }
    public static long[] statements = new long[744];
    public static long[] branches = new long[454];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[225];
  static {
    final String SECTION_NAME = "org.joda.time.format.DateTimeFormatterBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,0,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,3,1,2,1,2,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,2,1,1,2,1,1,2,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,2,1,1,1,1,2,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,3,1};
    for (int i = 1; i <= 224; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[97];

  public CodeCoverCoverageCounter$654mo6kyai828p9tp73nf924rb8c6mckm6f215emx9469 () {
    super("org.joda.time.format.DateTimeFormatterBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 743; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 453; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 224; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 96; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.DateTimeFormatterBuilder.java");
      for (int i = 1; i <= 743; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 453; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 224; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 32; i++) {
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

