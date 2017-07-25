/*
 * Copyright 2011 The Closure Compiler Authors.
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
import com.google.common.base.Predicates;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSDocInfo.Marker;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.SourcePosition;
import com.google.javascript.rhino.jstype.EnumType;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.SimpleReference;
import com.google.javascript.rhino.jstype.SimpleSlot;
import com.google.javascript.rhino.jstype.StaticReference;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.StaticSymbolTable;
import com.google.javascript.rhino.jstype.UnionType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.annotation.Nullable;

/**
 * A symbol table for people that want to use Closure Compiler as an indexer.
 *
 * Contains an index of all the symbols in the code within a compilation
 * job. The API is designed for people who want to visit all the symbols, rather
 * than people who want to lookup a specific symbol by a certain key.
 *
 * We can use this to combine different types of symbol tables. For example,
 * one class might have a {@code StaticSymbolTable} of all variable references,
 * and another class might have a {@code StaticSymbolTable} of all type names
 * in JSDoc comments. This class allows you to combine them into a unified
 * index.
 *
 * Most passes build their own "partial" symbol table that implements the same
 * interface (StaticSymbolTable, StaticSlot, and friends). Individual compiler
 * passes usually need more or less metadata about the certainty of symbol
 * information. Building a complete symbol table with all the necessary metadata
 * for all passes would be too slow. However, as long as these "partial" symbol
 * tables implement the proper interfaces, we should be able to add them to this
 * symbol table to make it more complete.
 *
 * If clients want fast lookup, they should build their own wrapper around
 * this symbol table that indexes symbols or references by the desired lookup
 * key.
 *
 * By design, when this symbol table creates symbols for types, it tries
 * to mimic the symbol table you would get in an OO language. For example,
 * the "type Foo" and "the constructor that creates objects of type Foo"
 * are the same symbol. The types of "Foo.prototype" and "new Foo()" also
 * have the same symbol. Although JSCompiler internally treats these as
 * distinct symbols, we assume that most clients will not care about
 * the distinction.
 *
 * @see #addSymbolsFrom For more information on how to write plugins for this
 *    symbol table.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public final class SymbolTable
    implements StaticSymbolTable<SymbolTable.Symbol, SymbolTable.Reference> {
  static {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.ping();
  }

  private static final Logger logger =
      Logger.getLogger(SymbolTable.class.getName());
  static {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[1]++;
  }

  /**
   * The name we use for the JavaScript built-in Global object.  It's
   * anonymous in JavaScript, so we have to give it an invalid identifier
   * to avoid conflicts with user-defined property names.
   */
  public static final String GLOBAL_THIS = "*global*";
  static {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[2]++;
  }

  /**
   * All symbols in the program, uniquely identified by the node where
   * they're declared and their name.
   */
  private final Table<Node, String, Symbol> symbols = HashBasedTable.create();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[3]++;
  }

  /**
   * All syntactic scopes in the program, uniquely identified by the node where
   * they're declared.
   */
  private final Map<Node, SymbolScope> scopes = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[4]++;
  }

  /**
   * All JSDocInfo in the program.
   */
  private final List<JSDocInfo> docInfos = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[5]++;
  }

  private SymbolScope globalScope = null;
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[6]++;
  }

  private final JSTypeRegistry registry;

  /**
   * Clients should get a symbol table by asking the compiler at the end
   * of a compilation job.
   */
  SymbolTable(JSTypeRegistry registry) {
    this.registry = registry;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[7]++;
  }

  @Override
  public Iterable<Reference> getReferences(Symbol symbol) {
    return Collections.unmodifiableCollection(symbol.references.values());
  }

  public List<Reference> getReferenceList(Symbol symbol) {
    return ImmutableList.copyOf(symbol.references.values());
  }

  @Override
  public Iterable<Symbol> getAllSymbols() {
    return Collections.unmodifiableCollection(symbols.values());
  }

  /**
   * Get the symbols in their natural ordering.
   * Always returns a mutable list.
   */
  public List<Symbol> getAllSymbolsSorted() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[8]++;
    List<Symbol> sortedSymbols = Lists.newArrayList(symbols.values());
    Collections.sort(sortedSymbols, getNaturalSymbolOrdering());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[9]++;
    return sortedSymbols;
  }

  /**
   * Gets the 'natural' ordering of symbols.
   *
   * Right now, we only guarantee that symbols in the global scope will come
   * before symbols in local scopes. After that, the order is deterministic but
   * undefined.
   */
  public Ordering<Symbol> getNaturalSymbolOrdering() {
    return SYMBOL_ORDERING;
  }

  @Override
  public SymbolScope getScope(Symbol slot) {
    return slot.scope;
  }

  public Collection<JSDocInfo> getAllJSDocInfo() {
    return Collections.unmodifiableList(docInfos);
  }

  /**
   * Declare a symbol after the main symbol table was constructed.
   * Throws an exception if you try to declare a symbol twice.
   */
  public Symbol declareInferredSymbol(
      SymbolScope scope, String name, Node declNode) {
    return declareSymbol(name, null, true, scope, declNode, null);
  }

  /**
   * Gets the scope that contains the given node.
   * If {@code n} is a function name, we return the scope that contains the
   * function, not the function itself.
   */
  public SymbolScope getEnclosingScope(Node n) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[10]++;
    Node current = n.getParent();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.getParent().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[1]++;
      current = current.getParent();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[12]++;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[2]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

    for (;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); current = current.getParent()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[1]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[2]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[3]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((scopes.containsKey(current)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[3]++;
        return scopes.get(current);

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[4]++;}
    }
    return null;
  }

  /**
   * If {@code sym} is a function, try to find a Symbol for
   * a parameter with the given name.
   *
   * Returns null if we couldn't find one.
   *
   * Notice that this just makes a best effort, and may not be able
   * to find parameters for non-conventional function definitions.
   * For example, we would not be able to find "y" in this code:
   * <code>
   * var x = x() ? function(y) {} : function(y) {};
   * </code>
   */
  public Symbol getParameterInFunction(Symbol sym, String paramName) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[15]++;
    SymbolScope scope = getScopeInFunction(sym);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[5]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[17]++;
      Symbol param = scope.getSlot(paramName);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((param.scope == scope) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[7]++;
        return param;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[6]++;}
    return null;
  }

  private SymbolScope getScopeInFunction(Symbol sym) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[19]++;
    FunctionType type = sym.getFunctionType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[9]++;
      return null;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[10]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[21]++;

    Node functionNode = type.getSource();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((functionNode == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[11]++;
      return null;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[12]++;}

    return scopes.get(functionNode);
  }

  /**
   * All local scopes are associated with a function, and some functions
   * are associated with a symbol. Returns the symbol associated with the given
   * scope.
   */
  public Symbol getSymbolForScope(SymbolScope scope) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((scope.getSymbolForScope() == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[13]++;
      scope.setSymbolForScope(findSymbolForScope(scope));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[24]++;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[14]++;}
    return scope.getSymbolForScope();
  }

  /**
   * Find the symbol associated with the given scope.
   * Notice that we won't always be able to figure out this association
   * dynamically, so sometimes we'll just create the association when we
   * create the scope.
   */
  private Symbol findSymbolForScope(SymbolScope scope) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[25]++;
    Node rootNode = scope.getRootNode();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((rootNode.getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[15]++;
      return globalScope.getSlot(GLOBAL_THIS);

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[16]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((rootNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[17]++;
      return null;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[18]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[28]++;

    String name = NodeUtil.getBestLValueName(
        NodeUtil.getBestLValue(rootNode));
    return name == null ? null : scope.getParentScope().getQualifiedSlot(name);
  }

  /**
   * Get all symbols associated with the type of the given symbol.
   *
   * For example, given a variable x declared as
   * /* @type {Array|Date} /
   * var x = f();
   * this will return the constructors for Array and Date.
   */
  public Iterable<Symbol> getAllSymbolsForTypeOf(Symbol sym) {
    return getAllSymbolsForType(sym.getType());
  }

  /**
   * Returns the global scope.
   */
  public SymbolScope getGlobalScope() {
    return globalScope;
  }

  /**
   * Gets the symbol for the given constructor or interface.
   */
  public Symbol getSymbolDeclaredBy(FunctionType fn) {
    Preconditions.checkState(fn.isConstructor() || fn.isInterface());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[29]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[30]++;
    ObjectType instanceType = fn.getInstanceType();
    return getSymbolForName(fn.getSource(), instanceType.getReferenceName());
  }

  /**
   * Gets the symbol for the given enum.
   */
  public Symbol getSymbolDeclaredBy(EnumType enumType) {
    return getSymbolForName(null,
        enumType.getElementsType().getReferenceName());
  }

  /**
   * Gets the symbol for the prototype if this is the symbol for a constructor
   * or interface.
   */
  public Symbol getSymbolForInstancesOf(Symbol sym) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[31]++;
    FunctionType fn = sym.getFunctionType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((fn != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((fn.isNominalConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[19]++;
      return getSymbolForInstancesOf(fn);

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[20]++;}
    return null;
  }

  /**
   * Gets the symbol for the prototype of the given constructor or interface.
   */
  public Symbol getSymbolForInstancesOf(FunctionType fn) {
    Preconditions.checkState(fn.isConstructor() || fn.isInterface());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[33]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[34]++;
    ObjectType pType = fn.getPrototype();
    return getSymbolForName(fn.getSource(), pType.getReferenceName());
  }

  private Symbol getSymbolForName(Node source, String name) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((globalScope == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[21]++;
      return null;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[22]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[36]++;

    SymbolScope scope = source == null ?
        globalScope : getEnclosingScope(source);

    // scope will sometimes be null if one of the type-stripping passes
    // was run, and the symbol isn't in the AST anymore.
    return scope == null ? null : scope.getQualifiedSlot(name);
  }

  /**
   * Gets all symbols associated with the given type.
   * For union types, this may be multiple symbols.
   * For instance types, this will return the constructor of
   * that instance.
   */
  public List<Symbol> getAllSymbolsForType(JSType type) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[23]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[24]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[38]++;

    UnionType unionType = type.toMaybeUnionType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((unionType != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[25]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[40]++;
      List<Symbol> result = Lists.newArrayListWithExpectedSize(2);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[41]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[4]++;


      for (JSType alt : unionType.getAlternates()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[4]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[5]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[6]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[42]++;
        // Our type system never has nested unions.
        Symbol altSym = getSymbolForTypeHelper(alt, true);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[43]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((altSym != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[27]++;
          result.add(altSym);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[44]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[28]++;}
      }
      return result;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[26]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[45]++;
    Symbol result = getSymbolForTypeHelper(type, true);
    return result == null
        ? ImmutableList.<Symbol>of() : ImmutableList.of(result);
  }

  /**
   * Gets all symbols associated with the given type.
   * If there is more that one symbol associated with the given type,
   * return null.
   * @param type The type.
   * @param linkToCtor If true, we should link instance types back
   *     to their constructor function. If false, we should link
   *     instance types back to their prototype. See the comments
   *     at the top of this file for more information on how
   *     our internal type system is more granular than Symbols.
   */
  private Symbol getSymbolForTypeHelper(JSType type, boolean linkToCtor) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[46]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[29]++;
      return null;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[30]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[47]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type.isGlobalThisType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[31]++;
      return globalScope.getSlot(GLOBAL_THIS);

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[32]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[48]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((type.isNominalConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[33]++;
      return linkToCtor ?
          globalScope.getSlot("Function") :
          getSymbolDeclaredBy(type.toMaybeFunctionType());

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[34]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[49]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((type.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[35]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[50]++;
      FunctionType ownerFn = ((ObjectType) type).getOwnerFunction();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[51]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((ownerFn.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ownerFn.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[37]++;
        return null;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[38]++;}
      return linkToCtor ?
          getSymbolDeclaredBy(ownerFn) :
          getSymbolForInstancesOf(ownerFn);

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[36]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[52]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((type.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[39]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[53]++;
      FunctionType ownerFn = ((ObjectType) type).getConstructor();
      return linkToCtor ?
          getSymbolDeclaredBy(ownerFn) :
          getSymbolForInstancesOf(ownerFn);

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[40]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[54]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((type.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[41]++;
      return linkToCtor ?
          globalScope.getSlot("Function") :
          globalScope.getQualifiedSlot("Function.prototype");

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[42]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[55]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((type.autoboxesTo() != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[43]++;
      return getSymbolForTypeHelper(type.autoboxesTo(), linkToCtor);

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[44]++;
      return null;
    }
}
}
}
}
}
  }

  public String toDebugString() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[56]++;
    StringBuilder builder = new StringBuilder();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[57]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[7]++;


    for (Symbol symbol : getAllSymbols()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[7]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[8]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[9]++;
}
      toDebugString(builder, symbol);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[58]++;
    }
    return builder.toString();
  }

  private void toDebugString(StringBuilder builder, Symbol symbol) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[59]++;
    SymbolScope scope = symbol.scope;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[60]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((scope.isGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[45]++;
      builder.append(
          String.format("'%s' : in global scope:\n", symbol.getName()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[61]++;

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[46]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[62]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((scope.getRootNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[47]++;
      builder.append(
          String.format("'%s' : in scope %s:%d\n",
              symbol.getName(),
              scope.getRootNode().getSourceFileName(),
              scope.getRootNode().getLineno()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[63]++;

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[48]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[64]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((scope.getSymbolForScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[49]++;
      builder.append(
          String.format("'%s' : in scope %s\n", symbol.getName(),
              scope.getSymbolForScope().getName()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[65]++;

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[50]++;
      builder.append(
          String.format("'%s' : in unknown scope\n", symbol.getName()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[66]++;
    }
}
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[67]++;

    int refCount = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[68]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[10]++;


    for (Reference ref : getReferences(symbol)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[10]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[11]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[12]++;
}
      builder.append(
          String.format("  Ref %d: %s:%d\n",
              refCount,
              ref.getNode().getSourceFileName(),
              ref.getNode().getLineno()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[69]++;
      refCount++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[70]++;
    }
  }

  /**
   * Make sure all the given scopes in {@code otherSymbolTable}
   * are in this symbol table.
   */
  <S extends StaticScope<JSType>>
  void addScopes(Collection<S> scopes) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[71]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[13]++;


    for (S scope : scopes) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[13]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[14]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[15]++;
}
      createScopeFrom(scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[72]++;
    }
  }

  /** Finds all the scopes and adds them to this symbol table. */
  void findScopes(AbstractCompiler compiler, Node externs, Node root) {
    NodeTraversal.traverseRoots(
        compiler,
        Lists.newArrayList(externs, root),
        new NodeTraversal.AbstractScopedCallback() {
          @Override
          public void enterScope(NodeTraversal t) {
            createScopeFrom(t.getScope());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[74]++;
          }

          @Override
          public void visit(NodeTraversal t, Node n, Node p) {}
        });
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[73]++;
  }

  /** Gets all the scopes in this symbol table. */
  public Collection<SymbolScope> getAllScopes() {
    return Collections.unmodifiableCollection(scopes.values());
  }

  /**
   * Finds anonymous functions in local scopes, and gives them names
   * and symbols. They will show up as local variables with names
   * "function%0", "function%1", etc.
   */
  public void addAnonymousFunctions() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[75]++;
    TreeSet<SymbolScope> scopes = Sets.newTreeSet(LEXICAL_SCOPE_ORDERING);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[76]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[16]++;


    for (SymbolScope scope : getAllScopes()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[16]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[17]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[18]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((scope.isLexicalScope()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[51]++;
        scopes.add(scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[78]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[52]++;}
    }
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[79]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[19]++;



    for (SymbolScope scope : scopes) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[19]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[20]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[21]++;
}
      addAnonymousFunctionsInScope(scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[80]++;
    }
  }

  private void addAnonymousFunctionsInScope(SymbolScope scope) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[81]++;
    Symbol sym = getSymbolForScope(scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[82]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((sym == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[53]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[83]++;
int CodeCoverConditionCoverageHelper_C29;
      // JSCompiler has no symbol for this scope. Check to see if it's a
      // local function. If it is, give it a name.
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (512)) == 0 || true) &&
 ((scope.isLexicalScope()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (256)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (128)) == 0 || true) &&
 ((scope.isGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((scope.getRootNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((scope.getRootNode().isFromExterns()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((scope.getParentScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 5) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 5) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[55]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[84]++;
        SymbolScope parent = scope.getParentScope();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[85]++;

        int count = parent.innerAnonFunctionsWithNames++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[86]++;
        String innerName = "function%" + count;
        scope.setSymbolForScope(
            declareInferredSymbol(
                parent, innerName, scope.getRootNode()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[87]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[56]++;}

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[54]++;}
  }

  /**
   * Make sure all the symbols and references in {@code otherSymbolTable}
   * are in this symbol table.
   *
   * Uniqueness of symbols and references is determined by the associated
   * node.
   *
   * If multiple symbol tables are mixed in, we do not check for consistency
   * between symbol tables. The first symbol we see dictates the type
   * information for that symbol.
   */
  <S extends StaticSlot<JSType>, R extends StaticReference<JSType>>
  void addSymbolsFrom(StaticSymbolTable<S, R> otherSymbolTable) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[88]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[22]++;


    for (S otherSymbol : otherSymbolTable.getAllSymbols()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[22]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[23]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[24]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[89]++;
      String name = otherSymbol.getName();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[90]++;
      SymbolScope myScope = createScopeFrom(
          otherSymbolTable.getScope(otherSymbol));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[91]++;

      StaticReference<JSType> decl =
          findBestDeclToAdd(otherSymbolTable, otherSymbol);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[92]++;
      Symbol mySymbol = null;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[93]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((decl != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[57]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[94]++;
        Node declNode = decl.getNode();

        // If we have a declaration node, we can ensure the symbol is declared.
        mySymbol = isAnySymbolDeclared(name, declNode, myScope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[95]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[96]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((mySymbol == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[59]++;
          mySymbol = copySymbolTo(otherSymbol, declNode, myScope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[97]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[60]++;}

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[58]++;
        // If we don't have a declaration node, we won't be able to declare
        // a symbol in this symbol table. But we may be able to salvage the
        // references if we already have a symbol.
        mySymbol = myScope.getOwnSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[98]++;
      }
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[99]++;
int CodeCoverConditionCoverageHelper_C32;

      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((mySymbol != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[61]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[100]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[25]++;


        for (R otherRef : otherSymbolTable.getReferences(otherSymbol)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[25]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[26]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[27]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[101]++;
int CodeCoverConditionCoverageHelper_C33;
          if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((isGoodRefToAdd(otherRef)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[63]++;
            mySymbol.defineReferenceAt(otherRef.getNode());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[102]++;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[64]++;}
        }

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[62]++;}
    }
  }

  /**
   * Checks if any symbol is already declared at the given node and scope
   * for the given name. If so, returns it.
   */
  private Symbol isAnySymbolDeclared(
      String name, Node declNode, SymbolScope scope) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[103]++;
    Symbol sym = symbols.get(declNode, name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[104]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((sym == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[65]++;
      // Sometimes, our symbol tables will disagree on where the
      // declaration node should be. In the rare case where this happens,
      // trust the existing symbol.
      // See SymbolTableTest#testDeclarationDisagreement.
      return scope.ownSymbols.get(name);

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[66]++;}
    return sym;
  }

  /** Helper for addSymbolsFrom, to determine the best declaration spot. */
  private <S extends StaticSlot<JSType>, R extends StaticReference<JSType>>
  StaticReference<JSType> findBestDeclToAdd(
      StaticSymbolTable<S, R> otherSymbolTable, S slot) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[105]++;
    StaticReference<JSType> decl = slot.getDeclaration();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[106]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isGoodRefToAdd(decl)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[67]++;
      return decl;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[68]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[107]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[28]++;



    for (R ref : otherSymbolTable.getReferences(slot)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[28]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[29]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[30]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[108]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isGoodRefToAdd(ref)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[69]++;
        return ref;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[70]++;}
    }

    return null;
  }

  /**
   * Helper for addSymbolsFrom, to determine whether a reference is
   * acceptable. A reference must be in the normal source tree.
   */
  private boolean isGoodRefToAdd(@Nullable StaticReference<JSType> ref) {
    return ref != null && ref.getNode() != null
        && ref.getNode().getStaticSourceFile() != null
        && !Compiler.SYNTHETIC_EXTERNS.equals(
            ref.getNode().getStaticSourceFile().getName());
  }

  private Symbol copySymbolTo(StaticSlot<JSType> sym, SymbolScope scope) {
    return copySymbolTo(sym, sym.getDeclaration().getNode(), scope);
  }

  private Symbol copySymbolTo(
      StaticSlot<JSType> sym, Node declNode, SymbolScope scope) {
    // All symbols must have declaration nodes.
    Preconditions.checkNotNull(declNode);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[109]++;
    return declareSymbol(
        sym.getName(), sym.getType(), sym.isTypeInferred(), scope, declNode,
        sym.getJSDocInfo());
  }

  private Symbol addSymbol(
      String name, JSType type, boolean inferred, SymbolScope scope,
      Node declNode) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[110]++;
    Symbol symbol = new Symbol(name, type, inferred, scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[111]++;
    Symbol replacedSymbol = symbols.put(declNode, name, symbol);
    Preconditions.checkState(
        replacedSymbol == null,
        "Found duplicate symbol %s in global index. Type %s", name, type);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[112]++;

    replacedSymbol = scope.ownSymbols.put(name, symbol);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[113]++;
    Preconditions.checkState(
        replacedSymbol == null,
        "Found duplicate symbol %s in its scope. Type %s", name, type);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[114]++;
    return symbol;
  }

  private Symbol declareSymbol(
      String name, JSType type, boolean inferred,
      SymbolScope scope, Node declNode, JSDocInfo info) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[115]++;
    Symbol symbol = addSymbol(name, type, inferred, scope, declNode);
    symbol.setJSDocInfo(info);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[116]++;
    symbol.setDeclaration(symbol.defineReferenceAt(declNode));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[117]++;
    return symbol;
  }

  private void removeSymbol(Symbol s) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[118]++;
    SymbolScope scope = getScope(s);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[119]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((scope.ownSymbols.remove(s.getName()) != s) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[71]++;
      throw new IllegalStateException("Symbol not found in scope " + s);

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[72]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[120]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((symbols.remove(s.getDeclaration().getNode(), s.getName()) != s) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[73]++;
      throw new IllegalStateException("Symbol not found in table " + s);

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[74]++;}
  }

  /**
   * Not all symbol tables record references to "namespace" objects.
   * For example, if you have:
   * goog.dom.DomHelper = function() {};
   * The symbol table may not record that as a reference to "goog.dom",
   * because that would be redundant.
   */
  void fillNamespaceReferences() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[121]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[31]++;


    for (Symbol symbol : getAllSymbolsSorted()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[31]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[32]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[33]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[122]++;
      String qName = symbol.getName();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[123]++;
      int rootIndex = qName.indexOf('.');
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[124]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((rootIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[75]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[125]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[76]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[126]++;

      Symbol root = symbol.scope.getQualifiedSlot(
          qName.substring(0, rootIndex));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[127]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[77]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[128]++;
        // In theory, this should never happen, but we fail quietly anyway
        // just to be safe.
        continue;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[78]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[129]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[34]++;



      for (Reference ref : getReferences(symbol)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[34]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[35]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[36]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[130]++;
        Node currentNode = ref.getNode();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[131]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((currentNode.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[79]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[132]++;
          continue;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[80]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[133]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[37]++;


int CodeCoverConditionCoverageHelper_C42;

        while ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((currentNode.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[37]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[38]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[39]++;
}
          currentNode = currentNode.getFirstChild();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[134]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[135]++;

          String name = currentNode.getQualifiedName();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[136]++;
int CodeCoverConditionCoverageHelper_C43;
          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[81]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[137]++;
            Symbol namespace =
                isAnySymbolDeclared(name, currentNode, root.scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[138]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[83]++;
              namespace = root.scope.getQualifiedSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[139]++;

            } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[84]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[140]++;
int CodeCoverConditionCoverageHelper_C45;

            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((root.scope.isGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[85]++;
              namespace = declareSymbol(name,
                  registry.getNativeType(JSTypeNative.UNKNOWN_TYPE),
                  true,
                  root.scope,
                  currentNode,
                  null /* JsDoc info */);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[141]++;

            } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[86]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[142]++;
int CodeCoverConditionCoverageHelper_C46;

            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[87]++;
              namespace.defineReferenceAt(currentNode);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[143]++;

            } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[88]++;}

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[82]++;}
        }
      }
    }
  }

  void fillPropertyScopes() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[144]++;
    // Collect all object symbols.
    List<Symbol> types = Lists.newArrayList();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[145]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[40]++;



    // Create a property scope for each named type and each anonymous object,
    // and populate it with that object's properties.
    //
    // We notably don't want to create a property scope for 'x' in
    // var x = new Foo();
    // where x is just an instance of another type.
    for (Symbol sym : getAllSymbols()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[40]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[41]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[42]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[146]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((needsPropertyScope(sym)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[89]++;
        types.add(sym);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[147]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[90]++;}
    }

    // The order of operations here is significant.
    //
    // When we add properties to Foo, we'll remove Foo.prototype from
    // the symbol table and replace it with a fresh symbol in Foo's
    // property scope. So the symbol for Foo.prototype in
    // {@code instances} will be stale.
    //
    // To prevent this, we sort the list by the reverse of the
    // default symbol order, which will do the right thing.
    Collections.sort(types,
        Collections.reverseOrder(getNaturalSymbolOrdering()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[148]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[149]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[43]++;


    for (Symbol s : types) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[43]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[44]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[45]++;
}
      createPropertyScopeFor(s);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[150]++;
    }

    pruneOrphanedNames();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[151]++;
  }

  private boolean needsPropertyScope(Symbol sym) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[152]++;
    ObjectType type = ObjectType.cast(sym.getType());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[153]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[91]++;
      return false;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[92]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[154]++;
int CodeCoverConditionCoverageHelper_C49;

    // Anonymous objects
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((type.getReferenceName() == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[93]++;
      return true;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[94]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[155]++;
int CodeCoverConditionCoverageHelper_C50;

    // Constructors/prototypes
    // Should this check for
    // (type.isNominalConstructor() || type.isFunctionPrototypeType())
    // ?
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((sym.getName().equals(type.getReferenceName())) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[95]++;
      return true;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[96]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[156]++;
int CodeCoverConditionCoverageHelper_C51;

    // Enums
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((type.isEnumType()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((sym.getName().equals(
            type.toMaybeEnumType().getElementsType().getReferenceName())) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[97]++;
      return true;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[98]++;}

    return false;
  }

  /**
   * Removes symbols where the namespace they're on has been removed.
   *
   * After filling property scopes, we may have two symbols represented
   * in different ways. For example, "A.superClass_.foo" and B.prototype.foo".
   *
   * This resolves that ambiguity by pruning the duplicates.
   * If we have a lexical symbol with a constructor in its property
   * chain, then we assume there's also a property path to this symbol.
   * In other words, we can remove "A.superClass_.foo" because it's rooted
   * at "A", and we built a property scope for "A" above.
   */
  void pruneOrphanedNames() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[157]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[46]++;


    nextSymbol: for (Symbol s : getAllSymbolsSorted()) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[46]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[47]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[48]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[158]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((s.isProperty()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[99]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[159]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[100]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[160]++;

      String currentName = s.getName();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[161]++;
      int dot = -1;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[162]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[49]++;


      while (-1 != (dot = currentName.lastIndexOf('.'))) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[49]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[50]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[51]++;
}
        currentName = currentName.substring(0, dot);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[163]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[164]++;

        Symbol owner = s.scope.getQualifiedSlot(currentName);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[165]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (512)) == 0 || true) &&
 ((owner != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (128)) == 0 || true) &&
 ((owner.getType() != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C54 |= (32)) == 0 || true) &&
 ((owner.getType().isNominalConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((owner.getType().isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((owner.getType().isEnumType()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 5) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 5) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[101]++;
          removeSymbol(s);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[166]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[167]++;
          continue nextSymbol;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[102]++;}
      }
    }
  }

  /**
   * Create symbols and references for all properties of types in
   * this symbol table.
   *
   * This gets a little bit tricky, because of the way this symbol table
   * conflates "type Foo" and "the constructor of type Foo". So if you
   * have:
   *
   * <code>
   * SymbolTable symbolTable = for("var x = new Foo();");
   * Symbol x = symbolTable.getGlobalScope().getSlot("x");
   * Symbol type = symbolTable.getAllSymbolsForType(x.getType()).get(0);
   * </code>
   *
   * Then type.getPropertyScope() will have the properties of the
   * constructor "Foo". To get the properties of instances of "Foo",
   * you will need to call:
   *
   * <code>
   * Symbol instance = symbolTable.getSymbolForInstancesOf(type);
   * </code>
   *
   * As described at the top of this file, notice that "new Foo()" and
   * "Foo.prototype" are represented by the same symbol.
   */
  void fillPropertySymbols(
      AbstractCompiler compiler, Node externs, Node root) {
    (new PropertyRefCollector(compiler)).process(externs, root);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[168]++;
  }

  /** Index JSDocInfo. */
  void fillJSDocInfo(
      AbstractCompiler compiler, Node externs, Node root) {
    NodeTraversal.traverseRoots(
        compiler, Lists.newArrayList(externs, root),
        new JSDocInfoCollector(compiler.getTypeRegistry()));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[169]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[170]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[52]++;



    // Create references to parameters in the JSDoc.
    for (Symbol sym : getAllSymbolsSorted()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[52]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[53]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[54]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[171]++;
      JSDocInfo info = sym.getJSDocInfo();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[172]++;
int CodeCoverConditionCoverageHelper_C55;
      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[103]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[173]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[104]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[174]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[55]++;



      for (Marker marker : info.getMarkers()) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[55]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[56]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[57]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[175]++;
        SourcePosition<Node> pos = marker.getNameNode();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[176]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((pos == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[105]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[177]++;
          continue;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[106]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[178]++;

        Node paramNode = pos.getItem();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[179]++;
        String name = paramNode.getString();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[180]++;
        Symbol param = getParameterInFunction(sym, name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[181]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((param == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[107]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[182]++;
          // There is no reference to this parameter in the actual JavaScript
          // code, so we'll try to create a special JsDoc-only symbol in
          // a JsDoc-only scope.
          SourcePosition<Node> typePos = marker.getType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[183]++;
          JSType type = null;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[184]++;
int CodeCoverConditionCoverageHelper_C58;
          if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((typePos != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[109]++;
            type = typePos.getItem().getJSType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[185]++;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[110]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[186]++;
int CodeCoverConditionCoverageHelper_C59;

          if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((sym.docScope == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[111]++;
            sym.docScope = new SymbolScope(null /* root */,
                null /* parent scope */, null /* type of this */, sym);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[187]++;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[112]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[188]++;

          // Check to make sure there's no existing symbol. In theory, this
          // should never happen, but we check anyway and fail silently
          // if our assumptions are wrong. (We do not want to put the symbol
          // table into an invalid state).
          Symbol existingSymbol =
              isAnySymbolDeclared(name, paramNode, sym.docScope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[189]++;
int CodeCoverConditionCoverageHelper_C60;
          if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((existingSymbol == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[113]++;
            declareSymbol(name, type, type == null, sym.docScope, paramNode,
                null /* info */);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[190]++;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[114]++;}

        } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[108]++;
          param.defineReferenceAt(paramNode);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[191]++;
        }
      }
    }
  }

  /**
   * Build a property scope for the given symbol. Any properties of the symbol
   * will be added to the property scope.
   *
   * It is important that property scopes are created in order from the leaves
   * up to the root, so this should only be called from #fillPropertyScopes.
   * If you try to create a property scope for a parent before its leaf,
   * then the leaf will get cut and re-added to the parent property scope,
   * and weird things will happen.
   */
  private void createPropertyScopeFor(Symbol s) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[192]++;
int CodeCoverConditionCoverageHelper_C61;
    // In order to build a property scope for s, we will need to build
    // a property scope for all its implicit prototypes first. This means
    // that sometimes we will already have built its property scope
    // for a previous symbol.
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((s.propertyScope != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[115]++;
      return;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[116]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[193]++;

    SymbolScope parentPropertyScope = null;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[194]++;
    ObjectType type = s.getType() == null ? null : s.getType().toObjectType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[195]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[117]++;
      return;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[118]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[196]++;

    ObjectType proto = type.getParentScope();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[197]++;
int CodeCoverConditionCoverageHelper_C63;
    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((proto != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((proto != type) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((proto.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[119]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[198]++;
      Symbol parentSymbol = getSymbolForInstancesOf(proto.getConstructor());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[199]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((parentSymbol != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[121]++;
        createPropertyScopeFor(parentSymbol);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[200]++;
        parentPropertyScope = parentSymbol.getPropertyScope();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[201]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[122]++;}

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[120]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[202]++;

    ObjectType instanceType = type;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[203]++;
    Iterable<String> propNames = type.getOwnPropertyNames();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[204]++;
int CodeCoverConditionCoverageHelper_C65;
    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((instanceType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[123]++;
      // Merge the properties of "Foo.prototype" and "new Foo()" together.
      instanceType = instanceType.getOwnerFunction().getInstanceType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[205]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[206]++;
      Set<String> set = Sets.newHashSet(propNames);
      Iterables.addAll(set, instanceType.getOwnPropertyNames());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[207]++;
      propNames = set;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[208]++;

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[124]++;}

    s.setPropertyScope(new SymbolScope(null, parentPropertyScope, type, s));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[209]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[210]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[58]++;


    for (String propName : propNames) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[58]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[59]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[60]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[211]++;
      StaticSlot<JSType> newProp = instanceType.getSlot(propName);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[212]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((newProp.getDeclaration() == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[125]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[213]++;
        // Skip properties without declarations. We won't know how to index
        // them, because we index things by node.
        continue;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[126]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[214]++;

      // We have symbol tables that do not do type analysis. They just try
      // to build a complete index of all objects in the program. So we might
      // already have symbols for things like "Foo.bar". If this happens,
      // throw out the old symbol and use the type-based symbol.
      Symbol oldProp = symbols.get(newProp.getDeclaration().getNode(),
          s.getName() + "." + propName);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[215]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((oldProp != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[127]++;
        removeSymbol(oldProp);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[216]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[128]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[217]++;
int CodeCoverConditionCoverageHelper_C68;

      // If we've already have an entry in the table for this symbol,
      // then skip it. This should only happen if we screwed up,
      // and declared multiple distinct properties with the same name
      // at the same node. We bail out here to be safe.
      if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((symbols.get(newProp.getDeclaration().getNode(),
              newProp.getName()) != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[129]++;
        logger.warning("Found duplicate symbol " + newProp);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[218]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[219]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[130]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[220]++;

      Symbol newSym = copySymbolTo(newProp, s.propertyScope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[221]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((oldProp != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[131]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[222]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((newSym.getJSDocInfo() == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[133]++;
          newSym.setJSDocInfo(oldProp.getJSDocInfo());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[223]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[134]++;}
        newSym.setPropertyScope(oldProp.propertyScope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[224]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[225]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[61]++;


        for (Reference ref : oldProp.references.values()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[61]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[62]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[63]++;
}
          newSym.defineReferenceAt(ref.getNode());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[226]++;
        }

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[132]++;}
    }
  }

  /**
   * Fill in references to "this" variables.
   */
  void fillThisReferences(
      AbstractCompiler compiler, Node externs, Node root) {
    (new ThisRefCollector(compiler)).process(externs, root);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[227]++;
  }

  /**
   * Given a scope from another symbol table, returns the {@code SymbolScope}
   * rooted at the same node. Creates one if it doesn't exist yet.
   */
  private SymbolScope createScopeFrom(StaticScope<JSType> otherScope) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[228]++;
    Node otherScopeRoot = otherScope.getRootNode();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[229]++;
    SymbolScope myScope = scopes.get(otherScopeRoot);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[230]++;
int CodeCoverConditionCoverageHelper_C71;
    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((myScope == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[135]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[231]++;
      StaticScope<JSType> otherScopeParent = otherScope.getParentScope();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[232]++;
int CodeCoverConditionCoverageHelper_C72;

      // If otherScope is a global scope, and we already have a global scope,
      // then something has gone seriously wrong.
      //
      // Not all symbol tables are rooted at the same global node, and
      // we do not want to mix and match symbol tables that are rooted
      // differently.

      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((otherScopeParent == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[137]++;
        // The global scope must be created before any local scopes.
        Preconditions.checkState(
            globalScope == null, "Global scopes found at different roots");
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[233]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[138]++;}

      myScope = new SymbolScope(
          otherScopeRoot,
          otherScopeParent == null ? null : createScopeFrom(otherScopeParent),
          otherScope.getTypeOfThis(),
          null);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[234]++;
      scopes.put(otherScopeRoot, myScope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[235]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[236]++;
int CodeCoverConditionCoverageHelper_C73;
      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((myScope.isGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[139]++;
        globalScope = myScope;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[237]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[140]++;}

    } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[136]++;}
    return myScope;
  }

  public static final class Symbol extends SimpleSlot {
    // Use a linked hash map, so that the results are deterministic
    // (and so the declaration always comes first).
    private final Map<Node, Reference> references = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[238]++;
  }

    private final SymbolScope scope;

    private SymbolScope propertyScope = null;
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[239]++;
  }

    private Reference declaration = null;
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[240]++;
  }

    private JSDocInfo docInfo = null;
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[241]++;
  }

    // A scope for symbols that are only documented in JSDoc.
    private SymbolScope docScope = null;
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[242]++;
  }

    Symbol(String name, JSType type, boolean inferred, SymbolScope scope) {
      super(name, type, inferred);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[243]++;
      this.scope = scope;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[244]++;
    }

    @Override
    public Reference getDeclaration() {
      return declaration;
    }

    public FunctionType getFunctionType() {
      return JSType.toMaybeFunctionType(getType());
    }

    public Reference defineReferenceAt(Node n) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[245]++;
      Reference result = references.get(n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[246]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[141]++;
        result = new Reference(this, n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[247]++;
        references.put(n, result);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[248]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[142]++;}
      return result;
    }

    /** Sets the declaration node. May only be called once. */
    void setDeclaration(Reference ref) {
      Preconditions.checkState(this.declaration == null);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[249]++;
      this.declaration = ref;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[250]++;
    }

    public boolean inGlobalScope() {
      return scope.isGlobalScope();
    }

    public boolean inExterns() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[251]++;
      Node n = getDeclarationNode();
      return n == null ? false : n.isFromExterns();
    }

    public Node getDeclarationNode() {
      return declaration == null ? null : declaration.getNode();
    }

    public String getSourceFileName() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[252]++;
      Node n = getDeclarationNode();
      return n == null ? null : n.getSourceFileName();
    }

    public SymbolScope getPropertyScope() {
      return propertyScope;
    }

    void setPropertyScope(SymbolScope scope) {
      this.propertyScope = scope;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[253]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[254]++;
int CodeCoverConditionCoverageHelper_C75;
      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[143]++;
        this.propertyScope.setSymbolForScope(this);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[255]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[144]++;}
    }

    @Override
    public JSDocInfo getJSDocInfo() {
      return docInfo;
    }

    void setJSDocInfo(JSDocInfo info) {
      this.docInfo = info;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[256]++;
    }

    /** Whether this is a property of another variable. */
    public boolean isProperty() {
      return scope.isPropertyScope();
    }

    /** Whether this is a variable in a lexical scope. */
    public boolean isLexicalVariable() {
      return scope.isLexicalScope();
    }

    /** Whether this is a variable that's only in JSDoc. */
    public boolean isDocOnlyParameter() {
      return scope.isDocScope();
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[257]++;
      Node n = getDeclarationNode();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[258]++;
      int lineNo = n == null ? -1 : n.getLineno();
      return getName() + "@" + getSourceFileName() + ":" + lineNo;
    }
  }

  public static final class Reference extends SimpleReference<Symbol> {
    Reference(Symbol symbol, Node node) {
      super(symbol, node);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[259]++;
    }
  }

  public static final class SymbolScope implements StaticScope<JSType> {
    private final Node rootNode;
    private final SymbolScope parent;
    private final JSType typeOfThis;
    private final Map<String, Symbol> ownSymbols = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[260]++;
  }
    private final int scopeDepth;

    // The number of inner anonymous functions that we've given names to.
    private int innerAnonFunctionsWithNames = 0;
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[261]++;
  }

    // The symbol associated with a property scope or doc scope.
    private Symbol mySymbol;

    SymbolScope(
        Node rootNode,
        @Nullable SymbolScope parent,
        JSType typeOfThis,
        Symbol mySymbol) {
      this.rootNode = rootNode;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[262]++;
      this.parent = parent;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[263]++;
      this.typeOfThis = typeOfThis;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[264]++;
      this.scopeDepth = parent == null ? 0 : (parent.getScopeDepth() + 1);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[265]++;
      this.mySymbol = mySymbol;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[266]++;
    }

    Symbol getSymbolForScope() {
      return mySymbol;
    }

    void setSymbolForScope(Symbol sym) {
      this.mySymbol = sym;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[267]++;
    }

    /** Gets a unique index for the symbol in this scope. */
    public int getIndexOfSymbol(Symbol sym) {
      return Iterables.indexOf(
          ownSymbols.values(), Predicates.equalTo(sym));
    }

    @Override
    public Node getRootNode() {
      return rootNode;
    }

    @Override
    public SymbolScope getParentScope() {
      return parent;
    }

    /**
     * Get the slot for a fully-qualified name (e.g., "a.b.c") by trying
     * to find property scopes at each part of the path.
     */
    public Symbol getQualifiedSlot(String name) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[268]++;
      Symbol fullyNamedSym = getSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[269]++;
int CodeCoverConditionCoverageHelper_C76;
      if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((fullyNamedSym != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[145]++;
        return fullyNamedSym;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[146]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[270]++;

      int dot = name.lastIndexOf(".");
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[271]++;
int CodeCoverConditionCoverageHelper_C77;
      if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((dot != -1) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[147]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[272]++;
        Symbol owner = getQualifiedSlot(name.substring(0, dot));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[273]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((owner != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((owner.getPropertyScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[149]++;
          return owner.getPropertyScope().getSlot(name.substring(dot + 1));

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[150]++;}

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[148]++;}

      return null;
    }

    @Override
    public Symbol getSlot(String name) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[274]++;
      Symbol own = getOwnSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[275]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((own != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[151]++;
        return own;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[152]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[276]++;

      Symbol ancestor = parent == null ? null : parent.getSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[277]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((ancestor != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[153]++;
        return ancestor;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[154]++;}
      return null;
    }

    @Override
    public Symbol getOwnSlot(String name) {
      return ownSymbols.get(name);
    }

    @Override
    public JSType getTypeOfThis() {
      return typeOfThis;
    }

    public boolean isGlobalScope() {
      return getParentScope() == null && getRootNode() != null;
    }

    /**
     * Returns whether this is a doc scope. A doc scope is a table for symbols
     * that are documented solely within a JSDoc comment.
     */
    public boolean isDocScope() {
      return getRootNode() == null && mySymbol != null &&
          mySymbol.docScope == this;
    }

    public boolean isPropertyScope() {
      return getRootNode() == null && !isDocScope();
    }

    public boolean isLexicalScope() {
      return getRootNode() != null;
    }

    public int getScopeDepth() {
      return scopeDepth;
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[278]++;
      Node n = getRootNode();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[279]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[155]++;
        return "Scope@" + n.getSourceFileName() + ":" + n.getLineno();

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[156]++;
        return "PropertyScope@" + getSymbolForScope();
      }
    }
  }

  private class PropertyRefCollector
      extends NodeTraversal.AbstractPostOrderCallback
      implements CompilerPass {
    private final AbstractCompiler compiler;

    PropertyRefCollector(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[280]++;
    }

    @Override
    public void process(Node externs, Node root) {
      NodeTraversal.traverseRoots(
          compiler,
          Lists.newArrayList(externs, root),
          this);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[281]++;
    }

    private boolean maybeDefineReference(
        Node n, String propName, Symbol ownerSymbol) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[282]++;
int CodeCoverConditionCoverageHelper_C82;
      // getPropertyScope() will be null in some rare cases where there
      // are no extern declarations for built-in types (like Function).
      if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((ownerSymbol != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((ownerSymbol.getPropertyScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[157]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[283]++;
        Symbol prop = ownerSymbol.getPropertyScope().getSlot(propName);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[284]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((prop != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[159]++;
          prop.defineReferenceAt(n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[285]++;
          return true;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[160]++;}

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[158]++;}
      return false;
    }

    // Try to find the symbol by its fully qualified name.
    private boolean tryDefineLexicalQualifiedNameRef(String name, Node n) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[286]++;
int CodeCoverConditionCoverageHelper_C84;
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[161]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[287]++;
        Symbol lexicalSym = getEnclosingScope(n).getQualifiedSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[288]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((lexicalSym != null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[163]++;
          lexicalSym.defineReferenceAt(n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[289]++;
          return true;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[164]++;}

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[162]++;}
      return false;
    }

    // Try to remove a reference by its fully qualified name.
    // If the symbol has no references left, remove it completely.
    private void tryRemoveLexicalQualifiedNameRef(String name, Node n) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[290]++;
int CodeCoverConditionCoverageHelper_C86;
      if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[165]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[291]++;
        Symbol lexicalSym = getEnclosingScope(n).getQualifiedSlot(name);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[292]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (32)) == 0 || true) &&
 ((lexicalSym != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((lexicalSym.isLexicalVariable()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((lexicalSym.getDeclaration().getNode() == n) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 3) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 3) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[167]++;
          removeSymbol(lexicalSym);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[293]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[168]++;}

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[166]++;}
    }

    private boolean maybeDefineTypedReference(
        Node n, String propName, JSType owner) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[294]++;
int CodeCoverConditionCoverageHelper_C88;
      if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((owner.isGlobalThisType()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[169]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[295]++;
        Symbol sym = globalScope.getSlot(propName);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[296]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((sym != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[171]++;
          sym.defineReferenceAt(n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[297]++;
          return true;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[172]++;}

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[170]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[298]++;
int CodeCoverConditionCoverageHelper_C90; if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((owner.isNominalConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[173]++;
        return maybeDefineReference(
            n, propName, getSymbolDeclaredBy(owner.toMaybeFunctionType()));

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[174]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[299]++;
int CodeCoverConditionCoverageHelper_C91; if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((owner.isEnumType()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[175]++;
        return maybeDefineReference(
            n, propName, getSymbolDeclaredBy(owner.toMaybeEnumType()));

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[176]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[300]++;
        boolean defined = false;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[301]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[64]++;


        for (Symbol ctor : getAllSymbolsForType(owner)) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[64]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[65]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[66]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[302]++;
int CodeCoverConditionCoverageHelper_C92;
          if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((maybeDefineReference(
                  n, propName, getSymbolForInstancesOf(ctor))) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[177]++;
            defined = true;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[303]++;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[178]++;}
        }
        return defined;
      }
}
}
      return false;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[304]++;
int CodeCoverConditionCoverageHelper_C93;
      // There are two ways to define a property reference:
      // 1) As a fully qualified lexical symbol (e.g., x.y)
      // 2) As a property of another object (e.g., x's y)
      // Property definitions should take precedence over lexical
      // definitions. e.g., for "a.b", it's more useful to record
      // this as "property b of the type of a", than as "symbol a.b".

      if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[179]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[305]++;
        JSType owner = n.getFirstChild().getJSType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[306]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((owner != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[181]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[307]++;
          boolean defined = maybeDefineTypedReference(
              n, n.getLastChild().getString(), owner);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[308]++;
int CodeCoverConditionCoverageHelper_C95;

          if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((defined) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[183]++;
            tryRemoveLexicalQualifiedNameRef(n.getQualifiedName(), n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[309]++;
            return;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[184]++;}

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[182]++;}

        tryDefineLexicalQualifiedNameRef(n.getQualifiedName(), n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[310]++;

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[180]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[311]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((n.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[185]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[312]++;
        JSType owner = parent.getJSType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[313]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((owner != null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[187]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[314]++;
          boolean defined =
              maybeDefineTypedReference(n, n.getString(), owner);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[315]++;
int CodeCoverConditionCoverageHelper_C98;

          if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((defined) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[189]++;
            tryRemoveLexicalQualifiedNameRef(
                NodeUtil.getBestLValueName(n), n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[316]++;
            return;

          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[190]++;}

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[188]++;}

        tryDefineLexicalQualifiedNameRef(
            NodeUtil.getBestLValueName(n), n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[317]++;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[186]++;}
}
    }
  }

  private class ThisRefCollector
      extends NodeTraversal.AbstractScopedCallback
      implements CompilerPass {
    private final AbstractCompiler compiler;

    // The 'this' symbols in the current scope chain.
    //
    // If we don't know how to declare 'this' in a scope chain,
    // then null should be on the stack. But this should be a rare
    // occurrence. We should strive to always be able to come up
    // with some symbol for 'this'.
    private final List<Symbol> thisStack = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[318]++;
  }

    ThisRefCollector(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[319]++;
    }

    @Override
    public void process(Node externs, Node root) {
      NodeTraversal.traverseRoots(
          compiler,
          Lists.newArrayList(externs, root),
          this);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[320]++;
    }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[321]++;
      Symbol symbol = null;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[322]++;
int CodeCoverConditionCoverageHelper_C99;
      if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[191]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[323]++;
        // Declare the global this at the first input root.
        // This is a bizarre place to put it, but we need some
        // location with a real file path (because all symbols
        // must have a path).
        // Note that root.lastChild.firstChild is the first non-extern input.
        Node firstInputRoot = t.getScopeRoot().getLastChild().getFirstChild();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[324]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((firstInputRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[193]++;
          symbol = addSymbol(
              GLOBAL_THIS,
              registry.getNativeType(JSTypeNative.GLOBAL_THIS),
              false /* declared */,
              globalScope,
              firstInputRoot);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[325]++;
          symbol.setDeclaration(new Reference(symbol, firstInputRoot));
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[326]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[194]++;}

      } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[192]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[327]++;
        // Otherwise, declare a "this" property when possible.
        SymbolScope scope = scopes.get(t.getScopeRoot());
        Preconditions.checkNotNull(scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[328]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[329]++;
        Symbol scopeSymbol = getSymbolForScope(scope);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[330]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((scopeSymbol != null) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[195]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[331]++;
          SymbolScope propScope = scopeSymbol.getPropertyScope();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[332]++;
int CodeCoverConditionCoverageHelper_C102;
          if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((propScope != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[197]++;
            // If a function is assigned multiple times, we only want
            // one addressable "this" symbol.
            symbol = propScope.getOwnSlot("this");
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[333]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[334]++;
int CodeCoverConditionCoverageHelper_C103;
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((symbol == null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[199]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[335]++;
              JSType rootType = t.getScopeRoot().getJSType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[336]++;
              FunctionType fnType = rootType == null
                  ? null : rootType.toMaybeFunctionType();
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[337]++;
              JSType type = fnType == null
                  ? null : fnType.getTypeOfThis();
              symbol = addSymbol(
                  "this",
                  type,
                  false /* declared */,
                  scope,
                  t.getScopeRoot());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[338]++;

            } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[200]++;}


            // TODO(nicksantos): It's non-obvious where the declaration of
            // the 'this' symbol should be. Figure this out later.
          } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[198]++;}

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[196]++;}
      }

      thisStack.add(symbol);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[339]++;
    }

    @Override
    public void exitScope(NodeTraversal t) {
      thisStack.remove(thisStack.size() - 1);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[340]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[341]++;
int CodeCoverConditionCoverageHelper_C104;
      if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((n.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[201]++;
        return;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[202]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[342]++;

      Symbol symbol = thisStack.get(thisStack.size() - 1);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[343]++;
int CodeCoverConditionCoverageHelper_C105;
      if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((symbol != null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[203]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[344]++;
        Reference ref = symbol.defineReferenceAt(n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[345]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((symbol.getDeclaration() == null) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[205]++;
          symbol.setDeclaration(ref);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[346]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[206]++;}

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[204]++;}
    }
  }

  /** Collects references to types in JSDocInfo. */
  private class JSDocInfoCollector
      extends NodeTraversal.AbstractPostOrderCallback {
    private final JSTypeRegistry typeRegistry;

    private JSDocInfoCollector(JSTypeRegistry registry) {
      this.typeRegistry = registry;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[347]++;
    }

    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[348]++;
int CodeCoverConditionCoverageHelper_C107;
      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((n.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[207]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[349]++;

        // Find references in the JSDocInfo.
        JSDocInfo info = n.getJSDocInfo();
        docInfos.add(info);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[350]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[351]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[67]++;



        for (Node typeAst : info.getTypeNodes()) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[67]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[68]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[69]++;
}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[352]++;
          SymbolScope scope = scopes.get(t.getScopeRoot());
          visitTypeNode(scope == null ? globalScope : scope, typeAst);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[353]++;
        }

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[208]++;}
    }

    public void visitTypeNode(SymbolScope scope, Node n) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[354]++;
int CodeCoverConditionCoverageHelper_C108;
      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((n.isString()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[209]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[355]++;
        Symbol symbol = scope.getSlot(n.getString());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[356]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((symbol == null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[211]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[357]++;
          // If we can't find this type, it might be a reference to a
          // primitive type (like {string}). Autobox it to check.
          JSType type = typeRegistry.getType(n.getString());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[358]++;
          JSType autobox = type == null ? null : type.autoboxesTo();
          symbol = autobox == null
              ? null : getSymbolForTypeHelper(autobox, true);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[359]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[212]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[360]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((symbol != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[213]++;
          symbol.defineReferenceAt(n);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[361]++;

        } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[214]++;}

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[210]++;}
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[362]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[70]++;


int CodeCoverConditionCoverageHelper_C111;

      for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[70]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[71]--;
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.loops[72]++;
}
        visitTypeNode(scope, child);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[363]++;
      }
    }
  }

  // Comparators
  private final Ordering<String> SOURCE_NAME_ORDERING =
      Ordering.natural().nullsFirst();
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[364]++;
  }

  private final Ordering<Node> NODE_ORDERING = new Ordering<Node>() {
    @Override
    public int compare(Node a, Node b) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[365]++;
      int result = SOURCE_NAME_ORDERING.compare(
          a.getSourceFileName(), b.getSourceFileName());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[366]++;
int CodeCoverConditionCoverageHelper_C112;
      if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((result != 0) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[215]++;
        return result;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[216]++;}

      // Source position is a bit mask of line in the top 4 bits, so this
      // is a quick way to compare order without computing absolute position.
      return a.getSourcePosition() - b.getSourcePosition();
    }
  };
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[367]++;
  }

  private final Ordering<SymbolScope> LEXICAL_SCOPE_ORDERING =
      new Ordering<SymbolScope>() {
    @Override
    public int compare(SymbolScope a, SymbolScope b) {
      Preconditions.checkState(a.isLexicalScope() && b.isLexicalScope(),
                               "We can only sort lexical scopes");
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[368]++;
      return NODE_ORDERING.compare(a.getRootNode(), b.getRootNode());
    }
  };
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[369]++;
  }

  private final Ordering<Symbol> SYMBOL_ORDERING = new Ordering<Symbol>() {
    @Override
    public int compare(Symbol a, Symbol b) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[370]++;
      SymbolScope scopeA = getScope(a);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[371]++;
      SymbolScope scopeB = getScope(b);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[372]++;

      // More deeply nested symbols should go later.
      int result = getLexicalScopeDepth(scopeA) - getLexicalScopeDepth(scopeB);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[373]++;
int CodeCoverConditionCoverageHelper_C113;
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((result != 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[217]++;
        return result;

      } else {
  CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[218]++;}

      // After than, just use lexicographic ordering.
      // This ensures "a.b" comes before "a.b.c".
      return a.getName().compareTo(b.getName());
    }
  };
  {
    CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[374]++;
  }

  /**
   * For a lexical scope, just returns the normal scope depth.
   *
   * For a property scope, returns the number of scopes we have to search
   *     to find the nearest lexical scope, plus that lexical scope's depth.
   *
   * For a doc info scope, returns 0.
   */
  private int getLexicalScopeDepth(SymbolScope scope) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[375]++;
int CodeCoverConditionCoverageHelper_C114;
    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8)) == 0 || true) &&
 ((scope.isLexicalScope()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((scope.isDocScope()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) || true)) || (CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) && false)) {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[219]++;
      return scope.getScopeDepth();

    } else {
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.branches[220]++;
      Preconditions.checkState(scope.isPropertyScope());
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[376]++;
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[377]++;
      Symbol sym = scope.getSymbolForScope();
      Preconditions.checkNotNull(sym);
CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p.statements[378]++;
      return getLexicalScopeDepth(getScope(sym)) + 1;
    }
  }
}

class CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p ());
  }
    public static long[] statements = new long[379];
    public static long[] branches = new long[221];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[115];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SymbolTable.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,0,3,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 114; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[73];

  public CodeCoverCoverageCounter$4xwog7z4xggfu2rzvytlpi82p () {
    super("com.google.javascript.jscomp.SymbolTable.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 378; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 220; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 114; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 72; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SymbolTable.java");
      for (int i = 1; i <= 378; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 220; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 114; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 24; i++) {
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

