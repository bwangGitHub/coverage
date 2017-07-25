/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class reflects a single Java constructor into the JavaScript
 * environment.  It satisfies a request for an overloaded constructor,
 * as introduced in LiveConnect 3.
 * All NativeJavaConstructors behave as JSRef `bound' methods, in that they
 * always construct the same NativeJavaClass regardless of any reparenting
 * that may occur.
 *
 * @see NativeJavaMethod
 * @see NativeJavaPackage
 * @see NativeJavaClass
 */

public class NativeJavaConstructor extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld.ping();
  }

    static final long serialVersionUID = -8149253217482668463L;
  static {
    CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld.statements[1]++;
  }

    MemberBox ctor;

    public NativeJavaConstructor(MemberBox ctor)
    {
        this.ctor = ctor;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld.statements[2]++;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return NativeJavaClass.constructSpecific(cx, scope, args, ctor);
    }

    @Override
    public String getFunctionName()
    {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld.statements[3]++;
        String sig = JavaMembers.liveConnectSignature(ctor.argTypes);
        return "<init>".concat(sig);
    }

    @Override
    public String toString()
    {
        return "[JavaConstructor " + ctor.getName() + "]";
    }
}

class CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$41o4hx3ypdgwbkwrtnsj9vpz41d3ebvoy656se73pyvj6k2etaqogmld () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaConstructor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaConstructor.java");
      for (int i = 1; i <= 3; i++) {
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

