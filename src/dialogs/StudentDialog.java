package dialogs;

import enums.Action;
import enums.Query;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import listeners.DialogActionHandler;
import main.DataModel;
import main.MainFrame;

@SuppressWarnings("serial")
public class StudentDialog extends EntityDialog {

  JTextField txtLastName, txtFirstName, txtMI, txtBday, txtGuardianName,
      txtGuardianNum, txtClassID;
  JButton btnAdd;
  JLabel lblLastName, lblFirstName, lblMI, lblBday, lblGuardianName,
      lblGuardianNum, lblClassID;
  private String studId;

  public StudentDialog(MainFrame parent, DataModel dm) {
    super(parent, dm);

    this.setVisible(false);
    this.setModal(true);
    this.setSize(350, 250);
    this.setLocationRelativeTo(null);
    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    addComponents();
    setActionHandler(new DialogActionHandler(this, dm, Action.SHOW_STUDENTS));
  }

  private void addComponents() {
    btnAdd = new JButton("ADD STUDENT");
    lblLastName = new JLabel("Last Name:");
    lblFirstName = new JLabel("First Name:");
    lblMI = new JLabel("Middle Initial:");
    lblBday = new JLabel("Birthdate:");
    lblGuardianName = new JLabel("Name of Guardian:");
    lblGuardianNum = new JLabel("Guardian Contact #:");
    lblClassID = new JLabel("Class ID:");
    txtLastName = new JTextField("");
    txtFirstName = new JTextField("");
    txtMI = new JTextField("");
    txtBday = new JTextField("");
    txtGuardianName = new JTextField("");
    txtGuardianNum = new JTextField("");
    txtClassID = new JTextField("");

    add(lblLastName);
    lblLastName.setPreferredSize(new Dimension(120, 20));
    add(txtLastName);
    txtLastName.setPreferredSize(new Dimension(200, 20));

    add(lblFirstName);
    lblFirstName.setPreferredSize(new Dimension(120, 20));
    add(txtFirstName);
    txtFirstName.setPreferredSize(new Dimension(200, 20));

    add(lblMI);
    lblMI.setPreferredSize(new Dimension(120, 20));
    add(txtMI);
    txtMI.setPreferredSize(new Dimension(200, 20));

    add(lblBday);
    lblBday.setPreferredSize(new Dimension(120, 20));
    add(txtBday);
    txtBday.setPreferredSize(new Dimension(200, 20));

    add(lblGuardianName);
    lblGuardianName.setPreferredSize(new Dimension(120, 20));
    add(txtGuardianName);
    txtGuardianName.setPreferredSize(new Dimension(200, 20));

    add(lblGuardianNum);
    lblGuardianNum.setPreferredSize(new Dimension(120, 20));
    add(txtGuardianNum);
    txtGuardianNum.setPreferredSize(new Dimension(200, 20));

    add(lblClassID);
    lblClassID.setPreferredSize(new Dimension(120, 20));
    add(txtClassID);
    txtClassID.setPreferredSize(new Dimension(200, 20));

    add(btnAdd);
    btnAdd.setPreferredSize(new Dimension(200, 20));

    addMandatories(txtLastName, txtFirstName, txtMI, txtBday, txtGuardianName,
        txtGuardianNum, txtClassID);
  }

  @Override
  protected void changeButton() {
    if (type == Action.UPDATE) {
      btnAdd.setText("Update Student");
      btnAdd.setActionCommand(Action.UPDATE_STUDENT.name());
    } else if (type == Action.INSERT) {
      btnAdd.setText("Add Student");
      btnAdd.setActionCommand(Action.INSERT_STUDENT.name());
    }
  }

  @Override
  protected void clearFields() {
    txtLastName.setText("");
    txtFirstName.setText("");
    txtMI.setText("");
    txtBday.setText("");
    txtGuardianName.setText("");
    txtGuardianNum.setText("");
    txtClassID.setText("");
  }

  @Override
  protected JButton getActionButton() {
    return btnAdd;
  }

  @Override
  protected Query getInsertQuery() {
    return Query.INSERT_STUDENT;
  }

  @Override
  protected Query getUpdateQuery() {
    return Query.UPDATE_STUDENT;
  }

  @Override
  public void setFieldData(String id) throws SQLException {
    studId = id;
    DataModel dm = new DataModel();
    String[] data = dm.getStudent(studId);
    txtLastName.setText(data[0]);
    txtFirstName.setText(data[1]);
    txtMI.setText(data[2]);
    txtBday.setText(data[3]);
    txtGuardianName.setText(data[4]);
    txtGuardianNum.setText(data[5]);
    txtClassID.setText(data[6]);
  }

  /**
   * Returns field data in ff order: last name, first name, mi, bday, g name, g
   * contact, class id
   */
  @Override
  public String[] getFieldData() {
    if (type == Action.INSERT)
      return new String[] { txtLastName.getText(), txtFirstName.getText(),
          txtMI.getText(), txtBday.getText(), txtGuardianName.getText(),
          txtGuardianNum.getText(), txtClassID.getText() };
    return new String[] { studId, txtLastName.getText(),
        txtFirstName.getText(), txtMI.getText(), txtBday.getText(),
        txtGuardianName.getText(), txtGuardianNum.getText(),
        txtClassID.getText() };
  }

}
