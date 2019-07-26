package it.gabrieletondi.telldontaskkata.domain;

public class Product {
    private final String name;
    private final Category category;
    private final Money netto;

    public static Product of(String name, Category category, Money netto) {
        return new Product(name, category, netto);
    }

    private Product(String name, Category category, Money netto) {
        this.name = name;
        this.category = category;
        this.netto = netto;
    }

    public String getName() {
        return name;
    }

    public Money netto() {
        return netto;
    }

    public Money tax() {
        return netto.tax(category.getTaxPercentage());
    }

    public Money brutto() {
        return netto.plusTax(category.getTaxPercentage());
    }
}
