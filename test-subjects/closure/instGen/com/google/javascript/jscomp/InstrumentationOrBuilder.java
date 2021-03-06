// Generated by the protocol buffer compiler.  DO NOT EDIT!

package com.google.javascript.jscomp;

public interface InstrumentationOrBuilder
    extends com.google.protobuf.MessageOrBuilder {

  // optional string report_defined = 1;
  boolean hasReportDefined();
  String getReportDefined();

  // optional string report_call = 2;
  boolean hasReportCall();
  String getReportCall();

  // optional string report_exit = 6;
  boolean hasReportExit();
  String getReportExit();

  // repeated string declaration_to_remove = 3;
  java.util.List<String> getDeclarationToRemoveList();
  int getDeclarationToRemoveCount();
  String getDeclarationToRemove(int index);

  // repeated string init = 4;
  java.util.List<String> getInitList();
  int getInitCount();
  String getInit(int index);

  // optional string app_name_setter = 5;
  boolean hasAppNameSetter();
  String getAppNameSetter();
}

class CodeCoverCoverageCounter$ox8x39jazhdtkq2a2yom7lmjmrvqzumpmcvhiwrbqnn61437d6jmca0rl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ox8x39jazhdtkq2a2yom7lmjmrvqzumpmcvhiwrbqnn61437d6jmca0rl ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$ox8x39jazhdtkq2a2yom7lmjmrvqzumpmcvhiwrbqnn61437d6jmca0rl () {
    super("com.google.javascript.jscomp.GEN-SRC-InstrumentationOrBuilder.java");
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
    log.startNamedSection("com.google.javascript.jscomp.GEN-SRC-InstrumentationOrBuilder.java");
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

