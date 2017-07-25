/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module;

import java.io.Serializable;
import java.net.URI;

import org.mozilla.javascript.Script;

/**
 * Represents a compiled CommonJS module script. The {@link Require} functions
 * use them and obtain them through a {@link ModuleScriptProvider}. Instances
 * are immutable.
 * @version $Id: ModuleScript.java,v 1.3 2011/04/07 20:26:11 hannes%helma.at Exp $
 */
public class ModuleScript implements Serializable
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp.statements[1]++;
  }

    private final Script script;
    private final URI uri;
    private final URI base;

    /**
     * Creates a new CommonJS module.
     * @param script the script representing the code of the module.
     * @param uri the URI of the module.
     * @param base the base URI, or null.
     */
    public ModuleScript(Script script, URI uri, URI base) {
        this.script = script;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp.statements[2]++;
        this.uri = uri;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp.statements[3]++;
        this.base = base;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp.statements[4]++;
    }

    /**
     * Returns the script object representing the code of the module.
     * @return the script object representing the code of the module.
     */
    public Script getScript(){
        return script;
    }

    /**
     * Returns the URI of the module.
     * @return the URI of the module.
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Returns the base URI from which this module source was loaded, or null
     * if it was loaded from an absolute URI.
     * @return the base URI, or null.
     */
    public URI getBase() {
        return base;
    }

    /**
     * Returns true if this script has a base URI and has a source URI that
     * is contained within that base URI.
     * @return true if this script is contained within its sandbox base URI.
     */
    public boolean isSandboxed() {
        return base != null
                && uri != null
                && !base.relativize(uri).isAbsolute();
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaogmdsvc5hrzebnmp () {
    super("org.mozilla.javascript.commonjs.module.RHINO-SRC-ModuleScript.java");
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.RHINO-SRC-ModuleScript.java");
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

