/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml;

import org.mozilla.javascript.*;

public abstract class XMLLib
{
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.ping();
  }

    private static final Object XML_LIB_KEY = new Object();
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[1]++;
  }

	/**
		An object which specifies an XMLLib implementation to be used at runtime.

		This interface should be considered experimental.  It may be better
		(and certainly more flexible) to write an interface that returns an
		XMLLib object rather than a class name, for example.  But that would
		cause many more ripple effects in the code, all the way back to
		{@link ScriptRuntime}.
	 */
	public static abstract class Factory {
		public static Factory create(final String className) {
			return new Factory() {
			    @Override
				public String getImplementationClassName() {
					return className;
				}
			};
		}

		public abstract String getImplementationClassName();
	}

    public static XMLLib extractFromScopeOrNull(Scriptable scope)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[2]++;
        ScriptableObject so = ScriptRuntime.getLibraryScopeOrNull(scope);
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((so == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.branches[1]++;
            // If library is not yet initialized, return null
            return null;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.branches[2]++;}

        // Ensure lazily initialization of real XML library instance
        // which is done on first access to XML property
        ScriptableObject.getProperty(so, "XML");
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[4]++;

        return (XMLLib)so.getAssociatedValue(XML_LIB_KEY);
    }

    public static XMLLib extractFromScope(Scriptable scope)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[5]++;
        XMLLib lib = extractFromScopeOrNull(scope);
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((lib != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.branches[3]++;
            return lib;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.branches[4]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[7]++;
        String msg = ScriptRuntime.getMessage0("msg.XML.not.available");
        throw Context.reportRuntimeError(msg);
    }

    protected final XMLLib bindToScope(Scriptable scope)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[8]++;
        ScriptableObject so = ScriptRuntime.getLibraryScopeOrNull(scope);
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((so == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.branches[5]++;
            // standard library should be initialized at this point
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd.branches[6]++;}
        return (XMLLib)so.associateValue(XML_LIB_KEY, this);
    }

    public abstract boolean isXMLName(Context cx, Object name);

    public abstract Ref nameRef(Context cx, Object name,
                                Scriptable scope, int memberTypeFlags);

    public abstract Ref nameRef(Context cx, Object namespace, Object name,
                                Scriptable scope, int memberTypeFlags);

    /**
     * Escapes the reserved characters in a value of an attribute.
     *
     * @param value Unescaped text
     * @return The escaped text
     */
    public abstract String escapeAttributeValue(Object value);

    /**
     * Escapes the reserved characters in a value of a text node.
     *
     * @param value Unescaped text
     * @return The escaped text
     */
    public abstract String escapeTextValue(Object value);


    /**
     * Construct namespace for default xml statement.
     */
    public abstract Object toDefaultXmlNamespace(Context cx, Object uriValue);

    public void setIgnoreComments(boolean b) {
        throw new UnsupportedOperationException();
    }

    public void setIgnoreWhitespace(boolean b) {
        throw new UnsupportedOperationException();
    }

    public void setIgnoreProcessingInstructions(boolean b) {
        throw new UnsupportedOperationException();
    }

    public void setPrettyPrinting(boolean b) {
        throw new UnsupportedOperationException();
    }

    public void setPrettyIndent(int i) {
        throw new UnsupportedOperationException();
    }

    public boolean isIgnoreComments() {
        throw new UnsupportedOperationException();
    }

    public boolean isIgnoreProcessingInstructions() {
        throw new UnsupportedOperationException();
    }

    public boolean isIgnoreWhitespace() {
        throw new UnsupportedOperationException();
    }

    public boolean isPrettyPrinting() {
        throw new UnsupportedOperationException();
    }

    public int getPrettyIndent() {
        throw new UnsupportedOperationException();
    }
}

class CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.RHINO-SRC-XMLLib.java";
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

  public CodeCoverCoverageCounter$1wcjkiz20v4sksnro27hda6jw5u7rgodd () {
    super("org.mozilla.javascript.xml.RHINO-SRC-XMLLib.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.mozilla.javascript.xml.RHINO-SRC-XMLLib.java");
      for (int i = 1; i <= 9; i++) {
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

