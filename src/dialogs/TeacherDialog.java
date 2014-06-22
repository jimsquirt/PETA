package dialogs;

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
import enums.Action;
import enums.Query;

@SuppressWarnings("serial")
public class TeacherDialog extends EntityDialog {
  private JLabel l_nameLabel;
  private JLabel f_nameLabel;
  private JLabel miLabel;
  private JLabel birthdateLabel;
  private JLabel contact_numLabel;
  private JTextField l_nameText;
  private JTextField f_nameText;
  private JTextField miText;
  private JTextField birthdateText;
  private JTextField contact_numText;
  private JButton addTeacher;
  private String teachId;

  public TeacherDialog(MainFrame parent, DataModel dm) {
    super(parent, dm);
    this.setVisible(false);
    this.setModal(true);
    this.setSize(350, 250);
    this.setLocationRelativeTo(null);
    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    addComponents();
    setActionHandler(new DialogActionHandler(this, dm, Action.SHOW_TEACHERS));
  }

  private void addComponents() {
    l_nameLabel = new JLabel("Last Name:");
    f_nameLabel = new JLabel("First Name:");
    miLabel = new JLabel("Middle Initial:");
    birthdateLabel = new JLabel("Birthdate:");
    contact_numLabel = new JLabel("Contact #:");
    l_nameText = new JTextField("");
    f_nameText = new JTextField("");
    miText = new JTextField("");
    birthdateText = new JTextField("");
    contact_numText = new JTextField("");
    addTeacher = new JButton("Add Teacher");

    add(l_nameLabel);
    l_nameLabel.setPreferredSize(new Dimension(120, 20));
    add(l_nameText);
    l_nameText.setPreferredSize(new Dimension(200, 20));
    add(f_nameLabel);
    f_nameLabel.setPreferredSize(new Dimension(120, 20));
    add(f_nameText);
    f_nameText.setPreferredSize(new Dimension(200, 20));
    add(miLabel);
    miLabel.setPreferredSize(new Dimension(120, 20));
    add(miText);
    miText.setPreferredSize(new Dimension(200, 20));
    add(birthdateLabel);
    birthdateLabel.setPreferredSize(new Dimension(120, 20));
    add(birthdateText);
    birthdateText.setPreferredSize(new Dimension(200, 20));
    add(contact_numLabel);
    contact_numLabel.setPreferredSize(new Dimension(120, 20));
    add(contact_numText);
    contact_numText.setPreferredSize(new Dimension(200, 20));
    add(addTeacher);
    addTeacher.setPreferredSize(new Dimension(200, 20));

    addMandatories(l_nameText, f_nameText, miText, birthdateText,
        contact_numText);

  }

  @Override
  protected void changeButton() {
    if (type == Action.UPDATE)
      addTeacher.setText("Update Teacher");
    else if (type == Action.INSERT)
      addTeacher.setText("Add Teacher");
  }

  @Override
  protected void clearFields() {
    l_nameText.setText("");
    f_nameText.setText("");
    miText.setText("");
    birthdateText.setText("");
    contact_numText.setText("");
  }

  @Override
  protected JButton getActionButton() {
    return addTeacher;
  }

  @Override
  protected Query getInsertQuery() {
    return Query.INSERT_TEACHER;
  }

  @Override
  protected Query getUpdateQuery() {
    return Query.UPDATE_TEACHER;
  }

  @Override
  public void setFieldData(String id) throws SQLException {
    teachId = id;
    DataModel dm = new DataModel();
    String[] data = dm.getTeacher(teachId);
    l_nameText.setText(data[0]);
    f_nameText.setText(data[1]);
    miText.setText(data[2]);
    birthdateText.setText(data[3]);
    contact_numText.setText(data[4]);
  }

  /**
   * Returns field data in the ff order: lname, fname, mi, bday, contact num
   */
  @Override
  public String[] getFieldData() {
    if (type == Action.INSERT)
      return new String[] { l_nameText.getText(), f_nameText.getText(),
          miText.getText(), birthdateText.getText(), contact_numText.getText() };
    return new String[] { teachId, l_nameText.getText(), f_nameText.getText(),
        miText.getText(), birthdateText.getText(), contact_numText.getText() };
  }

}
