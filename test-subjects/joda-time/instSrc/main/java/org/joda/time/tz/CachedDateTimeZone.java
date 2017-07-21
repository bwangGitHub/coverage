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
package org.joda.time.tz;

import org.joda.time.DateTimeZone;

/**
 * Improves the performance of requesting time zone offsets and name keys by
 * caching the results. Time zones that have simple rules or are fixed should
 * not be cached, as it is unlikely to improve performance.
 * <p>
 * CachedDateTimeZone is thread-safe and immutable.
 * 
 * @author Brian S O'Neill
 * @since 1.0
 */
public class CachedDateTimeZone extends DateTimeZone {
  static {
    CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.ping();
  }


    private static final long serialVersionUID = 5472298452022250685L;
  static {
    CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[1]++;
  }

    private static final int cInfoCacheMask;

    static {
        Integer i;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[2]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            i = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[3]++;
        } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[2]++;
            i = null;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[4]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[1]++;
}
  }

        int cacheSize;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[3]++;
            // With a cache size of 512, dates that lie within any 69.7 year
            // period have no cache collisions.
            cacheSize = 512;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[6]++;
 // (1 << 9)
        } else {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[4]++;
            cacheSize = i.intValue();
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[7]++;
            // Ensure cache size is even power of 2.
            cacheSize--;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[8]++;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[9]++;
            int shift = 0;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
            while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cacheSize > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[1]--;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[2]--;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[3]++;
}
                shift++;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[11]++;
                cacheSize >>= 1;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[12]++;
            }
            cacheSize = 1 << shift;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[13]++;
        }

        cInfoCacheMask = cacheSize - 1;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[14]++;
    }

    /**
     * Returns a new CachedDateTimeZone unless given zone is already cached.
     */
    public static CachedDateTimeZone forZone(DateTimeZone zone) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone instanceof CachedDateTimeZone) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[5]++;
            return (CachedDateTimeZone)zone;

        } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[6]++;}
        return new CachedDateTimeZone(zone);
    }

    /*
     * Caching is performed by breaking timeline down into periods of 2^32
     * milliseconds, or about 49.7 days. A year has about 7.3 periods, usually
     * with only 2 time zone offset periods. Most of the 49.7 day periods will
     * have no transition, about one quarter have one transition, and very rare
     * cases have multiple transitions.
     */

    private final DateTimeZone iZone;

    private transient Info[] iInfoCache;

    private CachedDateTimeZone(DateTimeZone zone) {
        super(zone.getID());
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[16]++;
        iZone = zone;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[17]++;
        iInfoCache = new Info[cInfoCacheMask + 1];
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[18]++;
    }

    private void readObject(java.io.ObjectInputStream in)
        throws java.io.IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[19]++;
        iInfoCache = new Info[cInfoCacheMask + 1];
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[20]++;
    }

    /**
     * Returns the DateTimeZone being wrapped.
     */
    public DateTimeZone getUncachedZone() {
        return iZone;
    }

    public String getNameKey(long instant) {
        return getInfo(instant).getNameKey(instant);
    }

    public int getOffset(long instant) {
        return getInfo(instant).getOffset(instant);
    }

    public int getStandardOffset(long instant) {
        return getInfo(instant).getStandardOffset(instant);
    }

    public boolean isFixed() {
        return iZone.isFixed();
    }

    public long nextTransition(long instant) {
        return iZone.nextTransition(instant);
    }

    public long previousTransition(long instant) {
        return iZone.previousTransition(instant);
    }

    public int hashCode() {
        return iZone.hashCode();
    }

    public boolean equals(Object obj) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[8]++;}
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof CachedDateTimeZone) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[9]++;
            return iZone.equals(((CachedDateTimeZone)obj).iZone);

        } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[10]++;}
        return false;
    }

    // Although accessed by multiple threads, this method doesn't need to be
    // synchronized.

    private Info getInfo(long millis) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[23]++;
        int period = (int)(millis >> 32);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[24]++;
        Info[] cache = iInfoCache;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[25]++;
        int index = period & cInfoCacheMask;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[26]++;
        Info info = cache[index];
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((int)((info.iPeriodStart >> 32)) != period) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[11]++;
            info = createInfo(millis);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[28]++;
            cache[index] = info;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[29]++;

        } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[12]++;}
        return info;
    }

    private Info createInfo(long millis) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[30]++;
        long periodStart = millis & (0xffffffffL << 32);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[31]++;
        Info info = new Info(iZone, periodStart);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[32]++;
        
        long end = periodStart | 0xffffffffL;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[33]++;
        Info chain = info;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[4]++;


        while (true) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[4]--;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[5]--;
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.loops[6]++;
}
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[35]++;
            long next = iZone.nextTransition(periodStart);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((next == periodStart) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((next > end) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[13]++;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[37]++;
                break;

            } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[14]++;}
            periodStart = next;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[38]++;
            chain = (chain.iNextInfo = new Info(iZone, periodStart));
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[39]++;
        }

        return info;
    }

    private final static class Info {
        // For first Info in chain, iPeriodStart's lower 32 bits are clear.
        public final long iPeriodStart;
        public final DateTimeZone iZoneRef;

        Info iNextInfo;

        private String iNameKey;
        private int iOffset = Integer.MIN_VALUE;
  {
    CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[40]++;
  }
        private int iStandardOffset = Integer.MIN_VALUE;
  {
    CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[41]++;
  }

        Info(DateTimeZone zone, long periodStart) {
            iPeriodStart = periodStart;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[42]++;
            iZoneRef = zone;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[43]++;
        }

        public String getNameKey(long millis) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((iNextInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((millis < iNextInfo.iPeriodStart) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[15]++;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((iNameKey == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[17]++;
                    iNameKey = iZoneRef.getNameKey(iPeriodStart);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[46]++;

                } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[18]++;}
                return iNameKey;

            } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[16]++;}
            return iNextInfo.getNameKey(millis);
        }

        public int getOffset(long millis) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((iNextInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((millis < iNextInfo.iPeriodStart) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[19]++;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((iOffset == Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[21]++;
                    iOffset = iZoneRef.getOffset(iPeriodStart);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[49]++;

                } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[22]++;}
                return iOffset;

            } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[20]++;}
            return iNextInfo.getOffset(millis);
        }

        public int getStandardOffset(long millis) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((iNextInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((millis < iNextInfo.iPeriodStart) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[23]++;
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((iStandardOffset == Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[25]++;
                    iStandardOffset = iZoneRef.getStandardOffset(iPeriodStart);
CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.statements[52]++;

                } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[26]++;}
                return iStandardOffset;

            } else {
  CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5.branches[24]++;}
            return iNextInfo.getStandardOffset(millis);
        }
    }
}

class CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5 ());
  }
    public static long[] statements = new long[53];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.joda.time.tz.CachedDateTimeZone.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,0,2,2,1,2,1,2,1};
    for (int i = 1; i <= 14; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$26maboq9ja7ltchu6phvqu8bhkm1f1sihen5 () {
    super("org.joda.time.tz.CachedDateTimeZone.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 52; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.tz.CachedDateTimeZone.java");
      for (int i = 1; i <= 52; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

