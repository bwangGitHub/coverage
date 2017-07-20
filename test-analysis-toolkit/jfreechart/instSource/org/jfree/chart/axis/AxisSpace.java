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
 * --------------
 * AxisSpace.java
 * --------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Jul-2003 : Version 1 (DG);
 * 14-Aug-2003 : Implemented Cloneable (DG);
 * 18-Aug-2003 : Implemented Serializable (DG);
 * 17-Mar-2004 : Added a toString() method for debugging (DG);
 * 07-Jan-2005 : Updated equals() method (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * 
 */
 
package org.jfree.chart.axis;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A record that contains the space required at each edge of a plot.
 */
public class AxisSpace implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -2490732595134766305L;
  static {
    CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[1]++;
  }
    
    /** The top space. */
    private double top;
    
    /** The bottom space. */
    private double bottom;
    
    /** The left space. */
    private double left;
    
    /** The right space. */
    private double right;
    
    /**
     * Creates a new axis space record.
     */
    public AxisSpace() {
        this.top = 0.0;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[2]++;
        this.bottom = 0.0;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[3]++;
        this.left = 0.0;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[4]++;
        this.right = 0.0;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[5]++;
    }

    /**
     * Returns the space reserved for axes at the top of the plot area.
     * 
     * @return The space (in Java2D units).
     */
    public double getTop() {
        return this.top;
    }
    
    /**
     * Sets the space reserved for axes at the top of the plot area. 
     * 
     * @param space  the space (in Java2D units).
     */
    public void setTop(double space) {
        this.top = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[6]++;
    }

    /**
     * Returns the space reserved for axes at the bottom of the plot area.
     * 
     * @return The space (in Java2D units).
     */
    public double getBottom() {
        return this.bottom;
    }
    
    /**
     * Sets the space reserved for axes at the bottom of the plot area. 
     * 
     * @param space  the space (in Java2D units).
     */
    public void setBottom(double space) {
        this.bottom = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[7]++;
    }

    /**
     * Returns the space reserved for axes at the left of the plot area.
     * 
     * @return The space (in Java2D units).
     */
    public double getLeft() {
        return this.left;
    }
    
    /**
     * Sets the space reserved for axes at the left of the plot area. 
     * 
     * @param space  the space (in Java2D units).
     */
    public void setLeft(double space) {
        this.left = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[8]++;
    }

    /**
     * Returns the space reserved for axes at the right of the plot area.
     * 
     * @return The space (in Java2D units).
     */
    public double getRight() {
        return this.right;
    }
    
    /**
     * Sets the space reserved for axes at the right of the plot area. 
     * 
     * @param space  the space (in Java2D units).
     */
    public void setRight(double space) {
        this.right = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[9]++;
    }

    /**
     * Adds space to the top, bottom, left or right edge of the plot area.
     * 
     * @param space  the space (in Java2D units).
     * @param edge  the edge (<code>null</code> not permitted).
     */
    public void add(double space, RectangleEdge edge) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((edge == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[1]++;
            throw new IllegalArgumentException("Null 'edge' argument.");

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[2]++;}
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[3]++;     
            this.top += space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[12]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[4]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[13]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[5]++;
            this.bottom += space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[14]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[6]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[15]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[7]++;
            this.left += space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[16]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[8]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[17]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[9]++;
            this.right += space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[18]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[10]++;
            throw new IllegalStateException("Unrecognised 'edge' argument.");
        }
}
}
}
    }
    
    /**
     * Ensures that this object reserves at least as much space as another.
     * 
     * @param space  the other space.
     */
    public void ensureAtLeast(AxisSpace space) {
        this.top = Math.max(this.top, space.top);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[19]++;
        this.bottom = Math.max(this.bottom, space.bottom);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[20]++;
        this.left = Math.max(this.left, space.left);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[21]++;
        this.right = Math.max(this.right, space.right);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[22]++;
    }
    
    /**
     * Ensures there is a minimum amount of space at the edge corresponding to 
     * the specified axis location.
     * 
     * @param space  the space.
     * @param edge  the location.
     */
    public void ensureAtLeast(double space, RectangleEdge edge) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[11]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.top < space) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[13]++;
                this.top = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[25]++;

            } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[14]++;}

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[12]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[26]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[15]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.bottom < space) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[17]++;
                this.bottom = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[28]++;

            } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[18]++;}

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[16]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[29]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[19]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.left < space) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[21]++;
                this.left = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[31]++;

            } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[22]++;}

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[20]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[32]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[23]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.right < space) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[25]++;
                this.right = space;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[34]++;

            } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[26]++;}

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[24]++;
            throw new IllegalStateException(
                "AxisSpace.ensureAtLeast(): unrecognised AxisLocation."
            );
        }
}
}
}
    }
    
    /**
     * Shrinks an area by the space attributes.
     * 
     * @param area  the area to shrink.
     * @param result  an optional carrier for the result.
     * 
     * @return The result.
     */
    public Rectangle2D shrink(Rectangle2D area, Rectangle2D result) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[27]++;
            result = new Rectangle2D.Double();
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[36]++;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[28]++;}
        result.setRect(
            area.getX() + this.left, 
            area.getY() + this.top,
            area.getWidth() - this.left - this.right,
            area.getHeight() - this.top - this.bottom
        );
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[37]++;
        return result;
    }

    /**
     * Expands an area by the amount of space represented by this object.
     * 
     * @param area  the area to expand.
     * @param result  an optional carrier for the result.
     * 
     * @return The result.
     */
    public Rectangle2D expand(Rectangle2D area, Rectangle2D result) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[29]++;
            result = new Rectangle2D.Double();
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[39]++;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[30]++;}
        result.setRect(
            area.getX() - this.left, 
            area.getY() - this.top,
            area.getWidth() + this.left + this.right,
            area.getHeight() + this.top + this.bottom
        );
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[40]++;
        return result;
    }
    
    /**
     * Calculates the reserved area.
     * 
     * @param area  the area.
     * @param edge  the edge.
     * 
     * @return The reserved area.
     */
    public Rectangle2D reserved(Rectangle2D area, RectangleEdge edge) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[41]++;
        Rectangle2D result = null;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[42]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[31]++;
            result = new Rectangle2D.Double(
                area.getX(), area.getY(), area.getWidth(), this.top
            );
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[43]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[32]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[44]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[33]++;
            result = new Rectangle2D.Double(
                area.getX(), area.getMaxY() - this.top,
                area.getWidth(), this.bottom
            );
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[45]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[34]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[46]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[35]++;
            result = new Rectangle2D.Double(
                area.getX(), area.getY(), this.left, area.getHeight()
            );
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[47]++;

        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[36]++;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[48]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[37]++;
            result = new Rectangle2D.Double(
                area.getMaxX() - this.right, area.getY(),
                this.right, area.getHeight()
            );
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[49]++;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[38]++;}
}
}
}
        return result;
    }
    
    /**
     * Returns a clone of the object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException This class won't throw this exception,
     *         but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    /**
     * Tests this object for equality with another object.
     * 
     * @param obj  the object to compare against.
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[50]++;
int CodeCoverConditionCoverageHelper_C20;       
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[39]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[40]++;}
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[51]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj instanceof AxisSpace) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[42]++;}
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[52]++;
        AxisSpace that = (AxisSpace) obj;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[53]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.top != that.top) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[44]++;}
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[54]++;
int CodeCoverConditionCoverageHelper_C23;   
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.bottom != that.bottom) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[46]++;}
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[55]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.left != that.left) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[48]++;}
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[56]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.right != that.right) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.branches[50]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[57]++;
        int result = 23;
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[58]++;
        long l = Double.doubleToLongBits(this.top);
        result = 37 * result + (int) (l ^ (l >>> 32));
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[59]++;
        l = Double.doubleToLongBits(this.bottom);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[60]++;
        result = 37 * result + (int) (l ^ (l >>> 32));
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[61]++;
        l = Double.doubleToLongBits(this.left);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[62]++;
        result = 37 * result + (int) (l ^ (l >>> 32));
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[63]++;
        l = Double.doubleToLongBits(this.right);
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[64]++;
        result = 37 * result + (int) (l ^ (l >>> 32));
CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h.statements[65]++;
        return result;
    }
    
    /**
     * Returns a string representing the object (for debugging purposes).
     * 
     * @return A string.
     */
    public String toString() {
        return super.toString() + "[left=" + this.left + ",right=" + this.right 
                    + ",top=" + this.top + ",bottom=" + this.bottom + "]";
    }
    
}

class CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.AxisSpace.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$2rc5pe8j9f19p73uc0bg4h () {
    super("org.jfree.chart.axis.AxisSpace.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.AxisSpace.java");
      for (int i = 1; i <= 65; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

