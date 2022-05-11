
package View.form;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.DashboardController;
import View.component.Card;
import View.main.Main;
import View.swing.PanelBorder;
import View.swing.ScrollBar;
import View.swing.Table;
import model.DonHangModelInterface;
import model.Observer;
import View.model.Model_Card;


public class FormHome extends JPanel implements Observer {
	private DashboardController con;
	private DonHangModelInterface data;
	private Model_Card mc1;
	private Model_Card mc2;
	private Model_Card mc3;
	 // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.component.Card card1;
    private View.component.Card card2;
    private View.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane panel;
    private PanelBorder panelBorder1;
    private Table table;
    public FormHome(DonHangModelInterface data,DashboardController con) {
    	this.con = con;
    	this.data =data;
        initComponents();
		data.registerObserver(this);
		updateCard();
        // add row table
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        dataStart();
    }
    public void updateCard() {
    	mc1 = new Model_Card(new ImageIcon(getClass().getResource("/View/icon/profit.png")), "Tổng lợi nhuận", data.totalProfitMonth()+"",formatPercent((data.getRatioTotalOrderMonth(1))));
        mc2 = new Model_Card(new ImageIcon(getClass().getResource("/View/icon/stock.png")), "Số lượng nhập hàng", data.totalReceivedMonth()+"", formatPercent((data.getRatioTotalOrderMonth(2))));
        mc3 =new Model_Card(new ImageIcon(getClass().getResource("/View/icon/flag.png")), "Số hoá đơn", data.countOrderMonth()+"", formatPercent((data.getRatioTotalOrderMonth(0))));
        card2.setData(mc2); 
        card1.setData(mc1);
        card3.setData(mc3);
    }
    public String formatPercent(double input) {
    	if (input == Double.POSITIVE_INFINITY||Double.isNaN(input)) {
			return"";
		}
    	double result =(double) Math.round(input* 100*100) / 100 -100;
    	if (result>0) {
			return "Tăng "+result+"%";
		}else if(result<0){
			return "Giảm "+Math.abs(result)+"%";
		}else {
	    	return result+"%";
		}
    }
    public void dataStart() {
        table.addArray(data.getDataTable());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new Card();
        card2 = new Card();
        card3 = new Card();
        panelBorder1 = new PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Table();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh sách đơn hàng");

        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách hàng", "Tổng tiền", "Địa chỉ", " Ngày lập", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


   
    // End of variables declaration//GEN-END:variables
	@Override
	public void update() {
		table.addArray(data.getDataTable());
		updateCard();
	}
}
