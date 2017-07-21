/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.convert;

import org.joda.time.JodaTimePermission;

/**
 * ConverterManager controls the date and time converters.
 * <p>
 * This class enables additional conversion classes to be added via
 * {@link #addInstantConverter(InstantConverter)}, which may replace an
 * existing converter. Similar methods exist for duration, time period and
 * interval converters.
 * <p>
 * This class is threadsafe, so adding/removing converters can be done at any
 * time. Updating the set of convertors is relatively expensive, and so should
 * not be performed often.
 * <p>
 * The default instant converters are:
 * <ul>
 * <li>ReadableInstant
 * <li>String
 * <li>Calendar
 * <li>Date (includes sql package subclasses)
 * <li>Long (milliseconds)
 * <li>null (now)
 * </ul>
 * 
 * The default partial converters are:
 * <ul>
 * <li>ReadablePartial
 * <li>ReadableInstant
 * <li>String
 * <li>Calendar
 * <li>Date (includes sql package subclasses)
 * <li>Long (milliseconds)
 * <li>null (now)
 * </ul>
 * 
 * The default duration converters are:
 * <ul>
 * <li>ReadableDuration
 * <li>ReadableInterval
 * <li>String
 * <li>Long (milliseconds)
 * <li>null (zero ms)
 * </ul>
 *
 * The default time period converters are:
 * <ul>
 * <li>ReadablePeriod
 * <li>ReadableInterval
 * <li>String
 * <li>null (zero)
 * </ul>
 * 
 * The default interval converters are:
 * <ul>
 * <li>ReadableInterval
 * <li>String
 * <li>null (zero-length from now to now)
 * </ul>
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class ConverterManager {
  static {
    CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.ping();
  }


    /**
     * Singleton instance, lazily loaded to avoid class loading.
     */
    private static ConverterManager INSTANCE;

    public static ConverterManager getInstance() {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((INSTANCE == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[1]++;
            INSTANCE = new ConverterManager();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[2]++;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[2]++;}
        return INSTANCE;
    }
    
    private ConverterSet iInstantConverters;
    private ConverterSet iPartialConverters;
    private ConverterSet iDurationConverters;
    private ConverterSet iPeriodConverters;
    private ConverterSet iIntervalConverters;
    
    /**
     * Restricted constructor.
     */
    protected ConverterManager() {
        super();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[3]++;

        iInstantConverters = new ConverterSet(new Converter[] {
            ReadableInstantConverter.INSTANCE,
            StringConverter.INSTANCE,
            CalendarConverter.INSTANCE,
            DateConverter.INSTANCE,
            LongConverter.INSTANCE,
            NullConverter.INSTANCE,
        });
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[4]++;

        iPartialConverters = new ConverterSet(new Converter[] {
            ReadablePartialConverter.INSTANCE,
            ReadableInstantConverter.INSTANCE,
            StringConverter.INSTANCE,
            CalendarConverter.INSTANCE,
            DateConverter.INSTANCE,
            LongConverter.INSTANCE,
            NullConverter.INSTANCE,
        });
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[5]++;

        iDurationConverters = new ConverterSet(new Converter[] {
            ReadableDurationConverter.INSTANCE,
            ReadableIntervalConverter.INSTANCE,
            StringConverter.INSTANCE,
            LongConverter.INSTANCE,
            NullConverter.INSTANCE,
        });
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[6]++;

        iPeriodConverters = new ConverterSet(new Converter[] {
            ReadableDurationConverter.INSTANCE,
            ReadablePeriodConverter.INSTANCE,
            ReadableIntervalConverter.INSTANCE,
            StringConverter.INSTANCE,
            NullConverter.INSTANCE,
        });
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[7]++;

        iIntervalConverters = new ConverterSet(new Converter[] {
            ReadableIntervalConverter.INSTANCE,
            StringConverter.INSTANCE,
            NullConverter.INSTANCE,
        });
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[8]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the best converter for the object specified.
     * 
     * @param object  the object to convert
     * @return the converter to use
     * @throws IllegalArgumentException if no suitable converter
     * @throws IllegalStateException if multiple converters match the type
     * equally well
     */
    public InstantConverter getInstantConverter(Object object) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[9]++;
        InstantConverter converter =
            (InstantConverter)iInstantConverters.select(object == null ? null : object.getClass());
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((converter != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[3]++;
            return converter;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[4]++;}
        throw new IllegalArgumentException("No instant converter found for type: " +
            (object == null ? "null" : object.getClass().getName()));
    }
    
    //-----------------------------------------------------------------------
    /**
     * Gets a copy of the set of converters.
     * 
     * @return the converters, a copy of the real data, never null
     */
    public InstantConverter[] getInstantConverters() {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[11]++;
        ConverterSet set = iInstantConverters;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[12]++;
        InstantConverter[] converters = new InstantConverter[set.size()];
        set.copyInto(converters);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[13]++;
        return converters;
    }
    
    /**
     * Adds a converter to the set of converters. If a matching converter is
     * already in the set, the given converter replaces it. If the converter is
     * exactly the same as one already in the set, no changes are made.
     * <p>
     * The order in which converters are added is not relevent. The best
     * converter is selected by examining the object hierarchy.
     * 
     * @param converter  the converter to add, null ignored
     * @return replaced converter, or null
     */
    public InstantConverter addInstantConverter(InstantConverter converter)
            throws SecurityException {
        
        checkAlterInstantConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[14]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[5]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[6]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[16]++;
        InstantConverter[] removed = new InstantConverter[1];
        iInstantConverters = iInstantConverters.add(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[17]++;
        return removed[0];
    }
    
    /**
     * Removes a converter from the set of converters. If the converter was
     * not in the set, no changes are made.
     * 
     * @param converter  the converter to remove, null ignored
     * @return replaced converter, or null
     */
    public InstantConverter removeInstantConverter(InstantConverter converter)
            throws SecurityException {
        
        checkAlterInstantConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[18]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[7]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[8]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[20]++;
        InstantConverter[] removed = new InstantConverter[1];
        iInstantConverters = iInstantConverters.remove(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[21]++;
        return removed[0];
    }
    
    /**
     * Checks whether the user has permission 'ConverterManager.alterInstantConverters'.
     * 
     * @throws SecurityException if the user does not have the permission
     */
    private void checkAlterInstantConverters() throws SecurityException {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[22]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[9]++;
            sm.checkPermission(new JodaTimePermission("ConverterManager.alterInstantConverters"));
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[24]++;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[10]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the best converter for the object specified.
     * 
     * @param object  the object to convert
     * @return the converter to use
     * @throws IllegalArgumentException if no suitable converter
     * @throws IllegalStateException if multiple converters match the type
     * equally well
     */
    public PartialConverter getPartialConverter(Object object) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[25]++;
        PartialConverter converter =
            (PartialConverter)iPartialConverters.select(object == null ? null : object.getClass());
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((converter != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[11]++;
            return converter;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[12]++;}
        throw new IllegalArgumentException("No partial converter found for type: " +
            (object == null ? "null" : object.getClass().getName()));
    }
    
    //-----------------------------------------------------------------------
    /**
     * Gets a copy of the set of converters.
     * 
     * @return the converters, a copy of the real data, never null
     */
    public PartialConverter[] getPartialConverters() {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[27]++;
        ConverterSet set = iPartialConverters;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[28]++;
        PartialConverter[] converters = new PartialConverter[set.size()];
        set.copyInto(converters);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[29]++;
        return converters;
    }
    
    /**
     * Adds a converter to the set of converters. If a matching converter is
     * already in the set, the given converter replaces it. If the converter is
     * exactly the same as one already in the set, no changes are made.
     * <p>
     * The order in which converters are added is not relevent. The best
     * converter is selected by examining the object hierarchy.
     * 
     * @param converter  the converter to add, null ignored
     * @return replaced converter, or null
     */
    public PartialConverter addPartialConverter(PartialConverter converter)
            throws SecurityException {
        
        checkAlterPartialConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[30]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[13]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[14]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[32]++;
        PartialConverter[] removed = new PartialConverter[1];
        iPartialConverters = iPartialConverters.add(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[33]++;
        return removed[0];
    }
    
    /**
     * Removes a converter from the set of converters. If the converter was
     * not in the set, no changes are made.
     * 
     * @param converter  the converter to remove, null ignored
     * @return replaced converter, or null
     */
    public PartialConverter removePartialConverter(PartialConverter converter)
            throws SecurityException {
        
        checkAlterPartialConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[34]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[15]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[16]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[36]++;
        PartialConverter[] removed = new PartialConverter[1];
        iPartialConverters = iPartialConverters.remove(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[37]++;
        return removed[0];
    }
    
    /**
     * Checks whether the user has permission 'ConverterManager.alterPartialConverters'.
     * 
     * @throws SecurityException if the user does not have the permission
     */
    private void checkAlterPartialConverters() throws SecurityException {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[38]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[17]++;
            sm.checkPermission(new JodaTimePermission("ConverterManager.alterPartialConverters"));
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[40]++;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[18]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the best converter for the object specified.
     * 
     * @param object  the object to convert
     * @return the converter to use
     * @throws IllegalArgumentException if no suitable converter
     * @throws IllegalStateException if multiple converters match the type
     * equally well
     */
    public DurationConverter getDurationConverter(Object object) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[41]++;
        DurationConverter converter =
            (DurationConverter)iDurationConverters.select(object == null ? null : object.getClass());
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((converter != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[19]++;
            return converter;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[20]++;}
        throw new IllegalArgumentException("No duration converter found for type: " +
            (object == null ? "null" : object.getClass().getName()));
    }
    
    //-----------------------------------------------------------------------
    /**
     * Gets a copy of the list of converters.
     * 
     * @return the converters, a copy of the real data, never null
     */
    public DurationConverter[] getDurationConverters() {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[43]++;
        ConverterSet set = iDurationConverters;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[44]++;
        DurationConverter[] converters = new DurationConverter[set.size()];
        set.copyInto(converters);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[45]++;
        return converters;
    }
    
    /**
     * Adds a converter to the set of converters. If a matching converter is
     * already in the set, the given converter replaces it. If the converter is
     * exactly the same as one already in the set, no changes are made.
     * <p>
     * The order in which converters are added is not relevent. The best
     * converter is selected by examining the object hierarchy.
     * 
     * @param converter  the converter to add, null ignored
     * @return replaced converter, or null
     */
    public DurationConverter addDurationConverter(DurationConverter converter)
            throws SecurityException {
        
        checkAlterDurationConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[46]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[21]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[22]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[48]++;
        DurationConverter[] removed = new DurationConverter[1];
        iDurationConverters = iDurationConverters.add(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[49]++;
        return removed[0];
    }
    
    /**
     * Removes a converter from the set of converters. If the converter was
     * not in the set, no changes are made.
     * 
     * @param converter  the converter to remove, null ignored
     * @return replaced converter, or null
     */
    public DurationConverter removeDurationConverter(DurationConverter converter)
            throws SecurityException {
        
        checkAlterDurationConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[50]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[51]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[23]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[24]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[52]++;
        DurationConverter[] removed = new DurationConverter[1];
        iDurationConverters = iDurationConverters.remove(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[53]++;
        return removed[0];
    }
    
    /**
     * Checks whether the user has permission 'ConverterManager.alterDurationConverters'.
     * 
     * @throws SecurityException if the user does not have the permission
     */
    private void checkAlterDurationConverters() throws SecurityException {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[54]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[25]++;
            sm.checkPermission(new JodaTimePermission("ConverterManager.alterDurationConverters"));
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[56]++;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[26]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the best converter for the object specified.
     * 
     * @param object  the object to convert
     * @return the converter to use
     * @throws IllegalArgumentException if no suitable converter
     * @throws IllegalStateException if multiple converters match the type
     * equally well
     */
    public PeriodConverter getPeriodConverter(Object object) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[57]++;
        PeriodConverter converter =
            (PeriodConverter)iPeriodConverters.select(object == null ? null : object.getClass());
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[58]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((converter != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[27]++;
            return converter;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[28]++;}
        throw new IllegalArgumentException("No period converter found for type: " +
            (object == null ? "null" : object.getClass().getName()));
    }
    
    //-----------------------------------------------------------------------
    /**
     * Gets a copy of the list of converters.
     * 
     * @return the converters, a copy of the real data, never null
     */
    public PeriodConverter[] getPeriodConverters() {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[59]++;
        ConverterSet set = iPeriodConverters;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[60]++;
        PeriodConverter[] converters = new PeriodConverter[set.size()];
        set.copyInto(converters);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[61]++;
        return converters;
    }
    
    /**
     * Adds a converter to the set of converters. If a matching converter is
     * already in the set, the given converter replaces it. If the converter is
     * exactly the same as one already in the set, no changes are made.
     * <p>
     * The order in which converters are added is not relevent. The best
     * converter is selected by examining the object hierarchy.
     * 
     * @param converter  the converter to add, null ignored
     * @return replaced converter, or null
     */
    public PeriodConverter addPeriodConverter(PeriodConverter converter)
            throws SecurityException {
        
        checkAlterPeriodConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[62]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[29]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[30]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[64]++;
        PeriodConverter[] removed = new PeriodConverter[1];
        iPeriodConverters = iPeriodConverters.add(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[65]++;
        return removed[0];
    }
    
    /**
     * Removes a converter from the set of converters. If the converter was
     * not in the set, no changes are made.
     * 
     * @param converter  the converter to remove, null ignored
     * @return replaced converter, or null
     */
    public PeriodConverter removePeriodConverter(PeriodConverter converter)
            throws SecurityException {
        
        checkAlterPeriodConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[66]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[31]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[32]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[68]++;
        PeriodConverter[] removed = new PeriodConverter[1];
        iPeriodConverters = iPeriodConverters.remove(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[69]++;
        return removed[0];
    }
    
    /**
     * Checks whether the user has permission 'ConverterManager.alterPeriodConverters'.
     * 
     * @throws SecurityException if the user does not have the permission
     */
    private void checkAlterPeriodConverters() throws SecurityException {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[70]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[71]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[33]++;
            sm.checkPermission(new JodaTimePermission("ConverterManager.alterPeriodConverters"));
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[72]++;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[34]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the best converter for the object specified.
     * 
     * @param object  the object to convert
     * @return the converter to use
     * @throws IllegalArgumentException if no suitable converter
     * @throws IllegalStateException if multiple converters match the type
     * equally well
     */
    public IntervalConverter getIntervalConverter(Object object) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[73]++;
        IntervalConverter converter =
            (IntervalConverter)iIntervalConverters.select(object == null ? null : object.getClass());
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((converter != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[35]++;
            return converter;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[36]++;}
        throw new IllegalArgumentException("No interval converter found for type: " +
            (object == null ? "null" : object.getClass().getName()));
    }
    
    //-----------------------------------------------------------------------
    /**
     * Gets a copy of the list of converters.
     * 
     * @return the converters, a copy of the real data, never null
     */
    public IntervalConverter[] getIntervalConverters() {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[75]++;
        ConverterSet set = iIntervalConverters;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[76]++;
        IntervalConverter[] converters = new IntervalConverter[set.size()];
        set.copyInto(converters);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[77]++;
        return converters;
    }
    
    /**
     * Adds a converter to the set of converters. If a matching converter is
     * already in the set, the given converter replaces it. If the converter is
     * exactly the same as one already in the set, no changes are made.
     * <p>
     * The order in which converters are added is not relevent. The best
     * converter is selected by examining the object hierarchy.
     * 
     * @param converter  the converter to add, null ignored
     * @return replaced converter, or null
     */
    public IntervalConverter addIntervalConverter(IntervalConverter converter) 
            throws SecurityException {
        
        checkAlterIntervalConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[78]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[37]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[38]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[80]++;
        IntervalConverter[] removed = new IntervalConverter[1];
        iIntervalConverters = iIntervalConverters.add(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[81]++;
        return removed[0];
    }
    
    /**
     * Removes a converter from the set of converters. If the converter was
     * not in the set, no changes are made.
     * 
     * @param converter  the converter to remove, null ignored
     * @return replaced converter, or null
     */
    public IntervalConverter removeIntervalConverter(IntervalConverter converter)
            throws SecurityException {
        
        checkAlterIntervalConverters();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[82]++;
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[83]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((converter == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[39]++;
            return null;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[40]++;}
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[84]++;
        IntervalConverter[] removed = new IntervalConverter[1];
        iIntervalConverters = iIntervalConverters.remove(converter, removed);
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[85]++;
        return removed[0];
    }
    
    /**
     * Checks whether the user has permission 'ConverterManager.alterIntervalConverters'.
     * 
     * @throws SecurityException if the user does not have the permission
     */
    private void checkAlterIntervalConverters() throws SecurityException {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[86]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[41]++;
            sm.checkPermission(new JodaTimePermission("ConverterManager.alterIntervalConverters"));
CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.statements[88]++;

        } else {
  CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9.branches[42]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a debug representation of the object.
     */
    public String toString() {
        return "ConverterManager[" +
            iInstantConverters.size() + " instant," +
            iPartialConverters.size() + " partial," +
            iDurationConverters.size() + " duration," +
            iPeriodConverters.size() + " period," +
            iIntervalConverters.size() + " interval]";
    }

}

class CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9 ());
  }
    public static long[] statements = new long[89];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.joda.time.convert.ConverterManager.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$1k0k7jbn48d4nbzxwpiamiwsir0sa6au9 () {
    super("org.joda.time.convert.ConverterManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 88; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.convert.ConverterManager.java");
      for (int i = 1; i <= 88; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

