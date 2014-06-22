package main;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class DataTable extends JTable{
	
	private DefaultTableModel tableModel;

	public DataTable(Object[][] data, String[] columnNames) {
		
		tableModel = new DefaultTableModel(data, columnNames){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
	
		this.getTableHeader().setReorderingAllowed(false);
		this.setModel(tableModel);
		this.setRowSorter(new TableRowSorter<>(tableModel));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void changeData(Object[][] data, String[] columnNames) {
		tableModel.setDataVector(data, columnNames);
		tableModel.fireTableDataChanged();
	}
}
