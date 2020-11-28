package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private Money price;
    private Category category;

    public static Product create(final String salad, final BigDecimal price, final Category food) {
        Product product = new Product();
        product.name = salad;
        product.price = new Money(price);
        product.category = food;
        return product;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.value();
    }

    public Money tax() {
        return price.multiply(category.taxRate());
    }

    public Money taxedPrice() {
        return price.add(tax());
    }

    public BigDecimal taxedPriceTimes(int quantity) {
        return taxedPrice().multiply(quantity).value();
    }

    public BigDecimal taxTimes(int quantity) {
        return tax().multiply(quantity).value();
    }

    public static class Money {
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
}
