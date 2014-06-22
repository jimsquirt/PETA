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
public class ClassDialog extends EntityDialog {
	 private JLabel class_idLabel;
	 private JLabel gr_lvlLabel;
	 private JLabel teacher_idLabel;
	 private JLabel start_timeLabel;
	 private JLabel end_timeLabel;
	 private JTextField class_idText;
	 private JTextField gr_lvlText;
	 private JTextField teacher_idText;
	 private JTextField start_timeText;
	 private JTextField end_timeText;
	 private JButton addClass;
	 
	public ClassDialog(MainFrame parent, DataModel dm) {
		super(parent, dm);
		this.setVisible(false);
		this.setModal(true);
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		addComponents();
    setActionHandler(new DialogActionHandler(this, dm, Action.SHOW_CLASSES));
	}
	
	private void addComponents() {
		class_idLabel = new JLabel("Class ID:");
		gr_lvlLabel = new JLabel("Grade Level:");
		teacher_idLabel = new JLabel("Teacher ID:");
		start_timeLabel = new JLabel("Start Time:");
		end_timeLabel = new JLabel("End Time:");
		class_idText = new JTextField("");
		gr_lvlText = new JTextField("");
		teacher_idText = new JTextField("");
		start_timeText = new JTextField("");
		end_timeText = new JTextField("");
		addClass = new JButton("Add Class");
	     
	     add(class_idLabel);
	     class_idLabel.setPreferredSize(new Dimension(120,20));
	     add(class_idText);
	     class_idText.setPreferredSize(new Dimension(200,20));
	     add(gr_lvlLabel);
	     gr_lvlLabel.setPreferredSize(new Dimension(120,20));
	     add(gr_lvlText);
	     gr_lvlText.setPreferredSize(new Dimension(200,20));
	     add(teacher_idLabel);
	     teacher_idLabel.setPreferredSize(new Dimension(120,20));
	     add(teacher_idText);
	     teacher_idText.setPreferredSize(new Dimension(200,20));
	     add(start_timeLabel);
	     start_timeLabel.setPreferredSize(new Dimension(120,20));
	     add(start_timeText);
	     start_timeText.setPreferredSize(new Dimension(200,20));
	     add(end_timeLabel);
	     end_timeLabel.setPreferredSize(new Dimension(120,20));
	     add(end_timeText);
	     end_timeText.setPreferredSize(new Dimension(200,20));
	     add(addClass);
	     addClass.setPreferredSize(new Dimension(200,20));
	     
	     addMandatories(
         class_idText,
         gr_lvlText,
         teacher_idText,
         start_timeText
       );
	
	}

  @Override
  protected void changeButton() {
    if (type==Action.UPDATE)
      addClass.setText("Update Class");
    else if (type==Action.INSERT)
      addClass.setText("Add Class");
  }

  @Override
  protected void clearFields() {
    class_idText.setText("");
    gr_lvlText.setText("");
    teacher_idText.setText("");
    start_timeText.setText("");
    end_timeText.setText("");
  }

  @Override
  protected Query getInsertQuery() {
    return Query.INSERT_CLASS;
  }

  @Override
  protected Query getUpdateQuery() {
    return Query.UPDATE_CLASS;
  }

  @Override
  protected JButton getActionButton() {
    return addClass;
  }

  @Override
  public void setFieldData(String id) throws SQLException {
    DataModel dm = new DataModel();
    String[]  data = dm.getClass(id);
    class_idText.setText(data[0]);
    gr_lvlText.setText(data[1]);
    teacher_idText.setText(data[2]);
    start_timeText.setText(data[3]);
    end_timeText.setText(data[4]);
  }
  
  /**
   * Returns field data in ff order:
   * class id, gr lvl, teacher id, start time, end time
   */
  @Override
  public String[] getFieldData() {
    return new String[] {
      class_idText.getText(),
      gr_lvlText.getText(),
      teacher_idText.getText(),
      start_timeText.getText(),
      end_timeText.getText().isEmpty()? "00:00:00" : end_timeText.getText()
    };
  }
  
}
