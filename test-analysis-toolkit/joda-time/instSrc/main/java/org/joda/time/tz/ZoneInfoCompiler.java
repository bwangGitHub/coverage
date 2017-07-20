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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Compiles Olson ZoneInfo database files into binary files for each time zone
 * in the database. {@link DateTimeZoneBuilder} is used to construct and encode
 * compiled data files. {@link ZoneInfoProvider} loads the encoded files and
 * converts them back into {@link DateTimeZone} objects.
 * <p>
 * Although this tool is similar to zic, the binary formats are not
 * compatible. The latest Olson database files may be obtained
 * <a href="http://www.twinsun.com/tz/tz-link.htm">here</a>.
 * <p>
 * ZoneInfoCompiler is mutable and not thread-safe, although the main method
 * may be safely invoked by multiple threads.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class ZoneInfoCompiler {
  static {
    CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.ping();
  }

    static DateTimeOfYear cStartOfYear;

    static Chronology cLenientISO;

    static ThreadLocal<Boolean> cVerbose = new ThreadLocal<Boolean>();
  static {
    CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[1]++;
  }
    static {
        cVerbose.set(Boolean.FALSE);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[2]++;
    }

    /**
     * Gets a flag indicating that verbose logging is required.
     * @return true to log verbosely
     */
    public static boolean verbose() {
        return cVerbose.get();
    }

    //-----------------------------------------------------------------------
    /**
     * Launches the ZoneInfoCompiler tool.
     *
     * <pre>
     * Usage: java org.joda.time.tz.ZoneInfoCompiler &lt;options&gt; &lt;source files&gt;
     * where possible options include:
     *   -src &lt;directory&gt;    Specify where to read source files
     *   -dst &lt;directory&gt;    Specify where to write generated files
     *   -verbose            Output verbosely (default false)
     * </pre>
     */
    public static void main(String[] args) throws Exception {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[1]++;
            printUsage();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[4]++;
            return;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[2]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[5]++;

        File inputDir = null;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[6]++;
        File outputDir = null;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[7]++;
        boolean verbose = false;

        int i;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (i=0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i<args.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[1]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[2]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[3]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[9]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 (("-src".equals(args[i])) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[4]++;
                    inputDir = new File(args[++i]);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[11]++;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[5]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[12]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (("-dst".equals(args[i])) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[6]++;
                    outputDir = new File(args[++i]);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[13]++;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[7]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[14]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (("-verbose".equals(args[i])) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[8]++;
                    verbose = true;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[15]++;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[9]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[16]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (("-?".equals(args[i])) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[10]++;
                    printUsage();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[17]++;
                    return;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[11]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[18]++;
                    break;
                }
}
}
}
            } catch (IndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[12]++;
                printUsage();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[19]++;
                return;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[3]++;
}
  }
        }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i >= args.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[13]++;
            printUsage();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[21]++;
            return;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[14]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[22]++;

        File[] sources = new File[args.length - i];
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int j=0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i<args.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++,j++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[4]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[5]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[6]++;
}
            sources[j] = inputDir == null ? new File(args[i]) : new File(inputDir, args[i]);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[24]++;
        }

        cVerbose.set(verbose);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[25]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[26]++;
        ZoneInfoCompiler zic = new ZoneInfoCompiler();
        zic.compile(outputDir, sources);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[27]++;
    }

    private static void printUsage() {
        System.out.println("Usage: java org.joda.time.tz.ZoneInfoCompiler <options> <source files>");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[28]++;
        System.out.println("where possible options include:");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[29]++;
        System.out.println("  -src <directory>    Specify where to read source files");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[30]++;
        System.out.println("  -dst <directory>    Specify where to write generated files");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[31]++;
        System.out.println("  -verbose            Output verbosely (default false)");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[32]++;
    }

    static DateTimeOfYear getStartOfYear() {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((cStartOfYear == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[15]++;
            cStartOfYear = new DateTimeOfYear();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[34]++;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[16]++;}
        return cStartOfYear;
    }

    static Chronology getLenientISOChronology() {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cLenientISO == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[17]++;
            cLenientISO = LenientChronology.getInstance(ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[36]++;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[18]++;}
        return cLenientISO;
    }

    /**
     * @param zimap maps string ids to DateTimeZone objects.
     */
    static void writeZoneInfoMap(DataOutputStream dout, Map<String, DateTimeZone> zimap) throws IOException {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[37]++;
        // Build the string pool.
        Map<String, Short> idToIndex = new HashMap<String, Short>(zimap.size());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[38]++;
        TreeMap<Short, String> indexToId = new TreeMap<Short, String>();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[39]++;

        short count = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[40]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[7]++;


        for (Entry<String, DateTimeZone> entry : zimap.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[7]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[8]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[9]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[41]++;
            String id = (String)entry.getKey();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((idToIndex.containsKey(id)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[19]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[43]++;
                Short index = Short.valueOf(count);
                idToIndex.put(id, index);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[44]++;
                indexToId.put(index, id);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[45]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((++count == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[21]++;
                    throw new InternalError("Too many time zone ids");

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[22]++;}

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[20]++;}
            id = ((DateTimeZone)entry.getValue()).getID();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[47]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((idToIndex.containsKey(id)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[23]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[49]++;
                Short index = Short.valueOf(count);
                idToIndex.put(id, index);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[50]++;
                indexToId.put(index, id);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[51]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((++count == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[25]++;
                    throw new InternalError("Too many time zone ids");

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[26]++;}

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[24]++;}
        }

        // Write the string pool, ordered by index.
        dout.writeShort(indexToId.size());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[53]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[54]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[10]++;


        for (String id : indexToId.values()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[10]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[11]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[12]++;
}
            dout.writeUTF(id);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[55]++;
        }

        // Write the mappings.
        dout.writeShort(zimap.size());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[56]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[57]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[13]++;


        for (Entry<String, DateTimeZone> entry : zimap.entrySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[13]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[14]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[15]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[58]++;
            String id = entry.getKey();
            dout.writeShort(idToIndex.get(id).shortValue());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[59]++;
            id = entry.getValue().getID();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[60]++;
            dout.writeShort(idToIndex.get(id).shortValue());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[61]++;
        }
    }

    static int parseYear(String str, int def) {
        str = str.toLowerCase();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[62]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((str.equals("minimum")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((str.equals("min")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[27]++;
            return Integer.MIN_VALUE;

        } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[28]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[64]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((str.equals("maximum")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((str.equals("max")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[29]++;
            return Integer.MAX_VALUE;

        } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[30]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[65]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((str.equals("only")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[31]++;
            return def;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[32]++;}
}
}
        return Integer.parseInt(str);
    }

    static int parseMonth(String str) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[66]++;
        DateTimeField field = ISOChronology.getInstanceUTC().monthOfYear();
        return field.get(field.set(0, str, Locale.ENGLISH));
    }

    static int parseDayOfWeek(String str) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[67]++;
        DateTimeField field = ISOChronology.getInstanceUTC().dayOfWeek();
        return field.get(field.set(0, str, Locale.ENGLISH));
    }
    
    static String parseOptional(String str) {
        return (str.equals("-")) ? null : str;
    }

    static int parseTime(String str) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[68]++;
        DateTimeFormatter p = ISODateTimeFormat.hourMinuteSecondFraction();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[69]++;
        MutableDateTime mdt = new MutableDateTime(0, getLenientISOChronology());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[70]++;
        int pos = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[71]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((str.startsWith("-")) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[33]++;
            pos = 1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[72]++;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[34]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[73]++;
        int newPos = p.parseInto(mdt, str, pos);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[74]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((newPos == ~pos) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[35]++;
            throw new IllegalArgumentException(str);

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[36]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[75]++;
        int millis = (int)mdt.getMillis();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[76]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((pos == 1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[37]++;
            millis = -millis;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[77]++;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[38]++;}
        return millis;
    }

    static char parseZoneChar(char c) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[78]++;
        switch (c) {
        case 's':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[39]++; case 'S':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[40]++;
            // Standard time
            return 's';
        case 'u':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[41]++; case 'U':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[42]++; case 'g':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[43]++; case 'G':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[44]++; case 'z':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[45]++; case 'Z':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[46]++;
            // UTC
            return 'u';
        case 'w':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[47]++; case 'W':
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[48]++; default:
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[49]++;
            // Wall time
            return 'w';
        }
    }

    /**
     * @return false if error.
     */
    static boolean test(String id, DateTimeZone tz) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((id.equals(tz.getID())) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[50]++;
            return true;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[51]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[80]++;

        // Test to ensure that reported transitions are not duplicated.

        long millis = ISOChronology.getInstanceUTC().year().set(0, 1850);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[81]++;
        long end = ISOChronology.getInstanceUTC().year().set(0, 2050);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[82]++;

        int offset = tz.getOffset(millis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[83]++;
        String key = tz.getNameKey(millis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[84]++;

        List<Long> transitions = new ArrayList<Long>();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[85]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[16]++;



        while (true) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[16]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[17]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[18]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[86]++;
            long next = tz.nextTransition(millis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[87]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((next == millis) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((next > end) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[52]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[88]++;
                break;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[53]++;}

            millis = next;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[89]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[90]++;

            int nextOffset = tz.getOffset(millis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[91]++;
            String nextKey = tz.getNameKey(millis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[92]++;
int CodeCoverConditionCoverageHelper_C24;

            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((offset == nextOffset) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((key.equals(nextKey)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[54]++;
                System.out.println("*d* Error in " + tz.getID() + " "
                                   + new DateTime(millis,
                                                  ISOChronology.getInstanceUTC()));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[93]++;
                return false;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[55]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[94]++;
int CodeCoverConditionCoverageHelper_C25;

            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((nextKey == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((nextKey.length() < 3) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 (("??".equals(nextKey)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[56]++;
                System.out.println("*s* Error in " + tz.getID() + " "
                                   + new DateTime(millis,
                                                  ISOChronology.getInstanceUTC())
                                   + ", nameKey=" + nextKey);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[95]++;
                return false;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[57]++;}

            transitions.add(Long.valueOf(millis));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[96]++;

            offset = nextOffset;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[97]++;
            key = nextKey;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[98]++;
        }

        // Now verify that reverse transitions match up.

        millis = ISOChronology.getInstanceUTC().year().set(0, 2050);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[99]++;
        end = ISOChronology.getInstanceUTC().year().set(0, 1850);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[100]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[101]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[19]++;


int CodeCoverConditionCoverageHelper_C26;

        for (int i=transitions.size();(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((--i>= 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); ) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[19]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[20]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[21]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[102]++;
            long prev = tz.previousTransition(millis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[103]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((prev == millis) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((prev < end) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[58]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[104]++;
                break;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[59]++;}

            millis = prev;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[105]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[106]++;

            long trans = transitions.get(i).longValue();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[107]++;
int CodeCoverConditionCoverageHelper_C28;
            
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((trans - 1 != millis) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[60]++;
                System.out.println("*r* Error in " + tz.getID() + " "
                                   + new DateTime(millis,
                                                  ISOChronology.getInstanceUTC()) + " != "
                                   + new DateTime(trans - 1,
                                                  ISOChronology.getInstanceUTC()));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[108]++;
                                   
                return false;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[61]++;}
        }

        return true;
    }

    // Maps names to RuleSets.
    private Map<String, RuleSet> iRuleSets;

    // List of Zone objects.
    private List<Zone> iZones;

    // List String pairs to link.
    private List<String> iLinks;

    public ZoneInfoCompiler() {
        iRuleSets = new HashMap<String, RuleSet>();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[109]++;
        iZones = new ArrayList<Zone>();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[110]++;
        iLinks = new ArrayList<String>();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[111]++;
    }

    /**
     * Returns a map of ids to DateTimeZones.
     *
     * @param outputDir optional directory to write compiled data files to
     * @param sources optional list of source files to parse
     */
    public Map<String, DateTimeZone> compile(File outputDir, File[] sources) throws IOException {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[112]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((sources != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[62]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[113]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[22]++;


int CodeCoverConditionCoverageHelper_C30;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i<sources.length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[22]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[23]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[24]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[114]++;
                BufferedReader in = new BufferedReader(new FileReader(sources[i]));
                parseDataFile(in);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[115]++;
                in.close();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[116]++;
            }

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[63]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[117]++;
int CodeCoverConditionCoverageHelper_C31;

        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((outputDir != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[64]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[118]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((outputDir.exists()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[66]++;
                throw new IOException("Destination directory doesn't exist: " + outputDir);

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[67]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[119]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((outputDir.isDirectory()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[68]++;
                throw new IOException("Destination is not a directory: " + outputDir);

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[69]++;}

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[65]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[120]++;

        Map<String, DateTimeZone> map = new TreeMap<String, DateTimeZone>();

        System.out.println("Writing zoneinfo files");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[121]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[122]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[25]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i<iZones.size()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[25]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[26]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[27]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[123]++;
            Zone zone = iZones.get(i);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[124]++;
            DateTimeZoneBuilder builder = new DateTimeZoneBuilder();
            zone.addToBuilder(builder, iRuleSets);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[125]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[126]++;
            final DateTimeZone original = builder.toDateTimeZone(zone.iName, true);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[127]++;
            DateTimeZone tz = original;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[128]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((test(tz.getID(), tz)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[70]++;
                map.put(tz.getID(), tz);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[129]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[130]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((outputDir != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[72]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[131]++;
int CodeCoverConditionCoverageHelper_C37;
                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((ZoneInfoCompiler.verbose()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[74]++;
                        System.out.println("Writing " + tz.getID());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[132]++;

                    } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[75]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[133]++;
                    File file = new File(outputDir, tz.getID());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[134]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((file.getParentFile().exists()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[76]++;
                        file.getParentFile().mkdirs();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[135]++;

                    } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[77]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[136]++;
                    OutputStream out = new FileOutputStream(file);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[137]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                    try {
CodeCoverTryBranchHelper_Try2 = true;
                        builder.writeTo(zone.iName, out);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[138]++;
                    } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[78]++;
}
                        out.close();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[139]++;
                    }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[140]++;

                    // Test if it can be read back.
                    InputStream in = new FileInputStream(file);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[141]++;
                    DateTimeZone tz2 = DateTimeZoneBuilder.readFrom(in, tz.getID());
                    in.close();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[142]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[143]++;
int CodeCoverConditionCoverageHelper_C39;

                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((original.equals(tz2)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[79]++;
                        System.out.println("*e* Error in " + tz.getID() +
                                           ": Didn't read properly from file");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[144]++;

                    } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[80]++;}

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[73]++;}

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[71]++;}
        }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[145]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[28]++;


int CodeCoverConditionCoverageHelper_C40;

        for (int pass=0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((pass<2) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); pass++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[28]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[29]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[30]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[146]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[31]++;


int CodeCoverConditionCoverageHelper_C41;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i<iLinks.size()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i += 2) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[31]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[32]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[33]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[147]++;
                String id = iLinks.get(i);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[148]++;
                String alias = iLinks.get(i + 1);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[149]++;
                DateTimeZone tz = map.get(id);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[150]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((tz == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[81]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[151]++;
int CodeCoverConditionCoverageHelper_C43;
                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((pass > 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[83]++;
                        System.out.println("Cannot find time zone '" + id +
                                           "' to link alias '" + alias + "' to");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[152]++;

                    } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[84]++;}

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[82]++;
                    map.put(alias, tz);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[153]++;
                }
            }
        }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[154]++;
int CodeCoverConditionCoverageHelper_C44;

        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((outputDir != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[85]++;
            System.out.println("Writing ZoneInfoMap");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[155]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[156]++;
            File file = new File(outputDir, "ZoneInfoMap");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[157]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((file.getParentFile().exists()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[87]++;
                file.getParentFile().mkdirs();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[158]++;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[88]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[159]++;

            OutputStream out = new FileOutputStream(file);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[160]++;
            DataOutputStream dout = new DataOutputStream(out);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[161]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[162]++;
                // Sort and filter out any duplicates that match case.
                Map<String, DateTimeZone> zimap = new TreeMap<String, DateTimeZone>(String.CASE_INSENSITIVE_ORDER);
                zimap.putAll(map);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[163]++;
                writeZoneInfoMap(dout, zimap);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[164]++;
            } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[89]++;
}
                dout.close();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[165]++;
            }

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[86]++;}

        return map;
    }

    public void parseDataFile(BufferedReader in) throws IOException {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[166]++;
        Zone zone = null;
        String line;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[167]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[34]++;


        while ((line = in.readLine()) != null) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[34]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[35]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[36]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[168]++;
            String trimmed = line.trim();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[169]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((trimmed.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((trimmed.charAt(0) == '#') && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[90]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[170]++;
                continue;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[91]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[171]++;

            int index = line.indexOf('#');
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[172]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[92]++;
                line = line.substring(0, index);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[173]++;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[93]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[174]++;

            //System.out.println(line);

            StringTokenizer st = new StringTokenizer(line, " \t");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[175]++;
int CodeCoverConditionCoverageHelper_C49;

            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((Character.isWhitespace(line.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[94]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[176]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[96]++;
                    // Zone continuation
                    zone.chain(st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[177]++;

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[97]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[178]++;
                continue;

            } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[95]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[179]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[98]++;
                    iZones.add(zone);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[180]++;

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[99]++;}
                zone = null;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[181]++;
            }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[182]++;
int CodeCoverConditionCoverageHelper_C52;

            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[100]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[183]++;
                String token = st.nextToken();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[184]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((token.equalsIgnoreCase("Rule")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[102]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[185]++;
                    Rule r = new Rule(st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[186]++;
                    RuleSet rs = iRuleSets.get(r.iName);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[187]++;
int CodeCoverConditionCoverageHelper_C54;
                    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((rs == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[104]++;
                        rs = new RuleSet(r);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[188]++;
                        iRuleSets.put(r.iName, rs);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[189]++;

                    } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[105]++;
                        rs.addRule(r);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[190]++;
                    }

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[103]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[191]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((token.equalsIgnoreCase("Zone")) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[106]++;
                    zone = new Zone(st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[192]++;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[107]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[193]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((token.equalsIgnoreCase("Link")) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[108]++;
                    iLinks.add(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[194]++;
                    iLinks.add(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[195]++;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[109]++;
                    System.out.println("Unknown line: " + line);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[196]++;
                }
}
}

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[101]++;}
        }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[197]++;
int CodeCoverConditionCoverageHelper_C57;

        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[110]++;
            iZones.add(zone);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[198]++;

        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[111]++;}
    }

    static class DateTimeOfYear {
        public final int iMonthOfYear;
        public final int iDayOfMonth;
        public final int iDayOfWeek;
        public final boolean iAdvanceDayOfWeek;
        public final int iMillisOfDay;
        public final char iZoneChar;

        DateTimeOfYear() {
            iMonthOfYear = 1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[199]++;
            iDayOfMonth = 1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[200]++;
            iDayOfWeek = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[201]++;
            iAdvanceDayOfWeek = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[202]++;
            iMillisOfDay = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[203]++;
            iZoneChar = 'w';
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[204]++;
        }

        DateTimeOfYear(StringTokenizer st) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[205]++;
            int month = 1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[206]++;
            int day = 1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[207]++;
            int dayOfWeek = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[208]++;
            int millis = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[209]++;
            boolean advance = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[210]++;
            char zoneChar = 'w';
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[211]++;
int CodeCoverConditionCoverageHelper_C58;

            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[112]++;
                month = parseMonth(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[212]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[213]++;
int CodeCoverConditionCoverageHelper_C59;

                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[114]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[214]++;
                    String str = st.nextToken();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[215]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((str.startsWith("last")) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[116]++;
                        day = -1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[216]++;
                        dayOfWeek = parseDayOfWeek(str.substring(4));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[217]++;
                        advance = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[218]++;

                    } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[117]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[219]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                        try {
CodeCoverTryBranchHelper_Try4 = true;
                            day = Integer.parseInt(str);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[220]++;
                            dayOfWeek = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[221]++;
                            advance = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[222]++;
                        } catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[119]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[223]++;
                            int index = str.indexOf(">=");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[224]++;
int CodeCoverConditionCoverageHelper_C61;
                            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((index > 0) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[120]++;
                                day = Integer.parseInt(str.substring(index + 2));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[225]++;
                                dayOfWeek = parseDayOfWeek(str.substring(0, index));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[226]++;
                                advance = true;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[227]++;

                            } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[121]++;
                                index = str.indexOf("<=");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[228]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[229]++;
int CodeCoverConditionCoverageHelper_C62;
                                if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((index > 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[122]++;
                                    day = Integer.parseInt(str.substring(index + 2));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[230]++;
                                    dayOfWeek = parseDayOfWeek(str.substring(0, index));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[231]++;
                                    advance = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[232]++;

                                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[123]++;
                                    throw new IllegalArgumentException(str);
                                }
                            }
                        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[118]++;
}
  }
                    }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[233]++;
int CodeCoverConditionCoverageHelper_C63;

                    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[124]++;
                        str = st.nextToken();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[234]++;
                        zoneChar = parseZoneChar(str.charAt(str.length() - 1));
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[235]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[236]++;
int CodeCoverConditionCoverageHelper_C64;
                        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((str.equals("24:00")) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[126]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[237]++;
                            LocalDate date = (day == -1 ?
                                    new LocalDate(2001, month, 1).plusMonths(1) :
                                    new LocalDate(2001, month, day).plusDays(1));
                            advance = (day != -1);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[238]++;
                            month = date.getMonthOfYear();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[239]++;
                            day = date.getDayOfMonth();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[240]++;
                            dayOfWeek = ((dayOfWeek - 1 + 1) % 7) + 1;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[241]++;

                        } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[127]++;
                            millis = parseTime(str);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[242]++;
                        }

                    } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[125]++;}

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[115]++;}

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[113]++;}

            iMonthOfYear = month;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[243]++;
            iDayOfMonth = day;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[244]++;
            iDayOfWeek = dayOfWeek;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[245]++;
            iAdvanceDayOfWeek = advance;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[246]++;
            iMillisOfDay = millis;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[247]++;
            iZoneChar = zoneChar;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[248]++;
        }

        /**
         * Adds a recurring savings rule to the builder.
         */
        public void addRecurring(DateTimeZoneBuilder builder, String nameKey,
                                 int saveMillis, int fromYear, int toYear)
        {
            builder.addRecurringSavings(nameKey, saveMillis,
                                        fromYear, toYear,
                                        iZoneChar,
                                        iMonthOfYear,
                                        iDayOfMonth,
                                        iDayOfWeek,
                                        iAdvanceDayOfWeek,
                                        iMillisOfDay);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[249]++;
        }

        /**
         * Adds a cutover to the builder.
         */
        public void addCutover(DateTimeZoneBuilder builder, int year) {
            builder.addCutover(year,
                               iZoneChar,
                               iMonthOfYear,
                               iDayOfMonth,
                               iDayOfWeek,
                               iAdvanceDayOfWeek,
                               iMillisOfDay);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[250]++;
        }

        public String toString() {
            return
                "MonthOfYear: " + iMonthOfYear + "\n" +
                "DayOfMonth: " + iDayOfMonth + "\n" +
                "DayOfWeek: " + iDayOfWeek + "\n" +
                "AdvanceDayOfWeek: " + iAdvanceDayOfWeek + "\n" +
                "MillisOfDay: " + iMillisOfDay + "\n" +
                "ZoneChar: " + iZoneChar + "\n";
        }
    }

    private static class Rule {
        public final String iName;
        public final int iFromYear;
        public final int iToYear;
        public final String iType;
        public final DateTimeOfYear iDateTimeOfYear;
        public final int iSaveMillis;
        public final String iLetterS;

        Rule(StringTokenizer st) {
            iName = st.nextToken().intern();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[251]++;
            iFromYear = parseYear(st.nextToken(), 0);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[252]++;
            iToYear = parseYear(st.nextToken(), iFromYear);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[253]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[254]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((iToYear < iFromYear) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[128]++;
                throw new IllegalArgumentException();

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[129]++;}
            iType = parseOptional(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[255]++;
            iDateTimeOfYear = new DateTimeOfYear(st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[256]++;
            iSaveMillis = parseTime(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[257]++;
            iLetterS = parseOptional(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[258]++;
        }

        /**
         * Adds a recurring savings rule to the builder.
         */
        public void addRecurring(DateTimeZoneBuilder builder, String nameFormat) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[259]++;
            String nameKey = formatName(nameFormat);
            iDateTimeOfYear.addRecurring
                (builder, nameKey, iSaveMillis, iFromYear, iToYear);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[260]++;
        }

        private String formatName(String nameFormat) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[261]++;
            int index = nameFormat.indexOf('/');
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[262]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((index > 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[130]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[263]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((iSaveMillis == 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[132]++;
                    // Extract standard name.
                    return nameFormat.substring(0, index).intern();

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[133]++;
                    return nameFormat.substring(index + 1).intern();
                }

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[131]++;}
            index = nameFormat.indexOf("%s");
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[264]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[265]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[134]++;
                return nameFormat;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[135]++;}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[266]++;
            String left = nameFormat.substring(0, index);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[267]++;
            String right = nameFormat.substring(index + 2);
            String name;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[268]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((iLetterS == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[136]++;
                name = left.concat(right);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[269]++;

            } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[137]++;
                name = left + iLetterS + right;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[270]++;
            }
            return name.intern();
        }

        public String toString() {
            return
                "[Rule]\n" + 
                "Name: " + iName + "\n" +
                "FromYear: " + iFromYear + "\n" +
                "ToYear: " + iToYear + "\n" +
                "Type: " + iType + "\n" +
                iDateTimeOfYear +
                "SaveMillis: " + iSaveMillis + "\n" +
                "LetterS: " + iLetterS + "\n";
        }
    }

    private static class RuleSet {
        private List<Rule> iRules;

        RuleSet(Rule rule) {
            iRules = new ArrayList<Rule>();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[271]++;
            iRules.add(rule);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[272]++;
        }

        void addRule(Rule rule) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[273]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((rule.iName.equals(iRules.get(0).iName)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[138]++;
                throw new IllegalArgumentException("Rule name mismatch");

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[139]++;}
            iRules.add(rule);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[274]++;
        }

        /**
         * Adds recurring savings rules to the builder.
         */
        public void addRecurring(DateTimeZoneBuilder builder, String nameFormat) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[275]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[37]++;


int CodeCoverConditionCoverageHelper_C71;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((i<iRules.size()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[37]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[38]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[39]++;
}
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[276]++;
                Rule rule = iRules.get(i);
                rule.addRecurring(builder, nameFormat);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[277]++;
            }
        }
    }

    private static class Zone {
        public final String iName;
        public final int iOffsetMillis;
        public final String iRules;
        public final String iFormat;
        public final int iUntilYear;
        public final DateTimeOfYear iUntilDateTimeOfYear;

        private Zone iNext;

        Zone(StringTokenizer st) {
            this(st.nextToken(), st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[278]++;
        }

        private Zone(String name, StringTokenizer st) {
            iName = name.intern();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[279]++;
            iOffsetMillis = parseTime(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[280]++;
            iRules = parseOptional(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[281]++;
            iFormat = st.nextToken().intern();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[282]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[283]++;

            int year = Integer.MAX_VALUE;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[284]++;
            DateTimeOfYear dtOfYear = getStartOfYear();
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[285]++;
int CodeCoverConditionCoverageHelper_C72;

            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[140]++;
                year = Integer.parseInt(st.nextToken());
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[286]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[287]++;
int CodeCoverConditionCoverageHelper_C73;
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[142]++;
                    dtOfYear = new DateTimeOfYear(st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[288]++;

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[143]++;}

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[141]++;}

            iUntilYear = year;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[289]++;
            iUntilDateTimeOfYear = dtOfYear;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[290]++;
        }

        void chain(StringTokenizer st) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[291]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((iNext != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[144]++;
                iNext.chain(st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[292]++;

            } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[145]++;
                iNext = new Zone(iName, st);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[293]++;
            }
        }

        /*
        public DateTimeZone buildDateTimeZone(Map ruleSets) {
            DateTimeZoneBuilder builder = new DateTimeZoneBuilder();
            addToBuilder(builder, ruleSets);
            return builder.toDateTimeZone(iName);
        }
        */

        /**
         * Adds zone info to the builder.
         */
        public void addToBuilder(DateTimeZoneBuilder builder, Map<String, RuleSet> ruleSets) {
            addToBuilder(this, builder, ruleSets);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[294]++;
        }

        private static void addToBuilder(Zone zone,
                                         DateTimeZoneBuilder builder,
                                         Map<String, RuleSet> ruleSets)
        {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[295]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[40]++;


int CodeCoverConditionCoverageHelper_C75;
            for (;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); zone = zone.iNext) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[40]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[41]--;
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.loops[42]++;
}
                builder.setStandardOffset(zone.iOffsetMillis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[296]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[297]++;
int CodeCoverConditionCoverageHelper_C76;

                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((zone.iRules == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[146]++;
                    builder.setFixedSavings(zone.iFormat, 0);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[298]++;

                } else {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[147]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[299]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                    try {
CodeCoverTryBranchHelper_Try5 = true;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[300]++;
                        // Check if iRules actually just refers to a savings.
                        int saveMillis = parseTime(zone.iRules);
                        builder.setFixedSavings(zone.iFormat, saveMillis);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[301]++;
                    }
                    catch (Exception e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[149]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[302]++;
                        RuleSet rs = ruleSets.get(zone.iRules);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[303]++;
int CodeCoverConditionCoverageHelper_C77;
                        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((rs == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[150]++;
                            throw new IllegalArgumentException
                                ("Rules not found: " + zone.iRules);

                        } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[151]++;}
                        rs.addRecurring(builder, zone.iFormat);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[304]++;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[148]++;
}
  }
                }
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[305]++;
int CodeCoverConditionCoverageHelper_C78;

                if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((zone.iUntilYear == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[152]++;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[306]++;
                    break;

                } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[153]++;}

                zone.iUntilDateTimeOfYear.addCutover(builder, zone.iUntilYear);
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[307]++;
            }
        }

        public String toString() {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[308]++;
            String str =
                "[Zone]\n" + 
                "Name: " + iName + "\n" +
                "OffsetMillis: " + iOffsetMillis + "\n" +
                "Rules: " + iRules + "\n" +
                "Format: " + iFormat + "\n" +
                "UntilYear: " + iUntilYear + "\n" +
                iUntilDateTimeOfYear;
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.statements[309]++;
int CodeCoverConditionCoverageHelper_C79;

            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((iNext == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[154]++;
                return str;

            } else {
  CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5.branches[155]++;}

            return str + "...\n" + iNext.toString();
        }
    }
}

class CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5 ());
  }
    public static long[] statements = new long[310];
    public static long[] branches = new long[156];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[80];
  static {
    final String SECTION_NAME = "org.joda.time.tz.ZoneInfoCompiler.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,0,2,2,3,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 79; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$234cfny5tunz78cwcxg82bc5w8hzmqvz5 () {
    super("org.joda.time.tz.ZoneInfoCompiler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 309; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 155; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 79; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.tz.ZoneInfoCompiler.java");
      for (int i = 1; i <= 309; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 155; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 79; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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


