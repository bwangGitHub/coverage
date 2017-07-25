/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.*;
import java.io.*;

/**
 * Wrappper class for Method and Constructor instances to cache
 * getParameterTypes() results, recover from IllegalAccessException
 * in some cases and provide serialization support.
 *
 */

final class MemberBox implements Serializable
{
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.ping();
  }

    static final long serialVersionUID = 6358550398665688245L;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[1]++;
  }

    private transient Member memberObject;
    transient Class<?>[] argTypes;
    transient Object delegateTo;
    transient boolean vararg;


    MemberBox(Method method)
    {
        init(method);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[2]++;
    }

    MemberBox(Constructor<?> constructor)
    {
        init(constructor);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[3]++;
    }

    private void init(Method method)
    {
        this.memberObject = method;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[4]++;
        this.argTypes = method.getParameterTypes();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[5]++;
        this.vararg = VMBridge.instance.isVarArgs(method);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[6]++;
    }

    private void init(Constructor<?> constructor)
    {
        this.memberObject = constructor;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[7]++;
        this.argTypes = constructor.getParameterTypes();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[8]++;
        this.vararg = VMBridge.instance.isVarArgs(constructor);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[9]++;
    }

    Method method()
    {
        return (Method)memberObject;
    }

    Constructor<?> ctor()
    {
        return (Constructor<?>)memberObject;
    }

    Member member()
    {
        return memberObject;
    }

    boolean isMethod()
    {
        return memberObject instanceof Method;
    }

    boolean isCtor()
    {
        return memberObject instanceof Constructor;
    }

    boolean isStatic()
    {
        return Modifier.isStatic(memberObject.getModifiers());
    }

    String getName()
    {
        return memberObject.getName();
    }

    Class<?> getDeclaringClass()
    {
        return memberObject.getDeclaringClass();
    }

    String toJavaDeclaration()
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[10]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[1]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[12]++;
            Method method = method();
            sb.append(method.getReturnType());
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[13]++;
            sb.append(' ');
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[14]++;
            sb.append(method.getName());
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[15]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[2]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[16]++;
            Constructor<?> ctor = ctor();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[17]++;
            String name = ctor.getDeclaringClass().getName();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[18]++;
            int lastDot = name.lastIndexOf('.');
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((lastDot >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[3]++;
                name = name.substring(lastDot + 1);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[20]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[4]++;}
            sb.append(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[21]++;
        }
        sb.append(JavaMembers.liveConnectSignature(argTypes));
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[22]++;
        return sb.toString();
    }

    @Override
    public String toString()
    {
        return memberObject.toString();
    }

    Object invoke(Object target, Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[23]++;
        Method method = method();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[24]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[25]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                return method.invoke(target, args);
            } catch (IllegalAccessException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[7]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[26]++;
                Method accessible = searchAccessibleMethod(method, argTypes);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((accessible != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[8]++;
                    memberObject = accessible;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[28]++;
                    method = accessible;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[29]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[9]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
                    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((VMBridge.instance.tryToMakeAccessible(method)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[10]++;
                        throw Context.throwAsScriptRuntimeEx(ex);

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[11]++;}
                }
                // Retry after recovery
                return method.invoke(target, args);
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[6]++;
}
  }
        } catch (InvocationTargetException ite) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[12]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[31]++;
            // Must allow ContinuationPending exceptions to propagate unhindered
            Throwable e = ite;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[32]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
            do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[1]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[2]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[3]++;
}
                e = ((InvocationTargetException) e).getTargetException();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[33]++;
            } while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((e instanceof InvocationTargetException) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false));
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((e instanceof ContinuationPending) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[13]++;
                throw (ContinuationPending) e;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[14]++;}
            throw Context.throwAsScriptRuntimeEx(e);
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[15]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[5]++;
}
  }
    }

    Object newInstance(Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[35]++;
        Constructor<?> ctor = ctor();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[36]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[37]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
            try {
CodeCoverTryBranchHelper_Try4 = true;
                return ctor.newInstance(args);
            } catch (IllegalAccessException ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[18]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((VMBridge.instance.tryToMakeAccessible(ctor)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[19]++;
                    throw Context.throwAsScriptRuntimeEx(ex);

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[20]++;}
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[17]++;
}
  }
            return ctor.newInstance(args);
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[21]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[16]++;
}
  }
    }

    private static Method searchAccessibleMethod(Method method, Class<?>[] params)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[39]++;
        int modifiers = method.getModifiers();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((Modifier.isPublic(modifiers)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(modifiers)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[22]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[41]++;
            Class<?> c = method.getDeclaringClass();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Modifier.isPublic(c.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[24]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[43]++;
                String name = method.getName();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[44]++;
                Class<?>[] intfs = c.getInterfaces();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[45]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
                for (int i = 0, N = intfs.length;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[4]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[5]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[6]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[46]++;
                    Class<?> intf = intfs[i];
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((Modifier.isPublic(intf.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[26]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[48]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                        try {
CodeCoverTryBranchHelper_Try5 = true;
                            return intf.getMethod(name, params);
                        } catch (NoSuchMethodException ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[29]++;
                        } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[30]++;  } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[28]++;
}
  }

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[27]++;}
                }
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[49]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[7]++;


                for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[7]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[8]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[9]++;
}
                    c = c.getSuperclass();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[50]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[31]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[52]++; break;
 } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[32]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Modifier.isPublic(c.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[33]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[54]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
                        try {
CodeCoverTryBranchHelper_Try6 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[55]++;
                            Method m = c.getMethod(name, params);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[56]++;
                            int mModifiers = m.getModifiers();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
                            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((Modifier.isPublic(mModifiers)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(mModifiers)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false))
                            {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[36]++;
                                return m;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[37]++;}
                        } catch (NoSuchMethodException ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[38]++;
                        } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[39]++;  } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[35]++;
}
  }

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[34]++;}
                }

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[25]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[23]++;}
        return null;
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[58]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[59]++;
        Member member = readMember(in);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((member instanceof Method) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[40]++;
            init((Method)member);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[61]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[41]++;
            init((Constructor<?>)member);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[62]++;
        }
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[63]++;
        writeMember(out, memberObject);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[64]++;
    }

    /**
     * Writes a Constructor or Method object.
     *
     * Methods and Constructors are not serializable, so we must serialize
     * information about the class, the name, and the parameters and
     * recreate upon deserialization.
     */
    private static void writeMember(ObjectOutputStream out, Member member)
        throws IOException
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[42]++;
            out.writeBoolean(false);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[66]++;
            return;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[43]++;}
        out.writeBoolean(true);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[67]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[68]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((member instanceof Method) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((member instanceof Constructor) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[44]++;
            throw new IllegalArgumentException("not Method or Constructor");
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[45]++;}
        out.writeBoolean(member instanceof Method);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[69]++;
        out.writeObject(member.getName());
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[70]++;
        out.writeObject(member.getDeclaringClass());
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[71]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[72]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((member instanceof Method) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[46]++;
            writeParameters(out, ((Method) member).getParameterTypes());
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[73]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[47]++;
            writeParameters(out, ((Constructor<?>) member).getParameterTypes());
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[74]++;
        }
    }

    /**
     * Reads a Method or a Constructor from the stream.
     */
    private static Member readMember(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[75]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((in.readBoolean()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[48]++;
            return null;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[49]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[76]++;
        boolean isMethod = in.readBoolean();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[77]++;
        String name = (String) in.readObject();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[78]++;
        Class<?> declaring = (Class<?>) in.readObject();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[79]++;
        Class<?>[] parms = readParameters(in);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[80]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[81]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((isMethod) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[51]++;
                return declaring.getMethod(name, parms);

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[52]++;
                return declaring.getConstructor(parms);
            }
        } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[53]++;
            throw new IOException("Cannot find member: " + e);
        } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[50]++;
}
  }
    }

    private static final Class<?>[] primitives = {
        Boolean.TYPE,
        Byte.TYPE,
        Character.TYPE,
        Double.TYPE,
        Float.TYPE,
        Integer.TYPE,
        Long.TYPE,
        Short.TYPE,
        Void.TYPE
    };
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[82]++;
  }

    /**
     * Writes an array of parameter types to the stream.
     *
     * Requires special handling because primitive types cannot be
     * found upon deserialization by the default Java implementation.
     */
    private static void writeParameters(ObjectOutputStream out, Class<?>[] parms)
        throws IOException
    {
        out.writeShort(parms.length);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[83]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[84]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[10]++;


int CodeCoverConditionCoverageHelper_C22;
    outer:
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < parms.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[10]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[11]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[12]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[85]++;
            Class<?> parm = parms[i];
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[86]++;
            boolean primitive = parm.isPrimitive();
            out.writeBoolean(primitive);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[87]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[88]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((primitive) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[54]++;
                out.writeObject(parm);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[89]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[90]++;
                continue;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[55]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[91]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[13]++;


int CodeCoverConditionCoverageHelper_C24;
            for (int j=0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((j < primitives.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[13]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[14]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[15]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[92]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((parm.equals(primitives[j])) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[56]++;
                    out.writeByte(j);
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[93]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[94]++;
                    continue outer;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[57]++;}
            }
            throw new IllegalArgumentException("Primitive " + parm +
                                               " not found");
        }
    }

    /**
     * Reads an array of parameter types from the stream.
     */
    private static Class<?>[] readParameters(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[95]++;
        Class<?>[] result = new Class[in.readShort()];
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[96]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[16]++;


int CodeCoverConditionCoverageHelper_C26;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[16]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[17]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.loops[18]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[97]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((in.readBoolean()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[58]++;
                result[i] = (Class<?>) in.readObject();
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[98]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[99]++;
                continue;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.branches[59]++;}
            result[i] = primitives[in.readByte()];
CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx.statements[100]++;
        }
        return result;
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx ());
  }
    public static long[] statements = new long[101];
    public static long[] branches = new long[60];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-MemberBox.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,0,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzguetx9y78ppt8iv9nuyhcx () {
    super("org.mozilla.javascript.RHINO-SRC-MemberBox.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 100; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 59; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-MemberBox.java");
      for (int i = 1; i <= 100; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 59; i++) {
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
      for (int i = 1; i <= 6; i++) {
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

