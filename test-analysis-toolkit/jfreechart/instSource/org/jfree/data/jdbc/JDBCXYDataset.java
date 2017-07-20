/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2006, by Object Refinery Limited and Contributors.
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
 * ------------------
 * JDBCXYDataset.java
 * ------------------
 * (C) Copyright 2002-2006, by Bryan Scott and Contributors.
 *
 * Original Author:  Bryan Scott;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Eric Alexander;
 *
 *
 * Changes
 * -------
 * 14-Mar-2002 : Version 1 contributed by Bryan Scott (DG);
 * 19-Apr-2002 : Updated executeQuery, to close cursors and to improve support 
 *               for types.
 * 26-Apr-2002 : Renamed JdbcXYDataset to better fit in with the existing data 
 *               source conventions.
 * 26-Apr-2002 : Changed to extend AbstractDataset.
 * 13-Aug-2002 : Updated Javadoc comments and imports (DG);
 * 18-Sep-2002 : Updated to support BIGINT (BS);
 * 21-Jan-2003 : Renamed JdbcXYDataset --> JDBCXYDataset (DG);
 * 01-Jul-2003 : Added support to query whether a timeseries (BS);
 * 30-Jul-2003 : Added empty contructor and executeQuery(connection,string) 
 *               method (BS);
 * 24-Sep-2003 : Added a check to ensure at least two valid columns are 
 *               returned by the query in executeQuery as suggest in online 
 *               forum by anonymous (BS);
 * 02-Dec-2003 : Throwing exceptions allows to handle errors, removed default 
 *               constructor, as without a connection, a query can never be 
 *               executed.
 * 16-Mar-2004 : Added check for null values (EA);
 * 05-May-2004 : Now extends AbstractXYDataset (DG);
 * 21-May-2004 : Implemented TableXYDataset, added support for SMALLINT and 
 *               fixed bug in code that determines the min and max values (see 
 *               bug id 938138) (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 18-Nov-2004 : Updated for changes in RangeInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 17-Oct-2006 : Deprecated unused methods - see bug 1578293 (DG);
 * 
 */

package org.jfree.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.Log;

/**
 * This class provides an {@link XYDataset} implementation over a database 
 * JDBC result set.  The dataset is populated via a call to executeQuery with 
 * the string sql query.  The sql query must return at least two columns.  
 * The first column will be the x-axis and remaining columns y-axis values.
 * executeQuery can be called a number of times.
 *
 * The database connection is read-only and no write back facility exists.
 */
public class JDBCXYDataset extends AbstractXYDataset 
                           implements XYDataset, 
                                      TableXYDataset, 
                                      RangeInfo {
  static {
    CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.ping();
  }


    /** The database connection. */
    private transient Connection connection;

    /** Column names. */
    private String[] columnNames = {};
  {
    CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[1]++;
  }

    /** Rows. */
    private ArrayList rows;

    /** The maximum y value of the returned result set */
    private double maxValue = 0.0;
  {
    CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[2]++;
  }

    /** The minimum y value of the returned result set */
    private double minValue = 0.0;
  {
    CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[3]++;
  }

    /** Is this dataset a timeseries ? */
    private boolean isTimeSeries = false;
  {
    CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[4]++;
  }

    /**
     * Creates a new JDBCXYDataset (initially empty) with no database 
     * connection.
     */
    private JDBCXYDataset() {
        this.rows = new ArrayList();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[5]++;
    }

    /**
     * Creates a new dataset (initially empty) and establishes a new database 
     * connection.
     *
     * @param  url  URL of the database connection.
     * @param  driverName  the database driver class name.
     * @param  user  the database user.
     * @param  password  the database user's password.
     * 
     * @throws ClassNotFoundException if the driver cannot be found.
     * @throws SQLException if there is a problem connecting to the database.
     */
    public JDBCXYDataset(String url,
                         String driverName,
                         String user,
                         String password)
        throws SQLException, ClassNotFoundException {
        
        this();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[6]++;
        Class.forName(driverName);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[7]++;
        this.connection = DriverManager.getConnection(url, user, password);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[8]++;
    }

    /**
     * Creates a new dataset (initially empty) using the specified database 
     * connection.
     *
     * @param  con  the database connection.
     * 
     * @throws SQLException if there is a problem connecting to the database.
     */
    public JDBCXYDataset(Connection con) throws SQLException {
        this();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[9]++;
        this.connection = con;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[10]++;
    }

    /**
     * Creates a new dataset using the specified database connection, and 
     * populates it using data obtained with the supplied query.
     *
     * @param con  the connection.
     * @param query  the SQL query.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public JDBCXYDataset(Connection con, String query) throws SQLException {
        this(con);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[11]++;
        executeQuery(query);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[12]++;
    }

    /**
     * Returns <code>true</code> if the dataset represents time series data, 
     * and <code>false</code> otherwise.
     * 
     * @return A boolean.
     */
    public boolean isTimeSeries() {
        return this.isTimeSeries;
    }

    /**
     * Sets a flag that indicates whether or not the data represents a time 
     * series.
     * 
     * @param timeSeries  the new value of the flag.
     */
    public void setTimeSeries(boolean timeSeries) {
        this.isTimeSeries = timeSeries;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[13]++;
    }

    /**
     * ExecuteQuery will attempt execute the query passed to it against the
     * existing database connection.  If no connection exists then no action
     * is taken.
     *
     * The results from the query are extracted and cached locally, thus
     * applying an upper limit on how many rows can be retrieved successfully.
     *
     * @param  query  the query to be executed.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public void executeQuery(String query) throws SQLException {
        executeQuery(this.connection, query);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[14]++;
    }

    /**
     * ExecuteQuery will attempt execute the query passed to it against the
     * provided database connection.  If connection is null then no action is 
     * taken.
     *
     * The results from the query are extracted and cached locally, thus
     * applying an upper limit on how many rows can be retrieved successfully.
     *
     * @param  query  the query to be executed.
     * @param  con  the connection the query is to be executed against.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public void executeQuery(Connection con, String query) 
        throws SQLException {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((con == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[1]++;
            throw new SQLException(
                "There is no database to execute the query."
            );

        } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[2]++;}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[16]++;

        ResultSet resultSet = null;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[17]++;
        Statement statement = null;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[18]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            statement = con.createStatement();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[19]++;
            resultSet = statement.executeQuery(query);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[20]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[21]++;
            ResultSetMetaData metaData = resultSet.getMetaData();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[22]++;

            int numberOfColumns = metaData.getColumnCount();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[23]++;
            int numberOfValidColumns = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[24]++;
            int [] columnTypes = new int[numberOfColumns];
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[25]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
            for (int column = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((column < numberOfColumns) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[1]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[2]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[3]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[26]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[27]++;
                    int type = metaData.getColumnType(column + 1);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[28]++;
                    switch (type) {

                        case Types.NUMERIC:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[5]++;
                        case Types.REAL:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[6]++;
                        case Types.INTEGER:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[7]++;
                        case Types.DOUBLE:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[8]++;
                        case Types.FLOAT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[9]++;
                        case Types.DECIMAL:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[10]++;
                        case Types.BIT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[11]++;
                        case Types.DATE:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[12]++;
                        case Types.TIME:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[13]++;
                        case Types.TIMESTAMP:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[14]++;
                        case Types.BIGINT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[15]++;
                        case Types.SMALLINT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[16]++;
                            ++numberOfValidColumns;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[29]++;
                            columnTypes[column] = type;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[30]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[31]++;
                            break;
                        default:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[17]++;
                            Log.warn(
                                "Unable to load column "
                                + column + " (" + type + ","
                                + metaData.getColumnClassName(column + 1) 
                                + ")"
                            );
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[32]++;
                            columnTypes[column] = Types.NULL;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[33]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[34]++;
                            break;
                    }
                }
                catch (SQLException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[18]++;
                    columnTypes[column] = Types.NULL;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[35]++;
                    throw e;
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[4]++;
}
  }
            }
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;


            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((numberOfValidColumns <= 1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[19]++;
                throw new SQLException(
                    "Not enough valid columns where generated by query."
                );

            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[20]++;}

            /// First column is X data
            this.columnNames = new String[numberOfValidColumns - 1];
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[37]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[38]++;
            /// Get the column names and cache them.
            int currentColumn = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[39]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
            for (int column = 1;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((column < numberOfColumns) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[4]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[5]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[6]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[40]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((columnTypes[column] != Types.NULL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[21]++;
                    this.columnNames[currentColumn] 
                        = metaData.getColumnLabel(column + 1);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[41]++;
                    ++currentColumn;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[42]++;

                } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[22]++;}
            }
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;

            // Might need to add, to free memory from any previous result sets
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.rows != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[23]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[44]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
                for (int column = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((column < this.rows.size()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[7]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[8]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[9]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[45]++;
                    ArrayList row = (ArrayList) this.rows.get(column);
                    row.clear();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[46]++;
                }
                this.rows.clear();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[47]++;

            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[24]++;}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[48]++;

            // Are we working with a time series.
            switch (columnTypes[0]) {
                case Types.DATE:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[25]++;
                case Types.TIME:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[26]++;
                case Types.TIMESTAMP:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[27]++;
                    this.isTimeSeries = true;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[49]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[50]++;
                    break;
                default :
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[28]++;
                    this.isTimeSeries = false;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[51]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[52]++;
                    break;
            }
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[53]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;

            // Get all rows.
            // rows = new ArrayList();
            while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((resultSet.next()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[10]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[11]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[12]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[54]++;
                ArrayList newRow = new ArrayList();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[55]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[13]++;


int CodeCoverConditionCoverageHelper_C9;
                for (int column = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((column < numberOfColumns) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[13]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[14]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[15]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[56]++;
                    Object xObject = resultSet.getObject(column + 1);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[57]++;
                    switch (columnTypes[column]) {
                        case Types.NUMERIC:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[29]++;
                        case Types.REAL:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[30]++;
                        case Types.INTEGER:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[31]++;
                        case Types.DOUBLE:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[32]++;
                        case Types.FLOAT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[33]++;
                        case Types.DECIMAL:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[34]++;
                        case Types.BIGINT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[35]++;
                        case Types.SMALLINT:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[36]++;
                            newRow.add(xObject);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[58]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[59]++;
                            break;

                        case Types.DATE:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[37]++;
                        case Types.TIME:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[38]++;
                        case Types.TIMESTAMP:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[39]++;
                            newRow.add(new Long(((Date) xObject).getTime()));
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[60]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[61]++;
                            break;
                        case Types.NULL:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[40]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[62]++;
                            break;
                        default:
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[41]++;
                            System.err.println("Unknown data");
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[63]++;
                            columnTypes[column] = Types.NULL;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[64]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[65]++;
                            break;
                    }
                }
                this.rows.add(newRow);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[66]++;
            }
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[67]++;
int CodeCoverConditionCoverageHelper_C10;

            /// a kludge to make everything work when no rows returned
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.rows.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[42]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[68]++;
                ArrayList newRow = new ArrayList();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[69]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[16]++;


int CodeCoverConditionCoverageHelper_C11;
                for (int column = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((column < numberOfColumns) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[16]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[17]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[18]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[70]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((columnTypes[column] != Types.NULL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[44]++;
                        newRow.add(new Integer(0));
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[71]++;

                    } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[45]++;}
                }
                this.rows.add(newRow);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[72]++;

            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[43]++;}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[73]++;
int CodeCoverConditionCoverageHelper_C13;

            /// Determine max and min values.
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.rows.size() < 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[46]++;
                this.maxValue = 0.0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[74]++;
                this.minValue = 0.0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[75]++;

            }
            else {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[47]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[76]++;
                ArrayList row = (ArrayList) this.rows.get(0);
                this.maxValue = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[77]++;
                this.minValue = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[78]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[79]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
                for (int rowNum = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((rowNum < this.rows.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); ++rowNum) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[19]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[20]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[21]++;
}
                    row = (ArrayList) this.rows.get(rowNum);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[80]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[81]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[22]++;


int CodeCoverConditionCoverageHelper_C15;
                    for (int column = 1;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((column < numberOfColumns) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[22]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[23]--;
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.loops[24]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[82]++;
                        Object testValue = row.get(column);
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[83]++;
int CodeCoverConditionCoverageHelper_C16;
                        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((testValue != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[48]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[84]++;
                            double test = ((Number) testValue).doubleValue();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[85]++;
int CodeCoverConditionCoverageHelper_C17;
                        
                            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((test < this.minValue) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[50]++;
                                this.minValue = test;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[86]++;

                            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[51]++;}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[87]++;
int CodeCoverConditionCoverageHelper_C18;
                            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((test > this.maxValue) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[52]++;
                                this.maxValue = test;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[88]++;

                            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[53]++;}

                        } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[49]++;}
                    }
                }
            }

            fireDatasetChanged();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[89]++; // Tell the listeners a new table has arrived.
        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[3]++;
}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[90]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((resultSet != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[54]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[91]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
                try {
CodeCoverTryBranchHelper_Try3 = true;
                    resultSet.close();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[92]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[57]++;
                    // TODO: is this a good idea?
                } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[56]++;
}
  }

            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[55]++;}
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[93]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((statement != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[58]++;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[94]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                try {
CodeCoverTryBranchHelper_Try4 = true;
                    statement.close();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[95]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[61]++;
                    // TODO: is this a good idea?
                } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[60]++;
}
  }

            } else {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[59]++;}
        }

    }

    /**
     * Returns the x-value for the specified series and item.  The
     * implementation is responsible for ensuring that the x-values are
     * presented in ascending order.
     *
     * @param  seriesIndex  the series (zero-based index).
     * @param  itemIndex  the item (zero-based index).
     *
     * @return The x-value
     *
     * @see XYDataset
     */
    public Number getX(int seriesIndex, int itemIndex) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[96]++;
        ArrayList row = (ArrayList) this.rows.get(itemIndex);
        return (Number) row.get(0);
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param  seriesIndex  the series (zero-based index).
     * @param  itemIndex  the item (zero-based index).
     *
     * @return The yValue value
     *
     * @see XYDataset
     */
    public Number getY(int seriesIndex, int itemIndex) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[97]++;
        ArrayList row = (ArrayList) this.rows.get(itemIndex);
        return (Number) row.get(seriesIndex + 1);
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param  seriesIndex  the series (zero-based index).
     *
     * @return The itemCount value
     *
     * @see XYDataset
     */
    public int getItemCount(int seriesIndex) {
        return this.rows.size();
    }

    /**
     * Returns the number of items in all series.  This method is defined by 
     * the {@link TableXYDataset} interface.
     * 
     * @return The item count.
     */
    public int getItemCount() {
        return getItemCount(0);
    }
    
    /**
     * Returns the number of series in the dataset.
     *
     * @return The seriesCount value
     *
     * @see XYDataset
     * @see Dataset
     */
    public int getSeriesCount() {
        return this.columnNames.length;
    }

    /**
     * Returns the key for the specified series.
     *
     * @param seriesIndex  the series (zero-based index).
     *
     * @return The seriesName value
     *
     * @see XYDataset
     * @see Dataset
     */
    public Comparable getSeriesKey(int seriesIndex) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[98]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((seriesIndex < this.columnNames.length) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.columnNames[seriesIndex] != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[62]++;
            return this.columnNames[seriesIndex];

        }
        else {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[63]++;
            return "";
        }

    }

    /**
     * Returns the number of items that should be displayed in the legend.
     *
     * @return The legendItemCount value
     *
     * @deprecated This method is not used in JFreeChart 1.0.x (it was left in
     *     the API by mistake and is officially deprecated from version 1.0.3
     *     onwards).
     */
    public int getLegendItemCount() {
        return getSeriesCount();
    }

    /**
     * Returns the legend item labels.
     *
     * @return The legend item labels.
     *
     * @deprecated This method is not used in JFreeChart 1.0.x (it was left in
     *     the API by mistake and is officially deprecated from version 1.0.3
     *     onwards).
     */
    public String[] getLegendItemLabels() {
        return this.columnNames;
    }

    /**
     * Close the database connection
     */
    public void close() {
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[99]++;
boolean CodeCoverTryBranchHelper_Try5 = false;

        try {
CodeCoverTryBranchHelper_Try5 = true;
            this.connection.close();
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[100]++;
        }
        catch (Exception e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[65]++;
            System.err.println("JdbcXYDataset: swallowing exception.");
CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.statements[101]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29.branches[64]++;
}
  }

    }

    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The minimum value.
     */
    public double getRangeLowerBound(boolean includeInterval) {
        return this.minValue;
    }
    
    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The maximum value.
     */
    public double getRangeUpperBound(boolean includeInterval) {
        return this.maxValue;
    }

    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval) {
        return new Range(this.minValue, this.maxValue);
    }

}

class CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29 ());
  }
    public static long[] statements = new long[102];
    public static long[] branches = new long[66];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.jfree.data.jdbc.JDBCXYDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2};
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
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$66c2d4peh0ygw8pob9ps5gscgh29 () {
    super("org.jfree.data.jdbc.JDBCXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 101; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 65; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.jdbc.JDBCXYDataset.java");
      for (int i = 1; i <= 101; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 65; i++) {
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
      for (int i = 1; i <= 8; i++) {
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

