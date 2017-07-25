/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.ShellConsole;

/**
 * Provides a specialized input stream for consoles to handle line
 * editing, history and completion. Relies on the JLine library (see
 * <http://jline.sourceforge.net>).
 */
@Deprecated
public class ShellLine {
  static {
    CodeCoverCoverageCounter$iypomt0ag81t84rk6kkevk499excw0rwp7srl.ping();
  }

    @Deprecated
    public static InputStream getStream(Scriptable scope) {
CodeCoverCoverageCounter$iypomt0ag81t84rk6kkevk499excw0rwp7srl.statements[1]++;
        ShellConsole console = ShellConsole.getConsole(scope,
                Charset.defaultCharset());
        return (console != null ? console.getIn() : null);
    }
}

class CodeCoverCoverageCounter$iypomt0ag81t84rk6kkevk499excw0rwp7srl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag81t84rk6kkevk499excw0rwp7srl ());
  }
    public static long[] statements = new long[2];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iypomt0ag81t84rk6kkevk499excw0rwp7srl () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-ShellLine.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1; i++) {
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
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-ShellLine.java");
      for (int i = 1; i <= 1; i++) {
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

