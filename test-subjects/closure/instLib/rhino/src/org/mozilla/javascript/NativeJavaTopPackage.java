/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class reflects Java packages into the JavaScript environment.  We
 * lazily reflect classes and subpackages, and use a caching/sharing
 * system to ensure that members reflected into one JavaPackage appear
 * in all other references to the same package (as with Packages.java.lang
 * and java.lang).
 *
 * @see NativeJavaArray
 * @see NativeJavaObject
 * @see NativeJavaClass
 */

public class NativeJavaTopPackage
    extends NativeJavaPackage implements Function, IdFunctionCall
{
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.ping();
  }

    static final long serialVersionUID = -1455787259477709999L;
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[1]++;
  }

    // we know these are packages so we can skip the class check
    // note that this is ok even if the package isn't present.
    private static final String[][] commonPackages = {
            {"java", "lang", "reflect"},
            {"java", "io"},
            {"java", "math"},
            {"java", "net"},
            {"java", "util", "zip"},
            {"java", "text", "resources"},
            {"java", "applet"},
            {"javax", "swing"}
    };
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[2]++;
  }

    NativeJavaTopPackage(ClassLoader loader)
    {
        super(true, "", loader);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[3]++;
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return construct(cx, scope, args);
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[4]++;
        ClassLoader loader = null;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[1]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[6]++;
            Object arg = args[0];
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arg instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[3]++;
                arg = ((Wrapper)arg).unwrap();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[8]++;

            } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[4]++;}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((arg instanceof ClassLoader) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[5]++;
                loader = (ClassLoader)arg;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[10]++;

            } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[2]++;}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((loader == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[7]++;
            Context.reportRuntimeError0("msg.not.classloader");
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[12]++;
            return null;

        } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[8]++;}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[13]++;
        NativeJavaPackage pkg = new NativeJavaPackage(true, "", loader);
        ScriptRuntime.setObjectProtoAndParent(pkg, scope);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[14]++;
        return pkg;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[15]++;
        ClassLoader loader = cx.getApplicationClassLoader();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[16]++;
        final NativeJavaTopPackage top = new NativeJavaTopPackage(loader);
        top.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[17]++;
        top.setParentScope(scope);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[18]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i != commonPackages.length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[1]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[2]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[3]++;
}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[20]++;
            NativeJavaPackage parent = top;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((j != commonPackages[i].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[4]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[5]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[6]++;
}
                parent = parent.forcePackage(commonPackages[i][j], scope);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[22]++;
            }
        }
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[23]++;

        // getClass implementation
        IdFunctionObject getClass = new IdFunctionObject(top, FTAG, Id_getClass,
                                                         "getClass", 1, scope);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[24]++;

        // We want to get a real alias, and not a distinct JavaPackage
        // with the same packageName, so that we share classes and top
        // that are underneath.
        String[] topNames = ScriptRuntime.getTopPackageNames();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[25]++;
        NativeJavaPackage[] topPackages = new NativeJavaPackage[topNames.length];
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[26]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < topNames.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[7]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[8]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[9]++;
}
            topPackages[i] = (NativeJavaPackage)top.get(topNames[i], top);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[27]++;
        }
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[28]++;

        // It's safe to downcast here since initStandardObjects takes
        // a ScriptableObject.
        ScriptableObject global = (ScriptableObject) scope;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[9]++;
            getClass.sealObject();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[30]++;

        } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[10]++;}
        getClass.exportAsScopeProperty();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[31]++;
        global.defineProperty("Packages", top, ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[32]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[33]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[10]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < topNames.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[10]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[11]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[12]++;
}
            global.defineProperty(topNames[i], topPackages[i],
                                  ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[34]++;
        }
    }

    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((f.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[11]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((f.methodId() == Id_getClass) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[13]++;
                return js_getClass(cx, scope, args);

            } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[12]++;}
        throw f.unknown();
    }

    private Scriptable js_getClass(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((args[0] instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[15]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[38]++;
            Scriptable result = this;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[39]++;
            Class<?> cl = ((Wrapper) args[0]).unwrap().getClass();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[40]++;
            // Evaluate the class name by getting successive properties of
            // the string to find the appropriate NativeJavaClass object
            String name = cl.getName();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[41]++;
            int offset = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[42]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[13]++;


            for (;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[13]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[14]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.loops[15]++;
}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[43]++;
                int index = name.indexOf('.', offset);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[44]++;
                String propName = index == -1
                                  ? name.substring(offset)
                                  : name.substring(offset, index);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[45]++;
                Object prop = result.get(propName, result);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[46]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((prop instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[17]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[47]++;
                    break;
} else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[18]++;}  // fall through to error
                result = (Scriptable) prop;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[48]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[49]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[19]++;
                    return result;
} else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[20]++;}
                offset = index+1;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[50]++;
            }

        } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.branches[16]++;}
        throw Context.reportRuntimeError0("msg.not.java.obj");
    }

    private static final Object FTAG = "JavaTopPackage";
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[51]++;
  }
    private static final int Id_getClass = 1;
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl.statements[52]++;
  }
}

class CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl ());
  }
    public static long[] statements = new long[53];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJavaTopPackage.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1};
    for (int i = 1; i <= 15; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$khgdrpvbfyfsmmlwuwp7kru906wjganc5evjc1u69js6rtls252uzl () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaTopPackage.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 52; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaTopPackage.java");
      for (int i = 1; i <= 52; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

