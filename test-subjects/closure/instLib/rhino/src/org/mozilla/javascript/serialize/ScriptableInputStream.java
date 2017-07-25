/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript.serialize;

import java.io.*;

import org.mozilla.javascript.*;

/**
 * Class ScriptableInputStream is used to read in a JavaScript
 * object or function previously serialized with a ScriptableOutputStream.
 * References to names in the exclusion list
 * replaced with references to the top-level scope specified during
 * creation of the ScriptableInputStream.
 *
 */

public class ScriptableInputStream extends ObjectInputStream {
  static {
    CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.ping();
  }


    /**
     * Create a ScriptableInputStream.
     * @param in the InputStream to read from.
     * @param scope the top-level scope to create the object in.
     */
    public ScriptableInputStream(InputStream in, Scriptable scope)
        throws IOException
    {
        super(in);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[1]++;
        this.scope = scope;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[2]++;
        enableResolveObject(true);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[3]++;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[4]++;
        Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[1]++;
            this.classLoader = cx.getApplicationClassLoader();
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[6]++;

        } else {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[2]++;}
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc)
        throws IOException, ClassNotFoundException
    {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[7]++;
        String name = desc.getName();
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((classLoader != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[3]++;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[9]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                return classLoader.loadClass(name);
            } catch (ClassNotFoundException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[6]++;
                // fall through to default loading
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[5]++;
}
  }

        } else {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[4]++;}
        return super.resolveClass(desc);
    }

    @Override
    protected Object resolveObject(Object obj)
        throws IOException
    {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof ScriptableOutputStream.PendingLookup) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[7]++;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[11]++;
            String name = ((ScriptableOutputStream.PendingLookup)obj).getName();
            obj = ScriptableOutputStream.lookupQualifiedName(scope, name);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[12]++;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[9]++;
                throw new IOException("Object " + name + " not found upon " +
                                      "deserialization.");

            } else {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[10]++;}

        }else {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[8]++;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[14]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof UniqueTag) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[11]++;
            obj = ((UniqueTag)obj).readResolve();
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[15]++;

        }else {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[12]++;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[16]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[13]++;
            obj = ((Undefined)obj).readResolve();
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.statements[17]++;

        } else {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl.branches[14]++;}
}
}
        return obj;
    }

    private Scriptable scope;
    private ClassLoader classLoader;
}

class CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.serialize.RHINO-SRC-ScriptableInputStream.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$41o4hx3ypdgwbkwrva5nsogyfohb9ks23zcfl3p5mq8fux6gv7bba9nl () {
    super("org.mozilla.javascript.serialize.RHINO-SRC-ScriptableInputStream.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.serialize.RHINO-SRC-ScriptableInputStream.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

