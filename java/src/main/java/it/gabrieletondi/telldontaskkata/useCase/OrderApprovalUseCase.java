package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

public class OrderApprovalUseCase {
    private final OrderRepository orderRepository;

    public OrderApprovalUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(OrderApprovalRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());
        boolean isApproved = request.isApproved();
        if (order.getStatus().equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }
        if (isApproved) {
            if (order.getStatus().equals(OrderStatus.REJECTED)) {
                throw new RejectedOrderCannotBeApprovedException();
            }

            order.setStatus(OrderStatus.APPROVED);
        } else {
            if (order.getStatus().equals(OrderStatus.APPROVED)) {
                throw new ApprovedOrderCannotBeRejectedException();
            }
            order.setStatus(OrderStatus.REJECTED);
        }
        orderRepository.save(order);
    }
}
