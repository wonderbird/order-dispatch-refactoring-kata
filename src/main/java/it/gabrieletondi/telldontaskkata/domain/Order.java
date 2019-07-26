package it.gabrieletondi.telldontaskkata.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Order {
    private List<OrderItem> items;
    private OrderStatus status;
    private int id;

    public static Order createEmpty() {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setItems(new ArrayList<>());
        return order;
    }

    public Money getTotal() {
        return sumUp(item -> item.getTaxedAmount());
    }

    public Money getTax() {
        return sumUp(item -> item.getTax());
    }

    private Money sumUp(Function<OrderItem, Money> which) {
        return items.stream()
            .map(which)
            .reduce(Money.zero(), (cur, next) -> cur.add(next));
    }

    public String getCurrency() {
        return getTotal().getCurrency();
    }

    public void setCurrency() {
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addItemToOrder(OrderItem orderItem) {
        items.add(orderItem);
    }

}
