package it.gabrieletondi.telldontaskkata.domain;

public class OrderItem {
    private Product product;
    private int quantity;

    public static OrderItem createOrderItem(Product product, int quantity) {
        final OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        return orderItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Money getTaxedAmount() {
        return product.brutto().multiply(quantity);
    }

    public Money getTax() {
        return product.tax().multiply(quantity);
    }

}
