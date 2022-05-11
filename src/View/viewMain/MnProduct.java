package View.viewMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.VatLieuController;
import Dao.VatLieuDao;
import View.model.ProductsData;
import model.Observer;
import model.VatLieuModelInterface;

public class MnProduct extends JPanel implements Observer{
	private VatLieuController controller;
	private JButton btnAdd = new JButton("Thêm ");
	private JButton btnFix = new JButton("Sửa ");
	private JButton btnDelete = new JButton("Xóa ");
//	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238, 238, 238), 6);
	private Border lineBorder3 = new LineBorder(new Color(238, 238, 238), 8);

	private String[] filterTitle = { "Theo mã hàng hóa", "Theo tên hàng hóa", "Theo loại hàng hóa" };
	private JComboBox jcbo = new JComboBox(filterTitle);
	private JTextField jtfFilter = new JTextField();
	private JButton btFilter = new JButton(" Lọc ");
	private JButton btCancel = new JButton(" Hủy ");

	private JPanel panelBtns0 = new JPanel();
	private JPanel panelBtns1 = new JPanel();
	private JPanel panelBtns3 = new JPanel();

	private JPanel panelTable = new JPanel();
	private JPanel panelTableIn2 = new JPanel();
	private JPanel panelTableIn22 = new JPanel();
	private JPanel panelTableIn21 = new JPanel();

	private String[] columnNames;
	private Object[][] rowData;
	private JTable jTable;
	private MyTableModel tableModel;
	private JTableHeader header;
	private TableRowSorter<TableModel> sorter;
	private VatLieuModelInterface data;

	private float[] columnWidthPercentage = { 0.03f, 0.1f, 0.24f, 0.12f, 0.14f, 0.14f, 0.12f, 0.12f };
// nhận vào model và controller, sử dụng model khi cần lấy dữ liệu và sử dụng controller khi cần thay đổi dữ liệu
	public MnProduct(VatLieuModelInterface data,VatLieuController con) {
		this.data=data;
		this.controller=con;
		data.registerObserver((Observer) this);
		data.setData();
		
		this.columnNames =data.getColumnNames();
		this.rowData = data.getRowData();

		// bắt đầu set property cho button
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "Button-Add-icon.png")));
		btnAdd.setBackground(new Color(255, 255, 255));

		btnFix.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "SuaIcon.png")));
		btnFix.setBackground(new Color(255, 255, 255));

		btnDelete.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "XoaIcon.png")));
		btnDelete.setBackground(new Color(255, 255, 255));

		btFilter.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/FilterIcon.png")));
		btFilter.setBackground(Color.WHITE);
		
		btCancel.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/CancelIcon.png")));
		btCancel.setBackground(Color.WHITE);
		jtfFilter.setPreferredSize(new Dimension(200, 20));
		// kết thúc set property cho button

		// set layout cho bố cục tổng thể
		setLayout(new BorderLayout());

		// bắt đầu đặt các nút thêm, xóa, sửa và label dm hàng hóa
		panelBtns1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelBtns1.add(btnAdd);
		panelBtns1.add(btnFix);

		panelBtns1.add(btnDelete);
//		panelBtns1.setBackground(Color.WHITE);

		panelBtns3.setLayout(new BorderLayout());
		JLabel nameScreen = new JLabel("  Danh mục hàng hóa");
		nameScreen.setFont(new java.awt.Font("SansSerif", 0, 15));
		panelBtns3.add(nameScreen, BorderLayout.NORTH);
		panelBtns3.add(panelBtns0, BorderLayout.CENTER);
		panelBtns0.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelBtns0.add(panelBtns1);
		// kết thúc đặt các nút thêm, xóa, sửa và label dm hàng hóa
		add(panelBtns3, BorderLayout.NORTH);

		// bắt đầu thiết lập data, header, sorter và gọi lại resizeColumns() cho bảng
		tableModel = new MyTableModel(rowData, columnNames);
		jTable = new JTable(tableModel);

		header = jTable.getTableHeader();
		header.setReorderingAllowed(false);
		header.setBackground(new Color(0, 0, 0));
		header.setForeground(new Color(0, 0, 0));
		sorter = new TableRowSorter<TableModel>(jTable.getModel());
		jTable.setRowSorter(sorter);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.LEFT);
		for (int x = 0; x < jTable.getColumnCount(); x++) {
			if (jTable.getColumnModel().getColumn(x).getClass() != null && x != 0) {
				jTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
			}
		}

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizeColumns();
			}
		});

		resizeColumns();
		// kết thúc thiết lập data, header, sorter và gọi lại resizeColumns() cho bảng

		// bắt đầu setlayout của panelTable thành gridbag và thêm các component vào
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		panelTable.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.9;
		c.weighty = 1.0;
		JScrollPane crollpane = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(crollpane, c);
//		crollpane.setBorder(lineBorder);
		panelTable.add(crollpane);

		// bắt đầu thiết kế phần bên phải bảng
//		panelTableIn2.setBorder(lineBorder);
		panelTableIn2.setLayout(new GridLayout(7, 1));

		panelTableIn22.setLayout(new GridLayout(2, 1));
		panelTableIn22.add(jcbo);
		panelTableIn22.add(jtfFilter);
		panelTableIn22.setBorder(lineBorder3);

		panelTableIn21.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelTableIn21.add(btFilter);
		panelTableIn21.add(btCancel);

		panelTableIn22.setPreferredSize(new Dimension(90, 30));
		panelTableIn2.add(panelTableIn22);
		panelTableIn2.add(panelTableIn21);
		// kết thúc thiết kế phần bên phải bảng

		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		c.weightx = 0.1;
		gridbag.setConstraints(panelTableIn2, c);
		panelTable.add(panelTableIn2);

		panelTable.setBorder(lineBorder2);
		// kết thúc setlayout của panelTable thành gridbag và thêm các component vào
		add(panelTable);
		final MnProduct mnProduct=this;
		btFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = jtfFilter.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}
		});
		btCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = "";
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}

			}

		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddProduct(mnProduct);
			}
		});
		btnFix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTable.getSelectedRow()>=0)new FixProduct(mnProduct);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTable.getSelectedRow() >= 0) {
				boolean isDelete=	controller.delete((String)jTable.getValueAt(jTable.getSelectedRow(), 1));
					
				}
					
			}
		});
	}

	// thiết lập tỉ lệ của các cột trong bảng
	public void resizeColumns() {
		jTable.setRowHeight(24);
		int widthTable = jTable.getWidth();
		for (int i = 0; i < columnNames.length; i++) {
			jTable.getColumnModel().getColumn(i).setPreferredWidth(Math.round(columnWidthPercentage[i] * widthTable));
//			System.out.println(widthTable);
		}
	}
	public boolean setPropertyMaterial(String id,String ten,String donViTinh,double donGia,double soLuong,String loai,double giaNhap,String idnew) {
		return controller.setProperty(id, ten, donViTinh, donGia, soLuong,loai, giaNhap, idnew);
}
	public boolean addVatLieu(String id, String ten, String donViTinh, double donGia, double soLuong, String loai,
			double giaNhap) {
		return controller.addVatLieu(id, ten, donViTinh, donGia, soLuong, loai, giaNhap);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		data.setData();
		if(data.getRowData().length==0) {
			tableModel.removeRow(jTable.getSelectedRow());
		}
		else {
			tableModel.setRowCount(data.getRowData().length);
			tableModel = new MyTableModel(data.getRowData(),data.getColumnNames());
			jTable.setModel(tableModel);
			sorter = new TableRowSorter<TableModel>(jTable.getModel());
			jTable.setRowSorter(sorter);
			resizeColumns();
			tableModel.fireTableDataChanged();
		}
	}
	public String getId() {
		return (String) jTable.getModel().getValueAt(jTable.getSelectedRow(), 1);
	}

}
