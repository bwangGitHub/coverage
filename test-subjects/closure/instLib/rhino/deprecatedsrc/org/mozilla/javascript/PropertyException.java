/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * @deprecated This exception is no longer thrown by Rhino runtime.
 */
public class PropertyException extends RuntimeException
{
  static {
    CodeCoverCoverageCounter$21tu3emdl0gbdmol1vqwvki6ixaclwexl7iucckkpwkibug1nl.ping();
  }

    static final long serialVersionUID = -8221564865490676219L;
  static {
    CodeCoverCoverageCounter$21tu3emdl0gbdmol1vqwvki6ixaclwexl7iucckkpwkibug1nl.statements[1]++;
  }

    public PropertyException(String detail) {
        super(detail);
CodeCoverCoverageCounter$21tu3emdl0gbdmol1vqwvki6ixaclwexl7iucckkpwkibug1nl.statements[2]++;
    }

}

class CodeCoverCoverageCounter$21tu3emdl0gbdmol1vqwvki6ixaclwexl7iucckkpwkibug1nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21tu3emdl0gbdmol1vqwvki6ixaclwexl7iucckkpwkibug1nl ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$21tu3emdl0gbdmol1vqwvki6ixaclwexl7iucckkpwkibug1nl () {
    super("org.mozilla.javascript.RHINO-DEP-PropertyException.java");
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
    log.startNamedSection("org.mozilla.javascript.RHINO-DEP-PropertyException.java");
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

