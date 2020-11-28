package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.Fixtures;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import org.junit.Test;

import static it.gabrieletondi.telldontaskkata.Fixtures.createOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OrderApprovalUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

    @Test
    public void approvesExistingOrder() {
        Order initialOrder = createOrder(OrderStatus.CREATED);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);
        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertTrue(savedOrder.isApproved());
    }

    @Test
    public void rejectsExistingOrder() {
        Order initialOrder = createOrder(OrderStatus.CREATED);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);
        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertTrue(savedOrder.isRejected());
    }

    @Test(expected = CannotApproveRejectedOrder.class)
    public void cannotApproveRejectedOrder() {
        Order initialOrder = createOrder(OrderStatus.REJECTED);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);
        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = CannotRejectApprovedOrder.class)
    public void cannotRejectApprovedOrder() {
        Order initialOrder = createOrder(OrderStatus.APPROVED);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);
        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = OrderAlreadyShipped.class)
    public void shippedOrdersCannotBeApproved() {
        Order initialOrder = createOrder(OrderStatus.SHIPPED);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = OrderAlreadyShipped.class)
    public void shippedOrdersCannotBeRejected() {
        Order initialOrder = createOrder(OrderStatus.SHIPPED);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);
        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }
}
