/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

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

public class NativeJavaPackage extends ScriptableObject
{
  static {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.ping();
  }

    static final long serialVersionUID = 7445054382212031523L;
  static {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[1]++;
  }

    NativeJavaPackage(boolean internalUsage, String packageName,
                      ClassLoader classLoader)
    {
        this.packageName = packageName;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[2]++;
        this.classLoader = classLoader;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[3]++;
    }

    /**
     * @deprecated NativeJavaPackage is an internal class, do not use
     * it directly.
     */
    public NativeJavaPackage(String packageName, ClassLoader classLoader) {
        this(false, packageName, classLoader);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[4]++;
    }

    /**
     * @deprecated NativeJavaPackage is an internal class, do not use
     * it directly.
     */
    public NativeJavaPackage(String packageName) {
        this(false, packageName,
             Context.getCurrentContext().getApplicationClassLoader());
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[5]++;
    }

    @Override
    public String getClassName() {
        return "JavaPackage";
    }

    @Override
    public boolean has(String id, Scriptable start) {
        return true;
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return false;
    }

    @Override
    public void put(String id, Scriptable start, Object value) {
        // Can't add properties to Java packages.  Sorry.
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        throw Context.reportRuntimeError0("msg.pkg.int");
    }

    @Override
    public Object get(String id, Scriptable start) {
        return getPkgProperty(id, start, true);
    }

    @Override
    public Object get(int index, Scriptable start) {
        return NOT_FOUND;
    }

    // set up a name which is known to be a package so we don't
    // need to look for a class by that name
    NativeJavaPackage forcePackage(String name, Scriptable scope)
    {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[6]++;
        Object cached = super.get(name, this);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((cached != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cached instanceof NativeJavaPackage) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[1]++;
            return (NativeJavaPackage) cached;

        } else {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[2]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[8]++;
            String newPackage = packageName.length() == 0
                                ? name
                                : packageName + "." + name;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[9]++;
            NativeJavaPackage pkg = new NativeJavaPackage(true, newPackage, classLoader);
            ScriptRuntime.setObjectProtoAndParent(pkg, scope);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[10]++;
            super.put(name, this, pkg);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[11]++;
            return pkg;
        }
    }

    synchronized Object getPkgProperty(String name, Scriptable start,
                                       boolean createPkg)
    {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[12]++;
        Object cached = super.get(name, start);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cached != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[3]++;
            return cached;
} else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[4]++;}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((negativeCache != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((negativeCache.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[5]++;
            // Performance optimization: see bug 421071
            return null;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[6]++;}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[15]++;

        String className = (packageName.length() == 0)
                               ? name : packageName + '.' + name;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[16]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[17]++;
        ClassShutter shutter = cx.getClassShutter();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[18]++;
        Scriptable newValue = null;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((shutter == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((shutter.visibleToScripts(className)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[7]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[20]++;
            Class<?> cl = null;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((classLoader != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[9]++;
                cl = Kit.classOrNull(classLoader, className);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[22]++;

            } else {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[10]++;
                cl = Kit.classOrNull(className);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[23]++;
            }
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((cl != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[11]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[25]++;
                WrapFactory wrapFactory = cx.getWrapFactory();
                newValue = wrapFactory.wrapJavaClass(cx, getTopLevelScope(this), cl);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[26]++;
                newValue.setPrototype(getPrototype());
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[27]++;

            } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[8]++;}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((newValue == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[13]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((createPkg) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[15]++;
                NativeJavaPackage pkg;
                pkg = new NativeJavaPackage(true, className, classLoader);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[30]++;
                ScriptRuntime.setObjectProtoAndParent(pkg, getParentScope());
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[31]++;
                newValue = pkg;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[32]++;

            } else {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[16]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
                // add to negative cache
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((negativeCache == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[17]++;
                    negativeCache = new HashSet<String>();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[34]++;
} else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[18]++;}
                negativeCache.add(name);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[35]++;
            }

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[14]++;}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((newValue != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[19]++;
            // Make it available for fast lookup and sharing of
            // lazily-reflected constructors and static members.
            super.put(name, start, newValue);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[37]++;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[20]++;}
        return newValue;
    }

    @Override
    public Object getDefaultValue(Class<?> ignored) {
        return toString();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[38]++;
        this.classLoader = Context.getCurrentContext().getApplicationClassLoader();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[39]++;
    }

    @Override
    public String toString() {
        return "[JavaPackage " + packageName + "]";
    }

    @Override
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj instanceof NativeJavaPackage) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[21]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[41]++;
            NativeJavaPackage njp = (NativeJavaPackage)obj;
            return packageName.equals(njp.packageName) &&
                   classLoader == njp.classLoader;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.branches[22]++;}
        return false;
    }

    @Override
    public int hashCode() {
        return packageName.hashCode() ^
               (classLoader == null ? 0 : classLoader.hashCode());
    }

    private String packageName;
    private transient ClassLoader classLoader;
    private Set<String> negativeCache = null;
  {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt.statements[42]++;
  }
}

class CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJavaPackage.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$21tu3emdl0l6sfnhl5otqoe2bwt3y3a11hq9e3zn3bpdy0c3tt () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaPackage.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaPackage.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

