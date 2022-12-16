package onlineshops.Objects;

import lombok.Getter;
import lombok.Setter;
import onlineshops.DataStorage.Storage;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

@Getter
@Setter
public class Shop {

    private static int shopId = 1;
    private int id;
    private String address;
    private String name;

    public static void showShops() {
       Storage.shops.forEach(System.out::println);
    }

    public void addShop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Shop Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Shop Address: ");
        String address = scanner.nextLine();
        if (checkShop(name,address)) {
            setName(name);
            setAddress(address);
            setId(shopId);
            shopId++;
        }
    }
    public static Shop getShop(int id){
        return Storage.shops.stream().filter(shop -> shop.getId()==id).findFirst().orElse(null);
    }

    static boolean checkShop(String name, String address) {
        Shop checkShop = Storage.shops.stream().filter(shop -> shop.getName().equals(name)
                && shop.getAddress().equals(address)).findFirst().orElse(null);
        return checkShop == null;
    }

    @Override
    public String toString() {
        return "\nShop - " + id +
                "\nName: " + name +
                "\nAddress: " + address +
                "\n< : > < = > < : >";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id && address.equals(shop.address) && name.equals(shop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, name);
    }
}
