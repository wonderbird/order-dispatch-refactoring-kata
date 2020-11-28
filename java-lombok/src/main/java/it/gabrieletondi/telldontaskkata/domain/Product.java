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
        product.price = price;
        product.category = food;
        return product;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal tax() {
        return price.multiply(category.getTaxPercentage().divide(valueOf(100), 2, HALF_UP))
            .setScale(2, HALF_UP);
    }

    public BigDecimal taxedPrice() {
        return price.add(tax()).setScale(2, HALF_UP);
    }
}
