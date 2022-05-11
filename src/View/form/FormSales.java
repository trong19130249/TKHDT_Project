/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.form;


import java.awt.Color;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.DashboardController;
import Controller.FormSalesController;
import View.component.Product;
import View.event.EventAction;
import View.event.EventProduct;
import View.swing.ScrollBar;
import View.viewMain.AddAnOrder;
import View.viewMain.MnCustomer;
import View.viewMain.MnOrders;
import model.ASanPham;
import model.DonHangModelInterface;
import model.KhachHang;
import model.Observer;


/**
 *
 * @author trong
 */
public class FormSales extends javax.swing.JPanel implements Observer{
    /**
     * Creates new form FormPay
     */
	private FormSalesController con;
	private DonHangModelInterface data;
    public void setEvent(EventProduct event) {
        this.event = event;
    }
    private EventProduct event;
    EventAction eventAction;
    public FormSales(DonHangModelInterface data,FormSalesController con) {
    	this.con = con;
    	this.data =data;
        initComponents();
		data.registerObserver(this);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        List<String> listUrl = new ArrayList<>();
         listUrl.add("/View/img_product/gach.jpg");
         eventAction = new EventAction() {

			@Override
			public void delete(ASanPham product) {
				con.removeProduct(product.getId());
				
			}

			@Override
			public void update(ASanPham product) {
				// TODO Auto-generated method stub
				
			}
        };
//        tableCart1.setAutoCreateRowSorter(true);
//        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableCart1.getModel());
//        tableCart1.setRowSorter(sorter);
        tableCart1.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getFirstRow()>=0) {
					double soLuongMuonMua;
					if ( e.getColumn()==4&&e.getFirstRow()>=0
							&&(soLuongMuonMua=(double) tableCart1.getValueAt(e.getFirstRow(), e.getColumn()))
							!=con.getQuality((String) tableCart1.getValueAt(e.getFirstRow(), 1))) {
						con.setQuality((String) tableCart1.getValueAt(e.getFirstRow(), 1), soLuongMuonMua);
//						System.out.println(e.getFirstRow()+" "+e.getColumn());
						tableCart1.setValueAt(con.getQuality((String) tableCart1.getValueAt(e.getFirstRow(), 1)),e.getFirstRow(),e.getColumn());
					}

					
				}
				textTotal.setText(formatDecimal(totalPay()));

				
			}
          });
       
//        for (int i = 0; i < 1; i++) {
//            tableCart1.addRow(new ASanPham("sp02", "cat 01", "", "Vien", 5555, 55, "cat", listUrl, 5000).toRowTable(eventAction,9));
//        }
//        for (int i = 0; i < 4; i++) {
//            tableCart1.addRow(new ASanPham("sp01", "cat 01111", "", "Vien", 111, 55, "cat", listUrl, 5000).toRowTable(eventAction,4));
//        }
//        addDetailsOrder(new ASanPham("sp02", "cat 01", "", "Vien", 5555, 55, "cat", listUrl, 5000), 5);
    }
    public String formatDecimal(double money) {
    	DecimalFormat formatter = new DecimalFormat("###,###,###");
    	return formatter.format(Math.round(money))+" VNĐ";
    }
    public void resetView() {
    	DefaultTableModel modelTable = (DefaultTableModel) tableCart1.getModel();
		modelTable.setRowCount(0);
		jCheckBox1.setSelected(false);
		jTextArea1.setText("");
		
    }
    public void removeRowTable(String id) {
		DefaultTableModel dtm =(DefaultTableModel) tableCart1.getModel();
    	for (int i = 0; i < dtm.getRowCount(); i++) {
			if (dtm.getValueAt(i, 1).equals(id)) {
				//de day khoi bi loi
				if (tableCart1.isEditing())
					tableCart1.getCellEditor().stopCellEditing();
				dtm.removeRow(i);
//				tableCart1.addNotify();
//				tableCart1.setModel(dtm);
		        break;
			}
		}

	}
    public double totalPay() {
    	double result =0;
    	for (int i = 0; i < tableCart1.getRowCount(); i++) {
    		double quality = (double)tableCart1.getValueAt(i, 4);
    		String strPrice =(String) tableCart1.getValueAt(i, 5);
    		double price = Double.parseDouble(strPrice.substring(0, strPrice.length()-2));
    		result+=quality*price ;
    	}
    	return result;
    }
    public void addEventBtnAdd(ActionListener event) {
    	jbtAddCustomer.addActionListener(event);
    }
    public void addDetailsOrder(ASanPham sanPham,double quanlity) {
    	boolean haveProduct =false;
    	for (int i = 0; i < tableCart1.getRowCount(); i++) {
			if (tableCart1.getValueAt(i, 1).equals(sanPham.getId())) {
		        tableCart1.setValueAt(quanlity,i, 4);
		        haveProduct = true;
			}
		}
    	if (!haveProduct) {
    		tableCart1.addRow(sanPham.toRowTable(eventAction, quanlity));
		}
    }
    public void removeAllProductView() {
    	panelItem.removeAll();
    	panelItem.repaint();
        panelItem.revalidate();
    }
    public void addCombobox(KhachHang k) {
    	jComboBoxCustomer.addItem(k);
    }
    public void removeAllCombobox() {
    	jComboBoxCustomer.removeAllItems();

    }
    
    public void addProduct(final ASanPham data){
    	final Product product = new Product();
        product.setData(data);
        product.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    event.productClick(product, data);
//                    System.out.println(data.getTenSanPham());
                }
            }
            
        });
        panelItem.add(product);
        panelItem.repaint();
        panelItem.revalidate();
    }
    public void setSelected(Component item){
        for (Component com : panelItem.getComponents()) {
            Product p = (Product)com;
            if (p.isSelected()) {
                p.setSelected(false);
            }
        }
        ((Product)item).setSelected(true);
        con.pushOrdersCache(((Product)item).getData());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        panelItem = new View.swing.PanelItem();
        panelBorder1 = new View.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCart1 = new View.swing.TableCart();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textPay = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jbtAddCustomer = new javax.swing.JButton();
        jComboBoxCustomer = new javax.swing.JComboBox<>();
//        jComboBoxCustomer.addItemListener(new ItemListener() {
//			
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				 if (e.getStateChange() == ItemEvent.SELECTED) {
//			          KhachHang k = (KhachHang)e.getItem();
//			          con.setIdKhachhang(k.getMaSo());
//			       }
//			}
//		});
        scroll.setBorder(null);
        scroll.setViewportView(panelItem);

        tableCart1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hành động", "ID", "Tên", "Đơn vị", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCart1.setToolTipText("");
        jScrollPane1.setViewportView(tableCart1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Tổng tiền hàng");

        textTotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        textTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textTotal.setEditable(false);
        textTotal.setText("0 đ");
        textTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTotalActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Khách trả");

        textPay.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        textPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textPay.setText("");
        textPay.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTotal1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Giao hàng");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton2.setBackground(new java.awt.Color(0, 0, 255));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("THANH TOÁN");
        jButton2.setOpaque(false);

        jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Frame f=new JFrame();  
				  
				if (jComboBoxCustomer.getSelectedIndex()>0) {
					try {
						String inputPay = textPay.getText(); 
						double payCustomer=0;
						if (!inputPay.isEmpty()) {
							payCustomer= Double.parseDouble(inputPay);
						}
						KhachHang kh = (KhachHang)jComboBoxCustomer.getSelectedItem();
						con.setKhachhang(kh);
						if (tableCart1.getRowCount()>0) {
							int input = JOptionPane.showOptionDialog(f, kh.stringShow()+"\n Với số tiền hoá đơn "+totalPay()+"\n Số tiền còn lại "+(totalPay()-payCustomer), "Xác nhận đơn hàng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
							if(input == JOptionPane.OK_OPTION)
							{
								if(con.addDonHang(payCustomer)) {
									
									
									JOptionPane.showMessageDialog(f,"Thêm đơn hàng thành công");  
								}else {
									JOptionPane.showMessageDialog(f,"Thêm đơn hàng thất bại");  
								}
								
							}
						}else {
							JOptionPane.showMessageDialog(f,"Vui lòng chọn sản phẩm để mua");  
						}
						
						
						
					} catch (NumberFormatException e2) {
						 JOptionPane.showMessageDialog(f,"Vui lòng nhập tiền khách trả hợp lệ");  
					}
					
				}else {
					
					 JOptionPane.showMessageDialog(f,"Vui lòng chọn khách hàng");  
				}
			}
		});
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(73, 73, 73)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textPay)
                                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtAddCustomer.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "Button-Add-icon.png")));
        jbtAddCustomer.setText("Thêm khách hàng");
        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jComboBoxCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)

                        .addComponent(jbtAddCustomer))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtAddCustomer)
                        .addComponent(jComboBoxCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        
    private void textTotal1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void textTotalActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }
    @Override
	public void update() {
    	con.updateListProduct();
    	con.updateListCustomer();
	}

    // Variables declaration - do not modify                     
    private javax.swing.JButton jbtAddCustomer;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private View.swing.PanelBorder panelBorder1;
    private View.swing.PanelItem panelItem;
    private JScrollPane scroll;
    private View.swing.TableCart tableCart1;
    private javax.swing.JTextField textTotal;
    private javax.swing.JTextField textPay;
    private javax.swing.JComboBox<KhachHang> jComboBoxCustomer;
    // End of variables declaration                   
	
	
}
