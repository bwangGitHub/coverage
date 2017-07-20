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
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

/**
 * Controls the printing and parsing of a datetime to and from a string.
 * <p>
 * This class is the main API for printing and parsing used by most applications.
 * Instances of this class are created via one of three factory classes:
 * <ul>
 * <li>{@link DateTimeFormat} - formats by pattern and style</li>
 * <li>{@link ISODateTimeFormat} - ISO8601 formats</li>
 * <li>{@link DateTimeFormatterBuilder} - complex formats created via method calls</li>
 * </ul>
 * <p>
 * An instance of this class holds a reference internally to one printer and
 * one parser. It is possible that one of these may be null, in which case the
 * formatter cannot print/parse. This can be checked via the {@link #isPrinter()}
 * and {@link #isParser()} methods.
 * <p>
 * The underlying printer/parser can be altered to behave exactly as required
 * by using one of the decorator modifiers:
 * <ul>
 * <li>{@link #withLocale(Locale)} - returns a new formatter that uses the specified locale</li>
 * <li>{@link #withZone(DateTimeZone)} - returns a new formatter that uses the specified time zone</li>
 * <li>{@link #withChronology(Chronology)} - returns a new formatter that uses the specified chronology</li>
 * <li>{@link #withOffsetParsed()} - returns a new formatter that returns the parsed time zone offset</li>
 * </ul>
 * Each of these returns a new formatter (instances of this class are immutable).
 * <p>
 * The main methods of the class are the <code>printXxx</code> and
 * <code>parseXxx</code> methods. These are used as follows:
 * <pre>
 * // print using the defaults (default locale, chronology/zone of the datetime)
 * String dateStr = formatter.print(dt);
 * // print using the French locale
 * String dateStr = formatter.withLocale(Locale.FRENCH).print(dt);
 * // print using the UTC zone
 * String dateStr = formatter.withZone(DateTimeZone.UTC).print(dt);
 * 
 * // parse using the Paris zone
 * DateTime date = formatter.withZone(DateTimeZone.forID("Europe/Paris")).parseDateTime(str);
 * </pre>
 * 
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @author Fredrik Borgh
 * @since 1.0
 */
public class DateTimeFormatter {
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.ping();
  }


    /** The internal printer used to output the datetime. */
    private final DateTimePrinter iPrinter;
    /** The internal parser used to output the datetime. */
    private final DateTimeParser iParser;
    /** The locale to use for printing and parsing. */
    private final Locale iLocale;
    /** Whether the offset is parsed. */
    private final boolean iOffsetParsed;
    /** The chronology to use as an override. */
    private final Chronology iChrono;
    /** The zone to use as an override. */
    private final DateTimeZone iZone;
    /** The pivot year to use for two-digit year parsing. */
    private final Integer iPivotYear;
    /** The default year for parsing month/day without year. */
    private final int iDefaultYear;

    /**
     * Creates a new formatter, however you will normally use the factory
     * or the builder.
     * 
     * @param printer  the internal printer, null if cannot print
     * @param parser  the internal parser, null if cannot parse
     */
    public DateTimeFormatter(
            DateTimePrinter printer, DateTimeParser parser) {
        super();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[1]++;
        iPrinter = printer;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[2]++;
        iParser = parser;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[3]++;
        iLocale = null;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[4]++;
        iOffsetParsed = false;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[5]++;
        iChrono = null;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[6]++;
        iZone = null;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[7]++;
        iPivotYear = null;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[8]++;
        iDefaultYear = 2000;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[9]++;
    }

    /**
     * Constructor.
     */
    private DateTimeFormatter(
            DateTimePrinter printer, DateTimeParser parser,
            Locale locale, boolean offsetParsed,
            Chronology chrono, DateTimeZone zone,
            Integer pivotYear, int defaultYear) {
        super();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[10]++;
        iPrinter = printer;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[11]++;
        iParser = parser;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[12]++;
        iLocale = locale;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[13]++;
        iOffsetParsed = offsetParsed;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[14]++;
        iChrono = chrono;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[15]++;
        iZone = zone;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[16]++;
        iPivotYear = pivotYear;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[17]++;
        iDefaultYear = defaultYear;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[18]++;
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
     * @return the internal printer; is null if printing not supported
     */
    public DateTimePrinter getPrinter() {
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
     * @return the internal parser; is null if parsing not supported
     */
    public DateTimeParser getParser() {
        return iParser;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter with a different locale that will be used
     * for printing and parsing.
     * <p>
     * A DateTimeFormatter is immutable, so a new instance is returned,
     * and the original is unaltered and still usable.
     * 
     * @param locale the locale to use; if null, formatter uses default locale
     * at invocation time
     * @return the new formatter
     */
    public DateTimeFormatter withLocale(Locale locale) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[19]++;
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
))) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[1]++;
            return this;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[2]++;}
        return new DateTimeFormatter(iPrinter, iParser, locale,
                iOffsetParsed, iChrono, iZone, iPivotYear, iDefaultYear);
    }

    /**
     * Gets the locale that will be used for printing and parsing.
     * 
     * @return the locale to use; if null, formatter uses default locale at
     * invocation time
     */
    public Locale getLocale() {
        return iLocale;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter that will create a datetime with a time zone
     * equal to that of the offset of the parsed string.
     * <p>
     * After calling this method, a string '2004-06-09T10:20:30-08:00' will
     * create a datetime with a zone of -08:00 (a fixed zone, with no daylight
     * savings rules). If the parsed string represents a local time (no zone
     * offset) the parsed datetime will be in the default zone.
     * <p>
     * Calling this method sets the override zone to null.
     * Calling the override zone method sets this flag off.
     * 
     * @return the new formatter
     */
    public DateTimeFormatter withOffsetParsed() {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iOffsetParsed == true) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[3]++;
            return this;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[4]++;}
        return new DateTimeFormatter(iPrinter, iParser, iLocale,
                true, iChrono, null, iPivotYear, iDefaultYear);
    }

    /**
     * Checks whether the offset from the string is used as the zone of
     * the parsed datetime.
     * 
     * @return true if the offset from the string is used as the zone
     */
    public boolean isOffsetParsed() {
        return iOffsetParsed;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter that will use the specified chronology in
     * preference to that of the printed object, or ISO on a parse.
     * <p>
     * When printing, this chronolgy will be used in preference to the chronology
     * from the datetime that would otherwise be used.
     * <p>
     * When parsing, this chronology will be set on the parsed datetime.
     * <p>
     * A null chronology means no-override.
     * If both an override chronology and an override zone are set, the
     * override zone will take precedence over the zone in the chronology.
     * 
     * @param chrono  the chronology to use as an override
     * @return the new formatter
     */
    public DateTimeFormatter withChronology(Chronology chrono) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iChrono == chrono) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[5]++;
            return this;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[6]++;}
        return new DateTimeFormatter(iPrinter, iParser, iLocale,
                iOffsetParsed, chrono, iZone, iPivotYear, iDefaultYear);
    }

    /**
     * Gets the chronology to use as an override.
     * 
     * @return the chronology to use as an override
     */
    public Chronology getChronology() {
        return iChrono;
    }

    /**
     * Gets the chronology to use as an override.
     * 
     * @return the chronology to use as an override
     * @deprecated Use the method with the correct spelling
     */
    @Deprecated
    public Chronology getChronolgy() {
        return iChrono;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter that will use the UTC zone in preference
     * to the zone of the printed object, or default zone on a parse.
     * <p>
     * When printing, UTC will be used in preference to the zone
     * from the datetime that would otherwise be used.
     * <p>
     * When parsing, UTC will be set on the parsed datetime.
     * <p>
     * If both an override chronology and an override zone are set, the
     * override zone will take precedence over the zone in the chronology.
     * 
     * @return the new formatter, never null
     * @since 2.0
     */
    public DateTimeFormatter withZoneUTC() {
        return withZone(DateTimeZone.UTC);
    }

    /**
     * Returns a new formatter that will use the specified zone in preference
     * to the zone of the printed object, or default zone on a parse.
     * <p>
     * When printing, this zone will be used in preference to the zone
     * from the datetime that would otherwise be used.
     * <p>
     * When parsing, this zone will be set on the parsed datetime.
     * <p>
     * A null zone means of no-override.
     * If both an override chronology and an override zone are set, the
     * override zone will take precedence over the zone in the chronology.
     * 
     * @param zone  the zone to use as an override
     * @return the new formatter
     */
    public DateTimeFormatter withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iZone == zone) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[7]++;
            return this;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[8]++;}
        return new DateTimeFormatter(iPrinter, iParser, iLocale,
                false, iChrono, zone, iPivotYear, iDefaultYear);
    }

    /**
     * Gets the zone to use as an override.
     * 
     * @return the zone to use as an override
     */
    public DateTimeZone getZone() {
        return iZone;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter that will use the specified pivot year for two
     * digit year parsing in preference to that stored in the parser.
     * <p>
     * This setting is useful for changing the pivot year of formats built
     * using a pattern - {@link DateTimeFormat#forPattern(String)}.
     * <p>
     * When parsing, this pivot year is used. Null means no-override.
     * There is no effect when printing.
     * <p>
     * The pivot year enables a two digit year to be converted to a four
     * digit year. The pivot represents the year in the middle of the
     * supported range of years. Thus the full range of years that will
     * be built is <code>(pivot - 50) .. (pivot + 49)</code>.
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
     * @param pivotYear  the pivot year to use as an override when parsing
     * @return the new formatter
     * @since 1.1
     */
    public DateTimeFormatter withPivotYear(Integer pivotYear) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((iPivotYear == pivotYear) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((iPivotYear != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iPivotYear.equals(pivotYear)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[9]++;
            return this;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[10]++;}
        return new DateTimeFormatter(iPrinter, iParser, iLocale,
                iOffsetParsed, iChrono, iZone, pivotYear, iDefaultYear);
    }

    /**
     * Returns a new formatter that will use the specified pivot year for two
     * digit year parsing in preference to that stored in the parser.
     * <p>
     * This setting is useful for changing the pivot year of formats built
     * using a pattern - {@link DateTimeFormat#forPattern(String)}.
     * <p>
     * When parsing, this pivot year is used.
     * There is no effect when printing.
     * <p>
     * The pivot year enables a two digit year to be converted to a four
     * digit year. The pivot represents the year in the middle of the
     * supported range of years. Thus the full range of years that will
     * be built is <code>(pivot - 50) .. (pivot + 49)</code>.
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
     * @param pivotYear  the pivot year to use as an override when parsing
     * @return the new formatter
     * @since 1.1
     */
    public DateTimeFormatter withPivotYear(int pivotYear) {
        return withPivotYear(Integer.valueOf(pivotYear));
    }

    /**
     * Gets the pivot year to use as an override.
     *
     * @return the pivot year to use as an override
     * @since 1.1
     */
    public Integer getPivotYear() {
      return iPivotYear;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new formatter that will use the specified default year.
     * <p>
     * The default year is used when parsing in the case where there is a
     * month or a day but not a year. Specifically, it is used if there is
     * a field parsed with a duration between the length of a month and the
     * length of a day inclusive.
     * <p>
     * This value is typically used to move the year from 1970 to a leap year
     * to enable February 29th to be parsed.
     * Unless customised, the year 2000 is used.
     * <p>
     * This setting has no effect when printing.
     *
     * @param defaultYear  the default year to use
     * @return the new formatter, not null
     * @since 2.0
     */
    public DateTimeFormatter withDefaultYear(int defaultYear) {
        return new DateTimeFormatter(iPrinter, iParser, iLocale,
                iOffsetParsed, iChrono, iZone, iPivotYear, defaultYear);
    }

    /**
     * Gets the default year for parsing months and days.
     *
     * @return the default year for parsing months and days
     * @since 2.0
     */
    public int getDefaultYear() {
      return iDefaultYear;
    }

    //-----------------------------------------------------------------------
    /**
     * Prints a ReadableInstant, using the chronology supplied by the instant.
     *
     * @param buf  the destination to format to, not null
     * @param instant  instant to format, null means now
     */
    public void printTo(StringBuffer buf, ReadableInstant instant) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[24]++;
        long millis = DateTimeUtils.getInstantMillis(instant);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[25]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(instant);
        printTo(buf, millis, chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[26]++;
    }

    /**
     * Prints a ReadableInstant, using the chronology supplied by the instant.
     *
     * @param out  the destination to format to, not null
     * @param instant  instant to format, null means now
     */
    public void printTo(Writer out, ReadableInstant instant) throws IOException {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[27]++;
        long millis = DateTimeUtils.getInstantMillis(instant);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[28]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(instant);
        printTo(out, millis, chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[29]++;
    }

    /**
     * Prints a ReadableInstant, using the chronology supplied by the instant.
     *
     * @param appendable  the destination to format to, not null
     * @param instant  instant to format, null means now
     * @since 2.0
     */
    public void printTo(Appendable appendable, ReadableInstant instant) throws IOException {
        appendable.append(print(instant));
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[30]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Prints an instant from milliseconds since 1970-01-01T00:00:00Z,
     * using ISO chronology in the default DateTimeZone.
     *
     * @param buf  the destination to format to, not null
     * @param instant  millis since 1970-01-01T00:00:00Z
     */
    public void printTo(StringBuffer buf, long instant) {
        printTo(buf, instant, null);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[31]++;
    }

    /**
     * Prints an instant from milliseconds since 1970-01-01T00:00:00Z,
     * using ISO chronology in the default DateTimeZone.
     *
     * @param out  the destination to format to, not null
     * @param instant  millis since 1970-01-01T00:00:00Z
     */
    public void printTo(Writer out, long instant) throws IOException {
        printTo(out, instant, null);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[32]++;
    }

    /**
     * Prints an instant from milliseconds since 1970-01-01T00:00:00Z,
     * using ISO chronology in the default DateTimeZone.
     *
     * @param appendable  the destination to format to, not null
     * @param instant  millis since 1970-01-01T00:00:00Z
     * @since 2.0
     */
    public void printTo(Appendable appendable, long instant) throws IOException {
        appendable.append(print(instant));
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[33]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Prints a ReadablePartial.
     * <p>
     * Neither the override chronology nor the override zone are used
     * by this method.
     *
     * @param buf  the destination to format to, not null
     * @param partial  partial to format
     */
    public void printTo(StringBuffer buf, ReadablePartial partial) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[34]++;
        DateTimePrinter printer = requirePrinter();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[35]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[11]++;
            throw new IllegalArgumentException("The partial must not be null");

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[12]++;}
        printer.printTo(buf, partial, iLocale);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[36]++;
    }

    /**
     * Prints a ReadablePartial.
     * <p>
     * Neither the override chronology nor the override zone are used
     * by this method.
     *
     * @param out  the destination to format to, not null
     * @param partial  partial to format
     */
    public void printTo(Writer out, ReadablePartial partial) throws IOException {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[37]++;
        DateTimePrinter printer = requirePrinter();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[13]++;
            throw new IllegalArgumentException("The partial must not be null");

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[14]++;}
        printer.printTo(out, partial, iLocale);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[39]++;
    }

    /**
     * Prints a ReadablePartial.
     * <p>
     * Neither the override chronology nor the override zone are used
     * by this method.
     *
     * @param appendable  the destination to format to, not null
     * @param partial  partial to format
     * @since 2.0
     */
    public void printTo(Appendable appendable, ReadablePartial partial) throws IOException {
        appendable.append(print(partial));
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[40]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Prints a ReadableInstant to a String.
     * <p>
     * This method will use the override zone and the override chronololgy if
     * they are set. Otherwise it will use the chronology and zone of the instant.
     *
     * @param instant  instant to format, null means now
     * @return the printed result
     */
    public String print(ReadableInstant instant) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[41]++;
        StringBuffer buf = new StringBuffer(requirePrinter().estimatePrintedLength());
        printTo(buf, instant);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[42]++;
        return buf.toString();
    }

    /**
     * Prints a millisecond instant to a String.
     * <p>
     * This method will use the override zone and the override chronololgy if
     * they are set. Otherwise it will use the ISO chronology and default zone.
     *
     * @param instant  millis since 1970-01-01T00:00:00Z
     * @return the printed result
     */
    public String print(long instant) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[43]++;
        StringBuffer buf = new StringBuffer(requirePrinter().estimatePrintedLength());
        printTo(buf, instant);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[44]++;
        return buf.toString();
    }

    /**
     * Prints a ReadablePartial to a new String.
     * <p>
     * Neither the override chronology nor the override zone are used
     * by this method.
     *
     * @param partial  partial to format
     * @return the printed result
     */
    public String print(ReadablePartial partial) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[45]++;
        StringBuffer buf = new StringBuffer(requirePrinter().estimatePrintedLength());
        printTo(buf, partial);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[46]++;
        return buf.toString();
    }

    private void printTo(StringBuffer buf, long instant, Chronology chrono) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[47]++;
        DateTimePrinter printer = requirePrinter();
        chrono = selectChronology(chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[48]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[49]++;
        // Shift instant into local time (UTC) to avoid excessive offset
        // calculations when printing multiple fields in a composite printer.
        DateTimeZone zone = chrono.getZone();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[50]++;
        int offset = zone.getOffset(instant);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[51]++;
        long adjustedInstant = instant + offset;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[52]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 (((instant ^ adjustedInstant) < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 (((instant ^ offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[15]++;
            // Time zone offset overflow, so revert to UTC.
            zone = DateTimeZone.UTC;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[53]++;
            offset = 0;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[54]++;
            adjustedInstant = instant;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[55]++;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[16]++;}
        printer.printTo(buf, adjustedInstant, chrono.withUTC(), offset, zone, iLocale);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[56]++;
    }

    private void printTo(Writer buf, long instant, Chronology chrono) throws IOException {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[57]++;
        DateTimePrinter printer = requirePrinter();
        chrono = selectChronology(chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[58]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[59]++;
        // Shift instant into local time (UTC) to avoid excessive offset
        // calculations when printing multiple fields in a composite printer.
        DateTimeZone zone = chrono.getZone();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[60]++;
        int offset = zone.getOffset(instant);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[61]++;
        long adjustedInstant = instant + offset;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[62]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 (((instant ^ adjustedInstant) < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 (((instant ^ offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[17]++;
            // Time zone offset overflow, so revert to UTC.
            zone = DateTimeZone.UTC;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[63]++;
            offset = 0;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[64]++;
            adjustedInstant = instant;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[65]++;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[18]++;}
        printer.printTo(buf, adjustedInstant, chrono.withUTC(), offset, zone, iLocale);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[66]++;
    }

    /**
     * Checks whether printing is supported.
     * 
     * @throws UnsupportedOperationException if printing is not supported
     */
    private DateTimePrinter requirePrinter() {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[67]++;
        DateTimePrinter printer = iPrinter;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[68]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((printer == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[19]++;
            throw new UnsupportedOperationException("Printing not supported");

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[20]++;}
        return printer;
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a datetime from the given text, at the given position, saving the
     * result into the fields of the given ReadWritableInstant. If the parse
     * succeeds, the return value is the new text position. Note that the parse
     * may succeed without fully reading the text and in this case those fields
     * that were read will be set.
     * <p>
     * Only those fields present in the string will be changed in the specified
     * instant. All other fields will remain unaltered. Thus if the string only
     * contains a year and a month, then the day and time will be retained from
     * the input instant. If this is not the behaviour you want, then reset the
     * fields before calling this method, or use {@link #parseDateTime(String)}
     * or {@link #parseMutableDateTime(String)}.
     * <p>
     * If it fails, the return value is negative, but the instant may still be
     * modified. To determine the position where the parse failed, apply the
     * one's complement operator (~) on the return value.
     * <p>
     * The parse will use the chronology of the instant.
     *
     * @param instant  an instant that will be modified, not null
     * @param text  the text to parse
     * @param position  position to start parsing from
     * @return new position, negative value means parse failed -
     *  apply complement operator (~) to get position of failure
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the instant is null
     * @throws IllegalArgumentException if any field is out of range
     */
    public int parseInto(ReadWritableInstant instant, String text, int position) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[69]++;
        DateTimeParser parser = requireParser();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[70]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[21]++;
            throw new IllegalArgumentException("Instant must not be null");

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[22]++;}
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[71]++;
        
        long instantMillis = instant.getMillis();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[72]++;
        Chronology chrono = instant.getChronology();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[73]++;
        long instantLocal = instantMillis + chrono.getZone().getOffset(instantMillis);
        chrono = selectChronology(chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[74]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[75]++;
        
        DateTimeParserBucket bucket = new DateTimeParserBucket(
            instantLocal, chrono, iLocale, iPivotYear, iDefaultYear);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[76]++;
        int newPos = parser.parseInto(bucket, text, position);
        instant.setMillis(bucket.computeMillis(false, text));
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[77]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[78]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((iOffsetParsed) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((bucket.getOffsetInteger() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[23]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[79]++;
            int parsedOffset = bucket.getOffsetInteger();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[80]++;
            DateTimeZone parsedZone = DateTimeZone.forOffsetMillis(parsedOffset);
            chrono = chrono.withZone(parsedZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[81]++;

        } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[24]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[82]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((bucket.getZone() != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[25]++;
            chrono = chrono.withZone(bucket.getZone());
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[83]++;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[26]++;}
}
        instant.setChronology(chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[84]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[85]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((iZone != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[27]++;
            instant.setZone(iZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[86]++;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[28]++;}
        return newPos;
    }

    /**
     * Parses a datetime from the given text, returning the number of
     * milliseconds since the epoch, 1970-01-01T00:00:00Z.
     * <p>
     * The parse will use the ISO chronology, and the default time zone.
     * If the text contains a time zone string then that will be taken into account.
     *
     * @param text  text to parse
     * @return parsed value expressed in milliseconds since the epoch
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the text to parse is invalid
     */
    public long parseMillis(String text) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[87]++;
        DateTimeParser parser = requireParser();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[88]++;
        
        Chronology chrono = selectChronology(iChrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[89]++;
        DateTimeParserBucket bucket = new DateTimeParserBucket(0, chrono, iLocale, iPivotYear, iDefaultYear);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[90]++;
        int newPos = parser.parseInto(bucket, text, 0);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[91]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((newPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[29]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[92]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((newPos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[31]++;
                return bucket.computeMillis(true, text);

            } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[32]++;}

        } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[30]++;
            newPos = ~newPos;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[93]++;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(text, newPos));
    }

    /**
     * Parses only the local date from the given text, returning a new LocalDate.
     * <p>
     * This will parse the text fully according to the formatter, using the UTC zone.
     * Once parsed, only the local date will be used.
     * This means that any parsed time, time-zone or offset field is completely ignored.
     * It also means that the zone and offset-parsed settings are ignored.
     *
     * @param text  the text to parse, not null
     * @return the parsed date, never null
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the text to parse is invalid
     * @since 2.0
     */
    public LocalDate parseLocalDate(String text) {
        return parseLocalDateTime(text).toLocalDate();
    }

    /**
     * Parses only the local time from the given text, returning a new LocalDate.
     * <p>
     * This will parse the text fully according to the formatter, using the UTC zone.
     * Once parsed, only the local time will be used.
     * This means that any parsed date, time-zone or offset field is completely ignored.
     * It also means that the zone and offset-parsed settings are ignored.
     *
     * @param text  the text to parse, not null
     * @return the parsed time, never null
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the text to parse is invalid
     * @since 2.0
     */
    public LocalTime parseLocalTime(String text) {
        return parseLocalDateTime(text).toLocalTime();
    }

    /**
     * Parses only the local date-time from the given text, returning a new LocalDate.
     * <p>
     * This will parse the text fully according to the formatter, using the UTC zone.
     * Once parsed, only the local date-time will be used.
     * This means that any parsed time-zone or offset field is completely ignored.
     * It also means that the zone and offset-parsed settings are ignored.
     *
     * @param text  the text to parse, not null
     * @return the parsed date-time, never null
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the text to parse is invalid
     * @since 2.0
     */
    public LocalDateTime parseLocalDateTime(String text) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[94]++;
        DateTimeParser parser = requireParser();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[95]++;
        
        Chronology chrono = selectChronology(null).withUTC();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[96]++;  // always use UTC, avoiding DST gaps
        DateTimeParserBucket bucket = new DateTimeParserBucket(0, chrono, iLocale, iPivotYear, iDefaultYear);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[97]++;
        int newPos = parser.parseInto(bucket, text, 0);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[98]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((newPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[33]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[99]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((newPos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[35]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[100]++;
                long millis = bucket.computeMillis(true, text);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[101]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((bucket.getOffsetInteger() != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[37]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[102]++;  // treat withOffsetParsed() as being true
                    int parsedOffset = bucket.getOffsetInteger();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[103]++;
                    DateTimeZone parsedZone = DateTimeZone.forOffsetMillis(parsedOffset);
                    chrono = chrono.withZone(parsedZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[104]++;

                } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[38]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[105]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((bucket.getZone() != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[39]++;
                    chrono = chrono.withZone(bucket.getZone());
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[106]++;

                } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[40]++;}
}
                return new LocalDateTime(millis, chrono);

            } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[36]++;}

        } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[34]++;
            newPos = ~newPos;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[107]++;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(text, newPos));
    }

    /**
     * Parses a date-time from the given text, returning a new DateTime.
     * <p>
     * The parse will use the zone and chronology specified on this formatter.
     * <p>
     * If the text contains a time zone string then that will be taken into
     * account in adjusting the time of day as follows.
     * If the {@link #withOffsetParsed()} has been called, then the resulting
     * DateTime will have a fixed offset based on the parsed time zone.
     * Otherwise the resulting DateTime will have the zone of this formatter,
     * but the parsed zone may have caused the time to be adjusted.
     *
     * @param text  the text to parse, not null
     * @return the parsed date-time, never null
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the text to parse is invalid
     */
    public DateTime parseDateTime(String text) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[108]++;
        DateTimeParser parser = requireParser();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[109]++;
        
        Chronology chrono = selectChronology(null);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[110]++;
        DateTimeParserBucket bucket = new DateTimeParserBucket(0, chrono, iLocale, iPivotYear, iDefaultYear);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[111]++;
        int newPos = parser.parseInto(bucket, text, 0);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[112]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((newPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[41]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[113]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((newPos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[43]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[114]++;
                long millis = bucket.computeMillis(true, text);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[115]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((iOffsetParsed) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((bucket.getOffsetInteger() != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[45]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[116]++;
                    int parsedOffset = bucket.getOffsetInteger();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[117]++;
                    DateTimeZone parsedZone = DateTimeZone.forOffsetMillis(parsedOffset);
                    chrono = chrono.withZone(parsedZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[118]++;

                } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[46]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[119]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((bucket.getZone() != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[47]++;
                    chrono = chrono.withZone(bucket.getZone());
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[120]++;

                } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[48]++;}
}
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[121]++;
                DateTime dt = new DateTime(millis, chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[122]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iZone != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[49]++;
                    dt = dt.withZone(iZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[123]++;

                } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[50]++;}
                return dt;

            } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[44]++;}

        } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[42]++;
            newPos = ~newPos;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[124]++;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(text, newPos));
    }

    /**
     * Parses a date-time from the given text, returning a new MutableDateTime.
     * <p>
     * The parse will use the zone and chronology specified on this formatter.
     * <p>
     * If the text contains a time zone string then that will be taken into
     * account in adjusting the time of day as follows.
     * If the {@link #withOffsetParsed()} has been called, then the resulting
     * DateTime will have a fixed offset based on the parsed time zone.
     * Otherwise the resulting DateTime will have the zone of this formatter,
     * but the parsed zone may have caused the time to be adjusted.
     *
     * @param text  the text to parse, not null
     * @return the parsed date-time, never null
     * @throws UnsupportedOperationException if parsing is not supported
     * @throws IllegalArgumentException if the text to parse is invalid
     */
    public MutableDateTime parseMutableDateTime(String text) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[125]++;
        DateTimeParser parser = requireParser();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[126]++;
        
        Chronology chrono = selectChronology(null);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[127]++;
        DateTimeParserBucket bucket = new DateTimeParserBucket(0, chrono, iLocale, iPivotYear, iDefaultYear);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[128]++;
        int newPos = parser.parseInto(bucket, text, 0);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[129]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((newPos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[51]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[130]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((newPos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[53]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[131]++;
                long millis = bucket.computeMillis(true, text);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[132]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((iOffsetParsed) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((bucket.getOffsetInteger() != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[55]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[133]++;
                    int parsedOffset = bucket.getOffsetInteger();
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[134]++;
                    DateTimeZone parsedZone = DateTimeZone.forOffsetMillis(parsedOffset);
                    chrono = chrono.withZone(parsedZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[135]++;

                } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[56]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[136]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((bucket.getZone() != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[57]++;
                    chrono = chrono.withZone(bucket.getZone());
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[137]++;

                } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[58]++;}
}
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[138]++;
                MutableDateTime dt = new MutableDateTime(millis, chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[139]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((iZone != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[59]++;
                    dt.setZone(iZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[140]++;

                } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[60]++;}
                return dt;

            } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[54]++;}

        } else {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[52]++;
            newPos = ~newPos;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[141]++;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(text, newPos));
    }

    /**
     * Checks whether parsing is supported.
     * 
     * @throws UnsupportedOperationException if parsing is not supported
     */
    private DateTimeParser requireParser() {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[142]++;
        DateTimeParser parser = iParser;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[143]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((parser == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[61]++;
            throw new UnsupportedOperationException("Parsing not supported");

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[62]++;}
        return parser;
    }

    //-----------------------------------------------------------------------
    /**
     * Determines the correct chronology to use.
     *
     * @param chrono  the proposed chronology
     * @return the actual chronology
     */
    private Chronology selectChronology(Chronology chrono) {
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[144]++;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[145]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((iChrono != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[63]++;
            chrono = iChrono;
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[146]++;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[64]++;}
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[147]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((iZone != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[65]++;
            chrono = chrono.withZone(iZone);
CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.statements[148]++;

        } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl.branches[66]++;}
        return chrono;
    }

}

class CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl ());
  }
    public static long[] statements = new long[149];
    public static long[] branches = new long[67];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "org.joda.time.format.DateTimeFormatter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,3,1,1,2,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 33; i++) {
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

  public CodeCoverCoverageCounter$b7x136z3gysgwnxg28ea9neh19ayawwqjl () {
    super("org.joda.time.format.DateTimeFormatter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 148; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 66; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.DateTimeFormatter.java");
      for (int i = 1; i <= 148; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 66; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 33; i++) {
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

