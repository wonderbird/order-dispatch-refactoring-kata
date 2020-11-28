package it.gabrieletondi.telldontaskkata;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;

public class Fixtures {
    public static Order createOrder(OrderStatus status) {
        return Order.create(1, status);
    }
}
