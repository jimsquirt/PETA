package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import main.DataModel;
import dialogs.EntityDialog;
import enums.Action;
import enums.PopUp;

public class DialogActionHandler implements ActionListener {

  protected EntityDialog caller;
  protected DataModel dataModel;
  protected Action refreshAct;

  public DialogActionHandler(EntityDialog ed, DataModel dm, Action refreshAct) {
    caller = ed;
    dataModel = dm;
    this.refreshAct = refreshAct; 
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      PopUp.UNKNOWN_COMMAND.show();
      return;
    }
    if (!caller.validMandatories()) {
      PopUp.INVALID_MANDATORY.show();
      return;
    }
    try {
      dataModel.doDML(caller.getQueryType(), caller.getFieldData());
      refreshMainTable();
      PopUp.SQL_EXECUTED.show();
    } catch (SQLException ex) {
      PopUp.SQL_ERROR.show(ex.getMessage());
      ex.printStackTrace();
    } finally {
      caller.setVisible(false);
    }
  }

  private void refreshMainTable() throws SQLException {
    switch (refreshAct) {
    case SHOW_CLASSES:
      caller.getMainFrame().refreshResults(Action.SHOW_CLASSES,
          dataModel.showClasses());
      break;
    case SHOW_STUDENTS:
      caller.getMainFrame().refreshResults(Action.SHOW_STUDENTS,
          dataModel.showStudents());
      break;
    case SHOW_SUBJECTS:
      caller.getMainFrame().refreshResults(Action.SHOW_SUBJECTS,
          dataModel.showSubjects());
      break;
    case SHOW_TEACHERS:
      caller.getMainFrame().refreshResults(Action.SHOW_TEACHERS,
          dataModel.showTeachers());
      break;
    case SHOW_GRADE_LEVELS:
      caller.getMainFrame().refreshResults(Action.SHOW_GRADE_LEVELS,
          dataModel.showGradeLevels());
      break;
    default:
      PopUp.UNKNOWN_COMMAND.show();
    }
  }

}
