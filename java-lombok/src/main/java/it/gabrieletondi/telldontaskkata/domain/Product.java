package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

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

    public Money taxedPriceTimes(int quantity) {
        return taxedPrice().multiply(quantity);
    }

    public Money taxTimes(int quantity) {
        return tax().multiply(quantity);
    }

}
