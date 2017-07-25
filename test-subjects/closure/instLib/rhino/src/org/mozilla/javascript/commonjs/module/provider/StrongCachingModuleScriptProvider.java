/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mozilla.javascript.commonjs.module.ModuleScript;

/**
 * A module script provider that uses a module source provider to load modules
 * and caches the loaded modules. It strongly references the loaded modules,
 * thus a module once loaded will not be eligible for garbage collection before
 * the module provider itself becomes eligible.
 * @version $Id: StrongCachingModuleScriptProvider.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public class StrongCachingModuleScriptProvider extends CachingModuleScriptProviderBase
{
  static {
    CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl.statements[1]++;
  }

    private final Map<String, CachedModuleScript> modules =
        new ConcurrentHashMap<String, CachedModuleScript>(16, .75f, getConcurrencyLevel());
  {
    CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl.statements[2]++;
  }

    /**
     * Creates a new module provider with the specified module source provider.
     * @param moduleSourceProvider provider for modules' source code
     */
    public StrongCachingModuleScriptProvider(
            ModuleSourceProvider moduleSourceProvider)
    {
        super(moduleSourceProvider);
CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl.statements[3]++;
    }

    @Override
    protected CachedModuleScript getLoadedModule(String moduleId) {
        return modules.get(moduleId);
    }

    @Override
    protected void putLoadedModule(String moduleId, ModuleScript moduleScript,
            Object validator) {
        modules.put(moduleId, new CachedModuleScript(moduleScript, validator));
CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl.statements[4]++;
    }
}

class CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$v2xok9dm62vgjst2sbrf6daawlagf4ffl83a270sqtj0o8rzxvlyeao1yfr5wqvtpg2wpz5izl () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-StrongCachingModuleScriptProvider.java");
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-StrongCachingModuleScriptProvider.java");
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
