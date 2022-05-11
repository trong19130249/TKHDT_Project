package View.viewMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import Dao.KhachHangDao;
import model.KhachHang;

public class FixCollect extends JFrame {
	private JLabel lbName = new JLabel("  Tên khách hàng: ");
	private JLabel lbCode = new JLabel("  Mã khách hàng: ");
	private JLabel lbMoney = new JLabel("  Số tiền: ");
	private JLabel lbDate = new JLabel("  Ngày thu: ");
	private JLabel lbEmail = new JLabel("  Email: ");
	private JLabel lbAddress = new JLabel("  Địa chỉ: ");

	private JComboBox jcbName = new JComboBox();
	private JTextField txtCode=new JTextField();
	private JTextField txtMoney = new JTextField();
	private JDateChooser dateCollect = new JDateChooser();
	private JTextField txtEmail = new JTextField();
	private JTextField txtAddress = new JTextField();
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Sửa");
	private JButton btCancel = new JButton("Hủy");

	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238, 238, 238), 10);

	public FixCollect(final MnCollectDebt mn) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(6, 2, 8, 4));
		container1.add(lbName); container1.add(jcbName); 
		container1.add(lbCode); container1.add(txtCode);
		container1.add(lbMoney);container1.add(txtMoney); 
		container1.add(lbDate); container1.add(dateCollect);
		txtEmail.setEditable(false);
		container1.add(lbEmail); container1.add(txtEmail); 
		txtAddress.setEditable(false);
		container1.add(lbAddress); container1.add(txtAddress); 

		gridbag.setConstraints(container1, c);
		add(container1);
		
		btAdd.setPreferredSize(new Dimension(90, 26));
		btCancel.setPreferredSize(new Dimension(90, 26));
		container2.setLayout(new FlowLayout(FlowLayout.CENTER, 24, 0));
		btAdd.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "CheckIcon2.png")));
		btAdd.setBackground(Color.WHITE);
//		btAdd.setBorder(lineBorder);
		
		btCancel.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "CancelIcon.png")));
		btCancel.setBackground(Color.WHITE);
//		btCancel.setBorder(lineBorder);
		container2.add(btAdd); container2.add(btCancel);
		container2.setBorder(lineBorder2);
		c.gridwidth = 1; // reset to the default
		c.gridheight = 1;
		c.weighty = 0.1;
		gridbag.setConstraints(container2, c);
		add(container2);
		dateCollect.setDateFormatString("dd-MM-yyyy");
		final List<KhachHang> ds=KhachHangDao.getInstance().getDanhSachKhachHang();
		for(KhachHang kh:ds) {
			jcbName.addItem(kh.getHoVaTen());
		}
		KhachHang kh=ds.get(jcbName.getSelectedIndex());
		txtCode.setText(kh.getMaSo());
     	txtEmail.setText(kh.getEmail());
     	txtMoney.setText(mn.getSoTien()+"");
     	txtAddress.setText(kh.getDiachi());
     	dateCollect.setDate(mn.getNgayThu());
		jcbName.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					KhachHang kh=ds.get(jcbName.getSelectedIndex());
			     	txtCode.setText(kh.getMaSo());
			     	txtEmail.setText(kh.getEmail());
			     	txtAddress.setText(kh.getDiachi());
			     	
			       }
			}
		});
		
		
		setTitle("Sửa phiếu thu nợ");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KhachHang kh=ds.get(jcbName.getSelectedIndex());
				String idkh=txtCode.getText().equals("")?kh.getMaSo():txtCode.getText();
				double soTienNo=txtMoney.getText().equals("")?mn.getSoTien():Double.parseDouble(txtMoney.getText());
				java.util.Date ngayThuChon=dateCollect.getDate();
		     	Date ngayThu=new Date(ngayThuChon.getYear(),ngayThuChon.getMonth(),ngayThuChon.getDay());
				mn.setDebt(idkh, ngayThu, soTienNo, mn.getId());
                dispose();
			}

		});
		btCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                dispose();
			}

		});
	}



}
