package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Category {
    private String name;
    private BigDecimal taxPercentage;

    public void setName(String name) {
        this.name = name;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal taxRate() {
        return taxPercentage.divide(valueOf(100), 2, HALF_UP);
    }
}
