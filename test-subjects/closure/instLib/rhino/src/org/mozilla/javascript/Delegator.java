/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * This is a helper class for implementing wrappers around Scriptable
 * objects. It implements the Function interface and delegates all
 * invocations to a delegee Scriptable object. The normal use of this
 * class involves creating a sub-class and overriding one or more of
 * the methods.
 *
 * A useful application is the implementation of interceptors,
 * pre/post conditions, debugging.
 *
 * @see Function
 * @see Scriptable
 */

public class Delegator implements Function {
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.ping();
  }


    protected Scriptable obj = null;
  {
    CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[1]++;
  }

    /**
     * Create a Delegator prototype.
     *
     * This constructor should only be used for creating prototype
     * objects of Delegator.
     *
     * @see org.mozilla.javascript.Delegator#construct
     */
    public Delegator() {
    }

    /**
     * Create a new Delegator that forwards requests to a delegee
     * Scriptable object.
     *
     * @param obj the delegee
     * @see org.mozilla.javascript.Scriptable
     */
    public Delegator(Scriptable obj) {
        this.obj = obj;
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[2]++;
    }

    /**
     * Crete new Delegator instance.
     * The default implementation calls this.getClass().newInstance().
     *
     * @see #construct(Context cx, Scriptable scope, Object[] args)
     */
    protected Delegator newInstance()
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[3]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return this.getClass().newInstance();
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.branches[2]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.branches[1]++;
}
  }
    }

    /**
     * Retrieve the delegee.
     *
     * @return the delegee
     */
    public Scriptable getDelegee() {
        return obj;
    }
    /**
     * Set the delegee.
     *
     * @param obj the delegee
     * @see org.mozilla.javascript.Scriptable
     */
    public void setDelegee(Scriptable obj) {
        this.obj = obj;
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[4]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#getClassName
     */
    public String getClassName() {
        return obj.getClassName();
    }
    /**
     * @see org.mozilla.javascript.Scriptable#get(String, Scriptable)
     */
    public Object get(String name, Scriptable start) {
        return obj.get(name,start);
    }
    /**
     * @see org.mozilla.javascript.Scriptable#get(int, Scriptable)
     */
    public Object get(int index, Scriptable start) {
        return obj.get(index,start);
        }
    /**
     * @see org.mozilla.javascript.Scriptable#has(String, Scriptable)
     */
    public boolean has(String name, Scriptable start) {
        return obj.has(name,start);
        }
    /**
     * @see org.mozilla.javascript.Scriptable#has(int, Scriptable)
     */
    public boolean has(int index, Scriptable start) {
        return obj.has(index,start);
        }
    /**
     * @see org.mozilla.javascript.Scriptable#put(String, Scriptable, Object)
     */
    public void put(String name, Scriptable start, Object value) {
        obj.put(name,start,value);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[5]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#put(int, Scriptable, Object)
     */
    public void put(int index, Scriptable start, Object value) {
        obj.put(index,start,value);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[6]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#delete(String)
     */
    public void delete(String name) {
        obj.delete(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[7]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#delete(int)
     */
    public void delete(int index) {
        obj.delete(index);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[8]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#getPrototype
     */
    public Scriptable getPrototype() {
        return obj.getPrototype();
    }
    /**
     * @see org.mozilla.javascript.Scriptable#setPrototype
     */
    public void setPrototype(Scriptable prototype) {
        obj.setPrototype(prototype);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[9]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#getParentScope
     */
    public Scriptable getParentScope() {
        return obj.getParentScope();
    }
    /**
     * @see org.mozilla.javascript.Scriptable#setParentScope
     */
    public void setParentScope(Scriptable parent) {
        obj.setParentScope(parent);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[10]++;
    }
    /**
     * @see org.mozilla.javascript.Scriptable#getIds
     */
    public Object[] getIds() {
        return obj.getIds();
    }
    /**
     * Note that this method does not get forwarded to the delegee if
     * the <code>hint</code> parameter is null,
     * <code>ScriptRuntime.ScriptableClass</code> or
     * <code>ScriptRuntime.FunctionClass</code>. Instead the object
     * itself is returned.
     *
     * @param hint the type hint
     * @return the default value
     *
     * @see org.mozilla.javascript.Scriptable#getDefaultValue
     */
    public Object getDefaultValue(Class<?> hint) {
        return (hint == null ||
                hint == ScriptRuntime.ScriptableClass ||
                hint == ScriptRuntime.FunctionClass) ?
            this : obj.getDefaultValue(hint);
    }
    /**
     * @see org.mozilla.javascript.Scriptable#hasInstance
     */
    public boolean hasInstance(Scriptable instance) {
        return obj.hasInstance(instance);
    }
    /**
     * @see org.mozilla.javascript.Function#call
     */
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return ((Function)obj).call(cx,scope,thisObj,args);
    }

    /**
     * Note that if the <code>delegee</code> is <code>null</code>,
     * this method creates a new instance of the Delegator itself
     * rathert than forwarding the call to the
     * <code>delegee</code>. This permits the use of Delegator
     * prototypes.
     *
     * @param cx the current Context for this thread
     * @param scope an enclosing scope of the caller except
     *              when the function is called from a closure.
     * @param args the array of arguments
     * @return the allocated object
     *
     * @see Function#construct(Context, Scriptable, Object[])
     */
    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.branches[3]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[12]++;
            //this little trick allows us to declare prototype objects for
            //Delegators
            Delegator n = newInstance();
            Scriptable delegee;
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.branches[5]++;
                delegee = new NativeObject();
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[14]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.branches[6]++;
                delegee = ScriptRuntime.toObject(cx, scope, args[0]);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[15]++;
            }
            n.setDelegee(delegee);
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.statements[16]++;
            return n;

        }
        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp.branches[4]++;
            return ((Function)obj).construct(cx,scope,args);
        }
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Delegator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$iypomt0ag7yuozzggr86oxd7quzh4u39zmgyp () {
    super("org.mozilla.javascript.RHINO-SRC-Delegator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Delegator.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

