/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.event;

import model.ASanPham;

/**
 *
 * @author trong
 */
public interface EventAction {
    public void delete(ASanPham product);
    public void update(ASanPham product);
}
