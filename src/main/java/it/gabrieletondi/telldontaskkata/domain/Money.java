package it.gabrieletondi.telldontaskkata.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Money {
    private final BigDecimal price;
    private final String currency = "EUR";

    public static Money of(String value) {
        return of(new BigDecimal(value));
    }

    public static Money of(BigDecimal value) {
        return new Money(value);
    }

    public static Money zero() {
        return of("0.00");
    }

    private Money(BigDecimal price) {
        this.price = price.setScale(2, HALF_UP);
    }

    public Money tax(BigDecimal percentage) {
        return new Money(price.divide(valueOf(100)).multiply(percentage));
    }

    public Money plusTax(BigDecimal taxPercentage) {
        return add(tax(taxPercentage));
    }

    public Money add(Money money) {
        return new Money(price.add(money.price));
    }

    public Money multiply(int factor) {
        return new Money(price.multiply(valueOf(factor)));
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "" + price;
    }
}
