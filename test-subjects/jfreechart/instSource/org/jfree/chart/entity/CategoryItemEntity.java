/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -----------------------
 * CategoryItemEntity.java
 * -----------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added Javadoc comments (DG);
 * 26-Jun-2002 : Added getImageMapAreaTag() method (DG);
 * 05-Aug-2002 : Added new constructor to populate URLText
 *               Moved getImageMapAreaTag() to ChartEntity (superclass) (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Jul-2003 : Added CategoryDataset reference (CZ);
 * 20-May-2004 : Added equals() and clone() methods, and implemented 
 *               Serializable (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-May-2007 : Updated to use row and column keys to identify item (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

import org.jfree.data.category.CategoryDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A chart entity that represents one item within a category plot.
 */
public class CategoryItemEntity extends ChartEntity 
                                implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8657249457902337349L;
  static {
    CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[1]++;
  }
    
    /** The dataset. */
    private CategoryDataset dataset;
    
    /** 
     * The series (zero-based index). 
     * 
     * @deprecated As of 1.0.6, this field is redundant as you can derive the
     *         index from the <code>rowKey</code> field.
     */
    private int series;
    
    /** 
     * The category.
     * 
     * @deprecated As of 1.0.6, this field is deprecated in favour of the
     *         <code>columnKey</code> field.
     */
    private Object category;

    /** 
     * The category index. 
     * 
     * @deprecated As of 1.0.6, this field is redundant as you can derive the
     *         index from the <code>columnKey</code> field.
     */
    private int categoryIndex;

    /**
     * The row key.
     * 
     * @since 1.0.6
     */
    private Comparable rowKey;
    
    /**
     * The column key.
     * 
     * @since 1.0.6
     */
    private Comparable columnKey;

    /**
     * Creates a new category item entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text for HTML image maps.
     * @param dataset  the dataset.
     * @param series  the series (zero-based index).
     * @param category  the category.
     * @param categoryIndex  the category index.
     * 
     * @deprecated As of 1.0.6, use {@link #CategoryItemEntity(Shape, String, 
     *         String, CategoryDataset, Comparable, Comparable)}.
     */
    public CategoryItemEntity(Shape area, String toolTipText, String urlText,
                              CategoryDataset dataset,
                              int series, Object category, int categoryIndex) {

        super(area, toolTipText, urlText);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[2]++;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[1]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[2]++;}
        this.dataset = dataset;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[4]++;
        this.series = series;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[5]++;
        this.category = category;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[6]++;
        this.categoryIndex = categoryIndex;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[7]++;
        this.rowKey = dataset.getRowKey(series);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[8]++;
        this.columnKey = dataset.getColumnKey(categoryIndex);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[9]++;
    }
    
    /**
     * Creates a new entity instance for an item in the specified dataset.
     * 
     * @param area  the 'hotspot' area (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text.
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @since 1.0.6
     */
    public CategoryItemEntity(Shape area, String toolTipText, String urlText,
            CategoryDataset dataset, Comparable rowKey, Comparable columnKey) {
        super(area, toolTipText, urlText);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[10]++;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[3]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[4]++;}
        this.dataset = dataset;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[12]++;
        this.rowKey = rowKey;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[13]++;
        this.columnKey = columnKey;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[14]++;
        
        // populate the deprecated fields
        this.series = dataset.getRowIndex(rowKey);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[15]++;
        this.category = columnKey;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[16]++;
        this.categoryIndex = dataset.getColumnIndex(columnKey);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[17]++;
    }

    /**
     * Returns the dataset this entity refers to.  This can be used to 
     * differentiate between items in a chart that displays more than one
     * dataset.
     *
     * @return The dataset (never <code>null</code>).
     * 
     * @see #setDataset(CategoryDataset)
     */
    public CategoryDataset getDataset() {
        return this.dataset; 
    }

    /**
     * Sets the dataset this entity refers to.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(CategoryDataset dataset) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[5]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[6]++;}
        this.dataset = dataset;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[19]++;
    }
    
    /**
     * Returns the row key.
     * 
     * @return The row key (never <code>null</code>).
     * 
     * @since 1.0.6
     * 
     * @see #setRowKey(Comparable)
     */
    public Comparable getRowKey() {
        return this.rowKey;
    }
    
    /**
     * Sets the row key.
     * 
     * @param rowKey  the row key (<code>null</code> not permitted).
     * 
     * @since 1.0.6
     * 
     * @see #getRowKey()
     */
    public void setRowKey(Comparable rowKey) {
        this.rowKey = rowKey;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[20]++;
        // update the deprecated field
        this.series = this.dataset.getRowIndex(rowKey);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[21]++;
    }

    /**
     * Returns the column key.
     * 
     * @return The column key (never <code>null</code>).
     * 
     * @since 1.0.6
     * 
     * @see #setColumnKey(Comparable)
     */
    public Comparable getColumnKey() {
        return this.columnKey;
    }
    
    /**
     * Sets the column key.
     * 
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @since 1.0.6
     * 
     * @see #getColumnKey()
     */
    public void setColumnKey(Comparable columnKey) {
        this.columnKey = columnKey;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[22]++;
        // update the deprecated fields
        this.category = columnKey;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[23]++;
        this.categoryIndex = this.dataset.getColumnIndex(columnKey);
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[24]++;
    }

    /**
     * Returns the series index.
     *
     * @return The series index.
     * 
     * @see #setSeries(int)
     * 
     * @deprecated As of 1.0.6, you can derive this information from the 
     *         {@link #getRowKey()} method.
     */
    public int getSeries() {
        return this.series;
    }

    /**
     * Sets the series index.
     *
     * @param series  the series index (zero-based).
     * 
     * @see #getSeries()
     * 
     * @deprecated As of 1.0.6, you should use {@link #setRowKey(Comparable)} 
     *         to designate the series.
     */
    public void setSeries(int series) {
        this.series = series;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[25]++;
    }

    /**
     * Returns the category.
     *
     * @return The category (possibly <code>null</code>).
     * 
     * @see #setCategory(Object)
     * 
     * @deprecated The return type for this method should be 
     *         <code>Comparable</code>, so it has been deprecated as of 
     *         version 1.0.6 and replaced by {@link #getColumnKey()}.
     */
    public Object getCategory() {
        return this.category;
    }

    /**
     * Sets the category.
     *
     * @param category  the category (<code>null</code> permitted).
     * 
     * @see #getCategory()
     * 
     * @deprecated As of version 1.0.6, use {@link #setColumnKey(Comparable)}.
     */
    public void setCategory(Object category) {
        this.category = category;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[26]++;
    }

    /**
     * Returns the category index.
     *
     * @return The index.
     * 
     * @see #setCategoryIndex(int)
     * 
     * @deprecated As of 1.0.6, you can derive this information from the 
     *         {@link #getColumnKey()} method.
     */
    public int getCategoryIndex() {
        return this.categoryIndex;
    }

    /**
     * Sets the category index.
     *
     * @param index  the category index.
     * 
     * @see #getCategoryIndex()
     * 
     * @deprecated As of 1.0.6, use {@link #setColumnKey(Comparable)} to 
     *         designate the category.
     */
    public void setCategoryIndex(int index) {
        this.categoryIndex = index;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[27]++;
    }

    /**
     * Returns a string representing this object (useful for debugging 
     * purposes).
     *
     * @return A string (never <code>null</code>).
     */
    public String toString() {
        return "CategoryItemEntity: rowKey=" + this.rowKey 
               + ", columnKey=" + this.columnKey + ", dataset=" + this.dataset;
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[7]++;
            return true;
      
        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[8]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryItemEntity) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[10]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[30]++;
        CategoryItemEntity that = (CategoryItemEntity) obj;
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.rowKey.equals(that.rowKey)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[12]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.columnKey.equals(that.columnKey)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[14]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.dataset, that.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[16]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        
        // check the deprecated fields
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.categoryIndex != that.categoryIndex) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[17]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[18]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.series != that.series) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[19]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[20]++;}
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.category, that.category)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[21]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35.branches[22]++;}
        return super.equals(obj);
    }

}

class CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35 ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.CategoryItemEntity.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$26mapsqpx9i1i5oa25grtlaw50f5nicazz35 () {
    super("org.jfree.chart.entity.CategoryItemEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.entity.CategoryItemEntity.java");
      for (int i = 1; i <= 36; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
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

