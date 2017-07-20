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
 * ----------
 * Title.java
 * ----------
 * (C) Copyright 2000-2007, by David Berry and Contributors.
 *
 * Original Author:  David Berry;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu;
 *
 * Changes (from 21-Aug-2001)
 * --------------------------
 * 21-Aug-2001 : Added standard header (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 14-Nov-2001 : Package com.jrefinery.common.ui.* changed to 
 *               com.jrefinery.ui.* (DG);
 * 07-Feb-2002 : Changed blank space around title from Insets --> Spacer, to 
 *               allow for relative or absolute spacing (DG);
 * 25-Jun-2002 : Removed unnecessary imports (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 14-Oct-2002 : Changed the event listener storage structure (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 22-Sep-2003 : Spacer cannot be null. Added nullpointer checks for this (TM);
 * 08-Jan-2003 : Renamed AbstractTitle --> Title and moved to separate 
 *               package (DG);
 * 26-Oct-2004 : Refactored to implement Block interface, and removed redundant 
 *               constants (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 02-Feb-2005 : Changed Spacer --> RectangleInsets for padding (DG);
 * 03-May-2005 : Fixed problem in equals() method (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.event.EventListenerList;

import org.jfree.chart.block.AbstractBlock;
import org.jfree.chart.block.Block;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.event.TitleChangeListener;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.ObjectUtilities;

/**
 * The base class for all chart titles.  A chart can have multiple titles, 
 * appearing at the top, bottom, left or right of the chart.
 * <P>
 * Concrete implementations of this class will render text and images, and 
 * hence do the actual work of drawing titles.
 */
public abstract class Title extends AbstractBlock 
                            implements Block, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1swumw79m5ycehy9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6675162505277817221L;
  static {
    CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[1]++;
  }
    
    /** The default title position. */
    public static final RectangleEdge DEFAULT_POSITION = RectangleEdge.TOP;
  static {
    CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[2]++;
  }

    /** The default horizontal alignment. */
    public static final HorizontalAlignment 
            DEFAULT_HORIZONTAL_ALIGNMENT = HorizontalAlignment.CENTER;
  static {
    CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[3]++;
  }

    /** The default vertical alignment. */
    public static final VerticalAlignment 
            DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignment.CENTER;
  static {
    CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[4]++;
  }

    /** Default title padding. */
    public static final RectangleInsets DEFAULT_PADDING = new RectangleInsets(
            1, 1, 1, 1);
  static {
    CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[5]++;
  }

    /** The title position. */
    private RectangleEdge position;

    /** The horizontal alignment of the title content. */
    private HorizontalAlignment horizontalAlignment;

    /** The vertical alignment of the title content. */
    private VerticalAlignment verticalAlignment;

    /** Storage for registered change listeners. */
    private transient EventListenerList listenerList;

    /** 
     * A flag that can be used to temporarily disable the listener mechanism. 
     */
    private boolean notify;

    /**
     * Creates a new title, using default attributes where necessary.
     */
    protected Title() {
        this(Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[6]++;
    }

    /**
     * Creates a new title, using default attributes where necessary.
     *
     * @param position  the position of the title (<code>null</code> not 
     *                  permitted).
     * @param horizontalAlignment  the horizontal alignment of the title 
     *                             (<code>null</code> not permitted).
     * @param verticalAlignment  the vertical alignment of the title 
     *                           (<code>null</code> not permitted).
     */
    protected Title(RectangleEdge position, 
                    HorizontalAlignment horizontalAlignment, 
                    VerticalAlignment verticalAlignment) {

        this(position, horizontalAlignment, verticalAlignment,
                Title.DEFAULT_PADDING);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[7]++;

    }

    /**
     * Creates a new title.
     *
     * @param position  the position of the title (<code>null</code> not 
     *                  permitted).
     * @param horizontalAlignment  the horizontal alignment of the title (LEFT,
     *                             CENTER or RIGHT, <code>null</code> not 
     *                             permitted).
     * @param verticalAlignment  the vertical alignment of the title (TOP, 
     *                           MIDDLE or BOTTOM, <code>null</code> not 
     *                           permitted).
     * @param padding  the amount of space to leave around the outside of the 
     *                 title (<code>null</code> not permitted).
     */
    protected Title(RectangleEdge position,
                    HorizontalAlignment horizontalAlignment, 
                    VerticalAlignment verticalAlignment,
                    RectangleInsets padding) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;

        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[1]++;
            throw new IllegalArgumentException("Null 'position' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[2]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((horizontalAlignment == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'horizontalAlignment' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[4]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((verticalAlignment == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'verticalAlignment' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[6]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[7]++;
            throw new IllegalArgumentException("Null 'spacer' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[8]++;}

        this.position = position;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[12]++;
        this.horizontalAlignment = horizontalAlignment;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[13]++;
        this.verticalAlignment = verticalAlignment;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[14]++;
        setPadding(padding);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[15]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[16]++;
        this.notify = true;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[17]++;

    }

    /**
     * Returns the position of the title.
     *
     * @return The title position (never <code>null</code>).
     */
    public RectangleEdge getPosition() {
        return this.position;
    }

    /**
     * Sets the position for the title and sends a {@link TitleChangeEvent} to 
     * all registered listeners.
     *
     * @param position  the position (<code>null</code> not permitted).
     */
    public void setPosition(RectangleEdge position) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[9]++;
            throw new IllegalArgumentException("Null 'position' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[10]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.position != position) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[11]++;
            this.position = position;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[20]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[21]++;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[12]++;}
    }

    /**
     * Returns the horizontal alignment of the title.
     *
     * @return The horizontal alignment (never <code>null</code>).
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    /**
     * Sets the horizontal alignment for the title and sends a 
     * {@link TitleChangeEvent} to all registered listeners.
     *
     * @param alignment  the horizontal alignment (<code>null</code> not 
     *                   permitted).
     */
    public void setHorizontalAlignment(HorizontalAlignment alignment) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((alignment == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[13]++;
            throw new IllegalArgumentException("Null 'alignment' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[14]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment != alignment) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[15]++;
            this.horizontalAlignment = alignment;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[24]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[25]++;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[16]++;}
    }

    /**
     * Returns the vertical alignment of the title.
     *
     * @return The vertical alignment (never <code>null</code>).
     */
    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }

    /**
     * Sets the vertical alignment for the title, and notifies any registered
     * listeners of the change.
     *
     * @param alignment  the new vertical alignment (TOP, MIDDLE or BOTTOM, 
     *                   <code>null</code> not permitted).
     */
    public void setVerticalAlignment(VerticalAlignment alignment) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((alignment == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[17]++;
            throw new IllegalArgumentException("Null 'alignment' argument.");

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[18]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.verticalAlignment != alignment) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[19]++;
            this.verticalAlignment = alignment;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[28]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[29]++;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[20]++;}
    }

    /**
     * Returns the flag that indicates whether or not the notification 
     * mechanism is enabled.
     *
     * @return The flag.
     */
    public boolean getNotify() {
        return this.notify;
    }

    /**
     * Sets the flag that indicates whether or not the notification mechanism
     * is enabled.  There are certain situations (such as cloning) where you
     * want to turn notification off temporarily.
     *
     * @param flag  the new value of the flag.
     */
    public void setNotify(boolean flag) {
        this.notify = flag;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[30]++;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[21]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[32]++;
   
        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[22]++;}
    }

    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area allocated for the title (subclasses should not
     *              draw outside this area).
     */
    public abstract void draw(Graphics2D g2, Rectangle2D area);

    /**
     * Returns a clone of the title.
     * <P>
     * One situation when this is useful is when editing the title properties -
     * you can edit a clone, and then it is easier to cancel the changes if
     * necessary.
     *
     * @return A clone of the title.
     *
     * @throws CloneNotSupportedException not thrown by this class, but it may 
     *         be thrown by subclasses.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[33]++;

        Title duplicate = (Title) super.clone();
        duplicate.listenerList = new EventListenerList();
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[34]++;
        // RectangleInsets is immutable => same reference in clone OK
        return duplicate;
    }

    /**
     * Registers an object for notification of changes to the title.
     *
     * @param listener  the object that is being registered.
     */
    public void addChangeListener(TitleChangeListener listener) {
        this.listenerList.add(TitleChangeListener.class, listener);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[35]++;
    }

    /**
     * Unregisters an object for notification of changes to the chart title.
     *
     * @param listener  the object that is being unregistered.
     */
    public void removeChangeListener(TitleChangeListener listener) {
        this.listenerList.remove(TitleChangeListener.class, listener);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[36]++;
    }

    /**
     * Notifies all registered listeners that the chart title has changed in 
     * some way.
     *
     * @param event  an object that contains information about the change to 
     *               the title.
     */
    protected void notifyListeners(TitleChangeEvent event) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.notify) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[23]++;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[38]++;
            Object[] listeners = this.listenerList.getListenerList();
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[39]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1swumw79m5ycehy9.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
            for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1swumw79m5ycehy9.loops[1]--;
  CodeCoverCoverageCounter$1swumw79m5ycehy9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1swumw79m5ycehy9.loops[2]--;
  CodeCoverCoverageCounter$1swumw79m5ycehy9.loops[3]++;
}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[40]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((listeners[i] == TitleChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[25]++;
                    ((TitleChangeListener) listeners[i + 1]).titleChanged(
                            event);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[41]++;

                } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[26]++;}
            }

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[24]++;}
    }

    /**
     * Tests an object for equality with this title.
     *
     * @param obj  the object (<code>null</code> not permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[28]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof Title) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[30]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[44]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[31]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[32]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[45]++;
        Title that = (Title) obj;
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[46]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.position != that.position) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[34]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[47]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment != that.horizontalAlignment) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[36]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[48]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.verticalAlignment != that.verticalAlignment) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[38]++;}
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[49]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.notify != that.notify) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1swumw79m5ycehy9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1swumw79m5ycehy9.branches[40]++;}
        return true;
    }

    /**
     * Returns a hashcode for the title.
     * 
     * @return The hashcode.
     */
    public int hashCode() {
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[50]++;
        int result = 193;
        result = 37 * result + ObjectUtilities.hashCode(this.position);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[51]++;    
        result = 37 * result 
                + ObjectUtilities.hashCode(this.horizontalAlignment);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[52]++;    
        result = 37 * result + ObjectUtilities.hashCode(this.verticalAlignment);
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[53]++;
        return result;
    }
        
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[54]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[55]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$1swumw79m5ycehy9.statements[56]++;
    }

}

class CodeCoverCoverageCounter$1swumw79m5ycehy9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1swumw79m5ycehy9 ());
  }
    public static long[] statements = new long[57];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.Title.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$1swumw79m5ycehy9 () {
    super("org.jfree.chart.title.Title.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 56; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.Title.java");
      for (int i = 1; i <= 56; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 40; i++) {
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

