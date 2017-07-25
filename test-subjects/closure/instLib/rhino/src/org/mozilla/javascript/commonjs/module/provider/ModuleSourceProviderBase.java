/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * A base implementation for all module script providers that actually load
 * module scripts. Performs validation of identifiers, allows loading from
 * preferred locations (attempted before require.paths), from require.paths
 * itself, and from fallback locations (attempted after require.paths). Note
 * that while this base class strives to be as generic as possible, it does
 * have loading from an URI built into its design, for the simple reason that
 * the require.paths is defined in terms of URIs.
 * @version $Id: ModuleSourceProviderBase.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public abstract class ModuleSourceProviderBase implements
        ModuleSourceProvider, Serializable
{
  static {
    CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[1]++;
  }

    public ModuleSource loadSource(String moduleId, Scriptable paths,
            Object validator) throws IOException, URISyntaxException
    {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((entityNeedsRevalidation(validator)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[1]++;
            return NOT_MODIFIED;

        } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[2]++;}
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[3]++;

        ModuleSource moduleSource = loadFromPrivilegedLocations(
                moduleId, validator);
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((moduleSource != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[3]++;
            return moduleSource;

        } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[4]++;}
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paths != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[5]++;
            moduleSource = loadFromPathArray(moduleId, paths,
                    validator);
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[6]++;
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
            if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((moduleSource != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[7]++;
                return moduleSource;

            } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[6]++;}
        return loadFromFallbackLocations(moduleId, validator);
    }

    public ModuleSource loadSource(URI uri, URI base, Object validator)
            throws IOException, URISyntaxException {
        return loadFromUri(uri, base, validator);
    }

    private ModuleSource loadFromPathArray(String moduleId,
            Scriptable paths, Object validator) throws IOException
    {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[8]++;
        final long llength = ScriptRuntime.toUint32(
                ScriptableObject.getProperty(paths, "length"));
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[9]++;
        // Yeah, I'll ignore entries beyond Integer.MAX_VALUE; so sue me.
        int ilength = llength > Integer.MAX_VALUE ? Integer.MAX_VALUE :
            (int)llength;
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;

        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < ilength) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.loops[1]--;
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.loops[2]--;
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.loops[3]++;
}
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[11]++;
            final String path = ensureTrailingSlash(
                    ScriptableObject.getTypedProperty(paths, i, String.class));
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[12]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[13]++;
                URI uri =  new URI(path);
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((uri.isAbsolute()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[10]++;
                    uri = new File(path).toURI().resolve("");
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[15]++;

                } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[11]++;}
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[16]++;
                final ModuleSource moduleSource = loadFromUri(
                        uri.resolve(moduleId), uri, validator);
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
                if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((moduleSource != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[12]++;
                    return moduleSource;

                } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[13]++;}
            }
            catch(URISyntaxException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[14]++;
                throw new MalformedURLException(e.getMessage());
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601.branches[9]++;
}
  }
        }
        return null;
    }

    private static String ensureTrailingSlash(String path) {
        return path.endsWith("/") ? path : path.concat("/");
    }

    /**
     * Override to determine whether according to the validator, the cached
     * module script needs revalidation. A validator can carry expiry
     * information. If the cached representation is not expired, it doesn'
     * t need revalidation, otherwise it does. When no cache revalidation is
     * required, the external resource will not be contacted at all, so some
     * level of expiry (staleness tolerance) can greatly enhance performance.
     * The default implementation always returns true so it will always require
     * revalidation.
     * @param validator the validator
     * @return returns true if the cached module needs revalidation.
     */
    protected boolean entityNeedsRevalidation(Object validator) {
        return true;
    }

    /**
     * Override in a subclass to load a module script from a logical URI. The
     * URI is absolute but does not have a file name extension such as ".js".
     * It is up to the ModuleSourceProvider implementation to add such an
     * extension.
     * @param uri the URI of the script, without file name extension.
     * @param base the base URI the uri was resolved from.
     * @param validator a validator that can be used to revalidate an existing
     * cached source at the URI. Can be null if there is no cached source
     * available.
     * @return the loaded module script, or null if it can't be found, or
     * {@link ModuleSourceProvider#NOT_MODIFIED} if it revalidated the existing
     * cached source against the URI.
     * @throws IOException if the module script was found, but an I/O exception
     * prevented it from being loaded.
     * @throws URISyntaxException if the final URI could not be constructed
     */
    protected abstract ModuleSource loadFromUri(URI uri, URI base,
            Object validator) throws IOException, URISyntaxException;

    /**
     * Override to obtain a module source from privileged locations. This will
     * be called before source is attempted to be obtained from URIs specified
     * in require.paths.
     * @param moduleId the ID of the module
     * @param validator a validator that can be used to validate an existing
     * cached script. Can be null if there is no cached script available.
     * @return the loaded module script, or null if it can't be found in the
     * privileged locations, or {@link ModuleSourceProvider#NOT_MODIFIED} if
     * the existing cached module script is still valid.
     * @throws IOException if the module script was found, but an I/O exception
     * prevented it from being loaded.
     * @throws URISyntaxException if the final URI could not be constructed.
     */
    protected ModuleSource loadFromPrivilegedLocations(
            String moduleId, Object validator)
            throws IOException, URISyntaxException
    {
        return null;
    }

    /**
     * Override to obtain a module source from fallback locations. This will
     * be called after source is attempted to be obtained from URIs specified
     * in require.paths.
     * @param moduleId the ID of the module
     * @param validator a validator that can be used to validate an existing
     * cached script. Can be null if there is no cached script available.
     * @return the loaded module script, or null if it can't be found in the
     * privileged locations, or {@link ModuleSourceProvider#NOT_MODIFIED} if
     * the existing cached module script is still valid.
     * @throws IOException if the module script was found, but an I/O exception
     * prevented it from being loaded.
     * @throws URISyntaxException if the final URI could not be constructed.
     */
    protected ModuleSource loadFromFallbackLocations(
            String moduleId, Object validator)
            throws IOException, URISyntaxException
    {
        return null;
    }
}

class CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601 ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ModuleSourceProviderBase.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$14f23oc0tagqirx3c220z7aeffrqdnipslti3bg6aemfeot8aopqqlnnmo601 () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ModuleSourceProviderBase.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ModuleSourceProviderBase.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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
