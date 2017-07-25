/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.regexp;

/**
 * A utility class for lazily instantiated substrings.
 */
public class SubString {
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.ping();
  }


    public SubString()
    {
    }

    public SubString(String str)
    {
        this.str = str;
CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[1]++;
        index = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[2]++;
        length = str.length();
CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[3]++;
    }

    public SubString(String source, int start, int len)
    {
        str = source;
CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[4]++;
        index = start;
CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[5]++;
        length = len;
CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[6]++;
    }

    @Override
    public String toString() {
        return str == null
               ? ""
               : str.substring(index, index + length);
    }

    public static final SubString emptySubString = new SubString();
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5.statements[7]++;
  }

    String str;
    int    index;
    int    length;
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5 ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzh3lyyrpb0dfhr4c5ihb4v5 () {
    super("org.mozilla.javascript.regexp.RHINO-SRC-SubString.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("org.mozilla.javascript.regexp.RHINO-SRC-SubString.java");
      for (int i = 1; i <= 7; i++) {
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


