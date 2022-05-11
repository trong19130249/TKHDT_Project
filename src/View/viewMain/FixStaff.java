package View.viewMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class FixStaff extends JFrame {
	private JLabel lbCode = new JLabel("  Mã nhân viên: ");
	private JLabel lbName = new JLabel("  Tên nhân viên: ");
	private JLabel lbPhone = new JLabel("  Số điện thoại: ");
	private JLabel lbActive = new JLabel("  Hoạt động: ");
	private JLabel lbNote = new JLabel("  Ghi chú: ");
	private JLabel lbSex = new JLabel("  Giới tính: ");
	private JLabel lbEmail = new JLabel("  Email: ");
	private JLabel lbAddress = new JLabel("  Địa chỉ: ");

	private JTextField txtCode = new JTextField();
	private JTextField txtName = new JTextField();
	private JTextField txtPhone = new JTextField();
	private String[] isActive = { "Có", "Không" };
	private JComboBox jcbActive = new JComboBox(isActive);
	private JTextField txtNote = new JTextField();
	private String[] isSex= {"Nam","Nữ"};
	private JComboBox jcbSex = new JComboBox(isSex);
	private JTextField txtEmail = new JTextField();
	private JTextField txtAddress = new JTextField();
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Sửa");
	private JButton btCancel = new JButton("Hủy");
	
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238,238,238), 10);

	public FixStaff(final MnStaff mn) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(5, 2, 8, 4));
		container1.add(lbCode); container1.add(txtCode); 
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
		btAdd.setIcon(new ImageIcon(getClass().getResource("/SwingIcon/" + "CheckIcon2.png")));
		btAdd.setBackground(Color.WHITE);
//		btAdd.setBorder(lineBorder);
		
		btCancel.setIcon(new ImageIcon(getClass().getResource("/SwingIcon/" + "CancelIcon.png")));
		btCancel.setBackground(Color.WHITE);
//		btCancel.setBorder(lineBorder);
		container2.add(btAdd); container2.add(btCancel);
		container2.setBorder(lineBorder2);
		c.gridwidth = 1; // reset to the default
		c.gridheight = 1;
		c.weighty = 0.1;
		gridbag.setConstraints(container2, c);
		add(container2);
		setTitle("Sửa nhân viên");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NhanVien nv=mn.getNhanVien(mn.getId());
				String id=txtCode.getText().equals("")?nv.getId():txtCode.getText();
				String ten=txtName.getText().equals("")?nv.getTen():txtName.getText();
				int sdt=txtPhone.getText().equals("")?nv.getSdt():Integer.parseInt(txtPhone.getText());
				int trangThai=jcbActive.getSelectedIndex();
				String ghiChu=txtNote.getText().equals("")?nv.getGhiChu():txtNote.getText();
				int gioiTinh=jcbSex.getSelectedIndex();
				String email=txtEmail.getText().equals("")?nv.getEmail():txtEmail.getText();
				String diaChi=txtAddress.getText().equals("")?nv.getDiaChi():txtAddress.getText();
				String idChucVu="cv01";
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
