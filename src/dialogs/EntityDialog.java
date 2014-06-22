package dialogs;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import listeners.DialogActionHandler;
import main.DataModel;
import main.MainFrame;
import enums.Action;
import enums.Query;

public abstract class EntityDialog extends JDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  protected Action type;
  protected MainFrame mf;
  protected JTextField[] mandatories;
  
  public EntityDialog(MainFrame parent, DataModel dm) {
    super(parent);
    mf = parent;
    type = Action.INSERT;
  }
  
  public MainFrame getMainFrame() {
    return mf;
  }
  
  public void setActionType(Action t) {
    type = t;
    if (t == Action.INSERT) clearFields();
    changeButton();
  }
  
  public Query getQueryType() {
    if (type==Action.INSERT)
      return getInsertQuery();
    return getUpdateQuery();
  }
  
  public boolean validMandatories() {
    for (JTextField field : mandatories) {
      if (field.getText().isEmpty()) return false;
    }
    return true;
  }
  
  protected void setActionHandler(DialogActionHandler ah) {
    getActionButton().addActionListener(ah);
  }
  
  protected void addMandatories(JTextField... m) {
    mandatories = m;
  }
  
  public abstract void setFieldData(String id) throws SQLException;
  
  public abstract String[] getFieldData();
  
  protected abstract JButton getActionButton();
  
  protected abstract Query getInsertQuery();
  
  protected abstract Query getUpdateQuery();

  protected abstract void changeButton();
  
  protected abstract void clearFields();
  
}
