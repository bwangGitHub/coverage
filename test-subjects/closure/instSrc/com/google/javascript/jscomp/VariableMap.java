/*
 * Copyright 2005 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Stores the mapping from original variable name to new variable names.
 * @see RenameVars
 */
public class VariableMap {
  static {
    CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.ping();
  }


  /** Maps original source name to new name */
  private final ImmutableMap<String, String> map;

  /** Maps new name to source name, lazily initialized */
  private ImmutableMap<String, String> reverseMap = null;
  {
    CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[1]++;
  }

  private static final char SEPARATOR = ':';
  static {
    CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[2]++;
  }

  VariableMap(Map<String, String> map) {
    this.map = ImmutableMap.copyOf(map);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[3]++;
  }

  /**
   * Given an original variable name, look up new name, may return null
   * if it's not found.
   */
  public String lookupNewName(String sourceName) {
    return map.get(sourceName);
  }

  /**
   * Given a new variable name, lookup the source name, may return null
   * if it's not found.
   */
  public String lookupSourceName(String newName) {
    initReverseMap();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[4]++;
    return reverseMap.get(newName);
  }

  /**
   * Initializes the reverse map.
   */
  private synchronized void initReverseMap() {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((reverseMap == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[1]++;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[6]++;
      ImmutableMap.Builder<String, String> rm = ImmutableMap.builder();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[1]++;


      for (Map.Entry<String, String> entry : map.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[1]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[2]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[3]++;
}
        rm.put(entry.getValue(), entry.getKey());
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[8]++;
      }
      reverseMap = rm.build();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[9]++;

    } else {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[2]++;}
  }

  /**
   * Returns an unmodifiable mapping from original names to new names.
   */
  public Map<String, String> getOriginalNameToNewNameMap() {
    return map;
  }

  /**
   * Returns an unmodifiable mapping from new names to original names.
   */
  public Map<String, String> getNewNameToOriginalNameMap() {
    initReverseMap();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[10]++;
    return reverseMap;
  }

  /**
   * Saves the variable map to a file.
   */
  public void save(String filename) throws IOException {
    Files.write(toBytes(), new File(filename));
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[11]++;
  }

  /**
   * Reads the variable map from a file written via {@link #save(String)}.
   */
  public static VariableMap load(String filename) throws IOException {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[12]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      return fromBytes(Files.toByteArray(new File(filename)));
    } catch (ParseException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[4]++;
      // Wrap parse exception for backwards compatibility.
      throw new IOException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[3]++;
}
  }
  }

  /**
   * Serializes the variable map to a byte array.
   */
  public byte[] toBytes() {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[13]++;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[14]++;
    Writer writer = new OutputStreamWriter(baos, Charsets.UTF_8);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[15]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[16]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[4]++;


      for (Map.Entry<String, String> entry : map.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[4]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[5]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[6]++;
}
        writer.write(escape(entry.getKey()));
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[17]++;
        writer.write(SEPARATOR);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[18]++;
        writer.write(escape(entry.getValue()));
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[19]++;
        writer.write('\n');
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[20]++;
      }
      writer.close();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[21]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[6]++;
      // Note: A ByteArrayOutputStream never throws IOException. This try/catch
      // is just here to appease the Java compiler.
      throw new RuntimeException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[5]++;
}
  }
    return baos.toByteArray();
  }

  /**
   * Deserializes the variable map from a byte array returned by
   * {@link #toBytes()}.
   */
  public static VariableMap fromBytes(byte[] bytes) throws ParseException {
    Iterable<String> lines;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[22]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
    try {
CodeCoverTryBranchHelper_Try3 = true;
      lines = CharStreams.readLines(CharStreams.newReaderSupplier(
          ByteStreams.newInputStreamSupplier(bytes), Charsets.UTF_8));
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[23]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[8]++;
      // Note: An IOException is never thrown while reading from a byte array.
      // This try/catch is just here to appease the Java compiler.
      throw new RuntimeException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[7]++;
}
  }
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[24]++;

    ImmutableMap.Builder<String, String> map = ImmutableMap.builder();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[25]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[7]++;



    for (String line : lines) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[7]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[8]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[9]++;
}
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[26]++;
      int pos = findIndexOfChar(line, SEPARATOR);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[27]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((pos <= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((pos == line.length() - 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[9]++;
        throw new ParseException("Bad line: " + line, 0);

      } else {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[10]++;}
      map.put(
          unescape(line.substring(0, pos)),
          unescape(line.substring(pos + 1)));
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[28]++;
    }
    return new VariableMap(map.build());
  }

  private static String escape(String value) {
    return value.replace("\\", "\\\\")
        .replace(":", "\\:")
        .replace("\n", "\\n");
  }

  private static int findIndexOfChar(String value, char stopChar) {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[29]++;
    int len = value.length();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[30]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[10]++;


int CodeCoverConditionCoverageHelper_C3;
    for (int i=0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[10]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[11]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[12]++;
}
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[31]++;
      char c = value.charAt(i);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((++i < len) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[11]++;
        c = value.charAt(i);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[33]++;

      } else {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[12]++;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[34]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c == stopChar) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)){
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[13]++;
        return i;

      } else {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[14]++;}
}
    }
    return -1;
  }

  private static String unescape(CharSequence value) {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[35]++;
    StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[36]++;
    int len = value.length();
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[37]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[13]++;


int CodeCoverConditionCoverageHelper_C6;
    for (int i=0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i<len) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[13]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[14]--;
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.loops[15]++;
}
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[38]++;
      char c = value.charAt(i);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((++i < len) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[15]++;
        c = value.charAt(i);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[40]++;

      } else {
  CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.branches[16]++;}
      sb.append(c);
CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h.statements[41]++;
    }
    return sb.toString();
  }

  /**
   * Initializes the variable map from an existing map.
   * @param map The map to use from original names to generated names. It is
   *   copied and changes to the specified map will not affect the returned
   *   object.
   */
  public static VariableMap fromMap(Map<String, String> map) {
    return new VariableMap(map);
  }

  @VisibleForTesting
  Map<String, String> toMap() {
    return map;
  }
}

class CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h ());
  }
    public static long[] statements = new long[42];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.VariableMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,2,1,1,2};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$543o5lgghm3s7ruqm05gv4s4h () {
    super("com.google.javascript.jscomp.VariableMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 41; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.VariableMap.java");
      for (int i = 1; i <= 41; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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
