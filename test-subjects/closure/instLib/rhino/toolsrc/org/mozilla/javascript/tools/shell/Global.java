/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.mozilla.javascript.*;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.mozilla.javascript.commonjs.module.provider.SoftCachingModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider;
import org.mozilla.javascript.tools.ToolErrorReporter;
import org.mozilla.javascript.serialize.*;

/**
 * This class provides for sharing functions across multiple threads.
 * This is of particular interest to server applications.
 *
 */
public class Global extends ImporterTopLevel
{
  static {
    CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.ping();
  }

    static final long serialVersionUID = 4029130780977538005L;
  static {
    CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[1]++;
  }

    NativeArray history;
    boolean attemptedJLineLoad;
    private ShellConsole console;
    private InputStream inStream;
    private PrintStream outStream;
    private PrintStream errStream;
    private boolean sealedStdLib = false;
  {
    CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[2]++;
  }
    boolean initialized;
    private QuitAction quitAction;
    private String[] prompts = { "js> ", "  > " };
  {
    CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[3]++;
  }
    private HashMap<String,String> doctestCanonicalizations;

    public Global()
    {
    }

    public Global(Context cx)
    {
        init(cx);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[4]++;
    }

    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Set the action to call from quit().
     */
    public void initQuitAction(QuitAction quitAction)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((quitAction == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[1]++;
            throw new IllegalArgumentException("quitAction is null");
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[2]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.quitAction != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[3]++;
            throw new IllegalArgumentException("The method is once-call.");
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[4]++;}

        this.quitAction = quitAction;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[7]++;
    }

    public void init(ContextFactory factory)
    {
        factory.call(new ContextAction() {
                public Object run(Context cx)
                {
                    init(cx);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[9]++;
                    return null;
                }
            });
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[8]++;
    }

    public void init(Context cx)
    {
        // Define some global functions particular to the shell. Note
        // that these functions are not part of ECMA.
        initStandardObjects(cx, sealedStdLib);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[10]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[11]++;
        String[] names = {
            "defineClass",
            "deserialize",
            "doctest",
            "gc",
            "help",
            "load",
            "loadClass",
            "print",
            "quit",
            "readFile",
            "readUrl",
            "runCommand",
            "seal",
            "serialize",
            "spawn",
            "sync",
            "toint32",
            "version",
        };
        defineFunctionProperties(names, Global.class,
                                 ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[12]++;

        // Set up "environment" in the global scope to provide access to the
        // System environment variables.
        Environment.defineClass(this);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[13]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[14]++;
        Environment environment = new Environment(this);
        defineProperty("environment", environment,
                       ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[15]++;

        history = (NativeArray) cx.newArray(this, 0);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[16]++;
        defineProperty("history", history, ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[17]++;

        initialized = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[18]++;
    }

    public Require installRequire(Context cx, List<String> modulePath,
                                  boolean sandboxed) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[19]++;
        RequireBuilder rb = new RequireBuilder();
        rb.setSandboxed(sandboxed);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[20]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[21]++;
        List<URI> uris = new ArrayList<URI>();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((modulePath != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[5]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[1]++;


            for (String path : modulePath) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[1]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[2]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[3]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[24]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[25]++;
                    URI uri = new URI(path);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
                    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((uri.isAbsolute()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[8]++;
                        // call resolve("") to canonify the path
                        uri = new File(path).toURI().resolve("");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[27]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[9]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((uri.toString().endsWith("/")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[10]++;
                        // make sure URI always terminates with slash to
                        // avoid loading from unintended locations
                        uri = new URI(uri + "/");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[29]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[11]++;}
                    uris.add(uri);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[30]++;
                } catch (URISyntaxException usx) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[12]++;
                    throw new RuntimeException(usx);
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[7]++;
}
  }
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[6]++;}
        rb.setModuleScriptProvider(
                new SoftCachingModuleScriptProvider(
                        new UrlModuleSourceProvider(uris, null)));
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[31]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[32]++;
        Require require = rb.createRequire(cx, this);
        require.install(this);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[33]++;
        return require;
    }

    /**
     * Print a help message.
     *
     * This method is defined as a JavaScript function.
     */
    public static void help(Context cx, Scriptable thisObj,
                            Object[] args, Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[34]++;
        PrintStream out = getInstance(funObj).getOut();
        out.println(ToolErrorReporter.getMessage("msg.help"));
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[35]++;
    }

    public static void gc(Context cx, Scriptable thisObj,
            Object[] args, Function funObj)
    {
        System.gc();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[36]++;
    }


    /**
     * Print the string values of its arguments.
     *
     * This method is defined as a JavaScript function.
     * Note that its arguments are of the "varargs" form, which
     * allows it to handle an arbitrary number of arguments
     * supplied to the JavaScript function.
     *
     */
    public static Object print(Context cx, Scriptable thisObj,
                               Object[] args, Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[37]++;
        PrintStream out = getInstance(funObj).getOut();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[38]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[4]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[5]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[6]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[13]++;
                out.print(" ");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[40]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[14]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[41]++;

            // Convert the arbitrary JavaScript value into a string form.
            String s = Context.toString(args[i]);

            out.print(s);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[42]++;
        }
        out.println();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[43]++;
        return Context.getUndefinedValue();
    }

    /**
     * Call embedding-specific quit action passing its argument as
     * int32 exit code.
     *
     * This method is defined as a JavaScript function.
     */
    public static void quit(Context cx, Scriptable thisObj,
                            Object[] args, Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[44]++;
        Global global = getInstance(funObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((global.quitAction != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[15]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[46]++;
            int exitCode = (args.length == 0 ? 0
                            : ScriptRuntime.toInt32(args[0]));
            global.quitAction.quit(cx, exitCode);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[47]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[16]++;}
    }

    /**
     * Get and set the language version.
     *
     * This method is defined as a JavaScript function.
     */
    public static double version(Context cx, Scriptable thisObj,
                                 Object[] args, Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[48]++;
        double result = cx.getLanguageVersion();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[17]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[50]++;
            double d = Context.toNumber(args[0]);
            cx.setLanguageVersion((int) d);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[51]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[18]++;}
        return result;
    }

    /**
     * Load and execute a set of JavaScript source files.
     *
     * This method is defined as a JavaScript function.
     *
     */
    public static void load(Context cx, Scriptable thisObj,
                            Object[] args, Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[52]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[7]++;


        for (Object arg : args) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[7]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[8]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[9]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[53]++;
            String file = Context.toString(arg);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[54]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                Main.processFile(cx, thisObj, file);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[55]++;
            } catch (IOException ioex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[20]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[56]++;
                String msg = ToolErrorReporter.getMessage(
                        "msg.couldnt.read.source", file, ioex.getMessage());
                throw Context.reportRuntimeError(msg);
            } catch (VirtualMachineError ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[21]++;
                // Treat StackOverflow and OutOfMemory as runtime errors
                ex.printStackTrace();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[57]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[58]++;
                String msg = ToolErrorReporter.getMessage(
                        "msg.uncaughtJSException", ex.toString());
                throw Context.reportRuntimeError(msg);
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[19]++;
}
  }
        }
    }

    /**
     * Load a Java class that defines a JavaScript object using the
     * conventions outlined in ScriptableObject.defineClass.
     * <p>
     * This method is defined as a JavaScript function.
     * @exception IllegalAccessException if access is not available
     *            to a reflected class member
     * @exception InstantiationException if unable to instantiate
     *            the named class
     * @exception InvocationTargetException if an exception is thrown
     *            during execution of methods of the named class
     * @see org.mozilla.javascript.ScriptableObject#defineClass(Scriptable,Class)
     */
    @SuppressWarnings({"unchecked"})
    public static void defineClass(Context cx, Scriptable thisObj,
                                   Object[] args, Function funObj)
        throws IllegalAccessException, InstantiationException,
               InvocationTargetException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[59]++;
        Class<?> clazz = getClass(args);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[60]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((Scriptable.class.isAssignableFrom(clazz)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[22]++;
            throw reportRuntimeError("msg.must.implement.Scriptable");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[23]++;}
        ScriptableObject.defineClass(thisObj, (Class<? extends Scriptable>)clazz);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[61]++;
    }

    /**
     * Load and execute a script compiled to a class file.
     * <p>
     * This method is defined as a JavaScript function.
     * When called as a JavaScript function, a single argument is
     * expected. This argument should be the name of a class that
     * implements the Script interface, as will any script
     * compiled by jsc.
     *
     * @exception IllegalAccessException if access is not available
     *            to the class
     * @exception InstantiationException if unable to instantiate
     *            the named class
     */
    public static void loadClass(Context cx, Scriptable thisObj,
                                 Object[] args, Function funObj)
        throws IllegalAccessException, InstantiationException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[62]++;
        Class<?> clazz = getClass(args);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((Script.class.isAssignableFrom(clazz)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[24]++;
            throw reportRuntimeError("msg.must.implement.Script");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[25]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[64]++;
        Script script = (Script) clazz.newInstance();
        script.exec(cx, thisObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[65]++;
    }

    private static Class<?> getClass(Object[] args) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[26]++;
            throw reportRuntimeError("msg.expected.string.arg");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[27]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[67]++;
        Object arg0 = args[0];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[68]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((arg0 instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[28]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[69]++;
            Object wrapped = ((Wrapper)arg0).unwrap();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[70]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((wrapped instanceof Class) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[30]++;
                return (Class<?>)wrapped;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[31]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[29]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[71]++;
        String className = Context.toString(args[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[72]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            return Class.forName(className);
        }
        catch (ClassNotFoundException cnfe) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[33]++;
            throw reportRuntimeError("msg.class.not.found", className);
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[32]++;
}
  }
    }

    public static void serialize(Context cx, Scriptable thisObj,
                                 Object[] args, Function funObj)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[73]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((args.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[34]++;
            throw Context.reportRuntimeError(
                "Expected an object to serialize and a filename to write " +
                "the serialization to");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[35]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[74]++;
        Object obj = args[0];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[75]++;
        String filename = Context.toString(args[1]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[76]++;
        FileOutputStream fos = new FileOutputStream(filename);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[77]++;
        Scriptable scope = ScriptableObject.getTopLevelScope(thisObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[78]++;
        ScriptableOutputStream out = new ScriptableOutputStream(fos, scope);
        out.writeObject(obj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[79]++;
        out.close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[80]++;
    }

    public static Object deserialize(Context cx, Scriptable thisObj,
                                     Object[] args, Function funObj)
        throws IOException, ClassNotFoundException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[81]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[36]++;
            throw Context.reportRuntimeError(
                "Expected a filename to read the serialization from");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[37]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[82]++;
        String filename = Context.toString(args[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[83]++;
        FileInputStream fis = new FileInputStream(filename);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[84]++;
        Scriptable scope = ScriptableObject.getTopLevelScope(thisObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[85]++;
        ObjectInputStream in = new ScriptableInputStream(fis, scope);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[86]++;
        Object deserialized = in.readObject();
        in.close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[87]++;
        return Context.toObject(deserialized, scope);
    }

    public String[] getPrompts(Context cx) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[88]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(this, "prompts")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[38]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[89]++;
            Object promptsJS = ScriptableObject.getProperty(this,
                                                            "prompts");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[90]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((promptsJS instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[40]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[91]++;
                Scriptable s = (Scriptable) promptsJS;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[92]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((ScriptableObject.hasProperty(s, 0)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(s, 1)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false))
                {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[42]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[93]++;
                    Object elem0 = ScriptableObject.getProperty(s, 0);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[94]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((elem0 instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[44]++;
                        elem0 = ((Function) elem0).call(cx, this, s,
                                new Object[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[95]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[45]++;}
                    prompts[0] = Context.toString(elem0);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[96]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[97]++;
                    Object elem1 = ScriptableObject.getProperty(s, 1);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[98]++;
int CodeCoverConditionCoverageHelper_C21;
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((elem1 instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[46]++;
                        elem1 = ((Function) elem1).call(cx, this, s,
                                new Object[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[99]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[47]++;}
                    prompts[1] = Context.toString(elem1);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[100]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[43]++;}

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[41]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[39]++;}
        return prompts;
    }

    /**
     * Example: doctest("js> function f() {\n  >   return 3;\n  > }\njs> f();\n3\n"); returns 2
     * (since 2 tests were executed).
     */
    public static Object doctest(Context cx, Scriptable thisObj,
                                 Object[] args, Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[101]++;
int CodeCoverConditionCoverageHelper_C22;
    	if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[48]++;
    		return Boolean.FALSE;

    	} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[49]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[102]++;
    	String session = Context.toString(args[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[103]++;
        Global global = getInstance(funObj);
        return new Integer(global.runDoctest(cx, global, session, null, 0));
    }

    public int runDoctest(Context cx, Scriptable scope, String session,
                          String sourceName, int lineNumber)
    {
        doctestCanonicalizations = new HashMap<String,String>();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[104]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[105]++;
        String[] lines = session.split("[\n\r]+");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[106]++;
        String prompt0 = this.prompts[0].trim();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[107]++;
        String prompt1 = this.prompts[1].trim();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[108]++;
        int testCount = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[109]++;
        int i = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[110]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
        while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((i < lines.length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((lines[i].trim().startsWith(prompt0)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[10]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[11]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[12]++;
}
            i++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[111]++; // skip lines that don't look like shell sessions
        }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[112]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[13]++;


int CodeCoverConditionCoverageHelper_C24;
    	while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < lines.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[13]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[14]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[15]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[113]++;
    		String inputString = lines[i].trim().substring(prompt0.length());
            inputString += "\n";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[114]++;
    		i++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[115]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[116]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[16]++;


int CodeCoverConditionCoverageHelper_C25;
    		while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((i < lines.length) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((lines[i].trim().startsWith(prompt1)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[16]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[17]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[18]++;
}
    			inputString += lines[i].trim().substring(prompt1.length());
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[117]++;
    			inputString += "\n";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[118]++;
    			i++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[119]++;
    		}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[120]++;
            String expectedString = "";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[121]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[19]++;


int CodeCoverConditionCoverageHelper_C26;
            while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((i < lines.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lines[i].trim().startsWith(prompt0)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false))
            {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[19]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[20]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[21]++;
}
                expectedString += lines[i] + "\n";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[122]++;
                i++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[123]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[124]++;
    		PrintStream savedOut = this.getOut();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[125]++;
    		PrintStream savedErr = this.getErr();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[126]++;
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[127]++;
    		ByteArrayOutputStream err = new ByteArrayOutputStream();
    		this.setOut(new PrintStream(out));
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[128]++;
    		this.setErr(new PrintStream(err));
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[129]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[130]++;
    		String resultString = "";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[131]++;
    		ErrorReporter savedErrorReporter = cx.getErrorReporter();
    		cx.setErrorReporter(new ToolErrorReporter(false, this.getErr()));
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[132]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[133]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
    		try {
CodeCoverTryBranchHelper_Try4 = true;
    		    testCount++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[134]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[135]++;
	    		Object result = cx.evaluateString(scope, inputString,
	    				            "doctest input", 1, null);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[136]++;
int CodeCoverConditionCoverageHelper_C27;
	            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (32)) == 0 || true) &&
 ((result != Context.getUndefinedValue()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((result instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((inputString.trim().startsWith("function")) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 3) && false))
	            {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[51]++;
	            	resultString = Context.toString(result);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[137]++;

	            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[52]++;}
    		} catch (RhinoException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[53]++;
                ToolErrorReporter.reportException(cx.getErrorReporter(), e);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[138]++;
    		} finally {
if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[50]++;
}
    		    this.setOut(savedOut);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[139]++;
    		    this.setErr(savedErr);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[140]++;
        		cx.setErrorReporter(savedErrorReporter);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[141]++;
    			resultString += err.toString() + out.toString();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[142]++;
    		}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[143]++;
int CodeCoverConditionCoverageHelper_C28;
    		if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((doctestOutputMatches(expectedString, resultString)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[54]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[144]++;
    		    String message = "doctest failure running:\n" +
                    inputString +
                    "expected: " + expectedString +
                    "actual: " + resultString + "\n";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[145]++;
int CodeCoverConditionCoverageHelper_C29;
    		    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[56]++;
                    throw Context.reportRuntimeError(message, sourceName,
                            lineNumber+i-1, null, 0);
}
    		    else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[57]++;
                    throw Context.reportRuntimeError(message);
}

    		} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[55]++;}
    	}
    	return testCount;
    }

    /**
     * Compare actual result of doctest to expected, modulo some
     * acceptable differences. Currently just trims the strings
     * before comparing, but should ignore differences in line numbers
     * for error messages for example.
     *
     * @param expected the expected string
     * @param actual the actual string
     * @return true iff actual matches expected modulo some acceptable
     *      differences
     */
    private boolean doctestOutputMatches(String expected, String actual) {
        expected = expected.trim();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[146]++;
        actual = actual.trim().replace("\r\n", "\n");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[147]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[148]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((expected.equals(actual)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[58]++;
            return true;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[59]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[149]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[22]++;


        for (Map.Entry<String,String> entry: doctestCanonicalizations.entrySet()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[22]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[23]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[24]++;
}
            expected = expected.replace(entry.getKey(), entry.getValue());
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[150]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[151]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((expected.equals(actual)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[60]++;
            return true;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[61]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[152]++;
        // java.lang.Object.toString() prints out a unique hex number associated
        // with each object. This number changes from run to run, so we want to
        // ignore differences between these numbers in the output. We search for a
        // regexp that matches the hex number preceded by '@', then enter mappings into
        // "doctestCanonicalizations" so that we ensure that the mappings are
        // consistent within a session.
        Pattern p = Pattern.compile("@[0-9a-fA-F]+");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[153]++;
        Matcher expectedMatcher = p.matcher(expected);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[154]++;
        Matcher actualMatcher = p.matcher(actual);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[155]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[25]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[25]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[26]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[27]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[156]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((expectedMatcher.find()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[62]++;
                return false;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[63]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[157]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((actualMatcher.find()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[64]++;
                return false;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[65]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[158]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((actualMatcher.start() != expectedMatcher.start()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[66]++;
                return false;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[67]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[159]++;
            int start = expectedMatcher.start();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[160]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((expected.substring(0, start).equals(actual.substring(0, start))) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[68]++;
                return false;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[69]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[161]++;
            String expectedGroup = expectedMatcher.group();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[162]++;
            String actualGroup = actualMatcher.group();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[163]++;
            String mapping = doctestCanonicalizations.get(expectedGroup);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[164]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((mapping == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[70]++;
                doctestCanonicalizations.put(expectedGroup, actualGroup);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[165]++;
                expected = expected.replace(expectedGroup, actualGroup);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[166]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[71]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[167]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((actualGroup.equals(mapping)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[72]++;
                return false;
 // wrong object!
            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[73]++;}
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[168]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((expected.equals(actual)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[74]++;
                return true;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[75]++;}
        }
    }

    /**
     * The spawn function runs a given function or script in a different
     * thread.
     *
     * js> function g() { a = 7; }
     * js> a = 3;
     * 3
     * js> spawn(g)
     * Thread[Thread-1,5,main]
     * js> a
     * 3
     */
    public static Object spawn(Context cx, Scriptable thisObj, Object[] args,
                               Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[169]++;
        Scriptable scope = funObj.getParentScope();
        Runner runner;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[170]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((args[0] instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[76]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[171]++;
            Object[] newArgs = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[172]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((args.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((args[1] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[78]++;
                newArgs = cx.getElements((Scriptable) args[1]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[173]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[79]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[174]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((newArgs == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[80]++; newArgs = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[175]++;
 } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[81]++;}
            runner = new Runner(scope, (Function) args[0], newArgs);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[176]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[77]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[177]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((args[0] instanceof Script) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[82]++;
            runner = new Runner(scope, (Script) args[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[178]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[83]++;
            throw reportRuntimeError("msg.spawn.args");
        }
}
        runner.factory = cx.getFactory();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[179]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[180]++;
        Thread thread = new Thread(runner);
        thread.start();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[181]++;
        return thread;
    }

    /**
     * The sync function creates a synchronized function (in the sense
     * of a Java synchronized method) from an existing function. The
     * new function synchronizes on the the second argument if it is
     * defined, or otherwise the <code>this</code> object of
     * its invocation.
     * js> var o = { f : sync(function(x) {
     *       print("entry");
     *       Packages.java.lang.Thread.sleep(x*1000);
     *       print("exit");
     *     })};
     * js> spawn(function() {o.f(5);});
     * Thread[Thread-0,5,main]
     * entry
     * js> spawn(function() {o.f(5);});
     * Thread[Thread-1,5,main]
     * js>
     * exit
     * entry
     * exit
     */
    public static Object sync(Context cx, Scriptable thisObj, Object[] args,
                              Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[182]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (32)) == 0 || true) &&
 ((args.length >= 1) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((args.length <= 2) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((args[0] instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[84]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[183]++;
            Object syncObject = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[184]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((args.length == 2) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((args[1] != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[86]++;
                syncObject = args[1];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[185]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[87]++;}
            return new Synchronizer((Function)args[0], syncObject);

        }
        else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[85]++;
            throw reportRuntimeError("msg.sync.args");
        }
    }

    /**
     * Execute the specified command with the given argument and options
     * as a separate process and return the exit status of the process.
     * <p>
     * Usage:
     * <pre>
     * runCommand(command)
     * runCommand(command, arg1, ..., argN)
     * runCommand(command, arg1, ..., argN, options)
     * </pre>
     * All except the last arguments to runCommand are converted to strings
     * and denote command name and its arguments. If the last argument is a
     * JavaScript object, it is an option object. Otherwise it is converted to
     * string denoting the last argument and options objects assumed to be
     * empty.
     * The following properties of the option object are processed:
     * <ul>
     * <li><tt>args</tt> - provides an array of additional command arguments
     * <li><tt>env</tt> - explicit environment object. All its enumerable
     *   properties define the corresponding environment variable names.
     * <li><tt>input</tt> - the process input. If it is not
     *   java.io.InputStream, it is converted to string and sent to the process
     *   as its input. If not specified, no input is provided to the process.
     * <li><tt>output</tt> - the process output instead of
     *   java.lang.System.out. If it is not instance of java.io.OutputStream,
     *   the process output is read, converted to a string, appended to the
     *   output property value converted to string and put as the new value of
     *   the output property.
     * <li><tt>err</tt> - the process error output instead of
     *   java.lang.System.err. If it is not instance of java.io.OutputStream,
     *   the process error output is read, converted to a string, appended to
     *   the err property value converted to string and put as the new
     *   value of the err property.
     * </ul>
     */
    public static Object runCommand(Context cx, Scriptable thisObj,
                                    Object[] args, Function funObj)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[186]++;
        int L = args.length;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[187]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (32)) == 0 || true) &&
 ((L == 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((L == 1) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((args[0] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[88]++;
            throw reportRuntimeError("msg.runCommand.bad.args");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[89]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[188]++;

        InputStream in = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[189]++;
        OutputStream out = null, err = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[190]++;
        ByteArrayOutputStream outBytes = null, errBytes = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[191]++;
        Object outObj = null, errObj = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[192]++;
        String[] environment = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[193]++;
        Scriptable params = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[194]++;
        Object[] addArgs = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[195]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((args[L - 1] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[90]++;
            params = (Scriptable)args[L - 1];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[196]++;
            --L;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[197]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[198]++;
            Object envObj = ScriptableObject.getProperty(params, "env");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[199]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((envObj != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[92]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[200]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((envObj == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[94]++;
                    environment = new String[0];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[201]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[95]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[202]++;
int CodeCoverConditionCoverageHelper_C50;
                    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((envObj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[96]++;
                        throw reportRuntimeError("msg.runCommand.bad.env");

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[97]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[203]++;
                    Scriptable envHash = (Scriptable)envObj;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[204]++;
                    Object[] ids = ScriptableObject.getPropertyIds(envHash);
                    environment = new String[ids.length];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[205]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[206]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[28]++;


int CodeCoverConditionCoverageHelper_C51;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((i != ids.length) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[28]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[29]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[30]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[207]++;
                        Object keyObj = ids[i], val;
                        String key;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[208]++;
int CodeCoverConditionCoverageHelper_C52;
                        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((keyObj instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[98]++;
                            key = (String)keyObj;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[209]++;
                            val = ScriptableObject.getProperty(envHash, key);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[210]++;

                        } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[99]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[211]++;
                            int ikey = ((Number)keyObj).intValue();
                            key = Integer.toString(ikey);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[212]++;
                            val = ScriptableObject.getProperty(envHash, ikey);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[213]++;
                        }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[214]++;
int CodeCoverConditionCoverageHelper_C53;
                        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((val == ScriptableObject.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[100]++;
                            val = Undefined.instance;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[215]++;

                        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[101]++;}
                        environment[i] = key+'='+ScriptRuntime.toString(val);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[216]++;
                    }
                }

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[93]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[217]++;
            Object inObj = ScriptableObject.getProperty(params, "input");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[218]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((inObj != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[102]++;
                in = toInputStream(inObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[219]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[103]++;}
            outObj = ScriptableObject.getProperty(params, "output");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[220]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[221]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((outObj != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[104]++;
                out = toOutputStream(outObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[222]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[223]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((out == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[106]++;
                    outBytes = new ByteArrayOutputStream();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[224]++;
                    out = outBytes;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[225]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[107]++;}

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[105]++;}
            errObj = ScriptableObject.getProperty(params, "err");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[226]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[227]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((errObj != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[108]++;
                err = toOutputStream(errObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[228]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[229]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((err == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[110]++;
                    errBytes = new ByteArrayOutputStream();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[230]++;
                    err = errBytes;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[231]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[111]++;}

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[109]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[232]++;
            Object addArgsObj = ScriptableObject.getProperty(params, "args");
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[233]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((addArgsObj != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[112]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[234]++;
                Scriptable s = Context.toObject(addArgsObj,
                                                getTopLevelScope(thisObj));
                addArgs = cx.getElements(s);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[235]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[113]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[91]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[236]++;
        Global global = getInstance(funObj);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[237]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((out == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[114]++;
            out = (global != null) ? global.getOut() : System.out;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[238]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[115]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[239]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((err == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[116]++;
            err = (global != null) ? global.getErr() : System.err;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[240]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[117]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[241]++;
        // If no explicit input stream, do not send any input to process,
        // in particular, do not use System.in to avoid deadlocks
        // when waiting for user input to send to process which is already
        // terminated as it is not always possible to interrupt read method.

        String[] cmd = new String[(addArgs == null) ? L : L + addArgs.length];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[242]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[31]++;


int CodeCoverConditionCoverageHelper_C62;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[31]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[32]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[33]++;
}
            cmd[i] = ScriptRuntime.toString(args[i]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[243]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[244]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((addArgs != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[118]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[245]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[34]++;


int CodeCoverConditionCoverageHelper_C64;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((i != addArgs.length) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[34]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[35]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[36]++;
}
                cmd[L + i] = ScriptRuntime.toString(addArgs[i]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[246]++;
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[119]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[247]++;

        int exitCode = runProcess(cmd, environment, in, out, err);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[248]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((outBytes != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[120]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[249]++;
            String s = ScriptRuntime.toString(outObj) + outBytes.toString();
            ScriptableObject.putProperty(params, "output", s);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[250]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[121]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[251]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((errBytes != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[122]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[252]++;
            String s = ScriptRuntime.toString(errObj) + errBytes.toString();
            ScriptableObject.putProperty(params, "err", s);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[253]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[123]++;}

        return new Integer(exitCode);
    }

    /**
     * The seal function seals all supplied arguments.
     */
    public static void seal(Context cx, Scriptable thisObj, Object[] args,
                            Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[254]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[37]++;


int CodeCoverConditionCoverageHelper_C67;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[37]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[38]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[39]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[255]++;
            Object arg = args[i];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[256]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((arg instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((arg == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false))
            {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[124]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[257]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((arg instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((arg == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false))
                {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[126]++;
                    throw reportRuntimeError("msg.shell.seal.not.object");

                } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[127]++;
                    throw reportRuntimeError("msg.shell.seal.not.scriptable");
                }

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[125]++;}
        }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[258]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[40]++;


int CodeCoverConditionCoverageHelper_C70;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[40]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[41]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[42]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[259]++;
            Object arg = args[i];
            ((ScriptableObject)arg).sealObject();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[260]++;
        }
    }

    /**
     * The readFile reads the given file content and convert it to a string
     * using the specified character coding or default character coding if
     * explicit coding argument is not given.
     * <p>
     * Usage:
     * <pre>
     * readFile(filePath)
     * readFile(filePath, charCoding)
     * </pre>
     * The first form converts file's context to string using the default
     * character coding.
     */
    public static Object readFile(Context cx, Scriptable thisObj, Object[] args,
                                  Function funObj)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[261]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[128]++;
            throw reportRuntimeError("msg.shell.readFile.bad.args");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[129]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[262]++;
        String path = ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[263]++;
        String charCoding = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[264]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((args.length >= 2) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[130]++;
            charCoding = ScriptRuntime.toString(args[1]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[265]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[131]++;}

        return readUrl(path, charCoding, true);
    }

    /**
     * The readUrl opens connection to the given URL, read all its data
     * and converts them to a string
     * using the specified character coding or default character coding if
     * explicit coding argument is not given.
     * <p>
     * Usage:
     * <pre>
     * readUrl(url)
     * readUrl(url, charCoding)
     * </pre>
     * The first form converts file's context to string using the default
     * charCoding.
     */
    public static Object readUrl(Context cx, Scriptable thisObj, Object[] args,
                                 Function funObj)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[266]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[132]++;
            throw reportRuntimeError("msg.shell.readUrl.bad.args");

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[133]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[267]++;
        String url = ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[268]++;
        String charCoding = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[269]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((args.length >= 2) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[134]++;
            charCoding = ScriptRuntime.toString(args[1]);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[270]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[135]++;}

        return readUrl(url, charCoding, false);
    }

    /**
     * Convert the argument to int32 number.
     */
    public static Object toint32(Context cx, Scriptable thisObj, Object[] args,
                                 Function funObj)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[271]++;
        Object arg = (args.length != 0 ? args[0] : Undefined.instance);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[272]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((arg instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[136]++;
            return arg;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[137]++;}
        return ScriptRuntime.wrapInt(ScriptRuntime.toInt32(arg));
    }

    private boolean loadJLine(Charset cs) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[273]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((attemptedJLineLoad) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[138]++;
            // Check if we can use JLine for better command line handling
            attemptedJLineLoad = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[274]++;
            console = ShellConsole.getConsole(this, cs);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[275]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[139]++;}
        return console != null;
    }

    public ShellConsole getConsole(Charset cs) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[276]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((loadJLine(cs)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[140]++;
            console = ShellConsole.getConsole(getIn(), getErr(), cs);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[277]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[141]++;}
        return console;
    }

    public InputStream getIn() {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[278]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((inStream == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((attemptedJLineLoad) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[142]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[279]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((loadJLine(Charset.defaultCharset())) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[144]++;
                inStream = console.getIn();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[280]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[145]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[143]++;}
        return inStream == null ? System.in : inStream;
    }

    public void setIn(InputStream in) {
        inStream = in;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[281]++;
    }

    public PrintStream getOut() {
        return outStream == null ? System.out : outStream;
    }

    public void setOut(PrintStream out) {
        outStream = out;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[282]++;
    }

    public PrintStream getErr() {
        return errStream == null ? System.err : errStream;
    }

    public void setErr(PrintStream err) {
        errStream = err;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[283]++;
    }

    public void setSealedStdLib(boolean value)
    {
        sealedStdLib = value;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[284]++;
    }

    private static Global getInstance(Function function)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[285]++;
        Scriptable scope = function.getParentScope();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[286]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((scope instanceof Global) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[146]++;
            throw reportRuntimeError("msg.bad.shell.function.scope",
                                     String.valueOf(scope));
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[147]++;}
        return (Global)scope;
    }

    /**
     * Runs the given process using Runtime.exec().
     * If any of in, out, err is null, the corresponding process stream will
     * be closed immediately, otherwise it will be closed as soon as
     * all data will be read from/written to process
     *
     * @return Exit value of process.
     * @throws IOException If there was an error executing the process.
     */
    private static int runProcess(String[] cmd, String[] environment,
                                  InputStream in, OutputStream out,
                                  OutputStream err)
        throws IOException
    {
        Process p;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[287]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((environment == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[148]++;
            p = Runtime.getRuntime().exec(cmd);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[288]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[149]++;
            p = Runtime.getRuntime().exec(cmd, environment);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[289]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[290]++;
boolean CodeCoverTryBranchHelper_Try5 = false;

        try {
CodeCoverTryBranchHelper_Try5 = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[291]++;
            PipeThread inThread = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[292]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((in != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[151]++;
                inThread = new PipeThread(false, in, p.getOutputStream());
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[293]++;
                inThread.start();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[294]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[152]++;
                p.getOutputStream().close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[295]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[296]++;

            PipeThread outThread = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[297]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((out != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[153]++;
                outThread = new PipeThread(true, p.getInputStream(), out);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[298]++;
                outThread.start();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[299]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[154]++;
                p.getInputStream().close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[300]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[301]++;

            PipeThread errThread = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[302]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((err != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[155]++;
                errThread = new PipeThread(true, p.getErrorStream(), err);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[303]++;
                errThread.start();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[304]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[156]++;
                p.getErrorStream().close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[305]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[306]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[43]++;



            // wait for process completion
            for (;;) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[43]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[44]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[45]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[307]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
                try {
CodeCoverTryBranchHelper_Try6 = true;
                    p.waitFor();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[308]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[309]++;
int CodeCoverConditionCoverageHelper_C86;
                    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((outThread != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[158]++;
                        outThread.join();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[310]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[159]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[311]++;
int CodeCoverConditionCoverageHelper_C87;
                    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((inThread != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[160]++;
                        inThread.join();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[312]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[161]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[313]++;
int CodeCoverConditionCoverageHelper_C88;
                    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((errThread != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[162]++;
                        errThread.join();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[314]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[163]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[315]++;
                    break;
                } catch (InterruptedException ignore) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[164]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[157]++;
}
  }
            }

            return p.exitValue();
        } finally {
if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[150]++;
}
            p.destroy();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[316]++;
        }
    }

    static void pipe(boolean fromProcess, InputStream from, OutputStream to)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[317]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[318]++;
            final int SIZE = 4096;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[319]++;
            byte[] buffer = new byte[SIZE];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[320]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[46]++;


            for (;;) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[46]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[47]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[48]++;
}
                int n;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[321]++;
int CodeCoverConditionCoverageHelper_C90;
                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((fromProcess) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[166]++;
                    n = from.read(buffer, 0, SIZE);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[322]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[167]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[323]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
                    try {
CodeCoverTryBranchHelper_Try8 = true;
                        n = from.read(buffer, 0, SIZE);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[324]++;
                    } catch (IOException ex) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[169]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[325]++;
                        // Ignore exception as it can be cause by closed pipe
                        break;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[168]++;
}
  }
                }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[326]++;
int CodeCoverConditionCoverageHelper_C91;
                if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[170]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[327]++; break;
 } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[171]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[328]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((fromProcess) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[172]++;
                    to.write(buffer, 0, n);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[329]++;
                    to.flush();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[330]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[173]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[331]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
                    try {
CodeCoverTryBranchHelper_Try9 = true;
                        to.write(buffer, 0, n);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[332]++;
                        to.flush();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[333]++;
                    } catch (IOException ex) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[175]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[334]++;
                        // Ignore exception as it can be cause by closed pipe
                        break;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[174]++;
}
  }
                }
            }
        } finally {
if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[165]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[335]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
            try {
CodeCoverTryBranchHelper_Try10 = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[336]++;
int CodeCoverConditionCoverageHelper_C93;
                if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((fromProcess) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[177]++;
                    from.close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[337]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[178]++;
                    to.close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[338]++;
                }
            } catch (IOException ex) {
CodeCoverTryBranchHelper_Try10 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[179]++;
                // Ignore errors on close. On Windows JVM may throw invalid
                // refrence exception if process terminates too fast.
            } finally {
    if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[176]++;
}
  }
        }
    }

    private static InputStream toInputStream(Object value)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[339]++;
        InputStream is = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[340]++;
        String s = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[341]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[180]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[342]++;
            Object unwrapped = ((Wrapper)value).unwrap();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[343]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((unwrapped instanceof InputStream) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[182]++;
                is = (InputStream)unwrapped;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[344]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[183]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[345]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((unwrapped instanceof byte[]) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[184]++;
                is = new ByteArrayInputStream((byte[])unwrapped);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[346]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[185]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[347]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((unwrapped instanceof Reader) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[186]++;
                s = readReader((Reader)unwrapped);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[348]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[187]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[349]++;
int CodeCoverConditionCoverageHelper_C98; if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((unwrapped instanceof char[]) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[188]++;
                s = new String((char[])unwrapped);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[350]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[189]++;}
}
}
}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[181]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[351]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((is == null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[190]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[352]++;
int CodeCoverConditionCoverageHelper_C100;
            if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[192]++; s = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[353]++;
 } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[193]++;}
            is = new ByteArrayInputStream(s.getBytes());
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[354]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[191]++;}
        return is;
    }

    private static OutputStream toOutputStream(Object value) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[355]++;
        OutputStream os = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[356]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[194]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[357]++;
            Object unwrapped = ((Wrapper)value).unwrap();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[358]++;
int CodeCoverConditionCoverageHelper_C102;
            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((unwrapped instanceof OutputStream) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[196]++;
                os = (OutputStream)unwrapped;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[359]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[197]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[195]++;}
        return os;
    }

    private static String readUrl(String filePath, String charCoding,
                                  boolean urlIsFile)
        throws IOException
    {
        int chunkLength;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[360]++;
        InputStream is = null;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[361]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
        try {
CodeCoverTryBranchHelper_Try11 = true;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[362]++;
int CodeCoverConditionCoverageHelper_C103;
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((urlIsFile) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[199]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[363]++;
                URL urlObj = new URL(filePath);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[364]++;
                URLConnection uc = urlObj.openConnection();
                is = uc.getInputStream();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[365]++;
                chunkLength = uc.getContentLength();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[366]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[367]++;
int CodeCoverConditionCoverageHelper_C104;
                if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((chunkLength <= 0) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[201]++;
                    chunkLength = 1024;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[368]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[202]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[369]++;
int CodeCoverConditionCoverageHelper_C105;
                if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((charCoding == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[203]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[370]++;
                    String type = uc.getContentType();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[371]++;
int CodeCoverConditionCoverageHelper_C106;
                    if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[205]++;
                        charCoding = getCharCodingFromType(type);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[372]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[206]++;}

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[204]++;}

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[200]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[373]++;
                File f = new File(filePath);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[374]++;
int CodeCoverConditionCoverageHelper_C107;
                if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((f.exists()) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[207]++;
                    throw new FileNotFoundException("File not found: " + filePath);

                } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[208]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[375]++;
int CodeCoverConditionCoverageHelper_C108; if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((f.canRead()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[209]++;
                    throw new IOException("Cannot read file: " + filePath);

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[210]++;}
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[376]++;
                long length = f.length();
                chunkLength = (int)length;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[377]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[378]++;
int CodeCoverConditionCoverageHelper_C109;
                if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((chunkLength != length) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[211]++;
                    throw new IOException("Too big file size: "+length);
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[212]++;}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[379]++;
int CodeCoverConditionCoverageHelper_C110;

                if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((chunkLength == 0) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[213]++; return "";
 } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[214]++;}

                is = new FileInputStream(f);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[380]++;
            }

            Reader r;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[381]++;
int CodeCoverConditionCoverageHelper_C111;
            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((charCoding == null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[215]++;
                r = new InputStreamReader(is);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[382]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[216]++;
                r = new InputStreamReader(is, charCoding);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[383]++;
            }
            return readReader(r, chunkLength);

        } finally {
if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[198]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[384]++;
int CodeCoverConditionCoverageHelper_C112;
            if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((is != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[217]++;
                is.close();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[385]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[218]++;}
        }
    }

    private static String getCharCodingFromType(String type)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[386]++;
        int i = type.indexOf(';');
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[387]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[219]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[388]++;
            int end = type.length();
            ++i;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[389]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[390]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[49]++;


int CodeCoverConditionCoverageHelper_C114;
            while ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((type.charAt(i) <= ' ') && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) && false)) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[49]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[50]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[51]++;
}
                ++i;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[391]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[392]++;
            String charset = "charset";
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[393]++;
int CodeCoverConditionCoverageHelper_C115;
            if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((charset.regionMatches(true, 0, type, i, charset.length())) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false))
            {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[221]++;
                i += charset.length();
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[394]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[395]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[52]++;


int CodeCoverConditionCoverageHelper_C116;
                while ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (8)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((type.charAt(i) <= ' ') && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) && false)) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[52]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[53]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[54]++;
}
                    ++i;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[396]++;
                }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[397]++;
int CodeCoverConditionCoverageHelper_C117;
                if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((type.charAt(i) == '=') && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[223]++;
                    ++i;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[398]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[399]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[55]++;


int CodeCoverConditionCoverageHelper_C118;
                    while ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((type.charAt(i) <= ' ') && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[55]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[56]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[57]++;
}
                        ++i;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[400]++;
                    }
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[401]++;
int CodeCoverConditionCoverageHelper_C119;
                    if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[225]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[402]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[58]++;


int CodeCoverConditionCoverageHelper_C120;
                        // i is at the start of non-empty
                        // charCoding spec
                        while ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((type.charAt(end -1) <= ' ') && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[58]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[59]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[60]++;
}
                            --end;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[403]++;
                        }
                        return type.substring(i, end);

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[226]++;}

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[224]++;}

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[222]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[220]++;}
        return null;
    }

    private static String readReader(Reader reader)
        throws IOException
    {
        return readReader(reader, 4096);
    }

    private static String readReader(Reader reader, int initialBufferSize)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[404]++;
        char[] buffer = new char[initialBufferSize];
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[405]++;
        int offset = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[406]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[61]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[61]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[62]--;
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.loops[63]++;
}
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[407]++;
            int n = reader.read(buffer, offset, buffer.length - offset);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[408]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[227]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[409]++; break;
    } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[228]++;}
            offset += n;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[410]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[411]++;
int CodeCoverConditionCoverageHelper_C123;
            if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((offset == buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[229]++;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[412]++;
                char[] tmp = new char[buffer.length * 2];
                System.arraycopy(buffer, 0, tmp, 0, offset);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[413]++;
                buffer = tmp;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[414]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[230]++;}
        }
        return new String(buffer, 0, offset);
    }

    static RuntimeException reportRuntimeError(String msgId) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[415]++;
        String message = ToolErrorReporter.getMessage(msgId);
        return Context.reportRuntimeError(message);
    }

    static RuntimeException reportRuntimeError(String msgId, String msgArg)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[416]++;
        String message = ToolErrorReporter.getMessage(msgId, msgArg);
        return Context.reportRuntimeError(message);
    }
}


class Runner implements Runnable, ContextAction {
  static {
    CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.ping();
  }


    Runner(Scriptable scope, Function func, Object[] args) {
        this.scope = scope;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[417]++;
        f = func;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[418]++;
        this.args = args;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[419]++;
    }

    Runner(Scriptable scope, Script script) {
        this.scope = scope;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[420]++;
        s = script;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[421]++;
    }

    public void run()
    {
        factory.call(this);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[422]++;
    }

    public Object run(Context cx)
    {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[423]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((f != null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[231]++;
            return f.call(cx, scope, scope, args);
}
        else {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[232]++;
            return s.exec(cx, scope);
}
    }

    ContextFactory factory;
    private Scriptable scope;
    private Function f;
    private Script s;
    private Object[] args;
}

class PipeThread extends Thread {
  static {
    CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.ping();
  }


    PipeThread(boolean fromProcess, InputStream from, OutputStream to) {
        setDaemon(true);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[424]++;
        this.fromProcess = fromProcess;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[425]++;
        this.from = from;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[426]++;
        this.to = to;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[427]++;
    }

    @Override
    public void run() {
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[428]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
        try {
CodeCoverTryBranchHelper_Try12 = true;
            Global.pipe(fromProcess, from, to);
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.statements[429]++;
        } catch (IOException ex) {
CodeCoverTryBranchHelper_Try12 = false;
CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[234]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9.branches[233]++;
}
  }
    }

    private boolean fromProcess;
    private InputStream from;
    private OutputStream to;
}

class CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9 ());
  }
    public static long[] statements = new long[430];
    public static long[] branches = new long[235];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[125];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-Global.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,2,2,3,1,1,1,1,0,1,1,1,1,1,1,1,2,2,1,2,3,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,2,2,1,1,0,1,1,1};
    for (int i = 1; i <= 124; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[64];

  public CodeCoverCoverageCounter$1wcjkiz20v538qq77ppvpzcpwxskg3mu9 () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-Global.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 429; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 234; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 124; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 63; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-Global.java");
      for (int i = 1; i <= 429; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 234; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 124; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 21; i++) {
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

