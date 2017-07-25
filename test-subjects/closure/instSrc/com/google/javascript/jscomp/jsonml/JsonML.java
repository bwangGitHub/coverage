/*
 * Copyright 2010 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.jsonml;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class which represents JsonML element according to the specification at
 * "http://code.google.com/p/es-lab/wiki/JsonMLASTFormat"
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class JsonML {
  static {
    CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.ping();
  }

  private final TagType type;
  private Map<TagAttr, Object> attributes =
      new EnumMap<TagAttr, Object>(TagAttr.class);
  {
    CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[1]++;
  }
  private List<JsonML> children = new ArrayList<JsonML>();
  {
    CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[2]++;
  }

  /**
   * Creates a new element with a given type.
   * @param type
   */
  public JsonML(TagType type) {
    this.type = type;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[3]++;
  }

  /**
   * Creates a new element.
   * @param type type of the element
   * @param children children to append to the element
   */
  public JsonML(TagType type, JsonML... children) {
    this(type, Arrays.asList(children));
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[4]++;
  }

  public JsonML(TagType type, List<? extends JsonML> children) {
    this(type, Collections.<TagAttr, Object>emptyMap(), children);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[5]++;
  }

  public JsonML(TagType type, Map<? extends TagAttr, ?> attributes) {
    this(type, attributes, Collections.<JsonML>emptyList());
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[6]++;
  }

  public JsonML(TagType type, Map<? extends TagAttr, ?> attributes,
      List<? extends JsonML> children) {
    this.type = type;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[7]++;
    this.attributes.putAll(attributes);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[8]++;
    appendChildren(children);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[9]++;
  }

  /**
   * Inserts the given JsonML element at the given position in the
   * list of children.
   * @param index index at which the given element is to be inserted
   * @param element JsonML element to be inserted
   */
  public void addChild(int index, JsonML element) {
    children.add(index, element);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[10]++;
  }

  /**
   * Appends a given child element to the list of children.
   * @param element JsonML element to append
   */
  public void appendChild(JsonML element) {
    children.add(element);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[11]++;
  }

  /**
   * Appends a collection of children to the back of the list of children.
   * @param elements collection of JsonML elements to append
   */
  public void appendChildren(Collection<? extends JsonML> elements) {
    children.addAll(elements);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[12]++;
  }

  /**
   * Returns number of the children.
   */
  public int childrenSize() {
    return children.size();
  }

  /**
   * Removes all elements from the list of children.
   */
  public void clearChildren() {
    setChildren();
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[13]++;
  }

  /**
   * Returns value associated with a given attribute.
   * @param name name of the attribute
   * @return associated value or null if the attribute is not present
   */
  public Object getAttribute(TagAttr name) {
    return attributes.get(name);
  }

  /**
   * Returns a map with attributes and respective values.
   */
  public Map<TagAttr, Object> getAttributes() {
    return attributes;
  }

  /**
   * Returns child at a given position.
   */
  public JsonML getChild(int index) {
    return children.get(index);
  }

  /**
   * Returns a list of all children.
   */
  public List<JsonML> getChildren() {
    return children;
  }

  /**
   * Returns the portion of children list between the specified
   * fromIndex, inclusive, and toIndex, exclusive.
   * @param fromIndex low endpoint (inclusive)
   * @param toIndex high endpoint (exclusive)
   */
  public List<JsonML> getChildren(int fromIndex, int toIndex) {
    return children.subList(fromIndex, toIndex);
  }

  /**
   * Returns type of the JsonML element.
   */
  public TagType getType() {
    return type;
  }

  /**
   * Returns true if the JsonML element has at least one child.
   */
  public boolean hasChildren() {
    return !children.isEmpty();
  }

  /**
   * Sets value for a given attribute.
   * @param name name of the attribute
   * @param value value to associate with the attribute
   */
  public void setAttribute(TagAttr name, Object value) {
    attributes.put(name, value);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[14]++;
  }

  /**
   * Sets attributes of the JsonML element.
   * @param attributes map with attributes and their values
   */
  public void setAttributes(Map<TagAttr, Object> attributes) {
    this.attributes = attributes;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[15]++;
  }

  /**
   * Replaces the element at the given position in the list of children wit
   * the given JsonML element.
   * @param index index of element to replace
   * @param element JsonML element to append
   */
  public void setChild(int index, JsonML element) {
    children.set(index, element);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[16]++;
  }

  /**
   * Replaces all elements in the list of children with the given
   * JsonML elements.
   * @param children a comma separated list of JsonML elements
   */
  public void setChildren(JsonML... children) {
    this.children.clear();
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[17]++;
      this.children.addAll(Arrays.asList(children));
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[18]++;
  }

  /**
   * Replaces all elements in the list of children with the given
   * list of JsonML elements..
   * @param children a list of JsonML elements.
   */
  public void setChildren(List<JsonML> children) {
    this.children = children;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[19]++;
  }

  @Override
  public String toString() {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[20]++;
    StringBuilder sb = new StringBuilder();
    toString(sb, true, true);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[21]++;
    return sb.toString();
  }

  private void toString(StringBuilder sb, boolean printAttributes,
      boolean printChildren) {
    sb.append("[\"");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[22]++;
    escapeStringOnto(type.name(), sb);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[23]++;
    sb.append('"');
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[24]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[25]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((printAttributes) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[1]++;
      sb.append(", {");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[26]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[27]++;
      boolean first = true;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[1]++;


      for (Entry<TagAttr, Object> entry : attributes.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[1]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[2]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[3]++;
}
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((first) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[3]++;
          first = false;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[30]++;

        } else {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[4]++;
          sb.append(", ");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[31]++;
        }
        sb.append('"');
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[32]++;
        escapeStringOnto(entry.getKey().toString(), sb);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[33]++;
        sb.append("\": ");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[34]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[35]++;
        Object value = entry.getValue();
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[5]++;
          sb.append("null");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[37]++;

        } else {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[6]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[38]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[7]++;
          sb.append('"');
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[39]++;
          escapeStringOnto((String) value, sb);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[40]++;
          sb.append('"');
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[41]++;

        } else {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[8]++;
          sb.append(value);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[42]++;
        }
}
      }
      sb.append("}");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[43]++;

    } else {
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[2]++;}
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[44]++;
int CodeCoverConditionCoverageHelper_C5;

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((printChildren) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[9]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[45]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[4]++;


      for (JsonML child : children) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[4]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[5]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[6]++;
}
        sb.append(", ");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[46]++;
        sb.append(child.toString());
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[47]++;
      }

    } else {
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[10]++;}
    sb.append(']');
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[48]++;
  }


  /**
   * Encodes the specified string and appends it to the given StringBuilder.
   */
  private static void escapeStringOnto(String s, StringBuilder sb) {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[49]++;
    int pos = 0, n = s.length();
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[7]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[8]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[9]++;
}
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[51]++;
      char ch = s.charAt(i);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[52]++;
      switch (ch) {
        case '\r':
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[11]++; case '\n':
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[12]++; case '"':
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[13]++; case '\\':
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[14]++;
        // these two characters are the exceptions to the general rule
        // that JSON is a syntactic subset of JavaScript
        // From JSON's perspective they are considered to be whitespaces,
        // while ES5 specifies them as line terminators.
        case '\u2028':
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[15]++; case '\u2029':
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[16]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[53]++;
          String hex = Integer.toString(ch, 16);
          sb.append(s, pos, i)
              .append("\\u").append("0000", hex.length(), 4).append(hex);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[54]++;
          pos = i + 1;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[55]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[56]++;
          break; default : CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[17]++;
      }
    }
    sb.append(s, pos, n);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[57]++;
  }

  /**
   * Prints a JsonML tree in a human readable format.
   */
  public String toStringTree() {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[58]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[59]++;
      StringBuilder s = new StringBuilder();
      toStringTreeHelper(this, 0, s);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[60]++;
      return s.toString();
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[19]++;
      throw new RuntimeException("Should not happen\n" + e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.branches[18]++;
}
  }
  }

  private static void toStringTreeHelper(JsonML element, int level,
      StringBuilder sb) throws IOException {
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[61]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < level) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[10]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[11]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[12]++;
}
      sb.append("    ");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[62]++;
    }
    element.toString(sb, true, false);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[63]++;
    sb.append("\n");
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[64]++;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[65]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[13]++;


    for (JsonML child : element.getChildren()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[13]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[14]--;
  CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.loops[15]++;
}
      toStringTreeHelper(child, level + 1, sb);
CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d.statements[66]++;
    }
  }
}

class CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d ());
  }
    public static long[] statements = new long[67];
    public static long[] branches = new long[20];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.JsonML.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$bb4ndwd3ni2z0g19d () {
    super("com.google.javascript.jscomp.jsonml.JsonML.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 66; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 19; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.JsonML.java");
      for (int i = 1; i <= 66; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 19; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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


