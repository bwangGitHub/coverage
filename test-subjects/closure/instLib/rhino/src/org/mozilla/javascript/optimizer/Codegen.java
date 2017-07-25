/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.*;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.classfile.*;

import java.util.*;
import java.lang.reflect.Constructor;

import static org.mozilla.classfile.ClassFileWriter.ACC_FINAL;
import static org.mozilla.classfile.ClassFileWriter.ACC_PRIVATE;
import static org.mozilla.classfile.ClassFileWriter.ACC_PUBLIC;
import static org.mozilla.classfile.ClassFileWriter.ACC_STATIC;
import static org.mozilla.classfile.ClassFileWriter.ACC_VOLATILE;

/**
 * This class generates code for a given IR tree.
 *
 */

public class Codegen implements Evaluator
{
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.ping();
  }

    public void captureStackInfo(RhinoException ex) {
        throw new UnsupportedOperationException();
    }

    public String getSourcePositionFromStack(Context cx, int[] linep) {
        throw new UnsupportedOperationException();
    }

    public String getPatchedStack(RhinoException ex, String nativeStackTrace) {
        throw new UnsupportedOperationException();
    }

    public List<String> getScriptStack(RhinoException ex) {
        throw new UnsupportedOperationException();
    }

    public void setEvalScriptFlag(Script script) {
        throw new UnsupportedOperationException();
    }

    public Object compile(CompilerEnvirons compilerEnv,
                          ScriptNode tree,
                          String encodedSource,
                          boolean returnFunction)
    {
        int serial;
        synchronized (globalLock) {
            serial = ++globalSerialClassCounter;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2]++;

        String baseName = "c";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((tree.getSourceName().length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[1]++;
          baseName = tree.getSourceName().replaceAll("\\W", "_");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[4]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(baseName.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[3]++;
            baseName = "_" + baseName;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[6]++;

          } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[2]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[7]++;

        String mainClassName = "org.mozilla.javascript.gen." + baseName + "_" + serial;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[8]++;

        byte[] mainClassBytes = compileToClassFile(compilerEnv, mainClassName,
                                                   tree, encodedSource,
                                                   returnFunction);

        return new Object[] { mainClassName, mainClassBytes };
    }

    public Script createScriptObject(Object bytecode,
                                     Object staticSecurityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[9]++;
        Class<?> cl = defineClass(bytecode, staticSecurityDomain);

        Script script;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[10]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            script = (Script)cl.newInstance();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[11]++;
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[6]++;
            throw new RuntimeException
                ("Unable to instantiate compiled class:" + ex.toString());
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[5]++;
}
  }
        return script;
    }

    public Function createFunctionObject(Context cx, Scriptable scope,
                                         Object bytecode,
                                         Object staticSecurityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[12]++;
        Class<?> cl = defineClass(bytecode, staticSecurityDomain);

        NativeFunction f;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[13]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[14]++;
            Constructor<?>ctor = cl.getConstructors()[0];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[15]++;
            Object[] initArgs = { scope, cx, Integer.valueOf(0) };
            f = (NativeFunction)ctor.newInstance(initArgs);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[16]++;
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[8]++;
            throw new RuntimeException
                ("Unable to instantiate compiled class:"+ex.toString());
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[7]++;
}
  }
        return f;
    }

    private Class<?> defineClass(Object bytecode,
                                 Object staticSecurityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[17]++;
        Object[] nameBytesPair = (Object[])bytecode;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[18]++;
        String className = (String)nameBytesPair[0];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[19]++;
        byte[] classBytes = (byte[])nameBytesPair[1];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[20]++;

        // The generated classes in this case refer only to Rhino classes
        // which must be accessible through this class loader
        ClassLoader rhinoLoader = getClass().getClassLoader();
        GeneratedClassLoader loader;
        loader = SecurityController.createLoader(rhinoLoader,
                                                 staticSecurityDomain);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[21]++;
        Exception e;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[22]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[23]++;
            Class<?> cl = loader.defineClass(className, classBytes);
            loader.linkClass(cl);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[24]++;
            return cl;
        } catch (SecurityException x) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[10]++;
            e = x;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[25]++;
        } catch (IllegalArgumentException x) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[11]++;
            e = x;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[26]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[9]++;
}
  }
        throw new RuntimeException("Malformed optimizer package " + e);
    }

    public byte[] compileToClassFile(CompilerEnvirons compilerEnv,
                                     String mainClassName,
                                     ScriptNode scriptOrFn,
                                     String encodedSource,
                                     boolean returnFunction)
    {
        this.compilerEnv = compilerEnv;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[27]++;

        transform(scriptOrFn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[28]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[29]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[12]++;
            System.out.println(scriptOrFn.toStringTree(scriptOrFn));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[30]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[13]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[14]++;
            scriptOrFn = scriptOrFn.getFunctionNode(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[32]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[15]++;}

        initScriptNodesData(scriptOrFn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[33]++;

        this.mainClassName = mainClassName;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[34]++;
        this.mainClassSignature
            = ClassFileWriter.classNameToSignature(mainClassName);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[35]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[36]++;
boolean CodeCoverTryBranchHelper_Try4 = false;

        try {
CodeCoverTryBranchHelper_Try4 = true;
            return generateCode(encodedSource);
        } catch (ClassFileWriter.ClassFileFormatException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[17]++;
            throw reportClassFileFormatException(scriptOrFn, e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[16]++;
}
  }
    }

    private RuntimeException reportClassFileFormatException(
        ScriptNode scriptOrFn,
        String message)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[37]++;
        String msg = scriptOrFn instanceof FunctionNode
        ? ScriptRuntime.getMessage2("msg.while.compiling.fn",
            ((FunctionNode)scriptOrFn).getFunctionName(), message)
        : ScriptRuntime.getMessage1("msg.while.compiling.script", message);
        return Context.reportRuntimeError(msg, scriptOrFn.getSourceName(),
            scriptOrFn.getLineno(), null, 0);
    }

    private void transform(ScriptNode tree)
    {
        initOptFunctions_r(tree);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[38]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[39]++;

        int optLevel = compilerEnv.getOptimizationLevel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[40]++;

        Map<String,OptFunctionNode> possibleDirectCalls = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((optLevel > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[18]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
           /*
            * Collect all of the contained functions into a hashtable
            * so that the call optimizer can access the class name & parameter
            * count for any call it encounters
            */
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((tree.getType() == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[20]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[43]++;
                int functionCount = tree.getFunctionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[44]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i != functionCount) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[3]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[45]++;
                    OptFunctionNode ofn = OptFunctionNode.get(tree, i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ofn.fnode.getFunctionType()
                        == FunctionNode.FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false))
                    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[22]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[47]++;
                        String name = ofn.fnode.getName();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
                        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((name.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[24]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
                            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((possibleDirectCalls == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[26]++;
                                possibleDirectCalls = new HashMap<String,OptFunctionNode>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[50]++;

                            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[27]++;}
                            possibleDirectCalls.put(name, ofn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[51]++;

                        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[25]++;}

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[23]++;}
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[21]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[19]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((possibleDirectCalls != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[28]++;
            directCallTargets = new ObjArray();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[53]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[29]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[54]++;

        OptTransformer ot = new OptTransformer(possibleDirectCalls,
                                               directCallTargets);
        ot.transform(tree);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[55]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((optLevel > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[30]++;
            (new Optimizer()).optimize(tree);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[57]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[31]++;}
    }

    private static void initOptFunctions_r(ScriptNode scriptOrFn)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[58]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = 0, N = scriptOrFn.getFunctionCount();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[6]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[59]++;
            FunctionNode fn = scriptOrFn.getFunctionNode(i);
            new OptFunctionNode(fn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[60]++;
            initOptFunctions_r(fn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[61]++;
        }
    }

    private void initScriptNodesData(ScriptNode scriptOrFn)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[62]++;
        ObjArray x = new ObjArray();
        collectScriptNodes_r(scriptOrFn, x);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[63]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[64]++;

        int count = x.size();
        scriptOrFnNodes = new ScriptNode[count];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[65]++;
        x.toArray(scriptOrFnNodes);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[66]++;

        scriptOrFnIndexes = new ObjToIntMap(count);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[67]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[68]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[9]++;
}
            scriptOrFnIndexes.put(scriptOrFnNodes[i], i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[69]++;
        }
    }

    private static void collectScriptNodes_r(ScriptNode n,
                                                 ObjArray x)
    {
        x.add(n);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[70]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[71]++;
        int nestedCount = n.getFunctionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[72]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i != nestedCount) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[12]++;
}
            collectScriptNodes_r(n.getFunctionNode(i), x);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[73]++;
        }
    }

    private byte[] generateCode(String encodedSource)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[74]++;
        boolean hasScript = (scriptOrFnNodes[0].getType() == Token.SCRIPT);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[75]++;
        boolean hasFunctions = (scriptOrFnNodes.length > 1 || !hasScript);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[76]++;

        String sourceFile = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateDebugInfo()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[32]++;
            sourceFile = scriptOrFnNodes[0].getSourceName();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[78]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[33]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[79]++;

        ClassFileWriter cfw = new ClassFileWriter(mainClassName,
                                                  SUPER_CLASS_NAME,
                                                  sourceFile);
        cfw.addField(ID_FIELD_NAME, "I", ACC_PRIVATE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[80]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((hasFunctions) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[34]++;
            generateFunctionConstructor(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[82]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[35]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[83]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((hasScript) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[36]++;
            cfw.addInterface("org/mozilla/javascript/Script");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[84]++;
            generateScriptCtor(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[85]++;
            generateMain(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[86]++;
            generateExecute(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[87]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[37]++;}

        generateCallMethod(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[88]++;
        generateResumeGenerator(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[89]++;

        generateNativeFunctionOverrides(cfw, encodedSource);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[90]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[91]++;

        int count = scriptOrFnNodes.length;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[92]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[13]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[14]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[15]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[93]++;
            ScriptNode n = scriptOrFnNodes[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[94]++;

            BodyCodegen bodygen = new BodyCodegen();
            bodygen.cfw = cfw;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[95]++;
            bodygen.codegen = this;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[96]++;
            bodygen.compilerEnv = compilerEnv;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[97]++;
            bodygen.scriptOrFn = n;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[98]++;
            bodygen.scriptOrFnIndex = i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[99]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[100]++;
boolean CodeCoverTryBranchHelper_Try5 = false;

            try {
CodeCoverTryBranchHelper_Try5 = true;
                bodygen.generateBodyCode();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[101]++;
            } catch (ClassFileWriter.ClassFileFormatException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[39]++;
                throw reportClassFileFormatException(n, e.getMessage());
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[38]++;
}
  }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[102]++;
int CodeCoverConditionCoverageHelper_C20;

            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n.getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[40]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[103]++;
                OptFunctionNode ofn = OptFunctionNode.get(n);
                generateFunctionInit(cfw, ofn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[104]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[105]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ofn.isTargetOfDirectCall()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[42]++;
                    emitDirectConstructor(cfw, ofn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[106]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[43]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[41]++;}
        }

        emitRegExpInit(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[107]++;
        emitConstantDudeInitializers(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[108]++;

        return cfw.toByteArray();
    }

    private void emitDirectConstructor(ClassFileWriter cfw,
                                       OptFunctionNode ofn)
    {
/*
    we generate ..
        Scriptable directConstruct(<directCallArgs>) {
            Scriptable newInstance = createObject(cx, scope);
            Object val = <body-name>(cx, scope, newInstance, <directCallArgs>);
            if (val instanceof Scriptable) {
                return (Scriptable) val;
            }
            return newInstance;
        }
*/
        cfw.startMethod(getDirectCtorName(ofn.fnode),
                        getBodyMethodSignature(ofn.fnode),
                        (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[109]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[110]++;

        int argCount = ofn.fnode.getParamCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[111]++;
        int firstLocal = (4 + argCount * 3) + 1;

        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[112]++; // this
        cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[113]++; // cx
        cfw.addALoad(2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[114]++; // scope
        cfw.addInvoke(ByteCode.INVOKEVIRTUAL,
                      "org/mozilla/javascript/BaseFunction",
                      "createObject",
                      "(Lorg/mozilla/javascript/Context;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[115]++;
        cfw.addAStore(firstLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[116]++;

        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[117]++;
        cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[118]++;
        cfw.addALoad(2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[119]++;
        cfw.addALoad(firstLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[120]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[121]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[16]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < argCount) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[16]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[17]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[18]++;
}
            cfw.addALoad(4 + (i * 3));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[122]++;
            cfw.addDLoad(5 + (i * 3));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[123]++;
        }
        cfw.addALoad(4 + argCount * 3);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[124]++;
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      mainClassName,
                      getBodyMethodName(ofn.fnode),
                      getBodyMethodSignature(ofn.fnode));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[125]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[126]++;
        int exitLabel = cfw.acquireLabel();
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[127]++; // make a copy of direct call result
        cfw.add(ByteCode.INSTANCEOF, "org/mozilla/javascript/Scriptable");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[128]++;
        cfw.add(ByteCode.IFEQ, exitLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[129]++;
        // cast direct call result
        cfw.add(ByteCode.CHECKCAST, "org/mozilla/javascript/Scriptable");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[130]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[131]++;
        cfw.markLabel(exitLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[132]++;

        cfw.addALoad(firstLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[133]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[134]++;

        cfw.stopMethod((short)(firstLocal + 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[135]++;
    }

    static boolean isGenerator(ScriptNode node)
    {
        return (node.getType() == Token.FUNCTION ) &&
                ((FunctionNode)node).isGenerator();
    }

    // How dispatch to generators works:
    // Two methods are generated corresponding to a user-written generator.
    // One of these creates a generator object (NativeGenerator), which is
    // returned to the user. The other method contains all of the body code
    // of the generator.
    // When a user calls a generator, the call() method dispatches control to
    // to the method that creates the NativeGenerator object. Subsequently when
    // the user invokes .next(), .send() or any such method on the generator
    // object, the resumeGenerator() below dispatches the call to the
    // method corresponding to the generator body. As a matter of convention
    // the generator body is given the name of the generator activation function
    // appended by "_gen".
    private void generateResumeGenerator(ClassFileWriter cfw)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[136]++;
        boolean hasGenerators = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[137]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[19]++;


int CodeCoverConditionCoverageHelper_C23;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < scriptOrFnNodes.length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[19]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[20]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[21]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[138]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isGenerator(scriptOrFnNodes[i])) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[44]++;
            	hasGenerators = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[139]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[45]++;}
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[140]++;
int CodeCoverConditionCoverageHelper_C25;

        // if there are no generators defined, we don't implement a
        // resumeGenerator(). The base class provides a default implementation.
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((hasGenerators) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[46]++;
            return;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[47]++;}

        cfw.startMethod("resumeGenerator",
                        "(Lorg/mozilla/javascript/Context;" +
                        "Lorg/mozilla/javascript/Scriptable;" +
                        "ILjava/lang/Object;" +
                        "Ljava/lang/Object;)Ljava/lang/Object;",
                        (short)(ACC_PUBLIC | ACC_FINAL));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[141]++;

        // load arguments for dispatch to the corresponding *_gen method
        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[142]++;
        cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[143]++;
        cfw.addALoad(2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[144]++;
        cfw.addALoad(4);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[145]++;
        cfw.addALoad(5);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[146]++;
        cfw.addILoad(3);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[147]++;

        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[148]++;
        cfw.add(ByteCode.GETFIELD, cfw.getClassName(), ID_FIELD_NAME, "I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[149]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[150]++;

        int startSwitch = cfw.addTableSwitch(0, scriptOrFnNodes.length - 1);
        cfw.markTableSwitchDefault(startSwitch);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[151]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[152]++;
        int endlabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[153]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[22]++;


int CodeCoverConditionCoverageHelper_C26;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < scriptOrFnNodes.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[22]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[23]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[24]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[154]++;
            ScriptNode n = scriptOrFnNodes[i];
            cfw.markTableSwitchCase(startSwitch, i, (short)6);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[155]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[156]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isGenerator(n)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[48]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[157]++;
                String type = "(" +
                              mainClassSignature +
                              "Lorg/mozilla/javascript/Context;" +
                              "Lorg/mozilla/javascript/Scriptable;" +
                              "Ljava/lang/Object;" +
                              "Ljava/lang/Object;I)Ljava/lang/Object;";
                cfw.addInvoke(ByteCode.INVOKESTATIC,
                              mainClassName,
                              getBodyMethodName(n) + "_gen",
                              type);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[158]++;
                cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[159]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[49]++;
                cfw.add(ByteCode.GOTO, endlabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[160]++;
            }
        }

        cfw.markLabel(endlabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[161]++;
        pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[162]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[163]++;


        // this method uses as many locals as there are arguments (hence 6)
        cfw.stopMethod((short)6);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[164]++;
    }

    private void generateCallMethod(ClassFileWriter cfw)
    {
        cfw.startMethod("call",
                        "(Lorg/mozilla/javascript/Context;" +
                        "Lorg/mozilla/javascript/Scriptable;" +
                        "Lorg/mozilla/javascript/Scriptable;" +
                        "[Ljava/lang/Object;)Ljava/lang/Object;",
                        (short)(ACC_PUBLIC | ACC_FINAL));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[165]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[166]++;

        // Generate code for:
        // if (!ScriptRuntime.hasTopCall(cx)) {
        //     return ScriptRuntime.doTopCall(this, cx, scope, thisObj, args);
        // }

        int nonTopCallLabel = cfw.acquireLabel();
        cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[167]++; //cx
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/ScriptRuntime",
                      "hasTopCall",
                      "(Lorg/mozilla/javascript/Context;"
                      +")Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[168]++;
        cfw.add(ByteCode.IFNE, nonTopCallLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[169]++;
        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[170]++;
        cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[171]++;
        cfw.addALoad(2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[172]++;
        cfw.addALoad(3);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[173]++;
        cfw.addALoad(4);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[174]++;
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/ScriptRuntime",
                      "doTopCall",
                      "(Lorg/mozilla/javascript/Callable;"
                      +"Lorg/mozilla/javascript/Context;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +"[Ljava/lang/Object;"
                      +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[175]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[176]++;
        cfw.markLabel(nonTopCallLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[177]++;

        // Now generate switch to call the real methods
        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[178]++;
        cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[179]++;
        cfw.addALoad(2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[180]++;
        cfw.addALoad(3);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[181]++;
        cfw.addALoad(4);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[182]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[183]++;

        int end = scriptOrFnNodes.length;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[184]++;
        boolean generateSwitch = (2 <= end);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[185]++;

        int switchStart = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[186]++;
        int switchStackTop = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[187]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((generateSwitch) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[50]++;
            cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[188]++;
            cfw.add(ByteCode.GETFIELD, cfw.getClassName(), ID_FIELD_NAME, "I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[189]++;
            // do switch from (1,  end - 1) mapping 0 to
            // the default case
            switchStart = cfw.addTableSwitch(1, end - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[190]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[51]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[191]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[25]++;


int CodeCoverConditionCoverageHelper_C29;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[25]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[26]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[27]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[192]++;
            ScriptNode n = scriptOrFnNodes[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[193]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((generateSwitch) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[52]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[194]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[54]++;
                    cfw.markTableSwitchDefault(switchStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[195]++;
                    switchStackTop = cfw.getStackTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[196]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[55]++;
                    cfw.markTableSwitchCase(switchStart, i - 1,
                                            switchStackTop);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[197]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[53]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[198]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((n.getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[56]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[199]++;
                OptFunctionNode ofn = OptFunctionNode.get(n);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[200]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ofn.isTargetOfDirectCall()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[58]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[201]++;
                    int pcount = ofn.fnode.getParamCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[202]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((pcount != 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[60]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[203]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[28]++;


int CodeCoverConditionCoverageHelper_C35;
                        // loop invariant:
                        // stack top == arguments array from addALoad4()
                        for (int p = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((p != pcount) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); ++p) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[28]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[29]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[30]++;
}
                            cfw.add(ByteCode.ARRAYLENGTH);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[204]++;
                            cfw.addPush(p);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[205]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[206]++;
                            int undefArg = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[207]++;
                            int beyond = cfw.acquireLabel();
                            cfw.add(ByteCode.IF_ICMPLE, undefArg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[208]++;
                            // get array[p]
                            cfw.addALoad(4);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[209]++;
                            cfw.addPush(p);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[210]++;
                            cfw.add(ByteCode.AALOAD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[211]++;
                            cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[212]++;
                            cfw.markLabel(undefArg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[213]++;
                            pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[214]++;
                            cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[215]++;
                            // Only one push
                            cfw.adjustStackTop(-1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[216]++;
                            cfw.addPush(0.0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[217]++;
                            // restore invariant
                            cfw.addALoad(4);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[218]++;
                        }

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[61]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[59]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[57]++;}
            cfw.addInvoke(ByteCode.INVOKESTATIC,
                          mainClassName,
                          getBodyMethodName(n),
                          getBodyMethodSignature(n));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[219]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[220]++;
        }
        cfw.stopMethod((short)5);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[221]++;
        // 5: this, cx, scope, js this, args[]
    }

    private void generateMain(ClassFileWriter cfw)
    {
        cfw.startMethod("main", "([Ljava/lang/String;)V",
                        (short)(ACC_PUBLIC | ACC_STATIC));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[222]++;

        // load new ScriptImpl()
        cfw.add(ByteCode.NEW, cfw.getClassName());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[223]++;
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[224]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, cfw.getClassName(),
                      "<init>", "()V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[225]++;
         // load 'args'
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[226]++;
        // Call mainMethodClass.main(Script script, String[] args)
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      mainMethodClass,
                      "main",
                      "(Lorg/mozilla/javascript/Script;[Ljava/lang/String;)V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[227]++;
        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[228]++;
        // 1 = String[] args
        cfw.stopMethod((short)1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[229]++;
    }

    private void generateExecute(ClassFileWriter cfw)
    {
        cfw.startMethod("exec",
                        "(Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +")Ljava/lang/Object;",
                        (short)(ACC_PUBLIC | ACC_FINAL));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[230]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[231]++;

        final int CONTEXT_ARG = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[232]++;
        final int SCOPE_ARG = 2;

        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[233]++;
        cfw.addALoad(CONTEXT_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[234]++;
        cfw.addALoad(SCOPE_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[235]++;
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[236]++;
        cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[237]++;
        cfw.addInvoke(ByteCode.INVOKEVIRTUAL,
                      cfw.getClassName(),
                      "call",
                      "(Lorg/mozilla/javascript/Context;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +"[Ljava/lang/Object;"
                      +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[238]++;

        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[239]++;
        // 3 = this + context + scope
        cfw.stopMethod((short)3);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[240]++;
    }

    private void generateScriptCtor(ClassFileWriter cfw)
    {
        cfw.startMethod("<init>", "()V", ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[241]++;

        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[242]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, SUPER_CLASS_NAME,
                      "<init>", "()V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[243]++;
        // set id to 0
        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[244]++;
        cfw.addPush(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[245]++;
        cfw.add(ByteCode.PUTFIELD, cfw.getClassName(), ID_FIELD_NAME, "I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[246]++;

        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[247]++;
        // 1 parameter = this
        cfw.stopMethod((short)1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[248]++;
    }

    private void generateFunctionConstructor(ClassFileWriter cfw)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[249]++;
        final int SCOPE_ARG = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[250]++;
        final int CONTEXT_ARG = 2;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[251]++;
        final int ID_ARG = 3;

        cfw.startMethod("<init>", FUNCTION_CONSTRUCTOR_SIGNATURE, ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[252]++;
        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[253]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, SUPER_CLASS_NAME,
                      "<init>", "()V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[254]++;

        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[255]++;
        cfw.addILoad(ID_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[256]++;
        cfw.add(ByteCode.PUTFIELD, cfw.getClassName(), ID_FIELD_NAME, "I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[257]++;

        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[258]++;
        cfw.addALoad(CONTEXT_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[259]++;
        cfw.addALoad(SCOPE_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[260]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[261]++;

        int start = (scriptOrFnNodes[0].getType() == Token.SCRIPT) ? 1 : 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[262]++;
        int end = scriptOrFnNodes.length;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[263]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((start == end) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[62]++; throw badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[63]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[264]++;
        boolean generateSwitch = (2 <= end - start);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[265]++;

        int switchStart = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[266]++;
        int switchStackTop = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[267]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((generateSwitch) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[64]++;
            cfw.addILoad(ID_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[268]++;
            // do switch from (start + 1,  end - 1) mapping start to
            // the default case
            switchStart = cfw.addTableSwitch(start + 1, end - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[269]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[65]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[270]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[31]++;


int CodeCoverConditionCoverageHelper_C38;

        for (int i = start;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[31]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[32]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[33]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[271]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((generateSwitch) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[66]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[272]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i == start) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[68]++;
                    cfw.markTableSwitchDefault(switchStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[273]++;
                    switchStackTop = cfw.getStackTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[274]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[69]++;
                    cfw.markTableSwitchCase(switchStart, i - 1 - start,
                                            switchStackTop);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[275]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[67]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[276]++;
            OptFunctionNode ofn = OptFunctionNode.get(scriptOrFnNodes[i]);
            cfw.addInvoke(ByteCode.INVOKESPECIAL,
                          mainClassName,
                          getFunctionInitMethodName(ofn),
                          FUNCTION_INIT_SIGNATURE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[277]++;
            cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[278]++;
        }

        // 4 = this + scope + context + id
        cfw.stopMethod((short)4);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[279]++;
    }

    private void generateFunctionInit(ClassFileWriter cfw,
                                      OptFunctionNode ofn)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[280]++;
        final int CONTEXT_ARG = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[281]++;
        final int SCOPE_ARG = 2;
        cfw.startMethod(getFunctionInitMethodName(ofn),
                        FUNCTION_INIT_SIGNATURE,
                        (short)(ACC_PRIVATE | ACC_FINAL));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[282]++;

        // Call NativeFunction.initScriptFunction
        cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[283]++;
        cfw.addALoad(CONTEXT_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[284]++;
        cfw.addALoad(SCOPE_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[285]++;
        cfw.addInvoke(ByteCode.INVOKEVIRTUAL,
                      "org/mozilla/javascript/NativeFunction",
                      "initScriptFunction",
                      "(Lorg/mozilla/javascript/Context;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +")V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[286]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[287]++;
int CodeCoverConditionCoverageHelper_C41;

        // precompile all regexp literals
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((ofn.fnode.getRegexpCount() != 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[70]++;
            cfw.addALoad(CONTEXT_ARG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[288]++;
            cfw.addInvoke(ByteCode.INVOKESTATIC, mainClassName,
                          REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[289]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[71]++;}

        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[290]++;
        // 3 = (scriptThis/functionRef) + scope + context
        cfw.stopMethod((short)3);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[291]++;
    }

    private void generateNativeFunctionOverrides(ClassFileWriter cfw,
                                                 String encodedSource)
    {
        // Override NativeFunction.getLanguageVersion() with
        // public int getLanguageVersion() { return <version-constant>; }

        cfw.startMethod("getLanguageVersion", "()I", ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[292]++;

        cfw.addPush(compilerEnv.getLanguageVersion());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[293]++;
        cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[294]++;

        // 1: this and no argument or locals
        cfw.stopMethod((short)1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[295]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[296]++;

        // The rest of NativeFunction overrides require specific code for each
        // script/function id

        final int Do_getFunctionName      = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[297]++;
        final int Do_getParamCount        = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[298]++;
        final int Do_getParamAndVarCount  = 2;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[299]++;
        final int Do_getParamOrVarName    = 3;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[300]++;
        final int Do_getEncodedSource     = 4;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[301]++;
        final int Do_getParamOrVarConst   = 5;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[302]++;
        final int SWITCH_COUNT            = 6;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[303]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[34]++;


int CodeCoverConditionCoverageHelper_C42;

        for (int methodIndex = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((methodIndex != SWITCH_COUNT) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); ++methodIndex) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[34]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[35]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[36]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[304]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((methodIndex == Do_getEncodedSource) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((encodedSource == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[72]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[305]++;
                continue;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[73]++;}

            // Generate:
            //   prologue;
            //   switch over function id to implement function-specific action
            //   epilogue

            short methodLocals;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[306]++;
            switch (methodIndex) {
              case Do_getFunctionName:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[74]++;
                methodLocals = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[307]++; // Only this
                cfw.startMethod("getFunctionName", "()Ljava/lang/String;",
                                ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[308]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[309]++;
                break;
              case Do_getParamCount:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[75]++;
                methodLocals = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[310]++; // Only this
                cfw.startMethod("getParamCount", "()I",
                                ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[311]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[312]++;
                break;
              case Do_getParamAndVarCount:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[76]++;
                methodLocals = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[313]++; // Only this
                cfw.startMethod("getParamAndVarCount", "()I",
                                ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[314]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[315]++;
                break;
              case Do_getParamOrVarName:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[77]++;
                methodLocals = 1 + 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[316]++; // this + paramOrVarIndex
                cfw.startMethod("getParamOrVarName", "(I)Ljava/lang/String;",
                                ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[317]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[318]++;
                break;
              case Do_getParamOrVarConst:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[78]++;
                methodLocals = 1 + 1 + 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[319]++; // this + paramOrVarName
                cfw.startMethod("getParamOrVarConst", "(I)Z",
                                ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[320]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[321]++;
                break;
              case Do_getEncodedSource:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[79]++;
                methodLocals = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[322]++; // Only this
                cfw.startMethod("getEncodedSource", "()Ljava/lang/String;",
                                ACC_PUBLIC);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[323]++;
                cfw.addPush(encodedSource);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[324]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[325]++;
                break;
              default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[80]++;
                throw Kit.codeBug();
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[326]++;

            int count = scriptOrFnNodes.length;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[327]++;

            int switchStart = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[328]++;
            int switchStackTop = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[329]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[81]++;
                // Generate switch but only if there is more then one
                // script/function
                cfw.addLoadThis();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[330]++;
                cfw.add(ByteCode.GETFIELD, cfw.getClassName(),
                        ID_FIELD_NAME, "I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[331]++;

                // do switch from 1 .. count - 1 mapping 0 to the default case
                switchStart = cfw.addTableSwitch(1, count - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[332]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[82]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[333]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[37]++;


int CodeCoverConditionCoverageHelper_C45;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[37]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[38]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[39]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[334]++;
                ScriptNode n = scriptOrFnNodes[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[335]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[83]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[336]++;
int CodeCoverConditionCoverageHelper_C47;
                    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[85]++;
                        cfw.markTableSwitchDefault(switchStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[337]++;
                        switchStackTop = cfw.getStackTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[338]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[86]++;}

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[84]++;
                    cfw.markTableSwitchCase(switchStart, i - 1,
                                            switchStackTop);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[339]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[340]++;

                // Impelemnet method-specific switch code
                switch (methodIndex) {
                  case Do_getFunctionName:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[87]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[341]++;
int CodeCoverConditionCoverageHelper_C48;
                    // Push function name
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((n.getType() == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[88]++;
                        cfw.addPush("");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[342]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[89]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[343]++;
                        String name = ((FunctionNode)n).getName();
                        cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[344]++;
                    }
                    cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[345]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[346]++;
                    break;

                  case Do_getParamCount:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[90]++;
                    // Push number of defined parameters
                    cfw.addPush(n.getParamCount());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[347]++;
                    cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[348]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[349]++;
                    break;

                  case Do_getParamAndVarCount:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[91]++;
                    // Push number of defined parameters and declared variables
                    cfw.addPush(n.getParamAndVarCount());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[350]++;
                    cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[351]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[352]++;
                    break;

                  case Do_getParamOrVarName:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[92]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[353]++;
                    // Push name of parameter using another switch
                    // over paramAndVarCount
                    int paramAndVarCount = n.getParamAndVarCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[354]++;
int CodeCoverConditionCoverageHelper_C49;
                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((paramAndVarCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[93]++;
                        // The runtime should never call the method in this
                        // case but to make bytecode verifier happy return null
                        // as throwing execption takes more code
                        cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[355]++;
                        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[356]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[94]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[357]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((paramAndVarCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[95]++;
                        // As above do not check for valid index but always
                        // return the name of the first param
                        cfw.addPush(n.getParamOrVarName(0));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[358]++;
                        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[359]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[96]++;
                        // Do switch over getParamOrVarName
                        cfw.addILoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[360]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[361]++; // param or var index
                        // do switch from 1 .. paramAndVarCount - 1 mapping 0
                        // to the default case
                        int paramSwitchStart = cfw.addTableSwitch(
                                                   1, paramAndVarCount - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[362]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[40]++;


int CodeCoverConditionCoverageHelper_C51;
                        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((j != paramAndVarCount) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[40]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[41]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[42]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[363]++;
int CodeCoverConditionCoverageHelper_C52;
                            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((cfw.getStackTop() != 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[97]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[364]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[98]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[365]++;
                            String s = n.getParamOrVarName(j);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[366]++;
int CodeCoverConditionCoverageHelper_C53;
                            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[99]++;
                                cfw.markTableSwitchDefault(paramSwitchStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[367]++;

                            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[100]++;
                                cfw.markTableSwitchCase(paramSwitchStart, j - 1,
                                                        0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[368]++;
                            }
                            cfw.addPush(s);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[369]++;
                            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[370]++;
                        }
                    }
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[371]++;
                    break;

                    case Do_getParamOrVarConst:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[101]++;
                        // Push name of parameter using another switch
                        // over paramAndVarCount
                        paramAndVarCount = n.getParamAndVarCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[372]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[373]++;
                        boolean [] constness = n.getParamAndVarConst();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[374]++;
int CodeCoverConditionCoverageHelper_C54;
                        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((paramAndVarCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[102]++;
                            // The runtime should never call the method in this
                            // case but to make bytecode verifier happy return null
                            // as throwing execption takes more code
                            cfw.add(ByteCode.ICONST_0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[375]++;
                            cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[376]++;

                        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[103]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[377]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((paramAndVarCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[104]++;
                            // As above do not check for valid index but always
                            // return the name of the first param
                            cfw.addPush(constness[0]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[378]++;
                            cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[379]++;

                        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[105]++;
                            // Do switch over getParamOrVarName
                            cfw.addILoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[380]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[381]++; // param or var index
                            // do switch from 1 .. paramAndVarCount - 1 mapping 0
                            // to the default case
                            int paramSwitchStart = cfw.addTableSwitch(
                                                       1, paramAndVarCount - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[382]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[43]++;


int CodeCoverConditionCoverageHelper_C56;
                            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((j != paramAndVarCount) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[43]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[44]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[45]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[383]++;
int CodeCoverConditionCoverageHelper_C57;
                                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((cfw.getStackTop() != 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[106]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[384]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[107]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[385]++;
int CodeCoverConditionCoverageHelper_C58;
                                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[108]++;
                                    cfw.markTableSwitchDefault(paramSwitchStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[386]++;

                                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[109]++;
                                    cfw.markTableSwitchCase(paramSwitchStart, j - 1,
                                                            0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[387]++;
                                }
                                cfw.addPush(constness[j]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[388]++;
                                cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[389]++;
                            }
                        }
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[390]++;
                      break;

                  case Do_getEncodedSource:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[110]++;
                    // Push number encoded source start and end
                    // to prepare for encodedSource.substring(start, end)
                    cfw.addPush(n.getEncodedSourceStart());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[391]++;
                    cfw.addPush(n.getEncodedSourceEnd());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[392]++;
                    cfw.addInvoke(ByteCode.INVOKEVIRTUAL,
                                  "java/lang/String",
                                  "substring",
                                  "(II)Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[393]++;
                    cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[394]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[395]++;
                    break;

                  default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[111]++;
                    throw Kit.codeBug();
                }
            }

            cfw.stopMethod(methodLocals);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[396]++;
        }
    }

    private void emitRegExpInit(ClassFileWriter cfw)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[397]++;
        // precompile all regexp literals

        int totalRegCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[398]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[46]++;


int CodeCoverConditionCoverageHelper_C59;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((i != scriptOrFnNodes.length) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[46]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[47]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[48]++;
}
            totalRegCount += scriptOrFnNodes[i].getRegexpCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[399]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[400]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((totalRegCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[112]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[113]++;}

        cfw.startMethod(REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE,
                (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[401]++;
        cfw.addField("_reInitDone", "Z",
                     (short)(ACC_STATIC | ACC_PRIVATE | ACC_VOLATILE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[402]++;
        cfw.add(ByteCode.GETSTATIC, mainClassName, "_reInitDone", "Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[403]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[404]++;
        int doInit = cfw.acquireLabel();
        cfw.add(ByteCode.IFEQ, doInit);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[405]++;
        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[406]++;
        cfw.markLabel(doInit);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[407]++;

        // get regexp proxy and store it in local slot 1
        cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[408]++; // context
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/ScriptRuntime",
                      "checkRegExpProxy",
                      "(Lorg/mozilla/javascript/Context;"
                      +")Lorg/mozilla/javascript/RegExpProxy;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[409]++;
        cfw.addAStore(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[410]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[411]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[49]++;


int CodeCoverConditionCoverageHelper_C61; // proxy

        // We could apply double-checked locking here but concurrency
        // shouldn't be a problem in practice
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((i != scriptOrFnNodes.length) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[49]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[50]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[51]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[412]++;
            ScriptNode n = scriptOrFnNodes[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[413]++;
            int regCount = n.getRegexpCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[414]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[52]++;


int CodeCoverConditionCoverageHelper_C62;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((j != regCount) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[52]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[53]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[54]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[415]++;
                String reFieldName = getCompiledRegexpName(n, j);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[416]++;
                String reFieldType = "Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[417]++;
                String reString = n.getRegexpString(j);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[418]++;
                String reFlags = n.getRegexpFlags(j);
                cfw.addField(reFieldName, reFieldType,
                             (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[419]++;
                cfw.addALoad(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[420]++; // proxy
                cfw.addALoad(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[421]++; // context
                cfw.addPush(reString);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[422]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[423]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((reFlags == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[114]++;
                    cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[424]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[115]++;
                    cfw.addPush(reFlags);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[425]++;
                }
                cfw.addInvoke(ByteCode.INVOKEINTERFACE,
                              "org/mozilla/javascript/RegExpProxy",
                              "compileRegExp",
                              "(Lorg/mozilla/javascript/Context;"
                              +"Ljava/lang/String;Ljava/lang/String;"
                              +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[426]++;
                cfw.add(ByteCode.PUTSTATIC, mainClassName,
                        reFieldName, reFieldType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[427]++;
            }
        }

        cfw.addPush(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[428]++;
        cfw.add(ByteCode.PUTSTATIC, mainClassName, "_reInitDone", "Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[429]++;
        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[430]++;
        cfw.stopMethod((short)2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[431]++;
    }

    private void emitConstantDudeInitializers(ClassFileWriter cfw)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[432]++;
        int N = itsConstantListSize;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[433]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[116]++;
            return;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[117]++;}

        cfw.startMethod("<clinit>", "()V", (short)(ACC_STATIC | ACC_FINAL));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[434]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[435]++;

        double[] array = itsConstantList;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[436]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[55]++;


int CodeCoverConditionCoverageHelper_C65;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[55]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[56]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[57]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[437]++;
            double num = array[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[438]++;
            String constantName = "_k" + i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[439]++;
            String constantType = getStaticConstantWrapperType(num);
            cfw.addField(constantName, constantType,
                        (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[440]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[441]++;
            int inum = (int)num;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[442]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((inum == num) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[118]++;
                cfw.addPush(inum);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[443]++;
                cfw.addInvoke(ByteCode.INVOKESTATIC, "java/lang/Integer",
                              "valueOf", "(I)Ljava/lang/Integer;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[444]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[119]++;
                cfw.addPush(num);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[445]++;
                addDoubleWrap(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[446]++;
            }
            cfw.add(ByteCode.PUTSTATIC, mainClassName,
                    constantName, constantType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[447]++;
        }

        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[448]++;
        cfw.stopMethod((short)0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[449]++;
    }

    void pushNumberAsObject(ClassFileWriter cfw, double num)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[450]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((num == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[120]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[451]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((1 / num > 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[122]++;
                // +0.0
                cfw.add(ByteCode.GETSTATIC,
                        "org/mozilla/javascript/optimizer/OptRuntime",
                        "zeroObj", "Ljava/lang/Double;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[452]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[123]++;
                cfw.addPush(num);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[453]++;
                addDoubleWrap(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[454]++;
            }


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[121]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[455]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((num == 1.0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[124]++;
            cfw.add(ByteCode.GETSTATIC,
                    "org/mozilla/javascript/optimizer/OptRuntime",
                    "oneObj", "Ljava/lang/Double;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[456]++;
            return;


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[125]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[457]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((num == -1.0) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[126]++;
            cfw.add(ByteCode.GETSTATIC,
                    "org/mozilla/javascript/optimizer/OptRuntime",
                    "minusOneObj", "Ljava/lang/Double;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[458]++;


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[127]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[459]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((num != num) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[128]++;
            cfw.add(ByteCode.GETSTATIC,
                    "org/mozilla/javascript/ScriptRuntime",
                    "NaNobj", "Ljava/lang/Double;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[460]++;


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[129]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[461]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((itsConstantListSize >= 2000) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[130]++;
            // There appears to be a limit in the JVM on either the number
            // of static fields in a class or the size of the class
            // initializer. Either way, we can't have any more than 2000
            // statically init'd constants.
            cfw.addPush(num);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[462]++;
            addDoubleWrap(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[463]++;


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[131]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[464]++;
            int N = itsConstantListSize;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[465]++;
            int index = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[466]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[132]++;
                itsConstantList = new double[64];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[467]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[133]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[468]++;
                double[] array = itsConstantList;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[469]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[58]++;


int CodeCoverConditionCoverageHelper_C74;
                while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((index != N) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((array[index] != num) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[58]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[59]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[60]++;
}
                    ++index;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[470]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[471]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((N == array.length) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[134]++;
                    array = new double[N * 2];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[472]++;
                    System.arraycopy(itsConstantList, 0, array, 0, N);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[473]++;
                    itsConstantList = array;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[474]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[135]++;}
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[475]++;
int CodeCoverConditionCoverageHelper_C76;
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((index == N) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[136]++;
                itsConstantList[N] = num;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[476]++;
                itsConstantListSize = N + 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[477]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[137]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[478]++;
            String constantName = "_k" + index;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[479]++;
            String constantType = getStaticConstantWrapperType(num);
            cfw.add(ByteCode.GETSTATIC, mainClassName,
                    constantName, constantType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[480]++;
        }
}
}
}
}
    }

    private static void addDoubleWrap(ClassFileWriter cfw)
    {
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/optimizer/OptRuntime",
                      "wrapDouble", "(D)Ljava/lang/Double;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[481]++;
    }

    private static String getStaticConstantWrapperType(double num)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[482]++;
        int inum = (int)num;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[483]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((inum == num) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[138]++;
            return "Ljava/lang/Integer;";

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[139]++;
            return "Ljava/lang/Double;";
        }
    }
    static void pushUndefined(ClassFileWriter cfw)
    {
        cfw.add(ByteCode.GETSTATIC, "org/mozilla/javascript/Undefined",
                "instance", "Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[484]++;
    }

    int getIndex(ScriptNode n)
    {
        return scriptOrFnIndexes.getExisting(n);
    }

    String getDirectCtorName(ScriptNode n)
    {
        return "_n" + getIndex(n);
    }

    String getBodyMethodName(ScriptNode n)
    {
        return "_c_" + cleanName(n) + "_" + getIndex(n);
    }

    /**
     * Gets a Java-compatible "informative" name for the the ScriptOrFnNode
     */
    String cleanName(final ScriptNode n)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[485]++;
      String result = "";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[486]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((n instanceof FunctionNode) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[140]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[487]++;
        Name name = ((FunctionNode) n).getFunctionName();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[488]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[142]++;
          result = "anonymous";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[489]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[143]++;
          result = name.getIdentifier();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[490]++;
        }

      } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[141]++;
        result = "script";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[491]++;
      }
      return result;
    }

    String getBodyMethodSignature(ScriptNode n)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[492]++;
        StringBuffer sb = new StringBuffer();
        sb.append('(');
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[493]++;
        sb.append(mainClassSignature);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[494]++;
        sb.append("Lorg/mozilla/javascript/Context;"
                  +"Lorg/mozilla/javascript/Scriptable;"
                  +"Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[495]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[496]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((n.getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[144]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[497]++;
            OptFunctionNode ofn = OptFunctionNode.get(n);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[498]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((ofn.isTargetOfDirectCall()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[146]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[499]++;
                int pCount = ofn.fnode.getParamCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[500]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[61]++;


int CodeCoverConditionCoverageHelper_C82;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i != pCount) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[61]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[62]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[63]++;
}
                    sb.append("Ljava/lang/Object;D");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[501]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[147]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[145]++;}
        sb.append("[Ljava/lang/Object;)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[502]++;
        return sb.toString();
    }

    String getFunctionInitMethodName(OptFunctionNode ofn)
    {
        return "_i"+getIndex(ofn.fnode);
    }

    String getCompiledRegexpName(ScriptNode n, int regexpIndex)
    {
        return "_re"+getIndex(n)+"_"+regexpIndex;
    }

    static RuntimeException badTree()
    {
        throw new RuntimeException("Bad tree in codegen");
    }

     public void setMainMethodClass(String className)
     {
         mainMethodClass = className;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[503]++;
     }

     static final String DEFAULT_MAIN_METHOD_CLASS
        = "org.mozilla.javascript.optimizer.OptRuntime";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[504]++;
  }

    private static final String SUPER_CLASS_NAME
        = "org.mozilla.javascript.NativeFunction";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[505]++;
  }

    static final String ID_FIELD_NAME = "_id";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[506]++;
  }

    static final String REGEXP_INIT_METHOD_NAME = "_reInit";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[507]++;
  }
    static final String REGEXP_INIT_METHOD_SIGNATURE
        =  "(Lorg/mozilla/javascript/Context;)V";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[508]++;
  }

    static final String FUNCTION_INIT_SIGNATURE
        =  "(Lorg/mozilla/javascript/Context;"
           +"Lorg/mozilla/javascript/Scriptable;"
           +")V";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[509]++;
  }

   static final String FUNCTION_CONSTRUCTOR_SIGNATURE
        = "(Lorg/mozilla/javascript/Scriptable;"
          +"Lorg/mozilla/javascript/Context;I)V";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[510]++;
  }

    private static final Object globalLock = new Object();
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[511]++;
  }
    private static int globalSerialClassCounter;

    private CompilerEnvirons compilerEnv;

    private ObjArray directCallTargets;
    ScriptNode[] scriptOrFnNodes;
    private ObjToIntMap scriptOrFnIndexes;

    private String mainMethodClass = DEFAULT_MAIN_METHOD_CLASS;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[512]++;
  }

    String mainClassName;
    String mainClassSignature;

    private double[] itsConstantList;
    private int itsConstantListSize;
}


class BodyCodegen
{
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.ping();
  }

    void generateBodyCode()
    {
        isGenerator = Codegen.isGenerator(scriptOrFn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[513]++;

        // generate the body of the current function or script object
        initBodyGeneration();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[514]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[515]++;
int CodeCoverConditionCoverageHelper_C83;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[148]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[516]++;

            // All functions in the generated bytecode have a unique name. Every
            // generator has a unique prefix followed by _gen
            String type = "(" +
                          codegen.mainClassSignature +
                          "Lorg/mozilla/javascript/Context;" +
                          "Lorg/mozilla/javascript/Scriptable;" +
                          "Ljava/lang/Object;" +
                          "Ljava/lang/Object;I)Ljava/lang/Object;";
            cfw.startMethod(codegen.getBodyMethodName(scriptOrFn) + "_gen",
                    type,
                    (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[517]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[149]++;
            cfw.startMethod(codegen.getBodyMethodName(scriptOrFn),
                    codegen.getBodyMethodSignature(scriptOrFn),
                    (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[518]++;
        }

        generatePrologue();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[519]++;
        Node treeTop;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[520]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((fnCurrent != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[150]++;
            treeTop = scriptOrFn.getLastChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[521]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[151]++;
            treeTop = scriptOrFn;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[522]++;
        }
        generateStatement(treeTop);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[523]++;
        generateEpilogue();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[524]++;

        cfw.stopMethod((short)(localsMax + 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[525]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[526]++;
int CodeCoverConditionCoverageHelper_C85;

        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[152]++;
            // generate the user visible method which when invoked will
            // return a generator object
            generateGenerator();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[527]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[153]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[528]++;
int CodeCoverConditionCoverageHelper_C86;

        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((literals != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[154]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[529]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[64]++;


int CodeCoverConditionCoverageHelper_C87;
            // literals list may grow while we're looping
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((i < literals.size()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[64]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[65]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[66]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[530]++;
                Node node = literals.get(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[531]++;
                int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[532]++;
                switch (type) {
                    case Token.OBJECTLIT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[156]++;
                        generateObjectLiteralFactory(node, i + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[533]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[534]++;
                        break;
                    case Token.ARRAYLIT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[157]++;
                        generateArrayLiteralFactory(node, i + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[535]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[536]++;
                        break;
                    default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[158]++;
                        Kit.codeBug(Token.typeToName(type));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[537]++;
                }
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[155]++;}

    }

    // This creates a the user-facing function that returns a NativeGenerator
    // object.
    private void generateGenerator()
    {
        cfw.startMethod(codegen.getBodyMethodName(scriptOrFn),
                        codegen.getBodyMethodSignature(scriptOrFn),
                        (short)(ACC_STATIC | ACC_PRIVATE));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[538]++;

        initBodyGeneration();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[539]++;
        argsLocal = firstFreeLocal++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[540]++;
        localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[541]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[542]++;
int CodeCoverConditionCoverageHelper_C88;

        // get top level scope
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((fnCurrent != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[159]++;
            // Unless we're in a direct call use the enclosing scope
            // of the function as our variable object.
            cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[543]++;
            cfw.addInvoke(ByteCode.INVOKEINTERFACE,
                          "org/mozilla/javascript/Scriptable",
                          "getParentScope",
                          "()Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[544]++;
            cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[545]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[160]++;}

        // generators are forced to have an activation record
        cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[546]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[547]++;
        cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[548]++;
        addScriptRuntimeInvoke("createFunctionActivation",
                               "(Lorg/mozilla/javascript/NativeFunction;"
                               +"Lorg/mozilla/javascript/Scriptable;"
                               +"[Ljava/lang/Object;"
                               +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[549]++;
        cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[550]++;

        // create a function object
        cfw.add(ByteCode.NEW, codegen.mainClassName);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[551]++;
        // Call function constructor
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[552]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[553]++;
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[554]++;           // load 'cx'
        cfw.addPush(scriptOrFnIndex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[555]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, codegen.mainClassName,
                      "<init>", Codegen.FUNCTION_CONSTRUCTOR_SIGNATURE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[556]++;

        generateNestedFunctionInits();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[557]++;

        // create the NativeGenerator object that we return
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[558]++;
        cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[559]++;
        cfw.addLoadConstant(maxLocals);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[560]++;
        cfw.addLoadConstant(maxStack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[561]++;
        addOptRuntimeInvoke("createNativeGenerator",
                               "(Lorg/mozilla/javascript/NativeFunction;"
                               +"Lorg/mozilla/javascript/Scriptable;"
                               +"Lorg/mozilla/javascript/Scriptable;II"
                               +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[562]++;

        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[563]++;
        cfw.stopMethod((short)(localsMax + 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[564]++;
    }

    private void generateNestedFunctionInits()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[565]++;
        int functionCount = scriptOrFn.getFunctionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[566]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[67]++;


int CodeCoverConditionCoverageHelper_C89;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((i != functionCount) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[67]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[68]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[69]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[567]++;
            OptFunctionNode ofn = OptFunctionNode.get(scriptOrFn, i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[568]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((ofn.fnode.getFunctionType()
                    == FunctionNode.FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[161]++;
                visitFunction(ofn, FunctionNode.FUNCTION_STATEMENT);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[569]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[162]++;}
        }
    }

    private void initBodyGeneration()
    {
        varRegisters = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[570]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[571]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((scriptOrFn.getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[163]++;
            fnCurrent = OptFunctionNode.get(scriptOrFn);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[572]++;
            hasVarsInRegs = !fnCurrent.fnode.requiresActivation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[573]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[574]++;
int CodeCoverConditionCoverageHelper_C92;
            if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[165]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[575]++;
                int n = fnCurrent.fnode.getParamAndVarCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[576]++;
int CodeCoverConditionCoverageHelper_C93;
                if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((n != 0) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[167]++;
                    varRegisters = new short[n];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[577]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[168]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[166]++;}
            inDirectCallFunction = fnCurrent.isTargetOfDirectCall();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[578]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[579]++;
int CodeCoverConditionCoverageHelper_C94;
            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[169]++; Codegen.badTree();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[580]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[170]++;}

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[164]++;
            fnCurrent = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[581]++;
            hasVarsInRegs = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[582]++;
            inDirectCallFunction = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[583]++;
        }

        locals = new int[MAX_LOCALS];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[584]++;

        funObjLocal = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[585]++;
        contextLocal = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[586]++;
        variableObjectLocal = 2;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[587]++;
        thisObjLocal = 3;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[588]++;
        localsMax = (short) 4;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[589]++;  // number of parms + "this"
        firstFreeLocal = 4;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[590]++;

        popvLocal = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[591]++;
        argsLocal = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[592]++;
        itsZeroArgArray = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[593]++;
        itsOneArgArray = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[594]++;
        epilogueLabel = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[595]++;
        enterAreaStartLabel = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[596]++;
        generatorStateLocal = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[597]++;
    }

    /**
     * Generate the prologue for a function or script.
     */
    private void generatePrologue()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[598]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[171]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[599]++;
            int directParameterCount = scriptOrFn.getParamCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[600]++;
int CodeCoverConditionCoverageHelper_C96;
            // 0 is reserved for function Object 'this'
            // 1 is reserved for context
            // 2 is reserved for parentScope
            // 3 is reserved for script 'this'
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((firstFreeLocal != 4) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[173]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[601]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[174]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[602]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[70]++;


int CodeCoverConditionCoverageHelper_C97;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((i != directParameterCount) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[70]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[71]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[72]++;
}
                varRegisters[i] = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[603]++;
                // 3 is 1 for Object parm and 2 for double parm
                firstFreeLocal += 3;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[604]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[605]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((fnCurrent.getParameterNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[175]++;
                // make sure that all parameters are objects
                itsForcedObjectParameters = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[606]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[607]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[73]++;


int CodeCoverConditionCoverageHelper_C99;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((i != directParameterCount) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[73]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[74]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[75]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[608]++;
                    short reg = varRegisters[i];
                    cfw.addALoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[609]++;
                    cfw.add(ByteCode.GETSTATIC,
                            "java/lang/Void",
                            "TYPE",
                            "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[610]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[611]++;
                    int isObjectLabel = cfw.acquireLabel();
                    cfw.add(ByteCode.IF_ACMPNE, isObjectLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[612]++;
                    cfw.addDLoad(reg + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[613]++;
                    addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[614]++;
                    cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[615]++;
                    cfw.markLabel(isObjectLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[616]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[176]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[172]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[617]++;
int CodeCoverConditionCoverageHelper_C100;

        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((fnCurrent != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[177]++;
            // Use the enclosing scope of the function as our variable object.
            cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[618]++;
            cfw.addInvoke(ByteCode.INVOKEINTERFACE,
                          "org/mozilla/javascript/Scriptable",
                          "getParentScope",
                          "()Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[619]++;
            cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[620]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[178]++;}

        // reserve 'args[]'
        argsLocal = firstFreeLocal++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[621]++;
        localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[622]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[623]++;
int CodeCoverConditionCoverageHelper_C101;

        // Generate Generator specific prelude
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[179]++;

            // reserve 'args[]'
            operationLocal = firstFreeLocal++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[624]++;
            localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[625]++;

            // Local 3 is a reference to a GeneratorState object. The rest
            // of codegen expects local 3 to be a reference to the thisObj.
            // So move the value in local 3 to generatorStateLocal, and load
            // the saved thisObj from the GeneratorState object.
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[626]++;
            generatorStateLocal = firstFreeLocal++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[627]++;
            localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[628]++;
            cfw.add(ByteCode.CHECKCAST, OptRuntime.GeneratorState.CLASS_NAME);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[629]++;
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[630]++;
            cfw.addAStore(generatorStateLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[631]++;
            cfw.add(ByteCode.GETFIELD,
                    OptRuntime.GeneratorState.CLASS_NAME,
                    OptRuntime.GeneratorState.thisObj_NAME,
                    OptRuntime.GeneratorState.thisObj_TYPE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[632]++;
            cfw.addAStore(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[633]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[634]++;
int CodeCoverConditionCoverageHelper_C102;

            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((epilogueLabel == -1) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[181]++;
                epilogueLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[635]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[182]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[636]++;

            List<Node> targets = ((FunctionNode)scriptOrFn).getResumptionPoints();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[637]++;
int CodeCoverConditionCoverageHelper_C103;
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((targets != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[183]++;
                // get resumption point
                generateGetGeneratorResumptionPoint();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[638]++;

                // generate dispatch table
                generatorSwitch = cfw.addTableSwitch(0,
                    targets.size() + GENERATOR_START);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[639]++;
                generateCheckForThrowOrClose(-1, false, GENERATOR_START);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[640]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[184]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[180]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[641]++;
int CodeCoverConditionCoverageHelper_C104;

        // Compile RegExp literals if this is a script. For functions
        // this is performed during instantiation in functionInit
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (8)) == 0 || true) &&
 ((fnCurrent == null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((scriptOrFn.getRegexpCount() != 0) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[185]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[642]++;
            cfw.addInvoke(ByteCode.INVOKESTATIC, codegen.mainClassName,
                          Codegen.REGEXP_INIT_METHOD_NAME,
                          Codegen.REGEXP_INIT_METHOD_SIGNATURE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[643]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[186]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[644]++;
int CodeCoverConditionCoverageHelper_C105;

        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[187]++;
            saveCurrentCodeOffset();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[645]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[188]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[646]++;
int CodeCoverConditionCoverageHelper_C106;

        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[189]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[647]++;
            // No need to create activation. Pad arguments if need be.
            int parmCount = scriptOrFn.getParamCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[648]++;
int CodeCoverConditionCoverageHelper_C107;
            if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 ((parmCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[191]++;
                // Set up args array
                // check length of arguments, pad if need be
                cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[649]++;
                cfw.add(ByteCode.ARRAYLENGTH);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[650]++;
                cfw.addPush(parmCount);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[651]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[652]++;
                int label = cfw.acquireLabel();
                cfw.add(ByteCode.IF_ICMPGE, label);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[653]++;
                cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[654]++;
                cfw.addPush(parmCount);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[655]++;
                addScriptRuntimeInvoke("padArguments",
                                       "([Ljava/lang/Object;I"
                                       +")[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[656]++;
                cfw.addAStore(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[657]++;
                cfw.markLabel(label);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[658]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[192]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[659]++;

            int paramCount = fnCurrent.fnode.getParamCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[660]++;
            int varCount = fnCurrent.fnode.getParamAndVarCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[661]++;
            boolean [] constDeclarations = fnCurrent.fnode.getParamAndVarConst();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[662]++;

            // REMIND - only need to initialize the vars that don't get a value
            // before the next call and are used in the function
            short firstUndefVar = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[663]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[76]++;


int CodeCoverConditionCoverageHelper_C108;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((i != varCount) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[76]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[77]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[78]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[664]++;
                short reg = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[665]++;
int CodeCoverConditionCoverageHelper_C109;
                if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((i < paramCount) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[193]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[666]++;
int CodeCoverConditionCoverageHelper_C110;
                    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[195]++;
                        reg = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[667]++;
                        cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[668]++;
                        cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[669]++;
                        cfw.add(ByteCode.AALOAD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[670]++;
                        cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[671]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[196]++;}

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[194]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[672]++;
int CodeCoverConditionCoverageHelper_C111; if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((fnCurrent.isNumberVar(i)) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[197]++;
                    reg = getNewWordPairLocal(constDeclarations[i]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[673]++;
                    cfw.addPush(0.0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[674]++;
                    cfw.addDStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[675]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[198]++;
                    reg = getNewWordLocal(constDeclarations[i]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[676]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[677]++;
int CodeCoverConditionCoverageHelper_C112;
                    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((firstUndefVar == -1) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[199]++;
                        Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[678]++;
                        firstUndefVar = reg;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[679]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[200]++;
                        cfw.addALoad(firstUndefVar);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[680]++;
                    }
                    cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[681]++;
                }
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[682]++;
int CodeCoverConditionCoverageHelper_C113;
                if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((reg >= 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[201]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[683]++;
int CodeCoverConditionCoverageHelper_C114;
                    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((constDeclarations[i]) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[203]++;
                        cfw.addPush(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[684]++;
                        cfw.addIStore(reg + (fnCurrent.isNumberVar(i) ? 2 : 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[685]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[204]++;}
                    varRegisters[i] = reg;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[686]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[202]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[687]++;
int CodeCoverConditionCoverageHelper_C115;

                // Add debug table entry if we're generating debug info
                if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateDebugInfo()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[205]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[688]++;
                    String name = fnCurrent.fnode.getParamOrVarName(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[689]++;
                    String type = fnCurrent.isNumberVar(i)
                                      ? "D" : "Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[690]++;
                    int startPC = cfw.getCurrentCodeOffset();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[691]++;
int CodeCoverConditionCoverageHelper_C116;
                    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((reg < 0) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[207]++;
                        reg = varRegisters[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[692]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[208]++;}
                    cfw.addVariableDescriptor(name, type, startPC, reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[693]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[206]++;}
            }

            // Skip creating activation object.
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[190]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[694]++;
int CodeCoverConditionCoverageHelper_C117;

        // skip creating activation object for the body of a generator. The
        // activation record required by a generator has already been created
        // in generateGenerator().
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[209]++;
            return;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[210]++;}


        String debugVariableName;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[695]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((fnCurrent != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[211]++;
            debugVariableName = "activation";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[696]++;
            cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[697]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[698]++;
            cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[699]++;
            addScriptRuntimeInvoke("createFunctionActivation",
                                   "(Lorg/mozilla/javascript/NativeFunction;"
                                   +"Lorg/mozilla/javascript/Scriptable;"
                                   +"[Ljava/lang/Object;"
                                   +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[700]++;
            cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[701]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[702]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[703]++;
            addScriptRuntimeInvoke("enterActivationFunction",
                                   "(Lorg/mozilla/javascript/Context;"
                                   +"Lorg/mozilla/javascript/Scriptable;"
                                   +")V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[704]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[212]++;
            debugVariableName = "global";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[705]++;
            cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[706]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[707]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[708]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[709]++;
            cfw.addPush(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[710]++; // false to indicate it is not eval script
            addScriptRuntimeInvoke("initScript",
                                   "(Lorg/mozilla/javascript/NativeFunction;"
                                   +"Lorg/mozilla/javascript/Scriptable;"
                                   +"Lorg/mozilla/javascript/Context;"
                                   +"Lorg/mozilla/javascript/Scriptable;"
                                   +"Z"
                                   +")V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[711]++;
        }

        enterAreaStartLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[712]++;
        epilogueLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[713]++;
        cfw.markLabel(enterAreaStartLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[714]++;

        generateNestedFunctionInits();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[715]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[716]++;
int CodeCoverConditionCoverageHelper_C119;

        // default is to generate debug info
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateDebugInfo()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[213]++;
            cfw.addVariableDescriptor(debugVariableName,
                    "Lorg/mozilla/javascript/Scriptable;",
                    cfw.getCurrentCodeOffset(), variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[717]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[214]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[718]++;
int CodeCoverConditionCoverageHelper_C120;

        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((fnCurrent == null) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[215]++;
            // OPT: use dataflow to prove that this assignment is dead
            popvLocal = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[719]++;
            Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[720]++;
            cfw.addAStore(popvLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[721]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[722]++;

            int linenum = scriptOrFn.getEndLineno();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[723]++;
int CodeCoverConditionCoverageHelper_C121;
            if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((linenum != -1) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[217]++;
              cfw.addLineNumberEntry((short)linenum);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[724]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[218]++;}


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[216]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[725]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((fnCurrent.itsContainsCalls0) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[219]++;
                itsZeroArgArray = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[726]++;
                cfw.add(ByteCode.GETSTATIC,
                        "org/mozilla/javascript/ScriptRuntime",
                        "emptyArgs", "[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[727]++;
                cfw.addAStore(itsZeroArgArray);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[728]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[220]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[729]++;
int CodeCoverConditionCoverageHelper_C123;
            if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((fnCurrent.itsContainsCalls1) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[221]++;
                itsOneArgArray = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[730]++;
                cfw.addPush(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[731]++;
                cfw.add(ByteCode.ANEWARRAY, "java/lang/Object");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[732]++;
                cfw.addAStore(itsOneArgArray);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[733]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[222]++;}
        }
    }

    private void generateGetGeneratorResumptionPoint()
    {
        cfw.addALoad(generatorStateLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[734]++;
        cfw.add(ByteCode.GETFIELD,
                OptRuntime.GeneratorState.CLASS_NAME,
                OptRuntime.GeneratorState.resumptionPoint_NAME,
                OptRuntime.GeneratorState.resumptionPoint_TYPE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[735]++;
    }

    private void generateSetGeneratorResumptionPoint(int nextState)
    {
        cfw.addALoad(generatorStateLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[736]++;
        cfw.addLoadConstant(nextState);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[737]++;
        cfw.add(ByteCode.PUTFIELD,
                OptRuntime.GeneratorState.CLASS_NAME,
                OptRuntime.GeneratorState.resumptionPoint_NAME,
                OptRuntime.GeneratorState.resumptionPoint_TYPE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[738]++;
    }

    private void generateGetGeneratorStackState()
    {
        cfw.addALoad(generatorStateLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[739]++;
        addOptRuntimeInvoke("getGeneratorStackState",
                    "(Ljava/lang/Object;)[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[740]++;
    }

    private void generateEpilogue()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[741]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[223]++;
            addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[742]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[224]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[743]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[225]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[744]++;
            // generate locals initialization
            Map<Node,int[]> liveLocals = ((FunctionNode)scriptOrFn).getLiveLocals();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[745]++;
int CodeCoverConditionCoverageHelper_C126;
            if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((liveLocals != null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[227]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[746]++;
                List<Node> nodes = ((FunctionNode)scriptOrFn).getResumptionPoints();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[747]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[79]++;


int CodeCoverConditionCoverageHelper_C127;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((i < nodes.size()) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[79]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[80]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[81]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[748]++;
                    Node node = nodes.get(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[749]++;
                    int[] live = liveLocals.get(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[750]++;
int CodeCoverConditionCoverageHelper_C128;
                    if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((live != null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[229]++;
                        cfw.markTableSwitchCase(generatorSwitch,
                            getNextGeneratorState(node));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[751]++;
                        generateGetGeneratorLocalsState();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[752]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[753]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[82]++;


int CodeCoverConditionCoverageHelper_C129;
                        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((j < live.length) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[82]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[83]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[84]++;
}
                                cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[754]++;
                                cfw.addLoadConstant(j);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[755]++;
                                cfw.add(ByteCode.AALOAD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[756]++;
                                cfw.addAStore(live[j]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[757]++;
                        }
                        cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[758]++;
                        cfw.add(ByteCode.GOTO, getTargetLabel(node));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[759]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[230]++;}
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[228]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[760]++;
int CodeCoverConditionCoverageHelper_C130;

            // generate dispatch tables for finally
            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((finallys != null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[231]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[761]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[85]++;


                for (Node n: finallys.keySet()) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[85]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[86]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[87]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[762]++;
int CodeCoverConditionCoverageHelper_C131;
                    if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((n.getType() == Token.FINALLY) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[233]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[763]++;
                        FinallyReturnPoint ret = finallys.get(n);
                        // the finally will jump here
                        cfw.markLabel(ret.tableLabel, (short)1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[764]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[765]++;

                        // start generating a dispatch table
                        int startSwitch = cfw.addTableSwitch(0,
                                            ret.jsrPoints.size() - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[766]++;
                        int c = 0;
                        cfw.markTableSwitchDefault(startSwitch);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[767]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[768]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[88]++;


int CodeCoverConditionCoverageHelper_C132;
                        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((i < ret.jsrPoints.size()) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[88]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[89]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[90]++;
}
                            // generate gotos back to the JSR location
                            cfw.markTableSwitchCase(startSwitch, c);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[769]++;
                            cfw.add(ByteCode.GOTO,
                                    ret.jsrPoints.get(i).intValue());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[770]++;
                            c++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[771]++;
                        }

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[234]++;}
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[232]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[226]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[772]++;
int CodeCoverConditionCoverageHelper_C133;

        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((epilogueLabel != -1) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[235]++;
            cfw.markLabel(epilogueLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[773]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[236]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[774]++;
int CodeCoverConditionCoverageHelper_C134;

        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[237]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[775]++;
            return;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[238]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[776]++;
int CodeCoverConditionCoverageHelper_C135; if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[239]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[777]++;
int CodeCoverConditionCoverageHelper_C136;
            if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((((FunctionNode)scriptOrFn).getResumptionPoints() != null) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[241]++;
                cfw.markTableSwitchDefault(generatorSwitch);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[778]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[242]++;}

            // change state for re-entry
            generateSetGeneratorResumptionPoint(GENERATOR_TERMINATE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[779]++;

            // throw StopIteration
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[780]++;
            addOptRuntimeInvoke("throwStopIteration",
                    "(Ljava/lang/Object;)V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[781]++;

            Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[782]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[783]++;


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[240]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[784]++;
int CodeCoverConditionCoverageHelper_C137; if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((fnCurrent == null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[243]++;
            cfw.addALoad(popvLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[785]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[786]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[244]++;
            generateActivationExit();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[787]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[788]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[789]++;

            // Generate catch block to catch all and rethrow to call exit code
            // under exception propagation as well.

            int finallyHandler = cfw.acquireLabel();
            cfw.markHandler(finallyHandler);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[790]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[791]++;
            short exceptionObject = getNewWordLocal();
            cfw.addAStore(exceptionObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[792]++;

            // Duplicate generateActivationExit() in the catch block since it
            // takes less space then full-featured ByteCode.JSR/ByteCode.RET
            generateActivationExit();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[793]++;

            cfw.addALoad(exceptionObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[794]++;
            releaseWordLocal(exceptionObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[795]++;
            // rethrow
            cfw.add(ByteCode.ATHROW);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[796]++;

            // mark the handler
            cfw.addExceptionHandler(enterAreaStartLabel, epilogueLabel,
                                    finallyHandler, null);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[797]++; // catch any
        }
}
}
    }

    private void generateGetGeneratorLocalsState() {
        cfw.addALoad(generatorStateLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[798]++;
        addOptRuntimeInvoke("getGeneratorLocalsState",
                                "(Ljava/lang/Object;)[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[799]++;
    }

    private void generateActivationExit()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[800]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (8)) == 0 || true) &&
 ((fnCurrent == null) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[245]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[246]++;}
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[801]++;
        addScriptRuntimeInvoke("exitActivationFunction",
                               "(Lorg/mozilla/javascript/Context;)V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[802]++;
    }

    private void generateStatement(Node node)
    {
        updateLineNumber(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[803]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[804]++;
        int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[805]++;
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[806]++;
        switch (type) {
              case Token.LOOP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[247]++;
              case Token.LABEL:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[248]++;
              case Token.WITH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[249]++;
              case Token.SCRIPT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[250]++;
              case Token.BLOCK:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[251]++;
              case Token.EMPTY:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[252]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[807]++;
int CodeCoverConditionCoverageHelper_C139;
                // no-ops.
                if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[253]++;
                    // Need to add instruction count even for no-ops to catch
                    // cases like while (1) {}
                    addInstructionCount(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[808]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[254]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[809]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[91]++;


int CodeCoverConditionCoverageHelper_C140;
                while ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[91]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[92]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[93]++;
}
                    generateStatement(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[810]++;
                    child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[811]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[812]++;
                break;

              case Token.LOCAL_BLOCK:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[255]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[813]++;
                boolean prevLocal = inLocalBlock;
                inLocalBlock = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[814]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[815]++;
                int local = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[816]++;
int CodeCoverConditionCoverageHelper_C141;
                if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[256]++;
                    cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[817]++;
                    cfw.addAStore(local);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[818]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[257]++;}
                node.putIntProp(Node.LOCAL_PROP, local);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[819]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[820]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[94]++;


int CodeCoverConditionCoverageHelper_C142;
                while ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[94]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[95]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[96]++;
}
                    generateStatement(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[821]++;
                    child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[822]++;
                }
                releaseWordLocal((short)local);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[823]++;
                node.removeProp(Node.LOCAL_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[824]++;
                inLocalBlock = prevLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[825]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[826]++;
                break;
              }

              case Token.FUNCTION:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[258]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[827]++;
                int fnIndex = node.getExistingIntProp(Node.FUNCTION_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[828]++;
                OptFunctionNode ofn = OptFunctionNode.get(scriptOrFn, fnIndex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[829]++;
                int t = ofn.fnode.getFunctionType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[830]++;
int CodeCoverConditionCoverageHelper_C143;
                if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((t == FunctionNode.FUNCTION_EXPRESSION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[259]++;
                    visitFunction(ofn, t);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[831]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[260]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[832]++;
int CodeCoverConditionCoverageHelper_C144;
                    if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((t != FunctionNode.FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[261]++;
                        throw Codegen.badTree();

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[262]++;}
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[833]++;
                break;
              }

              case Token.TRY:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[263]++;
                visitTryCatchFinally((Jump)node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[834]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[835]++;
                break;

              case Token.CATCH_SCOPE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[264]++;
                {
                    // nothing stays on the stack on entry into a catch scope
                    cfw.setStackTop((short) 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[836]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[837]++;

                    int local = getLocalBlockRegister(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[838]++;
                    int scopeIndex
                        = node.getExistingIntProp(Node.CATCH_SCOPE_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[839]++;

                    String name = child.getString(); // name of exception
                    child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[840]++;
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[841]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[842]++;
int CodeCoverConditionCoverageHelper_C145; // load expression object
                    if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((scopeIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[265]++;
                        cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[843]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[266]++;
                        // Load previous catch scope object
                        cfw.addALoad(local);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[844]++;
                    }
                    cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[845]++;
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[846]++;
                    cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[847]++;

                    addScriptRuntimeInvoke(
                        "newCatchScope",
                        "(Ljava/lang/Throwable;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"Ljava/lang/String;"
                        +"Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[848]++;
                    cfw.addAStore(local);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[849]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[850]++;
                break;

              case Token.THROW:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[267]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[851]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[852]++;
int CodeCoverConditionCoverageHelper_C146;
                if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[268]++;
                    addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[853]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[269]++;}
                generateThrowJavaScriptException();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[854]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[855]++;
                break;

              case Token.RETHROW:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[270]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[856]++;
int CodeCoverConditionCoverageHelper_C147;
                if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[271]++;
                    addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[857]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[272]++;}
                cfw.addALoad(getLocalBlockRegister(node));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[858]++;
                cfw.add(ByteCode.ATHROW);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[859]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[860]++;
                break;

              case Token.RETURN_RESULT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[273]++;
              case Token.RETURN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[274]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[861]++;
int CodeCoverConditionCoverageHelper_C148;
                if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[275]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[862]++;
int CodeCoverConditionCoverageHelper_C149;
                    if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[277]++;
                        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[863]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[278]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[864]++;
int CodeCoverConditionCoverageHelper_C150; if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((type == Token.RETURN) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[279]++;
                        Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[865]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[280]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[866]++;
int CodeCoverConditionCoverageHelper_C151;
                        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((popvLocal < 0) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[281]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[282]++;}
                        cfw.addALoad(popvLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[867]++;
                    }
}

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[276]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[868]++;
int CodeCoverConditionCoverageHelper_C152;
                if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[283]++;
                    addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[869]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[284]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[870]++;
int CodeCoverConditionCoverageHelper_C153;
                if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((epilogueLabel == -1) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[285]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[871]++;
int CodeCoverConditionCoverageHelper_C154;
                    if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[287]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[288]++;}
                    epilogueLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[872]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[286]++;}
                cfw.add(ByteCode.GOTO, epilogueLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[873]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[874]++;
                break;

              case Token.SWITCH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[289]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[875]++;
int CodeCoverConditionCoverageHelper_C155;
                if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[290]++;
                    addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[876]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[291]++;}
                visitSwitch((Jump)node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[877]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[878]++;
                break;

              case Token.ENTERWITH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[292]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[879]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[880]++;
                cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[881]++;
                addScriptRuntimeInvoke(
                    "enterWith",
                    "(Ljava/lang/Object;"
                    +"Lorg/mozilla/javascript/Context;"
                    +"Lorg/mozilla/javascript/Scriptable;"
                    +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[882]++;
                cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[883]++;
                incReferenceWordLocal(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[884]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[885]++;
                break;

              case Token.LEAVEWITH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[293]++;
                cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[886]++;
                addScriptRuntimeInvoke(
                    "leaveWith",
                    "(Lorg/mozilla/javascript/Scriptable;"
                    +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[887]++;
                cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[888]++;
                decReferenceWordLocal(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[889]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[890]++;
                break;

              case Token.ENUM_INIT_KEYS:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[294]++;
              case Token.ENUM_INIT_VALUES:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[295]++;
              case Token.ENUM_INIT_ARRAY:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[296]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[891]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[892]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[893]++;
                int enumType = type == Token.ENUM_INIT_KEYS
                                   ? ScriptRuntime.ENUMERATE_KEYS :
                               type == Token.ENUM_INIT_VALUES
                                   ? ScriptRuntime.ENUMERATE_VALUES :
                               ScriptRuntime.ENUMERATE_ARRAY;
                cfw.addPush(enumType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[894]++;
                addScriptRuntimeInvoke("enumInit",
                                       "(Ljava/lang/Object;"
                                       +"Lorg/mozilla/javascript/Context;"
                                       +"I"
                                       +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[895]++;
                cfw.addAStore(getLocalBlockRegister(node));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[896]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[897]++;
                break;

              case Token.EXPR_VOID:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[297]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[898]++;
int CodeCoverConditionCoverageHelper_C156;
                if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((child.getType() == Token.SETVAR) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[298]++;
                    /* special case this so as to avoid unnecessary
                    load's & pop's */
                    visitSetVar(child, child.getFirstChild(), false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[899]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[299]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[900]++;
int CodeCoverConditionCoverageHelper_C157; if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((child.getType() == Token.SETCONSTVAR) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[300]++;
                    /* special case this so as to avoid unnecessary
                    load's & pop's */
                    visitSetConstVar(child, child.getFirstChild(), false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[901]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[301]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[902]++;
int CodeCoverConditionCoverageHelper_C158; if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((child.getType() == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[302]++;
                    generateYieldPoint(child, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[903]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[303]++;
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[904]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[905]++;
int CodeCoverConditionCoverageHelper_C159;
                    if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[304]++;
                        cfw.add(ByteCode.POP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[906]++;
}
                    else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[305]++;
                        cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[907]++;
}
                }
}
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[908]++;
                break;

              case Token.EXPR_RESULT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[306]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[909]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[910]++;
int CodeCoverConditionCoverageHelper_C160;
                if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((popvLocal < 0) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[307]++;
                    popvLocal = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[911]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[308]++;}
                cfw.addAStore(popvLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[912]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[913]++;
                break;

              case Token.TARGET:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[309]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[914]++;
int CodeCoverConditionCoverageHelper_C161;
                    if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[310]++;
                        addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[915]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[311]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[916]++;
                    int label = getTargetLabel(node);
                    cfw.markLabel(label);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[917]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[918]++;
int CodeCoverConditionCoverageHelper_C162;
                    if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[312]++;
                        saveCurrentCodeOffset();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[919]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[313]++;}
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[920]++;
                break;

              case Token.JSR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[314]++;
              case Token.GOTO:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[315]++;
              case Token.IFEQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[316]++;
              case Token.IFNE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[317]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[921]++;
int CodeCoverConditionCoverageHelper_C163;
                if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[318]++;
                    addInstructionCount();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[922]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[319]++;}
                visitGoto((Jump)node, type, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[923]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[924]++;
                break;

              case Token.FINALLY:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[320]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[925]++;
int CodeCoverConditionCoverageHelper_C164;
                    // This is the non-exception case for a finally block. In
                    // other words, since we inline finally blocks wherever
                    // jsr was previously used, and jsr is only used when the
                    // function is not a generator, we don't need to generate
                    // this case if the function isn't a generator.
                    if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[321]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[926]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[322]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[927]++;
int CodeCoverConditionCoverageHelper_C165;

                    if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((compilerEnv.isGenerateObserverCount()) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[323]++;
                        saveCurrentCodeOffset();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[928]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[324]++;}
                    // there is exactly one value on the stack when enterring
                    // finally blocks: the return address (or its int encoding)
                    cfw.setStackTop((short)1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[929]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[930]++;

                    // Save return address in a new local
                    int finallyRegister = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[931]++;

                    int finallyStart = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[932]++;
                    int finallyEnd = cfw.acquireLabel();
                    cfw.markLabel(finallyStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[933]++;

                    generateIntegerWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[934]++;
                    cfw.addAStore(finallyRegister);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[935]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[936]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[97]++;


int CodeCoverConditionCoverageHelper_C166;

                    while ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[97]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[98]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[99]++;
}
                        generateStatement(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[937]++;
                        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[938]++;
                    }

                    cfw.addALoad(finallyRegister);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[939]++;
                    cfw.add(ByteCode.CHECKCAST, "java/lang/Integer");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[940]++;
                    generateIntegerUnwrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[941]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[942]++;
                    FinallyReturnPoint ret = finallys.get(node);
                    ret.tableLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[943]++;
                    cfw.add(ByteCode.GOTO, ret.tableLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[944]++;

                    releaseWordLocal((short)finallyRegister);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[945]++;
                    cfw.markLabel(finallyEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[946]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[947]++;
                break;

              case Token.DEBUGGER:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[325]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[948]++;
                break;

              default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[326]++;
                throw Codegen.badTree();
        }

    }

    private void generateIntegerWrap()
    {
        cfw.addInvoke(ByteCode.INVOKESTATIC, "java/lang/Integer", "valueOf",
                "(I)Ljava/lang/Integer;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[949]++;
    }


    private void generateIntegerUnwrap()
    {
        cfw.addInvoke(ByteCode.INVOKEVIRTUAL, "java/lang/Integer",
                "intValue", "()I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[950]++;
    }


    private void generateThrowJavaScriptException()
    {
        cfw.add(ByteCode.NEW,
                        "org/mozilla/javascript/JavaScriptException");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[951]++;
        cfw.add(ByteCode.DUP_X1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[952]++;
        cfw.add(ByteCode.SWAP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[953]++;
        cfw.addPush(scriptOrFn.getSourceName());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[954]++;
        cfw.addPush(itsLineNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[955]++;
        cfw.addInvoke(
                    ByteCode.INVOKESPECIAL,
                    "org/mozilla/javascript/JavaScriptException",
                    "<init>",
                    "(Ljava/lang/Object;Ljava/lang/String;I)V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[956]++;
        cfw.add(ByteCode.ATHROW);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[957]++;
    }

    private int getNextGeneratorState(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[958]++;
        int nodeIndex = ((FunctionNode)scriptOrFn).getResumptionPoints()
                .indexOf(node);
        return nodeIndex + GENERATOR_YIELD_START;
    }

    private void generateExpression(Node node, Node parent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[959]++;
        int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[960]++;
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[961]++;
        switch (type) {
              case Token.USE_STACK:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[327]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[962]++;
                break;

              case Token.FUNCTION:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[328]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[963]++;
int CodeCoverConditionCoverageHelper_C167;
                if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (8)) == 0 || true) &&
 ((fnCurrent != null) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((parent.getType() != Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[329]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[964]++;
                    int fnIndex = node.getExistingIntProp(Node.FUNCTION_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[965]++;
                    OptFunctionNode ofn = OptFunctionNode.get(scriptOrFn,
                                                             fnIndex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[966]++;
                    int t = ofn.fnode.getFunctionType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[967]++;
int CodeCoverConditionCoverageHelper_C168;
                    if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((t != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[331]++;
                        throw Codegen.badTree();

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[332]++;}
                    visitFunction(ofn, t);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[968]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[330]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[969]++;
                break;

              case Token.NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[333]++;
                {
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[970]++;
                    cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[971]++;
                    cfw.addPush(node.getString());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[972]++;
                    addScriptRuntimeInvoke(
                        "name",
                        "(Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"Ljava/lang/String;"
                        +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[973]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[974]++;
                break;

              case Token.CALL:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[334]++;
              case Token.NEW:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[335]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[975]++;
                    int specialType = node.getIntProp(Node.SPECIALCALL_PROP,
                                                      Node.NON_SPECIALCALL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[976]++;
int CodeCoverConditionCoverageHelper_C169;
                    if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((specialType == Node.NON_SPECIALCALL) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[336]++;
                        OptFunctionNode target;
                        target = (OptFunctionNode)node.getProp(
                                     Node.DIRECTCALL_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[977]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[978]++;
int CodeCoverConditionCoverageHelper_C170;

                        if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[338]++;
                            visitOptimizedCall(node, target, type, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[979]++;

                        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[339]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[980]++;
int CodeCoverConditionCoverageHelper_C171; if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((type == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[340]++;
                            visitStandardCall(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[981]++;

                        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[341]++;
                            visitStandardNew(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[982]++;
                        }
}

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[337]++;
                        visitSpecialCall(node, type, specialType, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[983]++;
                    }
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[984]++;
                break;

              case Token.REF_CALL:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[342]++;
                generateFunctionAndThisObj(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[985]++;
                // stack: ... functionObj thisObj
                child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[986]++;
                generateCallArgArray(node, child, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[987]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[988]++;
                addScriptRuntimeInvoke(
                    "callRef",
                    "(Lorg/mozilla/javascript/Callable;"
                    +"Lorg/mozilla/javascript/Scriptable;"
                    +"[Ljava/lang/Object;"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Lorg/mozilla/javascript/Ref;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[989]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[990]++;
                break;

              case Token.NUMBER:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[343]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[991]++;
                    double num = node.getDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[992]++;
int CodeCoverConditionCoverageHelper_C172;
                    if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[344]++;
                        cfw.addPush(num);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[993]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[345]++;
                        codegen.pushNumberAsObject(cfw, num);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[994]++;
                    }
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[995]++;
                break;

              case Token.STRING:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[346]++;
                cfw.addPush(node.getString());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[996]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[997]++;
                break;

              case Token.THIS:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[347]++;
                cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[998]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[999]++;
                break;

              case Token.THISFN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[348]++;
                cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1000]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1001]++;
                break;

              case Token.NULL:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[349]++;
                cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1002]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1003]++;
                break;

              case Token.TRUE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[350]++;
                cfw.add(ByteCode.GETSTATIC, "java/lang/Boolean",
                        "TRUE", "Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1004]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1005]++;
                break;

              case Token.FALSE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[351]++;
                cfw.add(ByteCode.GETSTATIC, "java/lang/Boolean",
                        "FALSE", "Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1006]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1007]++;
                break;

              case Token.REGEXP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[352]++;
                {
                    // Create a new wrapper around precompiled regexp
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1008]++;
                    cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1009]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1010]++;
                    int i = node.getExistingIntProp(Node.REGEXP_PROP);
                    cfw.add(ByteCode.GETSTATIC, codegen.mainClassName,
                            codegen.getCompiledRegexpName(scriptOrFn, i),
                            "Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1011]++;
                    cfw.addInvoke(ByteCode.INVOKESTATIC,
                                  "org/mozilla/javascript/ScriptRuntime",
                                  "wrapRegExp",
                                  "(Lorg/mozilla/javascript/Context;"
                                  +"Lorg/mozilla/javascript/Scriptable;"
                                  +"Ljava/lang/Object;"
                                  +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1012]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1013]++;
                break;

              case Token.COMMA:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[353]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1014]++;
                Node next = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1015]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[100]++;


int CodeCoverConditionCoverageHelper_C173;
                while ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[100]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[101]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[102]++;
}
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1016]++;
                    cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1017]++;
                    child = next;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1018]++;
                    next = next.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1019]++;
                }
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1020]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1021]++;
                break;
              }

              case Token.ENUM_NEXT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[354]++;
              case Token.ENUM_ID:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[355]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1022]++;
                int local = getLocalBlockRegister(node);
                cfw.addALoad(local);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1023]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1024]++;
int CodeCoverConditionCoverageHelper_C174;
                if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((type == Token.ENUM_NEXT) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[356]++;
                    addScriptRuntimeInvoke(
                        "enumNext", "(Ljava/lang/Object;)Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1025]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[357]++;
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1026]++;
                    addScriptRuntimeInvoke("enumId",
                                           "(Ljava/lang/Object;"
                                           +"Lorg/mozilla/javascript/Context;"
                                           +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1027]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1028]++;
                break;
              }

              case Token.ARRAYLIT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[358]++;
                visitArrayLiteral(node, child, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1029]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1030]++;
                break;

              case Token.OBJECTLIT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[359]++;
                visitObjectLiteral(node, child, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1031]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1032]++;
                break;

              case Token.NOT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[360]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1033]++;
                int trueTarget = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1034]++;
                int falseTarget = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1035]++;
                int beyond = cfw.acquireLabel();
                generateIfJump(child, node, trueTarget, falseTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1036]++;

                cfw.markLabel(trueTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1037]++;
                cfw.add(ByteCode.GETSTATIC, "java/lang/Boolean",
                                        "FALSE", "Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1038]++;
                cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1039]++;
                cfw.markLabel(falseTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1040]++;
                cfw.add(ByteCode.GETSTATIC, "java/lang/Boolean",
                                        "TRUE", "Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1041]++;
                cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1042]++;
                cfw.adjustStackTop(-1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1043]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1044]++;
                break;
              }

              case Token.BITNOT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[361]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1045]++;
                addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1046]++;
                cfw.addPush(-1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1047]++;         // implement ~a as (a ^ -1)
                cfw.add(ByteCode.IXOR);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1048]++;
                cfw.add(ByteCode.I2D);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1049]++;
                addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1050]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1051]++;
                break;

              case Token.VOID:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[362]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1052]++;
                cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1053]++;
                Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1054]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1055]++;
                break;

              case Token.TYPEOF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[363]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1056]++;
                addScriptRuntimeInvoke("typeof",
                                       "(Ljava/lang/Object;"
                                       +")Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1057]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1058]++;
                break;

              case Token.TYPEOFNAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[364]++;
                visitTypeofname(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1059]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1060]++;
                break;

              case Token.INC:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[365]++;
              case Token.DEC:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[366]++;
                visitIncDec(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1061]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1062]++;
                break;

              case Token.OR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[367]++;
              case Token.AND:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[368]++; {
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1063]++;
                    cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1064]++;
                    addScriptRuntimeInvoke("toBoolean",
                                           "(Ljava/lang/Object;)Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1065]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1066]++;
                    int falseTarget = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1067]++;
int CodeCoverConditionCoverageHelper_C175;
                    if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((type == Token.AND) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[369]++;
                        cfw.add(ByteCode.IFEQ, falseTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1068]++;
}
                    else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[370]++;
                        cfw.add(ByteCode.IFNE, falseTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1069]++;
}
                    cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1070]++;
                    generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1071]++;
                    cfw.markLabel(falseTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1072]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1073]++;
                break;

              case Token.HOOK :
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[371]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1074]++;
                    Node ifThen = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1075]++;
                    Node ifElse = ifThen.getNext();
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1076]++;
                    addScriptRuntimeInvoke("toBoolean",
                                           "(Ljava/lang/Object;)Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1077]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1078]++;
                    int elseTarget = cfw.acquireLabel();
                    cfw.add(ByteCode.IFEQ, elseTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1079]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1080]++;
                    short stack = cfw.getStackTop();
                    generateExpression(ifThen, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1081]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1082]++;
                    int afterHook = cfw.acquireLabel();
                    cfw.add(ByteCode.GOTO, afterHook);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1083]++;
                    cfw.markLabel(elseTarget, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1084]++;
                    generateExpression(ifElse, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1085]++;
                    cfw.markLabel(afterHook);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1086]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1087]++;
                break;

              case Token.ADD:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[372]++; {
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1088]++;
                    generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1089]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1090]++;
                    switch (node.getIntProp(Node.ISNUMBER_PROP, -1)) {
                      case Node.BOTH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[373]++;
                        cfw.add(ByteCode.DADD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1091]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1092]++;
                        break;
                      case Node.LEFT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[374]++;
                        addOptRuntimeInvoke("add",
                            "(DLjava/lang/Object;)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1093]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1094]++;
                        break;
                      case Node.RIGHT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[375]++;
                        addOptRuntimeInvoke("add",
                            "(Ljava/lang/Object;D)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1095]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1096]++;
                        break;
                      default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[376]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1097]++;
int CodeCoverConditionCoverageHelper_C176;
                        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((child.getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[377]++;
                            addScriptRuntimeInvoke("add",
                                "(Ljava/lang/CharSequence;"
                                +"Ljava/lang/Object;"
                                +")Ljava/lang/CharSequence;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1098]++;

                        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[378]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1099]++;
int CodeCoverConditionCoverageHelper_C177; if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((child.getNext().getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[379]++;
                            addScriptRuntimeInvoke("add",
                                "(Ljava/lang/Object;"
                                +"Ljava/lang/CharSequence;"
                                +")Ljava/lang/CharSequence;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1100]++;

                        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[380]++;
                            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1101]++;
                            addScriptRuntimeInvoke("add",
                                "(Ljava/lang/Object;"
                                +"Ljava/lang/Object;"
                                +"Lorg/mozilla/javascript/Context;"
                                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1102]++;
                        }
}
                    }
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1103]++;
                break;

              case Token.MUL:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[381]++;
                visitArithmetic(node, ByteCode.DMUL, child, parent);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1104]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1105]++;
                break;

              case Token.SUB:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[382]++;
                visitArithmetic(node, ByteCode.DSUB, child, parent);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1106]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1107]++;
                break;

              case Token.DIV:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[383]++;
              case Token.MOD:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[384]++;
                visitArithmetic(node, type == Token.DIV
                                      ? ByteCode.DDIV
                                      : ByteCode.DREM, child, parent);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1108]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1109]++;
                break;

              case Token.BITOR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[385]++;
              case Token.BITXOR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[386]++;
              case Token.BITAND:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[387]++;
              case Token.LSH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[388]++;
              case Token.RSH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[389]++;
              case Token.URSH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[390]++;
                visitBitOp(node, type, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1110]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1111]++;
                break;

              case Token.POS:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[391]++;
              case Token.NEG:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[392]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1112]++;
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1113]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1114]++;
int CodeCoverConditionCoverageHelper_C178;
                if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((type == Token.NEG) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[393]++;
                    cfw.add(ByteCode.DNEG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1115]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[394]++;}
                addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1116]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1117]++;
                break;

              case Token.TO_DOUBLE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[395]++;
                // cnvt to double (not Double)
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1118]++;
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1119]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1120]++;
                break;

              case Token.TO_OBJECT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[396]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1121]++;
                // convert from double
                int prop = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1122]++;
int CodeCoverConditionCoverageHelper_C179;
                if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((child.getType() == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[397]++;
                    prop = child.getIntProp(Node.ISNUMBER_PROP, -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1123]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[398]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1124]++;
int CodeCoverConditionCoverageHelper_C180;
                if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((prop != -1) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[399]++;
                    child.removeProp(Node.ISNUMBER_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1125]++;
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1126]++;
                    child.putIntProp(Node.ISNUMBER_PROP, prop);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1127]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[400]++;
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1128]++;
                    addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1129]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1130]++;
                break;
              }

              case Token.IN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[401]++;
              case Token.INSTANCEOF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[402]++;
              case Token.LE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[403]++;
              case Token.LT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[404]++;
              case Token.GE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[405]++;
              case Token.GT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[406]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1131]++;
                int trueGOTO = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1132]++;
                int falseGOTO = cfw.acquireLabel();
                visitIfJumpRelOp(node, child, trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1133]++;
                addJumpedBooleanWrap(trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1134]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1135]++;
                break;
              }

              case Token.EQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[407]++;
              case Token.NE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[408]++;
              case Token.SHEQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[409]++;
              case Token.SHNE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[410]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1136]++;
                int trueGOTO = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1137]++;
                int falseGOTO = cfw.acquireLabel();
                visitIfJumpEqOp(node, child, trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1138]++;
                addJumpedBooleanWrap(trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1139]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1140]++;
                break;
              }

              case Token.GETPROP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[411]++;
              case Token.GETPROPNOWARN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[412]++;
                visitGetProp(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1141]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1142]++;
                break;

              case Token.GETELEM:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[413]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1143]++; // object
                generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1144]++;  // id
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1145]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1146]++;
int CodeCoverConditionCoverageHelper_C181;
                if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[414]++;
                    addScriptRuntimeInvoke(
                        "getObjectIndex",
                        "(Ljava/lang/Object;D"
                        +"Lorg/mozilla/javascript/Context;"
                        +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1147]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[415]++;
                    cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1148]++;
                	addScriptRuntimeInvoke(
                        "getObjectElem",
                        "(Ljava/lang/Object;"
                        +"Ljava/lang/Object;"
                        +"Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1149]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1150]++;
                break;

              case Token.GET_REF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[416]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1151]++; // reference
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1152]++;
                addScriptRuntimeInvoke(
                    "refGet",
                    "(Lorg/mozilla/javascript/Ref;"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1153]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1154]++;
                break;

              case Token.GETVAR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[417]++;
                visitGetVar(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1155]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1156]++;
                break;

              case Token.SETVAR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[418]++;
                visitSetVar(node, child, true);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1157]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1158]++;
                break;

              case Token.SETNAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[419]++;
                visitSetName(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1159]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1160]++;
                break;

              case Token.STRICT_SETNAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[420]++;
                  visitStrictSetName(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1161]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1162]++;
                  break;

              case Token.SETCONST:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[421]++;
                visitSetConst(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1163]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1164]++;
                break;

              case Token.SETCONSTVAR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[422]++;
                visitSetConstVar(node, child, true);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1165]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1166]++;
                break;

              case Token.SETPROP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[423]++;
              case Token.SETPROP_OP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[424]++;
                visitSetProp(type, node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1167]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1168]++;
                break;

              case Token.SETELEM:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[425]++;
              case Token.SETELEM_OP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[426]++;
                visitSetElem(type, node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1169]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1170]++;
                break;

              case Token.SET_REF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[427]++;
              case Token.SET_REF_OP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[428]++;
                {
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1171]++;
                    child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1172]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1173]++;
int CodeCoverConditionCoverageHelper_C182;
                    if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((type == Token.SET_REF_OP) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[429]++;
                        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1174]++;
                        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1175]++;
                        addScriptRuntimeInvoke(
                            "refGet",
                            "(Lorg/mozilla/javascript/Ref;"
                            +"Lorg/mozilla/javascript/Context;"
                            +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1176]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[430]++;}
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1177]++;
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1178]++;
                    addScriptRuntimeInvoke(
                        "refSet",
                        "(Lorg/mozilla/javascript/Ref;"
                        +"Ljava/lang/Object;"
                        +"Lorg/mozilla/javascript/Context;"
                        +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1179]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1180]++;
                break;

              case Token.DEL_REF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[431]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1181]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1182]++;
                addScriptRuntimeInvoke("refDel",
                                       "(Lorg/mozilla/javascript/Ref;"
                                       +"Lorg/mozilla/javascript/Context;"
                                       +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1183]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1184]++;
                break;

              case Token.DELPROP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[432]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1185]++;
                boolean isName = child.getType() == Token.BINDNAME;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1186]++;
                child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1187]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1188]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1189]++;
                cfw.addPush(isName);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1190]++;
                addScriptRuntimeInvoke("delete",
                                       "(Ljava/lang/Object;"
                                       +"Ljava/lang/Object;"
                                       +"Lorg/mozilla/javascript/Context;"
                                       +"Z)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1191]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1192]++;
                break;

              case Token.BINDNAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[433]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1193]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[103]++;


int CodeCoverConditionCoverageHelper_C183;
                    while ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[103]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[104]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[105]++;
}
                        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1194]++;
                        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1195]++;
                    }
                    // Generate code for "ScriptRuntime.bind(varObj, "s")"
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1196]++;
                    cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1197]++;
                    cfw.addPush(node.getString());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1198]++;
                    addScriptRuntimeInvoke(
                        "bind",
                        "(Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"Ljava/lang/String;"
                        +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1199]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1200]++;
                break;

              case Token.LOCAL_LOAD:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[434]++;
                cfw.addALoad(getLocalBlockRegister(node));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1201]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1202]++;
                break;

              case Token.REF_SPECIAL:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[435]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1203]++;
                    String special = (String)node.getProp(Node.NAME_PROP);
                    generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1204]++;
                    cfw.addPush(special);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1205]++;
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1206]++;
                    addScriptRuntimeInvoke(
                        "specialRef",
                        "(Ljava/lang/Object;"
                        +"Ljava/lang/String;"
                        +"Lorg/mozilla/javascript/Context;"
                        +")Lorg/mozilla/javascript/Ref;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1207]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1208]++;
                break;

              case Token.REF_MEMBER:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[436]++;
              case Token.REF_NS_MEMBER:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[437]++;
              case Token.REF_NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[438]++;
              case Token.REF_NS_NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[439]++;
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1209]++;
                    int memberTypeFlags
                        = node.getIntProp(Node.MEMBER_TYPE_PROP, 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1210]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[106]++;


int CodeCoverConditionCoverageHelper_C184;
                    // generate possible target, possible namespace and member
                    do {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[106]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[107]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[108]++;
}
                        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1211]++;
                        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1212]++;
                    } while ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false));
                    cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1213]++;
                    String methodName, signature;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1214]++;
                    switch (type) {
                      case Token.REF_MEMBER:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[440]++;
                        methodName = "memberRef";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1215]++;
                        signature = "(Ljava/lang/Object;"
                                    +"Ljava/lang/Object;"
                                    +"Lorg/mozilla/javascript/Context;"
                                    +"I"
                                    +")Lorg/mozilla/javascript/Ref;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1216]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1217]++;
                        break;
                      case Token.REF_NS_MEMBER:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[441]++;
                        methodName = "memberRef";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1218]++;
                        signature = "(Ljava/lang/Object;"
                                    +"Ljava/lang/Object;"
                                    +"Ljava/lang/Object;"
                                    +"Lorg/mozilla/javascript/Context;"
                                    +"I"
                                    +")Lorg/mozilla/javascript/Ref;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1219]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1220]++;
                        break;
                      case Token.REF_NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[442]++;
                        methodName = "nameRef";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1221]++;
                        signature = "(Ljava/lang/Object;"
                                    +"Lorg/mozilla/javascript/Context;"
                                    +"Lorg/mozilla/javascript/Scriptable;"
                                    +"I"
                                    +")Lorg/mozilla/javascript/Ref;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1222]++;
                        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1223]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1224]++;
                        break;
                      case Token.REF_NS_NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[443]++;
                        methodName = "nameRef";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1225]++;
                        signature = "(Ljava/lang/Object;"
                                    +"Ljava/lang/Object;"
                                    +"Lorg/mozilla/javascript/Context;"
                                    +"Lorg/mozilla/javascript/Scriptable;"
                                    +"I"
                                    +")Lorg/mozilla/javascript/Ref;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1226]++;
                        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1227]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1228]++;
                        break;
                      default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[444]++;
                        throw Kit.codeBug();
                    }
                    cfw.addPush(memberTypeFlags);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1229]++;
                    addScriptRuntimeInvoke(methodName, signature);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1230]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1231]++;
                break;

              case Token.DOTQUERY:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[445]++;
                visitDotQuery(node, child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1232]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1233]++;
                break;

              case Token.ESCXMLATTR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[446]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1234]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1235]++;
                addScriptRuntimeInvoke("escapeAttributeValue",
                                       "(Ljava/lang/Object;"
                                       +"Lorg/mozilla/javascript/Context;"
                                       +")Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1236]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1237]++;
                break;

              case Token.ESCXMLTEXT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[447]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1238]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1239]++;
                addScriptRuntimeInvoke("escapeTextValue",
                                       "(Ljava/lang/Object;"
                                       +"Lorg/mozilla/javascript/Context;"
                                       +")Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1240]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1241]++;
                break;

              case Token.DEFAULTNAMESPACE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[448]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1242]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1243]++;
                addScriptRuntimeInvoke("setDefaultNamespace",
                                       "(Ljava/lang/Object;"
                                       +"Lorg/mozilla/javascript/Context;"
                                       +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1244]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1245]++;
                break;

              case Token.YIELD:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[449]++;
                generateYieldPoint(node, true);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1246]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1247]++;
                break;

              case Token.WITHEXPR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[450]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1248]++;
                Node enterWith = child;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1249]++;
                Node with = enterWith.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1250]++;
                Node leaveWith = with.getNext();
                generateStatement(enterWith);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1251]++;
                generateExpression(with.getFirstChild(), with);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1252]++;
                generateStatement(leaveWith);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1253]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1254]++;
                break;
              }

              case Token.ARRAYCOMP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[451]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1255]++;
                Node initStmt = child;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1256]++;
                Node expr = child.getNext();
                generateStatement(initStmt);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1257]++;
                generateExpression(expr, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1258]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1259]++;
                break;
              }

              default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[452]++;
                throw new RuntimeException("Unexpected node type "+type);
        }

    }

    private void generateYieldPoint(Node node, boolean exprContext) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1260]++;
        // save stack state
        int top = cfw.getStackTop();
        maxStack = maxStack > top ? maxStack : top;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1261]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1262]++;
int CodeCoverConditionCoverageHelper_C185;
        if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((cfw.getStackTop() != 0) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[453]++;
            generateGetGeneratorStackState();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1263]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1264]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[109]++;


int CodeCoverConditionCoverageHelper_C186;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((i < top) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[109]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[110]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[111]++;
}
                cfw.add(ByteCode.DUP_X1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1265]++;
                cfw.add(ByteCode.SWAP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1266]++;
                cfw.addLoadConstant(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1267]++;
                cfw.add(ByteCode.SWAP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1268]++;
                cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1269]++;
            }
            // pop the array object
            cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1270]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[454]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1271]++;

        // generate the yield argument
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1272]++;
int CodeCoverConditionCoverageHelper_C187;
        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[455]++;
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1273]++;
}
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[456]++;
            Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1274]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1275]++;

        // change the resumption state
        int nextState = getNextGeneratorState(node);
        generateSetGeneratorResumptionPoint(nextState);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1276]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1277]++;

        boolean hasLocals = generateSaveLocals(node);

        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1278]++;

        generateCheckForThrowOrClose(getTargetLabel(node),
                hasLocals, nextState);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1279]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1280]++;
int CodeCoverConditionCoverageHelper_C188;

        // reconstruct the stack
        if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((top != 0) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[457]++;
            generateGetGeneratorStackState();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1281]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1282]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[112]++;


int CodeCoverConditionCoverageHelper_C189;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((i < top) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[112]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[113]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[114]++;
}
                cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1283]++;
                cfw.addLoadConstant(top - i - 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1284]++;
                cfw.add(ByteCode.AALOAD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1285]++;
                cfw.add(ByteCode.SWAP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1286]++;
            }
            cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1287]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[458]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1288]++;
int CodeCoverConditionCoverageHelper_C190;

        // load return value from yield
        if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((exprContext) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[459]++;
            cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1289]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[460]++;}
    }

    private void generateCheckForThrowOrClose(int label,
                                              boolean hasLocals,
                                              int nextState) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1290]++;
        int throwLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1291]++;
        int closeLabel = cfw.acquireLabel();

        // throw the user provided object, if the operation is .throw()
        cfw.markLabel(throwLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1292]++;
        cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1293]++;
        generateThrowJavaScriptException();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1294]++;

        // throw our special internal exception if the generator is being closed
        cfw.markLabel(closeLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1295]++;
        cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1296]++;
        cfw.add(ByteCode.CHECKCAST, "java/lang/Throwable");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1297]++;
        cfw.add(ByteCode.ATHROW);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1298]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1299]++;
int CodeCoverConditionCoverageHelper_C191;

        // mark the re-entry point
        // jump here after initializing the locals
        if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((label != -1) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[461]++;
            cfw.markLabel(label);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1300]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[462]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1301]++;
int CodeCoverConditionCoverageHelper_C192;
        if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((hasLocals) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[463]++;
            // jump here directly if there are no locals
            cfw.markTableSwitchCase(generatorSwitch, nextState);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1302]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[464]++;}

        // see if we need to dispatch for .close() or .throw()
        cfw.addILoad(operationLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1303]++;
        cfw.addLoadConstant(NativeGenerator.GENERATOR_CLOSE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1304]++;
        cfw.add(ByteCode.IF_ICMPEQ, closeLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1305]++;
        cfw.addILoad(operationLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1306]++;
        cfw.addLoadConstant(NativeGenerator.GENERATOR_THROW);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1307]++;
        cfw.add(ByteCode.IF_ICMPEQ, throwLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1308]++;
    }

    private void generateIfJump(Node node, Node parent,
                                int trueLabel, int falseLabel)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1309]++;
        // System.out.println("gen code for " + node.toString());

        int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1310]++;
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1311]++;

        switch (type) {
          case Token.NOT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[465]++;
            generateIfJump(child, node, falseLabel, trueLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1312]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1313]++;
            break;

          case Token.OR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[466]++;
          case Token.AND:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[467]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1314]++;
            int interLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1315]++;
int CodeCoverConditionCoverageHelper_C193;
            if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((type == Token.AND) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[468]++;
                generateIfJump(child, node, interLabel, falseLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1316]++;

            }
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[469]++;
                generateIfJump(child, node, trueLabel, interLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1317]++;
            }
            cfw.markLabel(interLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1318]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1319]++;
            generateIfJump(child, node, trueLabel, falseLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1320]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1321]++;
            break;
          }

          case Token.IN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[470]++;
          case Token.INSTANCEOF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[471]++;
          case Token.LE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[472]++;
          case Token.LT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[473]++;
          case Token.GE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[474]++;
          case Token.GT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[475]++;
            visitIfJumpRelOp(node, child, trueLabel, falseLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1322]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1323]++;
            break;

          case Token.EQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[476]++;
          case Token.NE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[477]++;
          case Token.SHEQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[478]++;
          case Token.SHNE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[479]++;
            visitIfJumpEqOp(node, child, trueLabel, falseLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1324]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1325]++;
            break;

          default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[480]++;
            // Generate generic code for non-optimized jump
            generateExpression(node, parent);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1326]++;
            addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1327]++;
            cfw.add(ByteCode.IFNE, trueLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1328]++;
            cfw.add(ByteCode.GOTO, falseLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1329]++;
        }
    }

    private void visitFunction(OptFunctionNode ofn, int functionType)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1330]++;
        int fnIndex = codegen.getIndex(ofn.fnode);
        cfw.add(ByteCode.NEW, codegen.mainClassName);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1331]++;
        // Call function constructor
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1332]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1333]++;
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1334]++;           // load 'cx'
        cfw.addPush(fnIndex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1335]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, codegen.mainClassName,
                      "<init>", Codegen.FUNCTION_CONSTRUCTOR_SIGNATURE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1336]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1337]++;
int CodeCoverConditionCoverageHelper_C194;

        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((functionType == FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[481]++;
            // Leave closure object on stack and do not pass it to
            // initFunction which suppose to connect statements to scope
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[482]++;}
        cfw.addPush(functionType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1338]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1339]++;
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1340]++;           // load 'cx'
        addOptRuntimeInvoke("initFunction",
                            "(Lorg/mozilla/javascript/NativeFunction;"
                            +"I"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"Lorg/mozilla/javascript/Context;"
                            +")V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1341]++;
    }

    private int getTargetLabel(Node target)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1342]++;
        int labelId = target.labelId();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1343]++;
int CodeCoverConditionCoverageHelper_C195;
        if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((labelId == -1) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[483]++;
            labelId = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1344]++;
            target.labelId(labelId);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1345]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[484]++;}
        return labelId;
    }

    private void visitGoto(Jump node, int type, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1346]++;
        Node target = node.target;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1347]++;
int CodeCoverConditionCoverageHelper_C196;
        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (8)) == 0 || true) &&
 ((type == Token.IFEQ) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((type == Token.IFNE) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[485]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1348]++;
int CodeCoverConditionCoverageHelper_C197;
            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((child == null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[487]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[488]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1349]++;
            int targetLabel = getTargetLabel(target);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1350]++;
            int fallThruLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1351]++;
int CodeCoverConditionCoverageHelper_C198;
            if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((type == Token.IFEQ) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[489]++;
                generateIfJump(child, node, targetLabel, fallThruLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1352]++;
}
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[490]++;
                generateIfJump(child, node, fallThruLabel, targetLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1353]++;
}
            cfw.markLabel(fallThruLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1354]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[486]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1355]++;
int CodeCoverConditionCoverageHelper_C199;
            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((type == Token.JSR) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[491]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1356]++;
int CodeCoverConditionCoverageHelper_C200;
                if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[493]++;
                    addGotoWithReturn(target);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1357]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[494]++;
                    // This assumes that JSR is only ever used for finally
                    inlineFinally(target);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1358]++;
                }

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[492]++;
                addGoto(target, ByteCode.GOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1359]++;
            }
        }
    }

    private void addGotoWithReturn(Node target) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1360]++;
        FinallyReturnPoint ret = finallys.get(target);
        cfw.addLoadConstant(ret.jsrPoints.size());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1361]++;
        addGoto(target, ByteCode.GOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1362]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1363]++;
        int retLabel = cfw.acquireLabel();
        cfw.markLabel(retLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1364]++;
        ret.jsrPoints.add(Integer.valueOf(retLabel));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1365]++;
    }

    private void generateArrayLiteralFactory(Node node, int count) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1366]++;
        String methodName = codegen.getBodyMethodName(scriptOrFn) + "_literal" + count;
        initBodyGeneration();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1367]++;
        argsLocal = firstFreeLocal++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1368]++;
        localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1369]++;
        cfw.startMethod(methodName, "(Lorg/mozilla/javascript/Context;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"[Ljava/lang/Object;"
                +")Lorg/mozilla/javascript/Scriptable;",
                ACC_PRIVATE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1370]++;
        visitArrayLiteral(node, node.getFirstChild(), true);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1371]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1372]++;
        cfw.stopMethod((short)(localsMax + 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1373]++;
    }

    private void generateObjectLiteralFactory(Node node, int count) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1374]++;
        String methodName = codegen.getBodyMethodName(scriptOrFn) + "_literal" + count;
        initBodyGeneration();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1375]++;
        argsLocal = firstFreeLocal++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1376]++;
        localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1377]++;
        cfw.startMethod(methodName, "(Lorg/mozilla/javascript/Context;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"[Ljava/lang/Object;"
                +")Lorg/mozilla/javascript/Scriptable;",
                ACC_PRIVATE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1378]++;
        visitObjectLiteral(node, node.getFirstChild(), true);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1379]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1380]++;
        cfw.stopMethod((short)(localsMax + 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1381]++;
    }


    private void visitArrayLiteral(Node node, Node child, boolean topLevel)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1382]++;
        int count = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1383]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[115]++;


int CodeCoverConditionCoverageHelper_C201;
        for (Node cursor = child;(((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((cursor != null) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false); cursor = cursor.getNext()) {
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[115]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[116]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[117]++;
}
            ++count;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1384]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1385]++;
int CodeCoverConditionCoverageHelper_C202;

        // If code budget is tight swap out literals into separate method
        if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C202 |= (2048)) == 0 || true) &&
 ((topLevel) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1024)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C202 |= (512)) == 0 || true) &&
 ((count > 10) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C202 |= (128)) == 0 || true) &&
 ((cfw.getCurrentCodeOffset() > 30000) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (64)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C202 |= (32)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C202 |= (8)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((inLocalBlock) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 6) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 6) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[495]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1386]++;
int CodeCoverConditionCoverageHelper_C203;
            if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((literals == null) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[497]++;
                literals = new LinkedList<Node>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1387]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[498]++;}
            literals.add(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1388]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1389]++;
            String methodName = codegen.getBodyMethodName(scriptOrFn) + "_literal" + literals.size();
            cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1390]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1391]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1392]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1393]++;
            cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1394]++;
            cfw.addInvoke(ByteCode.INVOKEVIRTUAL, codegen.mainClassName, methodName,
                    "(Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"[Ljava/lang/Object;"
                        +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1395]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[496]++;}

        // load array to store array literal objects
        addNewObjectArray(count);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1396]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1397]++;
byte CodeCoverTryBranchHelper_L40 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[118]++;


int CodeCoverConditionCoverageHelper_C204;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L40 == 0) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[118]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[119]++;
} else if (CodeCoverTryBranchHelper_L40 == 1) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[119]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[120]++;
}
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1398]++;
            cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1399]++;
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1400]++;
            cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1401]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1402]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1403]++;
        int[] skipIndexes = (int[])node.getProp(Node.SKIP_INDEXES_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1404]++;
int CodeCoverConditionCoverageHelper_C205;
        if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((skipIndexes == null) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[499]++;
            cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1405]++;
            cfw.add(ByteCode.ICONST_0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1406]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[500]++;
            cfw.addPush(OptRuntime.encodeIntArray(skipIndexes));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1407]++;
            cfw.addPush(skipIndexes.length);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1408]++;
        }
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1409]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1410]++;
        addOptRuntimeInvoke("newArrayLiteral",
             "([Ljava/lang/Object;"
             +"Ljava/lang/String;"
             +"I"
             +"Lorg/mozilla/javascript/Context;"
             +"Lorg/mozilla/javascript/Scriptable;"
             +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1411]++;
    }

    private void visitObjectLiteral(Node node, Node child, boolean topLevel)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1412]++;
        Object[] properties = (Object[])node.getProp(Node.OBJECT_IDS_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1413]++;
        int count = properties.length;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1414]++;
int CodeCoverConditionCoverageHelper_C206;

        // If code budget is tight swap out literals into separate method
        if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C206 |= (2048)) == 0 || true) &&
 ((topLevel) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1024)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C206 |= (512)) == 0 || true) &&
 ((count > 10) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C206 |= (128)) == 0 || true) &&
 ((cfw.getCurrentCodeOffset() > 30000) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (64)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C206 |= (32)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C206 |= (8)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((inLocalBlock) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 6) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 6) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[501]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1415]++;
int CodeCoverConditionCoverageHelper_C207;
            if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((literals == null) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[503]++;
                literals = new LinkedList<Node>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1416]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[504]++;}
            literals.add(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1417]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1418]++;
            String methodName = codegen.getBodyMethodName(scriptOrFn) + "_literal" + literals.size();
            cfw.addALoad(funObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1419]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1420]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1421]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1422]++;
            cfw.addALoad(argsLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1423]++;
            cfw.addInvoke(ByteCode.INVOKEVIRTUAL, codegen.mainClassName, methodName,
                    "(Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"[Ljava/lang/Object;"
                        +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1424]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[502]++;}

        // load array with property ids
        addNewObjectArray(count);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1425]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1426]++;
byte CodeCoverTryBranchHelper_L41 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[121]++;


int CodeCoverConditionCoverageHelper_C208;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L41 == 0) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[121]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[122]++;
} else if (CodeCoverTryBranchHelper_L41 == 1) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[122]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[123]++;
}
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1427]++;
            cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1428]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1429]++;
            Object id = properties[i];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1430]++;
int CodeCoverConditionCoverageHelper_C209;
            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[505]++;
                cfw.addPush((String)id);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1431]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[506]++;
                cfw.addPush(((Integer)id).intValue());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1432]++;
                addScriptRuntimeInvoke("wrapInt", "(I)Ljava/lang/Integer;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1433]++;
            }
            cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1434]++;
        }
        // load array with property values
        addNewObjectArray(count);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1435]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1436]++;
        Node child2 = child;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1437]++;
byte CodeCoverTryBranchHelper_L42 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[124]++;


int CodeCoverConditionCoverageHelper_C210;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L42 == 0) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[124]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[125]++;
} else if (CodeCoverTryBranchHelper_L42 == 1) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[125]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[126]++;
}
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1438]++;
            cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1439]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1440]++;
            int childType = child2.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1441]++;
int CodeCoverConditionCoverageHelper_C211;
            if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (8)) == 0 || true) &&
 ((childType == Token.GET) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((childType == Token.SET) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[507]++;
                generateExpression(child2.getFirstChild(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1442]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[508]++;
                generateExpression(child2, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1443]++;
            }
            cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1444]++;
            child2 = child2.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1445]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1446]++;
        // check if object literal actually has any getters or setters
        boolean hasGetterSetters = false;
        child2 = child;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1447]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1448]++;
byte CodeCoverTryBranchHelper_L43 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[127]++;


int CodeCoverConditionCoverageHelper_C212;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L43 == 0) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[127]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[128]++;
} else if (CodeCoverTryBranchHelper_L43 == 1) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[128]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[129]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1449]++;
            int childType = child2.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1450]++;
int CodeCoverConditionCoverageHelper_C213;
            if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (8)) == 0 || true) &&
 ((childType == Token.GET) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((childType == Token.SET) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[509]++;
                hasGetterSetters = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1451]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1452]++;
                break;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[510]++;}
            child2 = child2.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1453]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1454]++;
int CodeCoverConditionCoverageHelper_C214;
        // create getter/setter flag array
        if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((hasGetterSetters) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[511]++;
            cfw.addPush(count);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1455]++;
            cfw.add(ByteCode.NEWARRAY, ByteCode.T_INT);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1456]++;
            child2 = child;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1457]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1458]++;
byte CodeCoverTryBranchHelper_L44 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[130]++;


int CodeCoverConditionCoverageHelper_C215;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L44 == 0) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[130]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[131]++;
} else if (CodeCoverTryBranchHelper_L44 == 1) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[131]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[132]++;
}
                cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1459]++;
                cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1460]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1461]++;
                int childType = child2.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1462]++;
int CodeCoverConditionCoverageHelper_C216;
                if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((childType == Token.GET) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[513]++;
                    cfw.add(ByteCode.ICONST_M1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1463]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[514]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1464]++;
int CodeCoverConditionCoverageHelper_C217; if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((childType == Token.SET) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[515]++;
                    cfw.add(ByteCode.ICONST_1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1465]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[516]++;
                    cfw.add(ByteCode.ICONST_0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1466]++;
                }
}
                cfw.add(ByteCode.IASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1467]++;
                child2 = child2.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1468]++;
            }

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[512]++;
            cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1469]++;
        }

        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1470]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1471]++;
        addScriptRuntimeInvoke("newObjectLiteral",
             "([Ljava/lang/Object;"
             +"[Ljava/lang/Object;"
             +"[I"
             +"Lorg/mozilla/javascript/Context;"
             +"Lorg/mozilla/javascript/Scriptable;"
             +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1472]++;
    }

    private void visitSpecialCall(Node node, int type, int specialType,
                                  Node child)
    {
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1473]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1474]++;
int CodeCoverConditionCoverageHelper_C218;

        if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[517]++;
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1475]++;

            // stack: ... cx functionObj
        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[518]++;
            generateFunctionAndThisObj(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1476]++;
            // stack: ... cx functionObj thisObj
        }
        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1477]++;

        generateCallArgArray(node, child, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1478]++;

        String methodName;
        String callSignature;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1479]++;
int CodeCoverConditionCoverageHelper_C219;

        if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[519]++;
            methodName = "newObjectSpecial";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1480]++;
            callSignature = "(Lorg/mozilla/javascript/Context;"
                            +"Ljava/lang/Object;"
                            +"[Ljava/lang/Object;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"I" // call type
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1481]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1482]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1483]++;
            cfw.addPush(specialType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1484]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[520]++;
            methodName = "callSpecial";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1485]++;
            callSignature = "(Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Callable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"[Ljava/lang/Object;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"I" // call type
                            +"Ljava/lang/String;I"  // filename, linenumber
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1486]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1487]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1488]++;
            cfw.addPush(specialType);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1489]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1490]++;
            String sourceName = scriptOrFn.getSourceName();
            cfw.addPush(sourceName == null ? "" : sourceName);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1491]++;
            cfw.addPush(itsLineNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1492]++;
        }

        addOptRuntimeInvoke(methodName, callSignature);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1493]++;
    }

    private void visitStandardCall(Node node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1494]++;
int CodeCoverConditionCoverageHelper_C220;
        if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((node.getType() != Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[521]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[522]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1495]++;

        Node firstArgChild = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1496]++;
        int childType = child.getType();

        String methodName;
        String signature;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1497]++;
int CodeCoverConditionCoverageHelper_C221;

        if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((firstArgChild == null) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[523]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1498]++;
int CodeCoverConditionCoverageHelper_C222;
            if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((childType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[525]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1499]++;
                // name() call
                String name = child.getString();
                cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1500]++;
                methodName = "callName0";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1501]++;
                signature = "(Ljava/lang/String;"
                            +"Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1502]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[526]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1503]++;
int CodeCoverConditionCoverageHelper_C223; if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((childType == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[527]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1504]++;
                // x.name() call
                Node propTarget = child.getFirstChild();
                generateExpression(propTarget, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1505]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1506]++;
                Node id = propTarget.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1507]++;
                String property = id.getString();
                cfw.addPush(property);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1508]++;
                methodName = "callProp0";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1509]++;
                signature = "(Ljava/lang/Object;"
                            +"Ljava/lang/String;"
                            +"Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1510]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[528]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1511]++;
int CodeCoverConditionCoverageHelper_C224; if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((childType == Token.GETPROPNOWARN) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[529]++;
                throw Kit.codeBug();

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[530]++;
                generateFunctionAndThisObj(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1512]++;
                methodName = "call0";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1513]++;
                signature = "(Lorg/mozilla/javascript/Callable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1514]++;
            }
}
}


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[524]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1515]++;
int CodeCoverConditionCoverageHelper_C225; if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((childType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[531]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1516]++;
            // XXX: this optimization is only possible if name
            // resolution
            // is not affected by arguments evaluation and currently
            // there are no checks for it
            String name = child.getString();
            generateCallArgArray(node, firstArgChild, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1517]++;
            cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1518]++;
            methodName = "callName";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1519]++;
            signature = "([Ljava/lang/Object;"
                        +"Ljava/lang/String;"
                        +"Lorg/mozilla/javascript/Context;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1520]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[532]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1521]++;
            int argCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1522]++;
byte CodeCoverTryBranchHelper_L45 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[133]++;


int CodeCoverConditionCoverageHelper_C226;
            for (Node arg = firstArgChild;(((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false); arg = arg.getNext()) {
if (CodeCoverTryBranchHelper_L45 == 0) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[133]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[134]++;
} else if (CodeCoverTryBranchHelper_L45 == 1) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[134]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[135]++;
}
                ++argCount;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1523]++;
            }
            generateFunctionAndThisObj(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1524]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1525]++;
int CodeCoverConditionCoverageHelper_C227;
            // stack: ... functionObj thisObj
            if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((argCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[533]++;
                generateExpression(firstArgChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1526]++;
                methodName = "call1";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1527]++;
                signature = "(Lorg/mozilla/javascript/Callable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"Ljava/lang/Object;"
                            +"Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1528]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[534]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1529]++;
int CodeCoverConditionCoverageHelper_C228; if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((argCount == 2) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[535]++;
                generateExpression(firstArgChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1530]++;
                generateExpression(firstArgChild.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1531]++;
                methodName = "call2";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1532]++;
                signature = "(Lorg/mozilla/javascript/Callable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"Ljava/lang/Object;"
                            +"Ljava/lang/Object;"
                            +"Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1533]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[536]++;
                generateCallArgArray(node, firstArgChild, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1534]++;
                methodName = "callN";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1535]++;
                signature = "(Lorg/mozilla/javascript/Callable;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +"[Ljava/lang/Object;"
                            +"Lorg/mozilla/javascript/Context;"
                            +"Lorg/mozilla/javascript/Scriptable;"
                            +")Ljava/lang/Object;";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1536]++;
            }
}
        }
}

        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1537]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1538]++;
        addOptRuntimeInvoke(methodName, signature);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1539]++;
    }

    private void visitStandardNew(Node node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1540]++;
int CodeCoverConditionCoverageHelper_C229;
        if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((node.getType() != Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[537]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[538]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1541]++;

        Node firstArgChild = child.getNext();

        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1542]++;
        // stack: ... functionObj
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1543]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1544]++;
        // stack: ... functionObj cx scope
        generateCallArgArray(node, firstArgChild, false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1545]++;
        addScriptRuntimeInvoke(
            "newObject",
            "(Ljava/lang/Object;"
            +"Lorg/mozilla/javascript/Context;"
            +"Lorg/mozilla/javascript/Scriptable;"
            +"[Ljava/lang/Object;"
            +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1546]++;
    }

    private void visitOptimizedCall(Node node, OptFunctionNode target,
                                    int type, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1547]++;
        Node firstArgChild = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1548]++;
        String className = codegen.mainClassName;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1549]++;

        short thisObjLocal = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1550]++;
int CodeCoverConditionCoverageHelper_C230;
        if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[539]++;
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1551]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[540]++;
            generateFunctionAndThisObj(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1552]++;
            thisObjLocal = getNewWordLocal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1553]++;
            cfw.addAStore(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1554]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1555]++;
        // stack: ... functionObj

        int beyond = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1556]++;
        int regularCall = cfw.acquireLabel();

        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1557]++;
        cfw.add(ByteCode.INSTANCEOF, className);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1558]++;
        cfw.add(ByteCode.IFEQ, regularCall);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1559]++;
        cfw.add(ByteCode.CHECKCAST, className);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1560]++;
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1561]++;
        cfw.add(ByteCode.GETFIELD, className, Codegen.ID_FIELD_NAME, "I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1562]++;
        cfw.addPush(codegen.getIndex(target.fnode));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1563]++;
        cfw.add(ByteCode.IF_ICMPNE, regularCall);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1564]++;

        // stack: ... directFunct
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1565]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1566]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1567]++;
int CodeCoverConditionCoverageHelper_C231;
        // stack: ... directFunc cx scope

        if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[541]++;
            cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1568]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[542]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1569]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1570]++;
        // stack: ... directFunc cx scope thisObj
/*
Remember that directCall parameters are paired in 1 aReg and 1 dReg
If the argument is an incoming arg, just pass the orginal pair thru.
Else, if the argument is known to be typed 'Number', pass Void.TYPE
in the aReg and the number is the dReg
Else pass the JS object in the aReg and 0.0 in the dReg.
*/
        Node argChild = firstArgChild;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1571]++;
byte CodeCoverTryBranchHelper_L46 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[136]++;


int CodeCoverConditionCoverageHelper_C232;
        while ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((argChild != null) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
if (CodeCoverTryBranchHelper_L46 == 0) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[136]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[137]++;
} else if (CodeCoverTryBranchHelper_L46 == 1) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[137]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[138]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1572]++;
            int dcp_register = nodeIsDirectCallParameter(argChild);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1573]++;
int CodeCoverConditionCoverageHelper_C233;
            if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((dcp_register >= 0) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[543]++;
                cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1574]++;
                cfw.addDLoad(dcp_register + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1575]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[544]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1576]++;
int CodeCoverConditionCoverageHelper_C234; if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((argChild.getIntProp(Node.ISNUMBER_PROP, -1)
                       == Node.BOTH) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[545]++;
                cfw.add(ByteCode.GETSTATIC,
                        "java/lang/Void",
                        "TYPE",
                        "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1577]++;
                generateExpression(argChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1578]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[546]++;
                generateExpression(argChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1579]++;
                cfw.addPush(0.0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1580]++;
            }
}
            argChild = argChild.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1581]++;
        }

        cfw.add(ByteCode.GETSTATIC,
                "org/mozilla/javascript/ScriptRuntime",
                "emptyArgs", "[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1582]++;
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      codegen.mainClassName,
                      (type == Token.NEW)
                          ? codegen.getDirectCtorName(target.fnode)
                          : codegen.getBodyMethodName(target.fnode),
                      codegen.getBodyMethodSignature(target.fnode));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1583]++;

        cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1584]++;

        cfw.markLabel(regularCall);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1585]++;
        // stack: ... functionObj
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1586]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1587]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1588]++;
int CodeCoverConditionCoverageHelper_C235;
        // stack: ... functionObj cx scope
        if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((type != Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[547]++;
            cfw.addALoad(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1589]++;
            releaseWordLocal(thisObjLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1590]++;

            // stack: ... functionObj cx scope thisObj
        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[548]++;}
        // XXX: this will generate code for the child array the second time,
        // so expression code generation better not to alter tree structure...
        generateCallArgArray(node, firstArgChild, true);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1591]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1592]++;
int CodeCoverConditionCoverageHelper_C236;

        if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[549]++;
            addScriptRuntimeInvoke(
                "newObject",
                "(Ljava/lang/Object;"
                +"Lorg/mozilla/javascript/Context;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"[Ljava/lang/Object;"
                +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1593]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[550]++;
            cfw.addInvoke(ByteCode.INVOKEINTERFACE,
                "org/mozilla/javascript/Callable",
                "call",
                "(Lorg/mozilla/javascript/Context;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +"[Ljava/lang/Object;"
                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1594]++;
        }

        cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1595]++;
    }

    private void generateCallArgArray(Node node, Node argChild, boolean directCall)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1596]++;
        int argCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1597]++;
byte CodeCoverTryBranchHelper_L47 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[139]++;


int CodeCoverConditionCoverageHelper_C237;
        for (Node child = argChild;(((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L47 == 0) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[139]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[140]++;
} else if (CodeCoverTryBranchHelper_L47 == 1) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[140]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[141]++;
}
            ++argCount;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1598]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1599]++;
int CodeCoverConditionCoverageHelper_C238;
        // load array object to set arguments
        if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (8)) == 0 || true) &&
 ((argCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((itsOneArgArray >= 0) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[551]++;
            cfw.addALoad(itsOneArgArray);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1600]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[552]++;
            addNewObjectArray(argCount);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1601]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1602]++;
byte CodeCoverTryBranchHelper_L48 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[142]++;


int CodeCoverConditionCoverageHelper_C239;
        // Copy arguments into it
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((i != argCount) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L48 == 0) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[142]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[143]++;
} else if (CodeCoverTryBranchHelper_L48 == 1) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[143]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[144]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1603]++;
int CodeCoverConditionCoverageHelper_C240;
            // If we are compiling a generator an argument could be the result
            // of a yield. In that case we will have an immediate on the stack
            // which we need to avoid
            if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[553]++;
                cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1604]++;
                cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1605]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[554]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1606]++;
int CodeCoverConditionCoverageHelper_C241;

            if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((directCall) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[555]++;
                generateExpression(argChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1607]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[556]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1608]++;
                // If this has also been a directCall sequence, the Number
                // flag will have remained set for any parameter so that
                // the values could be copied directly into the outgoing
                // args. Here we want to force it to be treated as not in
                // a Number context, so we set the flag off.
                int dcp_register = nodeIsDirectCallParameter(argChild);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1609]++;
int CodeCoverConditionCoverageHelper_C242;
                if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((dcp_register >= 0) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[557]++;
                    dcpLoadAsObject(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1610]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[558]++;
                    generateExpression(argChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1611]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1612]++;
                    int childNumberFlag
                            = argChild.getIntProp(Node.ISNUMBER_PROP, -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1613]++;
int CodeCoverConditionCoverageHelper_C243;
                    if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((childNumberFlag == Node.BOTH) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[559]++;
                        addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1614]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[560]++;}
                }
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1615]++;
int CodeCoverConditionCoverageHelper_C244;

            // When compiling generators, any argument to a method may be a
            // yield expression. Hence we compile the argument first and then
            // load the argument index and assign the value to the args array.
            if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[561]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1616]++;
                short tempLocal = getNewWordLocal();
                cfw.addAStore(tempLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1617]++;
                cfw.add(ByteCode.CHECKCAST, "[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1618]++;
                cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1619]++;
                cfw.addPush(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1620]++;
                cfw.addALoad(tempLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1621]++;
                releaseWordLocal(tempLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1622]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[562]++;}

            cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1623]++;

            argChild = argChild.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1624]++;
        }
    }

    private void generateFunctionAndThisObj(Node node, Node parent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1625]++;
        // Place on stack (function object, function this) pair
        int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1626]++;
        switch (node.getType()) {
          case Token.GETPROPNOWARN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[563]++;
            throw Kit.codeBug();

          case Token.GETPROP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[564]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[565]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1627]++;
            Node target = node.getFirstChild();
            generateExpression(target, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1628]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1629]++;
            Node id = target.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1630]++;
int CodeCoverConditionCoverageHelper_C245;
            if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((type == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[566]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1631]++;
                String property = id.getString();
                cfw.addPush(property);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1632]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1633]++;
                cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1634]++;
                addScriptRuntimeInvoke(
                    "getPropFunctionAndThis",
                    "(Ljava/lang/Object;"
                    +"Ljava/lang/String;"
                    +"Lorg/mozilla/javascript/Context;"
                    +"Lorg/mozilla/javascript/Scriptable;"
                    +")Lorg/mozilla/javascript/Callable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1635]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[567]++;
                generateExpression(id, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1636]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1637]++;
int CodeCoverConditionCoverageHelper_C246;  // id
                if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[568]++;
                    addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1638]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[569]++;}
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1639]++;
                addScriptRuntimeInvoke(
                    "getElemFunctionAndThis",
                    "(Ljava/lang/Object;"
                    +"Ljava/lang/Object;"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Lorg/mozilla/javascript/Callable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1640]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1641]++;
            break;
          }

          case Token.NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[570]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1642]++;
            String name = node.getString();
            cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1643]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1644]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1645]++;
            addScriptRuntimeInvoke(
                "getNameFunctionAndThis",
                "(Ljava/lang/String;"
                +"Lorg/mozilla/javascript/Context;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +")Lorg/mozilla/javascript/Callable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1646]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1647]++;
            break;
          }

          default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[571]++; // including GETVAR
            generateExpression(node, parent);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1648]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1649]++;
            addScriptRuntimeInvoke(
                "getValueFunctionAndThis",
                "(Ljava/lang/Object;"
                +"Lorg/mozilla/javascript/Context;"
                +")Lorg/mozilla/javascript/Callable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1650]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1651]++;
            break;
        }
        // Get thisObj prepared by get(Name|Prop|Elem|Value)FunctionAndThis
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1652]++;
        addScriptRuntimeInvoke(
            "lastStoredScriptable",
            "(Lorg/mozilla/javascript/Context;"
            +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1653]++;
    }

    private void updateLineNumber(Node node)
    {
        itsLineNumber = node.getLineno();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1654]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1655]++;
int CodeCoverConditionCoverageHelper_C247;
        if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((itsLineNumber == -1) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[572]++;
            return;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[573]++;}
        cfw.addLineNumberEntry((short)itsLineNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1656]++;
    }

    private void visitTryCatchFinally(Jump node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1657]++;
        /* Save the variable object, in case there are with statements
         * enclosed by the try block and we catch some exception.
         * We'll restore it for the catch block so that catch block
         * statements get the right scope.
         */

        // OPT we only need to do this if there are enclosed WITH
        // statements; could statically check and omit this if there aren't any.

        // XXX OPT Maybe instead do syntactic transforms to associate
        // each 'with' with a try/finally block that does the exitwith.

        short savedVariableObject = getNewWordLocal();
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1658]++;
        cfw.addAStore(savedVariableObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1659]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1660]++;

        /*
         * Generate the code for the tree; most of the work is done in IRFactory
         * and NodeTransformer;  Codegen just adds the java handlers for the
         * javascript catch and finally clauses.  */

        int startLabel = cfw.acquireLabel();
        cfw.markLabel(startLabel, (short)0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1661]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1662]++;

        Node catchTarget = node.target;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1663]++;
        Node finallyTarget = node.getFinally();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1664]++;
        int[] handlerLabels = new int[EXCEPTION_MAX];

        exceptionManager.pushExceptionInfo(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1665]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1666]++;
int CodeCoverConditionCoverageHelper_C248;
        if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((catchTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[574]++;
            handlerLabels[JAVASCRIPT_EXCEPTION] = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1667]++;
            handlerLabels[EVALUATOR_EXCEPTION] = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1668]++;
            handlerLabels[ECMAERROR_EXCEPTION] = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1669]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1670]++;
            Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1671]++;
int CodeCoverConditionCoverageHelper_C249;
            if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (8)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[576]++;
                handlerLabels[THROWABLE_EXCEPTION] = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1672]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[577]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[575]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1673]++;
int CodeCoverConditionCoverageHelper_C250;
        if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((finallyTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[578]++;
            handlerLabels[FINALLY_EXCEPTION] = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1674]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[579]++;}
        exceptionManager.setHandlers(handlerLabels, startLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1675]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1676]++;
int CodeCoverConditionCoverageHelper_C251;

        // create a table for the equivalent of JSR returns
        if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (8)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((finallyTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[580]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1677]++;
            FinallyReturnPoint ret = new FinallyReturnPoint();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1678]++;
int CodeCoverConditionCoverageHelper_C252;
            if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((finallys == null) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[582]++;
                finallys = new HashMap<Node,FinallyReturnPoint>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1679]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[583]++;}
            // add the finally target to hashtable
            finallys.put(finallyTarget, ret);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1680]++;
            // add the finally node as well to the hash table
            finallys.put(finallyTarget.getNext(), ret);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1681]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[581]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1682]++;
byte CodeCoverTryBranchHelper_L49 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[145]++;


int CodeCoverConditionCoverageHelper_C253;

        while ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false)) {
if (CodeCoverTryBranchHelper_L49 == 0) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[145]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[146]++;
} else if (CodeCoverTryBranchHelper_L49 == 1) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[146]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[147]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1683]++;
int CodeCoverConditionCoverageHelper_C254;
            if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((child == catchTarget) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[584]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1684]++;
                int catchLabel = getTargetLabel(catchTarget);
                exceptionManager.removeHandler(JAVASCRIPT_EXCEPTION,
                                               catchLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1685]++;
                exceptionManager.removeHandler(EVALUATOR_EXCEPTION,
                                               catchLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1686]++;
                exceptionManager.removeHandler(ECMAERROR_EXCEPTION,
                                               catchLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1687]++;
                exceptionManager.removeHandler(THROWABLE_EXCEPTION,
                                               catchLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1688]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[585]++;}
            generateStatement(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1689]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1690]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1691]++;

        // control flow skips the handlers
        int realEnd = cfw.acquireLabel();
        cfw.add(ByteCode.GOTO, realEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1692]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1693]++;

        int exceptionLocal = getLocalBlockRegister(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1694]++;
int CodeCoverConditionCoverageHelper_C255;
        // javascript handler; unwrap exception and GOTO to javascript
        // catch area.
        if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((catchTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[586]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1695]++;
            // get the label to goto
            int catchLabel = catchTarget.labelId();

            // If the function is a generator, then handlerLabels will consist
            // of zero labels. generateCatchBlock will create its own label
            // in this case. The extra parameter for the label is added for
            // the case of non-generator functions that inline finally blocks.

            generateCatchBlock(JAVASCRIPT_EXCEPTION, savedVariableObject,
                               catchLabel, exceptionLocal,
                               handlerLabels[JAVASCRIPT_EXCEPTION]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1696]++;
            /*
             * catch WrappedExceptions, see if they are wrapped
             * JavaScriptExceptions. Otherwise, rethrow.
             */
            generateCatchBlock(EVALUATOR_EXCEPTION, savedVariableObject,
                               catchLabel, exceptionLocal,
                               handlerLabels[EVALUATOR_EXCEPTION]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1697]++;

            /*
                we also need to catch EcmaErrors and feed the
                associated error object to the handler
            */
            generateCatchBlock(ECMAERROR_EXCEPTION, savedVariableObject,
                               catchLabel, exceptionLocal,
                               handlerLabels[ECMAERROR_EXCEPTION]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1698]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1699]++;

            Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1700]++;
int CodeCoverConditionCoverageHelper_C256;
            if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (8)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 2) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[588]++;
                generateCatchBlock(THROWABLE_EXCEPTION, savedVariableObject,
                                   catchLabel, exceptionLocal,
                                   handlerLabels[THROWABLE_EXCEPTION]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1701]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[589]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[587]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1702]++;
int CodeCoverConditionCoverageHelper_C257;

        // finally handler; catch all exceptions, store to a local; JSR to
        // the finally, then re-throw.
        if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((finallyTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[590]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1703]++;
            int finallyHandler = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1704]++;
            int finallyEnd = cfw.acquireLabel();
            cfw.markHandler(finallyHandler);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1705]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1706]++;
int CodeCoverConditionCoverageHelper_C258;
            if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[592]++;
                cfw.markLabel(handlerLabels[FINALLY_EXCEPTION]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1707]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[593]++;}
            cfw.addAStore(exceptionLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1708]++;

            // reset the variable object local
            cfw.addALoad(savedVariableObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1709]++;
            cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1710]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1711]++;

            // get the label to JSR to
            int finallyLabel = finallyTarget.labelId();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1712]++;
int CodeCoverConditionCoverageHelper_C259;
            if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[594]++;
                addGotoWithReturn(finallyTarget);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1713]++;
}
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[595]++;
                inlineFinally(finallyTarget, handlerLabels[FINALLY_EXCEPTION],
                              finallyEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1714]++;
            }

            // rethrow
            cfw.addALoad(exceptionLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1715]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1716]++;
int CodeCoverConditionCoverageHelper_C260;
            if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[596]++;
                cfw.add(ByteCode.CHECKCAST, "java/lang/Throwable");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1717]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[597]++;}
            cfw.add(ByteCode.ATHROW);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1718]++;

            cfw.markLabel(finallyEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1719]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1720]++;
int CodeCoverConditionCoverageHelper_C261;
            // mark the handler
            if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[598]++;
                cfw.addExceptionHandler(startLabel, finallyLabel,
                                        finallyHandler, null);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1721]++;
 // catch any
            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[599]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[591]++;}
        releaseWordLocal(savedVariableObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1722]++;
        cfw.markLabel(realEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1723]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1724]++;
int CodeCoverConditionCoverageHelper_C262;

        if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[600]++;
            exceptionManager.popExceptionInfo();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1725]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[601]++;}
    }

    private static final int JAVASCRIPT_EXCEPTION  = 0;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1726]++;
  }
    private static final int EVALUATOR_EXCEPTION   = 1;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1727]++;
  }
    private static final int ECMAERROR_EXCEPTION   = 2;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1728]++;
  }
    private static final int THROWABLE_EXCEPTION   = 3;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1729]++;
  }
    // Finally catch-alls are technically Throwable, but we want a distinction
    // for the exception manager and we want to use a null string instead of
    // an explicit Throwable string.
    private static final int FINALLY_EXCEPTION = 4;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1730]++;
  }
    private static final int EXCEPTION_MAX = 5;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1731]++;
  }

    private void generateCatchBlock(int exceptionType,
                                    short savedVariableObject,
                                    int catchLabel,
                                    int exceptionLocal,
                                    int handler)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1732]++;
int CodeCoverConditionCoverageHelper_C263;
        if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((handler == 0) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[602]++;
            handler = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1733]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[603]++;}
        cfw.markHandler(handler);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1734]++;

        // MS JVM gets cranky if the exception object is left on the stack
        cfw.addAStore(exceptionLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1735]++;

        // reset the variable object local
        cfw.addALoad(savedVariableObject);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1736]++;
        cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1737]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1738]++;

        String exceptionName = exceptionTypeToName(exceptionType);

        cfw.add(ByteCode.GOTO, catchLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1739]++;
    }

    private String exceptionTypeToName(int exceptionType)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1740]++;
int CodeCoverConditionCoverageHelper_C264;
        if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((exceptionType == JAVASCRIPT_EXCEPTION) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[604]++;
            return "org/mozilla/javascript/JavaScriptException";

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[605]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1741]++;
int CodeCoverConditionCoverageHelper_C265; if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((exceptionType == EVALUATOR_EXCEPTION) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[606]++;
            return "org/mozilla/javascript/EvaluatorException";

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[607]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1742]++;
int CodeCoverConditionCoverageHelper_C266; if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((exceptionType == ECMAERROR_EXCEPTION) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[608]++;
            return "org/mozilla/javascript/EcmaError";

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[609]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1743]++;
int CodeCoverConditionCoverageHelper_C267; if ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((exceptionType == THROWABLE_EXCEPTION) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[610]++;
            return "java/lang/Throwable";

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[611]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1744]++;
int CodeCoverConditionCoverageHelper_C268; if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((exceptionType == FINALLY_EXCEPTION) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[612]++;
            return null;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[613]++;
            throw Kit.codeBug();
        }
}
}
}
}
    }

    /**
     * Manages placement of exception handlers for non-generator functions.
     *
     * For generator functions, there are mechanisms put into place to emulate
     * jsr by using a goto with a return label. That is one mechanism for
     * implementing finally blocks. The other, which is implemented by Sun,
     * involves duplicating the finally block where jsr instructions would
     * normally be. However, inlining finally blocks causes problems with
     * translating exception handlers. Instead of having one big bytecode range
     * for each exception, we now have to skip over the inlined finally blocks.
     * This class is meant to help implement this.
     *
     * Every time a try block is encountered during translation, exception
     * information should be pushed into the manager, which is treated as a
     * stack. The addHandler() and setHandlers() methods may be used to register
     * exceptionHandlers for the try block; removeHandler() is used to reverse
     * the operation. At the end of the try/catch/finally, the exception state
     * for it should be popped.
     *
     * The important function here is markInlineFinally. This finds which
     * finally block on the exception state stack is being inlined and skips
     * the proper exception handlers until the finally block is generated.
     */
    private class ExceptionManager
    {
        ExceptionManager()
        {
            exceptionInfo = new LinkedList<ExceptionInfo>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1745]++;
        }

        /**
         * Push a new try block onto the exception information stack.
         *
         * @param node an exception handling node (node.getType() ==
         *             Token.TRY)
         */
        void pushExceptionInfo(Jump node)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1746]++;
            Node fBlock = getFinallyAtTarget(node.getFinally());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1747]++;
            ExceptionInfo ei = new ExceptionInfo(node, fBlock);
            exceptionInfo.add(ei);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1748]++;
        }

        /**
         * Register an exception handler for the try block at the top of the
         * exception information stack.
         *
         * @param exceptionType one of the integer constants representing an
         *                      exception type
         * @param handlerLabel the label of the exception handler
         * @param startLabel the label where the exception handling begins
         */
        void addHandler(int exceptionType, int handlerLabel, int startLabel)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1749]++;
            ExceptionInfo top = getTop();
            top.handlerLabels[exceptionType] = handlerLabel;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1750]++;
            top.exceptionStarts[exceptionType] = startLabel;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1751]++;
        }

        /**
         * Register multiple exception handlers for the top try block. If the
         * exception type maps to a zero label, then it is ignored.
         *
         * @param handlerLabels a map from integer constants representing an
         *                      exception type to the label of the exception
         *                      handler
         * @param startLabel the label where all of the exception handling
         *                   begins
         */
        void setHandlers(int[] handlerLabels, int startLabel)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1752]++;
            ExceptionInfo top = getTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1753]++;
byte CodeCoverTryBranchHelper_L50 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[148]++;


int CodeCoverConditionCoverageHelper_C269;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((i < handlerLabels.length) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L50 == 0) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[148]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[149]++;
} else if (CodeCoverTryBranchHelper_L50 == 1) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[149]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[150]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1754]++;
int CodeCoverConditionCoverageHelper_C270;
                if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((handlerLabels[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[614]++;
                    addHandler(i, handlerLabels[i], startLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1755]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[615]++;}
            }
        }

        /**
         * Remove an exception handler for the top try block.
         *
         * @param exceptionType one of the integer constants representing an
         *                      exception type
         * @param endLabel a label representing the end of the last bytecode
         *                 that should be handled by the exception
         * @returns the label of the exception handler associated with the
         *          exception type
         */
        int removeHandler(int exceptionType, int endLabel)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1756]++;
            ExceptionInfo top = getTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1757]++;
int CodeCoverConditionCoverageHelper_C271;
            if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((top.handlerLabels[exceptionType] != 0) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[616]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1758]++;
                int handlerLabel = top.handlerLabels[exceptionType];
                endCatch(top, exceptionType, endLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1759]++;
                top.handlerLabels[exceptionType] = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1760]++;
                return handlerLabel;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[617]++;}
            return 0;
        }

        /**
         * Remove the top try block from the exception information stack.
         */
        void popExceptionInfo()
        {
            exceptionInfo.removeLast();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1761]++;
        }

        /**
         * Mark the start of an inlined finally block.
         *
         * When a finally block is inlined, any exception handlers that are
         * lexically inside of its try block should not cover the range of the
         * exception block. We scan from the innermost try block outward until
         * we find the try block that matches the finally block. For any block
         * whose exception handlers that aren't currently stopped by a finally
         * block, we stop the handlers at the beginning of the finally block
         * and set it as the finally block that has stopped the handlers. This
         * prevents other inlined finally blocks from prematurely ending skip
         * ranges and creating bad exception handler ranges.
         *
         * @param finallyBlock the finally block that is being inlined
         * @param finallyStart the label of the beginning of the inlined code
         */
        void markInlineFinallyStart(Node finallyBlock, int finallyStart)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1762]++;
            // Traverse the stack in LIFO order until the try block
            // corresponding to the finally block has been reached. We must
            // traverse backwards because the earlier exception handlers in
            // the exception handler table have priority when determining which
            // handler to use. Therefore, we start with the most nested try
            // block and move outward.
            ListIterator<ExceptionInfo> iter =
                    exceptionInfo.listIterator(exceptionInfo.size());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1763]++;
byte CodeCoverTryBranchHelper_L51 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[151]++;


int CodeCoverConditionCoverageHelper_C272;
            while ((((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((iter.hasPrevious()) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false)) {
if (CodeCoverTryBranchHelper_L51 == 0) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[151]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[152]++;
} else if (CodeCoverTryBranchHelper_L51 == 1) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[152]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[153]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1764]++;
                ExceptionInfo ei = iter.previous();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1765]++;
byte CodeCoverTryBranchHelper_L52 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[154]++;


int CodeCoverConditionCoverageHelper_C273;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((i < EXCEPTION_MAX) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L52 == 0) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[154]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[155]++;
} else if (CodeCoverTryBranchHelper_L52 == 1) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[155]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[156]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1766]++;
int CodeCoverConditionCoverageHelper_C274;
                    if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (8)) == 0 || true) &&
 ((ei.handlerLabels[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((ei.currentFinally == null) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[618]++;
                        endCatch(ei, i, finallyStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1767]++;
                        ei.exceptionStarts[i] = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1768]++;
                        ei.currentFinally = finallyBlock;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1769]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[619]++;}
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1770]++;
int CodeCoverConditionCoverageHelper_C275;
                if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((ei.finallyBlock == finallyBlock) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[620]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1771]++;
                    break;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[621]++;}
            }
        }

        /**
         * Mark the end of an inlined finally block.
         *
         * For any set of exception handlers that have been stopped by the
         * inlined block, resume exception handling at the end of the finally
         * block.
         *
         * @param finallyBlock the finally block that is being inlined
         * @param finallyEnd the label of the end of the inlined code
         */
        void markInlineFinallyEnd(Node finallyBlock, int finallyEnd)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1772]++;
            ListIterator<ExceptionInfo> iter =
                    exceptionInfo.listIterator(exceptionInfo.size());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1773]++;
byte CodeCoverTryBranchHelper_L53 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[157]++;


int CodeCoverConditionCoverageHelper_C276;
            while ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((iter.hasPrevious()) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false)) {
if (CodeCoverTryBranchHelper_L53 == 0) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[157]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[158]++;
} else if (CodeCoverTryBranchHelper_L53 == 1) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[158]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[159]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1774]++;
                ExceptionInfo ei = iter.previous();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1775]++;
byte CodeCoverTryBranchHelper_L54 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[160]++;


int CodeCoverConditionCoverageHelper_C277;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((i < EXCEPTION_MAX) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L54 == 0) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[160]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[161]++;
} else if (CodeCoverTryBranchHelper_L54 == 1) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[161]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[162]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1776]++;
int CodeCoverConditionCoverageHelper_C278;
                    if ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (8)) == 0 || true) &&
 ((ei.handlerLabels[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((ei.currentFinally == finallyBlock) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[622]++;
                        ei.exceptionStarts[i] = finallyEnd;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1777]++;
                        ei.currentFinally = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1778]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[623]++;}
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1779]++;
int CodeCoverConditionCoverageHelper_C279;
                if ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((ei.finallyBlock == finallyBlock) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[624]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1780]++;
                    break;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[625]++;}
            }
        }

        /**
         * Mark off the end of a bytecode chunk that should be handled by an
         * exceptionHandler.
         *
         * The caller of this method must appropriately mark the start of the
         * next bytecode chunk or remove the handler.
         */
        private void endCatch(ExceptionInfo ei, int exceptionType, int catchEnd)
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1781]++;
int CodeCoverConditionCoverageHelper_C280;
            if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((ei.exceptionStarts[exceptionType] == 0) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[626]++;
                throw new IllegalStateException("bad exception start");

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[627]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1782]++;

            int currentStart = ei.exceptionStarts[exceptionType];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1783]++;
            int currentStartPC = cfw.getLabelPC(currentStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1784]++;
            int catchEndPC = cfw.getLabelPC(catchEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1785]++;
int CodeCoverConditionCoverageHelper_C281;
            if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((currentStartPC != catchEndPC) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[628]++;
                cfw.addExceptionHandler(ei.exceptionStarts[exceptionType],
                                        catchEnd,
                                        ei.handlerLabels[exceptionType],
                                        exceptionTypeToName(exceptionType));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1786]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[629]++;}
        }

        private ExceptionInfo getTop()
        {
            return exceptionInfo.getLast();
        }

        private class ExceptionInfo
        {
            ExceptionInfo(Jump node, Node finallyBlock)
            {
                this.node = node;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1787]++;
                this.finallyBlock = finallyBlock;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1788]++;
                handlerLabels = new int[EXCEPTION_MAX];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1789]++;
                exceptionStarts = new int[EXCEPTION_MAX];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1790]++;
                currentFinally = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1791]++;
            }

            Jump node;
            Node finallyBlock;
            int[] handlerLabels;
            int[] exceptionStarts;
            // The current finally block that has temporarily ended the
            // exception handler ranges
            Node currentFinally;
        }

        // A stack of try/catch block information ordered by lexical scoping
        private LinkedList<ExceptionInfo> exceptionInfo;
    }

    private ExceptionManager exceptionManager = new ExceptionManager();
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1792]++;
  }

    /**
     * Inline a FINALLY node into the method bytecode.
     *
     * This method takes a label that points to the real start of the finally
     * block as implemented in the bytecode. This is because in some cases,
     * the finally block really starts before any of the code in the Node. For
     * example, the catch-all-rethrow finally block has a few instructions
     * prior to the finally block made by the user.
     *
     * In addition, an end label that should be unmarked is given as a method
     * parameter. It is the responsibility of any callers of this method to
     * mark the label.
     *
     * The start and end labels of the finally block are used to exclude the
     * inlined block from the proper exception handler. For example, an inlined
     * finally block should not be handled by a catch-all-rethrow.
     *
     * @param finallyTarget a TARGET node directly preceding a FINALLY node or
     *                      a FINALLY node itself
     * @param finallyStart a pre-marked label that indicates the actual start
     *                     of the finally block in the bytecode.
     * @param finallyEnd an unmarked label that will indicate the actual end
     *                   of the finally block in the bytecode.
     */
    private void inlineFinally(Node finallyTarget, int finallyStart,
                               int finallyEnd) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1793]++;
        Node fBlock = getFinallyAtTarget(finallyTarget);
        fBlock.resetTargets();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1794]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1795]++;
        Node child = fBlock.getFirstChild();
        exceptionManager.markInlineFinallyStart(fBlock, finallyStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1796]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1797]++;
byte CodeCoverTryBranchHelper_L55 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[163]++;


int CodeCoverConditionCoverageHelper_C282;
        while ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false)) {
if (CodeCoverTryBranchHelper_L55 == 0) {
  CodeCoverTryBranchHelper_L55++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[163]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[164]++;
} else if (CodeCoverTryBranchHelper_L55 == 1) {
  CodeCoverTryBranchHelper_L55++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[164]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[165]++;
}
            generateStatement(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1798]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1799]++;
        }
        exceptionManager.markInlineFinallyEnd(fBlock, finallyEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1800]++;
    }

    private void inlineFinally(Node finallyTarget) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1801]++;
        int finallyStart = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1802]++;
        int finallyEnd = cfw.acquireLabel();
        cfw.markLabel(finallyStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1803]++;
        inlineFinally(finallyTarget, finallyStart, finallyEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1804]++;
        cfw.markLabel(finallyEnd);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1805]++;
    }

    /**
     * Get a FINALLY node at a point in the IR.
     *
     * This is strongly dependent on the generated IR. If the node is a TARGET,
     * it only check the next node to see if it is a FINALLY node.
     */
    private Node getFinallyAtTarget(Node node) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1806]++;
int CodeCoverConditionCoverageHelper_C283;
        if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[630]++;
            return null;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[631]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1807]++;
int CodeCoverConditionCoverageHelper_C284; if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((node.getType() == Token.FINALLY) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[632]++;
            return node;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[633]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1808]++;
int CodeCoverConditionCoverageHelper_C285; if ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (8)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((node.getType() == Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[634]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1809]++;
            Node fBlock = node.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1810]++;
int CodeCoverConditionCoverageHelper_C286;
            if ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (8)) == 0 || true) &&
 ((fBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((fBlock.getType() == Token.FINALLY) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[636]++;
                return fBlock;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[637]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[635]++;}
}
}
        throw Kit.codeBug("bad finally target");
    }

    private boolean generateSaveLocals(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1811]++;
        int count = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1812]++;
byte CodeCoverTryBranchHelper_L56 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[166]++;


int CodeCoverConditionCoverageHelper_C287;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((i < firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L56 == 0) {
  CodeCoverTryBranchHelper_L56++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[166]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[167]++;
} else if (CodeCoverTryBranchHelper_L56 == 1) {
  CodeCoverTryBranchHelper_L56++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[167]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[168]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1813]++;
int CodeCoverConditionCoverageHelper_C288;
            if ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((locals[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[638]++;
                count++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1814]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[639]++;}
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1815]++;
int CodeCoverConditionCoverageHelper_C289;

        if ((((((CodeCoverConditionCoverageHelper_C289 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C289 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[640]++;
            ((FunctionNode)scriptOrFn).addLiveLocals(node, null);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1816]++;
            return false;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[641]++;}

        // calculate the max locals
        maxLocals = maxLocals > count ? maxLocals : count;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1817]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1818]++;

        // create a locals list
        int[] ls = new int[count];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1819]++;
        int s = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1820]++;
byte CodeCoverTryBranchHelper_L57 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[169]++;


int CodeCoverConditionCoverageHelper_C290;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 ((i < firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L57 == 0) {
  CodeCoverTryBranchHelper_L57++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[169]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[170]++;
} else if (CodeCoverTryBranchHelper_L57 == 1) {
  CodeCoverTryBranchHelper_L57++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[170]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[171]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1821]++;
int CodeCoverConditionCoverageHelper_C291;
            if ((((((CodeCoverConditionCoverageHelper_C291 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C291 |= (2)) == 0 || true) &&
 ((locals[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C291 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[642]++;
                ls[s] = i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1822]++;
                s++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1823]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[643]++;}
        }

        // save the locals
        ((FunctionNode)scriptOrFn).addLiveLocals(node, ls);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1824]++;

        // save locals
        generateGetGeneratorLocalsState();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1825]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1826]++;
byte CodeCoverTryBranchHelper_L58 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[172]++;


int CodeCoverConditionCoverageHelper_C292;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C292 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C292 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L58 == 0) {
  CodeCoverTryBranchHelper_L58++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[172]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[173]++;
} else if (CodeCoverTryBranchHelper_L58 == 1) {
  CodeCoverTryBranchHelper_L58++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[173]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[174]++;
}
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1827]++;
            cfw.addLoadConstant(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1828]++;
            cfw.addALoad(ls[i]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1829]++;
            cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1830]++;
        }
        // pop the array off the stack
        cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1831]++;

        return true;
    }

    private void visitSwitch(Jump switchNode, Node child)
    {
        // See comments in IRFactory.createSwitch() for description
        // of SWITCH node

        generateExpression(child, switchNode);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1832]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1833]++;
        // save selector value
        short selector = getNewWordLocal();
        cfw.addAStore(selector);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1834]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1835]++;
byte CodeCoverTryBranchHelper_L59 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[175]++;


int CodeCoverConditionCoverageHelper_C293;

        for (Jump caseNode = (Jump)child.getNext();(((((CodeCoverConditionCoverageHelper_C293 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C293 |= (2)) == 0 || true) &&
 ((caseNode != null) && 
  ((CodeCoverConditionCoverageHelper_C293 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) && false);
             caseNode = (Jump)caseNode.getNext())
        {
if (CodeCoverTryBranchHelper_L59 == 0) {
  CodeCoverTryBranchHelper_L59++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[175]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[176]++;
} else if (CodeCoverTryBranchHelper_L59 == 1) {
  CodeCoverTryBranchHelper_L59++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[176]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[177]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1836]++;
int CodeCoverConditionCoverageHelper_C294;
            if ((((((CodeCoverConditionCoverageHelper_C294 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C294 |= (2)) == 0 || true) &&
 ((caseNode.getType() != Token.CASE) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[644]++;
                throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[645]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1837]++;
            Node test = caseNode.getFirstChild();
            generateExpression(test, caseNode);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1838]++;
            cfw.addALoad(selector);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1839]++;
            addScriptRuntimeInvoke("shallowEq",
                                   "(Ljava/lang/Object;"
                                   +"Ljava/lang/Object;"
                                   +")Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1840]++;
            addGoto(caseNode.target, ByteCode.IFNE);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1841]++;
        }
        releaseWordLocal(selector);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1842]++;
    }

    private void visitTypeofname(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1843]++;
int CodeCoverConditionCoverageHelper_C295;
        if ((((((CodeCoverConditionCoverageHelper_C295 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C295 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[646]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1844]++;
            int varIndex = fnCurrent.fnode.getIndexForNameNode(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1845]++;
int CodeCoverConditionCoverageHelper_C296;
            if ((((((CodeCoverConditionCoverageHelper_C296 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C296 |= (2)) == 0 || true) &&
 ((varIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C296 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[648]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1846]++;
int CodeCoverConditionCoverageHelper_C297;
                if ((((((CodeCoverConditionCoverageHelper_C297 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C297 |= (2)) == 0 || true) &&
 ((fnCurrent.isNumberVar(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C297 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[650]++;
                    cfw.addPush("number");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1847]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[651]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1848]++;
int CodeCoverConditionCoverageHelper_C298; if ((((((CodeCoverConditionCoverageHelper_C298 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C298 |= (2)) == 0 || true) &&
 ((varIsDirectCallParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C298 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[652]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1849]++;
                    int dcp_register = varRegisters[varIndex];
                    cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1850]++;
                    cfw.add(ByteCode.GETSTATIC, "java/lang/Void", "TYPE",
                            "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1851]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1852]++;
                    int isNumberLabel = cfw.acquireLabel();
                    cfw.add(ByteCode.IF_ACMPEQ, isNumberLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1853]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1854]++;
                    short stack = cfw.getStackTop();
                    cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1855]++;
                    addScriptRuntimeInvoke("typeof",
                                           "(Ljava/lang/Object;"
                                           +")Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1856]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1857]++;
                    int beyond = cfw.acquireLabel();
                    cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1858]++;
                    cfw.markLabel(isNumberLabel, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1859]++;
                    cfw.addPush("number");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1860]++;
                    cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1861]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[653]++;
                    cfw.addALoad(varRegisters[varIndex]);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1862]++;
                    addScriptRuntimeInvoke("typeof",
                                           "(Ljava/lang/Object;"
                                           +")Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1863]++;
                }
}
                return;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[649]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[647]++;}
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1864]++;
        cfw.addPush(node.getString());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1865]++;
        addScriptRuntimeInvoke("typeofName",
                               "(Lorg/mozilla/javascript/Scriptable;"
                               +"Ljava/lang/String;"
                               +")Ljava/lang/String;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1866]++;
    }

    /**
     * Save the current code offset. This saved code offset is used to
     * compute instruction counts in subsequent calls to
     * {@link #addInstructionCount()}.
     */
    private void saveCurrentCodeOffset() {
        savedCodeOffset = cfw.getCurrentCodeOffset();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1867]++;
    }

    /**
     * Generate calls to ScriptRuntime.addInstructionCount to keep track of
     * executed instructions and call <code>observeInstructionCount()</code>
     * if a threshold is exceeded.<br>
     * Calculates the count from getCurrentCodeOffset - savedCodeOffset
     */
    private void addInstructionCount() {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1868]++;
        int count = cfw.getCurrentCodeOffset() - savedCodeOffset;
        // TODO we used to return for count == 0 but that broke the following:
        //    while(true) continue; (see bug 531600)
        // To be safe, we now always count at least 1 instruction when invoked.
        addInstructionCount(Math.max(count, 1));
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1869]++;
    }

    /**
     * Generate calls to ScriptRuntime.addInstructionCount to keep track of
     * executed instructions and call <code>observeInstructionCount()</code>
     * if a threshold is exceeded.<br>
     * Takes the count as a parameter - used to add monitoring to loops and
     * other blocks that don't have any ops - this allows
     * for monitoring/killing of while(true) loops and such.
     */
    private void addInstructionCount(int count) {
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1870]++;
        cfw.addPush(count);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1871]++;
        addScriptRuntimeInvoke("addInstructionCount",
                "(Lorg/mozilla/javascript/Context;"
                +"I)V");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1872]++;
    }

    private void visitIncDec(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1873]++;
        int incrDecrMask = node.getExistingIntProp(Node.INCRDECR_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1874]++;
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1875]++;
        switch (child.getType()) {
          case Token.GETVAR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[654]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1876]++;
int CodeCoverConditionCoverageHelper_C299;
            if ((((((CodeCoverConditionCoverageHelper_C299 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C299 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C299 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[655]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1877]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[656]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1878]++;
            boolean post = ((incrDecrMask & Node.POST_FLAG) != 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1879]++;
            int varIndex = fnCurrent.getVarIndex(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1880]++;
            short reg = varRegisters[varIndex];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1881]++;
int CodeCoverConditionCoverageHelper_C300;
            if ((((((CodeCoverConditionCoverageHelper_C300 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C300 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[657]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1882]++;
                int offset = varIsDirectCallParameter(varIndex) ? 1 : 0;
                cfw.addDLoad(reg + offset);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1883]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1884]++;
int CodeCoverConditionCoverageHelper_C301;
                if ((((((CodeCoverConditionCoverageHelper_C301 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C301 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[659]++;
                    cfw.add(ByteCode.DUP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1885]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[660]++;}
                cfw.addPush(1.0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1886]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1887]++;
int CodeCoverConditionCoverageHelper_C302;
                if ((((((CodeCoverConditionCoverageHelper_C302 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C302 |= (2)) == 0 || true) &&
 (((incrDecrMask & Node.DECR_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C302 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[661]++;
                    cfw.add(ByteCode.DADD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1888]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[662]++;
                    cfw.add(ByteCode.DSUB);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1889]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1890]++;
int CodeCoverConditionCoverageHelper_C303;
                if ((((((CodeCoverConditionCoverageHelper_C303 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C303 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C303 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[663]++;
                    cfw.add(ByteCode.DUP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1891]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[664]++;}
                cfw.addDStore(reg + offset);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1892]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[658]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1893]++;
int CodeCoverConditionCoverageHelper_C304;
                if ((((((CodeCoverConditionCoverageHelper_C304 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C304 |= (2)) == 0 || true) &&
 ((varIsDirectCallParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C304 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[665]++;
                    dcpLoadAsObject(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1894]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[666]++;
                    cfw.addALoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1895]++;
                }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1896]++;
int CodeCoverConditionCoverageHelper_C305;
                if ((((((CodeCoverConditionCoverageHelper_C305 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C305 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C305 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[667]++;
                    cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1897]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[668]++;}
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1898]++;
                cfw.addPush(1.0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1899]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1900]++;
int CodeCoverConditionCoverageHelper_C306;
                if ((((((CodeCoverConditionCoverageHelper_C306 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C306 |= (2)) == 0 || true) &&
 (((incrDecrMask & Node.DECR_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C306 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[669]++;
                    cfw.add(ByteCode.DADD);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1901]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[670]++;
                    cfw.add(ByteCode.DSUB);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1902]++;
                }
                addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1903]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1904]++;
int CodeCoverConditionCoverageHelper_C307;
                if ((((((CodeCoverConditionCoverageHelper_C307 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C307 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C307 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[671]++;
                    cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1905]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[672]++;}
                cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1906]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1907]++;
                break;
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1908]++;
            break;
          case Token.NAME:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[673]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1909]++;
            cfw.addPush(child.getString());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1910]++;          // push name
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1911]++;
            cfw.addPush(incrDecrMask);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1912]++;
            addScriptRuntimeInvoke("nameIncrDecr",
                "(Lorg/mozilla/javascript/Scriptable;"
                +"Ljava/lang/String;"
                +"Lorg/mozilla/javascript/Context;"
                +"I)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1913]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1914]++;
            break;
          case Token.GETPROPNOWARN:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[674]++;
            throw Kit.codeBug();
          case Token.GETPROP:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[675]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1915]++;
            Node getPropChild = child.getFirstChild();
            generateExpression(getPropChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1916]++;
            generateExpression(getPropChild.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1917]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1918]++;
            cfw.addPush(incrDecrMask);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1919]++;
            addScriptRuntimeInvoke("propIncrDecr",
                                   "(Ljava/lang/Object;"
                                   +"Ljava/lang/String;"
                                   +"Lorg/mozilla/javascript/Context;"
                                   +"I)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1920]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1921]++;
            break;
          }
          case Token.GETELEM:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[676]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1922]++;
            Node elemChild = child.getFirstChild();
            generateExpression(elemChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1923]++;
            generateExpression(elemChild.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1924]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1925]++;
            cfw.addPush(incrDecrMask);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1926]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1927]++;
int CodeCoverConditionCoverageHelper_C308;
            if ((((((CodeCoverConditionCoverageHelper_C308 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C308 |= (2)) == 0 || true) &&
 ((elemChild.getNext().getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C308 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[677]++;
              addOptRuntimeInvoke("elemIncrDecr",
                  "(Ljava/lang/Object;"
                  +"D"
                  +"Lorg/mozilla/javascript/Context;"
                  +"I"
                  +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1928]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[678]++;
              addScriptRuntimeInvoke("elemIncrDecr",
                  "(Ljava/lang/Object;"
                  +"Ljava/lang/Object;"
                  +"Lorg/mozilla/javascript/Context;"
                  +"I"
                  +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1929]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1930]++;
            break;
          }
          case Token.GET_REF:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[679]++; {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1931]++;
            Node refChild = child.getFirstChild();
            generateExpression(refChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1932]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1933]++;
            cfw.addPush(incrDecrMask);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1934]++;
            addScriptRuntimeInvoke(
                "refIncrDecr",
                "(Lorg/mozilla/javascript/Ref;"
                +"Lorg/mozilla/javascript/Context;"
                +"I)Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1935]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1936]++;
            break;
          }
          default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[680]++;
            Codegen.badTree();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1937]++;
        }
    }

    private static boolean isArithmeticNode(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1938]++;
        int type = node.getType();
        return (type == Token.SUB)
                  || (type == Token.MOD)
                        || (type == Token.DIV)
                              || (type == Token.MUL);
    }

    private void visitArithmetic(Node node, int opCode, Node child,
                                 Node parent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1939]++;
        int childNumberFlag = node.getIntProp(Node.ISNUMBER_PROP, -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1940]++;
int CodeCoverConditionCoverageHelper_C309;
        if ((((((CodeCoverConditionCoverageHelper_C309 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C309 |= (2)) == 0 || true) &&
 ((childNumberFlag != -1) && 
  ((CodeCoverConditionCoverageHelper_C309 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[681]++;
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1941]++;
            generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1942]++;
            cfw.add(opCode);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1943]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[682]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1944]++;
            boolean childOfArithmetic = isArithmeticNode(parent);
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1945]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1946]++;
int CodeCoverConditionCoverageHelper_C310;
            if ((((((CodeCoverConditionCoverageHelper_C310 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C310 |= (2)) == 0 || true) &&
 ((isArithmeticNode(child)) && 
  ((CodeCoverConditionCoverageHelper_C310 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[683]++;
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1947]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[684]++;}
            generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1948]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1949]++;
int CodeCoverConditionCoverageHelper_C311;
            if ((((((CodeCoverConditionCoverageHelper_C311 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C311 |= (2)) == 0 || true) &&
 ((isArithmeticNode(child.getNext())) && 
  ((CodeCoverConditionCoverageHelper_C311 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[685]++;
                  addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1950]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[686]++;}
            cfw.add(opCode);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1951]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1952]++;
int CodeCoverConditionCoverageHelper_C312;
            if ((((((CodeCoverConditionCoverageHelper_C312 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C312 |= (2)) == 0 || true) &&
 ((childOfArithmetic) && 
  ((CodeCoverConditionCoverageHelper_C312 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[687]++;
                addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1953]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[688]++;}
        }
    }

    private void visitBitOp(Node node, int type, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1954]++;
        int childNumberFlag = node.getIntProp(Node.ISNUMBER_PROP, -1);
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1955]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1956]++;
int CodeCoverConditionCoverageHelper_C313;

        // special-case URSH; work with the target arg as a long, so
        // that we can return a 32-bit unsigned value, and call
        // toUint32 instead of toInt32.
        if ((((((CodeCoverConditionCoverageHelper_C313 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C313 |= (2)) == 0 || true) &&
 ((type == Token.URSH) && 
  ((CodeCoverConditionCoverageHelper_C313 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[689]++;
            addScriptRuntimeInvoke("toUint32", "(Ljava/lang/Object;)J");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1957]++;
            generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1958]++;
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1959]++;
            // Looks like we need to explicitly mask the shift to 5 bits -
            // LUSHR takes 6 bits.
            cfw.addPush(31);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1960]++;
            cfw.add(ByteCode.IAND);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1961]++;
            cfw.add(ByteCode.LUSHR);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1962]++;
            cfw.add(ByteCode.L2D);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1963]++;
            addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1964]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[690]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1965]++;
int CodeCoverConditionCoverageHelper_C314;
        if ((((((CodeCoverConditionCoverageHelper_C314 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C314 |= (2)) == 0 || true) &&
 ((childNumberFlag == -1) && 
  ((CodeCoverConditionCoverageHelper_C314 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[691]++;
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1966]++;
            generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1967]++;
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1968]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[692]++;
            addScriptRuntimeInvoke("toInt32", "(D)I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1969]++;
            generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1970]++;
            addScriptRuntimeInvoke("toInt32", "(D)I");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1971]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1972]++;
        switch (type) {
          case Token.BITOR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[693]++;
            cfw.add(ByteCode.IOR);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1973]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1974]++;
            break;
          case Token.BITXOR:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[694]++;
            cfw.add(ByteCode.IXOR);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1975]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1976]++;
            break;
          case Token.BITAND:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[695]++;
            cfw.add(ByteCode.IAND);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1977]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1978]++;
            break;
          case Token.RSH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[696]++;
            cfw.add(ByteCode.ISHR);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1979]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1980]++;
            break;
          case Token.LSH:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[697]++;
            cfw.add(ByteCode.ISHL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1981]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1982]++;
            break;
          default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[698]++;
            throw Codegen.badTree();
        }
        cfw.add(ByteCode.I2D);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1983]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1984]++;
int CodeCoverConditionCoverageHelper_C315;
        if ((((((CodeCoverConditionCoverageHelper_C315 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C315 |= (2)) == 0 || true) &&
 ((childNumberFlag == -1) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[699]++;
            addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1985]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[700]++;}
    }

    private int nodeIsDirectCallParameter(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1986]++;
int CodeCoverConditionCoverageHelper_C316;
        if ((((((CodeCoverConditionCoverageHelper_C316 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C316 |= (32)) == 0 || true) &&
 ((node.getType() == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C316 |= (8)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C316 |= (2)) == 0 || true) &&
 ((itsForcedObjectParameters) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 3) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 3) && false))
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[701]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1987]++;
            int varIndex = fnCurrent.getVarIndex(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1988]++;
int CodeCoverConditionCoverageHelper_C317;
            if ((((((CodeCoverConditionCoverageHelper_C317 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C317 |= (2)) == 0 || true) &&
 ((fnCurrent.isParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[703]++;
                return varRegisters[varIndex];

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[704]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[702]++;}
        return -1;
    }

    private boolean varIsDirectCallParameter(int varIndex)
    {
        return fnCurrent.isParameter(varIndex)
            && inDirectCallFunction && !itsForcedObjectParameters;
    }

    private void genSimpleCompare(int type, int trueGOTO, int falseGOTO)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1989]++;
int CodeCoverConditionCoverageHelper_C318;
        if ((((((CodeCoverConditionCoverageHelper_C318 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C318 |= (2)) == 0 || true) &&
 ((trueGOTO == -1) && 
  ((CodeCoverConditionCoverageHelper_C318 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[705]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[706]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1990]++;
        switch (type) {
            case Token.LE :
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[707]++;
                cfw.add(ByteCode.DCMPG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1991]++;
                cfw.add(ByteCode.IFLE, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1992]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1993]++;
                break;
            case Token.GE :
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[708]++;
                cfw.add(ByteCode.DCMPL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1994]++;
                cfw.add(ByteCode.IFGE, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1995]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1996]++;
                break;
            case Token.LT :
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[709]++;
                cfw.add(ByteCode.DCMPG);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1997]++;
                cfw.add(ByteCode.IFLT, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1998]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[1999]++;
                break;
            case Token.GT :
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[710]++;
                cfw.add(ByteCode.DCMPL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2000]++;
                cfw.add(ByteCode.IFGT, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2001]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2002]++;
                break;
            default :
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[711]++;
                throw Codegen.badTree();

        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2003]++;
int CodeCoverConditionCoverageHelper_C319;
        if ((((((CodeCoverConditionCoverageHelper_C319 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C319 |= (2)) == 0 || true) &&
 ((falseGOTO != -1) && 
  ((CodeCoverConditionCoverageHelper_C319 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[712]++;
            cfw.add(ByteCode.GOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2004]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[713]++;}
    }

    private void visitIfJumpRelOp(Node node, Node child,
                                  int trueGOTO, int falseGOTO)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2005]++;
int CodeCoverConditionCoverageHelper_C320;
        if ((((((CodeCoverConditionCoverageHelper_C320 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C320 |= (8)) == 0 || true) &&
 ((trueGOTO == -1) && 
  ((CodeCoverConditionCoverageHelper_C320 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C320 |= (2)) == 0 || true) &&
 ((falseGOTO == -1) && 
  ((CodeCoverConditionCoverageHelper_C320 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[714]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[715]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2006]++;
        int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2007]++;
        Node rChild = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2008]++;
int CodeCoverConditionCoverageHelper_C321;
        if ((((((CodeCoverConditionCoverageHelper_C321 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C321 |= (8)) == 0 || true) &&
 ((type == Token.INSTANCEOF) && 
  ((CodeCoverConditionCoverageHelper_C321 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C321 |= (2)) == 0 || true) &&
 ((type == Token.IN) && 
  ((CodeCoverConditionCoverageHelper_C321 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[716]++;
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2009]++;
            generateExpression(rChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2010]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2011]++;
            addScriptRuntimeInvoke(
                (type == Token.INSTANCEOF) ? "instanceOf" : "in",
                "(Ljava/lang/Object;"
                +"Ljava/lang/Object;"
                +"Lorg/mozilla/javascript/Context;"
                +")Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2012]++;
            cfw.add(ByteCode.IFNE, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2013]++;
            cfw.add(ByteCode.GOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2014]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[717]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2015]++;
        int childNumberFlag = node.getIntProp(Node.ISNUMBER_PROP, -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2016]++;
        int left_dcp_register = nodeIsDirectCallParameter(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2017]++;
        int right_dcp_register = nodeIsDirectCallParameter(rChild);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2018]++;
int CodeCoverConditionCoverageHelper_C322;
        if ((((((CodeCoverConditionCoverageHelper_C322 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C322 |= (2)) == 0 || true) &&
 ((childNumberFlag != -1) && 
  ((CodeCoverConditionCoverageHelper_C322 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[718]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2019]++;
int CodeCoverConditionCoverageHelper_C323;
            // Force numeric context on both parameters and optimize
            // direct call case as Optimizer currently does not handle it

            if ((((((CodeCoverConditionCoverageHelper_C323 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C323 |= (2)) == 0 || true) &&
 ((childNumberFlag != Node.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C323 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[720]++;
                // Left already has number content
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2020]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[721]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2021]++;
int CodeCoverConditionCoverageHelper_C324; if ((((((CodeCoverConditionCoverageHelper_C324 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C324 |= (2)) == 0 || true) &&
 ((left_dcp_register != -1) && 
  ((CodeCoverConditionCoverageHelper_C324 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[722]++;
                dcpLoadAsNumber(left_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2022]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[723]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2023]++;
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2024]++;
            }
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2025]++;
int CodeCoverConditionCoverageHelper_C325;

            if ((((((CodeCoverConditionCoverageHelper_C325 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C325 |= (2)) == 0 || true) &&
 ((childNumberFlag != Node.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C325 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[724]++;
                // Right already has number content
                generateExpression(rChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2026]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[725]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2027]++;
int CodeCoverConditionCoverageHelper_C326; if ((((((CodeCoverConditionCoverageHelper_C326 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C326 |= (2)) == 0 || true) &&
 ((right_dcp_register != -1) && 
  ((CodeCoverConditionCoverageHelper_C326 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[726]++;
                dcpLoadAsNumber(right_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2028]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[727]++;
                generateExpression(rChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2029]++;
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2030]++;
            }
}

            genSimpleCompare(type, trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2031]++;


        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[719]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2032]++;
int CodeCoverConditionCoverageHelper_C327;
            if ((((((CodeCoverConditionCoverageHelper_C327 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C327 |= (8)) == 0 || true) &&
 ((left_dcp_register != -1) && 
  ((CodeCoverConditionCoverageHelper_C327 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C327 |= (2)) == 0 || true) &&
 ((right_dcp_register != -1) && 
  ((CodeCoverConditionCoverageHelper_C327 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[327].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C327, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[327].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C327, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[728]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2033]++;
                // Generate code to dynamically check for number content
                // if both operands are dcp
                short stack = cfw.getStackTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2034]++;
                int leftIsNotNumber = cfw.acquireLabel();
                cfw.addALoad(left_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2035]++;
                cfw.add(ByteCode.GETSTATIC,
                        "java/lang/Void",
                        "TYPE",
                        "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2036]++;
                cfw.add(ByteCode.IF_ACMPNE, leftIsNotNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2037]++;
                cfw.addDLoad(left_dcp_register + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2038]++;
                dcpLoadAsNumber(right_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2039]++;
                genSimpleCompare(type, trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2040]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2041]++;
int CodeCoverConditionCoverageHelper_C328;
                if ((((((CodeCoverConditionCoverageHelper_C328 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C328 |= (2)) == 0 || true) &&
 ((stack != cfw.getStackTop()) && 
  ((CodeCoverConditionCoverageHelper_C328 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[730]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[731]++;}

                cfw.markLabel(leftIsNotNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2042]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2043]++;
                int rightIsNotNumber = cfw.acquireLabel();
                cfw.addALoad(right_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2044]++;
                cfw.add(ByteCode.GETSTATIC,
                        "java/lang/Void",
                        "TYPE",
                        "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2045]++;
                cfw.add(ByteCode.IF_ACMPNE, rightIsNotNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2046]++;
                cfw.addALoad(left_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2047]++;
                addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2048]++;
                cfw.addDLoad(right_dcp_register + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2049]++;
                genSimpleCompare(type, trueGOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2050]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2051]++;
int CodeCoverConditionCoverageHelper_C329;
                if ((((((CodeCoverConditionCoverageHelper_C329 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C329 |= (2)) == 0 || true) &&
 ((stack != cfw.getStackTop()) && 
  ((CodeCoverConditionCoverageHelper_C329 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[732]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[733]++;}

                cfw.markLabel(rightIsNotNumber);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2052]++;
                // Load both register as objects to call generic cmp_*
                cfw.addALoad(left_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2053]++;
                cfw.addALoad(right_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2054]++;


            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[729]++;
                generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2055]++;
                generateExpression(rChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2056]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2057]++;
int CodeCoverConditionCoverageHelper_C330;

            if ((((((CodeCoverConditionCoverageHelper_C330 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C330 |= (8)) == 0 || true) &&
 ((type == Token.GE) && 
  ((CodeCoverConditionCoverageHelper_C330 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C330 |= (2)) == 0 || true) &&
 ((type == Token.GT) && 
  ((CodeCoverConditionCoverageHelper_C330 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[734]++;
                cfw.add(ByteCode.SWAP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2058]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[735]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2059]++;
            String routine = ((type == Token.LT)
                      || (type == Token.GT)) ? "cmp_LT" : "cmp_LE";
            addScriptRuntimeInvoke(routine,
                                   "(Ljava/lang/Object;"
                                   +"Ljava/lang/Object;"
                                   +")Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2060]++;
            cfw.add(ByteCode.IFNE, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2061]++;
            cfw.add(ByteCode.GOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2062]++;
        }
    }

    private void visitIfJumpEqOp(Node node, Node child,
                                 int trueGOTO, int falseGOTO)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2063]++;
int CodeCoverConditionCoverageHelper_C331;
        if ((((((CodeCoverConditionCoverageHelper_C331 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C331 |= (8)) == 0 || true) &&
 ((trueGOTO == -1) && 
  ((CodeCoverConditionCoverageHelper_C331 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C331 |= (2)) == 0 || true) &&
 ((falseGOTO == -1) && 
  ((CodeCoverConditionCoverageHelper_C331 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[736]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[737]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2064]++;

        short stackInitial = cfw.getStackTop();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2065]++;
        int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2066]++;
        Node rChild = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2067]++;
int CodeCoverConditionCoverageHelper_C332;

        // Optimize if one of operands is null
        if ((((((CodeCoverConditionCoverageHelper_C332 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C332 |= (8)) == 0 || true) &&
 ((child.getType() == Token.NULL) && 
  ((CodeCoverConditionCoverageHelper_C332 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C332 |= (2)) == 0 || true) &&
 ((rChild.getType() == Token.NULL) && 
  ((CodeCoverConditionCoverageHelper_C332 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[738]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2068]++;
int CodeCoverConditionCoverageHelper_C333;
            // eq is symmetric in this case
            if ((((((CodeCoverConditionCoverageHelper_C333 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C333 |= (2)) == 0 || true) &&
 ((child.getType() == Token.NULL) && 
  ((CodeCoverConditionCoverageHelper_C333 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[740]++;
                child = rChild;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2069]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[741]++;}
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2070]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2071]++;
int CodeCoverConditionCoverageHelper_C334;
            if ((((((CodeCoverConditionCoverageHelper_C334 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C334 |= (8)) == 0 || true) &&
 ((type == Token.SHEQ) && 
  ((CodeCoverConditionCoverageHelper_C334 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C334 |= (2)) == 0 || true) &&
 ((type == Token.SHNE) && 
  ((CodeCoverConditionCoverageHelper_C334 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[742]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2072]++;
                int testCode = (type == Token.SHEQ)
                                ? ByteCode.IFNULL : ByteCode.IFNONNULL;
                cfw.add(testCode, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2073]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[743]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2074]++;
int CodeCoverConditionCoverageHelper_C335;
                if ((((((CodeCoverConditionCoverageHelper_C335 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C335 |= (2)) == 0 || true) &&
 ((type != Token.EQ) && 
  ((CodeCoverConditionCoverageHelper_C335 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[744]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2075]++;
int CodeCoverConditionCoverageHelper_C336;
                    // swap false/true targets for !=
                    if ((((((CodeCoverConditionCoverageHelper_C336 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C336 |= (2)) == 0 || true) &&
 ((type != Token.NE) && 
  ((CodeCoverConditionCoverageHelper_C336 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[746]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[747]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2076]++;
                    int tmp = trueGOTO;
                    trueGOTO = falseGOTO;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2077]++;
                    falseGOTO = tmp;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2078]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[745]++;}
                cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2079]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2080]++;
                int undefCheckLabel = cfw.acquireLabel();
                cfw.add(ByteCode.IFNONNULL, undefCheckLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2081]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2082]++;
                short stack = cfw.getStackTop();
                cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2083]++;
                cfw.add(ByteCode.GOTO, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2084]++;
                cfw.markLabel(undefCheckLabel, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2085]++;
                Codegen.pushUndefined(cfw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2086]++;
                cfw.add(ByteCode.IF_ACMPEQ, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2087]++;
            }
            cfw.add(ByteCode.GOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2088]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[739]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2089]++;
            int child_dcp_register = nodeIsDirectCallParameter(child);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2090]++;
int CodeCoverConditionCoverageHelper_C337;
            if ((((((CodeCoverConditionCoverageHelper_C337 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C337 |= (8)) == 0 || true) &&
 ((child_dcp_register != -1) && 
  ((CodeCoverConditionCoverageHelper_C337 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C337 |= (2)) == 0 || true) &&
 ((rChild.getType() == Token.TO_OBJECT) && 
  ((CodeCoverConditionCoverageHelper_C337 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 2) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[748]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2091]++;
                Node convertChild = rChild.getFirstChild();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2092]++;
int CodeCoverConditionCoverageHelper_C338;
                if ((((((CodeCoverConditionCoverageHelper_C338 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C338 |= (2)) == 0 || true) &&
 ((convertChild.getType() == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C338 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[750]++;
                    cfw.addALoad(child_dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2093]++;
                    cfw.add(ByteCode.GETSTATIC,
                            "java/lang/Void",
                            "TYPE",
                            "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2094]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2095]++;
                    int notNumbersLabel = cfw.acquireLabel();
                    cfw.add(ByteCode.IF_ACMPNE, notNumbersLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2096]++;
                    cfw.addDLoad(child_dcp_register + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2097]++;
                    cfw.addPush(convertChild.getDouble());
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2098]++;
                    cfw.add(ByteCode.DCMPL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2099]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2100]++;
int CodeCoverConditionCoverageHelper_C339;
                    if ((((((CodeCoverConditionCoverageHelper_C339 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C339 |= (2)) == 0 || true) &&
 ((type == Token.EQ) && 
  ((CodeCoverConditionCoverageHelper_C339 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[752]++;
                        cfw.add(ByteCode.IFEQ, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2101]++;
}
                    else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[753]++;
                        cfw.add(ByteCode.IFNE, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2102]++;
}
                    cfw.add(ByteCode.GOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2103]++;
                    cfw.markLabel(notNumbersLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2104]++;

                    // fall thru into generic handling
                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[751]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[749]++;}

            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2105]++;
            generateExpression(rChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2106]++;

            String name;
            int testCode;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2107]++;
            switch (type) {
              case Token.EQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[754]++;
                name = "eq";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2108]++;
                testCode = ByteCode.IFNE;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2109]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2110]++;
                break;
              case Token.NE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[755]++;
                name = "eq";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2111]++;
                testCode = ByteCode.IFEQ;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2112]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2113]++;
                break;
              case Token.SHEQ:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[756]++;
                name = "shallowEq";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2114]++;
                testCode = ByteCode.IFNE;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2115]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2116]++;
                break;
              case Token.SHNE:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[757]++;
                name = "shallowEq";
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2117]++;
                testCode = ByteCode.IFEQ;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2118]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2119]++;
                break;
              default:
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[758]++;
                throw Codegen.badTree();
            }
            addScriptRuntimeInvoke(name,
                                   "(Ljava/lang/Object;"
                                   +"Ljava/lang/Object;"
                                   +")Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2120]++;
            cfw.add(testCode, trueGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2121]++;
            cfw.add(ByteCode.GOTO, falseGOTO);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2122]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2123]++;
int CodeCoverConditionCoverageHelper_C340;
        if ((((((CodeCoverConditionCoverageHelper_C340 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C340 |= (2)) == 0 || true) &&
 ((stackInitial != cfw.getStackTop()) && 
  ((CodeCoverConditionCoverageHelper_C340 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[759]++; throw Codegen.badTree();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[760]++;}
    }

    private void visitSetName(Node node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2124]++;
        String name = node.getFirstChild().getString();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2125]++;
byte CodeCoverTryBranchHelper_L60 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[178]++;


int CodeCoverConditionCoverageHelper_C341;
        while ((((((CodeCoverConditionCoverageHelper_C341 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C341 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C341 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) && false)) {
if (CodeCoverTryBranchHelper_L60 == 0) {
  CodeCoverTryBranchHelper_L60++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[178]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[179]++;
} else if (CodeCoverTryBranchHelper_L60 == 1) {
  CodeCoverTryBranchHelper_L60++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[179]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[180]++;
}
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2126]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2127]++;
        }
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2128]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2129]++;
        cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2130]++;
        addScriptRuntimeInvoke(
            "setName",
            "(Lorg/mozilla/javascript/Scriptable;"
            +"Ljava/lang/Object;"
            +"Lorg/mozilla/javascript/Context;"
            +"Lorg/mozilla/javascript/Scriptable;"
            +"Ljava/lang/String;"
            +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2131]++;
    }

    private void visitStrictSetName(Node node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2132]++;
        String name = node.getFirstChild().getString();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2133]++;
byte CodeCoverTryBranchHelper_L61 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[181]++;


int CodeCoverConditionCoverageHelper_C342;
        while ((((((CodeCoverConditionCoverageHelper_C342 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C342 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C342 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) && false)) {
if (CodeCoverTryBranchHelper_L61 == 0) {
  CodeCoverTryBranchHelper_L61++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[181]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[182]++;
} else if (CodeCoverTryBranchHelper_L61 == 1) {
  CodeCoverTryBranchHelper_L61++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[182]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[183]++;
}
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2134]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2135]++;
        }
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2136]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2137]++;
        cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2138]++;
        addScriptRuntimeInvoke(
            "strictSetName",
            "(Lorg/mozilla/javascript/Scriptable;"
            +"Ljava/lang/Object;"
            +"Lorg/mozilla/javascript/Context;"
            +"Lorg/mozilla/javascript/Scriptable;"
            +"Ljava/lang/String;"
            +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2139]++;
    }

    private void visitSetConst(Node node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2140]++;
        String name = node.getFirstChild().getString();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2141]++;
byte CodeCoverTryBranchHelper_L62 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[184]++;


int CodeCoverConditionCoverageHelper_C343;
        while ((((((CodeCoverConditionCoverageHelper_C343 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C343 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C343 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) && false)) {
if (CodeCoverTryBranchHelper_L62 == 0) {
  CodeCoverTryBranchHelper_L62++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[184]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[185]++;
} else if (CodeCoverTryBranchHelper_L62 == 1) {
  CodeCoverTryBranchHelper_L62++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[185]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[186]++;
}
            generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2142]++;
            child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2143]++;
        }
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2144]++;
        cfw.addPush(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2145]++;
        addScriptRuntimeInvoke(
            "setConst",
            "(Lorg/mozilla/javascript/Scriptable;"
            +"Ljava/lang/Object;"
            +"Lorg/mozilla/javascript/Context;"
            +"Ljava/lang/String;"
            +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2146]++;
    }

    private void visitGetVar(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2147]++;
int CodeCoverConditionCoverageHelper_C344;
        if ((((((CodeCoverConditionCoverageHelper_C344 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C344 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C344 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[761]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2148]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[762]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2149]++;
        int varIndex = fnCurrent.getVarIndex(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2150]++;
        short reg = varRegisters[varIndex];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2151]++;
int CodeCoverConditionCoverageHelper_C345;
        if ((((((CodeCoverConditionCoverageHelper_C345 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C345 |= (2)) == 0 || true) &&
 ((varIsDirectCallParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C345 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[763]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2152]++;
int CodeCoverConditionCoverageHelper_C346;
            // Remember that here the isNumber flag means that we
            // want to use the incoming parameter in a Number
            // context, so test the object type and convert the
            //  value as necessary.
            if ((((((CodeCoverConditionCoverageHelper_C346 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C346 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.ISNUMBER_PROP, -1) != -1) && 
  ((CodeCoverConditionCoverageHelper_C346 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[765]++;
                dcpLoadAsNumber(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2153]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[766]++;
                dcpLoadAsObject(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2154]++;
            }

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[764]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2155]++;
int CodeCoverConditionCoverageHelper_C347; if ((((((CodeCoverConditionCoverageHelper_C347 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C347 |= (2)) == 0 || true) &&
 ((fnCurrent.isNumberVar(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C347 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[347].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C347, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[347].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C347, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[767]++;
            cfw.addDLoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2156]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[768]++;
            cfw.addALoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2157]++;
        }
}
    }

    private void visitSetVar(Node node, Node child, boolean needValue)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2158]++;
int CodeCoverConditionCoverageHelper_C348;
        if ((((((CodeCoverConditionCoverageHelper_C348 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C348 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C348 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[348].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C348, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[348].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C348, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[769]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2159]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[770]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2160]++;
        int varIndex = fnCurrent.getVarIndex(node);
        generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2161]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2162]++;
        boolean isNumber = (node.getIntProp(Node.ISNUMBER_PROP, -1) != -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2163]++;
        short reg = varRegisters[varIndex];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2164]++;
        boolean [] constDeclarations = fnCurrent.fnode.getParamAndVarConst();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2165]++;
int CodeCoverConditionCoverageHelper_C349;
        if ((((((CodeCoverConditionCoverageHelper_C349 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C349 |= (2)) == 0 || true) &&
 ((constDeclarations[varIndex]) && 
  ((CodeCoverConditionCoverageHelper_C349 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[349].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C349, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[349].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C349, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[771]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2166]++;
int CodeCoverConditionCoverageHelper_C350;
            if ((((((CodeCoverConditionCoverageHelper_C350 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C350 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C350 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[350].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C350, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[350].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C350, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[773]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2167]++;
int CodeCoverConditionCoverageHelper_C351;
                if ((((((CodeCoverConditionCoverageHelper_C351 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C351 |= (2)) == 0 || true) &&
 ((isNumber) && 
  ((CodeCoverConditionCoverageHelper_C351 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[351].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C351, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[351].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C351, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[775]++;
                    cfw.add(ByteCode.POP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2168]++;
}
                else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[776]++;
                    cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2169]++;
}

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[774]++;}

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[772]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2170]++;
int CodeCoverConditionCoverageHelper_C352; if ((((((CodeCoverConditionCoverageHelper_C352 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C352 |= (2)) == 0 || true) &&
 ((varIsDirectCallParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C352 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[352].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C352, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[352].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C352, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[777]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2171]++;
int CodeCoverConditionCoverageHelper_C353;
            if ((((((CodeCoverConditionCoverageHelper_C353 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C353 |= (2)) == 0 || true) &&
 ((isNumber) && 
  ((CodeCoverConditionCoverageHelper_C353 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[353].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C353, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[353].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C353, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[779]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2172]++;
int CodeCoverConditionCoverageHelper_C354;
                if ((((((CodeCoverConditionCoverageHelper_C354 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C354 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C354 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[354].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C354, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[354].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C354, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[781]++; cfw.add(ByteCode.DUP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2173]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[782]++;}
                cfw.addALoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2174]++;
                cfw.add(ByteCode.GETSTATIC,
                        "java/lang/Void",
                        "TYPE",
                        "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2175]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2176]++;
                int isNumberLabel = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2177]++;
                int beyond = cfw.acquireLabel();
                cfw.add(ByteCode.IF_ACMPEQ, isNumberLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2178]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2179]++;
                short stack = cfw.getStackTop();
                addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2180]++;
                cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2181]++;
                cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2182]++;
                cfw.markLabel(isNumberLabel, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2183]++;
                cfw.addDStore(reg + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2184]++;
                cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2185]++;

            }
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[780]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2186]++;
int CodeCoverConditionCoverageHelper_C355;
                if ((((((CodeCoverConditionCoverageHelper_C355 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C355 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C355 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[355].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C355, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[355].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C355, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[783]++; cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2187]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[784]++;}
                cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2188]++;
            }

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[778]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2189]++;
            boolean isNumberVar = fnCurrent.isNumberVar(varIndex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2190]++;
int CodeCoverConditionCoverageHelper_C356;
            if ((((((CodeCoverConditionCoverageHelper_C356 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C356 |= (2)) == 0 || true) &&
 ((isNumber) && 
  ((CodeCoverConditionCoverageHelper_C356 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[356].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C356, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[356].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C356, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[785]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2191]++;
int CodeCoverConditionCoverageHelper_C357;
                if ((((((CodeCoverConditionCoverageHelper_C357 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C357 |= (2)) == 0 || true) &&
 ((isNumberVar) && 
  ((CodeCoverConditionCoverageHelper_C357 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[357].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C357, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[357].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C357, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[787]++;
                    cfw.addDStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2192]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2193]++;
int CodeCoverConditionCoverageHelper_C358;
                    if ((((((CodeCoverConditionCoverageHelper_C358 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C358 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C358 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[358].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C358, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[358].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C358, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[789]++; cfw.addDLoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2194]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[790]++;}

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[788]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2195]++;
int CodeCoverConditionCoverageHelper_C359;
                    if ((((((CodeCoverConditionCoverageHelper_C359 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C359 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C359 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[359].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C359, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[359].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C359, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[791]++; cfw.add(ByteCode.DUP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2196]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[792]++;}
                    // Cannot save number in variable since !isNumberVar,
                    // so convert to object
                    addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2197]++;
                    cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2198]++;
                }

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[786]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2199]++;
int CodeCoverConditionCoverageHelper_C360;
                if ((((((CodeCoverConditionCoverageHelper_C360 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C360 |= (2)) == 0 || true) &&
 ((isNumberVar) && 
  ((CodeCoverConditionCoverageHelper_C360 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[360].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C360, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[360].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C360, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[793]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2200]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[794]++;}
                cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2201]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2202]++;
int CodeCoverConditionCoverageHelper_C361;
                if ((((((CodeCoverConditionCoverageHelper_C361 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C361 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C361 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[361].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C361, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[361].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C361, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[795]++; cfw.addALoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2203]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[796]++;}
            }
        }
}
    }

    private void visitSetConstVar(Node node, Node child, boolean needValue)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2204]++;
int CodeCoverConditionCoverageHelper_C362;
        if ((((((CodeCoverConditionCoverageHelper_C362 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C362 |= (2)) == 0 || true) &&
 ((hasVarsInRegs) && 
  ((CodeCoverConditionCoverageHelper_C362 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[362].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C362, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[362].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C362, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[797]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2205]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[798]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2206]++;
        int varIndex = fnCurrent.getVarIndex(node);
        generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2207]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2208]++;
        boolean isNumber = (node.getIntProp(Node.ISNUMBER_PROP, -1) != -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2209]++;
        short reg = varRegisters[varIndex];
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2210]++;
        int beyond = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2211]++;
        int noAssign = cfw.acquireLabel();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2212]++;
int CodeCoverConditionCoverageHelper_C363;
        if ((((((CodeCoverConditionCoverageHelper_C363 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C363 |= (2)) == 0 || true) &&
 ((isNumber) && 
  ((CodeCoverConditionCoverageHelper_C363 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[363].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C363, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[363].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C363, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[799]++;
            cfw.addILoad(reg + 2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2213]++;
            cfw.add(ByteCode.IFNE, noAssign);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2214]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2215]++;
            short stack = cfw.getStackTop();
            cfw.addPush(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2216]++;
            cfw.addIStore(reg + 2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2217]++;
            cfw.addDStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2218]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2219]++;
int CodeCoverConditionCoverageHelper_C364;
            if ((((((CodeCoverConditionCoverageHelper_C364 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C364 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C364 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[364].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C364, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[364].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C364, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[801]++;
                cfw.addDLoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2220]++;
                cfw.markLabel(noAssign, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2221]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[802]++;
                cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2222]++;
                cfw.markLabel(noAssign, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2223]++;
                cfw.add(ByteCode.POP2);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2224]++;
            }

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[800]++;
            cfw.addILoad(reg + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2225]++;
            cfw.add(ByteCode.IFNE, noAssign);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2226]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2227]++;
            short stack = cfw.getStackTop();
            cfw.addPush(1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2228]++;
            cfw.addIStore(reg + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2229]++;
            cfw.addAStore(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2230]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2231]++;
int CodeCoverConditionCoverageHelper_C365;
            if ((((((CodeCoverConditionCoverageHelper_C365 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C365 |= (2)) == 0 || true) &&
 ((needValue) && 
  ((CodeCoverConditionCoverageHelper_C365 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[365].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C365, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[365].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C365, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[803]++;
                cfw.addALoad(reg);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2232]++;
                cfw.markLabel(noAssign, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2233]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[804]++;
                cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2234]++;
                cfw.markLabel(noAssign, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2235]++;
                cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2236]++;
            }
        }
        cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2237]++;
    }

    private void visitGetProp(Node node, Node child)
    {
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2238]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2239]++; // object
        Node nameChild = child.getNext();
        generateExpression(nameChild, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2240]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2241]++;
int CodeCoverConditionCoverageHelper_C366;  // the name
        if ((((((CodeCoverConditionCoverageHelper_C366 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C366 |= (2)) == 0 || true) &&
 ((node.getType() == Token.GETPROPNOWARN) && 
  ((CodeCoverConditionCoverageHelper_C366 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[366].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C366, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[366].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C366, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[805]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2242]++;
            addScriptRuntimeInvoke(
                "getObjectPropNoWarn",
                "(Ljava/lang/Object;"
                +"Ljava/lang/String;"
                +"Lorg/mozilla/javascript/Context;"
                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2243]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[806]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2244]++;
        /*
            for 'this.foo' we call getObjectProp(Scriptable...) which can
            skip some casting overhead.
        */
        int childType = child.getType();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2245]++;
int CodeCoverConditionCoverageHelper_C367;
        if ((((((CodeCoverConditionCoverageHelper_C367 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C367 |= (8)) == 0 || true) &&
 ((childType == Token.THIS) && 
  ((CodeCoverConditionCoverageHelper_C367 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C367 |= (2)) == 0 || true) &&
 ((nameChild.getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C367 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[367].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C367, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[367].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C367, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[807]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2246]++;
            addScriptRuntimeInvoke(
                "getObjectProp",
                "(Lorg/mozilla/javascript/Scriptable;"
                +"Ljava/lang/String;"
                +"Lorg/mozilla/javascript/Context;"
                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2247]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[808]++;
            cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2248]++;
            cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2249]++;
            addScriptRuntimeInvoke(
                "getObjectProp",
                "(Ljava/lang/Object;"
                +"Ljava/lang/String;"
                +"Lorg/mozilla/javascript/Context;"
                +"Lorg/mozilla/javascript/Scriptable;"
                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2250]++;
        }
    }

    private void visitSetProp(int type, Node node, Node child)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2251]++;
        Node objectChild = child;
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2252]++;
        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2253]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2254]++;
int CodeCoverConditionCoverageHelper_C368;
        if ((((((CodeCoverConditionCoverageHelper_C368 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C368 |= (2)) == 0 || true) &&
 ((type == Token.SETPROP_OP) && 
  ((CodeCoverConditionCoverageHelper_C368 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[368].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C368, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[368].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C368, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[809]++;
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2255]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[810]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2256]++;
        Node nameChild = child;
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2257]++;
        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2258]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2259]++;
int CodeCoverConditionCoverageHelper_C369;
        if ((((((CodeCoverConditionCoverageHelper_C369 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C369 |= (2)) == 0 || true) &&
 ((type == Token.SETPROP_OP) && 
  ((CodeCoverConditionCoverageHelper_C369 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[369].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C369, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[369].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C369, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[811]++;
            // stack: ... object object name -> ... object name object name
            cfw.add(ByteCode.DUP_X1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2260]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2261]++;
int CodeCoverConditionCoverageHelper_C370;
            //for 'this.foo += ...' we call thisGet which can skip some
            //casting overhead.
            if ((((((CodeCoverConditionCoverageHelper_C370 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C370 |= (8)) == 0 || true) &&
 ((objectChild.getType() == Token.THIS) && 
  ((CodeCoverConditionCoverageHelper_C370 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C370 |= (2)) == 0 || true) &&
 ((nameChild.getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C370 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[370].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C370, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[370].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C370, 2) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[813]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2262]++;
                addScriptRuntimeInvoke(
                    "getObjectProp",
                    "(Lorg/mozilla/javascript/Scriptable;"
                    +"Ljava/lang/String;"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2263]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[814]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2264]++;
                addScriptRuntimeInvoke(
                    "getObjectProp",
                    "(Ljava/lang/Object;"
                    +"Ljava/lang/String;"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2265]++;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[812]++;}
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2266]++;
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2267]++;
        addScriptRuntimeInvoke(
            "setObjectProp",
            "(Ljava/lang/Object;"
            +"Ljava/lang/String;"
            +"Ljava/lang/Object;"
            +"Lorg/mozilla/javascript/Context;"
            +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2268]++;
    }

    private void visitSetElem(int type, Node node, Node child)
    {
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2269]++;
        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2270]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2271]++;
int CodeCoverConditionCoverageHelper_C371;
        if ((((((CodeCoverConditionCoverageHelper_C371 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C371 |= (2)) == 0 || true) &&
 ((type == Token.SETELEM_OP) && 
  ((CodeCoverConditionCoverageHelper_C371 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[371].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C371, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[371].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C371, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[815]++;
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2272]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[816]++;}
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2273]++;
        child = child.getNext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2274]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2275]++;
        boolean indexIsNumber = (node.getIntProp(Node.ISNUMBER_PROP, -1) != -1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2276]++;
int CodeCoverConditionCoverageHelper_C372;
        if ((((((CodeCoverConditionCoverageHelper_C372 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C372 |= (2)) == 0 || true) &&
 ((type == Token.SETELEM_OP) && 
  ((CodeCoverConditionCoverageHelper_C372 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[372].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C372, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[372].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C372, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[817]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2277]++;
int CodeCoverConditionCoverageHelper_C373;
            if ((((((CodeCoverConditionCoverageHelper_C373 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C373 |= (2)) == 0 || true) &&
 ((indexIsNumber) && 
  ((CodeCoverConditionCoverageHelper_C373 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[373].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C373, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[373].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C373, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[819]++;
                // stack: ... object object number
                //        -> ... object number object number
                cfw.add(ByteCode.DUP2_X1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2278]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2279]++;
                addOptRuntimeInvoke(
                    "getObjectIndex",
                    "(Ljava/lang/Object;D"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2280]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[820]++;
                // stack: ... object object indexObject
                //        -> ... object indexObject object indexObject
                cfw.add(ByteCode.DUP_X1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2281]++;
                cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2282]++;
                addScriptRuntimeInvoke(
                    "getObjectElem",
                    "(Ljava/lang/Object;"
                    +"Ljava/lang/Object;"
                    +"Lorg/mozilla/javascript/Context;"
                    +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2283]++;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[818]++;}
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2284]++;
        cfw.addALoad(contextLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2285]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2286]++;
int CodeCoverConditionCoverageHelper_C374;
        if ((((((CodeCoverConditionCoverageHelper_C374 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C374 |= (2)) == 0 || true) &&
 ((indexIsNumber) && 
  ((CodeCoverConditionCoverageHelper_C374 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[374].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C374, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[374].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C374, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[821]++;
            addScriptRuntimeInvoke(
                "setObjectIndex",
                "(Ljava/lang/Object;"
                +"D"
                +"Ljava/lang/Object;"
                +"Lorg/mozilla/javascript/Context;"
                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2287]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[822]++;
            addScriptRuntimeInvoke(
                "setObjectElem",
                "(Ljava/lang/Object;"
                +"Ljava/lang/Object;"
                +"Ljava/lang/Object;"
                +"Lorg/mozilla/javascript/Context;"
                +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2288]++;
        }
    }

    private void visitDotQuery(Node node, Node child)
    {
        updateLineNumber(node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2289]++;
        generateExpression(child, node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2290]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2291]++;
        addScriptRuntimeInvoke("enterDotQuery",
                               "(Ljava/lang/Object;"
                               +"Lorg/mozilla/javascript/Scriptable;"
                               +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2292]++;
        cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2293]++;

        // add push null/pop with label in between to simplify code for loop
        // continue when it is necessary to pop the null result from
        // updateDotQuery
        cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2294]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2295]++;
        int queryLoopStart = cfw.acquireLabel();
        cfw.markLabel(queryLoopStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2296]++; // loop continue jumps here
        cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2297]++;

        generateExpression(child.getNext(), node);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2298]++;
        addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2299]++;
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2300]++;
        addScriptRuntimeInvoke("updateDotQuery",
                               "(Z"
                               +"Lorg/mozilla/javascript/Scriptable;"
                               +")Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2301]++;
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2302]++;
        cfw.add(ByteCode.IFNULL, queryLoopStart);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2303]++;
        // stack: ... non_null_result_of_updateDotQuery
        cfw.addALoad(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2304]++;
        addScriptRuntimeInvoke("leaveDotQuery",
                               "(Lorg/mozilla/javascript/Scriptable;"
                               +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2305]++;
        cfw.addAStore(variableObjectLocal);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2306]++;
    }

    private int getLocalBlockRegister(Node node)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2307]++;
        Node localBlock = (Node)node.getProp(Node.LOCAL_BLOCK_PROP);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2308]++;
        int localSlot = localBlock.getExistingIntProp(Node.LOCAL_PROP);
        return localSlot;
    }

    private void dcpLoadAsNumber(int dcp_register)
    {
        cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2309]++;
        cfw.add(ByteCode.GETSTATIC,
                "java/lang/Void",
                "TYPE",
                "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2310]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2311]++;
        int isNumberLabel = cfw.acquireLabel();
        cfw.add(ByteCode.IF_ACMPEQ, isNumberLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2312]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2313]++;
        short stack = cfw.getStackTop();
        cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2314]++;
        addObjectToDouble();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2315]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2316]++;
        int beyond = cfw.acquireLabel();
        cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2317]++;
        cfw.markLabel(isNumberLabel, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2318]++;
        cfw.addDLoad(dcp_register + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2319]++;
        cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2320]++;
    }

    private void dcpLoadAsObject(int dcp_register)
    {
        cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2321]++;
        cfw.add(ByteCode.GETSTATIC,
                "java/lang/Void",
                "TYPE",
                "Ljava/lang/Class;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2322]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2323]++;
        int isNumberLabel = cfw.acquireLabel();
        cfw.add(ByteCode.IF_ACMPEQ, isNumberLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2324]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2325]++;
        short stack = cfw.getStackTop();
        cfw.addALoad(dcp_register);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2326]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2327]++;
        int beyond = cfw.acquireLabel();
        cfw.add(ByteCode.GOTO, beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2328]++;
        cfw.markLabel(isNumberLabel, stack);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2329]++;
        cfw.addDLoad(dcp_register + 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2330]++;
        addDoubleWrap();
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2331]++;
        cfw.markLabel(beyond);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2332]++;
    }

    private void addGoto(Node target, int jumpcode)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2333]++;
        int targetLabel = getTargetLabel(target);
        cfw.add(jumpcode, targetLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2334]++;
    }

    private void addObjectToDouble()
    {
        addScriptRuntimeInvoke("toNumber", "(Ljava/lang/Object;)D");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2335]++;
    }

    private void addNewObjectArray(int size)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2336]++;
int CodeCoverConditionCoverageHelper_C375;
        if ((((((CodeCoverConditionCoverageHelper_C375 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C375 |= (2)) == 0 || true) &&
 ((size == 0) && 
  ((CodeCoverConditionCoverageHelper_C375 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[375].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C375, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[375].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C375, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[823]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2337]++;
int CodeCoverConditionCoverageHelper_C376;
            if ((((((CodeCoverConditionCoverageHelper_C376 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C376 |= (2)) == 0 || true) &&
 ((itsZeroArgArray >= 0) && 
  ((CodeCoverConditionCoverageHelper_C376 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[376].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C376, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[376].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C376, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[825]++;
                cfw.addALoad(itsZeroArgArray);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2338]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[826]++;
                cfw.add(ByteCode.GETSTATIC,
                        "org/mozilla/javascript/ScriptRuntime",
                        "emptyArgs", "[Ljava/lang/Object;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2339]++;
            }

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[824]++;
            cfw.addPush(size);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2340]++;
            cfw.add(ByteCode.ANEWARRAY, "java/lang/Object");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2341]++;
        }
    }

    private void addScriptRuntimeInvoke(String methodName,
                                        String methodSignature)
    {
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org.mozilla.javascript.ScriptRuntime",
                      methodName,
                      methodSignature);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2342]++;
    }

    private void addOptRuntimeInvoke(String methodName,
                                     String methodSignature)
    {
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/optimizer/OptRuntime",
                      methodName,
                      methodSignature);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2343]++;
    }

    private void addJumpedBooleanWrap(int trueLabel, int falseLabel)
    {
        cfw.markLabel(falseLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2344]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2345]++;
        int skip = cfw.acquireLabel();
        cfw.add(ByteCode.GETSTATIC, "java/lang/Boolean",
                                "FALSE", "Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2346]++;
        cfw.add(ByteCode.GOTO, skip);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2347]++;
        cfw.markLabel(trueLabel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2348]++;
        cfw.add(ByteCode.GETSTATIC, "java/lang/Boolean",
                                "TRUE", "Ljava/lang/Boolean;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2349]++;
        cfw.markLabel(skip);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2350]++;
        cfw.adjustStackTop(-1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2351]++;   // only have 1 of true/false
    }

    private void addDoubleWrap()
    {
        addOptRuntimeInvoke("wrapDouble", "(D)Ljava/lang/Double;");
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2352]++;
    }

    /**
     * Const locals use an extra slot to hold the has-been-assigned-once flag at
     * runtime.
     * @param isConst true iff the variable is const
     * @return the register for the word pair (double/long)
     */
    private short getNewWordPairLocal(boolean isConst)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2353]++;
        short result = getConsecutiveSlots(2, isConst);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2354]++;
int CodeCoverConditionCoverageHelper_C377;
        if ((((((CodeCoverConditionCoverageHelper_C377 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C377 |= (2)) == 0 || true) &&
 ((result < (MAX_LOCALS - 1)) && 
  ((CodeCoverConditionCoverageHelper_C377 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[377].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C377, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[377].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C377, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[827]++;
            locals[result] = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2355]++;
            locals[result + 1] = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2356]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2357]++;
int CodeCoverConditionCoverageHelper_C378;
            if ((((((CodeCoverConditionCoverageHelper_C378 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C378 |= (2)) == 0 || true) &&
 ((isConst) && 
  ((CodeCoverConditionCoverageHelper_C378 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[378].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C378, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[378].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C378, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[829]++;
                locals[result + 2] = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2358]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[830]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2359]++;
int CodeCoverConditionCoverageHelper_C379;
            if ((((((CodeCoverConditionCoverageHelper_C379 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C379 |= (2)) == 0 || true) &&
 ((result == firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C379 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[379].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C379, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[379].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C379, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[831]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2360]++;
byte CodeCoverTryBranchHelper_L63 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[187]++;


int CodeCoverConditionCoverageHelper_C380;
                for (int i = firstFreeLocal + 2;(((((CodeCoverConditionCoverageHelper_C380 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C380 |= (2)) == 0 || true) &&
 ((i < MAX_LOCALS) && 
  ((CodeCoverConditionCoverageHelper_C380 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[380].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C380, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[380].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C380, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L63 == 0) {
  CodeCoverTryBranchHelper_L63++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[187]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[188]++;
} else if (CodeCoverTryBranchHelper_L63 == 1) {
  CodeCoverTryBranchHelper_L63++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[188]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[189]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2361]++;
int CodeCoverConditionCoverageHelper_C381;
                    if ((((((CodeCoverConditionCoverageHelper_C381 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C381 |= (2)) == 0 || true) &&
 ((locals[i] == 0) && 
  ((CodeCoverConditionCoverageHelper_C381 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[381].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C381, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[381].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C381, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[833]++;
                        firstFreeLocal = (short) i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2362]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2363]++;
int CodeCoverConditionCoverageHelper_C382;
                        if ((((((CodeCoverConditionCoverageHelper_C382 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C382 |= (2)) == 0 || true) &&
 ((localsMax < firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C382 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[382].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C382, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[382].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C382, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[835]++;
                            localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2364]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[836]++;}
                        return result;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[834]++;}
                }

            }
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[832]++;
                return result;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[828]++;}
        throw Context.reportRuntimeError("Program too complex " +
                                         "(out of locals)");
    }

    private short getNewWordLocal(boolean isConst)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2365]++;
        short result = getConsecutiveSlots(1, isConst);
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2366]++;
int CodeCoverConditionCoverageHelper_C383;
        if ((((((CodeCoverConditionCoverageHelper_C383 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C383 |= (2)) == 0 || true) &&
 ((result < (MAX_LOCALS - 1)) && 
  ((CodeCoverConditionCoverageHelper_C383 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[383].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C383, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[383].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C383, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[837]++;
            locals[result] = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2367]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2368]++;
int CodeCoverConditionCoverageHelper_C384;
            if ((((((CodeCoverConditionCoverageHelper_C384 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C384 |= (2)) == 0 || true) &&
 ((isConst) && 
  ((CodeCoverConditionCoverageHelper_C384 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[384].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C384, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[384].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C384, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[839]++;
                locals[result + 1] = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2369]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[840]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2370]++;
int CodeCoverConditionCoverageHelper_C385;
            if ((((((CodeCoverConditionCoverageHelper_C385 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C385 |= (2)) == 0 || true) &&
 ((result == firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C385 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[385].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C385, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[385].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C385, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[841]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2371]++;
byte CodeCoverTryBranchHelper_L64 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[190]++;


int CodeCoverConditionCoverageHelper_C386;
                for (int i = firstFreeLocal + 2;(((((CodeCoverConditionCoverageHelper_C386 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C386 |= (2)) == 0 || true) &&
 ((i < MAX_LOCALS) && 
  ((CodeCoverConditionCoverageHelper_C386 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[386].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C386, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[386].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C386, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L64 == 0) {
  CodeCoverTryBranchHelper_L64++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[190]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[191]++;
} else if (CodeCoverTryBranchHelper_L64 == 1) {
  CodeCoverTryBranchHelper_L64++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[191]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[192]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2372]++;
int CodeCoverConditionCoverageHelper_C387;
                    if ((((((CodeCoverConditionCoverageHelper_C387 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C387 |= (2)) == 0 || true) &&
 ((locals[i] == 0) && 
  ((CodeCoverConditionCoverageHelper_C387 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[387].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C387, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[387].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C387, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[843]++;
                        firstFreeLocal = (short) i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2373]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2374]++;
int CodeCoverConditionCoverageHelper_C388;
                        if ((((((CodeCoverConditionCoverageHelper_C388 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C388 |= (2)) == 0 || true) &&
 ((localsMax < firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C388 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[388].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C388, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[388].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C388, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[845]++;
                            localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2375]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[846]++;}
                        return result;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[844]++;}
                }

            }
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[842]++;
                return result;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[838]++;}
        throw Context.reportRuntimeError("Program too complex " +
                                         "(out of locals)");
    }

    private short getNewWordLocal()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2376]++;
        short result = firstFreeLocal;
        locals[result] = 1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2377]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2378]++;
byte CodeCoverTryBranchHelper_L65 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[193]++;


int CodeCoverConditionCoverageHelper_C389;
        for (int i = firstFreeLocal + 1;(((((CodeCoverConditionCoverageHelper_C389 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C389 |= (2)) == 0 || true) &&
 ((i < MAX_LOCALS) && 
  ((CodeCoverConditionCoverageHelper_C389 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[389].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C389, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[389].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C389, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L65 == 0) {
  CodeCoverTryBranchHelper_L65++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[193]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[194]++;
} else if (CodeCoverTryBranchHelper_L65 == 1) {
  CodeCoverTryBranchHelper_L65++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[194]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[195]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2379]++;
int CodeCoverConditionCoverageHelper_C390;
            if ((((((CodeCoverConditionCoverageHelper_C390 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C390 |= (2)) == 0 || true) &&
 ((locals[i] == 0) && 
  ((CodeCoverConditionCoverageHelper_C390 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[390].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C390, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[390].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C390, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[847]++;
                firstFreeLocal = (short) i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2380]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2381]++;
int CodeCoverConditionCoverageHelper_C391;
                if ((((((CodeCoverConditionCoverageHelper_C391 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C391 |= (2)) == 0 || true) &&
 ((localsMax < firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C391 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[391].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C391, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[391].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C391, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[849]++;
                    localsMax = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2382]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[850]++;}
                return result;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[848]++;}
        }
        throw Context.reportRuntimeError("Program too complex " +
                                         "(out of locals)");
    }

    private short getConsecutiveSlots(int count, boolean isConst) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2383]++;
int CodeCoverConditionCoverageHelper_C392;
        if ((((((CodeCoverConditionCoverageHelper_C392 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C392 |= (2)) == 0 || true) &&
 ((isConst) && 
  ((CodeCoverConditionCoverageHelper_C392 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[392].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C392, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[392].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C392, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[851]++;
            count++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2384]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[852]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2385]++;
        short result = firstFreeLocal;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2386]++;
byte CodeCoverTryBranchHelper_L66 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[196]++;


        while (true) {
if (CodeCoverTryBranchHelper_L66 == 0) {
  CodeCoverTryBranchHelper_L66++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[196]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[197]++;
} else if (CodeCoverTryBranchHelper_L66 == 1) {
  CodeCoverTryBranchHelper_L66++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[197]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[198]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2387]++;
int CodeCoverConditionCoverageHelper_C394;
            if ((((((CodeCoverConditionCoverageHelper_C394 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C394 |= (2)) == 0 || true) &&
 ((result >= (MAX_LOCALS - 1)) && 
  ((CodeCoverConditionCoverageHelper_C394 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[394].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C394, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[394].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C394, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[853]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2388]++;
                break;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[854]++;}
            int i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2389]++;
byte CodeCoverTryBranchHelper_L67 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[199]++;


int CodeCoverConditionCoverageHelper_C395;
            for (i = 0;(((((CodeCoverConditionCoverageHelper_C395 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C395 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C395 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[395].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C395, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[395].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C395, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L67 == 0) {
  CodeCoverTryBranchHelper_L67++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[199]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[200]++;
} else if (CodeCoverTryBranchHelper_L67 == 1) {
  CodeCoverTryBranchHelper_L67++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[200]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.loops[201]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2390]++;
int CodeCoverConditionCoverageHelper_C396;
                if ((((((CodeCoverConditionCoverageHelper_C396 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C396 |= (2)) == 0 || true) &&
 ((locals[result + i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C396 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[396].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C396, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[396].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C396, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[855]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2391]++;
                    break;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[856]++;}
  }
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2392]++;
int CodeCoverConditionCoverageHelper_C397;
            if ((((((CodeCoverConditionCoverageHelper_C397 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C397 |= (2)) == 0 || true) &&
 ((i >= count) && 
  ((CodeCoverConditionCoverageHelper_C397 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[397].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C397, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[397].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C397, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[857]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2393]++;
                break;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[858]++;}
            result++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2394]++;
        }
        return result;
    }

    // This is a valid call only for a local that is allocated by default.
    private void incReferenceWordLocal(short local)
    {
        locals[local]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2395]++;
    }

    // This is a valid call only for a local that is allocated by default.
    private void decReferenceWordLocal(short local)
    {
        locals[local]--;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2396]++;
    }

    private void releaseWordLocal(short local)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2397]++;
int CodeCoverConditionCoverageHelper_C398;
        if ((((((CodeCoverConditionCoverageHelper_C398 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C398 |= (2)) == 0 || true) &&
 ((local < firstFreeLocal) && 
  ((CodeCoverConditionCoverageHelper_C398 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[398].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C398, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.conditionCounters[398].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C398, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[859]++;
            firstFreeLocal = local;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2398]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.branches[860]++;}
        locals[local] = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2399]++;
    }


    static final int GENERATOR_TERMINATE = -1;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2400]++;
  }
    static final int GENERATOR_START = 0;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2401]++;
  }
    static final int GENERATOR_YIELD_START = 1;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2402]++;
  }

    ClassFileWriter cfw;
    Codegen codegen;
    CompilerEnvirons compilerEnv;
    ScriptNode scriptOrFn;
    public int scriptOrFnIndex;
    private int savedCodeOffset;

    private OptFunctionNode fnCurrent;

    private static final int MAX_LOCALS = 1024;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2403]++;
  }
    private int[] locals;
    private short firstFreeLocal;
    private short localsMax;

    private int itsLineNumber;

    private boolean hasVarsInRegs;
    private short[] varRegisters;
    private boolean inDirectCallFunction;
    private boolean itsForcedObjectParameters;
    private int enterAreaStartLabel;
    private int epilogueLabel;
    private boolean inLocalBlock;

    // special known locals. If you add a new local here, be sure
    // to initialize it to -1 in initBodyGeneration
    private short variableObjectLocal;
    private short popvLocal;
    private short contextLocal;
    private short argsLocal;
    private short operationLocal;
    private short thisObjLocal;
    private short funObjLocal;
    private short itsZeroArgArray;
    private short itsOneArgArray;
    private short generatorStateLocal;

    private boolean isGenerator;
    private int generatorSwitch;
    private int maxLocals = 0;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2404]++;
  }
    private int maxStack = 0;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2405]++;
  }

    private Map<Node,FinallyReturnPoint> finallys;
    private List<Node> literals;

    static class FinallyReturnPoint {
        public List<Integer> jsrPoints  = new ArrayList<Integer>();
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2406]++;
  }
        public int tableLabel = 0;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29.statements[2407]++;
  }
    }
}

class CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29 ());
  }
    public static long[] statements = new long[2408];
    public static long[] branches = new long[861];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[399];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.optimizer.RHINO-SRC-Codegen.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,3,1,1,1,3,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,2,2,1,1,1,1,1,2,1,1,2,2,2,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1};
    for (int i = 1; i <= 398; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[202];

  public CodeCoverCoverageCounter$di175yxae5e37vt04k9eky92bdagww0h29 () {
    super("org.mozilla.javascript.optimizer.RHINO-SRC-Codegen.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2407; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 860; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 398; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 201; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.optimizer.RHINO-SRC-Codegen.java");
      for (int i = 1; i <= 2407; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 860; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 398; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 67; i++) {
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

