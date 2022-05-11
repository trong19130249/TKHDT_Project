package View.viewMain;

import com.toedter.calendar.JDateChooser;

import Dao.KhachHangDao;
import model.DonHang;
import model.KhachHang;

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


public class AddAnOrder extends JFrame {
	private JLabel lbCode = new JLabel("  Mã đơn hàng: ");
	private JLabel lbName = new JLabel("  Tên khách hàng: ");
	private JLabel lbEmail = new JLabel("  Email: ");
	private JLabel lbDeliverTo = new JLabel("  Địa chỉ giao hàng: ");
	private JLabel lbDateSet = new JLabel("  Ngày lập: ");
	private JTextField txtCode = new JTextField();
	private JComboBox jcbName = new JComboBox();
	private JTextField txtEmail = new JTextField();
	private JTextField txtDeliverTo = new JTextField();
	private JDateChooser dateSet = new JDateChooser();
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Thêm");
	private JButton btCancel = new JButton("Hủy");
	
	private Border lineBorder2 = new LineBorder(new Color(238,238,238), 10);

	public AddAnOrder(final MnOrders mn) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(6, 2, 8, 4));
		container1.add(lbCode); container1.add(txtCode); 
		container1.add(lbName); container1.add(jcbName); 
		txtEmail.setEditable(false);
		container1.add(lbEmail); container1.add(txtEmail); 
		container1.add(lbDeliverTo);container1.add(txtDeliverTo); 
		container1.add(lbDateSet); container1.add(dateSet); 

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
		final List<KhachHang> dskh=KhachHangDao.getInstance().getDanhSachKhachHang();
		for(KhachHang kh:dskh) {
			jcbName.addItem((String)kh.getHoVaTen());
		}
		 int index0=jcbName.getSelectedIndex();
	     	KhachHang kh=dskh.get(index0);
	     	txtEmail.setText(kh.getEmail());
	     	txtDeliverTo.setText(kh.getDiachi());
	     	jcbName.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
			         int index=jcbName.getSelectedIndex();
			     	KhachHang kh=dskh.get(index);
			     	txtEmail.setText(kh.getEmail());
			     	txtDeliverTo.setText(kh.getDiachi());
			       }
			}
		});
		
		setTitle("Thêm đơn hàng");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date ngayLapChon=dateSet.getDate();
				String iddh=txtCode.getText().equals("")?"":txtCode.getText();
				String idkh=dskh.get(jcbName.getSelectedIndex()).getMaSo();
				Date ngayLap=dateSet.getDate()==null?new Date(System.currentTimeMillis()):new Date(ngayLapChon.getYear(),ngayLapChon.getMonth(),ngayLapChon.getDay());
				mn.addDonHang(iddh, idkh, ngayLap);
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
