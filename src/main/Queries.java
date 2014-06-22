package main;
/**
 * Class that serves as a list of all queries to be performed.
 * Every query here has an equivalent method in {@link DataModel}.
 * 
 * @author ~miming-chan~
 *
 */
public class Queries {

  public static final String SHOW_STUDENTS = "CALL getStudentList();";
  public static final String SHOW_TEACHERS = "CALL getTeacherList();";
  public static final String SHOW_CLASSES = "CALL getClassList();";
  public static final String SHOW_GRADE_LEVELS = "CALL getGradeLvlList()";
  public static final String SHOW_SUBJECTS = "CALL getSubjectList();";
  public static final String GET_STUDENT = "CALL getStudent(?);";
  public static final String GET_GRADE_LEVEL = "CALL getGrLvl(?);";
  public static final String GET_CLASS = "CALL getClass(?);";
  public static final String GET_TEACHER = "CALL getTeacher(?);";
  public static final String GET_SUBJECT = "CALL getSubject(?);";
  
}
