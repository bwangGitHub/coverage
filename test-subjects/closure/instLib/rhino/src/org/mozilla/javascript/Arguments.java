/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the "arguments" object.
 *
 * See ECMA 10.1.8
 *
 * @see org.mozilla.javascript.NativeCall
 */
final class Arguments extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.ping();
  }

    static final long serialVersionUID = 4275508002492040609L;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[1]++;
  }

    private static final String FTAG = "Arguments";
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[2]++;
  }

    public Arguments(NativeCall activation)
    {
        this.activation = activation;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[3]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[4]++;

        Scriptable parent = activation.getParentScope();
        setParentScope(parent);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[5]++;
        setPrototype(ScriptableObject.getObjectPrototype(parent));
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[6]++;

        args = activation.originalArgs;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[7]++;
        lengthObj = Integer.valueOf(args.length);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[8]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[9]++;

        NativeFunction f = activation.function;
        calleeObj = f;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[10]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[11]++;

        Scriptable topLevel = getTopLevelScope(parent);
        constructor = getProperty(topLevel, "Object");
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[12]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[13]++;

        int version = f.getLanguageVersion();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((version <= Context.VERSION_1_3) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((version != Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))
        {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[1]++;
            callerObj = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[15]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[2]++;
            callerObj = NOT_FOUND;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[16]++;
        }
    }

    @Override
    public String getClassName()
    {
        return FTAG;
    }

    private Object arg(int index) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((args.length <= index) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[3]++; return NOT_FOUND;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[4]++;}
      return args[index];
    }

    // the following helper methods assume that 0 < index < args.length

    private void putIntoActivation(int index, Object value) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[18]++;
        String argName = activation.function.getParamOrVarName(index);
        activation.put(argName, activation, value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[19]++;
    }

    private Object getFromActivation(int index) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[20]++;
        String argName = activation.function.getParamOrVarName(index);
        return activation.get(argName, activation);
    }

    private void replaceArg(int index, Object value) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sharedWithActivation(index)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[5]++;
        putIntoActivation(index, value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[22]++;

      } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[6]++;}
      synchronized (this) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((args == activation.originalArgs) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[7]++;
          args = args.clone();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[24]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[8]++;}
        args[index] = value;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[25]++;
      }
    }

    private void removeArg(int index) {
      synchronized (this) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((args[index] != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[9]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((args == activation.originalArgs) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[11]++;
            args = args.clone();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[28]++;

          } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[12]++;}
          args[index] = NOT_FOUND;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[29]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[10]++;}
      }
    }

    // end helpers

    @Override
    public boolean has(int index, Scriptable start)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((arg(index) != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[13]++;
          return true;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[14]++;}
        return super.has(index, start);
    }

    @Override
    public Object get(int index, Scriptable start)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[31]++;
      final Object value = arg(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[15]++;
        return super.get(index, start);

      } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[16]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sharedWithActivation(index)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[17]++;
          return getFromActivation(index);

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[18]++;
          return value;
        }
      }
    }

    private boolean sharedWithActivation(int index)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[34]++;
        NativeFunction f = activation.function;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[35]++;
        int definedCount = f.getParamCount();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((index < definedCount) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[19]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
            // Check if argument is not hidden by later argument with the same
            // name as hidden arguments are not shared with activation
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index < definedCount - 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[21]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[38]++;
                String argName = f.getParamOrVarName(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[39]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
                for (int i = index + 1;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < definedCount) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[1]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[2]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[3]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((argName.equals(f.getParamOrVarName(i))) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[23]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[24]++;}
                }

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[22]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[20]++;}
        return false;
    }

    @Override
    public void put(int index, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((arg(index) == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[25]++;
          super.put(index, start, value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[42]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[26]++;
          replaceArg(index, value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[43]++;
        }
    }

    @Override
    public void delete(int index)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[44]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index < args.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[27]++;
          removeArg(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[45]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[28]++;}
        super.delete(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[46]++;
    }

// #string_id_map#

    private static final int
        Id_callee           = 1,
        Id_length           = 2,
        Id_caller           = 3,
        Id_constructor      = 4,

        MAX_INSTANCE_ID     = Id_constructor;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[47]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return MAX_INSTANCE_ID;
    }

    @Override
    protected int findInstanceIdInfo(String s)
    {
        int id;
// #generated# Last update: 2010-01-06 05:48:21 ARST
        L0: { id = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[48]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[49]++; String X = null; int c;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[50]++;
            int s_length = s.length();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[51]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((s_length==6) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[29]++;
                c=s.charAt(5);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[52]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[53]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[31]++; X="callee";
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[54]++;id=Id_callee;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[55]++;
 }
                else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[32]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[56]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c=='h') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[33]++; X="length";
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[57]++;id=Id_length;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[58]++;
 }
                else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[34]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[59]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[35]++; X="caller";
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[60]++;id=Id_caller;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[61]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[36]++;}
}
}

            }
            else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[30]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[62]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[37]++; X="constructor";
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[63]++;id=Id_constructor;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[64]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[38]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[65]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[39]++; id = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[66]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[40]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[67]++;
            break L0;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[68]++;
int CodeCoverConditionCoverageHelper_C22;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[41]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[42]++;}

        int attr;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[69]++;
        switch (id) {
          case Id_callee:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[43]++;
          case Id_caller:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[44]++;
          case Id_length:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[45]++;
          case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[46]++;
            attr = DONTENUM;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[70]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[71]++;
            break;
          default:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[47]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, id);
    }

// #/string_id_map#

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[72]++;
        switch (id) {
            case Id_callee:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[48]++; return "callee";
            case Id_length:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[49]++; return "length";
            case Id_caller:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[50]++; return "caller";
            case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[51]++; return "constructor"; default : CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[52]++;
        }
        return null;
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[73]++;
        switch (id) {
            case Id_callee:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[53]++; return calleeObj;
            case Id_length:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[54]++; return lengthObj;
            case Id_caller:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[55]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[74]++;
                Object value = callerObj;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[75]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((value == UniqueTag.NULL_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[56]++; value = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[76]++;
 }
                else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[57]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[77]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[58]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[78]++;
                    NativeCall caller = activation.parentActivationCall;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[79]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((caller != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[60]++;
                        value = caller.get("arguments", caller);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[80]++;

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[61]++;}

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[59]++;}
}
                return value;
            }
            case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[62]++;
                return constructor; default : CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[63]++;
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[81]++;
        switch (id) {
            case Id_callee:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[64]++; calleeObj = value;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[82]++; return;
            case Id_length:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[65]++; lengthObj = value;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[83]++; return;
            case Id_caller:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[66]++;
                callerObj = (value != null) ? value : UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[84]++;
                return;
            case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[67]++; constructor = value;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[85]++; return; default : CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[68]++;
        }
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[86]++;
    }

    @Override
    Object[] getIds(boolean getAll)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[87]++;
        Object[] ids = super.getIds(getAll);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[88]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[69]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[89]++;
            boolean[] present = new boolean[args.length];
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[90]++;
            int extraCount = args.length;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[91]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[4]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i != ids.length) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[4]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[5]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[6]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[92]++;
                Object id = ids[i];
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[93]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((id instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[71]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[94]++;
                    int index = ((Integer)id).intValue();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[95]++;
int CodeCoverConditionCoverageHelper_C29;
                    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((index < args.length) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[73]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[96]++;
int CodeCoverConditionCoverageHelper_C30;
                        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((present[index]) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[75]++;
                            present[index] = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[97]++;
                            extraCount--;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[98]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[76]++;}

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[74]++;}

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[72]++;}
            }
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[99]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((getAll) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[77]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[100]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[7]++;


int CodeCoverConditionCoverageHelper_C32; // avoid adding args which were redefined to non-enumerable
              for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < present.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[7]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[8]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[9]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[101]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((present[i]) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((super.has(i, this)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[79]++;
                  present[i] = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[102]++;
                  extraCount--;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[103]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[80]++;}
              }

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[78]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[104]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((extraCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[81]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[105]++;
                Object[] tmp = new Object[extraCount + ids.length];
                System.arraycopy(ids, 0, tmp, extraCount, ids.length);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[106]++;
                ids = tmp;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[107]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[108]++;
                int offset = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[109]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[10]++;


int CodeCoverConditionCoverageHelper_C35;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[10]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[11]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.loops[12]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[110]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((present == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((present[i]) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[83]++;
                        ids[offset] = Integer.valueOf(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[111]++;
                        ++offset;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[112]++;

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[84]++;}
                }
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[113]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((offset != extraCount) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[85]++; Kit.codeBug();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[114]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[86]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[82]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[70]++;}
        return ids;
    }

    @Override
    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[115]++;
      double d = ScriptRuntime.toNumber(id);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[116]++;
      int index = (int) d;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[117]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((d != index) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[87]++;
        return super.getOwnPropertyDescriptor(cx, id);

      } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[88]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[118]++;
      Object value = arg(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[119]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[89]++;
        return super.getOwnPropertyDescriptor(cx, id);

      } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[90]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[120]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((sharedWithActivation(index)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[91]++;
        value = getFromActivation(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[121]++;

      } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[92]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[122]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((super.has(index, this)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[93]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[123]++; // the descriptor has been redefined
        ScriptableObject desc = super.getOwnPropertyDescriptor(cx, id);
        desc.put("value", desc, value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[124]++;
        return desc;

      } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[94]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[125]++;
        Scriptable scope = getParentScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[126]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[95]++; scope = this;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[127]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[96]++;}
        return buildDataDescriptor(scope, value, EMPTY);
      }
    }

    @Override
    protected void defineOwnProperty(Context cx, Object id,
                                     ScriptableObject desc,
                                     boolean checkValid) {
      super.defineOwnProperty(cx, id, desc, checkValid);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[128]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[129]++;

      double d = ScriptRuntime.toNumber(id);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[130]++;
      int index = (int) d;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[131]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((d != index) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[97]++; return;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[98]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[132]++;

      Object value = arg(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[133]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[99]++; return;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[100]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[134]++;
int CodeCoverConditionCoverageHelper_C45;

      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((isAccessorDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[101]++;
        removeArg(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[135]++;
        return;

      } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[102]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[136]++;

      Object newValue = getProperty(desc, "value");
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[137]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((newValue == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[103]++; return;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[104]++;}

      replaceArg(index, newValue);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[138]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[139]++;
int CodeCoverConditionCoverageHelper_C47;

      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((isFalse(getProperty(desc, "writable"))) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[105]++;
        removeArg(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.statements[140]++;

      } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5.branches[106]++;}
    }

// Fields to hold caller, callee and length properties,
// where NOT_FOUND value tags deleted properties.
// In addition if callerObj == NULL_VALUE, it tags null for scripts, as
// initial callerObj == null means access to caller arguments available
// only in JS <= 1.3 scripts
    private Object callerObj;
    private Object calleeObj;
    private Object lengthObj;
    private Object constructor;

    private NativeCall activation;

// Initially args holds activation.getOriginalArgs(), but any modification
// of its elements triggers creation of a copy. If its element holds NOT_FOUND,
// it indicates deleted index, in which case super class is queried.
    private Object[] args;
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5 ());
  }
    public static long[] statements = new long[141];
    public static long[] branches = new long[107];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[48];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Arguments.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,3,1,1,1,1,1,1,1,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 47; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzgca4np84jhm4ijj84nwkf5 () {
    super("org.mozilla.javascript.RHINO-SRC-Arguments.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 140; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 106; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Arguments.java");
      for (int i = 1; i <= 140; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 106; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

