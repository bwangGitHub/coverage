/*
 * Copyright 2004 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.debugging.sourcemap.FilePosition;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSTypeRegistry;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * CodePrinter prints out JS code in either pretty format or compact format.
 *
 * @see CodeGenerator
 */
class CodePrinter {
  static {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.ping();
  }

  // The number of characters after which we insert a line break in the code
  static final int DEFAULT_LINE_LENGTH_THRESHOLD = 500;
  static {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[1]++;
  }


  // There are two separate CodeConsumers, one for pretty-printing and
  // another for compact printing.

  // There are two implementations because the CompactCodePrinter
  // potentially has a very different implementation to the pretty
  // version.

  private abstract static class MappedCodePrinter extends CodeConsumer {
    final private Deque<Mapping> mappings;
    final private List<Mapping> allMappings;
    final private boolean createSrcMap;
    final private SourceMap.DetailLevel sourceMapDetailLevel;
    protected final StringBuilder code = new StringBuilder(1024);
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[2]++;
  }
    protected final int lineLengthThreshold;
    protected int lineLength = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[3]++;
  }
    protected int lineIndex = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[4]++;
  }

    MappedCodePrinter(
        int lineLengthThreshold,
        boolean createSrcMap,
        SourceMap.DetailLevel sourceMapDetailLevel) {
      Preconditions.checkState(sourceMapDetailLevel != null);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[5]++;
      this.lineLengthThreshold = lineLengthThreshold <= 0 ? Integer.MAX_VALUE :
        lineLengthThreshold;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[6]++;
      this.createSrcMap = createSrcMap;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[7]++;
      this.sourceMapDetailLevel = sourceMapDetailLevel;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[8]++;
      this.mappings = createSrcMap ? new ArrayDeque<Mapping>() : null;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[9]++;
      this.allMappings = createSrcMap ? new ArrayList<Mapping>() : null;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[10]++;
    }

    /**
     * Maintains a mapping from a given node to the position
     * in the source code at which its generated form was
     * placed. This position is relative only to the current
     * run of the CodeConsumer and will be normalized
     * later on by the SourceMap.
     *
     * @see SourceMap
     */
    private static class Mapping {
      Node node;
      FilePosition start;
      FilePosition end;
    }

    /**
     * Starts the source mapping for the given
     * node at the current position.
     */
    @Override
    void startSourceMapping(Node node) {
      Preconditions.checkState(sourceMapDetailLevel != null);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[11]++;
      Preconditions.checkState(node != null);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[12]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((createSrcMap) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((node.getSourceFileName() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((node.getLineno() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sourceMapDetailLevel.apply(node)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[1]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[14]++;
        int line = getCurrentLineIndex();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[15]++;
        int index = getCurrentCharIndex();
        Preconditions.checkState(line >= 0);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[16]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[17]++;
        Mapping mapping = new Mapping();
        mapping.node = node;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[18]++;
        mapping.start = new FilePosition(line, index);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[19]++;
        mappings.push(mapping);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[20]++;
        allMappings.add(mapping);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[21]++;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[2]++;}
    }

    /**
     * Finishes the source mapping for the given
     * node at the current position.
     */
    @Override
    void endSourceMapping(Node node) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((createSrcMap) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((mappings.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((mappings.peek().node == node) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[3]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[23]++;
        Mapping mapping = mappings.pop();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[24]++;
        int line = getCurrentLineIndex();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[25]++;
        int index = getCurrentCharIndex();
        Preconditions.checkState(line >= 0);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[26]++;
        mapping.end = new FilePosition(line, index);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[27]++;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[4]++;}
    }

    /**
     * Generates the source map from the given code consumer,
     * appending the information it saved to the SourceMap
     * object given.
     */
    void generateSourceMap(SourceMap map){
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((createSrcMap) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[5]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[1]++;


        for (Mapping mapping : allMappings) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[1]--;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[2]--;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[3]++;
}
          map.addMapping(mapping.node, mapping.start, mapping.end);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[30]++;
        }

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[6]++;}
    }

    /**
     * Reports to the code consumer that the given line has been cut at the
     * given position, i.e. a \n has been inserted there. Or that a cut has
     * been undone, i.e. a previously inserted \n has been removed.
     * All mappings in the source maps after that position will be renormalized
     * as needed.
     */
    void reportLineCut(int lineIndex, int charIndex, boolean insertion) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((createSrcMap) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[7]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[4]++;


        for (Mapping mapping : allMappings) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[4]--;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[5]--;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[6]++;
}
          mapping.start = convertPosition(mapping.start, lineIndex, charIndex,
              insertion);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[33]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;

          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((mapping.end != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[9]++;
            mapping.end = convertPosition(mapping.end, lineIndex, charIndex,
                insertion);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[35]++;

          } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[10]++;}
        }

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[8]++;}
    }

    /**
     * Converts the given position by normalizing it against the insertion
     * or removal of a newline at the given line and character position.
     *
     * @param position The existing position before the newline was inserted.
     * @param lineIndex The index of the line at which the newline was inserted.
     * @param characterPosition The position on the line at which the newline
     *     was inserted.
     * @param insertion True if a newline was inserted, false if a newline was
     *     removed.
     *
     * @return The normalized position.
     * @throws IllegalStateException if an attempt to reverse a line cut is
     *     made on a previous line rather than the current line.
     */
    private FilePosition convertPosition(FilePosition position, int lineIndex,
                                     int characterPosition, boolean insertion) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[36]++;
      int originalLine = position.getLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[37]++;
      int originalChar = position.getColumn();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((insertion) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[11]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((originalLine == lineIndex) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((originalChar >= characterPosition) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[13]++;
          // If the position falls on the line itself, then normalize it
          // if it falls at or after the place the newline was inserted.
          return new FilePosition(
              originalLine + 1, originalChar - characterPosition);

        } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[14]++;
          return position;
        }

      } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[12]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((originalLine == lineIndex) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[15]++;
          return new FilePosition(
              originalLine - 1, originalChar + characterPosition);

        } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[16]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[41]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((originalLine > lineIndex) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[17]++;
            // Not supported, can only undo a cut on the most recent line. To
            // do this on a previous lines would require reevaluating the cut
            // positions on all subsequent lines.
            throw new IllegalStateException(
                "Cannot undo line cut on a previous line.");

        } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[18]++;
          return position;
        }
}
      }
    }

    public String getCode() {
      return code.toString();
    }

    @Override
    char getLastChar() {
      return (code.length() > 0) ? code.charAt(code.length() - 1) : '\0';
    }

    protected final int getCurrentCharIndex() {
      return lineLength;
    }

    protected final int getCurrentLineIndex() {
      return lineIndex;
    }
  }

  static class PrettyCodePrinter
      extends MappedCodePrinter {
    // The number of characters after which we insert a line break in the code
    static final String INDENT = "  ";

    private int indent = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[42]++;
  }

    /**
     * @param lineLengthThreshold The length of a line after which we force
     *                            a newline when possible.
     * @param createSourceMap Whether to generate source map data.
     * @param sourceMapDetailLevel A filter to control which nodes get mapped
     *     into the source map.
     */
    private PrettyCodePrinter(
        int lineLengthThreshold,
        boolean createSourceMap,
        SourceMap.DetailLevel sourceMapDetailLevel) {
      super(lineLengthThreshold, createSourceMap, sourceMapDetailLevel);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[43]++;
    }

    /**
     * Appends a string to the code, keeping track of the current line length.
     */
    @Override
    void append(String str) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
      // For pretty printing: indent at the beginning of the line
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((lineLength == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[19]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[45]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < indent) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[7]--;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[8]--;
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.loops[9]++;
}
          code.append(INDENT);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[46]++;
          lineLength += INDENT.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[47]++;
        }

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[20]++;}
      code.append(str);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[48]++;
      lineLength += str.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[49]++;
    }

    /**
     * Adds a newline to the code, resetting the line length and handling
     * indenting for pretty printing.
     */
    @Override
    void startNewLine() {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lineLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[21]++;
        code.append('\n');
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[51]++;
        lineIndex++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[52]++;
        lineLength = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[53]++;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[22]++;}
    }

    @Override
    void maybeLineBreak() {
      maybeCutLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[54]++;
    }

    /**
     * This may start a new line if the current line is longer than the line
     * length threshold.
     */
    @Override
    void maybeCutLine() {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((lineLength > lineLengthThreshold) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[23]++;
        startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[56]++;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[24]++;}
    }

    @Override
    void endLine() {
      startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[57]++;
    }

    @Override
    void appendBlockStart() {
      append(" {");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[58]++;
      indent++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[59]++;
    }

    @Override
    void appendBlockEnd() {
      endLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[60]++;
      indent--;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[61]++;
      append("}");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[62]++;
    }

    @Override
    void listSeparator() {
      add(", ");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[63]++;
      maybeLineBreak();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[64]++;
    }

    @Override
    void endFunction(boolean statementContext) {
      super.endFunction(statementContext);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[65]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[66]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((statementContext) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[25]++;
        startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[67]++;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[26]++;}
    }

    @Override
    void beginCaseBody() {
      super.beginCaseBody();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[68]++;
      indent++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[69]++;
      endLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[70]++;
    }

    @Override
    void endCaseBody() {
      super.endCaseBody();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[71]++;
      indent--;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[72]++;
      endStatement();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[73]++;
    }

    @Override
    void appendOp(String op, boolean binOp) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[74]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((binOp) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[27]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[75]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((getLastChar() != ' ') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((op.charAt(0) != ',') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[29]++;
          append(" ");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[76]++;

        } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[30]++;}
        append(op);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[77]++;
        append(" ");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[78]++;

      } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[28]++;
        append(op);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[79]++;
      }
    }

    /**
     * If the body of a for loop or the then clause of an if statement has
     * a single statement, should it be wrapped in a block?
     * {@inheritDoc}
     */
    @Override
    boolean shouldPreserveExtraBlocks() {
      // When pretty-printing, always place the statement in its own block
      // so it is printed on a separate line.  This allows breakpoints to be
      // placed on the statement.
      return true;
    }

    /**
     * @return The TRY node for the specified CATCH node.
     */
    private Node getTryForCatch(Node n) {
      return n.getParent().getParent();
    }

    /**
     * @return Whether the a line break should be added after the specified
     * BLOCK.
     */
    @Override
    boolean breakAfterBlockFor(Node n,  boolean isStatementContext) {
      Preconditions.checkState(n.isBlock());
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[80]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[81]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[82]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[31]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[83]++;
        int type = parent.getType();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[84]++;
        switch (type) {
          case Token.DO:
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[33]++;
            // Don't break before 'while' in DO-WHILE statements.
            return false;
          case Token.FUNCTION:
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[34]++;
            // FUNCTIONs are handled separately, don't break here.
            return false;
          case Token.TRY:
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[35]++;
            // Don't break before catch
            return n != parent.getFirstChild();
          case Token.CATCH:
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[36]++;
            // Don't break before finally
            return !NodeUtil.hasFinally(getTryForCatch(parent));
          case Token.IF:
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[37]++;
            // Don't break before else
            return n == parent.getLastChild(); default : CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[38]++;
        }

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[32]++;}
      return true;
    }

    @Override
    void endFile() {
      maybeEndStatement();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[85]++;
    }
  }


  static class CompactCodePrinter
      extends MappedCodePrinter {

    // The CompactCodePrinter tries to emit just enough newlines to stop there
    // being lines longer than the threshold.  Since the output is going to be
    // gzipped, it makes sense to try to make the newlines appear in similar
    // contexts so that gzip can encode them for 'free'.
    //
    // This version tries to break the lines at 'preferred' places, which are
    // between the top-level forms.  This works because top-level forms tend to
    // be more uniform than arbitrary legal contexts.  Better compression would
    // probably require explicit modeling of the gzip algorithm.

    private final boolean lineBreak;
    private final boolean preferLineBreakAtEndOfFile;
    private int lineStartPosition = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[86]++;
  }
    private int preferredBreakPosition = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[87]++;
  }
    private int prevCutPosition = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[88]++;
  }
    private int prevLineStartPosition = 0;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[89]++;
  }

  /**
   * @param lineBreak break the lines a bit more aggressively
   * @param lineLengthThreshold The length of a line after which we force
   *                            a newline when possible.
   * @param createSrcMap Whether to gather source position
   *                            mapping information when printing.
   * @param sourceMapDetailLevel A filter to control which nodes get mapped into
   *     the source map.
   */
    private CompactCodePrinter(boolean lineBreak,
        boolean preferLineBreakAtEndOfFile, int lineLengthThreshold,
        boolean createSrcMap, SourceMap.DetailLevel sourceMapDetailLevel) {
      super(lineLengthThreshold, createSrcMap, sourceMapDetailLevel);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[90]++;
      this.lineBreak = lineBreak;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[91]++;
      this.preferLineBreakAtEndOfFile = preferLineBreakAtEndOfFile;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[92]++;
    }

    /**
     * Appends a string to the code, keeping track of the current line length.
     */
    @Override
    void append(String str) {
      code.append(str);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[93]++;
      lineLength += str.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[94]++;
    }

    /**
     * Adds a newline to the code, resetting the line length.
     */
    @Override
    void startNewLine() {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[95]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lineLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[39]++;
        prevCutPosition = code.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[96]++;
        prevLineStartPosition = lineStartPosition;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[97]++;
        code.append('\n');
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[98]++;
        lineLength = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[99]++;
        lineIndex++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[100]++;
        lineStartPosition = code.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[101]++;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[40]++;}
    }

    @Override
    void maybeLineBreak() {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[102]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((lineBreak) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[41]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[103]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((sawFunction) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[43]++;
          startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[104]++;
          sawFunction = false;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[105]++;

        } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[44]++;}

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[42]++;}
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[106]++;

      // Since we are at a legal line break, can we upgrade the
      // preferred break position?  We prefer to break after a
      // semicolon rather than before it.
      int len = code.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[107]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((preferredBreakPosition == len - 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[45]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[108]++;
        char ch = code.charAt(len - 1);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[109]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ch == ';') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[47]++;
          preferredBreakPosition = len;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[110]++;

        } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[48]++;}

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[46]++;}
      maybeCutLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[111]++;
    }

    /**
     * This may start a new line if the current line is longer than the line
     * length threshold.
     */
    @Override
    void maybeCutLine() {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[112]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((lineLength > lineLengthThreshold) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[49]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[113]++;
int CodeCoverConditionCoverageHelper_C24;
        // Use the preferred position provided it will break the line.
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((preferredBreakPosition > lineStartPosition) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((preferredBreakPosition < lineStartPosition + lineLength) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[51]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[114]++;
          int position = preferredBreakPosition;
          code.insert(position, '\n');
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[115]++;
          prevCutPosition = position;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[116]++;
          reportLineCut(lineIndex, position - lineStartPosition, true);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[117]++;
          lineIndex++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[118]++;
          lineLength -= (position - lineStartPosition);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[119]++;
          lineStartPosition = position + 1;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[120]++;

        } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[52]++;
          startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[121]++;
        }

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[50]++;}
    }

    @Override
    void notePreferredLineBreak() {
      preferredBreakPosition = code.length();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[122]++;
    }

    @Override
    void endFile() {
      super.endFile();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[123]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[124]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((preferLineBreakAtEndOfFile) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[53]++;
        return;

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[54]++;}
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[125]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lineLength > lineLengthThreshold / 2) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[55]++;
        // Add an extra break at end of file.
        append(";");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[126]++;
        startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[127]++;

      } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[56]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[128]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((prevCutPosition > 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[57]++;
        // Shift the previous break to end of file by replacing it with a
        // <space> and adding a new break at end of file. Adding the space
        // handles cases like instanceof\nfoo. (it would be nice to avoid this)
        code.setCharAt(prevCutPosition, ' ');
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[129]++;
        lineStartPosition = prevLineStartPosition;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[130]++;
        lineLength = code.length() - lineStartPosition;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[131]++;
        reportLineCut(lineIndex, prevCutPosition + 1, false);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[132]++;
        lineIndex--;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[133]++;
        prevCutPosition = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[134]++;
        prevLineStartPosition = 0;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[135]++;
        append(";");
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[136]++;
        startNewLine();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[137]++;

      } else {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[58]++;
        // A small file with no line breaks. We do nothing in this case to
        // avoid excessive line breaks. It's not ideal if a lot of these pile
        // up, but that is reasonably unlikely.
      }
}
    }

  }

  static class Builder {
    private final Node root;
    private CompilerOptions options = new CompilerOptions();
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[138]++;
  }
    private boolean outputTypes = false;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[139]++;
  }
    private SourceMap sourceMap = null;
  {
    CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[140]++;
  }
    private boolean tagAsStrict;
    private JSTypeRegistry registry;

    /**
     * Sets the root node from which to generate the source code.
     * @param node The root node.
     */
    Builder(Node node) {
      root = node;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[141]++;
    }

    /**
     * Sets the output options from compiler options.
     */
    Builder setCompilerOptions(CompilerOptions options) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[142]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        this.options = (CompilerOptions) options.clone();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[143]++;
      } catch (CloneNotSupportedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[60]++;
        throw Throwables.propagate(e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[59]++;
}
  }
      return this;
    }

    Builder setTypeRegistry(JSTypeRegistry registry) {
      this.registry = registry;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[144]++;
      return this;
    }

    /**
     * Sets whether pretty printing should be used.
     * @param prettyPrint If true, pretty printing will be used.
     */
    Builder setPrettyPrint(boolean prettyPrint) {
      options.prettyPrint = prettyPrint;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[145]++;
      return this;
    }

    /**
     * Sets whether line breaking should be done automatically.
     * @param lineBreak If true, line breaking is done automatically.
     */
    Builder setLineBreak(boolean lineBreak) {
      options.lineBreak = lineBreak;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[146]++;
      return this;
    }

    /**
     * Sets whether to output closure-style type annotations.
     * @param outputTypes If true, outputs closure-style type annotations.
     */
    Builder setOutputTypes(boolean outputTypes) {
      this.outputTypes = outputTypes;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[147]++;
      return this;
    }

    /**
     * Sets the source map to which to write the metadata about
     * the generated source code.
     *
     * @param sourceMap The source map.
     */
    Builder setSourceMap(SourceMap sourceMap) {
      this.sourceMap = sourceMap;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[148]++;
      return this;
    }

    /**
     * Set whether the output should be tags as ECMASCRIPT 5 Strict.
     */
    Builder setTagAsStrict(boolean tagAsStrict) {
      this.tagAsStrict = tagAsStrict;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[149]++;
      return this;
    }

    /**
     * Generates the source code and returns it.
     */
    String build() {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[150]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[61]++;
        throw new IllegalStateException(
            "Cannot build without root node being specified");

      } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[62]++;}
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[151]++;

      Format outputFormat = outputTypes
          ? Format.TYPED
          : options.prettyPrint
              ? Format.PRETTY
              : Format.COMPACT;

      return toSource(root, outputFormat, options, registry,
          sourceMap, tagAsStrict);
    }
  }

  enum Format {
    COMPACT,
    PRETTY,
    TYPED
  }

  /**
   * Converts a tree to JS code
   */
  private static String toSource(Node root, Format outputFormat,
      CompilerOptions options, JSTypeRegistry registry,
      SourceMap sourceMap,  boolean tagAsStrict) {
    Preconditions.checkState(options.sourceMapDetailLevel != null);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[152]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[153]++;

    boolean createSourceMap = (sourceMap != null);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[154]++;
    MappedCodePrinter mcp =
        outputFormat == Format.COMPACT
        ? new CompactCodePrinter(
            options.lineBreak,
            options.preferLineBreakAtEndOfFile,
            options.lineLengthThreshold,
            createSourceMap,
            options.sourceMapDetailLevel)
        : new PrettyCodePrinter(
            options.lineLengthThreshold,
            createSourceMap,
            options.sourceMapDetailLevel);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[155]++;
    CodeGenerator cg =
        outputFormat == Format.TYPED
        ? new TypedCodeGenerator(mcp, options, registry)
        : new CodeGenerator(mcp, options);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[156]++;
int CodeCoverConditionCoverageHelper_C29;

    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((tagAsStrict) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[63]++;
      cg.tagAsStrict();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[157]++;

    } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[64]++;}

    cg.add(root);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[158]++;
    mcp.endFile();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[159]++;
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[160]++;

    String code = mcp.getCode();
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[161]++;
int CodeCoverConditionCoverageHelper_C30;

    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((createSourceMap) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[65]++;
      mcp.generateSourceMap(sourceMap);
CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.statements[162]++;

    } else {
  CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh.branches[66]++;}

    return code;
  }
}

class CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh ());
  }
    public static long[] statements = new long[163];
    public static long[] branches = new long[67];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[31];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CodePrinter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,3,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$3zq1szv3k9yf40g6hi00zw8kh () {
    super("com.google.javascript.jscomp.CodePrinter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 162; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 66; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 30; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CodePrinter.java");
      for (int i = 1; i <= 162; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 66; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

