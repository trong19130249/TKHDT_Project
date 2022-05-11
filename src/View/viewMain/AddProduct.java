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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class AddProduct extends JFrame {
	private JLabel lbCode = new JLabel("  Mã hàng hóa: ");
	private JLabel lbName = new JLabel("  Tên hàng hóa: ");
	private JLabel lbUnit = new JLabel("  DVT: ");
	private JLabel lbBuy = new JLabel("  Giá nhập: ");
	private JLabel lbSell = new JLabel("  Giá bán: ");
	private JLabel lbAmount = new JLabel("  Tồn kho: ");
	private JLabel lbCate = new JLabel("  Loại hàng hóa: ");

	private JTextField txtCode = new JTextField();
	private JTextField txtName = new JTextField();
	private JTextField txtUnit = new JTextField();
	private JTextField txtBuy = new JTextField();
	private JTextField txtSell = new JTextField();
	private JTextField txtAmount = new JTextField();
	private JTextField txtCate = new JTextField();
	
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JButton btAdd = new JButton("Thêm");
	private JButton btCancel = new JButton("Hủy");
	
	private Border lineBorder = new LineBorder(new Color(0, 144, 255), 2);
	private Border lineBorder2 = new LineBorder(new Color(238,238,238), 10);

	public AddProduct(final MnProduct mnProduct) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		container1.setLayout(new GridLayout(7, 2, 8, 4));
		container1.add(lbCode); container1.add(txtCode); 
		container1.add(lbName); container1.add(txtName); 
		container1.add(lbUnit); container1.add(txtUnit); 
		container1.add(lbBuy); container1.add(txtBuy); 
		container1.add(lbSell); container1.add(txtSell); 
		container1.add(lbAmount); container1.add(txtAmount); 
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
		
		
		setTitle("Thêm hàng hóa");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double donGia=txtSell.getText().equals("")?0:Double.parseDouble(txtSell.getText());
	               String idnew=txtCode.getText().equals("")?"":txtCode.getText();
	               String ten=txtName.getText().equals("")?"":txtName.getText();
	               String donViTinh=txtUnit.getText().equals("")?"":txtUnit.getText();
	               double soLuong=txtAmount.getText().equals("")?0:Double.parseDouble(txtAmount.getText());
	               double giaNhap=txtBuy.getText().equals("")?0:Double.parseDouble(txtBuy.getText());
	               String loai=txtCate.getText().equals("")?"":txtCate.getText();
	               System.out.println(donGia);
	               System.out.println(idnew);
	               System.out.println(soLuong);
	               System.out.println(loai);
	               System.out.println(giaNhap);
	             mnProduct.addVatLieu(idnew, ten, donViTinh, donGia, soLuong, loai, giaNhap);
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
