/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.Reader;
import java.io.Serializable;
import java.net.URI;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

/**
 * Abstract base class that implements caching of loaded module scripts. It
 * uses a {@link ModuleSourceProvider} to obtain the source text of the
 * scripts. It supports a cache revalidation mechanism based on validator
 * objects returned from the {@link ModuleSourceProvider}. Instances of this
 * class and its subclasses are thread safe (and written to perform decently
 * under concurrent access).
 * @version $Id: CachingModuleScriptProviderBase.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public abstract class CachingModuleScriptProviderBase
implements ModuleScriptProvider, Serializable
{
  static {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[1]++;
  }

    private static final int loadConcurrencyLevel =
        Runtime.getRuntime().availableProcessors() * 8;
  static {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[2]++;
  }
    private static final int loadLockShift;
    private static final int loadLockMask;
    private static final int loadLockCount;
    static {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[3]++;
        int sshift = 0;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[4]++;
        int ssize = 1;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((ssize < loadConcurrencyLevel) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[1]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[2]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[3]++;
}
            ++sshift;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[6]++;
            ssize <<= 1;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[7]++;
        }
        loadLockShift = 32 - sshift;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[8]++;
        loadLockMask = ssize - 1;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[9]++;
        loadLockCount = ssize;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[10]++;
    }
    private final Object[] loadLocks = new Object[loadLockCount];
  {
    CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[11]++;
  } {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < loadLocks.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[4]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[5]--;
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.loops[6]++;
}
            loadLocks[i] = new Object();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[13]++;
        }
    }

    private final ModuleSourceProvider moduleSourceProvider;

    /**
     * Creates a new module script provider with the specified source.
     * @param moduleSourceProvider provider for modules' source code
     */
    protected CachingModuleScriptProviderBase(
            ModuleSourceProvider moduleSourceProvider) {
        this.moduleSourceProvider = moduleSourceProvider;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[14]++;
    }

    public ModuleScript getModuleScript(Context cx, String moduleId,
            URI moduleUri, URI baseUri, Scriptable paths) throws Exception
    {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[15]++;
        final CachedModuleScript cachedModule1 = getLoadedModule(moduleId);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[16]++;
        final Object validator1 = getValidator(cachedModule1);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[17]++;
        final ModuleSource moduleSource = (moduleUri == null)
                ? moduleSourceProvider.loadSource(moduleId, paths, validator1)
                : moduleSourceProvider.loadSource(moduleUri, baseUri, validator1);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((moduleSource == ModuleSourceProvider.NOT_MODIFIED) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[1]++;
            return cachedModule1.getModule();

        } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[2]++;}
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((moduleSource == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[3]++;
            return null;

        } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[4]++;}
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[20]++;
        final Reader reader = moduleSource.getReader();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[21]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[22]++;
            final int idHash = moduleId.hashCode();
            synchronized(loadLocks[(idHash >>> loadLockShift) & loadLockMask]) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[23]++;
                final CachedModuleScript cachedModule2 = getLoadedModule(moduleId);
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
                if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cachedModule2 != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[6]++;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
                    if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((equal(validator1, getValidator(cachedModule2))) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[8]++;
                        return cachedModule2.getModule();

                    } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[9]++;}

                } else {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[7]++;}
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[26]++;
                final URI sourceUri = moduleSource.getUri();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[27]++;
                final ModuleScript moduleScript = new ModuleScript(
                        cx.compileReader(reader, sourceUri.toString(), 1,
                                moduleSource.getSecurityDomain()),
                        sourceUri, moduleSource.getBase());
                putLoadedModule(moduleId, moduleScript,
                        moduleSource.getValidator());
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[28]++;
                return moduleScript;
            }
        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.branches[5]++;
}
            reader.close();
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[29]++;
        }
    }

    /**
     * Store a loaded module script for later retrieval using
     * {@link #getLoadedModule(String)}.
     * @param moduleId the ID of the module
     * @param moduleScript the module script
     * @param validator the validator for the module's source text entity
     */
    protected abstract void putLoadedModule(String moduleId,
            ModuleScript moduleScript, Object validator);

    /**
     * Retrieves an already loaded moduleScript stored using
     * {@link #putLoadedModule(String, ModuleScript, Object)}.
     * @param moduleId the ID of the module
     * @return a cached module script, or null if the module is not loaded.
     */
    protected abstract CachedModuleScript getLoadedModule(String moduleId);

    /**
     * Instances of this class represent a loaded and cached module script.
     * @version $Id: CachingModuleScriptProviderBase.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
     */
    public static class CachedModuleScript {
        private final ModuleScript moduleScript;
        private final Object validator;

        /**
         * Creates a new cached module script.
         * @param moduleScript the module script itself
         * @param validator a validator for the moduleScript's source text
         * entity.
         */
        public CachedModuleScript(ModuleScript moduleScript, Object validator) {
            this.moduleScript = moduleScript;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[30]++;
            this.validator = validator;
CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9.statements[31]++;
        }

        /**
         * Returns the module script.
         * @return the module script.
         */
        ModuleScript getModule() {
            return moduleScript;
        }

        /**
         * Returns the validator for the module script's source text entity.
         * @return the validator for the module script's source text entity.
         */
        Object getValidator() {
            return validator;
        }
    }

    private static Object getValidator(CachedModuleScript cachedModule) {
        return cachedModule == null ? null : cachedModule.getValidator();
    }

    private static boolean equal(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    /**
     * Returns the internal concurrency level utilized by caches in this JVM.
     * @return the internal concurrency level utilized by caches in this JVM.
     */
    protected static int getConcurrencyLevel() {
        return loadLockCount;
    }
}

class CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9 ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[10];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-CachingModuleScriptProviderBase.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$m4l3gj6wz1z4fn57k1lkktg7hxpflcwbr4is3gzllpatdojlc166mvm1slcqqwykjf56cm9 () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-CachingModuleScriptProviderBase.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 9; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-CachingModuleScriptProviderBase.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 9; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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
