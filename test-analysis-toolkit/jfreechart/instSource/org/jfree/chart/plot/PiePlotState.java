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
 * -----------------
 * PiePlotState.java
 * -----------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Mar-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.geom.Rectangle2D;

import org.jfree.chart.renderer.RendererState;

/**
 * A renderer state.
 */
public class PiePlotState extends RendererState {
  static {
    CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.ping();
  }


    /** The number of passes required by the renderer. */
    private int passesRequired;
    
    /** The total of the values in the dataset. */
    private double total;
    
    /** The latest angle. */
    private double latestAngle;
    
    /** The exploded pie area. */
    private Rectangle2D explodedPieArea;
   
    /** The pie area. */
    private Rectangle2D pieArea;
    
    /** The center of the pie in Java 2D coordinates. */
    private double pieCenterX;
   
    /** The center of the pie in Java 2D coordinates. */
    private double pieCenterY;
    
    /** The vertical pie radius. */
    private double pieHRadius;
   
    /** The horizontal pie radius. */
    private double pieWRadius;
    
    /** The link area. */
    private Rectangle2D linkArea;

    /**
     * Creates a new object for recording temporary state information for a 
     * renderer.
     * 
     * @param info  the plot rendering info.
     */
    public PiePlotState(PlotRenderingInfo info) {
        super(info);
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[1]++;
        this.passesRequired = 1;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[2]++;
        this.total = 0.0;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[3]++;
    }
    
    /**
     * Returns the number of passes required by the renderer.
     * 
     * @return The number of passes.
     */
    public int getPassesRequired() {
        return this.passesRequired;   
    }
    
    /**
     * Sets the number of passes required by the renderer.
     * 
     * @param passes  the passes.
     */
    public void setPassesRequired(int passes) {
        this.passesRequired = passes;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[4]++;   
    }
    
    /**
     * Returns the total of the values in the dataset.
     * 
     * @return The total.
     */
    public double getTotal() {
        return this.total;
    }
    
    /**
     * Sets the total.
     * 
     * @param total  the total.
     */
    public void setTotal(double total) {
        this.total = total;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[5]++;
    }
    
    /**
     * Returns the latest angle.
     * 
     * @return The latest angle.
     */
    public double getLatestAngle() {
        return this.latestAngle;   
    }
    
    /**
     * Sets the latest angle.
     * 
     * @param angle  the angle.
     */
    public void setLatestAngle(double angle) {
        this.latestAngle = angle;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[6]++;   
    }
    
    /**
     * Returns the pie area.
     * 
     * @return The pie area.
     */
    public Rectangle2D getPieArea() {
        return this.pieArea;   
    }
    
    /**
     * Sets the pie area.
     * 
     * @param area  the area.
     */
    public void setPieArea(Rectangle2D area) {
       this.pieArea = area;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[7]++;   
    }
    
    /**
     * Returns the exploded pie area.
     * 
     * @return The exploded pie area.
     */
    public Rectangle2D getExplodedPieArea() {
        return this.explodedPieArea;   
    }
    
    /**
     * Sets the exploded pie area.
     * 
     * @param area  the area.
     */
    public void setExplodedPieArea(Rectangle2D area) {
        this.explodedPieArea = area;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[8]++;   
    }
    
    /**
     * Returns the x-coordinate of the center of the pie chart.
     * 
     * @return The x-coordinate (in Java2D space).
     */
    public double getPieCenterX() {
        return this.pieCenterX;   
    }
    
    /**
     * Sets the x-coordinate of the center of the pie chart.
     * 
     * @param x  the x-coordinate (in Java2D space).
     */
    public void setPieCenterX(double x) {
        this.pieCenterX = x;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[9]++;   
    }
    
    /**
     * Returns the y-coordinate (in Java2D space) of the center of the pie 
     * chart.  For the {@link PiePlot3D} class, we derive this from the top of
     * the pie.
     * 
     * @return The y-coordinate (in Java2D space).
     */
    public double getPieCenterY() {
        return this.pieCenterY;   
    }
    
    /**
     * Sets the y-coordinate of the center of the pie chart.  This method is 
     * used by the plot and typically is not called directly by applications.
     * 
     * @param y  the y-coordinate (in Java2D space).
     */
    public void setPieCenterY(double y) {
        this.pieCenterY = y;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[10]++;   
    }

    /**
     * Returns the link area.  This defines the "dog-leg" point for the label 
     * linking lines.
     * 
     * @return The link area.
     */
    public Rectangle2D getLinkArea() {
        return this.linkArea;   
    }
    
    /**
     * Sets the label link area.  This defines the "dog-leg" point for the 
     * label linking lines.
     * 
     * @param area  the area.
     */
    public void setLinkArea(Rectangle2D area) {
        this.linkArea = area;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[11]++;   
    }

    /**
     * Returns the vertical pie radius.
     * 
     * @return The radius.
     */
    public double getPieHRadius() {
        return this.pieHRadius;   
    }
    
    /**
     * Sets the vertical pie radius.
     * 
     * @param radius  the radius.
     */
    public void setPieHRadius(double radius) {
        this.pieHRadius = radius;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[12]++;   
    }
    
    /**
     * Returns the horizontal pie radius.
     * 
     * @return The radius.
     */
    public double getPieWRadius() {
        return this.pieWRadius;   
    }
    
    /**
     * Sets the horizontal pie radius.
     * 
     * @param radius  the radius.
     */
    public void setPieWRadius(double radius) {
        this.pieWRadius = radius;
CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1.statements[13]++;   
    }
   
}

class CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1 ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$xupe05hnau6fdwwit5nfvk63c1 () {
    super("org.jfree.chart.plot.PiePlotState.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.PiePlotState.java");
      for (int i = 1; i <= 13; i++) {
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

