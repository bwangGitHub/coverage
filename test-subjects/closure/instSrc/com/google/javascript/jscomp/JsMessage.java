/*
 * Copyright 2006 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

/**
 * A representation of a translatable message in JavaScript source code.
 *
 * <p>Instances are created using a {@link JsMessage.Builder},
 * like this:
 * <pre>
 * JsMessage m = new JsMessage.Builder(key)
 *     .appendPart("Hi ")
 *     .appendPlaceholderReference("firstName")
 *     .appendPart("!")
 *     .setDesc("A welcome message")
 *     .build();
 * </pre>
 *
 */
public class JsMessage {
  static {
    CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.ping();
  }


  /**
   * Message style that could be used for JS code parsing.
   * The enum order is from most relaxed to most restricted.
   */
  public enum Style {
    LEGACY, // All legacy code is completely OK
    RELAX,  // You allowed to use legacy code but it would be reported as warn
    CLOSURE; // Any legacy code is prohibited

    /**
     * Calculates current messages {@link Style} based on the given arguments.
     *
     * @param useClosure if true then use closure style, otherwise not
     * @param allowLegacyMessages if true then allow legacy messages otherwise
     *        not
     * @return the message style based on the given arguments
     */
    static Style getFromParams(boolean useClosure,
        boolean allowLegacyMessages) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((useClosure) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[1]++;
        return allowLegacyMessages ? RELAX : CLOSURE;

      } else {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[2]++;
        return LEGACY;
      }
    }
  }

  private static final String MESSAGE_REPRESENTATION_FORMAT = "{$%s}";
  static {
    CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[2]++;
  }

  private final String key;
  private final String id;
  private final List<CharSequence> parts;
  private final Set<String> placeholders;
  private final String desc;
  private final boolean hidden;
  private final String meaning;

  private final String sourceName;
  private final boolean isAnonymous;
  private final boolean isExternal;

  /**
   * Creates an instance. Client code should use a {@link JsMessage.Builder}.
   *
   * @param key a key that should identify this message in sources; typically
   *     it is the message's name (e.g. {@code "MSG_HELLO"}).
   * @param id an id that *uniquely* identifies the message in the bundle.
   *     It could be either the message name or id generated from the message
   *     content.
   * @param meaning The user-specified meaning of the message. May be null if
   *     the user did not specify an explicit meaning.
   */
  private JsMessage(String sourceName, String key,
      boolean isAnonymous, boolean isExternal,
      String id, List<CharSequence> parts, Set<String> placeholders,
      String desc, boolean hidden, String meaning) {

    Preconditions.checkState(key != null);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[3]++;
    Preconditions.checkState(id != null);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[4]++;

    this.key = key;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[5]++;
    this.id = id;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[6]++;
    this.parts = Collections.unmodifiableList(parts);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[7]++;
    this.placeholders = Collections.unmodifiableSet(placeholders);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[8]++;
    this.desc = desc;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[9]++;
    this.hidden = hidden;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[10]++;
    this.meaning = meaning;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[11]++;

    this.sourceName = sourceName;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[12]++;
    this.isAnonymous = isAnonymous;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[13]++;
    this.isExternal = isExternal;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[14]++;
  }

  /**
   * Gets the message's sourceName.
   */
  public String getSourceName() {
    return sourceName;
  }

  /**
   * Gets the message's key, or name (e.g. {@code "MSG_HELLO"}).
   */
  public String getKey() {
    return key;
  }

  public boolean isAnonymous() {
    return isAnonymous;
  }

  public boolean isExternal() {
    return isExternal;
  }

  /**
   * Gets the message's id, or name (e.g. {@code "92430284230902938293"}).
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the description associated with this message, intended to help
   * translators, or null if this message has no description.
   */
  public String getDesc() {
    return desc;
  }

  /**
   * Gets the meaning annotated to the message, intended to force different
   * translations.
   */
  String getMeaning() {
    return meaning;
  }

  /**
   * Gets whether this message should be hidden from volunteer translators (to
   * reduce the chances of a new feature leak).
   */
  public boolean isHidden() {
    return hidden;
  }

  /**
   * Gets a read-only list of the parts of this message. Each part is either a
   * {@link String} or a {@link PlaceholderReference}.
   */
  public List<CharSequence> parts() {
    return parts;
  }

  /** Gets a read-only set of the registered placeholders in this message. */
  public Set<String> placeholders() {
    return placeholders;
  }

  @Override
  public String toString() {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[15]++;
    StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[1]++;


    for (CharSequence p : parts) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[1]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[2]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[3]++;
}
      sb.append(p.toString());
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[17]++;
    }
    return sb.toString();
  }

  /** @return false iff the message is represented by empty string. */
  public boolean isEmpty() {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[4]++;


    for (CharSequence part : parts) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[4]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[5]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[6]++;
}
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((part.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[3]++;
        return false;

      } else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[4]++;}
    }

    return true;
  }

  @Override
  public boolean equals(Object o) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((o == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[5]++; return true;
} else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[6]++;}
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((o instanceof JsMessage) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[7]++; return false;
} else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[8]++;}
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[22]++;

    JsMessage m = (JsMessage) o;
    return id.equals(m.id) &&
           key.equals(m.key) &&
           isAnonymous == m.isAnonymous &&
           parts.equals(m.parts) &&
           (meaning == null ? m.meaning == null : meaning.equals(m.meaning)) &&
           placeholders.equals(m.placeholders) &&
           (desc == null ? m.desc == null : desc.equals(m.desc)) &&
           (sourceName == null
               ? m.sourceName == null
               : sourceName.equals(m.sourceName)) &&
           hidden == m.hidden;
  }

  @Override
  public int hashCode() {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[23]++;
    int hash = key.hashCode();
    hash = 31 * hash + (isAnonymous ? 1 : 0);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[24]++;
    hash = 31 * hash + id.hashCode();
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[25]++;
    hash = 31 * hash + parts.hashCode();
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[26]++;
    hash = 31 * hash + (desc != null ? desc.hashCode() : 0);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[27]++;
    hash = 31 * hash + (hidden ? 1 : 0);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[28]++;
    hash = 31 * hash + (sourceName != null ? sourceName.hashCode() : 0);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[29]++;
    return hash;
  }

  /** A reference to a placeholder in a translatable message. */
  public static class PlaceholderReference implements CharSequence {

    private final String name;

    PlaceholderReference(String name) {
      this.name = name;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[30]++;
    }

    @Override
    public int length() {
      return name.length();
    }

    @Override
    public char charAt(int index) {
      return name.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
      return name.subSequence(start, end);
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return String.format(MESSAGE_REPRESENTATION_FORMAT, name);
    }

    @Override
    public boolean equals(Object o) {
      return o == this ||
             o instanceof PlaceholderReference &&
             name.equals(((PlaceholderReference) o).name);
    }

    @Override
    public int hashCode() {
      return 31 * name.hashCode();
    }
  }

  /**
   * Contains functionality for creating JS messages. Generates authoritative
   * keys and fingerprints for a message that must stay constant over time.
   *
   * This implementation correctly processes unnamed messages and creates a key
   * for them that looks like MSG_<fingerprint value>.
   */
  public static class Builder {

    private static final Pattern MSG_EXTERNAL_PATTERN =
        Pattern.compile("MSG_EXTERNAL_(\\d+)");

    /**
     * @return an external message id or null if this is not an
     * external message identifier
     */
    private static String getExternalMessageId(String identifier) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[31]++;
      Matcher m = MSG_EXTERNAL_PATTERN.matcher(identifier);
      return m.matches() ? m.group(1) : null;
    }

    private String key;

    private String meaning;

    private String desc;
    private boolean hidden;

    private List<CharSequence> parts = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[32]++;
  }
    private Set<String> placeholders = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[33]++;
  }

    private String sourceName;

    public Builder() {
      this(null);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[34]++;
    }

    /** Creates an instance. */
    public Builder(String key) {
      this.key = key;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[35]++;
    }

    /** Gets the message's key (e.g. {@code "MSG_HELLO"}). */
    public String getKey() {
      return key;
    }

    /**
     * @param key a key that should uniquely identify this message; typically
     *     it is the message's name (e.g. {@code "MSG_HELLO"}).
     */
    public Builder setKey(String key) {
      this.key = key;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[36]++;
      return this;
    }

    /**
     * @param sourceName The message's sourceName.
     */
    public Builder setSourceName(String sourceName) {
      this.sourceName = sourceName;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[37]++;
      return this;
    }

    /**
     * Appends a placeholder reference to the message
     */
    public Builder appendPlaceholderReference(String name) {
      Preconditions.checkNotNull(name, "Placeholder name could not be null");
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[38]++;
      parts.add(new PlaceholderReference(name));
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[39]++;
      placeholders.add(name);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[40]++;
      return this;
    }

    /** Appends a translatable string literal to the message. */
    public Builder appendStringPart(String part) {
      Preconditions.checkNotNull(part,
          "String part of the message could not be null");
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[41]++;
      parts.add(part);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[42]++;
      return this;
    }

    /** Returns the message registered placeholders */
    public Set<String> getPlaceholders() {
      return placeholders;
    }

    /** Sets the description of the message, which helps translators. */
    public Builder setDesc(String desc) {
      this.desc = desc;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[43]++;
      return this;
    }

    /**
     * Sets the programmer-specified meaning of this message, which
     * forces this message to translate differently.
     */
    public Builder setMeaning(String meaning) {
      this.meaning = meaning;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[44]++;
      return this;
    }

    /** Sets whether the message should be hidden from volunteer translators. */
    public Builder setIsHidden(boolean hidden) {
      this.hidden = hidden;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[45]++;
      return this;
    }

    /** Gets whether at least one part has been appended. */
    public boolean hasParts() {
      return !parts.isEmpty();
    }

    public List<CharSequence> getParts() {
      return parts;
    }

    public JsMessage build() {
      return build(null);
    }

    public JsMessage build(IdGenerator idGenerator) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[46]++;
      boolean isAnonymous = false;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[47]++;
      boolean isExternal = false;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[48]++;
      String id = null;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[49]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getKey() == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[9]++;
        // Before constructing a message we need to change unnamed messages name
        // to the unique one.
        key = JsMessageVisitor.MSG_PREFIX + fingerprint(getParts());
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[50]++;
        isAnonymous = true;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[51]++;

      } else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[10]++;}
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[52]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isAnonymous) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[11]++;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[53]++;
        String externalId = getExternalMessageId(key);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[54]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((externalId != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[13]++;
          isExternal = true;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[55]++;
          id = externalId;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[56]++;

        } else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[14]++;}

      } else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[12]++;}
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[57]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isExternal) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[15]++;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[58]++;
        String defactoMeaning = meaning != null ? meaning : key;
        id = idGenerator == null ? defactoMeaning :
            idGenerator.generateId(defactoMeaning, parts);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[59]++;

      } else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[16]++;}

      return new JsMessage(sourceName, key, isAnonymous, isExternal, id, parts,
          placeholders, desc, hidden, meaning);
    }

    /**
     * Generates a compact uppercase alphanumeric text representation of a
     * 63-bit fingerprint of the content parts of a message.
     */
    private static String fingerprint(List<CharSequence> messageParts) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[60]++;
      StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[61]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[7]++;


      for (CharSequence part : messageParts) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[7]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[8]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[9]++;
}
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[62]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((part instanceof JsMessage.PlaceholderReference) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[17]++;
          sb.append(part.toString());
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[63]++;

        } else {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[18]++;
          sb.append(part);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[64]++;
        }
      }
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[65]++;
      long nonnegativeHash = Long.MAX_VALUE & Hash.hash64(sb.toString());
      return Long.toString(nonnegativeHash, 36).toUpperCase();
    }
  }

  /**
   * This class contains routines for hashing.
   *
   * <p>The hash takes a byte array representing arbitrary data (a
   * number, String, or Object) and turns it into a small, hopefully
   * unique, number. There are additional convenience functions which
   * hash int, long, and String types.
   *
   * <p><b>Note</b>: this hash has weaknesses in the two
   * most-significant key bits and in the three least-significant seed
   * bits. The weaknesses are small and practically speaking, will not
   * affect the distribution of hash values. Still, it would be good
   * practice not to choose seeds 0, 1, 2, 3, ..., n to yield n,
   * independent hash functions. Use pseudo-random seeds instead.
   *
   * <p>This code is based on the work of Craig Silverstein and Sanjay
   * Ghemawat in, then forked from com.google.common.
   *
   * <p>The original code for the hash function is courtesy
   * <a href="http://burtleburtle.net/bob/hash/evahash.html">Bob Jenkins</a>.
   *
   * <p>TODO: Add stream hashing functionality.
   */
  final static class Hash {
    private Hash() {}

    /** Default hash seed (64 bit) */
    private static final long SEED64 =
        0x2b992ddfa23249d6L; // part of pi, arbitrary

    /** Hash constant (64 bit) */
    private static final long CONSTANT64 =
        0xe08c1d668b756f82L; // part of golden ratio, arbitrary


    /******************
     * STRING HASHING *
     ******************/

    /**
     * Hash a string to a 64 bit value. The digits of pi are used for
     * the hash seed.
     *
     * @param value the string to hash
     * @return 64 bit hash value
     */
    static long hash64(@Nullable String value) {
      return hash64(value, SEED64);
    }

    /**
     * Hash a string to a 64 bit value using the supplied seed.
     *
     * @param value the string to hash
     * @param seed the seed
     * @return 64 bit hash value
     */
    private static long hash64(@Nullable String value, long seed) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[66]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[19]++;
        return hash64(null, 0, 0, seed);

      } else {
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[20]++;}
      return hash64(value.getBytes(), seed);
    }

    /**
     * Hash byte array to a 64 bit value using the supplied seed.
     *
     * @param value the bytes to hash
     * @param seed the seed
     * @return 64 bit hash value
     */
    private static long hash64(byte[] value, long seed) {
      return hash64(value, 0, value == null ? 0 : value.length, seed);
    }

    /**
     * Hash byte array to a 64 bit value using the supplied seed.
     *
     * @param value the bytes to hash
     * @param offset the starting position of value where bytes are
     * used for the hash computation
     * @param length number of bytes of value that are used for the
     * hash computation
     * @param seed the seed
     * @return 64 bit hash value
     */
    @SuppressWarnings("fallthrough")
    private static long hash64(
        byte[] value, int offset, int length, long seed) {
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[67]++;
      long a = CONSTANT64;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[68]++;
      long b = a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[69]++;
      long c = seed;
      int keylen;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[70]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[10]++;


int CodeCoverConditionCoverageHelper_C11;

      for (keylen = length;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((keylen >= 24) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); keylen -= 24, offset += 24) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[10]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[11]--;
  CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.loops[12]++;
}
        a += word64At(value, offset);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[71]++;
        b += word64At(value, offset + 8);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[72]++;
        c += word64At(value, offset + 16);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[73]++;

        // Mix
        a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[74]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[75]++; a ^= c >>> 43;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[76]++;
        b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[77]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[78]++; b ^= a << 9;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[79]++;
        c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[80]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[81]++; c ^= b >>> 8;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[82]++;
        a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[83]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[84]++; a ^= c >>> 38;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[85]++;
        b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[86]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[87]++; b ^= a << 23;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[88]++;
        c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[89]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[90]++; c ^= b >>> 5;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[91]++;
        a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[92]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[93]++; a ^= c >>> 35;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[94]++;
        b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[95]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[96]++; b ^= a << 49;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[97]++;
        c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[98]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[99]++; c ^= b >>> 11;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[100]++;
        a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[101]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[102]++; a ^= c >>> 12;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[103]++;
        b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[104]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[105]++; b ^= a << 18;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[106]++;
        c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[107]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[108]++; c ^= b >>> 22;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[109]++;
      }

      c += length;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[110]++;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[111]++;
      switch (keylen) { // deal with rest. Cases fall through
        case 23:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[21]++;
          c += ((long) value[offset + 22]) << 56;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[112]++;
        case 22:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[22]++;
          c += (value[offset + 21] & 0xffL) << 48;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[113]++;
        case 21:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[23]++;
          c += (value[offset + 20] & 0xffL) << 40;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[114]++;
        case 20:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[24]++;
          c += (value[offset + 19] & 0xffL) << 32;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[115]++;
        case 19:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[25]++;
          c += (value[offset + 18] & 0xffL) << 24;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[116]++;
        case 18:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[26]++;
          c += (value[offset + 17] & 0xffL) << 16;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[117]++;
        case 17:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[27]++;
          c += (value[offset + 16] & 0xffL) << 8;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[118]++;
          // the first byte of c is reserved for the length
        case 16:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[28]++;
          b += word64At(value, offset + 8);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[119]++;
          a += word64At(value, offset);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[120]++;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[121]++;
          break;
        case 15:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[29]++;
          b += (value[offset + 14] & 0xffL) << 48;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[122]++;
        case 14:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[30]++;
          b += (value[offset + 13] & 0xffL) << 40;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[123]++;
        case 13:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[31]++;
          b += (value[offset + 12] & 0xffL) << 32;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[124]++;
        case 12:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[32]++;
          b += (value[offset + 11] & 0xffL) << 24;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[125]++;
        case 11:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[33]++;
          b += (value[offset + 10] & 0xffL) << 16;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[126]++;
        case 10:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[34]++;
          b += (value[offset + 9] & 0xffL) << 8;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[127]++;
        case 9:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[35]++;
          b += (value[offset + 8] & 0xffL);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[128]++;
        case 8:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[36]++;
          a += word64At(value, offset);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[129]++;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[130]++;
          break;
        case 7:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[37]++;
          a += (value[offset + 6] & 0xffL) << 48;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[131]++;
        case 6:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[38]++;
          a += (value[offset + 5] & 0xffL) << 40;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[132]++;
        case 5:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[39]++;
          a += (value[offset + 4] & 0xffL) << 32;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[133]++;
        case 4:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[40]++;
          a += (value[offset + 3] & 0xffL) << 24;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[134]++;
        case 3:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[41]++;
          a += (value[offset + 2] & 0xffL) << 16;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[135]++;
        case 2:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[42]++;
          a += (value[offset + 1] & 0xffL) << 8;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[136]++;
        case 1:
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[43]++;
          a += (value[offset + 0] & 0xffL);
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[137]++; default : CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.branches[44]++;
          // case 0: nothing left to add
      }
      return mix64(a, b, c);
    }

    private static long word64At(byte[] bytes, int offset) {
      return (bytes[offset + 0] & 0xffL)
          + ((bytes[offset + 1] & 0xffL) << 8)
          + ((bytes[offset + 2] & 0xffL) << 16)
          + ((bytes[offset + 3] & 0xffL) << 24)
          + ((bytes[offset + 4] & 0xffL) << 32)
          + ((bytes[offset + 5] & 0xffL) << 40)
          + ((bytes[offset + 6] & 0xffL) << 48)
          + ((bytes[offset + 7] & 0xffL) << 56);
    }

    /**
     * Mixes longs a, b, and c, and returns the final value of c.
     */
    private static long mix64(long a, long b, long c) {
      a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[138]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[139]++; a ^= c >>> 43;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[140]++;
      b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[141]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[142]++; b ^= a << 9;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[143]++;
      c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[144]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[145]++; c ^= b >>> 8;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[146]++;
      a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[147]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[148]++; a ^= c >>> 38;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[149]++;
      b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[150]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[151]++; b ^= a << 23;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[152]++;
      c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[153]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[154]++; c ^= b >>> 5;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[155]++;
      a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[156]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[157]++; a ^= c >>> 35;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[158]++;
      b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[159]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[160]++; b ^= a << 49;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[161]++;
      c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[162]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[163]++; c ^= b >>> 11;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[164]++;
      a -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[165]++; a -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[166]++; a ^= c >>> 12;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[167]++;
      b -= c;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[168]++; b -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[169]++; b ^= a << 18;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[170]++;
      c -= a;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[171]++; c -= b;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[172]++; c ^= b >>> 22;
CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x.statements[173]++;
      return c;
    }
  }

  public interface IdGenerator {
    /**
     * Generate the ID for the message. Messages with the same messageParts
     * and meaning will get the same id. Messages with the same id
     * will get the same translation.
     *
     * @param meaning The programmer-specified meaning. If no {@code @meaning}
     *     annotation appears, we will use the name of the variable it's
     *     assigned to. If the variable is unnamed, then we will just
     *     use a fingerprint of the message.
     * @param messageParts The parts of the message, including the main
     *     message text.
     */
    String generateId(String meaning, List<CharSequence> messageParts);
  }
}

class CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x ());
  }
    public static long[] statements = new long[174];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JsMessage.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$34yo5z3cm21gfbxlu7tw0x () {
    super("com.google.javascript.jscomp.JsMessage.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 173; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JsMessage.java");
      for (int i = 1; i <= 173; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

