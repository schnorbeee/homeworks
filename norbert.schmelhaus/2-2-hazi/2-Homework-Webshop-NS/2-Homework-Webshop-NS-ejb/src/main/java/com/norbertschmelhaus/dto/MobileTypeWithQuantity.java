package com.norbertschmelhaus.dto;

import java.io.Serializable;

/**
 *
 * @author norbeee
 */
public class MobileTypeWithQuantity implements Serializable {
    
    private int quantity;
    private MobileType mobile;

    public MobileTypeWithQuantity() {
        //Default contructor
    }

    public MobileTypeWithQuantity(int quantity, MobileType mobile) {
        this.quantity = quantity;
        this.mobile = mobile;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MobileType getMobile() {
        return mobile;
    }

    public void setMobile(MobileType mobile) {
        this.mobile = mobile;
    }

}
