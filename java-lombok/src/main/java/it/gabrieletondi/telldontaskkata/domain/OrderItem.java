package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public static OrderItem create(Product product, int quantity) {
        final OrderItem orderItem = new OrderItem();
        orderItem.product = product;
        orderItem.quantity = quantity;
        orderItem.tax = multiply(product.tax(), quantity);
        orderItem.taxedAmount = multiply(product.taxedPrice(), quantity);
        return orderItem;
    }

    public static BigDecimal multiply(BigDecimal value, int quantity) {
        return value.multiply(BigDecimal.valueOf(quantity))
            .setScale(2, HALF_UP);
    }
}
