package it.gabrieletondi.telldontaskkata;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;

public class OrderTestDataBuilder {

    private Order order = new Order();

    public OrderTestDataBuilder() {
        order.setId(1);
    }

    public OrderTestDataBuilder withStatus(OrderStatus status) {
        if (status == OrderStatus.CREATED)
        {
            // Do nothing
        } else if (status == OrderStatus.REJECTED) {
            order.reject();
        } else if (status == OrderStatus.APPROVED) {
            order.approve();
        }
        return this;
    }

    public Order build() {

        return order;
    }
}
