package onlineshops.Interfaces;

import onlineshops.DataStorage.Storage;
import onlineshops.Objects.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static onlineshops.Objects.ShopProduct.showShopProduct;

public class UserInterface {
    public static void userMenu() {
        Storage.orderProducts.clear();
        System.out.println("* * * U s e r * * * I n t e r f a c e * * *");
        System.out.println("'1' - \"Order Product\" '2' - \"Show my orders\" '0' - \"exit\"");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        if (command.equals("1")) {
            orderProduct();
        } else if (command.equals("2")) {
            List<Order> collect = Storage.orders.stream().filter(order -> order.getUser().getId() == User.dynamicUserId).toList();
            Order.showOrders(collect);
            userMenu();
        } else if (command.equals("0")) {
            MainMenu.mainMenu();
        } else {
            System.out.println("Wrong command!");
            userMenu();
        }
    }

    static void orderProduct() {
        Scanner scanner = new Scanner(System.in);
        if(!Storage.shops.isEmpty()){
            Shop.showShops();
        }else {
            System.out.println("No shops added yet!");
            userMenu();
        }
        System.out.println("Enter shop Id: ");
        int shopId = scanner.nextInt();
        Shop shop = Shop.getShop(shopId);
        List<ShopProduct> shopProducts = showShopProduct(shopId);
        System.out.println("Enter Product Id: ");
        int productId = scanner.nextInt();
        ShopProduct shopProduct = shopProducts.stream().filter(shopProduct1 ->
                shopProduct1.getProduct().getId() == productId).findFirst().orElse(null);
        System.out.println("Enter quantity: ");
        double quantity = scanner.nextDouble();
        setOrder(shopProduct,shop,quantity);
    }

    static void setOrder(ShopProduct shopProduct, Shop shop, double quantity){
        if (shop != null && shopProduct != null) {
            if (quantity > 0 && quantity <= shopProduct.getQuantity()) {
                BigDecimal cost = shopProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity));
                Storage.orderProducts.add(new OrderProduct(shopProduct.getProduct(), shopProduct.getShop(), quantity, cost));
                shopProduct.setQuantity(shopProduct.getQuantity() - quantity);
                endOrder();
            } else {
                System.out.println("Quantity is not enough!");
                orderProduct();
            }
        } else {
            System.out.println("Wrong choice Shop or Product!");
            orderProduct();
        }
    }

    static void endOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("'1' - \"continue order  \" '0' - \"exit\"");
        int command = scanner.nextInt();
        if (command == 1) {
            orderProduct();
        } else if (command == 0) {
            var orderProducts = new ArrayList<>(Storage.orderProducts);
            Order order = new Order(Order.orderId, User.getUser(User.dynamicUserId), orderProducts);
            order.setTotalSum(calculateTotalSum());
            Storage.orders.add(order);
            Order.orderId++;
            System.out.println("Thank you for your Order!");
            userMenu();

        } else {
            System.out.println("Wrong command!");
            endOrder();
        }
    }

    static BigDecimal calculateTotalSum() {
        return Storage.orderProducts.stream().map(OrderProduct::getCost).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}

