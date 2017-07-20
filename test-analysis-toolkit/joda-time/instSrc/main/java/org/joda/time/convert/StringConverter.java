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

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * StringConverter converts from a String to an instant, partial,
 * duration, period or interval..
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
class StringConverter extends AbstractConverter
        implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter {
  static {
    CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.ping();
  }


    /**
     * Singleton instance.
     */
    static final StringConverter INSTANCE = new StringConverter();
  static {
    CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected StringConverter() {
        super();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the millis, which is the ISO parsed string value.
     * 
     * @param object  the String to convert, must not be null
     * @param chrono  the chronology to use, non-null result of getChronology
     * @return the millisecond value
     * @throws IllegalArgumentException if the value if invalid
     */
    public long getInstantMillis(Object object, Chronology chrono) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[3]++;
        String str = (String) object;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[4]++;
        DateTimeFormatter p = ISODateTimeFormat.dateTimeParser();
        return p.withChronology(chrono).parseMillis(str);
    }

    /**
     * Extracts the values of the partial from an object of this converter's type.
     * This method checks if the parser has a zone, and uses it if present.
     * This is most useful for parsing local times with UTC.
     * 
     * @param fieldSource  a partial that provides access to the fields.
     *  This partial may be incomplete and only getFieldType(int) should be used
     * @param object  the object to convert
     * @param chrono  the chronology to use, which is the non-null result of getChronology()
     * @param parser the parser to use, may be null
     * @return the array of field values that match the fieldSource, must be non-null valid
     * @throws ClassCastException if the object is invalid
     * @throws IllegalArgumentException if the value if invalid
     * @since 1.3
     */
    public int[] getPartialValues(ReadablePartial fieldSource, Object object, Chronology chrono, DateTimeFormatter parser) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parser.getZone() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[1]++;
            chrono = chrono.withZone(parser.getZone());
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[6]++;

        } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[2]++;}
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[7]++;
        long millis = parser.withChronology(chrono).parseMillis((String) object);
        return chrono.get(fieldSource, millis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration of the string using the standard type.
     * This matches the toString() method of ReadableDuration.
     * 
     * @param object  the String to convert, must not be null
     * @throws ClassCastException if the object is invalid
     */
    public long getDurationMillis(Object object) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[8]++;
        // parse here because duration could be bigger than the int supported
        // by the period parser
        String original = (String) object;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[9]++;
        String str = original;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[10]++;
        int len = str.length();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8192)) == 0 || true) &&
 ((len >= 4) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4096)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C2 |= (2048)) == 0 || true) &&
 ((str.charAt(0) == 'P') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (512)) == 0 || true) &&
 ((str.charAt(0) == 'p') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (256)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (128)) == 0 || true) &&
 ((str.charAt(1) == 'T') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((str.charAt(1) == 't') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((str.charAt(len - 1) == 'S') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((str.charAt(len - 1) == 's') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 7) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 7) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[3]++;

            // ok
        } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[4]++;
            throw new IllegalArgumentException("Invalid format: \"" + original + '"');
        }
        str = str.substring(2, len - 1);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[12]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[13]++;
        int dot = -1;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < str.length()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.loops[1]--;
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.loops[2]--;
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.loops[3]++;
}
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((str.charAt(i) >= '0') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((str.charAt(i) <= '9') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((str.charAt(0) == '-') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[5]++;

                // ok
            } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[6]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[16]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((str.charAt(i) == '.') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((dot == -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[7]++;
                // ok
                dot = i;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[17]++;

            } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[8]++;
                throw new IllegalArgumentException("Invalid format: \"" + original + '"');
            }
}
        }
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[18]++;
        long millis = 0, seconds = 0;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dot > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[9]++;
            seconds = Long.parseLong(str.substring(0, dot));
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[20]++;
            str = str.substring(dot + 1);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[21]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((str.length() != 3) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[11]++;
                str = (str + "000").substring(0, 3);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[23]++;

            } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[12]++;}
            millis = Integer.parseInt(str);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[24]++;

        } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[10]++;
            seconds = Long.parseLong(str);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[25]++;
        }
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((seconds < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[13]++;
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(seconds, 1000), -millis);

        } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[14]++;
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(seconds, 1000), millis);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts duration values from an object of this converter's type, and
     * sets them into the given ReadWritableDuration.
     *
     * @param period  period to get modified
     * @param object  the String to convert, must not be null
     * @param chrono  the chronology to use
     * @return the millisecond duration
     * @throws ClassCastException if the object is invalid
     */
    public void setInto(ReadWritablePeriod period, Object object, Chronology chrono) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[27]++;
        String str = (String) object;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[28]++;
        PeriodFormatter parser = ISOPeriodFormat.standard();
        period.clear();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[29]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[30]++;
        int pos = parser.parseInto(period, str, 0);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((pos < str.length()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[15]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((pos < 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[17]++;
                // Parse again to get a better exception thrown.
                parser.withParseType(period.getPeriodType()).parseMutablePeriod(str);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[33]++;

            } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[18]++;}
            throw new IllegalArgumentException("Invalid format: \"" + str + '"');

        } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[16]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the value of the mutable interval from the string.
     * 
     * @param writableInterval  the interval to set
     * @param object  the String to convert, must not be null
     * @param chrono  the chronology to use, may be null
     */
    public void setInto(ReadWritableInterval writableInterval, Object object, Chronology chrono) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[34]++;
        String str = (String) object;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[35]++;

        int separator = str.indexOf('/');
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((separator < 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[19]++;
            throw new IllegalArgumentException("Format requires a '/' separator: " + str);

        } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[20]++;}
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[37]++;

        String leftStr = str.substring(0, separator);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((leftStr.length() <= 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[21]++;
            throw new IllegalArgumentException("Format invalid: " + str);

        } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[22]++;}
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[39]++;
        String rightStr = str.substring(separator + 1);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((rightStr.length() <= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[23]++;
            throw new IllegalArgumentException("Format invalid: " + str);

        } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[24]++;}
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[41]++;

        DateTimeFormatter dateTimeParser = ISODateTimeFormat.dateTimeParser();
        dateTimeParser = dateTimeParser.withChronology(chrono);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[42]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[43]++;
        PeriodFormatter periodParser = ISOPeriodFormat.standard();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[44]++;
        long startInstant = 0, endInstant = 0;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[45]++;
        Period period = null;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[46]++;
        Chronology parsedChrono = null;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[47]++;
        
        // before slash
        char c = leftStr.charAt(0);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[48]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((c == 'P') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c == 'p') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[25]++;
            period = periodParser.withParseType(getPeriodType(leftStr)).parsePeriod(leftStr);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[49]++;

        } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[26]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[50]++;
            DateTime start = dateTimeParser.parseDateTime(leftStr);
            startInstant = start.getMillis();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[51]++;
            parsedChrono = start.getChronology();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[52]++;
        }
        
        // after slash
        c = rightStr.charAt(0);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[53]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((c == 'P') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((c == 'p') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[27]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[55]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((period != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[29]++;
                throw new IllegalArgumentException("Interval composed of two durations: " + str);

            } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[30]++;}
            period = periodParser.withParseType(getPeriodType(rightStr)).parsePeriod(rightStr);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[56]++;
            chrono = (chrono != null ? chrono : parsedChrono);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[57]++;
            endInstant = chrono.add(period, startInstant, 1);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[58]++;

        } else {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[28]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[59]++;
            DateTime end = dateTimeParser.parseDateTime(rightStr);
            endInstant = end.getMillis();
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[60]++;
            parsedChrono = (parsedChrono != null ? parsedChrono : end.getChronology());
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[61]++;
            chrono = (chrono != null ? chrono : parsedChrono);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[62]++;
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((period != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[31]++;
                startInstant = chrono.add(period, endInstant, -1);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[64]++;

            } else {
  CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.branches[32]++;}
        }
        
        writableInterval.setInterval(startInstant, endInstant);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[65]++;
        writableInterval.setChronology(chrono);
CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp.statements[66]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns String.class.
     * 
     * @return String.class
     */
    public Class<?> getSupportedType() {
        return String.class;
    }

}

class CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp ());
  }
    public static long[] statements = new long[67];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.joda.time.convert.StringConverter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,1,3,3,1,1,1,1,1,1,1,1,2,2,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$9qy054i25zjjloszotej180n4ig5fmp () {
    super("org.joda.time.convert.StringConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 66; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.convert.StringConverter.java");
      for (int i = 1; i <= 66; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

