package enums;
import listeners.GUIControl;


/**
 * Acts as a list of keys to be used by {@link EnrollmentGUI} and {@link GUIControl} for uniformity.
 * 
 * @author ~miming-chan~
 *
 */
public enum Action {
  EXIT,
  
  SHOW_STUDENTS,
  SHOW_TEACHERS,
  SHOW_CLASSES,
  SHOW_GRADE_LEVELS,
  SHOW_SUBJECTS,
  
  DELETE,
  
  UPDATE,
  UPDATE_STUDENT,
  UPDATE_TEACHER,
  
  SAVE_STUDENTS,
  SAVE_TEACHERS,
  SAVE_CLASSES,
  SAVE_GRADE_LEVELS,
  SAVE_SUBJECTS,
  
  INSERT,
  INSERT_STUDENT,
  
  SAVE_NEW_STUDENTS,
  SAVE_NEW_TEACHERS,
  SAVE_NEW_CLASSES,
  SAVE_NEW_GRADE_LEVELS,
  SAVE_NEW_SUBJECTS
  
  ;
}
