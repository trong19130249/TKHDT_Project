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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Dao.DonHangDao;
import Dao.VatLieuDao;
import model.ASanPham;
import model.ChiTietDonHang;

public class AddAnDetailOrder extends JFrame {
	private JLabel lbCode = new JLabel("  Mã hàng hóa: ");
	private JLabel lbName = new JLabel("  Tên hàng hóa: ");
	private JLabel lbUnit = new JLabel("  DVT: ");
	private JLabel lbSell = new JLabel("  Giá bán: ");
	private JLabel lbAmount = new JLabel("  Số lượng: ");
	private JLabel lbCate = new JLabel("  Loại hàng hóa: ");
	private JComboBox jcbCode = new JComboBox();
	private JTextField txtName = new JTextField();
	private JTextField txtUnit = new JTextField();
	private JTextField txtSell = new JTextField();
	private JTextField txtAmount = new JTextField();
	private JTextField txtCate = new JTextField();
	
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Thêm");
	private JButton btCancel = new JButton("Hủy");
	
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238,238,238), 10);

	public AddAnDetailOrder(final DetailAnOrder dt) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(7, 2, 8, 4));
		container1.add(lbCode); container1.add(jcbCode); 
		container1.add(lbName); container1.add(txtName);
		txtUnit.setEditable(false);
		container1.add(lbUnit); container1.add(txtUnit);
		txtSell.setEditable(false);
		container1.add(lbSell); container1.add(txtSell); 
		container1.add(lbAmount); container1.add(txtAmount); 
		txtCate.setEditable(false);
		container1.add(lbCate); container1.add(txtCate); 
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
	     	final List<ASanPham> ds=VatLieuDao.getInstance().getAll();
			for(ASanPham vl:ds) {
				jcbCode.addItem((String)vl.getId());
			}
		     	ASanPham vl=ds.get(jcbCode.getSelectedIndex());
		     	txtName.setText(vl.getTenSanPham());
		     	txtUnit.setText(vl.getDonViTinh());
		     	txtSell.setText(vl.getDonGia()+"");
		     	txtCate.setText(vl.getType());
		     	jcbCode.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						int index0=jcbCode.getSelectedIndex();
				     	ASanPham vl=ds.get(index0);
				     	txtName.setText(vl.getTenSanPham());
				     	txtUnit.setText(vl.getDonViTinh());
				     	txtSell.setText(vl.getDonGia()+"");
				     	txtCate.setText(vl.getType());
				       }
				}
			});
		
		setTitle("Thêm hàng hóa vào chi tiết hàng hóa để bán");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idvl=(String) jcbCode.getSelectedItem();
				double soLuong=txtAmount.getText().equals("")?0:Double.parseDouble(txtAmount.getText());
				dt.addChiTiet(dt.getId(), idvl, soLuong);
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
