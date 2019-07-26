package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

public class OrderApprovalRequest {
    private final int orderId;
    private final boolean approved;

    public static OrderApprovalRequest of(int orderId, boolean approved) {
        return new OrderApprovalRequest(orderId, approved);
    }

    private OrderApprovalRequest(int orderId, boolean approved) {
        this.orderId = orderId;
        this.approved = approved;
    }

    public int getOrderId() {
        return orderId;
    }

    public void handle(Order order) {
        if (approved) {
            order.approve();
            return;
        }
        order.reject();
    }
}
