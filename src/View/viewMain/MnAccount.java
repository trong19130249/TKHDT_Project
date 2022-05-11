package View.viewMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.ReportController;
import model.Observer;
import model.ReportModelInterface;

public class MnAccount extends JPanel implements Observer{
	private JButton btnAdd = new JButton("Thêm ");
	private JButton btnFix = new JButton("Sửa ");
	private JButton btnDelete = new JButton("Xóa ");
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238, 238, 238), 6);
	private Border lineBorder3 = new LineBorder(new Color(238, 238, 238), 8);

	private String[] filterTitle = { "Theo tên đăng nhập","Theo mã nhân viên", "Theo tên nhân viên" };
	private JComboBox jcbo = new JComboBox(filterTitle);
	private JTextField jtfFilter = new JTextField();
	private JButton btFilter = new JButton("Lọc");
	private JButton btCancel = new JButton("Hủy");

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
	private ReportModelInterface data;
	private ReportController controller;
	private boolean admin=false;
	private float[] columnWidthPercentage = { 0.01f, 0.14f, 0.24f, 0.14f, 0.12f};
	public void setAdmin(boolean admin) {
		this.admin=admin;
		
	}
	public MnAccount(ReportModelInterface data,ReportController controller) {
		this.data=data;
		this.controller=controller;
		data.registerObserver((Observer) this);
		data.setAdmin(admin);
		data.setData();
		
		this.columnNames =data.getColumnNames();
		this.rowData = data.getRowData();

		// bắt đầu set property cho button
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "Button-Add-icon.png")));
		btnAdd.setBackground(Color.WHITE);

		btnFix.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "SuaIcon.png")));
		btnFix.setBackground(Color.WHITE);

		btnDelete.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "XoaIcon.png")));
		btnDelete.setBackground(Color.WHITE);

		btFilter.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "FilterIcon.png")));
		btFilter.setBackground(Color.WHITE);

		btCancel.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "CancelIcon.png")));
		btCancel.setBackground(Color.WHITE);

		jtfFilter.setPreferredSize(new Dimension(200, 20));
		// kết thúc set property cho button

		// set layout cho bố cục tổng thể
		setLayout(new BorderLayout());

		// bắt đầu đặt các nút thêm, xóa, sửa và label dm hàng hóa
		panelBtns1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelBtns1.add(btnAdd);
//		panelBtns1.add(btnFix);
		panelBtns1.add(btnDelete);

		panelBtns3.setLayout(new BorderLayout());
		JLabel nameScreen = new JLabel("  Danh mục tài khoản");
		nameScreen.setFont(new java.awt.Font("", Font.BOLD, 15));
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
		header.setBackground(new Color(13,84,141));
		header.setForeground(new Color(255, 255, 255));
		header.setPreferredSize(new Dimension(10, 30));

		sorter = new TableRowSorter<TableModel>(jTable.getModel());
		jTable.setRowSorter(sorter);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.LEFT);
		for (int x = 0; x < jTable.getColumnCount(); x++) {
			if (jTable.getColumnModel().getColumn(x).getClass() != null && x != 3 && x != 0) {
				jTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
			}
		}
		new ButtonColumn3(jTable, 4,this);

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
		panelTableIn21.setPreferredSize(new Dimension(140, 30));

		panelTableIn22.setPreferredSize(new Dimension(150, 30));
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
		final MnAccount mn=this;
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
				new AddAccount(mn);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTable.getSelectedRow() >= 0) {
				deleteAccount(getId());
				}	
			}
		});
	}
	public void checkAdmin(boolean admin) {
		data.setAdmin(admin);
	}
	// thiết lập tỉ lệ của các cột trong bảng
	public void resizeColumns() {
		jTable.setRowHeight(24);
		int widthTable = jTable.getWidth();
		for (int i = 0; i < columnNames.length; i++) {
			jTable.getColumnModel().getColumn(i).setPreferredWidth(Math.round(columnWidthPercentage[i] * widthTable));
		}
	}
public String getId() {
	return (String)jTable.getValueAt(jTable.getSelectedRow(), 1);
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
			new ButtonColumn3(jTable, 4,this);
			resizeColumns();
			tableModel.fireTableDataChanged();
		}
	}
	public boolean addAccount(String idNhanVien,String userName,String pass,int admin) {
		return data.addAccount(idNhanVien, userName, pass, admin);
	}
	public boolean deleteAccount(String idNhanVien) {
		return data.deleteAccount(idNhanVien);
	}
	public boolean editAccount(String idNew,String id, String userName,String pass,int admin) {
		return data.editAccount(idNew, id, userName, pass, admin);
	}
	

}
