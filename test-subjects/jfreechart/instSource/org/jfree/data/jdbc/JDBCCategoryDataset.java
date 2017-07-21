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
 * ------------------------
 * JDBCCategoryDataset.java
 * ------------------------
 * (C) Copyright 2002-2007, by Bryan Scott and Contributors.
 *
 * Original Author:  Bryan Scott; Andy;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Thomas Morgner;
 *
 * Changes
 * -------
 * 26-Apr-2002 : Creation based on JdbcXYDataSet, using code contributed from 
 *               Andy;
 * 13-Aug-2002 : Updated Javadocs, import statements and formatting (DG);
 * 03-Sep-2002 : Added fix for bug 591385 (DG);
 * 18-Sep-2002 : Updated to support BIGINT (BS);
 * 16-Oct-2002 : Added fix for bug 586667 (DG);
 * 03-Feb-2003 : Added Types.DECIMAL (see bug report 677814) (DG);
 * 13-Jun-2003 : Added Types.TIME as suggest by Bryan Scott in the forum (DG);
 * 30-Jun-2003 : CVS Write test (BS);
 * 30-Jul-2003 : Added empty contructor and executeQuery(connection,string) 
 *               method (BS);
 * 29-Aug-2003 : Added a 'transpose' flag, so that data can be easily 
 *               transposed if required (DG);
 * 10-Sep-2003 : Added support for additional JDBC types (DG);
 * 24-Sep-2003 : Added clearing results from previous queries to executeQuery
 *               following being highlighted on online forum (BS);
 * 02-Dec-2003 : Throwing exceptions allows to handle errors, removed default 
 *               constructor, as without a connection, a query can never be 
 *               executed (TM);
 * 04-Dec-2003 : Added missing Javadocs (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 08-Mar-2006 : Fixed bug 1445748 where an exception is thrown if 
 *               executeQuery() is called more than once (DG);
 * 
 */

package org.jfree.data.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A {@link CategoryDataset} implementation over a database JDBC result set.
 * The dataset is populated via a call to {@link #executeQuery(String)} with 
 * the string SQL query.  The SQL query must return at least two columns.  The 
 * first column will be the category name and remaining columns values (each
 * column represents a series).  Subsequent calls to 
 * {@link #executeQuery(String)} will refresh the dataset.
 * <p>
 * The database connection is read-only and no write back facility exists.
 * <p>
 * NOTE: Many people have found this class too restrictive in general use.  
 * For the greatest flexibility, please consider writing your own code to read 
 * data from a <code>ResultSet</code> and populate a 
 * {@link DefaultCategoryDataset} directly.
 */
public class JDBCCategoryDataset extends DefaultCategoryDataset {
  static {
    CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -3080395327918844965L;
  static {
    CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[1]++;
  }
    
    /** The database connection. */
    private transient Connection connection;

    /**
     * A flag the controls whether or not the table is transposed.  The default 
     * is 'true' because this provides the behaviour described in the 
     * documentation.
     */
    private boolean transpose = true;
  {
    CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[2]++;
  }


    /**
     * Creates a new dataset with a database connection.
     *
     * @param  url  the URL of the database connection.
     * @param  driverName  the database driver class name.
     * @param  user  the database user.
     * @param  passwd  the database user's password.
     * 
     * @throws ClassNotFoundException if the driver cannot be found.
     * @throws SQLException if there is an error obtaining a connection to the 
     *                      database.
     */
    public JDBCCategoryDataset(String url,
                               String driverName,
                               String user,
                               String passwd)
        throws ClassNotFoundException, SQLException {

        Class.forName(driverName);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[3]++;
        this.connection = DriverManager.getConnection(url, user, passwd);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[4]++;
    }

    /**
     * Create a new dataset with the given database connection.
     *
     * @param connection  the database connection.
     */
    public JDBCCategoryDataset(Connection connection) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((connection == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[1]++;
            throw new NullPointerException("A connection must be supplied.");

        } else {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[2]++;}
        this.connection = connection;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[6]++;
    }

    /**
     * Creates a new dataset with the given database connection, and executes 
     * the supplied query to populate the dataset.
     *
     * @param connection  the connection.
     * @param query  the query.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public JDBCCategoryDataset(Connection connection, String query) 
        throws SQLException {
        this(connection);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[7]++;
        executeQuery(query);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[8]++;
    }

    /**
     * Returns a flag that controls whether or not the table values are 
     * transposed when added to the dataset.
     *
     * @return A boolean.
     */
    public boolean getTranspose() {
        return this.transpose;
    }

    /**
     * Sets a flag that controls whether or not the table values are transposed
     * when added to the dataset.
     *
     * @param transpose  the flag.
     */
    public void setTranspose(boolean transpose) {
        this.transpose = transpose;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[9]++;
    }

    /**
     * Populates the dataset by executing the supplied query against the 
     * existing database connection.  If no connection exists then no action 
     * is taken.
     * <p>
     * The results from the query are extracted and cached locally, thus 
     * applying an upper limit on how many rows can be retrieved successfully.
     *
     * @param query  the query.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public void executeQuery(String query) throws SQLException {
        executeQuery(this.connection, query);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[10]++;
    }

    /**
     * Populates the dataset by executing the supplied query against the 
     * existing database connection.  If no connection exists then no action 
     * is taken.
     * <p>
     * The results from the query are extracted and cached locally, thus 
     * applying an upper limit on how many rows can be retrieved successfully.
     *
     * @param con  the connection.
     * @param query  the query.
     * 
     * @throws SQLException if there is a problem executing the query.
     */
    public void executeQuery(Connection con, String query) throws SQLException {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[11]++;

        Statement statement = null;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[12]++;
        ResultSet resultSet = null;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            statement = con.createStatement();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[14]++;
            resultSet = statement.executeQuery(query);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[15]++;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[16]++;
            ResultSetMetaData metaData = resultSet.getMetaData();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[17]++;

            int columnCount = metaData.getColumnCount();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;

            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((columnCount < 2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[4]++;
                throw new SQLException(
                    "JDBCCategoryDataset.executeQuery() : insufficient columns "
                    + "returned from the database.");

            } else {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[5]++;}
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[19]++;

            // Remove any previous old data
            int i = getRowCount();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[20]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((--i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[1]--;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[2]--;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[3]++;
}
                removeRow(i);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[21]++;
            }
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;

            while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((resultSet.next()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[4]--;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[5]--;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[6]++;
}
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[23]++;
                // first column contains the row key...
                Comparable rowKey = resultSet.getString(1);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
                for (int column = 2;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((column <= columnCount) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[7]--;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[8]--;
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.loops[9]++;
}
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[25]++;

                    Comparable columnKey = metaData.getColumnName(column);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[26]++;
                    int columnType = metaData.getColumnType(column);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[27]++;

                    switch (columnType) {
                        case Types.TINYINT:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[6]++;
                        case Types.SMALLINT:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[7]++;
                        case Types.INTEGER:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[8]++;
                        case Types.BIGINT:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[9]++;
                        case Types.FLOAT:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[10]++;
                        case Types.DOUBLE:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[11]++;
                        case Types.DECIMAL:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[12]++;
                        case Types.NUMERIC:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[13]++;
                        case Types.REAL:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[14]++; {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[28]++;
                            Number value = (Number) resultSet.getObject(column);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
                            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.transpose) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[15]++;
                                setValue(value, columnKey, rowKey);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[30]++;

                            }
                            else {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[16]++;
                                setValue(value, rowKey, columnKey);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[31]++;
                            }
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[32]++;
                            break;
                        }
                        case Types.DATE:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[17]++;
                        case Types.TIME:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[18]++;
                        case Types.TIMESTAMP:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[19]++; {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[33]++;
                            Date date = (Date) resultSet.getObject(column);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[34]++;
                            Number value = new Long(date.getTime());
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
                            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.transpose) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[20]++;
                                setValue(value, columnKey, rowKey);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[36]++;

                            }
                            else {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[21]++;
                                setValue(value, rowKey, columnKey);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[37]++;
                            }
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[38]++;
                            break;
                        }
                        case Types.CHAR:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[22]++;
                        case Types.VARCHAR:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[23]++;
                        case Types.LONGVARCHAR:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[24]++; {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[39]++;
                            String string 
                                = (String) resultSet.getObject(column);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[40]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                            try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[41]++;
                                Number value = Double.valueOf(string);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
                                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.transpose) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[26]++;
                                    setValue(value, columnKey, rowKey);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[43]++;

                                }
                                else {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[27]++;
                                    setValue(value, rowKey, columnKey);
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[44]++;
                                }
                            }
                            catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[28]++;
                                // suppress (value defaults to null)
                            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[25]++;
}
  }
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[45]++;
                            break;
                        }
                        default:
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[29]++;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[46]++;
                            // not a value, can't use it (defaults to null)
                            break;
                    }
                }
            }

            fireDatasetChanged();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[47]++;
        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[3]++;
}
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((resultSet != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[30]++;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[49]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
                try {
CodeCoverTryBranchHelper_Try3 = true;
                    resultSet.close();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[50]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[33]++;
                    // report this?
                } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[32]++;
}
  }

            } else {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[31]++;}
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[51]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((statement != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[34]++;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[52]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                try {
CodeCoverTryBranchHelper_Try4 = true;
                    statement.close();
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.statements[53]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[37]++;
                    // report this?
                } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[36]++;
}
  }

            } else {
  CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5.branches[35]++;}
        }
    }

}

class CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5 ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[38];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.jdbc.JDBCCategoryDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$h47e2ekp1qai7dftq23r1vdsw6q6d6t8y7xb5 () {
    super("org.jfree.data.jdbc.JDBCCategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 37; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.jdbc.JDBCCategoryDataset.java");
      for (int i = 1; i <= 53; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 37; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

