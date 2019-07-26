package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.util.List;

public class SellItemRequest {
    private final String productName;
    private final int quantity;

    public static SellItemRequest of(String productName, int quantity) {
        return new SellItemRequest(productName, quantity);
    }

    private SellItemRequest(String productName, int quantity) {
        this.quantity = quantity;
        this.productName = productName;
    }

    public boolean addTo(List<OrderItem> items, ProductCatalog productCatalog) {
        return items.add(OrderItem.createOrderItem(productCatalog.getByName(productName), quantity));
    }
}
