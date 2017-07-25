/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.jsc;

import java.io.*;
import java.util.*;
import org.mozilla.javascript.*;
import org.mozilla.javascript.optimizer.ClassCompiler;
import org.mozilla.javascript.tools.SourceReader;
import org.mozilla.javascript.tools.ToolErrorReporter;

/**
 */
public class Main {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.ping();
  }


    /**
     * Main entry point.
     *
     * Process arguments as would a normal Java program.
     * Then set up the execution environment and begin to
     * compile scripts.
     */
    public static void main(String args[])
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[1]++;
        Main main = new Main();
        args = main.processOptions(args);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[2]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[1]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((main.printHelp) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[3]++;
                System.out.println(ToolErrorReporter.getMessage(
                    "msg.jsc.usage", Main.class.getName()));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[5]++;
                System.exit(0);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[6]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[4]++;}
            System.exit(1);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[7]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[2]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((main.reporter.hasReportedError()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[5]++;
            main.processSource(args);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[9]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[6]++;}
    }

    public Main()
    {
        reporter = new ToolErrorReporter(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[10]++;
        compilerEnv = new CompilerEnvirons();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[11]++;
        compilerEnv.setErrorReporter(reporter);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[12]++;
        compiler = new ClassCompiler(compilerEnv);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[13]++;
    }

    /**
     * Parse arguments.
     *
     */
    public String[] processOptions(String args[])
    {
        targetPackage = "";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[14]++;        // default to no package
        compilerEnv.setGenerateDebugInfo(false);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[15]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;   // default to no symbols
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[1]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[2]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[3]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[17]++;
            String arg = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((arg.startsWith("-")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[7]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[19]++;
                int tail = args.length - i;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((targetName != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((tail > 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[9]++;
                    addError("msg.multiple.js.to.file", targetName);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[21]++;
                    return null;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[10]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[22]++;
                String[] result = new String[tail];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((j != tail) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[4]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[5]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[6]++;
}
                    result[j] = args[i + j];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[24]++;
                }
                return result;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[8]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((arg.equals("-help")) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((arg.equals("-h")) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((arg.equals("--help")) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false))
            {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[11]++;
                printHelp = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[26]++;
                return null;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[12]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[27]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

            try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((arg.equals("-version")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[14]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[29]++;
                    int version = Integer.parseInt(args[i]);
                    compilerEnv.setLanguageVersion(version);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[30]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[31]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[15]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((arg.equals("-opt")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((arg.equals("-O")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false))
                {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[16]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[33]++;
                    int optLevel = Integer.parseInt(args[i]);
                    compilerEnv.setOptimizationLevel(optLevel);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[34]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[35]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[17]++;}
            }
            catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[18]++;
                badUsage(args[i]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[36]++;
                return null;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[13]++;
}
  }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((arg.equals("-nosource")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[19]++;
                compilerEnv.setGeneratingSource(false);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[38]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[39]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[20]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[40]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((arg.equals("-debug")) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((arg.equals("-g")) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[21]++;
                compilerEnv.setGenerateDebugInfo(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[41]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[42]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[22]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((arg.equals("-main-method-class")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[23]++;
                compiler.setMainMethodClass(args[i]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[44]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[45]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[24]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[46]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((arg.equals("-encoding")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[25]++;
                characterEncoding = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[47]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[48]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[26]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[49]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((arg.equals("-o")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[27]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[50]++;
                String name = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[51]++;
                int end = name.length();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((end == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(name.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false))
                {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[29]++;
                    addError("msg.invalid.classfile.name", name);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[53]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[54]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[30]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[55]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[7]++;


int CodeCoverConditionCoverageHelper_C17;
                for (int j = 1;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((j < end) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[7]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[8]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[9]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[56]++;
                    char c = name.charAt(j);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;
                    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(c)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[31]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[58]++;
int CodeCoverConditionCoverageHelper_C19;
                        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[33]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[59]++;
int CodeCoverConditionCoverageHelper_C20;
                            // check if it is the dot in .class
                            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((j == end - 6) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((name.endsWith(".class")) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[35]++;
                                name = name.substring(0, j);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[60]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[61]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[36]++;}

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[34]++;}
                        addError("msg.invalid.classfile.name", name);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[62]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[63]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[32]++;}
                }
                targetName = name;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[64]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[65]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[28]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[66]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((arg.equals("-observe-instruction-count")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[37]++;
                compilerEnv.setGenerateObserverCount(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[67]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[38]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[68]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((arg.equals("-package")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[39]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[69]++;
                String pkg = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[70]++;
                int end = pkg.length();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[71]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((j != end) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[10]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[11]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[12]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[72]++;
                    char c = pkg.charAt(j);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[73]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(c)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[41]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[74]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[13]++;


int CodeCoverConditionCoverageHelper_C25;
                        for (++j;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((j != end) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[13]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[14]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[15]++;
}
                            c = pkg.charAt(j);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[75]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[76]++;
int CodeCoverConditionCoverageHelper_C26;
                            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(c)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[43]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[77]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[44]++;}
                        }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[78]++;
int CodeCoverConditionCoverageHelper_C27;
                        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((j == end) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[45]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[79]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[46]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[80]++;
int CodeCoverConditionCoverageHelper_C28;
                        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((j != end - 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[47]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[81]++;
                            continue;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[48]++;}

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[42]++;}
                    addError("msg.package.name", targetPackage);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[82]++;
                    return null;
                }
                targetPackage = pkg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[83]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[84]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[40]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[85]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((arg.equals("-extends")) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[49]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[86]++;
                String targetExtends = args[i];
                Class<?> superClass;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[87]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    superClass = Class.forName(targetExtends);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[88]++;
                } catch (ClassNotFoundException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[52]++;
                    throw new Error(e.toString()); // TODO: better error
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[51]++;
}
  }
                compiler.setTargetExtends(superClass);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[89]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[90]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[50]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[91]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((arg.equals("-implements")) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[53]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[92]++;
                // TODO: allow for multiple comma-separated interfaces.
                String targetImplements = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[93]++;
                StringTokenizer st = new StringTokenizer(targetImplements,
                                                         ",");
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[94]++;
                List<Class<?>> list = new ArrayList<Class<?>>();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[95]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[16]++;


int CodeCoverConditionCoverageHelper_C31;
                while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[16]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[17]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[18]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[96]++;
                    String className = st.nextToken();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[97]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
                    try {
CodeCoverTryBranchHelper_Try3 = true;
                        list.add(Class.forName(className));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[98]++;
                    } catch (ClassNotFoundException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[56]++;
                        throw new Error(e.toString()); // TODO: better error
                    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[55]++;
}
  }
                }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[99]++;
                Class<?>[] implementsClasses = list.toArray(new Class<?>[list.size()]);
                compiler.setTargetImplements(implementsClasses);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[100]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[101]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[54]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[102]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((arg.equals("-d")) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((++i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[57]++;
                destinationDir = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[103]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[104]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[58]++;}
            badUsage(arg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[105]++;
            return null;
        }
        // no file name
        p(ToolErrorReporter.getMessage("msg.no.file"));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[106]++;
        return null;
    }
    /**
     * Print a usage message.
     */
    private static void badUsage(String s) {
        System.err.println(ToolErrorReporter.getMessage(
            "msg.jsc.bad.usage", Main.class.getName(), s));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[107]++;
    }

    /**
     * Compile JavaScript source.
     *
     */
    public void processSource(String[] filenames)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[108]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[19]++;


int CodeCoverConditionCoverageHelper_C33;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((i != filenames.length) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[19]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[20]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[21]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[109]++;
            String filename = filenames[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[110]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((filename.endsWith(".js")) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[59]++;
                addError("msg.extension.not.js", filename);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[111]++;
                return;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[60]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[112]++;
            File f = new File(filename);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[113]++;
            String source = readSource(f);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[114]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[61]++; return;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[62]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[115]++;

            String mainClassName = targetName;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[116]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((mainClassName == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[63]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[117]++;
                String name = f.getName();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[118]++;
                String nojs = name.substring(0, name.length() - 3);
                mainClassName = getClassName(nojs);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[119]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[64]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[120]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((targetPackage.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[65]++;
                mainClassName = targetPackage+"."+mainClassName;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[121]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[66]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[122]++;

            Object[] compiled
                = compiler.compileToClassFiles(source, filename, 1,
                                               mainClassName);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[123]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((compiled == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((compiled.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[67]++;
                return;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[68]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[124]++;

            File targetTopDir = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[125]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((destinationDir != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[69]++;
                targetTopDir = new File(destinationDir);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[126]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[70]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[127]++;
                String parent = f.getParent();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[128]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[71]++;
                    targetTopDir = new File(parent);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[129]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[72]++;}
            }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[130]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[22]++;


int CodeCoverConditionCoverageHelper_C41;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((j != compiled.length) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); j += 2) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[22]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[23]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[24]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[131]++;
                String className = (String)compiled[j];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[132]++;
                byte[] bytes = (byte[])compiled[j + 1];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[133]++;
                File outfile = getOutputFile(targetTopDir, className);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[134]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[135]++;
                    FileOutputStream os = new FileOutputStream(outfile);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[136]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                    try {
CodeCoverTryBranchHelper_Try5 = true;
                        os.write(bytes);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[137]++;
                    } finally {
if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[74]++;
}
                        os.close();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[138]++;
                    }
                } catch (IOException ioe) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[75]++;
                    addFormatedError(ioe.toString());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[139]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[73]++;
}
  }
            }
        }
    }

    private String readSource(File f)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[140]++;
        String absPath = f.getAbsolutePath();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[141]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((f.isFile()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[76]++;
            addError("msg.jsfile.not.found", absPath);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[142]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[77]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[143]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
        try {
CodeCoverTryBranchHelper_Try6 = true;
            return (String)SourceReader.readFileOrUrl(absPath, true,
                    characterEncoding);
        } catch (FileNotFoundException ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[79]++;
            addError("msg.couldnt.open", absPath);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[144]++;
        } catch (IOException ioe) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[80]++;
            addFormatedError(ioe.toString());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[145]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[78]++;
}
  }
        return null;
    }

    private File getOutputFile(File parentDir, String className)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[146]++;
        String path = className.replace('.', File.separatorChar);
        path = path.concat(".class");
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[147]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[148]++;
        File f = new File(parentDir, path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[149]++;
        String dirPath = f.getParent();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[150]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((dirPath != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[81]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[151]++;
            File dir = new File(dirPath);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[152]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((dir.exists()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[83]++;
                dir.mkdirs();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[153]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[84]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[82]++;}
        return f;
    }

    /**
     * Verify that class file names are legal Java identifiers.  Substitute
     * illegal characters with underscores, and prepend the name with an
     * underscore if the file name does not begin with a JavaLetter.
     */

    String getClassName(String name) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[154]++;
        char[] s = new char[name.length()+1];
        char c;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[155]++;
        int j = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[156]++;
int CodeCoverConditionCoverageHelper_C45;

        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(name.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[85]++;
            s[j++] = '_';
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[157]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[86]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[158]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[25]++;


int CodeCoverConditionCoverageHelper_C46;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i < name.length()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); i++, j++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[25]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[26]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[27]++;
}
            c = name.charAt(i);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[159]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[160]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(c)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false) ) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[87]++;
                s[j] = c;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[161]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[88]++;
                s[j] = '_';
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[162]++;
            }
        }
        return (new String(s)).trim();
     }

    private static void p(String s) {
        System.out.println(s);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[163]++;
    }

    private void addError(String messageId, String arg)
    {
        String msg;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[164]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((arg == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[89]++;
            msg = ToolErrorReporter.getMessage(messageId);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[165]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[90]++;
            msg = ToolErrorReporter.getMessage(messageId, arg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[166]++;
        }
        addFormatedError(msg);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[167]++;
    }

    private void addFormatedError(String message)
    {
        reporter.error(message, null, -1, null, -1);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[168]++;
    }

    private boolean printHelp;
    private ToolErrorReporter reporter;
    private CompilerEnvirons compilerEnv;
    private ClassCompiler compiler;
    private String targetName;
    private String targetPackage;
    private String destinationDir;
    private String characterEncoding;
}

class CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x ());
  }
    public static long[] statements = new long[169];
    public static long[] branches = new long[91];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[49];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.jsc.RHINO-TOO-Main.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,3,2,3,1,2,2,2,2,2,1,1,1,2,1,2,1,1,1,1,1,2,2,2,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 48; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x () {
    super("org.mozilla.javascript.tools.jsc.RHINO-TOO-Main.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 168; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 90; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.jsc.RHINO-TOO-Main.java");
      for (int i = 1; i <= 168; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 90; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

