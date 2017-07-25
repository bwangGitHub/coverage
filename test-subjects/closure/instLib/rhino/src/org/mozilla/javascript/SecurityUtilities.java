/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 */
public class SecurityUtilities
{
  static {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.ping();
  }

    /**
     * Retrieves a system property within a privileged block. Use it only when
     * the property is used from within Rhino code and is not passed out of it.
     * @param name the name of the system property
     * @return the value of the system property
     */
    public static String getSystemProperty(final String name)
    {
        return AccessController.doPrivileged(
            new PrivilegedAction<String>()
            {
                public String run()
                {
                    return System.getProperty(name);
                }
            });
    }

    public static ProtectionDomain getProtectionDomain(final Class<?> clazz)
    {
        return AccessController.doPrivileged(
                new PrivilegedAction<ProtectionDomain>()
                {
                    public ProtectionDomain run()
                    {
                        return clazz.getProtectionDomain();
                    }
                });
    }

    /**
     * Look up the top-most element in the current stack representing a
     * script and return its protection domain. This relies on the system-wide
     * SecurityManager being an instance of {@link RhinoSecurityManager},
     * otherwise it returns <code>null</code>.
     * @return The protection of the top-most script in the current stack, or null
     */
    public static ProtectionDomain getScriptProtectionDomain() {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.statements[1]++;
        final SecurityManager securityManager = System.getSecurityManager();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((securityManager instanceof RhinoSecurityManager) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.branches[1]++;
            return AccessController.doPrivileged(
                new PrivilegedAction<ProtectionDomain>() {
                    public ProtectionDomain run() {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.statements[3]++;
                        Class c = ((RhinoSecurityManager) securityManager)
                                    .getCurrentScriptClass();
                        return c == null ? null : c.getProtectionDomain();
                    }
                }
            );

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl.branches[2]++;}
        return null;
    }
}

class CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-SecurityUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$21tu3emdl0l6sfnhlzbc1yr2ld1nqcopjqbcpz4ays4jxmr3vl () {
    super("org.mozilla.javascript.RHINO-SRC-SecurityUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-SecurityUtilities.java");
      for (int i = 1; i <= 3; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

