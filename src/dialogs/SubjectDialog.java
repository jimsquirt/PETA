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
public class SubjectDialog extends EntityDialog {
  private JLabel subject_codeLabel;
  private JLabel subject_nameLabel;
  private JLabel gr_lvlLabel;
  private JTextField subject_codeText;
  private JTextField subject_nameText;
  private JTextField gr_lvlText;
  private JButton addSubject;

  public SubjectDialog(MainFrame parent, DataModel dm) {
    super(parent, dm);
    this.setVisible(false);
    this.setModal(true);
    this.setSize(350, 250);
    this.setLocationRelativeTo(null);
    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    addComponents();
    setActionHandler(new DialogActionHandler(this, dm, Action.SHOW_SUBJECTS));
  }

  private void addComponents() {
    subject_codeLabel = new JLabel("Subject Code:");
    subject_nameLabel = new JLabel("Subject Name:");
    gr_lvlLabel = new JLabel("Grade Level:");
    subject_codeText = new JTextField("");
    subject_nameText = new JTextField("");
    gr_lvlText = new JTextField("");
    addSubject = new JButton("Add Subject");

    add(subject_codeLabel);
    subject_codeLabel.setPreferredSize(new Dimension(120, 20));
    add(subject_codeText);
    subject_codeText.setPreferredSize(new Dimension(200, 20));
    add(subject_nameLabel);
    subject_nameLabel.setPreferredSize(new Dimension(120, 20));
    add(subject_nameText);
    subject_nameText.setPreferredSize(new Dimension(200, 20));
    add(gr_lvlLabel);
    gr_lvlLabel.setPreferredSize(new Dimension(120, 20));
    add(gr_lvlText);
    gr_lvlText.setPreferredSize(new Dimension(200, 20));
    add(addSubject);
    addSubject.setPreferredSize(new Dimension(200, 20));

    addMandatories(subject_codeText, subject_nameText, gr_lvlText);

  }

  @Override
  protected void changeButton() {
    if (type == Action.UPDATE)
      addSubject.setText("Update Subject");
    else if (type == Action.INSERT)
      addSubject.setText("Add Subject");
  }

  @Override
  protected void clearFields() {
    subject_codeText.setText("");
    subject_nameText.setText("");
    gr_lvlText.setText("");
  }

  @Override
  protected JButton getActionButton() {
    return addSubject;
  }

  @Override
  protected Query getInsertQuery() {
    return Query.INSERT_SUBJECT;
  }

  @Override
  protected Query getUpdateQuery() {
    return Query.UPDATE_SUBJECT;
  }

  @Override
  public void setFieldData(String id) throws SQLException {
    DataModel dm = new DataModel();
    String[] data = dm.getSubject(id);
    subject_codeText.setText(data[0]);
    subject_nameText.setText(data[1]);
    gr_lvlText.setText(data[2]);
  }

  /**
   * Returns field data in the ff order: subj code, subj name, g lvl
   */
  @Override
  public String[] getFieldData() {
    return new String[] { subject_codeText.getText(),
        subject_nameText.getText(), gr_lvlText.getText() };
  }

}
