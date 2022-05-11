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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Dao.NhanVienDao;
import model.KhachHang;
import model.NhanVien;

public class AddAccount extends JFrame {
	private JLabel lbSignUp = new JLabel("  Tên đăng nhập: ");
	private JLabel lbPass = new JLabel("  Mật khẩu: ");
	private JLabel lbRePass = new JLabel("  Nhập lại mật khẩu: ");
	private JLabel lbActive = new JLabel("  Hoạt động: ");
	private JLabel lbCode = new JLabel("  Mã nhân viên: ");
	private JLabel lbName = new JLabel("  Tên nhân viên: ");

	private JTextField txtSignUp = new JTextField();
	private JTextField txtPass = new JTextField();
	private JTextField txtRePass = new JTextField();
	private String[] isActive = { "Có", "Không" };
	private JComboBox jcbActive = new JComboBox(isActive);
	private JComboBox jcbCode = new JComboBox();
	private JTextField jcbName = new JTextField();
	
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Thêm");
	private JButton btCancel = new JButton("Hủy");
	
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238,238,238), 10);

	public AddAccount(final MnAccount mn) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(6, 2, 8, 4));
		container1.add(lbSignUp); container1.add(txtSignUp); 
		container1.add(lbPass); container1.add(txtPass); 
		container1.add(lbRePass); container1.add(txtRePass); 
		container1.add(lbActive); container1.add(jcbActive); 
		container1.add(lbCode); container1.add(jcbCode); 
		container1.add(lbName); container1.add(jcbName); 

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
		final List<NhanVien> dsnv=NhanVienDao.getInstance().getAll();
		for(NhanVien nv:dsnv) {
			jcbCode.addItem((String)nv.getId());
		}
		jcbName.setText(dsnv.get(jcbCode.getSelectedIndex()).getTen());
		jcbCode.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
			         jcbName.setText(dsnv.get(jcbCode.getSelectedIndex()).getTen());
			       }
			}
		});
		setTitle("Thêm tài khoản");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user=txtSignUp.getText();
				String pass=txtPass.getText();
				String rePass=txtRePass.getText();
				if(!pass.equals(rePass))
					JOptionPane.showMessageDialog(null, this, "Vui lòng nhập đầy đủ thông tin", EXIT_ON_CLOSE);
				else {
					String idNhanVien=dsnv.get(jcbCode.getSelectedIndex()).getId();
					mn.addAccount(idNhanVien, user, rePass, 0);
				}
				
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
