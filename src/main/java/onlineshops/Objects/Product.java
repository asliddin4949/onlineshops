package onlineshops.Objects;

import lombok.Getter;
import lombok.Setter;
import onlineshops.DataStorage.Storage;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

@Getter
@Setter
public class Product {

    private static int productId = 1;
    private int id;
    private String name;
    private Measurement measurement;
    private BigDecimal price;

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product Name: ");
        String name = scanner.nextLine();
        if (hasProduct(name)) {
            setName(name);
            System.out.println("Choose Measurement:");
            System.out.println("1 - Kg  2 - Piece ");
            scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            if (command == 1) {
                setMeasurement(Measurement.KG);
            } else {
                setMeasurement(Measurement.PIECE);
            }
            System.out.println("Enter price: ");
            scanner = new Scanner(System.in);
            price = scanner.nextBigDecimal();
            setId(productId);
            productId++;
        }
    }

    public static Product getProduct(int id) {
        return Storage.products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    static boolean hasProduct(String name) {
        Product product1 = Storage.products.stream().filter(product ->
                product.name.equals(name)).findFirst().orElse(null);
        return product1 == null;
    }

    public static void showProducts() {
        Storage.products.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Product - " + id +
                "\nName: " + name +
                "\nMeasurement: " + measurement +
                "\nPrice: " + price +" dollars"+
                "\n< : > < = > < : >";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && name.equals(product.name) && measurement == product.measurement && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measurement, price);
    }
}
