package View.viewMain;

import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	public MyTableModel() {
		
	}
	public MyTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}

	public boolean isCellEditable(int row, int column) {
		Class columnClass = getColumnClass(column);
	    if (columnClass == Boolean.class || this.getColumnName(column).equals("Chi tiết")) {
	    	return true;
	    }
//		return columnClass != ImageIcon.class && columnClass != Date.class
//				&& columnClass != JButton.class;
		return false;
	}

}
