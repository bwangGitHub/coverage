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
 * ----------------
 * BlockParams.java
 * ----------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Apr-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.block;

/**
 * A standard parameter object that can be passed to the draw() method defined
 * by the {@link Block} class.
 */
public class BlockParams implements EntityBlockParams {
  static {
    CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.ping();
  }

    
    /** 
     * A flag that controls whether or not the block should generate and 
     * return chart entities for the items it draws.
     */
    private boolean generateEntities;
    
    /** 
     * The x-translation (used to enable chart entities to use global 
     * coordinates rather than coordinates that are local to the container
     * they are within).
     */
    private double translateX;
    
    /** 
     * The y-translation (used to enable chart entities to use global 
     * coordinates rather than coordinates that are local to the container
     * they are within).
     */
    private double translateY;
    
    /**
     * Creates a new instance.
     */
    public BlockParams() {
        this.translateX = 0.0;
CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.statements[1]++;
        this.translateY = 0.0;
CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.statements[2]++;
        this.generateEntities = false;
CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.statements[3]++;    
    }
    
    /**
     * Returns the flag that controls whether or not chart entities are 
     * generated.
     * 
     * @return A boolean.
     */
    public boolean getGenerateEntities() {
        return this.generateEntities;   
    }
    
    /**
     * Sets the flag that controls whether or not chart entities are generated.
     * 
     * @param generate  the flag.
     */
    public void setGenerateEntities(boolean generate) {
        this.generateEntities = generate;
CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.statements[4]++;   
    }
    
    /**
     * Returns the translation required to convert local x-coordinates back to
     * the coordinate space of the container.
     * 
     * @return The x-translation amount.
     */
    public double getTranslateX() {
        return this.translateX;   
    }
    
    /**
     * Sets the translation required to convert local x-coordinates into the
     * coordinate space of the container.
     * 
     * @param x  the x-translation amount.
     */
    public void setTranslateX(double x) {
        this.translateX = x;
CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.statements[5]++;   
    }
    
    /**
     * Returns the translation required to convert local y-coordinates back to
     * the coordinate space of the container.
     * 
     * @return The y-translation amount.
     */
    public double getTranslateY() {
        return this.translateY;   
    }
    
    /**
     * Sets the translation required to convert local y-coordinates into the
     * coordinate space of the container.
     * 
     * @param y  the y-translation amount.
     */
    public void setTranslateY(double y) {
        this.translateY = y;
CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild.statements[6]++;   
    }

}

class CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3xkfr27zxton3zk981345lild () {
    super("org.jfree.chart.block.BlockParams.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("org.jfree.chart.block.BlockParams.java");
      for (int i = 1; i <= 6; i++) {
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

