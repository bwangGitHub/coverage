/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.classfile;

import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.UintMap;

import java.io.*;
import java.util.Arrays;

/**
 * ClassFileWriter
 *
 * A ClassFileWriter is used to write a Java class file. Methods are
 * provided to create fields and methods, and within methods to write
 * Java bytecodes.
 *
 */
public class ClassFileWriter {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }


    /**
     * Thrown for cases where the error in generating the class file is
     * due to a program size constraints rather than a likely bug in the
     * compiler.
     */
    public static class ClassFileFormatException extends RuntimeException {

        private static final long serialVersionUID = 1263998431033790599L;

        ClassFileFormatException(String message) {
            super(message);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1]++;
        }
    }

    /**
     * Construct a ClassFileWriter for a class.
     *
     * @param className the name of the class to write, including
     *        full package qualification.
     * @param superClassName the name of the superclass of the class
     *        to write, including full package qualification.
     * @param sourceFileName the name of the source file to use for
     *        producing debug information, or null if debug information
     *        is not desired
     */
    public ClassFileWriter(String className, String superClassName,
                           String sourceFileName)
    {
        generatedClassName = className;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[2]++;
        itsConstantPool = new ConstantPool(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[3]++;
        itsThisClassIndex = itsConstantPool.addClass(className);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[4]++;
        itsSuperClassIndex = itsConstantPool.addClass(superClassName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[5]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sourceFileName != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1]++;
            itsSourceFileNameIndex = itsConstantPool.addUtf8(sourceFileName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[7]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[2]++;}
        // All "new" implementations are supposed to output ACC_SUPER as a
        // class flag. This is specified in the first JVM spec, so it should
        // be old enough that it's okay to always set it.
        itsFlags = ACC_PUBLIC | ACC_SUPER;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[8]++;
    }

    public final String getClassName()
    {
        return generatedClassName;
    }

    /**
     * Add an interface implemented by this class.
     *
     * This method may be called multiple times for classes that
     * implement multiple interfaces.
     *
     * @param interfaceName a name of an interface implemented
     *        by the class being written, including full package
     *        qualification.
     */
    public void addInterface(String interfaceName) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[9]++;
        short interfaceIndex = itsConstantPool.addClass(interfaceName);
        itsInterfaces.add(Short.valueOf(interfaceIndex));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[10]++;
    }

    public static final short
        ACC_PUBLIC = 0x0001,
        ACC_PRIVATE = 0x0002,
        ACC_PROTECTED = 0x0004,
        ACC_STATIC = 0x0008,
        ACC_FINAL = 0x0010,
        ACC_SUPER = 0x0020,
        ACC_SYNCHRONIZED = 0x0020,
        ACC_VOLATILE = 0x0040,
        ACC_TRANSIENT = 0x0080,
        ACC_NATIVE = 0x0100,
        ACC_ABSTRACT = 0x0400;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[11]++;
  }

    /**
     * Set the class's flags.
     *
     * Flags must be a set of the following flags, bitwise or'd
     * together:
     *      ACC_PUBLIC
     *      ACC_PRIVATE
     *      ACC_PROTECTED
     *      ACC_FINAL
     *      ACC_ABSTRACT
     * TODO: check that this is the appropriate set
     * @param flags the set of class flags to set
     */
    public void setFlags(short flags) {
        itsFlags = flags;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[12]++;
    }

    static String getSlashedForm(String name)
    {
        return name.replace('.', '/');
    }

    /**
     * Convert Java class name in dot notation into
     * "Lname-with-dots-replaced-by-slashes;" form suitable for use as
     * JVM type signatures.
     */
    public static String classNameToSignature(String name)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[13]++;
        int nameLength = name.length();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[14]++;
        int colonPos = 1 + nameLength;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[15]++;
        char[] buf = new char[colonPos + 1];
        buf[0] = 'L';
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[16]++;
        buf[colonPos] = ';';
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[17]++;
        name.getChars(0, nameLength, buf, 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[18]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i != colonPos) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[3]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((buf[i] == '.') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[3]++;
                buf[i] = '/';
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[21]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[4]++;}
        }
        return new String(buf, 0, colonPos + 1);
    }

    /**
     * Add a field to the class.
     *
     * @param fieldName the name of the field
     * @param type the type of the field using ...
     * @param flags the attributes of the field, such as ACC_PUBLIC, etc.
     *        bitwise or'd together
     */
    public void addField(String fieldName, String type, short flags) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[22]++;
        short fieldNameIndex = itsConstantPool.addUtf8(fieldName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[23]++;
        short typeIndex = itsConstantPool.addUtf8(type);
        itsFields.add(new ClassFileField(fieldNameIndex, typeIndex, flags));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[24]++;
    }

    /**
     * Add a field to the class.
     *
     * @param fieldName the name of the field
     * @param type the type of the field using ...
     * @param flags the attributes of the field, such as ACC_PUBLIC, etc.
     *        bitwise or'd together
     * @param value an initial integral value
     */
    public void addField(String fieldName, String type, short flags,
                         int value)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[25]++;
        short fieldNameIndex = itsConstantPool.addUtf8(fieldName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[26]++;
        short typeIndex = itsConstantPool.addUtf8(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[27]++;
        ClassFileField field = new ClassFileField(fieldNameIndex, typeIndex,
                                                  flags);
        field.setAttributes(itsConstantPool.addUtf8("ConstantValue"),
                            (short)0,
                            (short)0,
                            itsConstantPool.addConstant(value));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[28]++;
        itsFields.add(field);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[29]++;
    }

    /**
     * Add a field to the class.
     *
     * @param fieldName the name of the field
     * @param type the type of the field using ...
     * @param flags the attributes of the field, such as ACC_PUBLIC, etc.
     *        bitwise or'd together
     * @param value an initial long value
     */
    public void addField(String fieldName, String type, short flags,
                         long value)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[30]++;
        short fieldNameIndex = itsConstantPool.addUtf8(fieldName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[31]++;
        short typeIndex = itsConstantPool.addUtf8(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[32]++;
        ClassFileField field = new ClassFileField(fieldNameIndex, typeIndex,
                                                  flags);
        field.setAttributes(itsConstantPool.addUtf8("ConstantValue"),
                            (short)0,
                            (short)2,
                            itsConstantPool.addConstant(value));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[33]++;
        itsFields.add(field);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[34]++;
    }

    /**
     * Add a field to the class.
     *
     * @param fieldName the name of the field
     * @param type the type of the field using ...
     * @param flags the attributes of the field, such as ACC_PUBLIC, etc.
     *        bitwise or'd together
     * @param value an initial double value
     */
    public void addField(String fieldName, String type, short flags,
                         double value)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[35]++;
        short fieldNameIndex = itsConstantPool.addUtf8(fieldName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[36]++;
        short typeIndex = itsConstantPool.addUtf8(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[37]++;
        ClassFileField field = new ClassFileField(fieldNameIndex, typeIndex,
                                                  flags);
        field.setAttributes(itsConstantPool.addUtf8("ConstantValue"),
                            (short)0,
                            (short)2,
                            itsConstantPool.addConstant(value));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[38]++;
        itsFields.add(field);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[39]++;
    }

    /**
     * Add Information about java variable to use when generating the local
     * variable table.
     *
     * @param name variable name.
     * @param type variable type as bytecode descriptor string.
     * @param startPC the starting bytecode PC where this variable is live,
     *                 or -1 if it does not have a Java register.
     * @param register the Java register number of variable
     *                 or -1 if it does not have a Java register.
     */
    public void addVariableDescriptor(String name, String type, int startPC, int register)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[40]++;
        int nameIndex = itsConstantPool.addUtf8(name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[41]++;
        int descriptorIndex = itsConstantPool.addUtf8(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[42]++;
        int [] chunk = { nameIndex, descriptorIndex, startPC, register };
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[43]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((itsVarDescriptors == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[5]++;
            itsVarDescriptors = new ObjArray();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[44]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[6]++;}
        itsVarDescriptors.add(chunk);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[45]++;
    }

    /**
     * Add a method and begin adding code.
     *
     * This method must be called before other methods for adding code,
     * exception tables, etc. can be invoked.
     *
     * @param methodName the name of the method
     * @param type a string representing the type
     * @param flags the attributes of the field, such as ACC_PUBLIC, etc.
     *        bitwise or'd together
     */
    public void startMethod(String methodName, String type, short flags) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[46]++;
        short methodNameIndex = itsConstantPool.addUtf8(methodName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[47]++;
        short typeIndex = itsConstantPool.addUtf8(type);
        itsCurrentMethod = new ClassFileMethod(methodName, methodNameIndex,
                                               type, typeIndex, flags);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[48]++;
        itsJumpFroms = new UintMap();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[49]++;
        itsMethods.add(itsCurrentMethod);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[50]++;
        addSuperBlockStart(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[51]++;
    }

    /**
     * Complete generation of the method.
     *
     * After this method is called, no more code can be added to the
     * method begun with <code>startMethod</code>.
     *
     * @param maxLocals the maximum number of local variable slots
     *        (a.k.a. Java registers) used by the method
     */
    public void stopMethod(short maxLocals) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[52]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((itsCurrentMethod == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[7]++;
            throw new IllegalStateException("No method to stop");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[8]++;}

        fixLabelGotos();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[53]++;

        itsMaxLocals = maxLocals;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[54]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[55]++;

        StackMapTable stackMap = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[56]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((GenerateStackMap) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[9]++;
            finalizeSuperBlockStarts();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[57]++;
            stackMap = new StackMapTable();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[58]++;
            stackMap.generate();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[59]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[10]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[60]++;

        int lineNumberTableLength = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[61]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((itsLineNumberTable != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[11]++;
            // 6 bytes for the attribute header
            // 2 bytes for the line number count
            // 4 bytes for each entry
            lineNumberTableLength = 6 + 2 + (itsLineNumberTableTop * 4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[62]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[12]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[63]++;

        int variableTableLength = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[64]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((itsVarDescriptors != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[13]++;
            // 6 bytes for the attribute header
            // 2 bytes for the variable count
            // 10 bytes for each entry
            variableTableLength = 6 + 2 + (itsVarDescriptors.size() * 10);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[65]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[14]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[66]++;

        int stackMapTableLength = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[67]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((stackMap != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[15]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[68]++;
            int stackMapWriteSize = stackMap.computeWriteSize();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[69]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((stackMapWriteSize > 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[17]++;
                stackMapTableLength = 6 + stackMapWriteSize;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[70]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[16]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[71]++;

        int attrLength = 2 +                    // attribute_name_index
                         4 +                    // attribute_length
                         2 +                    // max_stack
                         2 +                    // max_locals
                         4 +                    // code_length
                         itsCodeBufferTop +
                         2 +                    // exception_table_length
                         (itsExceptionTableTop * 8) +
                         2 +                    // attributes_count
                         lineNumberTableLength +
                         variableTableLength +
                         stackMapTableLength;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[72]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((attrLength > 65536) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[19]++;
            // See http://java.sun.com/docs/books/jvms/second_edition/html/ClassFile.doc.html,
            // section 4.10, "The amount of code per non-native, non-abstract
            // method is limited to 65536 bytes...
            throw new ClassFileFormatException(
                "generated bytecode for method exceeds 64K limit.");

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[20]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[73]++;
        byte[] codeAttribute = new byte[attrLength];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[74]++;
        int index = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[75]++;
        int codeAttrIndex = itsConstantPool.addUtf8("Code");
        index = putInt16(codeAttrIndex, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[76]++;
        attrLength -= 6;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[77]++;                 // discount the attribute header
        index = putInt32(attrLength, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[78]++;
        index = putInt16(itsMaxStack, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[79]++;
        index = putInt16(itsMaxLocals, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[80]++;
        index = putInt32(itsCodeBufferTop, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[81]++;
        System.arraycopy(itsCodeBuffer, 0, codeAttribute, index,
                         itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[82]++;
        index += itsCodeBufferTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[83]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[84]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((itsExceptionTableTop > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[21]++;
            index = putInt16(itsExceptionTableTop, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[85]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[86]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[6]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[87]++;
                ExceptionTableEntry ete = itsExceptionTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[88]++;
                short startPC = (short)getLabelPC(ete.itsStartLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[89]++;
                short endPC = (short)getLabelPC(ete.itsEndLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[90]++;
                short handlerPC = (short)getLabelPC(ete.itsHandlerLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[91]++;
                short catchType = ete.itsCatchType;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[92]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((startPC == -1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[23]++;
                    throw new IllegalStateException("start label not defined");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[24]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[93]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((endPC == -1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[25]++;
                    throw new IllegalStateException("end label not defined");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[26]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[94]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((handlerPC == -1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[27]++;
                    throw new IllegalStateException(
                        "handler label not defined");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[28]++;}

                index = putInt16(startPC, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[95]++;
                index = putInt16(endPC, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[96]++;
                index = putInt16(handlerPC, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[97]++;
                index = putInt16(catchType, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[98]++;
            }

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[22]++;
            // write 0 as exception table length
            index = putInt16(0, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[99]++;
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[100]++;

        int attributeCount = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[101]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((itsLineNumberTable != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[29]++;
            attributeCount++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[102]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[30]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[103]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((itsVarDescriptors != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[31]++;
            attributeCount++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[104]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[32]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[105]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((stackMapTableLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[33]++;
            attributeCount++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[106]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[34]++;}
        index = putInt16(attributeCount, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[107]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[108]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((itsLineNumberTable != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[35]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[109]++;
            int lineNumberTableAttrIndex
                    = itsConstantPool.addUtf8("LineNumberTable");
            index = putInt16(lineNumberTableAttrIndex, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[110]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[111]++;
            int tableAttrLength = 2 + (itsLineNumberTableTop * 4);
            index = putInt32(tableAttrLength, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[112]++;
            index = putInt16(itsLineNumberTableTop, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[113]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[114]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[7]++;


int CodeCoverConditionCoverageHelper_C21;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < itsLineNumberTableTop) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[9]++;
}
                index = putInt32(itsLineNumberTable[i], codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[115]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[36]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[116]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((itsVarDescriptors != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[37]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[117]++;
            int variableTableAttrIndex
                    = itsConstantPool.addUtf8("LocalVariableTable");
            index = putInt16(variableTableAttrIndex, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[118]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[119]++;
            int varCount = itsVarDescriptors.size();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[120]++;
            int tableAttrLength = 2 + (varCount * 10);
            index = putInt32(tableAttrLength, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[121]++;
            index = putInt16(varCount, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[122]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[123]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < varCount) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[10]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[11]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[12]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[124]++;
                int[] chunk = (int[])itsVarDescriptors.get(i);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[125]++;
                int nameIndex       = chunk[0];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[126]++;
                int descriptorIndex = chunk[1];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[127]++;
                int startPC         = chunk[2];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[128]++;
                int register        = chunk[3];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[129]++;
                int length = itsCodeBufferTop - startPC;

                index = putInt16(startPC, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[130]++;
                index = putInt16(length, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[131]++;
                index = putInt16(nameIndex, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[132]++;
                index = putInt16(descriptorIndex, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[133]++;
                index = putInt16(register, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[134]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[38]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[135]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((stackMapTableLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[39]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[136]++;
            int stackMapTableAttrIndex =
                    itsConstantPool.addUtf8("StackMapTable");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[137]++;
            int start = index;
            index = putInt16(stackMapTableAttrIndex, codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[138]++;
            index = stackMap.write(codeAttribute, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[139]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[40]++;}

        itsCurrentMethod.setCodeAttribute(codeAttribute);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[140]++;

        itsExceptionTable = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[141]++;
        itsExceptionTableTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[142]++;
        itsLineNumberTableTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[143]++;
        itsCodeBufferTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[144]++;
        itsCurrentMethod = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[145]++;
        itsMaxStack = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[146]++;
        itsStackTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[147]++;
        itsLabelTableTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[148]++;
        itsFixupTableTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[149]++;
        itsVarDescriptors = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[150]++;
        itsSuperBlockStarts = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[151]++;
        itsSuperBlockStartsTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[152]++;
        itsJumpFroms = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[153]++;
    }

    /**
     * Add the single-byte opcode to the current method.
     *
     * @param theOpCode the opcode of the bytecode
     */
    public void add(int theOpCode) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[154]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((opcodeCount(theOpCode) != 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[41]++;
            throw new IllegalArgumentException("Unexpected operands");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[42]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[155]++;
        int newStack = itsStackTop + stackChange(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[156]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[43]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[157]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[44]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[158]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[45]++;
            System.out.println("Add " + bytecodeStr(theOpCode));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[159]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[46]++;}
        addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[160]++;
        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[161]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[162]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[47]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[163]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[48]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[164]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[49]++;
            System.out.println("After "+bytecodeStr(theOpCode)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[165]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[50]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[166]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((theOpCode == ByteCode.ATHROW) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[51]++;
            addSuperBlockStart(itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[167]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[52]++;}
    }

    /**
     * Add a single-operand opcode to the current method.
     *
     * @param theOpCode the opcode of the bytecode
     * @param theOperand the operand of the bytecode
     */
    public void add(int theOpCode, int theOperand) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[168]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[53]++;
            System.out.println("Add "+bytecodeStr(theOpCode)
                               +", "+Integer.toHexString(theOperand));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[169]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[54]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[170]++;
        int newStack = itsStackTop + stackChange(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[171]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[55]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[172]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[56]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[173]++;

        switch (theOpCode) {
            case ByteCode.GOTO :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[57]++;
                // This is necessary because dead code is seemingly being
                // generated and Sun's verifier is expecting type state to be
                // placed even at dead blocks of code.
                addSuperBlockStart(itsCodeBufferTop + 3);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[174]++;
                // fallthru...
            case ByteCode.IFEQ :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[58]++;
            case ByteCode.IFNE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[59]++;
            case ByteCode.IFLT :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[60]++;
            case ByteCode.IFGE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[61]++;
            case ByteCode.IFGT :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[62]++;
            case ByteCode.IFLE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[63]++;
            case ByteCode.IF_ICMPEQ :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[64]++;
            case ByteCode.IF_ICMPNE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[65]++;
            case ByteCode.IF_ICMPLT :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[66]++;
            case ByteCode.IF_ICMPGE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[67]++;
            case ByteCode.IF_ICMPGT :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[68]++;
            case ByteCode.IF_ICMPLE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[69]++;
            case ByteCode.IF_ACMPEQ :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[70]++;
            case ByteCode.IF_ACMPNE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[71]++;
            case ByteCode.JSR :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[72]++;
            case ByteCode.IFNULL :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[73]++;
            case ByteCode.IFNONNULL :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[74]++; {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[175]++;
int CodeCoverConditionCoverageHelper_C33;
                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 (((theOperand & 0x80000000) != 0x80000000) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[75]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[176]++;
int CodeCoverConditionCoverageHelper_C34;
                        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((theOperand < 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((theOperand > 65535) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[77]++;
                            throw new IllegalArgumentException(
                                "Bad label for branch");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[78]++;}

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[76]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[177]++;
                    int branchPC = itsCodeBufferTop;
                    addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[178]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[179]++;
int CodeCoverConditionCoverageHelper_C35;
                    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 (((theOperand & 0x80000000) != 0x80000000) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[79]++;
                            // hard displacement
                        addToCodeInt16(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[180]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[181]++;
                        int target = theOperand + branchPC;
                        addSuperBlockStart(target);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[182]++;
                        itsJumpFroms.put(target, branchPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[183]++;

                    }
                    else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[80]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[184]++;  // a label
                        int targetPC = getLabelPC(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[185]++;
int CodeCoverConditionCoverageHelper_C36;
                        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((DEBUGLABELS) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[81]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[186]++;
                            int theLabel = theOperand & 0x7FFFFFFF;
                            System.out.println("Fixing branch to " +
                                               theLabel + " at " + targetPC +
                                               " from " + branchPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[187]++;

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[82]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[188]++;
int CodeCoverConditionCoverageHelper_C37;
                        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((targetPC != -1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[83]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[189]++;
                            int offset = targetPC - branchPC;
                            addToCodeInt16(offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[190]++;
                            addSuperBlockStart(targetPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[191]++;
                            itsJumpFroms.put(targetPC, branchPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[192]++;

                        }
                        else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[84]++;
                            addLabelFixup(theOperand, branchPC + 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[193]++;
                            addToCodeInt16(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[194]++;
                        }
                    }
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[195]++;
                break;

            case ByteCode.BIPUSH :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[85]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[196]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 (((byte)theOperand != theOperand) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[86]++;
                    throw new IllegalArgumentException("out of range byte");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[87]++;}
                addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[197]++;
                addToCodeBuffer((byte)theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[198]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[199]++;
                break;

            case ByteCode.SIPUSH :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[88]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[200]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 (((short)theOperand != theOperand) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[89]++;
                    throw new IllegalArgumentException("out of range short");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[90]++;}
                addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[201]++;
                   addToCodeInt16(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[202]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[203]++;
                break;

            case ByteCode.NEWARRAY :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[91]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[204]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((0 <= theOperand) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((theOperand < 256) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[92]++;
                    throw new IllegalArgumentException("out of range index");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[93]++;}
                addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[205]++;
                addToCodeBuffer(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[206]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[207]++;
                break;

            case ByteCode.GETFIELD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[94]++;
            case ByteCode.PUTFIELD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[95]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[208]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((0 <= theOperand) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((theOperand < 65536) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[96]++;
                    throw new IllegalArgumentException("out of range field");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[97]++;}
                addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[209]++;
                addToCodeInt16(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[210]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[211]++;
                break;

            case ByteCode.LDC :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[98]++;
            case ByteCode.LDC_W :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[99]++;
            case ByteCode.LDC2_W :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[100]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[212]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((0 <= theOperand) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((theOperand < 65536) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[101]++;
                    throw new IllegalArgumentException("out of range index");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[102]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[213]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((theOperand >= 256) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((theOpCode == ByteCode.LDC_W) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((theOpCode == ByteCode.LDC2_W) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[103]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[214]++;
int CodeCoverConditionCoverageHelper_C44;
                    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((theOpCode == ByteCode.LDC) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[105]++;
                        addToCodeBuffer(ByteCode.LDC_W);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[215]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[106]++;
                        addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[216]++;
                    }
                    addToCodeInt16(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[217]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[104]++;
                    addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[218]++;
                    addToCodeBuffer(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[219]++;
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[220]++;
                break;

            case ByteCode.RET :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[107]++;
            case ByteCode.ILOAD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[108]++;
            case ByteCode.LLOAD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[109]++;
            case ByteCode.FLOAD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[110]++;
            case ByteCode.DLOAD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[111]++;
            case ByteCode.ALOAD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[112]++;
            case ByteCode.ISTORE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[113]++;
            case ByteCode.LSTORE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[114]++;
            case ByteCode.FSTORE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[115]++;
            case ByteCode.DSTORE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[116]++;
            case ByteCode.ASTORE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[117]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[221]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((0 <= theOperand) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((theOperand < 65536) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[118]++;
                    throw new ClassFileFormatException("out of range variable");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[119]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[222]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((theOperand >= 256) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[120]++;
                    addToCodeBuffer(ByteCode.WIDE);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[223]++;
                    addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[224]++;
                    addToCodeInt16(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[225]++;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[121]++;
                    addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[226]++;
                    addToCodeBuffer(theOperand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[227]++;
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[228]++;
                break;

            default :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[122]++;
                throw new IllegalArgumentException(
                    "Unexpected opcode for 1 operand");
        }

        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[229]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[230]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[123]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[231]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[124]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[232]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[125]++;
            System.out.println("After "+bytecodeStr(theOpCode)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[233]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[126]++;}
    }

    /**
     * Generate the load constant bytecode for the given integer.
     *
     * @param k the constant
     */
    public void addLoadConstant(int k) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[234]++;
        switch (k) {
            case 0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[127]++; add(ByteCode.ICONST_0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[235]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[236]++; break;
            case 1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[128]++; add(ByteCode.ICONST_1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[237]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[238]++; break;
            case 2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[129]++; add(ByteCode.ICONST_2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[239]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[240]++; break;
            case 3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[130]++; add(ByteCode.ICONST_3);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[241]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[242]++; break;
            case 4:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[131]++; add(ByteCode.ICONST_4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[243]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[244]++; break;
            case 5:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[132]++; add(ByteCode.ICONST_5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[245]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[246]++; break;
            default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[133]++;
                add(ByteCode.LDC, itsConstantPool.addConstant(k));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[247]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[248]++;
                break;
        }
    }

    /**
     * Generate the load constant bytecode for the given long.
     *
     * @param k the constant
     */
    public void addLoadConstant(long k) {
        add(ByteCode.LDC2_W, itsConstantPool.addConstant(k));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[249]++;
    }

    /**
     * Generate the load constant bytecode for the given float.
     *
     * @param k the constant
     */
    public void addLoadConstant(float k) {
        add(ByteCode.LDC, itsConstantPool.addConstant(k));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[250]++;
    }

    /**
     * Generate the load constant bytecode for the given double.
     *
     * @param k the constant
     */
    public void addLoadConstant(double k) {
        add(ByteCode.LDC2_W, itsConstantPool.addConstant(k));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[251]++;
    }

    /**
     * Generate the load constant bytecode for the given string.
     *
     * @param k the constant
     */
    public void addLoadConstant(String k) {
        add(ByteCode.LDC, itsConstantPool.addConstant(k));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[252]++;
    }

    /**
     * Add the given two-operand bytecode to the current method.
     *
     * @param theOpCode the opcode of the bytecode
     * @param theOperand1 the first operand of the bytecode
     * @param theOperand2 the second operand of the bytecode
     */
    public void add(int theOpCode, int theOperand1, int theOperand2) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[253]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[134]++;
            System.out.println("Add "+bytecodeStr(theOpCode)
                               +", "+Integer.toHexString(theOperand1)
                               +", "+Integer.toHexString(theOperand2));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[254]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[135]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[255]++;
        int newStack = itsStackTop + stackChange(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[256]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[136]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[257]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[137]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[258]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((theOpCode == ByteCode.IINC) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[138]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[259]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((0 <= theOperand1) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((theOperand1 < 65536) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[140]++;
                throw new ClassFileFormatException("out of range variable");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[141]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[260]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((0 <= theOperand2) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((theOperand2 < 65536) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[142]++;
                throw new ClassFileFormatException("out of range increment");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[143]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[261]++;
int CodeCoverConditionCoverageHelper_C54;

            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (32)) == 0 || true) &&
 ((theOperand1 > 255) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((theOperand2 < -128) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((theOperand2 > 127) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[144]++;
                addToCodeBuffer(ByteCode.WIDE);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[262]++;
                addToCodeBuffer(ByteCode.IINC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[263]++;
                addToCodeInt16(theOperand1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[264]++;
                addToCodeInt16(theOperand2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[265]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[145]++;
                addToCodeBuffer(ByteCode.IINC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[266]++;
                addToCodeBuffer(theOperand1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[267]++;
                addToCodeBuffer(theOperand2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[268]++;
            }

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[139]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[269]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((theOpCode == ByteCode.MULTIANEWARRAY) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[146]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[270]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((0 <= theOperand1) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((theOperand1 < 65536) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[148]++;
                throw new IllegalArgumentException("out of range index");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[149]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[271]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((0 <= theOperand2) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((theOperand2 < 256) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[150]++;
                throw new IllegalArgumentException("out of range dimensions");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[151]++;}

            addToCodeBuffer(ByteCode.MULTIANEWARRAY);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[272]++;
            addToCodeInt16(theOperand1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[273]++;
            addToCodeBuffer(theOperand2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[274]++;

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[147]++;
            throw new IllegalArgumentException(
                "Unexpected opcode for 2 operands");
        }
}
        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[275]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[276]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[152]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[277]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[153]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[278]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[154]++;
            System.out.println("After "+bytecodeStr(theOpCode)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[279]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[155]++;}

    }

    public void add(int theOpCode, String className) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[280]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[156]++;
            System.out.println("Add "+bytecodeStr(theOpCode)
                               +", "+className);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[281]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[157]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[282]++;
        int newStack = itsStackTop + stackChange(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[283]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[158]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[284]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[159]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[285]++;
        switch (theOpCode) {
            case ByteCode.NEW :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[160]++;
            case ByteCode.ANEWARRAY :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[161]++;
            case ByteCode.CHECKCAST :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[162]++;
            case ByteCode.INSTANCEOF :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[163]++; {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[286]++;
                short classIndex = itsConstantPool.addClass(className);
                addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[287]++;
                addToCodeInt16(classIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[288]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[289]++;
            break;

            default :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[164]++;
                throw new IllegalArgumentException(
                    "bad opcode for class reference");
        }
        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[290]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[291]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[165]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[292]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[166]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[293]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[167]++;
            System.out.println("After "+bytecodeStr(theOpCode)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[294]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[168]++;}
    }


    public void add(int theOpCode, String className, String fieldName,
                    String fieldType)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[295]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[169]++;
            System.out.println("Add "+bytecodeStr(theOpCode)
                               +", "+className+", "+fieldName+", "+fieldType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[296]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[170]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[297]++;
        int newStack = itsStackTop + stackChange(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[298]++;
        char fieldTypeChar = fieldType.charAt(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[299]++;
        int fieldSize = (fieldTypeChar == 'J' || fieldTypeChar == 'D')
                        ? 2 : 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[300]++;
        switch (theOpCode) {
            case ByteCode.GETFIELD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[171]++;
            case ByteCode.GETSTATIC :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[172]++;
                newStack += fieldSize;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[301]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[302]++;
                break;
            case ByteCode.PUTSTATIC :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[173]++;
            case ByteCode.PUTFIELD :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[174]++;
                newStack -= fieldSize;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[303]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[304]++;
                break;
            default :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[175]++;
                throw new IllegalArgumentException(
                    "bad opcode for field reference");
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[305]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[176]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[306]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[177]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[307]++;
        short fieldRefIndex = itsConstantPool.addFieldRef(className,
                                             fieldName, fieldType);
        addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[308]++;
        addToCodeInt16(fieldRefIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[309]++;

        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[310]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[311]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[178]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[312]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[179]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[313]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[180]++;
            System.out.println("After "+bytecodeStr(theOpCode)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[314]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[181]++;}
    }

    public void addInvoke(int theOpCode, String className, String methodName,
                          String methodType)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[315]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[182]++;
            System.out.println("Add "+bytecodeStr(theOpCode)
                               +", "+className+", "+methodName+", "
                               +methodType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[316]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[183]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[317]++;
        int parameterInfo = sizeOfParameters(methodType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[318]++;
        int parameterCount = parameterInfo >>> 16;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[319]++;
        int stackDiff = (short)parameterInfo;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[320]++;

        int newStack = itsStackTop + stackDiff;
        newStack += stackChange(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[321]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[322]++;
int CodeCoverConditionCoverageHelper_C69;     // adjusts for 'this'
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[184]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[323]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[185]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[324]++;

        switch (theOpCode) {
            case ByteCode.INVOKEVIRTUAL :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[186]++;
            case ByteCode.INVOKESPECIAL :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[187]++;
            case ByteCode.INVOKESTATIC :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[188]++;
            case ByteCode.INVOKEINTERFACE :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[189]++; {
                    addToCodeBuffer(theOpCode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[325]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[326]++;
int CodeCoverConditionCoverageHelper_C70;
                    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((theOpCode == ByteCode.INVOKEINTERFACE) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[190]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[327]++;
                        short ifMethodRefIndex
                                    = itsConstantPool.addInterfaceMethodRef(
                                               className, methodName,
                                               methodType);
                        addToCodeInt16(ifMethodRefIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[328]++;
                        addToCodeBuffer(parameterCount + 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[329]++;
                        addToCodeBuffer(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[330]++;

                    }
                    else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[191]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[331]++;
                        short methodRefIndex = itsConstantPool.addMethodRef(
                                               className, methodName,
                                               methodType);
                        addToCodeInt16(methodRefIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[332]++;
                    }
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[333]++;
                break;

            default :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[192]++;
                throw new IllegalArgumentException(
                    "bad opcode for method reference");
        }
        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[334]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[335]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[193]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[336]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[194]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[337]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[195]++;
            System.out.println("After "+bytecodeStr(theOpCode)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[338]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[196]++;}
    }

    /**
     * Generate code to load the given integer on stack.
     *
     * @param k the constant
     */
    public void addPush(int k)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[339]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 (((byte)k == k) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[197]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[340]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((k == -1) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[199]++;
                add(ByteCode.ICONST_M1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[341]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[200]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[342]++;
int CodeCoverConditionCoverageHelper_C75; if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((0 <= k) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((k <= 5) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[201]++;
                add((byte)(ByteCode.ICONST_0 + k));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[343]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[202]++;
                add(ByteCode.BIPUSH, (byte)k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[344]++;
            }
}

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[198]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[345]++;
int CodeCoverConditionCoverageHelper_C76; if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 (((short)k == k) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[203]++;
            add(ByteCode.SIPUSH, (short)k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[346]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[204]++;
            addLoadConstant(k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[347]++;
        }
}
    }

    public void addPush(boolean k)
    {
        add(k ? ByteCode.ICONST_1 : ByteCode.ICONST_0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[348]++;
    }

    /**
     * Generate code to load the given long on stack.
     *
     * @param k the constant
     */
    public void addPush(long k)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[349]++;
        int ik = (int)k;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[350]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((ik == k) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[205]++;
            addPush(ik);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[351]++;
            add(ByteCode.I2L);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[352]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[206]++;
            addLoadConstant(k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[353]++;
        }
    }

    /**
     * Generate code to load the given double on stack.
     *
     * @param k the constant
     */
    public void addPush(double k)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[354]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((k == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[207]++;
            // zero
            add(ByteCode.DCONST_0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[355]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[356]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((1.0 / k < 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[209]++;
                // Negative zero
                add(ByteCode.DNEG);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[357]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[210]++;}

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[208]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[358]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((k == 1.0) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((k == -1.0) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[211]++;
            add(ByteCode.DCONST_1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[359]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[360]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((k < 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[213]++;
                add(ByteCode.DNEG);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[361]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[214]++;}

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[212]++;
            addLoadConstant(k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[362]++;
        }
}
    }

    /**
     * Generate the code to leave on stack the given string even if the
     * string encoding exeeds the class file limit for single string constant
     *
     * @param k the constant
     */
    public void addPush(String k) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[363]++;
        int length = k.length();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[364]++;
        int limit = itsConstantPool.getUtfEncodingLimit(k, 0, length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[365]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((limit == length) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[215]++;
            addLoadConstant(k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[366]++;
            return;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[216]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[367]++;
        // Split string into picies fitting the UTF limit and generate code for
        // StringBuffer sb = new StringBuffer(length);
        // sb.append(loadConstant(piece_1));
        // ...
        // sb.append(loadConstant(piece_N));
        // sb.toString();
        final String SB = "java/lang/StringBuffer";
        add(ByteCode.NEW, SB);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[368]++;
        add(ByteCode.DUP);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[369]++;
        addPush(length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[370]++;
        addInvoke(ByteCode.INVOKESPECIAL, SB, "<init>", "(I)V");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[371]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[372]++;
        int cursor = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[373]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[13]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[13]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[14]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[15]++;
}
            add(ByteCode.DUP);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[374]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[375]++;
            String s = k.substring(cursor, limit);
            addLoadConstant(s);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[376]++;
            addInvoke(ByteCode.INVOKEVIRTUAL, SB, "append",
                      "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[377]++;
            add(ByteCode.POP);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[378]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[379]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((limit == length) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[217]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[380]++;
                break;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[218]++;}
            cursor = limit;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[381]++;
            limit = itsConstantPool.getUtfEncodingLimit(k, limit, length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[382]++;
        }
        addInvoke(ByteCode.INVOKEVIRTUAL, SB, "toString",
                  "()Ljava/lang/String;");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[383]++;
    }

    /**
     * Check if k fits limit on string constant size imposed by class file
     * format.
     *
     * @param k the string constant
     */
    public boolean isUnderStringSizeLimit(String k)
    {
        return itsConstantPool.isUnderUtfEncodingLimit(k);
    }

    /**
     * Store integer from stack top into the given local.
     *
     * @param local number of local register
     */
    public void addIStore(int local)
    {
        xop(ByteCode.ISTORE_0, ByteCode.ISTORE, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[384]++;
    }

    /**
     * Store long from stack top into the given local.
     *
     * @param local number of local register
     */
    public void addLStore(int local)
    {
        xop(ByteCode.LSTORE_0, ByteCode.LSTORE, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[385]++;
    }

    /**
     * Store float from stack top into the given local.
     *
     * @param local number of local register
     */
    public void addFStore(int local)
    {
        xop(ByteCode.FSTORE_0, ByteCode.FSTORE, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[386]++;
    }

    /**
     * Store double from stack top into the given local.
     *
     * @param local number of local register
     */
    public void addDStore(int local)
    {
        xop(ByteCode.DSTORE_0, ByteCode.DSTORE, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[387]++;
    }

    /**
     * Store object from stack top into the given local.
     *
     * @param local number of local register
     */
    public void addAStore(int local)
    {
        xop(ByteCode.ASTORE_0, ByteCode.ASTORE, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[388]++;
    }

    /**
     * Load integer from the given local into stack.
     *
     * @param local number of local register
     */
    public void addILoad(int local)
    {
        xop(ByteCode.ILOAD_0, ByteCode.ILOAD, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[389]++;
    }

    /**
     * Load long from the given local into stack.
     *
     * @param local number of local register
     */
    public void addLLoad(int local)
    {
        xop(ByteCode.LLOAD_0, ByteCode.LLOAD, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[390]++;
    }

    /**
     * Load float from the given local into stack.
     *
     * @param local number of local register
     */
    public void addFLoad(int local)
    {
        xop(ByteCode.FLOAD_0, ByteCode.FLOAD, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[391]++;
    }

    /**
     * Load double from the given local into stack.
     *
     * @param local number of local register
     */
    public void addDLoad(int local)
    {
        xop(ByteCode.DLOAD_0, ByteCode.DLOAD, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[392]++;
    }

    /**
     * Load object from the given local into stack.
     *
     * @param local number of local register
     */
    public void addALoad(int local)
    {
        xop(ByteCode.ALOAD_0, ByteCode.ALOAD, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[393]++;
    }

    /**
     * Load "this" into stack.
     */
    public void addLoadThis()
    {
        add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[394]++;
    }

    private void xop(int shortOp, int op, int local)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[395]++;
        switch (local) {
          case 0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[219]++;
            add(shortOp);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[396]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[397]++;
            break;
          case 1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[220]++;
            add(shortOp + 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[398]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[399]++;
            break;
          case 2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[221]++;
            add(shortOp + 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[400]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[401]++;
            break;
          case 3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[222]++;
            add(shortOp + 3);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[402]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[403]++;
            break;
          default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[223]++;
            add(op, local);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[404]++;
        }
    }

    public int addTableSwitch(int low, int high)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[405]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[224]++;
            System.out.println("Add "+bytecodeStr(ByteCode.TABLESWITCH)
                               +" "+low+" "+high);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[406]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[225]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[407]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((low > high) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[226]++;
            throw new ClassFileFormatException("Bad bounds: "+low+' '+ high);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[227]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[408]++;

        int newStack = itsStackTop + stackChange(ByteCode.TABLESWITCH);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[409]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[228]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[410]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[229]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[411]++;

        int entryCount = high - low + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[412]++;
        int padSize = 3 & ~itsCodeBufferTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[413]++; // == 3 - itsCodeBufferTop % 4

        int N = addReservedCodeSpace(1 + padSize + 4 * (1 + 2 + entryCount));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[414]++;
        int switchStart = N;
        itsCodeBuffer[N++] = (byte)ByteCode.TABLESWITCH;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[415]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[416]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[16]++;


int CodeCoverConditionCoverageHelper_C88;
        while ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((padSize != 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[16]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[17]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[18]++;
}
            itsCodeBuffer[N++] = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[417]++;
            --padSize;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[418]++;
        }
        N += 4;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[419]++; // skip default offset
        N = putInt32(low, itsCodeBuffer, N);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[420]++;
        putInt32(high, itsCodeBuffer, N);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[421]++;

        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[422]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[423]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[230]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[424]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[231]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[425]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[232]++;
            System.out.println("After "+bytecodeStr(ByteCode.TABLESWITCH)
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[426]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[233]++;}

        return switchStart;
    }

    public final void markTableSwitchDefault(int switchStart)
    {
        addSuperBlockStart(itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[427]++;
        itsJumpFroms.put(itsCodeBufferTop, switchStart);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[428]++;
        setTableSwitchJump(switchStart, -1, itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[429]++;
    }

    public final void markTableSwitchCase(int switchStart, int caseIndex)
    {
        addSuperBlockStart(itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[430]++;
        itsJumpFroms.put(itsCodeBufferTop, switchStart);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[431]++;
        setTableSwitchJump(switchStart, caseIndex, itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[432]++;
    }

    public final void markTableSwitchCase(int switchStart, int caseIndex,
                                          int stackTop)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[433]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((0 <= stackTop) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((stackTop <= itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[234]++;
            throw new IllegalArgumentException("Bad stack index: "+stackTop);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[235]++;}
        itsStackTop = (short)stackTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[434]++;
        addSuperBlockStart(itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[435]++;
        itsJumpFroms.put(itsCodeBufferTop, switchStart);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[436]++;
        setTableSwitchJump(switchStart, caseIndex, itsCodeBufferTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[437]++;
    }

    /**
     * Set a jump case for a tableswitch instruction. The jump target should
     * be marked as a super block start for stack map generation.
     */
    public void setTableSwitchJump(int switchStart, int caseIndex,
                                   int jumpTarget)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[438]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((0 <= jumpTarget) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((jumpTarget <= itsCodeBufferTop) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[236]++;
            throw new IllegalArgumentException("Bad jump target: "+jumpTarget);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[237]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[439]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((caseIndex >= -1) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[238]++;
            throw new IllegalArgumentException("Bad case index: "+caseIndex);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[239]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[440]++;

        int padSize = 3 & ~switchStart; // == 3 - switchStart % 4
        int caseOffset;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[441]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((caseIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[240]++;
            // default label
            caseOffset = switchStart + 1 + padSize;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[442]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[241]++;
            caseOffset = switchStart + 1 + padSize + 4 * (3 + caseIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[443]++;
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[444]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((0 <= switchStart) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((switchStart <= itsCodeBufferTop - 4 * 4 - padSize - 1) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[242]++;
            throw new IllegalArgumentException(
                switchStart+" is outside a possible range of tableswitch"
                +" in already generated code");

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[243]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[445]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 (((0xFF & itsCodeBuffer[switchStart]) != ByteCode.TABLESWITCH) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[244]++;
            throw new IllegalArgumentException(
                switchStart+" is not offset of tableswitch statement");

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[245]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[446]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C97 |= (8)) == 0 || true) &&
 ((0 <= caseOffset) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((caseOffset + 4 <= itsCodeBufferTop) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[246]++;
            // caseIndex >= -1 does not guarantee that caseOffset >= 0 due
            // to a possible overflow.
            throw new ClassFileFormatException(
                "Too big case index: "+caseIndex);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[247]++;}
        // ALERT: perhaps check against case bounds?
        putInt32(jumpTarget - switchStart, itsCodeBuffer, caseOffset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[447]++;
    }

    public int acquireLabel()
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[448]++;
        int top = itsLabelTableTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[449]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (8)) == 0 || true) &&
 ((itsLabelTable == null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((top == itsLabelTable.length) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[248]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[450]++;
int CodeCoverConditionCoverageHelper_C99;
            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((itsLabelTable == null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[250]++;
                itsLabelTable = new int[MIN_LABEL_TABLE_SIZE];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[451]++;

            }else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[251]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[452]++;
                int[] tmp = new int[itsLabelTable.length * 2];
                System.arraycopy(itsLabelTable, 0, tmp, 0, top);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[453]++;
                itsLabelTable = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[454]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[249]++;}
        itsLabelTableTop = top + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[455]++;
        itsLabelTable[top] = -1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[456]++;
        return top | 0x80000000;
    }

    public void markLabel(int label)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[457]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((label < 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[252]++;
            throw new IllegalArgumentException("Bad label, no biscuit");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[253]++;}

        label &= 0x7FFFFFFF;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[458]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[459]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((label > itsLabelTableTop) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[254]++;
            throw new IllegalArgumentException("Bad label");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[255]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[460]++;
int CodeCoverConditionCoverageHelper_C102;

        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((itsLabelTable[label] != -1) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[256]++;
            throw new IllegalStateException("Can only mark label once");

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[257]++;}

        itsLabelTable[label] = itsCodeBufferTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[461]++;
    }

    public void markLabel(int label, short stackTop)
    {
        markLabel(label);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[462]++;
        itsStackTop = stackTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[463]++;
    }

    public void markHandler(int theLabel) {
        itsStackTop = 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[464]++;
        markLabel(theLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[465]++;
    }

    public int getLabelPC(int label)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[466]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((label < 0) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[258]++;
            throw new IllegalArgumentException("Bad label, no biscuit");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[259]++;}
        label &= 0x7FFFFFFF;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[467]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[468]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((label < itsLabelTableTop) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[260]++;
            throw new IllegalArgumentException("Bad label");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[261]++;}
        return itsLabelTable[label];
    }

    private void addLabelFixup(int label, int fixupSite)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[469]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((label < 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[262]++;
            throw new IllegalArgumentException("Bad label, no biscuit");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[263]++;}
        label &= 0x7FFFFFFF;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[470]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[471]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((label < itsLabelTableTop) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[264]++;
            throw new IllegalArgumentException("Bad label");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[265]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[472]++;
        int top = itsFixupTableTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[473]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 ((itsFixupTable == null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((top == itsFixupTable.length) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[266]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[474]++;
int CodeCoverConditionCoverageHelper_C108;
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((itsFixupTable == null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[268]++;
                itsFixupTable = new long[MIN_FIXUP_TABLE_SIZE];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[475]++;

            }else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[269]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[476]++;
                long[] tmp = new long[itsFixupTable.length * 2];
                System.arraycopy(itsFixupTable, 0, tmp, 0, top);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[477]++;
                itsFixupTable = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[478]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[267]++;}
        itsFixupTableTop = top + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[479]++;
        itsFixupTable[top] = ((long)label << 32) | fixupSite;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[480]++;
    }

    private  void fixLabelGotos()
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[481]++;
        byte[] codeBuffer = itsCodeBuffer;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[482]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[19]++;


int CodeCoverConditionCoverageHelper_C109;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((i < itsFixupTableTop) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[19]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[20]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[21]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[483]++;
            long fixup = itsFixupTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[484]++;
            int label = (int)(fixup >> 32);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[485]++;
            int fixupSite = (int)fixup;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[486]++;
            int pc = itsLabelTable[label];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[487]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((pc == -1) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[270]++;
                // Unlocated label
                throw new RuntimeException();

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[271]++;}
            // -1 to get delta from instruction start
            addSuperBlockStart(pc);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[488]++;
            itsJumpFroms.put(pc, fixupSite - 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[489]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[490]++;
            int offset = pc - (fixupSite - 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[491]++;
int CodeCoverConditionCoverageHelper_C111;
            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 (((short)offset != offset) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[272]++;
                throw new ClassFileFormatException
                    ("Program too complex: too big jump offset");

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[273]++;}
            codeBuffer[fixupSite] = (byte)(offset >> 8);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[492]++;
            codeBuffer[fixupSite + 1] = (byte)offset;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[493]++;
        }
        itsFixupTableTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[494]++;
    }

    /**
     * Get the current offset into the code of the current method.
     *
     * @return an integer representing the offset
     */
    public int getCurrentCodeOffset() {
        return itsCodeBufferTop;
    }

    public short getStackTop() {
        return itsStackTop;
    }

    public void setStackTop(short n) {
        itsStackTop = n;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[495]++;
    }

    public void adjustStackTop(int delta) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[496]++;
        int newStack = itsStackTop + delta;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[497]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (8)) == 0 || true) &&
 ((newStack < 0) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((Short.MAX_VALUE < newStack) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[274]++; badStack(newStack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[498]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[275]++;}
        itsStackTop = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[499]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[500]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((newStack > itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[276]++; itsMaxStack = (short)newStack;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[501]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[277]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[502]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[278]++;
            System.out.println("After "+"adjustStackTop("+delta+")"
                               +" stack = "+itsStackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[503]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[279]++;}
    }

    private void addToCodeBuffer(int b)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[504]++;
        int N = addReservedCodeSpace(1);
        itsCodeBuffer[N] = (byte)b;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[505]++;
    }

    private void addToCodeInt16(int value)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[506]++;
        int N = addReservedCodeSpace(2);
        putInt16(value, itsCodeBuffer, N);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[507]++;
    }

    private int addReservedCodeSpace(int size)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[508]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((itsCurrentMethod == null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[280]++;
            throw new IllegalArgumentException("No method to add to");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[281]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[509]++;
        int oldTop = itsCodeBufferTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[510]++;
        int newTop = oldTop + size;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[511]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((newTop > itsCodeBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[282]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[512]++;
            int newSize = itsCodeBuffer.length * 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[513]++;
int CodeCoverConditionCoverageHelper_C117;
            if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((newTop > newSize) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[284]++; newSize = newTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[514]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[285]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[515]++;
            byte[] tmp = new byte[newSize];
            System.arraycopy(itsCodeBuffer, 0, tmp, 0, oldTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[516]++;
            itsCodeBuffer = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[517]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[283]++;}
        itsCodeBufferTop = newTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[518]++;
        return oldTop;
    }

    public void addExceptionHandler(int startLabel, int endLabel,
                                    int handlerLabel, String catchClassName)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[519]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 (((startLabel & 0x80000000) != 0x80000000) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[286]++;
            throw new IllegalArgumentException("Bad startLabel");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[287]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[520]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 (((endLabel & 0x80000000) != 0x80000000) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[288]++;
            throw new IllegalArgumentException("Bad endLabel");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[289]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[521]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 (((handlerLabel & 0x80000000) != 0x80000000) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[290]++;
            throw new IllegalArgumentException("Bad handlerLabel");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[291]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[522]++;

        /*
         * If catchClassName is null, use 0 for the catch_type_index; which
         * means catch everything.  (Even when the verifier has let you throw
         * something other than a Throwable.)
         */
        short catch_type_index = (catchClassName == null)
                                 ? 0
                                 : itsConstantPool.addClass(catchClassName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[523]++;
        ExceptionTableEntry newEntry = new ExceptionTableEntry(
                                           startLabel,
                                           endLabel,
                                           handlerLabel,
                                           catch_type_index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[524]++;
        int N = itsExceptionTableTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[525]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[292]++;
            itsExceptionTable = new ExceptionTableEntry[ExceptionTableSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[526]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[293]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[527]++;
int CodeCoverConditionCoverageHelper_C122; if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((N == itsExceptionTable.length) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[294]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[528]++;
            ExceptionTableEntry[] tmp = new ExceptionTableEntry[N * 2];
            System.arraycopy(itsExceptionTable, 0, tmp, 0, N);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[529]++;
            itsExceptionTable = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[530]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[295]++;}
}
        itsExceptionTable[N] = newEntry;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[531]++;
        itsExceptionTableTop = N + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[532]++;

    }

    public void addLineNumberEntry(short lineNumber) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[533]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((itsCurrentMethod == null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[296]++;
            throw new IllegalArgumentException("No method to stop");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[297]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[534]++;
        int N = itsLineNumberTableTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[535]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[298]++;
            itsLineNumberTable = new int[LineNumberTableSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[536]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[299]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[537]++;
int CodeCoverConditionCoverageHelper_C125; if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((N == itsLineNumberTable.length) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[300]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[538]++;
            int[] tmp = new int[N * 2];
            System.arraycopy(itsLineNumberTable, 0, tmp, 0, N);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[539]++;
            itsLineNumberTable = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[540]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[301]++;}
}
        itsLineNumberTable[N] = (itsCodeBufferTop << 16) + lineNumber;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[541]++;
        itsLineNumberTableTop = N + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[542]++;
    }

    /**
     * A stack map table is a code attribute introduced in Java 6 that
     * gives type information at key points in the method body (namely, at
     * the beginning of each super block after the first). Each frame of a
     * stack map table contains the state of local variable and operand stack
     * for a given super block.
     */
    final class StackMapTable {
        StackMapTable() {
            superBlocks = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[543]++;
            locals = stack = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[544]++;
            workList = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[545]++;
            rawStackMap = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[546]++;
            localsTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[547]++;
            stackTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[548]++;
            workListTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[549]++;
            rawStackMapTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[550]++;
            wide = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[551]++;
        }

        void generate() {
            superBlocks = new SuperBlock[itsSuperBlockStartsTop];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[552]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[553]++;
            int[] initialLocals = createInitialLocals();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[554]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[22]++;


int CodeCoverConditionCoverageHelper_C126;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((i < itsSuperBlockStartsTop) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[22]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[23]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[24]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[555]++;
                int start = itsSuperBlockStarts[i];
                int end;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[556]++;
int CodeCoverConditionCoverageHelper_C127;
                if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((i == itsSuperBlockStartsTop - 1) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[302]++;
                    end = itsCodeBufferTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[557]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[303]++;
                    end = itsSuperBlockStarts[i + 1];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[558]++;
                }
                superBlocks[i] = new SuperBlock(i, start, end, initialLocals);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[559]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[560]++;
int CodeCoverConditionCoverageHelper_C128;

            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[304]++;
                System.out.println("super blocks: ");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[561]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[562]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[25]++;


int CodeCoverConditionCoverageHelper_C129;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (8)) == 0 || true) &&
 ((i < superBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((superBlocks[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[25]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[26]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[27]++;
}
                    System.out.println("sb " + i + ": [" +
                                       superBlocks[i].getStart() + ", " +
                                       superBlocks[i].getEnd() + ")");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[563]++;
                }

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[305]++;}

            superBlockDeps = getSuperBlockDependencies();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[564]++;

            verify();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[565]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[566]++;
int CodeCoverConditionCoverageHelper_C130;

            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[306]++;
                System.out.println("type information:");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[567]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[568]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[28]++;


int CodeCoverConditionCoverageHelper_C131;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((i < superBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[28]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[29]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[30]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[569]++;
                    SuperBlock sb = superBlocks[i];
                    System.out.println("sb " + i + ":");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[570]++;
                    TypeInfo.print(sb.getLocals(), sb.getStack(),
                                   itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[571]++;
                }

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[307]++;}
        }

        private SuperBlock getSuperBlockFromOffset(int offset) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[572]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[31]++;


int CodeCoverConditionCoverageHelper_C132;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((i < superBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[31]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[32]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[33]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[573]++;
                SuperBlock sb = superBlocks[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[574]++;
int CodeCoverConditionCoverageHelper_C133;
                if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[308]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[575]++;
                    break;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[309]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[576]++;
int CodeCoverConditionCoverageHelper_C134; if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (8)) == 0 || true) &&
 ((offset >= sb.getStart()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((offset < sb.getEnd()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[310]++;
                    return sb;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[311]++;}
}
            }
            throw new IllegalArgumentException("bad offset: " + offset);
        }

        /**
         * Determine whether or not an opcode is an actual end to a super
         * block. This includes any returns or unconditional jumps.
         */
        private boolean isSuperBlockEnd(int opcode) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[577]++;
            switch (opcode) {
                case ByteCode.ARETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[312]++;
                case ByteCode.FRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[313]++;
                case ByteCode.IRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[314]++;
                case ByteCode.LRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[315]++;
                case ByteCode.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[316]++;
                case ByteCode.ATHROW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[317]++;
                case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[318]++;
                case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[319]++;
                case ByteCode.TABLESWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[320]++;
                case ByteCode.LOOKUPSWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[321]++;
                    return true;
                default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[322]++;
                    return false;
            }
        }

        /**
         * Calculate partial dependencies for super blocks.
         *
         * This is used as a workaround for dead code that is generated. Only
         * one dependency per super block is given.
         */
        private SuperBlock[] getSuperBlockDependencies() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[578]++;
            SuperBlock[] deps = new SuperBlock[superBlocks.length];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[579]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[34]++;


int CodeCoverConditionCoverageHelper_C135;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((i < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[34]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[35]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[36]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[580]++;
                ExceptionTableEntry ete = itsExceptionTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[581]++;
                short startPC = (short) getLabelPC(ete.itsStartLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[582]++;
                short handlerPC = (short) getLabelPC(ete.itsHandlerLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[583]++;
                SuperBlock handlerSB = getSuperBlockFromOffset(handlerPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[584]++;
                SuperBlock dep = getSuperBlockFromOffset(startPC);
                deps[handlerSB.getIndex()] = dep;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[585]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[586]++;
            int[] targetPCs = itsJumpFroms.getKeys();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[587]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[37]++;


int CodeCoverConditionCoverageHelper_C136;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((i < targetPCs.length) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[37]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[38]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[39]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[588]++;
                int targetPC = targetPCs[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[589]++;
                int branchPC = itsJumpFroms.getInt(targetPC, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[590]++;
                SuperBlock branchSB = getSuperBlockFromOffset(branchPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[591]++;
                SuperBlock targetSB = getSuperBlockFromOffset(targetPC);
                deps[targetSB.getIndex()] = branchSB;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[592]++;
            }

            return deps;
        }

        /**
         * Get the target super block of a branch instruction.
         *
         * @param bci the index of the branch instruction in the code buffer
         */
        private SuperBlock getBranchTarget(int bci) {
            int target;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[593]++;
int CodeCoverConditionCoverageHelper_C137;
            if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 (((itsCodeBuffer[bci] & 0xFF) == ByteCode.GOTO_W) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[323]++;
                target = bci + getOperand(bci + 1, 4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[594]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[324]++;
                target = bci + (short) getOperand(bci + 1, 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[595]++;
            }
            return getSuperBlockFromOffset(target);
        }

        /**
         * Determine whether or not an opcode is a conditional or unconditional
         * jump.
         */
        private boolean isBranch(int opcode) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[596]++;
            switch (opcode) {
                case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[325]++;
                case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[326]++;
                case ByteCode.IFEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[327]++;
                case ByteCode.IFGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[328]++;
                case ByteCode.IFGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[329]++;
                case ByteCode.IFLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[330]++;
                case ByteCode.IFLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[331]++;
                case ByteCode.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[332]++;
                case ByteCode.IFNONNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[333]++;
                case ByteCode.IFNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[334]++;
                case ByteCode.IF_ACMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[335]++;
                case ByteCode.IF_ACMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[336]++;
                case ByteCode.IF_ICMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[337]++;
                case ByteCode.IF_ICMPGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[338]++;
                case ByteCode.IF_ICMPGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[339]++;
                case ByteCode.IF_ICMPLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[340]++;
                case ByteCode.IF_ICMPLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[341]++;
                case ByteCode.IF_ICMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[342]++;
                    return true;
                default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[343]++;
                    return false;
            }
        }

        private int getOperand(int offset) {
            return getOperand(offset, 1);
        }

        /**
         * Extract a logical operand from the byte code.
         *
         * This is used, for example, to get branch offsets.
         */
        private int getOperand(int start, int size) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[597]++;
            int result = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[598]++;
int CodeCoverConditionCoverageHelper_C138;
            if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((size > 4) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[344]++;
                throw new IllegalArgumentException("bad operand size");

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[345]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[599]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[40]++;


int CodeCoverConditionCoverageHelper_C139;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[40]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[41]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[42]++;
}
                result = (result << 8) | (itsCodeBuffer[start + i] & 0xFF);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[600]++;
            }
            return result;
        }

        /**
         * Calculate initial local variable and op stack types for each super
         * block in the method.
         */
        private void verify() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[601]++;
            int[] initialLocals = createInitialLocals();
            superBlocks[0].merge(initialLocals, initialLocals.length,
                                 new int[0], 0, itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[602]++;

            // Start from the top of the method and queue up block dependencies
            // as they come along.
            workList = new SuperBlock[] { superBlocks[0] };
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[603]++;
            workListTop = 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[604]++;
            executeWorkList();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[605]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[606]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[43]++;


int CodeCoverConditionCoverageHelper_C140;

            // Replace dead code with no-ops.
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((i < superBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[43]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[44]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[45]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[607]++;
                SuperBlock sb = superBlocks[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[608]++;
int CodeCoverConditionCoverageHelper_C141;
                if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((sb.isInitialized()) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[346]++;
                    killSuperBlock(sb);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[609]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[347]++;}
            }
            executeWorkList();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[610]++;
        }

        /**
         * Replace the contents of a super block with no-ops.
         *
         * The above description is not strictly true; the last instruction is
         * an athrow instruction. This technique is borrowed from ASM's
         * developer guide: http://asm.ow2.org/doc/developer-guide.html#deadcode
         *
         * The proposed algorithm fills a block with nop, ending it with an
         * athrow. The stack map generated would be empty locals with an
         * exception on the stack. In theory, it shouldn't matter what the
         * locals are, as long as the stack has an exception for the athrow bit.
         * However, it turns out that if the code being modified falls into an
         * exception handler, it causes problems. Therefore, if it does, then
         * we steal the locals from the exception block.
         *
         * If the block itself is an exception handler, we remove it from the
         * exception table to simplify block dependencies.
         */
        private void killSuperBlock(SuperBlock sb) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[611]++;
            int[] locals = new int[0];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[612]++;
            int[] stack = new int[] { TypeInfo.OBJECT("java/lang/Throwable",
                                                      itsConstantPool) };
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[613]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[46]++;


int CodeCoverConditionCoverageHelper_C142;

            // If the super block is handled by any exception handler, use its
            // locals as the killed block's locals. Ignore uninitialized
            // handlers, because they will also be killed and removed from the
            // exception table.
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((i < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[46]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[47]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[48]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[614]++;
                ExceptionTableEntry ete = itsExceptionTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[615]++;
                int eteStart = getLabelPC(ete.itsStartLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[616]++;
                int eteEnd = getLabelPC(ete.itsEndLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[617]++;
                int handlerPC = getLabelPC(ete.itsHandlerLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[618]++;
                SuperBlock handlerSB = getSuperBlockFromOffset(handlerPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[619]++;
int CodeCoverConditionCoverageHelper_C143;
                if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C143 |= (512)) == 0 || true) &&
 ((sb.getStart() > eteStart) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C143 |= (128)) == 0 || true) &&
 ((sb.getStart() < eteEnd) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (64)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C143 |= (32)) == 0 || true) &&
 ((eteStart > sb.getStart()) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C143 |= (8)) == 0 || true) &&
 ((eteStart < sb.getEnd()) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((handlerSB.isInitialized()) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 5) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 5) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[348]++;
                    locals = handlerSB.getLocals();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[620]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[621]++;
                    break;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[349]++;}
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[622]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[49]++;


int CodeCoverConditionCoverageHelper_C144;

            // Remove any exception table entry whose handler is the killed
            // block. This removes block dependencies to make stack maps for
            // dead blocks easier to create.
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((i < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[49]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[50]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[51]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[623]++;
                ExceptionTableEntry ete = itsExceptionTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[624]++;
                int eteStart = getLabelPC(ete.itsStartLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[625]++;
int CodeCoverConditionCoverageHelper_C145;
                if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((eteStart == sb.getStart()) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[350]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[626]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[52]++;


int CodeCoverConditionCoverageHelper_C146;
                    for (int j = i + 1;(((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((j < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[52]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[53]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[54]++;
}
                        itsExceptionTable[j - 1] = itsExceptionTable[j];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[627]++;
                    }
                    itsExceptionTableTop--;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[628]++;
                    i--;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[629]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[351]++;}
            }

            sb.merge(locals, locals.length, stack, stack.length,
                     itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[630]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[631]++;

            int end = sb.getEnd() - 1;
            itsCodeBuffer[end] = (byte) ByteCode.ATHROW;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[632]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[633]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[55]++;


int CodeCoverConditionCoverageHelper_C147;
            for (int bci = sb.getStart();(((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((bci < end) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false); bci++) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[55]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[56]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[57]++;
}
                itsCodeBuffer[bci] = (byte) ByteCode.NOP;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[634]++;
            }
        }

        private void executeWorkList() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[635]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[58]++;


int CodeCoverConditionCoverageHelper_C148;
            while ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((workListTop > 0) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[58]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[59]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[60]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[636]++;
                SuperBlock work = workList[--workListTop];
                work.setInQueue(false);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[637]++;
                locals = work.getLocals();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[638]++;
                stack = work.getStack();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[639]++;
                localsTop = locals.length;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[640]++;
                stackTop = stack.length;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[641]++;
                executeBlock(work);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[642]++;
            }
        }

        /**
         * Simulate the local variable and op stack for a super block.
         */
        private void executeBlock(SuperBlock work) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[643]++;
            int bc = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[644]++;
            int next = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[645]++;
int CodeCoverConditionCoverageHelper_C149;

            if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[352]++;
                System.out.println("working on sb " + work.getIndex());
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[646]++;
                System.out.println("initial type state:");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[647]++;
                TypeInfo.print(locals, localsTop, stack, stackTop,
                               itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[648]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[353]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[649]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[61]++;


int CodeCoverConditionCoverageHelper_C150;

            for (int bci = work.getStart();(((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((bci < work.getEnd()) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false); bci += next) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[61]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[62]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[63]++;
}
                bc = itsCodeBuffer[bci] & 0xFF;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[650]++;
                next = execute(bci);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[651]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[652]++;
int CodeCoverConditionCoverageHelper_C151;

                // If we have a branch to some super block, we need to merge
                // the current state of the local table and op stack with what's
                // currently stored as the initial state of the super block. If
                // something actually changed, we need to add it to the work
                // list.
                if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((isBranch(bc)) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[354]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[653]++;
                    SuperBlock targetSB = getBranchTarget(bci);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[654]++;
int CodeCoverConditionCoverageHelper_C152;
                    if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[356]++;
                        System.out.println("sb " + work.getIndex() +
                                           " points to sb " +
                                           targetSB.getIndex() +
                                           " (offset " + bci + " -> " +
                                           targetSB.getStart() + ")");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[655]++;
                        System.out.println("type state at " + bci + ":");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[656]++;
                        TypeInfo.print(locals, localsTop, stack, stackTop,
                                       itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[657]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[357]++;}
                    flowInto(targetSB);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[658]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[659]++;
int CodeCoverConditionCoverageHelper_C153;
                    if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[358]++;
                        System.out.println("type state of " + targetSB +
                                           " after merge:");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[660]++;
                        TypeInfo.print(targetSB.getLocals(),
                                       targetSB.getStack(), itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[661]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[359]++;}

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[355]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[662]++;
int CodeCoverConditionCoverageHelper_C154; if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((bc == ByteCode.TABLESWITCH) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[360]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[663]++;
                    int switchStart = bci + 1 + (3 & ~bci);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[664]++; // 3 - bci % 4
                    int defaultOffset = getOperand(switchStart, 4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[665]++;
                    SuperBlock targetSB =
                            getSuperBlockFromOffset(bci + defaultOffset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[666]++;
int CodeCoverConditionCoverageHelper_C155;
                    if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[362]++;
                        System.out.println("merging sb " + work.getIndex() +
                                           " with sb " + targetSB.getIndex());
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[667]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[363]++;}
                    flowInto(targetSB);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[668]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[669]++;
                    int low = getOperand(switchStart + 4, 4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[670]++;
                    int high = getOperand(switchStart + 8, 4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[671]++;
                    int numCases = high - low + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[672]++;
                    int caseBase = switchStart + 12;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[673]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[64]++;


int CodeCoverConditionCoverageHelper_C156;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((i < numCases) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[64]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[65]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[66]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[674]++;
                        int label = bci + getOperand(caseBase + 4 * i, 4);
                        targetSB = getSuperBlockFromOffset(label);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[675]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[676]++;
int CodeCoverConditionCoverageHelper_C157;
                        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[364]++;
                            System.out.println("merging sb " +
                                               work.getIndex() + " with sb " +
                                               targetSB.getIndex());
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[677]++;

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[365]++;}
                        flowInto(targetSB);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[678]++;
                    }

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[361]++;}
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[679]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[67]++;


int CodeCoverConditionCoverageHelper_C158;

                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((i < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[67]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[68]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[69]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[680]++;
                    ExceptionTableEntry ete = itsExceptionTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[681]++;
                    short startPC = (short) getLabelPC(ete.itsStartLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[682]++;
                    short endPC = (short) getLabelPC(ete.itsEndLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[683]++;
int CodeCoverConditionCoverageHelper_C159;
                    if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (8)) == 0 || true) &&
 ((bci < startPC) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((bci >= endPC) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[366]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[684]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[367]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[685]++;
                    short handlerPC =
                            (short) getLabelPC(ete.itsHandlerLabel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[686]++;
                    SuperBlock sb = getSuperBlockFromOffset(handlerPC);
                    int exceptionType;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[687]++;
int CodeCoverConditionCoverageHelper_C160;

                    if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((ete.itsCatchType == 0) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[368]++;
                        exceptionType = TypeInfo.OBJECT(
                            itsConstantPool.addClass("java/lang/Throwable"));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[688]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[369]++;
                        exceptionType = TypeInfo.OBJECT(ete.itsCatchType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[689]++;
                    }
                    sb.merge(locals, localsTop, new int[] { exceptionType }, 1,
                             itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[690]++;
                    addToWorkList(sb);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[691]++;
                }
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[692]++;
int CodeCoverConditionCoverageHelper_C161;

            if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[370]++;
                System.out.println("end of sb " + work.getIndex() + ":");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[693]++;
                TypeInfo.print(locals, localsTop, stack, stackTop,
                               itsConstantPool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[694]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[371]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[695]++;
int CodeCoverConditionCoverageHelper_C162;

            // Check the last instruction to see if it is a true end of a
            // super block (ie., if the instruction is a return). If it
            // isn't, we need to continue processing the next chunk.
            if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((isSuperBlockEnd(bc)) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[372]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[696]++;
                int nextIndex = work.getIndex() + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[697]++;
int CodeCoverConditionCoverageHelper_C163;
                if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((nextIndex < superBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[374]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[698]++;
int CodeCoverConditionCoverageHelper_C164;
                    if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[376]++;
                        System.out.println("continuing from sb " +
                                           work.getIndex() + " into sb " +
                                           nextIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[699]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[377]++;}
                    flowInto(superBlocks[nextIndex]);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[700]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[375]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[373]++;}
        }

        /**
         * Perform a merge of type state and add the super block to the work
         * list if the merge changed anything.
         */
        private void flowInto(SuperBlock sb) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[701]++;
int CodeCoverConditionCoverageHelper_C165;
            if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((sb.merge(locals, localsTop, stack, stackTop, itsConstantPool)) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[378]++;
                addToWorkList(sb);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[702]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[379]++;}
        }

        private void addToWorkList(SuperBlock sb) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[703]++;
int CodeCoverConditionCoverageHelper_C166;
            if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((sb.isInQueue()) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[380]++;
                sb.setInQueue(true);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[704]++;
                sb.setInitialized(true);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[705]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[706]++;
int CodeCoverConditionCoverageHelper_C167;
                if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((workListTop == workList.length) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[382]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[707]++;
                    SuperBlock[] tmp = new SuperBlock[workListTop * 2];
                    System.arraycopy(workList, 0, tmp, 0, workListTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[708]++;
                    workList = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[709]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[383]++;}
                workList[workListTop++] = sb;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[710]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[381]++;}
        }

        /**
         * Execute a single byte code instruction.
         *
         * @param bci the index of the byte code instruction to execute
         * @return the length of the byte code instruction
         */
        private int execute(int bci) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[711]++;
            int bc = itsCodeBuffer[bci] & 0xFF;
            int type, type2, index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[712]++;
            int length = 0;
            long lType, lType2;
            String className;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[713]++;

            switch (bc) {
                case ByteCode.NOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[384]++;
                case ByteCode.IINC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[385]++;
                case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[386]++;
                case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[387]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[714]++;
                    // No change
                    break;
                case ByteCode.CHECKCAST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[388]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[715]++;
                    push(TypeInfo.OBJECT(getOperand(bci + 1, 2)));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[716]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[717]++;
                    break;
                case ByteCode.IASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[389]++; // pop; pop; pop
                case ByteCode.LASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[390]++;
                case ByteCode.FASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[391]++;
                case ByteCode.DASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[392]++;
                case ByteCode.AASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[393]++;
                case ByteCode.BASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[394]++;
                case ByteCode.CASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[395]++;
                case ByteCode.SASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[396]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[718]++;
                case ByteCode.PUTFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[397]++; // pop; pop
                case ByteCode.IF_ICMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[398]++;
                case ByteCode.IF_ICMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[399]++;
                case ByteCode.IF_ICMPLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[400]++;
                case ByteCode.IF_ICMPGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[401]++;
                case ByteCode.IF_ICMPGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[402]++;
                case ByteCode.IF_ICMPLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[403]++;
                case ByteCode.IF_ACMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[404]++;
                case ByteCode.IF_ACMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[405]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[719]++;
                case ByteCode.IFEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[406]++; // pop
                case ByteCode.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[407]++;
                case ByteCode.IFLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[408]++;
                case ByteCode.IFGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[409]++;
                case ByteCode.IFGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[410]++;
                case ByteCode.IFLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[411]++;
                case ByteCode.IFNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[412]++;
                case ByteCode.IFNONNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[413]++;
                case ByteCode.POP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[414]++;
                case ByteCode.MONITORENTER:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[415]++;
                case ByteCode.MONITOREXIT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[416]++;
                case ByteCode.PUTSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[417]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[720]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[721]++;
                    break;
                case ByteCode.POP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[418]++;
                    pop2();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[722]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[723]++;
                    break;
                case ByteCode.ACONST_NULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[419]++;
                    push(TypeInfo.NULL);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[724]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[725]++;
                    break;
                case ByteCode.IALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[420]++; // pop; pop; push(INTEGER)
                case ByteCode.BALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[421]++;
                case ByteCode.CALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[422]++;
                case ByteCode.SALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[423]++;
                case ByteCode.IADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[424]++;
                case ByteCode.ISUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[425]++;
                case ByteCode.IMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[426]++;
                case ByteCode.IDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[427]++;
                case ByteCode.IREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[428]++;
                case ByteCode.ISHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[429]++;
                case ByteCode.ISHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[430]++;
                case ByteCode.IUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[431]++;
                case ByteCode.IAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[432]++;
                case ByteCode.IOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[433]++;
                case ByteCode.IXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[434]++;
                case ByteCode.LCMP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[435]++;
                case ByteCode.FCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[436]++;
                case ByteCode.FCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[437]++;
                case ByteCode.DCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[438]++;
                case ByteCode.DCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[439]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[726]++;
                case ByteCode.INEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[440]++; // pop; push(INTEGER)
                case ByteCode.L2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[441]++;
                case ByteCode.F2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[442]++;
                case ByteCode.D2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[443]++;
                case ByteCode.I2B:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[444]++;
                case ByteCode.I2C:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[445]++;
                case ByteCode.I2S:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[446]++;
                case ByteCode.ARRAYLENGTH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[447]++;
                case ByteCode.INSTANCEOF:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[448]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[727]++;
                case ByteCode.ICONST_M1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[449]++; // push(INTEGER)
                case ByteCode.ICONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[450]++;
                case ByteCode.ICONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[451]++;
                case ByteCode.ICONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[452]++;
                case ByteCode.ICONST_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[453]++;
                case ByteCode.ICONST_4:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[454]++;
                case ByteCode.ICONST_5:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[455]++;
                case ByteCode.ILOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[456]++;
                case ByteCode.ILOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[457]++;
                case ByteCode.ILOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[458]++;
                case ByteCode.ILOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[459]++;
                case ByteCode.ILOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[460]++;
                case ByteCode.BIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[461]++;
                case ByteCode.SIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[462]++;
                    push(TypeInfo.INTEGER);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[728]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[729]++;
                    break;
                case ByteCode.LALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[463]++; // pop; pop; push(LONG)
                case ByteCode.LADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[464]++;
                case ByteCode.LSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[465]++;
                case ByteCode.LMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[466]++;
                case ByteCode.LDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[467]++;
                case ByteCode.LREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[468]++;
                case ByteCode.LSHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[469]++;
                case ByteCode.LSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[470]++;
                case ByteCode.LUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[471]++;
                case ByteCode.LAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[472]++;
                case ByteCode.LOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[473]++;
                case ByteCode.LXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[474]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[730]++;
                case ByteCode.LNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[475]++; // pop; push(LONG)
                case ByteCode.I2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[476]++;
                case ByteCode.F2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[477]++;
                case ByteCode.D2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[478]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[731]++;
                case ByteCode.LCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[479]++; // push(LONG)
                case ByteCode.LCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[480]++;
                case ByteCode.LLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[481]++;
                case ByteCode.LLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[482]++;
                case ByteCode.LLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[483]++;
                case ByteCode.LLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[484]++;
                case ByteCode.LLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[485]++;
                    push(TypeInfo.LONG);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[732]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[733]++;
                    break;
                case ByteCode.FALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[486]++; // pop; pop; push(FLOAT)
                case ByteCode.FADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[487]++;
                case ByteCode.FSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[488]++;
                case ByteCode.FMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[489]++;
                case ByteCode.FDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[490]++;
                case ByteCode.FREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[491]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[734]++;
                case ByteCode.FNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[492]++; // pop; push(FLOAT)
                case ByteCode.I2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[493]++;
                case ByteCode.L2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[494]++;
                case ByteCode.D2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[495]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[735]++;
                case ByteCode.FCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[496]++; // push(FLOAT)
                case ByteCode.FCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[497]++;
                case ByteCode.FCONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[498]++;
                case ByteCode.FLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[499]++;
                case ByteCode.FLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[500]++;
                case ByteCode.FLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[501]++;
                case ByteCode.FLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[502]++;
                case ByteCode.FLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[503]++;
                    push(TypeInfo.FLOAT);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[736]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[737]++;
                    break;
                case ByteCode.DALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[504]++; // pop; pop; push(DOUBLE)
                case ByteCode.DADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[505]++;
                case ByteCode.DSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[506]++;
                case ByteCode.DMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[507]++;
                case ByteCode.DDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[508]++;
                case ByteCode.DREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[509]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[738]++;
                case ByteCode.DNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[510]++; // pop; push(DOUBLE)
                case ByteCode.I2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[511]++;
                case ByteCode.L2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[512]++;
                case ByteCode.F2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[513]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[739]++;
                case ByteCode.DCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[514]++; // push(DOUBLE)
                case ByteCode.DCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[515]++;
                case ByteCode.DLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[516]++;
                case ByteCode.DLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[517]++;
                case ByteCode.DLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[518]++;
                case ByteCode.DLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[519]++;
                case ByteCode.DLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[520]++;
                    push(TypeInfo.DOUBLE);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[740]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[741]++;
                    break;
                case ByteCode.ISTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[521]++;
                    executeStore(getOperand(bci + 1, wide ? 2 : 1), TypeInfo.INTEGER);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[742]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[743]++;
                    break;
                case ByteCode.ISTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[522]++;
                case ByteCode.ISTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[523]++;
                case ByteCode.ISTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[524]++;
                case ByteCode.ISTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[525]++;
                    executeStore(bc - ByteCode.ISTORE_0, TypeInfo.INTEGER);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[744]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[745]++;
                    break;
                case ByteCode.LSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[526]++;
                    executeStore(getOperand(bci + 1, wide ? 2 : 1), TypeInfo.LONG);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[746]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[747]++;
                    break;
                case ByteCode.LSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[527]++;
                case ByteCode.LSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[528]++;
                case ByteCode.LSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[529]++;
                case ByteCode.LSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[530]++;
                    executeStore(bc - ByteCode.LSTORE_0, TypeInfo.LONG);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[748]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[749]++;
                    break;
                case ByteCode.FSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[531]++;
                    executeStore(getOperand(bci + 1, wide ? 2 : 1), TypeInfo.FLOAT);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[750]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[751]++;
                    break;
                case ByteCode.FSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[532]++;
                case ByteCode.FSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[533]++;
                case ByteCode.FSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[534]++;
                case ByteCode.FSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[535]++;
                    executeStore(bc - ByteCode.FSTORE_0, TypeInfo.FLOAT);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[752]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[753]++;
                    break;
                case ByteCode.DSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[536]++;
                    executeStore(getOperand(bci + 1, wide ? 2 : 1), TypeInfo.DOUBLE);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[754]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[755]++;
                    break;
                case ByteCode.DSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[537]++;
                case ByteCode.DSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[538]++;
                case ByteCode.DSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[539]++;
                case ByteCode.DSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[540]++;
                    executeStore(bc - ByteCode.DSTORE_0, TypeInfo.DOUBLE);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[756]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[757]++;
                    break;
                case ByteCode.ALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[541]++;
                    executeALoad(getOperand(bci + 1, wide ? 2 : 1));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[758]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[759]++;
                    break;
                case ByteCode.ALOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[542]++;
                case ByteCode.ALOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[543]++;
                case ByteCode.ALOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[544]++;
                case ByteCode.ALOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[545]++;
                    executeALoad(bc - ByteCode.ALOAD_0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[760]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[761]++;
                    break;
                case ByteCode.ASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[546]++;
                    executeAStore(getOperand(bci + 1, wide ? 2 : 1));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[762]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[763]++;
                    break;
                case ByteCode.ASTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[547]++;
                case ByteCode.ASTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[548]++;
                case ByteCode.ASTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[549]++;
                case ByteCode.ASTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[550]++;
                    executeAStore(bc - ByteCode.ASTORE_0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[764]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[765]++;
                    break;
                case ByteCode.IRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[551]++;
                case ByteCode.LRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[552]++;
                case ByteCode.FRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[553]++;
                case ByteCode.DRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[554]++;
                case ByteCode.ARETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[555]++;
                case ByteCode.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[556]++;
                    clearStack();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[766]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[767]++;
                    break;
                case ByteCode.ATHROW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[557]++;
                    type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[768]++;
                    clearStack();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[769]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[770]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[771]++;
                    break;
                case ByteCode.SWAP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[558]++;
                    type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[772]++;
                    type2 = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[773]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[774]++;
                    push(type2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[775]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[776]++;
                    break;
                case ByteCode.LDC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[559]++;
                case ByteCode.LDC_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[560]++;
                case ByteCode.LDC2_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[561]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[777]++;
int CodeCoverConditionCoverageHelper_C168;
                    if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((bc == ByteCode.LDC) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[562]++;
                        index = getOperand(bci + 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[778]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[563]++;
                        index = getOperand(bci + 1, 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[779]++;
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[780]++;
                    byte constType = itsConstantPool.getConstantType(index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[781]++;
                    switch (constType) {
                        case ConstantPool.CONSTANT_Double:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[564]++;
                            push(TypeInfo.DOUBLE);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[782]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[783]++;
                            break;
                        case ConstantPool.CONSTANT_Float:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[565]++;
                            push(TypeInfo.FLOAT);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[784]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[785]++;
                            break;
                        case ConstantPool.CONSTANT_Long:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[566]++;
                            push(TypeInfo.LONG);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[786]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[787]++;
                            break;
                        case ConstantPool.CONSTANT_Integer:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[567]++;
                            push(TypeInfo.INTEGER);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[788]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[789]++;
                            break;
                        case ConstantPool.CONSTANT_String:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[568]++;
                            push(TypeInfo.OBJECT("java/lang/String",
                                                 itsConstantPool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[790]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[791]++;
                            break;
                        default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[569]++;
                            throw new IllegalArgumentException(
                                "bad const type " + constType);
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[792]++;
                    break;
                case ByteCode.NEW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[570]++;
                    push(TypeInfo.UNINITIALIZED_VARIABLE(bci));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[793]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[794]++;
                    break;
                case ByteCode.NEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[571]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[795]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[796]++;
                    char componentType =
                            arrayTypeToName(itsCodeBuffer[bci + 1]);
                    index = itsConstantPool.addClass("[" + componentType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[797]++;
                    push(TypeInfo.OBJECT((short) index));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[798]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[799]++;
                    break;
                case ByteCode.ANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[572]++;
                    index = getOperand(bci + 1, 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[800]++;
                    className = (String) itsConstantPool.getConstantData(index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[801]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[802]++;
                    push(TypeInfo.OBJECT("[L" + className + ';',
                                         itsConstantPool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[803]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[804]++;
                    break;
                case ByteCode.INVOKEVIRTUAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[573]++;
                case ByteCode.INVOKESPECIAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[574]++;
                case ByteCode.INVOKESTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[575]++;
                case ByteCode.INVOKEINTERFACE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[576]++;
                    index = getOperand(bci + 1, 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[805]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[806]++;
                    FieldOrMethodRef m = (FieldOrMethodRef)
                            itsConstantPool.getConstantData(index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[807]++;
                    String methodType = m.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[808]++;
                    String methodName = m.getName();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[809]++;
                    int parameterCount = sizeOfParameters(methodType) >>> 16;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[810]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[70]++;


int CodeCoverConditionCoverageHelper_C169;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((i < parameterCount) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[70]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[71]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[72]++;
}
                        pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[811]++;
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[812]++;
int CodeCoverConditionCoverageHelper_C170;
                    if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((bc != ByteCode.INVOKESTATIC) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[577]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[813]++;
                        int instType = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[814]++;
                        int tag = TypeInfo.getTag(instType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[815]++;
int CodeCoverConditionCoverageHelper_C171;
                        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (8)) == 0 || true) &&
 ((tag == TypeInfo.UNINITIALIZED_VARIABLE(0)) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((tag == TypeInfo.UNINITIALIZED_THIS) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[579]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[816]++;
int CodeCoverConditionCoverageHelper_C172;
                            if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 (("<init>".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[581]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[817]++;
                                int newType =
                                        TypeInfo.OBJECT(itsThisClassIndex);
                                initializeTypeInfo(instType, newType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[818]++;

                            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[582]++;
                                throw new IllegalStateException("bad instance");
                            }

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[580]++;}

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[578]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[819]++;
                    int rParen = methodType.indexOf(')');
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[820]++;
                    String returnType = methodType.substring(rParen + 1);
                    returnType = descriptorToInternalName(returnType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[821]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[822]++;
int CodeCoverConditionCoverageHelper_C173;
                    if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((returnType.equals("V")) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[583]++;
                        push(TypeInfo.fromType(returnType, itsConstantPool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[823]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[584]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[824]++;
                    break;
                case ByteCode.GETFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[585]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[825]++;
                case ByteCode.GETSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[586]++;
                    index = getOperand(bci + 1, 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[826]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[827]++;
                    FieldOrMethodRef f = (FieldOrMethodRef)
                            itsConstantPool.getConstantData(index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[828]++;
                    String fieldType = descriptorToInternalName(f.getType());
                    push(TypeInfo.fromType(fieldType, itsConstantPool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[829]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[830]++;
                    break;
                case ByteCode.DUP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[587]++;
                    type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[831]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[832]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[833]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[834]++;
                    break;
                case ByteCode.DUP_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[588]++;
                    type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[835]++;
                    type2 = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[836]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[837]++;
                    push(type2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[838]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[839]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[840]++;
                    break;
                case ByteCode.DUP_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[589]++;
                    type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[841]++;
                    lType = pop2();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[842]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[843]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[844]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[845]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[846]++;
                    break;
                case ByteCode.DUP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[590]++;
                    lType = pop2();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[847]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[848]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[849]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[850]++;
                    break;
                case ByteCode.DUP2_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[591]++;
                    lType = pop2();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[851]++;
                    type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[852]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[853]++;
                    push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[854]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[855]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[856]++;
                    break;
                case ByteCode.DUP2_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[592]++;
                    lType = pop2();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[857]++;
                    lType2 = pop2();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[858]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[859]++;
                    push2(lType2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[860]++;
                    push2(lType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[861]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[862]++;
                    break;
                case ByteCode.TABLESWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[593]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[863]++;
                    int switchStart = bci + 1 + (3 & ~bci);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[864]++;
                    int low = getOperand(switchStart + 4, 4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[865]++;
                    int high = getOperand(switchStart + 8, 4);
                    length = 4 * (high - low + 4) + switchStart - bci;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[866]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[867]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[868]++;
                    break;
                case ByteCode.AALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[594]++;
                    pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[869]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[870]++;
                    int typeIndex = pop() >>> 8;
                    className =
                            (String) itsConstantPool.getConstantData(typeIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[871]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[872]++;
                    String arrayType = className;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[873]++;
int CodeCoverConditionCoverageHelper_C174;
                    if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((arrayType.charAt(0) != '[') && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[595]++;
                        throw new IllegalStateException("bad array type");

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[596]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[874]++;
                    String elementDesc = arrayType.substring(1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[875]++;
                    String elementType = descriptorToInternalName(elementDesc);
                    typeIndex = itsConstantPool.addClass(elementType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[876]++;
                    push(TypeInfo.OBJECT(typeIndex));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[877]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[878]++;
                    break;
                case ByteCode.WIDE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[597]++;
                    // Alters behaviour of next instruction
                    wide = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[879]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[880]++;
                    break;
                case ByteCode.MULTIANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[598]++;
                case ByteCode.LOOKUPSWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[599]++;
                    // Currently not used in any part of Rhino, so ignore it
                case ByteCode.JSR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[600]++; // TODO: JSR is deprecated
                case ByteCode.RET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[601]++;
                case ByteCode.JSR_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[602]++;
                default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[603]++;
                    throw new IllegalArgumentException("bad opcode: " + bc);
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[881]++;
int CodeCoverConditionCoverageHelper_C175;

            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[604]++;
                length = opcodeLength(bc, wide);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[882]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[605]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[883]++;
int CodeCoverConditionCoverageHelper_C176;
            if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (8)) == 0 || true) &&
 ((wide) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((bc != ByteCode.WIDE) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[606]++;
                wide = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[884]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[607]++;}
            return length;
        }

        private void executeALoad(int localIndex) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[885]++;
            int type = getLocal(localIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[886]++;
            int tag = TypeInfo.getTag(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[887]++;
int CodeCoverConditionCoverageHelper_C177;
            if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (128)) == 0 || true) &&
 ((tag == TypeInfo.OBJECT_TAG) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C177 |= (32)) == 0 || true) &&
 ((tag == TypeInfo.UNINITIALIZED_THIS) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C177 |= (8)) == 0 || true) &&
 ((tag == TypeInfo.UNINITIALIZED_VAR_TAG) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((tag == TypeInfo.NULL) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 4) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 4) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[608]++;
                push(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[888]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[609]++;
                throw new IllegalStateException("bad local variable type: " +
                                                type + " at index: " +
                                                localIndex);
            }
        }

        private void executeAStore(int localIndex) {
            setLocal(localIndex, pop());
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[889]++;
        }

        private void executeStore(int localIndex, int typeInfo) {
            pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[890]++;
            setLocal(localIndex, typeInfo);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[891]++;
        }

        /**
         * Change an UNINITIALIZED_OBJECT or UNINITIALIZED_THIS to the proper
         * type of the object. This occurs when the proper constructor is
         * invoked.
         */
        private void initializeTypeInfo(int prevType, int newType) {
            initializeTypeInfo(prevType, newType, locals, localsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[892]++;
            initializeTypeInfo(prevType, newType, stack, stackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[893]++;
        }

        private void initializeTypeInfo(int prevType, int newType, int[] data,
                                        int dataTop) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[894]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[73]++;


int CodeCoverConditionCoverageHelper_C178;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((i < dataTop) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[73]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[74]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[75]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[895]++;
int CodeCoverConditionCoverageHelper_C179;
                if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((data[i] == prevType) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[610]++;
                    data[i] = newType;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[896]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[611]++;}
            }
        }

        private int getLocal(int localIndex) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[897]++;
int CodeCoverConditionCoverageHelper_C180;
            if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((localIndex < localsTop) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[612]++;
                return locals[localIndex];

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[613]++;
                return TypeInfo.TOP;
            }
        }

        private void setLocal(int localIndex, int typeInfo) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[898]++;
int CodeCoverConditionCoverageHelper_C181;
            if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((localIndex >= localsTop) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[614]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[899]++;
                int[] tmp = new int[localIndex + 1];
                System.arraycopy(locals, 0, tmp, 0, localsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[900]++;
                locals = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[901]++;
                localsTop = localIndex + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[902]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[615]++;}
            locals[localIndex] = typeInfo;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[903]++;
        }

        private void push(int typeInfo) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[904]++;
int CodeCoverConditionCoverageHelper_C182;
            if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((stackTop == stack.length) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[616]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[905]++;
                int[] tmp = new int[Math.max(stackTop * 2, 4)];
                System.arraycopy(stack, 0, tmp, 0, stackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[906]++;
                stack = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[907]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[617]++;}
            stack[stackTop++] = typeInfo;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[908]++;
        }

        private int pop() {
            return stack[--stackTop];
        }

        /**
         * Push two words onto the op stack.
         *
         * This is only meant to be used as a complement to pop2(), and both
         * methods are helpers for the more complex DUP operations.
         */
        private void push2(long typeInfo) {
            push((int) (typeInfo & 0xFFFFFF));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[909]++;
            typeInfo >>>= 32;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[910]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[911]++;
int CodeCoverConditionCoverageHelper_C183;
            if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((typeInfo != 0) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[618]++;
                push((int) (typeInfo & 0xFFFFFF));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[912]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[619]++;}
        }

        /**
         * Pop two words from the op stack.
         *
         * If the top of the stack is a DOUBLE or LONG, then the bottom 32 bits
         * reflects the appropriate type and the top 32 bits are 0. Otherwise,
         * the top 32 bits are the first word on the stack and the lower 32
         * bits are the second word on the stack.
         */
        private long pop2() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[913]++;
            long type = pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[914]++;
int CodeCoverConditionCoverageHelper_C184;
            if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((TypeInfo.isTwoWords((int) type)) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[620]++;
                return type;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[621]++;
                return type << 32 | (pop() & 0xFFFFFF);
            }
        }

        private void clearStack() {
            stackTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[915]++;
        }

        /**
         * Compute the output size of the stack map table.
         *
         * Because this would share much in common with actual writing of the
         * stack map table, we instead just write the stack map table to a
         * buffer and return the size from it. The buffer is later used in
         * the actual writing of bytecode.
         */
        int computeWriteSize() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[916]++;
            // Allocate a buffer that can handle the worst case size of the
            // stack map to prevent lots of reallocations.
            int writeSize = getWorstCaseWriteSize();
            rawStackMap = new byte[writeSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[917]++;
            computeRawStackMap();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[918]++;
            return rawStackMapTop + 2;
        }

        int write(byte[] data, int offset) {
            offset = putInt32(rawStackMapTop + 2, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[919]++;
            offset = putInt16(superBlocks.length - 1, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[920]++;
            System.arraycopy(rawStackMap, 0, data, offset, rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[921]++;
            return offset + rawStackMapTop;
        }

        /**
         * Compute a space-optimal stack map table.
         */
        private void computeRawStackMap() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[922]++;
            SuperBlock prev = superBlocks[0];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[923]++;
            int[] prevLocals = prev.getTrimmedLocals();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[924]++;
            int prevOffset = -1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[925]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[76]++;


int CodeCoverConditionCoverageHelper_C185;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((i < superBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[76]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[77]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[78]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[926]++;
                SuperBlock current = superBlocks[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[927]++;
                int[] currentLocals = current.getTrimmedLocals();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[928]++;
                int[] currentStack = current.getStack();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[929]++;
                int offsetDelta = current.getStart() - prevOffset - 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[930]++;
int CodeCoverConditionCoverageHelper_C186;

                if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((currentStack.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[622]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[931]++;
                    int last = prevLocals.length > currentLocals.length ?
                            currentLocals.length : prevLocals.length;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[932]++;
                    int delta = Math.abs(prevLocals.length -
                                         currentLocals.length);
                    int j;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[933]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[79]++;


int CodeCoverConditionCoverageHelper_C187;
                    // Compare locals until one is different or the end of a
                    // local variable array is reached
                    for (j = 0;(((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((j < last) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[79]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[80]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[81]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[934]++;
int CodeCoverConditionCoverageHelper_C188;
                        if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((prevLocals[j] != currentLocals[j]) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[624]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[935]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[625]++;}
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[936]++;
int CodeCoverConditionCoverageHelper_C189;
                    if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (8)) == 0 || true) &&
 ((j == currentLocals.length) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((delta == 0) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[626]++;
                        // All of the compared locals are equal and the local
                        // arrays are of equal size
                        writeSameFrame(currentLocals, offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[937]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[627]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[938]++;
int CodeCoverConditionCoverageHelper_C190; if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (8)) == 0 || true) &&
 ((j == currentLocals.length) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((delta <= 3) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[628]++;
                        // All of the compared locals are equal and the current
                        // frame has less locals than the previous frame
                        writeChopFrame(delta, offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[939]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[629]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[940]++;
int CodeCoverConditionCoverageHelper_C191; if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (8)) == 0 || true) &&
 ((j == prevLocals.length) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((delta <= 3) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[630]++;
                        // All of the compared locals are equal and the current
                        // frame has more locals than the previous frame
                        writeAppendFrame(currentLocals, delta, offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[941]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[631]++;
                        // Not all locals were compared were equal, so a full
                        // frame is necessary
                        writeFullFrame(currentLocals, currentStack,
                                       offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[942]++;
                    }
}
}

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[623]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[943]++;
int CodeCoverConditionCoverageHelper_C192; if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((currentStack.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[632]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[944]++;
int CodeCoverConditionCoverageHelper_C193;
                    if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((Arrays.equals(prevLocals, currentLocals)) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[634]++;
                       writeSameLocalsOneStackItemFrame(currentLocals,
                                                        currentStack,
                                                        offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[945]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[635]++;
                        // Output a full frame, since no other frame types have
                        // one operand stack item.
                        writeFullFrame(currentLocals, currentStack,
                                       offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[946]++;
                    }

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[633]++;
                    // Any stack map frame that has more than one operand stack
                    // item has to be a full frame. All other frame types have
                    // at most one item on the stack.
                    writeFullFrame(currentLocals, currentStack, offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[947]++;
                }
}

                prev = current;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[948]++;
                prevLocals = currentLocals;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[949]++;
                prevOffset = current.getStart();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[950]++;
            }
        }

        /**
         * Get the worst case write size of the stack map table.
         *
         * This computes how much full frames would take, if each full frame
         * contained the maximum number of locals and stack operands, and each
         * verification type was 3 bytes.
         */
        private int getWorstCaseWriteSize() {
            return (superBlocks.length - 1) * (7 + itsMaxLocals * 3 +
                                               itsMaxStack * 3);
        }

        private void writeSameFrame(int[] locals, int offsetDelta) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[951]++;
int CodeCoverConditionCoverageHelper_C194;
            if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((offsetDelta <= 63) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[636]++;
                // Output a same_frame frame. Despite the name,
                // the operand stack may differ, but the current
                // operand stack must be empty.
                rawStackMap[rawStackMapTop++] = (byte) offsetDelta;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[952]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[637]++;
                // Output a same_frame_extended frame. Similar to
                // the above, except with a larger offset delta.
                rawStackMap[rawStackMapTop++] = (byte) 251;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[953]++;
                rawStackMapTop = putInt16(offsetDelta, rawStackMap,
                                          rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[954]++;
            }
        }

        private void writeSameLocalsOneStackItemFrame(int[] locals,
                                                      int[] stack,
                                                      int offsetDelta) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[955]++;
int CodeCoverConditionCoverageHelper_C195;
            if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((offsetDelta <= 63) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[638]++;
                // Output a same_locals_1_stack_item frame. Similar
                // to same_frame, only with one item on the operand
                // stack instead of zero.
                rawStackMap[rawStackMapTop++] = (byte) (64 + offsetDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[956]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[639]++;
                // Output a same_locals_1_stack_item_extended frame.
                // Similar to same_frame_extended, only with one
                // item on the operand stack instead of zero.
                rawStackMap[rawStackMapTop++] = (byte) 247;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[957]++;
                rawStackMapTop = putInt16(offsetDelta, rawStackMap,
                                          rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[958]++;
            }
            writeType(stack[0]);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[959]++;
        }

        private void writeFullFrame(int[] locals, int[] stack,
                                    int offsetDelta) {
            rawStackMap[rawStackMapTop++] = (byte) 255;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[960]++;
            rawStackMapTop = putInt16(offsetDelta, rawStackMap, rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[961]++;
            rawStackMapTop = putInt16(locals.length, rawStackMap,
                                      rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[962]++;
            rawStackMapTop = writeTypes(locals);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[963]++;
            rawStackMapTop = putInt16(stack.length, rawStackMap,
                                      rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[964]++;
            rawStackMapTop = writeTypes(stack);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[965]++;
        }

        private void writeAppendFrame(int[] locals, int localsDelta,
                                      int offsetDelta) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[966]++;
            int start = locals.length - localsDelta;
            rawStackMap[rawStackMapTop++] = (byte) (251 + localsDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[967]++;
            rawStackMapTop = putInt16(offsetDelta, rawStackMap, rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[968]++;
            rawStackMapTop = writeTypes(locals, start);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[969]++;
        }

        private void writeChopFrame(int localsDelta, int offsetDelta) {
            rawStackMap[rawStackMapTop++] = (byte) (251 - localsDelta);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[970]++;
            rawStackMapTop = putInt16(offsetDelta, rawStackMap, rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[971]++;
        }

        private int writeTypes(int[] types) {
            return writeTypes(types, 0);
        }

        private int writeTypes(int[] types, int start) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[972]++;
            int startOffset = rawStackMapTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[973]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[82]++;


int CodeCoverConditionCoverageHelper_C196;
            for (int i = start;(((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((i < types.length) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[82]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[83]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[84]++;
}
                rawStackMapTop = writeType(types[i]);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[974]++;
            }
            return rawStackMapTop;
        }

        private int writeType(int type) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[975]++;
            int tag = type & 0xFF;
            rawStackMap[rawStackMapTop++] = (byte) tag;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[976]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[977]++;
int CodeCoverConditionCoverageHelper_C197;
            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (8)) == 0 || true) &&
 ((tag == TypeInfo.OBJECT_TAG) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((tag == TypeInfo.UNINITIALIZED_VAR_TAG) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[640]++;
                rawStackMapTop = putInt16(type >>> 8, rawStackMap,
                                          rawStackMapTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[978]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[641]++;}
            return rawStackMapTop;
        }

        // Intermediate operand stack and local variable state. During
        // execution of a block, these are initialized to copies of the initial
        // block type state and are modified by the actual stack/local
        // emulation.
        private int[] locals;
        private int localsTop;
        private int[] stack;
        private int stackTop;

        private SuperBlock[] workList;
        private int workListTop;

        private SuperBlock[] superBlocks;
        private SuperBlock[] superBlockDeps;

        private byte[] rawStackMap;
        private int rawStackMapTop;

        private boolean wide;

        static final boolean DEBUGSTACKMAP = false;
    }

    /**
     * Convert a newarray operand into an internal type.
     */
    private static char arrayTypeToName(int type) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[979]++;
        switch (type) {
            case ByteCode.T_BOOLEAN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[642]++;
                return 'Z';
            case ByteCode.T_CHAR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[643]++;
                return 'C';
            case ByteCode.T_FLOAT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[644]++;
                return 'F';
            case ByteCode.T_DOUBLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[645]++;
                return 'D';
            case ByteCode.T_BYTE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[646]++;
                return 'B';
            case ByteCode.T_SHORT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[647]++;
                return 'S';
            case ByteCode.T_INT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[648]++;
                return 'I';
            case ByteCode.T_LONG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[649]++;
                return 'J';
            default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[650]++;
                throw new IllegalArgumentException("bad operand");
        }
    }

    /**
     * Convert a class descriptor into an internal name.
     *
     * For example, descriptor Ljava/lang/Object; becomes java/lang/Object.
     */
    private static String classDescriptorToInternalName(String descriptor) {
        return descriptor.substring(1, descriptor.length() - 1);
    }

    /**
     * Convert a non-method type descriptor into an internal type.
     *
     * @param descriptor the simple type descriptor to convert
     */
    private static String descriptorToInternalName(String descriptor) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[980]++;
        switch (descriptor.charAt(0)) {
            case 'B':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[651]++;
            case 'C':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[652]++;
            case 'D':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[653]++;
            case 'F':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[654]++;
            case 'I':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[655]++;
            case 'J':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[656]++;
            case 'S':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[657]++;
            case 'Z':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[658]++;
            case 'V':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[659]++;
            case '[':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[660]++;
                return descriptor;
            case 'L':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[661]++;
                return classDescriptorToInternalName(descriptor);
            default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[662]++;
                throw new IllegalArgumentException("bad descriptor:" +
                                                   descriptor);
        }
    }

    /**
     * Compute the initial local variable array for the current method.
     *
     * Creates an array of the size of the method's max locals, regardless of
     * the number of parameters in the method.
     */
    private int[] createInitialLocals() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[981]++;
        int[] initialLocals = new int[itsMaxLocals];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[982]++;
        int localsTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[983]++;
int CodeCoverConditionCoverageHelper_C198;
        // Instance methods require the first local variable in the array
        // to be "this". However, if the method being created is a
        // constructor, aka the method is <init>, then the type of "this"
        // should be StackMapTable.UNINITIALIZED_THIS
        if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 (((itsCurrentMethod.getFlags() & ACC_STATIC) == 0) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[663]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[984]++;
int CodeCoverConditionCoverageHelper_C199;
            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 (("<init>".equals(itsCurrentMethod.getName())) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[665]++;
                initialLocals[localsTop++] = TypeInfo.UNINITIALIZED_THIS;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[985]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[666]++;
                initialLocals[localsTop++] = TypeInfo.OBJECT(itsThisClassIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[986]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[664]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[987]++;

        // No error checking should be necessary, sizeOfParameters does this
        String type = itsCurrentMethod.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[988]++;
        int lParenIndex = type.indexOf('(');
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[989]++;
        int rParenIndex = type.indexOf(')');
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[990]++;
int CodeCoverConditionCoverageHelper_C200;
        if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (8)) == 0 || true) &&
 ((lParenIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((rParenIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[667]++;
            throw new IllegalArgumentException("bad method type");

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[668]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[991]++;
        int start = lParenIndex + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[992]++;
        StringBuilder paramType = new StringBuilder();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[993]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[85]++;


int CodeCoverConditionCoverageHelper_C201;
        while ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((start < rParenIndex) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[85]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[86]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[87]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[994]++;
            switch (type.charAt(start)) {
                case 'B':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[669]++;
                case 'C':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[670]++;
                case 'D':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[671]++;
                case 'F':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[672]++;
                case 'I':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[673]++;
                case 'J':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[674]++;
                case 'S':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[675]++;
                case 'Z':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[676]++;
                    paramType.append(type.charAt(start));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[995]++;
                    ++start;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[996]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[997]++;
                    break;
                case 'L':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[677]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[998]++;
                    int end = type.indexOf(';', start) + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[999]++;
                    String name = type.substring(start, end);
                    paramType.append(name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1000]++;
                    start = end;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1001]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1002]++;
                    break;
                case '[':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[678]++;
                    paramType.append('[');
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1003]++;
                    ++start;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1004]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1005]++;
                    continue; default : CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[679]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1006]++;
            String internalType =
                    descriptorToInternalName(paramType.toString());
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1007]++;
            int typeInfo = TypeInfo.fromType(internalType, itsConstantPool);
            initialLocals[localsTop++] = typeInfo;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1008]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1009]++;
int CodeCoverConditionCoverageHelper_C202;
            if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((TypeInfo.isTwoWords(typeInfo)) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[680]++;
                localsTop++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1010]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[681]++;}
            paramType.setLength(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1011]++;
        }
        return initialLocals;
    }

    /**
     * Write the class file to the OutputStream.
     *
     * @param oStream the stream to write to
     * @throws IOException if writing to the stream produces an exception
     */
    public void write(OutputStream oStream)
        throws IOException
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1012]++;
        byte[] array = toByteArray();
        oStream.write(array);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1013]++;
    }

    private int getWriteSize()
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1014]++;
        int size = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1015]++;
int CodeCoverConditionCoverageHelper_C203;

        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((itsSourceFileNameIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[682]++;
            itsConstantPool.addUtf8("SourceFile");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1016]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[683]++;}

        size += 8;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1017]++; //writeLong(FileHeaderConstant);
        size += itsConstantPool.getWriteSize();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1018]++;
        size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1019]++; //writeShort(itsFlags);
        size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1020]++; //writeShort(itsThisClassIndex);
        size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1021]++; //writeShort(itsSuperClassIndex);
        size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1022]++; //writeShort(itsInterfaces.size());
        size += 2 * itsInterfaces.size();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1023]++;

        size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1024]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1025]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[88]++;


int CodeCoverConditionCoverageHelper_C204; //writeShort(itsFields.size());
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((i < itsFields.size()) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[88]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[89]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[90]++;
}
            size += ((ClassFileField)(itsFields.get(i))).getWriteSize();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1026]++;
        }

        size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1027]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1028]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[91]++;


int CodeCoverConditionCoverageHelper_C205; //writeShort(itsMethods.size());
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((i < itsMethods.size()) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[91]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[92]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[93]++;
}
            size += ((ClassFileMethod)(itsMethods.get(i))).getWriteSize();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1029]++;
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1030]++;
int CodeCoverConditionCoverageHelper_C206;

        if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((itsSourceFileNameIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[684]++;
            size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1031]++; //writeShort(1);  attributes count
            size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1032]++; //writeShort(sourceFileAttributeNameIndex);
            size += 4;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1033]++; //writeInt(2);
            size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1034]++;
 //writeShort(itsSourceFileNameIndex);
        }else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[685]++;
            size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1035]++; //out.writeShort(0);  no attributes
        }

        return size;
    }

    /**
     * Get the class file as array of bytesto the OutputStream.
     */
    public byte[] toByteArray()
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1036]++;
        int dataSize = getWriteSize();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1037]++;
        byte[] data = new byte[dataSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1038]++;
        int offset = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1039]++;

        short sourceFileAttributeNameIndex = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1040]++;
int CodeCoverConditionCoverageHelper_C207;
        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((itsSourceFileNameIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[686]++;
            sourceFileAttributeNameIndex = itsConstantPool.addUtf8(
                                               "SourceFile");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1041]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[687]++;}

        offset = putInt32(FileHeaderConstant, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1042]++;
        offset = putInt16(MinorVersion, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1043]++;
        offset = putInt16(MajorVersion, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1044]++;
        offset = itsConstantPool.write(data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1045]++;
        offset = putInt16(itsFlags, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1046]++;
        offset = putInt16(itsThisClassIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1047]++;
        offset = putInt16(itsSuperClassIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1048]++;
        offset = putInt16(itsInterfaces.size(), data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1049]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1050]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[94]++;


int CodeCoverConditionCoverageHelper_C208;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((i < itsInterfaces.size()) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[94]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[95]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[96]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1051]++;
            int interfaceIndex = ((Short)(itsInterfaces.get(i))).shortValue();
            offset = putInt16(interfaceIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1052]++;
        }
        offset = putInt16(itsFields.size(), data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1053]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1054]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[97]++;


int CodeCoverConditionCoverageHelper_C209;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((i < itsFields.size()) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[97]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[98]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[99]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1055]++;
            ClassFileField field = (ClassFileField)itsFields.get(i);
            offset = field.write(data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1056]++;
        }
        offset = putInt16(itsMethods.size(), data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1057]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1058]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[100]++;


int CodeCoverConditionCoverageHelper_C210;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((i < itsMethods.size()) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[100]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[101]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[102]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1059]++;
            ClassFileMethod method = (ClassFileMethod)itsMethods.get(i);
            offset = method.write(data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1060]++;
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1061]++;
int CodeCoverConditionCoverageHelper_C211;
        if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((itsSourceFileNameIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[688]++;
            offset = putInt16(1, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1062]++; // attributes count
            offset = putInt16(sourceFileAttributeNameIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1063]++;
            offset = putInt32(2, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1064]++;
            offset = putInt16(itsSourceFileNameIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1065]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[689]++;
            offset = putInt16(0, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1066]++; // no attributes
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1067]++;
int CodeCoverConditionCoverageHelper_C212;

        if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((offset != dataSize) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[690]++;
            // Check getWriteSize is consistent with write!
            throw new RuntimeException();

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[691]++;}

        return data;
    }

    static int putInt64(long value, byte[] array, int offset)
    {
        offset = putInt32((int)(value >>> 32), array, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1068]++;
        return putInt32((int)value, array, offset);
    }

    private static void badStack(int value)
    {
        String s;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1069]++;
int CodeCoverConditionCoverageHelper_C213;
        if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[692]++; s = "Stack underflow: "+value;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1070]++;
 }
        else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[693]++; s = "Too big stack: "+value;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1071]++; }
        throw new IllegalStateException(s);
    }

    /*
        Really weird. Returns an int with # parameters in hi 16 bits, and
        stack difference removal of parameters from stack and pushing the
        result (it does not take into account removal of this in case of
        non-static methods).
        If Java really supported references we wouldn't have to be this
        perverted.
    */
    private static int sizeOfParameters(String pString)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1072]++;
        int length = pString.length();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1073]++;
        int rightParenthesis = pString.lastIndexOf(')');
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1074]++;
int CodeCoverConditionCoverageHelper_C214;
        if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (128)) == 0 || true) &&
 ((3 <= length) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C214 |= (32)) == 0 || true) &&
 ((pString.charAt(0) == '(') && 
  ((CodeCoverConditionCoverageHelper_C214 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C214 |= (8)) == 0 || true) &&
 ((1 <= rightParenthesis) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((rightParenthesis + 1 < length) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 4) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 4) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[694]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1075]++;
            boolean ok = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1076]++;
            int index = 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1077]++;
            int stackDiff = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1078]++;
            int count = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1079]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[103]++;


int CodeCoverConditionCoverageHelper_C215;
        stringLoop:
            while ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((index != rightParenthesis) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[103]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[104]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[105]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1080]++;
                switch (pString.charAt(index)) {
                    default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[696]++;
                        ok = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1081]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1082]++;
                        break stringLoop;
                    case 'J' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[697]++;
                    case 'D' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[698]++;
                        --stackDiff;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1083]++;
                        // fall thru
                    case 'B' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[699]++;
                    case 'S' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[700]++;
                    case 'C' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[701]++;
                    case 'I' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[702]++;
                    case 'Z' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[703]++;
                    case 'F' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[704]++;
                        --stackDiff;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1084]++;
                        ++count;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1085]++;
                        ++index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1086]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1087]++;
                        continue;
                    case '[' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[705]++;
                        ++index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1088]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1089]++;
                        int c = pString.charAt(index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1090]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[106]++;


int CodeCoverConditionCoverageHelper_C216;
                        while ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((c == '[') && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[106]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[107]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[108]++;
}
                            ++index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1091]++;
                            c = pString.charAt(index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1092]++;
                        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1093]++;
                        switch (c) {
                            default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[706]++;
                                ok = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1094]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1095]++;
                                break stringLoop;
                            case 'J' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[707]++;
                            case 'D' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[708]++;
                            case 'B' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[709]++;
                            case 'S' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[710]++;
                            case 'C' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[711]++;
                            case 'I' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[712]++;
                            case 'Z' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[713]++;
                            case 'F' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[714]++;
                                --stackDiff;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1096]++;
                                ++count;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1097]++;
                                ++index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1098]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1099]++;
                                continue;
                            case 'L':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[715]++;
                                // fall thru
                        }
                          // fall thru
                    case 'L' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[716]++; {
                        --stackDiff;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1100]++;
                        ++count;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1101]++;
                        ++index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1102]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1103]++;
                        int semicolon = pString.indexOf(';',  index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1104]++;
int CodeCoverConditionCoverageHelper_C217;
                        if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C217 |= (8)) == 0 || true) &&
 ((index + 1 <= semicolon) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((semicolon < rightParenthesis) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 2) && false))
                        {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[717]++;
                            ok = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1105]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1106]++;
                            break stringLoop;

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[718]++;}
                        index = semicolon + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1107]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1108]++;
                        continue;
                    }
                }
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1109]++;
int CodeCoverConditionCoverageHelper_C218;
            if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((ok) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[719]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1110]++;
                switch (pString.charAt(rightParenthesis + 1)) {
                    default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[721]++;
                        ok = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1111]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1112]++;
                        break;
                    case 'J' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[722]++;
                    case 'D' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[723]++;
                        ++stackDiff;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1113]++;
                        // fall thru
                    case 'B' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[724]++;
                    case 'S' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[725]++;
                    case 'C' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[726]++;
                    case 'I' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[727]++;
                    case 'Z' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[728]++;
                    case 'F' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[729]++;
                    case 'L' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[730]++;
                    case '[' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[731]++;
                        ++stackDiff;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1114]++;
                        // fall thru
                    case 'V' :
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[732]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1115]++;
                        break;
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1116]++;
int CodeCoverConditionCoverageHelper_C219;
                if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((ok) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[733]++;
                    return ((count << 16) | (0xFFFF & stackDiff));

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[734]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[720]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[695]++;}
        throw new IllegalArgumentException(
            "Bad parameter signature: "+pString);
    }

    static int putInt16(int value, byte[] array, int offset)
    {
        array[offset + 0] = (byte)(value >>> 8);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1117]++;
        array[offset + 1] = (byte)value;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1118]++;
        return offset + 2;
    }

    static int putInt32(int value, byte[] array, int offset)
    {
        array[offset + 0] = (byte)(value >>> 24);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1119]++;
        array[offset + 1] = (byte)(value >>> 16);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1120]++;
        array[offset + 2] = (byte)(value >>> 8);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1121]++;
        array[offset + 3] = (byte)value;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1122]++;
        return offset + 4;
    }

    /**
     * Size of a bytecode instruction, counting the opcode and its operands.
     *
     * This is different from opcodeCount, since opcodeCount counts logical
     * operands.
     */
    static int opcodeLength(int opcode, boolean wide) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1123]++;
        switch (opcode) {
            case ByteCode.AALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[735]++;
            case ByteCode.AASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[736]++;
            case ByteCode.ACONST_NULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[737]++;
            case ByteCode.ALOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[738]++;
            case ByteCode.ALOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[739]++;
            case ByteCode.ALOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[740]++;
            case ByteCode.ALOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[741]++;
            case ByteCode.ARETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[742]++;
            case ByteCode.ARRAYLENGTH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[743]++;
            case ByteCode.ASTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[744]++;
            case ByteCode.ASTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[745]++;
            case ByteCode.ASTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[746]++;
            case ByteCode.ASTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[747]++;
            case ByteCode.ATHROW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[748]++;
            case ByteCode.BALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[749]++;
            case ByteCode.BASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[750]++;
            case ByteCode.BREAKPOINT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[751]++;
            case ByteCode.CALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[752]++;
            case ByteCode.CASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[753]++;
            case ByteCode.D2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[754]++;
            case ByteCode.D2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[755]++;
            case ByteCode.D2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[756]++;
            case ByteCode.DADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[757]++;
            case ByteCode.DALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[758]++;
            case ByteCode.DASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[759]++;
            case ByteCode.DCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[760]++;
            case ByteCode.DCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[761]++;
            case ByteCode.DCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[762]++;
            case ByteCode.DCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[763]++;
            case ByteCode.DDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[764]++;
            case ByteCode.DLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[765]++;
            case ByteCode.DLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[766]++;
            case ByteCode.DLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[767]++;
            case ByteCode.DLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[768]++;
            case ByteCode.DMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[769]++;
            case ByteCode.DNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[770]++;
            case ByteCode.DREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[771]++;
            case ByteCode.DRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[772]++;
            case ByteCode.DSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[773]++;
            case ByteCode.DSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[774]++;
            case ByteCode.DSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[775]++;
            case ByteCode.DSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[776]++;
            case ByteCode.DSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[777]++;
            case ByteCode.DUP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[778]++;
            case ByteCode.DUP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[779]++;
            case ByteCode.DUP2_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[780]++;
            case ByteCode.DUP2_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[781]++;
            case ByteCode.DUP_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[782]++;
            case ByteCode.DUP_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[783]++;
            case ByteCode.F2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[784]++;
            case ByteCode.F2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[785]++;
            case ByteCode.F2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[786]++;
            case ByteCode.FADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[787]++;
            case ByteCode.FALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[788]++;
            case ByteCode.FASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[789]++;
            case ByteCode.FCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[790]++;
            case ByteCode.FCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[791]++;
            case ByteCode.FCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[792]++;
            case ByteCode.FCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[793]++;
            case ByteCode.FCONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[794]++;
            case ByteCode.FDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[795]++;
            case ByteCode.FLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[796]++;
            case ByteCode.FLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[797]++;
            case ByteCode.FLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[798]++;
            case ByteCode.FLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[799]++;
            case ByteCode.FMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[800]++;
            case ByteCode.FNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[801]++;
            case ByteCode.FREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[802]++;
            case ByteCode.FRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[803]++;
            case ByteCode.FSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[804]++;
            case ByteCode.FSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[805]++;
            case ByteCode.FSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[806]++;
            case ByteCode.FSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[807]++;
            case ByteCode.FSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[808]++;
            case ByteCode.I2B:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[809]++;
            case ByteCode.I2C:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[810]++;
            case ByteCode.I2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[811]++;
            case ByteCode.I2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[812]++;
            case ByteCode.I2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[813]++;
            case ByteCode.I2S:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[814]++;
            case ByteCode.IADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[815]++;
            case ByteCode.IALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[816]++;
            case ByteCode.IAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[817]++;
            case ByteCode.IASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[818]++;
            case ByteCode.ICONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[819]++;
            case ByteCode.ICONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[820]++;
            case ByteCode.ICONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[821]++;
            case ByteCode.ICONST_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[822]++;
            case ByteCode.ICONST_4:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[823]++;
            case ByteCode.ICONST_5:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[824]++;
            case ByteCode.ICONST_M1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[825]++;
            case ByteCode.IDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[826]++;
            case ByteCode.ILOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[827]++;
            case ByteCode.ILOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[828]++;
            case ByteCode.ILOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[829]++;
            case ByteCode.ILOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[830]++;
            case ByteCode.IMPDEP1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[831]++;
            case ByteCode.IMPDEP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[832]++;
            case ByteCode.IMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[833]++;
            case ByteCode.INEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[834]++;
            case ByteCode.IOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[835]++;
            case ByteCode.IREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[836]++;
            case ByteCode.IRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[837]++;
            case ByteCode.ISHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[838]++;
            case ByteCode.ISHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[839]++;
            case ByteCode.ISTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[840]++;
            case ByteCode.ISTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[841]++;
            case ByteCode.ISTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[842]++;
            case ByteCode.ISTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[843]++;
            case ByteCode.ISUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[844]++;
            case ByteCode.IUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[845]++;
            case ByteCode.IXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[846]++;
            case ByteCode.L2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[847]++;
            case ByteCode.L2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[848]++;
            case ByteCode.L2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[849]++;
            case ByteCode.LADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[850]++;
            case ByteCode.LALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[851]++;
            case ByteCode.LAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[852]++;
            case ByteCode.LASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[853]++;
            case ByteCode.LCMP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[854]++;
            case ByteCode.LCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[855]++;
            case ByteCode.LCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[856]++;
            case ByteCode.LDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[857]++;
            case ByteCode.LLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[858]++;
            case ByteCode.LLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[859]++;
            case ByteCode.LLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[860]++;
            case ByteCode.LLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[861]++;
            case ByteCode.LMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[862]++;
            case ByteCode.LNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[863]++;
            case ByteCode.LOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[864]++;
            case ByteCode.LREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[865]++;
            case ByteCode.LRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[866]++;
            case ByteCode.LSHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[867]++;
            case ByteCode.LSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[868]++;
            case ByteCode.LSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[869]++;
            case ByteCode.LSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[870]++;
            case ByteCode.LSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[871]++;
            case ByteCode.LSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[872]++;
            case ByteCode.LSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[873]++;
            case ByteCode.LUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[874]++;
            case ByteCode.LXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[875]++;
            case ByteCode.MONITORENTER:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[876]++;
            case ByteCode.MONITOREXIT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[877]++;
            case ByteCode.NOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[878]++;
            case ByteCode.POP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[879]++;
            case ByteCode.POP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[880]++;
            case ByteCode.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[881]++;
            case ByteCode.SALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[882]++;
            case ByteCode.SASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[883]++;
            case ByteCode.SWAP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[884]++;
            case ByteCode.WIDE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[885]++;
                return 1;
            case ByteCode.BIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[886]++;
            case ByteCode.LDC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[887]++;
            case ByteCode.NEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[888]++;
                return 2;
            case ByteCode.ALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[889]++;
            case ByteCode.ASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[890]++;
            case ByteCode.DLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[891]++;
            case ByteCode.DSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[892]++;
            case ByteCode.FLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[893]++;
            case ByteCode.FSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[894]++;
            case ByteCode.ILOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[895]++;
            case ByteCode.ISTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[896]++;
            case ByteCode.LLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[897]++;
            case ByteCode.LSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[898]++;
            case ByteCode.RET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[899]++;
                return wide ? 3 : 2;

            case ByteCode.ANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[900]++;
            case ByteCode.CHECKCAST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[901]++;
            case ByteCode.GETFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[902]++;
            case ByteCode.GETSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[903]++;
            case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[904]++;
            case ByteCode.IFEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[905]++;
            case ByteCode.IFGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[906]++;
            case ByteCode.IFGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[907]++;
            case ByteCode.IFLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[908]++;
            case ByteCode.IFLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[909]++;
            case ByteCode.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[910]++;
            case ByteCode.IFNONNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[911]++;
            case ByteCode.IFNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[912]++;
            case ByteCode.IF_ACMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[913]++;
            case ByteCode.IF_ACMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[914]++;
            case ByteCode.IF_ICMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[915]++;
            case ByteCode.IF_ICMPGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[916]++;
            case ByteCode.IF_ICMPGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[917]++;
            case ByteCode.IF_ICMPLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[918]++;
            case ByteCode.IF_ICMPLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[919]++;
            case ByteCode.IF_ICMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[920]++;
            case ByteCode.INSTANCEOF:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[921]++;
            case ByteCode.INVOKESPECIAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[922]++;
            case ByteCode.INVOKESTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[923]++;
            case ByteCode.INVOKEVIRTUAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[924]++;
            case ByteCode.JSR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[925]++;
            case ByteCode.LDC_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[926]++;
            case ByteCode.LDC2_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[927]++;
            case ByteCode.NEW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[928]++;
            case ByteCode.PUTFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[929]++;
            case ByteCode.PUTSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[930]++;
            case ByteCode.SIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[931]++;
                return 3;

            case ByteCode.IINC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[932]++;
                return wide ? 5 : 3;

            case ByteCode.MULTIANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[933]++;
                return 4;

            case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[934]++;
            case ByteCode.INVOKEINTERFACE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[935]++;
            case ByteCode.JSR_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[936]++;
                return 5; default : CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[937]++;

            /*
            case ByteCode.LOOKUPSWITCH:
            case ByteCode.TABLESWITCH:
                return -1;
            */
        }
        throw new IllegalArgumentException("Bad opcode: " + opcode);
    }

    /**
     * Number of operands accompanying the opcode.
     */
    static int opcodeCount(int opcode)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1124]++;
        switch (opcode) {
            case ByteCode.AALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[938]++;
            case ByteCode.AASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[939]++;
            case ByteCode.ACONST_NULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[940]++;
            case ByteCode.ALOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[941]++;
            case ByteCode.ALOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[942]++;
            case ByteCode.ALOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[943]++;
            case ByteCode.ALOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[944]++;
            case ByteCode.ARETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[945]++;
            case ByteCode.ARRAYLENGTH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[946]++;
            case ByteCode.ASTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[947]++;
            case ByteCode.ASTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[948]++;
            case ByteCode.ASTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[949]++;
            case ByteCode.ASTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[950]++;
            case ByteCode.ATHROW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[951]++;
            case ByteCode.BALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[952]++;
            case ByteCode.BASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[953]++;
            case ByteCode.BREAKPOINT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[954]++;
            case ByteCode.CALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[955]++;
            case ByteCode.CASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[956]++;
            case ByteCode.D2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[957]++;
            case ByteCode.D2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[958]++;
            case ByteCode.D2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[959]++;
            case ByteCode.DADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[960]++;
            case ByteCode.DALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[961]++;
            case ByteCode.DASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[962]++;
            case ByteCode.DCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[963]++;
            case ByteCode.DCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[964]++;
            case ByteCode.DCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[965]++;
            case ByteCode.DCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[966]++;
            case ByteCode.DDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[967]++;
            case ByteCode.DLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[968]++;
            case ByteCode.DLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[969]++;
            case ByteCode.DLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[970]++;
            case ByteCode.DLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[971]++;
            case ByteCode.DMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[972]++;
            case ByteCode.DNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[973]++;
            case ByteCode.DREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[974]++;
            case ByteCode.DRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[975]++;
            case ByteCode.DSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[976]++;
            case ByteCode.DSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[977]++;
            case ByteCode.DSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[978]++;
            case ByteCode.DSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[979]++;
            case ByteCode.DSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[980]++;
            case ByteCode.DUP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[981]++;
            case ByteCode.DUP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[982]++;
            case ByteCode.DUP2_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[983]++;
            case ByteCode.DUP2_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[984]++;
            case ByteCode.DUP_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[985]++;
            case ByteCode.DUP_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[986]++;
            case ByteCode.F2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[987]++;
            case ByteCode.F2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[988]++;
            case ByteCode.F2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[989]++;
            case ByteCode.FADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[990]++;
            case ByteCode.FALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[991]++;
            case ByteCode.FASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[992]++;
            case ByteCode.FCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[993]++;
            case ByteCode.FCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[994]++;
            case ByteCode.FCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[995]++;
            case ByteCode.FCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[996]++;
            case ByteCode.FCONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[997]++;
            case ByteCode.FDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[998]++;
            case ByteCode.FLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[999]++;
            case ByteCode.FLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1000]++;
            case ByteCode.FLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1001]++;
            case ByteCode.FLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1002]++;
            case ByteCode.FMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1003]++;
            case ByteCode.FNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1004]++;
            case ByteCode.FREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1005]++;
            case ByteCode.FRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1006]++;
            case ByteCode.FSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1007]++;
            case ByteCode.FSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1008]++;
            case ByteCode.FSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1009]++;
            case ByteCode.FSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1010]++;
            case ByteCode.FSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1011]++;
            case ByteCode.I2B:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1012]++;
            case ByteCode.I2C:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1013]++;
            case ByteCode.I2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1014]++;
            case ByteCode.I2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1015]++;
            case ByteCode.I2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1016]++;
            case ByteCode.I2S:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1017]++;
            case ByteCode.IADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1018]++;
            case ByteCode.IALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1019]++;
            case ByteCode.IAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1020]++;
            case ByteCode.IASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1021]++;
            case ByteCode.ICONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1022]++;
            case ByteCode.ICONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1023]++;
            case ByteCode.ICONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1024]++;
            case ByteCode.ICONST_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1025]++;
            case ByteCode.ICONST_4:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1026]++;
            case ByteCode.ICONST_5:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1027]++;
            case ByteCode.ICONST_M1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1028]++;
            case ByteCode.IDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1029]++;
            case ByteCode.ILOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1030]++;
            case ByteCode.ILOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1031]++;
            case ByteCode.ILOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1032]++;
            case ByteCode.ILOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1033]++;
            case ByteCode.IMPDEP1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1034]++;
            case ByteCode.IMPDEP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1035]++;
            case ByteCode.IMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1036]++;
            case ByteCode.INEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1037]++;
            case ByteCode.IOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1038]++;
            case ByteCode.IREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1039]++;
            case ByteCode.IRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1040]++;
            case ByteCode.ISHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1041]++;
            case ByteCode.ISHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1042]++;
            case ByteCode.ISTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1043]++;
            case ByteCode.ISTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1044]++;
            case ByteCode.ISTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1045]++;
            case ByteCode.ISTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1046]++;
            case ByteCode.ISUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1047]++;
            case ByteCode.IUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1048]++;
            case ByteCode.IXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1049]++;
            case ByteCode.L2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1050]++;
            case ByteCode.L2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1051]++;
            case ByteCode.L2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1052]++;
            case ByteCode.LADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1053]++;
            case ByteCode.LALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1054]++;
            case ByteCode.LAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1055]++;
            case ByteCode.LASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1056]++;
            case ByteCode.LCMP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1057]++;
            case ByteCode.LCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1058]++;
            case ByteCode.LCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1059]++;
            case ByteCode.LDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1060]++;
            case ByteCode.LLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1061]++;
            case ByteCode.LLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1062]++;
            case ByteCode.LLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1063]++;
            case ByteCode.LLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1064]++;
            case ByteCode.LMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1065]++;
            case ByteCode.LNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1066]++;
            case ByteCode.LOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1067]++;
            case ByteCode.LREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1068]++;
            case ByteCode.LRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1069]++;
            case ByteCode.LSHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1070]++;
            case ByteCode.LSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1071]++;
            case ByteCode.LSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1072]++;
            case ByteCode.LSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1073]++;
            case ByteCode.LSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1074]++;
            case ByteCode.LSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1075]++;
            case ByteCode.LSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1076]++;
            case ByteCode.LUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1077]++;
            case ByteCode.LXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1078]++;
            case ByteCode.MONITORENTER:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1079]++;
            case ByteCode.MONITOREXIT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1080]++;
            case ByteCode.NOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1081]++;
            case ByteCode.POP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1082]++;
            case ByteCode.POP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1083]++;
            case ByteCode.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1084]++;
            case ByteCode.SALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1085]++;
            case ByteCode.SASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1086]++;
            case ByteCode.SWAP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1087]++;
            case ByteCode.WIDE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1088]++;
                return 0;
            case ByteCode.ALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1089]++;
            case ByteCode.ANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1090]++;
            case ByteCode.ASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1091]++;
            case ByteCode.BIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1092]++;
            case ByteCode.CHECKCAST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1093]++;
            case ByteCode.DLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1094]++;
            case ByteCode.DSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1095]++;
            case ByteCode.FLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1096]++;
            case ByteCode.FSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1097]++;
            case ByteCode.GETFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1098]++;
            case ByteCode.GETSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1099]++;
            case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1100]++;
            case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1101]++;
            case ByteCode.IFEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1102]++;
            case ByteCode.IFGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1103]++;
            case ByteCode.IFGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1104]++;
            case ByteCode.IFLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1105]++;
            case ByteCode.IFLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1106]++;
            case ByteCode.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1107]++;
            case ByteCode.IFNONNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1108]++;
            case ByteCode.IFNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1109]++;
            case ByteCode.IF_ACMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1110]++;
            case ByteCode.IF_ACMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1111]++;
            case ByteCode.IF_ICMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1112]++;
            case ByteCode.IF_ICMPGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1113]++;
            case ByteCode.IF_ICMPGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1114]++;
            case ByteCode.IF_ICMPLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1115]++;
            case ByteCode.IF_ICMPLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1116]++;
            case ByteCode.IF_ICMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1117]++;
            case ByteCode.ILOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1118]++;
            case ByteCode.INSTANCEOF:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1119]++;
            case ByteCode.INVOKEINTERFACE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1120]++;
            case ByteCode.INVOKESPECIAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1121]++;
            case ByteCode.INVOKESTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1122]++;
            case ByteCode.INVOKEVIRTUAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1123]++;
            case ByteCode.ISTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1124]++;
            case ByteCode.JSR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1125]++;
            case ByteCode.JSR_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1126]++;
            case ByteCode.LDC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1127]++;
            case ByteCode.LDC2_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1128]++;
            case ByteCode.LDC_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1129]++;
            case ByteCode.LLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1130]++;
            case ByteCode.LSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1131]++;
            case ByteCode.NEW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1132]++;
            case ByteCode.NEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1133]++;
            case ByteCode.PUTFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1134]++;
            case ByteCode.PUTSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1135]++;
            case ByteCode.RET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1136]++;
            case ByteCode.SIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1137]++;
                return 1;

            case ByteCode.IINC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1138]++;
            case ByteCode.MULTIANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1139]++;
                return 2;

            case ByteCode.LOOKUPSWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1140]++;
            case ByteCode.TABLESWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1141]++;
                return -1; default : CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1142]++;
        }
        throw new IllegalArgumentException("Bad opcode: "+opcode);
    }

    /**
     *  The effect on the operand stack of a given opcode.
     */
    static int stackChange(int opcode)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1125]++;
        // For INVOKE... accounts only for popping this (unless static),
        // ignoring parameters and return type
        switch (opcode) {
            case ByteCode.DASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1143]++;
            case ByteCode.LASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1144]++;
                return -4;

            case ByteCode.AASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1145]++;
            case ByteCode.BASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1146]++;
            case ByteCode.CASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1147]++;
            case ByteCode.DCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1148]++;
            case ByteCode.DCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1149]++;
            case ByteCode.FASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1150]++;
            case ByteCode.IASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1151]++;
            case ByteCode.LCMP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1152]++;
            case ByteCode.SASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1153]++;
                return -3;

            case ByteCode.DADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1154]++;
            case ByteCode.DDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1155]++;
            case ByteCode.DMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1156]++;
            case ByteCode.DREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1157]++;
            case ByteCode.DRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1158]++;
            case ByteCode.DSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1159]++;
            case ByteCode.DSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1160]++;
            case ByteCode.DSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1161]++;
            case ByteCode.DSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1162]++;
            case ByteCode.DSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1163]++;
            case ByteCode.DSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1164]++;
            case ByteCode.IF_ACMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1165]++;
            case ByteCode.IF_ACMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1166]++;
            case ByteCode.IF_ICMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1167]++;
            case ByteCode.IF_ICMPGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1168]++;
            case ByteCode.IF_ICMPGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1169]++;
            case ByteCode.IF_ICMPLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1170]++;
            case ByteCode.IF_ICMPLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1171]++;
            case ByteCode.IF_ICMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1172]++;
            case ByteCode.LADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1173]++;
            case ByteCode.LAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1174]++;
            case ByteCode.LDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1175]++;
            case ByteCode.LMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1176]++;
            case ByteCode.LOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1177]++;
            case ByteCode.LREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1178]++;
            case ByteCode.LRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1179]++;
            case ByteCode.LSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1180]++;
            case ByteCode.LSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1181]++;
            case ByteCode.LSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1182]++;
            case ByteCode.LSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1183]++;
            case ByteCode.LSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1184]++;
            case ByteCode.LSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1185]++;
            case ByteCode.LXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1186]++;
            case ByteCode.POP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1187]++;
                return -2;

            case ByteCode.AALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1188]++;
            case ByteCode.ARETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1189]++;
            case ByteCode.ASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1190]++;
            case ByteCode.ASTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1191]++;
            case ByteCode.ASTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1192]++;
            case ByteCode.ASTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1193]++;
            case ByteCode.ASTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1194]++;
            case ByteCode.ATHROW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1195]++;
            case ByteCode.BALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1196]++;
            case ByteCode.CALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1197]++;
            case ByteCode.D2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1198]++;
            case ByteCode.D2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1199]++;
            case ByteCode.FADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1200]++;
            case ByteCode.FALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1201]++;
            case ByteCode.FCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1202]++;
            case ByteCode.FCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1203]++;
            case ByteCode.FDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1204]++;
            case ByteCode.FMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1205]++;
            case ByteCode.FREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1206]++;
            case ByteCode.FRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1207]++;
            case ByteCode.FSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1208]++;
            case ByteCode.FSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1209]++;
            case ByteCode.FSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1210]++;
            case ByteCode.FSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1211]++;
            case ByteCode.FSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1212]++;
            case ByteCode.FSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1213]++;
            case ByteCode.GETFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1214]++;
            case ByteCode.IADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1215]++;
            case ByteCode.IALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1216]++;
            case ByteCode.IAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1217]++;
            case ByteCode.IDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1218]++;
            case ByteCode.IFEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1219]++;
            case ByteCode.IFGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1220]++;
            case ByteCode.IFGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1221]++;
            case ByteCode.IFLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1222]++;
            case ByteCode.IFLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1223]++;
            case ByteCode.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1224]++;
            case ByteCode.IFNONNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1225]++;
            case ByteCode.IFNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1226]++;
            case ByteCode.IMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1227]++;
            case ByteCode.INVOKEINTERFACE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1228]++;       //
            case ByteCode.INVOKESPECIAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1229]++;         // but needs to account for
            case ByteCode.INVOKEVIRTUAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1230]++;         // pops 'this' (unless static)
            case ByteCode.IOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1231]++;
            case ByteCode.IREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1232]++;
            case ByteCode.IRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1233]++;
            case ByteCode.ISHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1234]++;
            case ByteCode.ISHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1235]++;
            case ByteCode.ISTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1236]++;
            case ByteCode.ISTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1237]++;
            case ByteCode.ISTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1238]++;
            case ByteCode.ISTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1239]++;
            case ByteCode.ISTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1240]++;
            case ByteCode.ISUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1241]++;
            case ByteCode.IUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1242]++;
            case ByteCode.IXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1243]++;
            case ByteCode.L2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1244]++;
            case ByteCode.L2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1245]++;
            case ByteCode.LOOKUPSWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1246]++;
            case ByteCode.LSHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1247]++;
            case ByteCode.LSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1248]++;
            case ByteCode.LUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1249]++;
            case ByteCode.MONITORENTER:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1250]++;
            case ByteCode.MONITOREXIT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1251]++;
            case ByteCode.POP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1252]++;
            case ByteCode.PUTFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1253]++;
            case ByteCode.SALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1254]++;
            case ByteCode.TABLESWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1255]++;
                return -1;

            case ByteCode.ANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1256]++;
            case ByteCode.ARRAYLENGTH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1257]++;
            case ByteCode.BREAKPOINT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1258]++;
            case ByteCode.CHECKCAST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1259]++;
            case ByteCode.D2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1260]++;
            case ByteCode.DALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1261]++;
            case ByteCode.DNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1262]++;
            case ByteCode.F2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1263]++;
            case ByteCode.FNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1264]++;
            case ByteCode.GETSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1265]++;
            case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1266]++;
            case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1267]++;
            case ByteCode.I2B:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1268]++;
            case ByteCode.I2C:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1269]++;
            case ByteCode.I2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1270]++;
            case ByteCode.I2S:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1271]++;
            case ByteCode.IINC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1272]++;
            case ByteCode.IMPDEP1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1273]++;
            case ByteCode.IMPDEP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1274]++;
            case ByteCode.INEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1275]++;
            case ByteCode.INSTANCEOF:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1276]++;
            case ByteCode.INVOKESTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1277]++;
            case ByteCode.L2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1278]++;
            case ByteCode.LALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1279]++;
            case ByteCode.LNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1280]++;
            case ByteCode.NEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1281]++;
            case ByteCode.NOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1282]++;
            case ByteCode.PUTSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1283]++;
            case ByteCode.RET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1284]++;
            case ByteCode.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1285]++;
            case ByteCode.SWAP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1286]++;
            case ByteCode.WIDE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1287]++;
                return 0;

            case ByteCode.ACONST_NULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1288]++;
            case ByteCode.ALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1289]++;
            case ByteCode.ALOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1290]++;
            case ByteCode.ALOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1291]++;
            case ByteCode.ALOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1292]++;
            case ByteCode.ALOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1293]++;
            case ByteCode.BIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1294]++;
            case ByteCode.DUP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1295]++;
            case ByteCode.DUP_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1296]++;
            case ByteCode.DUP_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1297]++;
            case ByteCode.F2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1298]++;
            case ByteCode.F2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1299]++;
            case ByteCode.FCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1300]++;
            case ByteCode.FCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1301]++;
            case ByteCode.FCONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1302]++;
            case ByteCode.FLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1303]++;
            case ByteCode.FLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1304]++;
            case ByteCode.FLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1305]++;
            case ByteCode.FLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1306]++;
            case ByteCode.FLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1307]++;
            case ByteCode.I2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1308]++;
            case ByteCode.I2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1309]++;
            case ByteCode.ICONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1310]++;
            case ByteCode.ICONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1311]++;
            case ByteCode.ICONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1312]++;
            case ByteCode.ICONST_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1313]++;
            case ByteCode.ICONST_4:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1314]++;
            case ByteCode.ICONST_5:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1315]++;
            case ByteCode.ICONST_M1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1316]++;
            case ByteCode.ILOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1317]++;
            case ByteCode.ILOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1318]++;
            case ByteCode.ILOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1319]++;
            case ByteCode.ILOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1320]++;
            case ByteCode.ILOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1321]++;
            case ByteCode.JSR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1322]++;
            case ByteCode.JSR_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1323]++;
            case ByteCode.LDC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1324]++;
            case ByteCode.LDC_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1325]++;
            case ByteCode.MULTIANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1326]++;
            case ByteCode.NEW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1327]++;
            case ByteCode.SIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1328]++;
                return 1;

            case ByteCode.DCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1329]++;
            case ByteCode.DCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1330]++;
            case ByteCode.DLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1331]++;
            case ByteCode.DLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1332]++;
            case ByteCode.DLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1333]++;
            case ByteCode.DLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1334]++;
            case ByteCode.DLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1335]++;
            case ByteCode.DUP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1336]++;
            case ByteCode.DUP2_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1337]++;
            case ByteCode.DUP2_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1338]++;
            case ByteCode.LCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1339]++;
            case ByteCode.LCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1340]++;
            case ByteCode.LDC2_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1341]++;
            case ByteCode.LLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1342]++;
            case ByteCode.LLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1343]++;
            case ByteCode.LLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1344]++;
            case ByteCode.LLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1345]++;
            case ByteCode.LLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1346]++;
                return 2; default : CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1347]++;
        }
        throw new IllegalArgumentException("Bad opcode: "+opcode);
    }

        /*
         * Number of bytes of operands generated after the opcode.
         * Not in use currently.
         */
/*
    int extra(int opcode)
    {
        switch (opcode) {
            case ByteCode.AALOAD:
            case ByteCode.AASTORE:
            case ByteCode.ACONST_NULL:
            case ByteCode.ALOAD_0:
            case ByteCode.ALOAD_1:
            case ByteCode.ALOAD_2:
            case ByteCode.ALOAD_3:
            case ByteCode.ARETURN:
            case ByteCode.ARRAYLENGTH:
            case ByteCode.ASTORE_0:
            case ByteCode.ASTORE_1:
            case ByteCode.ASTORE_2:
            case ByteCode.ASTORE_3:
            case ByteCode.ATHROW:
            case ByteCode.BALOAD:
            case ByteCode.BASTORE:
            case ByteCode.BREAKPOINT:
            case ByteCode.CALOAD:
            case ByteCode.CASTORE:
            case ByteCode.D2F:
            case ByteCode.D2I:
            case ByteCode.D2L:
            case ByteCode.DADD:
            case ByteCode.DALOAD:
            case ByteCode.DASTORE:
            case ByteCode.DCMPG:
            case ByteCode.DCMPL:
            case ByteCode.DCONST_0:
            case ByteCode.DCONST_1:
            case ByteCode.DDIV:
            case ByteCode.DLOAD_0:
            case ByteCode.DLOAD_1:
            case ByteCode.DLOAD_2:
            case ByteCode.DLOAD_3:
            case ByteCode.DMUL:
            case ByteCode.DNEG:
            case ByteCode.DREM:
            case ByteCode.DRETURN:
            case ByteCode.DSTORE_0:
            case ByteCode.DSTORE_1:
            case ByteCode.DSTORE_2:
            case ByteCode.DSTORE_3:
            case ByteCode.DSUB:
            case ByteCode.DUP2:
            case ByteCode.DUP2_X1:
            case ByteCode.DUP2_X2:
            case ByteCode.DUP:
            case ByteCode.DUP_X1:
            case ByteCode.DUP_X2:
            case ByteCode.F2D:
            case ByteCode.F2I:
            case ByteCode.F2L:
            case ByteCode.FADD:
            case ByteCode.FALOAD:
            case ByteCode.FASTORE:
            case ByteCode.FCMPG:
            case ByteCode.FCMPL:
            case ByteCode.FCONST_0:
            case ByteCode.FCONST_1:
            case ByteCode.FCONST_2:
            case ByteCode.FDIV:
            case ByteCode.FLOAD_0:
            case ByteCode.FLOAD_1:
            case ByteCode.FLOAD_2:
            case ByteCode.FLOAD_3:
            case ByteCode.FMUL:
            case ByteCode.FNEG:
            case ByteCode.FREM:
            case ByteCode.FRETURN:
            case ByteCode.FSTORE_0:
            case ByteCode.FSTORE_1:
            case ByteCode.FSTORE_2:
            case ByteCode.FSTORE_3:
            case ByteCode.FSUB:
            case ByteCode.I2B:
            case ByteCode.I2C:
            case ByteCode.I2D:
            case ByteCode.I2F:
            case ByteCode.I2L:
            case ByteCode.I2S:
            case ByteCode.IADD:
            case ByteCode.IALOAD:
            case ByteCode.IAND:
            case ByteCode.IASTORE:
            case ByteCode.ICONST_0:
            case ByteCode.ICONST_1:
            case ByteCode.ICONST_2:
            case ByteCode.ICONST_3:
            case ByteCode.ICONST_4:
            case ByteCode.ICONST_5:
            case ByteCode.ICONST_M1:
            case ByteCode.IDIV:
            case ByteCode.ILOAD_0:
            case ByteCode.ILOAD_1:
            case ByteCode.ILOAD_2:
            case ByteCode.ILOAD_3:
            case ByteCode.IMPDEP1:
            case ByteCode.IMPDEP2:
            case ByteCode.IMUL:
            case ByteCode.INEG:
            case ByteCode.IOR:
            case ByteCode.IREM:
            case ByteCode.IRETURN:
            case ByteCode.ISHL:
            case ByteCode.ISHR:
            case ByteCode.ISTORE_0:
            case ByteCode.ISTORE_1:
            case ByteCode.ISTORE_2:
            case ByteCode.ISTORE_3:
            case ByteCode.ISUB:
            case ByteCode.IUSHR:
            case ByteCode.IXOR:
            case ByteCode.L2D:
            case ByteCode.L2F:
            case ByteCode.L2I:
            case ByteCode.LADD:
            case ByteCode.LALOAD:
            case ByteCode.LAND:
            case ByteCode.LASTORE:
            case ByteCode.LCMP:
            case ByteCode.LCONST_0:
            case ByteCode.LCONST_1:
            case ByteCode.LDIV:
            case ByteCode.LLOAD_0:
            case ByteCode.LLOAD_1:
            case ByteCode.LLOAD_2:
            case ByteCode.LLOAD_3:
            case ByteCode.LMUL:
            case ByteCode.LNEG:
            case ByteCode.LOR:
            case ByteCode.LREM:
            case ByteCode.LRETURN:
            case ByteCode.LSHL:
            case ByteCode.LSHR:
            case ByteCode.LSTORE_0:
            case ByteCode.LSTORE_1:
            case ByteCode.LSTORE_2:
            case ByteCode.LSTORE_3:
            case ByteCode.LSUB:
            case ByteCode.LUSHR:
            case ByteCode.LXOR:
            case ByteCode.MONITORENTER:
            case ByteCode.MONITOREXIT:
            case ByteCode.NOP:
            case ByteCode.POP2:
            case ByteCode.POP:
            case ByteCode.RETURN:
            case ByteCode.SALOAD:
            case ByteCode.SASTORE:
            case ByteCode.SWAP:
            case ByteCode.WIDE:
                return 0;

            case ByteCode.ALOAD:
            case ByteCode.ASTORE:
            case ByteCode.BIPUSH:
            case ByteCode.DLOAD:
            case ByteCode.DSTORE:
            case ByteCode.FLOAD:
            case ByteCode.FSTORE:
            case ByteCode.ILOAD:
            case ByteCode.ISTORE:
            case ByteCode.LDC:
            case ByteCode.LLOAD:
            case ByteCode.LSTORE:
            case ByteCode.NEWARRAY:
            case ByteCode.RET:
                return 1;

            case ByteCode.ANEWARRAY:
            case ByteCode.CHECKCAST:
            case ByteCode.GETFIELD:
            case ByteCode.GETSTATIC:
            case ByteCode.GOTO:
            case ByteCode.IFEQ:
            case ByteCode.IFGE:
            case ByteCode.IFGT:
            case ByteCode.IFLE:
            case ByteCode.IFLT:
            case ByteCode.IFNE:
            case ByteCode.IFNONNULL:
            case ByteCode.IFNULL:
            case ByteCode.IF_ACMPEQ:
            case ByteCode.IF_ACMPNE:
            case ByteCode.IF_ICMPEQ:
            case ByteCode.IF_ICMPGE:
            case ByteCode.IF_ICMPGT:
            case ByteCode.IF_ICMPLE:
            case ByteCode.IF_ICMPLT:
            case ByteCode.IF_ICMPNE:
            case ByteCode.IINC:
            case ByteCode.INSTANCEOF:
            case ByteCode.INVOKEINTERFACE:
            case ByteCode.INVOKESPECIAL:
            case ByteCode.INVOKESTATIC:
            case ByteCode.INVOKEVIRTUAL:
            case ByteCode.JSR:
            case ByteCode.LDC2_W:
            case ByteCode.LDC_W:
            case ByteCode.NEW:
            case ByteCode.PUTFIELD:
            case ByteCode.PUTSTATIC:
            case ByteCode.SIPUSH:
                return 2;

            case ByteCode.MULTIANEWARRAY:
                return 3;

            case ByteCode.GOTO_W:
            case ByteCode.JSR_W:
                return 4;

            case ByteCode.LOOKUPSWITCH:    // depends on alignment
            case ByteCode.TABLESWITCH: // depends on alignment
                return -1;
        }
        throw new IllegalArgumentException("Bad opcode: "+opcode);
    }
*/
    private static String bytecodeStr(int code)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1126]++;
int CodeCoverConditionCoverageHelper_C220;
        if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (8)) == 0 || true) &&
 ((DEBUGSTACK) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((DEBUGCODE) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1348]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1127]++;
            switch (code) {
                case ByteCode.NOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1350]++;              return "nop";
                case ByteCode.ACONST_NULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1351]++;      return "aconst_null";
                case ByteCode.ICONST_M1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1352]++;        return "iconst_m1";
                case ByteCode.ICONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1353]++;         return "iconst_0";
                case ByteCode.ICONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1354]++;         return "iconst_1";
                case ByteCode.ICONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1355]++;         return "iconst_2";
                case ByteCode.ICONST_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1356]++;         return "iconst_3";
                case ByteCode.ICONST_4:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1357]++;         return "iconst_4";
                case ByteCode.ICONST_5:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1358]++;         return "iconst_5";
                case ByteCode.LCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1359]++;         return "lconst_0";
                case ByteCode.LCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1360]++;         return "lconst_1";
                case ByteCode.FCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1361]++;         return "fconst_0";
                case ByteCode.FCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1362]++;         return "fconst_1";
                case ByteCode.FCONST_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1363]++;         return "fconst_2";
                case ByteCode.DCONST_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1364]++;         return "dconst_0";
                case ByteCode.DCONST_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1365]++;         return "dconst_1";
                case ByteCode.BIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1366]++;           return "bipush";
                case ByteCode.SIPUSH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1367]++;           return "sipush";
                case ByteCode.LDC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1368]++;              return "ldc";
                case ByteCode.LDC_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1369]++;            return "ldc_w";
                case ByteCode.LDC2_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1370]++;           return "ldc2_w";
                case ByteCode.ILOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1371]++;            return "iload";
                case ByteCode.LLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1372]++;            return "lload";
                case ByteCode.FLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1373]++;            return "fload";
                case ByteCode.DLOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1374]++;            return "dload";
                case ByteCode.ALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1375]++;            return "aload";
                case ByteCode.ILOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1376]++;          return "iload_0";
                case ByteCode.ILOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1377]++;          return "iload_1";
                case ByteCode.ILOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1378]++;          return "iload_2";
                case ByteCode.ILOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1379]++;          return "iload_3";
                case ByteCode.LLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1380]++;          return "lload_0";
                case ByteCode.LLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1381]++;          return "lload_1";
                case ByteCode.LLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1382]++;          return "lload_2";
                case ByteCode.LLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1383]++;          return "lload_3";
                case ByteCode.FLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1384]++;          return "fload_0";
                case ByteCode.FLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1385]++;          return "fload_1";
                case ByteCode.FLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1386]++;          return "fload_2";
                case ByteCode.FLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1387]++;          return "fload_3";
                case ByteCode.DLOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1388]++;          return "dload_0";
                case ByteCode.DLOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1389]++;          return "dload_1";
                case ByteCode.DLOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1390]++;          return "dload_2";
                case ByteCode.DLOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1391]++;          return "dload_3";
                case ByteCode.ALOAD_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1392]++;          return "aload_0";
                case ByteCode.ALOAD_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1393]++;          return "aload_1";
                case ByteCode.ALOAD_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1394]++;          return "aload_2";
                case ByteCode.ALOAD_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1395]++;          return "aload_3";
                case ByteCode.IALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1396]++;           return "iaload";
                case ByteCode.LALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1397]++;           return "laload";
                case ByteCode.FALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1398]++;           return "faload";
                case ByteCode.DALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1399]++;           return "daload";
                case ByteCode.AALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1400]++;           return "aaload";
                case ByteCode.BALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1401]++;           return "baload";
                case ByteCode.CALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1402]++;           return "caload";
                case ByteCode.SALOAD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1403]++;           return "saload";
                case ByteCode.ISTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1404]++;           return "istore";
                case ByteCode.LSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1405]++;           return "lstore";
                case ByteCode.FSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1406]++;           return "fstore";
                case ByteCode.DSTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1407]++;           return "dstore";
                case ByteCode.ASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1408]++;           return "astore";
                case ByteCode.ISTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1409]++;         return "istore_0";
                case ByteCode.ISTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1410]++;         return "istore_1";
                case ByteCode.ISTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1411]++;         return "istore_2";
                case ByteCode.ISTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1412]++;         return "istore_3";
                case ByteCode.LSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1413]++;         return "lstore_0";
                case ByteCode.LSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1414]++;         return "lstore_1";
                case ByteCode.LSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1415]++;         return "lstore_2";
                case ByteCode.LSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1416]++;         return "lstore_3";
                case ByteCode.FSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1417]++;         return "fstore_0";
                case ByteCode.FSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1418]++;         return "fstore_1";
                case ByteCode.FSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1419]++;         return "fstore_2";
                case ByteCode.FSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1420]++;         return "fstore_3";
                case ByteCode.DSTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1421]++;         return "dstore_0";
                case ByteCode.DSTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1422]++;         return "dstore_1";
                case ByteCode.DSTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1423]++;         return "dstore_2";
                case ByteCode.DSTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1424]++;         return "dstore_3";
                case ByteCode.ASTORE_0:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1425]++;         return "astore_0";
                case ByteCode.ASTORE_1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1426]++;         return "astore_1";
                case ByteCode.ASTORE_2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1427]++;         return "astore_2";
                case ByteCode.ASTORE_3:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1428]++;         return "astore_3";
                case ByteCode.IASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1429]++;          return "iastore";
                case ByteCode.LASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1430]++;          return "lastore";
                case ByteCode.FASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1431]++;          return "fastore";
                case ByteCode.DASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1432]++;          return "dastore";
                case ByteCode.AASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1433]++;          return "aastore";
                case ByteCode.BASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1434]++;          return "bastore";
                case ByteCode.CASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1435]++;          return "castore";
                case ByteCode.SASTORE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1436]++;          return "sastore";
                case ByteCode.POP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1437]++;              return "pop";
                case ByteCode.POP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1438]++;             return "pop2";
                case ByteCode.DUP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1439]++;              return "dup";
                case ByteCode.DUP_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1440]++;           return "dup_x1";
                case ByteCode.DUP_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1441]++;           return "dup_x2";
                case ByteCode.DUP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1442]++;             return "dup2";
                case ByteCode.DUP2_X1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1443]++;          return "dup2_x1";
                case ByteCode.DUP2_X2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1444]++;          return "dup2_x2";
                case ByteCode.SWAP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1445]++;             return "swap";
                case ByteCode.IADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1446]++;             return "iadd";
                case ByteCode.LADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1447]++;             return "ladd";
                case ByteCode.FADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1448]++;             return "fadd";
                case ByteCode.DADD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1449]++;             return "dadd";
                case ByteCode.ISUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1450]++;             return "isub";
                case ByteCode.LSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1451]++;             return "lsub";
                case ByteCode.FSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1452]++;             return "fsub";
                case ByteCode.DSUB:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1453]++;             return "dsub";
                case ByteCode.IMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1454]++;             return "imul";
                case ByteCode.LMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1455]++;             return "lmul";
                case ByteCode.FMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1456]++;             return "fmul";
                case ByteCode.DMUL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1457]++;             return "dmul";
                case ByteCode.IDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1458]++;             return "idiv";
                case ByteCode.LDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1459]++;             return "ldiv";
                case ByteCode.FDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1460]++;             return "fdiv";
                case ByteCode.DDIV:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1461]++;             return "ddiv";
                case ByteCode.IREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1462]++;             return "irem";
                case ByteCode.LREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1463]++;             return "lrem";
                case ByteCode.FREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1464]++;             return "frem";
                case ByteCode.DREM:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1465]++;             return "drem";
                case ByteCode.INEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1466]++;             return "ineg";
                case ByteCode.LNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1467]++;             return "lneg";
                case ByteCode.FNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1468]++;             return "fneg";
                case ByteCode.DNEG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1469]++;             return "dneg";
                case ByteCode.ISHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1470]++;             return "ishl";
                case ByteCode.LSHL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1471]++;             return "lshl";
                case ByteCode.ISHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1472]++;             return "ishr";
                case ByteCode.LSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1473]++;             return "lshr";
                case ByteCode.IUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1474]++;            return "iushr";
                case ByteCode.LUSHR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1475]++;            return "lushr";
                case ByteCode.IAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1476]++;             return "iand";
                case ByteCode.LAND:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1477]++;             return "land";
                case ByteCode.IOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1478]++;              return "ior";
                case ByteCode.LOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1479]++;              return "lor";
                case ByteCode.IXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1480]++;             return "ixor";
                case ByteCode.LXOR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1481]++;             return "lxor";
                case ByteCode.IINC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1482]++;             return "iinc";
                case ByteCode.I2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1483]++;              return "i2l";
                case ByteCode.I2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1484]++;              return "i2f";
                case ByteCode.I2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1485]++;              return "i2d";
                case ByteCode.L2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1486]++;              return "l2i";
                case ByteCode.L2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1487]++;              return "l2f";
                case ByteCode.L2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1488]++;              return "l2d";
                case ByteCode.F2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1489]++;              return "f2i";
                case ByteCode.F2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1490]++;              return "f2l";
                case ByteCode.F2D:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1491]++;              return "f2d";
                case ByteCode.D2I:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1492]++;              return "d2i";
                case ByteCode.D2L:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1493]++;              return "d2l";
                case ByteCode.D2F:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1494]++;              return "d2f";
                case ByteCode.I2B:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1495]++;              return "i2b";
                case ByteCode.I2C:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1496]++;              return "i2c";
                case ByteCode.I2S:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1497]++;              return "i2s";
                case ByteCode.LCMP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1498]++;             return "lcmp";
                case ByteCode.FCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1499]++;            return "fcmpl";
                case ByteCode.FCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1500]++;            return "fcmpg";
                case ByteCode.DCMPL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1501]++;            return "dcmpl";
                case ByteCode.DCMPG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1502]++;            return "dcmpg";
                case ByteCode.IFEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1503]++;             return "ifeq";
                case ByteCode.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1504]++;             return "ifne";
                case ByteCode.IFLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1505]++;             return "iflt";
                case ByteCode.IFGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1506]++;             return "ifge";
                case ByteCode.IFGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1507]++;             return "ifgt";
                case ByteCode.IFLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1508]++;             return "ifle";
                case ByteCode.IF_ICMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1509]++;        return "if_icmpeq";
                case ByteCode.IF_ICMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1510]++;        return "if_icmpne";
                case ByteCode.IF_ICMPLT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1511]++;        return "if_icmplt";
                case ByteCode.IF_ICMPGE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1512]++;        return "if_icmpge";
                case ByteCode.IF_ICMPGT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1513]++;        return "if_icmpgt";
                case ByteCode.IF_ICMPLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1514]++;        return "if_icmple";
                case ByteCode.IF_ACMPEQ:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1515]++;        return "if_acmpeq";
                case ByteCode.IF_ACMPNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1516]++;        return "if_acmpne";
                case ByteCode.GOTO:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1517]++;             return "goto";
                case ByteCode.JSR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1518]++;              return "jsr";
                case ByteCode.RET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1519]++;              return "ret";
                case ByteCode.TABLESWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1520]++;      return "tableswitch";
                case ByteCode.LOOKUPSWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1521]++;     return "lookupswitch";
                case ByteCode.IRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1522]++;          return "ireturn";
                case ByteCode.LRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1523]++;          return "lreturn";
                case ByteCode.FRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1524]++;          return "freturn";
                case ByteCode.DRETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1525]++;          return "dreturn";
                case ByteCode.ARETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1526]++;          return "areturn";
                case ByteCode.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1527]++;           return "return";
                case ByteCode.GETSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1528]++;        return "getstatic";
                case ByteCode.PUTSTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1529]++;        return "putstatic";
                case ByteCode.GETFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1530]++;         return "getfield";
                case ByteCode.PUTFIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1531]++;         return "putfield";
                case ByteCode.INVOKEVIRTUAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1532]++;    return "invokevirtual";
                case ByteCode.INVOKESPECIAL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1533]++;    return "invokespecial";
                case ByteCode.INVOKESTATIC:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1534]++;     return "invokestatic";
                case ByteCode.INVOKEINTERFACE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1535]++;  return "invokeinterface";
                case ByteCode.NEW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1536]++;              return "new";
                case ByteCode.NEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1537]++;         return "newarray";
                case ByteCode.ANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1538]++;        return "anewarray";
                case ByteCode.ARRAYLENGTH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1539]++;      return "arraylength";
                case ByteCode.ATHROW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1540]++;           return "athrow";
                case ByteCode.CHECKCAST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1541]++;        return "checkcast";
                case ByteCode.INSTANCEOF:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1542]++;       return "instanceof";
                case ByteCode.MONITORENTER:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1543]++;     return "monitorenter";
                case ByteCode.MONITOREXIT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1544]++;      return "monitorexit";
                case ByteCode.WIDE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1545]++;             return "wide";
                case ByteCode.MULTIANEWARRAY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1546]++;   return "multianewarray";
                case ByteCode.IFNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1547]++;           return "ifnull";
                case ByteCode.IFNONNULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1548]++;        return "ifnonnull";
                case ByteCode.GOTO_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1549]++;           return "goto_w";
                case ByteCode.JSR_W:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1550]++;            return "jsr_w";
                case ByteCode.BREAKPOINT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1551]++;       return "breakpoint";

                case ByteCode.IMPDEP1:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1552]++;          return "impdep1";
                case ByteCode.IMPDEP2:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1553]++;          return "impdep2"; default : CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1554]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1349]++;}
        return "";
    }

    final char[] getCharBuffer(int minimalSize)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1128]++;
int CodeCoverConditionCoverageHelper_C221;
        if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((minimalSize > tmpCharBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1555]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1129]++;
            int newSize = tmpCharBuffer.length * 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1130]++;
int CodeCoverConditionCoverageHelper_C222;
            if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((minimalSize > newSize) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1557]++; newSize = minimalSize;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1131]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1558]++;}
            tmpCharBuffer = new char[newSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1132]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1556]++;}
        return tmpCharBuffer;
    }

    /**
     * Add a pc as the start of super block.
     *
     * A pc is the beginning of a super block if:
     * - pc == 0
     * - it is the target of a branch instruction
     * - it is the beginning of an exception handler
     * - it is directly after an unconditional jump
     */
    private void addSuperBlockStart(int pc) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1133]++;
int CodeCoverConditionCoverageHelper_C223;
        if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((GenerateStackMap) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1559]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1134]++;
int CodeCoverConditionCoverageHelper_C224;
            if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((itsSuperBlockStarts == null) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1561]++;
                itsSuperBlockStarts = new int[SuperBlockStartsSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1135]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1562]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1136]++;
int CodeCoverConditionCoverageHelper_C225; if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((itsSuperBlockStarts.length == itsSuperBlockStartsTop) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1563]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1137]++;
                int[] tmp = new int[itsSuperBlockStartsTop * 2];
                System.arraycopy(itsSuperBlockStarts, 0, tmp, 0,
                                 itsSuperBlockStartsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1138]++;
                itsSuperBlockStarts = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1139]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1564]++;}
}
            itsSuperBlockStarts[itsSuperBlockStartsTop++] = pc;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1140]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1560]++;}
    }

    /**
     * Sort the list of recorded super block starts and remove duplicates.
     *
     * Also adds exception handling blocks as block starts, since there is no
     * explicit control flow to these. Used for stack map table generation.
     */
    private void finalizeSuperBlockStarts() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1141]++;
int CodeCoverConditionCoverageHelper_C226;
        if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((GenerateStackMap) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1565]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1142]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[109]++;


int CodeCoverConditionCoverageHelper_C227;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((i < itsExceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[109]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[110]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[111]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1143]++;
                ExceptionTableEntry ete = itsExceptionTable[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1144]++;
                short handlerPC = (short) getLabelPC(ete.itsHandlerLabel);
                addSuperBlockStart(handlerPC);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1145]++;
            }
            Arrays.sort(itsSuperBlockStarts, 0, itsSuperBlockStartsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1146]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1147]++;
            int prev = itsSuperBlockStarts[0];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1148]++;
            int copyTo = 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1149]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[112]++;


int CodeCoverConditionCoverageHelper_C228;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((i < itsSuperBlockStartsTop) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[112]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[113]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[114]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1150]++;
                int curr = itsSuperBlockStarts[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1151]++;
int CodeCoverConditionCoverageHelper_C229;
                if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((prev != curr) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1567]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1152]++;
int CodeCoverConditionCoverageHelper_C230;
                    if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((copyTo != i) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1569]++;
                        itsSuperBlockStarts[copyTo] = curr;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1153]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1570]++;}
                    copyTo++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1154]++;
                    prev = curr;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1155]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1568]++;}
            }
            itsSuperBlockStartsTop = copyTo;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1156]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1157]++;
int CodeCoverConditionCoverageHelper_C231;
            if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((itsSuperBlockStarts[copyTo - 1] == itsCodeBufferTop) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1571]++;
                itsSuperBlockStartsTop--;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1158]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1572]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1566]++;}
    }

    private int[] itsSuperBlockStarts = null;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1159]++;
  }
    private int itsSuperBlockStartsTop = 0;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1160]++;
  }
    private static final int SuperBlockStartsSize = 4;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1161]++;
  }

    // Used to find blocks of code with no dependencies (aka dead code).
    // Necessary for generating type information for dead code, which is
    // expected by the Sun verifier. It is only necessary to store a single
    // jump source to determine if a block is reachable or not.
    private UintMap itsJumpFroms = null;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1162]++;
  }

    private static final int LineNumberTableSize = 16;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1163]++;
  }
    private static final int ExceptionTableSize = 4;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1164]++;
  }

    private static final int MajorVersion;
    private static final int MinorVersion;
    private static final boolean GenerateStackMap;

    static {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1165]++;
        // Figure out which classfile version should be generated. This assumes
        // that the runtime used to compile the JavaScript files is the same as
        // the one used to run them. This is important because there are cases
        // when bytecode is generated at runtime, where it is not easy to pass
        // along what version is necessary. Instead, we grab the version numbers
        // from the bytecode of this class and use that.
        //
        // Based on the version numbers we scrape, we can also determine what
        // bytecode features we need. For example, Java 6 bytecode (classfile
        // version 50) should have stack maps generated.
        InputStream is = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1166]++;
        int major = 48, minor = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1167]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            is = ClassFileWriter.class.getResourceAsStream("ClassFileWriter.class");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1168]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1169]++;
int CodeCoverConditionCoverageHelper_C232;
            if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((is == null) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1574]++;
                is = ClassLoader.getSystemResourceAsStream(
                    "org/mozilla/classfile/ClassFileWriter.class");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1170]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1575]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1171]++;
            byte[] header = new byte[8];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1172]++;
            // read loop is required since JDK7 will only provide 2 bytes
            // on the first read() - see bug #630111
            int read = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1173]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[115]++;


int CodeCoverConditionCoverageHelper_C233;
            while ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((read < 8) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[115]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[116]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[117]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1174]++;
                int c = is.read(header, read, 8 - read);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1175]++;
int CodeCoverConditionCoverageHelper_C234;
                if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((c < 0) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1576]++; throw new IOException();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1577]++;}
                read += c;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1176]++;
            }
            minor = (header[4] << 8) | (header[5] & 0xff);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1177]++;
            major = (header[6] << 8) | (header[7] & 0xff);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1178]++;
        } catch (Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1578]++;
            // Unable to get class file, use default bytecode version
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1573]++;
}
            MinorVersion = minor;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1179]++;
            MajorVersion = major;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1180]++;
            GenerateStackMap = major >= 50;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1181]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1182]++;
int CodeCoverConditionCoverageHelper_C235;
            if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((is != null) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1579]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1183]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    is.close();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1184]++;
                } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1582]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1581]++;
}
  }

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1580]++;}
        }
    }

    private final static int FileHeaderConstant = 0xCAFEBABE;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1185]++;
  }
    // Set DEBUG flags to true to get better checking and progress info.
    private static final boolean DEBUGSTACK = false;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1186]++;
  }
    private static final boolean DEBUGLABELS = false;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1187]++;
  }
    private static final boolean DEBUGCODE = false;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1188]++;
  }

    private String generatedClassName;

    private ExceptionTableEntry itsExceptionTable[];
    private int itsExceptionTableTop;

    private int itsLineNumberTable[];   // pack start_pc & line_number together
    private int itsLineNumberTableTop;

    private byte[] itsCodeBuffer = new byte[256];
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1189]++;
  }
    private int itsCodeBufferTop;

    private ConstantPool itsConstantPool;

    private ClassFileMethod itsCurrentMethod;
    private short itsStackTop;

    private short itsMaxStack;
    private short itsMaxLocals;

    private ObjArray itsMethods = new ObjArray();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1190]++;
  }
    private ObjArray itsFields = new ObjArray();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1191]++;
  }
    private ObjArray itsInterfaces = new ObjArray();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1192]++;
  }

    private short itsFlags;
    private short itsThisClassIndex;
    private short itsSuperClassIndex;
    private short itsSourceFileNameIndex;

    private static final int MIN_LABEL_TABLE_SIZE = 32;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1193]++;
  }
    private int[] itsLabelTable;
    private int itsLabelTableTop;

// itsFixupTable[i] = (label_index << 32) | fixup_site
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1194]++;
  }
    private long[] itsFixupTable;
    private int itsFixupTableTop;
    private ObjArray itsVarDescriptors;

    private char[] tmpCharBuffer = new char[64];
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1195]++;
  }
}

final class ExceptionTableEntry
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }


    ExceptionTableEntry(int startLabel, int endLabel,
                        int handlerLabel, short catchType)
    {
        itsStartLabel = startLabel;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1196]++;
        itsEndLabel = endLabel;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1197]++;
        itsHandlerLabel = handlerLabel;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1198]++;
        itsCatchType = catchType;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1199]++;
    }

    int itsStartLabel;
    int itsEndLabel;
    int itsHandlerLabel;
    short itsCatchType;
}

final class ClassFileField
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }


    ClassFileField(short nameIndex, short typeIndex, short flags)
    {
        itsNameIndex = nameIndex;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1200]++;
        itsTypeIndex = typeIndex;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1201]++;
        itsFlags = flags;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1202]++;
        itsHasAttributes = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1203]++;
    }

    void setAttributes(short attr1, short attr2, short attr3, int index)
    {
        itsHasAttributes = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1204]++;
        itsAttr1 = attr1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1205]++;
        itsAttr2 = attr2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1206]++;
        itsAttr3 = attr3;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1207]++;
        itsIndex = index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1208]++;
    }

    int write(byte[] data, int offset)
    {
        offset = ClassFileWriter.putInt16(itsFlags, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1209]++;
        offset = ClassFileWriter.putInt16(itsNameIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1210]++;
        offset = ClassFileWriter.putInt16(itsTypeIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1211]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1212]++;
int CodeCoverConditionCoverageHelper_C236;
        if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((itsHasAttributes) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1583]++;
            // write 0 attributes
            offset = ClassFileWriter.putInt16(0, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1213]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1584]++;
            offset = ClassFileWriter.putInt16(1, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1214]++;
            offset = ClassFileWriter.putInt16(itsAttr1, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1215]++;
            offset = ClassFileWriter.putInt16(itsAttr2, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1216]++;
            offset = ClassFileWriter.putInt16(itsAttr3, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1217]++;
            offset = ClassFileWriter.putInt16(itsIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1218]++;
        }
        return offset;
    }

    int getWriteSize()
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1219]++;
        int size = 2 * 3;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1220]++;
int CodeCoverConditionCoverageHelper_C237;
        if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((itsHasAttributes) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1585]++;
            size += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1221]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1586]++;
            size += 2 + 2 * 4;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1222]++;
        }
        return size;
    }

    private short itsNameIndex;
    private short itsTypeIndex;
    private short itsFlags;
    private boolean itsHasAttributes;
    private short itsAttr1, itsAttr2, itsAttr3;
    private int itsIndex;
}

final class ClassFileMethod
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }


    ClassFileMethod(String name, short nameIndex, String type, short typeIndex,
                    short flags)
    {
        itsName = name;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1223]++;
        itsNameIndex = nameIndex;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1224]++;
        itsType = type;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1225]++;
        itsTypeIndex = typeIndex;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1226]++;
        itsFlags = flags;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1227]++;
    }

    void setCodeAttribute(byte codeAttribute[])
    {
        itsCodeAttribute = codeAttribute;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1228]++;
    }

    int write(byte[] data, int offset)
    {
        offset = ClassFileWriter.putInt16(itsFlags, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1229]++;
        offset = ClassFileWriter.putInt16(itsNameIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1230]++;
        offset = ClassFileWriter.putInt16(itsTypeIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1231]++;
        // Code attribute only
        offset = ClassFileWriter.putInt16(1, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1232]++;
        System.arraycopy(itsCodeAttribute, 0, data, offset,
                         itsCodeAttribute.length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1233]++;
        offset += itsCodeAttribute.length;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1234]++;
        return offset;
    }

    int getWriteSize()
    {
        return 2 * 4 + itsCodeAttribute.length;
    }

    String getName()
    {
        return itsName;
    }

    String getType()
    {
        return itsType;
    }

    short getFlags()
    {
        return itsFlags;
    }

    private String itsName;
    private String itsType;
    private short itsNameIndex;
    private short itsTypeIndex;
    private short itsFlags;
    private byte[] itsCodeAttribute;

}

final class ConstantPool
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }


    ConstantPool(ClassFileWriter cfw)
    {
        this.cfw = cfw;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1235]++;
        itsTopIndex = 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1236]++;       // the zero'th entry is reserved
        itsPool = new byte[ConstantPoolSize];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1237]++;
        itsTop = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1238]++;
    }

    private static final int ConstantPoolSize = 256;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1239]++;
  }
    static final byte
        CONSTANT_Class = 7,
        CONSTANT_Fieldref = 9,
        CONSTANT_Methodref = 10,
        CONSTANT_InterfaceMethodref = 11,
        CONSTANT_String = 8,
        CONSTANT_Integer = 3,
        CONSTANT_Float = 4,
        CONSTANT_Long = 5,
        CONSTANT_Double = 6,
        CONSTANT_NameAndType = 12,
        CONSTANT_Utf8 = 1;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1240]++;
  }

    int write(byte[] data, int offset)
    {
        offset = ClassFileWriter.putInt16((short)itsTopIndex, data, offset);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1241]++;
        System.arraycopy(itsPool, 0, data, offset, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1242]++;
        offset += itsTop;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1243]++;
        return offset;
    }

    int getWriteSize()
    {
        return 2 + itsTop;
    }

    int addConstant(int k)
    {
        ensure(5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1244]++;
        itsPool[itsTop++] = CONSTANT_Integer;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1245]++;
        itsTop = ClassFileWriter.putInt32(k, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1246]++;
        itsPoolTypes.put(itsTopIndex, CONSTANT_Integer);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1247]++;
        return (short)(itsTopIndex++);
    }

    int addConstant(long k)
    {
        ensure(9);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1248]++;
        itsPool[itsTop++] = CONSTANT_Long;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1249]++;
        itsTop = ClassFileWriter.putInt64(k, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1250]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1251]++;
        int index = itsTopIndex;
        itsTopIndex += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1252]++;
        itsPoolTypes.put(index, CONSTANT_Long);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1253]++;
        return index;
    }

    int addConstant(float k)
    {
        ensure(5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1254]++;
        itsPool[itsTop++] = CONSTANT_Float;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1255]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1256]++;
        int bits = Float.floatToIntBits(k);
        itsTop = ClassFileWriter.putInt32(bits, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1257]++;
        itsPoolTypes.put(itsTopIndex, CONSTANT_Float);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1258]++;
        return itsTopIndex++;
    }

    int addConstant(double k)
    {
        ensure(9);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1259]++;
        itsPool[itsTop++] = CONSTANT_Double;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1260]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1261]++;
        long bits = Double.doubleToLongBits(k);
        itsTop = ClassFileWriter.putInt64(bits, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1262]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1263]++;
        int index = itsTopIndex;
        itsTopIndex += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1264]++;
        itsPoolTypes.put(index, CONSTANT_Double);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1265]++;
        return index;
    }

    int addConstant(String k)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1266]++;
        int utf8Index = 0xFFFF & addUtf8(k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1267]++;
        int theIndex = itsStringConstHash.getInt(utf8Index, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1268]++;
int CodeCoverConditionCoverageHelper_C238;
        if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((theIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1587]++;
            theIndex = itsTopIndex++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1269]++;
            ensure(3);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1270]++;
            itsPool[itsTop++] = CONSTANT_String;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1271]++;
            itsTop = ClassFileWriter.putInt16(utf8Index, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1272]++;
            itsStringConstHash.put(utf8Index, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1273]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1588]++;}
        itsPoolTypes.put(theIndex, CONSTANT_String);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1274]++;
        return theIndex;
    }

    boolean isUnderUtfEncodingLimit(String s)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1275]++;
        int strLen = s.length();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1276]++;
int CodeCoverConditionCoverageHelper_C239;
        if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((strLen * 3 <= MAX_UTF_ENCODING_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1589]++;
            return true;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1590]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1277]++;
int CodeCoverConditionCoverageHelper_C240; if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((strLen > MAX_UTF_ENCODING_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1591]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1592]++;}
}
        return strLen == getUtfEncodingLimit(s, 0, strLen);
    }

    /**
     * Get maximum i such that <tt>start <= i <= end</tt> and
     * <tt>s.substring(start, i)</tt> fits JVM UTF string encoding limit.
     */
    int getUtfEncodingLimit(String s, int start, int end)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1278]++;
int CodeCoverConditionCoverageHelper_C241;
        if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 (((end - start) * 3 <= MAX_UTF_ENCODING_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1593]++;
            return end;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1594]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1279]++;
        int limit = MAX_UTF_ENCODING_SIZE;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1280]++;
byte CodeCoverTryBranchHelper_L40 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[118]++;


int CodeCoverConditionCoverageHelper_C242;
        for (int i = start;(((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L40 == 0) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[118]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[119]++;
} else if (CodeCoverTryBranchHelper_L40 == 1) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[119]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[120]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1281]++;
            int c = s.charAt(i);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1282]++;
int CodeCoverConditionCoverageHelper_C243;
            if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (8)) == 0 || true) &&
 ((0 != c) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((c <= 0x7F) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1595]++;
                --limit;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1283]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1596]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1284]++;
int CodeCoverConditionCoverageHelper_C244; if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((c < 0x7FF) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1597]++;
                limit -= 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1285]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1598]++;
                limit -= 3;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1286]++;
            }
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1287]++;
int CodeCoverConditionCoverageHelper_C245;
            if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((limit < 0) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1599]++;
                return i;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1600]++;}
        }
        return end;
    }

    short addUtf8(String k)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1288]++;
        int theIndex = itsUtf8Hash.get(k, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1289]++;
int CodeCoverConditionCoverageHelper_C246;
        if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((theIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1601]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1290]++;
            int strLen = k.length();
            boolean tooBigString;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1291]++;
int CodeCoverConditionCoverageHelper_C247;
            if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((strLen > MAX_UTF_ENCODING_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1603]++;
                tooBigString = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1292]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1604]++;
                tooBigString = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1293]++;
                // Ask for worst case scenario buffer when each char takes 3
                // bytes
                ensure(1 + 2 + strLen * 3);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1294]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1295]++;
                int top = itsTop;

                itsPool[top++] = CONSTANT_Utf8;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1296]++;
                top += 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1297]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1298]++; // skip length

                char[] chars = cfw.getCharBuffer(strLen);
                k.getChars(0, strLen, chars, 0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1299]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1300]++;
byte CodeCoverTryBranchHelper_L41 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[121]++;


int CodeCoverConditionCoverageHelper_C248;

                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((i != strLen) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L41 == 0) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[121]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[122]++;
} else if (CodeCoverTryBranchHelper_L41 == 1) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[122]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[123]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1301]++;
                    int c = chars[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1302]++;
int CodeCoverConditionCoverageHelper_C249;
                    if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (8)) == 0 || true) &&
 ((c != 0) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((c <= 0x7F) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1605]++;
                        itsPool[top++] = (byte)c;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1303]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1606]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1304]++;
int CodeCoverConditionCoverageHelper_C250; if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((c > 0x7FF) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1607]++;
                        itsPool[top++] = (byte)(0xE0 | (c >> 12));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1305]++;
                        itsPool[top++] = (byte)(0x80 | ((c >> 6) & 0x3F));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1306]++;
                        itsPool[top++] = (byte)(0x80 | (c & 0x3F));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1307]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1608]++;
                        itsPool[top++] = (byte)(0xC0 | (c >> 6));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1308]++;
                        itsPool[top++] = (byte)(0x80 | (c & 0x3F));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1309]++;
                    }
}
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1310]++;

                int utfLen = top - (itsTop + 1 + 2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1311]++;
int CodeCoverConditionCoverageHelper_C251;
                if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((utfLen > MAX_UTF_ENCODING_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1609]++;
                    tooBigString = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1312]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1610]++;
                    // Write back length
                    itsPool[itsTop + 1] = (byte)(utfLen >>> 8);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1313]++;
                    itsPool[itsTop + 2] = (byte)utfLen;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1314]++;

                    itsTop = top;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1315]++;
                    theIndex = itsTopIndex++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1316]++;
                    itsUtf8Hash.put(k, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1317]++;
                }
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1318]++;
int CodeCoverConditionCoverageHelper_C252;
            if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((tooBigString) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1611]++;
                throw new IllegalArgumentException("Too big string");

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1612]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1602]++;}
        setConstantData(theIndex, k);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1319]++;
        itsPoolTypes.put(theIndex, CONSTANT_Utf8);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1320]++;
        return (short)theIndex;
    }

    private short addNameAndType(String name, String type)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1321]++;
        short nameIndex = addUtf8(name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1322]++;
        short typeIndex = addUtf8(type);
        ensure(5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1323]++;
        itsPool[itsTop++] = CONSTANT_NameAndType;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1324]++;
        itsTop = ClassFileWriter.putInt16(nameIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1325]++;
        itsTop = ClassFileWriter.putInt16(typeIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1326]++;
        itsPoolTypes.put(itsTopIndex, CONSTANT_NameAndType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1327]++;
        return (short)(itsTopIndex++);
    }

    short addClass(String className)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1328]++;
        int theIndex = itsClassHash.get(className, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1329]++;
int CodeCoverConditionCoverageHelper_C253;
        if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((theIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1613]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1330]++;
            String slashed = className;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1331]++;
int CodeCoverConditionCoverageHelper_C254;
            if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((className.indexOf('.') > 0) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1615]++;
                slashed = ClassFileWriter.getSlashedForm(className);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1332]++;
                theIndex = itsClassHash.get(slashed, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1333]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1334]++;
int CodeCoverConditionCoverageHelper_C255;
                if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((theIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1617]++;
                    itsClassHash.put(className, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1335]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1618]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1616]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1336]++;
int CodeCoverConditionCoverageHelper_C256;
            if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((theIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1619]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1337]++;
                int utf8Index = addUtf8(slashed);
                ensure(3);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1338]++;
                itsPool[itsTop++] = CONSTANT_Class;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1339]++;
                itsTop = ClassFileWriter.putInt16(utf8Index, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1340]++;
                theIndex = itsTopIndex++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1341]++;
                itsClassHash.put(slashed, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1342]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1343]++;
int CodeCoverConditionCoverageHelper_C257;
                if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((className != slashed) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1621]++;
                    itsClassHash.put(className, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1344]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1622]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1620]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1614]++;}
        setConstantData(theIndex, className);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1345]++;
        itsPoolTypes.put(theIndex, CONSTANT_Class);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1346]++;
        return (short)theIndex;
    }

    short addFieldRef(String className, String fieldName, String fieldType)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1347]++;
        FieldOrMethodRef ref = new FieldOrMethodRef(className, fieldName,
                                                    fieldType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1348]++;

        int theIndex = itsFieldRefHash.get(ref, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1349]++;
int CodeCoverConditionCoverageHelper_C258;
        if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((theIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1623]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1350]++;
            short ntIndex = addNameAndType(fieldName, fieldType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1351]++;
            short classIndex = addClass(className);
            ensure(5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1352]++;
            itsPool[itsTop++] = CONSTANT_Fieldref;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1353]++;
            itsTop = ClassFileWriter.putInt16(classIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1354]++;
            itsTop = ClassFileWriter.putInt16(ntIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1355]++;
            theIndex = itsTopIndex++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1356]++;
            itsFieldRefHash.put(ref, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1357]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1624]++;}
        setConstantData(theIndex, ref);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1358]++;
        itsPoolTypes.put(theIndex, CONSTANT_Fieldref);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1359]++;
        return (short)theIndex;
    }

    short addMethodRef(String className, String methodName,
                       String methodType)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1360]++;
        FieldOrMethodRef ref = new FieldOrMethodRef(className, methodName,
                                                    methodType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1361]++;

        int theIndex = itsMethodRefHash.get(ref, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1362]++;
int CodeCoverConditionCoverageHelper_C259;
        if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((theIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1625]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1363]++;
            short ntIndex = addNameAndType(methodName, methodType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1364]++;
            short classIndex = addClass(className);
            ensure(5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1365]++;
            itsPool[itsTop++] = CONSTANT_Methodref;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1366]++;
            itsTop = ClassFileWriter.putInt16(classIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1367]++;
            itsTop = ClassFileWriter.putInt16(ntIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1368]++;
            theIndex = itsTopIndex++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1369]++;
            itsMethodRefHash.put(ref, theIndex);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1370]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1626]++;}
        setConstantData(theIndex, ref);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1371]++;
        itsPoolTypes.put(theIndex, CONSTANT_Methodref);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1372]++;
        return (short)theIndex;
    }

    short addInterfaceMethodRef(String className,
                                String methodName, String methodType)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1373]++;
        short ntIndex = addNameAndType(methodName, methodType);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1374]++;
        short classIndex = addClass(className);
        ensure(5);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1375]++;
        itsPool[itsTop++] = CONSTANT_InterfaceMethodref;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1376]++;
        itsTop = ClassFileWriter.putInt16(classIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1377]++;
        itsTop = ClassFileWriter.putInt16(ntIndex, itsPool, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1378]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1379]++;
        FieldOrMethodRef r = new FieldOrMethodRef(className, methodName,
                                                  methodType);
        setConstantData(itsTopIndex, r);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1380]++;
        itsPoolTypes.put(itsTopIndex, CONSTANT_InterfaceMethodref);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1381]++;
        return (short)(itsTopIndex++);
    }

    Object getConstantData(int index)
    {
        return itsConstantData.getObject(index);
    }

    void setConstantData(int index, Object data)
    {
        itsConstantData.put(index, data);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1382]++;
    }

    byte getConstantType(int index)
    {
        return (byte) itsPoolTypes.getInt(index, 0);
    }

    void ensure(int howMuch)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1383]++;
int CodeCoverConditionCoverageHelper_C260;
        if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((itsTop + howMuch > itsPool.length) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1627]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1384]++;
            int newCapacity = itsPool.length * 2;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1385]++;
int CodeCoverConditionCoverageHelper_C261;
            if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((itsTop + howMuch > newCapacity) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1629]++;
                newCapacity = itsTop + howMuch;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1386]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1630]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1387]++;
            byte[] tmp = new byte[newCapacity];
            System.arraycopy(itsPool, 0, tmp, 0, itsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1388]++;
            itsPool = tmp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1389]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1628]++;}
    }

    private ClassFileWriter cfw;

    private static final int MAX_UTF_ENCODING_SIZE = 65535;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1390]++;
  }

    private UintMap itsStringConstHash = new UintMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1391]++;
  }
    private ObjToIntMap itsUtf8Hash = new ObjToIntMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1392]++;
  }
    private ObjToIntMap itsFieldRefHash = new ObjToIntMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1393]++;
  }
    private ObjToIntMap itsMethodRefHash = new ObjToIntMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1394]++;
  }
    private ObjToIntMap itsClassHash = new ObjToIntMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1395]++;
  }

    private int itsTop;
    private int itsTopIndex;
    private UintMap itsConstantData = new UintMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1396]++;
  }
    private UintMap itsPoolTypes = new UintMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1397]++;
  }
    private byte itsPool[];
}

final class FieldOrMethodRef
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }

    FieldOrMethodRef(String className, String name, String type)
    {
        this.className = className;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1398]++;
        this.name = name;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1399]++;
        this.type = type;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1400]++;
    }

    public String getClassName()
    {
        return className;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public boolean equals(Object obj)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1401]++;
int CodeCoverConditionCoverageHelper_C262;
        if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((obj instanceof FieldOrMethodRef) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1631]++; return false;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1632]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1402]++;
        FieldOrMethodRef x = (FieldOrMethodRef)obj;
        return className.equals(x.className)
            && name.equals(x.name)
            && type.equals(x.type);
    }

    @Override
    public int hashCode()
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1403]++;
int CodeCoverConditionCoverageHelper_C263;
        if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((hashCode == -1) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1633]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1404]++;
            int h1 = className.hashCode();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1405]++;
            int h2 = name.hashCode();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1406]++;
            int h3 = type.hashCode();
            hashCode = h1 ^ h2 ^ h3;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1407]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1634]++;}
        return hashCode;
    }

    private String className;
    private String name;
    private String type;
    private int hashCode = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1408]++;
  }
}

/**
 * A super block is defined as a contiguous chunk of code with a single entry
 * point and multiple exit points (therefore ending in an unconditional jump
 * or the end of the method). This is used to emulate OpenJDK's compiler, which
 * outputs stack map frames at the start of every super block except the method
 * start.
 */
final class SuperBlock {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }

    SuperBlock(int index, int start, int end, int[] initialLocals) {
        this.index = index;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1409]++;
        this.start = start;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1410]++;
        this.end = end;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1411]++;
        locals = new int[initialLocals.length];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1412]++;
        System.arraycopy(initialLocals, 0, locals, 0, initialLocals.length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1413]++;
        stack = new int[0];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1414]++;
        isInitialized = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1415]++;
        isInQueue = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1416]++;
    }

    int getIndex() {
        return index;
    }

    int[] getLocals() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1417]++;
        int[] copy = new int[locals.length];
        System.arraycopy(locals, 0, copy, 0, locals.length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1418]++;
        return copy;
    }

    /**
     * Get a copy of the super block's locals without any trailing TOP types.
     *
     * This is useful for actual writing stack maps; during the computation of
     * stack map types, all local arrays have the same size; the max locals for
     * the method. In addition, DOUBLE and LONG types have trailing TOP types
     * because they occupy two words. For writing purposes, these are not
     * useful.
     */
    int[] getTrimmedLocals() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1419]++;
        int last = locals.length - 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1420]++;
byte CodeCoverTryBranchHelper_L42 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[124]++;


int CodeCoverConditionCoverageHelper_C264;
        // Exclude all of the trailing TOPs not bound to a DOUBLE/LONG
        while ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (32)) == 0 || true) &&
 ((last >= 0) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C264 |= (8)) == 0 || true) &&
 ((locals[last] == TypeInfo.TOP) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((TypeInfo.isTwoWords(locals[last - 1])) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 3) && false)) {
if (CodeCoverTryBranchHelper_L42 == 0) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[124]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[125]++;
} else if (CodeCoverTryBranchHelper_L42 == 1) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[125]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[126]++;
}
            last--;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1421]++;
        }
        last++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1422]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1423]++;
        // Exclude trailing TOPs following a DOUBLE/LONG
        int size = last;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1424]++;
byte CodeCoverTryBranchHelper_L43 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[127]++;


int CodeCoverConditionCoverageHelper_C265;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((i < last) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L43 == 0) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[127]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[128]++;
} else if (CodeCoverTryBranchHelper_L43 == 1) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[128]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[129]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1425]++;
int CodeCoverConditionCoverageHelper_C266;
            if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((TypeInfo.isTwoWords(locals[i])) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1635]++;
                size--;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1426]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1636]++;}
        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1427]++;
        int[] copy = new int[size];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1428]++;
byte CodeCoverTryBranchHelper_L44 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[130]++;


int CodeCoverConditionCoverageHelper_C267;
        for (int i = 0, j = 0;(((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false); i++, j++) {
if (CodeCoverTryBranchHelper_L44 == 0) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[130]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[131]++;
} else if (CodeCoverTryBranchHelper_L44 == 1) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[131]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[132]++;
}
            copy[i] = locals[j];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1429]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1430]++;
int CodeCoverConditionCoverageHelper_C268;
            if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((TypeInfo.isTwoWords(locals[j])) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1637]++;
                j++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1431]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1638]++;}
        }
        return copy;
    }

    int[] getStack() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1432]++;
        int[] copy = new int[stack.length];
        System.arraycopy(stack, 0, copy, 0, stack.length);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1433]++;
        return copy;
    }

    boolean merge(int[] locals, int localsTop, int[] stack, int stackTop,
                  ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1434]++;
int CodeCoverConditionCoverageHelper_C269;
        if ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((isInitialized) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1639]++;
            System.arraycopy(locals, 0, this.locals, 0, localsTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1435]++;
            this.stack = new int[stackTop];
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1436]++;
            System.arraycopy(stack, 0, this.stack, 0, stackTop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1437]++;
            isInitialized = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1438]++;
            return true;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1640]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1439]++;
int CodeCoverConditionCoverageHelper_C270; if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (8)) == 0 || true) &&
 ((this.locals.length == localsTop) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((this.stack.length == stackTop) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1641]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1440]++;
            boolean localsChanged = mergeState(this.locals, locals, localsTop,
                                               pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1441]++;
            boolean stackChanged = mergeState(this.stack, stack, stackTop,
                                              pool);
            return localsChanged || stackChanged;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1642]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1442]++;
int CodeCoverConditionCoverageHelper_C271;
            if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((ClassFileWriter.StackMapTable.DEBUGSTACKMAP) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1643]++;
                System.out.println("bad merge");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1443]++;
                System.out.println("current type state:");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1444]++;
                TypeInfo.print(this.locals, this.stack, pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1445]++;
                System.out.println("incoming type state:");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1446]++;
                TypeInfo.print(locals, localsTop, stack, stackTop, pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1447]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1644]++;}
            throw new IllegalArgumentException("bad merge attempt");
        }
}
    }

    /**
     * Merge an operand stack or local variable array with incoming state.
     *
     * They are treated the same way; by this point, it should already be
     * ensured that the array sizes are the same, which is the only additional
     * constraint that is imposed on merging operand stacks (the local variable
     * array is always the same size).
     */
    private boolean mergeState(int[] current, int[] incoming, int size,
                               ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1448]++;
        boolean changed = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1449]++;
byte CodeCoverTryBranchHelper_L45 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[133]++;


int CodeCoverConditionCoverageHelper_C272;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L45 == 0) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[133]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[134]++;
} else if (CodeCoverTryBranchHelper_L45 == 1) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[134]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[135]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1450]++;
            int currentType = current[i];

            current[i] = TypeInfo.merge(current[i], incoming[i], pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1451]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1452]++;
int CodeCoverConditionCoverageHelper_C273;
            if ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((currentType != current[i]) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1645]++;
                changed = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1453]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1646]++;}
        }
        return changed;
    }

    int getStart() {
        return start;
    }

    int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "sb " + index;
    }

    boolean isInitialized() {
        return isInitialized;
    }

    void setInitialized(boolean b) {
        isInitialized = b;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1454]++;
    }

    boolean isInQueue() {
        return isInQueue;
    }

    void setInQueue(boolean b) {
        isInQueue = b;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1455]++;
    }

    private int index;
    private int start;
    private int end;
    private int[] locals;
    private int[] stack;
    private boolean isInitialized;
    private boolean isInQueue;
}

/**
 * Helper class for internal representations of type information. In most
 * cases, type information can be represented by a constant, but in some
 * cases, a payload is included. Despite the payload coming after the type
 * tag in the output, we store it in bits 8-23 for uniformity; the tag is
 * always in bits 0-7.
 */
final class TypeInfo {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.ping();
  }

    private TypeInfo() { }

    static final int TOP = 0;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1456]++;
  }
    static final int INTEGER = 1;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1457]++;
  }
    static final int FLOAT = 2;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1458]++;
  }
    static final int DOUBLE = 3;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1459]++;
  }
    static final int LONG = 4;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1460]++;
  }
    static final int NULL = 5;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1461]++;
  }
    static final int UNINITIALIZED_THIS = 6;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1462]++;
  }
    static final int OBJECT_TAG = 7;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1463]++;
  }
    static final int UNINITIALIZED_VAR_TAG = 8;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1464]++;
  }

    static final int OBJECT(int constantPoolIndex) {
        return ((constantPoolIndex & 0xFFFF) << 8) | OBJECT_TAG;
    }

    static final int OBJECT(String type, ConstantPool pool) {
        return OBJECT(pool.addClass(type));
    }

    static final int UNINITIALIZED_VARIABLE(int bytecodeOffset) {
        return ((bytecodeOffset & 0xFFFF) << 8) | UNINITIALIZED_VAR_TAG;
    }

    static final int getTag(int typeInfo) {
        return typeInfo & 0xFF;
    }

    static final int getPayload(int typeInfo) {
        return typeInfo >>> 8;
    }

    /**
     * Treat the result of getPayload as a constant pool index and fetch the
     * corresponding String mapped to it.
     *
     * Only works on OBJECT types.
     */
    static final String getPayloadAsType(int typeInfo, ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1465]++;
int CodeCoverConditionCoverageHelper_C274;
        if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((getTag(typeInfo) == OBJECT_TAG) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1647]++;
            return (String) pool.getConstantData(getPayload(typeInfo));

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1648]++;}
        throw new IllegalArgumentException("expecting object type");
    }

    /**
     * Create type information from an internal type.
     */
    static final int fromType(String type, ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1466]++;
int CodeCoverConditionCoverageHelper_C275;
        if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((type.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1649]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1467]++;
            switch (type.charAt(0)) {
                case 'B':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1651]++; // sbyte
                case 'C':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1652]++; // unicode char
                case 'S':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1653]++; // short
                case 'Z':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1654]++; // boolean
                case 'I':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1655]++; // all of the above are verified as integers
                    return INTEGER;
                case 'D':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1656]++;
                    return DOUBLE;
                case 'F':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1657]++;
                    return FLOAT;
                case 'J':
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1658]++;
                    return LONG;
                default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1659]++;
                    throw new IllegalArgumentException("bad type");
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1650]++;}
        return TypeInfo.OBJECT(type, pool);
    }

    static boolean isTwoWords(int type) {
        return type == DOUBLE || type == LONG;
    }

    /**
     * Merge two verification types.
     *
     * In most cases, the verification types must be the same. For example,
     * INTEGER and DOUBLE cannot be merged and an exception will be thrown.
     * The basic rules are:
     *
     * - If the types are equal, simply return one.
     * - If either type is TOP, return TOP.
     * - If either type is NULL, return the other type.
     * - If both types are objects, find the lowest common ancestor in the
     *   class hierarchy.
     *
     * This method uses reflection to traverse the class hierarchy. Therefore,
     * it is assumed that the current class being generated is never the target
     * of a full object-object merge, which would need to load the current
     * class reflectively.
     */
    static int merge(int current, int incoming, ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1468]++;
        int currentTag = getTag(current);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1469]++;
        int incomingTag = getTag(incoming);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1470]++;
        boolean currentIsObject = currentTag == TypeInfo.OBJECT_TAG;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1471]++;
        boolean incomingIsObject = incomingTag == TypeInfo.OBJECT_TAG;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1472]++;
int CodeCoverConditionCoverageHelper_C276;

        if ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (32)) == 0 || true) &&
 ((current == incoming) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C276 |= (8)) == 0 || true) &&
 ((currentIsObject) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((incoming == NULL) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 3) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1660]++;
            return current;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1661]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1473]++;
int CodeCoverConditionCoverageHelper_C277; if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (8)) == 0 || true) &&
 ((currentTag == TypeInfo.TOP) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((incomingTag == TypeInfo.TOP) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1662]++;
            return TypeInfo.TOP;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1663]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1474]++;
int CodeCoverConditionCoverageHelper_C278; if ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (8)) == 0 || true) &&
 ((current == NULL) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((incomingIsObject) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1664]++;
            return incoming;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1665]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1475]++;
int CodeCoverConditionCoverageHelper_C279; if ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (8)) == 0 || true) &&
 ((currentIsObject) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((incomingIsObject) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1666]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1476]++;
            String currentName = getPayloadAsType(current, pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1477]++;
            String incomingName = getPayloadAsType(incoming, pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1478]++;
            // The class file always has the class and super names in the same
            // spot. The constant order is: class_data, class_name, super_data,
            // super_name.
            String currentlyGeneratedName = (String) pool.getConstantData(2);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1479]++;
            String currentlyGeneratedSuperName =
                    (String) pool.getConstantData(4);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1480]++;
int CodeCoverConditionCoverageHelper_C280;

            // If any of the merged types are the class that's currently being
            // generated, automatically start at the super class instead. At
            // this point, we already know the classes are different, so we
            // don't need to handle that case.
            if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((currentName.equals(currentlyGeneratedName)) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1668]++;
                currentName = currentlyGeneratedSuperName;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1481]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1669]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1482]++;
int CodeCoverConditionCoverageHelper_C281;
            if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((incomingName.equals(currentlyGeneratedName)) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1670]++;
                incomingName = currentlyGeneratedSuperName;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1483]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1671]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1484]++;

            Class<?> currentClass = getClassFromInternalName(currentName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1485]++;
            Class<?> incomingClass = getClassFromInternalName(incomingName);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1486]++;
int CodeCoverConditionCoverageHelper_C282;

            if ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((currentClass.isAssignableFrom(incomingClass)) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1672]++;
                return current;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1673]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1487]++;
int CodeCoverConditionCoverageHelper_C283; if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((incomingClass.isAssignableFrom(currentClass)) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1674]++;
                return incoming;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1675]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1488]++;
int CodeCoverConditionCoverageHelper_C284; if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (8)) == 0 || true) &&
 ((incomingClass.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((currentClass.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1676]++;
                // For verification purposes, Sun specifies that interfaces are
                // subtypes of Object. Therefore, we know that the merge result
                // involving interfaces where one is not assignable to the
                // other results in Object.
                return OBJECT("java/lang/Object", pool);

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1677]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1489]++;
                Class<?> commonClass = incomingClass.getSuperclass();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1490]++;
byte CodeCoverTryBranchHelper_L46 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[136]++;


int CodeCoverConditionCoverageHelper_C285;
                while ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((commonClass != null) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) && false)) {
if (CodeCoverTryBranchHelper_L46 == 0) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[136]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[137]++;
} else if (CodeCoverTryBranchHelper_L46 == 1) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[137]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[138]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1491]++;
int CodeCoverConditionCoverageHelper_C286;
                    if ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((commonClass.isAssignableFrom(currentClass)) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1678]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1492]++;
                        String name = commonClass.getName();
                        name = ClassFileWriter.getSlashedForm(name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1493]++;
                        return OBJECT(name, pool);

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1679]++;}
                    commonClass = commonClass.getSuperclass();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1494]++;
                }
            }
}
}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1667]++;}
}
}
}
        throw new IllegalArgumentException("bad merge attempt between " +
                                           toString(current, pool) + " and " +
                                           toString(incoming, pool));
    }

    static String toString(int type, ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1495]++;
        int tag = getTag(type);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1496]++;
        switch (tag) {
            case TypeInfo.TOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1680]++;
                return "top";
            case TypeInfo.INTEGER:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1681]++;
                return "int";
            case TypeInfo.FLOAT:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1682]++;
                return "float";
            case TypeInfo.DOUBLE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1683]++;
                return "double";
            case TypeInfo.LONG:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1684]++;
                return "long";
            case TypeInfo.NULL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1685]++;
                return "null";
            case TypeInfo.UNINITIALIZED_THIS:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1686]++;
                return "uninitialized_this";
            default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1687]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1497]++;
int CodeCoverConditionCoverageHelper_C287;
                if ((((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((tag == TypeInfo.OBJECT_TAG) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1688]++;
                    return getPayloadAsType(type, pool);

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1689]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1498]++;
int CodeCoverConditionCoverageHelper_C288; if ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((tag == TypeInfo.UNINITIALIZED_VAR_TAG) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1690]++;
                    return "uninitialized";

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1691]++;
                    throw new IllegalArgumentException("bad type");
                }
}
        }
    }

    /**
     * Take an internal name and return a java.lang.Class instance that
     * represents it.
     *
     * For example, given "java/lang/Object", returns the equivalent of
     * Class.forName("java.lang.Object"), but also handles exceptions.
     */
    static Class getClassFromInternalName(String internalName) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1499]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            return Class.forName(internalName.replace('/', '.'));
        } catch (ClassNotFoundException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1693]++;
            throw new RuntimeException(e);
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1692]++;
}
  }
    }

    static String toString(int[] types, ConstantPool pool) {
        return toString(types, types.length, pool);
    }

    static String toString(int[] types, int typesTop, ConstantPool pool) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1500]++;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1501]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1502]++;
byte CodeCoverTryBranchHelper_L47 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[139]++;


int CodeCoverConditionCoverageHelper_C289;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C289 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C289 |= (2)) == 0 || true) &&
 ((i < typesTop) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L47 == 0) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[139]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[140]++;
} else if (CodeCoverTryBranchHelper_L47 == 1) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[140]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.loops[141]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1503]++;
int CodeCoverConditionCoverageHelper_C290;
            if ((((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1694]++;
                sb.append(", ");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1504]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.branches[1695]++;}
            sb.append(toString(types[i], pool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1505]++;
        }
        sb.append("]");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1506]++;
        return sb.toString();
    }

    static void print(int[] locals, int[] stack, ConstantPool pool) {
        print(locals, locals.length, stack, stack.length, pool);
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1507]++;
    }

    static void print(int[] locals, int localsTop, int[] stack, int stackTop,
                       ConstantPool pool) {
        System.out.print("locals: ");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1508]++;
        System.out.println(toString(locals, localsTop, pool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1509]++;
        System.out.print("stack: ");
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1510]++;
        System.out.println(toString(stack, stackTop, pool));
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1511]++;
        System.out.println();
CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t.statements[1512]++;
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t ());
  }
    public static long[] statements = new long[1513];
    public static long[] branches = new long[1696];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[291];
  static {
    final String SECTION_NAME = "org.mozilla.classfile.RHINO-SRC-ClassFileWriter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,2,1,1,1,1,1,2,2,2,3,1,2,1,1,1,1,2,1,2,2,3,1,2,2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1,0,1,1,1,2,1,1,1,2,2,1,1,2,1,2,2,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,3,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,2,1,1,1,1,1,3,2,2,2,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 290; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[142];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpt59aa1qy7kzbdrg2sc5rjd0s8hkn61t () {
    super("org.mozilla.classfile.RHINO-SRC-ClassFileWriter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1512; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 1695; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 290; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 141; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.classfile.RHINO-SRC-ClassFileWriter.java");
      for (int i = 1; i <= 1512; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 1695; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 290; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 47; i++) {
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

