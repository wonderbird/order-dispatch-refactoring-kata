package it.gabrieletondi.telldontaskkata.domain;

public class Product {
    private String name;
    private Money price;
    private Category category;

    public static Product create(final String salad, final Money price, final Category food) {
        Product product = new Product();
        product.name = salad;
        product.price = price;
        product.category = food;
        return product;
    }

    public String getName() {
        return name;
    }

    public Money price() {
        return price;
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
