/*
 * Copyright 2009 The Closure Compiler Authors.
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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Shorts;
import com.google.debugging.sourcemap.proto.Mapping.OriginalMapping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing and representing a SourceMap, as produced by the
 * Closure Compiler, Caja-Compiler, etc.
 */
public class SourceMapConsumerV1 implements SourceMapConsumer {
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.ping();
  }

  private static final String LINEMAP_HEADER = "/** Begin line maps. **/";
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[1]++;
  }
  private static final String FILEINFO_HEADER =
      "/** Begin file information. **/";
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[2]++;
  }

  private static final String DEFINITION_HEADER =
      "/** Begin mapping definitions. **/";
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[3]++;
  }

  /**
   * Internal class for parsing the SourceMap. Used to maintain parser
   * state in an easy to use instance.
   */
  private static class ParseState {
    final String contents;
    int currentPosition = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[4]++;
  }

    ParseState(String contents) {
      this.contents = contents;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[5]++;
    }

    /** Reads a line, returning null at EOF. */
    String readLineOrNull() {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((currentPosition >= contents.length()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[1]++;
        return null;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[2]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[7]++;
      int index = contents.indexOf('\n', currentPosition);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[3]++;
        index = contents.length();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[9]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[4]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[10]++;
      String line = contents.substring(currentPosition, index);
      currentPosition = index + 1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[11]++;
      return line;
    }

    /** Reads a line, throwing a parse exception at EOF. */
    String readLine() throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[12]++;
      String line = readLineOrNull();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((line == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[5]++;
        fail("EOF");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[14]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[6]++;}
      return line;
    }

    /**
     * Reads a line and throws an parse exception if the line does not
     * equal the argument.
     */
    void expectLine(String expect) throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[15]++;
      String line = readLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((expect.equals(line)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[7]++;
        fail("Expected " + expect + " got " + line);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[17]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[8]++;}
    }

    /**
     * Indicates that parsing has failed by throwing a parse exception.
     */
    void fail(String message) throws SourceMapParseException {
      throw new SourceMapParseException(message);
    }
  }

  /**
   * Mapping from a line number (0-indexed), to a list of mapping IDs, one for
   * each character on the line. For example, if the array for line 2 is
   * {@code [4,,,,5,6,,,7]}, then there will be the entry:
   *
   * <pre>
   * 1 => {4, 4, 4, 4, 5, 6, 6, 6, 7}
   * </pre>
   */
  private ImmutableList<ImmutableList<LineFragment>> characterMap;

  /**
   * Map of Mapping IDs to the actual mapping object.
   */
  private ImmutableList<SourceFile> mappings;

  /**
   * Parses the given contents containing a source map.
   */
  @Override
  public void parse(String contents) throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[18]++;
    ParseState parser = new ParseState(contents);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[19]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      parseInternal(parser);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[20]++;
    } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[10]++;
      parser.fail("JSON parse exception: " + ex);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[21]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[9]++;
}
  }
  }

  /**
   * Parses the first section of the source map file that has character
   * mappings.
   * @param parser The parser to use
   * @param lineCount The number of lines in the generated JS
   * @return The max id found in the file
   */
  private int parseCharacterMap(
      ParseState parser, int lineCount,
      ImmutableList.Builder<ImmutableList<LineFragment>> characterMapBuilder)
      throws SourceMapParseException, JSONException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[22]++;
    int maxID = -1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
    // [0,,,,,,1,2]
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < lineCount) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[1]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[2]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[3]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[24]++;
      String currentLine = parser.readLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;

      // Blank lines are allowed in the spec to indicate no mapping
      // information for the line.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((currentLine.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[11]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[26]++;
        continue;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[12]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[27]++;

      ImmutableList.Builder<LineFragment> fragmentList =
          ImmutableList.builder();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[28]++;
      // We need the start index to initialize this, needs to be done in the
      // loop.
      LineFragment myLineFragment = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[29]++;

      JSONArray charArray = new JSONArray(currentLine);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[30]++;
      int lastID = -1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[31]++;
      int startID = Integer.MIN_VALUE;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[32]++;
      List<Byte> currentOffsets = Lists.newArrayList();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
      for (int j = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((j < charArray.length()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[4]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[5]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[6]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[34]++;
        // Keep track of the current mappingID, if the next element in the
        // array is empty we reuse the existing mappingID for the column.
        int mappingID = lastID;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((charArray.isNull(j)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[13]++;
          mappingID = charArray.optInt(j);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[36]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((mappingID > maxID) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[15]++;
            maxID = mappingID;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[38]++;

          } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[14]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((startID == Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[17]++;
          startID = mappingID;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[40]++;

        } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[18]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[41]++;
int CodeCoverConditionCoverageHelper_C11;
          // If the difference is bigger than a byte we need to keep track of
          // a new line fragment with a new start value.
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((mappingID - lastID > Byte.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((mappingID - lastID < Byte.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[19]++;
            myLineFragment = new LineFragment(
                startID, Bytes.toArray(currentOffsets));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[42]++;
            currentOffsets.clear();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[43]++;
            // Start a new section.
            fragmentList.add(myLineFragment);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[44]++;
            startID = mappingID;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[45]++;

          } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[20]++;
            currentOffsets.add((byte) (mappingID - lastID));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[46]++;
          }
        }

        lastID = mappingID;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[47]++;
      }
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((startID != Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[21]++;
        myLineFragment = new LineFragment(
            startID, Bytes.toArray(currentOffsets));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[49]++;
        fragmentList.add(myLineFragment);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[50]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[22]++;}
      characterMapBuilder.add(fragmentList.build());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[51]++;
    }
    return maxID;
  }

  private class FileName {
    private final String dir;
    private final String name;

    FileName(String directory, String name) {
      this.dir = directory;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[52]++;
      this.name = name;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[53]++;
    }
  }

  /**
   * Split the file into a filename/directory pair.
   *
   * @param interner The interner to use for interning the strings.
   * @param input The input to split.
   * @return The pair of directory, filename.
   */
  private FileName splitFileName(
      Interner<String> interner, String input) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[54]++;
    int hashIndex = input.lastIndexOf('/');
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[55]++;
    String dir = interner.intern(input.substring(0, hashIndex + 1));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[56]++;
    String fileName = interner.intern(input.substring(hashIndex + 1));
    return new FileName(dir, fileName);
  }

  /**
   * Parse the file mappings section of the source map file.  This maps the
   * ids to the filename, line number and column number in the original
   * files.
   * @param parser The parser to get the data from.
   * @param maxID The maximum id found in the character mapping section.
   */
  private void parseFileMappings(ParseState parser, int maxID)
      throws SourceMapParseException, JSONException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[57]++;
    // ['d.js', 3, 78, 'foo']
    // Intern the strings to save memory.
    Interner<String> interner = Interners.newStrongInterner();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[58]++;
    ImmutableList.Builder<SourceFile> mappingsBuilder = ImmutableList.builder();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[59]++;

    // Setup all the arrays to keep track of the various details about the
    // source file.
    ArrayList<Byte> lineOffsets = Lists.newArrayList();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[60]++;
    ArrayList<Short> columns = Lists.newArrayList();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[61]++;
    ArrayList<String> identifiers = Lists.newArrayList();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[62]++;

    // The indexes and details about the current position in the file to do
    // diffs against.
    String currentFile = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[63]++;
    int lastLine = -1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[64]++;
    int startLine = -1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[65]++;
    int startMapId = -1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[66]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;
    for (int mappingId = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((mappingId <= maxID) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); ++mappingId) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[7]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[8]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[9]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[67]++;
      String currentLine = parser.readLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[68]++;
      JSONArray mapArray = new JSONArray(currentLine);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[69]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((mapArray.length() < 3) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[23]++;
        parser.fail("Invalid mapping array");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[70]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[24]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[71]++;

      // Split up the file and directory names to reduce memory usage.
      String myFile = mapArray.getString(0);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[72]++;
      int line = mapArray.getInt(1);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[73]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((myFile.equals(currentFile)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 (((line - lastLine) > Byte.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (((line - lastLine) < Byte.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[25]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((currentFile != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[27]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[75]++;
          FileName dirFile = splitFileName(interner, currentFile);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[76]++;
          SourceFile.Builder builder = SourceFile.newBuilder()
              .setDir(dirFile.dir)
              .setFileName(dirFile.name)
              .setStartLine(startLine)
              .setStartMapId(startMapId)
              .setLineOffsets(lineOffsets)
              .setColumns(columns)
              .setIdentifiers(identifiers);
          mappingsBuilder.add(builder.build());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[77]++;

        } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[28]++;}
        // Reset all the positions back to the start and clear out the arrays
        // to start afresh.
        currentFile = myFile;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[78]++;
        startLine = line;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[79]++;
        lastLine = line;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[80]++;
        startMapId = mappingId;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[81]++;
        columns.clear();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[82]++;
        lineOffsets.clear();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[83]++;
        identifiers.clear();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[84]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[26]++;}
      // We need to add on the columns and identifiers for all the lines, even
      // for the first line.
      lineOffsets.add((byte) (line - lastLine));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[85]++;
      columns.add((short) mapArray.getInt(2));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[86]++;
      identifiers.add(interner.intern(mapArray.optString(3, "")));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[87]++;
      lastLine = line;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[88]++;
    }
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[89]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((currentFile != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[29]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[90]++;
      FileName dirFile = splitFileName(interner, currentFile);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[91]++;
      SourceFile.Builder builder = SourceFile.newBuilder()
          .setDir(dirFile.dir)
          .setFileName(dirFile.name)
          .setStartLine(startLine)
          .setStartMapId(startMapId)
          .setLineOffsets(lineOffsets)
          .setColumns(columns)
          .setIdentifiers(identifiers);
      mappingsBuilder.add(builder.build());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[92]++;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[30]++;}
    mappings = mappingsBuilder.build();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[93]++;
  }

  private void parseInternal(ParseState parser)
      throws SourceMapParseException, JSONException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[94]++;

    // /** Begin line maps. **/{ count: 2 }
    String headerCount = parser.readLine();
    Preconditions.checkArgument(headerCount.startsWith(LINEMAP_HEADER),
        "Expected %s", LINEMAP_HEADER);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[95]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[96]++;
    JSONObject countObject = new JSONObject(
        headerCount.substring(LINEMAP_HEADER.length()));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[97]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((countObject.has("count")) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[31]++;
      parser.fail("Missing 'count'");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[98]++;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[32]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[99]++;

    int lineCount = countObject.getInt("count");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[100]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((lineCount <= 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[33]++;
      parser.fail("Count must be >= 1");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[101]++;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[34]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[102]++;
    ImmutableList.Builder<ImmutableList<LineFragment>> characterMapBuilder =
        ImmutableList.builder();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[103]++;
    int maxId = parseCharacterMap(parser, lineCount, characterMapBuilder);
    characterMap = characterMapBuilder.build();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[104]++;

    // /** Begin file information. **/
    parser.expectLine(FILEINFO_HEADER);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[105]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[106]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[10]++;


int CodeCoverConditionCoverageHelper_C20;

    // File information. Not used, so we just consume it.
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < lineCount) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[10]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[11]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[12]++;
}
      parser.readLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[107]++;
    }

    // /** Begin mapping definitions. **/
    parser.expectLine(DEFINITION_HEADER);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[108]++;

    parseFileMappings(parser, maxId);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[109]++;
  }

  @Override
  public OriginalMapping getMappingForLine(int lineNumber, int columnIndex) {
    Preconditions.checkNotNull(characterMap, "parse() must be called first");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[110]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[111]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((lineNumber < 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((lineNumber > characterMap.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((columnIndex < 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[35]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[36]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[112]++;

    List<LineFragment> lineFragments = characterMap.get(lineNumber - 1);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[113]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((lineFragments == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((lineFragments.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[37]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[38]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[114]++;

    int columnOffset = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[115]++;
    // The code assumes everything past the end is the same as the last item
    // so we default to the last item in the line.
    LineFragment lastFragment = lineFragments.get(lineFragments.size() - 1);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[116]++;
    int mapId = lastFragment.valueAtColumn(lastFragment.length());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[117]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[13]++;


    for (LineFragment lineFragment : lineFragments) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[13]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[14]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[15]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[118]++;
      int columnPosition = columnIndex - columnOffset;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[119]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((columnPosition <= lineFragment.length()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[39]++;
        mapId = lineFragment.valueAtColumn(columnPosition);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[120]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[121]++;
        break;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[40]++;}
      columnOffset += lineFragment.length();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[122]++;
    }
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[123]++;
int CodeCoverConditionCoverageHelper_C24;

    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((mapId < 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[41]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[42]++;}

    return getMappingFromId(mapId);
  }

  /**
   * Do a binary search for the correct mapping array to use.
   *
   * @param mapId The mapping array to find
   * @return The source file mapping to use.
   */
  private SourceFile binarySearch(int mapId) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[124]++;
    int lower = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[125]++;
    int upper = mappings.size() - 1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[126]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[16]++;


int CodeCoverConditionCoverageHelper_C25;

    while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((lower <= upper) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[16]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[17]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[18]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[127]++;
      int middle = lower + (upper - lower) / 2;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[128]++;
      SourceFile middleCompare = mappings.get(middle);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[129]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((mapId < middleCompare.getStartMapId()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[43]++;
        upper = middle - 1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[130]++;

      } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[44]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[131]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((mapId < (middleCompare.getStartMapId()
            + middleCompare.getLength())) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[45]++;
        return middleCompare;

      } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[46]++;
        lower = middle + 1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[132]++;
      }
}
    }

    return null;
  }

  /**
   * Find the original mapping for the specified mapping id.
   *
   * @param mapID The mapID to lookup.
   * @return The originalMapping protocol buffer for the id.
   */
  private OriginalMapping getMappingFromId(int mapID) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[133]++;
    SourceFile match = binarySearch(mapID);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[134]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((match == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[47]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.branches[48]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[135]++;
    int pos = mapID - match.getStartMapId();
    return match.getOriginalMapping(pos);
  }

  /**
   * Keeps track of the information about the line in a more compact way.  It
   * represents a fragment of the line starting at a specific index and then
   * looks at offsets from that index stored as a byte, this dramatically
   * reduces the memory usage of this array.
   */
  private static final class LineFragment {
    private final int startIndex;
    private final byte[] offsets;

    /**
     * Create a new line fragment to store information about.
     *
     * @param startIndex The start index for this line.
     * @param offsets The byte array of offsets to store.
     */
    LineFragment(int startIndex, byte[] offsets) {
      this.startIndex = startIndex;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[136]++;
      this.offsets = offsets;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[137]++;
    }

    /**
     * The length of columns stored in the line.  One is added because we
     * store the start index outside of the offsets array.
     */
    int length() {
      return offsets.length + 1;
    }

    /**
     * Find the mapping id at the specified column.
     *
     * @param column The column to lookup
     * @return the value at that point in the column
     */
    int valueAtColumn(int column) {
      Preconditions.checkArgument(column > 0);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[138]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[139]++;
      int pos = startIndex;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[140]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[19]++;


int CodeCoverConditionCoverageHelper_C29;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i < column - 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[19]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[20]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[21]++;
}
        pos += offsets[i];
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[141]++;
      }
      return pos;
    }
  }

  /**
   * Keeps track of data about the source file itself.  This is contains a list
   * of line offsets and columns to track down where exactly a line falls into
   * the data.
   */
  private static final class SourceFile {
    final String dir;
    final String fileName;
    final int startMapId;
    final int startLine;
    final byte[] lineOffsets;
    final short[] columns;
    final String[] identifiers;

    private SourceFile(
        String dir, String fileName, int startLine, int startMapId,
        byte[] lineOffsets, short[] columns, String[] identifiers) {
      this.fileName = Preconditions.checkNotNull(fileName);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[142]++;
      this.dir = Preconditions.checkNotNull(dir);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[143]++;
      this.startLine = startLine;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[144]++;
      this.startMapId = startMapId;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[145]++;
      this.lineOffsets = Preconditions.checkNotNull(lineOffsets);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[146]++;
      this.columns = Preconditions.checkNotNull(columns);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[147]++;
      this.identifiers = Preconditions.checkNotNull(identifiers);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[148]++;
      Preconditions.checkArgument(lineOffsets.length == columns.length &&
          columns.length == identifiers.length);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[149]++;
    }

    private SourceFile(int startMapId) {
      // Only used for binary searches.
      this.startMapId = startMapId;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[150]++;

      this.fileName = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[151]++;
      this.dir = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[152]++;
      this.startLine = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[153]++;
      this.lineOffsets = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[154]++;
      this.columns = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[155]++;
      this.identifiers = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[156]++;
    }

    /**
     * Returns the number of elements in this source file.
     */
    int getLength() {
      return lineOffsets.length;
    }

    /**
     * Returns the number of elements in this source file.
     */
    int getStartMapId() {
      return startMapId;
    }

    /**
     * Creates an original mapping from the data.
     *
     * @param offset The offset into the array to find the mapping for.
     * @return A new original mapping object.
     */
    OriginalMapping getOriginalMapping(int offset) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[157]++;
      int lineNumber = this.startLine;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[158]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[22]++;


int CodeCoverConditionCoverageHelper_C30;
      // Offset is an index into this array and we need to include it.
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i <= offset) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[22]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[23]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.loops[24]++;
}
        lineNumber += lineOffsets[i];
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[159]++;
      }
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[160]++;
      OriginalMapping.Builder builder = OriginalMapping.newBuilder()
          .setOriginalFile(dir + fileName)
          .setLineNumber(lineNumber)
          .setColumnPosition(columns[offset])
          .setIdentifier(identifiers[offset]);
      return builder.build();
    }

    /**
     * Builder to make a new SourceFile object.
     */
    static final class Builder {
      String dir;
      String fileName;
      int startMapId;
      int startLine;
      byte[] lineOffsets;
      short[] columns;
      String[] identifiers;

      Builder setDir(String dir) {
        this.dir = dir;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[161]++;
        return this;
      }

      Builder setFileName(String fileName) {
        this.fileName = fileName;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[162]++;
        return this;
      }

      Builder setStartMapId(int startMapId) {
        this.startMapId = startMapId;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[163]++;
        return this;
      }

      Builder setStartLine(int startLine) {
        this.startLine = startLine;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[164]++;
        return this;
      }

      Builder setLineOffsets(List<Byte> lineOffsets) {
        this.lineOffsets = Bytes.toArray(lineOffsets);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[165]++;
        return this;
      }

      Builder setColumns(List<Short> columns) {
        this.columns = Shorts.toArray(columns);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[166]++;
        return this;
      }

      Builder setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers.toArray(new String[0]);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t.statements[167]++;
        return this;
      }

      /**
       * Creates a new SourceFile from the parameters.
       */
      SourceFile build() {
        return new SourceFile(dir, fileName, startLine, startMapId,
            lineOffsets, columns, identifiers);
      }
    }

    static Builder newBuilder() {
      return new Builder();
    }
  }
}

class CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t ());
  }
    public static long[] statements = new long[168];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[31];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapConsumerV1.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,1,1,3,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 30; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w84d44g95t () {
    super("com.google.debugging.sourcemap.SourceMapConsumerV1.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 167; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 30; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapConsumerV1.java");
      for (int i = 1; i <= 167; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 30; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

