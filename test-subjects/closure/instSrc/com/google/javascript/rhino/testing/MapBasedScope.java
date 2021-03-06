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
 *   Nick Santos
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

package com.google.javascript.rhino.testing;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.SimpleSlot;
import com.google.javascript.rhino.jstype.StaticSlot;

import java.util.Map;

/**
 * A scope based on a simple hashmap.
 * @author nicksantos@google.com (Nick Santos)
 */
public class MapBasedScope extends AbstractStaticScope<JSType> {
  static {
    CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.ping();
  }

  private final Map<String, StaticSlot<JSType>> slots = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.statements[1]++;
  }

  public MapBasedScope(Map<String, ? extends JSType> namesToTypes) {
CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.loops[1]++;


    for (Map.Entry<String, ? extends JSType> entry : namesToTypes.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.loops[1]--;
  CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.loops[2]--;
  CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.loops[3]++;
}
      slots.put(
          entry.getKey(),
          new SimpleSlot(entry.getKey(), entry.getValue(), false));
CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1.statements[3]++;
    }
  }

  public static MapBasedScope emptyScope() {
    return new MapBasedScope(ImmutableMap.<String, JSType>of());
  }

  @Override
  public StaticSlot<JSType> getSlot(String name) {
    return slots.get(name);
  }
}

class CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1 ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$6fno9xqmeik3w1kawms7mxyu7dk1 () {
    super("com.google.javascript.rhino.testing.MapBasedScope.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.testing.MapBasedScope.java");
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
      for (int i = 1; i <= 1; i++) {
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

