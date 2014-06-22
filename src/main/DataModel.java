package main;
import java.sql.SQLException;

import enums.Query;
import enums.Tabs;

/**
 * Class in charge of performing DB operations. (MVC's M)
 * Every query has an entry in the {@link Query} class and a corresponding method here that executes those queries.
 * 
 * @author ~miming-chan~
 *
 */
public class DataModel {
  private SQLConnection sc;
  
  /**
   * Constructor.
   */
  public DataModel() {
    sc = SQLConnection.getInstance();
  }
  
  /**
   * Retrieves all records from STUDENT table.
   * 
   * @return
   * @throws SQLException
   */
  public TableData showStudents() throws SQLException {
    return sc.getFromDB(Query.SHOW_STUDENTS.getQuery());
  }

  /**
   * Retrieves all records from CLASS table.
   * 
   * @return
   * @throws SQLException
   */
  public TableData showClasses() throws SQLException {
    return sc.getFromDB(Query.SHOW_CLASSES.getQuery());
  }
  
  /**
   * Retrieves all records from TEACHER table.
   * 
   * @return
   * @throws SQLException
   */
  public TableData showTeachers() throws SQLException {
    return sc.getFromDB(Query.SHOW_TEACHERS.getQuery());
  }
  
  /**
   * Retrieves all records from SUBJECT table.
   * 
   * @return
   * @throws SQLException
   */
  public TableData showSubjects() throws SQLException {
    return sc.getFromDB(Query.SHOW_SUBJECTS.getQuery());
  }
  
  /**
   * Retrieves all records from GR_LVL table.
   * 
   * @return
   * @throws SQLException
   */
  public TableData showGradeLevels() throws SQLException {
    return sc.getFromDB(Query.SHOW_GRADE_LEVELS.getQuery());
  }
  
  public void delete(int tab, String id) throws SQLException {
    if (tab == Tabs.STUDENT.getIndex()) {
      doDelete(Query.DELETE_STUDENT, id);

    } else if (tab == Tabs.TEACHERS.getIndex()) {
      doDelete(Query.DELETE_TEACHER, id);
    
    } else if (tab == Tabs.CLASS.getIndex()) {
      doDelete(Query.DELETE_CLASS, id);
    
    } else if (tab == Tabs.GRADE_LEVEL.getIndex()) {
      doDelete(Query.DELETE_GRADE_LEVEL, id);
    
    } else if (tab == Tabs.SUBJECT.getIndex()) {
      doDelete(Query.DELETE_SUBJECT, id);
    }
  }
  
  private void doDelete(Query query, String id) throws SQLException {
    doDML(query, new String[] {id});
  }
  
  public String[] getStudent(String id) throws SQLException{
    return sc.getFromDB(Query.GET_STUDENT.getQuery(), id).getTable().get(0);
  }

  public String[] getGradeLevel(String id) throws SQLException {
    return sc.getFromDB(Query.GET_GRADE_LEVEL.getQuery(), id).getTable().get(0);
  }

  public String[] getClass(String id) throws SQLException {
    return sc.getFromDB(Query.GET_CLASS.getQuery(), id).getTable().get(0);
  }

  public String[] getTeacher(String id) throws SQLException {
    return sc.getFromDB(Query.GET_TEACHER.getQuery(), id).getTable().get(0);
  }

  public String[] getSubject(String id) throws SQLException {
    return sc.getFromDB(Query.GET_SUBJECT.getQuery(), id).getTable().get(0);
  }

  public void doDML(Query query, String[] paramValues) throws SQLException {
    sc.updateDB(query.getQuery(), paramValues);
  }
  
}
