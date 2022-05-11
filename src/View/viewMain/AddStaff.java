package View.viewMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.NhanVien;

public class AddStaff extends JFrame {
	private JLabel lbName = new JLabel("  Tên nhân viên: ");
	private JLabel lbPhone = new JLabel("  Số điện thoại: ");
	private JLabel lbActive = new JLabel("  Hoạt động: ");
	private JLabel lbNote = new JLabel("  Ghi chú: ");
	private JLabel lbSex = new JLabel("  Giới tính: ");
	private JLabel lbEmail = new JLabel("  Email: ");
	private JLabel lbAddress = new JLabel("  Địa chỉ: ");
	private JTextField txtName = new JTextField();
	private JTextField txtPhone = new JTextField();
	private String[] isActive = { "Không","Có" };
	private JComboBox jcbActive = new JComboBox(isActive);
	private JTextField txtNote = new JTextField();
	private String[] isSex= {"Nữ","Nam"};
	private JComboBox jcbSex = new JComboBox(isSex);
	private JTextField txtEmail = new JTextField();
	private JTextField txtAddress = new JTextField();
	
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Thêm");
	private JButton btCancel = new JButton("Hủy");
	
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238,238,238), 10);

	public AddStaff(final MnStaff mn) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(5, 2, 8, 4));
		container1.add(lbName); container1.add(txtName); 
		container1.add(lbPhone); container1.add(txtPhone); 
		container1.add(lbActive); container1.add(jcbActive); 
		container1.add(lbNote); container1.add(txtNote); 
		container1.add(lbSex); container1.add(jcbSex); 
		container1.add(lbEmail); container1.add(txtEmail); 
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
		
		
		setTitle("Thêm nhân viên");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String id=UUID.randomUUID().toString();
				String ten=txtName.getText().equals("")?"":txtName.getText();
				int sdt=txtPhone.getText().equals("")?0:Integer.parseInt(txtPhone.getText());
				int trangThai=jcbActive.getSelectedIndex();
				String ghiChu=txtNote.getText().equals("")?"":txtNote.getText();
				int gioiTinh=jcbSex.getSelectedIndex();
				String email=txtEmail.getText().equals("")?"":txtEmail.getText();
				String diaChi=txtAddress.getText().equals("")?"":txtAddress.getText();
				String idChucVu="cv01";
				mn.addNhanVien(id, ten, sdt, trangThai, 
						ghiChu, gioiTinh, email, diaChi, idChucVu);
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

