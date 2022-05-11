/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.event;

import java.awt.Component;

import model.ASanPham;

/**
 *
 * @author trong
 */
public interface EventProduct {
    public void productClick(Component com, ASanPham product);
}
