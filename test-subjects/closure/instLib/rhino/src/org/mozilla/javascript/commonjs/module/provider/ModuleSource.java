/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.Reader;
import java.io.Serializable;
import java.net.URI;

/**
 * Represents the source text of the module as a tuple of a reader, a URI, a
 * security domain, and a cache validator.
 * <h1>Cache validators</h1>
 * Validators are used by caches subclassed from
 * {@link CachingModuleScriptProviderBase} to avoid repeated loading of
 * unmodified resources as well as automatic reloading of modified resources.
 * Such a validator can be any value that can be used to detect modification or
 * non-modification of the resource that provided the source of the module. It
 * can be as simple as a tuple of a URI or a file path, and a last-modified
 * date, or an ETag (in case of HTTP). It is left to the implementation. It is
 * also allowed to carry expiration information (i.e. in case of HTTP
 * expiration header, or if a default expiration is used by the source provider
 * to avoid too frequent lookup of the resource), and to short-circuit the
 * validation in case the validator indicates the cached representation has not
 * yet expired. All these are plainly recommendations; the validators are
 * considered opaque and should only make sure to implement
 * {@link Object#equals(Object)} as caches themselves can rely on it to compare
 * them semantically. Also, it is advisable to have them be serializable.
 * @version $Id: ModuleSource.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public class ModuleSource implements Serializable
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.statements[1]++;
  }

    private final Reader reader;
    private final Object securityDomain;
    private final URI uri;
    private final URI base;
    private final Object validator;

    /**
     * Creates a new module source.
     * @param reader the reader returning the source text of the module.
     * @param securityDomain the object representing the security domain for
     * the module's source (passed to Rhino script compiler).
     * @param uri the URI of the module's source text
     * @param validator a validator that can be used for subsequent cache
     * validation of the source text.
     */
    public ModuleSource(Reader reader, Object securityDomain, URI uri,
                        URI base, Object validator) {
        this.reader = reader;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.statements[2]++;
        this.securityDomain = securityDomain;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.statements[3]++;
        this.uri = uri;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.statements[4]++;
        this.base = base;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.statements[5]++;
        this.validator = validator;
CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l.statements[6]++;
    }

    /**
     * Returns the reader returning the source text of the module. Note that
     * subsequent calls to this method return the same object, thus it is not
     * possible to read the source twice.
     * @return the reader returning the source text of the module.
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Returns the object representing the security domain for the module's
     * source.
     * @return the object representing the security domain for the module's
     * source.
     */
    public Object getSecurityDomain() {
        return securityDomain;
    }

    /**
     * Returns the URI of the module source text.
     * @return the URI of the module source text.
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Returns the base URI from which this module source was loaded, or null
     * if it was loaded from an absolute URI.
     * @return the base URI, or null.
     */
    public URI getBase() {
        return base;
    }

    /**
     * Returns the validator that can be used for subsequent cache validation
     * of the source text.
     * @return the validator that can be used for subsequent cache validation
     * of the source text.
     */
    public Object getValidator() {
        return validator;
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzml4hjqhbpcaopux24zgpmz3su3l () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ModuleSource.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ModuleSource.java");
      for (int i = 1; i <= 6; i++) {
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
