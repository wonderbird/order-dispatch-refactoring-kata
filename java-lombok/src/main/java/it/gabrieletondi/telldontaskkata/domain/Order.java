package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.CannotApproveRejectedOrder;
import it.gabrieletondi.telldontaskkata.useCase.CannotRejectApprovedOrder;
import it.gabrieletondi.telldontaskkata.useCase.OrderAlreadyShipped;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public void approveOrReject(OrderApprovalRequest request) {
        if (getStatus().equals(OrderStatus.SHIPPED)) {
            throw new OrderAlreadyShipped();
        }

        if (request.isApproved() && getStatus().equals(OrderStatus.REJECTED)) {
            throw new CannotApproveRejectedOrder();
        }

        if (!request.isApproved() && getStatus().equals(OrderStatus.APPROVED)) {
            throw new CannotRejectApprovedOrder();
        }

        setStatus(request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED);
    }
}
