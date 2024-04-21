package com.atf.template.ui.cucumber.context;

import com.atf.template.ui.cucumber.helper.ProductMiniCart;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MiniCart {

    private static final List<ProductMiniCart> miniCartProductsList = new ArrayList<>();
    @Getter
    private static double totalAmount = 0.0;

    public static void addProductToMiniCart(ProductMiniCart productMiniCart) {
        int index = checkAlreadyInCart(productMiniCart);
        if (index >= 0) {
            miniCartProductsList.get(index).setProductCount(miniCartProductsList.get(index).getProductCount() + productMiniCart.getProductCount());
            miniCartProductsList.get(index).setProductSubtotalPrice(miniCartProductsList.get(index).getProductSubtotalPrice() + productMiniCart.getProductSubtotalPrice());
//            productMiniCart.setProductCount(productMiniCart.getProductCount() + productMiniCart.getProductCount());
//            productMiniCart.setProductSubtotalPrice(productMiniCart.getProductSubtotalPrice() + productMiniCart.getProductSubtotalPrice());
            log.info("Product has been updated to MiniCart: {}", miniCartProductsList.get(index).toString());
        } else {
            miniCartProductsList.add(productMiniCart);
            log.info("Product has been added to MiniCart: {}", productMiniCart.toString());
        }

        totalAmount += productMiniCart.getProductSubtotalPrice();
        log.info("Total cost: {}", totalAmount);
    }

    public static int checkAlreadyInCart(ProductMiniCart productMiniCart) {
        int index = 0;
        for (ProductMiniCart product : miniCartProductsList) {
            if (product.getProductName().equals(productMiniCart.getProductName()) && product.getProductQuantity().equals(productMiniCart.getProductQuantity())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static void resetMiniCartContext() {
        miniCartProductsList.clear();
        totalAmount = 0.0;
    }

    public static int getMiniCartSize() {
        return miniCartProductsList.size();
    }

    public static int getMiniCartItemsCount() {
        int totalProductCount = miniCartProductsList.stream()
                .mapToInt(ProductMiniCart::getProductCount)
                .sum();
        return totalProductCount;
    }

    public static List<ProductMiniCart> getAll() {
        return miniCartProductsList;
    }

    public static ProductMiniCart getByIndex(int index) {
        return miniCartProductsList.get(index);
    }
}
