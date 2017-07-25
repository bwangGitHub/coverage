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

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.debugging.sourcemap.FilePosition;
import com.google.debugging.sourcemap.SourceMapFormat;
import com.google.debugging.sourcemap.SourceMapGenerator;
import com.google.debugging.sourcemap.SourceMapGeneratorFactory;
import com.google.debugging.sourcemap.SourceMapGeneratorV1;
import com.google.debugging.sourcemap.SourceMapGeneratorV2;
import com.google.javascript.rhino.Node;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Collects information mapping the generated (compiled) source back to
 * its original source for debugging purposes.
 *
 * @see CodeConsumer
 * @see CodeGenerator
 * @see CodePrinter
 *
 */
public class SourceMap {
  static {
    CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.ping();
  }


  public static enum Format {
     V1 {
       @Override SourceMap getInstance() {
         return new SourceMap(
           SourceMapGeneratorFactory.getInstance(SourceMapFormat.V1));
       }
     },
     DEFAULT {
       @Override SourceMap getInstance() {
         return new SourceMap(
           SourceMapGeneratorFactory.getInstance(SourceMapFormat.DEFAULT));
       }
     },
     V2 {
       @Override SourceMap getInstance() {
         return new SourceMap(
           SourceMapGeneratorFactory.getInstance(SourceMapFormat.V2));
        }
     },
     V3 {
       @Override SourceMap getInstance() {
         return new SourceMap(
           SourceMapGeneratorFactory.getInstance(SourceMapFormat.V3));
        }
     };
     abstract SourceMap getInstance();
  }

  /**
   * Source maps can be very large different levels of detail can be specified.
   */
  public static enum DetailLevel implements Predicate<Node> {
    // ALL is best when the fullest details are needed for debugging or for
    // code-origin analysis.
    ALL {
      @Override public boolean apply(Node node) {
        return true;
      }
    },
    // SYMBOLS is intended to be used for stack trace deobfuscation when full
    // detail is not needed.
    SYMBOLS {
      @Override public boolean apply(Node node) {
        return node.isCall()
            || node.isNew()
            || node.isFunction()
            || node.isName()
            || NodeUtil.isGet(node)
            || NodeUtil.isObjectLitKey(node)
            || (node.isString() && NodeUtil.isGet(node.getParent()));
      }
    };
  }

  public static class LocationMapping {
    final String prefix;
    final String replacement;
    public LocationMapping(String prefix, String replacement) {
      this.prefix = prefix;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[1]++;
      this.replacement = replacement;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[2]++;
    }
  }

  private final SourceMapGenerator generator;
  private List<LocationMapping> prefixMappings = Collections.emptyList();
  {
    CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[3]++;
  }
  private final Map<String, String> sourceLocationFixupCache =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[4]++;
  }

  private SourceMap(SourceMapGenerator generator) {
    this.generator = generator;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[5]++;
  }

  public void addMapping(
      Node node,
      FilePosition outputStartPosition,
      FilePosition outputEndPosition) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[6]++;
    String sourceFile = node.getSourceFileName();
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

    // If the node does not have an associated source file or
    // its line number is -1, then the node does not have sufficient
    // information for a mapping to be useful.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((sourceFile == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.getLineno() < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[2]++;}

    sourceFile = fixupSourceLocation(sourceFile);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[8]++;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[9]++;

    String originalName = (String) node.getProp(Node.ORIGINALNAME_PROP);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[10]++;

    // Strangely, Rhino source lines are one based but columns are
    // zero based.
    // We don't change this for the v1 or v2 source maps but for
    // v3 we make them both 0 based.
    int lineBaseOffset = 1;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((generator instanceof SourceMapGeneratorV1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((generator instanceof SourceMapGeneratorV2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[3]++;
      lineBaseOffset = 0;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[12]++;

    } else {
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[4]++;}

    generator.addMapping(
        sourceFile, originalName,
        new FilePosition(node.getLineno() - lineBaseOffset, node.getCharno()),
        outputStartPosition, outputEndPosition);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[13]++;
  }

  /**
   * @param sourceFile The source file location to fixup.
   * @return a remapped source file.
   */
  private String fixupSourceLocation(String sourceFile) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((prefixMappings.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[5]++;
      return sourceFile;

    } else {
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[6]++;}
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[15]++;

    String fixed = sourceLocationFixupCache.get(sourceFile);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((fixed != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[7]++;
      return fixed;

    } else {
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[8]++;}
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[17]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.loops[1]++;



    // Replace the first prefix found with its replacement
    for (LocationMapping mapping : prefixMappings) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.loops[1]--;
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.loops[2]--;
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.loops[3]++;
}
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((sourceFile.startsWith(mapping.prefix)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[9]++;
        fixed = mapping.replacement + sourceFile.substring(
          mapping.prefix.length());
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[19]++;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[20]++;
        break;

      } else {
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[10]++;}
    }
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;

    // If none of the mappings match then use the original file path.
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fixed == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[11]++;
      fixed = sourceFile;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[22]++;

    } else {
  CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.branches[12]++;}

    sourceLocationFixupCache.put(sourceFile, fixed);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[23]++;
    return fixed;
  }

  public void appendTo(Appendable out, String name) throws IOException {
    generator.appendTo(out, name);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[24]++;
  }

  public void reset() {
    generator.reset();
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[25]++;
    sourceLocationFixupCache.clear();
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[26]++;
  }

  public void setStartingPosition(int offsetLine, int offsetIndex) {
    generator.setStartingPosition(offsetLine, offsetIndex);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[27]++;
  }

  public void setWrapperPrefix(String prefix) {
    generator.setWrapperPrefix(prefix);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[28]++;
  }

  public void validate(boolean validate) {
    generator.validate(validate);
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[29]++;
  }

  /**
   * @param sourceMapLocationMappings
   */
  public void setPrefixMappings(List<LocationMapping> sourceMapLocationMappings) {
     this.prefixMappings = sourceMapLocationMappings;
CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd.statements[30]++;
  }
}

class CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SourceMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$3ilgcn9pepe274l6tz8rxd () {
    super("com.google.javascript.jscomp.SourceMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SourceMap.java");
      for (int i = 1; i <= 30; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

