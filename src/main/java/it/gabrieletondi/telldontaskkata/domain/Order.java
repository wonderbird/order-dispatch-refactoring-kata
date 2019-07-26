package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;
import it.gabrieletondi.telldontaskkata.useCase.ShippedOrdersCannotBeChangedException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Order {
    private final int id;
    private final List<OrderItem> items;
    private OrderStatus status;

    public static Order of(int id, OrderStatus status) {
        return new Order(id, new ArrayList<>(), status);
    }

    public static Order createEmpty() {
        return new Order(0, new ArrayList<>(), OrderStatus.CREATED);
    }

    public Order(int id, List<OrderItem> items, OrderStatus status) {
        this.items = items;
        this.status = status;
        this.id = id;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Money getTotal() {
        return sumUp(item -> item.getTaxedAmount());
    }

    public Money getTax() {
        return sumUp(item -> item.getTax());
    }

    private Money sumUp(Function<OrderItem, Money> which) {
        return items.stream()
            .map(which)
            .reduce(Money.zero(), (cur, next) -> cur.add(next));
    }

    public String getCurrency() {
        return getTotal().getCurrency();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void addItem(SellItemRequest itemRequest, ProductCatalog productCatalog) {
        itemRequest.addTo(items, productCatalog);
    }

    public void shipIt(ShipmentService shipmentService) {
        status.assertShippable();

        shipmentService.ship(this);

        status = OrderStatus.SHIPPED;
    }

    public void approve() {
        assertNotShipped();
        if (status.equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }
        setStatus(OrderStatus.APPROVED);
    }

    public void reject() {
        assertNotShipped();
        if (status.equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejectedException();
        }
        setStatus(OrderStatus.REJECTED);
    }

    private void assertNotShipped() {
        if (status.equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }
    }
}
