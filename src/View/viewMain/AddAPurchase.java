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

import Dao.NhaCungCapDao;
import model.KhachHang;
import model.NhaBanHang;

public class AddAPurchase extends JFrame {
	private JLabel lbCode = new JLabel("  Mã nhập hàng: ");
	private JLabel lbName = new JLabel("  Tên nhà cung cấp: ");
	private JLabel lbPhone = new JLabel("  Số điện thoại: ");

	private JTextField txtCode = new JTextField();
	private JComboBox jcbName = new JComboBox();
	private JTextField txtPhone = new JTextField();
	private JTextField txtMoney = new JTextField();

	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Thêm");
	private JButton btCancel = new JButton("Hủy");

	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238, 238, 238), 10);

	public AddAPurchase(final MnPurchases mn) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row

		container1.setLayout(new GridLayout(4, 2, 8, 4));
		container1.add(lbCode);
		container1.add(txtCode);
		container1.add(lbName);
		container1.add(jcbName);
		txtPhone.setEditable(false);
		container1.add(lbPhone);
		container1.add(txtPhone);
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
		container2.add(btAdd);
		container2.add(btCancel);
		container2.setBorder(lineBorder2);
		c.gridwidth = 1; // reset to the default
		c.gridheight = 1;
		c.weighty = 0.1;
		gridbag.setConstraints(container2, c);
		add(container2);
		final List<NhaBanHang> ds = NhaCungCapDao.getInstance().getAll();
		for (NhaBanHang nbh : ds) {
			jcbName.addItem((String) nbh.getTen());
		}
		int index0 = jcbName.getSelectedIndex();
		NhaBanHang nbh = ds.get(index0);
		txtPhone.setText("0" + nbh.getSdt() + "");
		jcbName.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int index = jcbName.getSelectedIndex();
					NhaBanHang nbh = ds.get(index);
					txtPhone.setText("0" + nbh.getSdt() + "");
				}
			}
		});
		setTitle("Thêm phiếu nhập hàng");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = new Date(System.currentTimeMillis());
				String idls = txtCode.getText().equals("") ? "" : txtCode.getText();
				String idnbh = ds.get(jcbName.getSelectedIndex()).getId();
				mn.addLichSu(idls, idnbh, date);
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
