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
package org.joda.time.chrono;

import java.lang.ref.WeakReference;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.TreeMap;
import java.util.WeakHashMap;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.IllegalFieldValueException;

/**
 * Utility class used by a few of the GJDateTimeFields.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
class GJLocaleSymbols {
  static {
    CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.ping();
  }

    private static final int FAST_CACHE_SIZE = 64;
  static {
    CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[1]++;
  }

    private static final GJLocaleSymbols[] cFastCache = new GJLocaleSymbols[FAST_CACHE_SIZE];
  static {
    CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[2]++;
  }

    private static WeakHashMap<Locale, GJLocaleSymbols> cCache = new WeakHashMap<Locale, GJLocaleSymbols>();
  static {
    CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[3]++;
  }

    public static GJLocaleSymbols forLocale(Locale locale) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[1]++;
            locale = Locale.getDefault();
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[5]++;

        } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[2]++;}
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[6]++;
        int index = System.identityHashCode(locale) & (FAST_CACHE_SIZE - 1);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[7]++;
        GJLocaleSymbols symbols = cFastCache[index];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((symbols != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((symbols.iLocale.get() == locale) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[3]++;
            return symbols;

        } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[4]++;}
        synchronized (cCache) {
            symbols = cCache.get(locale);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[9]++;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((symbols == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[5]++;
                symbols = new GJLocaleSymbols(locale);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[11]++;
                cCache.put(locale, symbols);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[12]++;

            } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[6]++;}
        }
        cFastCache[index] = symbols;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[13]++;
        return symbols;
    }

    private static String[] realignMonths(String[] months) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[14]++;
        String[] a = new String[13];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int i=1;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i<13) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[1]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[2]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[3]++;
}
            a[i] = months[i - 1];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[16]++;
        }
        return a;
    }

    private static String[] realignDaysOfWeek(String[] daysOfWeek) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[17]++;
        String[] a = new String[8];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i=1;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i<8) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[4]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[5]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[6]++;
}
            a[i] = daysOfWeek[(i < 7) ? i + 1 : 1];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[19]++;
        }
        return a;
    }

    private static void addSymbols(TreeMap<String, Integer> map, String[] symbols, Integer[] integers) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[20]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i=symbols.length;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[7]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[8]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[9]++;
}
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[21]++;
            String symbol = symbols[i];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((symbol != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[7]++;
                map.put(symbol, integers[i]);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[23]++;

            } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[8]++;}
        }
    }

    private static void addNumerals(TreeMap<String, Integer> map, int start, int end, Integer[] integers) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[24]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i=start;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i<=end) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[10]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[11]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[12]++;
}
            map.put(String.valueOf(i).intern(), integers[i]);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[25]++;
        }
    }

    private static int maxLength(String[] a) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[26]++;
        int max = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[27]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[13]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i=a.length;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); ) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[13]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[14]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[15]++;
}
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[28]++;
            String s = a[i];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[9]++;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[30]++;
                int len = s.length();
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((len > max) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[11]++;
                    max = len;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[32]++;

                } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[12]++;}

            } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[10]++;}
        }
        return max;
    }

    private final WeakReference<Locale> iLocale;

    private final String[] iEras;
    private final String[] iDaysOfWeek;
    private final String[] iShortDaysOfWeek;
    private final String[] iMonths;
    private final String[] iShortMonths;
    private final String[] iHalfday;

    private final TreeMap<String, Integer> iParseEras;
    private final TreeMap<String, Integer> iParseDaysOfWeek;
    private final TreeMap<String, Integer> iParseMonths;

    private final int iMaxEraLength;
    private final int iMaxDayOfWeekLength;
    private final int iMaxShortDayOfWeekLength;
    private final int iMaxMonthLength;
    private final int iMaxShortMonthLength;
    private final int iMaxHalfdayLength;

    /**
     * @param locale must not be null
     */
    private GJLocaleSymbols(Locale locale) {
        iLocale = new WeakReference<Locale>(locale);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[33]++;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[34]++;
        
        DateFormatSymbols dfs = DateTimeUtils.getDateFormatSymbols(locale);
        
        iEras = dfs.getEras();
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[35]++;
        iDaysOfWeek = realignDaysOfWeek(dfs.getWeekdays());
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[36]++;
        iShortDaysOfWeek = realignDaysOfWeek(dfs.getShortWeekdays());
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[37]++;
        iMonths = realignMonths(dfs.getMonths());
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[38]++;
        iShortMonths = realignMonths(dfs.getShortMonths());
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[39]++;
        iHalfday = dfs.getAmPmStrings();
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[40]++;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[41]++;

        Integer[] integers = new Integer[13];
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[42]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i<13) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[16]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[17]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[18]++;
}
            integers[i] = Integer.valueOf(i);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[43]++;
        }

        iParseEras = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[44]++;
        addSymbols(iParseEras, iEras, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[45]++;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[46]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 (("en".equals(locale.getLanguage())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[13]++;
            // Include support for parsing "BCE" and "CE" if the language is
            // English. At some point Joda-Time will need an independent set of
            // localized symbols and not depend on java.text.DateFormatSymbols.
            iParseEras.put("BCE", integers[0]);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[47]++;
            iParseEras.put("CE", integers[1]);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[48]++;

        } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[14]++;}

        iParseDaysOfWeek = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[49]++;
        addSymbols(iParseDaysOfWeek, iDaysOfWeek, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[50]++;
        addSymbols(iParseDaysOfWeek, iShortDaysOfWeek, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[51]++;
        addNumerals(iParseDaysOfWeek, 1, 7, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[52]++;

        iParseMonths = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[53]++;
        addSymbols(iParseMonths, iMonths, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[54]++;
        addSymbols(iParseMonths, iShortMonths, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[55]++;
        addNumerals(iParseMonths, 1, 12, integers);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[56]++;

        iMaxEraLength = maxLength(iEras);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[57]++;
        iMaxDayOfWeekLength = maxLength(iDaysOfWeek);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[58]++;
        iMaxShortDayOfWeekLength = maxLength(iShortDaysOfWeek);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[59]++;
        iMaxMonthLength = maxLength(iMonths);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[60]++;
        iMaxShortMonthLength = maxLength(iShortMonths);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[61]++;
        iMaxHalfdayLength = maxLength(iHalfday);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[62]++;
    }

    public String eraValueToText(int value) {
        return iEras[value];
    }

    public int eraTextToValue(String text) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[63]++;
        Integer era = iParseEras.get(text);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((era != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[15]++;
            return era.intValue();

        } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[16]++;}
        throw new IllegalFieldValueException(DateTimeFieldType.era(), text);
    }

    public int getEraMaxTextLength() {
        return iMaxEraLength;
    }

    public String monthOfYearValueToText(int value) {
        return iMonths[value];
    }

    public String monthOfYearValueToShortText(int value) {
        return iShortMonths[value];
    }

    public int monthOfYearTextToValue(String text) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[65]++;
        Integer month = iParseMonths.get(text);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((month != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[17]++;
            return month.intValue();

        } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[18]++;}
        throw new IllegalFieldValueException(DateTimeFieldType.monthOfYear(), text);
    }

    public int getMonthMaxTextLength() {
        return iMaxMonthLength;
    }

    public int getMonthMaxShortTextLength() {
        return iMaxShortMonthLength;
    }

    public String dayOfWeekValueToText(int value) {
        return iDaysOfWeek[value];
    }

    public String dayOfWeekValueToShortText(int value) {
        return iShortDaysOfWeek[value];
    }

    public int dayOfWeekTextToValue(String text) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[67]++;
        Integer day = iParseDaysOfWeek.get(text);
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((day != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[19]++;
            return day.intValue();

        } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[20]++;}
        throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(), text);
    }

    public int getDayOfWeekMaxTextLength() {
        return iMaxDayOfWeekLength;
    }

    public int getDayOfWeekMaxShortTextLength() {
        return iMaxShortDayOfWeekLength;
    }

    public String halfdayValueToText(int value) {
        return iHalfday[value];
    }

    public int halfdayTextToValue(String text) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[69]++;
        String[] halfday = iHalfday;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[70]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[19]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int i = halfday.length;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); ) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[19]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[20]--;
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.loops[21]++;
}
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.statements[71]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((halfday[i].equalsIgnoreCase(text)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[21]++;
                return i;

            } else {
  CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt.branches[22]++;}
        }
        throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(), text);
    }

    public int getHalfdayMaxTextLength() {
        return iMaxHalfdayLength;
    }
}

class CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt ());
  }
    public static long[] statements = new long[72];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.GJLocaleSymbols.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$8bsfk3kredejr5ka4vfkt3kc16cs3tt () {
    super("org.joda.time.chrono.GJLocaleSymbols.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 71; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.GJLocaleSymbols.java");
      for (int i = 1; i <= 71; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

