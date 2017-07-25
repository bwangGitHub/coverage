/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.idswitch;

public class IdValuePair
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h.ping();
  }

    public final int idLength;
    public final String id;
    public final String value;

    private int lineNumber;

    public IdValuePair(String id, String value) {
        this.idLength = id.length();
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h.statements[1]++;
        this.id = id;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h.statements[2]++;
        this.value = value;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h.statements[3]++;
    }

    public int getLineNumber() { return lineNumber; }

    public void setLineNumber(int value) { lineNumber = value;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h.statements[4]++; }
}

class CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$qmzdwrwnkgu76c4iv8pfadi1hsyt7qsu8sgted0h () {
    super("org.mozilla.javascript.tools.idswitch.RHINO-TOO-IdValuePair.java");
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
    log.startNamedSection("org.mozilla.javascript.tools.idswitch.RHINO-TOO-IdValuePair.java");
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


