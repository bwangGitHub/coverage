/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Bob Jervis
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link ErrorReporter} that collects warnings and errors and makes
 * them accessible via {@link #errors()} and {@link #warnings()}.
 *
 */
public class SimpleErrorReporter implements ErrorReporter {
  static {
    CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.ping();
  }

    private List<String> warnings = null;
  {
    CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[1]++;
  }
    private List<String> errors = null;
  {
    CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[2]++;
  }

    @Override
    public void warning(String message, String sourceName, int line,
                        int lineOffset) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((warnings == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[1]++;
            warnings = new ArrayList<String>();
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[4]++;

        } else {
  CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[2]++;}
        warnings.add(formatDetailedMessage(message, sourceName, line));
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[5]++;
    }

    @Override
    public void error(String message, String sourceName, int line,
                      int lineOffset) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((errors == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[3]++;
            errors = new ArrayList<String>();
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[7]++;

        } else {
  CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[4]++;}
        errors.add(formatDetailedMessage(message, sourceName, line));
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[8]++;
    }

    /**
     * Returns the list of errors, or {@code null} if there were none.
     */
    public List<String> errors() {
        return errors;
    }

    /**
     * Returns the list of warnings, or {@code null} if there were none.
     */
    public List<String> warnings() {
        return warnings;
    }

    private String formatDetailedMessage(
        String message, String sourceName, int lineNumber) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[9]++;
      String details = message;
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lineNumber <= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[5]++;
        return details;

      } else {
  CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[6]++;}
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[11]++;
      StringBuilder buf = new StringBuilder(details);
      buf.append(" (");
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[12]++;
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[7]++;
        buf.append(sourceName);
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[14]++;

      } else {
  CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[8]++;}
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lineNumber > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[9]++;
        buf.append('#');
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[16]++;
        buf.append(lineNumber);
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[17]++;

      } else {
  CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.branches[10]++;}
      buf.append(')');
CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d.statements[18]++;
      return buf.toString();
    }
}

class CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.SimpleErrorReporter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$j831vyg11stvlxlzfh1qj1mmdyo1yuz901f1d () {
    super("com.google.javascript.rhino.SimpleErrorReporter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.SimpleErrorReporter.java");
      for (int i = 1; i <= 18; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

