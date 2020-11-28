package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private BigDecimal price;
    private Category category;

    public static Product create(final String salad, final BigDecimal price, final Category food) {
        Product product = new Product();
        product.name = salad;
        product.price = new Money(price).value();
        product.category = food;
        return product;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return new Money(price).value();
    }

    public BigDecimal tax() {
        return new Money(price).multiply(category.getTaxPercentage().divide(valueOf(100), 2, HALF_UP));
    }

    public BigDecimal taxedPrice() {
        return new Money(price).value().add(tax()).setScale(2, HALF_UP);
    }

    public BigDecimal taxedPriceTimes(int quantity) {
        return new Money(taxedPrice()).multiply(valueOf(quantity));
    }

    public BigDecimal taxTimes(int quantity) {
        return new Money(tax()).multiply(valueOf(quantity));
    }

    public static class Money {
        private BigDecimal value;

        public Money(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal value() {
            return value;
        }

        public BigDecimal multiply(BigDecimal multiplicand) {
            return value().multiply(multiplicand)
                .setScale(2, HALF_UP);
        }
    }
}
