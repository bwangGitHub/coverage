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

package com.google.javascript.jscomp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * An abstract representation of a source file that provides access to
 * language-neutral features. The source file can be loaded from various
 * locations, such as from disk or from a preloaded string.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public class SourceFile implements StaticSourceFile, Serializable {
  static {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[1]++;
  }

  /** A JavaScript source code provider.  The value should
   * be cached so that the source text stays consistent throughout a single
   * compile. */
  public interface Generator {
    public String getCode();
  }

  /**
   * Number of lines in the region returned by {@link #getRegion(int)}.
   * This length must be odd.
   */
  private static final int SOURCE_EXCERPT_REGION_LENGTH = 5;
  static {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[2]++;
  }

  private final String fileName;
  private boolean isExternFile = false;
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[3]++;
  }

  // The fileName may not always identify the original file - for example,
  // supersourced Java inputs, or Java inputs that come from Jar files. This
  // is an optional field that the creator of an AST or SourceFile can set.
  // It could be a path to the original file, or in case this SourceFile came
  // from a Jar, it could be the path to the Jar.
  private String originalPath = null;
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[4]++;
  }

  // Source Line Information
  private int[] lineOffsets = null;
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[5]++;
  }

  private String code = null;
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[6]++;
  }

  /**
   * Construct a new abstract source file.
   *
   * @param fileName The file name of the source file. It does not necessarily
   *     need to correspond to a real path. But it should be unique. Will
   *     appear in warning messages emitted by the compiler.
   */
  public SourceFile(String fileName) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((fileName == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((fileName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[1]++;
      throw new IllegalArgumentException("a source must have a name");

    } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[2]++;}
    this.fileName = fileName;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[8]++;
  }

  @Override
  public int getLineOffset(int lineno) {
    findLineOffsets();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[9]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((lineno < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((lineno > lineOffsets.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[3]++;
      throw new IllegalArgumentException(
          "Expected line number between 1 and " + lineOffsets.length +
          "\nActual: " + lineno);

    } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[4]++;}
    return lineOffsets[lineno - 1];
  }

  /** @return The number of lines in this source file. */
  int getNumLines() {
    findLineOffsets();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[11]++;
    return lineOffsets.length;
  }


  private void findLineOffsets() {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lineOffsets != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[6]++;}
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[14]++;
      String[] sourceLines = getCode().split("\n");
      lineOffsets = new int[sourceLines.length];
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[15]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
      for (int ii = 1;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ii < sourceLines.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); ++ii) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[1]--;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[2]--;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[3]++;
}
        lineOffsets[ii] =
            lineOffsets[ii - 1] + sourceLines[ii - 1].length() + 1;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[17]++;
      }
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[8]++;
      lineOffsets = new int[1];
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[18]++;
      lineOffsets[0] = 0;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[19]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[7]++;
}
  }
  }


  //////////////////////////////////////////////////////////////////////////////
  // Implementation

  /**
   * Gets all the code in this source file.
   * @throws IOException
   */
  public String getCode() throws IOException {
    return code;
  }

  /**
   * Gets a reader for the code in this source file.
   */
  public Reader getCodeReader() throws IOException {
    return new StringReader(getCode());
  }

  @VisibleForTesting
  String getCodeNoCache() {
    return code;
  }

  private void setCode(String sourceCode) {
    code = sourceCode;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[20]++;
  }

  public String getOriginalPath() {
    return originalPath != null ? originalPath : fileName;
  }

  public void setOriginalPath(String originalPath) {
    this.originalPath = originalPath;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[21]++;
  }

  // For SourceFile types which cache source code that can be regenerated
  // easily, flush the cache.  We maintain the cache mostly to speed up
  // generating source when displaying error messages, so dumping the file
  // contents after the compile is a fine thing to do.
  public void clearCachedSource() {
    // By default, do nothing.  Not all kinds of SourceFiles can regenerate
    // code.
  }

  boolean hasSourceInMemory() {
    return code != null;
  }

  /** Returns a unique name for the source file. */
  @Override
  public String getName() {
    return fileName;
  }

  /** Returns whether this is an extern. */
  @Override
  public boolean isExtern() {
    return isExternFile;
  }

  /** Sets that this is an extern. */
  void setIsExtern(boolean newVal) {
    isExternFile = newVal;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[22]++;
  }

  @Override
  public int getLineOfOffset(int offset) {
    findLineOffsets();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[23]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[24]++;
    int search = Arrays.binarySearch(lineOffsets, offset);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((search >= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[9]++;
      return search + 1;
 // lines are 1-based.
    } else {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[10]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[26]++;
      int insertionPoint = -1 * (search + 1);
      return Math.min(insertionPoint - 1, lineOffsets.length - 1) + 1;
    }
  }

  @Override
  public int getColumnOfOffset(int offset) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[27]++;
    int line = getLineOfOffset(offset);
    return offset - lineOffsets[line - 1];
  }

  /**
   * Gets the source line for the indicated line number.
   *
   * @param lineNumber the line number, 1 being the first line of the file.
   * @return The line indicated. Does not include the newline at the end
   *     of the file. Returns {@code null} if it does not exist,
   *     or if there was an IO exception.
   */
  public String getLine(int lineNumber) {
    findLineOffsets();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[28]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((lineNumber > lineOffsets.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[11]++;
      return null;

    } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[12]++;}
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lineNumber < 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[13]++;
      lineNumber = 1;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[31]++;

    } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[14]++;}
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[32]++;

    int pos = lineOffsets[lineNumber - 1];
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[33]++;
    String js = "";
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[34]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      // NOTE(nicksantos): Right now, this is optimized for few warnings.
      // This is probably the right trade-off, but will be slow if there
      // are lots of warnings in one file.
      js = getCode();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[35]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[16]++;
      return null;
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[15]++;
}
  }
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((js.indexOf('\n', pos) == -1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[17]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
      // If next new line cannot be found, there are two cases
      // 1. pos already reaches the end of file, then null should be returned
      // 2. otherwise, return the contents between pos and the end of file.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((pos >= js.length()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[19]++;
        return null;

      } else {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[20]++;
        return js.substring(pos, js.length());
      }

    } else {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[18]++;
      return js.substring(pos, js.indexOf('\n', pos));
    }
  }

  /**
   * Get a region around the indicated line number. The exact definition of a
   * region is implementation specific, but it must contain the line indicated
   * by the line number. A region must not start or end by a carriage return.
   *
   * @param lineNumber the line number, 1 being the first line of the file.
   * @return The line indicated. Returns {@code null} if it does not exist,
   *     or if there was an IO exception.
   */
  public Region getRegion(int lineNumber) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[38]++;
    String js = "";
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[39]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
    try {
CodeCoverTryBranchHelper_Try3 = true;
      js = getCode();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[40]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[22]++;
      return null;
    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[21]++;
}
  }
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[41]++;
    int pos = 0;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[42]++;
    int startLine = Math.max(1,
        lineNumber - (SOURCE_EXCERPT_REGION_LENGTH + 1) / 2 + 1);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[43]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
    for (int n = 1;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n < startLine) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); n++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[4]--;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[5]--;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[6]++;
}
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[44]++;
      int nextpos = js.indexOf('\n', pos);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((nextpos == -1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[23]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[46]++;
        break;

      } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[24]++;}
      pos = nextpos + 1;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[47]++;
    }
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[48]++;
    int end = pos;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[49]++;
    int endLine = startLine;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;
    for (int n = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n < SOURCE_EXCERPT_REGION_LENGTH) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); n++, endLine++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[7]--;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[8]--;
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.loops[9]++;
}
      end = js.indexOf('\n', end);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[51]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((end == -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[25]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[53]++;
        break;

      } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[26]++;}
      end++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[54]++;
    }
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[55]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lineNumber >= endLine) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[27]++;
      return null;

    } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[28]++;}
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[56]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((end == -1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[29]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[57]++;
      int last = js.length() - 1;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[58]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((js.charAt(last) == '\n') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[31]++;
        return
            new SimpleRegion(startLine, endLine, js.substring(pos, last));

      } else {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[32]++;
        return new SimpleRegion(startLine, endLine, js.substring(pos));
      }

    } else {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[30]++;
      return new SimpleRegion(startLine, endLine, js.substring(pos, end));
    }
  }

  @Override
  public String toString() {
    return fileName;
  }

  public static SourceFile fromFile(String fileName, Charset c) {
    return builder().withCharset(c).buildFromFile(fileName);
  }

  public static SourceFile fromFile(String fileName) {
    return builder().buildFromFile(fileName);
  }

  public static SourceFile fromFile(File file, Charset c) {
    return builder().withCharset(c).buildFromFile(file);
  }

  public static SourceFile fromFile(File file) {
    return builder().buildFromFile(file);
  }

  public static SourceFile fromCode(String fileName, String code) {
    return builder().buildFromCode(fileName, code);
  }

  public static SourceFile fromCode(String fileName,
      String originalPath, String code) {
    return builder().withOriginalPath(originalPath)
        .buildFromCode(fileName, code);
  }

  public static SourceFile fromInputStream(String fileName, InputStream s)
      throws IOException {
    return builder().buildFromInputStream(fileName, s);
  }

  public static SourceFile fromInputStream(String fileName,
      String originalPath, InputStream s) throws IOException {
    return builder().withOriginalPath(originalPath)
        .buildFromInputStream(fileName, s);
  }

  public static SourceFile fromReader(String fileName, Reader r)
      throws IOException {
    return builder().buildFromReader(fileName, r);
  }

  public static SourceFile fromGenerator(String fileName,
      Generator generator) {
    return builder().buildFromGenerator(fileName, generator);
  }

  /** Create a new builder for source files. */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * A builder interface for source files.
   *
   * Allows users to customize the Charset, and the original path of
   * the source file (if it differs from the path on disk).
   */
  public static class Builder {
    private Charset charset = Charsets.UTF_8;
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[59]++;
  }
    private String originalPath = null;
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[60]++;
  }

    public Builder() {}

    /** Set the charset to use when reading from an input stream or file. */
    public Builder withCharset(Charset charset) {
      this.charset = charset;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[61]++;
      return this;
    }

    /** Set the original path to use. */
    public Builder withOriginalPath(String originalPath) {
      this.originalPath = originalPath;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[62]++;
      return this;
    }

    public SourceFile buildFromFile(String fileName) {
      return buildFromFile(new File(fileName));
    }

    public SourceFile buildFromFile(File file) {
      return new OnDisk(file, originalPath, charset);
    }

    public SourceFile buildFromCode(String fileName, String code) {
      return new Preloaded(fileName, originalPath, code);
    }

    public SourceFile buildFromInputStream(String fileName, InputStream s)
        throws IOException {
      return buildFromCode(fileName,
          CharStreams.toString(new InputStreamReader(s, charset)));
    }

    public SourceFile buildFromReader(String fileName, Reader r)
        throws IOException {
      return buildFromCode(fileName, CharStreams.toString(r));
    }

    public SourceFile buildFromGenerator(String fileName,
        Generator generator) {
      return new Generated(fileName, originalPath, generator);
    }
  }


  //////////////////////////////////////////////////////////////////////////////
  // Implementations

  /**
   * A source file where the code has been preloaded.
   */
  static class Preloaded extends SourceFile {
    private static final long serialVersionUID = 1L;

    Preloaded(String fileName, String originalPath, String code) {
      super(fileName);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[63]++;
      super.setOriginalPath(originalPath);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[64]++;
      super.setCode(code);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[65]++;
    }
  }

  /**
   * A source file where the code will be dynamically generated
   * from the injected interface.
   */
  static class Generated extends SourceFile {
    private static final long serialVersionUID = 1L;
    private final Generator generator;

    // Not private, so that LazyInput can extend it.
    Generated(String fileName, String originalPath, Generator generator) {
      super(fileName);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[66]++;
      super.setOriginalPath(originalPath);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[67]++;
      this.generator = generator;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[68]++;
    }

    @Override
    public synchronized String getCode() throws IOException {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[69]++;
      String cachedCode = super.getCode();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((cachedCode == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[33]++;
        cachedCode = generator.getCode();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[71]++;
        super.setCode(cachedCode);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[72]++;

      } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[34]++;}
      return cachedCode;
    }

    // Clear out the generated code when finished with a compile; we can
    // regenerate it if we ever need it again.
    @Override
    public void clearCachedSource() {
      super.setCode(null);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[73]++;
    }
  }

  /**
   * A source file where the code is only read into memory if absolutely
   * necessary. We will try to delay loading the code into memory as long as
   * possible.
   */
  static class OnDisk extends SourceFile {
    private static final long serialVersionUID = 1L;
    private final File file;

    // This is stored as a String, but passed in and out as a Charset so that
    // we can serialize the class.
    // Default input file format for JSCompiler has always been UTF_8.
    private String inputCharset = Charsets.UTF_8.name();
  {
    CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[74]++;
  }

    OnDisk(File file, String originalPath, Charset c) {
      super(file.getPath());
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[75]++;
      this.file = file;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[76]++;
      super.setOriginalPath(originalPath);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[77]++;
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[78]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[35]++;
        this.setCharset(c);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[79]++;

      } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[36]++;}
    }

    @Override
    public synchronized String getCode() throws IOException {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[80]++;
      String cachedCode = super.getCode();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((cachedCode == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[37]++;
        cachedCode = Files.toString(file, this.getCharset());
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[82]++;
        super.setCode(cachedCode);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[83]++;

      } else {
  CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[38]++;}
      return cachedCode;
    }

    /**
     * Gets a reader for the code in this source file.
     */
    @Override
    public Reader getCodeReader() throws IOException {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[84]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((hasSourceInMemory()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[39]++;
        return super.getCodeReader();

      } else {
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.branches[40]++;
        // If we haven't pulled the code into memory yet, don't.
        return new FileReader(file);
      }
    }

    // Flush the cached code after the compile; we can read it off disk
    // if we need it again.
    @Override
    public void clearCachedSource() {
      super.setCode(null);
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[85]++;
    }

    /**
     * Store the Charset specification as the string version of the name,
     * rather than the Charset itself.  This allows us to serialize the
     * SourceFile class.
     * @param c charset to use when reading the input.
     */
    public void setCharset(Charset c) {
      inputCharset = c.name();
CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl.statements[86]++;
    }

    /**
     * Get the Charset specifying how we're supposed to read the file
     * in off disk and into UTF-16.  This is stored as a strong to allow
     * SourceFile to be serialized.
     * @return Charset object representing charset to use.
     */
    public Charset getCharset() {
      return Charset.forName(inputCharset);
    }
  }
}

class CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl ());
  }
    public static long[] statements = new long[87];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SourceFile.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
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

  public CodeCoverCoverageCounter$p08k9xh0ojsuyc0072gc6zl () {
    super("com.google.javascript.jscomp.SourceFile.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 86; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SourceFile.java");
      for (int i = 1; i <= 86; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 40; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
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

