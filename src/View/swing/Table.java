/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.swing;

import java.awt.Color;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import View.model.StatusType;

/**
 *
 * @author trong
 */
public class Table extends JTable{

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(o + "");
//                if (column == 4) {
//                    header.setHorizontalAlignment(JLabel.CENTER);
//                   
//                }

                return header;
            }
            
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                if (i1 != 4) {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if (selected) {
                        com.setForeground(new Color(15, 89, 140));
                    } else {
                        com.setForeground(new Color(102, 102, 102));
                    }
                    return com;
                } else {
//                    this.setHorizontalAlignment( JLabel.CENTER );
//                    getColumnModel().getColumn(4).setCellRenderer(this);
//                	System.out.println(o);
                    StatusType type = (StatusType) o;
                    CellStatus cell = new CellStatus(type);
                    return cell;
                }
            }
        });
        
    }
     public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
     public void addArray(Object[][] dataTable) {
         DefaultTableModel model = (DefaultTableModel) getModel();
         model.setRowCount(0);
         for (int i = 0; i < dataTable.length; i++) {
			model.addRow(dataTable[i]);
		}
     }
}
