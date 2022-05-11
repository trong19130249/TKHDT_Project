/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.DashboardController;
import Controller.FormSalesController;
import Controller.HoaDonController;
import Controller.KhachHangController;
import Controller.LSNHController;
import Controller.NhaCungCapController;
import Controller.NhanVienController;
import Controller.ReportController;
import Controller.SignupVsSigninControler;
import Controller.ThuNoController;
import Controller.TraNoController;
import Controller.VatLieuController;
import View.component.Header;
import View.component.Menu;
import  View.event.EventMenuSelected;
import View.event.EventProduct;
import View.form.FormSales;
import  View.form.FormHome;
import View.model.AccountData;
import View.model.CollectDebtData;
import View.model.CustomerData;
import View.model.OrdersData;
import View.model.PayDebtData;
import View.model.ProductsData;
import View.model.PurchasesData;
import View.model.SignInSignUpData;
import View.model.StaffData;
import View.model.SupplierData;
import View.swing.Background;
import View.swing.MainForm;
import View.swing.PanelBorder;
import View.swing.WinButton;
import View.viewMain.AddCustomer;
import View.viewMain.InformationStaff;
import View.viewMain.MnAccount;
import View.viewMain.MnCollectDebt;
import View.viewMain.MnCustomer;
import View.viewMain.MnOrders;
import View.viewMain.MnPayDebt;
import View.viewMain.MnProduct;
import View.viewMain.MnPurchases;
import View.viewMain.MnStaff;
import View.viewMain.MnSupplier;
import View.viewMain.StaffOfAccount;
import model.ASanPham;
import model.DangNhapDangXuatModelInterface;
import model.Observer;
import model.ReportModelInterface;
import model.TaiKhoan;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author trong
 */
public class Main extends javax.swing.JFrame {

	/**
	 * Creates new form Main
	 */
	private FormHome home;
    private FormSales formSales;

	private ProductsData productsData;
	private MnProduct mnProduct;

	private CustomerData customerData;
	private MnCustomer mnCustomer;
	private SupplierData supplierData;
	private MnSupplier mnSupplier;

	private StaffData staffData;
	private MnStaff mnStaff;

	private OrdersData ordersData;
	private MnOrders mnOrders;
	private CollectDebtData collectDData;
	private MnCollectDebt mnCollectD;
	private PurchasesData purchasesData;
	private MnPurchases mnPurchases;
	private PayDebtData payDebtData;
	private MnPayDebt mnPayDebt;
	private AccountData accountData;
	private MnAccount mnAccount;
	private VatLieuController vatLieuController;
	private KhachHangController khController;
	private LSNHController lsController;
	private HoaDonController hdController;
	private NhaCungCapController nccController;
	private NhanVienController nvController;
	private ReportController reportController;
	private DashboardController dashboardController;
	private FormSalesController formSalesController;

	private ThuNoController thuNoController;
	private TraNoController traNoController;


	private Animator animator;
    private MigLayout layout;
    
	private Menu menu;
    private Header header;
    private MainForm main;
    private TaiKhoan taiKhoan;
	public Main(TaiKhoan tk) {
		this.taiKhoan=tk;
		initComponents();
		init();
//        dataTestFormSales();
	}
	public boolean notifyErr(int typeCheck) {
		switch(typeCheck) {
	
//		case 0:
//			if(taiKhoan.getQuyen()<1) {
//				JOptionPane.showMessageDialog(this, "Bạn không có đủ quyền hạn","Lỗi",JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			else return true;
		
		case 1:
			// form trang chu, danh sach hang,ban hang,don hang, khach hang, thu no
			//cho quan ly ban hang, nhan vien quay
			if(taiKhoan.getQuyen()<1) {
				JOptionPane.showMessageDialog(this, "Bạn không có đủ quyền hạn","Lỗi",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else return true;
		case 2:
			//form nha cung cap, nhap hang
			// danh cho quan ly kho
			
			if(taiKhoan.getQuyen()<2) {
				JOptionPane.showMessageDialog(this, "Bạn không có đủ quyền hạn","Lỗi",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else return true;
		case 3:
			//tat ca moi quyen bao gom nhan vien, tai khoan 
			if(taiKhoan.getQuyen()<3) {
				JOptionPane.showMessageDialog(this, "Bạn không có đủ quyền hạn","Lỗi",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else return true;
		}
		return true;
		
	}
	private void init() {
		setBackground(new Color(0, 0, 0, 0));
		menu = new Menu();
        header = new Header();
        main = new MainForm();
        menu.initMoving(Main.this);
        winButton1.initEvent(this, new Background());
        // bố cục , cột và hàng
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        panelBorder1.setLayout(layout);
         //  set when system open start with home form
        panelBorder1.add(menu, "w 200!, spany");    // Span Y 2cell
        panelBorder1.add(header, "h 50!, wrap");
        panelBorder1.add(main, "w 100%, h 100%");
		
//        form1 = new Form_1();
//        form2 = new Form_2();
//        form3 = new Form_3();

		productsData = new ProductsData();
		vatLieuController=new VatLieuController(productsData);
		mnProduct = new MnProduct(productsData,vatLieuController);
		customerData = new CustomerData();
		khController=new KhachHangController(customerData);
		mnCustomer = new MnCustomer(customerData,khController);
		supplierData = new SupplierData();
		nccController=new NhaCungCapController(supplierData);
		mnSupplier = new MnSupplier(supplierData,nccController);
		staffData = new StaffData();
		nvController=new NhanVienController(staffData);


		ordersData = new OrdersData();
		hdController=new HoaDonController(ordersData);
		mnOrders = new MnOrders(ordersData,hdController);
		
		collectDData = new CollectDebtData();
		thuNoController=new ThuNoController(collectDData);
		mnCollectD = new MnCollectDebt(collectDData,thuNoController);
		purchasesData = new PurchasesData();
		lsController=new LSNHController(purchasesData);
		mnPurchases = new MnPurchases(purchasesData,lsController);
		payDebtData = new PayDebtData();
		traNoController=new TraNoController(payDebtData);
		mnPayDebt = new MnPayDebt(payDebtData,traNoController);
		accountData = new AccountData();
		reportController=new ReportController(accountData);
		
		mnAccount = new MnAccount(accountData,reportController);

		mnStaff=new MnStaff(staffData, nvController);	
		// dk them
		collectDData.registerObserver((Observer)mnCustomer);
		customerData.registerObserver((Observer)mnCollectD);
		payDebtData.registerObserver((Observer)mnSupplier);
		supplierData.registerObserver((Observer)mnPayDebt);
		ordersData.registerObserver((Observer)mnCustomer);
		ordersData.registerObserver((Observer)mnCollectD);
		purchasesData.registerObserver((Observer)mnSupplier);
		purchasesData.registerObserver((Observer)mnPayDebt);
		
		formSalesController = new FormSalesController(ordersData);
		formSales = formSalesController.getView();
		customerData.registerObserver(formSales);
		productsData.registerObserver(formSales);
		purchasesData.registerObserver(formSales);
		formSales.addEventBtnAdd(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCustomer(mnCustomer);
				
			}
		});
        dashboardController = new DashboardController(ordersData);
        home = new FormHome(ordersData,dashboardController);
        customerData.registerObserver(home);
		productsData.registerObserver(home);
		purchasesData.registerObserver(home);
		menu.addEventMenuSelected(new EventMenuSelected() {
			@Override
			public void selected(int index) {
				if (index == 0) {
					if(notifyErr(1))
					setForm(home);
				} else if (index == 1) {
					if(notifyErr(1))
					setForm(mnProduct);
				} else if (index == 2) {
					if(notifyErr(2))
					setForm(mnSupplier);
				} else if (index == 3) {
					if(notifyErr(3))
					setForm(mnStaff);
				} else if (index == 4) {
					if(notifyErr(1))
					setForm(mnCustomer);
				} else if (index == 5) {
					if(notifyErr(3))
				setForm(mnAccount);
				} else if (index == 6) {
					if(notifyErr(1))
					setForm(mnOrders);
				} else if (index == 7) {
					if(notifyErr(2))
					setForm(mnPurchases);
				} else if (index == 8) {
					if(notifyErr(1))
					setForm(mnCollectD);
				} else if (index == 9) {
					if(notifyErr(2))
					setForm(mnPayDebt);
					
				}
				else if (index == 10) {
					if(notifyErr(1))
                    setForm(formSales);

				}else if (index == 13) {
					InformationStaff infor=new InformationStaff(taiKhoan.getUserName(),mnAccount);
					JScrollPane scrollPane = new JScrollPane(infor);
					scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollPane.setBounds(50, 30, 300, 50);
					add(scrollPane);
					setForm(scrollPane);
                }
				else if(index ==14) {
                	logout();

                }
			}
		});
		
		TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 58 + (144 * (1f - fraction));
                } else {
                    width = 58 + (144 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");

                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent ae) {
                 if (!animator.isRunning()) {
                     animator.start();
                 }
             }
           
        });
		// set when system open start with home form
        setForm(home);
     // event click product in form sales
        formSales.setEvent(new EventProduct() {
			
			@Override
			public void productClick(Component com, ASanPham product) {
                formSales.setSelected(com);
			}
			}	  
        );
	}
	public void setForm(JComponent com) {
		main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
	}
	public void logout() {
		 this.dispose();
		 DangNhapDangXuatModelInterface logData=new SignInSignUpData();
    	SignupVsSigninControler logController=new SignupVsSigninControler(logData);
    	SigninView signIn=new SigninView(logData, logController);
    	signIn.onView();
	}
//	public void dataTestFormSales(){
//        List<String> listUrl = new ArrayList<>();
//        listUrl.add("/View/img_product/gach.jpg");
//        for (int i = 0; i < 10; i++) {
//            formSales.addProduct(new ASanPham("sp01", "cat 01", "", "Vien", 5555, 55, "cat", listUrl, 5000));
//        }
//        for (int i = 0; i < 10; i++) {
//            formSales.addProduct(new ASanPham("sp01", "cat 0111", "", "Vien", 5555, 55, "cat", listUrl, 5000));
//        }
//    }
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	public void getMain() {
			try {
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (ClassNotFoundException ex) {
				java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			} catch (InstantiationException ex) {
				java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
				java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			} catch (javax.swing.UnsupportedLookAndFeelException ex) {
				java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			// </editor-fold>

			/* Create and display the form */
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
//					new Main(taiKhoan).setVisible(true);
					setVisible(true);
				}
			});
		}
		
	
	private void initComponents() {

		panelBorder1 = new View.swing.PanelBorder();
        jPanel1 = new javax.swing.JPanel();
        winButton1 = new View.swing.WinButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1145, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(winButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(winButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel jPanel1;
    private PanelBorder panelBorder1;
    private WinButton winButton1;
	// End of variables declaration//GEN-END:variables
}
