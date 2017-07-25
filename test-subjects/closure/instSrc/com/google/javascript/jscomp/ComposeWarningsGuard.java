/*
 * Copyright 2008 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.CheckLevel;

import java.io.Serializable;
import java.util.*;
import java.util.Map;
import java.util.TreeSet;

/**
 * WarningsGuard that represents just a chain of other guards. For example we
 * could have following chain
 * 1) all warnings outside of /foo/ should be suppressed
 * 2) errors with key JSC_BAR should be marked as warning
 * 3) the rest should be reported as error
 *
 * This class is designed for such behavior.
 *
 * @author anatol@google.com (Anatol Pomazau)
 */
public class ComposeWarningsGuard extends WarningsGuard {
  static {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.ping();
  }


  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[1]++;
  }

  // The order that the guards were added in.
  private final Map<WarningsGuard, Integer> orderOfAddition = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[2]++;
  }
  private int numberOfAdds = 0;
  {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[3]++;
  }

  private final Comparator<WarningsGuard> guardComparator =
      new GuardComparator(orderOfAddition);
  {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[4]++;
  }
  private boolean demoteErrors = false;
  {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[5]++;
  }

  private static class GuardComparator
      implements Comparator<WarningsGuard>, Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<WarningsGuard, Integer> orderOfAddition;
    private GuardComparator(Map<WarningsGuard, Integer> orderOfAddition) {
      this.orderOfAddition = orderOfAddition;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[6]++;
    }

    @Override
    public int compare(WarningsGuard a, WarningsGuard b) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[7]++;
      int priorityDiff = a.getPriority() - b.getPriority();
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((priorityDiff != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[1]++;
        return priorityDiff;

      } else {
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[2]++;}

      // If the warnings guards have the same priority, the one that
      // was added last wins.
      return orderOfAddition.get(b).intValue() -
          orderOfAddition.get(a).intValue();
    }
  }

  // The order that the guards are applied in.
  private final TreeSet<WarningsGuard> guards =
      new TreeSet<WarningsGuard>(guardComparator);
  {
    CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[9]++;
  }

  public ComposeWarningsGuard(List<WarningsGuard> guards) {
    addGuards(guards);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[10]++;
  }

  public ComposeWarningsGuard(WarningsGuard... guards) {
    this(Lists.newArrayList(guards));
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[11]++;
  }

  void addGuard(WarningsGuard guard) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((guard instanceof ComposeWarningsGuard) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[3]++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[13]++;
      ComposeWarningsGuard composeGuard = (ComposeWarningsGuard) guard;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((composeGuard.demoteErrors) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[5]++;
        this.demoteErrors = composeGuard.demoteErrors;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[15]++;

      } else {
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[6]++;}

      // Reverse the guards, so that they have the same order in the result.
      addGuards(Lists.newArrayList(composeGuard.guards.descendingSet()));
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[16]++;

    } else {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[4]++;
      numberOfAdds++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[17]++;
      orderOfAddition.put(guard, numberOfAdds);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[18]++;
      guards.remove(guard);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[19]++;
      guards.add(guard);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[20]++;
    }
  }

  private void addGuards(Iterable<WarningsGuard> guards) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[1]++;


    for (WarningsGuard guard : guards) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[1]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[2]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[3]++;
}
      addGuard(guard);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[22]++;
    }
  }

  @Override
  public CheckLevel level(JSError error) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[4]++;


    for (WarningsGuard guard : guards) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[4]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[5]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[6]++;
}
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[24]++;
      CheckLevel newLevel = guard.level(error);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((newLevel != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[7]++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((demoteErrors) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((newLevel == CheckLevel.ERROR) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[9]++;
          return CheckLevel.WARNING;

        } else {
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[10]++;}
        return newLevel;

      } else {
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[8]++;}
    }
    return null;
  }

  @Override
  public boolean disables(DiagnosticGroup group) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[27]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[7]++;


    nextSingleton:
    for (DiagnosticType type : group.getTypes()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[7]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[8]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[9]++;
}
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[28]++;
      DiagnosticGroup singleton = DiagnosticGroup.forType(type);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[29]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[10]++;



      for (WarningsGuard guard : guards) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[10]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[11]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[12]++;
}
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((guard.disables(singleton)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[11]++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[31]++;
          continue nextSingleton;

        } else {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[12]++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[32]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((guard.enables(singleton)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[13]++;
          return false;

        } else {
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[14]++;}
}
      }

      return false;
    }

    return true;
  }

  /**
   * Determines whether this guard will "elevate" the status of any disabled
   * diagnostic type in the group to a warning or an error.
   */
  @Override
  public boolean enables(DiagnosticGroup group) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[33]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[13]++;


    for (WarningsGuard guard : guards) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[13]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[14]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[15]++;
}
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((guard.enables(group)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[15]++;
        return true;

      } else {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[16]++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[35]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((guard.disables(group)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[17]++;
        return false;

      } else {
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.branches[18]++;}
}
    }

    return false;
  }

  List<WarningsGuard> getGuards() {
    return Collections.unmodifiableList(Lists.newArrayList(guards));
  }

  /**
   * Make a warnings guard that's the same as this one but with
   * all escalating guards turned down.
   */
  ComposeWarningsGuard makeEmergencyFailSafeGuard() {
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[36]++;
    ComposeWarningsGuard safeGuard = new ComposeWarningsGuard();
    safeGuard.demoteErrors = true;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[37]++;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[38]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[16]++;


    for (WarningsGuard guard : guards.descendingSet()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[16]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[17]--;
  CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.loops[18]++;
}
      safeGuard.addGuard(guard);
CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p.statements[39]++;
    }
    return safeGuard;
  }

  @Override
  public String toString() {
    return Joiner.on(", ").join(guards);
  }
}

class CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ComposeWarningsGuard.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$32iu5d8agjo3ilrkb6mv90e6kp2j2waauj95z6p () {
    super("com.google.javascript.jscomp.ComposeWarningsGuard.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ComposeWarningsGuard.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

