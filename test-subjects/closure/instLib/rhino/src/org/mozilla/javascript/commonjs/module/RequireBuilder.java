/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module;

import java.io.Serializable;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

/**
 * A builder for {@link Require} instances. Useful when you're creating many
 * instances of {@link Require} that are identical except for their top-level
 * scope and current {@link Context}. Also useful if you prefer configuring it
 * using named setters instead of passing many parameters in a constructor.
 * Every setter returns "this", so you can easily chain their invocations for
 * additional convenience.
 * @version $Id: RequireBuilder.java,v 1.4 2011/04/07 20:26:11 hannes%helma.at Exp $
 */
public class RequireBuilder implements Serializable
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.statements[1]++;
  }

    private boolean sandboxed = true;
  {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.statements[2]++;
  }
    private ModuleScriptProvider moduleScriptProvider;
    private Script preExec;
    private Script postExec;

    /**
     * Sets the {@link ModuleScriptProvider} for the {@link Require} instances
     * that this builder builds.
     * @param moduleScriptProvider the module script provider for the
     * {@link Require} instances that this builder builds.
     * @return this, so you can chain ("fluidize") setter invocations
     */
    public RequireBuilder setModuleScriptProvider(
            ModuleScriptProvider moduleScriptProvider)
    {
        this.moduleScriptProvider = moduleScriptProvider;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.statements[3]++;
        return this;
    }

    /**
     * Sets the script that should execute in every module's scope after the
     * module's own script has executed.
     * @param postExec the post-exec script.
     * @return this, so you can chain ("fluidize") setter invocations
     */
    public RequireBuilder setPostExec(Script postExec) {
        this.postExec = postExec;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.statements[4]++;
        return this;
    }

    /**
     * Sets the script that should execute in every module's scope before the
     * module's own script has executed.
     * @param preExec the pre-exec script.
     * @return this, so you can chain ("fluidize") setter invocations
     */
    public RequireBuilder setPreExec(Script preExec) {
        this.preExec = preExec;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.statements[5]++;
        return this;
    }

    /**
     * Sets whether the created require() instances will be sandboxed.
     * See {@link Require#Require(Context, Scriptable, ModuleScriptProvider,
     * Script, Script, boolean)} for explanation.
     * @param sandboxed true if the created require() instances will be
     * sandboxed.
     * @return this, so you can chain ("fluidize") setter invocations
     */
    public RequireBuilder setSandboxed(boolean sandboxed) {
        this.sandboxed = sandboxed;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd.statements[6]++;
        return this;
    }

    /**
     * Creates a new require() function. You are still responsible for invoking
     * either {@link Require#install(Scriptable)} or
     * {@link Require#requireMain(Context, String)} to effectively make it
     * available to its JavaScript program.
     * @param cx the current context
     * @param globalScope the global scope containing the JS standard natives.
     * @return a new Require instance.
     */
    public Require createRequire(Context cx, Scriptable globalScope) {
        return new Require(cx, globalScope, moduleScriptProvider, preExec,
                postExec, sandboxed);
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevwnmgimn4qzn80hhrr4f2hscoatkdd () {
    super("org.mozilla.javascript.commonjs.module.RHINO-SRC-RequireBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.RHINO-SRC-RequireBuilder.java");
      for (int i = 1; i <= 6; i++) {
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
