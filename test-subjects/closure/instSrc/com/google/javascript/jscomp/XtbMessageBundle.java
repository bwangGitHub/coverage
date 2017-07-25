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
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.*;
import java.util.*;

import javax.annotation.Nullable;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * A MessageBundle that parses messages from an XML Translation Bundle (XTB)
 * file.
 *
 */
@SuppressWarnings("sunapi")
public class XtbMessageBundle implements MessageBundle {
  static {
    CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.ping();
  }

  private static final SecureEntityResolver NOOP_RESOLVER
      = new SecureEntityResolver();
  static {
    CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[1]++;
  }

  private final Map<String, JsMessage> messages;
  private final JsMessage.IdGenerator idGenerator;

  public XtbMessageBundle(
      InputStream xtb, @Nullable String projectId,
      @SuppressWarnings("unused") boolean unused) {
    this(xtb, projectId);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[2]++;
  }

  /**
   * Creates an instance and initializes it with the messages in an XTB file.
   *
   * @param xtb  the XTB file as a byte stream
   * @param projectId  the translation console project id (i.e. name)
   */
  public XtbMessageBundle(InputStream xtb, @Nullable String projectId) {
    Preconditions.checkState(!"".equals(projectId));
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[3]++;
    this.messages = Maps.newHashMap();
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[4]++;
    this.idGenerator = new GoogleJsMessageIdGenerator(projectId);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[5]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[6]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[7]++;
      // Use a SAX parser for speed and less memory usage.
      SAXParser parser = createSAXParser();
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[8]++;
      XMLReader reader = parser.getXMLReader();
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[9]++;
      Handler contentHandler = new Handler();
      reader.setContentHandler(contentHandler);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[10]++;
      reader.parse(new InputSource(xtb));
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[11]++;
    } catch (ParserConfigurationException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[2]++;
      throw new RuntimeException(e);
    } catch (SAXException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[3]++;
      throw new RuntimeException(e);
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[4]++;
      throw new RuntimeException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[1]++;
}
  }
  }

  // Inlined from guava-internal.
  private SAXParser createSAXParser()
      throws ParserConfigurationException, SAXException {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[12]++;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(false);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[13]++;
    factory.setXIncludeAware(false);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[14]++;
    factory.setFeature(
        "http://xml.org/sax/features/external-general-entities", false);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[15]++;
    factory.setFeature(
        "http://xml.org/sax/features/external-parameter-entities",false);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[16]++;
    factory.setFeature(
        "http://apache.org/xml/features/nonvalidating/load-external-dtd",
        false);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[17]++;
    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[18]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[19]++;

    SAXParser parser = factory.newSAXParser();
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[20]++;
    XMLReader xmlReader = parser.getXMLReader();
    xmlReader.setEntityResolver(NOOP_RESOLVER);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[21]++;
    return parser;
  }

  @Override
  public JsMessage getMessage(String id) {
    return messages.get(id);
  }

  @Override
  public JsMessage.IdGenerator idGenerator() {
    return idGenerator;
  }

  @Override
  public Iterable<JsMessage> getAllMessages() {
    return Iterables.unmodifiableIterable(messages.values());
  }

  /**
   * A {@link ContentHandler} that creates a {@link JsMessage} for each message
   * parsed from an XML Translation Bundle (XTB) file.
   */
  private class Handler implements ContentHandler {
    private static final String BUNDLE_ELEM_NAME = "translationbundle";
    private static final String LANG_ATT_NAME = "lang";

    private static final String TRANSLATION_ELEM_NAME = "translation";
    private static final String MESSAGE_ID_ATT_NAME = "id";

    private static final String PLACEHOLDER_ELEM_NAME = "ph";
    private static final String PLACEHOLDER_NAME_ATT_NAME = "name";

    String lang;
    JsMessage.Builder msgBuilder;

    @Override
    public void setDocumentLocator(Locator locator) {}

    @Override
    public void startDocument() {}

    @Override
    public void endDocument() {}

    @Override
    public void startPrefixMapping(String prefix, String uri) {}

    @Override
    public void endPrefixMapping(String prefix) {}

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[22]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((BUNDLE_ELEM_NAME.equals(qName)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[5]++;
        Preconditions.checkState(lang == null);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[23]++;
        lang = atts.getValue(LANG_ATT_NAME);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[24]++;
        Preconditions.checkState(lang != null && !lang.isEmpty());
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[25]++;

      } else {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[6]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[26]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((TRANSLATION_ELEM_NAME.equals(qName)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[7]++;
        Preconditions.checkState(msgBuilder == null);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[27]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[28]++;
        String id = atts.getValue(MESSAGE_ID_ATT_NAME);
        Preconditions.checkState(id != null && !id.isEmpty());
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[29]++;
        msgBuilder = new JsMessage.Builder(id);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[30]++;

      } else {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[8]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[31]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((PLACEHOLDER_ELEM_NAME.equals(qName)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[9]++;
        Preconditions.checkState(msgBuilder != null);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[32]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[33]++;
        String phRef = atts.getValue(PLACEHOLDER_NAME_ATT_NAME);
        phRef = JsMessageVisitor.toLowerCamelCaseWithNumericSuffixes(phRef);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[34]++;
        msgBuilder.appendPlaceholderReference(phRef);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[35]++;

      } else {
  CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[10]++;}
}
}
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((TRANSLATION_ELEM_NAME.equals(qName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[11]++;
        Preconditions.checkState(msgBuilder != null);
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[37]++;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[38]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((msgBuilder.hasParts()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[13]++;
          msgBuilder.appendStringPart("");
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[39]++;

        } else {
  CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[14]++;}
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[40]++;
        String key = msgBuilder.getKey();
        messages.put(key, msgBuilder.build());
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[41]++;
        msgBuilder = null;
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[42]++;

      } else {
  CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[12]++;}
    }

    @Override
    public void characters(char ch[], int start, int length) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((msgBuilder != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[15]++;
        // Append a string literal to the message.
        msgBuilder.appendStringPart(String.valueOf(ch, start, length));
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[44]++;

      } else {
  CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[16]++;}
    }

    @Override
    public void ignorableWhitespace(char ch[], int start, int length) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[45]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((msgBuilder != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[17]++;
        // Preserve whitespace in messages.
        msgBuilder.appendStringPart(String.valueOf(ch, start, length));
CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.statements[46]++;

      } else {
  CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up.branches[18]++;}
    }

    @Override
    public void processingInstruction(String target, String data) {}

    @Override
    public void skippedEntity(String name) {}
  }

  /**
   * A secure EntityResolver that returns an empty string in response to
   * any attempt to resolve an external entity. The class is used by our
   * secure version of the internal saxon SAX parser.
   */
  private static final class SecureEntityResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) {
      return new InputSource(new StringReader(""));
    }
  }
}

class CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up ());
  }
    public static long[] statements = new long[47];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.XtbMessageBundle.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$21h476a9m1if6wg5v0pbkjkej0ordn5up () {
    super("com.google.javascript.jscomp.XtbMessageBundle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 46; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.XtbMessageBundle.java");
      for (int i = 1; i <= 46; i++) {
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
    for (int i = 1; i <= 7; i++) {
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

