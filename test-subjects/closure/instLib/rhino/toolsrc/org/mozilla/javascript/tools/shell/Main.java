/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.commonjs.module.ModuleScope;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.tools.SourceReader;
import org.mozilla.javascript.tools.ToolErrorReporter;

/**
 * The shell program.
 *
 * Can execute scripts interactively or in batch mode at the command line.
 * An example of controlling the JavaScript engine.
 *
 */
public class Main
{
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.ping();
  }

    public static ShellContextFactory
        shellContextFactory = new ShellContextFactory();
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[1]++;
  }

    public static Global global = new Global();
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[2]++;
  }
    static protected ToolErrorReporter errorReporter;
    static protected int exitCode = 0;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[3]++;
  }
    static private final int EXITCODE_RUNTIME_ERROR = 3;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[4]++;
  }
    static private final int EXITCODE_FILE_NOT_FOUND = 4;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[5]++;
  }
    static boolean processStdin = true;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[6]++;
  }
    static List<String> fileList = new ArrayList<String>();
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[7]++;
  }
    static List<String> modulePath;
    static String mainModule;
    static boolean sandboxed = false;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[8]++;
  }
    static boolean useRequire = false;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[9]++;
  }
    static Require require;
    private static SecurityProxy securityImpl;
    private final static ScriptCache scriptCache = new ScriptCache(32);
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[10]++;
  }

    static {
        global.initQuitAction(new IProxy(IProxy.SYSTEM_EXIT));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[11]++;
    }

    /**
     * Proxy class to avoid proliferation of anonymous classes.
     */
    private static class IProxy implements ContextAction, QuitAction
    {
        private static final int PROCESS_FILES = 1;
        private static final int EVAL_INLINE_SCRIPT = 2;
        private static final int SYSTEM_EXIT = 3;

        private int type;
        String[] args;
        String scriptText;

        IProxy(int type)
        {
            this.type = type;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[12]++;
        }

        public Object run(Context cx)
        {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((useRequire) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[1]++;
                require = global.installRequire(cx, modulePath, sandboxed);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[14]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[2]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type == PROCESS_FILES) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[3]++;
                processFiles(cx, args);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[16]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[4]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[17]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type == EVAL_INLINE_SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[5]++;
                evalInlineScript(cx, scriptText);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[18]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[6]++;
                throw Kit.codeBug();
            }
}
            return null;
        }

        public void quit(Context cx, int exitCode)
        {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type == SYSTEM_EXIT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[7]++;
                System.exit(exitCode);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[20]++;
                return;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[8]++;}
            throw Kit.codeBug();
        }
    }

    /**
     * Main entry point.
     *
     * Process arguments as would a normal Java program. Also
     * create a new Context and associate it with the current thread.
     * Then set up the execution environment and begin to
     * execute scripts.
     */
    public static void main(String args[]) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[21]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Boolean.getBoolean("rhino.use_java_policy_security")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[10]++;
                initJavaPolicySecuritySupport();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[23]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[11]++;}
        } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[12]++;
            ex.printStackTrace(System.err);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[24]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[9]++;
}
  }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[25]++;

        int result = exec(args);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((result != 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[13]++;
            System.exit(result);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[27]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[14]++;}
    }

    /**
     *  Execute the given arguments, but don't System.exit at the end.
     */
    public static int exec(String origArgs[])
    {
        errorReporter = new ToolErrorReporter(false, global.getErr());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[28]++;
        shellContextFactory.setErrorReporter(errorReporter);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[29]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[30]++;
        String[] args = processOptions(origArgs);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((processStdin) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[15]++;
            fileList.add(null);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[32]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[16]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((global.initialized) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[17]++;
            global.init(shellContextFactory);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[34]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[18]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[35]++;
        IProxy iproxy = new IProxy(IProxy.PROCESS_FILES);
        iproxy.args = args;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[36]++;
        shellContextFactory.call(iproxy);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[37]++;

        return exitCode;
    }

    static void processFiles(Context cx, String[] args)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[38]++;
        // define "arguments" array in the top-level object:
        // need to allocate new array since newArray requires instances
        // of exactly Object[], not ObjectSubclass[]
        Object[] array = new Object[args.length];
        System.arraycopy(args, 0, array, 0, args.length);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[39]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[40]++;
        Scriptable argsObj = cx.newArray(global, array);
        global.defineProperty("arguments", argsObj,
                              ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[41]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[42]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[1]++;



        for (String file: fileList) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[1]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[2]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[3]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[43]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                processSource(cx, file);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[44]++;
            } catch (IOException ioex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[20]++;
                Context.reportError(ToolErrorReporter.getMessage(
                        "msg.couldnt.read.source", file, ioex.getMessage()));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[45]++;
                exitCode = EXITCODE_FILE_NOT_FOUND;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[46]++;
            } catch (RhinoException rex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[21]++;
                ToolErrorReporter.reportException(
                    cx.getErrorReporter(), rex);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[47]++;
                exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[48]++;
            } catch (VirtualMachineError ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[22]++;
                // Treat StackOverflow and OutOfMemory as runtime errors
                ex.printStackTrace();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[49]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[50]++;
                String msg = ToolErrorReporter.getMessage(
                    "msg.uncaughtJSException", ex.toString());
                Context.reportError(msg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[51]++;
                exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[52]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[19]++;
}
  }
        }
    }

    static void evalInlineScript(Context cx, String scriptText) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[53]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[54]++;
            Script script = cx.compileString(scriptText, "<command>", 1, null);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[55]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[24]++;
                script.exec(cx, getShellScope());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[56]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[25]++;}
        } catch (RhinoException rex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[26]++;
            ToolErrorReporter.reportException(
                    cx.getErrorReporter(), rex);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[57]++;
            exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[58]++;
        } catch (VirtualMachineError ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[27]++;
            // Treat StackOverflow and OutOfMemory as runtime errors
            ex.printStackTrace();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[59]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[60]++;
            String msg = ToolErrorReporter.getMessage(
                    "msg.uncaughtJSException", ex.toString());
            Context.reportError(msg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[61]++;
            exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[62]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[23]++;
}
  }
    }

    public static Global getGlobal()
    {
        return global;
    }

    static Scriptable getShellScope() {
        return getScope(null);
    }

    static Scriptable getScope(String path) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[63]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((useRequire) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[28]++;
            // If CommonJS modules are enabled use a module scope that resolves
            // relative ids relative to the current URL, file or working directory.
            URI uri;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[64]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((path == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[30]++;
                // use current directory for shell and -e switch
                uri = new File(System.getProperty("user.dir")).toURI();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[65]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[31]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
                // find out whether this is a file path or a URL
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((SourceReader.toUrl(path) != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[32]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[67]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                    try {
CodeCoverTryBranchHelper_Try4 = true;
                        uri = new URI(path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[68]++;
                    } catch (URISyntaxException x) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[35]++;
                        // fall back to file uri
                        uri = new File(path).toURI();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[69]++;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[34]++;
}
  }

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[33]++;
                    uri = new File(path).toURI();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[70]++;
                }
            }
            return new ModuleScope(global, uri, null);

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[29]++;
            return global;
        }
    }

    /**
     * Parse arguments.
     */
    public static String[] processOptions(String args[])
    {
        String usageError;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[71]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[4]++;


        goodUsage: for (int i = 0; ; ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[4]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[5]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[6]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[72]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[36]++;
                return new String[0];

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[37]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[73]++;
            String arg = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[74]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((arg.startsWith("-")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[38]++;
                processStdin = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[75]++;
                fileList.add(arg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[76]++;
                mainModule = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[77]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[78]++;
                String[] result = new String[args.length - i - 1];
                System.arraycopy(args, i+1, result, 0, args.length - i - 1);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[79]++;
                return result;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[39]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[80]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((arg.equals("-version")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[40]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((++i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[42]++;
                    usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[82]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[83]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[43]++;}
                int version;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[84]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                try {
CodeCoverTryBranchHelper_Try5 = true;
                    version = Integer.parseInt(args[i]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[85]++;
                } catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[45]++;
                    usageError = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[86]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[87]++;
                    break goodUsage;
                } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[44]++;
}
  }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((Context.isValidLanguageVersion(version)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[46]++;
                    usageError = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[89]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[90]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[47]++;}
                shellContextFactory.setLanguageVersion(version);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[91]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[92]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[41]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[93]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((arg.equals("-opt")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((arg.equals("-O")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[48]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[94]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((++i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[50]++;
                    usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[95]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[96]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[51]++;}
                int opt;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[97]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
                try {
CodeCoverTryBranchHelper_Try6 = true;
                    opt = Integer.parseInt(args[i]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[98]++;
                } catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[53]++;
                    usageError = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[99]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[100]++;
                    break goodUsage;
                } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[52]++;
}
  }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[101]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((opt == -2) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[54]++;
                    // Compatibility with Cocoon Rhino fork
                    opt = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[102]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[55]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[103]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((Context.isValidOptimizationLevel(opt)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[56]++;
                    usageError = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[104]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[105]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[57]++;}
}
                shellContextFactory.setOptimizationLevel(opt);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[106]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[107]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[49]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[108]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((arg.equals("-encoding")) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[58]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[109]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((++i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[60]++;
                    usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[110]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[111]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[61]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[112]++;
                String enc = args[i];
                shellContextFactory.setCharacterEncoding(enc);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[113]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[114]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[59]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[115]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((arg.equals("-strict")) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[62]++;
                shellContextFactory.setStrictMode(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[116]++;
                shellContextFactory.setAllowReservedKeywords(false);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[117]++;
                errorReporter.setIsReportingWarnings(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[118]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[119]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[63]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[120]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((arg.equals("-fatal-warnings")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[64]++;
                shellContextFactory.setWarningAsError(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[121]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[122]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[65]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[123]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((arg.equals("-e")) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[66]++;
                processStdin = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[124]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[125]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((++i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[68]++;
                    usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[126]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[127]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[69]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[128]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((global.initialized) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[70]++;
                    global.init(shellContextFactory);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[129]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[71]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[130]++;
                IProxy iproxy = new IProxy(IProxy.EVAL_INLINE_SCRIPT);
                iproxy.scriptText = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[131]++;
                shellContextFactory.call(iproxy);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[132]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[133]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[67]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[134]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((arg.equals("-require")) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[72]++;
                useRequire = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[135]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[136]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[73]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[137]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((arg.equals("-sandbox")) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[74]++;
                sandboxed = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[138]++;
                useRequire = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[139]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[140]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[75]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[141]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((arg.equals("-modules")) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[76]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[142]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((++i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[78]++;
                    usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[143]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[144]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[79]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[145]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((modulePath == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[80]++;
                    modulePath = new ArrayList<String>();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[146]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[81]++;}
                modulePath.add(args[i]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[147]++;
                useRequire = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[148]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[149]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[77]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[150]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((arg.equals("-w")) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[82]++;
                errorReporter.setIsReportingWarnings(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[151]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[152]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[83]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[153]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((arg.equals("-f")) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[84]++;
                processStdin = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[154]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[155]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((++i == args.length) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[86]++;
                    usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[156]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[157]++;
                    break goodUsage;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[87]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[158]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((args[i].equals("-")) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[88]++;
                    fileList.add(null);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[159]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[89]++;
                    fileList.add(args[i]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[160]++;
                    mainModule = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[161]++;
                }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[162]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[85]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[163]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((arg.equals("-sealedlib")) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[90]++;
                global.setSealedStdLib(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[164]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[165]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[91]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[166]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((arg.equals("-debug")) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[92]++;
                shellContextFactory.setGeneratingDebug(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[167]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[168]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[93]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[169]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((arg.equals("-?")) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((arg.equals("-help")) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[94]++;
                // print usage message
                global.getOut().println(
                    ToolErrorReporter.getMessage("msg.shell.usage", Main.class.getName()));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[170]++;
                System.exit(1);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[171]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[95]++;}
            usageError = arg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[172]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[173]++;
            break goodUsage;
        }
        // print error and usage message
        global.getOut().println(
            ToolErrorReporter.getMessage("msg.shell.invalid", usageError));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[174]++;
        global.getOut().println(
            ToolErrorReporter.getMessage("msg.shell.usage", Main.class.getName()));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[175]++;
        System.exit(1);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[176]++;
        return null;
    }

    private static void initJavaPolicySecuritySupport()
    {
        Throwable exObj;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[177]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[178]++;
            Class<?> cl = Class.forName
                ("org.mozilla.javascript.tools.shell.JavaPolicySecurity");
            securityImpl = (SecurityProxy)cl.newInstance();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[179]++;
            SecurityController.initGlobal(securityImpl);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[180]++;
            return;
        } catch (ClassNotFoundException ex) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[97]++;
            exObj = ex;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[181]++;
        } catch (IllegalAccessException ex) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[98]++;
            exObj = ex;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[182]++;
        } catch (InstantiationException ex) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[99]++;
            exObj = ex;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[183]++;
        } catch (LinkageError ex) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[100]++;
            exObj = ex;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[184]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[96]++;
}
  }
        throw Kit.initCause(new IllegalStateException(
            "Can not load security support: "+exObj), exObj);
    }

    /**
     * Evaluate JavaScript source.
     *
     * @param cx the current context
     * @param filename the name of the file to compile, or null
     *                 for interactive mode.
     * @throws IOException if the source could not be read
     * @throws RhinoException thrown during evaluation of source
     */
    public static void processSource(Context cx, String filename)
            throws IOException
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[185]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((filename.equals("-")) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[101]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[186]++;
            Scriptable scope = getShellScope();
            Charset cs;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[187]++;
            String charEnc = shellContextFactory.getCharacterEncoding();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[188]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((charEnc != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[103]++;
                cs = Charset.forName(charEnc);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[189]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[104]++;
                cs = Charset.defaultCharset();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[190]++;
            }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[191]++;
            ShellConsole console = global.getConsole(cs);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[192]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[105]++;
                // print implementation version
                console.println(cx.getImplementationVersion());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[193]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[106]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[194]++;

            int lineno = 1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[195]++;
            boolean hitEOF = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[196]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[7]++;


int CodeCoverConditionCoverageHelper_C45;
            while ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((hitEOF) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[7]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[8]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[9]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[197]++;
                String[] prompts = global.getPrompts(cx);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[198]++;
                String prompt = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[199]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[107]++;
                    prompt = prompts[0];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[200]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[108]++;}
                console.flush();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[201]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[202]++;
                String source = "";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[203]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[10]++;



                // Collect lines of source to compile.
                while (true) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[10]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[11]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[12]++;
}
                    String newline;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[204]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
                    try {
CodeCoverTryBranchHelper_Try8 = true;
                        newline = console.readLine(prompt);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[205]++;
                    }
                    catch (IOException ioe) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[110]++;
                        console.println(ioe.toString());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[206]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[207]++;
                        break;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[109]++;
}
  }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[208]++;
int CodeCoverConditionCoverageHelper_C48;
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((newline == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[111]++;
                        hitEOF = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[209]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[210]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[112]++;}
                    source = source + newline + "\n";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[211]++;
                    lineno++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[212]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[213]++;
int CodeCoverConditionCoverageHelper_C49;
                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((cx.stringIsCompilableUnit(source)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[113]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[214]++;
                        break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[114]++;}
                    prompt = prompts[1];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[215]++;
                }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[216]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
                try {
CodeCoverTryBranchHelper_Try9 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[217]++;
                    Script script = cx.compileString(source, "<stdin>", lineno, null);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[218]++;
int CodeCoverConditionCoverageHelper_C50;
                    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[116]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[219]++;
                        Object result = script.exec(cx, scope);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[220]++;
int CodeCoverConditionCoverageHelper_C51;
                        // Avoid printing out undefined or function definitions.
                        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (32)) == 0 || true) &&
 ((result != Context.getUndefinedValue()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((result instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((source.trim().startsWith("function")) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) && false))
                        {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[118]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[221]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
                            try {
CodeCoverTryBranchHelper_Try10 = true;
                                console.println(Context.toString(result));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[222]++;
                            } catch (RhinoException rex) {
CodeCoverTryBranchHelper_Try10 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[121]++;
                                ToolErrorReporter.reportException(
                                        cx.getErrorReporter(), rex);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[223]++;
                            } finally {
    if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[120]++;
}
  }

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[119]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[224]++;
                        NativeArray h = global.history;
                        h.put((int)h.getLength(), h, source);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[225]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[117]++;}
                } catch (RhinoException rex) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[122]++;
                    ToolErrorReporter.reportException(
                        cx.getErrorReporter(), rex);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[226]++;
                    exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[227]++;
                } catch (VirtualMachineError ex) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[123]++;
                    // Treat StackOverflow and OutOfMemory as runtime errors
                    ex.printStackTrace();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[228]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[229]++;
                    String msg = ToolErrorReporter.getMessage(
                        "msg.uncaughtJSException", ex.toString());
                    Context.reportError(msg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[230]++;
                    exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[231]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[115]++;
}
  }
            }
            console.println();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[232]++;
            console.flush();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[233]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[102]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[234]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((useRequire) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((filename.equals(mainModule)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[124]++;
            require.requireMain(cx, filename);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[235]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[125]++;
            processFile(cx, getScope(filename), filename);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[236]++;
        }
}
    }

    public static void processFileNoThrow(Context cx, Scriptable scope, String filename) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[237]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
        try {
CodeCoverTryBranchHelper_Try11 = true;
            processFile(cx, scope, filename);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[238]++;
        } catch (IOException ioex) {
CodeCoverTryBranchHelper_Try11 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[127]++;
            Context.reportError(ToolErrorReporter.getMessage(
                    "msg.couldnt.read.source", filename, ioex.getMessage()));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[239]++;
            exitCode = EXITCODE_FILE_NOT_FOUND;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[240]++;
        } catch (RhinoException rex) {
CodeCoverTryBranchHelper_Try11 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[128]++;
            ToolErrorReporter.reportException(
                    cx.getErrorReporter(), rex);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[241]++;
            exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[242]++;
        } catch (VirtualMachineError ex) {
CodeCoverTryBranchHelper_Try11 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[129]++;
            // Treat StackOverflow and OutOfMemory as runtime errors
            ex.printStackTrace();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[243]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[244]++;
            String msg = ToolErrorReporter.getMessage(
                    "msg.uncaughtJSException", ex.toString());
            Context.reportError(msg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[245]++;
            exitCode = EXITCODE_RUNTIME_ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[246]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[126]++;
}
  }
    }

    public static void processFile(Context cx, Scriptable scope, String filename)
            throws IOException
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[247]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((securityImpl == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[130]++;
            processFileSecure(cx, scope, filename, null);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[248]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[131]++;
            securityImpl.callProcessFileSecure(cx, scope, filename);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[249]++;
        }
    }

    static void processFileSecure(Context cx, Scriptable scope,
                                  String path, Object securityDomain)
            throws IOException {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[250]++;

        boolean isClass = path.endsWith(".class");
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[251]++;
        Object source = readFileOrUrl(path, !isClass);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[252]++;

        byte[] digest = getDigest(source);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[253]++;
        String key = path + "_" + cx.getOptimizationLevel();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[254]++;
        ScriptReference ref = scriptCache.get(key, digest);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[255]++;
        Script script = ref != null ? ref.get() : null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[256]++;
int CodeCoverConditionCoverageHelper_C54;

        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((script == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[132]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[257]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((isClass) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[134]++;
                script = loadCompiledScript(cx, path, (byte[])source, securityDomain);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[258]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[135]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[259]++;
                String strSrc = (String) source;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[260]++;
int CodeCoverConditionCoverageHelper_C56;
                // Support the executable script #! syntax:  If
                // the first line begins with a '#', treat the whole
                // line as a comment.
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((strSrc.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((strSrc.charAt(0) == '#') && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[136]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[261]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[13]++;


int CodeCoverConditionCoverageHelper_C57;
                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i != strSrc.length()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[13]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[14]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[15]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[262]++;
                        int c = strSrc.charAt(i);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[263]++;
int CodeCoverConditionCoverageHelper_C58;
                        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[138]++;
                            strSrc = strSrc.substring(i);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[264]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[265]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[139]++;}
                    }

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[137]++;}
                script = cx.compileString(strSrc, path, 1, securityDomain);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[266]++;
            }
            scriptCache.put(key, digest, script);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[267]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[133]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[268]++;
int CodeCoverConditionCoverageHelper_C59;

        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[140]++;
            script.exec(cx, scope);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[269]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[141]++;}
    }

    private static byte[] getDigest(Object source) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[270]++;
        byte[] bytes, digest = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[271]++;
int CodeCoverConditionCoverageHelper_C60;

        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((source != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[142]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[272]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((source instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[144]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[273]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
                try {
CodeCoverTryBranchHelper_Try12 = true;
                    bytes = ((String)source).getBytes("UTF-8");
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[274]++;
                } catch (UnsupportedEncodingException ue) {
CodeCoverTryBranchHelper_Try12 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[147]++;
                    bytes = ((String)source).getBytes();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[275]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[146]++;
}
  }

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[145]++;
                bytes = (byte[])source;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[276]++;
            }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[277]++;
boolean CodeCoverTryBranchHelper_Try13 = false;
            try {
CodeCoverTryBranchHelper_Try13 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[278]++;
                MessageDigest md = MessageDigest.getInstance("MD5");
                digest = md.digest(bytes);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[279]++;
            } catch (NoSuchAlgorithmException nsa) {
CodeCoverTryBranchHelper_Try13 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[149]++;
                // Should not happen
                throw new RuntimeException(nsa);
            } finally {
    if ( CodeCoverTryBranchHelper_Try13 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[148]++;
}
  }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[143]++;}

        return digest;
    }

    private static Script loadCompiledScript(Context cx, String path,
                                             byte[] data, Object securityDomain)
            throws FileNotFoundException
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[280]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[150]++;
            throw new FileNotFoundException(path);

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[151]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[281]++;
        // XXX: For now extract class name of compiled Script from path
        // instead of parsing class bytes
        int nameStart = path.lastIndexOf('/');
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[282]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((nameStart < 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[152]++;
            nameStart = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[283]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[153]++;
            ++nameStart;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[284]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[285]++;
        int nameEnd = path.lastIndexOf('.');
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[286]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((nameEnd < nameStart) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[154]++;
            // '.' does not exist in path (nameEnd < 0)
            // or it comes before nameStart
            nameEnd = path.length();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[287]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[155]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[288]++;
        String name = path.substring(nameStart, nameEnd);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[289]++;
boolean CodeCoverTryBranchHelper_Try14 = false;
        try {
CodeCoverTryBranchHelper_Try14 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[290]++;
            GeneratedClassLoader loader = SecurityController.createLoader(cx.getApplicationClassLoader(), securityDomain);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[291]++;
            Class<?> clazz = loader.defineClass(name, data);
            loader.linkClass(clazz);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[292]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[293]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((Script.class.isAssignableFrom(clazz)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[157]++;
                throw Context.reportRuntimeError("msg.must.implement.Script");

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[158]++;}
            return (Script) clazz.newInstance();
        } catch (IllegalAccessException iaex) {
CodeCoverTryBranchHelper_Try14 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[159]++;
            Context.reportError(iaex.toString());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[294]++;
            throw new RuntimeException(iaex);
        } catch (InstantiationException inex) {
CodeCoverTryBranchHelper_Try14 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[160]++;
            Context.reportError(inex.toString());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[295]++;
            throw new RuntimeException(inex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try14 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[156]++;
}
  }
    }

    public static InputStream getIn() {
        return getGlobal().getIn();
    }

    public static void setIn(InputStream in) {
        getGlobal().setIn(in);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[296]++;
    }

    public static PrintStream getOut() {
        return getGlobal().getOut();
    }

    public static void setOut(PrintStream out) {
        getGlobal().setOut(out);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[297]++;
    }

    public static PrintStream getErr() {
        return getGlobal().getErr();
    }

    public static void setErr(PrintStream err) {
        getGlobal().setErr(err);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[298]++;
    }

    /**
     * Read file or url specified by <tt>path</tt>.
     * @return file or url content as <tt>byte[]</tt> or as <tt>String</tt> if
     * <tt>convertToString</tt> is true.
     */
    private static Object readFileOrUrl(String path, boolean convertToString)
            throws IOException
    {
        return SourceReader.readFileOrUrl(path, convertToString,
                shellContextFactory.getCharacterEncoding());
    }

    static class ScriptReference extends SoftReference<Script> {
        String path;
        byte[] digest;

        ScriptReference(String path, byte[] digest,
                        Script script, ReferenceQueue<Script> queue) {
            super(script, queue);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[299]++;
            this.path = path;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[300]++;
            this.digest = digest;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[301]++;
        }
    }

    static class ScriptCache extends LinkedHashMap<String, ScriptReference> {
        ReferenceQueue<Script> queue;
        int capacity;

        ScriptCache(int capacity) {
            super(capacity + 1, 2f, true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[302]++;
            this.capacity = capacity;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[303]++;
            queue = new ReferenceQueue<Script>();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[304]++;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, ScriptReference> eldest) {
            return size() > capacity;
        }

        ScriptReference get(String path, byte[] digest) {
            ScriptReference ref;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[305]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[16]++;


            while((ref = (ScriptReference) queue.poll()) != null) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[16]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[17]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[18]++;
}
                remove(ref.path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[306]++;
            }
            ref = get(path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[307]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[308]++;
int CodeCoverConditionCoverageHelper_C67;
            if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((ref != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((Arrays.equals(digest, ref.digest)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[161]++;
                remove(ref.path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[309]++;
                ref = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[310]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[162]++;}
            return ref;
        }

        void put(String path, byte[] digest, Script script) {
            put(path, new ScriptReference(path, digest, script, queue));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[311]++;
        }

    }
}

class CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x ());
  }
    public static long[] statements = new long[312];
    public static long[] branches = new long[163];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[68];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-Main.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,0,1,1,1,3,2,1,1,1,2,1,2,1,1,1,1,1,1,1,0,2};
    for (int i = 1; i <= 67; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-Main.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 311; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 162; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 67; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-Main.java");
      for (int i = 1; i <= 311; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 162; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 67; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

