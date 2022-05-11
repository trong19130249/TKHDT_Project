package View.viewMain;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Controller.ChiTietHoaDonController;
import View.model.AnOrderData;
import model.CTDonHangModelInterface;
import model.Observer;

public class ButtonColumn1 extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	private JTable table;
	private JButton renderButton;
	private JButton editButton;
	private String text;
private MnOrders mn;
	public ButtonColumn1(JTable table, int column,MnOrders mn) {
		super();
		this.mn=mn;
		this.table = table;
		renderButton = new JButton();
		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (hasFocus) {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		} else if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		renderButton.setText((value == null) ? "" : value.toString());
		return renderButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		text = (value == null) ? "" : value.toString();
		editButton.setText(text);
		return editButton;
	}

	public Object getCellEditorValue() {
		return text;
	}

	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		 CTDonHangModelInterface data=new AnOrderData();
		 data.registerObserver((Observer)mn);
		 ChiTietHoaDonController controller=new ChiTietHoaDonController(data);
		 new DetailAnOrder(data,controller,(String) table.getModel().getValueAt(table.getSelectedRow(),2),mn);
	}
}
