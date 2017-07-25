/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;

import org.mozilla.javascript.debug.DebuggableScript;

final class InterpreterData implements Serializable, DebuggableScript
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.ping();
  }

    static final long serialVersionUID = 5067677351589230234L;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[1]++;
  }

    static final int INITIAL_MAX_ICODE_LENGTH = 1024;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[2]++;
  }
    static final int INITIAL_STRINGTABLE_SIZE = 64;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[3]++;
  }
    static final int INITIAL_NUMBERTABLE_SIZE = 64;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[4]++;
  }

    InterpreterData(int languageVersion, String sourceFile,
                    String encodedSource, boolean isStrict)
    {
        this.languageVersion = languageVersion;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[5]++;
        this.itsSourceFile = sourceFile;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[6]++;
        this.encodedSource = encodedSource;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[7]++;
        this.isStrict = isStrict;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[8]++;
        init();
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[9]++;
    }

    InterpreterData(InterpreterData parent)
    {
        this.parentData = parent;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[10]++;
        this.languageVersion = parent.languageVersion;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[11]++;
        this.itsSourceFile = parent.itsSourceFile;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[12]++;
        this.encodedSource = parent.encodedSource;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[13]++;

        init();
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[14]++;
    }

    private void init()
    {
        itsICode = new byte[INITIAL_MAX_ICODE_LENGTH];
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[15]++;
        itsStringTable = new String[INITIAL_STRINGTABLE_SIZE];
CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[16]++;
    }

    String itsName;
    String itsSourceFile;
    boolean itsNeedsActivation;
    int itsFunctionType;

    String[] itsStringTable;
    double[] itsDoubleTable;
    InterpreterData[] itsNestedFunctions;
    Object[] itsRegExpLiterals;

    byte[] itsICode;

    int[] itsExceptionTable;

    int itsMaxVars;
    int itsMaxLocals;
    int itsMaxStack;
    int itsMaxFrameArray;

    // see comments in NativeFuncion for definition of argNames and argCount
    String[] argNames;
    boolean[] argIsConst;
    int argCount;

    int itsMaxCalleeArgs;

    String encodedSource;
    int encodedSourceStart;
    int encodedSourceEnd;

    int languageVersion;

    boolean isStrict;
    boolean topLevel;

    Object[] literalIds;

    UintMap longJumps;

    int firstLinePC = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5.statements[17]++;
  } // PC for the first LINE icode

    InterpreterData parentData;

    boolean evalScriptFlag; // true if script corresponds to eval() code

    public boolean isTopLevel()
    {
        return topLevel;
    }

    public boolean isFunction()
    {
        return itsFunctionType != 0;
    }

    public String getFunctionName()
    {
        return itsName;
    }

    public int getParamCount()
    {
        return argCount;
    }

    public int getParamAndVarCount()
    {
        return argNames.length;
    }

    public String getParamOrVarName(int index)
    {
        return argNames[index];
    }

    public boolean getParamOrVarConst(int index)
    {
        return argIsConst[index];
    }

    public String getSourceName()
    {
        return itsSourceFile;
    }

    public boolean isGeneratedScript()
    {
        return ScriptRuntime.isGeneratedScript(itsSourceFile);
    }

    public int[] getLineNumbers()
    {
        return Interpreter.getLineNumbers(this);
    }

    public int getFunctionCount()
    {
        return (itsNestedFunctions == null) ? 0 : itsNestedFunctions.length;
    }

    public DebuggableScript getFunction(int index)
    {
        return itsNestedFunctions[index];
    }

    public DebuggableScript getParent()
    {
         return parentData;
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5 ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwptuiu2vcgqxegbgii4g9gwohtfy7s7z5 () {
    super("org.mozilla.javascript.RHINO-SRC-InterpreterData.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-InterpreterData.java");
      for (int i = 1; i <= 17; i++) {
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

