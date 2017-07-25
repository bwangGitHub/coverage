/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.net.URI;

/**
 * Should be implemented by Rhino embeddings to allow the require() function to
 * obtain {@link ModuleScript} objects. We provide two default implementations,
 * but you can of course roll your own if they don't suit your needs.
 * @version $Id: ModuleScriptProvider.java,v 1.4 2011/04/07 20:26:11 hannes%helma.at Exp $
 */
public interface ModuleScriptProvider
{
    /**
     * Returns a module script. It should attempt to load the module script if
     * it is not already available to it, or return an already loaded module
     * script instance if it is available to it.
     * @param cx current context. Can be used to compile module scripts.
     * @param moduleId the ID of the module. An implementation must only accept
     * an absolute ID, starting with a term.
     * @param moduleUri the URI of the module. If this is not null, resolution
     * of <code>moduleId</code> is bypassed and the script is directly loaded
     * from <code>moduleUri</code>
     * @param baseUri the module path base URI from which <code>moduleUri</code>
     * was derived.
     * @param paths the value of the require() function's "paths" attribute. If
     * the require() function is sandboxed, it will be null, otherwise it will
     * be a JavaScript Array object. It is up to the provider implementation
     * whether and how it wants to honor the contents of the array.
     * @return a module script representing the compiled code of the module.
     * Null should be returned if the script could not found.
     * @throws Exception if there was an unrecoverable problem obtaining the
     * script
     * @throws IllegalArgumentException if the module ID is syntactically not a
     * valid absolute module identifier.
     */
    public ModuleScript getModuleScript(Context cx, String moduleId,
            URI moduleUri, URI baseUri, Scriptable paths)
            throws Exception;

}

class CodeCoverCoverageCounter$khgdrpvbfyfsmmlwtcwrqj71egw3qstnzo1i07my4jodmu147hqe01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$khgdrpvbfyfsmmlwtcwrqj71egw3qstnzo1i07my4jodmu147hqe01 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$khgdrpvbfyfsmmlwtcwrqj71egw3qstnzo1i07my4jodmu147hqe01 () {
    super("org.mozilla.javascript.commonjs.module.RHINO-SRC-ModuleScriptProvider.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.RHINO-SRC-ModuleScriptProvider.java");
      for (int i = 1; i <= -1; i++) {
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

