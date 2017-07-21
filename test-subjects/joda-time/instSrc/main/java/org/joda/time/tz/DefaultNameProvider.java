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
package org.joda.time.tz;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTimeUtils;

/**
 * The default name provider acquires localized names from
 * {@link DateFormatSymbols java.text.DateFormatSymbols}.
 * <p>
 * DefaultNameProvider is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class DefaultNameProvider implements NameProvider {
  static {
    CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.ping();
  }

    // locale -> (id -> (nameKey -> [shortName, name]))
    private HashMap<Locale, Map<String, Map<String, Object>>> iByLocaleCache = createCache();
  {
    CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[1]++;
  }

    public DefaultNameProvider() {
    }

    public String getShortName(Locale locale, String id, String nameKey) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[2]++;
        String[] nameSet = getNameSet(locale, id, nameKey);
        return nameSet == null ? null : nameSet[0];
    }
    
    public String getName(Locale locale, String id, String nameKey) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[3]++;
        String[] nameSet = getNameSet(locale, id, nameKey);
        return nameSet == null ? null : nameSet[1];
    }

    private synchronized String[] getNameSet(Locale locale, String id, String nameKey) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((id == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameKey == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[2]++;}
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[5]++;

        Map<String, Map<String, Object>> byIdCache = iByLocaleCache.get(locale);
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((byIdCache == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[3]++;
            iByLocaleCache.put(locale, byIdCache = createCache());
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[7]++;

        } else {
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[4]++;}
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[8]++;

        Map<String, Object> byNameKeyCache = byIdCache.get(id);
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((byNameKeyCache == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[5]++;
            byIdCache.put(id, byNameKeyCache = createCache());
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[10]++;
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[11]++;
            String[][] zoneStrings = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i<zoneStrings.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.loops[1]--;
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.loops[2]--;
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.loops[3]++;
}
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[13]++;
                String[] set = zoneStrings[i];
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((set != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((set.length == 5) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((id.equals(set[0])) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[7]++;
                    byNameKeyCache.put(set[2], new String[] {set[2], set[1]});
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[15]++;
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
                    // need to handle case where summer and winter have the same
                    // abbreviation, such as EST in Australia [1716305]
                    // we handle this by appending "-Summer", cf ZoneInfoCompiler
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((set[2].equals(set[4])) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[9]++;
                        byNameKeyCache.put(set[4] + "-Summer", new String[] {set[4], set[3]});
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[17]++;

                    } else {
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[10]++;
                        byNameKeyCache.put(set[4], new String[] {set[4], set[3]});
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[18]++;
                    }
CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.statements[19]++;
                    break;

                } else {
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[8]++;}
            }

        } else {
  CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup.branches[6]++;}

        return (String[])byNameKeyCache.get(nameKey);
    }

    private HashMap createCache() {
        return new HashMap(7);
    }
}

class CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.joda.time.tz.DefaultNameProvider.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,3,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$frhv3lfhm1vtn4mz6w60oylnef99uwk2wnlup () {
    super("org.joda.time.tz.DefaultNameProvider.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.tz.DefaultNameProvider.java");
      for (int i = 1; i <= 19; i++) {
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
    for (int i = 1; i <= 6; i++) {
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

