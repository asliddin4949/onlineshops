package onlineshops.Interfaces;

import onlineshops.DataStorage.Storage;
import onlineshops.Objects.Order;
import onlineshops.Objects.Product;
import onlineshops.Objects.Shop;
import onlineshops.Objects.ShopProduct;

import java.util.Scanner;

public class AdminInterface {
    static void adminMenu() {
        System.out.println("* * * Welcome to Admin Interface * * *");
        System.out.println("'1' - \"add shop\" '2' - \"add product\" '3' - \"add product to shop\" " +
                "\n'4' - \"show shops\"  '5' - \"show products\" '6' - \"Orders\"  '0' - \"exit\"");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        if (Integer.parseInt(command) == 1) {
            addShopToList();
        } else if (Integer.parseInt(command) == 2) {
            addProductToList();
        } else if (Integer.parseInt(command) == 3) {
            addShopProductToList();
        } else if (Integer.parseInt(command) == 4) {
            Shop.showShops();
            adminMenu();
        } else if (Integer.parseInt(command) == 5) {
            Product.showProducts();
            adminMenu();
        } else if (Integer.parseInt(command)==6) {
            Order.showOrders(Storage.orders);
            adminMenu();
        } else if (Integer.parseInt(command) == 0) {
            MainMenu.mainMenu();
        } else {
            System.out.println("Wrong command!");
            adminMenu();
        }

    }

    static void addShopProductToList() {
        Shop.showShops();
        System.out.println("Enter Shop ID: ");
        Scanner scanner = new Scanner(System.in);
        int shopId = scanner.nextInt();
        Product.showProducts();
        System.out.println("Enter product ID:");
        int productId = scanner.nextInt();
        System.out.println("Enter quantity:");
        double quantity = scanner.nextDouble();
        Shop shop = Shop.getShop(shopId);
        Product product = Product.getProduct(productId);
        if (shop != null && product != null && quantity > 0) {
            Storage.shopProducts.add(new ShopProduct(shop, product, quantity));
            System.out.println("Product successfully added to Shop!");
            adminMenu();
        } else {
            System.out.println("Something went wrong!");
            adminMenu();
        }
    }


    static void addShopToList() {
        Shop shop = new Shop();
        shop.addShop();
        if (shop.getName() != null) {
            Storage.shops.add(shop);
            adminMenu();
        } else {
            System.out.println("This ShopName has already taken!");
            adminMenu();
        }
    }

    static void addProductToList() {
        Product product = new Product();
        product.addProduct();
        if (product.getName() != null) {
            Storage.products.add(product);
            System.out.println("Product successfully added!");
            adminMenu();
        } else {
            System.out.println("This product added before!");
            adminMenu();
        }
    }

}
