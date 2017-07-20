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
 * ---------------
 * ImageTitle.java
 * ---------------
 * (C) Copyright 2000-2007, by David Berry and Contributors;
 *
 * Original Author:  David Berry;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header (DG);
 * 07-Nov-2001 : Separated the JCommon Class Library classes, JFreeChart now 
 *               requires jcommon.jar (DG);
 * 09-Jan-2002 : Updated Javadoc comments (DG);
 * 07-Feb-2002 : Changed blank space around title from Insets --> Spacer, to 
 *               allow for relative or absolute spacing (DG);
 * 25-Jun-2002 : Updated import statements (DG);
 * 23-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Nov-2002 : Added method for drawing images at left or right (DG);
 * 22-Sep-2003 : Added checks that the Image can never be null (TM).
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);    
 * 02-Feb-2005 : Changed padding mechanism for all titles (DG);
 * 20-Apr-2005 : Added new draw() method (DG);   
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.ui.VerticalAlignment;

/**
 * A chart title that displays an image.  This is useful, for example, if you
 * have an image of your corporate logo and want to use as a footnote or part
 * of a title in a chart you create.
 * <P>
 * ImageTitle needs an image passed to it in the constructor.  For ImageTitle
 * to work, you must have already loaded this image from its source (disk or
 * URL).  It is recomended you use something like
 * Toolkit.getDefaultToolkit().getImage() to get the image.  Then, use
 * MediaTracker or some other message to make sure the image is fully loaded
 * from disk.
 */
public class ImageTitle extends Title {
  static {
    CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.ping();
  }


    /** The title image. */
    private Image image;

    /**
     * Creates a new image title.
     *
     * @param image  the image (<code>null</code> not permitted).
     */
    public ImageTitle(Image image) {
        this(image, image.getHeight(null), image.getWidth(null), 
                Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[1]++;
    }

    /**
     * Creates a new image title.
     *
     * @param image  the image (<code>null</code> not permitted).
     * @param position  the title position.
     * @param horizontalAlignment  the horizontal alignment.
     * @param verticalAlignment  the vertical alignment.
     */
    public ImageTitle(Image image, RectangleEdge position, 
                      HorizontalAlignment horizontalAlignment, 
                      VerticalAlignment verticalAlignment) {

        this(image, image.getHeight(null), image.getWidth(null),
                position, horizontalAlignment, verticalAlignment, 
                Title.DEFAULT_PADDING);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[2]++;
    }

    /**
     * Creates a new image title with the given image scaled to the given
     * width and height in the given location.
     *
     * @param image  the image (<code>null</code> not permitted).
     * @param height  the height used to draw the image.
     * @param width  the width used to draw the image.
     * @param position  the title position.
     * @param horizontalAlignment  the horizontal alignment.
     * @param verticalAlignment  the vertical alignment.
     * @param padding  the amount of space to leave around the outside of the 
     *                 title.
     */
    public ImageTitle(Image image, int height, int width, 
                      RectangleEdge position,
                      HorizontalAlignment horizontalAlignment, 
                      VerticalAlignment verticalAlignment,
                      RectangleInsets padding) {

        super(position, horizontalAlignment, verticalAlignment, padding);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[3]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((image == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[1]++;
            throw new NullPointerException("Null 'image' argument.");

        } else {
  CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[2]++;}
        this.image = image;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[5]++;
        setHeight(height);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[6]++;
        setWidth(width);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[7]++;

    }

    /**
     * Returns the image for the title.
     *
     * @return The image for the title (never <code>null</code>).
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Sets the image for the title and notifies registered listeners that the
     * title has been modified.
     *
     * @param image  the new image (<code>null</code> not permitted).
     */
    public void setImage(Image image) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((image == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[3]++;
            throw new NullPointerException("Null 'image' argument.");

        } else {
  CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[4]++;}
        this.image = image;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[9]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[10]++;
    }

    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param titleArea  the area within which the title (and plot) should be 
     *                   drawn.
     */
    public void draw(Graphics2D g2, Rectangle2D titleArea) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[11]++;

        RectangleEdge position = getPosition();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[5]++;
            drawHorizontal(g2, titleArea);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[13]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[6]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[14]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[7]++;
            drawVertical(g2, titleArea);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[15]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[8]++;
            throw new RuntimeException("Invalid title position.");
        }
}
    }

    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param chartArea  the area within which the title (and plot) should be 
     *                   drawn.
     *
     * @return The size of the area used by the title.
     */
    protected Size2D drawHorizontal(Graphics2D g2, Rectangle2D chartArea) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[16]++;

        double startY = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[17]++;
        double topSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[18]++;
        double bottomSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[19]++;
        double leftSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[20]++;
        double rightSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[21]++;

        double w = getWidth();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[22]++;
        double h = getHeight();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[23]++;
        RectangleInsets padding = getPadding();
        topSpace = padding.calculateTopOutset(h);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[24]++;
        bottomSpace = padding.calculateBottomOutset(h);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[25]++;
        leftSpace = padding.calculateLeftOutset(w);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[26]++;
        rightSpace = padding.calculateRightOutset(w);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[27]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getPosition() == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[9]++;
            startY = chartArea.getY() + topSpace;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[10]++;
            startY = chartArea.getY() + chartArea.getHeight() - bottomSpace - h;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[30]++;
        }
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[31]++;

        // what is our alignment?
        HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[32]++;
        double startX = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[11]++;
            startX = chartArea.getX() + leftSpace + chartArea.getWidth() / 2.0 
                     - w / 2.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[34]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[12]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[35]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[13]++;
            startX = chartArea.getX() + leftSpace;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[36]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[14]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[37]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[15]++;
            startX = chartArea.getX() + chartArea.getWidth() - rightSpace - w;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[38]++;

        } else {
  CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[16]++;}
}
}
        g2.drawImage(this.image, (int) startX, (int) startY, (int) w, (int) h, 
                null);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[39]++;

        return new Size2D(chartArea.getWidth() + leftSpace + rightSpace,
            h + topSpace + bottomSpace);

    }

    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param chartArea  the area within which the title (and plot) should be 
     *                   drawn.
     *
     * @return The size of the area used by the title.
     */
    protected Size2D drawVertical(Graphics2D g2, Rectangle2D chartArea) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[40]++;

        double startX = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[41]++;
        double topSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[42]++;
        double bottomSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[43]++;
        double leftSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[44]++;
        double rightSpace = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[45]++;

        double w = getWidth();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[46]++;
        double h = getHeight();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[47]++;
        
        RectangleInsets padding = getPadding();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((padding != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[17]++;
            topSpace = padding.calculateTopOutset(h);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[49]++;
            bottomSpace = padding.calculateBottomOutset(h);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[50]++;
            leftSpace = padding.calculateLeftOutset(w);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[51]++;
            rightSpace = padding.calculateRightOutset(w);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[52]++;

        } else {
  CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[18]++;}
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getPosition() == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[19]++;
            startX = chartArea.getX() + leftSpace;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[54]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[20]++;
            startX = chartArea.getMaxX() - rightSpace - w;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[55]++;
        }
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[56]++;

        // what is our alignment?
        VerticalAlignment alignment = getVerticalAlignment();
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[57]++;
        double startY = 0.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[58]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((alignment == VerticalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[21]++;
            startY = chartArea.getMinY() + topSpace 
                     + chartArea.getHeight() / 2.0 - h / 2.0;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[59]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[22]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[60]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((alignment == VerticalAlignment.TOP) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[23]++;
            startY = chartArea.getMinY() + topSpace;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[61]++;

        }
        else {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[24]++;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[62]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((alignment == VerticalAlignment.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[25]++;
            startY = chartArea.getMaxY() - bottomSpace - h;
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[63]++;

        } else {
  CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.branches[26]++;}
}
}

        g2.drawImage(this.image, (int) startX, (int) startY, (int) w, (int) h, 
                null);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[64]++;

        return new Size2D(chartArea.getWidth() + leftSpace + rightSpace,
            h + topSpace + bottomSpace);

    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     * 
     * @return Always <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        draw(g2, area);
CodeCoverCoverageCounter$m09507defda2gruct5xt2e9.statements[65]++;
        return null;
    }

}

class CodeCoverCoverageCounter$m09507defda2gruct5xt2e9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m09507defda2gruct5xt2e9 ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.ImageTitle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$m09507defda2gruct5xt2e9 () {
    super("org.jfree.chart.title.ImageTitle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.ImageTitle.java");
      for (int i = 1; i <= 65; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

