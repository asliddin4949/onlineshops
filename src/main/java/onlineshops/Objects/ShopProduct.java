package onlineshops.Objects;

import lombok.Getter;
import lombok.Setter;
import onlineshops.DataStorage.Storage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopProduct {
    private Shop shop;
    private Product product;
    private double quantity;

    public static List<ShopProduct> showShopProduct(int id){
        List<ShopProduct> shopProducts = Storage.shopProducts.stream().filter(shopProduct ->
                shopProduct.getShop().getId() == id&&shopProduct.getQuantity()>0).toList();
        shopProducts.forEach(System.out::println);
        return shopProducts;
    }

    @Override
    public String toString() {
        return "ShopProduct:\n" +
                product +
                "Quantity: " + quantity +
                "\n* * * * * * * * ";
    }

    public ShopProduct(Shop shop, Product product, double quantity) {
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
    }
}
