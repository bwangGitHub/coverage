/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

public class IdFunctionObject extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.ping();
  }


    static final long serialVersionUID = -5332312783643935019L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[1]++;
  }

    public IdFunctionObject(IdFunctionCall idcall, Object tag, int id, int arity)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((arity < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[1]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[2]++;}

        this.idcall = idcall;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[3]++;
        this.tag = tag;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[4]++;
        this.methodId = id;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[5]++;
        this.arity = arity;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[6]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arity < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[3]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[4]++;}
    }

    public IdFunctionObject(IdFunctionCall idcall, Object tag, int id,
                            String name, int arity, Scriptable scope)
    {
        super(scope, null);
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[8]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((arity < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[5]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[6]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[7]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[8]++;}

        this.idcall = idcall;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[11]++;
        this.tag = tag;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[12]++;
        this.methodId = id;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[13]++;
        this.arity = arity;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[14]++;
        this.functionName = name;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[15]++;
    }

    public void initFunction(String name, Scriptable scope)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[9]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[10]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[11]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[12]++;}
        this.functionName = name;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[18]++;
        setParentScope(scope);
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[19]++;
    }

    public final boolean hasTag(Object tag)
    {
        return tag == null ? this.tag == null : tag.equals(this.tag);
    }

    public final int methodId()
    {
        return methodId;
    }

    public final void markAsConstructor(Scriptable prototypeProperty)
    {
        useCallAsConstructor = true;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[20]++;
        setImmunePrototypeProperty(prototypeProperty);
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[21]++;
    }

    public final void addAsProperty(Scriptable target)
    {
        ScriptableObject.defineProperty(target, functionName, this,
                                        ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[22]++;
    }

    public void exportAsScopeProperty()
    {
        addAsProperty(getParentScope());
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[23]++;
    }

    @Override
    public Scriptable getPrototype()
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[24]++;
        // Lazy initialization of prototype: for native functions this
        // may not be called at all
        Scriptable proto = super.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((proto == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[13]++;
            proto = getFunctionPrototype(getParentScope());
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[26]++;
            setPrototype(proto);
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[27]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[14]++;}
        return proto;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return idcall.execIdCall(this, cx, scope, thisObj, args);
    }

    @Override
    public Scriptable createObject(Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((useCallAsConstructor) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[15]++;
            return null;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[16]++;}
        // Throw error if not explicitly coded to be used as constructor,
        // to satisfy ECMAScript standard (see bugzilla 202019).
        // To follow current (2003-05-01) SpiderMonkey behavior, change it to:
        // return super.createObject(cx, scope);
        throw ScriptRuntime.typeError1("msg.not.ctor", functionName);
    }

    @Override
    String decompile(int indent, int flags)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[29]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[30]++;
        boolean justbody = (0 != (flags & Decompiler.ONLY_BODY_FLAG));
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((justbody) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[17]++;
            sb.append("function ");
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[32]++;
            sb.append(getFunctionName());
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[33]++;
            sb.append("() { ");
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[34]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[18]++;}
        sb.append("[native code for ");
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[35]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((idcall instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[19]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[37]++;
            Scriptable sobj = (Scriptable)idcall;
            sb.append(sobj.getClassName());
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[38]++;
            sb.append('.');
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[39]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.branches[20]++;}
        sb.append(getFunctionName());
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[40]++;
        sb.append(", arity=");
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[41]++;
        sb.append(getArity());
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[42]++;
        sb.append(justbody ? "]\n" : "] }\n");
CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5.statements[43]++;
        return sb.toString();
    }

    @Override
    public int getArity()
    {
        return arity;
    }

    @Override
    public int getLength() { return getArity(); }

    @Override
    public String getFunctionName()
    {
        return (functionName == null) ? "" : functionName;
    }

    public final RuntimeException unknown()
    {
        // It is program error to call id-like methods for unknown function
        return new IllegalArgumentException(
            "BAD FUNCTION ID="+methodId+" MASTER="+idcall);
    }

    private final IdFunctionCall idcall;
    private final Object tag;
    private final int methodId;
    private int arity;
    private boolean useCallAsConstructor;
    private String functionName;
}

class CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5 ());
  }
    public static long[] statements = new long[44];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-IdFunctionObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$adralqrs9n89mg8no7v4v1lqf9m83qj08u0yv7jgi5le4rj5 () {
    super("org.mozilla.javascript.RHINO-SRC-IdFunctionObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 43; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-IdFunctionObject.java");
      for (int i = 1; i <= 43; i++) {
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
    for (int i = 1; i <= 10; i++) {
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

