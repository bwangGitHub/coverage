/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * Implements the require() function as defined by
 * <a href="http://wiki.commonjs.org/wiki/Modules/1.1">Common JS modules</a>.
 * <h1>Thread safety</h1>
 * You will ordinarily create one instance of require() for every top-level
 * scope. This ordinarily means one instance per program execution, except if
 * you use shared top-level scopes and installing most objects into them.
 * Module loading is thread safe, so using a single require() in a shared
 * top-level scope is also safe.
 * <h1>Creation</h1>
 * If you need to create many otherwise identical require() functions for
 * different scopes, you might want to use {@link RequireBuilder} for
 * convenience.
 * <h1>Making it available</h1>
 * In order to make the require() function available to your JavaScript
 * program, you need to invoke either {@link #install(Scriptable)} or
 * {@link #requireMain(Context, String)}.
 * @version $Id: Require.java,v 1.4 2011/04/07 20:26:11 hannes%helma.at Exp $
 */
public class Require extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[1]++;
  }

    private final ModuleScriptProvider moduleScriptProvider;
    private final Scriptable nativeScope;
    private final Scriptable paths;
    private final boolean sandboxed;
    private final Script preExec;
    private final Script postExec;
    private String mainModuleId = null;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[2]++;
  }
    private Scriptable mainExports;

    // Modules that completed loading; visible to all threads
    private final Map<String, Scriptable> exportedModuleInterfaces =
        new ConcurrentHashMap<String, Scriptable>();
  {
    CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[3]++;
  }
    private final Object loadLock = new Object();
  {
    CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[4]++;
  }
    // Modules currently being loaded on the thread. Used to resolve circular
    // dependencies while loading.
    private static final ThreadLocal<Map<String, Scriptable>>
        loadingModuleInterfaces = new ThreadLocal<Map<String,Scriptable>>();
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[5]++;
  }

    /**
     * Creates a new instance of the require() function. Upon constructing it,
     * you will either want to install it in the global (or some other) scope
     * using {@link #install(Scriptable)}, or alternatively, you can load the
     * program's main module using {@link #requireMain(Context, String)} and
     * then act on the main module's exports.
     * @param cx the current context
     * @param nativeScope a scope that provides the standard native JavaScript
     * objects.
     * @param moduleScriptProvider a provider for module scripts
     * @param preExec an optional script that is executed in every module's
     * scope before its module script is run.
     * @param postExec an optional script that is executed in every module's
     * scope after its module script is run.
     * @param sandboxed if set to true, the require function will be sandboxed.
     * This means that it doesn't have the "paths" property, and also that the
     * modules it loads don't export the "module.uri" property.
     */
    public Require(Context cx, Scriptable nativeScope,
            ModuleScriptProvider moduleScriptProvider, Script preExec,
            Script postExec, boolean sandboxed) {
        this.moduleScriptProvider = moduleScriptProvider;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[6]++;
        this.nativeScope = nativeScope;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[7]++;
        this.sandboxed = sandboxed;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[8]++;
        this.preExec = preExec;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[9]++;
        this.postExec = postExec;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[10]++;
        setPrototype(ScriptableObject.getFunctionPrototype(nativeScope));
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[11]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sandboxed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[1]++;
            paths = cx.newArray(nativeScope, 0);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[13]++;
            defineReadOnlyProperty(this, "paths", paths);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[14]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[2]++;
            paths = null;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[15]++;
        }
    }

    /**
     * Calling this method establishes a module as being the main module of the
     * program to which this require() instance belongs. The module will be
     * loaded as if require()'d and its "module" property will be set as the
     * "main" property of this require() instance. You have to call this method
     * before the module has been loaded (that is, the call to this method must
     * be the first to require the module and thus trigger its loading). Note
     * that the main module will execute in its own scope and not in the global
     * scope. Since all other modules see the global scope, executing the main
     * module in the global scope would open it for tampering by other modules.
     * @param cx the current context
     * @param mainModuleId the ID of the main module
     * @return the "exports" property of the main module
     * @throws IllegalStateException if the main module is already loaded when
     * required, or if this require() instance already has a different main
     * module set.
     */
    public Scriptable requireMain(Context cx, String mainModuleId) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.mainModuleId != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[3]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.mainModuleId.equals(mainModuleId)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[5]++;
                throw new IllegalStateException("Main module already set to " +
                    this.mainModuleId);

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[6]++;}
            return mainExports;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[4]++;}

        ModuleScript moduleScript;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[18]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            // try to get the module script to see if it is on the module path
            moduleScript = moduleScriptProvider.getModuleScript(
                    cx, mainModuleId, null, null, paths);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[19]++;
        } catch (RuntimeException x) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[8]++;
            throw x;
        } catch (Exception x) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[9]++;
            throw new RuntimeException(x);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[7]++;
}
  }
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((moduleScript != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[10]++;
            mainExports = getExportedModuleInterface(cx, mainModuleId,
                    null, null, true);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[21]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[11]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[22]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((sandboxed) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[12]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[23]++;

            URI mainUri = null;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[24]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

            // try to resolve to an absolute URI or file path
            try {
CodeCoverTryBranchHelper_Try2 = true;
                mainUri = new URI(mainModuleId);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[25]++;
            } catch (URISyntaxException usx) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[15]++;
                // fall through
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[14]++;
}
  }
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;

            // if not an absolute uri resolve to a file path
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((mainUri == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((mainUri.isAbsolute()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[16]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[27]++;
                File file = new File(mainModuleId);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((file.isFile()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[18]++;
                    throw ScriptRuntime.throwError(cx, nativeScope,
                            "Module \"" + mainModuleId + "\" not found.");

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[19]++;}
                mainUri = file.toURI();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[29]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[17]++;}
            mainExports = getExportedModuleInterface(cx, mainUri.toString(),
                    mainUri, null, true);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[30]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[13]++;}
}

        this.mainModuleId = mainModuleId;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[31]++;
        return mainExports;
    }

    /**
     * Binds this instance of require() into the specified scope under the
     * property name "require".
     * @param scope the scope where the require() function is to be installed.
     */
    public void install(Scriptable scope) {
        ScriptableObject.putProperty(scope, "require", this);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[32]++;
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
            Object[] args)
    {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[20]++;
            throw ScriptRuntime.throwError(cx, scope,
                    "require() needs one argument");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[21]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[34]++;

        String id = (String)Context.jsToJava(args[0], String.class);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[35]++;
        URI uri = null;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[36]++;
        URI base = null;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((id.startsWith("./")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((id.startsWith("../")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[22]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((thisObj instanceof ModuleScope) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[24]++;
                throw ScriptRuntime.throwError(cx, scope,
                        "Can't resolve relative module ID \"" + id +
                                "\" when require() is used outside of a module");

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[25]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[39]++;

            ModuleScope moduleScope = (ModuleScope) thisObj;
            base = moduleScope.getBase();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[40]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[41]++;
            URI current = moduleScope.getUri();
            uri = current.resolve(id);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[42]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[43]++;
int CodeCoverConditionCoverageHelper_C11;

            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[26]++;
                // calling module is absolute, resolve to absolute URI
                // (but without file extension)
                id = uri.toString();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[44]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[27]++;
                // try to convert to a relative URI rooted on base
                id = base.relativize(current).resolve(id).toString();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[45]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((id.charAt(0) == '.') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[28]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[47]++;
int CodeCoverConditionCoverageHelper_C13;
                    // resulting URI is not contained in base,
                    // throw error or make absolute depending on sandbox flag.
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((sandboxed) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[30]++;
                        throw ScriptRuntime.throwError(cx, scope,
                            "Module \"" + id + "\" is not contained in sandbox.");

                    } else {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[31]++;
                        id = uri.toString();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[48]++;
                    }

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[29]++;}
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[23]++;}
        return getExportedModuleInterface(cx, id, uri, base, false);
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        throw ScriptRuntime.throwError(cx, scope,
                "require() can not be invoked as a constructor");
    }

    private Scriptable getExportedModuleInterface(Context cx, String id,
            URI uri, URI base, boolean isMain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[49]++;
        // Check if the requested module is already completely loaded
        Scriptable exports = exportedModuleInterfaces.get(id);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[50]++;
int CodeCoverConditionCoverageHelper_C14;
        if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((exports != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[32]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[51]++;
int CodeCoverConditionCoverageHelper_C15;
            if((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isMain) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[34]++;
                throw new IllegalStateException(
                        "Attempt to set main module after it was loaded");

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[35]++;}
            return exports;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[33]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[52]++;
        // Check if it is currently being loaded on the current thread
        // (supporting circular dependencies).
        Map<String, Scriptable> threadLoadingModules =
            loadingModuleInterfaces.get();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[53]++;
int CodeCoverConditionCoverageHelper_C16;
        if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((threadLoadingModules != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[36]++;
            exports = threadLoadingModules.get(id);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[54]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
            if((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((exports != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[38]++;
                return exports;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[39]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[37]++;}
        // The requested module is neither already loaded, nor is it being
        // loaded on the current thread. End of fast path. We must synchronize
        // now, as we have to guarantee that at most one thread can load
        // modules at any one time. Otherwise, two threads could end up
        // attempting to load two circularly dependent modules in opposite
        // order, which would lead to either unacceptable non-determinism or
        // deadlock, depending on whether we underprotected or overprotected it
        // with locks.
        synchronized(loadLock) {
            // Recheck if it is already loaded - other thread might've
            // completed loading it just as we entered the synchronized block.
            exports = exportedModuleInterfaces.get(id);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[56]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;
            if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((exports != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[40]++;
                return exports;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[41]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[58]++;
            // Nope, still not loaded; we're loading it then.
            final ModuleScript moduleScript = getModule(cx, id, uri, base);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[59]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((sandboxed) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((moduleScript.isSandboxed()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[42]++;
                throw ScriptRuntime.throwError(cx, nativeScope, "Module \""
                        + id + "\" is not contained in sandbox.");

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[43]++;}
            exports = cx.newObject(nativeScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[60]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[61]++;
            // Are we the outermost locked invocation on this thread?
            final boolean outermostLocked = threadLoadingModules == null;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[62]++;
int CodeCoverConditionCoverageHelper_C20;
            if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((outermostLocked) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[44]++;
                threadLoadingModules = new HashMap<String, Scriptable>();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[63]++;
                loadingModuleInterfaces.set(threadLoadingModules);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[64]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[45]++;}
            // Must make the module exports available immediately on the
            // current thread, to satisfy the CommonJS Modules/1.1 requirement
            // that "If there is a dependency cycle, the foreign module may not
            // have finished executing at the time it is required by one of its
            // transitive dependencies; in this case, the object returned by
            // "require" must contain at least the exports that the foreign
            // module has prepared before the call to require that led to the
            // current module's execution."
            threadLoadingModules.put(id, exports);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[65]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[66]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[67]++;
                // Support non-standard Node.js feature to allow modules to
                // replace the exports object by setting module.exports.
                Scriptable newExports = executeModuleScript(cx, id, exports,
                        moduleScript, isMain);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[68]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((exports != newExports) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[47]++;
                    threadLoadingModules.put(id, newExports);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[69]++;
                    exports = newExports;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[70]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[48]++;}
            }
            catch(RuntimeException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[49]++;
                // Throw loaded module away if there was an exception
                threadLoadingModules.remove(id);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[71]++;
                throw e;
            }
            finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[46]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[72]++;
int CodeCoverConditionCoverageHelper_C22;
                if((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((outermostLocked) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[50]++;
                    // Make loaded modules visible to other threads only after
                    // the topmost triggering load has completed. This strategy
                    // (compared to the one where we'd make each module
                    // globally available as soon as it loads) prevents other
                    // threads from observing a partially loaded circular
                    // dependency of a module that completed loading.
                    exportedModuleInterfaces.putAll(threadLoadingModules);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[73]++;
                    loadingModuleInterfaces.set(null);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[74]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[51]++;}
            }
        }
        return exports;
    }

    private Scriptable executeModuleScript(Context cx, String id,
            Scriptable exports, ModuleScript moduleScript, boolean isMain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[75]++;
        final ScriptableObject moduleObject = (ScriptableObject)cx.newObject(
                nativeScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[76]++;
        URI uri = moduleScript.getUri();
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[77]++;
        URI base = moduleScript.getBase();
        defineReadOnlyProperty(moduleObject, "id", id);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[78]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;
        if((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((sandboxed) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[52]++;
            defineReadOnlyProperty(moduleObject, "uri", uri.toString());
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[80]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[53]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[81]++;
        final Scriptable executionScope = new ModuleScope(nativeScope, uri, base);
        // Set this so it can access the global JS environment objects.
        // This means we're currently using the "MGN" approach (ModuleScript
        // with Global Natives) as specified here:
        // <http://wiki.commonjs.org/wiki/Modules/ProposalForNativeExtension>
        executionScope.put("exports", executionScope, exports);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[82]++;
        executionScope.put("module", executionScope, moduleObject);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[83]++;
        moduleObject.put("exports", moduleObject, exports);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[84]++;
        install(executionScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[85]++;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[86]++;
int CodeCoverConditionCoverageHelper_C24;
        if((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isMain) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[54]++;
            defineReadOnlyProperty(this, "main", moduleObject);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[87]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[55]++;}
        executeOptionalScript(preExec, cx, executionScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[88]++;
        moduleScript.getScript().exec(cx, executionScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[89]++;
        executeOptionalScript(postExec, cx, executionScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[90]++;
        return ScriptRuntime.toObject(nativeScope,
                ScriptableObject.getProperty(moduleObject, "exports"));
    }

    private static void executeOptionalScript(Script script, Context cx,
            Scriptable executionScope)
    {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[91]++;
int CodeCoverConditionCoverageHelper_C25;
        if((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[56]++;
            script.exec(cx, executionScope);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[92]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[57]++;}
    }

    private static void defineReadOnlyProperty(ScriptableObject obj,
            String name, Object value) {
        ScriptableObject.putProperty(obj, name, value);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[93]++;
        obj.setAttributes(name, ScriptableObject.READONLY |
                ScriptableObject.PERMANENT);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[94]++;
    }

    private ModuleScript getModule(Context cx, String id, URI uri, URI base) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[95]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[96]++;
            final ModuleScript moduleScript =
                    moduleScriptProvider.getModuleScript(cx, id, uri, base, paths);
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.statements[97]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((moduleScript == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[59]++;
                throw ScriptRuntime.throwError(cx, nativeScope, "Module \""
                        + id + "\" not found.");

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[60]++;}
            return moduleScript;
        }
        catch(RuntimeException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[61]++;
            throw e;
        }
        catch(Exception e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[62]++;
            throw Context.throwAsScriptRuntimeEx(e);
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld.branches[58]++;
}
  }
    }

    @Override
    public String getFunctionName() {
        return "require";
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public int getLength() {
        return 1;
    }
}

class CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld ());
  }
    public static long[] statements = new long[98];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.RHINO-SRC-Require.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
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

  public CodeCoverCoverageCounter$di175yxae5e37vt0kq1quyz47hd4kh92ld () {
    super("org.mozilla.javascript.commonjs.module.RHINO-SRC-Require.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 97; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.RHINO-SRC-Require.java");
      for (int i = 1; i <= 97; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
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
