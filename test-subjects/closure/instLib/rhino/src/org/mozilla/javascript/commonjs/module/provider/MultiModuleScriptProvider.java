/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

/**
 * A multiplexer for module script providers.
 * @version $Id: MultiModuleScriptProvider.java,v 1.4 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public class MultiModuleScriptProvider implements ModuleScriptProvider
{
  static {
    CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.ping();
  }

    private final ModuleScriptProvider[] providers;

    /**
     * Creates a new multiplexing module script provider tht gathers the
     * specified providers
     * @param providers the providers to multiplex.
     */
    public MultiModuleScriptProvider(Iterable<? extends ModuleScriptProvider> providers) {
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[1]++;
        final List<ModuleScriptProvider> l = new LinkedList<ModuleScriptProvider>();
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[1]++;


        for (ModuleScriptProvider provider : providers) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[1]--;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[2]--;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[3]++;
}
            l.add(provider);
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[3]++;
        }
        this.providers = l.toArray(new ModuleScriptProvider[l.size()]);
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[4]++;
    }

    public ModuleScript getModuleScript(Context cx, String moduleId, URI uri,
                                        URI base, Scriptable paths) throws Exception {
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[5]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[4]++;


        for (ModuleScriptProvider provider : providers) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[4]--;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[5]--;
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.loops[6]++;
}
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[6]++;
            final ModuleScript script = provider.getModuleScript(cx, moduleId,
                    uri, base, paths);
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
            if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.branches[1]++;
                return script;

            } else {
  CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5.branches[2]++;}
        }
        return null;
    }
}

class CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5 ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-MultiModuleScriptProvider.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$7zf2y51hsaf0lijbpr5x35xtand84hkrda11aekgqaus8p10ht9bp7r6lpfof5 () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-MultiModuleScriptProvider.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-MultiModuleScriptProvider.java");
      for (int i = 1; i <= 7; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

