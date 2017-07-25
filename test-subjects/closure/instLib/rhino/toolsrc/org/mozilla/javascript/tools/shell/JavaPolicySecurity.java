/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import java.io.IOException;
import java.security.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import org.mozilla.javascript.*;

public class JavaPolicySecurity extends SecurityProxy
{
  static {
    CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.ping();
  }


    @Override
    public Class<?> getStaticSecurityDomainClassInternal() {
        return ProtectionDomain.class;
    }

    private static class Loader extends ClassLoader
        implements GeneratedClassLoader
    {
        private ProtectionDomain domain;

        Loader(ClassLoader parent, ProtectionDomain domain) {
            super(parent != null ? parent : getSystemClassLoader());
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[1]++;
            this.domain = domain;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[2]++;
        }

        public Class<?> defineClass(String name, byte[] data) {
            return super.defineClass(name, data, 0, data.length, domain);
        }

        public void linkClass(Class<?> cl) {
            resolveClass(cl);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[3]++;
        }
    }

    private static class ContextPermissions extends PermissionCollection
    {
        static final long serialVersionUID = -1721494496320750721L;

// Construct PermissionCollection that permits an action only
// if it is permitted by staticDomain and by security context of Java stack on
// the moment of constructor invocation
        ContextPermissions(ProtectionDomain staticDomain) {
            _context = AccessController.getContext();
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[4]++;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((staticDomain != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[1]++;
                _statisPermissions = staticDomain.getPermissions();
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[6]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[2]++;}
            setReadOnly();
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[7]++;
        }

        @Override
        public void add(Permission permission) {
            throw new RuntimeException("NOT IMPLEMENTED");
        }

        @Override
        public boolean implies(Permission permission) {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((_statisPermissions != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[3]++;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((_statisPermissions.implies(permission)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[5]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[6]++;}

            } else {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[4]++;}
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[10]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                _context.checkPermission(permission);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[11]++;
                return true;
            }catch (AccessControlException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[8]++;
                return false;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[7]++;
}
  }
        }

        @Override
        public Enumeration<Permission> elements()
        {
            return new Enumeration<Permission>() {
                public boolean hasMoreElements() { return false; }
                public Permission nextElement() { return null; }
            };
        }

        @Override
        public String toString() {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[12]++;
            StringBuffer sb = new StringBuffer();
            sb.append(getClass().getName());
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[13]++;
            sb.append('@');
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[14]++;
            sb.append(Integer.toHexString(System.identityHashCode(this)));
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[15]++;
            sb.append(" (context=");
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[16]++;
            sb.append(_context);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[17]++;
            sb.append(", static_permitions=");
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[18]++;
            sb.append(_statisPermissions);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[19]++;
            sb.append(')');
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[20]++;
            return sb.toString();
        }

        AccessControlContext _context;
        PermissionCollection _statisPermissions;
    }

    public JavaPolicySecurity()
    {
        // To trigger error on jdk-1.1 with lazy load
        new CodeSource(null,  (java.security.cert.Certificate[])null);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[21]++;
    }

    @Override
    protected void callProcessFileSecure(final Context cx,
                                         final Scriptable scope,
                                         final String filename)
    {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[23]++;
                URL url = getUrlObj(filename);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[24]++;
                ProtectionDomain staticDomain = getUrlDomain(url);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[25]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    Main.processFileSecure(cx, scope, url.toExternalForm(),
                                           staticDomain);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[26]++;
                } catch (IOException ioex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[10]++;
                    throw new RuntimeException(ioex);
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[9]++;
}
  }
                return null;
            }
        });
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[22]++;
    }

    private URL getUrlObj(String url)
    {
        URL urlObj;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[27]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            urlObj = new URL(url);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[28]++;
        } catch (MalformedURLException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[12]++;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[29]++;
            // Assume as Main.processFileSecure it is file, need to build its
            // URL
            String curDir = System.getProperty("user.dir");
            curDir = curDir.replace('\\', '/');
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[30]++;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((curDir.endsWith("/")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[13]++;
                curDir = curDir+'/';
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[32]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[14]++;}
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[33]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
            try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[34]++;
                URL curDirURL = new URL("file:"+curDir);
                urlObj = new URL(curDirURL, url);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[35]++;
            } catch (MalformedURLException ex2) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[16]++;
                throw new RuntimeException
                    ("Can not construct file URL for '"+url+"':"
                     +ex2.getMessage());
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[15]++;
}
  }
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.branches[11]++;
}
  }
        return urlObj;
    }

    private ProtectionDomain getUrlDomain(URL url)
    {
        CodeSource cs;
        cs = new CodeSource(url, (java.security.cert.Certificate[])null);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[36]++;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[37]++;
        PermissionCollection pc = Policy.getPolicy().getPermissions(cs);
        return new ProtectionDomain(cs, pc);
    }

    @Override
    public GeneratedClassLoader
    createClassLoader(final ClassLoader parentLoader, Object securityDomain)
    {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[38]++;
        final ProtectionDomain domain = (ProtectionDomain)securityDomain;
        return AccessController.doPrivileged(new PrivilegedAction<Loader>() {
            public Loader run() {
                return new Loader(parentLoader, domain);
            }
        });
    }

    @Override
    public Object getDynamicSecurityDomain(Object securityDomain)
    {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[39]++;
        ProtectionDomain staticDomain = (ProtectionDomain)securityDomain;
        return getDynamicDomain(staticDomain);
    }

    private ProtectionDomain getDynamicDomain(ProtectionDomain staticDomain) {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[40]++;
        ContextPermissions p = new ContextPermissions(staticDomain);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[41]++;
        ProtectionDomain contextDomain = new ProtectionDomain(null, p);
        return contextDomain;
    }

    @Override
    public Object callWithDomain(Object securityDomain,
                                 final Context cx,
                                 final Callable callable,
                                 final Scriptable scope,
                                 final Scriptable thisObj,
                                 final Object[] args)
    {
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[42]++;
        ProtectionDomain staticDomain = (ProtectionDomain)securityDomain;
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[43]++;
        // There is no direct way in Java to intersect permissions according
        // stack context with additional domain.
        // The following implementation first constructs ProtectionDomain
        // that allows actions only allowed by both staticDomain and current
        // stack context, and then constructs AccessController for this dynamic
        // domain.
        // If this is too slow, alternative solution would be to generate
        // class per domain with a proxy method to call to infect
        // java stack.
        // Another optimization in case of scripts coming from "world" domain,
        // that is having minimal default privileges is to construct
        // one AccessControlContext based on ProtectionDomain
        // with least possible privileges and simply call
        // AccessController.doPrivileged with this untrusted context

        ProtectionDomain dynamicDomain = getDynamicDomain(staticDomain);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[44]++;
        ProtectionDomain[] tmp = { dynamicDomain };
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[45]++;
        AccessControlContext restricted = new AccessControlContext(tmp);
CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1.statements[46]++;

        PrivilegedAction<Object> action = new PrivilegedAction<Object>() {
            public Object run() {
                return callable.call(cx, scope, thisObj, args);
            }
        };

        return AccessController.doPrivileged(action, restricted);
    }
}

class CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1 ());
  }
    public static long[] statements = new long[47];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-JavaPolicySecurity.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$el0607z4lg8y7eprl8wdbjv5o1010d0b1xq4mjm23qghuwoxco1 () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-JavaPolicySecurity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 46; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-JavaPolicySecurity.java");
      for (int i = 1; i <= 46; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
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

