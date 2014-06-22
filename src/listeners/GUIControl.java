package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dialogs.ClassDialog;
import dialogs.EntityDialog;
import dialogs.GradeLevelDialog;
import dialogs.StudentDialog;
import dialogs.SubjectDialog;
import dialogs.TeacherDialog;
import main.DataModel;
import main.DataTable;
import main.MainFrame;
import enums.Action;
import enums.PopUp;

/**
 * Controller class of the system. (MVC's C) Maps the methods to be committed
 * when a gui trigger is activated. Used by EnrollmentGUI.
 * 
 * @author ~miming-chan~
 * 
 */
public class GUIControl implements ActionListener {

  // Class variables.
  private Action act;
  private DataModel dataModel;
  private MainFrame gui;

  private EntityDialog[] dialogs;

  /**
   * Constructor.
   * 
   * @param gui
   *          - The gui that calls this action listener.
   */
  public <EnrollmentGUI> GUIControl(MainFrame gui) {
    this.gui = gui;
    dataModel = new DataModel();
    dialogs = new EntityDialog[5];
    dialogs[0] = new StudentDialog(gui, dataModel);
    dialogs[1] = new TeacherDialog(gui, dataModel);
    dialogs[2] = new ClassDialog(gui, dataModel);
    dialogs[3] = new GradeLevelDialog(gui, dataModel);
    dialogs[4] = new SubjectDialog(gui, dataModel);
  }

  /**
   * Determines the action performed when a button from the EnrollmentGUI is
   * clicked. Exceptions are caught here.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {

      act = Action.valueOf(e.getActionCommand());

      switch (act) {
      case EXIT:
        System.exit(0);
        break;
      case INSERT:
        tryInsert();
        break;
      case UPDATE:
        tryUpdate();
        break;
      case DELETE:
        tryDeleteRow();
        break;
      default:
        break;
      }

    } catch (IllegalArgumentException ex) {
      PopUp.UNKNOWN_COMMAND.show();
    } catch (SQLException ex) {
      PopUp.SQL_ERROR.show();
      ex.printStackTrace();
    }
  }

  private void tryInsert() {
    int activeTab = gui.getActivePane();
    dialogs[activeTab].setActionType(Action.INSERT);
    dialogs[activeTab].setVisible(true);
  }

  private void tryUpdate() throws SQLException {
    int activeTab = gui.getActivePane();
    DataTable dt = gui.getTable(activeTab);
    int rowNum = dt.getSelectedRow();

    if (rowNum == -1)
      return;
    dialogs[activeTab].setFieldData((String) dt.getValueAt(rowNum, 0));
    dialogs[activeTab].setActionType(Action.UPDATE);
    dialogs[activeTab].setVisible(true);
  }

  private void tryDeleteRow() throws SQLException {
    int activeTab = gui.getActivePane();
    DataTable dt = gui.getTable(activeTab);
    int rowNum = dt.getSelectedRow();

    if (JOptionPane.showConfirmDialog(gui, "Are you sure you want to delete?",
        "Confirm Delete", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
      return;

    if (rowNum == -1) {
      PopUp.NO_ROW_SELECTED.show();
      return;
    }
    dataModel.delete(activeTab, (String) dt.getValueAt(rowNum, 0));
    refreshTables(activeTab);
    PopUp.SQL_EXECUTED.show();
  }

  public void loadInitialDataTo(MainFrame mainFrame) {
    try {
      gui.refreshResults(Action.SHOW_CLASSES, dataModel.showClasses());
      gui.refreshResults(Action.SHOW_STUDENTS, dataModel.showStudents());
      gui.refreshResults(Action.SHOW_SUBJECTS, dataModel.showSubjects());
      gui.refreshResults(Action.SHOW_TEACHERS, dataModel.showTeachers());
      gui.refreshResults(Action.SHOW_GRADE_LEVELS, dataModel.showGradeLevels());
    } catch (SQLException e) {
      PopUp.SQL_ERROR.show();
      e.printStackTrace();
    }
  }

  public void refreshTables(int activeTab) throws SQLException {
    switch (activeTab) {
    case 2:
      gui.refreshResults(Action.SHOW_CLASSES, dataModel.showClasses());
      break;
    case 0:
      gui.refreshResults(Action.SHOW_STUDENTS, dataModel.showStudents());
      break;
    case 4:
      gui.refreshResults(Action.SHOW_SUBJECTS, dataModel.showSubjects());
      break;
    case 1:
      gui.refreshResults(Action.SHOW_TEACHERS, dataModel.showTeachers());
      break;
    case 3:
      gui.refreshResults(Action.SHOW_GRADE_LEVELS, dataModel.showGradeLevels());
      break;
    default:
      PopUp.UNKNOWN_COMMAND.show();
    }
  }

}
