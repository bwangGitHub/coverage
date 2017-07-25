/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * This class provides support for implementing Java-style synchronized
 * methods in Javascript.
 *
 * Synchronized functions are created from ordinary Javascript
 * functions by the <code>Synchronizer</code> constructor, e.g.
 * <code>new Packages.org.mozilla.javascript.Synchronizer(fun)</code>.
 * The resulting object is a function that establishes an exclusive
 * lock on the <code>this</code> object of its invocation.
 *
 * The Rhino shell provides a short-cut for the creation of
 * synchronized methods: <code>sync(fun)</code> has the same effect as
 * calling the above constructor.
 *
 * @see org.mozilla.javascript.Delegator
 */

public class Synchronizer extends Delegator {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d.ping();
  }


    private Object syncObject;

    /**
     * Create a new synchronized function from an existing one.
     *
     * @param obj the existing function
     */
    public Synchronizer(Scriptable obj) {
        super(obj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d.statements[1]++;
    }

    /**
     * Create a new synchronized function from an existing one using
     * an explicit object as synchronization object.
     *
     * @param obj the existing function
     * @param syncObject the object to synchronized on
     */
    public Synchronizer(Scriptable obj, Object syncObject) {
        super(obj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d.statements[2]++;
        this.syncObject = syncObject;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d.statements[3]++;
    }

    /**
     * @see org.mozilla.javascript.Function#call
     */
    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d.statements[4]++;
        Object sync = syncObject != null ? syncObject : thisObj;
        synchronized(sync instanceof Wrapper ? ((Wrapper)sync).unwrap() : sync) {
            return ((Function)obj).call(cx,scope,thisObj,args);
        }
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmno0lmlx0c0bvtx2rl0cdqe4h9d () {
    super("org.mozilla.javascript.RHINO-SRC-Synchronizer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Synchronizer.java");
      for (int i = 1; i <= 4; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

