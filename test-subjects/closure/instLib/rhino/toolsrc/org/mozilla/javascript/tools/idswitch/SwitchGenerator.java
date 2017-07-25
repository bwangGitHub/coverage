/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.idswitch;

import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class SwitchGenerator {
  static {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.ping();
  }


    String v_switch_label = "L0";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[1]++;
  }
    String v_label = "L";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[2]++;
  }
    String v_s = "s";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[3]++;
  }
    String v_c = "c";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[4]++;
  }
    String v_guess = "X";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[5]++;
  }
    String v_id = "id";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[6]++;
  }
    String v_length_suffix = "_length";
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[7]++;
  }

    int use_if_threshold = 3;
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[8]++;
  }
    int char_tail_test_threshold = 2;
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[9]++;
  }

    private IdValuePair[] pairs;
    private String default_value;
    private int[] columns;
    private boolean c_was_defined;

    private CodePrinter P;
    private ToolErrorReporter R;
    private String source_file;

    public CodePrinter getCodePrinter() { return P; }
    public void setCodePrinter(CodePrinter value) { P = value;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[10]++; }

    public ToolErrorReporter getReporter() { return R; }
    public void setReporter(ToolErrorReporter value) { R = value;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[11]++; }

    public String getSourceFileName() { return source_file; }
    public void setSourceFileName(String value) { source_file = value;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[12]++; }

    public void generateSwitch(String[] pairs, String default_value) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[13]++;
        int N = pairs.length / 2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[14]++;
        IdValuePair[] id_pairs = new IdValuePair[N];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[3]++;
}
            id_pairs[i] = new IdValuePair(pairs[2 * i], pairs[2 * i + 1]);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[16]++;
        }
        generateSwitch(id_pairs, default_value);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[17]++;

    }

    public void generateSwitch(IdValuePair[] pairs, String default_value) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[18]++;
        int begin = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[19]++;
        int end = pairs.length;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((begin == end) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[1]++; return;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[2]++;}
        this.pairs = pairs;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[21]++;
        this.default_value = default_value;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[22]++;

        generate_body(begin, end, 2);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[23]++;
    }

    private void generate_body(int begin, int end, int indent_level) {
        P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[24]++;
        P.p(v_switch_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[25]++; P.p(": { ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[26]++;
        P.p(v_id);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[27]++; P.p(" = ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[28]++; P.p(default_value);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[29]++;
        P.p("; String ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[30]++; P.p(v_guess);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[31]++; P.p(" = null;");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[32]++;

        c_was_defined = false;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[33]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[34]++;
        int c_def_begin = P.getOffset();
        P.p(" int ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[35]++; P.p(v_c);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[36]++; P.p(';');
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[37]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[38]++;
        int c_def_end = P.getOffset();
        P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[39]++;

        generate_length_switch(begin, end, indent_level + 1);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[40]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[41]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c_was_defined) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[3]++;
            P.erase(c_def_begin, c_def_end);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[42]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[4]++;}

        P.indent(indent_level + 1);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[43]++;
        P.p("if (");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[44]++; P.p(v_guess);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[45]++; P.p("!=null && ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[46]++;
        P.p(v_guess);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[47]++; P.p("!=");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[48]++; P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[49]++;
        P.p(" && !");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[50]++; P.p(v_guess);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[51]++; P.p(".equals(");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[52]++; P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[53]++; P.p(")) ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[54]++;
        P.p(v_id);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[55]++; P.p(" = ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[56]++; P.p(default_value);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[57]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[58]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[59]++;

        // Add break at end of block to suppress warning for unused label
        P.indent(indent_level + 1);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[60]++;
        P.p("break ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[61]++; P.p(v_switch_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[62]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[63]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[64]++;

        P.line(indent_level, "}");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[65]++;
    }

    private void generate_length_switch(int begin, int end, int indent_level) {

        sort_pairs(begin, end, -1);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[66]++;

        check_all_is_different(begin, end);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[67]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[68]++;

        int lengths_count = count_different_lengths(begin, end);

        columns = new int[pairs[end  - 1].idLength];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[69]++;

        boolean use_if;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[70]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lengths_count <= use_if_threshold) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[5]++;
            use_if = true;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[71]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[72]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lengths_count != 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[7]++;
                P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[73]++;
                P.p("int ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[74]++; P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[75]++; P.p(v_length_suffix);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[76]++;
                P.p(" = ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[77]++; P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[78]++; P.p(".length();");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[79]++;
                P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[80]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[8]++;}

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[6]++;
            use_if = false;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[81]++;
            P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[82]++;
            P.p(v_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[83]++; P.p(": switch (");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[84]++;
            P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[85]++; P.p(".length()) {");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[86]++;
            P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[87]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[88]++;

        int same_length_begin = begin;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[89]++;
        int cur_l = pairs[begin].idLength, l = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[90]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[4]++;


        for (int i = begin;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[6]++;
}
            ++i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[91]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[92]++;
            if (i == end || (l = pairs[i].idLength) != cur_l) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[9]++;
                int next_indent;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[93]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((use_if) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[11]++;
                    P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[94]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[95]++;
int CodeCoverConditionCoverageHelper_C9;
                    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((same_length_begin != begin) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[13]++; P.p("else ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[96]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[14]++;}
                    P.p("if (");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[97]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[98]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((lengths_count == 1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[15]++;
                        P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[99]++; P.p(".length()==");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[100]++;

                    }
                    else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[16]++;
                        P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[101]++; P.p(v_length_suffix);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[102]++; P.p("==");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[103]++;
                    }
                    P.p(cur_l);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[104]++;
                    P.p(") {");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[105]++;
                    next_indent = indent_level + 1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[106]++;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[12]++;
                    P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[107]++;
                    P.p("case ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[108]++; P.p(cur_l);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[109]++; P.p(":");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[110]++;
                    next_indent = indent_level + 1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[111]++;
                }
                generate_letter_switch
                    (same_length_begin, i, next_indent, !use_if, use_if);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[112]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[113]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((use_if) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[17]++;
                    P.p("}");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[114]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[115]++;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[18]++;
                    P.p("break ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[116]++; P.p(v_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[117]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[118]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[119]++;
                }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[120]++;
int CodeCoverConditionCoverageHelper_C12;

                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i == end) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[19]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[121]++; break;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[20]++;}
                same_length_begin = i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[122]++;
                cur_l = l;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[123]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[10]++;}
        }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[124]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((use_if) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[21]++;
            P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[125]++; P.p("}");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[126]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[127]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[22]++;}

    }

    private void generate_letter_switch
        (int begin, int end,
         int indent_level, boolean label_was_defined, boolean inside_if)
    {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[128]++;
        int L = pairs[begin].idLength;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[129]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[9]++;
}
            columns[i] = i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[130]++;
        }

        generate_letter_switch_r
            (begin, end, L, indent_level, label_was_defined, inside_if);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[131]++;
    }


    private boolean generate_letter_switch_r
        (int begin, int end, int L,
         int indent_level, boolean label_was_defined, boolean inside_if)
    {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[132]++;
        boolean next_is_unreachable = false;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[133]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((begin + 1 == end) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[23]++;
            P.p(' ');
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[134]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[135]++;
            IdValuePair pair = pairs[begin];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[136]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((L > char_tail_test_threshold) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[25]++;
                P.p(v_guess);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[137]++; P.p("=");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[138]++; P.qstring(pair.id);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[139]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[140]++;
                P.p(v_id);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[141]++; P.p("=");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[142]++; P.p(pair.value);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[143]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[144]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[26]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[145]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((L == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[27]++;
                    next_is_unreachable = true;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[146]++;
                    P.p(v_id);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[147]++; P.p("=");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[148]++; P.p(pair.value);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[149]++;
                    P.p("; break ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[150]++; P.p(v_switch_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[151]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[152]++;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[28]++;
                    P.p("if (");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[153]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[154]++;
                    int column = columns[0];
                    P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[155]++; P.p(".charAt(");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[156]++; P.p(column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[157]++; P.p(")==");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[158]++;
                    P.qchar(pair.id.charAt(column));
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[159]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[160]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[10]++;


int CodeCoverConditionCoverageHelper_C18;
                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[10]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[11]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[12]++;
}
                        P.p(" && ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[161]++;
                        column = columns[i];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[162]++;
                        P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[163]++; P.p(".charAt(");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[164]++; P.p(column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[165]++; P.p(")==");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[166]++;
                        P.qchar(pair.id.charAt(column));
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[167]++;
                    }
                    P.p(") {");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[168]++;
                    P.p(v_id);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[169]++; P.p("=");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[170]++; P.p(pair.value);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[171]++;
                    P.p("; break ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[172]++; P.p(v_switch_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[173]++; P.p(";}");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[174]++;
                }
            }
            P.p(' ');
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[175]++;
            return next_is_unreachable;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[24]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[176]++;

        int max_column_index = find_max_different_column(begin, end, L);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[177]++;
        int max_column = columns[max_column_index];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[178]++;
        int count = count_different_chars(begin, end, max_column);

        columns[max_column_index] = columns[L - 1];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[179]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[180]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((inside_if) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[29]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[181]++; P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[182]++;
 }
        else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[30]++; P.p(' ');
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[183]++; }

        boolean use_if;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[184]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((count <= use_if_threshold) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[31]++;
            use_if = true;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[185]++;
            c_was_defined = true;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[186]++;
            P.p(v_c);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[187]++; P.p("=");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[188]++; P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[189]++;
            P.p(".charAt(");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[190]++; P.p(max_column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[191]++; P.p(");");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[192]++;

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[32]++;
            use_if = false;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[193]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[194]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((label_was_defined) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[33]++;
                label_was_defined = true;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[195]++;
                P.p(v_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[196]++; P.p(": ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[197]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[34]++;}
            P.p("switch (");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[198]++; P.p(v_s);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[199]++;
            P.p(".charAt(");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[200]++; P.p(max_column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[201]++; P.p(")) {");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[202]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[203]++;

        int same_char_begin = begin;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[204]++;
        int cur_ch = pairs[begin].id.charAt(max_column), ch = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[205]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[13]++;


        for (int i = begin;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[13]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[14]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[15]++;
}
            ++i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[206]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[207]++;
            if (i == end || (ch = pairs[i].id.charAt(max_column)) != cur_ch) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[35]++;
                int next_indent;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[208]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((use_if) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[37]++;
                    P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[209]++; P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[210]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[211]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((same_char_begin != begin) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[39]++; P.p("else ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[212]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[40]++;}
                    P.p("if (");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[213]++; P.p(v_c);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[214]++; P.p("==");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[215]++;
                    P.qchar(cur_ch);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[216]++; P.p(") {");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[217]++;
                    next_indent = indent_level + 1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[218]++;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[38]++;
                    P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[219]++; P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[220]++;
                    P.p("case ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[221]++; P.qchar(cur_ch);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[222]++; P.p(":");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[223]++;
                    next_indent = indent_level + 1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[224]++;
                }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[225]++;
                boolean after_unreachable = generate_letter_switch_r
                    (same_char_begin, i, L - 1,
                     next_indent, label_was_defined, use_if);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[226]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((use_if) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[41]++;
                    P.p("}");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[227]++;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[42]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[228]++;
int CodeCoverConditionCoverageHelper_C27;
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((after_unreachable) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[43]++;
                        P.p("break ");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[229]++; P.p(v_label);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[230]++; P.p(";");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[231]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[44]++;}
                }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[232]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i == end) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[45]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[233]++; break;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[46]++;}
                same_char_begin = i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[234]++;
                cur_ch = ch;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[235]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[36]++;}
        }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[236]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((use_if) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[47]++;
            P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[237]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[238]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((inside_if) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[49]++; P.indent(indent_level - 1);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[239]++;
 }
            else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[50]++; P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[240]++; }

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[48]++;
            P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[241]++; P.indent(indent_level);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[242]++; P.p("}");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[243]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[244]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((inside_if) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[51]++; P.nl();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[245]++; P.indent(indent_level - 1);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[246]++;
}
            else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[52]++; P.p(' ');
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[247]++; }
        }

        columns[max_column_index] = max_column;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[248]++;

        return next_is_unreachable;
    }


    private int count_different_lengths(int begin, int end) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[249]++;
        int lengths_count = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[250]++;
        int cur_l = -1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[251]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[16]++;


int CodeCoverConditionCoverageHelper_C32;
        for (;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((begin != end) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); ++begin) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[16]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[17]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[18]++;
}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[252]++;
            int l = pairs[begin].idLength;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[253]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cur_l != l) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[53]++;
                ++lengths_count;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[254]++; cur_l = l;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[255]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[54]++;}
        }
        return lengths_count;
    }

    private int find_max_different_column(int begin, int end, int L) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[256]++;
        int max_count = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[257]++;
        int max_index = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[258]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[19]++;


int CodeCoverConditionCoverageHelper_C34;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[19]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[20]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[21]++;
}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[259]++;
            int column = columns[i];
            sort_pairs(begin, end, column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[260]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[261]++;
            int count = count_different_chars(begin, end, column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[262]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((count == end - begin) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[55]++; return i;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[56]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[263]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((max_count < count) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[57]++;
                max_count = count;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[264]++;
                max_index = i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[265]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[58]++;}
        }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[266]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((max_index != L - 1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[59]++;
            sort_pairs(begin, end, columns[max_index]);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[267]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[60]++;}

        return max_index;
    }

    private int count_different_chars(int begin, int end, int column) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[268]++;
        int chars_count = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[269]++;
        int cur_ch = -1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[270]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[22]++;


int CodeCoverConditionCoverageHelper_C38;
        for (;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((begin != end) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); ++begin) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[22]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[23]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[24]++;
}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[271]++;
            int ch = pairs[begin].id.charAt(column);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[272]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ch != cur_ch) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[61]++;
                ++chars_count;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[273]++; cur_ch = ch;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[274]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[62]++;}
        }
        return chars_count;
    }

    private void check_all_is_different(int begin, int end) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[275]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((begin != end) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[63]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[276]++;
            IdValuePair prev = pairs[begin];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[277]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[25]++;


int CodeCoverConditionCoverageHelper_C41;
            while ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((++begin != end) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[25]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[26]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[27]++;
}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[278]++;
                IdValuePair current = pairs[begin];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[279]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((prev.id.equals(current.id)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[65]++;
                    throw on_same_pair_fail(prev, current);

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[66]++;}
                prev = current;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[280]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[64]++;}
    }

    private EvaluatorException on_same_pair_fail(IdValuePair a, IdValuePair b) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[281]++;
        int line1 = a.getLineNumber(), line2 = b.getLineNumber();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[282]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((line2 > line1) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[67]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[283]++; int tmp = line1; line1 = line2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[284]++; line2 = tmp;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[285]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[68]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[286]++;
        String error_text = ToolErrorReporter.getMessage(
            "msg.idswitch.same_string", a.id, new Integer(line2));
        return R.runtimeError(error_text, source_file, line1, null, 0);
    }

    private void sort_pairs(int begin, int end, int comparator) {
        heap4Sort(pairs, begin, end - begin, comparator);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[287]++;
    }

    private static boolean bigger
        (IdValuePair a, IdValuePair b, int comparator)
    {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[288]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((comparator < 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[69]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[289]++;
        // For length selection switch it is enough to compare just length,
        // but to detect same strings full comparison is essential
            //return a.idLength > b.idLength;
            int diff = a.idLength - b.idLength;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[290]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((diff != 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[71]++; return diff > 0;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[72]++;}
            return a.id.compareTo(b.id) > 0;

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[70]++;
            return a.id.charAt(comparator) > b.id.charAt(comparator);
        }
    }

    private static void heap4Sort
        (IdValuePair[] array, int offset, int size, int comparator)
    {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[291]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((size <= 1) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[73]++; return;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[74]++;}
        makeHeap4(array, offset, size, comparator);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[292]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[293]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[28]++;


int CodeCoverConditionCoverageHelper_C47;
        while ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((size > 1) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[28]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[29]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[30]++;
}
            --size;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[294]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[295]++;
            IdValuePair v1 = array[offset + size];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[296]++;
            IdValuePair v2 = array[offset + 0];
            array[offset + size] = v2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[297]++;
            array[offset + 0] = v1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[298]++;
            heapify4(array, offset, size, 0, comparator);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[299]++;
        }
    }

    private static void makeHeap4
        (IdValuePair[] array, int offset, int size, int comparator)
    {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[300]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[31]++;


int CodeCoverConditionCoverageHelper_C48;
        for (int i = ((size + 2) >> 2);(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false);) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[31]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[32]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[33]++;
}
            --i;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[301]++;
            heapify4(array, offset, size, i, comparator);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[302]++;
        }
    }

    private static void heapify4
        (IdValuePair[] array, int offset, int size, int i, int comparator)
    {
        int new_i1, new_i2, new_i3;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[303]++;
        IdValuePair i_val = array[offset + i];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[304]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[34]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[34]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[35]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.loops[36]++;
}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[305]++;
            int base = (i << 2);
            new_i1 = base | 1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[306]++;
            new_i2 = base | 2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[307]++;
            new_i3 = base | 3;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[308]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[309]++;
            int new_i4 = base + 4;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[310]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((new_i4 >= size) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[75]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[311]++; break;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[76]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[312]++;
            IdValuePair val1 = array[offset + new_i1];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[313]++;
            IdValuePair val2 = array[offset + new_i2];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[314]++;
            IdValuePair val3 = array[offset + new_i3];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[315]++;
            IdValuePair val4 = array[offset + new_i4];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[316]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((bigger(val2, val1, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[77]++;
                val1 = val2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[317]++; new_i1 = new_i2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[318]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[78]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[319]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((bigger(val4, val3, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[79]++;
                val3 = val4;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[320]++; new_i3 = new_i4;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[321]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[80]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[322]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((bigger(val3, val1, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[81]++;
                val1 = val3;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[323]++; new_i1 = new_i3;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[324]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[82]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[325]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((bigger(i_val, val1, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[83]++; return;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[84]++;}
            array[offset + i] = val1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[326]++;
            array[offset + new_i1] = i_val;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[327]++;
            i = new_i1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[328]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[329]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((new_i1 < size) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[85]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[330]++;
            IdValuePair val1 = array[offset + new_i1];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[331]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((new_i2 != size) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[87]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[332]++;
                IdValuePair val2 = array[offset + new_i2];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[333]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((bigger(val2, val1, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[89]++;
                    val1 = val2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[334]++; new_i1 = new_i2;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[335]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[90]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[336]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((new_i3 != size) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[91]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[337]++;
                    IdValuePair val3 = array[offset + new_i3];
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[338]++;
int CodeCoverConditionCoverageHelper_C59;
                    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((bigger(val3, val1, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[93]++;
                        val1 = val3;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[339]++; new_i1 = new_i3;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[340]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[94]++;}

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[92]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[88]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[341]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((bigger(val1, i_val, comparator)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[95]++;
                array[offset + i] = val1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[342]++;
                array[offset + new_i1] = i_val;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.statements[343]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[96]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh.branches[86]++;}
    }
}

class CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh ());
  }
    public static long[] statements = new long[344];
    public static long[] branches = new long[97];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[61];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.idswitch.RHINO-TOO-SwitchGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 60; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$1gk5ffks5uu2sz1dfdawm1vq3ozi3hlkt6d7audimnfg1gh () {
    super("org.mozilla.javascript.tools.idswitch.RHINO-TOO-SwitchGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 343; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 96; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 60; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.idswitch.RHINO-TOO-SwitchGenerator.java");
      for (int i = 1; i <= 343; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 96; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 60; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

