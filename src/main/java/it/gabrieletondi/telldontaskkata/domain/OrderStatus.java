package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedTwiceException;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.ShippedOrdersCannotBeChangedException;

public enum OrderStatus {
    APPROVED, REJECTED, SHIPPED, CREATED;

    void assertShippable() {
        if (equals(CREATED) || equals(REJECTED)) {
            throw new OrderCannotBeShippedException();
        }

        if (equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwiceException();
        }
    }

    void assertApprovable() {
        assertNotShipped();
        if (equals(REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }
    }

    void assertRejectable() {
        assertNotShipped();
        if (equals(APPROVED)) {
            throw new ApprovedOrderCannotBeRejectedException();
        }
    }

    void assertNotShipped() {
        if (equals(SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }
    }
}
