/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * Load generated classes.
 *
 */
public class DefiningClassLoader extends ClassLoader
    implements GeneratedClassLoader
{
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.ping();
  }

    public DefiningClassLoader() {
        this.parentLoader = getClass().getClassLoader();
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[1]++;
    }

    public DefiningClassLoader(ClassLoader parentLoader) {
        this.parentLoader = parentLoader;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[2]++;
    }

    public Class<?> defineClass(String name, byte[] data) {
        // Use our own protection domain for the generated classes.
        // TODO: we might want to use a separate protection domain for classes
        // compiled from scripts, based on where the script was loaded from.
        return super.defineClass(name, data, 0, data.length,
                SecurityUtilities.getProtectionDomain(getClass()));
    }

    public void linkClass(Class<?> cl) {
        resolveClass(cl);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[3]++;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[4]++;
        Class<?> cl = findLoadedClass(name);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cl == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.branches[1]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parentLoader != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.branches[3]++;
                cl = parentLoader.loadClass(name);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[7]++;

            } else {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.branches[4]++;
                cl = findSystemClass(name);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[8]++;
            }

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.branches[2]++;}
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((resolve) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.branches[5]++;
            resolveClass(cl);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.statements[10]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx.branches[6]++;}
        return cl;
    }

    private final ClassLoader parentLoader;
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-DefiningClassLoader.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojydamnlwep4q4j6ac2n5a0uudxlat12r7bv2fkx () {
    super("org.mozilla.javascript.RHINO-SRC-DefiningClassLoader.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-DefiningClassLoader.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

