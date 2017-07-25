/*
 * Copyright 1998 Sun Microsystems, Inc.  All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.mozilla.javascript.tools.debugger.treetable;

import javax.swing.*;
import javax.swing.event.*;
import java.util.EventObject;

public class AbstractCellEditor implements CellEditor {
  static {
    CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.ping();
  }


    protected EventListenerList listenerList = new EventListenerList();
  {
    CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[1]++;
  }

    public Object getCellEditorValue() { return null; }
    public boolean isCellEditable(EventObject e) { return true; }
    public boolean shouldSelectCell(EventObject anEvent) { return false; }
    public boolean stopCellEditing() { return true; }
    public void cancelCellEditing() {}

    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[2]++;
    }

    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[3]++;
    }

    /*
     * Notify all listeners that have registered interest for
     * notification on this event type.
     * @see EventListenerList
     */
    protected void fireEditingStopped() {
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[4]++;
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i>=0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i-=2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[1]--;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[2]--;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[3]++;
}
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((listeners[i]==CellEditorListener.class) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.branches[1]++;
                ((CellEditorListener)listeners[i+1]).editingStopped(new ChangeEvent(this));
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[7]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.branches[2]++;}
        }
    }

    /*
     * Notify all listeners that have registered interest for
     * notification on this event type.
     * @see EventListenerList
     */
    protected void fireEditingCanceled() {
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[8]++;
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[9]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i>=0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i-=2) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[4]--;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[5]--;
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.loops[6]++;
}
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((listeners[i]==CellEditorListener.class) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.branches[3]++;
                ((CellEditorListener)listeners[i+1]).editingCanceled(new ChangeEvent(this));
CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.statements[11]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5.branches[4]++;}
        }
    }
}

class CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-AbstractCellEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$el0607z4lg8y7eprar0q1m3c1wuenk16g5w0etm1sevma31nkv5 () {
    super("org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-AbstractCellEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-AbstractCellEditor.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

