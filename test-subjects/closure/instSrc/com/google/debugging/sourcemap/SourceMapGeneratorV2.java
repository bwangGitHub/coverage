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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

/**
 * Collects information mapping the generated (compiled) source back to
 * its original source for debugging purposes.
 *
 */
public class SourceMapGeneratorV2 implements SourceMapGenerator {
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.ping();
  }


  private boolean validate = false;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[1]++;
  }

  private static final int UNMAPPED = -1;
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[2]++;
  }

  /**
   * A pre-order traversal ordered list of mappings stored in this map.
   */
  private List<Mapping> mappings = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[3]++;
  }

  /**
   * A map of source names to source name index
   */
  private LinkedHashMap<String, Integer> sourceFileMap =
      Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[4]++;
  }

  /**
   * A map of symbol names to symbol name index
   */
  private LinkedHashMap<String, Integer> originalNameMap =
      Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[5]++;
  }

  /**
   * Cache of the last mappings source name.
   */
  private String lastSourceFile = null;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[6]++;
  }

  /**
   * Cache of the last mappings source name index.
   */
  private int lastSourceFileIndex = -1;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[7]++;
  }

  /**
   * For validation store the last mapping added.
   */
  private Mapping lastMapping;

  /**
   * The position that the current source map is offset in the
   * buffer being used to generated the compiled source file.
   */
  private FilePosition offsetPosition = new FilePosition(0, 0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[8]++;
  }

  /**
   * The position that the current source map is offset in the
   * generated the compiled source file by the addition of a
   * an output wrapper prefix.
   */
  private FilePosition prefixPosition = new FilePosition(0, 0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[9]++;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    mappings.clear();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[10]++;
    lastMapping = null;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[11]++;
    sourceFileMap.clear();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[12]++;
    originalNameMap.clear();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[13]++;
    lastSourceFile = null;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[14]++;
    lastSourceFileIndex = -1;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[15]++;
    offsetPosition = new FilePosition(0, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[16]++;
    prefixPosition = new FilePosition(0, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[17]++;
  }

  /**
   * @param validate Whether to perform (potentially costly) validation on the
   * generated source map.
   */
  @Override
  @VisibleForTesting
  public void validate(boolean validate) {
    this.validate = validate;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[18]++;
  }

  /**
   * Sets the prefix used for wrapping the generated source file before
   * it is written. This ensures that the source map is adjusted for the
   * change in character offsets.
   *
   * @param prefix The prefix that is added before the generated source code.
   */
  @Override
  public void setWrapperPrefix(String prefix) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[19]++;
    // Determine the current line and character position.
    int prefixLine = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[20]++;
    int prefixIndex = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < prefix.length()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[1]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[2]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[3]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prefix.charAt(i) == '\n') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[1]++;
        prefixLine++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[23]++;
        prefixIndex = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[24]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[2]++;
        prefixIndex++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[25]++;
      }
    }

    prefixPosition = new FilePosition(prefixLine, prefixIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[26]++;
  }

  /**
   * Sets the source code that exists in the buffer for which the
   * generated code is being generated. This ensures that the source map
   * accurately reflects the fact that the source is being appended to
   * an existing buffer and as such, does not start at line 0, position 0
   * but rather some other line and position.
   *
   * @param offsetLine The index of the current line being printed.
   * @param offsetIndex The column index of the current character being printed.
   */
  @Override
  public void setStartingPosition(int offsetLine, int offsetIndex) {
    Preconditions.checkState(offsetLine >= 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[27]++;
    Preconditions.checkState(offsetIndex >= 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[28]++;
    offsetPosition = new FilePosition(offsetLine, offsetIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[29]++;
  }

  /**
   * Adds a mapping for the given node.  Mappings must be added in order.
   * @param startPosition The position on the starting line
   * @param endPosition The position on the ending line.
   */
  @Override
  public void addMapping(
      String sourceName, @Nullable String symbolName,
      FilePosition sourceStartPosition,
      FilePosition startPosition, FilePosition endPosition) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;

    // Don't bother if there is not sufficient information to be useful.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sourceStartPosition.getLine() < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[4]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[31]++;

    FilePosition adjustedStart = startPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[32]++;
    FilePosition adjustedEnd = endPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((offsetPosition.getLine() != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((offsetPosition.getColumn() != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[5]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[34]++;
      // If the mapping is found on the first line, we need to offset
      // its character position by the number of characters found on
      // the *last* line of the source file to which the code is
      // being generated.
      int offsetLine = offsetPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[35]++;
      int startOffsetPosition = offsetPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[36]++;
      int endOffsetPosition = offsetPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((startPosition.getLine() > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[7]++;
        startOffsetPosition = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[38]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[8]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((endPosition.getLine() > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[9]++;
        endOffsetPosition = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[40]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[10]++;}

      adjustedStart = new FilePosition(
          startPosition.getLine() + offsetLine,
          startPosition.getColumn() + startOffsetPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[41]++;

      adjustedEnd = new FilePosition(
          endPosition.getLine() + offsetLine,
          endPosition.getColumn() + endOffsetPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[42]++;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[6]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[43]++;

    // Create the new mapping.
    Mapping mapping = new Mapping();
    mapping.sourceFile = getSourceId(sourceName);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[44]++;
    mapping.originalPosition = sourceStartPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[45]++;
    mapping.originalName = symbolName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[46]++;
    mapping.startPosition = adjustedStart;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[47]++;
    mapping.endPosition = adjustedEnd;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[48]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[49]++;
int CodeCoverConditionCoverageHelper_C7;

    // Validate the mappings are in a proper order.
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lastMapping != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[11]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[50]++;
      int lastLine = lastMapping.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[51]++;
      int lastColumn = lastMapping.startPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[52]++;
      int nextLine = mapping.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[53]++;
      int nextColumn = mapping.startPosition.getColumn();
      Preconditions.checkState(nextLine > lastLine
          || (nextLine == lastLine && nextColumn >= lastColumn),
          "Incorrect source mappings order, previous : (%s,%s)\n"
          + "new : (%s,%s)\nnode : %s",
          lastLine, lastColumn, nextLine, nextColumn);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[54]++;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[12]++;}

    lastMapping = mapping;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[55]++;
    mappings.add(mapping);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[56]++;
  }

  /**
   * Writes out the source map in the following format (line numbers are for
   * reference only and are not part of the format):
   *
   * 1.  {
   * 2.    version: 2,
   * 3.    file: "out.js"
   * 4.    lineCount: 2
   * 5.    lineMaps: [
   * 6.        "ABAAA",
   * 7.        "ABAA"
   * 8.     ],
   * 9.    sourceRoot: "",
   * 10.   sources: ["foo.js", "bar.js"],
   * 11.   names: ["src", "maps", "are", "fun"],
   * 12.   mappings: [
   * 13.       [1, 1, 2, 4],
   * 14.       [2, 1, 2, "yack"],
   * 15.   ],
   * 16.  }
   *
   * Line 1: The entire file is a single JSON object
   * Line 2: File revision (always the first entry in the object)
   * Line 3: The name of the file that this source map is associated with.
   * Line 4: The number of lines represented in the source map.
   * Line 5: "lineMaps" field is a JSON array, where each entry represents a
   *     line in the generated text.
   * Line 6: A line entry, representing a series of line segments, where each
   *     segment encodes an mappings-id and repetition count.
   * Line 9: An optional source root, useful for relocating source files on a
   *     server or removing repeated prefix values in the "sources" entry.
   * Line 10: A list of sources used by the "mappings" entry relative to the
   *     sourceRoot.
   * Line 11: A list of symbol names used by the "mapping" entry.  This list
   *     may be incomplete.
   * Line 12: The mappings field.
   * Line 13: Each entry represent a block of text in the original source, and
   *     consists four fields:
   *     The source file name
   *     The line in the source file the text begins
   *     The column in the line that the text begins
   *     An optional name (from the original source) that this entry represents.
   *     This can either be an string or index into the "names" field.
   */
  @Override
  public void appendTo(Appendable out, String name) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[57]++;
    int maxLine = prepMappings();

    // Add the header fields.
    out.append("{\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[58]++;
    appendFirstField(out, "version", "2");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[59]++;
    appendField(out, "file", escapeString(name));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[60]++;
    appendField(out, "lineCount", String.valueOf(maxLine + 1));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[61]++;

    // Add the line character maps.
    appendFieldStart(out, "lineMaps");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[62]++;
    out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[63]++;
    (new LineMapper(out)).appendLineMappings();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[64]++;
    out.append("]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[65]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[66]++;

    // Add the mappings themselves.
    appendFieldStart(out, "mappings");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[67]++;
    out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[68]++;
    (new MappingWriter()).appendMappings(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[69]++;
    out.append("]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[70]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[71]++;

    // Files names
    appendFieldStart(out, "sources");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[72]++;
    out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[73]++;
    addSourceNameMap(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[74]++;
    out.append("]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[75]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[76]++;

    // Files names
    appendFieldStart(out, "names");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[77]++;
    out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[78]++;
    addOriginalNameMap(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[79]++;
    out.append("]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[80]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[81]++;

    out.append("\n}\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[82]++;
  }

  /**
   * Writes the source name map to 'out'.
   */
  private void addSourceNameMap(Appendable out) throws IOException {
    addMap(out, sourceFileMap);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[83]++;
  }

  /**
   * Writes the original name map to 'out'.
   */
  private void addOriginalNameMap(Appendable out) throws IOException {
    addMap(out, originalNameMap);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[84]++;
  }

  /**
   * Writes the source name map to 'out'.
   */
  private void addMap(Appendable out, Map<String, Integer> map)
      throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[85]++;
    int i = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[86]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[4]++;


    for (Entry<String, Integer> entry : map.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[4]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[5]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[6]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[87]++;
      String key = entry.getKey();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[88]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[13]++;
        out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[89]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[14]++;}
      out.append(escapeString(key));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[90]++;
      i++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[91]++;
    }
  }

  /**
   * Escapes the given string for JSON.
   */
  private static String escapeString(String value) {
    return Util.escapeString(value);
  }

  // Source map field helpers.

  private static void appendFirstField(
      Appendable out, String name, CharSequence value)
      throws IOException {
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[92]++;
    out.append(name);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[93]++;
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[94]++;
    out.append(":");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[95]++;
    out.append(value);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[96]++;
  }

  private static void appendField(
      Appendable out, String name, CharSequence value)
      throws IOException {
    out.append(",\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[97]++;
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[98]++;
    out.append(name);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[99]++;
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[100]++;
    out.append(":");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[101]++;
    out.append(value);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[102]++;
  }

  private static void appendFieldStart(Appendable out, String name)
      throws IOException {
    appendField(out, name, "");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[103]++;
  }

  @SuppressWarnings("unused")
  private static void appendFieldEnd(Appendable out)
     throws IOException {
  }

  /**
   * Assigns sequential ids to used mappings, and returns the last line mapped.
   */
  private int prepMappings() throws IOException {
    // Mark any unused mappings.
    (new MappingTraversal()).traverse(new UsedMappingCheck());
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[104]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[105]++;

    // Renumber used mappings and keep track of the last line.
    int id = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[106]++;
    int maxLine = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[107]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[7]++;


    for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[7]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[8]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[9]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[108]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((m.used) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[15]++;
        m.id = id++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[109]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[110]++;
        int endPositionLine = m.endPosition.getLine();
        maxLine = Math.max(maxLine, endPositionLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[111]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[16]++;}
    }

    // Adjust for the prefix.
    return maxLine + prefixPosition.getLine();
  }

  /**
   * Pools source names.
   * @param sourceName The source location to index.
   * @return The id to represent the source name in the output.
   */
  private int getSourceId(String sourceName) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[112]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((sourceName != lastSourceFile) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[17]++;
      lastSourceFile = sourceName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[113]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[114]++;
      Integer index = sourceFileMap.get(sourceName);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[115]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[19]++;
        lastSourceFileIndex = index;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[116]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[20]++;
        lastSourceFileIndex = sourceFileMap.size();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[117]++;
        sourceFileMap.put(sourceName, lastSourceFileIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[118]++;
      }

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[18]++;}
    return lastSourceFileIndex;
  }

  /**
   * Pools symbol names
   * @param symbolName The symbol name to index.
   * @return The id to represent the symbol name in the output.
   */
  private int getNameId(String symbolName) {
    int originalNameIndex;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[119]++;
    Integer index = originalNameMap.get(symbolName);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[120]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((index != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[21]++;
      originalNameIndex = index;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[121]++;

    } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[22]++;
      originalNameIndex = originalNameMap.size();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[122]++;
      originalNameMap.put(symbolName, originalNameIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[123]++;
    }
    return originalNameIndex;
  }

  /**
   * A mapping from a given position in an input source file to a given position
   * in the generated code.
   */
  static class Mapping {
    /**
     * A unique ID for this mapping for record keeping purposes.
     */
    int id = UNMAPPED;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[124]++;
  }

    /**
     * The source file index.
     */
    int sourceFile;

    /**
     * The position of the code in the input source file. Both
     * the line number and the character index are indexed by
     * 1 for legacy reasons via the Rhino Node class.
     */
    FilePosition originalPosition;

    /**
     * The starting position of the code in the generated source
     * file which this mapping represents. Indexed by 0.
     */
    FilePosition startPosition;

    /**
     * The ending position of the code in the generated source
     * file which this mapping represents. Indexed by 0.
     */
    FilePosition endPosition;

    /**
     * The original name of the token found at the position
     * represented by this mapping (if any).
     */
    String originalName;

    /**
     * Whether the mapping is actually used by the source map.
     */
    boolean used = false;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[125]++;
  }
  }

  private class MappingWriter {
    /**
     * Cache of escaped source file name.
     */
    private int lastLine = 0;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[126]++;
  }
    private String lastLineString = String.valueOf(0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[127]++;
  }

    /**
     * Appends the mapping to the given buffer.
     */
    private void appendMappingTo(
        Mapping m, Appendable out) throws IOException {
      out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[128]++;

      out.append(String.valueOf(m.sourceFile));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[129]++;
      out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[130]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[131]++;

      int line = m.originalPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[132]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((line != lastLine) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[23]++;
        lastLineString = String.valueOf(line);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[133]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[24]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[134]++;
      String lineValue = lastLineString;

      out.append(lineValue);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[135]++;

      out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[136]++;
      out.append(String.valueOf(m.originalPosition.getColumn()));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[137]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[138]++;
int CodeCoverConditionCoverageHelper_C14;

      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((m.originalName != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[25]++;
        out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[139]++;
        out.append(String.valueOf(getNameId(m.originalName)));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[140]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[26]++;}

      out.append("],\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[141]++;
    }

    /**
     * Add used mappings to the supplied Appendable.
     */
    void appendMappings(Appendable out) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[142]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[10]++;


      for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[10]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[11]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[12]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[143]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((m.used) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[27]++;
          appendMappingTo(m, out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[144]++;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[28]++;}
      }
    }
  }

  private class LineMapper implements MappingVisitor {
    // The destination.
    private final Appendable out;

    // Whether the current line has had a value written yet.
    private int lastId = UNMAPPED;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[145]++;
  }

    LineMapper(Appendable out) {
      this.out = out;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[146]++;
    }

    /**
     * As each segment is visited write out the appropriate line mapping.
     */
    @Override
    public void visit(Mapping m, int line, int col, int nextLine, int nextCol)
      throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[147]++;

      int id = (m != null) ? m.id : UNMAPPED;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[148]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[13]++;


int CodeCoverConditionCoverageHelper_C16;

      for (int i = line;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i <= nextLine) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[13]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[14]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[15]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[149]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[29]++;
          closeEntry(id, nextCol - col);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[150]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[151]++;
          break;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[30]++;}

        closeLine(false);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[152]++;
        openLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[153]++;

        // Set the starting location for the next line.
        col = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[154]++;
      }
    }

    // Append the line mapping entries.
    void appendLineMappings() throws IOException {
      // Start the first line.
      openLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[155]++;

      (new MappingTraversal()).traverse(this);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[156]++;

      // And close the final line.
      closeLine(true);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[157]++;
    }

    /**
     * Begin the entry for a new line.
     */
    private void openLine() throws IOException {
      out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[158]++;
      // The first id of the line is not relative.
      this.lastId = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[159]++;
    }

    /**
     * End the entry for a line.
     */
    private void closeLine(boolean finalEntry) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[160]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((finalEntry) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[31]++;
        out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[161]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[32]++;
        out.append("\",\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[162]++;
      }
    }

    private void closeEntry(int id, int reps) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[163]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((reps == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[33]++;
        return;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[34]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[164]++;

      StringBuilder sb = new StringBuilder();
      LineMapEncoder.encodeEntry(sb, id, lastId, reps);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[165]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[166]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((validate) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[35]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[167]++;
        SourceMapLineDecoder.LineEntry entry =
            SourceMapLineDecoder.decodeLineEntry(sb.toString(), lastId);
        Preconditions.checkState(entry.id == id && entry.reps == reps,
            "expected (%s,%s) but got (%s,%s)",
            id, reps, entry.id, entry.reps);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[168]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[36]++;}

      out.append(sb);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[169]++;
      lastId = id;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[170]++;
    }
  }

  @VisibleForTesting
  public static class LineMapEncoder {
    /**
     * The source map line map is consists of a series of entries each
     * representing a map entry and a repetition count of that entry.
     *
     * @param out The entry destination.
     * @param id  The id for the entry.
     * @param lastId The previous id written, used to generate a relative
     *     map id.
     * @param reps The number of times the id is repeated in the map.
     * @throws IOException
     */
    public static void encodeEntry(Appendable out, int id, int lastId, int reps)
        throws IOException {
      Preconditions.checkState(reps > 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[171]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[172]++;
      int relativeIdLength = getRelativeMappingIdLength(id, lastId);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[173]++;
      int relativeId = getRelativeMappingId(id, relativeIdLength, lastId);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[174]++;

      String relativeIdString = valueToBase64(relativeId, relativeIdLength);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[175]++;
int CodeCoverConditionCoverageHelper_C21;

      // If we can, we use a single base64 digit to encode both the id length
      // and the repetition count.  The current best division of the base64
      // digit (which has 6 bits) is 2 bits for the id length (1-4 digits) and
      // 4 bit for the repetition count (1-16 repetitions).  If either of these
      // two values are exceeded a "!" is written (a non-base64 character) to
      // signal the a full base64 character is used for repetition count and
      // the mapping id length.  As the repetition count can exceed 64, we
      // allow the "escape" ("!") to be repeated to signal additional
      // repetition count length characters.  It is extremely unlikely that
      // mapping id length will exceed 64 base64 characters in length so
      // additional "!" don't signal additional id length characters.
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((reps > 16) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((relativeIdLength > 4) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[37]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[176]++;
        String repsString = valueToBase64(reps - 1, 1);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[177]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[16]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < repsString.length()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[16]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[17]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[18]++;
}
          // TODO(johnlenz): update this to whatever is agreed to.
          out.append('!');
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[178]++;
        }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[179]++;
        String sizeId = valueToBase64(relativeIdString.length() - 1, 1);

        out.append(sizeId);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[180]++;
        out.append(repsString);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[181]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[38]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[182]++;
        int prefix = ((reps - 1) << 2) + (relativeIdString.length() - 1);
        Preconditions.checkState(prefix < 64 && prefix >= 0,
            "prefix (%s) reps(%s) map id size(%s)",
            prefix, reps, relativeIdString.length());
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[183]++;
        out.append(valueToBase64(prefix, 1));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[184]++;
      }
      out.append(relativeIdString);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[185]++;
    }

    /**
     * @param idLength the length relative id, when encoded in as a base64
     *     value. @see #getRelativeMappingIdLength
     * @return A value relative to the the lastId.  Negative value are
     * represented as a two-complement value.
     */
    public static int getRelativeMappingId(int id, int idLength, int lastId) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[186]++;
      int base = 1 << (idLength * 6);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[187]++;
      int relativeId = id - lastId;
      return (relativeId < 0) ? relativeId + base : relativeId;
    }

    /**
     * @return The length of the base64 number needed to include the id.
     */
    public static int getRelativeMappingIdLength(int rawId, int lastId) {
      Preconditions.checkState(rawId >= 0 || rawId == UNMAPPED);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[188]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[189]++;
      int relativeId = rawId - lastId;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[190]++;
      int id = (relativeId < 0 ? Math.abs(relativeId) - 1 : relativeId) << 1;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[191]++;
      int digits = 1;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[192]++;
      int base = 64;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[193]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[19]++;


int CodeCoverConditionCoverageHelper_C23;
      while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((id >= base) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[19]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[20]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[21]++;
}
        digits++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[194]++;
        base *= 64;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[195]++;
      }
      return digits;
    }

    /**
     * @return return the base64 number encoding the provided value,
     *    padded if necessary to create a number with the given minimum length.
     */
    static String valueToBase64(int value, int minimumSize) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[196]++;
      int size = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[197]++;
      char chars[] = new char[4];
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[198]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[22]++;


int CodeCoverConditionCoverageHelper_C24;
      do {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[22]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[23]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[24]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[199]++;
        int charValue = value & 63; // base64 chars
        value = value >>> 6;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[200]++; // get the next value;
        chars[size++] = Base64.toBase64(charValue);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[201]++;
      } while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((value > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[202]++;

      StringBuilder sb = new StringBuilder(size);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[203]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[25]++;


int CodeCoverConditionCoverageHelper_C25;

      while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((minimumSize > size) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[25]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[26]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[27]++;
}
        sb.append(Base64.toBase64(0));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[204]++;
        minimumSize--;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[205]++;
      }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[206]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[28]++;


int CodeCoverConditionCoverageHelper_C26;
      while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((size > 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[28]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[29]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[30]++;
}
        sb.append(chars[--size]);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[207]++;
      }
      return sb.toString();
    }
  }

  /**
   * Mark any visited mapping as "used".
   */
  private class UsedMappingCheck implements MappingVisitor {
    /**
     * @throws IOException
     */
    @Override
    public void visit(Mapping m, int line, int col, int nextLine, int nextCol)
        throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[208]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[39]++;
        m.used = true;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[209]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[40]++;}
    }
  }

  private interface MappingVisitor {
    /**
     * @param m The mapping for the current code segment. null if the segment
     *     is unmapped.
     * @param line The starting line for this code segment.
     * @param col The starting column for this code segment.
     * @param endLine The ending line
     * @param endCol The ending column
     * @throws IOException
     */
    void visit(Mapping m, int line, int col, int endLine, int endCol)
        throws IOException;
  }

  /**
   * Walk the mappings and visit each segment of the mappings, unmapped
   * segments are visited with a null mapping, unused mapping are not visited.
   */
  private class MappingTraversal {
    // The last line and column written
    private int line;
    private int col;

    MappingTraversal() {
    }

    // Append the line mapping entries.
    void traverse(MappingVisitor v) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[210]++;
      // The mapping list is ordered as a pre-order traversal.  The mapping
      // positions give us enough information to rebuild the stack and this
      // allows the building of the source map in O(n) time.
      Deque<Mapping> stack = new ArrayDeque<Mapping>();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[211]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[31]++;


      for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[31]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[32]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[33]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[212]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[34]++;


int CodeCoverConditionCoverageHelper_C28;
        // Find the closest ancestor of the current mapping:
        // An overlapping mapping is an ancestor of the current mapping, any
        // non-overlapping mappings are siblings (or cousins) and must be
        // closed in the reverse order of when they encountered.
        while ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isOverlapped(stack.peek(), m)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[34]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[35]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[36]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[213]++;
          Mapping previous = stack.pop();
          maybeVisit(v, previous);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[214]++;
        }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[215]++;

        // Any gaps between the current line position and the start of the
        // current mapping belong to the parent.
        Mapping parent = stack.peek();
        maybeVisitParent(v, parent, m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[216]++;

        stack.push(m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[217]++;
      }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[218]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[37]++;


int CodeCoverConditionCoverageHelper_C29;

      // There are no more children to be had, simply close the remaining
      // mappings in the reverse order of when they encountered.
      while ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[37]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[38]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.loops[39]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[219]++;
        Mapping m = stack.pop();
        maybeVisit(v, m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[220]++;
      }
    }

    /**
     * @return The line adjusted for the prefix position.
     */
    private int getAdjustedLine(FilePosition p) {
      return p.getLine() + prefixPosition.getLine();
    }

    /**
     * @return The column adjusted for the prefix position.
     */
    private int getAdjustedCol(FilePosition p) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[221]++;
      int rawLine = p.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[222]++;
      int rawCol = p.getColumn();
      // Only the first line needs the character position adjusted.
      return (rawLine != 0)
          ? rawCol : rawCol + prefixPosition.getColumn();
    }

    /**
     * @return Whether m1 ends before m2 starts.
     */
    private boolean isOverlapped(Mapping m1, Mapping m2) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[223]++;
      // No need to use adjusted values here, relative positions are sufficient.
      int l1 = m1.endPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[224]++;
      int l2 = m2.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[225]++;
      int c1 = m1.endPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[226]++;
      int c2 = m2.startPosition.getColumn();

      return (l1 == l2 && c1 >= c2) || l1 > l2;
    }

    /**
     * Write any needed entries from the current position to the end of the
     * provided mapping.
     */
    private void maybeVisit(MappingVisitor v, Mapping m) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[227]++;
      int nextLine = getAdjustedLine(m.endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[228]++;
      int nextCol = getAdjustedCol(m.endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[229]++;
int CodeCoverConditionCoverageHelper_C30;
      // If this anything remaining in this mapping beyond the
      // current line and column position, write it out now.
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (32)) == 0 || true) &&
 ((line < nextLine) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((col < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[41]++;
        visit(v, m, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[230]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[42]++;}
    }

    /**
     * Write any needed entries to complete the provided mapping.
     */
    private void maybeVisitParent(MappingVisitor v, Mapping parent, Mapping m)
        throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[231]++;
      int nextLine = getAdjustedLine(m.startPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[232]++;
      int nextCol = getAdjustedCol(m.startPosition);
      // If the previous value is null, no mapping exists.
      Preconditions.checkState(line < nextLine || col <= nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[233]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[234]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((line < nextLine) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((col < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[43]++;
        visit(v, parent, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[235]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[44]++;}
    }

    /**
     * Write any entries needed between the current position the next position
     * and update the current position.
     */
    private void visit(MappingVisitor v, Mapping m,
        int nextLine, int nextCol)
        throws IOException {
      Preconditions.checkState(line <= nextLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[236]++;
      Preconditions.checkState(line < nextLine || col < nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[237]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[238]++;
int CodeCoverConditionCoverageHelper_C32;

      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((col == nextCol) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[45]++;
        // Nothing to do.
        Preconditions.checkState(false);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[239]++;
        return;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.branches[46]++;}

      v.visit(m, line, col, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[240]++;

      line = nextLine;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[241]++;
      col = nextCol;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d.statements[242]++;
    }
  }

  @Override
  public void appendIndexMapTo(
      Appendable out, String name, List<SourceMapSection> appSections) {
    throw new UnsupportedOperationException();
  }
}

class CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d ());
  }
    public static long[] statements = new long[243];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[33];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapGeneratorV2.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,3,3,2};
    for (int i = 1; i <= 32; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tpb95kj1d () {
    super("com.google.debugging.sourcemap.SourceMapGeneratorV2.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 242; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapGeneratorV2.java");
      for (int i = 1; i <= 242; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

