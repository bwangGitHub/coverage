/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

import org.mozilla.javascript.Context;

/**
 * Interface for URL connection based security domain providers. Used by
 * {@link UrlModuleSourceProvider} to create Rhino security domain objects for
 * newly loaded scripts (see {@link Context#compileReader(java.io.Reader,
 * String, int, Object)}) based on the properties obtainable through their URL
 * connection.
 * @version $Id: UrlConnectionSecurityDomainProvider.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public interface UrlConnectionSecurityDomainProvider
{
    /**
     * Create a new security domain object for a script source identified by
     * its URL connection.
     * @param urlConnection the URL connection of the script source
     * @return the security domain object for the script source. Can be null if
     * no security domain object can be created, although it is advisable for
     * the implementations to be able to create a security domain object for
     * any URL connection.
     */
    public Object getSecurityDomain(URLConnection urlConnection);
}

class CodeCoverCoverageCounter$17nq84r74it7zqod70rthapm7ov0g7qie99e2jo8lcirh8bz4n30i3j2l47hiz9swekhq0u0viw02p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$17nq84r74it7zqod70rthapm7ov0g7qie99e2jo8lcirh8bz4n30i3j2l47hiz9swekhq0u0viw02p ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$17nq84r74it7zqod70rthapm7ov0g7qie99e2jo8lcirh8bz4n30i3j2l47hiz9swekhq0u0viw02p () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlConnectionSecurityDomainProvider.java");
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlConnectionSecurityDomainProvider.java");
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
