package View.viewMain;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Dao.NhanVienDao;
import Dao.TaiKhoanDao;
import model.ChucVu;
import model.KhachHang;
import model.NhanVien;
import model.TaiKhoan;

public class AccountOfStaff extends JFrame {
	private JLabel lbName = new JLabel("      Họ và tên: ");
	private JTextField txtName = new JTextField();

	private JLabel lbSignUp = new JLabel("      Tên đăng nhập: ");
	private JTextField txtSignUp = new JTextField();

	private JLabel lbPass = new JLabel("      Mật khẩu: ");
	private JPasswordField txtPass = new JPasswordField();

	private JLabel lbPhone = new JLabel("      Số điện thoại: ");
	private JTextField txtPhone = new JTextField();

	private JLabel lbEmail = new JLabel("      Email: ");
	private JTextField txtEmail = new JTextField();

	private JLabel lbSex = new JLabel("      Giới tính: ");
	private String[] isSex = { "Nữ", "Nam" };
	private JComboBox jcbSex = new JComboBox(isSex);
	private JLabel lbPos = new JLabel("      Chức vụ: ");
	List<ChucVu> listChucVu = TaiKhoanDao.getInstance().getListChucVu();
	private JComboBox jcbPos;;
	private JLabel lbSalary = new JLabel("      Lương: ");
	private JTextField txtSalary = new JTextField();
	private JLabel lbNote = new JLabel("      Ghi Chú: ");
	private JTextField txtNote = new JTextField();
	private JLabel lbAddress = new JLabel("      Địa Chỉ: ");
	private JTextField txtAddress = new JTextField();
	private JPanel panel = new JPanel();
	private JPanel temp1 = new JPanel();
	private JPanel temp2 = new JPanel();

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JPanel panel5 = new JPanel();
	private JPanel panel6 = new JPanel();
	private JPanel panel7 = new JPanel();
	private JPanel panel8 = new JPanel();
	private JPanel panel9 = new JPanel();
	private JPanel panel10 = new JPanel();
	private JPanel panel11 = new JPanel();
	private JPanel containSave = new JPanel();
	private JButton btSave = new JButton("Lưu");

	private JPanel panelIm0 = new JPanel();
	private JPanel panelIm1 = new JPanel();
	private JPanel panelIm2 = new JPanel();

	private JLabel jLabelImage = new JLabel();
	private JButton jButtonBrowseImage = new JButton("Mở ảnh");
	NhanVien nv;
	TaiKhoan tk;
	private String id;

	public AccountOfStaff(final String id, final MnStaff mn) {
		this.id = id;

		setLayout(new GridLayout(0, 2));
		jcbPos = new JComboBox();
		for (ChucVu cv : listChucVu) {
			jcbPos.addItem(cv.getTen());
		}
		jcbPos.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int index = jcbPos.getSelectedIndex();
					listChucVu = TaiKhoanDao.getInstance().getListChucVu();
					txtSalary.setText(listChucVu.get(index).getLuong() + "");
				}
			}
		});

		panel1.add(lbName);
		panel1.add(txtName);

		panel2.add(lbSignUp);
		panel2.add(txtSignUp);

		panel3.add(lbPass);
		panel3.add(txtPass);

		panel5.add(lbPhone);
		panel5.add(txtPhone);

		panel6.add(lbEmail);
		panel6.add(txtEmail);

		panel7.add(lbSex);
		panel7.add(jcbSex);

		panel8.add(lbPos);
		panel8.add(jcbPos);

		panel9.add(lbSalary);
		panel9.add(txtSalary);
		panel10.add(lbNote);
		panel10.add(txtNote);

		panel11.add(lbAddress);
		txtSalary.setEditable(false);
		panel11.add(txtAddress);
		btSave.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "SaveIcon.png")));
		containSave.add(btSave);

		panel.setLayout(new GridLayout(13, 1, 16, 15));
		panel1.setLayout(new GridLayout(1, 2));
		panel2.setLayout(new GridLayout(1, 2));
		panel3.setLayout(new GridLayout(1, 2));
		panel4.setLayout(new GridLayout(1, 2));
		panel5.setLayout(new GridLayout(1, 2));
		panel6.setLayout(new GridLayout(1, 2));
		panel7.setLayout(new GridLayout(1, 2));
		panel8.setLayout(new GridLayout(1, 2));
		panel9.setLayout(new GridLayout(1, 2));
		panel10.setLayout(new GridLayout(1, 2));
		panel11.setLayout(new GridLayout(1, 2));

		panel.add(temp1);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		panel.add(panel7);
		panel.add(panel8);
		panel.add(panel9);
		panel.add(panel10);
		panel.add(panel11);
		panel.add(containSave);
		add(panel);

		jLabelImage.setOpaque(true);

		jLabelImage.setPreferredSize(new Dimension(180, 180));
		jLabelImage.setBackground(Color.BLUE);

		panelIm0.setLayout(new GridLayout(2, 1));
		temp2.setBackground(new Color(238, 238, 238));
		temp2.setPreferredSize(new Dimension(890, 100));
		panelIm1.add(temp2, BorderLayout.NORTH);
		panelIm1.add(jLabelImage, BorderLayout.CENTER);
		panelIm0.add(panelIm1);

		jButtonBrowseImage.setFocusable(false);
		jButtonBrowseImage.setIcon(new ImageIcon(getClass().getResource("/View/SwingIcon/" + "open-icon.png")));
		panelIm2.add(jButtonBrowseImage);
		panelIm0.add(panelIm2);
		add(panelIm0);
		setData();
		btSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu không?");
				if (result == JOptionPane.OK_OPTION) {
					// ...

					String ten = txtName.getText();
					String userName = txtSignUp.getText();
					String pass = txtPass.getText();
					int sdt = Integer.parseInt(txtPhone.getText());
					String ghiChu = txtNote.getText().equals("") ? nv.getGhiChu() : txtNote.getText();
					int gioiTinh = jcbSex.getSelectedIndex();
					String email = txtEmail.getText().equals("") ? nv.getEmail() : txtEmail.getText();
					String diaChi = txtAddress.getText().equals("") ? nv.getDiaChi() : txtAddress.getText();
					boolean isExitsAccount = userName.equals("") ? false : true;
					String idChucVu = listChucVu.get(jcbPos.getSelectedIndex()).getId();
					NhanVienDao.getInstance().setNhanVien(id, ten, sdt, nv.getTrangThai(), ghiChu, gioiTinh, email,
							diaChi, idChucVu);
					if (isExitsAccount) {
						if (tk == null) {
							TaiKhoanDao.getInstance().addAccount(id, userName, pass, 0);
						} else {
							int admin = tk.getQuyen();
							TaiKhoanDao.getInstance().editAccount(id, id, userName, pass, admin);
						}

					}
					mn.update();

				}
			}
		});
		jButtonBrowseImage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonBrowseImageActionPerformed(evt);
			}
		});

		setTitle("Thông tin chi tiết nhân viên");
		setSize(960, 630);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void setData() {
		nv = NhanVienDao.getInstance().getNhanVien(id);
		tk = TaiKhoanDao.getInstance().getAccount(id);
		txtName.setText(nv.getTen());
		txtSignUp.setText(tk == null ? "" : tk.getUserName());
		txtPass.setText(tk == null ? "" : tk.getPass());
		txtPhone.setText("0" + nv.getSdt());
		txtEmail.setText(nv.getEmail());
		jcbSex.setSelectedIndex(nv.getGioiTinh());
		int indexPos = 0;
		for (int i = 0; i < listChucVu.size(); i++) {
			if (nv.getChucVu().equals(listChucVu.get(i).getId())) {
				indexPos = i;
				break;
			}
		}
		jcbPos.setSelectedIndex(indexPos);
		txtSalary.setText(nv.getLuong() + "");
		txtNote.setText(nv.getGhiChu());
		txtAddress.setText(nv.getDiaChi());

	}

	private void jButtonBrowseImageActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		JFileChooser browseImageFile = new JFileChooser();
		// Filter image extensions
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
		browseImageFile.addChoosableFileFilter(fnef);
		int showOpenDialogue = browseImageFile.showOpenDialog(null);

		if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
			File selectedImageFile = browseImageFile.getSelectedFile();
			String selectedImagePath = selectedImageFile.getAbsolutePath();
			JOptionPane.showMessageDialog(null, selectedImagePath);
			// Display image on jlable
			ImageIcon ii = new ImageIcon(selectedImagePath);
//            Resize image to fit jlabel
			Image image = ii.getImage().getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(),
					Image.SCALE_SMOOTH);

			jLabelImage.setIcon(new ImageIcon(image));
		}
	}

}
