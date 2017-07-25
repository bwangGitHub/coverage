/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.List;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 *
 */
public abstract class ShellConsole {
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.ping();
  }


    private final static Class[] NO_ARG = {};
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[1]++;
  }
    private final static Class[] BOOLEAN_ARG = {Boolean.TYPE};
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[2]++;
  }
    private final static Class[] STRING_ARG = {String.class};
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[3]++;
  }
    private final static Class[] CHARSEQ_ARG = {CharSequence.class};
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[4]++;
  }

    protected ShellConsole() {
    }

    /**
     * Returns the underlying {@link InputStream}
     */
    public abstract InputStream getIn();

    /**
     * Reads a single line from the console
     */
    public abstract String readLine() throws IOException;

    /**
     * Reads a single line from the console and sets the console's prompt to
     * {@code prompt}
     */
    public abstract String readLine(String prompt) throws IOException;

    /**
     * Flushes the console's output
     */
    public abstract void flush() throws IOException;

    /**
     * Prints a single string to the console
     */
    public abstract void print(String s) throws IOException;

    /**
     * Prints the newline character-sequence to the console
     */
    public abstract void println() throws IOException;

    /**
     * Prints a string and the newline character-sequence to the console
     */
    public abstract void println(String s) throws IOException;


    private static Object tryInvoke(Object obj, String method,
                                    Class[] paramTypes, Object... args) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[5]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[6]++;
            Method m = obj.getClass().getDeclaredMethod(method, paramTypes);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[2]++;
                return m.invoke(obj, args);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[3]++;}
        } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[4]++;
        } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[5]++;
        } catch (IllegalAccessException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[6]++;
        } catch (InvocationTargetException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[7]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[1]++;
}
  }
        return null;
    }

    /**
     * {@link ShellConsole} implementation for JLine v1
     */
    private static class JLineShellConsoleV1 extends ShellConsole {
        private final Object reader;
        private final InputStream in;

        JLineShellConsoleV1(Object reader, Charset cs) {
            this.reader = reader;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[8]++;
            this.in = new ConsoleInputStream(this, cs);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[9]++;
        }

        @Override
        public InputStream getIn() {
            return in;
        }

        @Override
        public String readLine() throws IOException {
            return (String) tryInvoke(reader, "readLine", NO_ARG);
        }

        @Override
        public String readLine(String prompt) throws IOException {
            return (String) tryInvoke(reader, "readLine", STRING_ARG, prompt);
        }

        @Override
        public void flush() throws IOException {
            tryInvoke(reader, "flushConsole", NO_ARG);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[10]++;
        }

        @Override
        public void print(String s) throws IOException {
            tryInvoke(reader, "printString", STRING_ARG, s);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[11]++;
        }

        @Override
        public void println() throws IOException {
            tryInvoke(reader, "printNewline", NO_ARG);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[12]++;
        }

        @Override
        public void println(String s) throws IOException {
            tryInvoke(reader, "printString", STRING_ARG, s);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[13]++;
            tryInvoke(reader, "printNewline", NO_ARG);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[14]++;
        }
    }

    /**
     * {@link ShellConsole} implementation for JLine v2
     */
    private static class JLineShellConsoleV2 extends ShellConsole {
        private final Object reader;
        private final InputStream in;

        JLineShellConsoleV2(Object reader, Charset cs) {
            this.reader = reader;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[15]++;
            this.in = new ConsoleInputStream(this, cs);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[16]++;
        }

        @Override
        public InputStream getIn() {
            return in;
        }

        @Override
        public String readLine() throws IOException {
            return (String) tryInvoke(reader, "readLine", NO_ARG);
        }

        @Override
        public String readLine(String prompt) throws IOException {
            return (String) tryInvoke(reader, "readLine", STRING_ARG, prompt);
        }

        @Override
        public void flush() throws IOException {
            tryInvoke(reader, "flush", NO_ARG);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[17]++;
        }

        @Override
        public void print(String s) throws IOException {
            tryInvoke(reader, "print", CHARSEQ_ARG, s);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[18]++;
        }

        @Override
        public void println() throws IOException {
            tryInvoke(reader, "println", NO_ARG);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[19]++;
        }

        @Override
        public void println(String s) throws IOException {
            tryInvoke(reader, "println", CHARSEQ_ARG, s);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[20]++;
        }
    }

    /**
     * JLine's ConsoleReaderInputStream is no longer public, therefore we need
     * to use our own implementation
     */
    private static class ConsoleInputStream extends InputStream {
        private static final byte[] EMPTY = new byte[] {};
        private final ShellConsole console;
        private final Charset cs;
        private byte[] buffer = EMPTY;
  {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[21]++;
  }
        private int cursor = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[22]++;
  }
        private boolean atEOF = false;
  {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[23]++;
  }

        public ConsoleInputStream(ShellConsole console, Charset cs) {
            this.console = console;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[24]++;
            this.cs = cs;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[25]++;
        }

        @Override
        public synchronized int read(byte[] b, int off, int len)
                throws IOException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[8]++;
                throw new NullPointerException();

            } else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[9]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[27]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((off < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((len < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((len > b.length - off) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[10]++;
                throw new IndexOutOfBoundsException();

            } else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[11]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[28]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((len == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[12]++;
                return 0;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[13]++;}
}
}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ensureInput()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[14]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[15]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[30]++;
            int n = Math.min(len, buffer.length - cursor);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[3]++;
}
                b[off + i] = buffer[cursor + i];
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[32]++;
            }
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n < len) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[16]++;
                b[off + n++] = '\n';
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[34]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[17]++;}
            cursor += n;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[35]++;
            return n;
        }

        @Override
        public synchronized int read() throws IOException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ensureInput()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[18]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[19]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((cursor == buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[20]++;
                cursor++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[38]++;
                return '\n';

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[21]++;}
            return buffer[cursor++];
        }

        private boolean ensureInput() throws IOException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((atEOF) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[22]++;
                return false;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[23]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((cursor < 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cursor > buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[24]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((readNextLine() == -1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[26]++;
                    atEOF = true;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[42]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[27]++;}
                cursor = 0;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[43]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[25]++;}
            return true;
        }

        private int readNextLine() throws IOException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[44]++;
            String line = console.readLine(null);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[45]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((line != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[28]++;
                buffer = line.getBytes(cs);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[46]++;
                return buffer.length;

            } else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[29]++;
                buffer = EMPTY;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[47]++;
                return -1;
            }
        }
    }

    private static class SimpleShellConsole extends ShellConsole {
        private final InputStream in;
        private final PrintWriter out;
        private final BufferedReader reader;

        SimpleShellConsole(InputStream in, PrintStream ps, Charset cs) {
            this.in = in;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[48]++;
            this.out = new PrintWriter(ps);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[49]++;
            this.reader = new BufferedReader(new InputStreamReader(in, cs));
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[50]++;
        }

        @Override
        public InputStream getIn() {
            return in;
        }

        @Override
        public String readLine() throws IOException {
            return reader.readLine();
        }

        @Override
        public String readLine(String prompt) throws IOException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((prompt != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[30]++;
                out.write(prompt);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[52]++;
                out.flush();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[53]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[31]++;}
            return reader.readLine();
        }

        @Override
        public void flush() throws IOException {
            out.flush();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[54]++;
        }

        @Override
        public void print(String s) throws IOException {
            out.print(s);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[55]++;
        }

        @Override
        public void println() throws IOException {
            out.println();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[56]++;
        }

        @Override
        public void println(String s) throws IOException {
            out.println(s);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[57]++;
        }
    }

    /**
     * Returns a new {@link ShellConsole} which uses the supplied
     * {@link InputStream} and {@link PrintStream} for its input/output
     */
    public static ShellConsole getConsole(InputStream in, PrintStream ps,
            Charset cs) {
        return new SimpleShellConsole(in, ps, cs);
    }

    /**
     * Provides a specialized {@link ShellConsole} to handle line editing,
     * history and completion. Relies on the JLine library (see
     * <http://jline.sourceforge.net>).
     */
    public static ShellConsole getConsole(Scriptable scope, Charset cs) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[58]++;
        // We don't want a compile-time dependency on the JLine jar, so use
        // reflection to load and reference the JLine classes.
        ClassLoader classLoader = ShellConsole.class.getClassLoader();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((classLoader == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[32]++;
            // If the attempt to get a class specific class loader above failed
            // then fallback to the system class loader.
            classLoader = ClassLoader.getSystemClassLoader();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[60]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[33]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((classLoader == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[34]++;
            // If for some reason we still don't have a handle to a class
            // loader then give up (avoid a NullPointerException).
            return null;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[35]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[62]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[63]++;
            // first try to load JLine v2...
            Class<?> readerClass = Kit.classOrNull(classLoader,
                    "jline.console.ConsoleReader");
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[64]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((readerClass != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[37]++;
                return getJLineShellConsoleV2(classLoader, readerClass, scope, cs);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[38]++;}
            // ...if that fails, try to load JLine v1
            readerClass = Kit.classOrNull(classLoader, "jline.ConsoleReader");
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[65]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[66]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((readerClass != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[39]++;
                return getJLineShellConsoleV1(classLoader, readerClass, scope, cs);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[40]++;}
        } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[41]++;
        } catch (IllegalAccessException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[42]++;
        } catch (InstantiationException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[43]++;
        } catch (InvocationTargetException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[44]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[36]++;
}
  }
        return null;
    }

    private static JLineShellConsoleV1 getJLineShellConsoleV1(
            ClassLoader classLoader, Class<?> readerClass, Scriptable scope,
            Charset cs) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[67]++;
        // ConsoleReader reader = new ConsoleReader();
        Constructor<?> c = readerClass.getConstructor();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[68]++;
        Object reader = c.newInstance();

        // reader.setBellEnabled(false);
        tryInvoke(reader, "setBellEnabled", BOOLEAN_ARG, Boolean.FALSE);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[69]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[70]++;

        // reader.addCompletor(new FlexibleCompletor(prefixes));
        Class<?> completorClass = Kit.classOrNull(classLoader,
                "jline.Completor");
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[71]++;
        Object completor = Proxy.newProxyInstance(classLoader,
                new Class[] { completorClass },
                new FlexibleCompletor(completorClass, scope));
        tryInvoke(reader, "addCompletor", new Class[] {completorClass}, completor);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[72]++;

        return new JLineShellConsoleV1(reader, cs);
    }

    private static JLineShellConsoleV2 getJLineShellConsoleV2(
            ClassLoader classLoader, Class<?> readerClass, Scriptable scope,
            Charset cs) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[73]++;
        // ConsoleReader reader = new ConsoleReader();
        Constructor<?> c = readerClass.getConstructor();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[74]++;
        Object reader = c.newInstance();

        // reader.setBellEnabled(false);
        tryInvoke(reader, "setBellEnabled", BOOLEAN_ARG, Boolean.FALSE);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[75]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[76]++;

        // reader.addCompleter(new FlexibleCompletor(prefixes));
        Class<?> completorClass = Kit.classOrNull(classLoader,
                "jline.console.completer.Completer");
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[77]++;
        Object completor = Proxy.newProxyInstance(classLoader,
                new Class[] { completorClass },
                new FlexibleCompletor(completorClass, scope));
        tryInvoke(reader, "addCompleter", new Class[] {completorClass}, completor);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[78]++;

        return new JLineShellConsoleV2(reader, cs);
    }
}

/**
* The completors provided with JLine are pretty uptight, they only
* complete on a line that it can fully recognize (only composed of
* completed strings). This one completes whatever came before.
*/
class FlexibleCompletor implements java.lang.reflect.InvocationHandler {
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.ping();
  }

    private Method completeMethod;
    private Scriptable global;

    FlexibleCompletor(Class<?> completorClass, Scriptable global)
        throws NoSuchMethodException
    {
        this.global = global;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[79]++;
        this.completeMethod = completorClass.getMethod("complete", String.class,
                Integer.TYPE, List.class);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[80]++;
    }

    @SuppressWarnings({"unchecked"})
    public Object invoke(Object proxy, Method method, Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((method.equals(this.completeMethod)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[45]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[82]++;
            int result = complete((String)args[0], ((Integer) args[1]).intValue(),
                    (List<String>) args[2]);
            return Integer.valueOf(result);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[46]++;}
        throw new NoSuchMethodError(method.toString());
    }

    public int complete(String buffer, int cursor, List<String> candidates) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[83]++;
        // Starting from "cursor" at the end of the buffer, look backward
        // and collect a list of identifiers separated by (possibly zero)
        // dots. Then look up each identifier in turn until getting to the
        // last, presumably incomplete fragment. Then enumerate all the
        // properties of the last object and find any that have the
        // fragment as a prefix and return those for autocompletion.
        int m = cursor - 1;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[84]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
        while ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((m >= 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[6]++;
}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[85]++;
            char c = buffer.charAt(m);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[86]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(c)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((c != '.') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[47]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[87]++;
                break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[48]++;}
            m--;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[88]++;
        }
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[89]++;
        String namesAndDots = buffer.substring(m+1, cursor);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[90]++;
        String[] names = namesAndDots.split("\\.", -1);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[91]++;
        Scriptable obj = this.global;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[92]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < names.length - 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[9]++;
}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[93]++;
            Object val = obj.get(names[i], global);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[94]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[49]++;
                obj = (Scriptable) val;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[95]++;
}
            else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[50]++;
                return buffer.length(); // no matches
            }
        }
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[96]++;
        Object[] ids = (obj instanceof ScriptableObject)
                       ? ((ScriptableObject)obj).getAllIds()
                       : obj.getIds();
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[97]++;
        String lastPart = names[names.length-1];
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[98]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < ids.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[10]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[11]--;
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.loops[12]++;
}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[99]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((ids[i] instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[51]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[100]++;
                continue;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[52]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[101]++;
            String id = (String)ids[i];
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[102]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((id.startsWith(lastPart)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[53]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[103]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj.get(id, obj) instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[55]++;
                    id += "(";
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[104]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[56]++;}
                candidates.add(id);
CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.statements[105]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p.branches[54]++;}
        }
        return buffer.length() - lastPart.length();
    }
}

class CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p ());
  }
    public static long[] statements = new long[106];
    public static long[] branches = new long[57];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-ShellConsole.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$59ffmx2g7ljqr128adynmsr9vyek9lzisfpccsh36p () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-ShellConsole.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 105; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 56; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-ShellConsole.java");
      for (int i = 1; i <= 105; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 56; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

