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
 *   Bob Jervis
 *   Google Inc.
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

package com.google.javascript.rhino.jstype;

import com.google.common.collect.ImmutableList;
import com.google.javascript.rhino.Node;

/**
 * A builder class for function and arrow types.
 *
 * If you need to build an interface constructor,
 * use {@link JSTypeRegistry#createInterfaceType}.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public final class FunctionBuilder {
  static {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.ping();
  }

  private final JSTypeRegistry registry;
  private String name = null;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[1]++;
  }
  private Node sourceNode = null;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[2]++;
  }
  private Node parametersNode = null;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[3]++;
  }
  private JSType returnType = null;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[4]++;
  }
  private JSType typeOfThis = null;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[5]++;
  }
  private TemplateTypeMap templateTypeMap = null;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[6]++;
  }
  private boolean inferredReturnType = false;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[7]++;
  }
  private boolean isConstructor = false;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[8]++;
  }
  private boolean isNativeType = false;
  {
    CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[9]++;
  }

  public FunctionBuilder(JSTypeRegistry registry) {
    this.registry = registry;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[10]++;
  }

  /** Set the name of the function type. */
  public FunctionBuilder withName(String name) {
    this.name = name;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[11]++;
    return this;
  }

  /** Set the source node of the function type. */
  public FunctionBuilder withSourceNode(Node sourceNode) {
    this.sourceNode = sourceNode;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[12]++;
    return this;
  }

  /** Set the parameters of the function type from a FunctionParamBuilder. */
  public FunctionBuilder withParams(FunctionParamBuilder params) {
    this.parametersNode = params.build();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[13]++;
    return this;
  }

  /**
   * Set the parameters of the function type with a specially-formatted node.
   */
  public FunctionBuilder withParamsNode(Node parametersNode) {
    this.parametersNode = parametersNode;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[14]++;
    return this;
  }

  /** Set the return type. */
  public FunctionBuilder withReturnType(JSType returnType) {
    this.returnType = returnType;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[15]++;
    return this;
  }

  /** Set the return type and whether it's inferred. */
  public FunctionBuilder withReturnType(JSType returnType, boolean inferred) {
    this.returnType = returnType;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[16]++;
    this.inferredReturnType = inferred;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[17]++;
    return this;
  }

  /** Sets an inferred return type. */
  public FunctionBuilder withInferredReturnType(JSType returnType) {
    this.returnType = returnType;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[18]++;
    this.inferredReturnType = true;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[19]++;
    return this;
  }

  /** Set the "this" type. */
  public FunctionBuilder withTypeOfThis(JSType typeOfThis) {
    this.typeOfThis = typeOfThis;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[20]++;
    return this;
  }

  /** Set the template name. */
  public FunctionBuilder withTemplateKeys(ImmutableList<String> templateKeys) {
    this.templateTypeMap = registry.createTemplateTypeMap(templateKeys, null);
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[21]++;
    return this;
  }

  /** Make this a constructor. */
  public FunctionBuilder forConstructor() {
    this.isConstructor = true;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[22]++;
    return this;
  }

  /** Set whether this is a constructor. */
  public FunctionBuilder setIsConstructor(boolean isConstructor) {
    this.isConstructor = isConstructor;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[23]++;
    return this;
  }

  /** Make this a native type. */
  FunctionBuilder forNativeType() {
    this.isNativeType = true;
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[24]++;
    return this;
  }

  /** Copies all the information from another function type. */
  public FunctionBuilder copyFromOtherFunction(FunctionType otherType) {
    this.name = otherType.getReferenceName();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[25]++;
    this.sourceNode = otherType.getSource();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[26]++;
    this.parametersNode = otherType.getParametersNode();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[27]++;
    this.returnType = otherType.getReturnType();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[28]++;
    this.typeOfThis = otherType.getTypeOfThis();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[29]++;
    this.templateTypeMap = otherType.getTemplateTypeMap();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[30]++;
    this.isConstructor = otherType.isConstructor();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[31]++;
    this.isNativeType = otherType.isNativeObjectType();
CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9.statements[32]++;
    return this;
  }

  /** Construct a new function type. */
  public FunctionType build() {
    return new FunctionType(registry, name, sourceNode,
        new ArrowType(registry, parametersNode, returnType, inferredReturnType),
        typeOfThis, templateTypeMap, isConstructor, isNativeType);
  }
}

class CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9 ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$88ajuz4hqiibz2p3z092dmvmuzyo9y9 () {
    super("com.google.javascript.rhino.jstype.FunctionBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.FunctionBuilder.java");
      for (int i = 1; i <= 32; i++) {
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

