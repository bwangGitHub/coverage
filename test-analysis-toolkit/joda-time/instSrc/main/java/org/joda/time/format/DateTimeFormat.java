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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

/**
 * Factory that creates instances of DateTimeFormatter from patterns and styles.
 * <p>
 * Datetime formatting is performed by the {@link DateTimeFormatter} class.
 * Three classes provide factory methods to create formatters, and this is one.
 * The others are {@link ISODateTimeFormat} and {@link DateTimeFormatterBuilder}.
 * <p>
 * This class provides two types of factory:
 * <ul>
 * <li>{@link #forPattern(String) Pattern} provides a DateTimeFormatter based on
 * a pattern string that is mostly compatible with the JDK date patterns.
 * <li>{@link #forStyle(String) Style} provides a DateTimeFormatter based on a
 * two character style, representing short, medium, long and full.
 * </ul>
 * <p>
 * For example, to use a patterm:
 * <pre>
 * DateTime dt = new DateTime();
 * DateTimeFormatter fmt = DateTimeFormat.forPattern("MMMM, yyyy");
 * String str = fmt.print(dt);
 * </pre>
 *
 * The pattern syntax is mostly compatible with java.text.SimpleDateFormat -
 * time zone names cannot be parsed and a few more symbols are supported.
 * All ASCII letters are reserved as pattern letters, which are defined as follows:
 * <blockquote>
 * <pre>
 * Symbol  Meaning                      Presentation  Examples
 * ------  -------                      ------------  -------
 * G       era                          text          AD
 * C       century of era (&gt;=0)         number        20
 * Y       year of era (&gt;=0)            year          1996
 *
 * x       weekyear                     year          1996
 * w       week of weekyear             number        27
 * e       day of week                  number        2
 * E       day of week                  text          Tuesday; Tue
 *
 * y       year                         year          1996
 * D       day of year                  number        189
 * M       month of year                month         July; Jul; 07
 * d       day of month                 number        10
 *
 * a       halfday of day               text          PM
 * K       hour of halfday (0~11)       number        0
 * h       clockhour of halfday (1~12)  number        12
 *
 * H       hour of day (0~23)           number        0
 * k       clockhour of day (1~24)      number        24
 * m       minute of hour               number        30
 * s       second of minute             number        55
 * S       fraction of second           number        978
 *
 * z       time zone                    text          Pacific Standard Time; PST
 * Z       time zone offset/id          zone          -0800; -08:00; America/Los_Angeles
 *
 * '       escape for text              delimiter
 * ''      single quote                 literal       '
 * </pre>
 * </blockquote>
 * The count of pattern letters determine the format.
 * <p>
 * <strong>Text</strong>: If the number of pattern letters is 4 or more,
 * the full form is used; otherwise a short or abbreviated form is used if
 * available.
 * <p>
 * <strong>Number</strong>: The minimum number of digits. Shorter numbers
 * are zero-padded to this amount.
 * <p>
 * <strong>Year</strong>: Numeric presentation for year and weekyear fields
 * are handled specially. For example, if the count of 'y' is 2, the year
 * will be displayed as the zero-based year of the century, which is two
 * digits.
 * <p>
 * <strong>Month</strong>: 3 or over, use text, otherwise use number.
 * <p>
 * <strong>Zone</strong>: 'Z' outputs offset without a colon, 'ZZ' outputs
 * the offset with a colon, 'ZZZ' or more outputs the zone id.
 * <p>
 * <strong>Zone names</strong>: Time zone names ('z') cannot be parsed.
 * <p>
 * Any characters in the pattern that are not in the ranges of ['a'..'z']
 * and ['A'..'Z'] will be treated as quoted text. For instance, characters
 * like ':', '.', ' ', '#' and '?' will appear in the resulting time text
 * even they are not embraced within single quotes.
 * <p>
 * DateTimeFormat is thread-safe and immutable, and the formatters it returns
 * are as well.
 *
 * @author Brian S O'Neill
 * @author Maxim Zhao
 * @since 1.0
 * @see ISODateTimeFormat
 * @see DateTimeFormatterBuilder
 */
public class DateTimeFormat {
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.ping();
  }


    /** Style constant for FULL. */
    static final int FULL = 0;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[1]++;
  }  // DateFormat.FULL
    /** Style constant for LONG. */
    static final int LONG = 1;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[2]++;
  }  // DateFormat.LONG
    /** Style constant for MEDIUM. */
    static final int MEDIUM = 2;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[3]++;
  }  // DateFormat.MEDIUM
    /** Style constant for SHORT. */
    static final int SHORT = 3;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[4]++;
  }  // DateFormat.SHORT
    /** Style constant for NONE. */
    static final int NONE = 4;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[5]++;
  }

    /** Type constant for DATE only. */
    static final int DATE = 0;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[6]++;
  }
    /** Type constant for TIME only. */
    static final int TIME = 1;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[7]++;
  }
    /** Type constant for DATETIME. */
    static final int DATETIME = 2;
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[8]++;
  }

    /** Maps patterns to formatters, patterns don't vary by locale. */
    private static final Map<String, DateTimeFormatter> cPatternedCache = new HashMap<String, DateTimeFormatter>(7);
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[9]++;
  }
    /** Maps patterns to formatters, patterns don't vary by locale. */
    private static final DateTimeFormatter[] cStyleCache = new DateTimeFormatter[25];
  static {
    CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[10]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Factory to create a formatter from a pattern string.
     * The pattern string is described above in the class level javadoc.
     * It is very similar to SimpleDateFormat patterns.
     * <p>
     * The format may contain locale specific output, and this will change as
     * you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * For example:
     * <pre>
     * DateTimeFormat.forPattern(pattern).withLocale(Locale.FRANCE).print(dt);
     * </pre>
     *
     * @param pattern  pattern specification
     * @return the formatter
     * @throws IllegalArgumentException if the pattern is invalid
     */
    public static DateTimeFormatter forPattern(String pattern) {
        return createFormatterForPattern(pattern);
    }

    /**
     * Factory to create a format from a two character style pattern.
     * <p>
     * The first character is the date style, and the second character is the
     * time style. Specify a character of 'S' for short style, 'M' for medium,
     * 'L' for long, and 'F' for full.
     * A date or time may be ommitted by specifying a style character '-'.
     * <p>
     * The returned formatter will dynamically adjust to the locale that
     * the print/parse takes place in. Thus you just call
     * {@link DateTimeFormatter#withLocale(Locale)} and the Short/Medium/Long/Full
     * style for that locale will be output. For example:
     * <pre>
     * DateTimeFormat.forStyle(style).withLocale(Locale.FRANCE).print(dt);
     * </pre>
     *
     * @param style  two characters from the set {"S", "M", "L", "F", "-"}
     * @return the formatter
     * @throws IllegalArgumentException if the style is invalid
     */
    public static DateTimeFormatter forStyle(String style) {
        return createFormatterForStyle(style);
    }

    /**
     * Returns the pattern used by a particular style and locale.
     * <p>
     * The first character is the date style, and the second character is the
     * time style. Specify a character of 'S' for short style, 'M' for medium,
     * 'L' for long, and 'F' for full.
     * A date or time may be ommitted by specifying a style character '-'.
     *
     * @param style  two characters from the set {"S", "M", "L", "F", "-"}
     * @param locale  locale to use, null means default
     * @return the formatter
     * @throws IllegalArgumentException if the style is invalid
     * @since 1.3
     */
    public static String patternForStyle(String style, Locale locale) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[11]++;
        DateTimeFormatter formatter = createFormatterForStyle(style);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[1]++;
            locale = Locale.getDefault();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[13]++;

        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[2]++;}
        // Not pretty, but it works.
        return ((StyleFormatter) formatter.getPrinter()).getPattern(locale);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a format that outputs a short date format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter shortDate() {
        return createFormatterForStyleIndex(SHORT, NONE);
    }

    /**
     * Creates a format that outputs a short time format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter shortTime() {
        return createFormatterForStyleIndex(NONE, SHORT);
    }

    /**
     * Creates a format that outputs a short datetime format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter shortDateTime() {
        return createFormatterForStyleIndex(SHORT, SHORT);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a format that outputs a medium date format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter mediumDate() {
        return createFormatterForStyleIndex(MEDIUM, NONE);
    }

    /**
     * Creates a format that outputs a medium time format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter mediumTime() {
        return createFormatterForStyleIndex(NONE, MEDIUM);
    }

    /**
     * Creates a format that outputs a medium datetime format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter mediumDateTime() {
        return createFormatterForStyleIndex(MEDIUM, MEDIUM);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a format that outputs a long date format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter longDate() {
        return createFormatterForStyleIndex(LONG, NONE);
    }

    /**
     * Creates a format that outputs a long time format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter longTime() {
        return createFormatterForStyleIndex(NONE, LONG);
    }

    /**
     * Creates a format that outputs a long datetime format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter longDateTime() {
        return createFormatterForStyleIndex(LONG, LONG);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a format that outputs a full date format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter fullDate() {
        return createFormatterForStyleIndex(FULL, NONE);
    }

    /**
     * Creates a format that outputs a full time format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter fullTime() {
        return createFormatterForStyleIndex(NONE, FULL);
    }

    /**
     * Creates a format that outputs a full datetime format.
     * <p>
     * The format will change as you change the locale of the formatter.
     * Call {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
     * 
     * @return the formatter
     */
    public static DateTimeFormatter fullDateTime() {
        return createFormatterForStyleIndex(FULL, FULL);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses the given pattern and appends the rules to the given
     * DateTimeFormatterBuilder.
     *
     * @param pattern  pattern specification
     * @throws IllegalArgumentException if the pattern is invalid
     */
    static void appendPatternTo(DateTimeFormatterBuilder builder, String pattern) {
        parsePatternTo(builder, pattern);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[14]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @since 1.1 (previously private)
     */
    protected DateTimeFormat() {
        super();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[15]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Parses the given pattern and appends the rules to the given
     * DateTimeFormatterBuilder.
     *
     * @param pattern  pattern specification
     * @throws IllegalArgumentException if the pattern is invalid
     * @see #forPattern
     */
    private static void parsePatternTo(DateTimeFormatterBuilder builder, String pattern) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[16]++;
        int length = pattern.length();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[17]++;
        int[] indexRef = new int[1];
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

        for (int i=0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[1]--;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[2]--;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[3]++;
}
            indexRef[0] = i;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[19]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[20]++;
            String token = parseToken(pattern, indexRef);
            i = indexRef[0];
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[21]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[22]++;

            int tokenLen = token.length();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((tokenLen == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[3]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[24]++;
                break;

            } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[4]++;}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[25]++;
            char c = token.charAt(0);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[26]++;

            switch (c) {
            case 'G':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[5]++; // era designator (text)
                builder.appendEraText();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[27]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[28]++;
                break;
            case 'C':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[6]++; // century of era (number)
                builder.appendCenturyOfEra(tokenLen, tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[29]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[30]++;
                break;
            case 'x':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[7]++; // weekyear (number)
            case 'y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[8]++; // year (number)
            case 'Y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[9]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[31]++;
int CodeCoverConditionCoverageHelper_C4; // year of era (number)
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((tokenLen == 2) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[10]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[32]++;
                    boolean lenientParse = true;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;

                    // Peek ahead to next token.
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i + 1 < length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[12]++;
                        indexRef[0]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[34]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[35]++;
int CodeCoverConditionCoverageHelper_C6;
                        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isNumericToken(parseToken(pattern, indexRef))) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[14]++;
                            // If next token is a number, cannot support
                            // lenient parse, because it will consume digits
                            // that it should not.
                            lenientParse = false;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[36]++;

                        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[15]++;}
                        indexRef[0]--;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[37]++;

                    } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[13]++;}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[38]++;

                    // Use pivots which are compatible with SimpleDateFormat.
                    switch (c) {
                    case 'x':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[16]++;
                        builder.appendTwoDigitWeekyear
                            (new DateTime().getWeekyear() - 30, lenientParse);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[39]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[40]++;
                        break;
                    case 'y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[17]++;
                    case 'Y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[18]++;
                    default:
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[19]++;
                        builder.appendTwoDigitYear(new DateTime().getYear() - 30, lenientParse);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[41]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[42]++;
                        break;
                    }

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[11]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[43]++;
                    // Try to support long year values.
                    int maxDigits = 9;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;

                    // Peek ahead to next token.
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i + 1 < length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[20]++;
                        indexRef[0]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[45]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
                        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isNumericToken(parseToken(pattern, indexRef))) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[22]++;
                            // If next token is a number, cannot support long years.
                            maxDigits = tokenLen;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[47]++;

                        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[23]++;}
                        indexRef[0]--;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[48]++;

                    } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[21]++;}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[49]++;

                    switch (c) {
                    case 'x':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[24]++;
                        builder.appendWeekyear(tokenLen, maxDigits);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[50]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[51]++;
                        break;
                    case 'y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[25]++;
                        builder.appendYear(tokenLen, maxDigits);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[52]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[53]++;
                        break;
                    case 'Y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[26]++;
                        builder.appendYearOfEra(tokenLen, maxDigits);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[54]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[55]++;
                        break; default : CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[27]++;
                    }
                }
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[56]++;
                break;
            case 'M':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[28]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[57]++;
int CodeCoverConditionCoverageHelper_C9; // month of year (text and number)
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((tokenLen >= 3) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[29]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[58]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((tokenLen >= 4) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[31]++;
                        builder.appendMonthOfYearText();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[59]++;

                    } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[32]++;
                        builder.appendMonthOfYearShortText();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[60]++;
                    }

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[30]++;
                    builder.appendMonthOfYear(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[61]++;
                }
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[62]++;
                break;
            case 'd':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[33]++; // day of month (number)
                builder.appendDayOfMonth(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[63]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[64]++;
                break;
            case 'a':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[34]++; // am/pm marker (text)
                builder.appendHalfdayOfDayText();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[65]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[66]++;
                break;
            case 'h':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[35]++; // clockhour of halfday (number, 1..12)
                builder.appendClockhourOfHalfday(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[67]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[68]++;
                break;
            case 'H':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[36]++; // hour of day (number, 0..23)
                builder.appendHourOfDay(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[69]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[70]++;
                break;
            case 'k':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[37]++; // clockhour of day (1..24)
                builder.appendClockhourOfDay(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[71]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[72]++;
                break;
            case 'K':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[38]++; // hour of halfday (0..11)
                builder.appendHourOfHalfday(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[73]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[74]++;
                break;
            case 'm':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[39]++; // minute of hour (number)
                builder.appendMinuteOfHour(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[75]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[76]++;
                break;
            case 's':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[40]++; // second of minute (number)
                builder.appendSecondOfMinute(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[77]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[78]++;
                break;
            case 'S':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[41]++; // fraction of second (number)
                builder.appendFractionOfSecond(tokenLen, tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[79]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[80]++;
                break;
            case 'e':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[42]++; // day of week (number)
                builder.appendDayOfWeek(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[81]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[82]++;
                break;
            case 'E':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[43]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[83]++;
int CodeCoverConditionCoverageHelper_C11; // dayOfWeek (text)
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((tokenLen >= 4) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[44]++;
                    builder.appendDayOfWeekText();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[84]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[45]++;
                    builder.appendDayOfWeekShortText();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[85]++;
                }
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[86]++;
                break;
            case 'D':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[46]++; // day of year (number)
                builder.appendDayOfYear(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[87]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[88]++;
                break;
            case 'w':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[47]++; // week of weekyear (number)
                builder.appendWeekOfWeekyear(tokenLen);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[89]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[90]++;
                break;
            case 'z':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[48]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[91]++;
int CodeCoverConditionCoverageHelper_C12; // time zone (text)
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((tokenLen >= 4) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[49]++;
                    builder.appendTimeZoneName();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[92]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[50]++;
                    builder.appendTimeZoneShortName();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[93]++;
                }
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[94]++;
                break;
            case 'Z':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[51]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[95]++;
int CodeCoverConditionCoverageHelper_C13; // time zone offset
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((tokenLen == 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[52]++;
                    builder.appendTimeZoneOffset(null, "Z", false, 2, 2);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[96]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[53]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[97]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((tokenLen == 2) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[54]++;
                    builder.appendTimeZoneOffset(null, "Z", true, 2, 2);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[98]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[55]++;
                    builder.appendTimeZoneId();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[99]++;
                }
}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[100]++;
                break;
            case '\'':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[56]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[101]++; // literal text
                String sub = token.substring(1);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[102]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((sub.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[57]++;
                    builder.appendLiteral(sub.charAt(0));
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[103]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[58]++;
                    // Create copy of sub since otherwise the temporary quoted
                    // string would still be referenced internally.
                    builder.appendLiteral(new String(sub));
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[104]++;
                }
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[105]++;
                break;
            default:
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[59]++;
                throw new IllegalArgumentException
                    ("Illegal pattern component: " + token);
            }
        }
    }

    /**
     * Parses an individual token.
     * 
     * @param pattern  the pattern string
     * @param indexRef  a single element array, where the input is the start
     *  location and the output is the location after parsing the token
     * @return the parsed token
     */
    private static String parseToken(String pattern, int[] indexRef) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[106]++;
        StringBuffer buf = new StringBuffer();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[107]++;

        int i = indexRef[0];
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[108]++;
        int length = pattern.length();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[109]++;

        char c = pattern.charAt(i);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[110]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (128)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 4) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 4) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[60]++;
            // Scan a run of the same character, which indicates a time
            // pattern.
            buf.append(c);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[111]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[112]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;

            while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i + 1 < length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[4]--;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[5]--;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[6]++;
}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[113]++;
                char peek = pattern.charAt(i + 1);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[114]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((peek == c) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[62]++;
                    buf.append(c);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[115]++;
                    i++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[116]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[63]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[117]++;
                    break;
                }
            }

        } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[61]++;
            // This will identify token as text.
            buf.append('\'');
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[118]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[119]++;

            boolean inLiteral = false;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[120]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[7]++;


int CodeCoverConditionCoverageHelper_C19;

            for (;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[7]--;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[8]--;
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.loops[9]++;
}
                c = pattern.charAt(i);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[121]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[122]++;
int CodeCoverConditionCoverageHelper_C20;
                
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((c == '\'') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[64]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[123]++;
int CodeCoverConditionCoverageHelper_C21;
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((i + 1 < length) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((pattern.charAt(i + 1) == '\'') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[66]++;
                        // '' is treated as escaped '
                        i++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[124]++;
                        buf.append(c);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[125]++;

                    } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[67]++;
                        inLiteral = !inLiteral;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[126]++;
                    }

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[65]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[127]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (512)) == 0 || true) &&
 ((inLiteral) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (256)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C22 |= (128)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((c <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 5) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 5) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[68]++;
                    i--;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[128]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[129]++;
                    break;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[69]++;
                    buf.append(c);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[130]++;
                }
}
            }
        }

        indexRef[0] = i;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[131]++;
        return buf.toString();
    }

    /**
     * Returns true if token should be parsed as a numeric field.
     * 
     * @param token  the token to parse
     * @return true if numeric field
     */
    private static boolean isNumericToken(String token) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[132]++;
        int tokenLen = token.length();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[133]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((tokenLen > 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[70]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[134]++;
            char c = token.charAt(0);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[135]++;
            switch (c) {
            case 'c':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[72]++; // century (number)
            case 'C':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[73]++; // century of era (number)
            case 'x':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[74]++; // weekyear (number)
            case 'y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[75]++; // year (number)
            case 'Y':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[76]++; // year of era (number)
            case 'd':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[77]++; // day of month (number)
            case 'h':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[78]++; // hour of day (number, 1..12)
            case 'H':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[79]++; // hour of day (number, 0..23)
            case 'm':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[80]++; // minute of hour (number)
            case 's':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[81]++; // second of minute (number)
            case 'S':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[82]++; // fraction of second (number)
            case 'e':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[83]++; // day of week (number)
            case 'D':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[84]++; // day of year (number)
            case 'F':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[85]++; // day of week in month (number)
            case 'w':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[86]++; // week of year (number)
            case 'W':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[87]++; // week of month (number)
            case 'k':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[88]++; // hour of day (1..24)
            case 'K':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[89]++; // hour of day (0..11)
                return true;
            case 'M':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[90]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[136]++;
int CodeCoverConditionCoverageHelper_C24; // month of year (text and number)
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((tokenLen <= 2) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[91]++;
                    return true;

                } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[92]++;} default : CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[93]++;
            }

        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[71]++;}
            
        return false;
    }

    //-----------------------------------------------------------------------
    /**
     * Select a format from a custom pattern.
     *
     * @param pattern  pattern specification
     * @throws IllegalArgumentException if the pattern is invalid
     * @see #appendPatternTo
     */
    private static DateTimeFormatter createFormatterForPattern(String pattern) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[137]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((pattern.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[94]++;
            throw new IllegalArgumentException("Invalid pattern specification");

        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[95]++;}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[138]++;
        DateTimeFormatter formatter = null;
        synchronized (cPatternedCache) {
            formatter = cPatternedCache.get(pattern);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[139]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[140]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[96]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[141]++;
                DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
                parsePatternTo(builder, pattern);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[142]++;
                formatter = builder.toFormatter();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[143]++;

                cPatternedCache.put(pattern, formatter);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[144]++;

            } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[97]++;}
        }
        return formatter;
    }

    /**
     * Select a format from a two character style pattern. The first character
     * is the date style, and the second character is the time style. Specify a
     * character of 'S' for short style, 'M' for medium, 'L' for long, and 'F'
     * for full. A date or time may be ommitted by specifying a style character '-'.
     *
     * @param style  two characters from the set {"S", "M", "L", "F", "-"}
     * @throws IllegalArgumentException if the style is invalid
     */
    private static DateTimeFormatter createFormatterForStyle(String style) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[145]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((style == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((style.length() != 2) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[98]++;
            throw new IllegalArgumentException("Invalid style specification: " + style);

        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[99]++;}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[146]++;
        int dateStyle = selectStyle(style.charAt(0));
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[147]++;
        int timeStyle = selectStyle(style.charAt(1));
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[148]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((dateStyle == NONE) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((timeStyle == NONE) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[100]++;
            throw new IllegalArgumentException("Style '--' is invalid");

        } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[101]++;}
        return createFormatterForStyleIndex(dateStyle, timeStyle);
    }

    /**
     * Gets the formatter for the specified style.
     * 
     * @param dateStyle  the date style
     * @param timeStyle  the time style
     * @return the formatter
     */
    private static DateTimeFormatter createFormatterForStyleIndex(int dateStyle, int timeStyle) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[149]++;
        int index = ((dateStyle << 2) + dateStyle) + timeStyle;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[150]++;
        DateTimeFormatter f = null;
        synchronized (cStyleCache) {
            f = cStyleCache[index];
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[151]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[152]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[102]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[153]++;
                int type = DATETIME;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[154]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((dateStyle == NONE) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[104]++;
                    type = TIME;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[155]++;

                } else {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[105]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[156]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((timeStyle == NONE) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[106]++;
                    type = DATE;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[157]++;

                } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[107]++;}
}
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[158]++;
                StyleFormatter llf = new StyleFormatter(
                        dateStyle, timeStyle, type);
                f = new DateTimeFormatter(llf, llf);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[159]++;
                cStyleCache[index] = f;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[160]++;

            } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[103]++;}
        }
        return f;
    }

    /**
     * Gets the JDK style code from the Joda code.
     * 
     * @param ch  the Joda style code
     * @return the JDK style code
     */
    private static int selectStyle(char ch) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[161]++;
        switch (ch) {
        case 'S':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[108]++;
            return SHORT;
        case 'M':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[109]++;
            return MEDIUM;
        case 'L':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[110]++;
            return LONG;
        case 'F':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[111]++;
            return FULL;
        case '-':
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[112]++;
            return NONE;
        default:
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[113]++;
            throw new IllegalArgumentException("Invalid style character: " + ch);
        }
    }

    //-----------------------------------------------------------------------
    static class StyleFormatter
            implements DateTimePrinter, DateTimeParser {

        private static final Map<String, DateTimeFormatter> cCache = new HashMap<String, DateTimeFormatter>();  // manual sync
        
        private final int iDateStyle;
        private final int iTimeStyle;
        private final int iType;

        StyleFormatter(int dateStyle, int timeStyle, int type) {
            super();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[162]++;
            iDateStyle = dateStyle;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[163]++;
            iTimeStyle = timeStyle;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[164]++;
            iType = type;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[165]++;
        }

        public int estimatePrintedLength() {
            return 40;  // guess
        }

        public void printTo(
                StringBuffer buf, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[166]++;
            DateTimePrinter p = getFormatter(locale).getPrinter();
            p.printTo(buf, instant, chrono, displayOffset, displayZone, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[167]++;
        }

        public void printTo(
                Writer out, long instant, Chronology chrono,
                int displayOffset, DateTimeZone displayZone, Locale locale) throws IOException {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[168]++;
            DateTimePrinter p = getFormatter(locale).getPrinter();
            p.printTo(out, instant, chrono, displayOffset, displayZone, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[169]++;
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[170]++;
            DateTimePrinter p = getFormatter(locale).getPrinter();
            p.printTo(buf, partial, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[171]++;
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[172]++;
            DateTimePrinter p = getFormatter(locale).getPrinter();
            p.printTo(out, partial, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[173]++;
        }

        public int estimateParsedLength() {
            return 40;  // guess
        }

        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[174]++;
            DateTimeParser p = getFormatter(bucket.getLocale()).getParser();
            return p.parseInto(bucket, text, position);
        }

        private DateTimeFormatter getFormatter(Locale locale) {
            locale = (locale == null ? Locale.getDefault() : locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[175]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[176]++;
            String key = Integer.toString(iType + (iDateStyle << 4) + (iTimeStyle << 8)) + locale.toString();
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[177]++;
            DateTimeFormatter f = null;
            synchronized (cCache) {
                f = cCache.get(key);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[178]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[179]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[114]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[180]++;
                    String pattern = getPattern(locale);
                    f = DateTimeFormat.forPattern(pattern);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[181]++;
                    cCache.put(key, f);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[182]++;

                } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[115]++;}
            }
            return f;
        }

        String getPattern(Locale locale) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[183]++;
            DateFormat f = null;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[184]++;
            switch (iType) {
                case DATE:
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[116]++;
                    f = DateFormat.getDateInstance(iDateStyle, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[185]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[186]++;
                    break;
                case TIME:
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[117]++;
                    f = DateFormat.getTimeInstance(iTimeStyle, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[187]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[188]++;
                    break;
                case DATETIME:
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[118]++;
                    f = DateFormat.getDateTimeInstance(iDateStyle, iTimeStyle, locale);
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[189]++;
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[190]++;
                    break; default : CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[119]++;
            }
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.statements[191]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((f instanceof SimpleDateFormat == false) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[120]++;
                throw new IllegalArgumentException("No datetime pattern for locale: " + locale);

            } else {
  CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h.branches[121]++;}
            return ((SimpleDateFormat) f).toPattern();
        }
    }

}

class CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h ());
  }
    public static long[] statements = new long[192];
    public static long[] branches = new long[122];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "org.joda.time.format.DateTimeFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,2,3,1,1,2,1,2,2,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$14fqtf8ank13oisnm3z9byupkl0j8h () {
    super("org.joda.time.format.DateTimeFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 191; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 121; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.DateTimeFormat.java");
      for (int i = 1; i <= 191; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 121; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

