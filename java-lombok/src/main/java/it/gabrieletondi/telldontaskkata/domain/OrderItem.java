package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;

    public static OrderItem create(Product product, int quantity) {
        final OrderItem orderItem = new OrderItem();
        orderItem.product = product;
        orderItem.quantity = quantity;
        return orderItem;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return product.taxedPriceTimes(quantity).value();
    }

    public BigDecimal getTax() {
        return product.taxTimes(quantity).value();
    }

}
