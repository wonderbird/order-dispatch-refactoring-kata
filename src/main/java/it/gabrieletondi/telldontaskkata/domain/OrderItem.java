package it.gabrieletondi.telldontaskkata.domain;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public static OrderItem createOrderItem(Product product, int quantity) {
        return new OrderItem(product, quantity);
    }

    private OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getTaxedAmount() {
        return product.brutto().multiply(quantity);
    }

    public Money getTax() {
        return product.tax().multiply(quantity);
    }

}
