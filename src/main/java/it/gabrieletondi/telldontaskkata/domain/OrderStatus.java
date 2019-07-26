package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedTwiceException;

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
}
