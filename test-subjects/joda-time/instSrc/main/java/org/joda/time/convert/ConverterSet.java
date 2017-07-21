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

/**
 * A set of converters, which allows exact converters to be quickly
 * selected. This class is threadsafe because it is (essentially) immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
class ConverterSet {
  static {
    CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.ping();
  }

    private final Converter[] iConverters;

    // A simple immutable hashtable: closed hashing, linear probing, sized
    // power of 2, at least one null slot.
    private Entry[] iSelectEntries;

    ConverterSet(Converter[] converters) {
        // Since this is a package private constructor, we trust ourselves not
        // to alter the array outside this class.
        iConverters = converters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[1]++;
        iSelectEntries = new Entry[1 << 4];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[2]++; // 16
    }

    /**
     * Returns the closest matching converter for the given type, or null if
     * none found.
     *
     * @param type type to select, which may be null
     * @throws IllegalStateException if multiple converters match the type
     * equally well
     */
    Converter select(Class<?> type) throws IllegalStateException {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[3]++;
        // Check the hashtable first.
        Entry[] entries = iSelectEntries;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[4]++;
        int length = entries.length;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[5]++;
        int index = type == null ? 0 : type.hashCode() & (length - 1);

        Entry e;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[1]++;


        // This loop depends on there being at least one null slot.
        while ((e = entries[index]) != null) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[1]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[2]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[3]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((e.iType == type) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[1]++;
                return e.iConverter;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[2]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((++index >= length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[3]++;
                index = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[9]++;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[4]++;}
        }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[10]++;

        // Not found in the hashtable, so do actual work.

        Converter converter = selectSlow(this, type);
        e = new Entry(type, converter);
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[11]++;

        // Save the entry for future selects. This class must be threadsafe,
        // but there is no synchronization. Since the hashtable is being used
        // as a cache, it is okay to destroy existing entries. This isn't
        // likely to occur unless there is a high amount of concurrency. As
        // time goes on, cache updates will occur less often, and the cache
        // will fill with all the necessary entries.

        // Do all updates on a copy: slots in iSelectEntries must not be
        // updated by multiple threads as this can allow all null slots to be
        // consumed.
        entries = (Entry[])entries.clone();
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[12]++;

        // Add new entry.
        entries[index] = e;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[13]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;

        // Verify that at least one null slot exists!
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[4]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[5]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[6]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((entries[i] == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[5]++;
                // Found a null slot, swap in new hashtable.
                iSelectEntries = entries;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[16]++;
                return converter;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[6]++;}
        }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[17]++;

        // Double capacity and re-hash.

        int newLength = length << 1;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[18]++;
        Entry[] newEntries = new Entry[newLength];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[19]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[7]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[8]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[9]++;
}
            e = entries[i];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[20]++;
            type = e.iType;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[21]++;
            index = type == null ? 0 : type.hashCode() & (newLength - 1);
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[22]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[23]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;
            while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((newEntries[index] != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[10]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[11]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[12]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((++index >= newLength) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[7]++;
                    index = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[25]++;

                } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[8]++;}
            }
            newEntries[index] = e;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[26]++;
        }

        // Swap in new hashtable.
        iSelectEntries = newEntries;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[27]++;
        return converter;
    }

    /**
     * Returns the amount of converters in the set.
     */
    int size() {
        return iConverters.length;
    }

    /**
     * Copies all the converters in the set to the given array.
     */
    void copyInto(Converter[] converters) {
        System.arraycopy(iConverters, 0, converters, 0, iConverters.length);
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[28]++;
    }

    /**
     * Returns a copy of this set, with the given converter added. If a
     * matching converter is already in the set, the given converter replaces
     * it. If the converter is exactly the same as one already in the set, the
     * original set is returned.
     *
     * @param converter  converter to add, must not be null
     * @param removed  if not null, element 0 is set to the removed converter
     * @throws NullPointerException if converter is null
     */
    ConverterSet add(Converter converter, Converter[] removed) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[29]++;
        Converter[] converters = iConverters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[30]++;
        int length = converters.length;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[31]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[13]++;


int CodeCoverConditionCoverageHelper_C9;

        for (int i=0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[13]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[14]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[15]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[32]++;
            Converter existing = converters[i];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((converter.equals(existing)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[9]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[34]++;
int CodeCoverConditionCoverageHelper_C11;
                // Already in the set.
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((removed != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[11]++;
                    removed[0] = null;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[35]++;

                } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[12]++;}
                return this;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[10]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
            
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((converter.getSupportedType() == existing.getSupportedType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[13]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[37]++;
                // Replace the converter.
                Converter[] copy = new Converter[length];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[38]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[16]++;


int CodeCoverConditionCoverageHelper_C13;
                    
                for (int j=0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((j<length) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[16]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[17]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[18]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((j != i) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[15]++;
                        copy[j] = converters[j];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[40]++;

                    } else {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[16]++;
                        copy[j] = converter;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[41]++;
                    }
                }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;

                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((removed != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[17]++;
                    removed[0] = existing;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[43]++;

                } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[18]++;}
                return new ConverterSet(copy);

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[14]++;}
        }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[44]++;

        // Not found, so add it.
        Converter[] copy = new Converter[length + 1];
        System.arraycopy(converters, 0, copy, 0, length);
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[45]++;
        copy[length] = converter;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[46]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[47]++;
int CodeCoverConditionCoverageHelper_C16;
        
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((removed != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[19]++;
            removed[0] = null;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[48]++;

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[20]++;}
        return new ConverterSet(copy);
    }

    /**
     * Returns a copy of this set, with the given converter removed. If the
     * converter was not in the set, the original set is returned.
     *
     * @param converter  converter to remove, must not be null
     * @param removed  if not null, element 0 is set to the removed converter
     * @throws NullPointerException if converter is null
     */
    ConverterSet remove(Converter converter, Converter[] removed) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[49]++;
        Converter[] converters = iConverters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[50]++;
        int length = converters.length;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[51]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[19]++;


int CodeCoverConditionCoverageHelper_C17;

        for (int i=0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[19]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[20]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[21]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[52]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((converter.equals(converters[i])) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[21]++;
                return remove(i, removed);

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[22]++;}
        }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[53]++;
int CodeCoverConditionCoverageHelper_C19;

        // Not found.
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((removed != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[23]++;
            removed[0] = null;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[54]++;

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[24]++;}
        return this;
    }

    /**
     * Returns a copy of this set, with the converter at the given index
     * removed.
     *
     * @param index index of converter to remove
     * @param removed if not null, element 0 is set to the removed converter
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    ConverterSet remove(final int index, Converter[] removed) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[55]++;
        Converter[] converters = iConverters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[56]++;
        int length = converters.length;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[57]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index >= length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[25]++;
            throw new IndexOutOfBoundsException();

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[26]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[58]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((removed != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[27]++;
            removed[0] = converters[index];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[59]++;

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[28]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[60]++;

        Converter[] copy = new Converter[length - 1];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[61]++;
                
        int j = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[62]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[22]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[22]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[23]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[24]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[63]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i != index) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[29]++;
                copy[j++] = converters[i];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[64]++;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[30]++;}
        }
        
        return new ConverterSet(copy);
    }

    /**
     * Returns the closest matching converter for the given type, but not very
     * efficiently.
     */
    private static Converter selectSlow(ConverterSet set, Class<?> type) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[65]++;
        Converter[] converters = set.iConverters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[66]++;
        int length = converters.length;
        Converter converter;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[67]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[25]++;


int CodeCoverConditionCoverageHelper_C24;

        for (int i=length;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[25]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[26]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[27]++;
}
            converter = converters[i];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[68]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[69]++;
            Class<?> supportedType = converter.getSupportedType();
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[70]++;
int CodeCoverConditionCoverageHelper_C25;

            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((supportedType == type) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[31]++;
                // Exact match.
                return converter;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[32]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[71]++;
int CodeCoverConditionCoverageHelper_C26;

            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((supportedType == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((supportedType.isAssignableFrom(type)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[33]++;
                // Eliminate the impossible.
                set = set.remove(i, null);
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[72]++;
                converters = set.iConverters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[73]++;
                length = converters.length;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[74]++;

            } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[34]++;}
        }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[75]++;
int CodeCoverConditionCoverageHelper_C27;

        // Haven't found exact match, so check what remains in the set.

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[35]++;
            return null;

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[36]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[76]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((length == 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[37]++;
            // Found the one best match.
            return converters[0];

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[38]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[77]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[28]++;


int CodeCoverConditionCoverageHelper_C29;

        // At this point, there exist multiple potential converters.

        // Eliminate supertypes.
        for (int i=length;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); ) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[28]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[29]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[30]++;
}
            converter = converters[i];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[78]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[79]++;
            Class<?> supportedType = converter.getSupportedType();
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[80]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[31]++;


int CodeCoverConditionCoverageHelper_C30;
            for (int j=length;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((--j>=0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); ) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[31]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[32]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[33]++;
}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[81]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((j != i) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((converters[j].getSupportedType().isAssignableFrom(supportedType)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[39]++;
                    // Eliminate supertype.
                    set = set.remove(j, null);
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[82]++;
                    converters = set.iConverters;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[83]++;
                    length = converters.length;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[84]++;
                    i = length - 1;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[85]++;

                } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[40]++;}
            }
        }
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[86]++;
int CodeCoverConditionCoverageHelper_C32;        
        
        // Check what remains in the set.

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((length == 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[41]++;
            // Found the one best match.
            return converters[0];

        } else {
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.branches[42]++;}
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[87]++;

        // Class c implements a, b {}
        // Converters exist only for a and b. Which is better? Neither.

        StringBuffer msg = new StringBuffer();
        msg.append("Unable to find best converter for type \"");
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[88]++;
        msg.append(type.getName());
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[89]++;
        msg.append("\" from remaining set: ");
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[90]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[91]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[34]++;


int CodeCoverConditionCoverageHelper_C33;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((i<length) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[34]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[35]--;
  CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.loops[36]++;
}
            converter = converters[i];
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[92]++;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[93]++;
            Class<?> supportedType = converter.getSupportedType();

            msg.append(converter.getClass().getName());
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[94]++;
            msg.append('[');
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[95]++;
            msg.append(supportedType == null ? null : supportedType.getName());
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[96]++;
            msg.append("], ");
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[97]++;
        }

        throw new IllegalStateException(msg.toString());
    }

    static class Entry {
        final Class<?> iType;
        final Converter iConverter;

        Entry(Class<?> type, Converter converter) {
            iType = type;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[98]++;
            iConverter = converter;
CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x.statements[99]++;
        }
    }

}

class CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x ());
  }
    public static long[] statements = new long[100];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "org.joda.time.convert.ConverterSet.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,1,1,2,1,1};
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
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$se1but7ylikomopix1gmjd828x () {
    super("org.joda.time.convert.ConverterSet.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 99; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.convert.ConverterSet.java");
      for (int i = 1; i <= 99; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
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
      for (int i = 1; i <= 12; i++) {
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

