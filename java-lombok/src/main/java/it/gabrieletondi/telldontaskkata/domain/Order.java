package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.CannotApproveRejectedOrder;
import it.gabrieletondi.telldontaskkata.useCase.CannotRejectApprovedOrder;
import it.gabrieletondi.telldontaskkata.useCase.OrderAlreadyShipped;
import it.gabrieletondi.telldontaskkata.useCase.OrderNotShippable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;
import static java.math.BigDecimal.valueOf;

public class Order {
    private String currency = "EUR";
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = CREATED;
    private int id;

    public static Order create(int id, OrderStatus status) {
        Order initialOrder = new Order();
        initialOrder.id = id;
        initialOrder.status = status;
        return initialOrder;
    }

    public BigDecimal getTotal() {
        return items.stream()
            .map(orderItem -> orderItem.taxedAmount().value())
            .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return items.stream()
            .map(orderItem -> orderItem.tax().value())
            .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public int getId() {
        return id;
    }

    public void approve() {
        assertNotShipped();

        if (isRejected()) {
            throw new CannotApproveRejectedOrder();
        }

        this.status = OrderStatus.APPROVED;
    }

    public void reject() {
        assertNotShipped();

        if (isApproved()) {
            throw new CannotRejectApprovedOrder();
        }

        this.status = OrderStatus.REJECTED;
    }

    public void ship(ShipmentService shipmentService) {
        if (isCreated() || this.isRejected()) {
            throw new OrderNotShippable();
        }

        if (status.equals(SHIPPED)) {
            throw new OrderAlreadyShipped();
        }

        shipmentService.ship(this);

        this.status = OrderStatus.SHIPPED;
    }

    public boolean isApproved() {
        return status.equals(OrderStatus.APPROVED);
    }

    public boolean isRejected() {
        return status.equals(OrderStatus.REJECTED);
    }

    public boolean isCreated() {
        return status.equals(OrderStatus.CREATED);
    }

    public boolean isShipped() {
        return status.equals(SHIPPED);
    }

    private void assertNotShipped() {
        if (status.equals(OrderStatus.SHIPPED)) {
            throw new OrderAlreadyShipped();
        }
    }

    public void addProduct(Product product, int quantity) {
        items.add(OrderItem.create(product, quantity));
    }

}
