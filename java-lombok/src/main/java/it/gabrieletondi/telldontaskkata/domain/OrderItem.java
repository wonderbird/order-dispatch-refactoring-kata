package it.gabrieletondi.telldontaskkata.domain;

import lombok.Data;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Data
public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public static OrderItem create(Product product, int quantity) {
        final OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setTax(product.tax().multiply(BigDecimal.valueOf(quantity)));
        orderItem.setTaxedAmount(product.taxedPrice()
            .multiply(BigDecimal.valueOf(quantity))
            .setScale(2, HALF_UP));
        return orderItem;
    }
}
