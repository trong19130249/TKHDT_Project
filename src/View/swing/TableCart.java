/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import View.model.ModelAction;

/**
 *
 * @author trong
 */
public class TableCart extends JTable{

    public TableCart() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        TableColumn c = null;


        setShowVerticalLines(false);
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
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            		boolean hasFocus, int row, int column) {
            	
            	if (value instanceof ModelAction) {
                    ModelAction data = (ModelAction) value;
                   Action cell = new Action(data);
                   if (isSelected) {
                       cell.setBackground(new Color(239, 244, 255));
                   } else {
                       cell.setBackground(Color.WHITE);
                   }
                   return cell;
               }else{
                   Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                   com.setBackground(Color.WHITE);
                   setBorder(noFocusBorder);
                   if (isSelected) {
                       com.setForeground(new Color(15, 89, 140));
                   } else {
                       com.setForeground(new Color(102, 102, 102));
                   }
                   return com;
               }
            }
        });
        
    }

    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        if (col == 0) {
            return new TableCellAction();
        } else {
            return super.getCellEditor(row, col);
        }
    }
    
     public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
