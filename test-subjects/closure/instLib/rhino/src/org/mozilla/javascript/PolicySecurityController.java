/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;

import org.mozilla.classfile.ByteCode;
import org.mozilla.classfile.ClassFileWriter;

/**
 * A security controller relying on Java {@link Policy} in effect. When you use
 * this security controller, your securityDomain objects must be instances of
 * {@link CodeSource} representing the location from where you load your
 * scripts. Any Java policy "grant" statements matching the URL and certificate
 * in code sources will apply to the scripts. If you specify any certificates
 * within your {@link CodeSource} objects, it is your responsibility to verify
 * (or not) that the script source files are signed in whatever
 * implementation-specific way you're using.
 */
public class PolicySecurityController extends SecurityController
{
  static {
    CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.ping();
  }

    private static final byte[] secureCallerImplBytecode = loadBytecode();
  static {
    CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[1]++;
  }

    // We're storing a CodeSource -> (ClassLoader -> SecureRenderer), since we
    // need to have one renderer per class loader. We're using weak hash maps
    // and soft references all the way, since we don't want to interfere with
    // cleanup of either CodeSource or ClassLoader objects.
    private static final Map<CodeSource,Map<ClassLoader,SoftReference<SecureCaller>>>
        callers =
            new WeakHashMap<CodeSource,Map<ClassLoader,SoftReference<SecureCaller>>>();
  static {
    CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[2]++;
  }

    @Override
    public Class<?> getStaticSecurityDomainClassInternal() {
        return CodeSource.class;
    }

    private static class Loader extends SecureClassLoader
    implements GeneratedClassLoader
    {
        private final CodeSource codeSource;

        Loader(ClassLoader parent, CodeSource codeSource)
        {
            super(parent);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[3]++;
            this.codeSource = codeSource;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[4]++;
        }

        public Class<?> defineClass(String name, byte[] data)
        {
            return defineClass(name, data, 0, data.length, codeSource);
        }

        public void linkClass(Class<?> cl)
        {
            resolveClass(cl);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[5]++;
        }
    }

    @Override
    public GeneratedClassLoader createClassLoader(final ClassLoader parent,
            final Object securityDomain)
    {
        return (Loader)AccessController.doPrivileged(
            new PrivilegedAction<Object>()
            {
                public Object run()
                {
                    return new Loader(parent, (CodeSource)securityDomain);
                }
            });
    }

    @Override
    public Object getDynamicSecurityDomain(Object securityDomain)
    {
        // No separate notion of dynamic security domain - just return what was
        // passed in.
        return securityDomain;
    }

    @Override
    public Object callWithDomain(final Object securityDomain, final Context cx,
            Callable callable, Scriptable scope, Scriptable thisObj,
            Object[] args)
    {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[6]++;
        // Run in doPrivileged as we might be checked for "getClassLoader"
        // runtime permission
        final ClassLoader classLoader = (ClassLoader)AccessController.doPrivileged(
            new PrivilegedAction<Object>() {
                public Object run() {
                    return cx.getApplicationClassLoader();
                }
            });
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[7]++;
        final CodeSource codeSource = (CodeSource)securityDomain;
        Map<ClassLoader,SoftReference<SecureCaller>> classLoaderMap;
        synchronized (callers) {
            classLoaderMap = callers.get(codeSource);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[8]++;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
            if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((classLoaderMap == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[1]++;
                classLoaderMap = new WeakHashMap<ClassLoader,SoftReference<SecureCaller>>();
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[10]++;
                callers.put(codeSource, classLoaderMap);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[11]++;

            } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[2]++;}
        }
        SecureCaller caller;
        synchronized (classLoaderMap) {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[12]++;
            SoftReference<SecureCaller> ref = classLoaderMap.get(classLoader);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ref != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[3]++;
                caller = ref.get();
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[14]++;

            } else {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[4]++;
                caller = null;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[15]++;
            }
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((caller == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false))
            {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[5]++;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[17]++;
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
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[19]++;
                            Loader loader = new Loader(classLoader,
                                    codeSource);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[20]++;
                            Class<?> c = loader.defineClass(
                                    SecureCaller.class.getName() + "Impl",
                                    secureCallerImplBytecode);
                            return c.newInstance();
                        }
                    });
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[18]++;
                    classLoaderMap.put(classLoader, new SoftReference<SecureCaller>(caller));
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[21]++;
                }
                catch(PrivilegedActionException ex)
                {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[8]++;
                    throw new UndeclaredThrowableException(ex.getCause());
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[7]++;
}
  }

            } else {
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.branches[6]++;}
        }
        return caller.call(callable, cx, scope, thisObj, args);
    }

    public abstract static class SecureCaller
    {
        public abstract Object call(Callable callable, Context cx, Scriptable scope,
                Scriptable thisObj, Object[] args);
    }


    private static byte[] loadBytecode()
    {
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[22]++;
        String secureCallerClassName = SecureCaller.class.getName();
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[23]++;
        ClassFileWriter cfw = new ClassFileWriter(
                secureCallerClassName + "Impl", secureCallerClassName,
                "<generated>");
        cfw.startMethod("<init>", "()V", ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[24]++;
        cfw.addALoad(0);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[25]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, secureCallerClassName,
                "<init>", "()V");
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[26]++;
        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[27]++;
        cfw.stopMethod((short)1);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[28]++;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[29]++;
        String callableCallSig =
            "Lorg/mozilla/javascript/Context;" +
            "Lorg/mozilla/javascript/Scriptable;" +
            "Lorg/mozilla/javascript/Scriptable;" +
            "[Ljava/lang/Object;)Ljava/lang/Object;";

        cfw.startMethod("call",
                "(Lorg/mozilla/javascript/Callable;" + callableCallSig,
                (short)(ClassFileWriter.ACC_PUBLIC
                        | ClassFileWriter.ACC_FINAL));
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[30]++;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for(int i = 1;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < 6) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.loops[1]--;
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.loops[2]--;
  CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.loops[3]++;
}
            cfw.addALoad(i);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[32]++;
        }
        cfw.addInvoke(ByteCode.INVOKEINTERFACE,
                "org/mozilla/javascript/Callable", "call",
                "(" + callableCallSig);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[33]++;
        cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[34]++;
        cfw.stopMethod((short)6);
CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh.statements[35]++;
        return cfw.toByteArray();
    }
}

class CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-PolicySecurityController.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$14f23oc0tagqirx3cbragarhvy0ul6ccj1i57n91w1dqp20nqpbi7i35mghgh () {
    super("org.mozilla.javascript.RHINO-SRC-PolicySecurityController.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-PolicySecurityController.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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
