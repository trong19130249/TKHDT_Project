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
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.ChiTietLSNHController;
import model.CTNhapHangModelInterface;
import model.LichSuMuaHang;
import model.Observer;

public class DetailAPurchase extends JFrame implements Observer {
	private JButton btnAdd = new JButton("Thêm ");
	private JButton btnFix = new JButton("Sửa ");
	private JButton btnDelete = new JButton("Xóa ");
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
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
	private CTNhapHangModelInterface data;
	private ChiTietLSNHController controller;
	private float[] columnWidthPercentage = { 0.03f, 0.1f, 0.24f, 0.12f, 0.14f, 0.14f, 0.12f, 0.12f, 0.1f, 0.1f};
private String idLichSu;
private MnPurchases mn;
	public DetailAPurchase(CTNhapHangModelInterface data,ChiTietLSNHController con,String idLichSu,MnPurchases mn) {
		this.data=data;
		this.controller=con;
		this.idLichSu=idLichSu;
		this.mn=mn;
		data.registerObserver((Observer) this);
		data.setData(idLichSu);
		
		this.columnNames =data.getColumnNames();
		this.rowData = data.getRowData();
		// bắt đầu set property cho button
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "Button-Add-icon.png")));
		btnAdd.setBackground(Color.WHITE);
//		btnAdd.setBorder(lineBorder);

		btnFix.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "SuaIcon.png")));
		btnFix.setBackground(Color.WHITE);
//		btnFix.setBorder(lineBorder);

		btnDelete.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "XoaIcon.png")));
		btnDelete.setBackground(Color.WHITE);
//		btnDelete.setBorder(lineBorder);

		btFilter.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "FilterIcon.png")));
		btFilter.setBackground(Color.WHITE);
//		btFilter.setBorder(lineBorder);

		btCancel.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "CancelIcon.png")));
		btCancel.setBackground(Color.WHITE);
//		btCancel.setBorder(lineBorder);

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
		JLabel nameScreen = new JLabel("  Danh sách hàng hóa");
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
		header.setBackground(new Color(0, 144, 218));
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
		final DetailAPurchase dt=this;
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
				new AddADetailPurchase(dt);
			}
		});
		
		btnFix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTable.getSelectedRow() >= 0)new FixADetailPurchase(dt);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTable.getSelectedRow() >= 0)
					tableModel.removeRow(jTable.getSelectedRow());
			}
		});
		
		setTitle("Chi tiết một phiếu nhập hàng");
		setSize(880, 580);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	// thiết lập tỉ lệ của các cột trong bảng
	public void resizeColumns() {
		jTable.setRowHeight(24);
		int widthTable = jTable.getWidth();
		for (int i = 0; i < columnNames.length; i++) {
			jTable.getColumnModel().getColumn(i).setPreferredWidth(Math.round(columnWidthPercentage[i] * widthTable));
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		data.setData(idLichSu);
		tableModel.setRowCount(data.getRowData().length);
		tableModel = new MyTableModel(data.getRowData(),data.getColumnNames());
		jTable.setModel(tableModel);
		sorter = new TableRowSorter<TableModel>(jTable.getModel());
		jTable.setRowSorter(sorter);
		resizeColumns();
		tableModel.fireTableDataChanged();
	}
	public String getId() {
		return idLichSu;
	}
	public String getIdVatLieu() {
		return (String)jTable.getValueAt(jTable.getSelectedRow(), 1);

	}
	public boolean addChiTiet(String id, String idvl, double soLuong) {
		// TODO Auto-generated method stub
		boolean r=data.addChiTiet(id, idvl, soLuong);
		mn.childCallObserver();
		return r;
	}
	public boolean deleteChiTiet(String id,String idvl) {
		boolean r= data.deleteChiTiet(id, idvl);
		mn.childCallObserver();
		return r;
	}
	public boolean editChiTiet(String id,String idVatLieuCu,String idVatLieu,double soLuong) {
		boolean r= data.editChiTiet(id,idVatLieuCu, idVatLieu, soLuong);
		mn.childCallObserver();
		return r;
	}
	public double getSoLuong() {
		return (double) jTable.getValueAt(jTable.getSelectedRow(),5 );
	}


}
