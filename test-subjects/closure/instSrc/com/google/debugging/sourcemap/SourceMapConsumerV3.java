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
import com.google.debugging.sourcemap.Base64VLQ.CharIterator;
import com.google.debugging.sourcemap.proto.Mapping.OriginalMapping;
import com.google.debugging.sourcemap.proto.Mapping.OriginalMapping.Builder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for parsing version 3 of the SourceMap format, as produced by the
 * Closure Compiler, etc.
 * http://code.google.com/p/closure-compiler/wiki/SourceMaps
 * @author johnlenz@google.com (John Lenz)
 */
public class SourceMapConsumerV3 implements SourceMapConsumer,
    SourceMappingReversable {
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.ping();
  }

  static final int UNMAPPED = -1;
  static {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[1]++;
  }

  private String[] sources;
  private String[] names;
  private int lineCount;
  // Slots in the lines list will be null if the line does not have any entries.
  private ArrayList<ArrayList<Entry>> lines = null;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[2]++;
  }
  /** originalFile path ==> original line ==> target mappings */
  private Map<String, Map<Integer, Collection<OriginalMapping>>>
      reverseSourceMapping;

  public SourceMapConsumerV3() {

  }

  static class DefaultSourceMapSupplier implements SourceMapSupplier {
    @Override
    public String getSourceMap(String url) {
      return null;
    }
  }

  /**
   * Parses the given contents containing a source map.
   */
  @Override
  public void parse(String contents) throws SourceMapParseException {
    parse(contents, null);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[3]++;
  }

  /**
   * Parses the given contents containing a source map.
   */
  public void parse(String contents, SourceMapSupplier sectionSupplier)
      throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[4]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[5]++;
      JSONObject sourceMapRoot = new JSONObject(contents);
      parse(sourceMapRoot, sectionSupplier);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[6]++;
    } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[2]++;
      throw new SourceMapParseException("JSON parse exception: " + ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[1]++;
}
  }
  }

  /**
   * Parses the given contents containing a source map.
   */
  public void parse(JSONObject sourceMapRoot) throws SourceMapParseException {
    parse(sourceMapRoot, null);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[7]++;
  }

  /**
   * Parses the given contents containing a source map.
   */
  public void parse(JSONObject sourceMapRoot, SourceMapSupplier sectionSupplier)
      throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[8]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[9]++;
      // Check basic assertions about the format.
      int version = sourceMapRoot.getInt("version");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((version != 3) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[4]++;
        throw new SourceMapParseException("Unknown version: " + version);

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[5]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[11]++;

      String file = sourceMapRoot.getString("file");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((file.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[6]++;
        throw new SourceMapParseException("File entry is missing or empty");

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[7]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sourceMapRoot.has("sections")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[8]++;
        // Looks like a index map, try to parse it that way.
        parseMetaMap(sourceMapRoot, sectionSupplier);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[14]++;
        return;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[9]++;}

      lineCount = sourceMapRoot.getInt("lineCount");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[15]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[16]++;
      String lineMap = sourceMapRoot.getString("mappings");

      sources = getJavaStringArray(sourceMapRoot.getJSONArray("sources"));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[17]++;
      names = getJavaStringArray(sourceMapRoot.getJSONArray("names"));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[18]++;

      lines = Lists.newArrayListWithCapacity(lineCount);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[19]++;

      new MappingBuilder(lineMap).build();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[20]++;
    } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[10]++;
      throw new SourceMapParseException("JSON parse exception: " + ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[3]++;
}
  }
  }

  /**
   * @param sourceMapRoot
   * @throws SourceMapParseException
   */
  private void parseMetaMap(
      JSONObject sourceMapRoot, SourceMapSupplier sectionSupplier)
      throws SourceMapParseException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((sectionSupplier == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[11]++;
      sectionSupplier = new DefaultSourceMapSupplier();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[22]++;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[12]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[23]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

    try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[24]++;
      // Check basic assertions about the format.
      int version = sourceMapRoot.getInt("version");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((version != 3) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[14]++;
        throw new SourceMapParseException("Unknown version: " + version);

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[15]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[26]++;

      String file = sourceMapRoot.getString("file");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((file.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[16]++;
        throw new SourceMapParseException("File entry is missing or empty");

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[17]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (128)) == 0 || true) &&
 ((sourceMapRoot.has("lineCount")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((sourceMapRoot.has("mappings")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((sourceMapRoot.has("sources")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((sourceMapRoot.has("names")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 4) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 4) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[18]++;
        throw new SourceMapParseException("Invalid map format");

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[19]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[29]++;

      SourceMapGeneratorV3 generator = new SourceMapGeneratorV3();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[30]++;
      JSONArray sections = sourceMapRoot.getJSONArray("sections");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
      for (int i = 0, count = sections.length();(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[1]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[2]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[3]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[32]++;
        JSONObject section = sections.getJSONObject(i);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((section.has("map")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((section.has("url")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[20]++;
          throw new SourceMapParseException(
              "Invalid map format: section may not have both 'map' and 'url'");

        } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[21]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[34]++;
        JSONObject offset = section.getJSONObject("offset");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[35]++;
        int line = offset.getInt("line");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[36]++;
        int column = offset.getInt("column");
        String mapSectionContents;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((section.has("url")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[22]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[38]++;
          String url = section.getString("url");
          mapSectionContents = sectionSupplier.getSourceMap(url);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[39]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((mapSectionContents == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[24]++;
            throw new SourceMapParseException("Unable to retrieve: " + url);

          } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[25]++;}

        } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[23]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[41]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((section.has("map")) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[26]++;
          mapSectionContents = section.getString("map");
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[42]++;

        } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[27]++;
          throw new SourceMapParseException(
              "Invalid map format: section must have either 'map' or 'url'");
        }
}
        generator.mergeMapSection(line, column, mapSectionContents);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[43]++;
      }
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[44]++;

      StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[45]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
      try {
CodeCoverTryBranchHelper_Try4 = true;
        generator.appendTo(sb, file);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[46]++;
      } catch (IOException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[29]++;
        // Can't happen.
        throw new RuntimeException(e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[28]++;
}
  }

      parse(sb.toString());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[47]++;
    } catch (IOException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[30]++;
      throw new SourceMapParseException("IO exception: " + ex);
    } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[31]++;
      throw new SourceMapParseException("JSON parse exception: " + ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[13]++;
}
  }
  }

  @Override
  public OriginalMapping getMappingForLine(int lineNumber, int column) {
    // Normalize the line and column numbers to 0.
    lineNumber--;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[48]++;
    column--;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[49]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((lineNumber < 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((lineNumber >= lines.size()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[32]++;
      return null;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[33]++;}

    Preconditions.checkState(lineNumber >= 0);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[51]++;
    Preconditions.checkState(column >= 0);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[52]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;


    // If the line is empty return the previous mapping.
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lines.get(lineNumber) == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[34]++;
      return getPreviousMapping(lineNumber);

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[35]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[54]++;

    ArrayList<Entry> entries = lines.get(lineNumber);
    // No empty lists.
    Preconditions.checkState(entries.size() > 0);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[55]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[56]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((entries.get(0).getGeneratedColumn() > column) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[36]++;
      return getPreviousMapping(lineNumber);

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[37]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[57]++;

    int index = search(entries, column, 0, entries.size() - 1);
    Preconditions.checkState(index >= 0, "unexpected:%s", index);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[58]++;
    return getOriginalMappingForEntry(entries.get(index));
  }

  @Override
  public Collection<String> getOriginalSources() {
    return Arrays.asList(sources);
  }

  @Override
  public Collection<OriginalMapping> getReverseMapping(String originalFile,
      int line, int column) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;
    // TODO(user): This implementation currently does not make use of the column
    // parameter.

    // Synchronization needs to be handled by callers.
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((reverseSourceMapping == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[38]++;
      createReverseMapping();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[60]++;

    } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[39]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[61]++;

    Map<Integer, Collection<OriginalMapping>> sourceLineToCollectionMap =
        reverseSourceMapping.get(originalFile);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((sourceLineToCollectionMap == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[40]++;
      return Collections.emptyList();

    } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[41]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[63]++;
      Collection<OriginalMapping> mappings =
          sourceLineToCollectionMap.get(line);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[64]++;
int CodeCoverConditionCoverageHelper_C18;

      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((mappings == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[42]++;
        return Collections.emptyList();

      } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[43]++;
        return mappings;
      }
    }
  }

  private String[] getJavaStringArray(JSONArray array) throws JSONException {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[65]++;
    int len = array.length();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[66]++;
    String[] result = new String[len];
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[67]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[4]++;


int CodeCoverConditionCoverageHelper_C19;
    for(int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[4]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[5]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[6]++;
}
      result[i] = array.getString(i);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[68]++;
    }
    return result;
  }

  private class MappingBuilder {
    private static final int MAX_ENTRY_VALUES = 5;
    private final StringCharIterator content;
    private int line = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[69]++;
  }
    private int previousCol = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[70]++;
  }
    private int previousSrcId = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[71]++;
  }
    private int previousSrcLine = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[72]++;
  }
    private int previousSrcColumn = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[73]++;
  }
    private int previousNameId = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[74]++;
  }

    MappingBuilder(String lineMap) {
      this.content = new StringCharIterator(lineMap);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[75]++;
    }

    void build() {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[76]++;
      int [] temp = new int[MAX_ENTRY_VALUES];
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[77]++;
      ArrayList<Entry> entries = new ArrayList<Entry>();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[78]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[7]++;


int CodeCoverConditionCoverageHelper_C20;
      while ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((content.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[7]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[8]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[9]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
        // ';' denotes a new line.
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((tryConsumeToken(';')) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[44]++;
          // The line is complete, store the result for the line,
          // null if the line is empty.
          ArrayList<Entry> result;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[80]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((entries.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[46]++;
            result = entries;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[81]++;
            // A new array list for the next line.
            entries = new ArrayList<Entry>();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[82]++;

          } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[47]++;
            result = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[83]++;
          }
          lines.add(result);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[84]++;
          entries.clear();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[85]++;
          line++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[86]++;
          previousCol = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[87]++;

        } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[45]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[88]++;
          // grab the next entry for the current line.
          int entryValues = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[89]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
          while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((entryComplete()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[10]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[11]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[12]++;
}
            temp[entryValues] = nextValue();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[90]++;
            entryValues++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[91]++;
          }
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[92]++;
          Entry entry = decodeEntry(temp, entryValues);

          validateEntry(entry);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[93]++;
          entries.add(entry);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[94]++;

          // Consume the separating token, if there is one.
          tryConsumeToken(',');
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[95]++;
        }
      }
    }

    /**
     * Sanity check the entry.
     */
    private void validateEntry(Entry entry) {
      Preconditions.checkState(line < lineCount);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[96]++;
      Preconditions.checkState(entry.getSourceFileId() == UNMAPPED
          || entry.getSourceFileId() < sources.length);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[97]++;
      Preconditions.checkState(entry.getNameId() == UNMAPPED
          || entry.getNameId() < names.length);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[98]++;
    }

    /**
     * Decodes the next entry, using the previous encountered values to
     * decode the relative values.
     *
     * @param vals An array of integers that represent values in the entry.
     * @param entryValues The number of entries in the array.
     * @return The entry object.
     */
    private Entry decodeEntry(int[] vals, int entryValues) {
      Entry entry;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[99]++;
      switch (entryValues) {
        // The first values, if present are in the following order:
        //   0: the starting column in the current line of the generated file
        //   1: the id of the original source file
        //   2: the starting line in the original source
        //   3: the starting column in the original source
        //   4: the id of the original symbol name
        // The values are relative to the last encountered value for that field.
        // Note: the previously column value for the generated file is reset
        // to '0' when a new line is encountered.  This is done in the 'build'
        // method.

        case 1:
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[48]++;
          // An unmapped section of the generated file.
          entry = new UnmappedEntry(
              vals[0] + previousCol);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[100]++;
          // Set the values see for the next entry.
          previousCol = entry.getGeneratedColumn();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[101]++;
          return entry;

        case 4:
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[49]++;
          // A mapped section of the generated file.
          entry = new UnnamedEntry(
              vals[0] + previousCol,
              vals[1] + previousSrcId,
              vals[2] + previousSrcLine,
              vals[3] + previousSrcColumn);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[102]++;
          // Set the values see for the next entry.
          previousCol = entry.getGeneratedColumn();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[103]++;
          previousSrcId = entry.getSourceFileId();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[104]++;
          previousSrcLine = entry.getSourceLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[105]++;
          previousSrcColumn = entry.getSourceColumn();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[106]++;
          return entry;

        case 5:
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[50]++;
          // A mapped section of the generated file, that has an associated
          // name.
          entry = new NamedEntry(
              vals[0] + previousCol,
              vals[1] + previousSrcId,
              vals[2] + previousSrcLine,
              vals[3] + previousSrcColumn,
              vals[4] + previousNameId);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[107]++;
          // Set the values see for the next entry.
          previousCol = entry.getGeneratedColumn();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[108]++;
          previousSrcId = entry.getSourceFileId();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[109]++;
          previousSrcLine = entry.getSourceLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[110]++;
          previousSrcColumn = entry.getSourceColumn();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[111]++;
          previousNameId = entry.getNameId();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[112]++;
          return entry;

        default:
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[51]++;
          throw new IllegalStateException(
              "Unexpected number of values for entry:" + entryValues);
      }
    }

    private boolean tryConsumeToken(char token) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[113]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((content.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((content.peek() == token) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[52]++;
        // consume the comma
        content.next();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[114]++;
        return true;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[53]++;}
      return false;
    }

    private boolean entryComplete() {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[115]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((content.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[54]++;
        return true;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[55]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[116]++;

      char c = content.peek();
      return (c == ';' || c == ',');
    }

    private int nextValue() {
      return Base64VLQ.decode(content);
    }
  }

  /**
   * Perform a binary search on the array to find a section that covers
   * the target column.
   */
  private int search(ArrayList<Entry> entries, int target, int start, int end) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[117]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[13]++;


    while (true) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[13]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[14]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[15]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[118]++;
      int mid = ((end - start) / 2) + start;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[119]++;
      int compare = compareEntry(entries, mid, target);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[120]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((compare == 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[56]++;
        return mid;

      } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[57]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[121]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((compare < 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[58]++;
        // it is in the upper half
        start = mid + 1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[122]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[123]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((start > end) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[60]++;
          return end;

        } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[61]++;}

      } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[59]++;
        // it is in the lower half
        end = mid - 1;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[124]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[125]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((end < start) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[62]++;
          return end;

        } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[63]++;}
      }
}
    }
  }

  /**
   * Compare an array entry's column value to the target column value.
   */
  private int compareEntry(ArrayList<Entry> entries, int entry, int target) {
    return entries.get(entry).getGeneratedColumn() - target;
  }

  /**
   * Returns the mapping entry that proceeds the supplied line or null if no
   * such entry exists.
   */
  private OriginalMapping getPreviousMapping(int lineNumber) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[126]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[16]++;


int CodeCoverConditionCoverageHelper_C31;
    do {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[16]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[17]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[18]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[127]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((lineNumber == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[64]++;
        return null;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[65]++;}
      lineNumber--;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[128]++;
    } while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((lines.get(lineNumber) == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[129]++;
    ArrayList<Entry> entries = lines.get(lineNumber);
    return getOriginalMappingForEntry(entries.get(entries.size() - 1));
  }

  /**
   * Creates an "OriginalMapping" object for the given entry object.
   */
  private OriginalMapping getOriginalMappingForEntry(Entry entry) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[130]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((entry.getSourceFileId() == UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[66]++;
      return null;

    } else {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[67]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[131]++;
      // Adjust the line/column here to be start at 1.
      Builder x = OriginalMapping.newBuilder()
        .setOriginalFile(sources[entry.getSourceFileId()])
        .setLineNumber(entry.getSourceLine() + 1)
        .setColumnPosition(entry.getSourceColumn() + 1);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[132]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((entry.getNameId() != UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[68]++;
        x.setIdentifier(names[entry.getNameId()]);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[133]++;

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[69]++;}
      return x.build();
    }
  }

  /**
   * Reverse the source map; the created mapping will allow us to quickly go
   * from a source file and line number to a collection of target
   * OriginalMappings.
   */
  private void createReverseMapping() {
    reverseSourceMapping =
        new HashMap<String, Map<Integer, Collection<OriginalMapping>>>();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[134]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[135]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[19]++;


int CodeCoverConditionCoverageHelper_C35;

    for (int targetLine = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((targetLine < lines.size()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); targetLine++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[19]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[20]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[21]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[136]++;
      ArrayList<Entry> entries = lines.get(targetLine);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[137]++;
int CodeCoverConditionCoverageHelper_C36;

      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((entries != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[70]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[138]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[22]++;


        for (Entry entry : entries) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[22]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[23]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[24]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[139]++;
int CodeCoverConditionCoverageHelper_C37;
          if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((entry.getSourceFileId() != UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((entry.getSourceLine() != UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[72]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[140]++;
            String originalFile = sources[entry.getSourceFileId()];
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[141]++;
int CodeCoverConditionCoverageHelper_C38;

            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((reverseSourceMapping.containsKey(originalFile)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[74]++;
              reverseSourceMapping.put(originalFile,
                  new HashMap<Integer, Collection<OriginalMapping>>());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[142]++;

            } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[75]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[143]++;

            Map<Integer, Collection<OriginalMapping>> lineToCollectionMap =
                reverseSourceMapping.get(originalFile);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[144]++;

            int sourceLine = entry.getSourceLine();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[145]++;
int CodeCoverConditionCoverageHelper_C39;

            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((lineToCollectionMap.containsKey(sourceLine)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[76]++;
              lineToCollectionMap.put(sourceLine,
                  new ArrayList<OriginalMapping>(1));
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[146]++;

            } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[77]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[147]++;

            Collection<OriginalMapping> mappings =
                lineToCollectionMap.get(sourceLine);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[148]++;

            Builder builder = OriginalMapping.newBuilder().setLineNumber(
                targetLine).setColumnPosition(entry.getGeneratedColumn());

            mappings.add(builder.build());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[149]++;

          } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[73]++;}
        }

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[71]++;}
    }
  }

  /**
   * A implementation of the Base64VLQ CharIterator used for decoding the
   * mappings encoded in the JSON string.
   */
  private static class StringCharIterator implements CharIterator {
    final String content;
    final int length;
    int current = 0;
  {
    CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[150]++;
  }

    StringCharIterator(String content) {
      this.content = content;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[151]++;
      this.length = content.length();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[152]++;
    }

    @Override
    public char next() {
      return content.charAt(current++);
    }

    char peek() {
      return content.charAt(current);
    }

    @Override
    public boolean hasNext() {
      return current < length;
    }
  }

  /**
   * Represents a mapping entry in the source map.
   */
  private interface Entry {
    int getGeneratedColumn();
    int getSourceFileId();
    int getSourceLine();
    int getSourceColumn();
    int getNameId();
  }

  /**
   * This class represents a portion of the generated file, that is not mapped
   * to a section in the original source.
   */
  private static class UnmappedEntry implements Entry {
    private final int column;

    UnmappedEntry(int column) {
      this.column = column;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[153]++;
    }

    @Override
    public int getGeneratedColumn() {
      return column;
    }

    @Override
    public int getSourceFileId() {
      return UNMAPPED;
    }

    @Override
    public int getSourceLine() {
      return UNMAPPED;
    }

    @Override
    public int getSourceColumn() {
      return UNMAPPED;
    }

    @Override
    public int getNameId() {
      return UNMAPPED;
    }
  }

  /**
   * This class represents a portion of the generated file, that is mapped
   * to a section in the original source.
   */
  private static class UnnamedEntry extends UnmappedEntry {
    private final int srcFile;
    private final int srcLine;
    private final int srcColumn;

    UnnamedEntry(int column, int srcFile, int srcLine, int srcColumn) {
      super(column);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[154]++;
      this.srcFile = srcFile;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[155]++;
      this.srcLine = srcLine;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[156]++;
      this.srcColumn = srcColumn;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[157]++;
    }

    @Override
    public int getSourceFileId() {
      return srcFile;
    }

    @Override
    public int getSourceLine() {
      return srcLine;
    }

    @Override
    public int getSourceColumn() {
      return srcColumn;
    }

    @Override
    public int getNameId() {
      return UNMAPPED;
    }
  }

  /**
   * This class represents a portion of the generated file, that is mapped
   * to a section in the original source, and is associated with a name.
   */
  private static class NamedEntry extends UnnamedEntry {
    private final int name;

    NamedEntry(int column, int srcFile, int srcLine, int srcColumn, int name) {
      super(column, srcFile, srcLine, srcColumn);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[158]++;
      this.name = name;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[159]++;
    }

    @Override
    public int getNameId() {
      return name;
    }
  }

  public static interface EntryVisitor {
    void visit(String sourceName,
               String symbolName,
               FilePosition sourceStartPosition,
               FilePosition startPosition,
               FilePosition endPosition);
  }

  public void visitMappings(EntryVisitor visitor) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[160]++;
    boolean pending = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[161]++;
    String sourceName = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[162]++;
    String symbolName = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[163]++;
    FilePosition sourceStartPosition = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[164]++;
    FilePosition startPosition = null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[165]++;

    final int lineCount = lines.size();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[166]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[25]++;


int CodeCoverConditionCoverageHelper_C40;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < lineCount) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[25]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[26]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[27]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[167]++;
      ArrayList<Entry> line = lines.get(i);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[168]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((line != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[78]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[169]++;
        final int entryCount = line.size();
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[170]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[28]++;


int CodeCoverConditionCoverageHelper_C42;
        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((j < entryCount) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[28]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[29]--;
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.loops[30]++;
}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[171]++;
          Entry entry = line.get(j);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[172]++;
int CodeCoverConditionCoverageHelper_C43;
          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((pending) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[80]++;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[173]++;
            FilePosition endPosition = new FilePosition(
                i, entry.getGeneratedColumn());
            visitor.visit(
                sourceName,
                symbolName,
                sourceStartPosition,
                startPosition,
                endPosition);
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[174]++;
            pending = false;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[175]++;

          } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[81]++;}
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[176]++;
int CodeCoverConditionCoverageHelper_C44;

          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((entry.getSourceFileId() != UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[82]++;
            pending = true;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[177]++;
            sourceName = sources[entry.getSourceFileId()];
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[178]++;
            symbolName = (entry.getNameId() != UNMAPPED)
                ? names[entry.getNameId()] : null;
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[179]++;
            sourceStartPosition = new FilePosition(
                entry.getSourceLine(), entry.getSourceColumn());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[180]++;
            startPosition = new FilePosition(
                i, entry.getGeneratedColumn());
CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.statements[181]++;

          } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[83]++;}
        }

      } else {
  CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip.branches[79]++;}
    }
  }
}

class CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip ());
  }
    public static long[] statements = new long[182];
    public static long[] branches = new long[84];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[45];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapConsumerV3.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,3,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 44; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$j8a388xc53njihbm5q9ecljnbz1w8wfby4cip () {
    super("com.google.debugging.sourcemap.SourceMapConsumerV3.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 181; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 83; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapConsumerV3.java");
      for (int i = 1; i <= 181; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 83; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

