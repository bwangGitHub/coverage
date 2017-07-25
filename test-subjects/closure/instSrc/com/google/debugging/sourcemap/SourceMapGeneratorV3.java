/*
 * Copyright 2011 The Closure Compiler Authors.
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
import com.google.debugging.sourcemap.SourceMapConsumerV3.EntryVisitor;

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
 * @author johnlenz@google.com (John Lenz)
 */
public class SourceMapGeneratorV3 implements SourceMapGenerator {
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.ping();
  }


  private static final int UNMAPPED = -1;
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[1]++;
  }


  /**
   * A pre-order traversal ordered list of mappings stored in this map.
   */
  private List<Mapping> mappings = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[2]++;
  }

  /**
   * A map of source names to source name index
   */
  private LinkedHashMap<String, Integer> sourceFileMap =
      Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[3]++;
  }

  /**
   * A map of source names to source name index
   */
  private LinkedHashMap<String, Integer> originalNameMap =
      Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[4]++;
  }

  /**
   * Cache of the last mappings source name.
   */
  private String lastSourceFile = null;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[5]++;
  }

  /**
   * Cache of the last mappings source name index.
   */
  private int lastSourceFileIndex = -1;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[6]++;
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
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[7]++;
  }

  /**
   * The position that the current source map is offset in the
   * generated the compiled source file by the addition of a
   * an output wrapper prefix.
   */
  private FilePosition prefixPosition = new FilePosition(0, 0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[8]++;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    mappings.clear();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[9]++;
    lastMapping = null;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[10]++;
    sourceFileMap.clear();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[11]++;
    originalNameMap.clear();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[12]++;
    lastSourceFile = null;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[13]++;
    lastSourceFileIndex = -1;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[14]++;
    offsetPosition = new FilePosition(0, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[15]++;
    prefixPosition = new FilePosition(0, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[16]++;
  }

  /**
   * @param validate Whether to perform (potentially costly) validation on the
   * generated source map.
   */
  @Override
  public void validate(boolean validate) {
    // Nothing currently.
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[17]++;
    // Determine the current line and character position.
    int prefixLine = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[18]++;
    int prefixIndex = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < prefix.length()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[1]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[2]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[3]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prefix.charAt(i) == '\n') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[1]++;
        prefixLine++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[21]++;
        prefixIndex = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[22]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[2]++;
        prefixIndex++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[23]++;
      }
    }

    prefixPosition = new FilePosition(prefixLine, prefixIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[24]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[25]++;
    Preconditions.checkState(offsetIndex >= 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[26]++;
    offsetPosition = new FilePosition(offsetLine, offsetIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[27]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[28]++;
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
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[4]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[29]++;

    FilePosition adjustedStart = startPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[30]++;
    FilePosition adjustedEnd = endPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((offsetPosition.getLine() != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((offsetPosition.getColumn() != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[5]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[32]++;
      // If the mapping is found on the first line, we need to offset
      // its character position by the number of characters found on
      // the *last* line of the source file to which the code is
      // being generated.
      int offsetLine = offsetPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[33]++;
      int startOffsetPosition = offsetPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[34]++;
      int endOffsetPosition = offsetPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((startPosition.getLine() > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[7]++;
        startOffsetPosition = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[36]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[8]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((endPosition.getLine() > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[9]++;
        endOffsetPosition = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[38]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[10]++;}

      adjustedStart = new FilePosition(
          startPosition.getLine() + offsetLine,
          startPosition.getColumn() + startOffsetPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[39]++;

      adjustedEnd = new FilePosition(
          endPosition.getLine() + offsetLine,
          endPosition.getColumn() + endOffsetPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[40]++;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[6]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[41]++;

    // Create the new mapping.
    Mapping mapping = new Mapping();
    mapping.sourceFile = sourceName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[42]++;
    mapping.originalPosition = sourceStartPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[43]++;
    mapping.originalName = symbolName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[44]++;
    mapping.startPosition = adjustedStart;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[45]++;
    mapping.endPosition = adjustedEnd;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[46]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;

    // Validate the mappings are in a proper order.
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lastMapping != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[11]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[48]++;
      int lastLine = lastMapping.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[49]++;
      int lastColumn = lastMapping.startPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[50]++;
      int nextLine = mapping.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[51]++;
      int nextColumn = mapping.startPosition.getColumn();
      Preconditions.checkState(nextLine > lastLine
          || (nextLine == lastLine && nextColumn >= lastColumn),
          "Incorrect source mappings order, previous : (%s,%s)\n"
          + "new : (%s,%s)\nnode : %s",
          lastLine, lastColumn, nextLine, nextColumn);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[52]++;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[12]++;}

    lastMapping = mapping;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[53]++;
    mappings.add(mapping);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[54]++;
  }

  class ConsumerEntryVisitor implements EntryVisitor {

    @Override
    public void visit(
        String sourceName, String symbolName,
        FilePosition sourceStartPosition,
        FilePosition startPosition, FilePosition endPosition) {
      addMapping(sourceName, symbolName,
          sourceStartPosition, startPosition, endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[55]++;
    }
  }

  public void mergeMapSection(int line, int column, String mapSectionContents)
      throws SourceMapParseException {
     setStartingPosition(line, column);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[56]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[57]++;
     SourceMapConsumerV3 section = new SourceMapConsumerV3();
     section.parse(mapSectionContents);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[58]++;
     section.visitMappings(new ConsumerEntryVisitor());
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[59]++;
  }

  /**
   * Writes out the source map in the following format (line numbers are for
   * reference only and are not part of the format):
   *
   * 1.  {
   * 2.    version: 3,
   * 3.    file: "out.js",
   * 4.    lineCount: 2,
   * 5.    sourceRoot: "",
   * 6.    sources: ["foo.js", "bar.js"],
   * 7.    names: ["src", "maps", "are", "fun"],
   * 8.    mappings: "a;;abcde,abcd,a;"
   * 9.  }
   *
   * Line 1: The entire file is a single JSON object
   * Line 2: File revision (always the first entry in the object)
   * Line 3: The name of the file that this source map is associated with.
   * Line 4: The number of lines represented in the source map.
   * Line 5: An optional source root, useful for relocating source files on a
   *     server or removing repeated prefix values in the "sources" entry.
   * Line 6: A list of sources used by the "mappings" entry relative to the
   *     sourceRoot.
   * Line 7: A list of symbol names used by the "mapping" entry.  This list
   *     may be incomplete.
   * Line 8: The mappings field.
   */
  @Override
  public void appendTo(Appendable out, String name) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[60]++;
    int maxLine = prepMappings();

    // Add the header fields.
    out.append("{\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[61]++;
    appendFirstField(out, "version", "3");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[62]++;
    appendField(out, "file", escapeString(name));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[63]++;
    appendField(out, "lineCount", String.valueOf(maxLine + 1));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[64]++;

    // Add the mappings themselves.
    appendFieldStart(out, "mappings");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[65]++;
    // out.append("[");
    (new LineMapper(out)).appendLineMappings();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[66]++;
    // out.append("]");
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[67]++;

    // Files names
    appendFieldStart(out, "sources");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[68]++;
    out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[69]++;
    addSourceNameMap(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[70]++;
    out.append("]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[71]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[72]++;

    // Files names
    appendFieldStart(out, "names");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[73]++;
    out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[74]++;
    addSymbolNameMap(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[75]++;
    out.append("]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[76]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[77]++;

    out.append("\n}\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[78]++;
  }

  /**
   * Writes the source name map to 'out'.
   */
  private void addSourceNameMap(Appendable out) throws IOException {
    addNameMap(out, sourceFileMap);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[79]++;
  }

  /**
   * Writes the source name map to 'out'.
   */
  private void addSymbolNameMap(Appendable out) throws IOException {
    addNameMap(out, originalNameMap);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[80]++;
  }

  private void addNameMap(Appendable out, Map<String, Integer> map)
      throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[81]++;
    int i = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[82]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[4]++;


    for (Entry<String, Integer> entry : map.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[4]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[5]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[6]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[83]++;
      String key = entry.getKey();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[84]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[13]++;
        out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[85]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[14]++;}
      out.append(escapeString(key));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[86]++;
      i++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[87]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[88]++;
    out.append(name);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[89]++;
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[90]++;
    out.append(":");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[91]++;
    out.append(value);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[92]++;
  }

  private static void appendField(
      Appendable out, String name, CharSequence value)
      throws IOException {
    out.append(",\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[93]++;
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[94]++;
    out.append(name);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[95]++;
    out.append("\"");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[96]++;
    out.append(":");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[97]++;
    out.append(value);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[98]++;
  }

  private static void appendFieldStart(Appendable out, String name)
      throws IOException {
    appendField(out, name, "");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[99]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[100]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[101]++;

    // Renumber used mappings and keep track of the last line.
    int id = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[102]++;
    int maxLine = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[103]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[7]++;


    for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[7]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[8]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[9]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[104]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((m.used) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[15]++;
        m.id = id++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[105]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[106]++;
        int endPositionLine = m.endPosition.getLine();
        maxLine = Math.max(maxLine, endPositionLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[107]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[16]++;}
    }

    // Adjust for the prefix.
    return maxLine + prefixPosition.getLine();
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
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[108]++;
  }

    /**
     * The source file index.
     */
    String sourceFile;

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
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[109]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[110]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[17]++;
        m.used = true;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[111]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[18]++;}
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[112]++;
      // The mapping list is ordered as a pre-order traversal.  The mapping
      // positions give us enough information to rebuild the stack and this
      // allows the building of the source map in O(n) time.
      Deque<Mapping> stack = new ArrayDeque<Mapping>();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[113]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[10]++;


      for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[10]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[11]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[12]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[114]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[13]++;


int CodeCoverConditionCoverageHelper_C11;
        // Find the closest ancestor of the current mapping:
        // An overlapping mapping is an ancestor of the current mapping, any
        // non-overlapping mappings are siblings (or cousins) and must be
        // closed in the reverse order of when they encountered.
        while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isOverlapped(stack.peek(), m)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[13]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[14]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[15]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[115]++;
          Mapping previous = stack.pop();
          maybeVisit(v, previous);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[116]++;
        }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[117]++;

        // Any gaps between the current line position and the start of the
        // current mapping belong to the parent.
        Mapping parent = stack.peek();
        maybeVisitParent(v, parent, m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[118]++;

        stack.push(m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[119]++;
      }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[120]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;

      // There are no more children to be had, simply close the remaining
      // mappings in the reverse order of when they encountered.
      while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[16]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[17]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[18]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[121]++;
        Mapping m = stack.pop();
        maybeVisit(v, m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[122]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[123]++;
      int rawLine = p.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[124]++;
      int rawCol = p.getColumn();
      // Only the first line needs the character position adjusted.
      return (rawLine != 0)
          ? rawCol : rawCol + prefixPosition.getColumn();
    }

    /**
     * @return Whether m1 ends before m2 starts.
     */
    private boolean isOverlapped(Mapping m1, Mapping m2) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[125]++;
      // No need to use adjusted values here, relative positions are sufficient.
      int l1 = m1.endPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[126]++;
      int l2 = m2.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[127]++;
      int c1 = m1.endPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[128]++;
      int c2 = m2.startPosition.getColumn();

      return (l1 == l2 && c1 >= c2) || l1 > l2;
    }

    /**
     * Write any needed entries from the current position to the end of the
     * provided mapping.
     */
    private void maybeVisit(MappingVisitor v, Mapping m) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[129]++;
      int nextLine = getAdjustedLine(m.endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[130]++;
      int nextCol = getAdjustedCol(m.endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[131]++;
int CodeCoverConditionCoverageHelper_C13;
      // If this anything remaining in this mapping beyond the
      // current line and column position, write it out now.
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((line < nextLine) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((col < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[19]++;
        visit(v, m, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[132]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[20]++;}
    }

    /**
     * Write any needed entries to complete the provided mapping.
     */
    private void maybeVisitParent(MappingVisitor v, Mapping parent, Mapping m)
        throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[133]++;
      int nextLine = getAdjustedLine(m.startPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[134]++;
      int nextCol = getAdjustedCol(m.startPosition);
      // If the previous value is null, no mapping exists.
      Preconditions.checkState(line < nextLine || col <= nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[135]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[136]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((line < nextLine) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((col < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[21]++;
        visit(v, parent, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[137]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[22]++;}
    }

    /**
     * Write any entries needed between the current position the next position
     * and update the current position.
     */
    private void visit(MappingVisitor v, Mapping m,
        int nextLine, int nextCol)
        throws IOException {
      Preconditions.checkState(line <= nextLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[138]++;
      Preconditions.checkState(line < nextLine || col < nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[139]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[140]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((col == nextCol) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[23]++;
        // Nothing to do.
        Preconditions.checkState(false);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[141]++;
        return;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[24]++;}

      v.visit(m, line, col, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[142]++;

      line = nextLine;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[143]++;
      col = nextCol;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[144]++;
    }
  }

  /**
   * Appends the index source map to the given buffer.
   *
   * @param out The stream to which the map will be appended.
   * @param name The name of the generated source file that this source map
   *   represents.
   * @param sections An ordered list of map sections to include in the index.
   * @throws IOException
   */
  @Override
  public void appendIndexMapTo(
      Appendable out, String name, List<SourceMapSection> sections)
      throws IOException {
    // Add the header fields.
    out.append("{\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[145]++;
    appendFirstField(out, "version", "3");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[146]++;
    appendField(out, "file", escapeString(name));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[147]++;

    // Add the line character maps.
    appendFieldStart(out, "sections");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[148]++;
    out.append("[\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[149]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[150]++;
    boolean first = true;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[151]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[19]++;


    for (SourceMapSection section : sections) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[19]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[20]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[21]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[152]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((first) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[25]++;
        first = false;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[153]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[26]++;
        out.append(",\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[154]++;
      }
      out.append("{\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[155]++;
      appendFirstField(out, "offset",
          offsetValue(section.getLine(), section.getColumn()));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[156]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[157]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((section.getSectionType() == SourceMapSection.SectionType.URL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[27]++;
        appendField(out, "url", escapeString(section.getSectionValue()));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[158]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[28]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[159]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((section.getSectionType() == SourceMapSection.SectionType.MAP) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[29]++;
        appendField(out, "map", section.getSectionValue());
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[160]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[30]++;
        throw new IOException("Unexpected section type");
      }
}
      out.append("\n}");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[161]++;
    }

    out.append("\n]");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[162]++;
    appendFieldEnd(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[163]++;

    out.append("\n}\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[164]++;
  }

  private CharSequence offsetValue(int line, int column) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[165]++;
    StringBuilder out = new StringBuilder();
    out.append("{\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[166]++;
    appendFirstField(out, "line", String.valueOf(line));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[167]++;
    appendField(out, "column", String.valueOf(column));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[168]++;
    out.append("\n}");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[169]++;
    return out;
  }

  private int getSourceId(String sourceName) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[170]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((sourceName != lastSourceFile) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[31]++;
      lastSourceFile = sourceName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[171]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[172]++;
      Integer index = sourceFileMap.get(sourceName);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[173]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[33]++;
        lastSourceFileIndex = index;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[174]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[34]++;
        lastSourceFileIndex = sourceFileMap.size();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[175]++;
        sourceFileMap.put(sourceName, lastSourceFileIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[176]++;
      }

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[32]++;}
    return lastSourceFileIndex;
  }

  private int getNameId(String symbolName) {
    int originalNameIndex;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[177]++;
    Integer index = originalNameMap.get(symbolName);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[178]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((index != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[35]++;
      originalNameIndex = index;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[179]++;

    } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[36]++;
      originalNameIndex = originalNameMap.size();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[180]++;
      originalNameMap.put(symbolName, originalNameIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[181]++;
    }
    return originalNameIndex;
  }

  private class LineMapper implements MappingVisitor {
    // The destination.
    private final Appendable out;

    private int previousLine = -1;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[182]++;
  }
    private int previousColumn = 0;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[183]++;
  }

    // Previous values used for storing relative ids.
    private int previousSourceFileId;
    private int previousSourceLine;
    private int previousSourceColumn;
    private int previousNameId;

    LineMapper(Appendable out) {
      this.out = out;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[184]++;
    }

    /**
     * As each segment is visited write out the appropriate line mapping.
     */
    @Override
    public void visit(Mapping m, int line, int col, int nextLine, int nextCol)
      throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[185]++;
int CodeCoverConditionCoverageHelper_C22;

      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((previousLine != line) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[37]++;
        previousColumn = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[186]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[38]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[187]++;
int CodeCoverConditionCoverageHelper_C23;

      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((line != nextLine) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((col != nextCol) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[39]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[188]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((previousLine == line) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[41]++; // not the first entry for the line
          out.append(',');
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[189]++;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[42]++;}
        writeEntry(m, col);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[190]++;
        previousLine = line;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[191]++;
        previousColumn = col;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[192]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[40]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[193]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[22]++;


int CodeCoverConditionCoverageHelper_C25;

      for (int i = line;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i <= nextLine) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[22]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[23]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.loops[24]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[194]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[43]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[195]++;
          break;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[44]++;}

        closeLine(false);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[196]++;
        openLine(false);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[197]++;
      }
    }

    /**
     * Writes an entry for the given column (of the generated text) and
     * associated mapping.
     * The values are stored as relative to the last seen values for each
     * field and encoded as Base64VLQs.
     */
    void writeEntry(Mapping m, int column) throws IOException {
      // The relative generated column number
      Base64VLQ.encode(out, column - previousColumn);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[198]++;
      previousColumn = column;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[199]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[200]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[45]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[201]++;
        // The relative source file id
        int sourceId = getSourceId(m.sourceFile);
        Base64VLQ.encode(out, sourceId - previousSourceFileId);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[202]++;
        previousSourceFileId = sourceId;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[203]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[204]++;

        // The relative source file line and column
        int srcline = m.originalPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[205]++;
        int srcColumn = m.originalPosition.getColumn();
        Base64VLQ.encode(out, srcline - previousSourceLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[206]++;
        previousSourceLine = srcline;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[207]++;

        Base64VLQ.encode(out, srcColumn - previousSourceColumn);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[208]++;
        previousSourceColumn = srcColumn;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[209]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[210]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((m.originalName != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[47]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[211]++;
          // The relative id for the associated symbol name
          int nameId = getNameId(m.originalName);
          Base64VLQ.encode(out, (nameId - previousNameId));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[212]++;
          previousNameId = nameId;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[213]++;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[48]++;}

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[46]++;}
    }

    // Append the line mapping entries.
    void appendLineMappings() throws IOException {
      // Start the first line.
      openLine(true);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[214]++;

      (new MappingTraversal()).traverse(this);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[215]++;

      // And close the final line.
      closeLine(true);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[216]++;
    }

    /**
     * Begin the entry for a new line.
     */
    private void openLine(boolean firstEntry) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[217]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((firstEntry) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[49]++;
        out.append('\"');
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[218]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[50]++;}
    }

    /**
     * End the entry for a line.
     */
    private void closeLine(boolean finalEntry) throws IOException {
      out.append(';');
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[219]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[220]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((finalEntry) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[51]++;
        out.append('\"');
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.statements[221]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt.branches[52]++;}
    }
  }

}

class CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt ());
  }
    public static long[] statements = new long[222];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[31];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapGeneratorV3.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,2,1,1,1,1,1,1,2,1,3,3,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7u3cd2ekpt () {
    super("com.google.debugging.sourcemap.SourceMapGeneratorV3.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 221; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
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
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapGeneratorV3.java");
      for (int i = 1; i <= 221; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
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

