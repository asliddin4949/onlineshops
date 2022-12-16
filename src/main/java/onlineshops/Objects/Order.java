package onlineshops.Objects;

import lombok.Getter;
import lombok.Setter;
import onlineshops.DataStorage.Storage;
import onlineshops.Interfaces.UserInterface;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class Order {
    public static int orderId = 1;
    private int id;
    User user;
    private BigDecimal totalSum;
    List<OrderProduct> orderProducts;


    public Order(int id, User user, List<OrderProduct> orderProducts) {
        this.id = id;
        this.user = user;
        this.orderProducts = orderProducts;
    }

    public static void showOrders(List<Order> collects) {
        for (Order collect : collects) {
            System.out.println("Order Id: " + collect.getId()
                    + "\nTotal Sum: " + collect.getTotalSum());
            int j = 1;
            for (OrderProduct orderProduct : collect.getOrderProducts()) {
                System.out.println("Product - " + j
                        + "\nProduct Name: " + orderProduct.getProduct().getName()
                        + "\nShop Name: " + orderProduct.getShop().getName()
                        + "\nProduct Price: " + orderProduct.getProduct().getPrice()
                        + "\nQuantity: " + orderProduct.getQuantity()
                        + "\nCost: " + orderProduct.getCost()
                        + "\n- - - - - - - - - - - - - -");
                j++;
            }
        }
    }

}
