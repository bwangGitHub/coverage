/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;

/**
 * A module script provider that uses a module source provider to load modules
 * and caches the loaded modules. It softly references the loaded modules'
 * Rhino {@link Script} objects, thus a module once loaded can become eligible
 * for garbage collection if it is otherwise unused under memory pressure.
 * Instances of this class are thread safe.
 * @version $Id: SoftCachingModuleScriptProvider.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public class SoftCachingModuleScriptProvider extends CachingModuleScriptProviderBase
{
  static {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[1]++;
  }

    private transient ReferenceQueue<Script> scriptRefQueue =
        new ReferenceQueue<Script>();
  {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[2]++;
  }

    private transient ConcurrentMap<String, ScriptReference> scripts =
        new ConcurrentHashMap<String, ScriptReference>(16, .75f,
                getConcurrencyLevel());
  {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[3]++;
  }

    /**
     * Creates a new module provider with the specified module source provider.
     * @param moduleSourceProvider provider for modules' source code
     */
    public SoftCachingModuleScriptProvider(
            ModuleSourceProvider moduleSourceProvider)
    {
        super(moduleSourceProvider);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[4]++;
    }

    @Override
    public ModuleScript getModuleScript(Context cx, String moduleId,
            URI uri, URI base, Scriptable paths)
            throws Exception
    {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[1]++;


        // Overridden to clear the reference queue before retrieving the
        // script.
        for(;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[1]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[2]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[3]++;
}
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[6]++;
            ScriptReference ref = (ScriptReference)scriptRefQueue.poll();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ref == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.branches[1]++;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[8]++;
                break;

            } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.branches[2]++;}
            scripts.remove(ref.getModuleId(), ref);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[9]++;
        }
        return super.getModuleScript(cx, moduleId, uri, base, paths);
    }

    @Override
    protected CachedModuleScript getLoadedModule(String moduleId) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[10]++;
        final ScriptReference scriptRef = scripts.get(moduleId);
        return scriptRef != null ? scriptRef.getCachedModuleScript() : null;
    }

    @Override
    protected void putLoadedModule(String moduleId, ModuleScript moduleScript,
            Object validator)
    {
        scripts.put(moduleId, new ScriptReference(moduleScript.getScript(),
                moduleId, moduleScript.getUri(), moduleScript.getBase(),
                validator, scriptRefQueue));
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[11]++;
    }

    private static class ScriptReference extends SoftReference<Script> {
        private final String moduleId;
        private final URI uri;
        private final URI base;
        private final Object validator;

        ScriptReference(Script script, String moduleId, URI uri, URI base,
                Object validator, ReferenceQueue<Script> refQueue) {
            super(script, refQueue);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[12]++;
            this.moduleId = moduleId;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[13]++;
            this.uri = uri;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[14]++;
            this.base = base;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[15]++;
            this.validator = validator;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[16]++;
        }

        CachedModuleScript getCachedModuleScript() {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[17]++;
            final Script script = get();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
            if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((script == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.branches[3]++;
                return null;

            } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.branches[4]++;}
            return new CachedModuleScript(new ModuleScript(script, uri, base),
                    validator);
        }

        String getModuleId() {
            return moduleId;
        }
    }

    private void readObject(ObjectInputStream in) throws IOException,
    ClassNotFoundException
    {
        scriptRefQueue = new ReferenceQueue<Script>();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[19]++;
        scripts = new ConcurrentHashMap<String, ScriptReference>();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[20]++;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[21]++;
        final Map<String, CachedModuleScript> serScripts = (Map)in.readObject();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[4]++;


        for(Map.Entry<String, CachedModuleScript> entry: serScripts.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[4]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[5]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[6]++;
}
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[23]++;
            final CachedModuleScript cachedModuleScript = entry.getValue();
            putLoadedModule(entry.getKey(), cachedModuleScript.getModule(),
                    cachedModuleScript.getValidator());
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[24]++;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[25]++;
        final Map<String, CachedModuleScript> serScripts =
            new HashMap<String, CachedModuleScript>();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[26]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[7]++;


        for(Map.Entry<String, ScriptReference> entry: scripts.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[7]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[8]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.loops[9]++;
}
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[27]++;
            final CachedModuleScript cachedModuleScript =
                entry.getValue().getCachedModuleScript();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
            if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cachedModuleScript != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.branches[5]++;
                serScripts.put(entry.getKey(), cachedModuleScript);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[29]++;

            } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.branches[6]++;}
        }
        out.writeObject(serScripts);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9.statements[30]++;
    }
}

class CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9 ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-SoftCachingModuleScriptProvider.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,0,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$m4l3gj6wz1z4fn58cgtiqe0949bvk3k2wu8jjt8yurm1e3nkcxyxb25a8crjysncnvy5ba9 () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-SoftCachingModuleScriptProvider.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-SoftCachingModuleScriptProvider.java");
      for (int i = 1; i <= 30; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
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
      for (int i = 1; i <= 3; i++) {
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
