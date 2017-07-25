/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;

import java.net.URI;

/**
 * A top-level module scope. This class provides methods to retrieve the
 * module's source and base URIs in order to resolve relative module IDs
 * and check sandbox constraints.
 */
public class ModuleScope extends TopLevel {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d.ping();
  }


    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d.statements[1]++;
  }

    private final URI uri;
    private final URI base;

    public ModuleScope(Scriptable prototype, URI uri, URI base) {
        this.uri = uri;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d.statements[2]++;
        this.base = base;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d.statements[3]++;
        setPrototype(prototype);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d.statements[4]++;
        cacheBuiltins();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d.statements[5]++;
    }

    public URI getUri() {
        return uri;
    }

    public URI getBase() {
        return base;
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9id3fyu5t7i1i2c3os5thk9rn1d () {
    super("org.mozilla.javascript.commonjs.module.RHINO-SRC-ModuleScope.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.RHINO-SRC-ModuleScope.java");
      for (int i = 1; i <= 5; i++) {
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

