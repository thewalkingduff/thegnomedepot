package com.devduffy.gnomedepot.form;

import com.devduffy.gnomedepot.entity.Product;

public class CartItem {
    private Integer productId;
    private Integer quantity;
    
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
   
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    

}
