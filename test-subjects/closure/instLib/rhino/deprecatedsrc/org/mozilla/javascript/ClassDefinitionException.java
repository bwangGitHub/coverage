
/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
// API class

package org.mozilla.javascript;

/**
 * @deprecated The exception is no longer thrown by Rhino runtime as
 * {@link EvaluatorException} is used instead.
 */
public class ClassDefinitionException extends RuntimeException
{
  static {
    CodeCoverCoverageCounter$14f23oc0tae2ho93z6iwzd9wbdqnuqma9271wzwhvikbauqeoagg0e39rz5nl.ping();
  }

    static final long serialVersionUID = -5637830967241712746L;
  static {
    CodeCoverCoverageCounter$14f23oc0tae2ho93z6iwzd9wbdqnuqma9271wzwhvikbauqeoagg0e39rz5nl.statements[1]++;
  }

    public ClassDefinitionException(String detail) {
        super(detail);
CodeCoverCoverageCounter$14f23oc0tae2ho93z6iwzd9wbdqnuqma9271wzwhvikbauqeoagg0e39rz5nl.statements[2]++;
    }
}

class CodeCoverCoverageCounter$14f23oc0tae2ho93z6iwzd9wbdqnuqma9271wzwhvikbauqeoagg0e39rz5nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14f23oc0tae2ho93z6iwzd9wbdqnuqma9271wzwhvikbauqeoagg0e39rz5nl ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$14f23oc0tae2ho93z6iwzd9wbdqnuqma9271wzwhvikbauqeoagg0e39rz5nl () {
    super("org.mozilla.javascript.RHINO-DEP-ClassDefinitionException.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-DEP-ClassDefinitionException.java");
      for (int i = 1; i <= 2; i++) {
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

