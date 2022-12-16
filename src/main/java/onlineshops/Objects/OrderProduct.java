package onlineshops.Objects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class OrderProduct {
    private Product product;
    private double quantity;
    Shop shop;
    private BigDecimal cost;

    public OrderProduct(Product product,Shop shop, double quantity, BigDecimal cost) {
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }
}
