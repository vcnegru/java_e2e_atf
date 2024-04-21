package com.atf.template.ui.cucumber.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {

    private String productDescription;
    private String testSetCount;
    private int productCount;
    private double productSubtotalPrice;
//    private double productOldSubtotalPrice;
//    private double productSavings;

    public Product(String productDescription, String testSetCount, String productCount, String productSubtotalPrice) {
        this.productDescription = productDescription;
        this.testSetCount = testSetCount;
        this.productCount = Integer.valueOf(productCount);
        this.productSubtotalPrice = Double.valueOf(productSubtotalPrice);
    }

    public Product(String productDescription, int productCount, String testSetCount, double productSubtotalPrice) {
    }
}
