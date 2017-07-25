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

import java.util.Map;

/**
 * Compilation results
 */
public class Result {
  static {
    CodeCoverCoverageCounter$ciksv18mo7z6q62xt.ping();
  }

  public final boolean success;
  public final JSError[] errors;
  public final JSError[] warnings;
  public final String debugLog;
  public final VariableMap variableMap;
  public final VariableMap propertyMap;
  public final VariableMap namedAnonFunctionMap;
  public final VariableMap stringMap;
  public final FunctionInformationMap functionInformationMap;
  public final SourceMap sourceMap;
  public final Map<String, Integer> cssNames;
  public final String externExport;
  public final String idGeneratorMap;

  Result(JSError[] errors, JSError[] warnings, String debugLog,
         VariableMap variableMap, VariableMap propertyMap,
         VariableMap namedAnonFunctionMap,
         VariableMap stringMap,
         FunctionInformationMap functionInformationMap,
         SourceMap sourceMap, String externExport,
         Map<String, Integer> cssNames, String idGeneratorMap) {
    this.success = errors.length == 0;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[1]++;
    this.errors = errors;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[2]++;
    this.warnings = warnings;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[3]++;
    this.debugLog = debugLog;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[4]++;
    this.variableMap = variableMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[5]++;
    this.propertyMap = propertyMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[6]++;
    this.namedAnonFunctionMap = namedAnonFunctionMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[7]++;
    this.stringMap = stringMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[8]++;
    this.functionInformationMap = functionInformationMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[9]++;
    this.sourceMap = sourceMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[10]++;
    this.externExport = externExport;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[11]++;
    this.cssNames = cssNames;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[12]++;
    this.idGeneratorMap = idGeneratorMap;
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[13]++;
  }

  // Visible for testing only.
  public Result(JSError[] errors, JSError[] warnings, String debugLog,
                VariableMap variableMap, VariableMap propertyMap,
                VariableMap namedAnonFunctionMap,
                FunctionInformationMap functionInformationMap,
                SourceMap sourceMap, String externExport) {
    this(errors, warnings, debugLog, variableMap, propertyMap,
         namedAnonFunctionMap, null, functionInformationMap, sourceMap,
         externExport, null, null);
CodeCoverCoverageCounter$ciksv18mo7z6q62xt.statements[14]++;
  }
}

class CodeCoverCoverageCounter$ciksv18mo7z6q62xt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ciksv18mo7z6q62xt ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$ciksv18mo7z6q62xt () {
    super("com.google.javascript.jscomp.Result.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Result.java");
      for (int i = 1; i <= 14; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
      for (int i = 1; i <= 0; i++) {
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

