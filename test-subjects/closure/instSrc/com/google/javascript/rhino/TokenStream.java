/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Roger Lawrence
 *   Mike McCabe
 *   Igor Bukanov
 *   Ethan Hugg
 *   Bob Jervis
 *   Terry Lucas
 *   Milen Nankov
 *   Pascal-Louis Perez
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino;

/**
 * This class implements the JavaScript scanner.
 *
 * It is based on the C source files jsscan.c and jsscan.h
 * in the jsref package.
 *
 */

public class TokenStream {
  static {
    CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.ping();
  }

    public static boolean isKeyword(String name) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[1]++;
        boolean id = false;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[2]++;
        String s = name;
        complete: {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[3]++;
            String X = null;
            int c;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[4]++;
            partial: switch (s.length()) {
            case 2:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[1]++; c=s.charAt(1);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[5]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
                if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[2]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
                  if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='i') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[4]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[8]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[9]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[5]++;}

                } else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[3]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[10]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[6]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
                  if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='i') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[8]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[12]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[13]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[9]++;}

                } else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[7]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[14]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[10]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
                  if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='d') && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[12]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[16]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[17]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[13]++;}

                } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[11]++;}
}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[18]++;
                break partial;
            case 3:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[14]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[19]++; switch (s.charAt(0)) {
                case 'f':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[15]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
                  if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='r') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='o') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[16]++;
                    id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[21]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[22]++; break complete;

                  } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[17]++;}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[23]++; break partial;
                case 'i':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[18]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
                  if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='t') && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='n') && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[19]++;
                    id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[25]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[26]++; break complete;

                  } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[20]++;}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[27]++; break partial;
                case 'n':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[21]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
                  if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='w') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='e') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[22]++;
                    id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[29]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[30]++; break complete;

                  } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[23]++;}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[31]++; break partial;
                case 't':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[24]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
                  if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='y') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='r') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[25]++;
                    id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[33]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[34]++; break complete;

                  } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[26]++;}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[35]++; break partial;
                case 'v':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[27]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
                  if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='r') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[28]++;
                    id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[37]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[38]++; break complete;

                  } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[29]++;}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[39]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[30]++;
                }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[40]++; break partial;
            case 4:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[31]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[41]++; switch (s.charAt(0)) {
                case 'b':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[32]++; X="byte";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[42]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[43]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[44]++; break partial;
                case 'c':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[33]++; c=s.charAt(3);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[45]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[34]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[47]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='s') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[36]++;
                            id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[48]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[49]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[37]++;}
 }
                    else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[35]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[50]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[38]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[51]++;
int CodeCoverConditionCoverageHelper_C15;
                      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='a') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='h') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[40]++;
                        id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[52]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[53]++; break complete;

                      } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[41]++;}

                    } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[39]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[54]++;
                    break partial;
                case 'e':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[42]++; c=s.charAt(3);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[55]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[43]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[57]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='s') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='l') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[45]++;
                            id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[58]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[59]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[46]++;}
 }
                    else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[44]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[60]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c=='m') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[47]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[61]++;
int CodeCoverConditionCoverageHelper_C19;
                      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='u') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='n') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[49]++;
                            id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[62]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[63]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[50]++;}
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[48]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[64]++;
                    break partial;
                case 'g':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[51]++; X="goto";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[65]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[66]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[67]++; break partial;
                case 'l':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[52]++; X="long";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[68]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[69]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[70]++; break partial;
                case 'n':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[53]++; X="null";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[71]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[72]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[73]++; break partial;
                case 't':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[54]++; c=s.charAt(3);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[74]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[75]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[55]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[76]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='u') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='r') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[57]++;
                            id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[77]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[78]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[58]++;}
 }
                    else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[56]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[79]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[59]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;
                      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='i') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='h') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[61]++;
                            id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[81]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[82]++; break complete;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[62]++;}
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[60]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[83]++;
                    break partial;
                case 'v':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[63]++; X="void";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[84]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[85]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[86]++; break partial;
                case 'w':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[64]++; X="with";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[87]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[88]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[89]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[65]++;
                }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[90]++; break partial;
            case 5:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[66]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[91]++; switch (s.charAt(2)) {
                case 'a':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[67]++; X="class";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[92]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[93]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[94]++; break partial;
                case 'e':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[68]++; X="break";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[95]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[96]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[97]++; break partial;
                case 'i':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[69]++; X="while";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[98]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[99]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[100]++; break partial;
                case 'l':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[70]++; X="false";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[101]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[102]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[103]++; break partial;
                case 'n':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[71]++; c=s.charAt(0);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[104]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[105]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[72]++; X="const";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[106]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[107]++;
 }
                    else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[73]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[108]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[74]++; X="final";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[109]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[110]++;
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[75]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[111]++;
                    break partial;
                case 'o':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[76]++; c=s.charAt(0);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[112]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[113]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[77]++; X="float";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[114]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[115]++;
 }
                    else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[78]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[116]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[79]++; X="short";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[117]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[118]++;
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[80]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[119]++;
                    break partial;
                case 'p':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[81]++; X="super";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[120]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[121]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[122]++; break partial;
                case 'r':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[82]++; X="throw";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[123]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[124]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[125]++; break partial;
                case 't':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[83]++; X="catch";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[126]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[127]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[128]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[84]++;
                }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[129]++; break partial;
            case 6:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[85]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[130]++; switch (s.charAt(1)) {
                case 'a':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[86]++; X="native";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[131]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[132]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[133]++; break partial;
                case 'e':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[87]++; c=s.charAt(0);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[134]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[135]++;
int CodeCoverConditionCoverageHelper_C28;
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c=='d') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[88]++; X="delete";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[136]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[137]++;
 }
                    else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[89]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[138]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[90]++; X="return";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[139]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[140]++;
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[91]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[141]++;
                    break partial;
                case 'h':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[92]++; X="throws";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[142]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[143]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[144]++; break partial;
                case 'm':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[93]++; X="import";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[145]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[146]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[147]++; break partial;
                case 'o':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[94]++; X="double";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[148]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[149]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[150]++; break partial;
                case 't':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[95]++; X="static";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[151]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[152]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[153]++; break partial;
                case 'u':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[96]++; X="public";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[154]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[155]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[156]++; break partial;
                case 'w':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[97]++; X="switch";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[157]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[158]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[159]++; break partial;
                case 'x':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[98]++; X="export";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[160]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[161]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[162]++; break partial;
                case 'y':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[99]++; X="typeof";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[163]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[164]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[165]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[100]++;
                }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[166]++; break partial;
            case 7:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[101]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[167]++; switch (s.charAt(1)) {
                case 'a':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[102]++; X="package";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[168]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[169]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[170]++; break partial;
                case 'e':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[103]++; X="default";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[171]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[172]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[173]++; break partial;
                case 'i':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[104]++; X="finally";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[174]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[175]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[176]++; break partial;
                case 'o':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[105]++; X="boolean";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[177]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[178]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[179]++; break partial;
                case 'r':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[106]++; X="private";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[180]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[181]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[182]++; break partial;
                case 'x':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[107]++; X="extends";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[183]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[184]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[185]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[108]++;
                }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[186]++; break partial;
            case 8:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[109]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[187]++; switch (s.charAt(0)) {
                case 'a':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[110]++; X="abstract";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[188]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[189]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[190]++; break partial;
                case 'c':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[111]++; X="continue";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[191]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[192]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[193]++; break partial;
                case 'd':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[112]++; X="debugger";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[194]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[195]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[196]++; break partial;
                case 'f':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[113]++; X="function";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[197]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[198]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[199]++; break partial;
                case 'v':
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[114]++; X="volatile";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[200]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[201]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[202]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[115]++;
                }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[203]++; break partial;
            case 9:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[116]++; c=s.charAt(0);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[204]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[205]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[117]++; X="interface";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[206]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[207]++;
 }
                else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[118]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[208]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[119]++; X="protected";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[209]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[210]++;
 }
                else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[120]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[211]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[121]++; X="transient";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[212]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[213]++;
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[122]++;}
}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[214]++;
                break partial;
            case 10:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[123]++; c=s.charAt(1);
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[215]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[216]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((c=='m') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[124]++; X="implements";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[217]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[218]++;
 }
                else {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[125]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[219]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[126]++; X="instanceof";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[220]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[221]++;
 } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[127]++;}
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[222]++;
                break partial;
            case 12:
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[128]++; X="synchronized";
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[223]++;id=true;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[224]++;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[225]++; break partial; default : CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[129]++;
            }
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[226]++;
int CodeCoverConditionCoverageHelper_C35;
            // partial match validate the entire string the one possibility
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[130]++; return false;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[131]++;}
        }
        return id;
    }

    public static boolean isJSIdentifier(String s) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[227]++;
      int length = s.length();
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[228]++;
int CodeCoverConditionCoverageHelper_C36;

      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(s.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[132]++;
        return false;
} else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[133]++;}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[229]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.loops[1]++;


int CodeCoverConditionCoverageHelper_C37;

      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.loops[1]--;
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.loops[2]--;
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.loops[3]++;
}
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.statements[230]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(s.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[134]++;
          return false;

        } else {
  CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p.branches[135]++;}
      }

      return true;
    }
}

class CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p ());
  }
    public static long[] statements = new long[231];
    public static long[] branches = new long[136];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.TokenStream.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,2,2,2,2,1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,3,2,1,1};
    for (int i = 1; i <= 38; i++) {
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

  public CodeCoverCoverageCounter$4zyem9kz04dgmcevur6nr536p () {
    super("com.google.javascript.rhino.TokenStream.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 230; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 135; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.TokenStream.java");
      for (int i = 1; i <= 230; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 135; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
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

