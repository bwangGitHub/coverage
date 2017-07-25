/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.idswitch;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class Main {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.ping();
  }


    private static final String SWITCH_TAG_STR = "string_id_map";
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[1]++;
  }
    private static final String GENERATED_TAG_STR = "generated";
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[2]++;
  }
    private static final String STRING_TAG_STR = "string";
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[3]++;
  }

    private static final int
        NORMAL_LINE        = 0,
        SWITCH_TAG         = 1,
        GENERATED_TAG      = 2,
        STRING_TAG         = 3;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[4]++;
  }

    private final List<IdValuePair> all_pairs = new ArrayList<IdValuePair>();
  {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[5]++;
  }

    private ToolErrorReporter R;
    private CodePrinter P;
    private FileBody body;
    private String source_file;

    private int tag_definition_end;

    private int tag_value_start;
    private int tag_value_end;

    private static boolean is_value_type(int id) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((id == STRING_TAG) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[1]++; return true;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[2]++;}
        return false;
    }

    private static String tag_name(int id) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[7]++;
        switch (id) {
            case SWITCH_TAG:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[3]++; return SWITCH_TAG_STR;
            case -SWITCH_TAG:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[4]++; return "/" + SWITCH_TAG_STR;
            case GENERATED_TAG:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[5]++; return GENERATED_TAG_STR;
            case -GENERATED_TAG:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[6]++; return "/" + GENERATED_TAG_STR; default : CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[7]++;
        }
        return "";
    }

    void process_file(String file_path) throws IOException {
        source_file = file_path;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[8]++;

        body = new FileBody();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[9]++;

        InputStream is;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((file_path.equals("-")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[8]++;
            is = System.in;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[11]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[9]++;
            is = new FileInputStream(file_path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[12]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[14]++;
            Reader r = new InputStreamReader(is, "ASCII");
            body.readData(r);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[15]++;
        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[10]++;
} is.close();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[16]++; }

        process_file();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[17]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((body.wasModified()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[11]++;
            OutputStream os;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((file_path.equals("-")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[13]++;
                os = System.out;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[20]++;

            }
            else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[14]++;
                os = new FileOutputStream(file_path);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[21]++;
            }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[22]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

            try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[23]++;
                Writer w = new OutputStreamWriter(os);
                body.writeData(w);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[24]++;
                w.flush();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[25]++;
            }
            finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[15]++;
} os.close();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[26]++; }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[12]++;}
    }

    private void process_file() {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[27]++;
        int cur_state = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[28]++;
        char[] buffer = body.getBuffer();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[29]++;

        int generated_begin = -1, generated_end = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[30]++;
        int time_stamp_begin = -1, time_stamp_end = -1;

        body.startLineLoop();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[31]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[32]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((body.nextLine()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[1]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[2]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[3]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[33]++;
            int begin = body.getLineBegin();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[34]++;
            int end = body.getLineEnd();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[35]++;

            int tag_id = extract_line_tag_id(buffer, begin, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[36]++;
            boolean bad_tag = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[37]++;
            switch (cur_state) {
                case NORMAL_LINE:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[16]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((tag_id == SWITCH_TAG) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[17]++;
                        cur_state = SWITCH_TAG;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[39]++;
                        all_pairs.clear();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[40]++;
                        generated_begin = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[41]++;

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[18]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[42]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((tag_id == -SWITCH_TAG) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[19]++;
                        bad_tag = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[43]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[20]++;}
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[44]++;
                    break;
                case SWITCH_TAG:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[21]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((tag_id == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[22]++;
                        look_for_id_definitions(buffer, begin, end, false);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[46]++;

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[23]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[47]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((tag_id == STRING_TAG) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[24]++;
                        look_for_id_definitions(buffer, begin, end, true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[48]++;

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[25]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[49]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((tag_id == GENERATED_TAG) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[26]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
                        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((generated_begin >= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[28]++; bad_tag = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[51]++;
 }
                        else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[29]++;
                            cur_state = GENERATED_TAG;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[52]++;
                            time_stamp_begin = tag_definition_end;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[53]++;
                            time_stamp_end = end;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[54]++;
                        }

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[27]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[55]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((tag_id == -SWITCH_TAG) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[30]++;
                        cur_state = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[56]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
                        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((generated_begin >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((all_pairs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[32]++;
                            generate_java_code();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[58]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[59]++;
                            String code = P.toString();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[60]++;
                            boolean different = body.setReplacement
                                (generated_begin, generated_end, code);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;
                            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((different) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[34]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[62]++;
                                String stamp = get_time_stamp();
                                body.setReplacement
                                    (time_stamp_begin, time_stamp_end, stamp);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[63]++;

                            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[35]++;}

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[33]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[64]++;

                        break;

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[31]++;
                        bad_tag = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[65]++;
                    }
}
}
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[66]++;
                    break;
                case GENERATED_TAG:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[36]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[67]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((tag_id == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[37]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;
                        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((generated_begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[39]++; generated_begin = begin;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[69]++;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[40]++;}

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[38]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[70]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((tag_id == -GENERATED_TAG) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[41]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[71]++;
int CodeCoverConditionCoverageHelper_C18;
                        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((generated_begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[43]++; generated_begin = begin;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[72]++;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[44]++;}
                        cur_state = SWITCH_TAG;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[73]++;
                        generated_end = begin;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[74]++;

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[42]++;
                        bad_tag = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[75]++;
                    }
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[76]++;
                    break; default : CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[45]++;
            }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[77]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((bad_tag) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[46]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[78]++;
                String text = ToolErrorReporter.getMessage(
                    "msg.idswitch.bad_tag_order", tag_name(tag_id));
                throw R.runtimeError
                    (text, source_file, body.getLineNumber(), null, 0);

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[47]++;}
        }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[79]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((cur_state != 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[48]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[80]++;
            String text = ToolErrorReporter.getMessage(
                "msg.idswitch.file_end_in_switch", tag_name(cur_state));
            throw R.runtimeError
                (text, source_file, body.getLineNumber(), null, 0);

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[49]++;}
    }

    private String get_time_stamp() {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[81]++;
        SimpleDateFormat f = new SimpleDateFormat
            (" 'Last update:' yyyy-MM-dd HH:mm:ss z");
        return f.format(new Date());
    }

    private void generate_java_code() {

        P.clear();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[82]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[83]++;

        IdValuePair[] pairs = new IdValuePair[all_pairs.size()];
        all_pairs.toArray(pairs);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[84]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[85]++;

        SwitchGenerator g = new SwitchGenerator();
        g.char_tail_test_threshold = 2;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[86]++;
        g.setReporter(R);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[87]++;
        g.setCodePrinter(P);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[88]++;

        g.generateSwitch(pairs, "0");
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[89]++;
    }

    private int extract_line_tag_id(char[] array, int cursor, int end) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[90]++;
        int id = 0;
        cursor = skip_white_space(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[91]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[92]++;
        int after_leading_white_space = cursor;
        cursor = look_for_slash_slash(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[93]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[94]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[50]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[95]++;
            boolean at_line_start = (after_leading_white_space + 2 == cursor);
            cursor = skip_white_space(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[96]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[97]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((array[cursor] == '#') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[52]++;
                ++cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[98]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[99]++;

                boolean end_tag = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[100]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((array[cursor] == '/') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[54]++;
                    ++cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[101]++; end_tag = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[102]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[55]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[103]++;

                int tag_start = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[104]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[4]++;


int CodeCoverConditionCoverageHelper_C24;

                for (;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ++cursor) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[4]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[5]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[6]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[105]++;
                    int c = array[cursor];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[106]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((c == '#') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((c == '=') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((is_white_space(c)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[56]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[107]++; break;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[57]++;}
                }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[108]++;
int CodeCoverConditionCoverageHelper_C26;

                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[58]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[109]++;
                    int tag_end = cursor;
                    cursor = skip_white_space(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[110]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[111]++;
int CodeCoverConditionCoverageHelper_C27;
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[60]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[112]++;
                        int c = array[cursor];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[113]++;
int CodeCoverConditionCoverageHelper_C28;
                        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((c == '=') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c == '#') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[62]++;
                            id = get_tag_id
                                (array, tag_start, tag_end, at_line_start);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[114]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[115]++;
int CodeCoverConditionCoverageHelper_C29;
                            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[64]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[116]++;
                                String bad = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[117]++;
int CodeCoverConditionCoverageHelper_C30;
                                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c == '#') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[66]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[118]++;
int CodeCoverConditionCoverageHelper_C31;
                                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((end_tag) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[68]++;
                                        id = -id;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[119]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[120]++;
int CodeCoverConditionCoverageHelper_C32;
                                        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((is_value_type(id)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[70]++;
                                            bad = "msg.idswitch.no_end_usage";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[121]++;

                                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[71]++;}

                                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[69]++;}
                                    tag_definition_end = cursor + 1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[122]++;

                                }
                                else  {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[67]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[123]++;
int CodeCoverConditionCoverageHelper_C33;
                                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((end_tag) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[72]++;
                                        bad = "msg.idswitch.no_end_with_value";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[124]++;

                                    }
                                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[73]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[125]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((is_value_type(id)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[74]++;
                                        bad = "msg.idswitch.no_value_allowed";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[126]++;

                                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[75]++;}
}
                                    id = extract_tag_value
                                        (array, cursor + 1, end, id);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[127]++;
                                }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[128]++;
int CodeCoverConditionCoverageHelper_C35;
                                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((bad != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[76]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[129]++;
                                    String s = ToolErrorReporter.getMessage(
                                        bad, tag_name(id));
                                    throw R.runtimeError
                                        (s, source_file, body.getLineNumber(),
                                         null, 0);

                                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[77]++;}

                            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[65]++;}

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[63]++;}

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[61]++;}

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[59]++;}

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[53]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[51]++;}
        return id;
    }

// Return position after first of // or end if not found
    private int look_for_slash_slash(char[] array, int cursor, int end) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[130]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[7]++;


int CodeCoverConditionCoverageHelper_C36;
        while ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((cursor + 2 <= end) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[7]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[8]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[9]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[131]++;
            int c = array[cursor++];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[132]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[78]++;
                c = array[cursor++];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[133]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[134]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[80]++;
                    return cursor;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[81]++;}

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[79]++;}
        }
        return end;
    }

    private int extract_tag_value(char[] array, int cursor, int end, int id) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[135]++;
        // cursor points after #[^#=]+=
        // ALERT: implement support for quoted strings
        boolean found = false;
        cursor = skip_white_space(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[136]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[137]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[82]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[138]++;
            int value_start = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[139]++;
            int value_end = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[140]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[10]++;


int CodeCoverConditionCoverageHelper_C40;
            while ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[10]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[11]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[12]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[141]++;
                int c = array[cursor];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[142]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((is_white_space(c)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[84]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[143]++;
                    int after_space = skip_white_space(array, cursor + 1, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[144]++;
int CodeCoverConditionCoverageHelper_C42;
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((after_space != end) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((array[after_space] == '#') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[86]++;
                        value_end = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[145]++;
                        cursor = after_space;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[146]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[147]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[87]++;}
                    cursor = after_space + 1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[148]++;

                }
                else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[85]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[149]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((c == '#') && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[88]++;
                    value_end = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[150]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[151]++;
                    break;

                }
                else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[89]++;
                    ++cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[152]++;
                }
}
            }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[153]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[90]++;
                // array[cursor] is '#' here
                found = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[154]++;
                tag_value_start = value_start;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[155]++;
                tag_value_end = value_end;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[156]++;
                tag_definition_end = cursor + 1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[157]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[91]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[83]++;}
        return (found) ? id : 0;
    }

    private int get_tag_id
        (char[] array, int begin, int end, boolean at_line_start)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[158]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((at_line_start) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[92]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[159]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((equals(SWITCH_TAG_STR, array, begin, end)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[94]++;
                return SWITCH_TAG;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[95]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[160]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((equals(GENERATED_TAG_STR, array, begin, end)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[96]++;
                return GENERATED_TAG;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[97]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[93]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[161]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((equals(STRING_TAG_STR, array, begin, end)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[98]++;
            return STRING_TAG;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[99]++;}
        return 0;
    }

    private void look_for_id_definitions
        (char[] array, int begin, int end, boolean use_tag_value_as_string)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[162]++;
    // Look for the pattern
    // '^[ \t]+Id_([a-zA-Z0-9_]+)[ \t]*=.*$'
    // where \1 gives field or method name
        int cursor = begin;
        // Skip tab and spaces at the beginning
        cursor = skip_white_space(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[163]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[164]++;
        int id_start = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[165]++;
        int name_start = skip_matched_prefix("Id_", array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[166]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((name_start >= 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[100]++;
            // Found Id_ prefix
            cursor = name_start;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[167]++;
            cursor = skip_name_char(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[168]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[169]++;
            int name_end = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[170]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((name_start != name_end) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[102]++;
                cursor = skip_white_space(array, cursor, end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[171]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[172]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[104]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[173]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((array[cursor] == '=') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[106]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[174]++;
                        int id_end = name_end;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[175]++;
int CodeCoverConditionCoverageHelper_C53;
                        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((use_tag_value_as_string) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[108]++;
                            name_start = tag_value_start;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[176]++;
                            name_end = tag_value_end;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[177]++;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[109]++;}
                        // Got the match
                        add_id(array, id_start, id_end, name_start, name_end);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[178]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[107]++;}

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[105]++;}

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[103]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[101]++;}
    }

    private void add_id
        (char[] array, int id_start, int id_end, int name_start, int name_end)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[179]++;
        String name = new String(array, name_start, name_end - name_start);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[180]++;
        String value = new String(array, id_start, id_end - id_start);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[181]++;

        IdValuePair pair = new IdValuePair(name, value);

        pair.setLineNumber(body.getLineNumber());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[182]++;

        all_pairs.add(pair);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[183]++;
    }

    private static boolean is_white_space(int c) {
        return c == ' ' || c == '\t';
    }

    private static int skip_white_space(char[] array, int begin, int end) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[184]++;
        int cursor = begin;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[185]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[13]++;


int CodeCoverConditionCoverageHelper_C54;
        for (;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); ++cursor) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[13]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[14]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[15]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[186]++;
            int c = array[cursor];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[187]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((is_white_space(c)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[110]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[188]++; break;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[111]++;}
        }
        return cursor;
    }

    private static int skip_matched_prefix
        (String prefix, char[] array, int begin, int end)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[189]++;
        int cursor = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[190]++;
        int prefix_length = prefix.length();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[191]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((prefix_length <= end - begin) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[112]++;
            cursor = begin;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[192]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[193]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[16]++;


int CodeCoverConditionCoverageHelper_C57;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i != prefix_length) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); ++i, ++cursor) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[16]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[17]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[18]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[194]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((prefix.charAt(i) != array[cursor]) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[114]++;
                    cursor = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[195]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[196]++; break;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[115]++;}
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[113]++;}
        return cursor;
    }

    private static boolean equals(String str, char[] array, int begin, int end)
    {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[197]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((str.length() == end - begin) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[116]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[198]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[19]++;


int CodeCoverConditionCoverageHelper_C60;
            for (int i = begin, j = 0;(((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false); ++i, ++j) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[19]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[20]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[21]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[199]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((array[i] != str.charAt(j)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[118]++; return false;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[119]++;}
            }
            return true;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[117]++;}
        return false;
    }

    private static int skip_name_char(char[] array, int begin, int end) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[200]++;
        int cursor = begin;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[201]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[22]++;


int CodeCoverConditionCoverageHelper_C62;
        for (;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((cursor != end) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); ++cursor) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[22]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[23]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[24]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[202]++;
            int c = array[cursor];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[203]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C63 |= (128)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((c <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 4) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 4) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[120]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[204]++;
int CodeCoverConditionCoverageHelper_C64;
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[122]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[205]++;
int CodeCoverConditionCoverageHelper_C65;
                    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((c != '_') && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[124]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[206]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[125]++;}

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[123]++;}

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[121]++;}
        }
        return cursor;
    }

    public static void main(String[] args) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[207]++;
        Main self = new Main();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[208]++;
        int status = self.exec(args);
        System.exit(status);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[209]++;
    }

    private int exec(String[] args) {
        R = new ToolErrorReporter(true, System.err);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[210]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[211]++;

        int arg_count = process_options(args);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[212]++;
int CodeCoverConditionCoverageHelper_C66;

        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((arg_count == 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[126]++;
            option_error(ToolErrorReporter.getMessage(
                             "msg.idswitch.no_file_argument"));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[213]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[127]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[214]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((arg_count > 1) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[128]++;
            option_error(ToolErrorReporter.getMessage(
                             "msg.idswitch.too_many_arguments"));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[215]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[129]++;}

        P = new CodePrinter();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[216]++;
        P.setIndentStep(4);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[217]++;
        P.setIndentTabSize(0);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[218]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[219]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

        try {
CodeCoverTryBranchHelper_Try3 = true;
            process_file(args[0]);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[220]++;
        }
        catch (IOException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[131]++;
            print_error(ToolErrorReporter.getMessage(
                            "msg.idswitch.io_error", ex.toString()));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[221]++;
            return -1;
        }
        catch (EvaluatorException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[132]++;
            return -1;
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[130]++;
}
  }
        return 0;
    }

    private int process_options(String[] args) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[222]++;

        int status = 1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[223]++;

        boolean show_usage = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[224]++;
        boolean show_version = false;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[225]++;

        int N = args.length;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[226]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[25]++;


int CodeCoverConditionCoverageHelper_C68;
        L: for (int i = 0;(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[25]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[26]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[27]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[227]++;
            String arg = args[i];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[228]++;
            int arg_length = arg.length();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[229]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((arg_length >= 2) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[133]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[230]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((arg.charAt(0) == '-') && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[135]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[231]++;
int CodeCoverConditionCoverageHelper_C71;
                    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((arg.charAt(1) == '-') && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[137]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[232]++;
int CodeCoverConditionCoverageHelper_C72;
                        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((arg_length == 2) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[139]++;
                            args[i] = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[233]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[234]++; break;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[140]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[235]++;
int CodeCoverConditionCoverageHelper_C73;
                        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((arg.equals("--help")) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[141]++;
                            show_usage = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[236]++;

                        }
                        else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[142]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[237]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((arg.equals("--version")) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[143]++;
                            show_version = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[238]++;

                        }
                        else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[144]++;
                            option_error(ToolErrorReporter.getMessage(
                                             "msg.idswitch.bad_option", arg));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[239]++;
                            status = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[240]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[241]++; break L;
                        }
}

                    }
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[138]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[242]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[28]++;


int CodeCoverConditionCoverageHelper_C75;
                        for (int j = 1;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((j != arg_length) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[28]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[29]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[30]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[243]++;
                            char c = arg.charAt(j);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[244]++;
                            switch (c) {
                                case 'h':
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[145]++; show_usage = true;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[245]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[246]++; break;
                                default:
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[146]++;
                                    option_error(
                                        ToolErrorReporter.getMessage(
                                            "msg.idswitch.bad_option_char",
                                            String.valueOf(c)));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[247]++;
                                    status = -1;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[248]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[249]++;
                                    break L;
                            }

                        }
                    }
                    args[i] = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[250]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[136]++;}

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[134]++;}
        }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[251]++;
int CodeCoverConditionCoverageHelper_C76;

        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((status == 1) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[147]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[252]++;
int CodeCoverConditionCoverageHelper_C77;
            if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((show_usage) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[149]++; show_usage();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[253]++; status = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[254]++;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[150]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[255]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((show_version) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[151]++; show_version();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[256]++; status = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[257]++;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[152]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[148]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[258]++;
int CodeCoverConditionCoverageHelper_C79;

        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((status != 1) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[153]++; System.exit(status);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[259]++;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[154]++;}

        return remove_nulls(args);
    }

    private void show_usage() {
        System.out.println(
            ToolErrorReporter.getMessage("msg.idswitch.usage"));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[260]++;
        System.out.println();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[261]++;
    }

    private void show_version() {
        System.out.println(
            ToolErrorReporter.getMessage("msg.idswitch.version"));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[262]++;
    }

    private void option_error(String str) {
        print_error(
            ToolErrorReporter.getMessage("msg.idswitch.bad_invocation", str));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[263]++;
    }

    private void print_error(String text) {
        System.err.println(text);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[264]++;
    }

    private int remove_nulls(String[] array) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[265]++;
        int N = array.length;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[266]++;
        int cursor = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[267]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[31]++;


int CodeCoverConditionCoverageHelper_C80;
        for (;(((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((cursor != N) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false); ++cursor) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[31]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[32]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[33]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[268]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((array[cursor] == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[155]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[269]++; break;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[156]++;}
        }
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[270]++;
        int destination = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[271]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((cursor != N) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[157]++;
            ++cursor;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[272]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[273]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[34]++;


int CodeCoverConditionCoverageHelper_C83;
            for (;(((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((cursor != N) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false); ++cursor) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[34]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[35]--;
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.loops[36]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[274]++;
                String elem = array[cursor];
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[275]++;
int CodeCoverConditionCoverageHelper_C84;
                if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((elem != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[159]++;
                    array[destination] = elem;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[276]++; ++destination;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[277]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[160]++;}
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[158]++;}
        return destination;
    }
}

class CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x ());
  }
    public static long[] statements = new long[278];
    public static long[] branches = new long[161];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[85];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.idswitch.RHINO-TOO-Main.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,2,1,3,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 84; i++) {
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

  public CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x () {
    super("org.mozilla.javascript.tools.idswitch.RHINO-TOO-Main.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 277; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 160; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 84; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.idswitch.RHINO-TOO-Main.java");
      for (int i = 1; i <= 277; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 160; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 84; i++) {
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


