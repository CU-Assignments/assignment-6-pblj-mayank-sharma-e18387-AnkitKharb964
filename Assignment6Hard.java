import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class Assignment6Hard {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200),
                new Product("Phone", "Electronics", 800),
                new Product("T-Shirt", "Clothing", 20),
                new Product("Jeans", "Clothing", 40),
                new Product("Blender", "Appliances", 60),
                new Product("Oven", "Appliances", 200)
        );

        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Grouped by Category:");
        groupedByCategory.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        Map<String, Product> mostExpensive = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)),
                                Optional::get
                        )
                ));
        mostExpensive.forEach((k, v) -> System.out.println(k + ": " + v));

        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage Price of All Products: $" + averagePrice);
    }
}
