/*
 *  Copyright 2001-2010 Stephen Colebourne
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

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.chrono.ISOChronology;

/**
 * DateTimeZoneBuilder allows complex DateTimeZones to be constructed. Since
 * creating a new DateTimeZone this way is a relatively expensive operation,
 * built zones can be written to a file. Reading back the encoded data is a
 * quick operation.
 * <p>
 * DateTimeZoneBuilder itself is mutable and not thread-safe, but the
 * DateTimeZone objects that it builds are thread-safe and immutable.
 * <p>
 * It is intended that {@link ZoneInfoCompiler} be used to read time zone data
 * files, indirectly calling DateTimeZoneBuilder. The following complex
 * example defines the America/Los_Angeles time zone, with all historical
 * transitions:
 * 
 * <pre>
 * DateTimeZone America_Los_Angeles = new DateTimeZoneBuilder()
 *     .addCutover(-2147483648, 'w', 1, 1, 0, false, 0)
 *     .setStandardOffset(-28378000)
 *     .setFixedSavings("LMT", 0)
 *     .addCutover(1883, 'w', 11, 18, 0, false, 43200000)
 *     .setStandardOffset(-28800000)
 *     .addRecurringSavings("PDT", 3600000, 1918, 1919, 'w',  3, -1, 7, false, 7200000)
 *     .addRecurringSavings("PST",       0, 1918, 1919, 'w', 10, -1, 7, false, 7200000)
 *     .addRecurringSavings("PWT", 3600000, 1942, 1942, 'w',  2,  9, 0, false, 7200000)
 *     .addRecurringSavings("PPT", 3600000, 1945, 1945, 'u',  8, 14, 0, false, 82800000)
 *     .addRecurringSavings("PST",       0, 1945, 1945, 'w',  9, 30, 0, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1948, 1948, 'w',  3, 14, 0, false, 7200000)
 *     .addRecurringSavings("PST",       0, 1949, 1949, 'w',  1,  1, 0, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1950, 1966, 'w',  4, -1, 7, false, 7200000)
 *     .addRecurringSavings("PST",       0, 1950, 1961, 'w',  9, -1, 7, false, 7200000)
 *     .addRecurringSavings("PST",       0, 1962, 1966, 'w', 10, -1, 7, false, 7200000)
 *     .addRecurringSavings("PST",       0, 1967, 2147483647, 'w', 10, -1, 7, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1967, 1973, 'w', 4, -1,  7, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1974, 1974, 'w', 1,  6,  0, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1975, 1975, 'w', 2, 23,  0, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1976, 1986, 'w', 4, -1,  7, false, 7200000)
 *     .addRecurringSavings("PDT", 3600000, 1987, 2147483647, 'w', 4, 1, 7, true, 7200000)
 *     .toDateTimeZone("America/Los_Angeles", true);
 * </pre>
 *
 * @author Brian S O'Neill
 * @see ZoneInfoCompiler
 * @see ZoneInfoProvider
 * @since 1.0
 */
public class DateTimeZoneBuilder {
  static {
    CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.ping();
  }

    /**
     * Decodes a built DateTimeZone from the given stream, as encoded by
     * writeTo.
     *
     * @param in input stream to read encoded DateTimeZone from.
     * @param id time zone id to assign
     */
    public static DateTimeZone readFrom(InputStream in, String id) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((in instanceof DataInput) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[1]++;
            return readFrom((DataInput)in, id);

        } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[2]++;
            return readFrom((DataInput)new DataInputStream(in), id);
        }
    }

    /**
     * Decodes a built DateTimeZone from the given stream, as encoded by
     * writeTo.
     *
     * @param in input stream to read encoded DateTimeZone from.
     * @param id time zone id to assign
     */
    public static DateTimeZone readFrom(DataInput in, String id) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[2]++;
        switch (in.readUnsignedByte()) {
        case 'F':
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[3]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[3]++;
            DateTimeZone fixed = new FixedDateTimeZone
                (id, in.readUTF(), (int)readMillis(in), (int)readMillis(in));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fixed.equals(DateTimeZone.UTC)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[4]++;
                fixed = DateTimeZone.UTC;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[5]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[5]++;}
            return fixed;
        case 'C':
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[6]++;
            return CachedDateTimeZone.forZone(PrecalculatedZone.readFrom(in, id));
        case 'P':
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[7]++;
            return PrecalculatedZone.readFrom(in, id);
        default:
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[8]++;
            throw new IOException("Invalid encoding");
        }
    }

    /**
     * Millisecond encoding formats:
     *
     * upper two bits  units       field length  approximate range
     * ---------------------------------------------------------------
     * 00              30 minutes  1 byte        +/- 16 hours
     * 01              minutes     4 bytes       +/- 1020 years
     * 10              seconds     5 bytes       +/- 4355 years
     * 11              millis      9 bytes       +/- 292,000,000 years
     *
     * Remaining bits in field form signed offset from 1970-01-01T00:00:00Z.
     */
    static void writeMillis(DataOutput out, long millis) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((millis % (30 * 60000L) == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[9]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[7]++;
            // Try to write in 30 minute units.
            long units = millis / (30 * 60000L);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((((units << (64 - 6)) >> (64 - 6)) == units) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[11]++;
                // Form 00 (6 bits effective precision)
                out.writeByte((int)(units & 0x3f));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[9]++;
                return;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[10]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((millis % 60000L == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[13]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[11]++;
            // Try to write minutes.
            long minutes = millis / 60000L;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((((minutes << (64 - 30)) >> (64 - 30)) == minutes) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[15]++;
                // Form 01 (30 bits effective precision)
                out.writeInt(0x40000000 | (int)(minutes & 0x3fffffff));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[13]++;
                return;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[14]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
        
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((millis % 1000L == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[17]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[15]++;
            // Try to write seconds.
            long seconds = millis / 1000L;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((((seconds << (64 - 38)) >> (64 - 38)) == seconds) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[19]++;
                // Form 10 (38 bits effective precision)
                out.writeByte(0x80 | (int)((seconds >> 32) & 0x3f));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[17]++;
                out.writeInt((int)(seconds & 0xffffffff));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[18]++;
                return;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[20]++;}

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[18]++;}

        // Write milliseconds either because the additional precision is
        // required or the minutes didn't fit in the field.
        
        // Form 11 (64 bits effective precision, but write as if 70 bits)
        out.writeByte(millis < 0 ? 0xff : 0xc0);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[19]++;
        out.writeLong(millis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[20]++;
    }

    /**
     * Reads encoding generated by writeMillis.
     */
    static long readMillis(DataInput in) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[21]++;
        int v = in.readUnsignedByte();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[22]++;
        switch (v >> 6) {
        case 0:
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[21]++; default:
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[22]++;
            // Form 00 (6 bits effective precision)
            v = (v << (32 - 6)) >> (32 - 6);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[23]++;
            return v * (30 * 60000L);

        case 1:
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[23]++;
            // Form 01 (30 bits effective precision)
            v = (v << (32 - 6)) >> (32 - 30);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[24]++;
            v |= (in.readUnsignedByte()) << 16;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[25]++;
            v |= (in.readUnsignedByte()) << 8;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[26]++;
            v |= (in.readUnsignedByte());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[27]++;
            return v * 60000L;

        case 2:
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[24]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[28]++;
            // Form 10 (38 bits effective precision)
            long w = (((long)v) << (64 - 6)) >> (64 - 38);
            w |= (in.readUnsignedByte()) << 24;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[29]++;
            w |= (in.readUnsignedByte()) << 16;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[30]++;
            w |= (in.readUnsignedByte()) << 8;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[31]++;
            w |= (in.readUnsignedByte());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[32]++;
            return w * 1000L;

        case 3:
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[25]++;
            // Form 11 (64 bits effective precision)
            return in.readLong();
        }
    }

    private static DateTimeZone buildFixedZone(String id, String nameKey,
                                               int wallOffset, int standardOffset) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 (("UTC".equals(id)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((id.equals(nameKey)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((wallOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((standardOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[26]++;
            return DateTimeZone.UTC;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[27]++;}
        return new FixedDateTimeZone(id, nameKey, wallOffset, standardOffset);
    }

    // List of RuleSets.
    private final ArrayList<RuleSet> iRuleSets;

    public DateTimeZoneBuilder() {
        iRuleSets = new ArrayList<RuleSet>(10);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[34]++;
    }

    /**
     * Adds a cutover for added rules. The standard offset at the cutover
     * defaults to 0. Call setStandardOffset afterwards to change it.
     *
     * @param year  the year of cutover
     * @param mode 'u' - cutover is measured against UTC, 'w' - against wall
     *  offset, 's' - against standard offset
     * @param monthOfYear  the month from 1 (January) to 12 (December)
     * @param dayOfMonth  if negative, set to ((last day of month) - ~dayOfMonth).
     *  For example, if -1, set to last day of month
     * @param dayOfWeek  from 1 (Monday) to 7 (Sunday), if 0 then ignore
     * @param advanceDayOfWeek  if dayOfMonth does not fall on dayOfWeek, advance to
     *  dayOfWeek when true, retreat when false.
     * @param millisOfDay  additional precision for specifying time of day of cutover
     */
    public DateTimeZoneBuilder addCutover(int year,
                                          char mode,
                                          int monthOfYear,
                                          int dayOfMonth,
                                          int dayOfWeek,
                                          boolean advanceDayOfWeek,
                                          int millisOfDay)
    {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((iRuleSets.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[28]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[36]++;
            OfYear ofYear = new OfYear
                (mode, monthOfYear, dayOfMonth, dayOfWeek, advanceDayOfWeek, millisOfDay);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[37]++;
            RuleSet lastRuleSet = iRuleSets.get(iRuleSets.size() - 1);
            lastRuleSet.setUpperLimit(year, ofYear);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[38]++;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[29]++;}
        iRuleSets.add(new RuleSet());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[39]++;
        return this;
    }

    /**
     * Sets the standard offset to use for newly added rules until the next
     * cutover is added.
     * @param standardOffset  the standard offset in millis
     */
    public DateTimeZoneBuilder setStandardOffset(int standardOffset) {
        getLastRuleSet().setStandardOffset(standardOffset);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[40]++;
        return this;
    }

    /**
     * Set a fixed savings rule at the cutover.
     */
    public DateTimeZoneBuilder setFixedSavings(String nameKey, int saveMillis) {
        getLastRuleSet().setFixedSavings(nameKey, saveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[41]++;
        return this;
    }

    /**
     * Add a recurring daylight saving time rule.
     *
     * @param nameKey  the name key of new rule
     * @param saveMillis  the milliseconds to add to standard offset
     * @param fromYear  the first year that rule is in effect, MIN_VALUE indicates
     * beginning of time
     * @param toYear  the last year (inclusive) that rule is in effect, MAX_VALUE
     *  indicates end of time
     * @param mode  'u' - transitions are calculated against UTC, 'w' -
     *  transitions are calculated against wall offset, 's' - transitions are
     *  calculated against standard offset
     * @param monthOfYear  the month from 1 (January) to 12 (December)
     * @param dayOfMonth  if negative, set to ((last day of month) - ~dayOfMonth).
     *  For example, if -1, set to last day of month
     * @param dayOfWeek  from 1 (Monday) to 7 (Sunday), if 0 then ignore
     * @param advanceDayOfWeek  if dayOfMonth does not fall on dayOfWeek, advance to
     *  dayOfWeek when true, retreat when false.
     * @param millisOfDay  additional precision for specifying time of day of transitions
     */
    public DateTimeZoneBuilder addRecurringSavings(String nameKey, int saveMillis,
                                                   int fromYear, int toYear,
                                                   char mode,
                                                   int monthOfYear,
                                                   int dayOfMonth,
                                                   int dayOfWeek,
                                                   boolean advanceDayOfWeek,
                                                   int millisOfDay)
    {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((fromYear <= toYear) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[30]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[43]++;
            OfYear ofYear = new OfYear
                (mode, monthOfYear, dayOfMonth, dayOfWeek, advanceDayOfWeek, millisOfDay);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[44]++;
            Recurrence recurrence = new Recurrence(ofYear, nameKey, saveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[45]++;
            Rule rule = new Rule(recurrence, fromYear, toYear);
            getLastRuleSet().addRule(rule);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[46]++;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[31]++;}
        return this;
    }

    private RuleSet getLastRuleSet() {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((iRuleSets.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[32]++;
            addCutover(Integer.MIN_VALUE, 'w', 1, 1, 0, false, 0);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[48]++;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[33]++;}
        return iRuleSets.get(iRuleSets.size() - 1);
    }
    
    /**
     * Processes all the rules and builds a DateTimeZone.
     *
     * @param id  time zone id to assign
     * @param outputID  true if the zone id should be output
     */
    public DateTimeZone toDateTimeZone(String id, boolean outputID) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((id == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[34]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[35]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[50]++;

        // Discover where all the transitions occur and store the results in
        // these lists.
        ArrayList<Transition> transitions = new ArrayList<Transition>();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[51]++;

        // Tail zone picks up remaining transitions in the form of an endless
        // DST cycle.
        DSTZone tailZone = null;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[52]++;

        long millis = Long.MIN_VALUE;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[53]++;
        int saveMillis = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[54]++;
            
        int ruleSetCount = iRuleSets.size();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[55]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i<ruleSetCount) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[1]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[2]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[3]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[56]++;
            RuleSet rs = iRuleSets.get(i);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[57]++;
            Transition next = rs.firstTransition(millis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[58]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((next == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[36]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[59]++;
                continue;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[37]++;}
            addTransition(transitions, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[60]++;
            millis = next.getMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[61]++;
            saveMillis = next.getSaveMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[62]++;

            // Copy it since we're going to destroy it.
            rs = new RuleSet(rs);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[63]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[64]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[4]++;



            while ((next = rs.nextTransition(millis, saveMillis)) != null) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[4]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[5]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[6]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((addTransition(transitions, next)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[38]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[66]++;
int CodeCoverConditionCoverageHelper_C18;
                    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((tailZone != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[40]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[67]++;
                        // Got the extra transition before DSTZone.
                        break;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[41]++;}

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[39]++;}
                millis = next.getMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[68]++;
                saveMillis = next.getSaveMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[69]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[70]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((tailZone == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i == ruleSetCount - 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[42]++;
                    tailZone = rs.buildTailZone(id);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[71]++;

                    // If tailZone is not null, don't break out of main loop until
                    // at least one more transition is calculated. This ensures a
                    // correct 'seam' to the DSTZone.
                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[43]++;}
            }

            millis = rs.getUpperLimit(saveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[72]++;
        }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[73]++;
int CodeCoverConditionCoverageHelper_C20;

        // Check if a simpler zone implementation can be returned.
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((transitions.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[44]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[74]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((tailZone != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[46]++;
                // This shouldn't happen, but handle just in case.
                return tailZone;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[47]++;}
            return buildFixedZone(id, "UTC", 0, 0);

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[45]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[75]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((transitions.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((tailZone == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[48]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[76]++;
            Transition tr = transitions.get(0);
            return buildFixedZone(id, tr.getNameKey(),
                                  tr.getWallOffset(), tr.getStandardOffset());

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[49]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[77]++;

        PrecalculatedZone zone = PrecalculatedZone.create(id, outputID, transitions, tailZone);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[78]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((zone.isCachable()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[50]++;
            return CachedDateTimeZone.forZone(zone);

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[51]++;}
        return zone;
    }

    private boolean addTransition(ArrayList<Transition> transitions, Transition tr) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[79]++;
        int size = transitions.size();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[80]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((size == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[52]++;
            transitions.add(tr);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[81]++;
            return true;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[53]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[82]++;

        Transition last = transitions.get(size - 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[83]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((tr.isTransitionFrom(last)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[54]++;
            return false;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[55]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[84]++;

        // If local time of new transition is same as last local time, just
        // replace last transition with new one.
        int offsetForLast = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[85]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((size >= 2) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[56]++;
            offsetForLast = transitions.get(size - 2).getWallOffset();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[86]++;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[57]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[87]++;
        int offsetForNew = last.getWallOffset();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[88]++;

        long lastLocal = last.getMillis() + offsetForLast;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[89]++;
        long newLocal = tr.getMillis() + offsetForNew;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[90]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((newLocal != lastLocal) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[58]++;
            transitions.add(tr);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[91]++;
            return true;

        } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[59]++;}

        transitions.remove(size - 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[92]++;
        return addTransition(transitions, tr);
    }

    /**
     * Encodes a built DateTimeZone to the given stream. Call readFrom to
     * decode the data into a DateTimeZone object.
     *
     * @param out  the output stream to receive the encoded DateTimeZone
     * @since 1.5 (parameter added)
     */
    public void writeTo(String zoneID, OutputStream out) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[93]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((out instanceof DataOutput) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[60]++;
            writeTo(zoneID, (DataOutput)out);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[94]++;

        } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[61]++;
            writeTo(zoneID, (DataOutput)new DataOutputStream(out));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[95]++;
        }
    }

    /**
     * Encodes a built DateTimeZone to the given stream. Call readFrom to
     * decode the data into a DateTimeZone object.
     *
     * @param out  the output stream to receive the encoded DateTimeZone
     * @since 1.5 (parameter added)
     */
    public void writeTo(String zoneID, DataOutput out) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[96]++;
        // pass false so zone id is not written out
        DateTimeZone zone = toDateTimeZone(zoneID, false);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[97]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((zone instanceof FixedDateTimeZone) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[62]++;
            out.writeByte('F');
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[98]++; // 'F' for fixed
            out.writeUTF(zone.getNameKey(0));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[99]++;
            writeMillis(out, zone.getOffset(0));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[100]++;
            writeMillis(out, zone.getStandardOffset(0));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[101]++;

        } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[63]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[102]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((zone instanceof CachedDateTimeZone) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[64]++;
                out.writeByte('C');
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[103]++; // 'C' for cached, precalculated
                zone = ((CachedDateTimeZone)zone).getUncachedZone();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[104]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[65]++;
                out.writeByte('P');
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[105]++; // 'P' for precalculated, uncached
            }
            ((PrecalculatedZone)zone).writeTo(out);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[106]++;
        }
    }

    /**
     * Supports setting fields of year and moving between transitions.
     */
    private static final class OfYear {
        static OfYear readFrom(DataInput in) throws IOException {
            return new OfYear((char)in.readUnsignedByte(),
                              (int)in.readUnsignedByte(),
                              (int)in.readByte(),
                              (int)in.readUnsignedByte(),
                              in.readBoolean(),
                              (int)readMillis(in));
        }

        // Is 'u', 'w', or 's'.
        final char iMode;

        final int iMonthOfYear;
        final int iDayOfMonth;
        final int iDayOfWeek;
        final boolean iAdvance;
        final int iMillisOfDay;

        OfYear(char mode,
               int monthOfYear,
               int dayOfMonth,
               int dayOfWeek, boolean advanceDayOfWeek,
               int millisOfDay)
        {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[107]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((mode != 'u') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((mode != 'w') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((mode != 's') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[66]++;
                throw new IllegalArgumentException("Unknown mode: " + mode);

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[67]++;}

            iMode = mode;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[108]++;
            iMonthOfYear = monthOfYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[109]++;
            iDayOfMonth = dayOfMonth;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[110]++;
            iDayOfWeek = dayOfWeek;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[111]++;
            iAdvance = advanceDayOfWeek;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[112]++;
            iMillisOfDay = millisOfDay;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[113]++;
        }

        /**
         * @param standardOffset standard offset just before instant
         */
        public long setInstant(int year, int standardOffset, int saveMillis) {
            int offset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[114]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((iMode == 'w') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[68]++;
                offset = standardOffset + saveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[115]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[69]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[116]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((iMode == 's') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[70]++;
                offset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[117]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[71]++;
                offset = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[118]++;
            }
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[119]++;

            Chronology chrono = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[120]++;
            long millis = chrono.year().set(0, year);
            millis = chrono.monthOfYear().set(millis, iMonthOfYear);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[121]++;
            millis = chrono.millisOfDay().set(millis, iMillisOfDay);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[122]++;
            millis = setDayOfMonth(chrono, millis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[123]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[124]++;
int CodeCoverConditionCoverageHelper_C34;

            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((iDayOfWeek != 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[72]++;
                millis = setDayOfWeek(chrono, millis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[125]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[73]++;}

            // Convert from local time to UTC.
            return millis - offset;
        }

        /**
         * @param standardOffset standard offset just before next recurrence
         */
        public long next(long instant, int standardOffset, int saveMillis) {
            int offset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[126]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((iMode == 'w') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[74]++;
                offset = standardOffset + saveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[127]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[75]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[128]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((iMode == 's') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[76]++;
                offset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[129]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[77]++;
                offset = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[130]++;
            }
}

            // Convert from UTC to local time.
            instant += offset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[131]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[132]++;

            Chronology chrono = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[133]++;
            long next = chrono.monthOfYear().set(instant, iMonthOfYear);
            // Be lenient with millisOfDay.
            next = chrono.millisOfDay().set(next, 0);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[134]++;
            next = chrono.millisOfDay().add(next, iMillisOfDay);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[135]++;
            next = setDayOfMonthNext(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[136]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[137]++;
int CodeCoverConditionCoverageHelper_C37;

            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((iDayOfWeek == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[78]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[138]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((next <= instant) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[80]++;
                    next = chrono.year().add(next, 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[139]++;
                    next = setDayOfMonthNext(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[140]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[81]++;}

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[79]++;
                next = setDayOfWeek(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[141]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[142]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((next <= instant) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[82]++;
                    next = chrono.year().add(next, 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[143]++;
                    next = chrono.monthOfYear().set(next, iMonthOfYear);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[144]++;
                    next = setDayOfMonthNext(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[145]++;
                    next = setDayOfWeek(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[146]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[83]++;}
            }

            // Convert from local time to UTC.
            return next - offset;
        }

        /**
         * @param standardOffset standard offset just before previous recurrence
         */
        public long previous(long instant, int standardOffset, int saveMillis) {
            int offset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[147]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((iMode == 'w') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[84]++;
                offset = standardOffset + saveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[148]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[85]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[149]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((iMode == 's') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[86]++;
                offset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[150]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[87]++;
                offset = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[151]++;
            }
}

            // Convert from UTC to local time.
            instant += offset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[152]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[153]++;

            Chronology chrono = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[154]++;
            long prev = chrono.monthOfYear().set(instant, iMonthOfYear);
            // Be lenient with millisOfDay.
            prev = chrono.millisOfDay().set(prev, 0);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[155]++;
            prev = chrono.millisOfDay().add(prev, iMillisOfDay);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[156]++;
            prev = setDayOfMonthPrevious(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[157]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[158]++;
int CodeCoverConditionCoverageHelper_C42;

            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((iDayOfWeek == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[88]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[159]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((prev >= instant) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[90]++;
                    prev = chrono.year().add(prev, -1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[160]++;
                    prev = setDayOfMonthPrevious(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[161]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[91]++;}

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[89]++;
                prev = setDayOfWeek(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[162]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[163]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((prev >= instant) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[92]++;
                    prev = chrono.year().add(prev, -1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[164]++;
                    prev = chrono.monthOfYear().set(prev, iMonthOfYear);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[165]++;
                    prev = setDayOfMonthPrevious(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[166]++;
                    prev = setDayOfWeek(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[167]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[93]++;}
            }

            // Convert from local time to UTC.
            return prev - offset;
        }

        public boolean equals(Object obj) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[168]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[94]++;
                return true;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[95]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[169]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((obj instanceof OfYear) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[96]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[170]++;
                OfYear other = (OfYear)obj;
                return
                    iMode == other.iMode &&
                    iMonthOfYear == other.iMonthOfYear &&
                    iDayOfMonth == other.iDayOfMonth &&
                    iDayOfWeek == other.iDayOfWeek &&
                    iAdvance == other.iAdvance &&
                    iMillisOfDay == other.iMillisOfDay;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[97]++;}
            return false;
        }

        /*
        public String toString() {
            return
                "[OfYear]\n" + 
                "Mode: " + iMode + '\n' +
                "MonthOfYear: " + iMonthOfYear + '\n' +
                "DayOfMonth: " + iDayOfMonth + '\n' +
                "DayOfWeek: " + iDayOfWeek + '\n' +
                "AdvanceDayOfWeek: " + iAdvance + '\n' +
                "MillisOfDay: " + iMillisOfDay + '\n';
        }
        */

        public void writeTo(DataOutput out) throws IOException {
            out.writeByte(iMode);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[171]++;
            out.writeByte(iMonthOfYear);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[172]++;
            out.writeByte(iDayOfMonth);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[173]++;
            out.writeByte(iDayOfWeek);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[174]++;
            out.writeBoolean(iAdvance);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[175]++;
            writeMillis(out, iMillisOfDay);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[176]++;
        }

        /**
         * If month-day is 02-29 and year isn't leap, advances to next leap year.
         */
        private long setDayOfMonthNext(Chronology chrono, long next) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[177]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                next = setDayOfMonth(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[178]++;
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[99]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[179]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((iMonthOfYear == 2) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((iDayOfMonth == 29) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[100]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[180]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[7]++;


int CodeCoverConditionCoverageHelper_C48;
                    while ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((chrono.year().isLeap(next) == false) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[7]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[8]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[9]++;
}
                        next = chrono.year().add(next, 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[181]++;
                    }
                    next = setDayOfMonth(chrono, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[182]++;

                } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[101]++;
                    throw e;
                }
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[98]++;
}
  }
            return next;
        }

        /**
         * If month-day is 02-29 and year isn't leap, retreats to previous leap year.
         */
        private long setDayOfMonthPrevious(Chronology chrono, long prev) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[183]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                prev = setDayOfMonth(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[184]++;
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[103]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[185]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((iMonthOfYear == 2) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((iDayOfMonth == 29) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[104]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[186]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[10]++;


int CodeCoverConditionCoverageHelper_C50;
                    while ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((chrono.year().isLeap(prev) == false) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[10]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[11]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[12]++;
}
                        prev = chrono.year().add(prev, -1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[187]++;
                    }
                    prev = setDayOfMonth(chrono, prev);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[188]++;

                } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[105]++;
                    throw e;
                }
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[102]++;
}
  }
            return prev;
        }

        private long setDayOfMonth(Chronology chrono, long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[189]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((iDayOfMonth >= 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[106]++;
                instant = chrono.dayOfMonth().set(instant, iDayOfMonth);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[190]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[107]++;
                instant = chrono.dayOfMonth().set(instant, 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[191]++;
                instant = chrono.monthOfYear().add(instant, 1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[192]++;
                instant = chrono.dayOfMonth().add(instant, iDayOfMonth);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[193]++;
            }
            return instant;
        }

        private long setDayOfWeek(Chronology chrono, long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[194]++;
            int dayOfWeek = chrono.dayOfWeek().get(instant);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[195]++;
            int daysToAdd = iDayOfWeek - dayOfWeek;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[196]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((daysToAdd != 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[108]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[197]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((iAdvance) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[110]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[198]++;
int CodeCoverConditionCoverageHelper_C54;
                    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((daysToAdd < 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[112]++;
                        daysToAdd += 7;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[199]++;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[113]++;}

                } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[111]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[200]++;
int CodeCoverConditionCoverageHelper_C55;
                    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((daysToAdd > 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[114]++;
                        daysToAdd -= 7;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[201]++;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[115]++;}
                }
                instant = chrono.dayOfWeek().add(instant, daysToAdd);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[202]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[109]++;}
            return instant;
        }
    }

    /**
     * Extends OfYear with a nameKey and savings.
     */
    private static final class Recurrence {
        static Recurrence readFrom(DataInput in) throws IOException {
            return new Recurrence(OfYear.readFrom(in), in.readUTF(), (int)readMillis(in));
        }

        final OfYear iOfYear;
        final String iNameKey;
        final int iSaveMillis;

        Recurrence(OfYear ofYear, String nameKey, int saveMillis) {
            iOfYear = ofYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[203]++;
            iNameKey = nameKey;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[204]++;
            iSaveMillis = saveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[205]++;
        }

        public OfYear getOfYear() {
            return iOfYear;
        }

        /**
         * @param standardOffset standard offset just before next recurrence
         */
        public long next(long instant, int standardOffset, int saveMillis) {
            return iOfYear.next(instant, standardOffset, saveMillis);
        }

        /**
         * @param standardOffset standard offset just before previous recurrence
         */
        public long previous(long instant, int standardOffset, int saveMillis) {
            return iOfYear.previous(instant, standardOffset, saveMillis);
        }

        public String getNameKey() {
            return iNameKey;
        }

        public int getSaveMillis() {
            return iSaveMillis;
        }

        public boolean equals(Object obj) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[206]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[116]++;
                return true;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[117]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[207]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((obj instanceof Recurrence) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[118]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[208]++;
                Recurrence other = (Recurrence)obj;
                return
                    iSaveMillis == other.iSaveMillis &&
                    iNameKey.equals(other.iNameKey) &&
                    iOfYear.equals(other.iOfYear);

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[119]++;}
            return false;
        }

        public void writeTo(DataOutput out) throws IOException {
            iOfYear.writeTo(out);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[209]++;
            out.writeUTF(iNameKey);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[210]++;
            writeMillis(out, iSaveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[211]++;
        }

        Recurrence rename(String nameKey) {
            return new Recurrence(iOfYear, nameKey, iSaveMillis);
        }

        Recurrence renameAppend(String appendNameKey) {
            return rename((iNameKey + appendNameKey).intern());
        }
    }

    /**
     * Extends Recurrence with inclusive year limits.
     */
    private static final class Rule {
        final Recurrence iRecurrence;
        final int iFromYear; // inclusive
        final int iToYear;   // inclusive

        Rule(Recurrence recurrence, int fromYear, int toYear) {
            iRecurrence = recurrence;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[212]++;
            iFromYear = fromYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[213]++;
            iToYear = toYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[214]++;
        }

        public int getFromYear() {
            return iFromYear;
        }

        public int getToYear() {
            return iToYear;
        }

        public OfYear getOfYear() {
            return iRecurrence.getOfYear();
        }

        public String getNameKey() {
            return iRecurrence.getNameKey();
        }

        public int getSaveMillis() {
            return iRecurrence.getSaveMillis();
        }

        public long next(final long instant, int standardOffset, int saveMillis) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[215]++;
            Chronology chrono = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[216]++;

            final int wallOffset = standardOffset + saveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[217]++;
            long testInstant = instant;

            int year;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[218]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((instant == Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[120]++;
                year = Integer.MIN_VALUE;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[219]++;

            } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[121]++;
                year = chrono.year().get(instant + wallOffset);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[220]++;
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[221]++;
int CodeCoverConditionCoverageHelper_C59;

            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((year < iFromYear) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[122]++;
                // First advance instant to start of from year.
                testInstant = chrono.year().set(0, iFromYear) - wallOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[222]++;
                // Back off one millisecond to account for next recurrence
                // being exactly at the beginning of the year.
                testInstant -= 1;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[223]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[123]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[224]++;

            long next = iRecurrence.next(testInstant, standardOffset, saveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[225]++;
int CodeCoverConditionCoverageHelper_C60;

            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((next > instant) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[124]++;
                year = chrono.year().get(next + wallOffset);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[226]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[227]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((year > iToYear) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[126]++;
                    // Out of range, return original value.
                    next = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[228]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[127]++;}

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[125]++;}

            return next;
        }
    }

    private static final class Transition {
        private final long iMillis;
        private final String iNameKey;
        private final int iWallOffset;
        private final int iStandardOffset;

        Transition(long millis, Transition tr) {
            iMillis = millis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[229]++;
            iNameKey = tr.iNameKey;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[230]++;
            iWallOffset = tr.iWallOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[231]++;
            iStandardOffset = tr.iStandardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[232]++;
        }

        Transition(long millis, Rule rule, int standardOffset) {
            iMillis = millis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[233]++;
            iNameKey = rule.getNameKey();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[234]++;
            iWallOffset = standardOffset + rule.getSaveMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[235]++;
            iStandardOffset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[236]++;
        }

        Transition(long millis, String nameKey,
                   int wallOffset, int standardOffset) {
            iMillis = millis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[237]++;
            iNameKey = nameKey;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[238]++;
            iWallOffset = wallOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[239]++;
            iStandardOffset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[240]++;
        }

        public long getMillis() {
            return iMillis;
        }

        public String getNameKey() {
            return iNameKey;
        }

        public int getWallOffset() {
            return iWallOffset;
        }

        public int getStandardOffset() {
            return iStandardOffset;
        }

        public int getSaveMillis() {
            return iWallOffset - iStandardOffset;
        }

        /**
         * There must be a change in the millis, wall offsets or name keys.
         */
        public boolean isTransitionFrom(Transition other) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[241]++;
int CodeCoverConditionCoverageHelper_C62;
            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[128]++;
                return true;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[129]++;}
            return iMillis > other.iMillis &&
                (iWallOffset != other.iWallOffset ||
                 //iStandardOffset != other.iStandardOffset ||
                 !(iNameKey.equals(other.iNameKey)));
        }
    }

    private static final class RuleSet {
        private static final int YEAR_LIMIT;

        static {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[242]++;
            // Don't pre-calculate more than 100 years into the future. Almost
            // all zones will stop pre-calculating far sooner anyhow. Either a
            // simple DST cycle is detected or the last rule is a fixed
            // offset. If a zone has a fixed offset set more than 100 years
            // into the future, then it won't be observed.
            long now = DateTimeUtils.currentTimeMillis();
            YEAR_LIMIT = ISOChronology.getInstanceUTC().year().get(now) + 100;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[243]++;
        }

        private int iStandardOffset;
        private ArrayList<Rule> iRules;

        // Optional.
        private String iInitialNameKey;
        private int iInitialSaveMillis;

        // Upper limit is exclusive.
        private int iUpperYear;
        private OfYear iUpperOfYear;

        RuleSet() {
            iRules = new ArrayList<Rule>(10);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[244]++;
            iUpperYear = Integer.MAX_VALUE;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[245]++;
        }

        /**
         * Copy constructor.
         */
        RuleSet(RuleSet rs) {
            iStandardOffset = rs.iStandardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[246]++;
            iRules = new ArrayList<Rule>(rs.iRules);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[247]++;
            iInitialNameKey = rs.iInitialNameKey;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[248]++;
            iInitialSaveMillis = rs.iInitialSaveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[249]++;
            iUpperYear = rs.iUpperYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[250]++;
            iUpperOfYear = rs.iUpperOfYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[251]++;
        }

        public int getStandardOffset() {
            return iStandardOffset;
        }

        public void setStandardOffset(int standardOffset) {
            iStandardOffset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[252]++;
        }

        public void setFixedSavings(String nameKey, int saveMillis) {
            iInitialNameKey = nameKey;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[253]++;
            iInitialSaveMillis = saveMillis;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[254]++;
        }

        public void addRule(Rule rule) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[255]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((iRules.contains(rule)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[130]++;
                iRules.add(rule);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[256]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[131]++;}
        }

        public void setUpperLimit(int year, OfYear ofYear) {
            iUpperYear = year;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[257]++;
            iUpperOfYear = ofYear;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[258]++;
        }

        /**
         * Returns a transition at firstMillis with the first name key and
         * offsets for this rule set. This method may return null.
         *
         * @param firstMillis millis of first transition
         */
        public Transition firstTransition(final long firstMillis) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[259]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((iInitialNameKey != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[132]++;
                // Initial zone info explicitly set, so don't search the rules.
                return new Transition(firstMillis, iInitialNameKey,
                                      iStandardOffset + iInitialSaveMillis, iStandardOffset);

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[133]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[260]++;

            // Make a copy before we destroy the rules.
            ArrayList<Rule> copy = new ArrayList<Rule>(iRules);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[261]++;

            // Iterate through all the transitions until firstMillis is
            // reached. Use the name key and savings for whatever rule reaches
            // the limit.

            long millis = Long.MIN_VALUE;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[262]++;
            int saveMillis = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[263]++;
            Transition first = null;

            Transition next;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[264]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[13]++;


            while ((next = nextTransition(millis, saveMillis)) != null) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[13]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[14]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[15]++;
}
                millis = next.getMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[265]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[266]++;
int CodeCoverConditionCoverageHelper_C66;

                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((millis == firstMillis) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[134]++;
                    first = new Transition(firstMillis, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[267]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[268]++;
                    break;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[135]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[269]++;
int CodeCoverConditionCoverageHelper_C67;

                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((millis > firstMillis) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[136]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[270]++;
int CodeCoverConditionCoverageHelper_C68;
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[138]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[271]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[16]++;


                        // Find first rule without savings. This way a more
                        // accurate nameKey is found even though no rule
                        // extends to the RuleSet's lower limit.
                        for (Rule rule : copy) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[16]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[17]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[18]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[272]++;
int CodeCoverConditionCoverageHelper_C69;
                            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((rule.getSaveMillis() == 0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[140]++;
                                first = new Transition(firstMillis, rule, iStandardOffset);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[273]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[274]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[141]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[139]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[275]++;
int CodeCoverConditionCoverageHelper_C70;
                    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[142]++;
                        // Found no rule without savings. Create a transition
                        // with no savings anyhow, and use the best available
                        // name key.
                        first = new Transition(firstMillis, next.getNameKey(),
                                               iStandardOffset, iStandardOffset);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[276]++;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[143]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[277]++;
                    break;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[137]++;}
                
                // Set first to the best transition found so far, but next
                // iteration may find something closer to lower limit.
                first = new Transition(firstMillis, next);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[278]++;

                saveMillis = next.getSaveMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[279]++;
            }

            iRules = copy;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[280]++;
            return first;
        }

        /**
         * Returns null if RuleSet is exhausted or upper limit reached. Calling
         * this method will throw away rules as they each become
         * exhausted. Copy the RuleSet before using it to compute transitions.
         *
         * Returned transition may be a duplicate from previous
         * transition. Caller must call isTransitionFrom to filter out
         * duplicates.
         *
         * @param saveMillis savings before next transition
         */
        public Transition nextTransition(final long instant, final int saveMillis) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[281]++;
            Chronology chrono = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[282]++;

            // Find next matching rule.
            Rule nextRule = null;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[283]++;
            long nextMillis = Long.MAX_VALUE;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[284]++;
            
            Iterator<Rule> it = iRules.iterator();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[285]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[19]++;


int CodeCoverConditionCoverageHelper_C71;
            while ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[19]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[20]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[21]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[286]++;
                Rule rule = it.next();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[287]++;
                long next = rule.next(instant, iStandardOffset, saveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[288]++;
int CodeCoverConditionCoverageHelper_C72;
                if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((next <= instant) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[144]++;
                    it.remove();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[289]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[290]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[145]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[291]++;
int CodeCoverConditionCoverageHelper_C73;
                // Even if next is same as previous next, choose the rule
                // in order for more recently added rules to override.
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((next <= nextMillis) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[146]++;
                    // Found a better match.
                    nextRule = rule;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[292]++;
                    nextMillis = next;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[293]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[147]++;}
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[294]++;
int CodeCoverConditionCoverageHelper_C74;
            
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((nextRule == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[148]++;
                return null;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[149]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[295]++;
int CodeCoverConditionCoverageHelper_C75;
            
            // Stop precalculating if year reaches some arbitrary limit.
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((chrono.year().get(nextMillis) >= YEAR_LIMIT) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[150]++;
                return null;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[151]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[296]++;
int CodeCoverConditionCoverageHelper_C76;
            
            // Check if upper limit reached or passed.
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((iUpperYear < Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[152]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[297]++;
                long upperMillis =
                    iUpperOfYear.setInstant(iUpperYear, iStandardOffset, saveMillis);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[298]++;
int CodeCoverConditionCoverageHelper_C77;
                if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((nextMillis >= upperMillis) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[154]++;
                    // At or after upper limit.
                    return null;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[155]++;}

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[153]++;}
            
            return new Transition(nextMillis, nextRule, iStandardOffset);
        }

        /**
         * @param saveMillis savings before upper limit
         */
        public long getUpperLimit(int saveMillis) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[299]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((iUpperYear == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[156]++;
                return Long.MAX_VALUE;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[157]++;}
            return iUpperOfYear.setInstant(iUpperYear, iStandardOffset, saveMillis);
        }

        /**
         * Returns null if none can be built.
         */
        public DSTZone buildTailZone(String id) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[300]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((iRules.size() == 2) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[158]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[301]++;
                Rule startRule = iRules.get(0);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[302]++;
                Rule endRule = iRules.get(1);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[303]++;
int CodeCoverConditionCoverageHelper_C80;
                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((startRule.getToYear() == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((endRule.getToYear() == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[160]++;

                    // With exactly two infinitely recurring rules left, a
                    // simple DSTZone can be formed.

                    // The order of rules can come in any order, and it doesn't
                    // really matter which rule was chosen the 'start' and
                    // which is chosen the 'end'. DSTZone works properly either
                    // way.
                    return new DSTZone(id, iStandardOffset,
                                       startRule.iRecurrence, endRule.iRecurrence);

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[161]++;}

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[159]++;}
            return null;
        }
    }

    private static final class DSTZone extends DateTimeZone {
        private static final long serialVersionUID = 6941492635554961361L;

        static DSTZone readFrom(DataInput in, String id) throws IOException {
            return new DSTZone(id, (int)readMillis(in), 
                               Recurrence.readFrom(in), Recurrence.readFrom(in));
        }

        final int iStandardOffset;
        final Recurrence iStartRecurrence;
        final Recurrence iEndRecurrence;

        DSTZone(String id, int standardOffset,
                Recurrence startRecurrence, Recurrence endRecurrence) {
            super(id);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[304]++;
            iStandardOffset = standardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[305]++;
            iStartRecurrence = startRecurrence;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[306]++;
            iEndRecurrence = endRecurrence;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[307]++;
        }

        public String getNameKey(long instant) {
            return findMatchingRecurrence(instant).getNameKey();
        }

        public int getOffset(long instant) {
            return iStandardOffset + findMatchingRecurrence(instant).getSaveMillis();
        }

        public int getStandardOffset(long instant) {
            return iStandardOffset;
        }

        public boolean isFixed() {
            return false;
        }

        public long nextTransition(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[308]++;
            int standardOffset = iStandardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[309]++;
            Recurrence startRecurrence = iStartRecurrence;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[310]++;
            Recurrence endRecurrence = iEndRecurrence;

            long start, end;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[311]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

            try {
CodeCoverTryBranchHelper_Try3 = true;
                start = startRecurrence.next
                    (instant, standardOffset, endRecurrence.getSaveMillis());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[312]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[313]++;
int CodeCoverConditionCoverageHelper_C81;
                if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((instant > 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[163]++;
                    // Overflowed.
                    start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[314]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[164]++;}
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[165]++;
                // Overflowed.
                start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[315]++;
            } catch (ArithmeticException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[166]++;
                // Overflowed.
                start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[316]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[162]++;
}
  }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[317]++;
boolean CodeCoverTryBranchHelper_Try4 = false;

            try {
CodeCoverTryBranchHelper_Try4 = true;
                end = endRecurrence.next
                    (instant, standardOffset, startRecurrence.getSaveMillis());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[318]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[319]++;
int CodeCoverConditionCoverageHelper_C82;
                if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((instant > 0) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[168]++;
                    // Overflowed.
                    end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[320]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[169]++;}
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[170]++;
                // Overflowed.
                end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[321]++;
            } catch (ArithmeticException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[171]++;
                // Overflowed.
                end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[322]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[167]++;
}
  }

            return (start > end) ? end : start;
        }

        public long previousTransition(long instant) {
            // Increment in order to handle the case where instant is exactly at
            // a transition.
            instant++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[323]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[324]++;

            int standardOffset = iStandardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[325]++;
            Recurrence startRecurrence = iStartRecurrence;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[326]++;
            Recurrence endRecurrence = iEndRecurrence;

            long start, end;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[327]++;
boolean CodeCoverTryBranchHelper_Try5 = false;

            try {
CodeCoverTryBranchHelper_Try5 = true;
                start = startRecurrence.previous
                    (instant, standardOffset, endRecurrence.getSaveMillis());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[328]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[329]++;
int CodeCoverConditionCoverageHelper_C83;
                if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((instant < 0) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((start > 0) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[173]++;
                    // Overflowed.
                    start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[330]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[174]++;}
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[175]++;
                // Overflowed.
                start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[331]++;
            } catch (ArithmeticException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[176]++;
                // Overflowed.
                start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[332]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[172]++;
}
  }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[333]++;
boolean CodeCoverTryBranchHelper_Try6 = false;

            try {
CodeCoverTryBranchHelper_Try6 = true;
                end = endRecurrence.previous
                    (instant, standardOffset, startRecurrence.getSaveMillis());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[334]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[335]++;
int CodeCoverConditionCoverageHelper_C84;
                if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((instant < 0) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((end > 0) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[178]++;
                    // Overflowed.
                    end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[336]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[179]++;}
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[180]++;
                // Overflowed.
                end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[337]++;
            } catch (ArithmeticException e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[181]++;
                // Overflowed.
                end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[338]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[177]++;
}
  }

            return ((start > end) ? start : end) - 1;
        }

        public boolean equals(Object obj) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[339]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[182]++;
                return true;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[183]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[340]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((obj instanceof DSTZone) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[184]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[341]++;
                DSTZone other = (DSTZone)obj;
                return
                    getID().equals(other.getID()) &&
                    iStandardOffset == other.iStandardOffset &&
                    iStartRecurrence.equals(other.iStartRecurrence) &&
                    iEndRecurrence.equals(other.iEndRecurrence);

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[185]++;}
            return false;
        }

        public void writeTo(DataOutput out) throws IOException {
            writeMillis(out, iStandardOffset);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[342]++;
            iStartRecurrence.writeTo(out);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[343]++;
            iEndRecurrence.writeTo(out);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[344]++;
        }

        private Recurrence findMatchingRecurrence(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[345]++;
            int standardOffset = iStandardOffset;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[346]++;
            Recurrence startRecurrence = iStartRecurrence;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[347]++;
            Recurrence endRecurrence = iEndRecurrence;

            long start, end;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[348]++;
boolean CodeCoverTryBranchHelper_Try7 = false;

            try {
CodeCoverTryBranchHelper_Try7 = true;
                start = startRecurrence.next
                    (instant, standardOffset, endRecurrence.getSaveMillis());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[349]++;
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[187]++;
                // Overflowed.
                start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[350]++;
            } catch (ArithmeticException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[188]++;
                // Overflowed.
                start = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[351]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[186]++;
}
  }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[352]++;
boolean CodeCoverTryBranchHelper_Try8 = false;

            try {
CodeCoverTryBranchHelper_Try8 = true;
                end = endRecurrence.next
                    (instant, standardOffset, startRecurrence.getSaveMillis());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[353]++;
            } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[190]++;
                // Overflowed.
                end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[354]++;
            } catch (ArithmeticException e) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[191]++;
                // Overflowed.
                end = instant;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[355]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[189]++;
}
  }

            return (start > end) ? startRecurrence : endRecurrence;
        }
    }

    private static final class PrecalculatedZone extends DateTimeZone {
        private static final long serialVersionUID = 7811976468055766265L;

        static PrecalculatedZone readFrom(DataInput in, String id) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[356]++;
            // Read string pool.
            int poolSize = in.readUnsignedShort();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[357]++;
            String[] pool = new String[poolSize];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[358]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[22]++;


int CodeCoverConditionCoverageHelper_C87;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((i<poolSize) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[22]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[23]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[24]++;
}
                pool[i] = in.readUTF();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[359]++;
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[360]++;

            int size = in.readInt();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[361]++;
            long[] transitions = new long[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[362]++;
            int[] wallOffsets = new int[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[363]++;
            int[] standardOffsets = new int[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[364]++;
            String[] nameKeys = new String[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[365]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[25]++;


int CodeCoverConditionCoverageHelper_C88;
            
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[25]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[26]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[27]++;
}
                transitions[i] = readMillis(in);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[366]++;
                wallOffsets[i] = (int)readMillis(in);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[367]++;
                standardOffsets[i] = (int)readMillis(in);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[368]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[369]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
                try {
CodeCoverTryBranchHelper_Try9 = true;
                    int index;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[370]++;
int CodeCoverConditionCoverageHelper_C89;
                    if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((poolSize < 256) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[193]++;
                        index = in.readUnsignedByte();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[371]++;

                    } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[194]++;
                        index = in.readUnsignedShort();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[372]++;
                    }
                    nameKeys[i] = pool[index];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[373]++;
                } catch (ArrayIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[195]++;
                    throw new IOException("Invalid encoding");
                } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[192]++;
}
  }
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[374]++;

            DSTZone tailZone = null;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[375]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((in.readBoolean()) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[196]++;
                tailZone = DSTZone.readFrom(in, id);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[376]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[197]++;}

            return new PrecalculatedZone
                (id, transitions, wallOffsets, standardOffsets, nameKeys, tailZone);
        }

        /**
         * Factory to create instance from builder.
         * 
         * @param id  the zone id
         * @param outputID  true if the zone id should be output
         * @param transitions  the list of Transition objects
         * @param tailZone  optional zone for getting info beyond precalculated tables
         */
        static PrecalculatedZone create(String id, boolean outputID, ArrayList<Transition> transitions,
                                        DSTZone tailZone) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[377]++;
            int size = transitions.size();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[378]++;
int CodeCoverConditionCoverageHelper_C91;
            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((size == 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[198]++;
                throw new IllegalArgumentException();

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[199]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[379]++;

            long[] trans = new long[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[380]++;
            int[] wallOffsets = new int[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[381]++;
            int[] standardOffsets = new int[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[382]++;
            String[] nameKeys = new String[size];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[383]++;

            Transition last = null;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[384]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[28]++;


int CodeCoverConditionCoverageHelper_C92;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[28]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[29]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[30]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[385]++;
                Transition tr = transitions.get(i);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[386]++;
int CodeCoverConditionCoverageHelper_C93;

                if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((tr.isTransitionFrom(last)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[200]++;
                    throw new IllegalArgumentException(id);

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[201]++;}

                trans[i] = tr.getMillis();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[387]++;
                wallOffsets[i] = tr.getWallOffset();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[388]++;
                standardOffsets[i] = tr.getStandardOffset();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[389]++;
                nameKeys[i] = tr.getNameKey();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[390]++;

                last = tr;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[391]++;
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[392]++;

            // Some timezones (Australia) have the same name key for
            // summer and winter which messes everything up. Fix it here.
            String[] zoneNameData = new String[5];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[393]++;
            String[][] zoneStrings = new DateFormatSymbols(Locale.ENGLISH).getZoneStrings();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[394]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[31]++;


int CodeCoverConditionCoverageHelper_C94;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((j < zoneStrings.length) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[31]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[32]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[33]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[395]++;
                String[] set = zoneStrings[j];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[396]++;
int CodeCoverConditionCoverageHelper_C95;
                if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (32)) == 0 || true) &&
 ((set != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((set.length == 5) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((id.equals(set[0])) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 3) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 3) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[202]++;
                    zoneNameData = set;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[397]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[203]++;}
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[398]++;

            Chronology chrono = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[399]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[34]++;


int CodeCoverConditionCoverageHelper_C96;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((i < nameKeys.length - 1) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[34]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[35]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[36]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[400]++;
                String curNameKey = nameKeys[i];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[401]++;
                String nextNameKey = nameKeys[i + 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[402]++;
                long curOffset = wallOffsets[i];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[403]++;
                long nextOffset = wallOffsets[i + 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[404]++;
                long curStdOffset = standardOffsets[i];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[405]++;
                long nextStdOffset = standardOffsets[i + 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[406]++;
                Period p = new Period(trans[i], trans[i + 1], PeriodType.yearMonthDay(), chrono);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[407]++;
int CodeCoverConditionCoverageHelper_C97;
                if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (32768)) == 0 || true) &&
 ((curOffset != nextOffset) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (16384)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (8192)) == 0 || true) &&
 ((curStdOffset == nextStdOffset) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4096)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (2048)) == 0 || true) &&
 ((curNameKey.equals(nextNameKey)) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (512)) == 0 || true) &&
 ((p.getYears() == 0) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (128)) == 0 || true) &&
 ((p.getMonths() > 4) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (32)) == 0 || true) &&
 ((p.getMonths() < 8) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (8)) == 0 || true) &&
 ((curNameKey.equals(zoneNameData[2])) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((curNameKey.equals(zoneNameData[4])) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 8) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 8) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[204]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[408]++;
int CodeCoverConditionCoverageHelper_C98;
                    
                    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((ZoneInfoCompiler.verbose()) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[206]++;
                        System.out.println("Fixing duplicate name key - " + nextNameKey);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[409]++;
                        System.out.println("     - " + new DateTime(trans[i], chrono) +
                                           " - " + new DateTime(trans[i + 1], chrono));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[410]++;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[207]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[411]++;
int CodeCoverConditionCoverageHelper_C99;
                    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((curOffset > nextOffset) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[208]++;
                        nameKeys[i] = (curNameKey + "-Summer").intern();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[412]++;

                    } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[209]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[413]++;
int CodeCoverConditionCoverageHelper_C100; if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((curOffset < nextOffset) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[210]++;
                        nameKeys[i + 1] = (nextNameKey + "-Summer").intern();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[414]++;
                        i++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[415]++;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[211]++;}
}

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[205]++;}
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[416]++;
int CodeCoverConditionCoverageHelper_C101;

            if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((tailZone != null) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[212]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[417]++;
int CodeCoverConditionCoverageHelper_C102;
                if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((tailZone.iStartRecurrence.getNameKey()
                    .equals(tailZone.iEndRecurrence.getNameKey())) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[214]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[418]++;
int CodeCoverConditionCoverageHelper_C103;
                    if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((ZoneInfoCompiler.verbose()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[216]++;
                        System.out.println("Fixing duplicate recurrent name key - " +
                                           tailZone.iStartRecurrence.getNameKey());
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[419]++;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[217]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[420]++;
int CodeCoverConditionCoverageHelper_C104;
                    if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((tailZone.iStartRecurrence.getSaveMillis() > 0) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[218]++;
                        tailZone = new DSTZone(
                            tailZone.getID(),
                            tailZone.iStandardOffset,
                            tailZone.iStartRecurrence.renameAppend("-Summer"),
                            tailZone.iEndRecurrence);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[421]++;

                    } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[219]++;
                        tailZone = new DSTZone(
                            tailZone.getID(),
                            tailZone.iStandardOffset,
                            tailZone.iStartRecurrence,
                            tailZone.iEndRecurrence.renameAppend("-Summer"));
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[422]++;
                    }

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[215]++;}

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[213]++;}
            
            return new PrecalculatedZone
                ((outputID ? id : ""), trans, wallOffsets, standardOffsets, nameKeys, tailZone);
        }

        // All array fields have the same length.

        private final long[] iTransitions;

        private final int[] iWallOffsets;
        private final int[] iStandardOffsets;
        private final String[] iNameKeys;

        private final DSTZone iTailZone;

        /**
         * Constructor used ONLY for valid input, loaded via static methods.
         */
        private PrecalculatedZone(String id, long[] transitions, int[] wallOffsets,
                          int[] standardOffsets, String[] nameKeys, DSTZone tailZone)
        {
            super(id);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[423]++;
            iTransitions = transitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[424]++;
            iWallOffsets = wallOffsets;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[425]++;
            iStandardOffsets = standardOffsets;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[426]++;
            iNameKeys = nameKeys;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[427]++;
            iTailZone = tailZone;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[428]++;
        }

        public String getNameKey(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[429]++;
            long[] transitions = iTransitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[430]++;
            int i = Arrays.binarySearch(transitions, instant);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[431]++;
int CodeCoverConditionCoverageHelper_C105;
            if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[220]++;
                return iNameKeys[i];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[221]++;}
            i = ~i;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[432]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[433]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((i < transitions.length) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[222]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[434]++;
int CodeCoverConditionCoverageHelper_C107;
                if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[224]++;
                    return iNameKeys[i - 1];

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[225]++;}
                return "UTC";

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[223]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[435]++;
int CodeCoverConditionCoverageHelper_C108;
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((iTailZone == null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[226]++;
                return iNameKeys[i - 1];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[227]++;}
            return iTailZone.getNameKey(instant);
        }

        public int getOffset(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[436]++;
            long[] transitions = iTransitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[437]++;
            int i = Arrays.binarySearch(transitions, instant);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[438]++;
int CodeCoverConditionCoverageHelper_C109;
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[228]++;
                return iWallOffsets[i];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[229]++;}
            i = ~i;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[439]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[440]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((i < transitions.length) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[230]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[441]++;
int CodeCoverConditionCoverageHelper_C111;
                if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[232]++;
                    return iWallOffsets[i - 1];

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[233]++;}
                return 0;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[231]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[442]++;
int CodeCoverConditionCoverageHelper_C112;
            if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((iTailZone == null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[234]++;
                return iWallOffsets[i - 1];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[235]++;}
            return iTailZone.getOffset(instant);
        }

        public int getStandardOffset(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[443]++;
            long[] transitions = iTransitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[444]++;
            int i = Arrays.binarySearch(transitions, instant);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[445]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[236]++;
                return iStandardOffsets[i];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[237]++;}
            i = ~i;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[446]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[447]++;
int CodeCoverConditionCoverageHelper_C114;
            if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((i < transitions.length) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[238]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[448]++;
int CodeCoverConditionCoverageHelper_C115;
                if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[240]++;
                    return iStandardOffsets[i - 1];

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[241]++;}
                return 0;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[239]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[449]++;
int CodeCoverConditionCoverageHelper_C116;
            if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((iTailZone == null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[242]++;
                return iStandardOffsets[i - 1];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[243]++;}
            return iTailZone.getStandardOffset(instant);
        }

        public boolean isFixed() {
            return false;
        }

        public long nextTransition(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[450]++;
            long[] transitions = iTransitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[451]++;
            int i = Arrays.binarySearch(transitions, instant);
            i = (i >= 0) ? (i + 1) : ~i;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[452]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[453]++;
int CodeCoverConditionCoverageHelper_C117;
            if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((i < transitions.length) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[244]++;
                return transitions[i];

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[245]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[454]++;
int CodeCoverConditionCoverageHelper_C118;
            if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((iTailZone == null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[246]++;
                return instant;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[247]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[455]++;
            long end = transitions[transitions.length - 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[456]++;
int CodeCoverConditionCoverageHelper_C119;
            if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((instant < end) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[248]++;
                instant = end;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[457]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[249]++;}
            return iTailZone.nextTransition(instant);
        }

        public long previousTransition(long instant) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[458]++;
            long[] transitions = iTransitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[459]++;
            int i = Arrays.binarySearch(transitions, instant);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[460]++;
int CodeCoverConditionCoverageHelper_C120;
            if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[250]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[461]++;
int CodeCoverConditionCoverageHelper_C121;
                if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((instant > Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[252]++;
                    return instant - 1;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[253]++;}
                return instant;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[251]++;}
            i = ~i;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[462]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[463]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((i < transitions.length) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[254]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[464]++;
int CodeCoverConditionCoverageHelper_C123;
                if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[256]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[465]++;
                    long prev = transitions[i - 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[466]++;
int CodeCoverConditionCoverageHelper_C124;
                    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((prev > Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[258]++;
                        return prev - 1;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[259]++;}

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[257]++;}
                return instant;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[255]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[467]++;
int CodeCoverConditionCoverageHelper_C125;
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((iTailZone != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[260]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[468]++;
                long prev = iTailZone.previousTransition(instant);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[469]++;
int CodeCoverConditionCoverageHelper_C126;
                if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((prev < instant) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[262]++;
                    return prev;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[263]++;}

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[261]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[470]++;
            long prev = transitions[i - 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[471]++;
int CodeCoverConditionCoverageHelper_C127;
            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((prev > Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[264]++;
                return prev - 1;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[265]++;}
            return instant;
        }

        public boolean equals(Object obj) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[472]++;
int CodeCoverConditionCoverageHelper_C128;
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[266]++;
                return true;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[267]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[473]++;
int CodeCoverConditionCoverageHelper_C129;
            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((obj instanceof PrecalculatedZone) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[268]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[474]++;
                PrecalculatedZone other = (PrecalculatedZone)obj;
                return
                    getID().equals(other.getID()) &&
                    Arrays.equals(iTransitions, other.iTransitions) &&
                    Arrays.equals(iNameKeys, other.iNameKeys) &&
                    Arrays.equals(iWallOffsets, other.iWallOffsets) &&
                    Arrays.equals(iStandardOffsets, other.iStandardOffsets) &&
                    ((iTailZone == null)
                     ? (null == other.iTailZone)
                     : (iTailZone.equals(other.iTailZone)));

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[269]++;}
            return false;
        }

        public void writeTo(DataOutput out) throws IOException {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[475]++;
            int size = iTransitions.length;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[476]++;

            // Create unique string pool.
            Set<String> poolSet = new HashSet<String>();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[477]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[37]++;


int CodeCoverConditionCoverageHelper_C130;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[37]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[38]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[39]++;
}
                poolSet.add(iNameKeys[i]);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[478]++;
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[479]++;

            int poolSize = poolSet.size();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[480]++;
int CodeCoverConditionCoverageHelper_C131;
            if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((poolSize > 65535) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[270]++;
                throw new UnsupportedOperationException("String pool is too large");

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[271]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[481]++;
            String[] pool = new String[poolSize];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[482]++;
            Iterator<String> it = poolSet.iterator();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[483]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[40]++;


int CodeCoverConditionCoverageHelper_C132;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[40]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[41]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[42]++;
}
                pool[i] = it.next();
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[484]++;
            }

            // Write out the pool.
            out.writeShort(poolSize);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[485]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[486]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[43]++;


int CodeCoverConditionCoverageHelper_C133;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((i<poolSize) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[43]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[44]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[45]++;
}
                out.writeUTF(pool[i]);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[487]++;
            }

            out.writeInt(size);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[488]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[489]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[46]++;


int CodeCoverConditionCoverageHelper_C134;

            for (int i=0;(((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((i<size) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[46]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[47]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[48]++;
}
                writeMillis(out, iTransitions[i]);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[490]++;
                writeMillis(out, iWallOffsets[i]);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[491]++;
                writeMillis(out, iStandardOffsets[i]);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[492]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[493]++;
                
                // Find pool index and write it out.
                String nameKey = iNameKeys[i];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[494]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[49]++;


int CodeCoverConditionCoverageHelper_C135;
                for (int j=0;(((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((j<poolSize) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[49]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[50]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[51]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[495]++;
int CodeCoverConditionCoverageHelper_C136;
                    if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((pool[j].equals(nameKey)) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[272]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[496]++;
int CodeCoverConditionCoverageHelper_C137;
                        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((poolSize < 256) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[274]++;
                            out.writeByte(j);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[497]++;

                        } else {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[275]++;
                            out.writeShort(j);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[498]++;
                        }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[499]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[273]++;}
                }
            }

            out.writeBoolean(iTailZone != null);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[500]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[501]++;
int CodeCoverConditionCoverageHelper_C138;
            if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((iTailZone != null) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[276]++;
                iTailZone.writeTo(out);
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[502]++;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[277]++;}
        }

        public boolean isCachable() {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[503]++;
int CodeCoverConditionCoverageHelper_C139;
            if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((iTailZone != null) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[278]++;
                return true;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[279]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[504]++;
            long[] transitions = iTransitions;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[505]++;
int CodeCoverConditionCoverageHelper_C140;
            if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((transitions.length <= 1) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[280]++;
                return false;

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[281]++;}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[506]++;

            // Add up all the distances between transitions that are less than
            // about two years.
            double distances = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[507]++;
            int count = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[508]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[52]++;


int CodeCoverConditionCoverageHelper_C141;

            for (int i=1;(((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((i<transitions.length) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[52]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[53]--;
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.loops[54]++;
}
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[509]++;
                long diff = transitions[i] - transitions[i - 1];
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[510]++;
int CodeCoverConditionCoverageHelper_C142;
                if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((diff < ((366L + 365) * 24 * 60 * 60 * 1000)) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[282]++;
                    distances += (double)diff;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[511]++;
                    count++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[512]++;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[283]++;}
            }
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[513]++;
int CodeCoverConditionCoverageHelper_C143;

            if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[284]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[514]++;
                double avg = distances / count;
                avg /= 24 * 60 * 60 * 1000;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[515]++;
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.statements[516]++;
int CodeCoverConditionCoverageHelper_C144;
                if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((avg >= 25) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[286]++;
                    // Only bother caching if average distance between
                    // transitions is at least 25 days. Why 25?
                    // CachedDateTimeZone is more efficient if the distance
                    // between transitions is large. With an average of 25, it
                    // will on average perform about 2 tests per cache
                    // hit. (49.7 / 25) is approximately 2.
                    return true;

                } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[287]++;}

            } else {
  CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p.branches[285]++;}

            return false;
        }
    }
}

class CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p ());
  }
    public static long[] statements = new long[517];
    public static long[] branches = new long[288];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[145];
  static {
    final String SECTION_NAME = "org.joda.time.tz.DateTimeZoneBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,0,1,1,2,1,1,2,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,3,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 144; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[55];

  public CodeCoverCoverageCounter$frd9dpy1f3aeslisbf1cbsr0bhfy2qcxeef6p () {
    super("org.joda.time.tz.DateTimeZoneBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 516; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 287; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 144; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 54; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.tz.DateTimeZoneBuilder.java");
      for (int i = 1; i <= 516; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 287; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 144; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 18; i++) {
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

