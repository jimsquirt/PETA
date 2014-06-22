package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import enums.Action;
import enums.Tabs;
import listeners.GUIControl;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private JButton add, update, delete, exit;
	private JPanel studentPanel, teacherPanel, classPanel, gLevelPanel, subjectPanel;
	private JScrollPane studentPaneScroller, teacherPaneScroller, classPaneScroller, 
	gLevelPaneScroller, subjectPaneScroller;
	private JTabbedPane tabPane;

	private DataTable studentTable, teacherTable, classTable, gLevelTable, subjectTable;

	private Object[][] studentData, teacherData, classData, gLevelData, subjectData;
	private String[] studentTableColumnNames = {"Student ID", "Name", "Class", "Birthdate", "Guardian", "Guardian Contact"};
	private String[] teacherTableColumnNames = {"Teacher ID", "Name", "Birthdate", "Contact Information"};
	private String[] classTableColumnNames = {"Class Code", "Grade Level", "Teacher-in-Charge", "Start Time", "End Time"};
	private String[] gLevelTableColumnNames = {"Grade Level", "Description"};
	private String[] subjectTableColumnNames = {"Subject Code", "Subject Name", "Grade Level"};

	private GUIControl controller;

	public MainFrame() {
		setProperties();
		initializeButton();
		setActionCommands();
		initializeTable();
		initializePanels();
		addListeners();
		loadInitialData();
	}

	private void setProperties() {
		this.setSize((int)(screenSize.getWidth()*0.75), (int)(screenSize.getHeight()*0.75));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("PET Academy (Pre-school Educational Training Academy)");
	}

	private void initializeTable() {
		studentData = new Object[0][0];
		teacherData = new Object[0][0];
		classData = new Object[0][0];
		gLevelData = new Object[0][0];
		studentData = new Object[0][0];

		studentTable = new DataTable(studentData, studentTableColumnNames);
		teacherTable = new DataTable(teacherData, teacherTableColumnNames);
		classTable = new DataTable(classData, classTableColumnNames);
		gLevelTable = new DataTable(gLevelData, gLevelTableColumnNames);
		subjectTable = new DataTable(subjectData, subjectTableColumnNames);
	}

	private void initializePanels() {
		tabPane = new JTabbedPane();
		tabPane.setBounds(0, 0, this.getWidth()-135, this.getHeight()-20);

		add(tabPane);

		studentPanel = new JPanel();
		studentPanel.setLayout(null);
		studentPanel.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		studentPaneScroller = new JScrollPane(studentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentPaneScroller.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		studentPanel.add(studentPaneScroller, BorderLayout.CENTER);

		teacherPanel = new JPanel();
		teacherPanel.setLayout(null);
		teacherPanel.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		teacherPaneScroller = new JScrollPane(teacherTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		teacherPaneScroller.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		teacherPanel.add(teacherPaneScroller, BorderLayout.CENTER);

		classPanel = new JPanel();
		classPanel.setLayout(null);
		classPanel.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		classPaneScroller = new JScrollPane(classTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		classPaneScroller.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		classPanel.add(classPaneScroller, BorderLayout.CENTER);

		gLevelPanel = new JPanel();
		gLevelPanel.setLayout(null);
		gLevelPanel.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		gLevelPaneScroller = new JScrollPane(gLevelTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gLevelPaneScroller.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		gLevelPanel.add(gLevelPaneScroller, BorderLayout.CENTER);

		subjectPanel = new JPanel();
		subjectPanel.setLayout(null);
		subjectPanel.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		subjectPaneScroller = new JScrollPane(subjectTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		subjectPaneScroller.setBounds(0, 0, tabPane.getWidth(), tabPane.getHeight());

		subjectPanel.add(subjectPaneScroller, BorderLayout.CENTER);

		tabPane.addTab("Students", studentPanel);
		tabPane.addTab("Teachers", teacherPanel);
		tabPane.addTab("Class Schedules", classPanel);
		tabPane.addTab("Grade Levels", gLevelPanel);
		tabPane.addTab("Subjects", subjectPanel);

	}

	private void initializeButton() {
		add = new JButton("ADD");
		add.setBounds(this.getWidth()-115, (this.getHeight()/2) - 100, 85, 25);

		update = new JButton("UPDATE");
		update.setBounds(this.getWidth()-115, (this.getHeight()/2) - 65, 85, 25);

		delete = new JButton("DELETE");
		delete.setBounds(this.getWidth()-115, (this.getHeight()/2) - 30, 85, 25);

		exit = new JButton("EXIT");
		exit.setBounds(this.getWidth()-115, this.getHeight() - 110, 85, 25);

		add(add);
		add(update);
		add(delete);
		add(exit);
	}

	private void addListeners() {
		//add ActionListener
		controller = new GUIControl(this);
    exit.addActionListener(controller);
    add.addActionListener(controller);
    update.addActionListener(controller);
    delete.addActionListener(controller);
		// TODO add Button ActionListeners here
	}

	private void setActionCommands() {
    exit.setActionCommand(Action.EXIT.name());
    add.setActionCommand(Action.INSERT.name());
    update.setActionCommand(Action.UPDATE.name());
    delete.setActionCommand(Action.DELETE.name());
		// TODO add Button ActionCommands here
	}

	private void loadInitialData() {
		controller.loadInitialDataTo(this);
	}

	public void refreshResults(Action act, TableData td) {
		switch (act) {
		case SHOW_CLASSES:
			refreshTable(classTable, classTableColumnNames, td);
			break;
		case SHOW_TEACHERS:
			refreshTable(teacherTable, teacherTableColumnNames, td);
			break;
		case SHOW_STUDENTS:
			refreshTable(studentTable, studentTableColumnNames, td);
			break;
		case SHOW_GRADE_LEVELS:
			refreshTable(gLevelTable, gLevelTableColumnNames, td);
			break;
		case SHOW_SUBJECTS:
			refreshTable(subjectTable, subjectTableColumnNames, td);
			break;
		default:
			break;
		}
	}

	private void refreshTable(DataTable t, String[] cols, TableData td) {
		t.changeData(td.generate2D(), cols);
	}
	
  public int getActivePane() {
    return tabPane.getSelectedIndex();
  }

  public int getSelected() {
    return tabPane.getSelectedIndex();
  }

  public DataTable getTable(int index) {
    if (index == Tabs.STUDENT.getIndex()) {
      return studentTable;
    } else if (index == Tabs.TEACHERS.getIndex()) {
      return teacherTable;
    } else if (index == Tabs.CLASS.getIndex()) {
      return classTable;
    } else if (index == Tabs.GRADE_LEVEL.getIndex()) {
      return gLevelTable;
    } else if (index == Tabs.SUBJECT.getIndex()) {
      return subjectTable;
    }
    return null;
  }
  
}