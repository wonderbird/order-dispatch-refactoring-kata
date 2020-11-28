package it.gabrieletondi.telldontaskkata.domain;

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

    public Product.Money taxedAmount() {
        return product.taxedPriceTimes(quantity);
    }

    public Product.Money tax() {
        return product.taxTimes(quantity);
    }

}
