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
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Collects information mapping the generated (compiled) source back to
 * its original source for debugging purposes.
 *
 */
public class SourceMapGeneratorV1 implements SourceMapGenerator {
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.ping();
  }


  private final static int UNMAPPED = -1;
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[1]++;
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
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[2]++;
  }

    /**
     * The input source file.
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
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[3]++;
  }
  }

  private class MappingWriter {
    /**
     * Cache of escaped source file name.
     */
    private String lastSourceFile = null;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[4]++;
  }
    private String lastSourceFileEscaped = null;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[5]++;
  }
    private int lastLine = 0;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[6]++;
  }
    private String lastLineString = String.valueOf(0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[7]++;
  }

    /**
     * Appends the mapping to the given buffer.
     */
    private void appendMappingTo(
        Mapping m, Appendable out) throws IOException {
      out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[8]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[9]++;

      String sourceFile = m.sourceFile;
      // The source file rarely changes, so cache the escaped string.
      String escapedSourceFile;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lastSourceFile != sourceFile) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[1]++; // yes, s1 != s2, not !s1.equals(s2)
        lastSourceFile = sourceFile;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[11]++;
        lastSourceFileEscaped = escapeString(sourceFile);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[12]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[2]++;}
      escapedSourceFile = lastSourceFileEscaped;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[13]++;

      out.append(escapedSourceFile);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[14]++;
      out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[15]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[16]++;

      int line = m.originalPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((line != lastLine) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[3]++;
        lastLineString = String.valueOf(line);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[18]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[4]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[19]++;
      String lineValue = lastLineString;

      out.append(lineValue);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[20]++;

      out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[21]++;
      out.append(String.valueOf(
          m.originalPosition.getColumn()));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[22]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((m.originalName != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[5]++;
        out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[24]++;
        out.append(escapeString(m.originalName));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[25]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[6]++;}

      out.append("]\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[26]++;
    }

    /**
     * Add used mappings to the supplied Appendable.
     */
    void appendMappings(Appendable out) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[27]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[1]++;


      for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[1]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[2]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[3]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((m.used) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[7]++;
          appendMappingTo(m, out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[29]++;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[8]++;}
      }
    }
  }

  /**
   * A pre-order traversal ordered list of mappings stored in this map.
   */
  private List<Mapping> mappings = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[30]++;
  }

  /**
   * For validation store the start of the last mapping added.
   */
  private Mapping lastMapping;

  /**
   * The position that the current source map is offset in the
   * buffer being used to generated the compiled source file.
   */
  private FilePosition offsetPosition = new FilePosition(0, 0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[31]++;
  }

  /**
   * The position that the current source map is offset in the
   * generated the compiled source file by the addition of a
   * an output wrapper prefix.
   */
  private FilePosition prefixPosition = new FilePosition(0, 0);
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[32]++;
  }

  /**
   * Escapes the given string for JSON.
   */
  private static String escapeString(String value) {
    return Util.escapeString(value);
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;

    // Don't bother if there is not sufficient information to be useful.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((sourceStartPosition.getLine() < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[9]++;
      return;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[10]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[34]++;

    // Create the new mapping.
    Mapping mapping = new Mapping();
    mapping.sourceFile = sourceName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[35]++;
    mapping.originalPosition = sourceStartPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[36]++;
    mapping.originalName = symbolName;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[37]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[38]++;
int CodeCoverConditionCoverageHelper_C6; // may be null

    // NOTE: When multiple outputs are concatenated together, the positions in
    // the mapping are relative to offsetPosition.
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((offsetPosition.getLine() == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((offsetPosition.getColumn() == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[11]++;
      mapping.startPosition = startPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[39]++;
      mapping.endPosition = endPosition;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[40]++;

    } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[12]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[41]++;
      // If the mapping is found on the first line, we need to offset
      // its character position by the number of characters found on
      // the *last* line of the source file to which the code is
      // being generated.
      int offsetLine = offsetPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[42]++;
      int startOffsetPosition = offsetPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[43]++;
      int endOffsetPosition = offsetPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((startPosition.getLine() > 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[13]++;
        startOffsetPosition = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[45]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[14]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((endPosition.getLine() > 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[15]++;
        endOffsetPosition = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[47]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[16]++;}

      mapping.startPosition =
          new FilePosition(startPosition.getLine() + offsetLine,
                       startPosition.getColumn() + startOffsetPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[48]++;

      mapping.endPosition =
          new FilePosition(endPosition.getLine() + offsetLine,
                       endPosition.getColumn() + endOffsetPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[49]++;
    }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;

    // Validate the mappings are in a proper order.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((lastMapping != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[17]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[51]++;
      int lastLine = lastMapping.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[52]++;
      int lastColumn = lastMapping.startPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[53]++;
      int nextLine = mapping.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[54]++;
      int nextColumn = mapping.startPosition.getColumn();
      Preconditions.checkState(nextLine > lastLine
          || (nextLine == lastLine && nextColumn >= lastColumn),
          "Incorrect source mappings order, previous : (%s,%s)\n"
          + "new : (%s,%s)\nnode : %s",
          lastLine, lastColumn, nextLine, nextColumn);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[55]++;

    } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[18]++;}

    lastMapping = mapping;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[56]++;
    mappings.add(mapping);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[57]++;
  }

  /**
   * Sets the prefix used for wrapping the generated source file before
   * it is output. This ensures that the source map is adjusted as
   * needed.
   *
   * @param prefix The prefix that is added before the generated source code.
   */
  @Override
  public void setWrapperPrefix(String prefix) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[58]++;
    // Determine the current line and character position.
    int prefixLine = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[59]++;
    int prefixIndex = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[60]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < prefix.length()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[4]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[5]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[6]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[61]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((prefix.charAt(i) == '\n') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[19]++;
        prefixLine++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[62]++;
        prefixIndex = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[63]++;

      } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[20]++;
        prefixIndex++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[64]++;
      }
    }

    prefixPosition = new FilePosition(prefixLine, prefixIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[65]++;
  }

  /**
   * Sets the source code that exists in the buffer to which the
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[66]++;
    Preconditions.checkState(offsetIndex >= 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[67]++;
    offsetPosition = new FilePosition(offsetLine, offsetIndex);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[68]++;
  }

  /**
   * Resets the source map for reuse for the generation of a new source file.
   */
  @Override
  public void reset() {
    mappings = Lists.newArrayList();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[69]++;
    lastMapping = null;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[70]++;
    offsetPosition = new FilePosition(0, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[71]++;
    prefixPosition = new FilePosition(0, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[72]++;
  }

  /**
   * Appends the source map in LavaBug format to the given buffer.
   *
   * @param out The stream to which the map will be appended.
   * @param name The name of the generated source file that this source map
   *   represents.
   */
  @Override
  public void appendTo(Appendable out, String name) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[73]++;
    // Write the mappings out to the file. The format of the generated
    // source map is three sections, each delimited by a magic comment.
    //
    // The first section contains an array for each line of the generated
    // code, where each element in the array is the ID of the mapping which
    // best represents the index-th character found on that line of the
    // generated source code.
    //
    // The second section contains an array per generated line. Unused.
    //
    // The third and final section contains an array per line, each of which
    // represents a mapping with a unique ID. The mappings are added in order.
    // The array itself contains a tuple representing
    // ['source file', line, col (, 'original name')]
    //
    // Example for 2 lines of generated code (with line numbers added for
    // readability):
    //
    // 1)  /** Begin line maps. **/{ "count": 2 }
    // 2)  [0,0,0,0,0,0,1,1,1,1,2]
    // 3)  [2,2,2,2,2,2,3,4,4,4,4,4]
    // 4)  /** Begin file information. **/
    // 5)  []
    // 6)  []
    // 7)  /** Begin mapping definitions. **/
    // 8)  ["a.js", 1, 34]
    // 9)  ["a.js", 5, 2]
    // 10) ["b.js", 1, 3, "event"]
    // 11) ["c.js", 1, 4]
    // 12) ["d.js", 3, 78, "foo"]

    int maxLine = prepMappings();

    // Add the line character maps.
    out.append("/** Begin line maps. **/{ \"file\" : ");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[74]++;
    out.append(escapeString(name));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[75]++;
    out.append(", \"count\": ");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[76]++;
    out.append(String.valueOf(maxLine + 1));
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[77]++;
    out.append(" }\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[78]++;
    (new LineMapper(out)).appendLineMappings();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[79]++;

    // Add the source file maps.
    out.append("/** Begin file information. **/\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[80]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[81]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;

    // This section is unused but we need one entry per line to
    // prevent changing the format.
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i <= maxLine) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[7]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[8]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[9]++;
}
      out.append("[]\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[82]++;
    }

    // Add the mappings themselves.
    out.append("/** Begin mapping definitions. **/\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[83]++;

    (new MappingWriter()).appendMappings(out);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[84]++;
  }

  /**
   * Assigns sequential ids to used mappings, and returns the last line mapped.
   */
  private int prepMappings() throws IOException {
    // Mark any unused mappings.
    (new MappingTraversal()).traverse(new UsedMappingCheck());
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[85]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[86]++;

    // Renumber used mappings and keep track of the last line.
    int id = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[87]++;
    int maxLine = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[88]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[10]++;


    for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[10]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[11]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[12]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[89]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((m.used) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[21]++;
        m.id = id++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[90]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[91]++;
        int endPositionLine = m.endPosition.getLine();
        maxLine = Math.max(maxLine, endPositionLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[92]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[22]++;}
    }

    // Adjust for the prefix.
    return maxLine + prefixPosition.getLine();
  }

  private class LineMapper implements MappingVisitor {
    // The destination.
    private final Appendable out;

    // Whether the current line has had a value written yet.
    private boolean firstChar = true;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[93]++;
  }

    private final static String UNMAPPED_STRING = "-1";

    private int lastId = UNMAPPED;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[94]++;
  }
    private String lastIdString = UNMAPPED_STRING;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[95]++;
  }

    LineMapper(Appendable out) {
      this.out = out;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[96]++;
    }

    /**
     * As each segment is visited write out the appropriate line mapping.
     */
    @Override
    public void visit(Mapping m, int line, int col, int nextLine, int nextCol)
      throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[97]++;

      int id = (m != null) ? m.id : UNMAPPED;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[98]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lastId != id) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[23]++;
        // Prevent the creation of unnecessary temporary stings for often
        // repeated values.
        lastIdString = (id == UNMAPPED) ? UNMAPPED_STRING : String.valueOf(id);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[99]++;
        lastId = id;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[100]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[24]++;}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[101]++;
      String idString = lastIdString;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[102]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;

      for (int i = line;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i <= nextLine) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[13]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[14]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[15]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[103]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[25]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[104]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[16]++;


int CodeCoverConditionCoverageHelper_C17;
          for (int j = col;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((j < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[16]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[17]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[18]++;
}
            addCharEntry(idString);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[105]++;
          }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[106]++;
          break;

        } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[26]++;}

        closeLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[107]++;
        openLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[108]++;

        // Set the starting location for the next line.
        col = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[109]++;
      }
    }

    // Append the line mapping entries.
    void appendLineMappings() throws IOException {
      // Start the first line.
      openLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[110]++;

      (new MappingTraversal()).traverse(this);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[111]++;

      // And close the final line.
      closeLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[112]++;
    }

    /**
     * Begin the entry for a new line.
     */
    private void openLine() throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[113]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((out != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[27]++;
        out.append("[");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[114]++;
        this.firstChar = true;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[115]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[28]++;}
    }

    /**
     * End the entry for a line.
     */
    private void closeLine() throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[116]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((out != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[29]++;
        out.append("]\n");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[117]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[30]++;}
    }

    /**
     * Add a new char position entry.
     * @param id The mapping id to record.
     */
    private void addCharEntry(String id) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[118]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((out != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[31]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[119]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((firstChar) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[33]++;
          firstChar = false;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[120]++;

        } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[34]++;
          out.append(",");
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[121]++;
        }
        out.append(id);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[122]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[32]++;}
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[123]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[35]++;
        m.used = true;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[124]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[36]++;}
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[125]++;
      // The mapping list is ordered as a pre-order traversal.  The mapping
      // positions give us enough information to rebuild the stack and this
      // allows the building of the source map in O(n) time.
      Deque<Mapping> stack = new ArrayDeque<Mapping>();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[126]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[19]++;


      for (Mapping m : mappings) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[19]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[20]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[21]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[127]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[22]++;


int CodeCoverConditionCoverageHelper_C23;
        // Find the closest ancestor of the current mapping:
        // An overlapping mapping is an ancestor of the current mapping, any
        // non-overlapping mappings are siblings (or cousins) and must be
        // closed in the reverse order of when they encountered.
        while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isOverlapped(stack.peek(), m)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[22]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[23]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[24]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[128]++;
          Mapping previous = stack.pop();
          maybeVisit(v, previous);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[129]++;
        }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[130]++;

        // Any gaps between the current line position and the start of the
        // current mapping belong to the parent.
        Mapping parent = stack.peek();
        maybeVisitParent(v, parent, m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[131]++;

        stack.push(m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[132]++;
      }
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[133]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[25]++;


int CodeCoverConditionCoverageHelper_C24;

      // There are no more children to be had, simply close the remaining
      // mappings in the reverse order of when they encountered.
      while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[25]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[26]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.loops[27]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[134]++;
        Mapping m = stack.pop();
        maybeVisit(v, m);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[135]++;
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
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[136]++;
      int rawLine = p.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[137]++;
      int rawCol = p.getColumn();
      // Only the first line needs the character position adjusted.
      return (rawLine != 0)
          ? rawCol : rawCol + prefixPosition.getColumn();
    }

    /**
     * @return Whether m1 ends before m2 starts.
     */
    private boolean isOverlapped(Mapping m1, Mapping m2) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[138]++;
      // No need to use adjusted values here, relative positions are sufficient.
      int l1 = m1.endPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[139]++;
      int l2 = m2.startPosition.getLine();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[140]++;
      int c1 = m1.endPosition.getColumn();
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[141]++;
      int c2 = m2.startPosition.getColumn();

      return (l1 == l2 && c1 >= c2) || l1 > l2;
    }

    /**
     * Write any needed entries from the current position to the end of the
     * provided mapping.
     */
    private void maybeVisit(MappingVisitor v, Mapping m) throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[142]++;
      int nextLine = getAdjustedLine(m.endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[143]++;
      int nextCol = getAdjustedCol(m.endPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[144]++;
int CodeCoverConditionCoverageHelper_C25;
      // If this anything remaining in this mapping beyond the
      // current line and column position, write it out now.
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((line < nextLine) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((col < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[37]++;
        visit(v, m, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[145]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[38]++;}
    }

    /**
     * Write any needed entries to complete the provided mapping.
     */
    private void maybeVisitParent(MappingVisitor v, Mapping parent, Mapping m)
        throws IOException {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[146]++;
      int nextLine = getAdjustedLine(m.startPosition);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[147]++;
      int nextCol = getAdjustedCol(m.startPosition);
      // If the previous value is null, no mapping exists.
      Preconditions.checkState(line < nextLine || col <= nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[148]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[149]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((line < nextLine) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((col < nextCol) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[39]++;
        visit(v, parent, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[150]++;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[40]++;}
    }

    /**
     * Write any entries needed between the current position the next position
     * and update the current position.
     */
    private void visit(MappingVisitor v, Mapping m,
        int nextLine, int nextCol)
        throws IOException {
      Preconditions.checkState(line <= nextLine);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[151]++;
      Preconditions.checkState(line < nextLine || col < nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[152]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[153]++;
int CodeCoverConditionCoverageHelper_C27;

      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((line == nextLine) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((col == nextCol) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[41]++;
        // Nothing to do.
        Preconditions.checkState(false);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[154]++;
        return;

      } else {
  CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.branches[42]++;}

      v.visit(m, line, col, nextLine, nextCol);
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[155]++;

      line = nextLine;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[156]++;
      col = nextCol;
CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx.statements[157]++;
    }
  }

  @Override
  public void validate(boolean validate) {
    // No additional validation to do.
  }

  @Override
  public void appendIndexMapTo(
      Appendable out, String name, List<SourceMapSection> appSections) {
    throw new UnsupportedOperationException();
  }
}

class CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx ());
  }
    public static long[] statements = new long[158];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapGeneratorV1.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,3,3,2};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$3sqvqynh2c9zerff1hfpxue08xc4i7tba58qhcx () {
    super("com.google.debugging.sourcemap.SourceMapGeneratorV1.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 157; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapGeneratorV1.java");
      for (int i = 1; i <= 157; i++) {
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
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

