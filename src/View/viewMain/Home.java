package View.viewMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Home extends JPanel {
	
	public Home() {

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.setFont(new Font("Calibri", Font.BOLD, (int) (getWidth() / 18)));
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth("Phần mềm quản lí vật liệu xây dựng");
		int stringAscent = fm.getAscent();

		int xCoordinate = getWidth() / 2 - stringWidth / 2;
		int yCoordinate = getHeight() / 2 + stringAscent / 2;

		g.drawString("Phần mềm quản lí vật liệu xây dựng", xCoordinate, yCoordinate);

	}
}
