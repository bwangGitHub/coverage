/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class reflects Java methods into the JavaScript environment and
 * handles overloading of methods.
 *
 * @see NativeJavaArray
 * @see NativeJavaPackage
 * @see NativeJavaClass
 */

public class NativeJavaMethod extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.ping();
  }

    static final long serialVersionUID = -3440381785576412928L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[1]++;
  }

    NativeJavaMethod(MemberBox[] methods)
    {
        this.functionName = methods[0].getName();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[2]++;
        this.methods = methods;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[3]++;
    }

    NativeJavaMethod(MemberBox[] methods, String name)
    {
        this.functionName = name;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[4]++;
        this.methods = methods;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[5]++;
    }

    NativeJavaMethod(MemberBox method, String name)
    {
        this.functionName = name;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[6]++;
        this.methods = new MemberBox[] { method };
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[7]++;
    }

    public NativeJavaMethod(Method method, String name)
    {
        this(new MemberBox(method), name);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[8]++;
    }

    @Override
    public String getFunctionName()
    {
        return functionName;
    }

    static String scriptSignature(Object[] values)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[9]++;
        StringBuffer sig = new StringBuffer();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i != values.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[1]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[2]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[3]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[11]++;
            Object value = values[i];

            String s;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[1]++;
                s = "null";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[13]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[2]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[14]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[3]++;
                s = "boolean";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[15]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[4]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[16]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[5]++;
                s = "string";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[17]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[6]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[18]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[7]++;
                s = "number";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[19]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[8]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[20]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[9]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[11]++;
                    s = "undefined";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[22]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[12]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[23]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[13]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[24]++;
                    Object wrapped = ((Wrapper)value).unwrap();
                    s = wrapped.getClass().getName();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[25]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[14]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[26]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[15]++;
                    s = "function";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[27]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[16]++;
                    s = "object";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[28]++;
                }
}
}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[10]++;
                s = JavaMembers.javaSignature(value.getClass());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[29]++;
            }
}
}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;

            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[17]++;
                sig.append(',');
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[31]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[18]++;}
            sig.append(s);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[32]++;
        }
        return sig.toString();
    }

    @Override
    String decompile(int indent, int flags)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[33]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[34]++;
        boolean justbody = (0 != (flags & Decompiler.ONLY_BODY_FLAG));
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[35]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((justbody) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[19]++;
            sb.append("function ");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[36]++;
            sb.append(getFunctionName());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[37]++;
            sb.append("() {");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[38]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[20]++;}
        sb.append("/*\n");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[39]++;
        sb.append(toString());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[40]++;
        sb.append(justbody ? "*/\n" : "*/}\n");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[41]++;
        return sb.toString();
    }

    @Override
    public String toString()
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[42]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[43]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0, N = methods.length;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[4]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[5]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[6]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[44]++;
int CodeCoverConditionCoverageHelper_C13;
            // Check member type, we also use this for overloaded constructors
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((methods[i].isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[21]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[45]++;
                Method method = methods[i].method();
                sb.append(JavaMembers.javaSignature(method.getReturnType()));
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[46]++;
                sb.append(' ');
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[47]++;
                sb.append(method.getName());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[48]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[22]++;
                sb.append(methods[i].getName());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[49]++;
            }
            sb.append(JavaMembers.liveConnectSignature(methods[i].argTypes));
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[50]++;
            sb.append('\n');
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[51]++;
        }
        return sb.toString();
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
        // Find a method that matches the types given.
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((methods.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[23]++;
            throw new RuntimeException("No methods defined for call");

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[24]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[53]++;

        int index = findCachedFunction(cx, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[25]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[55]++;
            Class<?> c = methods[0].method().getDeclaringClass();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[56]++;
            String sig = c.getName() + '.' + getFunctionName() + '(' +
                         scriptSignature(args) + ')';
            throw Context.reportRuntimeError1("msg.java.no_such_method", sig);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[26]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[57]++;

        MemberBox meth = methods[index];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[58]++;
        Class<?>[] argTypes = meth.argTypes;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((meth.vararg) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[27]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[60]++;
            // marshall the explicit parameters
            Object[] newArgs = new Object[argTypes.length];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[61]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[7]++;


int CodeCoverConditionCoverageHelper_C17;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < argTypes.length-1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[7]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[8]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[9]++;
}
                newArgs[i] = Context.jsToJava(args[i], argTypes[i]);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[62]++;
            }

            Object varArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[63]++;
int CodeCoverConditionCoverageHelper_C18;

            // Handle special situation where a single variable parameter
            // is given and it is a Java or ECMA array or is null.
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (128)) == 0 || true) &&
 ((args.length == argTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((args[args.length-1] == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((args[args.length-1] instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((args[args.length-1] instanceof NativeJavaArray) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[29]++;
                // convert the ECMA array into a native array
                varArgs = Context.jsToJava(args[args.length-1],
                                           argTypes[argTypes.length - 1]);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[64]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[30]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[65]++;
                // marshall the variable parameters
                Class<?> componentType = argTypes[argTypes.length - 1].
                                         getComponentType();
                varArgs = Array.newInstance(componentType,
                                            args.length - argTypes.length + 1);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[66]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[67]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < Array.getLength(varArgs)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[10]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[11]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[12]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[68]++;
                    Object value = Context.jsToJava(args[argTypes.length-1 + i],
                                                    componentType);
                    Array.set(varArgs, i, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[69]++;
                }
            }

            // add varargs
            newArgs[argTypes.length-1] = varArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[70]++;
            // replace the original args with the new one
            args = newArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[71]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[28]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[72]++;
            // First, we marshall the args.
            Object[] origArgs = args;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[73]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[13]++;


int CodeCoverConditionCoverageHelper_C20;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[13]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[14]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[15]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[74]++;
                Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[75]++;
                Object coerced = Context.jsToJava(arg, argTypes[i]);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((coerced != arg) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[31]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[77]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((origArgs == args) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[33]++;
                        args = args.clone();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[78]++;

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[34]++;}
                    args[i] = coerced;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[79]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[32]++;}
            }
        }
        Object javaObject;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((meth.isStatic()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[35]++;
            javaObject = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[81]++;
  // don't need an object
        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[36]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[82]++;
            Scriptable o = thisObj;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[83]++;
            Class<?> c = meth.getDeclaringClass();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[84]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[16]++;


            for (;;) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[16]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[17]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[18]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((o == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[37]++;
                    throw Context.reportRuntimeError3(
                        "msg.nonjava.method", getFunctionName(),
                        ScriptRuntime.toString(thisObj), c.getName());

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[38]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[86]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((o instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[39]++;
                    javaObject = ((Wrapper)o).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[87]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[88]++;
int CodeCoverConditionCoverageHelper_C27;
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c.isInstance(javaObject)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[41]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[89]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[42]++;}

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[40]++;}
                o = o.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[90]++;
            }
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[91]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[43]++;
            printDebug("Calling ", meth, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[92]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[44]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[93]++;

        Object retval = meth.invoke(javaObject, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[94]++;
        Class<?> staticType = meth.method().getReturnType();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[95]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[45]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[96]++;
            Class<?> actualType = (retval == null) ? null
                                                : retval.getClass();
            System.err.println(" ----- Returned " + retval +
                               " actual = " + actualType +
                               " expect = " + staticType);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[97]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[46]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[98]++;

        Object wrapped = cx.getWrapFactory().wrap(cx, scope,
                                                  retval, staticType);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[99]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[47]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[100]++;
            Class<?> actualType = (wrapped == null) ? null
                                                 : wrapped.getClass();
            System.err.println(" ----- Wrapped as " + wrapped +
                               " class = " + actualType);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[101]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[48]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[102]++;
int CodeCoverConditionCoverageHelper_C31;

        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((wrapped == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((staticType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[49]++;
            wrapped = Undefined.instance;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[103]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[50]++;}
        return wrapped;
    }

    int findCachedFunction(Context cx, Object[] args) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[104]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((methods.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[51]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[105]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((overloadCache != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[53]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[106]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[19]++;


                for (ResolvedOverload ovl : overloadCache) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[19]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[20]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[21]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[107]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((ovl.matches(args)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[55]++;
                        return ovl.index;

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[56]++;}
                }

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[54]++;
                overloadCache = new LinkedList<ResolvedOverload>();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[108]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[109]++;
            int index = findFunction(cx, methods, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[110]++;
int CodeCoverConditionCoverageHelper_C35;
            // As a sanity measure, don't let the lookup cache grow longer
            // than twice the number of overloaded methods
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((overloadCache.size() < methods.length * 2) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[57]++;
                synchronized (overloadCache) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[111]++;
                    ResolvedOverload ovl = new ResolvedOverload(args, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[112]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((overloadCache.contains(ovl)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[59]++;
                        overloadCache.addFirst(ovl);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[113]++;

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[60]++;}
                }

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[58]++;}
            return index;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[52]++;}
        return findFunction(cx, methods, args);
    }

    /**
     * Find the index of the correct function to call given the set of methods
     * or constructors and the arguments.
     * If no function can be found to call, return -1.
     */
    static int findFunction(Context cx,
                            MemberBox[] methodsOrCtors, Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[114]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((methodsOrCtors.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[61]++;
            return -1;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[62]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[115]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((methodsOrCtors.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[63]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[116]++;
            MemberBox member = methodsOrCtors[0];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[117]++;
            Class<?>[] argTypes = member.argTypes;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[118]++;
            int alength = argTypes.length;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[119]++;
int CodeCoverConditionCoverageHelper_C39;

            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((member.vararg) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[65]++;
                alength--;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[120]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[121]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((alength > args.length) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[67]++;
                    return -1;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[68]++;}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[66]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[122]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((alength != args.length) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[69]++;
                    return -1;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[70]++;}
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[123]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[22]++;


int CodeCoverConditionCoverageHelper_C42;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((j != alength) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[22]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[23]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[24]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[124]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((NativeJavaObject.canConvert(args[j], argTypes[j])) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[71]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[125]++;
int CodeCoverConditionCoverageHelper_C44;
                    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[73]++; printDebug("Rejecting (args can't convert) ",
                                          member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[126]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[74]++;}
                    return -1;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[72]++;}
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[127]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[75]++; printDebug("Found ", member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[128]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[76]++;}
            return 0;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[64]++;}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[129]++;

        int firstBestFit = -1;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[130]++;
        int[] extraBestFits = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[131]++;
        int extraBestFitsCount = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[132]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[25]++;


int CodeCoverConditionCoverageHelper_C46;

      search:
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i < methodsOrCtors.length) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[25]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[26]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[27]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[133]++;
            MemberBox member = methodsOrCtors[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[134]++;
            Class<?>[] argTypes = member.argTypes;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[135]++;
            int alength = argTypes.length;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[136]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((member.vararg) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[77]++;
                alength--;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[137]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[138]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((alength > args.length) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[79]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[139]++;
                    continue search;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[80]++;}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[78]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[140]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((alength != args.length) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[81]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[141]++;
                    continue search;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[82]++;}
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[142]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[28]++;


int CodeCoverConditionCoverageHelper_C50;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((j < alength) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[28]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[29]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[30]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[143]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((NativeJavaObject.canConvert(args[j], argTypes[j])) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[83]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[144]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[85]++; printDebug("Rejecting (args can't convert) ",
                                          member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[145]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[86]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[146]++;
                    continue search;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[84]++;}
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[147]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((firstBestFit < 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[87]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[148]++;
int CodeCoverConditionCoverageHelper_C54;
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[89]++; printDebug("Found first applicable ", member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[149]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[90]++;}
                firstBestFit = i;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[150]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[88]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[151]++;
                // Compare with all currently fit methods.
                // The loop starts from -1 denoting firstBestFit and proceed
                // until extraBestFitsCount to avoid extraBestFits allocation
                // in the most common case of no ambiguity
                int betterCount = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[152]++; // number of times member was prefered over
                                     // best fits
                int worseCount = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[153]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[31]++;


int CodeCoverConditionCoverageHelper_C55;  // number of times best fits were prefered
                                     // over member
                for (int j = -1;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((j != extraBestFitsCount) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[31]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[32]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[33]++;
}
                    int bestFitIndex;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[154]++;
int CodeCoverConditionCoverageHelper_C56;
                    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((j == -1) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[91]++;
                        bestFitIndex = firstBestFit;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[155]++;

                    } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[92]++;
                        bestFitIndex = extraBestFits[j];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[156]++;
                    }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[157]++;
                    MemberBox bestFit = methodsOrCtors[bestFitIndex];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[158]++;
int CodeCoverConditionCoverageHelper_C57;
                    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 (((bestFit.member().getModifiers() & Modifier.PUBLIC) !=
                            (member.member().getModifiers() & Modifier.PUBLIC)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false))
                    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[93]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[159]++;
int CodeCoverConditionCoverageHelper_C58;
                        // When FEATURE_ENHANCED_JAVA_ACCESS gives us access
                        // to non-public members, continue to prefer public
                        // methods in overloading
                        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 (((bestFit.member().getModifiers() & Modifier.PUBLIC) == 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[95]++;
                            ++betterCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[160]++;
}
                        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[96]++;
                            ++worseCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[161]++;
}

                    } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[94]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[162]++;
                        int preference = preferSignature(args, argTypes,
                                                         member.vararg,
                                                         bestFit.argTypes,
                                                         bestFit.vararg );
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[163]++;
int CodeCoverConditionCoverageHelper_C59;
                        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((preference == PREFERENCE_AMBIGUOUS) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[97]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[164]++;
                            break;

                        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[98]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[165]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((preference == PREFERENCE_FIRST_ARG) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[99]++;
                            ++betterCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[166]++;

                        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[100]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[167]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((preference == PREFERENCE_SECOND_ARG) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[101]++;
                            ++worseCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[168]++;

                        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[102]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[169]++;
int CodeCoverConditionCoverageHelper_C62;
                            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((preference != PREFERENCE_EQUAL) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[103]++; Kit.codeBug();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[170]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[104]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[171]++;
int CodeCoverConditionCoverageHelper_C63;
                            // This should not happen in theory
                            // but on some JVMs, Class.getMethods will return all
                            // static methods of the class hierarchy, even if
                            // a derived class's parameters match exactly.
                            // We want to call the derived class's method.
                            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((bestFit.isStatic()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((bestFit.getDeclaringClass().isAssignableFrom(
                                       member.getDeclaringClass())) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false))
                            {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[105]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[172]++;
int CodeCoverConditionCoverageHelper_C64;
                                // On some JVMs, Class.getMethods will return all
                                // static methods of the class hierarchy, even if
                                // a derived class's parameters match exactly.
                                // We want to call the derived class's method.
                                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[107]++; printDebug(
                                    "Substituting (overridden static)",
                                    member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[173]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[108]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[174]++;
int CodeCoverConditionCoverageHelper_C65;
                                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((j == -1) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[109]++;
                                    firstBestFit = i;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[175]++;

                                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[110]++;
                                    extraBestFits[j] = i;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[176]++;
                                }

                            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[106]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[177]++;
int CodeCoverConditionCoverageHelper_C66;
                                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[111]++; printDebug(
                                    "Ignoring same signature member ",
                                    member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[178]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[112]++;}
                            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[179]++;
                            continue search;
                        }
}
}
                    }
                }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[180]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((betterCount == 1 + extraBestFitsCount) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[113]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[181]++;
int CodeCoverConditionCoverageHelper_C68;
                    // member was prefered over all best fits
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[115]++; printDebug(
                        "New first applicable ", member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[182]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[116]++;}
                    firstBestFit = i;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[183]++;
                    extraBestFitsCount = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[184]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[114]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[185]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((worseCount == 1 + extraBestFitsCount) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[117]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[186]++;
int CodeCoverConditionCoverageHelper_C70;
                    // all best fits were prefered over member, ignore it
                    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[119]++; printDebug(
                        "Rejecting (all current bests better) ", member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[187]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[120]++;}

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[118]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[188]++;
int CodeCoverConditionCoverageHelper_C71;
                    // some ambiguity was present, add member to best fit set
                    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[121]++; printDebug(
                        "Added to best fit set ", member, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[189]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[122]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[190]++;
int CodeCoverConditionCoverageHelper_C72;
                    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((extraBestFits == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[123]++;
                        // Allocate maximum possible array
                        extraBestFits = new int[methodsOrCtors.length - 1];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[191]++;

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[124]++;}
                    extraBestFits[extraBestFitsCount] = i;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[192]++;
                    ++extraBestFitsCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[193]++;
                }
}
            }
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[194]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((firstBestFit < 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[125]++;
            // Nothing was found
            return -1;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[126]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[195]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((extraBestFitsCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[127]++;
            // single best fit
            return firstBestFit;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[128]++;}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[196]++;

        // report remaining ambiguity
        StringBuffer buf = new StringBuffer();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[197]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[34]++;


int CodeCoverConditionCoverageHelper_C75;
        for (int j = -1;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((j != extraBestFitsCount) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[34]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[35]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[36]++;
}
            int bestFitIndex;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[198]++;
int CodeCoverConditionCoverageHelper_C76;
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((j == -1) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[129]++;
                bestFitIndex = firstBestFit;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[199]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[130]++;
                bestFitIndex = extraBestFits[j];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[200]++;
            }
            buf.append("\n    ");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[201]++;
            buf.append(methodsOrCtors[bestFitIndex].toJavaDeclaration());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[202]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[203]++;

        MemberBox firstFitMember = methodsOrCtors[firstBestFit];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[204]++;
        String memberName = firstFitMember.getName();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[205]++;
        String memberClass = firstFitMember.getDeclaringClass().getName();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[206]++;
int CodeCoverConditionCoverageHelper_C77;

        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((methodsOrCtors[0].isCtor()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[131]++;
            throw Context.reportRuntimeError3(
                "msg.constructor.ambiguous",
                memberName, scriptSignature(args), buf.toString());

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[132]++;
            throw Context.reportRuntimeError4(
                "msg.method.ambiguous", memberClass,
                memberName, scriptSignature(args), buf.toString());
        }
    }

    /** Types are equal */
    private static final int PREFERENCE_EQUAL      = 0;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[207]++;
  }
    private static final int PREFERENCE_FIRST_ARG  = 1;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[208]++;
  }
    private static final int PREFERENCE_SECOND_ARG = 2;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[209]++;
  }
    /** No clear "easy" conversion */
    private static final int PREFERENCE_AMBIGUOUS  = 3;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[210]++;
  }

    /**
     * Determine which of two signatures is the closer fit.
     * Returns one of PREFERENCE_EQUAL, PREFERENCE_FIRST_ARG,
     * PREFERENCE_SECOND_ARG, or PREFERENCE_AMBIGUOUS.
     */
    private static int preferSignature(Object[] args,
                                       Class<?>[] sig1,
                                       boolean vararg1,
                                       Class<?>[] sig2,
                                       boolean vararg2 )
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[211]++;
        int totalPreference = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[212]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[37]++;


int CodeCoverConditionCoverageHelper_C78;
        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((j < args.length) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[37]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[38]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[39]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[213]++;
            Class<?> type1 = vararg1 && j >= sig1.length ? sig1[sig1.length-1] : sig1[j];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[214]++;
            Class<?> type2 = vararg2 && j >= sig2.length ? sig2[sig2.length-1] : sig2[j];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[215]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((type1 == type2) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[133]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[216]++;
                continue;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[134]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[217]++;
            Object arg = args[j];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[218]++;

            // Determine which of type1, type2 is easier to convert from arg.

            int rank1 = NativeJavaObject.getConversionWeight(arg, type1);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[219]++;
            int rank2 = NativeJavaObject.getConversionWeight(arg, type2);

            int preference;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[220]++;
int CodeCoverConditionCoverageHelper_C80;
            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((rank1 < rank2) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[135]++;
                preference = PREFERENCE_FIRST_ARG;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[221]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[136]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[222]++;
int CodeCoverConditionCoverageHelper_C81; if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((rank1 > rank2) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[137]++;
                preference = PREFERENCE_SECOND_ARG;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[223]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[138]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[224]++;
int CodeCoverConditionCoverageHelper_C82;
                // Equal ranks
                if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((rank1 == NativeJavaObject.CONVERSION_NONTRIVIAL) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[139]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[225]++;
int CodeCoverConditionCoverageHelper_C83;
                    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((type1.isAssignableFrom(type2)) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[141]++;
                        preference = PREFERENCE_SECOND_ARG;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[226]++;

                    } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[142]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[227]++;
int CodeCoverConditionCoverageHelper_C84; if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((type2.isAssignableFrom(type1)) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[143]++;
                        preference = PREFERENCE_FIRST_ARG;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[228]++;

                    } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[144]++;
                        preference = PREFERENCE_AMBIGUOUS;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[229]++;
                    }
}

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[140]++;
                    preference = PREFERENCE_AMBIGUOUS;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[230]++;
                }
            }
}

            totalPreference |= preference;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[231]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[232]++;
int CodeCoverConditionCoverageHelper_C85;

            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((totalPreference == PREFERENCE_AMBIGUOUS) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[145]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[233]++;
                break;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[146]++;}
        }
        return totalPreference;
    }


    private static final boolean debug = false;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[234]++;
  }

    private static void printDebug(String msg, MemberBox member,
                                   Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[235]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[147]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[236]++;
            StringBuffer sb = new StringBuffer();
            sb.append(" ----- ");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[237]++;
            sb.append(msg);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[238]++;
            sb.append(member.getDeclaringClass().getName());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[239]++;
            sb.append('.');
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[240]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[241]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((member.isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[149]++;
                sb.append(member.getName());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[242]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[150]++;}
            sb.append(JavaMembers.liveConnectSignature(member.argTypes));
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[243]++;
            sb.append(" for arguments (");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[244]++;
            sb.append(scriptSignature(args));
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[245]++;
            sb.append(')');
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[246]++;
            System.out.println(sb);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[247]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[148]++;}
    }

    MemberBox[] methods;
    private String functionName;
    private transient LinkedList<ResolvedOverload> overloadCache;
}

class ResolvedOverload {
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.ping();
  }

    final Class<?>[] types;
    final int index;

    ResolvedOverload(Object[] args, int index) {
        this.index = index;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[248]++;
        types = new Class<?>[args.length];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[249]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[250]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[40]++;


int CodeCoverConditionCoverageHelper_C88;
        for (int i = 0, l = args.length;(((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((i < l) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[40]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[41]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[42]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[251]++;
            Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[252]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((arg instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[151]++;
                arg = ((Wrapper)arg).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[253]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[152]++;}
            types[i] = arg == null ? null : arg.getClass();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[254]++;
        }
    }

    boolean matches(Object[] args) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[255]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((args.length != types.length) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[153]++;
            return false;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[154]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[256]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[43]++;


int CodeCoverConditionCoverageHelper_C91;
        for (int i = 0, l = args.length;(((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((i < l) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[43]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[44]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.loops[45]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[257]++;
            Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[258]++;
int CodeCoverConditionCoverageHelper_C92;
            if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((arg instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[155]++;
                arg = ((Wrapper)arg).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[259]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[156]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[260]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((arg == null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[157]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[261]++;
int CodeCoverConditionCoverageHelper_C94;
                if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((types[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[159]++; return false;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[160]++;}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[158]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[262]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((arg.getClass() != types[i]) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[161]++;
                return false;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[162]++;}
}
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[263]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((other instanceof ResolvedOverload) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[163]++;
            return false;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.branches[164]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5.statements[264]++;
        ResolvedOverload ovl = (ResolvedOverload) other;
        return Arrays.equals(types, ovl.types) && index == ovl.index;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(types);
    }
}

class CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5 ());
  }
    public static long[] statements = new long[265];
    public static long[] branches = new long[165];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[97];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJavaMethod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 96; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[46];

  public CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3ibhjjd2h0gqj8exr5 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaMethod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 264; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 164; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 96; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 45; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaMethod.java");
      for (int i = 1; i <= 264; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 164; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 96; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 15; i++) {
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

