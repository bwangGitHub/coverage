/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;

/**
 */
public abstract class SecureCaller
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.ping();
  }

    private static final byte[] secureCallerImplBytecode = loadBytecode();
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[1]++;
  }

    // We're storing a CodeSource -> (ClassLoader -> SecureRenderer), since we
    // need to have one renderer per class loader. We're using weak hash maps
    // and soft references all the way, since we don't want to interfere with
    // cleanup of either CodeSource or ClassLoader objects.
    private static final Map<CodeSource,Map<ClassLoader,SoftReference<SecureCaller>>>
    callers =
        new WeakHashMap<CodeSource,Map<ClassLoader,SoftReference<SecureCaller>>>();
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[2]++;
  }

    public abstract Object call(Callable callable, Context cx,
            Scriptable scope, Scriptable thisObj, Object[] args);

    /**
     * Call the specified callable using a protection domain belonging to the
     * specified code source.
     */
    static Object callSecurely(final CodeSource codeSource, Callable callable,
            Context cx, Scriptable scope, Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[3]++;
        final Thread thread = Thread.currentThread();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[4]++;
        // Run in doPrivileged as we might be checked for "getClassLoader"
        // runtime permission
        final ClassLoader classLoader = (ClassLoader)AccessController.doPrivileged(
            new PrivilegedAction<Object>() {
                public Object run() {
                    return thread.getContextClassLoader();
                }
            });
        Map<ClassLoader,SoftReference<SecureCaller>> classLoaderMap;
        synchronized(callers)
        {
            classLoaderMap = callers.get(codeSource);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[5]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
            if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((classLoaderMap == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false))
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[1]++;
                classLoaderMap = new WeakHashMap<ClassLoader,SoftReference<SecureCaller>>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[7]++;
                callers.put(codeSource, classLoaderMap);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[8]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[2]++;}
        }
        SecureCaller caller;
        synchronized(classLoaderMap)
        {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[9]++;
            SoftReference<SecureCaller> ref = classLoaderMap.get(classLoader);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ref != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[3]++;
                caller = ref.get();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[11]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[4]++;
                caller = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[12]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((caller == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[5]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[14]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try
                {
CodeCoverTryBranchHelper_Try1 = true;
                    // Run in doPrivileged as we'll be checked for
                    // "createClassLoader" runtime permission
                    caller = (SecureCaller)AccessController.doPrivileged(
                            new PrivilegedExceptionAction<Object>()
                    {
                        public Object run() throws Exception
                        {
                            ClassLoader effectiveClassLoader;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[16]++;
                            Class<?> thisClass = getClass();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
                            if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((classLoader.loadClass(thisClass.getName()) != thisClass) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[8]++;
                                effectiveClassLoader = thisClass.getClassLoader();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[18]++;

                            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[9]++;
                                effectiveClassLoader = classLoader;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[19]++;
                            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[20]++;
                            SecureClassLoaderImpl secCl =
                                new SecureClassLoaderImpl(effectiveClassLoader);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[21]++;
                            Class<?> c = secCl.defineAndLinkClass(
                                    SecureCaller.class.getName() + "Impl",
                                    secureCallerImplBytecode, codeSource);
                            return c.newInstance();
                        }
                    });
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[15]++;
                    classLoaderMap.put(classLoader, new SoftReference<SecureCaller>(caller));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[22]++;
                }
                catch(PrivilegedActionException ex)
                {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[10]++;
                    throw new UndeclaredThrowableException(ex.getCause());
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[7]++;
}
  }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[6]++;}
        }
        return caller.call(callable, cx, scope, thisObj, args);
    }

    private static class SecureClassLoaderImpl extends SecureClassLoader
    {
        SecureClassLoaderImpl(ClassLoader parent)
        {
            super(parent);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[23]++;
        }

        Class<?> defineAndLinkClass(String name, byte[] bytes, CodeSource cs)
        {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[24]++;
            Class<?> cl = defineClass(name, bytes, 0, bytes.length, cs);
            resolveClass(cl);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[25]++;
            return cl;
        }
    }

    private static byte[] loadBytecode()
    {
        return (byte[])AccessController.doPrivileged(new PrivilegedAction<Object>()
        {
            public Object run()
            {
                return loadBytecodePrivileged();
            }
        });
    }

    private static byte[] loadBytecodePrivileged()
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[26]++;
        URL url = SecureCaller.class.getResource("SecureCallerImpl.clazz");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[27]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try
        {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[28]++;
            InputStream in = url.openStream();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[29]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try
            {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[30]++;
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.loops[1]++;


                for(;;)
                {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.loops[3]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[32]++;
                    int r = in.read();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
                    if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((r == -1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false))
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[13]++;
                        return bout.toByteArray();

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[14]++;}
                    bout.write(r);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[34]++;
                }
            }
            finally
            {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[12]++;
}
                in.close();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.statements[35]++;
            }
        }
        catch(IOException e)
        {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[15]++;
            throw new UndeclaredThrowableException(e);
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup.branches[11]++;
}
  }
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[16];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-SecureCaller.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,0,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmnmtvtpxzutldxniolfymu5ldup () {
    super("org.mozilla.javascript.RHINO-SRC-SecureCaller.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 15; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-SecureCaller.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 15; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

