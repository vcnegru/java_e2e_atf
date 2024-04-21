package com.atf.template.ui.cucumber.data;

public class DataActions {
    //add items to in mem MiniProductCart and ProductCart storages
    //implement actions
    public final static String PRODUCT_COUNT_XPATH = "//div[@role='table']//div[@role='row']/div[@role='cell']//div[@class='_99ss3s1 _1fragemou _1fragemnl _1fragemps _99ss3s4 _99ss3s2 _99ss3s8']/div";
    public final static String PRODUCT_DESCRIPTION_XPATH = "//div[@role='table']//div[@role='row']/div[@role='cell']//p[@class='_1x52f9s1 _1fragemms _1x52f9so _1fragemp3']";
    public final static String PRODUCT_TESTS_SET_XPATH = "//div[@role='table']//div[@role='row']/div[@role='cell']//p[contains(text(),'test')]";
    public final static String PRODUCT_SUBTOTAL_XPATH = "//div[@role='table']//div[@role='row']/div[@role='cell']//div[contains(@class,'bua0H')]/*[self::p or self::span]";
}
