package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.CannotApproveRejectedOrder;
import it.gabrieletondi.telldontaskkata.useCase.CannotRejectApprovedOrder;
import it.gabrieletondi.telldontaskkata.useCase.OrderAlreadyShipped;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderNotShippable;

import java.math.BigDecimal;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void approveOrReject(OrderApprovalRequest request) {
        if (status.equals(OrderStatus.SHIPPED)) {
            throw new OrderAlreadyShipped();
        }

        if (request.isApproved() && status.equals(OrderStatus.REJECTED)) {
            throw new CannotApproveRejectedOrder();
        }

        if (!request.isApproved() && this.isApproved()) {
            throw new CannotRejectApprovedOrder();
        }

        this.status = request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED;
    }

    public void ship(ShipmentService shipmentService) {
        if (status.equals(CREATED) || this.isRejected()) {
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
        return status.equals(REJECTED);
    }
}
