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
public class GradeLevelDialog extends EntityDialog {
  private JLabel descriptionLabel;
  private JLabel gr_lvlLabel;
  private JTextField descriptionText;
  private JTextField gr_lvlText;
  private JButton addGradeLevel;

  public GradeLevelDialog(MainFrame parent, DataModel dm) {
    super(parent, dm);
    this.setVisible(false);
    this.setModal(true);
    this.setSize(350, 250);
    this.setLocationRelativeTo(null);
    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    addComponents();
    setActionHandler(new DialogActionHandler(this, dm, Action.SHOW_GRADE_LEVELS));
  }

  private void addComponents() {
    gr_lvlLabel = new JLabel("Grade level:");
    descriptionLabel = new JLabel("Description:");
    gr_lvlText = new JTextField("");
    descriptionText = new JTextField("");
    addGradeLevel = new JButton("Add Grade Level");

    add(gr_lvlLabel);
    gr_lvlLabel.setPreferredSize(new Dimension(120, 20));
    add(gr_lvlText);
    gr_lvlText.setPreferredSize(new Dimension(200, 20));
    add(descriptionLabel);
    descriptionLabel.setPreferredSize(new Dimension(120, 20));
    add(descriptionText);
    descriptionText.setPreferredSize(new Dimension(200, 20));
    add(addGradeLevel);
    addGradeLevel.setPreferredSize(new Dimension(200, 20));
    
    addMandatories(gr_lvlText, descriptionText);

  }

  @Override
  protected void changeButton() {
    if (type == Action.UPDATE)
      addGradeLevel.setText("Update Grade Level");
    else if (type == Action.INSERT)
      addGradeLevel.setText("Add Grade Level");
  }

  @Override
  protected void clearFields() {
    gr_lvlText.setText("");
    descriptionText.setText("");
  }

  @Override
  protected JButton getActionButton() {
    return addGradeLevel;
  }

  @Override
  protected Query getInsertQuery() {
    return Query.INSERT_GRADE_LEVEL;
  }

  @Override
  protected Query getUpdateQuery() {
    return Query.UPDATE_GRADE_LEVEL;
  }

  @Override
  public void setFieldData(String id) throws SQLException {
    DataModel dm = new DataModel();
    String[] data = dm.getGradeLevel(id);
    gr_lvlText.setText(data[0]);
    descriptionText.setText(data[1]);
  }

  /**
   * Returns field data in ff order: gr lvl, description
   */
  @Override
  public String[] getFieldData() {
    return new String[] { gr_lvlText.getText(), descriptionText.getText() };
  }

}
