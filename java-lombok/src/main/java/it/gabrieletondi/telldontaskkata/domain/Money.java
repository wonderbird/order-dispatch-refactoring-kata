package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Money {
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }

    public Money multiply(int quantity) {
        return multiply(valueOf(quantity));
    }

    public Money multiply(BigDecimal multiplicand) {
        return new Money(value.multiply(multiplicand)
            .setScale(2, HALF_UP));
    }

    public Money add(Money amount) {
        return new Money(value.add(amount.value).setScale(2, HALF_UP));
    }
}
