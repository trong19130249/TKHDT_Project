/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.swing;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonCyc extends JButton {

    public ButtonCyc() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(6, 6, 6, 6));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        g2.setColor(getBackground());
        g2.fillOval(x, y, size, size);
        super.paint(grphcs);
    }
}