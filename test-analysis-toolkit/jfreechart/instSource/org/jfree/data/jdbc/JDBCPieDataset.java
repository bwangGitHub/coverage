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
 * -------------------
 * JDBCPieDataset.java
 * -------------------
 * (C) Copyright 2002-2007, by Bryan Scott and Contributors.
 *
 * Original Author:  Bryan Scott; Andy
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Thomas Morgner;
 *
 * Changes
 * -------
 * 26-Apr-2002 : Creation based on JdbcXYDataSet, but extending 
 *               DefaultPieDataset (BS);
 * 24-Jun-2002 : Removed unnecessary import and local variable (DG);
 * 13-Aug-2002 : Updated Javadoc comments and imports, removed default 
 *               constructor (DG);
 * 18-Sep-2002 : Updated to support BIGINT (BS);
 * 21-Jan-2003 : Renamed JdbcPieDataset --> JDBCPieDataset (DG);
 * 03-Feb-2003 : Added Types.DECIMAL (see bug report 677814) (DG);
 * 05-Jun-2003 : Updated to support TIME, optimised executeQuery method (BS);
 * 30-Jul-2003 : Added empty contructor and executeQuery(connection,string) 
 *               method (BS);
 * 02-Dec-2003 : Throwing exceptions allows to handle errors, removed default 
 *               constructor, as without a connection, a query can never be 
 *               executed (TM);
 * 04-Dec-2003 : Added missing Javadocs (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 
 */

package org.jfree.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * A {@link PieDataset} that reads data from a database via JDBC.
 * <P>
 * A query should be supplied that returns data in two columns, the first 
 * containing VARCHAR data, and the second containing numerical data.  The 
 * data is cached in-memory and can be refreshed at any time.
 */
public class JDBCPieDataset extends DefaultPieDataset {
  static {
    CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -8753216855496746108L;
  static {
    CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[1]++;
  }

    /** The database connection. */
    private transient Connection connection;

    /**
     * Creates a new JDBCPieDataset and establishes a new database connection.
     *
     * @param url  the URL of the database connection.
     * @param driverName  the database driver class name.
     * @param user  the database user.
     * @param password  the database users password.
     * 
     * @throws ClassNotFoundException if the driver cannot be found.
     * @throws SQLException if there is a problem obtaining a database 
     *                      connection.
     */
    public JDBCPieDataset(String url,
                          String driverName,
                          String user,
                          String password)
        throws SQLException, ClassNotFoundException {
        
        Class.forName(driverName);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[2]++;
        this.connection = DriverManager.getConnection(url, user, password);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[3]++;
    }

    /**
     * Creates a new JDBCPieDataset using a pre-existing database connection.
     * <P>
     * The dataset is initially empty, since no query has been supplied yet.
     *
     * @param con  the database connection.
     */
    public JDBCPieDataset(Connection con) {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((con == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[1]++;
            throw new NullPointerException("A connection must be supplied.");

        } else {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[2]++;}
        this.connection = con;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[5]++;
    }


    /**
     * Creates a new JDBCPieDataset using a pre-existing database connection.
     * <P>
     * The dataset is initialised with the supplied query.
     *
     * @param con  the database connection.
     * @param query  the database connection.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public JDBCPieDataset(Connection con, String query) throws SQLException {
        this(con);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[6]++;
        executeQuery(query);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[7]++;
    }

    /**
     *  ExecuteQuery will attempt execute the query passed to it against the
     *  existing database connection.  If no connection exists then no action
     *  is taken.
     *  The results from the query are extracted and cached locally, thus
     *  applying an upper limit on how many rows can be retrieved successfully.
     *
     * @param  query  the query to be executed.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public void executeQuery(String query) throws SQLException {
      executeQuery(this.connection, query);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[8]++;
    }

    /**
     *  ExecuteQuery will attempt execute the query passed to it against the
     *  existing database connection.  If no connection exists then no action
     *  is taken.
     *  The results from the query are extracted and cached locally, thus
     *  applying an upper limit on how many rows can be retrieved successfully.
     *
     * @param  query  the query to be executed
     * @param  con  the connection the query is to be executed against
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public void executeQuery(Connection con, String query) throws SQLException {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[9]++;

        Statement statement = null;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[10]++;
        ResultSet resultSet = null;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[11]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

        try {
CodeCoverTryBranchHelper_Try1 = true;
            statement = con.createStatement();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[12]++;
            resultSet = statement.executeQuery(query);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[13]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[14]++;
            ResultSetMetaData metaData = resultSet.getMetaData();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[15]++;

            int columnCount = metaData.getColumnCount();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((columnCount != 2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[4]++;
                throw new SQLException(
                    "Invalid sql generated.  PieDataSet requires 2 columns only"
                );

            } else {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[5]++;}
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[17]++;

            int columnType = metaData.getColumnType(2);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[18]++;
            double value = Double.NaN;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((resultSet.next()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.loops[1]--;
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.loops[2]--;
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.loops[3]++;
}
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[20]++;
                Comparable key = resultSet.getString(1);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[21]++;
                switch (columnType) {
                    case Types.NUMERIC:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[6]++;
                    case Types.REAL:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[7]++;
                    case Types.INTEGER:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[8]++;
                    case Types.DOUBLE:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[9]++;
                    case Types.FLOAT:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[10]++;
                    case Types.DECIMAL:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[11]++;
                    case Types.BIGINT:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[12]++;
                        value = resultSet.getDouble(2);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[22]++;
                        setValue(key, value);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[23]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[24]++;
                        break;

                    case Types.DATE:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[13]++;
                    case Types.TIME:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[14]++;
                    case Types.TIMESTAMP:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[15]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[25]++;
                        Timestamp date = resultSet.getTimestamp(2);
                        value = date.getTime();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[26]++;
                        setValue(key, value);
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[27]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[28]++;
                        break;

                    default:
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[16]++;
                        System.err.println(
                            "JDBCPieDataset - unknown data type"
                        );
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[29]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[30]++;
                        break;
                }
            }

            fireDatasetChanged();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[31]++;

        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[3]++;
}
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((resultSet != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[17]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[33]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    resultSet.close();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[34]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[20]++;
                    System.err.println("JDBCPieDataset: swallowing exception.");
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[35]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[19]++;
}
  }

            } else {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[18]++;}
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((statement != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[21]++;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[37]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
                try {
CodeCoverTryBranchHelper_Try3 = true;
                    statement.close();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[38]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[24]++;
                    System.err.println("JDBCPieDataset: swallowing exception.");
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[39]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[23]++;
}
  }

            } else {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[22]++;}
        }
    }


    /**
     * Close the database connection
     */
    public void close() {
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[40]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
            this.connection.close();
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[41]++;
        }
        catch (Exception e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[26]++;
            System.err.println("JdbcXYDataset: swallowing exception.");
CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.statements[42]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p.branches[25]++;
}
  }
    }
}

class CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.data.jdbc.JDBCPieDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$17x1stde9a4sd2f7qmxto35s31b42p () {
    super("org.jfree.data.jdbc.JDBCPieDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.jdbc.JDBCPieDataset.java");
      for (int i = 1; i <= 42; i++) {
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
    for (int i = 1; i <= 5; i++) {
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

