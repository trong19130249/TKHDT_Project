/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.model;

import View.event.EventAction;
import model.ASanPham;

/**
 *
 * @author trong
 */

public class ModelAction {

	private ASanPham product;
	private EventAction event;

	public ModelAction() {
	}

	public ModelAction(ASanPham product, EventAction event) {
		this.product = product;
		this.event = event;
	}

	public ASanPham getProduct() {
		return product;
	}

	public void setProduct(ASanPham product) {
		this.product = product;
	}

	public EventAction getEvent() {
		return event;
	}

	public void setEvent(EventAction event) {
		this.event = event;
	}

}
