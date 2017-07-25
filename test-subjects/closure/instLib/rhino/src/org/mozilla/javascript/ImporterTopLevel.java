/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * Class ImporterTopLevel
 *
 * This class defines a ScriptableObject that can be instantiated
 * as a top-level ("global") object to provide functionality similar
 * to Java's "import" statement.
 * <p>
 * This class can be used to create a top-level scope using the following code:
 * <pre>
 *  Scriptable scope = new ImporterTopLevel(cx);
 * </pre>
 * Then JavaScript code will have access to the following methods:
 * <ul>
 * <li>importClass - will "import" a class by making its unqualified name
 *                   available as a property of the top-level scope
 * <li>importPackage - will "import" all the classes of the package by
 *                     searching for unqualified names as classes qualified
 *                     by the given package.
 * </ul>
 * The following code from the shell illustrates this use:
 * <pre>
 * js> importClass(java.io.File)
 * js> f = new File('help.txt')
 * help.txt
 * js> importPackage(java.util)
 * js> v = new Vector()
 * []
 *
 */
public class ImporterTopLevel extends TopLevel {
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.ping();
  }

    static final long serialVersionUID = -9095380847465315412L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[1]++;
  }

    private static final Object IMPORTER_TAG = "Importer";
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[2]++;
  }

    public ImporterTopLevel() { }

    public ImporterTopLevel(Context cx) {
        this(cx, false);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[3]++;
    }

    public ImporterTopLevel(Context cx, boolean sealed)
    {
        initStandardObjects(cx, sealed);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[4]++;
    }

    @Override
    public String getClassName()
    {
        return (topScopeFlag) ? "global" : "JavaImporter";
    }

    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[5]++;
        ImporterTopLevel obj = new ImporterTopLevel();
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[6]++;
    }

    public void initStandardObjects(Context cx, boolean sealed)
    {
        // Assume that Context.initStandardObjects initialize JavaImporter
        // property lazily so the above init call is not yet called
        cx.initStandardObjects(this, sealed);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[7]++;
        topScopeFlag = true;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[8]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[9]++;
        // If seal is true then exportAsJSClass(cx, seal) would seal
        // this obj. Since this is scope as well, it would not allow
        // to add variables.
        IdFunctionObject ctor = exportAsJSClass(MAX_PROTOTYPE_ID, this, false);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[1]++;
            ctor.sealObject();
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[11]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[2]++;}
        // delete "constructor" defined by exportAsJSClass so "constructor"
        // name would refer to Object.constructor
        // and not to JavaImporter.prototype.constructor.
        delete("constructor");
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[12]++;
    }

    @Override
    public boolean has(String name, Scriptable start) {
        return super.has(name, start)
               || getPackageProperty(name, start) != NOT_FOUND;
    }

    @Override
    public Object get(String name, Scriptable start) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[13]++;
        Object result = super.get(name, start);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((result != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[3]++;
            return result;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[4]++;}
        result = getPackageProperty(name, start);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[15]++;
        return result;
    }

    private Object getPackageProperty(String name, Scriptable start) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[16]++;
        Object result = NOT_FOUND;
        Object[] elements;
        synchronized (importedPackages) {
            elements = importedPackages.toArray();
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[17]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < elements.length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[1]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[2]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[3]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[19]++;
            NativeJavaPackage p = (NativeJavaPackage) elements[i];
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[20]++;
            Object v = p.getPkgProperty(name, start, false);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((v instanceof NativeJavaPackage) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[5]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((result == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[7]++;
                    result = v;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[23]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[8]++;
                    throw Context.reportRuntimeError2(
                        "msg.ambig.import", result.toString(), v.toString());
                }

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[6]++;}
        }
        return result;
    }

    /**
     * @deprecated Kept only for compatibility.
     */
    public void importPackage(Context cx, Scriptable thisObj, Object[] args,
                              Function funObj)
    {
        js_importPackage(args);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[24]++;
    }

    private Object js_construct(Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[25]++;
        ImporterTopLevel result = new ImporterTopLevel();
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[4]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[5]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[6]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[27]++;
            Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((arg instanceof NativeJavaClass) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[9]++;
                result.importClass((NativeJavaClass)arg);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[29]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[10]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[30]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((arg instanceof NativeJavaPackage) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[11]++;
                result.importPackage((NativeJavaPackage)arg);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[31]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[12]++;
                throw Context.reportRuntimeError1(
                    "msg.not.class.not.pkg", Context.toString(arg));
            }
}
        }
        // set explicitly prototype and scope
        // as otherwise in top scope mode BaseFunction.construct
        // would keep them set to null. It also allow to use
        // JavaImporter without new and still get properly
        // initialized object.
        result.setParentScope(scope);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[32]++;
        result.setPrototype(this);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[33]++;
        return result;
    }

    private Object js_importClass(Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[7]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[8]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[9]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[35]++;
            Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((arg instanceof NativeJavaClass) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[13]++;
                throw Context.reportRuntimeError1(
                    "msg.not.class", Context.toString(arg));

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[14]++;}
            importClass((NativeJavaClass)arg);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[37]++;
        }
        return Undefined.instance;
    }

    private Object js_importPackage(Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[38]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[10]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[10]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[11]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[12]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[39]++;
            Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[40]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((arg instanceof NativeJavaPackage) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[15]++;
                throw Context.reportRuntimeError1(
                    "msg.not.pkg", Context.toString(arg));

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[16]++;}
            importPackage((NativeJavaPackage)arg);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[41]++;
        }
        return Undefined.instance;
    }

    private void importPackage(NativeJavaPackage pkg)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
        if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((pkg == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[17]++;
            return;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[18]++;}
        synchronized (importedPackages) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[43]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[13]++;


int CodeCoverConditionCoverageHelper_C14;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((j != importedPackages.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[13]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[14]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.loops[15]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[44]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pkg.equals(importedPackages.get(j))) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[19]++;
                    return;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[20]++;}
            }
            importedPackages.add(pkg);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[45]++;
        }
    }

    private void importClass(NativeJavaClass cl)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[46]++;
        String s = cl.getClassObject().getName();
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[47]++;
        String n = s.substring(s.lastIndexOf('.')+1);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[48]++;
        Object val = get(n, this);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[49]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((val != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((val != cl) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[21]++;
            throw Context.reportRuntimeError1("msg.prop.defined", n);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[22]++;}
        //defineProperty(n, cl, DONTENUM);
        put(n, this, cl);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[50]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[51]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[23]++;   arity=0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[52]++; s="constructor";
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[53]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[54]++;   break;
          case Id_importClass:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[24]++;   arity=1;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[55]++; s="importClass";
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[56]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[57]++;   break;
          case Id_importPackage:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[25]++; arity=1;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[58]++; s="importPackage";
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[59]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[60]++; break;
          default:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[26]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(IMPORTER_TAG, id, s, arity);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[61]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((f.hasTag(IMPORTER_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[27]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[28]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[63]++;
        int id = f.methodId();
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[64]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[29]++;
            return js_construct(scope, args);

          case Id_importClass:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[30]++;
            return realThis(thisObj, f).js_importClass(args);

          case Id_importPackage:
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[31]++;
            return realThis(thisObj, f).js_importPackage(args); default : CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[32]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private ImporterTopLevel realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[65]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((topScopeFlag) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[33]++;
            // when used as top scope importPackage and importClass are global
            // function that ignore thisObj
            return this;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[34]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[66]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((thisObj instanceof ImporterTopLevel) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[35]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[36]++;}
        return (ImporterTopLevel)thisObj;
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:15:24 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[67]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[68]++; String X = null; int c;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[69]++;
            int s_length = s.length();
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[37]++;
                c=s.charAt(0);
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[71]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[39]++; X="constructor";
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[73]++;id=Id_constructor;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[74]++;
 }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[40]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[75]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[41]++; X="importClass";
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[76]++;id=Id_importClass;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[77]++;
 } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[42]++;}
}

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[38]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[78]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((s_length==13) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[43]++; X="importPackage";
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[79]++;id=Id_importPackage;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[80]++;
 } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[44]++;}
}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[81]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[45]++; id = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[82]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.branches[46]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[83]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor          = 1,
        Id_importClass          = 2,
        Id_importPackage        = 3,
        MAX_PROTOTYPE_ID        = 3;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[84]++;
  }

// #/string_id_map#

    private ObjArray importedPackages = new ObjArray();
  {
    CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt.statements[85]++;
  }
    private boolean topScopeFlag;
}

class CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt ());
  }
    public static long[] statements = new long[86];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ImporterTopLevel.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 24; i++) {
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

  public CodeCoverCoverageCounter$adralqrs9n89mg8no8xo65d8coe5od2ah7w2y8um21ic6rtt () {
    super("org.mozilla.javascript.RHINO-SRC-ImporterTopLevel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 85; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ImporterTopLevel.java");
      for (int i = 1; i <= 85; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
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

