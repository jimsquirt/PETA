package main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSetMetaData;

/**
 * Generates an {@link ArrayList} (table) of String array (per row) from a {@link ResultSet} object.
 * 
 * @author ~miming-chan~
 *
 */
public class TableData {

  // Class variables
  private ArrayList<String[]> table;
  private String[] header;
  private int columnCount;
  private int rowCount;
  
  /*  GETTERS AND SETTERS  */
  
  public ArrayList<String[]> getTable() {
    return table;
  }

  public void setTable(ArrayList<String[]> table) {
    this.table = table;
  }

  public String[] getHeader() {
    return header;
  }

  public void setHeader(String[] header) {
    this.header = header;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public void setColumnCount(int columnCount) {
    this.columnCount = columnCount;
  }
  
  public int getRowCount() {
    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public boolean isEmpty() {
    return table.isEmpty();
  }

  
  /**
   * Constructor.
   * 
   * @param rs
   */
  public TableData(ResultSet rs) {
    try {
      gatherMetaData(rs);
      buildTable(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Gathers metadata from resultset given.
   * 
   * @param rs - Source resultset.
   * @throws SQLException
   */
  private void gatherMetaData(ResultSet rs) throws SQLException {
    ResultSetMetaData meta = rs.getMetaData();
    columnCount = meta.getColumnCount();
    header = new String[columnCount];
    for (int i=0; i<columnCount; i++) {
      header[i] = meta.getColumnLabel(i+1);
    }
  }
  
  /**
   * Builds table from the resultset given.
   * 
   * @param rs - Source resultset.
   * @throws SQLException
   */
  public void buildTable(ResultSet rs) throws SQLException {
    table = new ArrayList<String[]>();
    while (rs.next()) {
      String row[] = new String[columnCount];
      for (int i=0; i<columnCount; i++) {
        row[i] = rs.getString(i+1);
      }
      table.add(row);
    }
    setRowCount(table.size());
  }

  public String[][] generate2D() {
    String[][] result = new String[rowCount][columnCount];
    int i=0;
    for (String[] row : table) {
      result[i] = row;
      i++;
    }
    return result;
  }



}
