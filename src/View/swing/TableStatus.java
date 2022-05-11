/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.swing;

import java.awt.Color;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import View.model.StatusType;

/**
 *
 * @author trong
 */
public class TableStatus extends JLabel{
    private StatusType type;

     public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }


    public void setType(StatusType type) {
        this.type = type;
        String text="";
        switch (type) {
            case ACCEPT:
                text="Chờ xác nhận";
                break;
            case SHIP:
                text="Đang giao";
                break;
            case RECEIVE:
                text="Đã giao";
                break;
            case CANCELLED:
                text="Đã huỷ";
                break;
            default:
                throw new AssertionError();
        }
        setText(text);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;   
            switch (type) {
                case ACCEPT:
                    g = new GradientPaint(0, 0, new Color(180, 80, 251), 0, getHeight(), new Color(160, 60, 231));
                    break;
                case SHIP:
                    g = new GradientPaint(0, 0, new Color(80, 198, 250), 0, getHeight(), new Color(60, 180, 230));
                    break;
                case RECEIVE:
                    g = new GradientPaint(0, 0, new Color(100, 255, 94), 0, getHeight(), new Color(80, 235, 74));
                    break;
                case CANCELLED:
                    g = new GradientPaint(0, 0, new Color(255, 76, 33), 0, getHeight(), new Color(235, 56, 13));
                    break;
                default:
                    g = new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(211, 184, 61));
                    break;
            }
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        super.paintComponent(grphcs);
    }
}
