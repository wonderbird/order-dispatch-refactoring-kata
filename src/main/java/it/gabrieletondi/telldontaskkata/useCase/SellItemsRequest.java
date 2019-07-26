package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.util.List;

import static java.util.Arrays.asList;

public class SellItemsRequest {
    private final List<SellItemRequest> requests;

    public static SellItemsRequest of(SellItemRequest... requests) {
        return new SellItemsRequest(asList(requests));
    }

    private SellItemsRequest(List<SellItemRequest> requests) {
        this.requests = requests;
    }

    void addToOrder(Order order, ProductCatalog productCatalog) {
        for (SellItemRequest itemRequest : requests) {
            order.addItem(itemRequest, productCatalog);
        }
    }
}
