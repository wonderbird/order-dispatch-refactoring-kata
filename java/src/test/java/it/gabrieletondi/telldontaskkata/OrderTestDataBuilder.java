package it.gabrieletondi.telldontaskkata;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;

public class OrderTestDataBuilder {
    public OrderTestDataBuilder withStatus(OrderStatus created) {
        return this;
    }

    public Order build() {
        Order result = new Order();

        result.setStatus(OrderStatus.CREATED);
        result.setId(1);

        return result;
    }
}
