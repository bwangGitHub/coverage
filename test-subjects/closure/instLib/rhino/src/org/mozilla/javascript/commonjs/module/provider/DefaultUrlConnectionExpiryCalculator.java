/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.Serializable;
import java.net.URLConnection;

/**
 * The default heuristic for calculating cache expiry of URL-based resources.
 * It is simply configured with a default relative expiry, and each invocation
 * of {@link #calculateExpiry(URLConnection)} returns
 * {@link System#currentTimeMillis()} incremented with the relative expiry.
 * @version $Id: DefaultUrlConnectionExpiryCalculator.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public class DefaultUrlConnectionExpiryCalculator
implements UrlConnectionExpiryCalculator, Serializable
{
  static {
    CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.statements[1]++;
  }

    private final long relativeExpiry;

    /**
     * Creates a new default expiry calculator with one minute relative expiry.
     */
    public DefaultUrlConnectionExpiryCalculator() {
        this(60000L);
CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.statements[2]++;
    }

    /**
     * Creates a new default expiry calculator with the specified relative
     * expiry.
     * @param relativeExpiry the fixed relative expiry, in milliseconds.
     */
    public DefaultUrlConnectionExpiryCalculator(long relativeExpiry) {
CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((relativeExpiry < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.branches[1]++;
            throw new IllegalArgumentException("relativeExpiry < 0");

        } else {
  CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.branches[2]++;}
        this.relativeExpiry = relativeExpiry;
CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75.statements[4]++;
    }

    public long calculateExpiry(URLConnection urlConnection) {
        return System.currentTimeMillis() + relativeExpiry;
    }
}

class CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75 ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-DefaultUrlConnectionExpiryCalculator.java";
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

  public CodeCoverCoverageCounter$8mgqhtteo5rsu5p9i5yg2jyw2onux9rftot3328mn840xywp7p8pyg7zhu06hcsvtlnrdp4amswom75 () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-DefaultUrlConnectionExpiryCalculator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-DefaultUrlConnectionExpiryCalculator.java");
      for (int i = 1; i <= 4; i++) {
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
