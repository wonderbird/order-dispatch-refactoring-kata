package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

public class Category {
    private final String name;
    private final BigDecimal taxPercentage;

    public static Category of(String name, BigDecimal taxPercentage) {
        return new Category(name, taxPercentage);
    }

    private Category(String name, BigDecimal taxPercentage) {
        this.name = name;
        this.taxPercentage = taxPercentage;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

}
