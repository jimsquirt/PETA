package enums;

import main.DataModel;

/**
 * Class that serves as a list of all queries to be performed.
 * Every query here has an equivalent method in {@link DataModel}.
 * 
 * @author ~miming-chan~
 *
 */
public enum Query {

  SHOW_STUDENTS ("CALL getStudentList();"),
  SHOW_TEACHERS ("CALL getTeacherList();"),
  SHOW_CLASSES ("CALL getClassList();"),
  SHOW_GRADE_LEVELS ("CALL getGradeLvlList();"),
  SHOW_SUBJECTS ("CALL getSubjectList();"),
  
  GET_STUDENT ("CALL getStudent(?);"),
  GET_GRADE_LEVEL ("CALL getGrLvl(?);"),
  GET_CLASS ("CALL getClass(?);"),
  GET_TEACHER ("CALL getTeacher(?);"),
  GET_SUBJECT ("CALL getSubject(?);"),
  
  INSERT_STUDENT ("CALL insertStudent(?, ?, ?, ?, ?, ?, ?);"),
  INSERT_GRADE_LEVEL ("CALL insertGradeLvl(?, ?);"),
  INSERT_CLASS ("CALL insertClass(?, ?, ?, ?, ?);"),
  INSERT_TEACHER ("CALL insertTeacher(?, ?, ?, ?, ?);"),
  INSERT_SUBJECT ("CALL insertSubject(?, ?, ?);"),
  
  UPDATE_STUDENT ("CALL updateStudent(?, ?, ?, ?, ?, ?, ?, ?);"),
  UPDATE_GRADE_LEVEL ("CALL updateGradeLvl(?, ?);"),
  UPDATE_CLASS ("CALL updateClass(?, ?, ?, ?, ?);"),
  UPDATE_TEACHER ("CALL updateTeacher(?, ?, ?, ?, ?, ?);"),
  UPDATE_SUBJECT ("CALL updateSubject(?, ?, ?);"),
  
  DELETE_STUDENT ("CALL deleteStudent(?);"),
  DELETE_GRADE_LEVEL ("CALL deleteGradeLevel(?);"),
  DELETE_CLASS ("CALL deleteClass(?);"),
  DELETE_TEACHER ("CALL deleteTeacher(?);"),
  DELETE_SUBJECT ("CALL deleteSubject(?);")
  ;
  
  
  private String query;
  
  Query(String q) {
    setQuery(q);
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }
  
}
