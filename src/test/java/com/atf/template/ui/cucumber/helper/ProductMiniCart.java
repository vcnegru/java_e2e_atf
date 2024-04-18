package com.atf.template.ui.cucumber.helper;

import lombok.*;

@Getter
@Setter
//@Builder
@AllArgsConstructor
@ToString
public class ProductMiniCart {

    private String productName;
    private String productQuantity;
    private int productCount;
    private double productSubtotalPrice;

}
