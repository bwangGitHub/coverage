/*
 * Copyright 2010 The Closure Compiler Authors.
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

package com.google.debugging.sourcemap;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.debugging.sourcemap.proto.Mapping.OriginalMapping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Class for parsing version 2 of the SourceMap format, as produced by the
 * Closure Compiler, etc.
 * @author johnlenz@google.com (John Lenz)
 * @author jschorr@google.com (Joseph Schorr)
 */
public class SourceMapConsumerV2 implements SourceMapConsumer {
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.ping();
  }

  /**
   * The character map for each line. If a line does not have an entry,
   * then it has not yet been decoded.
   */
  private Map<Integer, List<Integer>> characterMap = null;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[1]++;
  }

  /**
   * The undecoded line maps. Will be accessed to decode lines as needed.
   */
  private JSONArray lineMaps = null;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[2]++;
  }

  /**
   * Map of Mapping IDs to the actual mapping object.
   */
  private List<OriginalMapping> mappings;

  public SourceMapConsumerV2() {}

  /**
   * Parses the given contents containing a source map.
   */
  @Override
  public void parse(String contents) throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[3]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[4]++;
      JSONObject sourceMapRoot = new JSONObject(contents);
      parse(sourceMapRoot);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[5]++;
    } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[2]++;
      throw new SourceMapParseException("JSON parse exception: " + ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[1]++;
}
  }
  }

  /**
   * Parses the given contents containing a source map.
   */
  public void parse(JSONObject sourceMapRoot) throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[6]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      parseInternal(sourceMapRoot);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[7]++;
    } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[4]++;
      throw new SourceMapParseException("JSON parse exception: " + ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[3]++;
}
  }
  }

  /**
   * Parses the given contents as version 2 of a SourceMap.
   */
  private void parseInternal(JSONObject sourceMapRoot)
      throws JSONException, SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[8]++;

    // Check basic assertions about the format.
    int version = sourceMapRoot.getInt("version");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((version != 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[5]++;
      throw new SourceMapParseException("Unknown version: " + version);

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[6]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[10]++;

    String file = sourceMapRoot.getString("file");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((file.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[7]++;
      throw new SourceMapParseException("File entry is missing or empty");

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[8]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[12]++;

    int lineCount = sourceMapRoot.getInt("lineCount");
    lineMaps = sourceMapRoot.getJSONArray("lineMaps");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[13]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lineCount != lineMaps.length()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[9]++;
      throw new SourceMapParseException(
          "lineMaps length does not match lineCount");

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[10]++;}

    // Build an empty character map. The character map will be filled in as
    // lines are requested.
    characterMap = Maps.newHashMap();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[15]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[16]++;

    JSONArray sources = sourceMapRoot.getJSONArray("sources");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[17]++;
    JSONArray names = sourceMapRoot.has("names")
        ? sourceMapRoot.getJSONArray("names") : null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[18]++;

    // Create each of the OriginalMappings.
    JSONArray jsonMappings = sourceMapRoot.getJSONArray("mappings");
    mappings = Lists.newArrayListWithCapacity(lineCount);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[19]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[20]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < jsonMappings.length()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.loops[1]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.loops[2]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.loops[3]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[21]++;
      JSONArray entry = jsonMappings.getJSONArray(i);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[22]++;

      // The name can be accessed in two ways: Directly (i.e. a string) or
      // indirectly (i.e. an index into the name map).
      String name = entry.optString(3, "");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((names != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[11]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[24]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[25]++;
          int nameIndex = entry.getInt(3);
          name = names.getString(nameIndex);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[26]++;
        } catch (JSONException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[14]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[13]++;
}
  }

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[12]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[27]++;

      // Build the new OriginalMapping entry.
      String sourceFile = sources.getString(entry.getInt(0));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[28]++;
      int lineNumber = entry.getInt(1);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[29]++;
      int column = entry.getInt(2);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[30]++;

      OriginalMapping.Builder builder = OriginalMapping.newBuilder()
          .setOriginalFile(sourceFile)
          .setLineNumber(lineNumber)
          .setColumnPosition(column)
          .setIdentifier(name);
      mappings.add(builder.build());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[31]++;
    }
  }

  @Override
  public OriginalMapping getMappingForLine(int lineNumber, int columnIndex) {
    // Normalize the line and column numbers to 0.
    lineNumber--;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[32]++;
    columnIndex--;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[33]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((lineNumber >= lineMaps.length()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[15]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[16]++;}

    Preconditions.checkState(lineNumber >= 0, "Line number must be >= 0");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[35]++;
    Preconditions.checkState(columnIndex >= 0, "Column index must be >= 0");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[36]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((characterMap.containsKey(lineNumber)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[17]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[38]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
      // Parse the line map entry and place it into the character map.
      try {
CodeCoverTryBranchHelper_Try4 = true;
        characterMap.put(lineNumber,
            SourceMapLineDecoder.decodeLine(lineMaps.getString(lineNumber)));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[39]++;
      } catch (JSONException jse) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[20]++;
        throw new IllegalStateException(
            "JSON exception when retrieving line map", jse);
      } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[19]++;
}
  }

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[18]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[40]++;

    List<Integer> map = characterMap.get(lineNumber);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((map == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((map.size() <= columnIndex) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[21]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[22]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[42]++;

    int index = map.get(columnIndex);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[23]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.branches[24]++;}
    Preconditions.checkState(index < mappings.size(),
        "Invalid mapping reference");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9.statements[44]++;
    return mappings.get(index);
  }
}

class CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9 ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapConsumerV2.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8ie81aau9 () {
    super("com.google.debugging.sourcemap.SourceMapConsumerV2.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapConsumerV2.java");
      for (int i = 1; i <= 44; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

