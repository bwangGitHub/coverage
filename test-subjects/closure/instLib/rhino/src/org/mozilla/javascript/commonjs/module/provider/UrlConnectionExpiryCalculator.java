/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

/**
 * Implemented by objects that can be used as heuristic strategies for
 * calculating the expiry of a cached resource in cases where the server of the
 * resource doesn't provide explicit expiry information.
 * @version $Id: UrlConnectionExpiryCalculator.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public interface UrlConnectionExpiryCalculator
{
    /**
     * Given a URL connection, returns a calculated heuristic expiry time (in
     * terms of milliseconds since epoch) for the resource.
     * @param urlConnection the URL connection for the resource
     * @return the expiry for the resource
     */
    public long calculateExpiry(URLConnection urlConnection);
}

class CodeCoverCoverageCounter$fr3mluyk641jzvwsttzmpbt5r2fbunhfesfww9z8hhqa3lurfjmtcptc5gxn7bz27iap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fr3mluyk641jzvwsttzmpbt5r2fbunhfesfww9z8hhqa3lurfjmtcptc5gxn7bz27iap ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$fr3mluyk641jzvwsttzmpbt5r2fbunhfesfww9z8hhqa3lurfjmtcptc5gxn7bz27iap () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlConnectionExpiryCalculator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlConnectionExpiryCalculator.java");
      for (int i = 1; i <= -1; i++) {
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

