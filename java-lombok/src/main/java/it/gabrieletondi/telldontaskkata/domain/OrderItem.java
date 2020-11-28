package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return multiply(product.taxedPrice(), quantity);
    }

    public BigDecimal getTax() {
        return multiply(product.tax(), quantity);
    }

    public static OrderItem create(Product product, int quantity) {
        final OrderItem orderItem = new OrderItem();
        orderItem.product = product;
        orderItem.quantity = quantity;
        return orderItem;
    }

    public static BigDecimal multiply(BigDecimal value, int quantity) {
        return value.multiply(BigDecimal.valueOf(quantity))
            .setScale(2, HALF_UP);
    }
}
