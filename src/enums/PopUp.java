package enums;
import javax.swing.JOptionPane;

/**
 * Contains all pop-up dialogs to be used for error-handling or info prompt.
 * 
 * @author ~miming-chan~
 *
 */
public enum PopUp {
  // For parameter details, refer to constructor.
  DB_NOT_AVAILABLE("Database Server Not Available!", "Error!", JOptionPane.ERROR_MESSAGE),
  UNKNOWN_COMMAND("System Error: Unknown Action Command.", "Error!", JOptionPane.ERROR_MESSAGE),
  SQL_ERROR("SQL Error: Error Executing SQL Statement.", "Error!", JOptionPane.ERROR_MESSAGE),
  SQL_EXECUTED("Data has been updated.", "Success", JOptionPane.INFORMATION_MESSAGE),
  NO_ROW_SELECTED ("No row has been selected.", "Warning!", JOptionPane.WARNING_MESSAGE),
  INVALID_MANDATORY ("A mandatory field has been left blank.", "Warning!", JOptionPane.WARNING_MESSAGE)
  ;
  
  
  /*  ENUM DEFINITION  */
  
  // Enum Variables
  private String errMsg;
  private String title;
  private int msgType;

  /**
   * Constructor.
   * 
   * @param errMsg - Message to be displayed in the dialog box.
   * @param title - Title of the dialog box.
   * @param msgType - Type of message. See {@link JOptionPane} constants for details.
   */
  PopUp (String errMsg, String title, int msgType) {
    this.errMsg = errMsg;
    this.title = title;
    this.msgType = msgType;
  }
  
  /**
   * Displays pop-up dialog box on screen.
   */
  public void show() {
    JOptionPane.showMessageDialog(null, errMsg, title, msgType);
  }
  
  public void show(String str) {
    JOptionPane.showMessageDialog(null, errMsg+"\n"+str, title, msgType);
  }
  
}
